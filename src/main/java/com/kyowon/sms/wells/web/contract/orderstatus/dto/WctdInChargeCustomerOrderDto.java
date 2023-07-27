package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import com.kyowon.sms.common.web.contract.zcommon.utils.CtContractUtils;

import io.swagger.annotations.ApiModel;

public class WctdInChargeCustomerOrderDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 담당 고객 및 주문 조회 Search Request Dto
    @ApiModel("WctdInChargeCustomerOrderDto-SearchReq")
    public record SearchReq(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstKnm, /* 계약자명 */
        String cstNo, /* 고객번호 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexno, /* 휴대전화국번호 */
        String cralIdvTno /* 휴대개별전화번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 담당 고객 및 주문 조회 Search Result Dto
    @ApiModel("WctdInChargeCustomerOrderDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cstKnm, /* 계약자명 */
        String cstNo, /* 고객번호 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexno, /* 휴대전화국번호 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String mpNo, /* 휴대전화번호 */
        String sellTpNm, /* 계약구분 */
        String cntrDtlStatNm, /* 상태 */
        String pdNm, /* 상품명 */
        String adr, /* 기준주소 */
        String dtlAdr, /* 상세주소 */
        String cntrPrgsStatCd /* 계약진행상태코드 */
    ) {
        public SearchRes{
            mpNo =  CtContractUtils.buildTno(cralLocaraTno, mexno, cralIdvTno);
        }

    }
}




















