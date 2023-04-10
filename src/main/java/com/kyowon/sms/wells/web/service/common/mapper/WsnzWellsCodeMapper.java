package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.*;

@Mapper
public interface WsnzWellsCodeMapper {

    List<WsnzWellsCodeWorkingEngineersDvo> selectWorkingEngineers(SearchWorkingEngineersReq req);

    List<WsnzWellsCodeWareHouseDvo> selectWareHouses(SearchWareHouseReq req);

    List<WsnzWellsCodeMonthStockDvo> selectMonthStocks(SearchMonthStockReq req);

    List<WsnzWellsCodeServiceCenterOrgsDvo> selectServiceCenterOrgs(SearchServiceCenterOrgsReq req);

    List<WsnzWellsCodeAllEngineersDvo> selectAllEngineers(SearchAllEngineersReq req);

    List<WsnzWellsCodePartMasterDvo> selectPartMaster(SearchPartMasterReq req);

    List<WsnzWellsCodeServiceCenterDvo> selectServiceCenters(SearchServiceCenterReq req);

    List<WsnzWellsCodeDistrictsDvo> selectDistrictsSido(SearchDistrictsReq req);

    List<WsnzWellsCodeDistrictsDvo> selectDistrictsGu(SearchDistrictsReq req);

    List<WsnzWellsCodeDistrictsDvo> selectDistrictsGuAll(SearchDistrictsReq req);

    List<WsnzWellsCodeDistrictsDvo> selectDistricts(SearchDistrictsReq req);

    List<WsnzWellsCodeProductBaseDvo> selectProductBase(SearchProductBaseReq req);

    List<WsnzWellsCodeMonthCstServsDvo> selectMonthCstServs(SearchMcbyCstSvOjIzReq req);

    List<WsnzWellsCodeLgldCtpvLocarasDvo> selectLgldCtpvLocaras(SearchLgldCtpvLocarasReq req);

    int selectWarehouseCloseCheckCounter(SearchWarehouseCLReq dto);
}
