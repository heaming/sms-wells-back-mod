package com.kyowon.sms.wells.web.contract.changeorder.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbMembershipBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "멤버십 일괄변경 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbMembershipBulkChangeController {
    private final WctbMembershipBulkChangeService service;

    @ApiOperation(value = "멤버십 일괄변경 관리", notes = "멤버십 주문건에 대한 일괄 변경 처리 및 조회하면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "rfdt", value = "반영일", paramType = "query"),
    })
    @GetMapping("/membership-bulk-change")
    public List<WctbMembershipBulkChangeDto.SearchRes> getMembershipBulkChanges(
        String cntrNo,
        String cntrSn,
        @ValidDate
        String rfdt
    ) {
        return service.getMembershipBulkChanges(cntrNo, cntrSn, rfdt);
    }

    @ApiOperation(value = "멤버십 일괄변경 계약 조회", notes = "계약번호,계약일련번호로 계약기본,계약상세, 계약WELLS상세의 데이타를 조회한다.")
    @GetMapping("/membership-change-contracts")
    public WctbMembershipBulkChangeDto.SearchCntrRes getMembershipBulkChangesContracts(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {
        return service.getMembershipBulkChangesContracts(cntrNo, cntrSn);
    }

    @ApiOperation(value = "멤버십 일괄변경 등록", notes = "멤버십 일괄변경 등록")
    @PostMapping("/membership-bulk-change")
    public SaveResponse saveMembershipBulkChange(
        @RequestBody
        @Valid
        WctbMembershipBulkChangeDto.SaveReq dto
    ) {
        return SaveResponse.builder().processCount(service.saveMembershipBulkChange(dto)).build();
    }
}
