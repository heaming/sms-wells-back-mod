package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniEkpEngineerPhotoDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniEkpEngineerPhotoDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniEkpEngineerPhotoService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": EKP 엔지니어 사진 파일 다운로드")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/ekp-engineer-photo")
@RequiredArgsConstructor
@Validated
public class WsniEkpEngineerPhotoController {

    private final WsniEkpEngineerPhotoService service;

    @ApiOperation(value = "EKP 엔지니어 사진 파일 다운로드", notes = "EKP 엔지니어 사진 파일 다운로드를 수행한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ATTH_DOC_ID", value = "첨부문서 ID", paramType = "query", example = "APP_WELLS_SERVICE_00001", required = true)
    })
    @PostMapping
    public EaiWrapper getEkpEngineerPhoto(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<SearchRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getEkpEngineerPhoto(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
