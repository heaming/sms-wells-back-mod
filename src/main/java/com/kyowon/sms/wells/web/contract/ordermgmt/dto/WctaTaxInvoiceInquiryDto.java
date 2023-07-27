package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaTaxInvoiceInquiryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 주문상세페이지 내부 팝업_세금계산서, 계산서 Save Request Dto
    @Builder
    @ApiModel("WctaTaxInvoiceInquiryDto-SearchReq")
    public record SaveReq(
        @NotBlank
        String txinvPblOjYn, /* 세금계산서발행여부 */
        @NotBlank
        String bzrno, /* 사업자등록번호 */
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotNull
        int cntrSn, /* 계약일련번호 */
        @NotBlank
        String dlpnrPsicNm, /* 거래처담당자명 */
        @NotBlank
        String cralLocaraTno, /* 휴대지역전화번호 */
        @NotBlank
        String mexno, /* 휴대전화국번호암호화 */
        @NotBlank
        String cralIdvTno, /* 휴대개별전화번호 */
        @NotBlank
        String txinvPblD, /* 세금계산서발행일 */
        @NotBlank
        String emadr, /* 이메일 주소 */
        String dlpnrNm, /* 거래처명 */
        String emadr1, /* @앞 메일주소 */
        String emadr2, /* @뒤 메일주소 */
        String cntrCnfmDtm, /*  계약확정일시 */
        String dlpnrCd, /* 거래처 코드 */
        String dpTpCd, /* 입금유형코드 */
        String sellTpCd, /* 판매유형코드 */
        String bfchCn /* 변경 전 내용 */
    ) {}
}
