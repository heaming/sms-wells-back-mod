package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInstallationShippingDto.*;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationShippingService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 설치/배송 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/installation-shippings")
public class WctaInstallationShippingController {

    private final WctaInstallationShippingService service;

    @ApiOperation(value = "설치/배송 조회", notes = "")
    @GetMapping("/paging")
    public PagingResult<SearchIstShippingRes> getInstallationShippings(
        @Valid
        SearchIstShippingReq dto,
        @Valid
        PageInfo pageinfo
    ) {
        return service.getInstallationShippingsPages(dto, pageinfo);
    }

    @ApiOperation(value = "설치/배송 배정처리 전 체크", notes = "")
    @PostMapping("/checks")
    public String getProcessingBfChecks(
        @RequestBody
        SearchAssignProcessingReq dto
    ) {
        return service.getProcessingBfChecks(dto);
    }

    @ApiOperation(value = "설치/배송 배정처리(접수/배정/배정취소)", notes = "")
    @PostMapping
    public String saveAssignProcessings(
        @RequestBody
        SaveAssignProcessingReq dto
    ) throws Exception {
        return service.saveAssignProcessings(dto);
    }

    @ApiOperation(value = "설치/배송 예정일자 수정", notes = "")
    @PutMapping("/due-date")
    public String editDueDates(
        @RequestBody
        EditDueDateReq dto
    ) {
        return service.editDueDates(dto);
    }

    @ApiOperation(value = "설치/배송 예정일자 취소", notes = "")
    @PutMapping("/due-date-cancel")
    public String editDueDateCancels(
        @RequestBody
        EditDueDateCancelReq dto
    ) {
        return service.editDueDateCancels(dto);
    }
}
