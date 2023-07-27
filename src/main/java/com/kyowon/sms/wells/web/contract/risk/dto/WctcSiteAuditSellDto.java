package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcSiteAuditSellDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 현장감사 판매리스트 Search Request Dto
    @Builder
    @ApiModel("WctcSiteAuditSellDto-SearchReq")
    public record SearchReq(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String sellPrtnrNo,
        @NotBlank
        @Size(min = 1, max = 1)
        String ptrmDv,
        @NotBlank
        @ValidDate
        String dtStrt,
        @NotBlank
        @ValidDate
        String dtEnd,
        String cntrStatCd,
        String sellTpCd,
        String sellTpDtlCd,
        String pdMclsfId
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 현장감사 판매리스트 Search Result Dto
    @ApiModel("WctcSiteAuditSellDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        Integer perfCnt
    ) {}

    @ApiModel("WctcSiteAuditSellDto-SearchDetailRes")
    public record SearchDetailRes(
        String dgr1LevlOgCd,
        String dgr1LevlDgPrtnrNo,
        String dgr1LevlDgPrtnrNm,
        String dgr2LevlOgCd,
        String dgr2LevlDgPrtnrNo,
        String dgr2LevlDgPrtnrNm,
        String bldNm,
        String bldCd,
        String ogCd,
        String sellPrtnrNo,
        String prtnrKnm,
        String pstnDvCd,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String fstCntrDt,
        String rcntrDt,
        String cltnDt,
        String cntrTpCd,
        String cntrTpNm,
        String sellTpCd,
        String sellTpNm,
        String mchnChReIstGbn,
        String dtlCntrNo,
        String cstKnm,
        String copnDvCd,
        String bryyBzrno,
        String rcgvpKnm,
        String alncmpCd,
        String cntrCnfmDt,
        String istDt,
        String cntrPdEnddt,
        String reqdDt,
        String cntrCstNo,
        String cstRgstDt,
        Integer sellAmt,
        Integer ackmtPerfAmt,
        Integer ackmtPerfRt,
        String booSellTpYn,
        Integer dlqMcn,
        Integer eotDlqAmt,
        String initDqmYm,
        Integer dpTotAmt,
        Integer eotUcAmt,
        String slStpYn,
        Integer rentalTn,
        Integer cntrAmt,
        Integer ramt,
        String prchDv,
        String sellDscCd,
        String pmotCd,
        String ojDtlCntrNo,
        String ojCstKnm,
        String ojPdNm,
        String vlStrtDt,
        String vlEndDt,
        String bnkOwrKnm,
        String bnkNo,
        String bnkNm,
        String bnkMpyBsdt,
        String cdcoOwrKnm,
        String cdcoNo,
        String cdcoNm,
        String cdcoMpyBsdt,
        String pdHclsfNm,
        String pdMclsfNm,
        String pdNm,
        String pdCd,
        Integer pdqty,
        Integer subscAmt,
        Integer mmIstmAmt,
        Integer istmMcn,
        Integer cashTotAmt,
        Integer cardTotAmt1,
        String crcdnoEncr1,
        String cdcoNm1,
        String cdcoOwrKnm1,
        Integer crdcdIstmMcn1,
        Integer cardTotAmt2,
        String crcdnoEncr2,
        String cdcoNm2,
        String cdcoOwrKnm2,
        Integer crdcdIstmMcn2,
        Integer initBlam,
        Integer nowBlam,
        Integer blam1,
        Integer blam2,
        Integer blam3,
        Integer blam4,
        String buNotiDt,
        String buPrtnrNo,
        String buPrtnrNm,
        String buPstnDvCd,
        String cntrCltnYn,
        String buCltnDt,
        String cntrLocaraTno,
        String cntrExno,
        String cntrIdvTno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrAdr,
        String cntrDtlAdr,
        String adr,
        String dtlAdr,
        String initAdr,
        String initDtlAdr
    ) {}
}
