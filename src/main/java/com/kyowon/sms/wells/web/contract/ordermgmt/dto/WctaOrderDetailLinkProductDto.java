package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaOrderDetailLinkProductDto {
    @ApiModel("WctaOrderDetailLinkProductDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* [계약상세번호] 계약번호 */
        String cntrSn, /* [계약상세번호] 계약일련번호 */
        String cntrRelTpCd, /* 계약관계유형코드 */
        String cntrRelTpNm, /* 계약관계유형코드명 */
        String cntrRelDtlCd, /* 계약관계상세코드 */
        String cntrRelDtlNm, /* [연관계약명] 계약관계상세코드명 */
        String relDv, /* 계약번호 구분 B:기준, O:대상 */
        String basePdCd, /* [상품코드] 기준상품코드 */
        String basePdNm, /* [상품명] */
        String pdQty /* [상품수량] */
    ) {}
}
