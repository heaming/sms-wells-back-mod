package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0094 유상 서비스 결제정보 생성
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.28
 */
@Setter
@Getter
public class WsnbPaidServiceSettlementDvo {

    // 공통
    String reqPrgsStatCd; // 요청진행상태코드
    String csBilNo; // 비용청구번호
    String rveAkNo; // 수납요청번호
    String cntrNo; // 계약번호
    String cntrSn; // 계약일련번호
    String cstSvAsnNo; // 고객서비스배정번호
    String trdIdno; // 거래고유번호
    String crcdnoEncr; // 신용카드번호암호화
    String crdcdExpdtYm; // 신용카드유효기간년월
    String istmMcn; // 할부개월수
    String cardAprno; // 카드승인번호
    String iscmpCd; // 발급사코드
    String canDvCd; // 취소구분코드
    String stlmPrgsStatCd; // 결제진행상태코드
    String bilCtrSumAmt; // 청구조정합계금액
    String adpBilStatCd; // 합산청구상태코드

    // 서비스비용청구
    String dpSn; // 입금일련번호
    String stlmDvCd; // 결제구분코드
    String dpSplAmt; // 입금공급금액
    String dpVat; // 입금부가가치세
    String dpSumAmt; // 입금합계금액
    String dpDtm; // 입금일시
    String crcdonrNm; // 신용카드주명
    String vacNo; // 가상계좌번호
    String vacBnkCd; // 가상계좌은행코드
    String itgDpNo;// 통합입금번호

    // 신용카드
    String csBilSn; // 비용청구일련번호
    String msgno; // 전문번호
    String etxtSn; // 전문일련번호
    String aprDvCd; // 승인구분코드
    String cardFntImpsCd; // 카드이체불능코드
    String cardAprAmt; // 카드승인금액
    String aprDtm; // 승인일시

    // 가상계좌
    String rveCoCd; // 수납회사코드
    String etxtAkDt; // 전문요청일자
    String etxtAkSn; // 전문요청일련번호
    String vacDpErrCd; // 가상계좌입금오류코드
    String vacStlmAmt;// 가상계좌결제금액

}
