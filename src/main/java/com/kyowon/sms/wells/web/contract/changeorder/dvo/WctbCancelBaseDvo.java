package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbCancelBaseDvo {
    private String alncmpCd; // 제휴사코드
    private String refPdClsfVal; // 상품참조분류 - 커피원두 구분용
    private String cntrPdStrtdt; // 상품시작일자/매출일자
    private String stplPtrm; // 의무기간
    private String stplDscAmt; // 재약정할인금액
    private String stplStrtdt; // 재약정시작일자
    private String stplEnddt; // 재약정종료일자
    private String exnDt; // 만료일자
    private Integer useDays; // 사용일자
    private String grade; // 등급
    private Integer thmSlSumAmt; // 당월매출금액
    private Integer thmDpTotAmt; // 당월입금총합
    private Integer thmRfndTotAmt; // 당월환불총합

    private String cntrNo;
    private String cntrSn;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String basePdCd;
    private Integer cntrPtrm; // 렌탈개월
    private Integer rentalTn; // 렌탈차월
    private Integer cntrPasgDc; // 계약경과일수
    private Integer sppNmnN; // 배송차월수
    private Integer rtngdQty; // 반품수량
    private Integer slDc; // 매출일수
    private Integer nomSlAmt; // 정상매출금액
    private Integer spmtSlAmt; // 추가매출금액
    private Integer nomDscAmt; // 정상할인금액
    private Integer spmtDscAmt; // 추가할인금액
    private Integer slCtrAmt; // 매출조정금액
    private Integer canCtrAmt; // 취소조정금액
    private Integer slSumAmt; // 매출합계금액
    private Integer slSumVat; // 매출합계부가가치세
    private Integer slAggAmt; // 매출누계금액
    private Integer slAggAmtVat; // 매출누계금액부가가치세
    private Integer dscAggAmt; // 할인누계금액
    private Integer ctrAggAmt; // 조정누계금액
    private Integer nomIntAmt; // 정상이자금액
    private Integer intCtrAmt; // 이자조정금액
    private Integer intSumAmt; // 이자합계금액
    private Integer intVat; // 이자부가가치세
    private Integer intAggAmt; // 이자누계금액
    private Integer intDscAggAmt; // 이자할인누계금액
    private Integer thmPaiam; // 당월원리금
    private Integer thmSvAmt; // 당월서비스금액
    private Integer slBlam; // 매출잔액
    private Integer adnSvSpmtSlAmt; // 부가서비스추가매출금액
    private Integer prmBtdAmt; // 선납잔액기말금액 PRM_BTD_AMT
    private Integer eotAtam; // 기말선수금
    private Integer totPrpdAmt; // 총선수금액
    private Integer slDpAmt; // 매출입금금액
    private Integer slDpAggAmt; // 매출입금누계금액
    private Integer ucAmt; // 미수금액
    private Integer dlqAmt; // 연체금액
    private Integer prmRfndAmt; // 선납환불금액 0
    private Integer prpdRfndAmt; // 선수환불금액 0
    private Integer dscDdctam; // 할인공제금액 0
    private Integer filtDdctam; // 필터공제금액 0
    private Integer rentalRgstCostRfndAmt; // 렌탈등록비환불금액 0
    private Integer rentalRgstCostRfndAmtVat; // 렌탈등록비환불금액부가가치세 0
    private Integer borAmt; // 위약금액
    private Integer totRfndAmt; // 총환불금액
    private Integer resRtlfeBorAmt; // 잔여렌탈료위약금액
    private Integer rgstCostDscBorAmt; // 등록비할인위약금액
    private Integer rentalDscBorAmt; // 렌탈할인위약금액
    private Integer csmbCostBorAmt; // 소모품비위약금액
    private Integer csmbCostBorAmt2; // 소모품비위약금액2
    private Integer ptBorAmt; // 포인트위약금액
    private Integer reqdCsBorAmt; // 철거비용위약금액
    private Integer reqdCsBorAmt2; // 철거비용위약금액2
    private Integer lsnt; // 분실손료
    private Integer eotDlqAddAmt; // 기말연체가산금액

    private String cntrStatChRsonCd; // 계약상태변경사유코드
    private String ccamExmptDvCd; // 위약금면책구분코드
    private String csmbCsExmptDvCd; // 소모품비용면책구분코드
    private String reqdCsExmptDvCd; // 철거비용면책구분코드
    private String reqdAkRcvryDvCd; // 철거요청복구구분코드
    private String rsgAplcDt; // 요청일자
    private String rsgFshDt; // 취소일자
    private String slCtrRqrId; // 매출조정요청자ID
    private String slCtrRmkCn; // 매출조정비고내용
    private String ichrOgTpCd; // 담당조직유형코드
    private String ichrPrtnrNo; // 담당파트너번호

    private Integer chSn;
    //private String sellDscDvCd; // 할인구분코드
    //private String reStplPtrm; // 재약정의무기간
    //private Integer cntrPdStrtdt;
}
