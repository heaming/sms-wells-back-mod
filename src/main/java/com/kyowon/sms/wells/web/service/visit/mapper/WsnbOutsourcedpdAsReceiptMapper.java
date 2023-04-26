package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbOutsourcedpdAsReceiptMapper {

    PagingResult<SearchRes> selectOutsourcedpdAsReceipts(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectOutsourcedpdAsReceipts(SearchReq dto);

}
