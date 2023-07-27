package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractExOjDtlDvo {
    private String exProcsId;
    private int exSn;
    private String vlStrtDtm;
    private String vlEndDtm;
    private String exProcsTpCd;
    private String exProcsChval;
    private String exProcsNuval;
    private String dtaDlYn;
    private String fstRgstDtm;
    private String fstRgstUsrId;
    private String fstRgstPrgId;
    private String fstRgstDeptId;
    private String fnlMdfcDtm;
    private String fnlMdfcUsrId;
    private String fnlMdfcPrgId;
    private String fnlMdfcDeptId;
    private String orglFnlMdfcDtm;

    public void setValsByExProcsCd(int exSn, String exProcsTpCd, String exProcsChval) {
        this.exSn = exSn;
        this.exProcsTpCd = exProcsTpCd;
        this.exProcsChval = exProcsChval;
    }
}
