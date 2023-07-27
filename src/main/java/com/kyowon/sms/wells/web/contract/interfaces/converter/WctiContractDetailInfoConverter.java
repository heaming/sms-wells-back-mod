package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailInfoDvo;

@Mapper(componentModel = "spring")
public interface WctiContractDetailInfoConverter {
   WctiContractDetailInfoDto.FindRes mapWctiContractDetailInfoDvoToFindRes(WctiContractDetailInfoDvo dvo);

}
