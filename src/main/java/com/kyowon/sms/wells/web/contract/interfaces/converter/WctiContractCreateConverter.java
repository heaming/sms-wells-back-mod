package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateKmembersReq;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;

@Mapper(componentModel = "spring")
public interface WctiContractCreateConverter {

    @Mapping(source = "cstKnm", target = "cntrCstKnm")
    @Mapping(source = "cstEmadr", target = "cntrtEmadr")
    @Mapping(source = "istPlcLocaraTno", target = "istLocaraTno")
    @Mapping(source = "istPlcExnoEncr", target = "istExnoEncr")
    @Mapping(source = "istPlcIdvTno", target = "istIdvTno")
    @Mapping(source = "istCstCralLocaraTno", target = "istCralLocaraTno")
    @Mapping(source = "istCstMexnoEncr", target = "istMexnoEncr")
    @Mapping(source = "istCstCralIdvTno", target = "istCralIdvTno")
    @Mapping(source = "pdCd01", target = "basePdCd")
    @Mapping(source = "pdQty01", target = "pdQty")
    @Mapping(source = "amt01", target = "fnlAmt")
    @Mapping(source = "sellTam", target = "cntrTam")
    @Mapping(source = "cntrDt", target = "cntrCnfmDtm")
    @Mapping(source = "ag1", target = "pifThpOfrAgYn")
    @Mapping(source = "ag2", target = "thpAgYn")
    @Mapping(source = "ag3", target = "mktgPurpAgYn")
    @Mapping(source = "ag4", target = "pifCinfThpOfrAgYn")
    @Mapping(source = "ag5", target = "pifBizFstrAgYn")
    @Mapping(source = "dpDvCd1", target = "dpDvCd")
    @Mapping(source = "subscAmt1", target = "stlmAmt")
    @Mapping(source = "cdno1", target = "crcdNo")
    @Mapping(source = "cino", target = "cntrtBryyMmdd")
    @Mapping(source = "crdcdIstmMcn1", target = "istmMcn")
    @Mapping(source = "cdonrNm1", target = "crcdOwrKnm")
    @Mapping(source = "evCd", target = "sellEvCd")
    @Mapping(source = "dcde", target = "sellPrtnrNo")
    @Mapping(source = "achldrNm", target = "owrKnm")
    @Mapping(source = "mngtPrd", target = "svPrd")
    @Mapping(source = "duedt", target = "sppDuedt")
    @Mapping(source = "frisuPrd", target = "frisuBfsvcPtrmN")
    @Mapping(source = "cstAkArtc", target = "istAkArtcMoCn")
    @Mapping(source = "dscDv", target = "sellDscDvCd")
    @Mapping(source = "dscTp", target = "sellDscTpCd")
    @Mapping(source = "uswy", target = "svPdTpCd")
    WctiContractCreateDvo mapCreateKmembersReqToWctiContractCreateDvo(CreateKmembersReq dto);
}
