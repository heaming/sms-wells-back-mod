package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeGiftDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeGiftDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiFreeGiftService;
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
public class WctiFreeGiftInterfaceController {
    private final WctiFreeGiftService service;

    @ApiOperation(value = "[EAI_WSSI1055] 사은품 정보 조회", notes = "계약번호, 계약일련번호에 대한 사은품 정보를 조회")
    @PostMapping("/free-gifts")
    public EaiWrapper getFreeGift(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<SearchRes> res = service.getFreeGift(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
