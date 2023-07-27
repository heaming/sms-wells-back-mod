package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzPdPrcFnlDtlDvo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaSpayFinalPriceDvo extends WctzPdPrcFnlDtlDvo {
    private Double basVal; /*기준가격*/
}
