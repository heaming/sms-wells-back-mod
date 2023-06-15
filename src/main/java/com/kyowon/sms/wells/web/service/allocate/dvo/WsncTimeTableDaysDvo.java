package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableDaysDvo {
    String baseY;/* 기준연도 */
    String baseMm;/* 기준월 */
    String baseD;/* 기준일 */
    String dowDvCd;/* 요일구분코드 */
    String dfYn;/* 휴무여부 */
    String phldYn;/* 공휴일여부 */
    String rmkCn;/* 비고내용 */
}
