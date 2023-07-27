package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaDocumentReceiptPssService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] wells 서류접수현황(추가)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1)
public class WctaDocumentReceiptPssController {

    private final WctaDocumentReceiptPssService service;

    @ApiOperation(value = "wells 서류접수현황(추가) 조회", notes = "업무요청시 증빙서류가 필요한 건에 대한 접수현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrChRcpStrtDtm", value = "접수시작일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrChRcpFinsDtm", value = "접수종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrChPrgsStatCd", value = "접수현황", paramType = "query"),
        @ApiImplicitParam(name = "cntrChTypeCd", value = "접수유형", paramType = "query"),
        @ApiImplicitParam(name = "cntrChRcpId", value = "접수번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping("/document-receipts")
    public List<SearchRes> getDocumentReceipts(
        @Valid
        SearchReq dto
    ) {
        return service.getDocumentReceipts(dto);
    }

    @GetMapping("/document-receipts/excel-download")
    public List<SearchRes> getDocumentReceiptsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getDocumentReceiptsExcelDownload(dto);
    }

    @ApiOperation(value = "wells 서류 접수 기타 종료 선택", notes = "서류 접수 기타 종료 선택 후 확정 처리를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrChRcpId", value = "접수번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrChPrgsStatCd", value = "계약변경진행상태코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrChAkCn", value = "재접수 사유", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping("/document-receipts/confirms")
    public int saveDocumentRcpCnfm(
        @Valid
        SaveReq dto
    ) throws Exception {
        return service.saveDocumentRcpCnfm(dto);
    }

    @ApiOperation(value = "wells 서류접수상세 조회", notes = "전자 업무 요청에 대한 처리 현황(개명/명의변경/ 해지신청 등 증빙서류 첨부 건에 대한 최종 확정)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrChRcpId", value = "접수번호", paramType = "query"),
    })
    @GetMapping("/document-receipts/details")
    public WctaDocumentReceiptPssDto.SearchDocumentRcpDtlRes getDocumentRcpDtlInqrs(
        @Valid
        String cntrChRcpId
    ) {
        return service.getDocumentRcpDtlInqrs(cntrChRcpId);
    }
}
