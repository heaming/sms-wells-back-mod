package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerRglrBfsvcDlService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNB] 고객 정기BS 삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/rglr-bfsvc-dl")
@Slf4j
public class WsnbCustomerRglrBfsvcDlController {

    private final WsnbCustomerRglrBfsvcDlService service;

    @ApiOperation(value = "고객 정기BS 삭제", notes = "고객 정기BS 삭제")
    @DeleteMapping
    public SaveResponse removeRglrBfsvcDl(
        @Valid
        @RequestBody
        WsnbCustomerRglrBfsvcDlDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeRglrBfsvcDl(dto))
            .build();
    }
}
