package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SearchRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractExceptionService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 예외 처리 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contract-exceptions")
public class WctaContractExceptionController {

    private final WctaContractExceptionService service;

    @ApiOperation(value = "예외 처리 관리 페이징 조회", notes = "계약예외대상기본 테이블에서 데이터를 조회하고 결과를 반환한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "적용기간 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "적용기간 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "판매자사번", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "exProcsCd", value = "판매구분(예외처리코드)", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getContractExceptionPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getContractExceptionPages(dto, pageInfo);
    }

    @ApiOperation(value = "예외 처리 관리 엑셀 다운로드", notes = "계약예외대상기본 테이블에서 데이터를 조회하고 결과를 엑셀 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "vlStrtDtm", value = "적용기간 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "vlEndDtm", value = "적용기간 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "판매자사번", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "exProcsCd", value = "판매구분(예외처리코드)", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getContractExceptionsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getContractExceptionsForExcelDownload(dto);
    }

    @ApiOperation(value = "예외 처리 관리 저장", notes = "주어진 입력파라미터에 따라 아래와 같이 DB에 저장한다.\n예외처리구분 코드에 따라 아래와 같이 저장한다.\n" +
        "\n" +
        "예외처리구분 = 'W01' 인 경우,\n" +
        " 계약예외대상기본 테이블 내 예외처리대상식별유형코드 = 파트너번호(5), 예외처리대상식별값 = 파트너번호, 조직유형코드 = 조직유형코드 저장\n" +
        " 계약예외대상상세 테이블 내 예외처리유형코드 = 'C1' , 예외처리문자값 = 고객번호 저장\n" +
        "예외처리구분 = 'W04' 인 경우,\n" +
        " 계약예외대상기본 테이블 내 예외처리대상식별유형코드 = 계약번호(2), 예외처리대상식별값 = 계약번호 저장\n" +
        " 계약예외대상상세 테이블 내 예외처리유형코드 = 'C5' , 예외처리문자값 = 고객번호 저장\n" +
        "예외처리구분 = 'W02' 인 경우,\n" +
        " 계약예외대상기본 테이블 내 예외처리대상식별유형코드 = 고객번호(1), 예외처리대상식별값 = 고객번호 저장\n" +
        " 계약예외대상상세 테이블 내 예외처리유형코드 = 'C2' , 예외처리문자값 = 파트너번호 저장\n" +
        "                       예외처리유형코드 = 'C3' , 예외처리문자값 = 조직유형코드 저장\n" +
        "                       예외처리유형코드 = 'C4' , 예외처리문자값 = 계약번호 저장")
    @PostMapping
    public SaveResponse saveContractExceptions(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveContractExceptions(dtos)).build();
    }

    @ApiOperation(value = "예외 처리 관리 삭제", notes = "선택한 row 의 예외처리ID 를 기준으로 계약예외대상기본 테이블에서 데이터를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeContractExceptions(
        @RequestBody
        @Valid
        @NotEmpty
        List<String> keys
    ) {
        return SaveResponse.builder().processCount(service.removeContractExceptions(keys)).build();
    }
}
