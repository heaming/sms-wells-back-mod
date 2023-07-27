package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrChRcchStatChangeHistDvo {
    private String cntrChRcpId;
    private String histStrtDtm;
    private String histEndDtm;
    private String cntrChRcpDtm;
    private String cntrChTpCd;
    private String chRqrDvCd;
    private String chRqrNm;
    private String cstNo;
    private String cntrChAkCn;
    private String cntrChPrgsStatCd;
    private String cntrChPrgsMoCn;
    private String chRcstDvCd;
    private String chRcpUsrId;
    private String aprDtm;
    private String aprUsrId;
    private String cntrChFshDtm;
    private String bizTfId;
    private String dtaDlYn;
}
