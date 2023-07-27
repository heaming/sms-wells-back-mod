package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDetailMngtInfDto {
    @Builder
    @ApiModel("WctaOrderDetailMngtInfDto-SearchRes")
    public record SearchRes(
        String cntrDt, /* 계약일 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String pdQty, /* 상품수량 */
        String istDt, /* 설치일자 */
        String sellTpNm, /* 판매유형코드명 */
        String dscDvNm, /* 할인구분 */
        String dscTpNm, /* 할인유형명 */
        String stplPtrm, /* 약정기간 */
        String prd, /* 주기 */
        String uswy, /* 용도 */
        String frisuAS, /* 무상AS */
        String sellPrtnrBlgCd, /* 판매자소속코드 */
        String sellPrtnrBlgNm, /* 판매자소속명 */
        String sellPrtnrBlgBrmgrNm, /* 판매자소속지점장명 */
        String sellPrtnrNm, /* 판매자성명 */
        String sellPrtnrNo, /* 판매파트너번호 */
        String chdvcDt, /* 기변일자 */
        String ptyCd, /* 상대코드 */
        String alncPtyCd, /* 제휴상태코드 */
        String etcArtc, /* 기타사항 */
        String chngDt, /* 교체일자 */
        String reqdDt, /* 철거일자 */
        String canDt, /* 취소일자 */
        String svStpDt, /* 서비스중지일자 */
        String mshJoinDt, /* 멤버십가입 */
        String mshWdwalDt, /* 멤버십탈퇴 */
        String ichrWelsMngerNm, /* 담당웰스매니저명 */
        String istMthCd, /* 설치방법코드 */
        String istMthNm, /* 설치방법명 */
        String dutyStplMcnt, /* 의무약정개월 */
        String rgstCs, /* 등록비용 */
        String rentalTam, /* 렌탈총액 */
        String ackmtPerfAmt /* 인정실적금액 */
    ) {}

    @ApiModel("WctaOrderDetailMngtInfDto-SearchCntrPmotRes")
    public record SearchCntrPmotRes(
        String pmotCd, /* 프로모션코드 */
        String pmotNm, /* 프로모션코드명 */
        String pmotTpCd, /* 프로모션유형코드 */
        String pmotTpNm /* 프로모션유형코드명 */
    ) {}

    @ApiModel("WctaOrderDetailMngtInfDto-SearchFgptCntrRes")
    public record SearchFgptCntrRes(
        String fgptPdCd, /* 사은품상품코드 */
        String pdNm, /* 상품명 */
        String fgptQty /* 사은품수량 */
    ) {}

    @ApiModel("WctaOrderDetailMngtInfDto-SearchPmotFgptRes")
    public record SearchPmotFgptRes(
        List<WctaOrderDetailMngtInfDto.SearchCntrPmotRes> searchCntrPmotResList,
        List<WctaOrderDetailMngtInfDto.SearchFgptCntrRes> searchFgptCntrResList
    ) {}
}
