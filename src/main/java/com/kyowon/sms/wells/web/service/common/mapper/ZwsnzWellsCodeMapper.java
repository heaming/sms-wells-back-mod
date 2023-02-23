package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.ZwsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZwsnzWellsCodeMapper {

    List<ZwsnzWellsCodeWorkingEngineersDvo> selectWorkingEngineers(SearchWorkingEngineersReq req);

    List<ZwsnzWellsCodeWareHouseDvo> selectWareHouses(SearchWareHouseReq req);

    List<ZwsnzWellsCodeMonthStockDvo> selectMonthStocks(SearchMonthStockReq req);

    List<ZwsnzWellsCodeServiceCenterOrgsDvo> selectServiceCenterOrgs(SearchServiceCenterOrgsReq req);

    List<ZwsnzWellsCodeAllEngineersDvo> selectAllEngineers(SearchAllEngineersReq req);
}
