package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.CNTR_REL_DTL_CD_HOMECARE_MEMBERSHIP;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.CNTR_REL_DTL_CD_SDING_COMBI;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.IN_CHNL_DV_CD_WEB;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.MTR_STAT_CD_DEL;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.PG_GRP_CD_WELLS_FARM;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.PG_GRP_CD_WELLS_SEEDING;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.SV_BIZ_HCLSF_CD_DEL;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.SV_BIZ_HCLSF_CD_HOME_CARE;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.SV_BIZ_MCLSF_CD_IST;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.SV_BIZ_MCLSF_CD_NEW;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallationOrderConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractRelationDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkProgStatDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationOrderMapper;
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

    private final WsnbWorkOrderService workOrderService; // 작업오더 서비스

    private final WsnbContractService contractService;

    private final WctaInstallationReqdDtInService contractIstService; // 계약설치요청일자변경 서비스

    public String saveInstallationOrder(SaveReq dto) throws Exception {
        WsnbWorkOrderDvo workOrderDvo = converter.mapSaveReqToWorkOrderDvo(dto);
        workOrderDvo.setInChnlDvCd(dto.inflwChnl());

        // TODO: 파라미터 로그 저장

        return this.saveInstallationOrderByDvo(workOrderDvo);
    }

    @Transactional
    public String saveInstallationOrderByDvo(WsnbWorkOrderDvo workOrder) throws Exception {
        String cntrNo = workOrder.getCntrNo();
        String cntrSn = workOrder.getCntrSn();
        String asIstOjNo = null;

        // 계약 조회
        WsnbContractDvo contract = contractService.getContract(cntrNo, cntrSn);

        // 1. 당일 계약취소 [AS-IS] LC_ASREGN_API_U03_T -> PR_KIWI_DEL_CSMR
        if (SV_BIZ_HCLSF_CD_DEL.equals(workOrder.getSvBizHclsfCd())) {
            processContractCancel(workOrder, contract);
        }

        // 2. 설치("11%", "41%")이면 계약-예정일자 업데이트 LC_ASREGN_API_U02_T
        if (workOrder.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_IST)
            || workOrder.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_NEW)) {
            processContractUpdate(workOrder);
        }

        // 3. 작업오더 호출
        if (!SV_BIZ_HCLSF_CD_DEL.equals(workOrder.getSvBizHclsfCd())) {
            asIstOjNo = saveWorkOrder(workOrder, contract);
        }

        return asIstOjNo;
    }

    private String saveWorkOrder(
        WsnbWorkOrderDvo workOrder, WsnbContractDvo contract
    ) throws Exception {
        String cntrNo = workOrder.getCntrNo();
        String cntrSn = workOrder.getCntrSn();
        String mtrStatCd = workOrder.getMtrStatCd();

        // 작업오더 호출 [AS-IS] PR_KIWI_WRK_CREATE_V2
        String asIstOjNo = workOrderService.saveWorkOrders(workOrder);

        // 웰스팜 설치인 경우 모종설치 작업오더 생성
        if (workOrder.getSvBizDclsfCd().startsWith(SV_BIZ_MCLSF_CD_IST)
            && PG_GRP_CD_WELLS_FARM.equals(contract.getPdctPdGrpCd())) {
            // 모종계약 조회
            List<WsnbContractRelationDvo> sdingContractRelations = contractService
                .getContractRelation(cntrNo, cntrSn, CNTR_REL_DTL_CD_SDING_COMBI);

            for (WsnbContractRelationDvo sdingContractRelation : sdingContractRelations) {
                String sdingCntrNo = sdingContractRelation.getOjDtlCntrNo();
                String sdingCntrSn = sdingContractRelation.getOjDtlCntrSn();

                workOrder.setCntrNo(sdingCntrNo);
                workOrder.setCntrSn(sdingCntrSn);

                // 모종작업오더 조회
                if (Arrays.asList("2", "3").contains(mtrStatCd)) { // 수정/취소로 넘어오는 경우 모종작업오더 찾은 후 작업오더호출
                    List<String> sdingAsIstOjNos = mapper.selectSdingAsIstOjNos(sdingCntrNo, sdingCntrSn);
                    workOrder.setAsIstOjNo(sdingAsIstOjNos.get(0));
                }

                workOrderService.saveWorkOrders(workOrder); // 작업오더 호출
            }
        }

        // 홈케어 맴버쉽 패키지 설치오더 생성 [AS-IS] LC_ASREGN_API_U06_T
        List<WsnbContractRelationDvo> homeCareContractRelations = contractService
            .getContractRelation(cntrNo, cntrSn, CNTR_REL_DTL_CD_HOMECARE_MEMBERSHIP);

        for (WsnbContractRelationDvo homeCareContractRelation : homeCareContractRelations) {
            workOrder.setCntrNo(homeCareContractRelation.getOjDtlCntrNo());
            workOrder.setCntrSn(homeCareContractRelation.getOjDtlCntrSn());
            workOrderService.saveWorkOrders(workOrder); // 작업오더 호출
        }

        return asIstOjNo;
    }

    private void processContractUpdate(WsnbWorkOrderDvo workOrder) {
        String cntrNo = workOrder.getCntrNo();
        String cntrSn = workOrder.getCntrSn();

        String lcCttRsCd = "01"; // 91 (컨택완료)
        String sppDuedt = workOrder.getVstRqdt();

        if ((MTR_STAT_CD_DEL.equals(workOrder.getMtrStatCd())
            || SV_BIZ_HCLSF_CD_DEL.equals(workOrder.getSvBizHclsfCd()))
            && (IN_CHNL_DV_CD_WEB.equals(workOrder.getInChnlDvCd())
                && SV_BIZ_HCLSF_CD_HOME_CARE.equals(workOrder.getSvBizHclsfCd()))) {
            sppDuedt = "";
            lcCttRsCd = "91"; // 고객이 계약을 취소하고자함,고객과 신속히 재접촉요망!
        }

        // 방문예정일, 컨택코드 업데이트
        contractIstService.saveInstallOrderReqDt(cntrNo, cntrSn, sppDuedt, lcCttRsCd);
    }

    private void processContractCancel(
        WsnbWorkOrderDvo workOrder, WsnbContractDvo contract
    ) throws Exception {
        String cntrNo = contract.getCntrNo();
        String cntrSn = contract.getCntrSn();
        String rcgvpKnm = contract.getRcgvpKnm();

        mapper.insertAsIstCancel(workOrder); // 작업취소 입력
        WsnbWorkProgStatDvo workProgStat = mapper.selectWorkProgStat(cntrNo, cntrSn); // 작업진행상태 조회

        BizAssert.isTrue(
            workProgStat.getAcpteCt() == 0,
            rcgvpKnm + "고객님에게 당일 방문 예정인 작업이 있습니다.\r엔지니어와 협의 하여 해당 작업 취소 이후에 처리 부탁 드리겠습니다."
        );
        BizAssert.isTrue(
            workProgStat.getVstCnfmCt() == 0,
            rcgvpKnm + "고객님에게, 엔지니어가 방문 확정된 작업이 있습니다.\r엔지니어와 협의 하여 해당 작업 취소 이후에 처리 부탁 드리겠습니다."
        );
        BizAssert.isTrue(
            workProgStat.getFshCt() == 0, rcgvpKnm + "고객님에게 완료된 작업이 있습니다.\r엔지니어에게 확인 부탁 드리겠습니다."
        );

        this.removeContract(cntrNo, cntrSn); // 계약 삭제 및 작업할당 삭제

        if (PG_GRP_CD_WELLS_FARM.equals(contract.getPdctPdGrpCd())) { // 웰스팜기기
            // 모종계약번호 조회
            List<WsnbContractRelationDvo> sdingContractRelations = contractService
                .getContractRelation(cntrNo, cntrSn, CNTR_REL_DTL_CD_SDING_COMBI);

            // 모종계약 삭제
            for (WsnbContractRelationDvo sdingContractRelation : sdingContractRelations) {
                String sdingCntrNo = sdingContractRelation.getOjDtlCntrNo();
                String sdingCntrSn = sdingContractRelation.getOjDtlCntrSn();

                this.removeContract(sdingCntrNo, sdingCntrSn); // 계약 삭제 및 작업할당 삭제

                mapper.deleteSdingSppPlan(sdingCntrNo, sdingCntrSn); // 모종 배송 스케줄 삭제
                mapper.deleteSdingSppExpIz(sdingCntrNo, sdingCntrSn); // 모종 배송 예정내역(구성정보) 삭제
            }
        }

        if (PG_GRP_CD_WELLS_SEEDING.equals(contract.getPdctPdGrpCd())) { // 웰스팜모종
            mapper.deleteSdingSppPlan(workOrder.getCntrNo(), workOrder.getCntrSn()); // 모종 배송 스케줄 삭제
            mapper.deleteSdingSppExpIz(workOrder.getCntrNo(), workOrder.getCntrSn()); // 모종 배송 예정내역(구성정보) 삭제
        }
    }

    /**
     * 계약 삭제 및 작업할당 삭제
     * @param cntrNo
     * @param cntrSn
     * @throws Exception
     */
    private void removeContract(String cntrNo, String cntrSn) throws Exception {
        mapper.deleteSvExcnIz(cntrNo, cntrSn); // 계약 삭제 후 고객서비스수행내역 삭제
        mapper.deleteIstAsnIz(cntrNo, cntrSn); // 작업할당 삭제 - 고객서비스설치배정내역
        mapper.deleteIstOjIz(cntrNo, cntrSn); // 작업할당 삭제 - 고객서비스설치대상내역
    }

}
