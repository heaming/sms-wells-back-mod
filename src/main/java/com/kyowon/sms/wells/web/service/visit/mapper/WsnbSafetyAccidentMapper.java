package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.FindInitReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbSafetyAccidentDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbSafetyAccidentMapper {

    PagingResult<WsnbSafetyAccidentDvo> selectSafetyAccidents(SearchReq dto, PageInfo pageInfo);

    List<WsnbSafetyAccidentDvo> selectSafetyAccidents(SearchReq dto);

    WsnbSafetyAccidentDvo selectSafetyAccident(String acdnRcpId);

    int updateSafetyAccidentResult(WsnbSafetyAccidentDvo dvo);

    int updateSafetyAccidentBiztalk(WsnbSafetyAccidentDvo dvo);

    int updateSafetyAccidentSign(WsnbSafetyAccidentDvo dvo);

    WsnbSafetyAccidentDvo selectSafetyAccidentInit(FindInitReq dto);

    int mergeSafetyAccident(WsnbSafetyAccidentDvo dvo);

    int updateWorkResult(WsnbSafetyAccidentDvo dvo);

}
