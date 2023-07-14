package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncBsPeriodChartReqDvo {
    String cntrNo;
    String cntrSn;
    String dtlSn;
    String bfsvcSppStpRsonCd;
    String sellTpCd;
    String svPdCd;
    String pdctPdCd;
    String istDt;
    int chekInstMths;
    int chekInstMthsBs04;
    String cntrPdStrtdt;

    //additional parameter
    String chekCockYn;
    String chekVstGb20;
    String chekVstDt;
    String newWrkTypDtl;
    String vstGb;
    String partCd;
    String newPartCd;
    String newVstGb;
    int chekCyclMths;
    String chngStg;
    String itemKnd;
    int qty;
    String crtErrCn;
    String pgrmId;
    String vVs107CnclDt;
    String newGdsCd;
    String newPartList;
}
