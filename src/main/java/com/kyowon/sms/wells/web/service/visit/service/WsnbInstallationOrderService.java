package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallationOrderConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationOrderMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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

    // private final WctbContractDtlStatCdChService contractService; // 계약 서비스

    @Transactional
    public int saveInstallationOrder(SaveReq dto) throws Exception {
        WsnbMultipleTaskOrderDvo multipleTaskOrderDvo = converter.mapSaveReqToMultipleTaskOrderDvo(dto);
        multipleTaskOrderDvo.setInChnlDvCd(dto.inflwChnl()); // FIXME: 확인필요

        String cntrNo = multipleTaskOrderDvo.getCntrNo();
        String cntrSn = multipleTaskOrderDvo.getCntrSn();
        String dataStatCd = multipleTaskOrderDvo.getDtaStatCd();

        // 1. 계약 조회
        WsnbContractReqDvo contractDvo = this.getContractByPk(cntrNo, cntrSn);

        String rcgvpKnm = contractDvo.getRcgvpKnm();

        // 2-1. 당일 계약취소 [AS-IS] LC_ASREGN_API_U03_T -> PR_KIWI_DEL_CSMR
        if (SnServiceConst.SV_BIZ_HCLSF_CD_DEL.equals(multipleTaskOrderDvo.getSvBizHclsfCd())) {
            mapper.insertAsIstCancel(multipleTaskOrderDvo); // 작업취소 입력
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

            // TODO: 계약삭제 서비스 호출 / 파라미터 : 계약번호, 계약일련번호, 계약상세상태코드(303)
            // contractService.editContractDtlStatCdCh(WctbContractDtlStatCdChDvo dvo);

            // mapper.deleteIstAsnIz(cntrNo, cntrSn); // 작업할당 삭제 - 고객서비스설치배정내역
            // mapper.deleteIstOjIz(cntrNo, cntrSn); // 작업할당 삭제 - 고객서비스설치대상내역

            if (SnServiceConst.PG_GRP_CD_WELLS_FARM.equals(contractDvo.getPdctPdGrpCd())) { // 웰스팜기기
                // TODO: 모종계약번호 조회

                // TODO: 모종계약 삭제

                // TODO: 모종 작업할당 삭제

                // TODO: 모종 배종 스케줄 삭제

                // TODO: 모종 구성정보 삭제
            }

            if (SnServiceConst.PG_GRP_CD_WELLS_SEEDING.equals(contractDvo.getPdctPdGrpCd())) { // 웰스팜모종
                // TODO: 모종 배송 스케줄 삭제

                // TODO: 모종 구성정보 삭제
            }
        }

        // 2-2. 설치("11%", "41%")이면 계약-예정일자 업데이트 LC_ASREGN_API_U02_T
        if (multipleTaskOrderDvo.getSvBizDclsfCd().startsWith(SnServiceConst.SV_BIZ_MCLSF_CD_IST)
            || multipleTaskOrderDvo.getSvBizDclsfCd().startsWith(SnServiceConst.SV_BIZ_MCLSF_CD_NEW)) {
            String cttRsCd = "01"; // 91 (컨택완료)
            String sppDuedt = multipleTaskOrderDvo.getVstRqdt();

            if ((SnServiceConst.DTA_STAT_CD_DEL.equals(multipleTaskOrderDvo.getDtaStatCd())
                || SnServiceConst.SV_BIZ_HCLSF_CD_DEL.equals(multipleTaskOrderDvo.getSvBizHclsfCd()))
                && (SnServiceConst.IN_CHNL_DV_CD_WEB.equals(multipleTaskOrderDvo.getInChnlDvCd())
                    && SnServiceConst.SV_BIZ_HCLSF_CD_HOME_CARE.equals(multipleTaskOrderDvo.getSvBizHclsfCd()))) {
                sppDuedt = "";
                cttRsCd = "91"; // 고객이 계약을 취소하고자함,고객과 신속히 재접촉요망!
            }

            // TODO: 방문예정일 컨택코드 업데이트 (계약삭제 서비스 호출)
        }

        // 3. 작업오더 호출
        if (!SnServiceConst.SV_BIZ_HCLSF_CD_DEL.equals(multipleTaskOrderDvo.getSvBizHclsfCd())) {

            // 작업오더 호출 [AS-IS] PR_KIWI_WRK_CREATE_V2
            taskOrderService.saveMultipleTaskOrders(multipleTaskOrderDvo);

            // 웰스팜 설치인 경우 모종설치 작업오더 생성
            if (multipleTaskOrderDvo.getSvBizDclsfCd().startsWith(SnServiceConst.SV_BIZ_MCLSF_CD_IST)
                && SnServiceConst.PG_GRP_CD_WELLS_FARM.equals(contractDvo.getPdctPdGrpCd())) {
                // 모종계약 조회
                List<WsnbOjContractDvo> seedingDvos = mapper
                    .selectOjContract(new WsnbOjContractDvo(cntrNo, cntrSn, "216"));

                // 모종작업오더 조회
                if (Arrays.asList("2", "3").contains(dataStatCd)) { // 수정/취소로 넘어오는 경우 모종작업오더 찾은 후 작업오더호출
                    WsnbOjContractDvo seedingDvo = seedingDvos.get(0);
                    String sidingCntrNo = seedingDvo.getCntrNo();
                    String sidingCntrSn = seedingDvo.getCntrSn();
                    List<String> cstSvAsnNos = mapper.selectSeedingCstSvAsnNos(sidingCntrNo, sidingCntrSn);

                    for (String cstSvAsnNo : cstSvAsnNos) {
                        multipleTaskOrderDvo.setCntrNo(sidingCntrNo);
                        multipleTaskOrderDvo.setCntrSn(sidingCntrSn);
                        multipleTaskOrderDvo.setPuCstSvAsnNo(cstSvAsnNo); // FIXME: 확인 필요
                        taskOrderService.saveMultipleTaskOrders(multipleTaskOrderDvo); // 작업오더 호출
                    }
                }
            }

            // 홈케어 맴버쉽 패키지 설치오더 생성 [AS-IS] LC_ASREGN_API_U06_T
            List<WsnbOjContractDvo> homecareDvos = mapper
                .selectOjContract(new WsnbOjContractDvo(cntrNo, cntrSn, "221"));

            for (WsnbOjContractDvo homecareDvo : homecareDvos) {
                multipleTaskOrderDvo.setCntrNo(homecareDvo.getCntrNo());
                multipleTaskOrderDvo.setCntrSn(homecareDvo.getCntrSn());
                multipleTaskOrderDvo.setPuCstSvAsnNo(null); // FIXME: 확인 필요
                taskOrderService.saveMultipleTaskOrders(multipleTaskOrderDvo); // 작업오더 호출
            }
        }

        return 0;
    }

    public WsnbContractReqDvo getContractByPk(String cntrNo, String cntrSn) {
        return mapper.selectContractByPk(cntrNo, cntrSn).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

}
