package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMachineChangeDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiMachineChangeDvo;

@Mapper
public interface WctiMachineChangeMapper {
    List<WctiMachineChangeDvo> selectMachineChanges(SearchReq dto);
}
