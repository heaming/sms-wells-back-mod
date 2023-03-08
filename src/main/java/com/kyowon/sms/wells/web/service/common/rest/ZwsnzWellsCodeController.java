package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.common.web.zcommon.constants.CommonConst;
import com.kyowon.sms.wells.web.service.common.dto.ZwsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.service.ZwsnzWellsCodeService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonConst.REST_URL_WELLS_COMMON + "/sms-wells-codes")
@Api(tags = "[WSNZ] 코드조회(wells) RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ZwsnzWellsCodeController {

    private final ZwsnzWellsCodeService service;

    @ApiOperation(value = "서비스센터 엔지니어 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "hgrDeptCd", value = "상위부서코드", paramType = "query", example = "00810"),
    })
    @GetMapping("/all-engineers")
    public List<SearchAllEngineersRes> getAllEngineers(
        SearchAllEngineersReq req
    ) {
        return service.getAllEngineers(req);
    }

    @ApiOperation(value = "광역시도, 시군구 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchType", value = "조회구분", paramType = "query", example = "sido"),
        @ApiImplicitParam(name = "fr2pLgldCd", value = "앞2자리법정동코드", paramType = "query", example = "11"),
    })
    @GetMapping("/districts")
    public List<SearchDistrictsRes> getDistricts(
        SearchDistrictsReq req
    ) {
        return service.getDistricts(req);
    }

    @ApiOperation(value = "법정시도명에 해당 하는 지역코드 조회")
    @GetMapping("/lgld-ctpv-locaras")
    public List<SearchLgldCtpvLocarasRes> getLgldCtpvLocaras(
        SearchLgldCtpvLocarasReq req
    ) {
        return service.getLgldCtpvLocaras(req);
    }

    @ApiOperation(value = "월별고객서비스대상내역")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년월", paramType = "query", example = "2022"),
        @ApiImplicitParam(name = "pdGdCd", value = "상품등급코드", paramType = "query", example = "A"),
    })
    @GetMapping("/month-customer-services")
    public List<SearchMcbyCstSvOjIzRes> getMonthCstServs(
        SearchMcbyCstSvOjIzReq req
    ) {
        return service.getMonthCstServs(req);
    }

    @ApiOperation(value = "월별 창고내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wareMngtPrtnrNo", value = "창고관리자", paramType = "query"),
        @ApiImplicitParam(name = "apyYm", value = "적용일자", paramType = "query"),
    })
    @GetMapping("/month-stocks")
    public List<SearchMonthStockRes> getMonthStocks(
        SearchMonthStockReq req
    ) {
        return service.getMonthStocks(req);
    }

    @ApiOperation(value = "상품 기본 조회")
    @GetMapping("/part-master")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdTpCd", value = "상품유형코드", paramType = "query", example = "P"),
        @ApiImplicitParam(name = "sellIndate", value = "상품판매기간 조회 기준", paramType = "query"),
        @ApiImplicitParam(name = "partCd", value = "자재번호", paramType = "query"),
        @ApiImplicitParam(name = "itemKnd", value = "품목종류", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "itemGr", value = "상품그룹", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query"),
        @ApiImplicitParam(name = "pdClsfId", value = "상품분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdLclsfId", value = "상품소분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdDclsfId", value = "상품세분류ID", paramType = "query"),
    })
    public List<SearchPartMasterRes> getPartMaster(
        SearchPartMasterReq req
    ) {
        return service.getPartMaster(req);
    }

    @ApiOperation(value = "상품기본조회")
    @GetMapping("/products")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "newAdrZip", paramType = "query", example = "37714"),
        @ApiImplicitParam(name = "emdSn", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "ctpvNm", paramType = "query", example = "경상북도"),
        @ApiImplicitParam(name = "ctctyNm", paramType = "query", example = "포항시 북구"),
        @ApiImplicitParam(name = "lawcEmdNm", paramType = "query", example = "항구동"),
        @ApiImplicitParam(name = "amtdNm", paramType = "query", example = "중앙동"),
        @ApiImplicitParam(name = "pdlvNo", paramType = "query")
    })
    public List<SearchProductBaseRes> getProductBase(
        SearchProductBaseReq req
    ) {
        return service.getProductBase(req);
    }

    @ApiOperation(value = "서비스센터 조직 조회")
    @GetMapping("/service-center-orgs")
    public List<SearchServiceCenterOrgsRes> getServiceCenterOrgs(
        SearchServiceCenterOrgsReq req
    ) {
        return service.getServiceCenterOrgs(req);
    }

    @ApiOperation(value = "서비스센터 조회")
    @GetMapping("/service-centers")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", paramType = "query", example = "OG00002"),
        @ApiImplicitParam(name = "hgrOgId", paramType = "query", example = "HOG00002"),
        @ApiImplicitParam(name = "ogCd", paramType = "query", example = "OGC00002"),
        @ApiImplicitParam(name = "ogNm", paramType = "query", example = "도봉센터"),
    })
    public List<SearchServiceCentersRes> getServiceCenters(
        SearchServiceCenterReq req
    ) {
        return service.getServiceCenters(req);
    }

    @ApiOperation(value = "창고조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startYm", value = "적용년월시작", paramType = "query"),
        @ApiImplicitParam(name = "endYm", value = "적용년월종료", paramType = "query"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query"),
        @ApiImplicitParam(name = "wareIchrNo", value = "창고담당번호", paramType = "query"),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query"),
    })
    @GetMapping("/ware-houses")
    public List<SearchWareHouseRes> getWareHouses(
        SearchWareHouseReq req
    ) {
        return service.getWareHouses(req);
    }

    @ApiOperation(value = "창고조직마감내역체크조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyYm", value = "적용년월", paramType = "query"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query"),
    })
    @GetMapping("/warehouse-close-check")
    public int getWarehouseCloseCheckCounter(SearchWarehouseCLReq dto) {
        return this.service.getWarehouseCloseCheckCounter(dto);
    }

    @ApiOperation(value = "서비스센터 근무 엔지니어(Wells서비스관리팀 또는 Wells고객서비스부문) 조회")
    @GetMapping("/working-engineers")
    public List<SearchWorkingEngineersRes> getWorkingEngineers(
        SearchWorkingEngineersReq req
    ) {
        return service.getWorkingEngineers(req);
    }
}
