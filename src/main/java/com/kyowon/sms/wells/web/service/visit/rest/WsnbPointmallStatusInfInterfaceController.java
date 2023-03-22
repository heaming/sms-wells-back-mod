package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbPointmallStatusInfService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.INTERFACE_URL_V1 + "pointmall-status-inf")
@Api(tags = "[WSNB] 포인트몰 상태정보 조회 트랜잭션")
@RequiredArgsConstructor
@Validated
public class WsnbPointmallStatusInfInterfaceController {

    private final WsnbPointmallStatusInfService service;

    @ApiOperation(value = "포인트몰 상태정보 조회 트랜잭션", notes = "포인트몰의 상태정보를 조회하는 인터페이스이다.")
    @GetMapping
    public EaiWrapper getPointmallStatusInfs(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<List<SearchRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getPointmallStatusInfs(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
