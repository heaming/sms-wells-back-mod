package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsTransferDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncAsTransferMapper {

    PagingResult<WsncAsTransferDvo> selectAsTransferPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsncAsTransferDvo> selectAsTransferPages(SearchReq dto);

    int insertAssignResultTransferIz(WsncAsTransferDvo dvo);

    int selectFarmCodeCount(WsncAsTransferDvo dvo);

    int selectAssignByPk(WsncAsTransferDvo dvo);

    List<WsncAsTransferDvo> selectFarmShippings(WsncAsTransferDvo dvo);

    int updateInstallationAssignIz(WsncAsTransferDvo dvo);

    int updateSeedingShippingPlanIz(WsncAsTransferDvo dvo);

}
