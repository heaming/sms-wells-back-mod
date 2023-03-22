package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchRes;

@Mapper
public interface WsnbPointmallStatusInfMapper {

    List<SearchRes> selectPointmallStatusInfs(SearchReq dto);

}
