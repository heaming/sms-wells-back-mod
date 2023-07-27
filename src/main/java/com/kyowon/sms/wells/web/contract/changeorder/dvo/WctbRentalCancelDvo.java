package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbRentalCancelDvo {

    /* input */
    private String cntrNo; // 계약번호
    private int cntrSn; // 계약일련번호
    private String canRqdt; // 취소요청일
    private String canDt; // 취소일
    private int addCtrAmt; // 가산조정액
    private String borExmptDvCd; // 위약면책구분
    private String canTpCd; // 취소유형코드
    private String cnmsExmptDvCd; // 소모면책구분
    private String reqdExmptDvCd; // 철거면책구분
    private String rcvryOptCd; // 복구옵션(미사용)
    private int borAmt; // 위약금（입력）
    private int csmbCs; // 소모품비（입력）
    private int reqdCs; // 철거비（입력）
    private int canCtrAmt; // 취소조정금액(부호)
    private int lsRntf; // 분실손료(입력)
    private String dvCd; // 입력구분
    private String akId; // 요청사번

    /* FIELD-AM116-RTN 계산로직 */
    int workAmt11;
    boolean isProdGrd;
    int workSday;
    int lastCanRqDay;
    int rentalAmtParam;
    int workAmt15;
    int pre1SlAggAmtParam;
    int workAmt12;
    String orgCanDt;

    /* output */
    private String errYn; // 오류여부
    private String errCn; // 오류메세지
    private String pdChYn; // 상변여부
    private String slYn; // 매출정보 존재여부
    private String alncmpCd; // 제휴사코드
    private String chkDv2; // 확인구분2
    private String chkDv4; // 확인구분4
    private String sellTpDtlCd; // 판매유형상세코드
    private String pdCd; // 상품코드
    private String dispCdyn; // 재약정의무기간내 설정
    private String prmStrtY; // 선납시작년
    private String prmStrtMm; // 선납시작월
    private String prmEndY; // 선납종료년
    private String prmEndMm; // 선납종료월
    private String alncPrtnrDrmVal; // 제휴파트너식별값
    private String canAdjDv; // 취소정산구분

    private int stplTn; // 약정회차
    private int cntrDt; // 계약일
    private int pre1ThmUcBlam; // 당월미수잔액
    private int rentalRgstCost; // 렌탈등록비
    private int dscAmt; // 할인금액
    private int eotPcamBlam; // 기말원금잔액
    private int eotPcamIntBlam; // 기말원금이자잔액
    private int adnSvCsAmt; // 서비스:서비스비용
    private int adnSvDscAmt; // 서비스:할인금액
    private int stplEnddt; // 약정종료일자
    private int prmSlAmt; // 선납매출금액
    private int prmSlAmt2; // 선납매출금액2
    private int pre1DscAggAmt; // 할인누계금액
    private int pre1SlAggVat; // 매출누계부가세
    private int pre1IntDscAggAmt; // 이자할인누계금액
    private int pre1IntCtrAggAmt; // 이자조정누계금액
    private int slDc; // 매출일수
    private int saveA443; // 할인금집계 + 선납할인금액
    private int dscSumAmt; // 할인금액집계
    private int prmDscSum; // 선납할인금액
    private int recapDutyPtrmN; // 유상의무기간수
    private int pdChRentalTn; // 상변회차
    private int rentalTn; // 렌탈회차
    private int workAmt71; // 빨간펜포인트
    private int prmMcn; // 선납개월수
    private int slRcogDt; // 매출인식일
    private int istmMcn; // 할부개월수
    private int cntrRcpFshDt; // 계약시작일자
    private int prmBlamBtdAmt; // 선납잔액기초금액
    private int thmDlqAddDpSumAmt; // 연체가산금- 당월연체가산입금합계금액
    private int thmCtrDlqAddAmt; // 연체가산금 - 당월조정연체가산금액
    private int btdDlqAddAmt; // 연체가산금- 기초연체가산금액
    private int btdAtam; // 기초선수금
    private int rentalAmt; // 렌탈금액
    private int rentalAmt2; // 렌탈금액2
    private int rentalDscAmt; // 렌탈할인1
    private int rentalDscAmt2; // 렌탈할인1
    private int pre1EotUcAmt; // 기말미수금액
    private int svAmt; // 서비스금액
    private int stplDscAmt; // 약정할인금액
    private int stplDscConfAmt; // 재약정할인
    private int slThmSlSumAmt; // 매출(설치일)월기준- 매출합계
    private int slRentalDc; // 매출(설치일)월기준-렌탈일수
    private int pre1SlAggAmt; // 매출누계금액
    private int pre1IntAggAmt; // 이자누계금액
    private int pdBaseAmt; // 월 렌탈료
    private int sellAmt; // 판매금액
    private int asdpAmt; // 입금금액
    private int rfndAmt; // 환불금액
    private int asdpRfndAmt; // 입금금액 + 환불금액
    private int stplPtrm; // 약정기간
    private int rentalTam; // 렌탈총액
    private int rentalPtrm; // 렌탈기간
    private int rentalPtrm2; // 렌탈기간2
    private int nomSlAmt; // 정상매출금액
    private int spmtSlAmt; // 추가매출금액
    private int nomDscAmt; // 정상할인금액
    private int spmtDscAmt; // 추가할인금액
    private int slCtrAmt; // 매출조정금액
    private int slSumAmt; // 매출합계금액
    private int slSumVat; // 매출합계부가가치세
    private int slAggAmt; // 매출누계금액
    private int thmSlSumAmt;// 당월매출합계금액
    private int dscAggAmt; // 할인누계금액
    private int ctrAggAmt; // 조정누계금액
    private int nomIntAmt; // 정상이자금액
    private int intCtrAmt; // 이자조정금액
    private int intCanCtrAmt; // 이자취소조정금액
    private int intSumAmt; // 이자합계금액
    private int intSumVat; // 이자합계부가세
    private int intAggAmt; // 이자누계금액
    private int intDscAggAmt; // 이자할인누계금액
    private int intCtrAggAmt; // 이자조정누계금액
    private int slBlamAmt; // 매출잔액금액
    private int prmBlamAmt; // 선납잔액금액
    private int prpdBlamAmt; // 선수잔액금액
    private int totPrpdAmt; // 총선수금액
    private int slDpAmt; // 매출입금금액
    private int slDpAggAmt; // 매출입금누계금액
    private int ucAmt; // 미수금액
    private String pdUscDc; // 상품사용일수
    private String assetGdCd; // 자산등급코드
    private int prmRfndAmt; // 선납환불금액
    private int prpdRfndAmt; // 선수환불금액
    private int rgstCostRtrnAmt; // 등록비반환금
    private int rgstCostRtrnVat; // 등록비반환금부가세
    private int rentalResBorAmt; // 렌탈잔여위약금액
    private int rentalRgstCostBorAmt; // 렌탈등록비위약금액
    private int dscCsBorAmt; // 할인비용위약금액
    private int csmbCsBorAmt; // 소모품비용위약금액
    private int rstlBorAmt; // 재약정위약금액
    private int pBorAmt; // 포인트위약금액
    private int reqdCsBorAmt; // 철거비용위약금액
    private int etcBorAmt1; // 기타위약금액1
    private int etcBorAmt2; // 기타위약금액2
    private int totRfndAmt; // 총환불금액
    private int rpRfndAmt; // rp환불금액
    private int adamtBtdAmt; // 가산금기초금액
    private int adamtDpAmt; // 가산금입금금액
    private int adamtEotAmt; // 가산금기말금액
    private int adamtDdctam; // 가산금공제금액
    private int adnSvSlAmt; // 부가서비스매출금액
    private int pre1SlDpAggAmt; // 매출입금누계금액

    private int workPamt; // (포인트:+입금 -환불)의 SUM
    private int workIamt; // (입반입금액 : +입금 -환불)의 SUM

    private int pUseAmt; // 포인트사용금액
    private int ocyRentalResBorAmt; // 원본_렌탈잔여위약금액
    private int ocyRentalRgstCostBorAmt; // 원본_렌탈등록비위약금액
    private int ocyDscCsBorAmt; // 원본_할인비용위약금액
    private int ocyCsmbCsBorAmt; // 원본_소모품비용위약금액
    private int ocyRstlBorAmt;// 원본_재약정위약금액
    private int ocyPBorAmt; // 원본_포인트위약금액
    private int ocyReqdCsBorAmt; // 원본_철거비용위약금액
    private int ocyEtcBorAmt1; // 원본_기타위약금액1
    private int ocyEtcBorAmt2; // 원본_기타위약금액2

    private int totRfndAmtCalsg; // 총환불금액부호
    private int slAggVat; // 매출누계부가세
    private int intAggVat; // 이자누계부가가치세
    private int pBtdAmt; // 포인트기초금액
    private int pSlAmt; // 포인트매출금액
    private int slSumAmtCalsg; // 매출합계금액_부호
    private int slSumVatCalsg; // 매출합계부가가치세_부호
    private int intSlSumAmtCalsg; // 이자매출합계금액_부호
    private int intSlSumVatCalsg;// 이자매출합계부가가치세_부호
    private int thmPaiam; // 당월원리금
    private int thmSvAmt; // 당월서비스금액
    private int slAggAmtCalsg; // 매출누계금액
    private int slAggAmtVatCalsg; // 매출누계금액부가가치세

}
