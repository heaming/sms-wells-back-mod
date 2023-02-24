package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.common.web.common.dto.ZwsnzComCodeDto;
import com.kyowon.sms.wells.web.service.common.converter.ZwsnzWellsCodeConverter;
import com.kyowon.sms.wells.web.service.common.dto.ZwsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.mapper.ZwsnzWellsCodeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZwsnzWellsCodeService {

    private final ZwsnzWellsCodeMapper mapper;
    private final ZwsnzWellsCodeConverter converter;

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
}
