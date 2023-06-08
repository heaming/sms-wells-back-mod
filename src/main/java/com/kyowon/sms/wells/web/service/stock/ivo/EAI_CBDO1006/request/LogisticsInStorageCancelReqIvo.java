package com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1006.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * EAI_CBDO1006 입고요청취소 Request Interface Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Getter
@Setter
public class LogisticsInStorageCancelReqIvo {

    @JsonProperty("strAkSn")
    private int strAkSn;

    @JsonProperty("sapPlntCd")
    private String sapPlntCd;

    @JsonProperty("strAkNo")
    private String strAkNo;

    @JsonProperty("akCanDeptId")
    private String akCanDeptId;

    @JsonProperty("akCanUsrId")
    private String akCanUsrId;
}
