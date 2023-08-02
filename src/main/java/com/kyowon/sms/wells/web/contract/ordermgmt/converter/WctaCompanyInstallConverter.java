package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCompanyInstallDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractAdrpcBasDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctaCompanyInstallConverter {
    WctaCompanyInstallDvo mapSearchReqToDvo(SearchReq dto);

    List<SearchRes> mapAllDvoToSearchRes(List<WctaCompanyInstallDvo> dvo);

    PagingResult<SearchRes> mapAllDvoToSearchRes(PagingResult<WctaCompanyInstallDvo> dvo);

    SaveReq mapCompanyInstallDvoToSaveReq(WctaCompanyInstallDvo dvo);

    WctaCompanyInstallDvo mapSaveReqToCompanyInstallDvo(SaveReq dvo);

    @Mapping(target = "cntrtZip", source = "zip")
    @Mapping(target = "cntrtBasAdr", source = "basAdr")
    @Mapping(target = "cntrtDtlAdr", source = "dtlAdr")
    @Mapping(target = "istZip", source = "installZip")
    @Mapping(target = "istBasAdr", source = "installBasAdr")
    @Mapping(target = "istDtlAdr", source = "installDtlAdr")
    @Mapping(target = "cntrtCralLocaraTno", source = "cralLocaraTno")
    @Mapping(target = "cntrtMexnoEncr", source = "mexnoEncr")
    @Mapping(target = "cntrtCralIdvTno", source = "cralIdvTno")
    @Mapping(target = "istCralLocaraTno", source = "installCralLocaraTno")
    @Mapping(target = "istMexnoEncr", source = "installMexnoEncr")
    @Mapping(target = "istCralIdvTno", source = "installCralIdvTno")
    @Mapping(target = "sellOgTpCd", source = "ogCd")
    @Mapping(target = "istllKnm", source = "rcgvpKnm")
    @Mapping(target = "cntrRcpDt", source = "cntrRcpDt")
    @Mapping(target = "cntrRcpTm", source = "cntrRcpTm")
    @Mapping(target = "cntrCnfmDt", source = "cntrRcpDt")
    @Mapping(target = "cntrCnfmTm", source = "cntrRcpTm")
    WctiContractCreateDvo mapCompanyInstallDvoToContractDvo(WctaCompanyInstallDvo dvo);

    @Mapping(target = "copnDvCd", source = "istCopnDvCd")
    @Mapping(target = "cralLocaraTno", source = "installCralLocaraTno")
    @Mapping(target = "mexnoEncr", source = "installMexnoEncr")
    @Mapping(target = "cralIdvTno", source = "installCralIdvTno")
    @Mapping(target = "locaraTno", source = "installLocaraTno")
    @Mapping(target = "exnoEncr", source = "installExnoEncr")
    @Mapping(target = "idvTno", source = "installIdvTno")
    @Mapping(target = "zip", source = "installZip")
    @Mapping(target = "adr", source = "installBasAdr")
    @Mapping(target = "adrDtl", source = "installDtlAdr")
    WctaContractAdrpcBasDvo mapCompanyInstallDvoToAdrpcBasDvo(WctaCompanyInstallDvo dvo);

}
