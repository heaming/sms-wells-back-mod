package com.kyowon.sms.wells.web.contract.changeorder.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbRentalMshSpayUseDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WctbRentalMshSpayUseDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String paramInqrDv, /* 조회구분 */
        String paramCstNo, /* 고객번호 */
        String paramCntrNo, /* 계약번호 */
        String paramSellerCd, /* 판매자코드 */
        String paramRgstDt, /* 등록일자 */
        String paramRgstHh, /* 등록시간 */
        String paramStrtDt, /* 시작일자 */
        String paramEndDt /* 종료일자 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctbRentalMshSpayUseDto-SearchRes")
    public record SearchRes(

        String rsCt1, /* 정수기수 */
        String rsCt2, /* 비데건수 */
        String rsCt3, /* 청정기수 */
        String rsCt4, /* 연수기건수 */
        String rsCt5, /* 음처건수 */
        String rsCt6, /* 커피머신 */
        String rsCt7, /* 전기레인지 */
        String rsCt8, /* 재배기 */
        String rsCt9, /* 안마의자 */
        String rsCt10, /* 건조기 */
        String rsCt11, /* 세탁기 */
        String rsCt12, /* 여분 */
        String rsCt13, /* 여분 */
        String rsCt14, /* 여분 */
        String rsCt15, /* 여분 */
        String rsCheckYn /* 결과체크여부 */

    ) {}
}
