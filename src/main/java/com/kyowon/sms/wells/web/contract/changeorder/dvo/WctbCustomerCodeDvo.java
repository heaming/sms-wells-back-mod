package com.kyowon.sms.wells.web.contract.changeorder.dvo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbCustomerCodeDvo {
    private String cstKnm; //  고객명
    private String sellTpCd; // 판매유형코드(1. 일시불/할부  2 렌탈/리스  3 멤버십 4 회사설치)
    private String mshBfSellTpCd; // 멤버십이전판매유형코드(1.일시불, 2.렌탈 등)
}
