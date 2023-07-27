package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaEntrepreneurCustomerPssDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 법인(사업자) 고객 현황(일시불 및 렌탈) Search Request Dto
    @Builder
    @ApiModel("WctaEntrepreneurCustomerPssDto-SearchReq")
    public record SearchReq(
        String sellTpCd,
        String perfGbn,
        String empGbn,
        String canGbn,
        String dateGbn,
        String fromDate,
        String toDate,
        String fromRental,
        String toRental,
        String pdHclsf,
        String pdMclsf,
        String pdCd,
        String pdNm,
        String cstrGbn,
        String fntGbn,
        String fromOgCd,
        String toOgCd,
        String prtnrNo,
        String incentiveGbn,
        String dlpnrItemNm,
        String dlpnrBzclNm
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 법인(사업자) 고객 현황(일시불 및 렌탈) Search Result Dto
    @ApiModel("WctaEntrepreneurCustomerPssDto-SearchRes")
    public record SearchRes(
        String sellTpNm,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String copnDvCd,
        String bzrno,
        String bryy,
        String txnTpCd,
        String adrZip,
        String adr,
        String dtlAdr,
        String sellTpCd,
        String cntrTpCd,
        String basePdCd,
        String pdAbbrNm,
        String pdMclsfNm,
        String pdNm, /* 상품명 */
        String svPrd,
        String fnlPdUswyCd,
        String bznsSpptOgCd,
        String bizSpptPrtnrNo,
        String bznsSpptPrtnrKnm,
        String ackmtPerfAmt,
        String cntrAmt,
        String fnlAmt,
        String discountAmt,
        String pdBaseAmt,
        String recapDutyPtrmN,
        String rentalTn,
        String txinvPblYn,
        String txinvPblTpCd,
        String emadr,
        String mpyMthdTpCd,
        String mpyMthdTpNm,
        String mpyBsdt,
        String slDt,
        String canDt,
        String fulpyDt,
        String cntrPdStrtdt,
        String cntrPdEnddt,
        String prmEndMm,
        String prtnrNo,
        String prtnrKnm,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String ogCd,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String bldNm,
        String dscApyTpCd,
        String dscApyDtlCd,
        String chdvcYn,
        String rernt,
        String nw,
        String discSys,
        String dlpnrBzclNm,
        String dlpnrItemNm,
        String reqdDt,
        String chdvcCntrNo,
        String ojCntrNo,
        String ojCntrSn,
        String chdvcCntrStlmFshDtm,
        String chdvcCntrCanDtm,
        String chdvcBasePdCd,
        String chdvcPdNm,
        String chdvcSellRate,
        String chdvcSellAmt,
        String pkgPdCd,
        String pkgPdNm,
        String cntrAdrpcId,
        String cntrtRelCd,
        String ogTpCd,
        String pointDt,
        String pointAmt,
        String cntrCstNo,
        String pmotCd,
        String pmotDesc,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String fstRgstDt,
        String fstRgstTm,
        String fnlMdfcUsrId,
        String fnlMdfcDt,
        String fnlMdfcTm

    ) {}
}
