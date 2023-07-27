package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractRegStep4Dvo {
    private String isUseAttach;
    private WctaContractBasDvo bas;
    private List<WctaContractDtlDvo> dtls;
    private WctaContractCstRelDvo cntrt;
    private WctaContractPrtnrRelDvo prtnr;
    private WctaContractPrtnrRelDvo prtnr7;
    private List<CntrDtlDvo> cntrDtls; /* 계약상세정보 */
    private List<StlmDtlDvo> stlmDtls; /* 결제상세정보 */

    @Getter
    @Setter
    public static class CntrDtlDvo {
        String cntrNo;
        String cntrSn;
        String sellTpNm;
        String pdNm;
        Long regAmt;
        Long rntlAmt;
        Long pdAmt;
        Long stplPtrm;
        Long cntrPtrm;
        Long dscAmt;
    }

    @Getter
    @Setter
    public static class StlmDtlDvo {
        String stlmTp;
        String stlmMth;
        BigDecimal stlmAmt;
    }
}
