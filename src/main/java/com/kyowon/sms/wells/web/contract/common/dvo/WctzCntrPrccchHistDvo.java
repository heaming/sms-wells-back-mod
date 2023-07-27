package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrPrccchHistDvo {
    private String cntrPrcCmptId; /* 계약가격산출ID */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String pdCd; /* 상품코드 */
    private String pdPrcFnlDtlId; /* 상품가격최종상세ID */
    private Integer verSn; /* 버전일련번호 */
    private Integer apyStrtTn; /* 적용시작회차 */
    private Integer apyEndTn; /* 적용종료회차 */
    private String fxamFxrtDvCd; /* 정액정률구분코드 */
    private Long ctrVal; /* 조정값 */
    private Long fnlVal; /* 최종값 */
    private String pdPrcId; /* 상품가격ID */
    private String dtaDlYn; /* 데이터삭제여부 */
}
