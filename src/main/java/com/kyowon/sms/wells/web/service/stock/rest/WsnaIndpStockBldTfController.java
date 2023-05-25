package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.service.WsnaIndpStockBldTfService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[WSNA] 독립 창고 예외 빌딩 지역 데이터 이관 SERVICE REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/indp-stock-bld-tf")
public class WsnaIndpStockBldTfController {

    private final WsnaIndpStockBldTfService service;

    public SaveResponse createIndpWareBldTfs(){
        return SaveResponse.builder()
            .processCount(service.SaveCarriedOverAddressUseYn())
            .build();
    }
}
