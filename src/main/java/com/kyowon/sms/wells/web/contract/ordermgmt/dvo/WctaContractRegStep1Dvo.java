package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import com.kyowon.sms.common.web.closing.mileage.dvo.ZdceMileageRestAmountDvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractRegStep1Dvo {
    private WctaContractBasDvo bas;
    private WctaContractPrtnrRelDvo prtnr;
    private WctaContractPrtnrRelDvo prtnr7;
    private WctaContractCstRelDvo cntrt; /* 계약자 */
    private String lrnrCstNo; /* 학습자고객번호(list의 key로 활용) */
    private List<WctaContractCstRelDvo> lrnrs; /* 학습자목록 */
    private List<ZdceMileageRestAmountDvo> mlgs; /*마일리지*/
    private List<ResBznsPsbDtDvo> resBznsPsbDts; /* 당월 잔여영업가능일 */
    private List<PextCntrDvo> pextCntrs; /* 기존계약존재여부 */
    private List<PrrBizRgstPtrmDvo> prrBizRgstPtrms; /* 사전업무 등록기간 */
    private String isFmlPrtnrYn; /* 파트너가족여부 */
    private String pspcCstId; /* 가망고객ID */
    private String mshCntrNo; /* 멤버십 원계약 계약번호 */
    private Integer mshCntrSn; /* 멤버십 원계약 계약일련번호 */

    @Getter
    @Setter
    public static class ResBznsPsbDtDvo {
        String stnrdYr;
        String stnrdMn;
        String stnrdDy;
    }

    @Getter
    @Setter
    public static class PextCntrDvo {
        String cntrNo;
        String cntrTpCd;
        String cntrTempSaveDtm;
        String cntrRcpFshDtm;
        String prtnrNo;
        String prtnrKnm;
    }

    @Getter
    @Setter
    public static class PrrBizRgstPtrmDvo {
        String wkYm;
        String rcpStrtdt;
        String rcpEnddt;
        String cnfmStrtdt;
        String cnfmEnddt;
        String lrnStrtdt;
        String rgstDt;
        String rgstDtm;
        String rgr;
    }
}
