package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WctaManagementDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaManagementDto-SearchKssOrdrListReq")
    public record SearchKssOrdrListReq(
        String cntrDv, /* 계약구분 */
        String cntrRcpStrtdt, /* 작성일자(시작일자) */
        String cntrRcpEnddt, /* 작성일자(종료일자) */
        String cntrwTpCd, /* 계약서구분(계약서유형코드) */
        List<String> alncmpCd, /* 계약서구분2(상조관련) */
        List<String> dgr2LevlOgId, /* 조직코드(지역단) */
        List<String> dgr3LevlOgId, /* 조직코드(지점) */
        List<String> cntrPrgsStatCd, /* 계약상태 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstKnm, /* 고객명 */
        String cntrCstNo, /* 고객번호 */
        String sellChnlSnrDv /* 판매채널 */
    ) {}

    //계약관리 - 계약상세(일시불(환경가전),일시불(BH),렌탈,멤버십,홈케어서비스,모종일시불,정기배송,장기할부) Search Request Dto
    @ApiModel(value = "WctaManagementDto-SearchLspyOrdrDtptListReq")
    public record SearchLspyOrdrDtptListReq(
        String cntrNo, /* 계약번호 */
        String cntrwTpCd, /* 계약서구분(계약서유형코드) */
        String cntrPrgsStatCd /* 계약진행상태코드 */
    ) {}

    //계약관리 - 확정승인 Save Request Dto
    @ApiModel(value = "WctaManagementDto-SaveConfirmApprovalsReq")
    public record SaveConfirmApprovalsReq(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cnfmMsgYn /* 확정승인 메세지 */
    ) {}

    //계약관리 - 알림톡 발송 Save Request Dto
    @ApiModel(value = "WctaManagementDto-SaveNotificationTalkFwsReq")
    public record SaveNotificationTalkFwsReq(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDv, /* 계약상세구분 */
        String stplRcpDt, /* 접수일자(계약일자) */
        String stplPtrm /* 약정개월(의무사용기간) */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //계약관리 - 계약구분(신규/변경,신규,계약변경,재약정,직원구매) Search Result Dto
    @ApiModel("WctaManagementDto-SearchRes")
    public record SearchRes(
        List<WctaManagementDto.SearchKssOrdrListRes> searchKssOrdrListResList,
        List<WctaManagementDto.SearchRePromConcListRes> searchRePromConcListResList,
        List<WctaManagementDto.SearchEmployeePurchaseListRes> searchEmployeePurchaseListResList
    ) {}

    //계약관리 - 계약구분(신규/변경,신규,계약변경) Search Result Dto
    @ApiModel("WctaManagementDto-SearchKssOrdrListRes")
    public record SearchKssOrdrListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrCt, /* 계약건수(CONC_CNT) */
        String dgr2LevlOgCd, /* 조직코드(지역단) */
        String dgr3LevlOgCd, /* 조직코드(지점) */
        String dgr2LevlOgNm, /* 조직코드명(지역단) */
        String dgr3LevlOgNm, /* 조직코드명(지점) */
        String cntrDtlNo, /* 계약상세번호 */
        String cntrCstNo, /* 고객번호 */
        String sellPrtnrNo, /* 판매자 사번 */
        String cntrPrgsStatNm2, /* 상태 */
        String cntrPrgsStatNm, /* 계약상태 */
        String cstStlmInMthNm, /* 계약방식 */
        String cntrwTpNm, /* 상품군 */
        String cstKnm, /* 고객명 */
        String fstRgstDtm, /* 작성일시 */
        String cntrRcpFshDtm, /* 접수일시 */
        String cntrCanDtm, /* 삭제(취소)일시 */
        String cntrCanUsrId, /* 계약취소자 */
        String cntrAprCn, /* 확정승인요청건수 */
        String rgstDv, /* 등록구분 */
        String rgstDvNm, /* 등록구분명 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrwTpCd, /* 계약서구분 */
        String cstSignCn, /* 고객서명내용 */
        String cntrtEmadr, /* 계약자메일 */
        String prtnrEmadr, /* 판매자 이메일 */
        String emilSndgYn, /* 계약서 발송여부 */
        String emilSndgDt, /* 계약서 발송일자 */
        String notakFwDt, /* 알림톡 발송일자 */
        String talkRcvYn, /* 알림톡 수신여부 */
        String notakRcvDt /* 알림톡 수신일자 */
    ) {}

    //계약관리 - 계약구분(재약정) Search Result Dto
    @ApiModel("WctaManagementDto-SearchRePromConcListRes")
    public record SearchRePromConcListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstKnm, /* 고객명 */
        String pdNm, /* 상품 */
        String hgrPdCd, /* 모종코드 */
        String hgrPdNm, /* 모종코드명 */
        String feeAckmtCt, /* 인정건수 */
        String feeAckmtBaseAmt, /* 기준수수료 */
        String cntrPrgsStatNm, /* 계약상태 */
        String stplTpNm, /* 약정유형 */
        String stplPtrm, /* 약정개월 */
        String stplStrtdt, /* 약정시작일자 */
        String stplEnddt, /* 약정종료일자 */
        String stplDscAmt, /* 약정할인금액 */
        String stplRcpDt, /* 접수일자 */
        String rentalTn, /* 재약정 가입차월 */
        String stplCnfmDt, /* 약정확정일자 */
        String stplCanDt, /* 약정취소일자 */
        String reqdDt, /* 철거일자 */
        String prtnrKnm, /* 판매자 */
        String rcpPrtnrNo, /* 판매자 사번 */
        String ogCd, /* 판매자 지점코드 */
        String notakRcvYn, /* 알림톡 수신여부 */
        String notakFwDt, /* 알림톡 발송일자 */
        String basePdCd /* 상품코드 */
    ) {}

    //계약관리 - 계약구분(직원구매) Search Result Dto
    @ApiModel("WctaManagementDto-SearchEmployeePurchaseListRes")
    public record SearchEmployeePurchaseListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrCstNo, /* 고객번호 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String cntrwTpCd, /* 계약서유형코드 */
        String cntrwTpNm, /* 계약서유형코드명 */
        String cstKnm, /* 고객명 */
        String dgr2LevlOgCd, /* 조직코드(지역단) */
        String dgr3LevlOgCd, /* 조직코드(지점) */
        String dgr2LevlOgNm, /* 조직코드명(지역단) */
        String dgr3LevlOgNm, /* 조직코드명(지점) */
        String sellPrtnrNo, /* 판매자 사번 */
        String prtnrKnm, /* 판매자 */
        String wrpBlg, /* 작성자 소속 */
        String fstRgstUsrId, /* 작성자 사번 */
        String fstRgstUsrNm, /* 작성자 */
        String fstRgstDtm, /* 작성일시 */
        String cntrRcpFshDtm, /* 접수일시 */
        String istDuedt, /* 설치예정일 */
        String slDt, /* 매출일 */
        String sellTpCd, /* 판매유형 */
        String sellTpDtlNm, /* 판매유형코드명 */
        String svPdTpCd, /* 용도 */
        String svPdTpNm, /* 용도명 */
        String svPrd, /* 주기 */
        String svPrdNm, /* 용도명 */
        String mchnChYn, /* 기기변경여부 */
        String mchnChBfCd, /* 기기변경(이전) 코드 */
        String ensmRernt, /* 임직원 재렌탈 */
        String inqCstCd /* 문의고객코드 */
    ) {}

    //계약관리 - 계약상세(일시불(환경가전),일시불(BH),렌탈,멤버십,홈케어서비스,모종 일시불,정기배송,장기할부) Search Result Dto
    @ApiModel("WctaManagementDto-SearchRes")
    public record SearchCntrDtlRes(
        List<WctaManagementDto.SearchLspyOrdrDtptListRes> searchLspyOrdrDtptListResList,
        List<WctaManagementDto.SearchBhOrdrDtptListRes> searchBhOrdrDtptListResList,
        List<WctaManagementDto.SearchRentOrdrDtptListRes> searchRentOrdrDtptListResList,
        List<WctaManagementDto.SearchMbOrdrDtptListRes> searchMbOrdrDtptListResList,
        List<WctaManagementDto.SearchHcsOrdrDtptListRes> searchHcsOrdrDtptListResList,
        List<WctaManagementDto.SearchPlantsOrdrDtptListRes> searchPlantsOrdrDtptListResList,
        List<WctaManagementDto.SearchRglrDlvrOrdrDtptListRes> searchRglrDlvrOrdrDtptListResList
    ) {}

    //계약관리 - 계약상세(일시불(환경가전)) Search Result Dto
    @ApiModel("WctaManagementDto-SearchLspyOrdrDtptListRes")
    public record SearchLspyOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrRcpFshDt, /* 접수완료일 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrwTpCd, /* 계약서유형코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String sellOgTpCd, /* 판매조직유형코드 */
        String sellPrtnrNo, /* 판매자사번 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품 */
        String pdQty, /* 상품수량 */
        String svPdTpCd, /* 용도 */
        String svPdTpNm, /* 용도명 */
        String svPrd, /* 서비스주기 */
        String svPrdNm, /* 서비스주기명 */
        String frisuAsPtrmN, /* 무상개월 AS */
        String mshJYn, /* 무상여부 */
        String frisuBfsvcPtrmN, /* 무상개월 BS */
        String nocmDvCd, /* 무상구분코드 */
        String fnlAmt, /* 상품금액 */
        String cntrAmt, /* 계약금 총액 */
        String cshStlmAmt, /* 현금결제 */
        String cardStlmAmt, /* 카드결제 */
        String crpUcAmt, /* 법인미수 */
        String istmPcamAmt, /* 할부원금 */
        String istmMcn, /* 할부개월 */
        String istmIntAmt, /* 할부수수료 */
        String mmIstmAmt, /* 월납부액 */
        String aftnDvCd, /* 자동이체 */
        String mshYn, /* 멤버십여부 */
        String mshSspcs, /* 멤버십회비 */
        String dpTpNm, /* 할부수수료 */
        String mchnChYn, /* 기기변경 */
        String mchnChCntrNo, /* 기변 기준계약번호 */
        String mchnChCntrSn, /* 기변 기준계약일련번호 */
        String sellEvCd, /* 행사코드 */
        String sellEvNm, /* 행사코드명 */
        String alncmpCd, /* 제휴사코드 */
        String alncmpNm, /* 제휴사코드명 */
        String alncJAcN, /* 제휴 구좌수 */
        String alncCntrNo /* 제휴계좌번호 */
    ) {}

    //계약관리 - 계약상세(일시불(BH)) Search Result Dto
    @ApiModel("WctaManagementDto-SearchBhOrdrDtptListRes")
    public record SearchBhOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품 */
        String pdQty, /* 상품수량 */
        String fnlAmt, /* 상품총금액 */
        String cntrAmt, /* 계약금 총액 */
        String istmPcamAmt, /* 할부원금 */
        String istmMcn, /* 할부개월 */
        String istmIntAmt, /* 할부수수료 */
        String mmIstmAmt, /* 월납부액 */
        String aftnDvCd, /* 자동이체 */
        String cttMoCn /* 요청사항 */
    ) {}

    //계약관리 - 계약상세(렌탈) Search Result Dto
    @ApiModel("WctaManagementDto-SearchRentOrdrDtptListRes")
    public record SearchRentOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품 */
        String pdQty, /* 상품수량 */
        String sellDscDvCd, /* 판매할인구분코드 */
        String sellDscDvNm, /* 판매할인구분코드명 */
        String sellDscTpCd, /* 판매할인유형코드 */
        String sellDscTpNm, /* 판매할인유형코드명 */
        String svPdTpCd, /* 용도 */
        String svPdTpNm, /* 용도명 */
        String svPrd, /* 서비스주기 */
        String svPrdNm, /* 서비스주기명 */
        String pdBaseAmt, /* 렌탈료(기본약정) */
        String sellAmt, /* 할인적용가격 */
        String sellEvCd, /* 행사코드 */
        String sellEvNm, /* 행사코드명 */
        String alncmpCd, /* 제휴사코드 */
        String alncmpNm, /* 제휴사코드명 */
        String mchnChYn, /* 기기변경 */
        String pmotCd, /* 할인유형(프로모션코드) */
        String pmotNm, /* 할인유형(프로모션코드명) */
        String aftnDvCd, /* 자동이체 */
        String prmYn, /* 선납여부 */
        String dcevdnDocId /* 첨부파일(증빙서류문서ID) */
    ) {}

    //계약관리 - 계약상세(멤버십) Search Result Dto
    @ApiModel("WctaManagementDto-SearchMbOrdrDtptListRes")
    public record SearchMbOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품 */
        String pdQty, /* 상품수량 */
        String mshSspcs, /* 멤버십회비 */
        String aftnDvCd, /* 자동이체 */
        String prmYn /* 선납여부 */
    ) {}

    //계약관리 - 계약상세(홈케어서비스) Search Result Dto
    @ApiModel("WctaManagementDto-SearchHcsOrdrDtptListRes")
    public record SearchHcsOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품 */
        String sellAmt, /* 판매금액 */
        String prdtAdtlInfo1, /* 상품추가정보1 */
        String prdtAdtlInfo2, /* 상품추가정보2 */
        String bdtMnftCoCd, /* 제조사 코드 */
        String bdtMnftCoNm, /* 제조사명 */
        String aftnDvCd /* 자동이체 */
    ) {}

    //계약관리 - 계약상세(모종 일시불) Search Result Dto
    @ApiModel("WctaManagementDto-SearchPlantsOrdrDtptListRes")
    public record SearchPlantsOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrwTpCd, /* 계약서 구분(계약유형코드) */
        String cntrwTpNm, /* 계약서 구분(계약유형코드명) */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* 판매유형코드명 */
        String sellTpDtlCd, /* 판매유형상세코드 */
        String sellTpDtlNm, /* 판매유형상세코드명 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String rglrSppMchnTpNm, /* 정기배송기기유형(기기종류) */
        String wellsFarmCntrDtlNo, /* 웰스팜 계약상세번호 */
        String prchsTpNm, /* 구매유형 */
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String pdQty, /* 상품수량 */
        String sellAmt /* 판매금액 */
    ) {}

    //계약관리 - 계약상세(정기배송) Search Result Dto
    @ApiModel("WctaManagementDto-SearchRglrDlvrOrdrDtptListRes")
    public record SearchRglrDlvrOrdrDtptListRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrwTpCd, /* 계약서 구분(계약유형코드) */
        String cntrwTpNm, /* 계약서 구분(계약유형코드명) */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrPrgsStatNm, /* 계약진행상태코드명 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* 판매유형코드명 */
        String sellTpDtlCd, /* 판매유형상세코드 */
        String sellTpDtlNm, /* 판매유형상세코드명 */
        String rcgvpKnm, /* 이름(설치자명) */
        String istCralTno, /* 휴대전화번호 */
        String istCralLocaraTno, /* 휴대지역전화번호 */
        String istMexnoEncr, /* 휴대전화국번호암호화 */
        String istCralIdvTno, /* 휴대개별전화번호 */
        String istAdrZip, /* 우편번호 */
        String istAdr, /* 설치처 정보 */
        String istRnadr, /* 기준주소 */
        String istRdadr, /* 상세주소 */
        String rglrSppMchnTpNm, /* 정기배송기기유형(기기종류) */
        String prchsTpNm, /* 구매유형 */
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String pdQty, /* 상품수량 */
        String sellAmt /* 판매금액 */
    ) {}

    //계약관리 - 확정 요청 승인 체크 Search Result Dto
    @ApiModel("WctaManagementDto-SearchOrdrInfo4ReqDfntRes")
    public record SearchOrdrInfo4ReqDfntRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String sellInflwChnlDtlCd, /* 판매유입채널상세코드 */
        String cstStlmInMthCd, /* 고객결제입력방법코드 */
        String pymnSkipYn, /* 확정승인스킵여부 */
        String cntrwTpCd, /* 계약서 구분(계약유형코드) */
        String sellOgTpCd, /* 조직유형코드 */
        String cntrPrgsStatCd /* 계약진행상태코드 */
    ) {}

    //계약관리 - 미승인 확정 요청건 조회 Search Result Dto
    @ApiModel("WctaManagementDto-SearchDfntAckdReqListRes")
    public record SearchDfntAckdReqListRes(
        String cntrAprId, /* 계약승인ID */
        String aprvYn, /* 승인여부 */
        String cntrAprAkDvCdNm, /* 계약승인요청구분코드명 */
        String cntrAprAkDvCd, /* 계약승인요청구분코드 */
        String ackdCnftMsg /* 승인 확인 메시지 */
    ) {}

    //계약관리 - 계약정보 조회 Search Result Dto
    @ApiModel("WctaManagementDto-SearchMastOrdrDtptRes")
    public record SearchMastOrdrDtptRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cntrwTpCd, /* 계약서구분(계약서유형코드) */
        String cntrPrgsStatCd, /* 계약진행상태코드 */
        String cntrRcpFshDt, /* 접수일자 */
        String cntrCralLocaraTno, /* 계약자 휴대지역전화번호 */
        String cntrMexnoEncr, /* 계약자 휴대전화국번호암호화 */
        String cntrCralIdvTno, /* 계약자 휴대개별전화번호 */
        String sellTpCd, /* 판매유형코드 */
        String cstKnm, /* 고객명 */
        String cntrCnt, /* 그룹주문 건수 */
        String basePdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String sellTpDtlCd, /* 상품각사속성상세 */
        String svAlncBzsCd, /* 서비스제휴업체 */
        String sppDuedt, /* 배송일자 */
        String sowDuedt /* 파종예정일자 */
    ) {}

    //계약관리 - 확정처리 전 계약진행상태코드 체크 Search Result Dto
    @ApiModel("WctaManagementDto-SearchOrderStatCdInfoRes")
    public record SearchOrderStatCdInfoRes(
        String cntrPrgsStatCd /* 계약진행상태코드 */
    ) {}
}
