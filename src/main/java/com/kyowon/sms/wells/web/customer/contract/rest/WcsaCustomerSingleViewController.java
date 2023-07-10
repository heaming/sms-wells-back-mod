package com.kyowon.sms.wells.web.customer.contract.rest;

import static com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.SearchReq;
import static com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.ContractRes;
import com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.PaymentRes;
import com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.ProductRes;
import com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.ServiceRes;
import com.kyowon.sms.wells.web.customer.contract.service.WcsaCustomerSingleViewService;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CsCustomerConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCSA] 고객 >> 고객관리 >> 고객 SingleView")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = CsCustomerConst.REST_URL_V1 + "/single-views")
public class WcsaCustomerSingleViewController {

    private final WcsaCustomerSingleViewService service;

    @ApiOperation(value = "Wells 고객 SingleView - 고객정보 조회", notes = "조회조건에 해당하는 고객의 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query", required = true),
        @ApiImplicitParam(name = "bryyMmdd", value = "생년월일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sexDvCd", value = "성별", paramType = "query", required = true),
    })
    @GetMapping("/customer-infos")
    public List<SearchRes> getCustomerSingleViewInfos(@Valid SearchReq dto) {
        return service.getCustomerSingleViewInfos(dto);
    }

    @ApiOperation(value = "Wells 고객 SingleView - 납입현황 조회", notes = "해당 고객번호의 납입현황 목록을 조회한다.")
    @GetMapping("/payments/{cstNo}")
    public PaymentRes getCustomerSingleViewPayments(@PathVariable String cstNo) {
        return service.getCustomerSingleViewPayments(cstNo);
    }

    @ApiOperation(value = "Wells 고객 SingleView - 계약현황 조회", notes = "해당 고객번호의 계약현황 목록을 조회한다.")
    @GetMapping("/contracts/{cstNo}")
    public List<ContractRes> getCustomerSingleViewContracts(@PathVariable String cstNo) {
        return service.getCustomerSingleViewContracts(cstNo);
    }

    @ApiOperation(value = "Wells 고객 SingleView - 서비스이력 조회", notes = "해당 고객번호의 서비스이력 목록을 조회한다.")
    @GetMapping("/services/{cstNo}")
    public List<ServiceRes> getCustomerSingleViewServices(@PathVariable String cstNo) {
        return service.getCustomerSingleViewServices(cstNo);
    }

    @ApiOperation(value = "Wells 고객 SingleView - 보유상품 조회", notes = "해당 고객번호의 보유상품 목록을 조회한다.")
    @GetMapping("/products/{cstNo}")
    public List<ProductRes> getCustomerSingleViewProducts(@PathVariable String cstNo) {
        return service.getCustomerSingleViewProducts(cstNo);
    }
}
