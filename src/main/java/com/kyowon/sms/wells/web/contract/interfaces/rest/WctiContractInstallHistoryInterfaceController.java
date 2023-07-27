package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractInstallHistoryService;
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
public class WctiContractInstallHistoryInterfaceController {
    private final WctiContractInstallHistoryService service;

    @ApiOperation(value = "[EAI_WSSI1052] 계약처, 설치처 정보 변경 이력 조회", notes = "입력받은 계약번호, 계약일련번호에 대한 설치처 변경 이력을 조회")
    @PostMapping("/contract-install-histories")
    public EaiWrapper getInstallHistories(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<SearchRes> res = service.getIstlcChHist(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
