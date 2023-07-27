package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaFilterContractInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  고객필터정보관리 - 필터계약정보 조회 Search Request Dto
    @Builder
    @ApiModel("WctaFilterContractInqrDto-SearchReq")
    public record SearchReq(

        String qryCond,
        String filterYrMn,
        String svPdTpCd,
        String pdCd,
        String cntrNo,
        String cntrSn

    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  고객필터정보관리 - 필터계약정보 조회 Search Result Dto
    @ApiModel("WctaFilterContractInqrDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String filterPdCd1,
        String filterPdNm1,
        String filterPdCd2,
        String filterPdNm2,
        String filterPdCd3,
        String filterPdNm3,
        String filterPdCd4,
        String filterPdNm4,
        String filterPdCd5,
        String filterPdNm5,
        String svPdTpCd,
        String svPdTpNm,
        String svPrd,
        String sellTpCd,
        String sellTpNm,
        String mbGbn,
        String sldt,
        String cttDt,
        String istGbn,
        String filterAmt,
        String filterQty,
        String cttRsCd,
        String reCttYn,
        String cttPsicId,
        String sconCn,
        String istAkArtcMoCn
    ) {
    }
}
