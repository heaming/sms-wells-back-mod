package com.kyowon.sms.wells.web.contract.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzProductDto.SearchRes;

@Mapper
public interface WctzProductMapper {

    List<SearchRes> selectHighClasses();

    List<SearchRes> selectMiddleClasses();
}
