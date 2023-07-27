package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbContractChangeDvo {

    /* input */
    private String cntrCnfmDtmFr; // 계약시작접수일자
    private String cntrCnfmDtmTo; // 계약종료접수일자
    private String inDv; // 처리구분
    private String aprvDv; // 승인구분

    /* output */
    private String cntrNo; /* 계약번호  lcyear + lccode*/
    private int cntrSn; /* 계약일련번호  */
    private String sellTpCd; /* 판매유형코드 lcType (2:l20)*/
    private String sellTpDtlCd; /* 판매유형상세코드 */
    private String resYn; /* 결과코드 */
    private String cntrAdrpcId; /* 계약주소지 ID */
    private String rn; /* rownum */
    private String asIstOjNo; /* 작업순번 */
    private String cnslMoCn; /* 상담메모(엔지니어 전달메모) */
    private String eggrAsnDt; /* 배정일자(엔지니어 배정일자) */
    private String wkAcpteStatCd; /* 작업수락상태 */
    private String wkAcpteDt; /* 작업수락일자 */
    private String wkAcpteHh; /* 작업수락시간 */
    private String vstExpHh; /* 방문확정예약시간 */
    private String vstCnfmdt; /* 방문확정일자 */
    private String vstCnfmHh; /* 방문확정시간 */
    private String wkPrgsStatCd; /* 작업진행상태 */
    private String wkCanMoCn; /* 작업취소메모 */
    private String ogNm; /* 센터명 */
    private String egerCrallocaraTno; /* 휴대지역전화번호 */
    private boolean hasKiwiOrd; /* 키위 존재여부 */
    private String egerNm; /* 엔지니어명 */

    /* 화면 표시 */
    private String sellTpDtlNm; /* 판매유형사케코드명 (판매유형:lcTypeNm)*/
    private String cstKnm; /* 계약자명 - 고객명(lc31.lccnam) */
    private String bryyBzrno; /* 계약자정보 - 사업자번호(lccino) / 생년 */
    private String copnDvCd; /* 법인격구분코드 (lccgub) 개인:1; 법인:2*/
    private String copnDvNm; /* 법인격구분명 (lccgub) 개인:1; 법인:2*/
    private String cntrDtlNo; /* 계약상세번호 (lcOrdrNo)*/
    private String cntrCnfmDt; /* 계약확정일시(접수일) : 접수일자(lccrtDt) */
    private String pdNm; /* 상품명 (kainam) */
    private String basePdCd; /* 상품코드 (lcicde) */
    private String svPdTpCd; /* 용도구분코드 - 상품의 서비스유형 (용도구분명:lciuseNm; lciuse) */
    private String svPdTpNm; /* 용도구분 - 상품의 서비스유형 (용도구분명:lciuseNm; lciuse) */
    private String svPrd; /* (계약상세.서비스주기) lc31.lcimon */
    private String svPtrmUnitCd; /* 서비스기간단위코드 */
    private String svPtrmUnitNm; /* 서비스기간단위명 */
    private String sexDvCd; /* 성별 (lccsex) */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
    private String cntrDtlStatNm; /* 계약상세상태명 (사용구분 - 1:관리(101);2:탈퇴(201~303); 만료(401;402) :usedivnm)*/
    private int rentalAmt1; /* 렌탈료-최종값 (렌탈료1:lcamt1) */
    private int cntrPtrm1; /* 계약기간 (렌탈기간1 : lcmon1) */
    private int rentalAmt2; /* todo.맵핑없음 (렌탈료2:lcamt2) */
    private int cntrPtrm2; /* todo.맵핑없음 (렌탈기간2 : lcmon2) */
    private String sellDscDvCd;
    private String sellDscDvNm; /* 판매할인구분코드-할인적용유형명 - 할인구분 명(lcetc3Nm) */
    private String sellDscrCd;
    private String sellDscrNm; /* 판매할인율코드- - 할인유형 명(lcetc4M;) */
    private String sellDscTpCd;
    private String sellDscTpNm; /* 판매할인유형코드  (프로모션코드 :lcflg4Nm ; lcflg4)  */
    private String alncmpCd; /* 제휴사코드 (제휴코드:lcetc8)*/
    private String alncmpNm; /* 제휴코드명- 제휴코드명(lcetc8Nm) */
    private String fgptInfo; /* 사은품정보 (lcGift; prmt) 첫번째 사은품명 + 첫번째 사은품코드 외 사은품갯수 */
    private String hclsfRefPdClsfVal; /* kaetc1 06(order.kaetc1=='8') */
    private String mclsfRefPdClsfVal; /* kaetc2 06003("order.kaetc1=='8' and order.kaetc2=='m');  06005(order.kaetc1=='8' and order.kaetc2=='2') */
    private String pdHclsfNm; /* 대분류 kaetc1 kaetc1nm*/
    private String pdMclsfNm; /* 중분류 kaetc2 kaetc2nm*/
    private String hcrDvNm; /* 홈케어구분코드(합쳐짐) = lcprt1Nm + lcprt2Nm*/
    private String bdtMnftNm; /* 제조회사 (일시불일때만 제조회사 표시하지만; 맵핑없음 공통코드:bdtMnftCoCd) lcjejoNm */
    private String istDt; /* 설치일자  lcsetDt*/
    private String sellInflwChnlDtlCd; /* 판매유입채널상세코드 (직원구매 체크용(9020:직원구매->vSalediv:9)) */

    /* 계약주소지기본 / 상세 */
    private String cntrCstNo; /* 계약자정보 - 고객번호 */
    private String bryyMmdd; /* 계약자정보 - 생년월일 */
    private String bzrno; /* 계약자정보 - 사업자번호(lccino) */
    private String cntrAdrId; /* 계약자정보 -주소ID */
    private String cntrAdrDvCd; /* 계약자정보 -주소구분코드  */
    private String cntrAdrZip; /* 계약자정보 - 우편번호 */
    private String cntrCstRnadr; /* 계약자정보 - 기준주소 */
    private String cntrCstRdadr; /* 계약자정보 - 상세주소 */
    private String adrId; /* 주소ID */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String bfchCn; /* 변경전 내용 */
    private String chApyStrtdt; /* 변경적용시작일자 */
    private String chApyEnddt; /* 변경적용종료일자 */
    private String procsYn; /* 처리여부 */

    private String cntrCopnLocaraTno; /* 계약자정보 - 법인지역번호 */
    @DBEncField
    @DBDecField
    private String cntrCopnExnoEncr; /* 계약자정보 - 법인전화국번호암호화 */
    private String cntrCopnIdvTno; /* 계약자정보 - 법인개별전화번호 */
    private String rcgvpKnm; /* 설치처정보 - 설치자　명(lcwnam)*/
    private String istCralLocaraTno; /* 설치자 - 휴대전화번호1 */
    @DBEncField
    @DBDecField
    private String istMexnoEncr; /* 설치자 - 휴대전화번호2 */
    private String istCralIdvTno; /* 설치자 - 휴대전화번호3 */
    private String istAdrId; /* 설치자 - 주소 ID */
    private String istAdrDvCd; /* 설치자 - 주소구분코드 */
    private String istAdrZip; /* 설치처정보 - 우편번호 : lcwzip */
    private String istRnadr; /* 설치처정보 - 기준주소 : lcwad1 + lcwad2 +lcwad3 */
    private String istRdadr; /* 설치처정보 - 상세주소 : lcwad1 + lcwad2 +lcwad3 */

    /* 계약주문 Check */
    private String pkgYn; /* 패키지 주문 여부 (LC348_GSEQ) */
    private String prmPtrmYn; /* 선납주문여부 (LCST10) */
    private String dpYn; /* 입금여부 (LC_DPST_YN) */
    private String ftfYn; /* 대면여부 concWay:2 = FTF_YN:N*/
    private String istBzsCd; /* 설치업체 (설치업체(IST_BZS_CD):S:삼성전자,C:청호,o:기타) : KA11.KACK04, KA_CK04)  */
    private String istPcsvTpCd; /* 설치택배구분 (1:설치, 2:택배) 설치요청 구분  - REQ_SET_DIV - KA11.KACK05 , LCGUBN2*/
    private String rcpdt; /* 계약접수완료일시 - 접수일자(LCCRT_DT ,LCCRTY) bindMsgs[4] */
    private String cttRsCd; /* 컨택결과 코드 LC_CCDE */
    private String canDt; /* 취소일(LCCANT.LCCANY, LCCANT) */
    private String onePlusOneYn; /* 1+1 상대코드 등록 여부 체크(isOnpsOpntCd)*/
    private String canPrgsStatCd; /* 취소 진행 상태코드 */
    private String rglrSppCntr; /* 정기배송 계약리스트 */
    private String brmgrNm; /* 지점장명(AKDBNM) bindMsgs[0] */
    private String cntrCralLocaraTno; /* 계약자　휴대폰번호1 LCCNOT. LCCNO1 bindMsgs[7] */
    @DBEncField
    @DBDecField
    private String cntrMexnoEncr; /* 계약자　휴대폰번호2 LCCNOT. LCCNO2 bindMsgs[7] */
    private String cntrCralIdvTno; /* 계약자　휴대폰번호3 LCCNOT. LCCNO3 bindMsgs[7] */
    private String brmgrCralLocaraTno; /* 지점장전화번호-휴대지역전화번호 (AKDBTD,LCBPHONE) -  */
    @DBEncField
    @DBDecField
    private String brmgrMexnoEncr; /* 지점장전화번호-휴대전화국번호암호화 (AKDBT1,LCBPHONE) - 수신자 전화번호  */
    private String brmgrCralIdvTno; /* 지점장전화번호-휴대개별전화번호 (AKDBT2,LCBPHONE) - 수신자 전화번호 */

    /* 계약변경접수 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String dtlCntrNo; /* 상세계약번호 */
    private int dtlCntrSn; /* 상세계약일련번호 */
    private String cntrChRsonDvCd; /* 계약변경사유구분코드 */
    private String cntrChRsonCd; /* 계약변경사유코드 */
    private String cntrChAtcDvCd; /*계약변경항목구분코드 */
    private String cntrChAkCn; /* 계약변경요청내용 */
    private String aprDtm; /* 승인일시 */
    private String aprUsrId; /* 승인사용자ID */
    private String dtaDlYn; /* 데이터삭제여부 */

    /* 상세 */
    private String cntrChRcpDtm; /* 계약변경접수일시 */
    private String cntrChTpCd; /* 계약변경유형코드 */
    private String chRqrDvCd; /* 변경요청자구분코드 */
    private String chRqrNm; /* 변경요청자명 */
    private String cstNo; /* 고객번호 */
    private String cntrChPrgsStatCd; /* 계약변경진행상태코드 */
    private String chRcstDvCd; /* 변경접수자구분코드 */
    private String chRcpUsrId; /* 변경접수사용자id */
    private String cntrChFshDtm; /* 계약변경완료일시 */

    private String prtnrKnm; // 판매자한글명
    private String sellPrtnrNo; // 판매파트너번호
    private String ogTpCd; // 조직유형코드
    private String bfPrtnrNo; /* 변경전 파트너번호 */
    private String bfOgTpCd; /* 변경전 조직유형코드 */

    // DATETIME 반환값
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록유저ID
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcDtm; // 최종수정일시
    private String fnlMdfcUsrId; // 최종수정유저ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID

    private String cntrChRcpId; // 계약변경접수ID
    private String cntrChSn; // 계약변경일련번호
}
