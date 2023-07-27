package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractInstallHistoryDvo;

@Mapper(componentModel = "spring")
public interface WctiContractInstallHistoryConverter {
    List<WctiContractInstallHistoryDto.SearchRes> mapWctiContractInstallHistoryDvoToSearchRes(List<WctiContractInstallHistoryDvo> dvo);
}
