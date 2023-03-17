package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsTransferDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncAsTransferMapper {

    PagingResult<SearchRes> selectAsTransferPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectAsTransferPages(SearchReq dto);

    int insertAssignResultTransferIz(WsncAsTransferDvo dvo);

    String selectCntrNoByPdCd(String pdCd);

    List<SearchRes> selectAssignByCntrNo(String farmCntrNo);

    int updateInstallationAssignIz(WsncAsTransferDvo dvo);

    int updateSeedingShippingPlanIz(WsncAsTransferDvo dvo);
}
