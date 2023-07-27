package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMembershipContactDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMembershipContactDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiMembershipContactService;
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
public class WctiMembershipContactInterfaceController {
    private final WctiMembershipContactService service;

    @ApiOperation(value = "[EAI_WSSI1076] 멤버십 계약 접수 현황 조회", notes = "고객센터 아웃바운드운영팀에서 멤버십 계약 접수 컨택을 진행하는 건들을 조회한다.")
    @PostMapping("/membership-contact-lists")
    public EaiWrapper getMembershipContracts(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getMembershipContracts(reqWrapper.getBody()));

        return resWrapper;
    }
}
