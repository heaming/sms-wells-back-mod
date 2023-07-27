package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaRentalProductPriceCalculationDvo {
    // INPUT
    private String pdCd; /* 상품코드 */
    private String sellTpCd; /* 판매유형코드 */
    private String sellChnlCd; /* 판매채널코드 */
    private String svPdCd; /* 서비스상품코드 */
    private String stplPrdCd; /* 약정주기코드 */
    private String rentalDscDvCd; /* 렌탈할인구분 */
    private String rentalDscTpCd; /* 렌탈할인유형 */
    private String rentalCrpDscrCd; /* 렌탈법인할인율코드 */
    private String rentalCombiDvCd; /* 렌탈결합구분 */
    private String cntrRcpdt; /* 계약접수일자 */

    // OUTPUT
    private String rnlFeeAmt; /* 월렌탈료 */
    private String svFeeAmt; /* 환경리스. 환경할부 시 사용*/
    private String ackmtAmt; /* 인정실적액*/
    private String vat; /* 부가가치세 */
    private String ackmtRt; /* 인정실적율 */
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String basePdCd; // 기준상품코드
    private String orgRtPrc; // 원복렌탈료
    private String currRtPrc; // 현재렌탈료

    // INPUT/OUTPUT
    private String rgstFee; /* 등록비 */
}
