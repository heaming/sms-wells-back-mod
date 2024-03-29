package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchFrdmPkgReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchFrdmPkgRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaSeedingService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineRes;

@Api(tags = "[WCTA] 모종/캡슐 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/seeding")
public class WctaSeedingController {
    private final WctaSeedingService service;

    @ApiOperation(value = "Machine 다건 조회", notes = "generated by LT, for Machine, dto modifier: Machine")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "rglrSppMchnTpCd", value = "정기배송기기유형코드", paramType = "query", required = true),
    })
    @GetMapping("/machinery")
    public List<SearchMachineRes> getMachinery(
        @Valid
        SearchMachineReq dto
    ) {
        return service.getMachinery(dto);
    }

    @ApiOperation(value = "FRDM PKG 다건 조회", notes = "generated by LT, for FRDM PKG, dto modifier: FRDM PKG")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "rglrSppMchnTpCd", value = "정기배송기기유형코드", paramType = "query"),
        @ApiImplicitParam(name = "rglrSppPrcDvCd", value = "정기배송가격구분코드", paramType = "query"),
    })
    @GetMapping("/package-products")
    public SearchFrdmPkgRes getFrdmPkgs(
        SearchFrdmPkgReq req
    ) {
        return service.getFrdmPkgs(req);
    }
}
