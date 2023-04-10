package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.converter.WsnzWellsCodeConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeDistrictsDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzWellsCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnzWellsCodeService {

    private final WsnzWellsCodeMapper mapper;
    private final WsnzWellsCodeConverter converter;

    public List<SearchWorkingEngineersRes> getWorkingEngineers(
        SearchWorkingEngineersReq req
    ) {
        return converter.mapAllWorkingEngineersDvoToRes(mapper.selectWorkingEngineers(req));
    }

    public List<SearchWareHouseRes> getWareHouses(
        SearchWareHouseReq req
    ) {
        return converter.mapAllWareHouseDvoToRes(mapper.selectWareHouses(req));
    }

    public List<SearchMonthStockRes> getMonthStocks(
        SearchMonthStockReq req
    ) {
        return converter.mapAllDvoToRes(mapper.selectMonthStocks(req));
    }

    public List<SearchServiceCenterOrgsRes> getServiceCenterOrgs(
        SearchServiceCenterOrgsReq req
    ) {
        return converter.mapAllServiceCenterOrgsDvoToRes(mapper.selectServiceCenterOrgs(req));
    }

    public List<SearchAllEngineersRes> getAllEngineers(
        SearchAllEngineersReq req
    ) {
        return converter.mapAllEngineersDvoToRes(mapper.selectAllEngineers(req));
    }

    public List<SearchPartMasterRes> getPartMaster(
        SearchPartMasterReq req
    ) {
        return converter.mapAllPartMasterDvoToRes(mapper.selectPartMaster(req));
    }

    public List<SearchServiceCentersRes> getServiceCenters(
        SearchServiceCenterReq dto
    ) {
        return converter.mapAllServiceCentersDvoToRes(mapper.selectServiceCenters(dto));
    }

    public List<SearchDistrictsRes> getDistricts(
        SearchDistrictsReq dto
    ) {
        List<WsnzWellsCodeDistrictsDvo> result = switch (dto.searchType()) {
            case "sido" -> mapper.selectDistrictsSido(dto);
            case "gu" -> mapper.selectDistrictsGu(dto);
            case "guAll" -> mapper.selectDistrictsGuAll(dto);
            default -> mapper.selectDistricts(dto);
        };
        return converter.mapAllDistrictsDvoToRes(result);
    }

    public List<SearchProductBaseRes> getProductBase(
        SearchProductBaseReq dto
    ) {
        return converter.mapAllProductBaseDvoToRes(mapper.selectProductBase(dto));
    }

    public List<SearchMcbyCstSvOjIzRes> getMonthCstServs(
        SearchMcbyCstSvOjIzReq dto
    ) {
        return converter.mapAllMonthCstServsDvoToRes(mapper.selectMonthCstServs(dto));
    }

    public List<SearchLgldCtpvLocarasRes> getLgldCtpvLocaras(
        SearchLgldCtpvLocarasReq req
    ) {
        return converter.mapAllLgldCtpvLocarasDvoToRes(mapper.selectLgldCtpvLocaras(req));
    }

    public int getWarehouseCloseCheckCounter(SearchWarehouseCLReq dto) {
        return this.mapper.selectWarehouseCloseCheckCounter(dto);
    }
}
