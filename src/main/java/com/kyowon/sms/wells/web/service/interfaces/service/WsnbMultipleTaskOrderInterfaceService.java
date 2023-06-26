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
import com.kyowon.sms.wells.web.service.interfaces.converter.WsnbMultipleTaskOrderInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbMultipleTaskOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbMultipleTaskOrderInterfaceDto.CreateOrderRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbMultipleTaskOrderInterfaceDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;
import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationOrderService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbMultipleTaskOrderService;

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
public class WsnbMultipleTaskOrderInterfaceService {

    private final WsnbMultipleTaskOrderInterfaceConverter converter;

    private final SujiewonService sujiewonService;

    private final WsnbInstallationOrderService installationOrderService; // [W-SV-S-0001]

    private final WsnbMultipleTaskOrderService taskOrderService; // [W-SV-S-0012]

    private final WctiContractInstallService contractInstallService; // 계약설치정보변경 서비스

    @Transactional
    public List<CreateOrderRes> createMultipleTaskOrders(List<CreateOrderReq> dtos) throws Exception {
        List<WsnbMultipleTaskOrderInterfaceDvo> orders = converter.mapAllCreateOrderReqToDvos(dtos);
        List<CreateOrderRes> orderRes = new ArrayList<>();

        for (WsnbMultipleTaskOrderInterfaceDvo order : orders) {

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
    private CreateOrderRes createInstallOrder(WsnbMultipleTaskOrderInterfaceDvo install) throws Exception {
        WsnbMultipleTaskOrderDvo multiTaskOrderDvo = this.convertInterfaceDvoToDvo(install);

        // W-SV-S-0001 [설치/AS/BS/홈케어 서비스 작업 오더] 호출
        String asIstOjNo = installationOrderService.saveInstallationOrderByDvo(multiTaskOrderDvo);
        return CreateOrderRes.builder().asIstOjNo(asIstOjNo).build();
    }

    /**
     * 분리 오더 생성
     * @param separate
     * @return CreateOrderRes
     */
    private CreateOrderRes createSeparateOrder(WsnbMultipleTaskOrderInterfaceDvo separate) throws Exception {
        WsnbMultipleTaskOrderDvo multiTaskOrderDvo = this.convertInterfaceDvoToDvo(separate);

        // 1. W-SV-S-0012 [다건 작업오더, 정보변경 처리] 호출
        String asIstOjNo = taskOrderService.saveMultipleTaskOrders(multiTaskOrderDvo);

        // 2. 정보변경 처리
        this.editContract(multiTaskOrderDvo, separate);

        return CreateOrderRes.builder().asIstOjNo(asIstOjNo).build();
    }

    /**
     * 기타 오더 생성 (계약주소 업데이트)
     * @return CreateOrderRes
     */
    private CreateOrderRes createEtcOrder(WsnbMultipleTaskOrderInterfaceDvo etc) throws Exception {
        WsnbMultipleTaskOrderDvo multiTaskOrderDvo = this.convertInterfaceDvoToDvo(etc);

        // 1. W-SV-S-0012 [다건 작업오더, 정보변경 처리] 호출
        String asIstOjNo = taskOrderService.saveMultipleTaskOrders(multiTaskOrderDvo);

        // 2. 재설치 오더와 정보변경 오더일 경우 정보변경 처리
        if (etc.getSvBizDclsfCd().startsWith(SV_BIZ_LCLSF_CD_REINSTALL)
            || etc.getSvBizDclsfCd().startsWith(SV_BIZ_HCLSF_CD_INFO_CHANGE)) {
            this.editContract(multiTaskOrderDvo, etc);
        }

        return CreateOrderRes.builder().asIstOjNo(asIstOjNo).build();
    }

    /**
     * 계약주소 및 연락처 업데이트
     * @param multiTaskOrderDvo
     * @param ifDvo
     * @throws Exception
     */
    private void editContract(WsnbMultipleTaskOrderDvo multiTaskOrderDvo, WsnbMultipleTaskOrderInterfaceDvo ifDvo)
        throws Exception {
        // 1. 수지원넷 주소정제
        FormatAddressDvo formatAddress = sujiewonService.getFormattedAddress(
            ifDvo.getIstAdr() + " " + ifDvo.getIstDtlAdr(), CmSujiewonConst.FORMAT_TYPE_ROAD_ADDRESS
        );

        log.info("=== Sujiewon formatAddress ===");
        log.info(formatAddress.toString());

        multiTaskOrderDvo.setAdrId(formatAddress.getAdrId());
        multiTaskOrderDvo.setCralLocaraTno(ifDvo.getMtcmco());
        multiTaskOrderDvo.setMexno(ifDvo.getCphonIdvTno1());
        multiTaskOrderDvo.setCralIdvTno(ifDvo.getCphonIdvTno2());
        multiTaskOrderDvo.setLocaraTno(ifDvo.getLocaraTno());
        multiTaskOrderDvo.setExno(ifDvo.getIdvTno1());
        multiTaskOrderDvo.setIdvTno(ifDvo.getIdvTno2());

        // 2. 계약주소 업데이트
        contractInstallService.editContractInstall(converter.mapDvoToContractSaveReq(multiTaskOrderDvo));
    }

    private WsnbMultipleTaskOrderDvo convertInterfaceDvoToDvo(WsnbMultipleTaskOrderInterfaceDvo dvo) {
        WsnbMultipleTaskOrderDvo multiTaskOrderDvo = new WsnbMultipleTaskOrderDvo();
        multiTaskOrderDvo.setInChnlDvCd(dvo.getInChnlDvCd());
        multiTaskOrderDvo.setSvBizHclsfCd(dvo.getSvBizHclsfCd());
        multiTaskOrderDvo.setRcpdt(dvo.getRcpdt());
        multiTaskOrderDvo.setAsIstOjNo(dvo.getAsIstOjNo());
        multiTaskOrderDvo.setMtrStatCd(dvo.getMtrStatCd());
        multiTaskOrderDvo.setSvBizDclsfCd(dvo.getSvBizDclsfCd());
        multiTaskOrderDvo.setCntrNo(dvo.getCntrNo());
        multiTaskOrderDvo.setCntrSn(dvo.getCntrSn());
        multiTaskOrderDvo.setVstRqdt(dvo.getVstRqdt());
        multiTaskOrderDvo.setVstAkHh(dvo.getVstAkHh());
        multiTaskOrderDvo.setUrgtYn(dvo.getUrgtYn());
        multiTaskOrderDvo.setSmsFwYn(dvo.getSmsFwYn());
        multiTaskOrderDvo.setSvEtAmt(Integer.parseInt(dvo.getSvEtAmt()));
        multiTaskOrderDvo.setDpDvCd(dvo.getDpDvCd());
        multiTaskOrderDvo.setCnslTpHclsfCd(dvo.getCnslTpHclsfCd());
        multiTaskOrderDvo.setCnslTpMclsfCd(dvo.getCnslTpMclsfCd());
        multiTaskOrderDvo.setCnslTpLclsfCd(dvo.getCnslTpLclsfCd());
        multiTaskOrderDvo.setCnslDtlpTpCd(dvo.getCnslDtlpTpCd());
        multiTaskOrderDvo.setCnslMoCn(dvo.getCnslMoCn());
        multiTaskOrderDvo.setCstCnrRefriDvCd(dvo.getAsRefriDvCd());
        multiTaskOrderDvo.setPartList(dvo.getPrchsMatList());
        multiTaskOrderDvo.setUserId(dvo.getRegUserId());
        return multiTaskOrderDvo;
    }

}
