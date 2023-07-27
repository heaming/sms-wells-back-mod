package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractForRveAkDvo {
    String cntrCstNo;// 고객번호
    String cstStlmInMthCd;// 수납요청방식코드 (대면/비대면)
    String sellPrtnrNo;// 수납요청파트너번호
    String sellOgTpCd;// 수납요청파트너조직유형코드
    String copnDvCd;
    String bryyMmdd;
    String bzrno;
    String cstKnm;
}
