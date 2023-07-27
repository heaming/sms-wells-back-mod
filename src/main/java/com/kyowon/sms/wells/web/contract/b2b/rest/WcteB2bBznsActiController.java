package com.kyowon.sms.wells.web.contract.b2b.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchReq;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchRes;
import com.kyowon.sms.wells.web.contract.b2b.service.WcteB2bBznsActiService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "B2B BO관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/business-to-business")
public class WcteB2bBznsActiController {
    private final WcteB2bBznsActiService service;

    @ApiOperation(value = "B2B BO관리", notes = "법인고객 관리하는 화면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "srchDateGbn", value = "조회기간구분", paramType = "query"),
        @ApiImplicitParam(name = "strtdt", value = "조회기간시작", paramType = "query"),
        @ApiImplicitParam(name = "enddt", value = "조회기간끝", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자번호", paramType = "query"),
        @ApiImplicitParam(name = "leadCstNm", value = "업체명", paramType = "query"),
        @ApiImplicitParam(name = "prjNm", value = "프로젝트ID", paramType = "query"),
    })
    @GetMapping("/business-opportunities")
    public List<SearchRes> getRentalMutuAlncCheck(SearchReq dto) {
        return service.getRentalMutuAlncCheck(dto);
    }

    @ApiOperation(value = "Key Man 조회", notes = "사업자번호를 통해 Key Man의 정보를 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzrno", value = "사업자번호", paramType = "query"),
        @ApiImplicitParam(name = "leadCstNm", value = "업체명", paramType = "query"),
    })
    @GetMapping("/business-keyman")
    public WcteB2bBznsActiDto.SearchKeyManRes getKeyMan(WcteB2bBznsActiDto.SearchKeyManReq dto) {
        return service.getKeyMan(dto);
    }

    @ApiOperation(value = "B2B BO관리 저장", notes = "추가 / 수정한 B2B BO관리를 저장한다.")
    @PostMapping("/business-opportunities")
    public SaveResponse saveB2bBoMngtSaves(
        @RequestBody
        @Valid
        @NotEmpty
        List<WcteB2bBznsActiDto.SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveB2bBoMngtSaves(dtos)).build();
    }

    @ApiOperation(value = "B2B BO관리 삭제", notes = "B2B BO관리 삭제")
    @DeleteMapping("/business-opportunities")
    public SaveResponse removeBusinessToBusinessDls(
        @RequestBody
        @Valid
        @NotEmpty
        List<WcteB2bBznsActiDto.SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeBusinessToBusinessDls(dtos)).build();
    }

    @ApiOperation(value = "B2B BO관리 상세내역(팝업) 조회", notes = "B2B BO관리 상세내역(팝업) 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "opptId", value = "기회ID", paramType = "query"),
    })
    @GetMapping("/business-opportunities/details")
    public List<WcteB2bBznsActiDto.SearchDetailRes> getB2bBoMngtDtlIzs(
        @RequestParam
        @Valid
        @NotEmpty
        String opptId
    ) {
        return service.getB2bBoMngtDtlIzs(opptId);
    }

    @ApiOperation(value = "B2B BO관리 상세내역(팝업) 저장", notes = "추가 / 수정한 B2B BO관리를 저장한다.")
    @PostMapping("/business-opportunities/details")
    public SaveResponse saveB2bBoMngtDtlSaves(
        @RequestBody
        @Valid
        @NotEmpty
        List<WcteB2bBznsActiDto.SaveDetailReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveB2bBoMngtDtlSaves(dtos)).build();
    }

    @ApiOperation(value = "B2B BO관리 상세내역(팝업) 삭제", notes = "B2B BO관리 삭제")
    @DeleteMapping("/business-opportunities/details")
    public SaveResponse removeB2bBoMngtDtlIzDls(
        @RequestBody
        @Valid
        @NotEmpty
        List<WcteB2bBznsActiDto.SaveDetailReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeB2bBoMngtDtlIzDls(dtos)).build();
    }

    @ApiOperation(value = "B2B BO관리 상세내역(팝업) 삭제", notes = "B2B BO관리 삭제")
    @DeleteMapping("/business-opportunities/details/excel-download")
    public SaveResponse removeB2bBoMngtDtlIzDlsExcel(
        @RequestBody
        @Valid
        @NotEmpty
        List<WcteB2bBznsActiDto.SaveDetailReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeB2bBoMngtDtlIzDls(dtos)).build();
    }
}
