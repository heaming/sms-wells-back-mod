package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRerentalOnplusoneDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRerentalOnplusoneDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiRerentalOnePlusoneService;
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
public class WctiRerentalOnePlusoneInterfaceController {
    private final WctiRerentalOnePlusoneService service;

    @ApiOperation(value = "[EAI_WSSI1058] wells 재렌탈, 1+1 정보 조회", notes = "입력받은 계약상세번호로 wells 재렌탈, 1+1 정보를 가져온다.")
    @PostMapping("/onePlusone")
    public EaiWrapper getRerentalOneplusones(
        @Valid
        @RequestBody
        EaiWrapper<List<SearchReq>> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        List<SearchRes> resList = new ArrayList<>();

        // 서비스 메소드 호출
        for (SearchReq req : reqWrapper.getBody()) {
            List<SearchRes> res = service.getRerentalOneplusones(req);
            resList.addAll(res);
        }

        // Response Body 세팅
        resWrapper.setBody(resList);

        return resWrapper;
    }
}
