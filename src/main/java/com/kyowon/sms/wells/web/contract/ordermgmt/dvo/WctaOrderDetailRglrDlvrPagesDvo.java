package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailRglrDlvrPagesDvo {
    private String cntrDtlNo; /* 계약상세번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String dgr3LevlDgPrtnrNo; /* 파트너정보-지점장 사번 */
    private String dgr3LevlDgPrtnrNm; /* 파트너정보-지점장명 */
    private String dgr3LevlOgCd; /* 파트너정보-지점코드 */
    private String sellPrtnrNo; /* 파트너정보-파트너사번 */
    private String prtnrKnm; /* 파트너정보-파트너명 */
    private String sellPrtnrCralTno; /* 판매자-휴대전화번호 */
    private String cralLocaraTno; /* 판매자-휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 판매자-휴대전화국번호암호화 */
    private String cralIdvTno; /* 판매자-휴대개별전화번호 */
    private String cstKnm; /* 계약자 정보-계약자명 */
    private String cstNo; /* 계약자 정보-사업자번호 */
    private String adrZip; /* 계약자 정보-우편번호 */
    private String cntrCstRnadr; /* 계약자 정보-기준주소 */
    private String cntrCstRdadr; /* 계약자 정보-상세주소 */
    private String rcgvpKnm; /* 설치정보-설치자명 */
    private String shpadrCralTno; /* 설치정보-휴대전화번호 */
    private String shpadrCralLocaraTno; /* 설치정보-휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String shpadrMexnoEncr; /* 설치정보-휴대전화국번호암호화 */
    private String shpadrCralIdvTno; /* 설치정보-휴대개별전화번호 */
    private String shpadrAdrZip; /* 설치정보-우편번호 */
    private String shpadrRnadr; /* 설치정보-기준주소 */
    private String shpadrRdadr; /* 설치정보-상세주소 */
    private String sellInflwChnlDtlNm; /* 판매구분 */
    private String empDvVal; /* 직원구분 */
    private String copnDvCd; /* 고객구분코드 */
    private String copnDvNm; /* 계약자구분 */
    private String mchnSellTpNm; /* 기기정보-판매유형(원주문) */
    private String mchnCntrNo; /* 기기계약번호 */
    private String mchnRcgvpKnm; /* 기기주문자 명 */
    private String mchnPdCd; /* 기기상품 */
    private String mchnPdNm; /* 기기상품명 */
    private String mchnSvTpNm; /* 기기용도 */
    private String mchnSvPrd; /* 기기주기 */
    private String mchnPdMclsfNm; /* 기기종류 */
    private String mchnPdLclsfNm; /* 기기구분 */
    private String pdTpNm; /* 상품선택유형 */
    private String stplPtrm; /* 의무기간 */
    private String cntrPtrm; /* 계약기간 */
    private String fnlAmt; /* 판매가격 */
    private String sellAmt; /* 공급가 */
    private String vat; /* 부가세 */
    private String cntrAmt; /* 계약총액 */
    private String mmBilAmt; /* 월청구액 */
    private String pdBaseAmt; /* 상품정상가격 */
    private String ackmtPerfRt; /* 인정실적률(%) */
    private String ackmtPerfAmt; /* 인정실적액 */
    private String dscMcn; /* 할인개월 */
    private String ctrAmt; /* 할인금액 */
    private String svTpNm; /* 용도구분 명 */
    private String spcYn; /* 스페셜구분 */
    private String svPrd; /* 배송주기 */
    private String sppFshDt; /* 배송일(배송완료일시) */
    private String sppMthdTpNm; /* 배송구분 */
    private String lctjobNm; /* 배송부서 */
    private String rglrSppBilDvNm; /* 청구구분 */
    private String mpyMthdTpNm; /* 계좌정보-자동이체 */
    private String txinvPblOjYn; /* 세금계산서 발행 */
    private String frisuBfsvcPtrmN; /* 프로모션 정보-무료개월 */
    private String lcmcnt; /* 무료회수 */
    private String lcck05Nm; /* 가격유형 */
    private String rcpPkgYn; /* 패키지여부 */
    private String rcpPkgCd; /* 패키지코드 */
    private String rcpPkgNm; /* 패키지명 */
    private String pkgYn; /* 패키지여부 */
    private String pkgPrcApy; /* 패키지가격 적용 */
    private String pkgclsfNm; /* 패키지군 */
    private String pkgCd; /* 패키지코드 */
    private String pkgNm; /* 패키지명 */
    private String lcscnt; /* 배송기준횟수 */
    private String freCnfmYn; /* 확정유무 */
    private String ordCnfmYn; /* 주문확정유무 */
    private String dCnfmYn; /* 일매출 확정 */
    private String frisuYn; /* 체험유무 */
    private String lcecdd; /* 체험센터배송 */
    private String cntrRcpFshDt; /* 접수일 */
    private String sppDuedt; /* 예정일 */
    private String fshSppFshDt; /* 최초배송일 */
    private String istDt; /* 매출일 */
    private String cntrPdEnddt; /* 만료일 */
    private String cltnRqdt; /* 해약요청일 */
    private String reqdDt; /* 해약일 */
    private String sppFshM; /* 배송기준년월 */
    private String cntrCnfmDtm; /* 주문확정일시 */
    private String slDtm; /* 일매줄확정일시 */
    private String freSppFshD; /* 체험배송일 */
    private String lkCntrDtlNo; /* 연계정보-계약번호 */
    private String lkCstKnm; /* 연계정보-계약자명 */
    private String lkPdCd; /* 연계정보-상품코드 */
    private String lkPdNm; /* 연계정보-상품명 */
    private String lkIstDt; /* 연계정보-설치일 */
    private String lkIstNmnN; /* 연계정보-설치차월 */
    private String lkReqdDt; /* 연계정보-철거일자 */
    private String pmotNm; /* 프로모션명 */
    private String pmotTpCd; /* 프로모션유형 */
    private String pmotCd; /* 프로모션 코드 */
    private String pmotSn; /* 프로모션 순번 */
    private String fstRgstDt; /* 등록일 */
    private String fstRgstTm; /* 등록시간 */
}
