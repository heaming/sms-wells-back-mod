package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbPointmallStatusDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbPointmallStatusService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 포인트몰 상태정보 조회 트랜잭션")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "pointmall-statuses")
//@Api(tags = "[WSNB] 포인트몰 상태정보 조회 트랜잭션")
@RequiredArgsConstructor
@Validated
public class WsnbPointmallStatusInterfaceController {

    private final WsnbPointmallStatusService service;

    @ApiOperation(value = "포인트몰 상태정보 조회 트랜잭션", notes = "포인트몰의 상태정보를 조회하는 인터페이스이다.")
    @GetMapping
    public EaiWrapper getPointmallStatuses(
        @Valid
        @RequestBody
        EaiWrapper<WsnbPointmallStatusDto.SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<List<WsnbPointmallStatusDto.SearchRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getPointmallStatuses(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
