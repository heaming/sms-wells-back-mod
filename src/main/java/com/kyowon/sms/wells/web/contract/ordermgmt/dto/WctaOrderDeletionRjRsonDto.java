package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDeletionRjRsonDto {
    @Builder
    @ApiModel("WctaOrderDeletionRjRsonDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrAprId,
        @NotBlank
        String cntrNo,
        String rjRsonCn
    ) {}
}
