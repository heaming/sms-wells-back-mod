package com.kyowon.sms.wells.web.contract.changeorder.rest;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPrepaymentChCstPsDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPrepaymentChCstPsDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.service.WctbPrepaymentChCstPsService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] wells 선납변경 고객현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbPrepaymentChCstPsController {

    private final WctbPrepaymentChCstPsService service;

    @ApiOperation(value = "선납변경 고객현황 조회", notes = "관리년월, 조직코드로 선납변경을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류ID", paramType = "query"),

    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getPrepaymentChCstPsPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getPrepaymentChCstPsPages(dto, pageInfo);
    }

    @ApiOperation(value = " 엑셀 다운로드", notes = "관리년월, 조직코드로 조회한 선납변경을 엑셀로 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류ID", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getPrepaymentChCstPssForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getPrepaymentChCstPssForExcelDownload(dto);
    }
}
