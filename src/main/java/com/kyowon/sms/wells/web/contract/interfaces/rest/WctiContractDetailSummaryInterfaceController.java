package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractDetailSummaryService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 고객센터I/F")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/customer-centers")
@RequiredArgsConstructor
@Validated
public class WctiContractDetailSummaryInterfaceController {

    private final WctiContractDetailSummaryService service;

    @ApiOperation(value = "[EAI_WSSI1049] 계약상세 요약 정보 조회", notes = "계약번호, 계약일련번호를 기준으로 간단하고 빠르게 계약 정보 내역을 조회")
    @PostMapping("/contract-detail-summaries")
    public EaiWrapper getDetailSummary(
            @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        FindRes res = service.getDetailSummary(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
