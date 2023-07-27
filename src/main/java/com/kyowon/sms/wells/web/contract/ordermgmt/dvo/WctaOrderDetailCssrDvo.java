package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaOrderDetailCssrDvo {

    private String kwGrpCoCd; /* 그룹회사코드 */
    private String rveDt; /*수납일자*/
    private String pdNm; /*상품명*/
    private String cssrIsDvCd; /* 현금영수증발급구분코드 */
    private String cssrAgrgSn; /*현금영수증집계일련번호*/
    private String cssrTrdDvCd; /*공제구분*/
    private String cntrNo;
    private String cntrSn;
    private String cstNo; /* 고객번호 */

    /* 변경전 */
    private String cssrIsNo; /* 발행번호 */
    private String cssrDtlSn; /*현금영수증상세일련번호*/
    private String cntr; /*계약상세번호*/
    private String cstKnm; /*고객명*/
    private String bfchCssrTrdDvCd; /*변경전 공제구분*/
    private String bfchCssrIsNo; /*변경전 발행번호*/
    private String bfchCssrTrdAmt; /*변경전 승인금액*/
    private String bfchCssrAprRsCd; /*승인결과*/
    private String dpDvCd; /* 입금구분코드 */
    private String fstRgstDtm; /*등록일*/
    private String fstRgstUsrId; /*등록자*/

    /*변경후*/
    private String afchCssrIsDvCd; /* 변경후 현금영수증발급구분코드 */
    private String afchCssrTrdDvCd; /*공제구분*/
    private String afchCssrIsNo; /*발행번호*/
    private String afchCssrTrdAmt; /*승인금액*/
    private String afchCssrAprno; /*승인번호*/
    private String afchCssrAprRsCd; /*승인결과*/
    private String chRsonCn; /*변경사유*/
    private String fnlMdfcDtm; /*등록일*/
    private String fnlMdfcUsrId; /* 등록자 사번*/
    private String fnlMdfcUsrNm; /* 등록자 이름 */

}
