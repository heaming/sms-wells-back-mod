package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailMngtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세조회/관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailMngtController {

    private final WctaOrderDetailMngtService service;

    @ApiOperation(value = "주문상세조회/관리", notes = "렌탈 주문상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류(대분류)", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류(중분류)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "제휴코드", paramType = "query"),
        @ApiImplicitParam(name = "sellEvCd", value = "행사코드", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드(총괄단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드(지역단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드(지점)", paramType = "query"),
        @ApiImplicitParam(name = "cndtSellTpCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "booSellYn", value = "자료구분-예약자료", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "자료구분-취소제외", paramType = "query"),
        @ApiImplicitParam(name = "slYn", value = "자료구분-매출생성", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "istCralTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpKnm", value = "설치자명", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/rentals/paging")
    public PagingResult<SearchRes> getOrderDetailRentalPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderDetailRentalPages(dto, pageInfo);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "렌탈 주문상세내역을 조회 후 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류(대분류)", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류(중분류)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "제휴코드", paramType = "query"),
        @ApiImplicitParam(name = "sellEvCd", value = "행사코드", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드(총괄단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드(지역단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드(지점)", paramType = "query"),
        @ApiImplicitParam(name = "cndtSellTpCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "booSellYn", value = "자료구분-예약자료", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "자료구분-취소제외", paramType = "query"),
        @ApiImplicitParam(name = "slYn", value = "자료구분-매출생성", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "istCralTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpKnm", value = "설치자명", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/rentals/excel-download")
    public List<SearchRes> getOrderDtlRentalExcels(
        @Valid
        SearchReq dto
    ) {
        return service.getOrderDtlRentalExcels(dto);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "멤버쉽 주문상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpDateDv", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "dateSeltDv", value = "일자선택", paramType = "query"),
        @ApiImplicitParam(name = "choStrtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "choEndDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "계약구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrwTpCd", value = "멤버십구분", paramType = "query"),
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류(대분류)", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류(중분류)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrRcpFshDtYn", value = "미가입자만 조회", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmddEntrpNoCbno", value = "생년월일/사업자/법인등록번호", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query"),
        @ApiImplicitParam(name = "sexDvCd", value = "성별구분", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자번호/법인번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cntrCralTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "탈퇴제외", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/membership/paging")
    public PagingResult<SearchOrderDetailMshPagesRes> getOrderDetailMshPages(
        @Valid
        SearchOrderDetailMshPagesReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderDetailMshPages(dto, pageInfo);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "멤버쉽 주문상세내역을 조회 후 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpDateDv", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "dateSeltDv", value = "일자선택", paramType = "query"),
        @ApiImplicitParam(name = "choStrtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "choEndDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "계약구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrwTpCd", value = "멤버십구분", paramType = "query"),
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류(대분류)", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류(중분류)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrRcpFshDtYn", value = "미가입자만 조회", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmddEntrpNoCbno", value = "생년월일/사업자/법인등록번호", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query"),
        @ApiImplicitParam(name = "sexDvCd", value = "성별구분", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자번호/법인번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cntrCralTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "탈퇴제외", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/membership/excel-download")
    public List<SearchOrderDetailMshPagesRes> getOrderDetailMshExcels(
        @Valid
        SearchOrderDetailMshPagesReq dto
    ) {
        return service.getOrderDetailMshExcels(dto);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "일시불 주문상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchGbn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자/법인등록번호", paramType = "query"),
        @ApiImplicitParam(name = "sexGbn", value = "남녀구분", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCanYn", value = "취소제외", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류(대분류)", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류(중분류)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "제휴코드", paramType = "query"),
        @ApiImplicitParam(name = "sellEvCd", value = "행사코드", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드(총괄단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드(지역단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드(지점)", paramType = "query"),
        @ApiImplicitParam(name = "etcDv", value = "기타", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/singlepayments/paging")
    public PagingResult<SearchOrderDetailSnglPmntPagesRes> getOrderDetailSpayCntrtPages(
        @Valid
        SearchOrderDetailSnglPmntPagesReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderDetailSpayCntrtPages(dto, pageInfo);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "일시불 주문상세내역을 조회 후 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchGbn", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자/법인등록번호", paramType = "query"),
        @ApiImplicitParam(name = "sexGbn", value = "남녀구분", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCanYn", value = "취소제외", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "hcsfVal", value = "상품분류(대분류)", paramType = "query"),
        @ApiImplicitParam(name = "hcsfMcsfVal", value = "상품분류(중분류)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "제휴코드", paramType = "query"),
        @ApiImplicitParam(name = "sellEvCd", value = "행사코드", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드(총괄단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드(지역단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드(지점)", paramType = "query"),
        @ApiImplicitParam(name = "etcDv", value = "기타", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/singlepayments/excel-download")
    public List<SearchOrderDetailSnglPmntPagesRes> getOrderDetailSpayCntrtPagesExcelDownload(
        @Valid
        SearchOrderDetailSnglPmntPagesReq dto
    ) {
        return service.getOrderDetailSpayCntrtPagesExcelDownload(dto);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "정기배송 주문상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "자료구분-취소제외", paramType = "query"),
        @ApiImplicitParam(name = "slYn", value = "자료구분-매출생성", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드-지점", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "mchnDv", value = "기기종류", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/regular-shippings/paging")
    public PagingResult<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsPages(
        @Valid
        SearchOrderDetailRglrDlvrPagesReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOrderRegularShippingsPages(dto, pageInfo);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "정기배송 주문상세내역을 조회 후 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdEnqry", value = "기간조회", paramType = "query"),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "canYn", value = "자료구분-취소제외", paramType = "query"),
        @ApiImplicitParam(name = "slYn", value = "자료구분-매출생성", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "조직코드-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드-지점", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "파트너코드", paramType = "query"),
        @ApiImplicitParam(name = "mchnDv", value = "기기종류", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/regular-shippings/excel-download")
    public List<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsExcels(
        @Valid
        SearchOrderDetailRglrDlvrPagesReq dto
    ) {
        return service.getOrderRegularShippingsExcels(dto);
    }

    @ApiOperation(value = "멤버쉽 확정관리", notes = "선택한 멤버쉽 계약의 확정일자와 가입일자를 수정")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmYn", value = "확정유무", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmDtm", value = "확정일", paramType = "query"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "가입일", paramType = "query"),
    })
    @PutMapping("/order-detail-mngt/membership-confirm")
    public SaveResponse saveMembershipConfirms(
        @RequestBody
        @NotEmpty
        List<SaveMembershipConfirmsReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveMembershipConfirms(dtos)).build();
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "주문상세조회/관리(정기배송) -복합상품목록")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/regular-shippings/composition-products")
    public List<SearchCompositionProductsRes> getCompositionProducts(
        @Valid
        SearchCompositionProductsReq dto
    ) {
        return service.getCompositionProducts(dto);
    }

    @ApiOperation(value = "주문상세조회/관리", notes = "주문상세조회/관리(일시불) -사은품정보")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-detail-mngt/singlepayments/free-gift-information")
    public List<SearchFreeGiftInformationRes> getFreeGiftInformation(
        @Valid
        SearchCompositionProductsReq dto
    ) {
        return service.getFreeGiftInformation(dto);
    }
}
