package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractRestipulationCntrRegDvo {
    private String cntrNo;
    private Integer cntrSn;
    private String stplTn;
    private String stplTpCd;
    private String stplPtrmUnitCd;
    private String stplPtrm;
    private String stplStrtdt;
    private String stplEnddt;
    private String stplDscAmt;
    private String rstlStatCd;
    private String stplRcpDtm;
    private String rcpOgTpCd;
    private String rcpPrtnrNo;
    private String feeAckmtCt;
    private String feeAckmtBaseAmt;
    private String feeFxamYn;
    private String ackmtPerFrt;
    private String ackmtPerFamt;
    private String notyFwId;
    private String stplCnfmDtm;
    private String cnfmUsrId;
    private String cntrChFshDtm;
    private String stplCanDtm;
    private String stplCanUsrId;
    private String stplWdwlDtm;
    private String stplWdwlUsrId;
    private String stplWdwlCn;
    private String stplDscStrtdt;
    private String stplDscEnddt;
    private String stplDscAcuAmt;
    private String stplDscBorAmt;
    private String dtaDlYn;
}
