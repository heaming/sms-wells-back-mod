package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationConfirmationDto.CreateAgreeReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationConfirmationDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationConfirmationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REPORT_URL_V1 + "/installtion-confirmation")
@Api(tags = "[WSNB] 전자설치확인서 API")
@RequiredArgsConstructor
public class WsnbInstallationConfirmationController {

    private final WsnbInstallationConfirmationService service;

    @ApiOperation(value = "전자설치확인서 정보 조회", notes = "전자설치확인서에 보여줘하는 정보를 조회한다.")
    @GetMapping("/{cstSvAsnNo}")
    public Map<String, Object> getInstallationConfirmation(
        @PathVariable
        String cstSvAsnNo,
        @RequestParam
        String wprs
    ) {
        return service.getInstallationConfirmation(cstSvAsnNo, wprs);
    }

    @ApiOperation(value = "전자설치확인서 저장 시 파라미터 재전송", notes = "전자설치확인서화면에서 저장시 입력한 값을 리턴한다.")
    @PostMapping("/agree")
    CreateAgreeReq createAgree(
        @RequestBody
        CreateAgreeReq dto
    ) {
        return dto;
    }

    @ApiOperation(value = "전자설치확인서 저장", notes = "전자설치확인서화면에서 선택한 값을 저장한다.")
    @PostMapping
    public SaveResponse createInstallationConfirmation(
        @RequestBody
        CreateReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createInstallationConfirmation(dto))
            .build();
    }

}
