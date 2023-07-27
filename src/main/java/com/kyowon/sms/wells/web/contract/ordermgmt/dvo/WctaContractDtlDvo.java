package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractDtlDvo {
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String basePdCd; /* 기준상품코드 */
    private String hgrPdCd; /* 상위상품코드 */
    private Long pdQty; /* 상품수량 */
    private String sellTpCd; /* 판매유형코드 */
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
    private String booSellTpCd; /* 예약판매유형코드 */
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
    private BigDecimal apyExcr; /* 적용환율 */
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
    private BigDecimal ackmtPerfRt; /* 인정실적율 */
    private Long ackmtPerfAmt; /* 인정실적금액 */
    private Long cvtPerfAmt; /* 환산실적금액 */
    private BigDecimal feeAckmtCt; /* 수수료인정건수 */
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

    private List<WctaContractPrtnrRelDvo> prtnrRels;
    private List<WctaContractCstRelDvo> cstRels;
    private List<WctaContractPdRelDvo> pdRels;
    private List<WctaContractPmotIzDvo> pmotIzs;
    private List<WctaFgptRcpIzDvo> fgptRcpIzs;
    private List<WctaContractPrcCmptIzDvo> prcCmptIzs;
    private List<WctaContractStlmRelDvo> stlmRels;
    private WctaContractWellsDtlDvo wellsDtl;
    private WctaContractAdrpcBasDvo adrpc;
    private WctaContractAdrRelDvo adrRel;

    /*STEP2*/
    private String mclsfVal;
    private String lclsfVal;
    private String pdCd;
    private String pdClsf;
    private String pdClsfNm;
    private String pdNm;
    private String pdChip1;
    private String pdChip2;
    private String svPdCd;
    private Long frisuBfsvcPtrmN;
    private Integer frisuAsPtrmN;
    private String sellInflwChnlDtlCd;
    private boolean isExistAlncPds; /* 제휴상품 콤보 노출여부 */
    private String adrType; /* 주소유형(1: 기본, 2: 새로운주소) */

    private Long ackmtAmt;
    private BigDecimal ackmtRt;
    private String pdPrcFnlDtlId;
    private String fxamFxrtDvCd;
    private Integer verSn;
    private Long ctrVal;
    private String pdPrcId;

    private String cntrRelDtlCd; /* 계약관계상세코드 */
    private String pkg; /* 선택한 패키지 상품번호 */
    private List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> pkgs; /* 패키지 */
    private List<WctaContractRegStep2Dvo.PdSdingCapsl> sdingCapsls; /* 모종캡슐 */
    private String ojCntrNo; /* 대상계약번호 */
    private Integer ojCntrSn; /* 대상계약일련번호 */
    private Boolean rentalDiscountFixed; /* 렌탈할인유형 고정여부 */

    private WctaContractRegStep2Dvo.PdOpo opo; /* 1+1 */
    private WctaMachineChangeIzDvo mchnCh; /* 기기변경내역 */
    private WctaContractRegStep2Dvo.PdSltrRglrSppMchn sltrRglrSppMchn; /* 단독정기배송 기기 */

    private Long rglrSppCntrDvCd; /* 정기배송 계약기간 */
    private Long rglrSppDutyPtrmDvCd; /* 정기배송 약정기간 */
    private String rglrSppPrcDvCd; /* 정기배송 가격구분 */
    private String rglrSppMchnTpCd; /* 정기배송 기기유형 */
    private String pdChoLmYn; /* 제품선택제한여부 */
    private Long pdChoQty; /* 제품선택개수 */

    private List<WctaContractRegStep2Dvo.PdDetailDvo> svPdCds; /* 서비스상품코드 */
    private List<WctaContractRegStep2Dvo.PdDetailDvo> sellDscrCds; /* 판매할인율코드 */
    private List<WctaContractRegStep2Dvo.PdDetailDvo> sellDscDvCds; /* 판매할인구분코드 */
    private List<WctaContractRegStep2Dvo.PdDetailDvo> alncmpCntrDrmVals; /* 제휴상품 */
    // TODO 구좌수
    /*STEP2 일시불*/
    private List<WctaContractRegStep2Dvo.PdDetailDvo> frisuBfsvcPtrmNs; /* 무상BS기간수 */
    /*STEP2 렌탈*/
    private List<WctaContractRegStep2Dvo.PdDetailDvo> stplPtrms; /* 약정기간 */
    private List<WctaContractRegStep2Dvo.PdDetailDvo> cntrPtrms; /* 소유권이전기간(계약기간) */
    private List<WctaContractRegStep2Dvo.PdDetailDvo> rgstCss; /* 등록비(계약금액 cntrAmt) */
    private List<WctaContractRegStep2Dvo.PdDetailDvo> sellDscTpCds; /* 판매할인유형코드 */

    /*STEP3*/
    private String blkApy; /* 조건 일괄적용 */
    private Integer recapMshPtrm; /* 유상멤버십기간 */
    private String sodbtNftfCntrYn; /* 총판 비대면 계약여부 */

    private Long pdAmt; /* 결제정보 Type A, B 상품금액(신용카드) */
    private String dpTpCdMsh; /* 결제정보 Type A 멤버십 자동이체 방식 */
    private Long mshAmt; /* 결제정보 Type A 멤버십 금액 */

    private String dpTpCdAftn; /* 결제정보 Type C 자동이체방식 */
    private Long aftnAmt; /* 결제정보 Type C 등록비 */
    private String dpTpCdIdrv; /* 결제정보 Type C 등록비(계약금)결제유형 */
}
