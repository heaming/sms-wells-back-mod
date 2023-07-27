package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractRelationDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractRelationDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractRelationService;
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
public class WctiContractRelationInterfaceController {
    private final WctiContractRelationService service;

    @ApiOperation(value = "[EAI_WSSI1048] 연관계약건 목록 조회", notes = "입력받은 계약번호, 계약일련번호에 대해 계약 관계 내에서 '연계상품', '대체회원' 관계에 있는 계약정보를 조회")
    @PostMapping("/contract-relations")
    public EaiWrapper getRelatedContracts(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<SearchRes> res = service.getRelatedContracts(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
