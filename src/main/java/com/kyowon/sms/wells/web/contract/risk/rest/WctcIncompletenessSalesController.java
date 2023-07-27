package com.kyowon.sms.wells.web.contract.risk.rest;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchByCntrNoReq;
import com.kyowon.sms.wells.web.contract.risk.service.WctcIncompletenessSalesService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] 불완전 판매")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/incomplete-sales")
public class WctcIncompletenessSalesController {

    private final WctcIncompletenessSalesService service;

    @ApiOperation(value = "기기 변경 부정 행위 상세 조회", notes = "Grid에서 입력한 신규계약번호, 이전계약번호의 상세내용을 조회하여 반환한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseCntrNo", value = "기준계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseCntrSn", value = "기준계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "ojCntrNo", value = "대상계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "ojCntrSn", value = "대상계약일련번호", paramType = "query", required = true),
    })
    @GetMapping
    public SearchRes getIncompletenessSales(
        @Valid
        SearchByCntrNoReq dto
    ) {
        return service.getIncompletenessSales(dto);
    }

    @ApiOperation(value = "기기 변경 부정 행위 페이징 조회", notes = "불완전판매내역 테이블을 조회하여, 해당하는 계약번호의 계약상세 및 파트너, 조직 테이블을 Join 하여 정보를 가져온다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "apyCls", value = "적발년월/발생년월", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "strtYm", value = "시작년월", paramType = "query"),
        @ApiImplicitParam(name = "endYm", value = "종료년월", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "지역단 코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "지점 코드", paramType = "query"),
        @ApiImplicitParam(name = "prtnrKnm", value = "파트너명", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getIncompletenessSalesPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getIncompletenessSalesPages(dto, pageInfo);
    }

    @ApiOperation(value = "기기 변경 부정 행위 엑셀 다운로드", notes = "기기 변경 부정 행위 조회 결과를 엑셀 다운로드 한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "icptSellExrDt", value = "적발년월", paramType = "query"),
        @ApiImplicitParam(name = "baseCntrRcpdt", value = "발생년월", paramType = "query"),
        @ApiImplicitParam(name = "apyStrtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "apyEndDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "지역단 코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "지점 코드", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getIncompletenessSalesForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getIncompletenessSalesForExcelDownload(dto);
    }

    @ApiOperation(value = "기기 변경 부정 행위 저장", notes = "수정 또는 입력된 정보를 DB에 저장 혹은 업데이트 한다. (이력테이블에도 추가한다)")
    @PostMapping
    public SaveResponse saveIncompletenessSales(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveIncompletenessSales(dtos)).build();
    }

    @ApiOperation(value = "기기 변경 부정 행위 삭제", notes = "선택한 row의 불완전판매ID를 기준으로 해당 레코드의 데이터 삭제 여부를 Y로 변경한다. ")
    @DeleteMapping
    public SaveResponse removeIncompletenessSales(
        @RequestBody
        @Valid
        @NotEmpty
        List<String> icptSellIds
    ) {
        return SaveResponse.builder().processCount(service.removeIncompletenessSales(icptSellIds)).build();
    }
}
