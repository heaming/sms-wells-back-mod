package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import java.util.List;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbCustomerBaseBulkChangeDvo {
    private String sellTpCd; // 업무구분
    private String prcDvCd; // 처리구분
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cntrCstNo; // 고객번호
    private String rcpDtFrom; // 접수기간From
    private String rcpDtTo; // 접수기간To
    private String prtnrNo; // 파트너번호
    private String emadr; // 이메일
    private String copnDvCd; // 법인격구분코드
    @DBDecField
    @DBEncField
    private String cardAccNo; // 계좌/카드번호
    private String ogCd; // 조직코드
    private String sellPrtnrNo; //  판매파트너번호
    private String prtnrKnm; //  [이름] 파트너한글명
    private String sellTpNm; //  [업무구분] 판매유형명
    private String cstKnm; //  [고객명] 고객한글명
    private String cntrCnfmDtm; //  [접수일자] 계약확정일시
    private String txinvPblOjYn; //  [세금계산서] 세금계산서발행대상여부
    private String atmtStat; //  [자동이체정보-상태]
    private String mpyBsdt; //  [자동이체정보-이체일] 납부기준일자
    private String aftnInfFntDvCd; // 자동이체정보-이체구분코드
    private String aftnInfFntDvNm; // [자동이체정보-이체구분] 이체구분명
    private String dpTpCd; // 입금유형코드
    private String dpTpNm; // 입금유형명
    private String bnkCdcoCd; //  은행/카드사코드
    private String bnkCdcoNm; //  [자동이체정보-카드사/은행] 은행/카드사명
    @DBDecField
    private String acnoCrcdno; //  [자동이체정보-카드/계좌번호]
    private String isBndl; //  [자동이체정보-묶음/대표] 묶음출금여부
    private String isBndlMast; //  [자동이체정보-묶음/대표] 묶음출금 대표주문 여부
    private String evidOcyInqr; //  [자동이체정보-선택]
    private String resign; // [자동이체정보-해지]
    private String istKnm; //  [설치자 정보-설치고객명] 수령자한글명
    private String wCralLocaraTno; //  [설치자 정보-휴대전화번호1]
    @DBDecField
    private String wMexnoEncr; //  [설치자 정보-휴대전화번호2]
    private String wCralIdvTno; //  [설치자 정보-휴대전화번호3]
    private String wLocaraTno; //  [설치자 정보-전화번호1]
    @DBDecField
    private String wExnoEncr; //  [설치자 정보-전화번호2]
    private String wIdvTno; //  [설치자 정보-전화번호3]
    private String wAdrZip; //  [설치자 정보-우편번호]
    private String wRnadr; //  [설치자 정보-주소1] 주소
    private String wRdadr; //  [설치자 정보-주소2] 상세주소
    private String cralLocaraTno; //  [계약자 정보-휴대전화번호1]
    @DBDecField
    private String mexnoEncr; //  [계약자 정보-휴대전화번호2]
    private String cralIdvTno; //  [계약자 정보-휴대전화번호3]
    private String locaraTno; //  [계약자 정보-전화번호1]
    @DBDecField
    private String exnoEncr; //  [계약자 정보-전화번호2]
    private String idvTno; //  [계약자 정보-전화번호3]
    private String adrZip; //  [계약자 정보-우편번호]
    private String rnadr; // [계약자정보-주소1]
    private String rdadr; // [계약자정보-주소2]
    private String rprsCntrNo; // 묶음출금 대표계약번호
    private String slDt; /* [매출일자] 매출인식일자 */
    private String dgr3LevlDgPrtnrNo; /* [계약마스터-지점장코드] 3차레벨대표파트너번호 */
    private String dgr2LevlDgPrtnrNo; /* [계약마스터-지역단장코드] 2차레벨대표파트너번호 */
    private String dgr1LevlDgPrtnrNo; /* [계약마스터-총괄단장코드] 1차레벨대표파트너번호 */
    private String dgr1LevlOgCd; /* [계약마스터-총괄단코드] 1차레벨조직코드 */
    private String curOgCd; /* [대리인마스터-조직코드] 조직코드 */
    private String curDgr3LevlDgPrtnrNo; /* [대리인마스터-지점장코드] 3차레벨대표파트너번호 */
    private String curDgr2LevlDgPrtnrNo; /* [대리인마스터-지역단장코드] 2차레벨대표파트너번호 */
    private String curDgr1LevlDgPrtnrNo; /* [대리인마스터-총괄단장코드] 1차레벨대표파트너번호 */
    private String curDgr1LevlOgCd; /* [대리인마스터-총괄단코드] 1차레벨조직코드 */
    private String chEpNo; /* [변경사번] */

    /* 저장용 Req */
    private List<Contract> contractList; // 계약리스트
    private String istNm; /* 설치자명 */
    private String pblYn; /* 세금계산서발행대상여부 */
    private String rcgvpKnm; /* 수령자 한글명 */
    private String sellInflwChnlDtlCd; /* 판매유입채널상세코드 */
    private String ogTpCd; /* 판매조직유형코드 */
    private String fntDvCd; /* 이체구분 */

    @Getter
    @Setter
    public static class Contract {
        private String cntrNo; // 계약번호
        private String cntrSn; // 계약일련번호
        private String cntrCstNo; // 계약고객번호
        private String istKnm; // 수령자 한글명
        private String txinvPblOjYn; // 세금계산서변경대상여부
        private String sellPrtnrNo; // 판매자파트너번호
        private String prtnrKnm; // 판매자 파트너 명
        private String aftnInfFntDvCd; // 자동이체정보-이체구분코드
        private String copnDvCd; // 법인격구분코드
        private String sellTpCd; // 판매유형코드
        private String dpTpCd; // 입금유형코드
        private String cntrStlmId; // 계약주소ID
    }

    /* 계약변경접수기본 */
    private String cntrChRcpId; /* 계약변경접수ID */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String cntrChRcpDtm; /* 계약변경접수일시 */
    private String cntrChTpCd; /* 계약변경유형코드 */
    private String chRqrDvCd; /* 변경요청자구분코드 */
    private String chRqrNm; /* 변경요청자명 */
    private String cstNo; /* 고객번호 */
    private String cntrChAkCn; /* 계약변경요청내용 */
    private String cntrChPrgsStatCd; /* 계약변경진행상태코드 */
    private String cntrChPrgsMoCn; /* 계약변경진행메모내용 */
    private String chRcstDvCd; /* 변경접수자구분코드 */
    private String chRcpUsrId; /* 변경접수사용자ID */
    private String aprDtm; /* 승인일시 */
    private String aprUsrId; /* 승인사용자ID */
    private String cntrChFshDtm; /* 계약변경완료일시 */
    private String bizTfId; /* 업무이관ID */
    private String dtaDlYn; /* 데이터삭제여부 */

    /* 계약변경접수상세 */
    private Integer cntrChSn; /* 계약변경일련번호 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String dtlCntrNo; /* 상세계약번호 */
    private Integer dtlCntrSn; /* 상세계약일련번호 */
    private String cntrChRsonDvCd; /* 계약변경사유구분코드 */
    private String cntrChRsonCd; /* 계약변경사유코드 */
    private String cntrChAtcDvCd; /* 계약변경항목구분코드 */
    private String chApyStrtdt; /* 변경적용시작일자 */
    private String chApyEnddt; /* 변경적용종료일자 */
    private String chPdCd; /* 변경상품코드 */
    private Long chQty; /* 변경수량 */
    private Long chAmt; /* 변경금액 */
    private String chPtrmUnitCd; /* 변경기간단위코드 */
    private Long chPtrmN; /* 변경기간수 */
    private String cntrAdrpcId; /* 계약주소지ID */
    private String cntrStlmId; /* 계약결제ID */
    private String bfchCn; /* 변경전내용 */
    private String procsYn; /* 처리여부 */
    private String procsDuedt; /* 처리예정일자 */
    private String procsFshDtm; /* 처리완료일시 */
    private String stlmHdDvCd; /* 결제보류구분코드 */

    /* 최초(최종)등록 */
    private String fstRgstDtm; /* 최초등록일시 */
    private String fstRgstUsrId; /* 최초등록사용자ID */
    private String fstRgstPrgId; /* 최초등록프로그램ID */
    private String fstRgstDeptId; /* 최초등록부서ID */
    private String fnlMdfcDtm; /* 최종수정일시 */
    private String fnlMdfcUsrId; /* 최종수정사용자ID */
    private String fnlMdfcPrgId; /* 최종수정프로그램ID */
    private String fnlMdfcDeptId; /* 최종수정부서ID */
}
