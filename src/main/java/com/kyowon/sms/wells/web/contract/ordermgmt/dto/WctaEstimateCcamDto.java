package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaEstimateCcamDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaEstimateCcamDto-SearchReq")
    public record SearchReq(
        String slClYm,
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaEstimateCcamDto-SearchRes")
    public record SearchRes(
        String prmTn, /* 선납회차 */
        String prmMcn, /* 선납개월수 */
        String prmDscr, /* 선납할인율 */
        String rentalAmt, /* 렌탈금액 */
        String rentalDscAmt, /* 렌탈할인금액 */
        String prmStrtYymm, /* 선납기간-시작년월 */
        String prmEndYymm, /* 선납기간-종료년월 */
        String prmDscAmt, /* 할인 총 금액(선납기간기준) */
        String totPrmAmt, /* 선납예상총금액 */
        String rentalTn, /* 렌탈회차 */
        String nomSlAmt, /* 정상매출금액 */
        String rentalDc, /* 렌탈일수 */
        String slDc, /* 매출일수 */
        String rplmDt, /* 교체일자 */
        String spmtSlAmt, /* 추가매출금액 */
        String nomDscAmt, /* 정상할인금액 */
        String spmtDscAmt, /* 추가할인금액 */
        String slCtrAmt, /* 매출조정금액 */
        String thmSlSumAmt, /* 당월매출합계금액 */
        String slSumVat, /* 매출합계부가가치세 */
        String slAggAmt, /* 매출누계금액 */
        String dscAggAmt, /* 할인누계 */
        String ctrAggAmt, /* 조정누계 */
        String thmUcBlam, /* 매출잔액 */
        String btdDlqAddAmt, /* 연체가산금 */
        String thmDlqAddDpSumAmt, /* 입 */
        String thmDlqAddRfndSumAmt, /* 출 */
        String thmCtrDlqAddAmt /* 조정 */
    ) {}
}
