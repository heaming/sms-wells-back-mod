package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaKMembersWellsOrdInqrDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaKMembersWellsOrdInqrDto-SearchRes")
    public record SearchRes(
        String cmnSfkVal,
        String cntrNo,
        String cntrSn,
        String basePdCd,
        String pdNm,
        String prchsPh,
        String sellTpCd,
        String sellTpDtlCd,
        String fnlAmt,
        String cntrPdStrtdt,
        String recapDutyPtrmN,
        String mmIstmAmt,
        String istRnadr,
        String istRdadr,
        String cntrDtlStat,
        String istDt,
        String rentalTn,
        String welsMnger,
        String welsMngerCralLocaraTno,
        String welsMngerMexnoEncr,
        String welsMngerCralIdvTno,
        String mpyMthdTp,
        String fnitNm,
        String acnoCrdcdEncr,
        String owrKnm,
        String mpyBsdt

    ) {}
}
