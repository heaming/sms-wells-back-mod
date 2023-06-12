package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0086 월별 품목재고내역 관리 request dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-12
 */

@Getter
@Setter
public class WsnaMonthlyItemStocksReqDvo {

    private String procsYm; /*처리년월*/
    private String procsDt; /*처리일자*/
    private String wareDv; /*창고구분*/
    private String wareNo; /*창고번호*/
    private String wareMngtPrtnrNo; /*창고관리파트너번호*/
    private String iostTp; /*입출고유형*/
    private String workDiv; /*작업구분*/
    private String itmPdCd; /*품목상품코드*/
    private String mngtUnit; /*관리단위*/
    private String itemGd; /*상품등급*/
    private String qty; /*수량*/

}
