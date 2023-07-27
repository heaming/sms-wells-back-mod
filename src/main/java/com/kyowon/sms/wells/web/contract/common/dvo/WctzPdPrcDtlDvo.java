package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * TB_PD_PRC_DTL 를 위한 DVO
 * </pre>
 *
 * @author GOAT
 * @since 2023-07-08
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class WctzPdPrcDtlDvo {
    private String pdPrcDtlId; /*상품가격상세ID*/
    private Integer verSn; /*버전일련번호*/
    private String histStrtDtm; /*이력시작일시*/
    private String histEndDtm; /*이력종료일시*/
    private String vlStrtDtm; /*유효시작일시*/
    private String vlEndDtm; /*유효종료일시*/
    private String pdCd; /*상품코드*/
    private String pdPrcId; /*상품가격ID*/
    private String prcMtrxFlorId; /*가격매트릭스층ID*/
    private Double basVal; /*기본값*/
    private Double minVal; /*최소값*/
    private Double maxVal; /*최대값*/
    private Long btcomTrdPrc; /*사간거래가격*/
    private String crncyDvCd; /*통화구분코드*/
    private String tempSaveYn; /*임시저장여부*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String svPdCd; /*서비스상품코드*/
    private String stplPrdCd; /*약정주기코드*/
    private Long ccamBasePrc; /*위약금기준가격*/
    private Long spayPrc; /*일시불가격*/
    private String basePdCd; /*기준상품코드*/
}
