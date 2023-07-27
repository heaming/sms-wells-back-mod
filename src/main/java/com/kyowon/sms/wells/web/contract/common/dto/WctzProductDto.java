package com.kyowon.sms.wells.web.contract.common.dto;

import io.swagger.annotations.ApiModel;

public class WctzProductDto {
    @ApiModel("WctzProductDto-SearchMiddleClassesRes")
    public record SearchRes(
        String pdClsfId, // 상품분류ID
        String pdClsfNm, // 상품분류명
        String refPdClsfVal, // 참조상품분류값
        String hgrPdClsfId, // 상위상품분류ID
        String pdClsfCd // 상품분류코드
    ) {}
}
