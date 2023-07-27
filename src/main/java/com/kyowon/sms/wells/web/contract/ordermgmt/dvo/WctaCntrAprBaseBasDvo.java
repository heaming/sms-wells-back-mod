package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaCntrAprBaseBasDvo {
    private String checkType;
    private String cntrAprBaseId;
    private String cntrAprSellDvCd;
    private String cntrAprAkDvCd;
    private String cntrAprAkDvCdNm;
    private String cntrAprChnlDvVal;
    private String cntrAprIchrDvCd;
    private String ichrUsrId;
    private String psicNm;
    private String vlStrtDtm;
    private String vlEndDtm;

    private String dtaDlYn;
    private String notyFwOjYn;
}
