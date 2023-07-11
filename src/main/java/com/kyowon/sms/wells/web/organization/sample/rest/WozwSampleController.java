package com.kyowon.sms.wells.web.organization.sample.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.organization.zcommon.constants.OzConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "[WOZW] 조직 샘플(wells특화+웹/모바일) REST API")
@RequestMapping(OzConst.REST_URL_V1 + "/mod-samples")
@RestController
public class WozwSampleController {

    @ApiOperation(value = "조직 샘플목록 조회", notes = "파라미터 없이 샘플데이터 조회한다.")
    @GetMapping
    public List<String> getSampleData() {
        System.out.println("===> TEST LOG");

        return List.of("TEST1", "TEST2", "TEST3");
    }
}
