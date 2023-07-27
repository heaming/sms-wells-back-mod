package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;

import javax.annotation.RegEx;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class WctaReStipulationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 재약정 관리 Search Request Dto
    @Builder
    @ApiModel("WctaReStipulationDto-SearchReq")
    public record SearchReq(
        @Pattern(regexp = "[12]")
        String copnDvCd,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {
        public SearchReq {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr;
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 재약정 관리 Search Result Dto
    @ApiModel("WctaReStipulationDto-SearchRes")
    public record SearchRes(
        String sellTpCd,
        String cntrDtlStatCd,
        String copnDvCd,
        String cntrCstNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sexDvCd,
        String bryyMmdd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String pdNm,
        String stplPtrm,
        String istTn,
        String dlqYn,
        String reqdDt,
        String cntrPdEnddt,
        String cpsDt
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }
    }

    @ApiModel("WctaReStipulationDto-BasInfoRes")
    public record BasInfoRes(
        String stplDscAmt,        /*약정할인금액*/
        String rstlBaseTpCd,      /*재약정기준유형코드*/
        String rstlSellChnlDvCd,  /*재약정판매채널구분코드*/
        String rstlMcn,           /*재약정개월수*/
        String minRentalAmt,      /*최소렌탈금액*/
        String rstlDutyMcn,       /*재약정의무개월수*/
        String text               /*조회용 TEXT*/
    ) { }

    @ApiModel("WctaReStipulationDto-ContractRes")
    public record ContractRes(
        String cntrNo,
        String cntrSn,
        String rentalTn,       /*현재렌탈차월*/
        String stplPtrm,       /*의무사용기간*/
        String cntrPtrm,       /*총계약기간*/
        String istDt,          /*설치일자*/
        String cntrPdStrtdt,   /*상품시작일자*/
           int stplTn          /*약정회차*/
    ) { }
}
