package com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1005.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * EAI_CBDO1005 출고요청취소 Response Interface Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Getter
@Setter
public class LogisticsOutOfStorageCancelResIvo {

    @JsonProperty("resultCode")
    private String resultCode;

    @JsonProperty("resultMessage")
    private String resultMessage;
}
