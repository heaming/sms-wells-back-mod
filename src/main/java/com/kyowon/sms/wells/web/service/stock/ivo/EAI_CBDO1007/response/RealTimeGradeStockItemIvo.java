package com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * EAI_CBDO1007 실시간 재고조회 EAI Response Ivo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-06
 */
@Getter
@Setter
public class RealTimeGradeStockItemIvo {

    @JsonProperty("itmPdCd")
    private String itmPdCd;

    @JsonProperty("lgstAGdQty")
    private BigDecimal lgstAGdQty;

    @JsonProperty("lgstBGdQty")
    private BigDecimal lgstBGdQty;

    @JsonProperty("lgstCGdQty")
    private BigDecimal lgstCGdQty;

    @JsonProperty("lgstFGdQty")
    private BigDecimal lgstFGdQty;

    @JsonProperty("lgstEGdQty")
    private BigDecimal lgstEGdQty;

    @JsonProperty("lgstRGdQty")
    private BigDecimal lgstRGdQty;

    @JsonProperty("lgstXGdQty")
    private BigDecimal lgstXGdQty;

}
