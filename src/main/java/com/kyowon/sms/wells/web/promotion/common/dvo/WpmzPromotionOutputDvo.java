package com.kyowon.sms.wells.web.promotion.common.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WpmzPromotionOutputDvo {
    private String pmotCd;              /* 프로모션코드      */
    private String dscMcnt;             /* 할인개월         */
    private String dscFxam;             /* 할인금액         */
    private String freeMcnt;            /* 무료개월         */
    private String rentalFxam;          /* 렌탈금액         */
    private String ackmtCt;             /* 인정건수 */
    private String ackmtAmt;            /* 인정금액 */
    private String ackmtRt;             /* 인정금액(P) */
    private String stdFeeAmt;           /* 기준수수료 */
    private String feeFxamYn;           /* 수수료정액여부 */
    private String fgptChoGrpCd;        /* 사은품선택그룹 */
    private String fgptChoCd;           /* 사은품선택 */
    private String prmDupPrmitYn;       /* 선납중복허용여부 */
    private String pdPrcDtlCd;          /* 상품가격상세코드 */
    private List<WpmzPromotionFreeGiftDvo> pmotFreeGifts;  /* 사은품목록 */


}