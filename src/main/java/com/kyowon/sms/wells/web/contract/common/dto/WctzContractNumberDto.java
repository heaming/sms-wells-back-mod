package com.kyowon.sms.wells.web.contract.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctzContractNumberDto {
    @Builder
    @ApiModel("WctzContractNumberDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String rsltYn
    ) {}
}
