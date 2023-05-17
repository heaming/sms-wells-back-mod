package com.kyowon.sms.wells.web.promotion.common.rest;

import static com.kyowon.sms.wells.web.promotion.common.dto.WpmzPromotionCheckDto.SearchReq;
import static com.kyowon.sms.wells.web.promotion.common.dto.WpmzPromotionCheckDto.SearchRes;

import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.promotion.common.converter.WpmzPromotionCheckConverter;
import com.kyowon.sms.wells.web.promotion.common.service.WpmzPromotionCheckService;
import com.kyowon.sms.wells.web.promotion.zcommon.constants.PmPromotionConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WPMZ] Wells 공통 프로모션 체크 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = PmPromotionConst.REST_URL_V1 + "/services")
public class WpmzPromotionCheckController {

    private final WpmzPromotionCheckService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pmotCd",              value = "프로모션코드",             paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "pmotOjGrpDvCd",       value = "프로모대상그룹코드",          paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "pmotApyChnlCd",       value = "프로모션적용채널코드",         paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "pmotApyOgTpCd",       value = "프로모션적용조직코드",         paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "basePdCd",            value = "상품코드",               paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "basePdPrcDtlCd",      value = "상품가격상세코드",           paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "lkPdClsfCd",          value = "연계상품분류(모종 상품분류코드)", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "lkPdCd",              value = "연계상품코드(모종 상품코드)",   paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "pkgMndtPdCd",         value = "패키지필수 상품코드",         paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "chdvcPrmitYn",        value = "기기변경허용여부",           paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "chdvcBfPdClsfCd",     value = "기기변경이전상품분류",         paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "chdvcBfPdCd",         value = "기기변경이전상품코드",         paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "lkChdvcPrmitYn",      value = "연계코드기변제외",           paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "chdvcTpCd",           value = "기기변경유형",             paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "oppstOrdRcptdt",      value = "기기변경이전상품접수일자",      paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "oppstSlDt",           value = "기기변경이전상품매출일자",      paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "alncBzsCd",           value = "제휴업체코드",             paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "evCd",                value = "행사코드",               paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "selrCd",              value = "판매자코드",              paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "dupApyPsbYn",         value = "중복적용가능여부",           paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "crpDscExcdYn",        value = "법인DC제외여부",           paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "spcDscCd",            value = "특별할인코드",             paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "(서비스 API) 적용 프로모션 정보 조회", notes = "조건에 맞는 적용 프로모션 리스트를 조회한다.")
    @GetMapping("/promotions")
    public List<SearchRes> getAppliedPromotions(SearchReq req) throws NoSuchFieldException, IllegalAccessException {

        return service.getAppliedPromotions(req);
    }
}
