package com.kyowon.sms.wells.web.promotion.common.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WpmzPromotionInputDvo {
    private String pmotCd;              /* 프로모션코드       */
    private String basePdCd;            /* 상품코드          */
    private String basePdPrcDtlCd;      /* 상품가격상세코드    */
    private String lkPdClsfCd;          /* 연계상품분류(모종 상품분류코드)  */
    private String lkPdCd;              /* 연계상품코드(모종 상품코드)  */
    private String pkgMndtPdCd;         /* 패키지필수 상품코드  */
    private String chdvcPrmitYn;        /* 기기변경허용여부 */
    private String chdvcBfPdClsfCd;     /* 기기변경이전상품분류 */
    private String chdvcBfPdCd;         /* 기기변경이전상품코드 */
    private String lkChdvcPrmitYn;      /* 연계코드기변제외 */
    private String chdvcTpCd;           /* 기기변경유형 */
    private String oppstOrdRcptdt;      /* 기기변경이전상품접수일자 */
    private String oppstSlDt;           /* 기기변경이전상품매출일자 */
    private String alncBzsCd;           /* 제휴업체코드 */
    private String evCd;                /* 행사코드 */
    private String selrCd;              /* 판매자코드 */
    private String dupApyPsbYn;         /* 중복적용가능여부 */
    private String crpDscExcdYn;        /* 법인DC제외여부 */
    private String spcDscCd;            /* 특별할인코드 */

}
