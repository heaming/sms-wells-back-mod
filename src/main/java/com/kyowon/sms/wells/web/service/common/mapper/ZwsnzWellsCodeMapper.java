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

    List<ZwsnzWellsCodePartMasterDvo> selectPartMaster(SearchPartMasterReq req);

    List<ZwsnzWellsCodeServiceCenterDvo> selectServiceCenters(SearchServiceCenterReq req);

    List<ZwsnzWellsCodeDistrictsDvo> selectDistrictsSido(SearchDistrictsReq req);

    List<ZwsnzWellsCodeDistrictsDvo> selectDistrictsGu(SearchDistrictsReq req);

    List<ZwsnzWellsCodeDistrictsDvo> selectDistrictsGuAll(SearchDistrictsReq req);

    List<ZwsnzWellsCodeDistrictsDvo> selectDistricts(SearchDistrictsReq req);

    List<ZwsnzWellsCodeProductBaseDvo> selectProductBase(SearchProductBaseReq req);

    List<ZwsnzWellsCodeMonthCstServsDvo> selectMonthCstServs(SearchMcbyCstSvOjIzReq req);

    List<ZwsnzWellsCodeLgldCtpvLocarasDvo> selectLgldCtpvLocaras(SearchLgldCtpvLocarasReq req);

    int selectWarehouseCloseCheckCounter(SearchWarehouseCLReq dto);
}
