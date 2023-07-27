package com.kyowon.sms.wells.web.contract.changeorder.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbPrepaymentChCstPsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WctbPrepaymentChCstPsDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slClYm, /*관리년월 */
        @NotBlank
        String ogCd, /* 조직코드 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String pdHclsfId, /* 상품대분류ID */
        String pdMclsfId /* 상품중분류ID */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctbPrepaymentChCstPsDto-SearchRes")
    public record SearchRes(

        String ogCd, /*조직코드 AKDDPT*/
        String cntrDtlNo, /*계약번호(-)계약일련번호 LCCODE02*/
        String cstKnm, /*고객명 LCCNAM*/
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 전화국번호암호화 */
        String cralIdvTno, /* 개별전화번호 */
        String pdNm, /*상품 LC31.LCICDE KA11.KAINAM*/
        String pdCd, /*상품 LC31.LCICDE KA11.KAINAM*/
        String pdClsfNm, /* 상품분류 */
        String prmPtrm, /*선납기간 LCPSED*/
        int prmMcn, /*선납개월 LCPMON*/
        int prmDscr, /*선납할인(율) LCPRAT*/
        String prmPtrmThm, /*선납기간 LCPSED02*/
        int prmMcnThm, /*선납개월 LCPMON02*/
        int prmDscrThm /*선납할인(율) LCPRAT02*/

    ) {}
}
