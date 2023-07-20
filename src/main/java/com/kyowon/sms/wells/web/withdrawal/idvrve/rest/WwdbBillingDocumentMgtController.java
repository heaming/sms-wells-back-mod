package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SaveFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchDtlsRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchFwRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbBillingDocumentMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 청구서 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/billing-document-orders")
public class WwdbBillingDocumentMgtController {

    private final WwdbBillingDocumentMgtService service;

    @ApiOperation(value = "청구서 관리 목록 조회", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBillingDocumentPages(SearchReq dto, PageInfo pageInfo) {
        return service.getBillingDocumentPages(dto, pageInfo);
    }

    @ApiOperation(value = "청구서 관리 엑셀다운로드", notes = " 검색조건을 받아 청구서 관리 엑셀다운로드을 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getBillingDocumentExcels(SearchReq dto) {
        return service.getBillingDocumentExcels(dto);
    }

    @ApiOperation(value = "청구서 관리 목록 삭제", notes = " 청구서 관리 목록을 삭제한다.")
    @DeleteMapping
    public SaveResponse removeBillingDocumentMgts(
        @RequestBody
        @Valid
        List<RemoveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeBillingDocuments(dto))
            .build();
    }

    @ApiOperation(value = "청구서 관리 목록 조회", notes = "청구서 관리 목록을 조회한다.")
    @GetMapping("/details")
    public PagingResult<SearchDtlsRes> getBillingDocumentDetails(SearchDtlsReq dto, PageInfo pageInfo) {
        return service.getBillingDocumentDetails(dto, pageInfo);
    }

    @ApiOperation(value = "청구서 관리 목록 저장", notes = "RDS 적요 청구서 관리 등록한다.")
    @PostMapping("/details")
    public SaveResponse saveBillingDocuments(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        log.info("=========cont===============");
        log.info(dto.saveMainReq().bildcPblSn());
        log.info("=========cont===============");

        return SaveResponse.builder()
            .processCount(service.saveBillingDocuments(dto))
            .build();
    }

    @ApiOperation(value = "청구서 발송 목록 조회", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/forwardings")
    public List<SearchFwRes> getBillingDocumentForwardings(SearchFwReq dto) {
        return service.getBillingDocumentForwardings(dto);
    }

    @ApiOperation(value = "청구서 발송", notes = "청구서 발송을 한다.")
    @PostMapping("/forwardings")
    public SaveResponse saveBillingDocumentForwarding(
        @RequestBody
        @Valid
        SaveFwReq dto
    ) throws Exception {
        log.info("===========");
        log.info(dto.toString());
        log.info("===========");
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentForwarding(dto))
            .build();
    }

}
