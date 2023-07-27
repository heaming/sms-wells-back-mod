package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrDetailChangeHistDvo {
    private String cntrNo; /* 계약번호 */
    private String histStrtDtm; /* 이력시작일시 */
    private Integer cntrSn; /* 계약일련번호 */
    private String histEndDtm; /* 이력종료일시 */
    private String basePdCd; /* 기준상품코드 */
    private String hgrPdCd; /* 상위상품코드 */
    private Long pdQty; /* 상품수량 */
    private String sellTpCd;
    private String sellTpDtlCd; /* 판매유형상세코드 */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
    private String cntrPtrmUnitCd; /* 계약기간단위코드 */
    private Long cntrPtrm; /* 계약기간 */
    private String cntrPdStrtdt; /* 계약상품시작일자 */
    private String cntrPdEnddt; /* 계약상품종료일자 */
    private String stplPtrmUnitCd; /* 약정기간단위코드 */
    private Long stplPtrm; /* 약정기간 */
    private String svPtrmUnitCd; /* 서비스기간단위코드 */
    private Long svPrd; /* 서비스주기 */
    private String cntrwTpCd; /* 계약서유형코드 */
    private String blgCrpCd; /* 소속법인코드 */
    private String rveCrpCd; /* 수납법인코드 */
    private String coCd; /* 회사코드 */
    private String booSellTpCd;
    private String pdGdCd; /* 상품등급코드 */
    private String pdHclsfId; /* 상품대분류ID */
    private String pdMclsfId; /* 상품중분류ID */
    private String pdLclsfId; /* 상품소분류ID */
    private String pdDclsfId; /* 상품세분류ID */
    private String sellDscDvCd; /* 판매할인구분코드 */
    private String sellDscrCd; /* 판매할인율코드 */
    private Long sellDscCtrAmt; /* 판매할인조정금액 */
    private String sellDscTpCd; /* 판매할인유형코드 */
    private String stlmTpCd; /* 결제유형코드 */
    private String crncyDvCd; /* 통화구분코드 */
    private Long apyExcr; /* 적용환율 */
    private Long pdBaseAmt; /* 상품기준금액 */
    private Long sellAmt; /* 판매금액 */
    private Long dscAmt; /* 할인금액 */
    private Long fnlAmt; /* 최종금액 */
    private Long vat; /* 부가가치세 */
    private Long cntrAmt; /* 계약금액 */
    private Long cntramDscAmt; /* 계약금할인금액 */
    private Long istmMcn; /* 할부개월수 */
    private Long istmPcamAmt; /* 할부원금금액 */
    private Long istmIntAmt; /* 할부이자금액 */
    private Long mmIstmAmt; /* 월할부금액 */
    private Long crpUcAmt; /* 법인미수금액 */
    private Long sellFee; /* 판매수수료 */
    private Long cntrTam; /* 계약총액 */
    private Long ackmtPerfRt; /* 인정실적율 */
    private Long ackmtPerfAmt; /* 인정실적금액 */
    private Long cvtPerfAmt; /* 환산실적금액 */
    private Long feeAckmtCt; /* 수수료인정건수 */
    private Long feeAckmtBaseAmt; /* 수수료인정기준금액 */
    private String feeFxamYn; /* 수수료정액여부 */
    private String sppDuedt; /* 배송예정일자 */
    private String resubYn; /* 재구독여부 */
    private String rstlYn; /* 재약정여부 */
    private String frisuYn; /* 무상여부 */
    private String frisuDsbTpCd; /* 무상지급유형코드 */
    private String txinvPblOjYn; /* 세금계산서발행대상여부 */
    private String alncmpCd; /* 제휴사코드 */
    private String alncmpCntrDrmVal; /* 제휴사계약식별값 */
    private String smtplId; /* 스마트플랜ID */
    private Integer smtplSn; /* 스마트플랜일련번호 */
    private String cttRsCd; /* 컨택결과코드 */
    private String cttPsicId; /* 컨택담당자ID */
    private String bfOrdNo; /* 이전주문번호 */
    private String cntrChRcpId; /* 계약변경접수ID */
    private Integer cntrChSn; /* 계약변경일련번호 */
    private String cntrChDtlAkCn; /* 계약변경상세요청내용 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
