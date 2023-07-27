package com.kyowon.sms.wells.web.contract.changeorder.dvo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class WctbSellLimitProductOutDvo {
    private String rtnMessag;
    private String rtnFlag;
}
