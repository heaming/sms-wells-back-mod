package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableDisableDaysDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableMojongDaysDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableMonthScheduleDvo;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WsncTimeTableDto {

    @ApiModel(value = "WsncTimeTableDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String prevTag,
        String gbCd,
        String dataGb,
        String prtnrNo,
        @NotBlank
        String selDate,
        @NotBlank
        String saleCd,
        String ordDt,
        String ordSeq,
        String wrkTypDtl,
        String zipno,
        @NotBlank
        String cntrNo,
        String mojongCd,
        String inGb,
        String gdsCd,
        String wrkDt,
        String dataStus
    ) {}

    @ApiModel(value = "WsncTimeTableDto-SearchRes")
    public record SearchRes(
        String baseY,
        String baseMm,
        String baseD,
        String dowDvCd,
        String dfYn,
        String phldYn,
        String rmkCn,
        String zip,
        String wrkTypDtl,
        String gbCd,
        String dataGb,
        String cntrNo,
        String selDate,
        String ordDt,
        String ordSeq,
        String prtnrNo,
        String saleCd,
        List<WsncTimeTableMojongDaysDvo> list1,
        List<WsncTimeTableMonthScheduleDvo> ordcnt,
        List<WsncTimeTableDisableDaysDvo> diabledays

    ) {}
}
