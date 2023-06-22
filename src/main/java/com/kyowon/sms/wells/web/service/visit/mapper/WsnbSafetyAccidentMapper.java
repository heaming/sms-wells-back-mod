package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbSafetyAccidentDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

;

@Mapper
public interface WsnbSafetyAccidentMapper {

    PagingResult<WsnbSafetyAccidentDvo> selectSafetyAccidents(SearchReq dto, PageInfo pageInfo);

    WsnbSafetyAccidentDvo selectSafetyAccident(String acdnRcpId);

    int updateSafetyAccident(WsnbSafetyAccidentDvo dvo);
}
