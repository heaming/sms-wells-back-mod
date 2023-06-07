package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.RemoveReceiptBzReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.RemoveReceiptIzReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchReceiptBzReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchReceiptIzReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncOutsourcedpdAsReceiptDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncOutsourcedpdAsReceiptMapper {

    PagingResult<WsncOutsourcedpdAsReceiptDvo> selectOutsourcedpdAsReceiptIzs(
        SearchReceiptIzReq dto, PageInfo pageInfo
    );

    List<WsncOutsourcedpdAsReceiptDvo> selectOutsourcedpdAsReceiptIzs(SearchReceiptIzReq dto);

    PagingResult<WsncOutsourcedpdAsReceiptDvo> selectMobileOutsourcedpdAsReceiptIzs(
        SearchReceiptIzReq dto, PageInfo pageInfo
    );

    int deleteOutsourcedpdAsReceiptIz(RemoveReceiptIzReq dto);

    int insertOutsourcedpdAsReceiptIz(WsncOutsourcedpdAsReceiptDvo dvo);

    int updateOutsourcedpdAsReceiptIz(WsncOutsourcedpdAsReceiptDvo dvo);

    PagingResult<WsncOutsourcedpdAsReceiptDvo> selectOutsourcedpdAsReceiptBzs(
        SearchReceiptBzReq dto, PageInfo pageInfo
    );

    List<WsncOutsourcedpdAsReceiptDvo> selectOutsourcedpdAsReceiptBzs(SearchReceiptBzReq dto);

    int deleteOutsourcedpdAsReceiptBz(RemoveReceiptBzReq dto);

    int insertOutsourcedpdAsReceiptBz(WsncOutsourcedpdAsReceiptDvo dvo);

    int updateOutsourcedpdAsReceiptBz(WsncOutsourcedpdAsReceiptDvo dvo);
}
