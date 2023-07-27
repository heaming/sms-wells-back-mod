package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailDvo;

@Mapper(componentModel = "spring")
public interface WctiContractDetailConverter {
    List<WctiContractDetailDto.SearchRes> mapWctiContractDetailDvoToSearchRes(List<WctiContractDetailDvo> dvo);
}
