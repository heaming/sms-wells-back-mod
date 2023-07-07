package com.kyowon.sms.wells.web.service.visit.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0299PM01 (법인계약공통) 세금계산서발행요청
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.05.30
 */
public class WsnbTaxInvoicePublicationDto {

    @ApiModel(value = "WsnbTaxInvoicePublicationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstSvAsnNo,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String prtnrNo,
        List<String> cstSvAsnNos
    ) {
        public SearchReq {
            cstSvAsnNos = new ArrayList<>(Arrays.asList(cstSvAsnNo.split(",")));
        }
    }

    @ApiModel(value = "WsnbTaxInvoicePublicationDto-SearchRes")
    public record SearchRes(
        String rcgvpKnm,
        String cntrNo,
        String cntrSn,
        String pdAbbrNm,
        BigDecimal stlmAmt,
        String cstSvAsnNo,
        String sellTpNm,
        String copnDvNm,
        String pdUswyNm,
        String pdGdNm,
        String cntrCnfmDtm,
        BigDecimal mmIstmAmt,
        String csBilNo,
        String stlmDvCd,
        String cntrCstNo,
        String copnDvCd,
        String bzrno,
        String emadr
    ) {}

    @ApiModel(value = "WsnbTaxInvoicePublicationDto-EditReq")
    public record EditReq(
        @NotBlank
        String billDvCd,
        @NotBlank
        String bzrno,
        @NotBlank
        String emadr
    ) {}

}
