package com.kyowon.sms.wells.web.service.allocate.dvo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableCntrDvo {
    String cntrNo;
    String cntrSn;
    String adrZip;
    String basePdCd;
    String basePdNm;
    String pdctPdCd;
    String pdctPdNm;
    String pdGrpCd;
    String pdGrpNm;
    String cntrDt;
    String copnDvCd; // 법인격구분코드 (1개인, 2법인)
}
