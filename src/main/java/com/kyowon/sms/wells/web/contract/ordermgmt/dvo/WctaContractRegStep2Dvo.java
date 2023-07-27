package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Getter
@Setter
public class WctaContractRegStep2Dvo {
    WctaContractBasDvo bas;
    List<WctaContractDtlDvo> dtls;
    List<PdClsfDvo> pdClsf;
    List<PdDvo> products;

    @Getter
    @Setter
    @Builder
    public static class PdClsfDvo {
        String pdClsfId;
        String pdClsfNm;
        String pdClsfCd;
        List<PdDvo> products; /* wells는 server에서 상품 분류 */
    }

    @Getter
    @Setter
    public static class PdDvo {
        String pdClsf;
        String pdClsfNm;
        String mclsfVal;
        String lclsfVal;
        String pdCd;
        String pdNm;
        String pdQty;
        String pdHclsfId;
        String pdMclsfId;
        String pdLclsfId;
        String pdDclsfId;
        String pdAbbrNm;
        String cstBasePdAbbrNm;
        String pdEplCn;
        String sellTpCd;
        String sellTpNm;
        String sellTpDtlCd;
        String sellTpDtlNm;
        String pdTpCd;
        String pdTpDtlCd;
        String basePdChoYn;
        String crncyDvCd;
        String spaySellPsbYn;
        String vatTpCd;
        String vatTpNm;
        String pdctUprcUseYn;
        String fgptYn;
        String filtEnddt;
        String otscPdYn;
        String rfbshYn;
        String istPcsvTpCd;
        String mpactYn;
        String istBzsCd;
        String stocMngtOjYn;
        String pcsvBzsCd;
        String svAlncBzsCd;
        String ordPrmitYm;
        String bzsSppYn;
        String bfPdCd;
        String cntrPtrmDvCd;
        String dutyUsePtrmDvCd;
        String rglrSppCntrDvCd;
        String rglrSppDutyPtrmDvCd;
        String rglrSppPrmMcn;
        String rglrSppBilDvCd;
        String rglrSppConsTpcd;
        String rglrSppPdctConsQty;
        String pdChoLmYn;
        String pdChoQty;
        String rglrSppPrcDvCd;
        String rglrSppMchnTpCd;
        String laboEuYn;
        String chdvcPrmitYn;
        String fgptTpYn;
        String asRcpPrmitYn;
        String frisuAsPtrmN;
        String rntf;
        String ackmtPerfRt;
        String ackmtCt;
        String feeAmt;
        String hdCndtCd;
        String rdsYn;
        String redfYn;
        String anaFactVal1;
        String anaFactVal2;
        String pvdaYn;
        String hcrMshTpCd;
        String pdChip1;
        Long fnlAmt;
        Integer rntlMcn;
        List<String> mshPdCds;
        List<PdSdingCapsl> sdingCapsls;
    }

    @Getter
    @Setter
    public static class PdDetailDvo {
        String codeId;
        String codeName;
    }

    @Getter
    @Setter
    public static class PdAmtDvo {
        Long fnlAmt;
        Long vat;
        Long ackmtPerfAmt;
        BigDecimal ackmtPerfRt;
        Long cvtPerfAmt;
        String pdPrcFnlDtlId;
        String fxamFxrtDvCd;
        Integer verSn;
        Long ctrVal;
        String pdPrcId;
        boolean isExistAlncPds; // 제휴상품노출여부(금액조회 시 함께 판단)
        Integer recapMshPtrm;
        Integer mshStrtMcn;
        Integer mshEndMcn;
    }

    @Getter
    @Setter
    public static class PdSvcDvo {
        String codeId;
        String codeName;
        String pdNm;
        String pdCd;
        String pdHclsfId;
        String pdMclsfId;
        String pdAbbrNm;
        String svStrtdt;
        String svEnddt;
        String mndtSvYn;
        String svTpCd;
        String cstBasePdAbbrNm;
        String pdEplCn;
        String asPtrmCd;
        String svPrdUnitCd;
        String svVstPrdCd;
        String pcsvPrdCd;
    }

    @Getter
    @Setter
    public static class PdWelsfHcfPkg {
        String codeId;
        String codeName;
        String pdClsfNm;
        String pdCd;
        String pdNm;
        String pdChoLmYn;
        Long pdChoQty;
        Long cntrPtrm;
        String rglrSppPrcDvCd;
        String rglrSppMchnTpCd;
        String pdctUprcUseYn;
        Long fnlAmt;
        Long pdQty;
        String sellTpCd;
        String sellTpDtlCd;
        String pdHclsfId;
        String pdMclsfId;
        String pdLclsfId;
        String pdDclsfId;
        String crncyDvCd;
        BigDecimal ackmtPerfRt;
        Long ackmtPerfAmt;
        List<PdSdingCapsl> sdingCapsls;
        String cntrRelDtlCd;
        String pdChip1;
    }

    @Getter
    @Setter
    public static class PdSdingCapsl {
        String partPdNm;
        String svPdCd;
        String pdctPdCd;
        String dtlSn;
        String prdMmVal;
        String vstDvCd;
        String partPdCd;
        Long itmQty;
        String pdRelId;
        String pdRelTpCd;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdOpo {
        Boolean opoYn;
        String ojCntrNo;
        Integer ojCntrSn;
        String pdNm;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PdSltrRglrSppMchn {
        Boolean rglrSppMchnYn;
        String ojCntrNo;
        Integer ojCntrSn;
        String pdNm;
    }
}
