package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchRes;

@Mapper
public interface WmsncOutsourcedpdAsReceiptMapper {

    List<SearchRes> selectOutsourcedpdAsReceipts(String searchParam);

}
