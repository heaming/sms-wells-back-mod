package com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1005.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * EAI_CBDO1005 출고요청취소 Request Interface Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Getter
@Setter
public class LogisticsOutOfStorageCancelReqIvo {

    @JsonProperty("ostrAkSn")
    private int ostrAkSn;

    @JsonProperty("sapPlntCd")
    private String sapPlntCd;

    @JsonProperty("akCanDeptId")
    private String akCanDeptId;

    @JsonProperty("ostrAkNo")
    private String ostrAkNo;

    @JsonProperty("akCanUsrId")
    private String akCanUsrId;
}
