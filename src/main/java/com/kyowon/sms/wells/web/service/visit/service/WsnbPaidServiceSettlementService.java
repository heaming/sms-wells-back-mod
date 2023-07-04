package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.*;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbPaidServiceSettlementConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPaidServiceSettlementDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPaidServiceSettlementDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbPaidServiceSettlementDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbPaidServiceSettlementMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0094 유상 서비스 결제정보 생성
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.28
 */
@Service
@RequiredArgsConstructor
public class WsnbPaidServiceSettlementService {

    private final WsnbPaidServiceSettlementMapper mapper;

    private final WsnbPaidServiceSettlementConverter converter;

    @Transactional
    public int savePaidServiceSettlements(SaveReq dto) {
        int queryFlag = 0;
        String reqPrgsStatCd = dto.reqPrgsStatCd(); // 요청진행상태코드

        if (REQ_PRGS_STAT_RECEIVE.equals(reqPrgsStatCd)) { // 1. 수납요청정보 생성
            WsnbPaidServiceSettlementDvo costDepositDvo = converter.mapCostDepositToDvo(dto.costDeposit());
            costDepositDvo.setReqPrgsStatCd(reqPrgsStatCd); // 요청진행상태코드 set
            costDepositDvo.setRveAkNo(dto.rveAkNo()); // 수납요청번호 set

            this.saveSvCsDpIz(costDepositDvo); // 서비스비용입금내역 merge

            if (dto.creditCard() != null) { // 서비스비용신용카드처리내역 merge
                this.saveSvCsCrdcdPcsIz(dto.creditCard());
            }

            if (dto.virtualAccount() != null) { // 서비스비용가상계좌처리내역 merge
                this.saveSvCsVacPcsIz(dto.virtualAccount());
            }

            queryFlag = mapper.updateSvCsBilBas(costDepositDvo); // 서비스비용청구기본 - 수납요청번호, 결제진행상태코드("01" 청구등록) 업데이트
            BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_BIL_BAS"});

            costDepositDvo.setAdpBilStatCd(ADP_BIL_STAT_CD_PRGS); // 합산청구상태코드("02" 진행중)
            this.editAdpBilStatCd(costDepositDvo);

        } else if (Arrays.asList(REQ_PRGS_STAT_CRDCD_STLM, REQ_PRGS_STAT_CDCO_DP).contains(reqPrgsStatCd)) { // 2. "2" : 신용카드 계약 결제정보 or "3" : 신용카드 카드사 입금정보
            WsnbPaidServiceSettlementDvo costDepositDvo = converter.mapCostDepositToDvo(dto.costDeposit());
            WsnbPaidServiceSettlementDvo creditCardDvo = converter.mapCreditCardToDvo(dto.creditCard());
            creditCardDvo.setReqPrgsStatCd(reqPrgsStatCd); // 요청진행상태코드 set

            this.saveSvCsDpIz(costDepositDvo); // 서비스비용입금내역 merge
            this.saveSvCsCrdcdPcsIz(dto.creditCard()); // 서비스비용신용카드처리내역 merge

            if (REQ_PRGS_STAT_CRDCD_STLM.equals(reqPrgsStatCd)) { // "2" : 신용카드 결제정보 생성 요청인 경우
                if (Arrays.asList(APR_DV_CD_CRD_APR, APR_DV_CD_CSH_APR).contains(creditCardDvo.getAprDvCd())) { // 승인구분코드
                    creditCardDvo.setStlmPrgsStatCd(STLM_PRGS_STAT_CD_APR); // 청구승인
                } else if (Arrays.asList(APR_DV_CD_CRD_CAN, APR_DV_CD_CSH_CAN).contains(creditCardDvo.getAprDvCd())) {
                    creditCardDvo.setStlmPrgsStatCd(STLM_PRGS_STAT_CD_FAIL); // 청구실패
                }
                queryFlag = mapper.updateSvCsBilBas(creditCardDvo); // 서비스비용청구기본 - 결제진행상태코드 업데이트
                BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_BIL_BAS"});

            } else if (REQ_PRGS_STAT_CDCO_DP.equals(reqPrgsStatCd)) { // "3" : 신용카드 입금정보 생성 요청인 경우
                this.saveStatCdByAmount(costDepositDvo, "MSG_ALT_CRDCD_DP_INF_ERR"); // 결제진행상태, 합산청구상태 업데이트
            }

        } else if (REQ_PRGS_STAT_VAC_DP.equals(reqPrgsStatCd)) { // 4. 가상계좌 입금정보 생성
            WsnbPaidServiceSettlementDvo costDepositDvo = converter.mapCostDepositToDvo(dto.costDeposit());
            WsnbPaidServiceSettlementDvo virtualAccountDvo = converter.mapVirtualAccountToDvo(dto.virtualAccount());
            virtualAccountDvo.setReqPrgsStatCd(reqPrgsStatCd); // 요청진행상태코드 set

            this.saveSvCsDpIz(costDepositDvo); // 서비스비용입금내역 merge
            this.saveSvCsVacPcsIz(dto.virtualAccount()); // 서비스비용가상계좌처리내역 merge
            this.saveStatCdByAmount(costDepositDvo, "MSG_ALT_VAC_DP_INF_ERR"); // 결제진행상태, 합산청구상태 업데이트
        }

        return queryFlag;
    }

