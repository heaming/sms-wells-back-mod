package com.kyowon.sms.wells.web.promotion.common.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionInputDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionOutputDvo;
import com.kyowon.sms.wells.web.promotion.common.service.WpmzPromotionCheckService;
import com.kyowon.sms.wells.web.promotion.zcommon.constants.PmPromotionConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WPMZ] Wells 공통 프로모션 체크 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = PmPromotionConst.REST_URL_V1 + "/services")
public class WpmzPromotionCheckController {

    private final WpmzPromotionCheckService service;

    @ApiOperation(value = "(서비스 API) 적용 프로모션 정보 조회", notes = "조건에 맞는 적용 프로모션 리스트를 조회한다.")
    @GetMapping("/promotions")
    public List<WpmzPromotionOutputDvo> getAppliedPromotions(WpmzPromotionInputDvo dvo) throws NoSuchFieldException, IllegalAccessException {

        return service.getAppliedPromotions(dvo);
    }
}
