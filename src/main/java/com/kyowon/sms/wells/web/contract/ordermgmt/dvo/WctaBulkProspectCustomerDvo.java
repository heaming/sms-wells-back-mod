package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzPspcCstBasDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzPspcCstCnslBasDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzPspcCstCnslRcmdIzDvo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WctaBulkProspectCustomerDvo {
    WctzPspcCstBasDvo wctzPspcCstBasDvo;
    WctzPspcCstCnslBasDvo wctzPspcCstCnslBasDvo;
    WctzPspcCstCnslRcmdIzDvo wctzPspcCstCnslRcmdIzDvo;

    public void setPspcCstId(String pspcCstId) {
        this.wctzPspcCstBasDvo.setPspcCstId(pspcCstId);
        this.wctzPspcCstCnslBasDvo.setPspcCstId(pspcCstId);
    }

    public void setPspcCstCnslId(String pspcCstCnslId) {
        this.wctzPspcCstCnslBasDvo.setPspcCstCnslId(pspcCstCnslId);
        this.wctzPspcCstCnslRcmdIzDvo.setPspcCstCnslId(pspcCstCnslId);
    }

    public void setPspcCstCnslSn(int PspcCstCnslSn) {
        this.wctzPspcCstCnslRcmdIzDvo.setPspcCstCnslSn(PspcCstCnslSn);
    }
}
