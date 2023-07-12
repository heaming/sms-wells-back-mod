package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.FormatAddressDvo;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sflex.common.zcommon.constants.CmSujiewonConst;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractInstallService;
import com.kyowon.sms.wells.web.service.interfaces.converter.WsnbWorkOrderInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbWorkOrderInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbWorkOrderInterfaceMapper;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationOrderService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWorkOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-I-0009 타시스템(교원웰스, 고객센터, KMEMBERS)에서 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbWorkOrderInterfaceService {

    private final WsnbWorkOrderInterfaceMapper mapper;

    private final WsnbWorkOrderInterfaceConverter converter;

    private final SujiewonService sujiewonService;

    private final WsnbInstallationOrderService installationOrderService; // [W-SV-S-0001]

    private final WsnbWorkOrderService taskOrderService; // [W-SV-S-0012]

    private final WctiContractInstallService contractInstallService; // 계약설치정보변경 서비스

    @Transactional
    public List<CreateOrderRes> createWorkOrders(List<CreateOrderReq> dtos) throws Exception {
        List<WsnbWorkOrderInterfaceDvo> orders = converter.mapAllCreateOrderReqToDvos(dtos);
        List<CreateOrderRes> orderRes = new ArrayList<>();

        for (WsnbWorkOrderInterfaceDvo order : orders) {

            if (order.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_IST)) { // 1. 설치 요청
                CreateOrderRes res = this.createInstallOrder(order);
                orderRes.add(res);

            } else if (order.getSvBizDclsfCd().startsWith(SV_BIZ_LCLSF_CD_SEP)) { // 2. 분리 요청
                CreateOrderRes res = this.createSeparateOrder(order);
                orderRes.add(res);

            } else { // 3. 기타 요청
                CreateOrderRes res = this.createEtcOrder(order);
                orderRes.add(res);
            }
        }

        return orderRes;
    }

    /**
     * 설치 오더 생성
     * @param install
     * @return CreateOrderRes
     */
    private CreateOrderRes createInstallOrder(WsnbWorkOrderInterfaceDvo install) throws Exception {
        WsnbWorkOrderDvo multiTaskOrderDvo = converter.convertInterfaceDvoToDvo(install);

        // W-SV-S-0001 [설치/AS/BS/홈케어 서비스 작업 오더] 호출
        String asIstOjNo = installationOrderService.saveInstallationOrderByDvo(multiTaskOrderDvo);
        return new CreateOrderRes(asIstOjNo);
    }

    /**
     * 분리 오더 생성
     * @param separate
     * @return CreateOrderRes
     */
    private CreateOrderRes createSeparateOrder(WsnbWorkOrderInterfaceDvo separate) throws Exception {
        WsnbWorkOrderDvo workOrderDvo = converter.convertInterfaceDvoToDvo(separate);

        // 1. W-SV-S-0012 [다건 작업오더, 정보변경 처리] 호출
        String asIstOjNo = taskOrderService.saveWsnbWorkOrders(workOrderDvo);

        // 2. 정보변경 처리
        this.editContract(workOrderDvo, separate);

        return new CreateOrderRes(asIstOjNo);
    }

    /**
     * 기타 오더 생성 (계약주소 업데이트)
     * @return CreateOrderRes
     */
    private CreateOrderRes createEtcOrder(WsnbWorkOrderInterfaceDvo etc) throws Exception {
        WsnbWorkOrderDvo workOrderDvo = converter.convertInterfaceDvoToDvo(etc);

        // 1. W-SV-S-0012 [다건 작업오더, 정보변경 처리] 호출
        String asIstOjNo = taskOrderService.saveWsnbWorkOrders(workOrderDvo);

        // 2. 재설치 오더와 정보변경 오더일 경우 정보변경 처리
        if (etc.getSvBizDclsfCd().startsWith(SV_BIZ_LCLSF_CD_REINSTALL)
            || etc.getSvBizDclsfCd().startsWith(SV_BIZ_HCLSF_CD_INFO_CHANGE)) {
            this.editContract(workOrderDvo, etc);
        }

        return new CreateOrderRes(asIstOjNo);
    }

    /**
     * 계약주소 및 연락처 업데이트
     * @param workOrderDvo
     * @param ifDvo
     * @throws Exception
     */
    private void editContract(WsnbWorkOrderDvo workOrderDvo, WsnbWorkOrderInterfaceDvo ifDvo)
        throws Exception {
        // 0. 기존 계약 주소 조회
        String oldAdrId = mapper.selectContractAdr(ifDvo);

        // 1. 수지원넷 주소정제
        FormatAddressDvo formatAddress = sujiewonService.getFormattedAddress(
            ifDvo.getIstAdr() + " " + ifDvo.getIstDtlAdr(), CmSujiewonConst.FORMAT_TYPE_ROAD_ADDRESS
        );

        log.info("=== Sujiewon formatAddress ===");
        log.info(formatAddress.toString());

        // 2. 계약주소 업데이트 (기존 계약 ADR_ID와 다른 경우에만 업데이트)
        if (!formatAddress.getAdrId().equals(oldAdrId)) {
            workOrderDvo.setAdrId(formatAddress.getAdrId());
            workOrderDvo.setCralLocaraTno(ifDvo.getMtcmco());
            workOrderDvo.setMexno(ifDvo.getCphonIdvTno1());
            workOrderDvo.setCralIdvTno(ifDvo.getCphonIdvTno2());
            workOrderDvo.setLocaraTno(ifDvo.getLocaraTno());
            workOrderDvo.setExno(ifDvo.getIdvTno1());
            workOrderDvo.setIdvTno(ifDvo.getIdvTno2());

            contractInstallService.editContractInstall(converter.mapDvoToContractSaveReq(workOrderDvo));
        }

    }

}
