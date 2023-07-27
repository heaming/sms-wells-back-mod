package com.kyowon.sms.wells.web.contract.salesstatus.mapper;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRcpIstDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRcpIstDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WcteRcpIstMapper {

    PagingResult<SearchRes> selectRcpIstPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectRcpIstPages(SearchReq dto);
}
