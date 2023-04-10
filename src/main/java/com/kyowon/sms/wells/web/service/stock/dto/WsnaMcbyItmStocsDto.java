package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-S-0086 월별 품목재고내역 관리
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.04.05
 */
public class WsnaMcbyItmStocsDto {

    @ApiModel(value = "WsnaMcbyItmStocsDto-SaveReq")
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
        String qty /*수량*/
    ) {}

    @ApiModel(value = "WsnaMcbyItmStocsDto-CrdovrReq")
    public record CrdovrReq(
        String baseYm, /*기준년월*/
        String wareNo, /*창고번호*/
        String itmPdCd, /*품목상품코드*/
        String itemGd, /*상품등급*/
        String mmtStocQty, /*이동재고수량*/
        String pitmStocQty, /*시점재고수량*/
        String wareMngtPrtnrNo /*창고관리파트너번호*/
    ) {}
}
