package com.kyowon.sms.wells.web.contract.salesstatus.mapper;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WctePartnerAggregationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WctePartnerAggregationDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctePartnerAggregationMapper {
    String selectBmno();

    List<SearchRes> selectPartnerAggregations(
        SearchReq dto
    );

    List<SearchRes> selectPartnerAggregationsWithBmno(
        String bmno
    );
}
