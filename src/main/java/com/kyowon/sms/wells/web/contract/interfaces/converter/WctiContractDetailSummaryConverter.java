package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailSummaryDvo;

@Mapper(componentModel = "spring")
public interface WctiContractDetailSummaryConverter {

    @Mapping(source = "cntrtNm", target = "CNTRT_NM")
    @Mapping(source = "cntrtCralLocaraTno", target = "CNTRT_CRAL_LOCARA_TNO")
    @Mapping(source = "cntrtMexno", target = "CNTRT_MEXNO")
    @Mapping(source = "cntrtCralIdvTno", target = "CNTRT_CRAL_IDV_TNO")
    @Mapping(source = "istCstNm", target = "IST_CST_NM")
    @Mapping(source = "istCralLocaraTno", target = "IST_CRAL_LOCARA_TNO")
    @Mapping(source = "istMexno", target = "IST_MEXNO")
    @Mapping(source = "istCralIdvTno", target = "IST_CRAL_IDV_TNO")
    @Mapping(source = "istLocaraTno", target = "IST_LOCARA_TNO")
    @Mapping(source = "istExno", target = "IST_EXNO")
    @Mapping(source = "istIdvTno", target = "IST_IDV_TNO")
    @Mapping(source = "basePdCd", target = "BASE_PD_CD")
    @Mapping(source = "basePdNm", target = "BASE_PD_NM")
    WctiContractDetailSummaryDto.FindRes mapWctiContractDetailSummaryDvoToFindRes(WctiContractDetailSummaryDvo dvo);
}



