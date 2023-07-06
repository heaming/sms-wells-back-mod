package com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * EAI_CBDO1007 실시간 재고조회 EAI Request Ivo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-28
 */

@Getter
@Setter
public class RealTimeGradeStockReqIvo {

    @JsonProperty("sapPlntCd")
    private String sapPlntCd;

    @JsonProperty("sapSaveLctCd")
    private String sapSaveLctCd;

    @JsonProperty("itmPdCds")
    private List<String> itmPdCds;
}
