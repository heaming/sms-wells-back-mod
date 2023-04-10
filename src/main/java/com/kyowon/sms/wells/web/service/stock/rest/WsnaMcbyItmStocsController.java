package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMcbyItmStocsDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMcbyItmStocsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "[WSNA] 월별 품목재고내역 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "mcby-itm-stocs")
public class WsnaMcbyItmStocsController {

    private final WsnaMcbyItmStocsService service;

    @ApiOperation(value = "월별 품목재고내역 등록", notes = "고객서비스 월별 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @PostMapping("/mcby-stocks")
    public SaveResponse saveMcbyStock(
        @RequestBody
        WsnaMcbyItmStocsDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveMcbyStock(dto)).build();
    }

    @ApiOperation(value = "월별 품목재고내역 삭제", notes = "고객서비스 월별 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @DeleteMapping("/mcby-stocks")
    public SaveResponse removeMcbyStock(
        @RequestBody
        WsnaMcbyItmStocsDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.removeMcbyStock(dto)).build();
    }

    @ApiOperation(value = "월별 품목재고내역 이월", notes = "고객서비스 월별 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @PostMapping("/mcby-stocs-iz")
    public SaveResponse saveMcbyItmStocCrdovrs(
        @RequestBody
        WsnaMcbyItmStocsDto.CrdovrReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveMcbyItmStocCrdovrs(dto)).build();
    }
}
