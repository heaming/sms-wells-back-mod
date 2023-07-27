package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDetailDepositIzDto {
    @Builder
    @ApiModel("WctaOrderDetailDepositIzDto-SearchRentalDepositIzRes")
    public record SearchRentalDepositIzRes(
        String rentalMsh1Cost, /* 렌탈,멤버십료1 */
        String rental1Ptrm, /* 렌탈기간1 */
        String rentalMsh2Cost, /* 렌탈,멤버십료2 */
        String rental2Ptrm, /* 렌탈기간2 */
        String spmtSlAmt, /* 추가매출 */
        String spmtDscAmt, /* 추가할인 */
        String slDt, /* 매출일자 */
        String slAmt, /* 매출금액 */
        String prpdAmt, /* 선수금액 */
        String ucAmt, /* 미수금액 */
        String dlqAmt, /* 연체금액 */
        String slStpAmt, /* 매출중지금액 */
        String etMshAmt, /* 예상멤버십 금액 */
        String prtnrKnm /* 판매자성명 */
    ) {}

    @ApiModel("WctaOrderDetailDepositIzDto-SearchMembershipDepositIzRes")
    public record SearchMembershipDepositIzRes(
        String rentalMsh1Cost, /* 렌탈,멤버십료1 */
        String rental1Ptrm, /* 렌탈기간1 */
        String rentalMsh2Cost, /* 렌탈,멤버십료2 */
        String rental2Ptrm, /* 렌탈기간2 */
        String spmtSlAmt, /* 추가매출 */
        String spmtDscAmt, /* 추가할인 */
        String slDt, /* 매출일자 */
        String slAmt, /* 매출금액 */
        String prpdAmt, /* 선수금액 */
        String ucAmt, /* 미수금액 */
        String dlqAmt, /* 연체금액 */
        String slStpAmt, /* 매출중지금액 */
        String etMshAmt, /* 예상멤버십 금액 */
        String prtnrKnm /* 판매자성명 */
    ) {}

    @ApiModel("WctaOrderDetailDepositIzDto-SearchSpayCntrtDepositIzRes")
    public record SearchSpayCntrtDepositIzRes(
        String sellAmt, /* 판매금액 */
        String mmIstmAmt, /* 월할부금 */
        String istmMcn, /* 할부개월 */
        String subscAmt, /* 청약금액 */
        String istmAmt, /* 할부금액 */
        String istmPcamAmt, /* 현금잔액 */
        String ucAmt, /* 미수금액 */
        String prtnrKnm /* 판매자성명 */
    ) {}

    @ApiModel("WctaOrderDetailDepositIzDto-SearchRegularShippingsDepositIzRes")
    public record SearchRegularShippingsDepositIzRes(
        String sellAmt, /* 판매금액 */
        String sellTam, /* 최종금액(판매총액) */
        String dscAmt, /* 할인금액 */
        String spmtDsc, /* 판매할인조정금액(추가할인) */
        String mmBilPyAmt, /* 월 청구(납입)금액 */
        String svPrd, /* 서비스주기 */
        String dlqAmt, /* 당월발생연체금액 */
        String ucAmt, /* 당월미수잔액 */
        String prpdAmt, /* 기말선수금 */
        String bilUc, /* 기말미수금액 */
        String pcsvSpmt, /* 택배추가 */
        String prtnrKnm /* 판매자성명 */
    ) {}

    @ApiModel("WctaOrderDetailDepositIzDto-SearchLendingLimitDepositIzRes")
    public record SearchLendingLimitDepositIzRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrCstNo, /* 고객번호 */
        String sellAmt, /* 판매금액 */
        String ucAmt, /* 미수금액 */
        String thmOcDlqAmt, /* 당월발생연체금액 */
        String refPdClsfVal /* 참조상품분류값 */
    ) {}

    @ApiModel("WctaOrderDetailDepositIzDto-SearchRes")
    public record SearchRes(
        List<WctaOrderDetailDepositIzDto.SearchRentalDepositIzRes> searchRentalDepositIzResList,
        List<WctaOrderDetailDepositIzDto.SearchMembershipDepositIzRes> searchMembershipDepositIzResList,
        List<WctaOrderDetailDepositIzDto.SearchSpayCntrtDepositIzRes> searchSpayCntrtDepositIzResList,
        List<WctaOrderDetailDepositIzDto.SearchRegularShippingsDepositIzRes> searchRegularShippingsDepositIzResList,
        List<WctaOrderDetailDepositIzDto.SearchLendingLimitDepositIzRes> searchLendingLimitDepositIzResList
    ) {}
}
