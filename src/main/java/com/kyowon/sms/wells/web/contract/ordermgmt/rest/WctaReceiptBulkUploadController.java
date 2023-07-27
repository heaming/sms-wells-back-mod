package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaReceiptBulkUploadService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "[WCTA] 대량 업로드")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/bulk-upload")
public class WctaReceiptBulkUploadController {

    private final WctaReceiptBulkUploadService service;

    @ApiOperation(
        value = "렌탈 대량 업로드 유효성 검사",
        notes = "렌탈 대량 업로드 유효성 검사"
    )
    @PutMapping("/rentals/validate")
    public ValidateBulkRentalRes validateRentals(@RequestBody ValidateBulkRentalReq req) {
        return service.validateBulkRental(req);
    }

    @ApiOperation(
        value = "렌탈 일괄 저장",
        notes = "권한 주의"
    )
    @PostMapping("/rentals")
    public SaveResponse createRentals(
        @RequestBody
        List<CreateBulkRentalReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.createBulkRentals(reqs)).build();
    }

    @ApiOperation(
        value = "일시불 대량 업로드 유효성 검사",
        notes = "일시불 대량 업로드 유효성 검사"
    )
    @PutMapping("/single-payments/validate")
    public ValidateBulkSpayRes validateSinglePayment(@RequestBody ValidateBulkSpayReq req) {
        return service.validateBulkSpay(req);
    }

    @ApiOperation(
        value = "일시불 일괄 저장",
        notes = "권한 주의"
    )
    @PostMapping("/single-payments")
    public SaveResponse createSpays(
        @RequestBody
        List<CreateBulkSpayReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.createBulkSpays(reqs)).build();
    }

    @ApiOperation(
        value = "가망고객 유효성 검사",
        notes = "가망고객 유효성 검사"
    )
    @PutMapping("/prospects/validate")
    public ValidateProspectRes validateProspect(@RequestBody ValidateProspectReq req) {
        return service.validateProspect(req);
    }

    @ApiOperation(
        value = "가망고객 일괄 등록",
        notes = "가망고객 일괄 등록"
    )
    @PostMapping("/prospects")
    public SaveResponse createProspectCsts(
        @RequestBody
        List<CreateProspectCstReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.createBulkProspectCsts(reqs)).build();
    }

    @ApiOperation(
        value = "설치처 유효성 검사",
        notes = "설치처 유효성 검사"
    )
    @PutMapping("/install-location/validate")
    public ValidateIstlcRes validateInstallLocation(@RequestBody ValidateIstlcReq req) {
        return service.validateInstallLocation(req);
    }

    @ApiOperation(
        value = "설치처 일괄 등록",
        notes = "설치처 일괄 등록"
    )
    @PostMapping("/install-locations")
    public SaveResponse createInstallLocations(
        @RequestBody
        List<CreateBulkIstlcReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.createBulkInstallLocations(reqs)).build();
    }

}
