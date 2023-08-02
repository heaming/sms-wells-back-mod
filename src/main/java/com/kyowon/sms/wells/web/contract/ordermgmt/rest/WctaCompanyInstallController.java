package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaCompanyInstallService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 회사설치")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaCompanyInstallController {

    private final WctaCompanyInstallService service;

    @ApiOperation(value = "회사설치 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/company-install/paging")
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
    @GetMapping("/company-install/excel-download")
    public List<SearchRes> getCompanyInstallsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getCompanyInstallsForExcelDownload(dto);
    }

    @ApiOperation(value = "회사설치 엑셀 업로드")
    @PostMapping("/company-install/excel-upload")
    public SaveResponse saveCompanyInstallsForExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        List<SaveReq> result = service.saveEntrepreneurForExcelUpload(file);

        return SaveResponse.builder()
            .processCount(service.saveCompanyInstalls(result))
            .build();
    }

    @ApiOperation(value = "회사설치 저장", notes = "")
    @PostMapping("/company-install")
    public SaveResponse saveCompanyInstalls(
        @RequestBody
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveCompanyInstalls(dtos))
            .build();
    }

    @ApiOperation(value = "회사설치 - 주기/용도 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/company-service")
    public List<SearchService> getCompanyServices(
        @NotNull
        String pdCd
    ) {
        return service.getCompanyServices(pdCd);
    }
}
