package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import org.springframework.util.ObjectUtils;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractRegDvo {
    String cntrNo;

    WctaContractRegStep1Dvo step1;
    WctaContractRegStep2Dvo step2;
    WctaContractRegStep3Dvo step3;
    WctaContractRegStep4Dvo step4;

    @Setter(AccessLevel.NONE)
    WctaContractBasDvo bas;
    @Setter(AccessLevel.NONE)
    List<WctaContractPrtnrRelDvo> prtnrRels;
    @Setter(AccessLevel.NONE)
    WctaContractAdrpcBasDvo adrpcBas;
    @Setter(AccessLevel.NONE)
    List<WctaContractDtlDvo> dtls;

    public void setBas(WctaContractBasDvo bas) {
        this.bas = ObjectUtils.isEmpty(bas) ? new WctaContractBasDvo() : bas;
    }

    public void setPrtnrRels(List<WctaContractPrtnrRelDvo> prtnrRels) {
        this.prtnrRels = ObjectUtils.isEmpty(prtnrRels) ? List.of(new WctaContractPrtnrRelDvo()) : prtnrRels;
    }

    public void setAdrpcBas(WctaContractAdrpcBasDvo adrpcBas) {
        this.adrpcBas = ObjectUtils.isEmpty(adrpcBas) ? new WctaContractAdrpcBasDvo() : adrpcBas;
    }

    public void setDtls(List<WctaContractDtlDvo> dtls) {
        this.dtls = ObjectUtils.isEmpty(dtls) ? List.of(new WctaContractDtlDvo()) : dtls;
    }
}
