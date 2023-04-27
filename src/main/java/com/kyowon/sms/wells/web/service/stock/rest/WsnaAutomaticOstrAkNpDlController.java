package com.kyowon.sms.wells.web.service.stock.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaAutomaticOstrAkNpDlService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 자동출고 요청건 중 미처리건 삭제 처리 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/automatic-ostr-ak-np-dl")
public class WsnaAutomaticOstrAkNpDlController {

    private final WsnaAutomaticOstrAkNpDlService service;

    @PutMapping
    public int removeAutomaticOstrAkNpDls() {
        return service.removeAutoOstrAkNpDls();
    }
}
