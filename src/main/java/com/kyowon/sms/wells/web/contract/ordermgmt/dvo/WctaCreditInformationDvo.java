package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctaCreditInformationDvo {
    //request parameter
    private String inqrDiv; /* (필수)조회구분(1.14일이내건,2.무조건 조회) */
    private String safeKey; /* (필수)세이프키 */
    private String inqrUswy; /* (필수)조회용도(AS-IS KSUCDE) */
    private String coCd; /* 회사코드 */
    private String sellerEpNo; /* 판매자사번 */
    private String dsmnEpNo; /* 지국장사번 */
    private String sellDvCd; /* 판매구분(1.EDU, 2.wells) */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cstDvCd; /* 개인/법인구분(1.개인, 2.법인) */

    //etc
    private String ksiseq;
    private String ksqymd;
}
