package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDetailDepositRgstDto {
    @Builder
    @ApiModel(value = "WctaOrderDetailDepositRgstDto-SearchReq")
    public record SearchReq(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String rveDtFr, /* 입금일자(시작) */
        String rveDtTo /* 입금일자(종료) */
    ) {}

    @ApiModel("WctaOrderDetailDepositRgstDto-SearchRes")
    public record SearchRes(
        String rveDt, /* 수납일자[수납일] */
        String perfDt, /* 실적일자[실적일] */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String rveAmt, /* 금액 */
        String dpMesCd, /* 유형(입금수단코드) */
        String dpMesNm, /* 유형명(입금수단코드명) */
        String cdcoCd, /* 카드(은행사) 코드 */
        String cdcoNm, /* 카드(은행사) */
        String crcdnoEncr, /* 카드번호(가상계좌) */
        String crdcdIstmMcn, /* 할부개월 */
        String fnlMdfcPrgId, /* 모듈 */
        String stat /* 상태 */
    ) {}
}
