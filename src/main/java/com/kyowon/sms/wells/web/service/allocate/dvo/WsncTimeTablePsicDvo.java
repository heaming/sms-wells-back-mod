package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WsncTimeTablePsicDvo {
    String prtnrNo;//ac021EmpId;
    String sellDate; // ac221CfrmDt;
    String iscgubNm;
    String rolDvNm;
    String rolDvNm2;
    String sjHp1;
    @DBDecField
    String sjHp2;
    String sjHp3;
    String rpbLocaraCd; // ac146LocalGb;
    String ogNm; // ac125DeptNm;
    String ogId; // ac125DeptCd;
    String prtnrKnm; // ac021EmpNm;
    String prtnrKnm2; // ac021EmpNm1;
    String vstDowVal; // ac146VstCycl
    String degNm;
    String instCnt;
    String bsCnt;
    String asCnt;
    String satWrkYn; // ac146Sat13WrkYn;
    String dfYn; // co160OffdayGb;
    String dowDvCd; // co160Days;
    String fr2pLgldCd; // ac112AdmCd;
    String rstrCndtUseYn; // ac146TtbUse;
    String udsnUseYn; // ac146UaUse;
    String vstPos;
    String rsbDvCd; // ac025EmpOr;
    String amWrkCnt;
    String pmWrkCnt;
    String totalWrkCnt;
    String empPic;
    String locaraTno;
    @DBDecField
    String exnoEncr;
    String idvTno;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;

}
