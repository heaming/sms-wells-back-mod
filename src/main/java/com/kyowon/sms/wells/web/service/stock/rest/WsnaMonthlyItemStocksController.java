package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyItemStocksDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMonthlyItemStocksService;
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
@RequestMapping(SnServiceConst.REST_URL_V1 + "monthly-item-stocks")
public class WsnaMonthlyItemStocksController {

    private final WsnaMonthlyItemStocksService service;

    @ApiOperation(value = "월별 품목재고내역 등록", notes = "고객서비스 월별 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @PostMapping
    public SaveResponse saveMonthlyStock(
        @RequestBody
        WsnaMonthlyItemStocksDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveMonthlyStock(dto)).build();
    }

    @ApiOperation(value = "월별 품목재고내역 삭제", notes = "고객서비스 월별 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @DeleteMapping
    public SaveResponse removeMonthlyStock(
        @RequestBody
        WsnaMonthlyItemStocksDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.removeMonthlyStock(dto)).build();
    }

    @ApiOperation(value = "월별 품목재고내역 이월", notes = "고객서비스 월별 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @PostMapping("/carried-over")
    public SaveResponse saveMonthlyItemStocCrdovrs(
        @RequestBody
        WsnaMonthlyItemStocksDto.CrdovrReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveMonthlyItemStocCrdovrs(dto)).build();
    }
}
