package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

public class WsnbPointmallStatusDto {

    @ApiModel(value = "WsnbPointmallStatusInfDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, /* 계약번호 */
        @JsonProperty("AS_IST_OJ_NO")
        String asIstOjNo /* AS설치대상번호 */
    ) {}

    @ApiModel(value = "WsnbPointmallStatusInfDto-SearchRes")
    public record SearchRes(
        @JsonProperty("WK_ACPTE_STAT_CD")
        String wkAcpteStatCd, /* 작업수락상태코드 */
        @JsonProperty("WK_ACPTE_DT")
        String wkAcpteDt, /* 작업수락일자 */
        @JsonProperty("WK_ACPTE_HH")
        String wkAcpteHh, /* 작업수락시간 */
        @JsonProperty("VST_CNFMDT")
        String vstCnfmdt, /* 방문확정일자 */
        @JsonProperty("VST_CNFM_HH")
        String vstCnfmHh, /* 방문확적시간 */
        @JsonProperty("WK_EXCN_DT")
        String wkExcnDt, /* 작업수행일자 */
        @JsonProperty("WK_EXCN_HH")
        String wkExcnHh, /* 작업수행시간 */
        @JsonProperty("WK_PRGS_STAT_CD")
        String wkPrgsStatCd, /* 작업진행상태코드 */
        @JsonProperty("RTNGD_YN")
        String rtngdYn /* 반품여부 */
    ) {}
}
