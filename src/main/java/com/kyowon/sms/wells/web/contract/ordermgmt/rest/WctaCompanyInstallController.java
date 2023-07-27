package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaCompanyInstallService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 회사설치")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1+"/contracts/company-install")
public class WctaCompanyInstallController {

    private final WctaCompanyInstallService service;

    @ApiOperation(value = "회사설치 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getCompanyInstallPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyInstallPages(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getCompanyInstallsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getCompanyInstallsForExcelDownload(dto);
    }
}
