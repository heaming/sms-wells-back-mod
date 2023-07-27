package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaMachineChangeInfoInqrDto.SearchRes;

@Mapper
public interface WctaMachineChangeInfoInqrMapper {
    List<SearchRes> selectMachineChangeInfoInqr(String cntrNo, String cntrSn);
}
