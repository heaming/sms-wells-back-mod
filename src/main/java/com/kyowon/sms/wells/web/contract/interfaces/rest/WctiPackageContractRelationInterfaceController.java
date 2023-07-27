package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiPackageContractRelationService;
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
public class WctiPackageContractRelationInterfaceController {
    private final WctiPackageContractRelationService service;

    @ApiOperation(value = "[EAI_WSSI1067] 패키지연관 계약건 조회", notes = "입력받은 계약번호, 계약일련번호에 대한 홈케어멤버십, 패키지 관계 계약정보를 조회")
    @PostMapping("/package-contract-relations")
    public EaiWrapper getPackageContractRelations(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<FindRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<FindRes> res = service.getPackageContractRelations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