    /**
     * 서비스비용입금내역 merge
     * @param costDepositDvo
     */
    private void saveSvCsDpIz(WsnbPaidServiceSettlementDvo costDepositDvo) {
        int queryFlag = mapper.mergeSvCsDpIz(costDepositDvo);
        BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_DP_IZ"});
    }

    /**
     * 서비스비용신용카드처리내역 merge
     * @param dto
     */
    private void saveSvCsCrdcdPcsIz(WsnbPaidServiceSettlementDto.CreditCard dto) {
        WsnbPaidServiceSettlementDvo creditCardDvo = converter.mapCreditCardToDvo(dto);
        int queryFlag = mapper.mergeSvCsCrdcdPcsIz(creditCardDvo);
        BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_CRDCD_PCS_IZ"});
    }

    /**
     * 서비스비용가상계좌처리내역 merge
     * @param dto
     */
    private void saveSvCsVacPcsIz(WsnbPaidServiceSettlementDto.VirtualAccount dto) {
        WsnbPaidServiceSettlementDvo virtualAccountDvo = converter.mapVirtualAccountToDvo(dto);
        int queryFlag = mapper.mergeSvCsVacPcsIz(virtualAccountDvo);
        BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_VAC_PCS_IZ"});
    }

    /**
     * 결제정보별 결제진행상태, 합산청구상태 업데이트
     * @param settlementDvo
     * @param errMsg
     */
    private void saveStatCdByAmount(WsnbPaidServiceSettlementDvo settlementDvo, String errMsg) {
        int bilCtrSumAmt = mapper.selectSvCsBilBas(settlementDvo); // 청구조정합계금액
        int dpSumAmt = Integer.parseInt(StringUtil.nvl2(settlementDvo.getDpSumAmt(), "0")); // 입금합계금액
        String reqPrgsStatCd = settlementDvo.getReqPrgsStatCd(); // 요청진행상태코드

        if ((REQ_PRGS_STAT_CDCO_DP.equals(reqPrgsStatCd) && StringUtil.isNotBlank(settlementDvo.getCardFntImpsCd())) // 신용카드 - 카드이체불능인 경우
            || (REQ_PRGS_STAT_VAC_DP.equals(reqPrgsStatCd) && StringUtil.isNotBlank(settlementDvo.getVacDpErrCd()))) { // 가상계좌 - 입금오류인 경우
            settlementDvo.setStlmPrgsStatCd(STLM_PRGS_STAT_CD_FAIL); // 결제진행상태코드("02" 청구실패)
            settlementDvo.setAdpBilStatCd(ADP_BIL_STAT_CD_STNB); // 합산청구상태코드("01" 대기)

        } else if (bilCtrSumAmt == dpSumAmt) { // 청구조정합계금액 = 입금합계금액
            settlementDvo.setStlmPrgsStatCd(STLM_PRGS_STAT_CD_FSH); // 결제진행상태코드("05" 결제완료)
            settlementDvo.setAdpBilStatCd(ADP_BIL_STAT_CD_FSH); // 합산청구상태코드("03" 완료)

        } else if (bilCtrSumAmt > dpSumAmt) { // 청구조정합계금액 > 입금합계금액
            settlementDvo.setStlmPrgsStatCd(STLM_PRGS_STAT_CD_PRTN); // 결제진행상태코드("04" 일부결제)
            settlementDvo.setAdpBilStatCd(ADP_BIL_STAT_CD_PRGS); // 합산청구상태코드("02" 진행중)
        } else {
            throw new BizException(errMsg);
        }

        this.editStlmPrgsStatCd(settlementDvo); // 서비스비용청구기본 - 결제진행상태코드 업데이트

        if (STLM_DV_CD_ADP_BIL.equals(settlementDvo.getStlmDvCd())) { // 결제구분코드 = "01" (합산청구)인 경우
            this.editAdpBilStatCd(settlementDvo);
        }
    }

    /**
     * 결제진행상태코드 업데이트 (서비스비용청구기본)
     * @param dvo
     */
    private void editStlmPrgsStatCd(WsnbPaidServiceSettlementDvo dvo) {
        int queryFlag = mapper.updateSvCsBilBas(dvo);
        BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_BIL_BAS"});
    }

    /**
     * 합산청구상태코드 업데이트 (서비스비용청구내역)
     * @param dvo
     */
    private void editAdpBilStatCd(WsnbPaidServiceSettlementDvo dvo) {
        int queryFlag = mapper.updateSvCsBilIz(dvo);
        BizAssert.isTrue(queryFlag == 1, "MSG_ALT_TBL_SVE_ERR", new String[] {"TB_SVPD_SV_CS_BIL_IZ"});
    }

}
