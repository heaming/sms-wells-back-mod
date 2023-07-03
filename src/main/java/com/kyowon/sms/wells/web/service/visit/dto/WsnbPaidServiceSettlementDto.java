package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-S-0094 유상 서비스 결제정보 생성
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.28
 */
public class WsnbPaidServiceSettlementDto {

    @ApiModel(value = "WsnbPaidServiceSettlementDto-CreateReq")
    public record SaveReq(
        @NotBlank
        @Pattern(regexp = "^[1|2|3|4]$")
        String reqPrgsStatCd, // 요청진행상태코드
        String rveAkNo, // 수납요청번호
        @NotNull
        SaveCostDepositReq costDeposit, // 서비스비용입금내역
        SaveCreditCardReq creditCard, // 서비스비용신용카드처리내역
        SaveVirtualAccountReq virtualAccount // 서비스비용가상계좌처리내역
    ) {}

    // 서비스비용입금내역 DTO
    @ApiModel(value = "WsnbPaidServiceSettlementDto-SaveCostDepositReq")
    public record SaveCostDepositReq(
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn, // 계약일련번호
        @NotBlank
        String csBilNo, // 비용청구번호
        @NotBlank
        String cstSvAsnNo, // 고객서비스배정번호
        String dpSn, // 입금일련번호
        @NotBlank
        @Pattern(regexp = "^0[1|2|3]$")
        String stlmDvCd, // 결제구분코드
        String dpSplAmt, // 입금공급금액
        String dpVat, // 입금부가가치세
        String dpSumAmt, // 입금합계금액
        String dpDtm, // 입금일시
        String trdIdno, // 거래고유번호
        String crcdonrNm, // 신용카드주명
        String crcdnoEncr, // 신용카드번호암호화
        String crdcdExpdtYm, // 신용카드유효기간년월
        String istmMcn, // 할부개월수
        String cardAprno, // 카드승인번호
        String iscmpCd, // 발급사코드
        String canDvCd, // 취소구분코드
        String vacNo, // 가상계좌번호
        String vacBnkCd, // 가상계좌은행코드
        String itgDpNo // 통합입금번호
    ) {}

    // 서비스비용신용카드처리내역 DTO
    @ApiModel(value = "WsnbPaidServiceSettlementDto-SaveCreditCardReq")
    public record SaveCreditCardReq(
        @NotBlank
        String csBilNo, // 비용청구번호
        String csBilSn, // 비용청구일련번호
        String msgno, // 전문번호
        String etxtSn, // 전문일련번호
        @NotBlank
        @Pattern(regexp = "^0[1|2][0|1]0$")
        String aprDvCd, // 승인구분코드
        String cardFntImpsCd, // 카드이체불능코드
        String trdIdno, // 거래고유번호
        String crcdnoEncr, // 신용카드번호암호화
        String crdcdExpdtYm, // 신용카드유효기간년월
        String istmMcn, // 할부개월수
        String cardAprAmt, // 카드승인금액
        String cardAprno, // 카드승인번호
        String aprDtm, // 승인일시
        String iscmpCd, // 발급사코드
        String canDvCd // 취소구분코드
    ) {}

    // 서비스비용가상계좌처리내역 DTO
    @ApiModel(value = "WsnbPaidServiceSettlementDto-SaveVirtualAccountReq")
    public record SaveVirtualAccountReq(
        @NotBlank
        String csBilNo, // 비용청구번호
        String csBilSn, // 비용청구일련번호
        String rveCoCd, // 수납회사코드
        String etxtAkDt, // 전문요청일자
        String etxtAkSn, // 전문요청일련번호
        String vacDpErrCd, // 가상계좌입금오류코드
        String vacNo, // 가상계좌번호
        String vacBnkCd, // 가상계좌은행코드
        String vacStlmAmt // 가상계좌결제금액
    ) {}

}
