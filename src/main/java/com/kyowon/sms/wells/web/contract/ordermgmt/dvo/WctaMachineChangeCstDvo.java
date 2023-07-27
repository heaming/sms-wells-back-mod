package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaMachineChangeCstDvo {
    private String adr; //주소 (기본주소+상세주소)
    private String sellTpCd; //판매유형코드
    private String cstKnm; //계약고객명
    private String istDt; // 설치일자
    private String reqdDt; // 취소일자(철거일자)
}
