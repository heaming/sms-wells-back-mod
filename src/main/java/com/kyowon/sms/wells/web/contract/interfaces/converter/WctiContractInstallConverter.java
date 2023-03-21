package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractInstallDvo;

@Mapper(componentModel = "spring")
public interface WctiContractInstallConverter {
    @Mapping(source = "CNTR_NO", target = "cntrNo")
    @Mapping(source = "CNTR_SN", target = "cntrSn")
    @Mapping(source = "ADR_ID", target = "adrId")
    @Mapping(source = "CRAL_LOCARA_TNO", target = "cralLocaraTno")
    @Mapping(source = "MEXNO", target = "mexno")
    @Mapping(source = "CRAL_IDV_TNO", target = "cralIdvTno")
    @Mapping(source = "LOCARA_TNO", target = "locaraTno")
    @Mapping(source = "EXNO", target = "exno")
    @Mapping(source = "IDV_TNO", target = "idvTno")
    WctiContractInstallDvo mapSaveReqToWctiContractInstallDvo(WctiContractInstallDto.SaveReq dto);
}
