package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import com.sds.sflex.system.config.validation.validator.ValidMonth;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 삼성전자 상품관리 DTO
 * </pre>
 *
 * @author SAVEMEGOAT
 * @since 2023-05-24
 */
public class WcteSecProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchNotInstalledReq", description = "삼성전자 주문 정보 미설치 Search Request Dto")
    public record SearchNotInstalledReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,

        String cntrNo,
        Integer cntrSn,
        String cntrCstKnm,
        String strtOgCd,
        String endOgCd
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchReservationReq", description = "삼성전자 주문 정보 예약일 Search Request Dto")
    public record SearchReservationReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,
        String sellTpCd
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchConfirmReq", description = "삼성전자 주문 정보 확정일 Search Request Dto")
    public record SearchConfirmReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,
        String sellTpCd,
        String sppBzsOrdId,
        String pdctIdno
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-CreateConfirmReq", description = "삼성전자 주문 정보 확정일 Request Dto")
    public record CreateConfirmReq(
        @NotBlank
        String cntrNo,
        int cntrSn,
        @NotBlank
        @ValidDate
        String sppFshDt,
        @NotBlank
        String pdctIdno,
        @NotBlank
        String sppBzsModelId,
        @NotBlank
        String sppBzsOrdId
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchShippingReq", description = "삼성전자 주문 정보 배송일 Request Dto")
    public record SearchShippingReq(
        @NotBlank
        @ValidDate
        String strtdt,
        @NotBlank
        @ValidDate
        String enddt,
        List<String> pdMclsfIds,
        List<String> pdCds,
        String isCombi
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchFreeAsRes", description = "Search Free As Res Dto")
    public record SearchFreeAsReq(
        @ValidDate
        String cntrCnfmStrtdt,
        @ValidDate
        String cntrCnfmEnddt,
        @ValidDate
        String istStrtdt,
        @ValidDate
        String istEnddt,
        String cntrDtlStatCd,
        String cntrNo,
        Integer cntrSn,
        String cntrCstKnm,
        String pdCd,
        String pdNm,
        String pdctIdno,
        @Pattern(regexp = "[YN]|^$")
        String slStpYn,
        @Pattern(regexp = "[YN]|^$")
        String afterTgYn,
        @ValidMonth
        String afterTgBaseYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************

    @ApiModel(value = "WcteSecProductDto-SearchNotInstalledRes", description = "삼성전자 주문 정보 미설치 Response Dto")
    public record SearchNotInstalledRes(
        String cntrNo,
        int cntrSn,
        String cntrCstKnm,
        String sppBzsOrdId,
        String pdHclsfNm,
        String pdMclsfNm,
        String cntrCnfmDtm,
        String duedt,
        String prtnrNo,
        String prtnrKnm,
        String prtnrOgCd,
        String canRson
    ) {}

    @ApiModel(value = "WcteSecProductDto-SearchReservationRes", description = "삼성전자 주문 정보 예약일 Response Dto")
    public record SearchReservationRes(
        String ogCd,
        String hooPrtnrNo,
        String sellPrtnrNo,
        String prtnrKnm,
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String pdNm,
        String pdCd,
        String sppBzsOrdId,
        String sellTpCd,
        String resDt,
        String stocStrDt,
        String fstRgstDtm
    ) {}

    @ApiModel(value = "WcteSecProductDto-SearchConfirmRes", description = "삼성전자 주문 정보 확정일 Response Dto")
    public record SearchConfirmRes(
        String cntrNo,
        int cntrSn,
        String sellTpCd,
        String pdHclsfId,
        String pdMclsfId,
        String rcgvpKnm,
        String sppBzsOrdId,
        String sppFshDt,
        String pdCd,
        String pdNm,
        String sppFshRgstDtm,
        String batWkFshDtm,
        String canDt,
        String cttRsCd,
        String pdctIdno,
        String sppBzsModelId,
        String rgstFeeFlpymYn
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchSecPdBycfRes", description = "삼성전자 주문 정보 배송 중분류된 상품 Response Dto")
    public record SearchSecPdBycfRes(
        String pdMclsfId,
        String pdMclsfNm,
        String pdCd,
        String pdNm
    ) {}

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchShippingRes", description = "삼성전자 배송 조회 응답 객체")
    public record SearchShippingRes(
        String blkYn1,
        String blkYn2,
        String blkYn3,
        String blkYn4,
        String cttOrCnfmDtm,
        String cntrNo,
        Integer cntrSn,
        String sppBzsOrdId,
        String ojDtlCntrNo,
        Integer ojDtlCntrSn,
        String rcgvpKnm,
        String zip,
        String adr,
        String dtlAdr,
        String pdMclsfId,
        String pdMclsfNm,
        String pdCd,
        String pdNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String prtnrCralLocaraTno,
        String prtnrMexnoEncr,
        String prtnrCralIdvTno,
        String sellTpCd
    ) {
        public SearchShippingRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            prtnrMexnoEncr = DbEncUtil.dec(prtnrMexnoEncr);
        }
    }

    @Builder
    @ApiModel(value = "WcteSecProductDto-SearchFreeAsReq", description = "Search Free As Req Dto")
    public record SearchFreeAsRes(
        String cntrNo,
        int cntrSn,
        String cntrCstKnm,
        String pdCd,
        String pdNm,
        String pdctIdno,
        String cntrCnfmDtm,
        String istDt,
        String cntrPdEnddt,
        String canPdEnddt,
        String cntrDtlStatCd,
        Integer frisuAsMcn,
        Integer ssFrisuAsMcn,
        Integer kwFrisuAsMcn,
        String frisuEndDt,
        String slStpYn,
        String cntrPdStrtdt,
        String afterTgYn,
        String afterTgStDt,
        String afterTgEdDt,
        String dlqYn,
        Integer eotDlqAmt
    ) {}
}
