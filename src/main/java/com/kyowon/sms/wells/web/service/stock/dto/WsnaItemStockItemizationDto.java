package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * W-SV-S-0086 품목재고내역 관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.03.13
 */
public class WsnaItemStockItemizationDto {

    @ApiModel(value = "WsnaItemStockItemizationDto-SaveReq")
    public record SaveReq(
        String procsYm, /*처리년월*/
        String procsDt, /*처리일자*/
        String wareDv, /*창고구분*/
        String wareNo, /*창고번호*/
        String wareMngtPrtnrNo, /*창고관리파트너번호*/
        String iostTp, /*입출고유형*/
        String workDiv, /*작업구분*/
        String itmPdCd, /*품목상품코드*/
        String mngtUnit, /*관리단위*/
        String itemGd, /*상품등급*/
        String qty, /*수량*/
        /*TODO : 아래 3개항목 새로 정의 필요*/
        String mvcYn, /*??*/
        String freeYn, /*??*/
        String chgYn /*??*/

    ) {}
}
