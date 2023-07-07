package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableProductDvo {
    String basePdCd;
    String basePdNm;
    String pdctPdCd;
    String pdctPdNm;
    String sellTpDtlCd;
    String sellTpDtlNm;
    String rglrSppPrcDvCd;
    String rglrSppPrcDvNm;
    String pdctPdGrpCd;
    String pdctPdGrpNm;
    /*모종여부*/
    String sidingYn;
    String hcrYn;
    String hcr;
}
