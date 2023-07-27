package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaSpectxBlkPrntService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "wells 거래명세서 일괄출력")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaSpectxBlkPrntController {
    private final WctaSpectxBlkPrntService service;

    @ApiOperation(value = "거래명세서 일괄 출력 조회", notes = "거래명세서 일괄 출력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rgstStartDt", value = "등록시작일", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "rgstEndDt", value = "등록마지막일", paramType = "query", example = "20220131"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", example = "W20225871582"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "grpStartNo", value = "그룹번호시작", paramType = "query", example = "0"),
        @ApiImplicitParam(name = "grpEndNo", value = "그룹번호끝", paramType = "query", example = "99999"),
    })
    @GetMapping("/trade-specification-sheets")
    public List<SearchRes> getSpectxBlkPrnts(SearchReq dto) {
        return service.getSpectxBlkPrnts(dto);
    }

    @ApiOperation(value = "거래명세서 일괄 출력 조회", notes = "거래명세서 일괄 출력 조회")
    @GetMapping("/trade-specification-sheets/excel-download")
    public List<SearchRes> getSpectxBlkPrntsExcelDwonload(SearchReq dto) {
        return service.getSpectxBlkPrntsExcelDwonload(dto);
    }

    @ApiOperation(value = "거래명세서 일괄 출력 조회", notes = "거래명세서 일괄 출력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", example = "W20225871582"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", example = "1"),
    })
    @GetMapping("/trade-specification-sheets/{cntrSn}")
    public SearchCntrRes getTradeSpcshCst(String cntrNo, String cntrSn) {
        return service.getTradeSpcshCst(cntrNo, cntrSn);
    }

    @ApiOperation(value = "거래명세서 생성", notes = "거래명세서 생성")
    @PostMapping("/trade-specification-sheets/addRows")
    public String saveSpectxBlkPrntsGrpNo() {
        return service.saveSpectxBlkPrntsGrpNo();
    }

    @ApiOperation(value = "거래명세서 일괄 출력 저장", notes = "거래명세서 일괄 출력 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "spectxGrpNo", value = "그룹번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "계약구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "spectxPrntY", value = "발행년도", paramType = "query"),
        @ApiImplicitParam(name = "spectxPblDDvCd", value = "발행일", paramType = "query"),
        @ApiImplicitParam(name = "spectxPrdDvCd", value = "발행주기", paramType = "query"),
        @ApiImplicitParam(name = "emadr", value = "이메일", paramType = "query"),
        @ApiImplicitParam(name = "faxLocaraTno", value = "팩스번호1", paramType = "query"),
        @ApiImplicitParam(name = "faxExno", value = "팩스번호2", paramType = "query"),
        @ApiImplicitParam(name = "faxIdvTno", value = "팩스번호3", paramType = "query"),
        @ApiImplicitParam(name = "lstmmYn", value = "전월여부", paramType = "query"),
    })
    @PostMapping("/trade-specification-sheets")
    public SaveResponse saveSpectxBlkPrnts(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.saveSpectxBlkPrnts(dtos)).build();
    }

    @ApiOperation(value = "거래명세서 일괄 출력 삭제", notes = "거래명세서 일괄 출력 삭제")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "spectxGrpNo", value = "그룹번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @DeleteMapping("/trade-specification-sheets")
    public SaveResponse removeSpectxBlkPrnts(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.removeSpectxBlkPrnts(dtos)).build();
    }

    @ApiOperation(value = "거래명세서 발송 조회", notes = "거래명세서 발송 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fromDate", value = "기간조회from", paramType = "query"),
        @ApiImplicitParam(name = "toDate", value = "기간조회to", paramType = "query"),
        @ApiImplicitParam(name = "spectxPblDDvCd", value = "발행일", paramType = "query"),
        @ApiImplicitParam(name = "fromGrpNo", value = "그룹번호from", paramType = "query"),
        @ApiImplicitParam(name = "toGrpNo", value = "그룹번호to", paramType = "query"),
        @ApiImplicitParam(name = "isYn", value = "발급여부", paramType = "query"),
    })
    @GetMapping("/send-trade-specification-sheets")
    public List<WctaSpectxBlkPrntDto.SpectxFwRes> getTradeSpcshFwInqrs(WctaSpectxBlkPrntDto.SpectxFwReq dto) {
        return service.getTradeSpcshFwInqrs(dto);
    }

    @ApiOperation(value = "거래명세서 발송", notes = "거래명세서 발송")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "spectxGrpNo", value = "그룹번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @PostMapping("/send-trade-specification-sheets")
    public int saveTradeSpcshFws(
        @RequestBody
        @Valid
        @NotEmpty
        List<WctaSpectxBlkPrntDto.SaveTradeSpcshFwReq> dtos
    ) throws Exception {
        return service.saveTradeSpcshFws(dtos);
    }
}
