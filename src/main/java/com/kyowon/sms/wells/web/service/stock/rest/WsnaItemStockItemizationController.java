package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaItemStockItemizationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "[WSNA] 품목재고내역 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-stock-itemization")
public class WsnaItemStockItemizationController {

    private final WsnaItemStockItemizationService service;

    @ApiOperation(value = "품목재고내역 등록", notes = "고객서비스 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @PostMapping("/stocks")
    public SaveResponse createStock(
        @Valid
        @RequestBody
        WsnaItemStockItemizationDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.createStock(dto)).build();
    }

    @ApiOperation(value = "품목재고내역 삭제", notes = "고객서비스 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @DeleteMapping("/stocks")
    public SaveResponse removeStock(
        @Valid
        @RequestBody
        WsnaItemStockItemizationDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.removeStock(dto)).build();
    }

    @ApiOperation(value = "품목재고내역 이동", notes = "고객서비스 품목재고내역에 입출고유형 및 등급에 따라 내역, 수량을 INSERT / DELETE / UPDATE 한다.")
    @PutMapping("/stocks/movement")
    public SaveResponse saveStockMovement(
        @Valid
        @RequestBody
        WsnaItemStockItemizationDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveStockMovement(dto)).build();
    }

}
