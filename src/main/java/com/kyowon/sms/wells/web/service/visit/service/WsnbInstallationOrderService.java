package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallationOrderConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractReqDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbOjContractDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaskProgStatDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationOrderMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.16
 * @see [AS-IS] LC_ASREGN_API_000
 */
@Service
@RequiredArgsConstructor
public class WsnbInstallationOrderService {

    private final WsnbInstallationOrderMapper mapper;

    private final WsnbInstallationOrderConverter converter;

    private final WsnbMultipleTaskOrderService taskOrderService; // 작업오더 서비스

    private final WctbContractDtlStatCdChService contractDtlService; // 계약상세상태변경 서비스

    private final WctaInstallationReqdDtInService contractIstService; // 계약설치요청일자변경 서비스

    public String saveInstallationOrder(SaveReq dto) throws Exception {
        WsnbMultipleTaskOrderDvo multipleTaskOrderDvo = converter.mapSaveReqToMultipleTaskOrderDvo(dto);
        multipleTaskOrderDvo.setInChnlDvCd(dto.inflwChnl()); // FIXME: 확인필요
        return this.saveInstallationOrderByDvo(multipleTaskOrderDvo);
    }

    @Transactional
    public String saveInstallationOrderByDvo(WsnbMultipleTaskOrderDvo dvo) throws Exception {
        String cntrNo = dvo.getCntrNo();
        String cntrSn = dvo.getCntrSn();
        String mtrStatCd = dvo.getMtrStatCd();
        String asIstOjNo = null;

        // 1. 계약 조회
        WsnbContractReqDvo contractDvo = this.getContractByPk(cntrNo, cntrSn);

        String rcgvpKnm = contractDvo.getRcgvpKnm();

        // 2-1. 당일 계약취소 [AS-IS] LC_ASREGN_API_U03_T -> PR_KIWI_DEL_CSMR
        if (SV_BIZ_HCLSF_CD_DEL.equals(dvo.getSvBizHclsfCd())) {
            mapper.insertAsIstCancel(dvo); // 작업취소 입력
            WsnbTaskProgStatDvo taskProgStatDvo = mapper.selectTaskProgStat(cntrNo, cntrSn); // 작업진행상태 조회

            BizAssert.isTrue(
                taskProgStatDvo.getAcpteCt() == 0,
                rcgvpKnm + "고객님에게 당일 방문 예정인 작업이 있습니다.\r엔지니어와 협의 하여 해당 작업 취소 이후에 처리 부탁 드리겠습니다."
            );
            BizAssert.isTrue(
                taskProgStatDvo.getVstCnfmCt() == 0,
                rcgvpKnm + "고객님에게, 엔지니어가 방문 확정된 작업이 있습니다.\r엔지니어와 협의 하여 해당 작업 취소 이후에 처리 부탁 드리겠습니다."
            );
            BizAssert.isTrue(
                taskProgStatDvo.getFshCt() == 0, rcgvpKnm + "고객님에게 완료된 작업이 있습니다.\r엔지니어에게 확인 부탁 드리겠습니다."
            );

            this.removeContract(cntrNo, cntrSn); // 계약 삭제 및 작업할당 삭제

            if (PG_GRP_CD_WELLS_FARM.equals(contractDvo.getPdctPdGrpCd())) { // 웰스팜기기
                // 모종계약번호 조회
                List<WsnbOjContractDvo> seedingDvos = mapper
                    .selectOjContract(new WsnbOjContractDvo(cntrNo, cntrSn, CNTR_REL_DTL_CD_SDING_COMBI));

                // 모종계약 삭제
                for (WsnbOjContractDvo seedingDvo : seedingDvos) {
                    String sidingCntrNo = seedingDvo.getCntrNo();
                    String sidingCntrSn = seedingDvo.getCntrSn();

                    this.removeContract(sidingCntrNo, sidingCntrSn); // 계약 삭제 및 작업할당 삭제

                    // TODO: 모종 배송, 구성정보 삭제 시 키 값 확인
                    mapper.deleteSdingSppPlan(dvo); // 모종 배송 스케줄 삭제
                    mapper.deleteSdingSppExpIz(dvo); // 모종 배송 예정내역(구성정보) 삭제
                }
            }

            if (PG_GRP_CD_WELLS_SEEDING.equals(contractDvo.getPdctPdGrpCd())) { // 웰스팜모종
                mapper.deleteSdingSppPlan(dvo); // 모종 배송 스케줄 삭제
                mapper.deleteSdingSppExpIz(dvo); // 모종 배송 예정내역(구성정보) 삭제
            }
        }

        // 2-2. 설치("11%", "41%")이면 계약-예정일자 업데이트 LC_ASREGN_API_U02_T
        if (dvo.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_IST)
            || dvo.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_NEW)) {
            String cttRsCd = "01"; // 91 (컨택완료)
            String sppDuedt = dvo.getVstRqdt();

            if ((MTR_STAT_CD_DEL.equals(dvo.getMtrStatCd())
                || SV_BIZ_HCLSF_CD_DEL.equals(dvo.getSvBizHclsfCd()))
                && (IN_CHNL_DV_CD_WEB.equals(dvo.getInChnlDvCd())
                    && SV_BIZ_HCLSF_CD_HOME_CARE.equals(dvo.getSvBizHclsfCd()))) {
                sppDuedt = "";
                cttRsCd = "91"; // 고객이 계약을 취소하고자함,고객과 신속히 재접촉요망!
            }

            // 방문예정일, TODO: 컨택코드 업데이트
            contractIstService.saveInstallOrderReqDt(cntrNo, cntrSn, sppDuedt);
        }

        // 3. 작업오더 호출
        if (!SV_BIZ_HCLSF_CD_DEL.equals(dvo.getSvBizHclsfCd())) {

            // 작업오더 호출 [AS-IS] PR_KIWI_WRK_CREATE_V2
            asIstOjNo = taskOrderService.saveMultipleTaskOrders(dvo);

            // 웰스팜 설치인 경우 모종설치 작업오더 생성
            if (dvo.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_IST)
                && PG_GRP_CD_WELLS_FARM.equals(contractDvo.getPdctPdGrpCd())) {
                // 모종계약 조회
                List<WsnbOjContractDvo> seedingDvos = mapper
                    .selectOjContract(new WsnbOjContractDvo(cntrNo, cntrSn, CNTR_REL_DTL_CD_SDING_COMBI));

                // 모종작업오더 조회
                if (Arrays.asList("2", "3").contains(mtrStatCd)) { // 수정/취소로 넘어오는 경우 모종작업오더 찾은 후 작업오더호출
                    WsnbOjContractDvo seedingDvo = seedingDvos.get(0);
                    String sidingCntrNo = seedingDvo.getCntrNo();
                    String sidingCntrSn = seedingDvo.getCntrSn();
                    List<String> cstSvAsnNos = mapper.selectSeedingCstSvAsnNos(sidingCntrNo, sidingCntrSn);

                    for (String cstSvAsnNo : cstSvAsnNos) {
                        dvo.setCntrNo(sidingCntrNo);
                        dvo.setCntrSn(sidingCntrSn);
                        taskOrderService.saveMultipleTaskOrders(dvo); // 작업오더 호출
                    }
                }
            }

            // 홈케어 맴버쉽 패키지 설치오더 생성 [AS-IS] LC_ASREGN_API_U06_T
            List<WsnbOjContractDvo> homecareDvos = mapper
                .selectOjContract(new WsnbOjContractDvo(cntrNo, cntrSn, CNTR_REL_DTL_CD_HOMECARE_MEMBERSHIP));

            for (WsnbOjContractDvo homecareDvo : homecareDvos) {
                dvo.setCntrNo(homecareDvo.getCntrNo());
                dvo.setCntrSn(homecareDvo.getCntrSn());
                taskOrderService.saveMultipleTaskOrders(dvo); // 작업오더 호출
            }
        }

        return asIstOjNo;
    }

    /**
     * 계약 삭제 및 작업할당 삭제
     * @param cntrNo
     * @param cntrSn
     * @throws Exception
     */
    private void removeContract(String cntrNo, String cntrSn) throws Exception {
        WctbContractDtlStatCdChDvo contractDtlStatCdChDvo = new WctbContractDtlStatCdChDvo();
        contractDtlStatCdChDvo.setCntrNo(cntrNo);
        contractDtlStatCdChDvo.setCntrSn(cntrSn);
        contractDtlStatCdChDvo.setCntrDtlStatCd(CNTR_DTL_STAT_CD_CANCEL); // "303" 계약취소 상태로 set

        // 계약상세상태 업데이트
        contractDtlService.editContractDtlStatCdCh(contractDtlStatCdChDvo);

        mapper.deleteSvExcnIz(cntrNo, cntrSn); // 계약 삭제 후 고객서비스수행내역 삭제
        mapper.deleteIstAsnIz(cntrNo, cntrSn); // 작업할당 삭제 - 고객서비스설치배정내역
        mapper.deleteIstOjIz(cntrNo, cntrSn); // 작업할당 삭제 - 고객서비스설치대상내역
    }

    /**
     * 계약상세일련번호로 계약정보 조회
     * @param cntrNo
     * @param cntrSn
     * @return
     */
    public WsnbContractReqDvo getContractByPk(String cntrNo, String cntrSn) {
        return mapper.selectContractByPk(cntrNo, cntrSn).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

}
