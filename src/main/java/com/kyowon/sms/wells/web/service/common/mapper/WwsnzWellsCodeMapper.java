package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WwsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwsnzWellsCodeMapper {

    List<WwsnzWellsCodeWorkingEngineersDvo> selectWorkingEngineers(SearchWorkingEngineersReq req);

    List<WwsnzWellsCodeWareHouseDvo> selectWareHouses(SearchWareHouseReq req);

    List<WwsnzWellsCodeMonthStockDvo> selectMonthStocks(SearchMonthStockReq req);

    List<WwsnzWellsCodeServiceCenterOrgsDvo> selectServiceCenterOrgs(SearchServiceCenterOrgsReq req);

    List<WwsnzWellsCodeAllEngineersDvo> selectAllEngineers(SearchAllEngineersReq req);

    List<WwsnzWellsCodePartMasterDvo> selectPartMaster(SearchPartMasterReq req);

    List<WwsnzWellsCodeServiceCenterDvo> selectServiceCenters(SearchServiceCenterReq req);

    List<WwsnzWellsCodeDistrictsDvo> selectDistrictsSido(SearchDistrictsReq req);

    List<WwsnzWellsCodeDistrictsDvo> selectDistrictsGu(SearchDistrictsReq req);

    List<WwsnzWellsCodeDistrictsDvo> selectDistrictsGuAll(SearchDistrictsReq req);

    List<WwsnzWellsCodeDistrictsDvo> selectDistricts(SearchDistrictsReq req);

    List<WwsnzWellsCodeProductBaseDvo> selectProductBase(SearchProductBaseReq req);

    List<WwsnzWellsCodeMonthCstServsDvo> selectMonthCstServs(SearchMcbyCstSvOjIzReq req);

    List<WwsnzWellsCodeLgldCtpvLocarasDvo> selectLgldCtpvLocaras(SearchLgldCtpvLocarasReq req);

    int selectWarehouseCloseCheckCounter(SearchWarehouseCLReq dto);
}
