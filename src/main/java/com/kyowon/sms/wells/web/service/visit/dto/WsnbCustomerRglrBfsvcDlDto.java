package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WsnbCustomerRglrBfsvcDlDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnbCustomerRglrBfsvcDlDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cstSvAsnNo,
        @NotBlank
        String asnOjYm
    ) {}

}
