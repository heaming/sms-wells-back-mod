package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaSafeKeyCinfInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // Search Request Dto
    @ApiModel("WctaSafeKeyCinfInqrDto-SearchReq")
    @Builder
    public record SearchReq(
        String cntrNo,
        String sellTpCd,
        String cstNo,
        String cntrCnfmDtm,
        String copnDvCd,
        String cstKnm,
        String bryyMmdd,
        String bzrno,
        String sexDvCd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String basePdCd,
        String prtnrKnm,
        String prtnrNo,
        String ogCd,
        String ogTpCd,
        String bizSpptPrtnrNo,
        String pstnDvCd,
        boolean isCinfInqr
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // Search Result Dto
    @ApiModel("WctaSafeKeyCinfInqrDto-SearchCntrNoRes")
    @Builder
    public record SearchCstInfoRes(
        String fnlIsDtm,
        String sfkVal,
        String copnDvCd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {
        public SearchCstInfoRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel("WctaSafeKeyCinfInqrDto-SearchCntrNoRes")
    @Builder
    public record SearchRes(
        boolean isSuccess,
        String message,
        String fnlIsDtm,
        String sfkVal
    ) {}
}
