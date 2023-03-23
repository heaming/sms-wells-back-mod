package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusDto.SearchRes;

@Mapper
public interface WsnbPointmallStatusMapper {

    List<SearchRes> selectPointmallStatuses(SearchReq dto);

}
