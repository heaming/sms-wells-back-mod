package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMachineChangeDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiMachineChangeDvo;

@Mapper(componentModel = "spring")
public interface WctiMachineChangeConverter {
    List<WctiMachineChangeDto.SearchRes> mapAllWctiMachineChangeDvoToSearchRes(List<WctiMachineChangeDvo> dvo);
}
