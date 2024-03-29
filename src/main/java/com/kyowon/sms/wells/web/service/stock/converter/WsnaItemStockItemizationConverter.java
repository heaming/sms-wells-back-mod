package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksReqDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;

@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class,
    org.apache.commons.lang3.ObjectUtils.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaItemStockItemizationConverter {

    @Mapping(source = "procsYm", target = "dspsYrmn")
    @Mapping(source = "procsDt", target = "dspsDt")
    @Mapping(source = "iostTp", target = "stdlTyp")
    WsnaItemStockItemizationDvo mapWsnaItemStockItemizationReqDvoToWsnaItemStockItemizationDvo(
        WsnaItemStockItemizationReqDvo dvo
    );

    WsnaMonthlyItemStocksReqDvo mapWsnaItemStockItemizationReqDvoToWsnaMonthlyItemStocksReqDvo(
        WsnaItemStockItemizationReqDvo dvo
    );

    @Mapping(target = "sapPlntCd", expression = "java(map.get(\"sapPlntCd\").toString())")
    @Mapping(target = "sapSaveLctCd", expression = "java(map.get(\"sapSaveLctCd\").toString())")
    @Mapping(target = "itmPdCd", expression = "java(map.get(\"itmPdCd\").toString())")
    @Mapping(target = "lgstAGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstAGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstAGdQty\") ) )")
    @Mapping(target = "lgstBGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstBGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstBGdQty\") ) )")
    @Mapping(target = "lgstCGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstCGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstCGdQty\") ) )")
    @Mapping(target = "lgstFGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstFGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstFGdQty\") ) )")
    @Mapping(target = "lgstEGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstEGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstEGdQty\") ) )")
    @Mapping(target = "lgstRGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstRGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstRGdQty\") ) )")
    @Mapping(target = "lgstXGdQty", expression = "java( ObjectUtils.isEmpty(map.get(\"lgstXGdQty\")) ? BigDecimal.ZERO : new BigDecimal( (int) map.get(\"lgstXGdQty\") ) )")
    RealTimeGradeStockResIvo mapHashMapToRealTimeGradeStockResIvo(HashMap<String, Object> map);

    List<RealTimeGradeStockResIvo> mapAllHashMapToRealTimeGradeStockResIvo(ArrayList<HashMap<String, Object>> list);

}
