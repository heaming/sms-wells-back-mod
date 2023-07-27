package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WctaContractIntegrationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //회원 통합 조회 Search Request Dto
    @ApiModel(value = "WctaContractIntegrationDto-SearchReq")
    public record SearchReq(
        String cntrCnfmStrtDtm,
        String cntrCnfmFinsDtm,
        String plarDv,
        String prtnrDv,
        String hmnrscEmpno,
        String ogTpCd,
        String ogCd,
        String cntrCstSeltDv,
        String cntrCstNo,
        String cntrCstNm,
        String cntrCstMpno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String bzrno,
        String sfkVal,
        String cntrNo,
        String cntrSn,
        List<String> sellTpCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //회원 통합 조회 Search Result Dto
    @ApiModel("WctaContractIntegrationDto-SearchRes")
    public record SearchRes(
        String ojSellTpNm,
        String cntrDtlNo,
        String cstKnm,
        String rcgvpKnm,
        String cstGdCd,
        String pdNm,
        String sellTpCd,
        String cntrCnfmDtm,
        String istDt,
        String cntrStat,
        String svPrd,
        String cancWtdrDt,
        String rentalTn,
        String fmmbN,
        String cntrTno,
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno,
        String cntrCralTno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String istTno,
        String istLocaraTno,
        String istExnoEncr,
        String istIdvTno,
        String istCralTno,
        String istCralLocaraTno,
        String istMexnoEncr,
        String istCralIdvTno,
        String copnDvCd,
        String dpTpCd,
        String dpTpNm,
        String mpyBsdt,
        String bryyMmdd,
        String bzrno,
        String txinvPblOjYn,
        String txinvPblD,
        String sexDvNm,
        String cntrCstNo,
        String adrZip,
        String instAddr,
        String sppOrdIvcNo,
        String sellOgNm,
        String istmMcn,
        String pdctReqdRqdt,
        String reqdDt,
        String histStrtDtm,
        String alncmpCd,
        String rsltYn,
        String sellPrtnrNo,
        String sellPrtnrKnm,
        String sellCralTno,
        String sellCralLocaraTno,
        String sellMexnoEncr,
        String sellCralIdvTno,
        String ogCd,
        String ogNm,
        String bsPrtnrNo,
        String bsPrtnrKnm,
        String bsOgCd,
        String bsOgNm,
        String bsCralTno,
        String bsCralLocaraTno,
        String bsMexnoEncr,
        String bsCralIdvTno
    ) {}
}
