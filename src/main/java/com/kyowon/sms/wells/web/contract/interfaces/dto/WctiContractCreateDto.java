package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiContractCreateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // KMEMBERS 계약생성 Request Dto
    @Builder
    @ApiModel("WctiContractCreateDto-CreateKmembersReq")
    public record CreateKmembersReq(
        @JsonProperty("USE_CLS")
        String useCls,
        @JsonProperty("IN_CLS")
        String inCls,
        @JsonProperty("PD_CLS")
        String pdCls,
        @JsonProperty("CNTR_DT")
        String cntrDt,
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @NotBlank
        @JsonProperty("CST_KNM")
        String cstKnm,
        @NotBlank
        @JsonProperty("CNTRT_CRAL_LOCARA_TNO")
        String cntrtCralLocaraTno,
        @NotBlank
        @JsonProperty("CNTRT_MEXNO_ENCR")
        String cntrtMexnoEncr,
        @NotBlank
        @JsonProperty("CNTRT_CRAL_IDV_TNO")
        String cntrtCralIdvTno,
        @JsonProperty("CNTRT_LOCARA_TNO")
        String cntrtLocaraTno,
        @JsonProperty("CNTRT_EXNO_ENCR")
        String cntrtExnoEncr,
        @JsonProperty("CNTRT_IDV_TNO")
        String cntrtIdvTno,
        @JsonProperty("CNTRT_ZIP")
        String cntrtZip,
        @JsonProperty("CNTRT_ADR_DV_CD")
        String cntrtAdrDvCd,
        @JsonProperty("CNTRT_BAS_ADR")
        String cntrtBasAdr,
        @JsonProperty("CNTRT_DTL_ADR")
        String cntrtDtlAdr,
        @NotBlank
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo,
        @NotBlank
        @JsonProperty("ISTLL_KNM")
        String istllKnm,
        @NotBlank
        @JsonProperty("IST_CST_CRAL_LOCARA_TNO")
        String istCstCralLocaraTno,
        @NotBlank
        @JsonProperty("IST_CST_MEXNO_ENCR")
        String istCstMexnoEncr,
        @NotBlank
        @JsonProperty("IST_CST_CRAL_IDV_TNO")
        String istCstCralIdvTno,
        @JsonProperty("IST_PLC_LOCARA_TNO")
        String istPlcLocaraTno,
        @JsonProperty("IST_PLC_EXNO_ENCR")
        String istPlcExnoEncr,
        @JsonProperty("IST_PLC_IDV_TNO")
        String istPlcIdvTno,
        @NotBlank
        @JsonProperty("IST_ZIP")
        String istZip,
        @JsonProperty("IST_ADR_DV_CD")
        String istAdrDvCd,
        @NotBlank
        @JsonProperty("IST_BAS_ADR")
        String istBasAdr,
        @NotBlank
        @JsonProperty("IST_DTL_ADR")
        String istDtlAdr,
        @JsonProperty("TXINV")
        String txinv,
        @NotBlank
        @JsonProperty("PD_CD01")
        String pdCd01,
        @NotBlank
        @JsonProperty("PD_QTY01")
        String pdQty01,
        @JsonProperty("AMT01")
        String amt01,
        @JsonProperty("PD_CD02")
        String pdCd02,
        @JsonProperty("PD_QTY02")
        String pdQty02,
        @JsonProperty("AMT02")
        String amt02,
        @JsonProperty("PD_CD03")
        String pdCd03,
        @JsonProperty("PD_QTY03")
        String pdQty03,
        @JsonProperty("AMT03")
        String amt03,
        @JsonProperty("PD_CD04")
        String pdCd04,
        @JsonProperty("PD_QTY04")
        String pdQty04,
        @JsonProperty("AMT04")
        String amt04,
        @JsonProperty("PD_CD05")
        String pdCd05,
        @JsonProperty("PD_QTY05")
        String pdQty05,
        @JsonProperty("AMT05")
        String amt05,
        @JsonProperty("PD_CD06")
        String pdCd06,
        @JsonProperty("PD_QTY06")
        String pdQty06,
        @JsonProperty("AMT06")
        String amt06,
        @JsonProperty("PD_CD07")
        String pdCd07,
        @JsonProperty("PD_QTY07")
        String pdQty07,
        @JsonProperty("AMT07")
        String amt07,
        @JsonProperty("PD_CD08")
        String pdCd08,
        @JsonProperty("PD_QTY08")
        String pdQty08,
        @JsonProperty("AMT08")
        String amt08,
        @JsonProperty("PD_CD09")
        String pdCd09,
        @JsonProperty("PD_QTY09")
        String pdQty09,
        @JsonProperty("AMT09")
        String amt09,
        @JsonProperty("PD_CD10")
        String pdCd10,
        @JsonProperty("PD_QTY10")
        String pdQty10,
        @JsonProperty("AMT10")
        String amt10,
        @JsonProperty("SELL_TAM")
        String sellTam,
        @JsonProperty("SELL_AMT")
        String sellAmt,
        @JsonProperty("VAT")
        String vat,
        @JsonProperty("CPRCNF_PROCS_YN")
        String cprcnfProcsYn,
        @NotBlank
        @JsonProperty("SUBSC_AMT1")
        String subscAmt1,
        @NotBlank
        @JsonProperty("DP_DV_CD1")
        String dpDvCd1,
        @JsonProperty("CDNO1")
        String cdno1,
        @JsonProperty("CRDCD_ISTM_MCN1")
        String crdcdIstmMcn1,
        @JsonProperty("CARD_APRNO1")
        String cardAprno1,
        @JsonProperty("CDONR_NM1")
        String cdonrNm1,
        @JsonProperty("CPRCNF_PROCS_YN1")
        String cprcnfProcsYn1,
        @JsonProperty("CPRCNF_NO1")
        String cprcnfNo1,
        @JsonProperty("SUBSC_AMT2")
        String subscAmt2,
        @JsonProperty("DP_DV_CD2")
        String dpDvCd2,
        @JsonProperty("CDNO2")
        String cdno2,
        @JsonProperty("CRDCD_ISTM_MCN2")
        String crdcdIstmMcn2,
        @JsonProperty("CARD_APRNO2")
        String cardAprno2,
        @JsonProperty("CDONR_NM2")
        String cdonrNm2,
        @JsonProperty("CPRCNF_PROCS_YN2")
        String cprcnfProcsYn2,
        @JsonProperty("CPRCNF_NO2")
        String cprcnfNo2,
        @JsonProperty("SUBSC_AMT3")
        String subscAmt3,
        @JsonProperty("DP_DV_CD3")
        String dpDvCd3,
        @JsonProperty("CDNO3")
        String cdno3,
        @JsonProperty("CRDCD_ISTM_MCN3")
        String crdcdIstmMcn3,
        @JsonProperty("CARD_APRNO3")
        String cardAprno3,
        @JsonProperty("CDONR_NM3")
        String cdonrNm3,
        @JsonProperty("CPRCNF_PROCS_YN3")
        String cprcnfProcsYn3,
        @JsonProperty("CPRCNF_NO3")
        String cprcnfNo3,
        @JsonProperty("CTF_NO")
        String ctfNo,
        @JsonProperty("CTF_DV")
        String ctfDv,
        @JsonProperty("ACAM_CPRCNF_CNT")
        String acamCprcnfCnt,
        @JsonProperty("CHECK_YN")
        String checkYn,
        @JsonProperty("ENCR_OJ_YN")
        String encrOjYn,
        @JsonProperty("MNCO_CO")
        String mncoCo,
        @JsonProperty("PD_DV1")
        String pdDv1,
        @JsonProperty("PD_DV2")
        String pdDv2,
        @NotBlank
        @JsonProperty("DSC_DV")
        String dscDv,
        @JsonProperty("DSC_TP")
        String dscTp,
        @JsonProperty("MNGT_PRD")
        String mngtPrd,
        @NotBlank
        @JsonProperty("USWY")
        String uswy,
        @JsonProperty("SFK")
        String sfk,
        @JsonProperty("DCDE")
        String dcde,
        @JsonProperty("MSH_FNT_DV")
        String mshFntDv,
        @JsonProperty("BNK_CD")
        String bnkCd,
        @JsonProperty("ACNO")
        String acno,
        @JsonProperty("FNT_DT")
        String fntDt,
        @JsonProperty("ACHLDR_NM")
        String achldrNm,
        @JsonProperty("CINO")
        String cino,
        @JsonProperty("CDCO_CD")
        String cdcoCd,
        @JsonProperty("CDNO")
        String cdno,
        @JsonProperty("CARD_EXPDT_YM")
        String cardExpdtYm,
        @JsonProperty("DUEDT")
        String duedt,
        @JsonProperty("P_DV")
        String pDv,
        @JsonProperty("SELL_DV_CD")
        String sellDvCd,
        @JsonProperty("SELR_BLG_DEPT")
        String selrBlgDept,
        @JsonProperty("BIZ_PSIC_ID")
        String bizPsicId,
        @JsonProperty("ALNC_CDCO")
        String alncCdco,
        @JsonProperty("AG1")
        String ag1,
        @JsonProperty("AG2")
        String ag2,
        @JsonProperty("AG3")
        String ag3,
        @JsonProperty("AG4")
        String ag4,
        @JsonProperty("AG5")
        String ag5,
        @JsonProperty("CST_EMADR")
        String cstEmadr,
        @JsonProperty("FGPT_CHO")
        String fgptCho,
        @JsonProperty("ALNC_AC_CN")
        String alncAcCn,
        @JsonProperty("ALNC_CNTR_NO1")
        String alncCntrNo1,
        @JsonProperty("ALNC_CNTR_SN1")
        String alncCntrSn1,
        @JsonProperty("ALNC_CNTR_NO2")
        String alncCntrNo2,
        @JsonProperty("ALNC_CNTR_SN2")
        String alncCntrSn2,
        @JsonProperty("COMBI_DV")
        String combiDv,
        @JsonProperty("COMBI_CNTR_NO")
        String combiCntrNo,
        @JsonProperty("COMBI_CNTR_SN")
        String combiCntrSn,
        @JsonProperty("FTF_YN")
        String ftfYn,
        @JsonProperty("BLG_DV")
        String blgDv,
        @JsonProperty("EMP_PRCH")
        String empPrch,
        @JsonProperty("FRISU_PRD")
        String frisuPrd,
        @JsonProperty("MCHN_CH")
        String mchnCh,
        @JsonProperty("EV_CD")
        String evCd,
        @JsonProperty("IST_OPT")
        String istOpt,
        @JsonProperty("CNTRT_REL_CD")
        String cntrtRelCd,
        @JsonProperty("PIF_THP_OFR_AG_YN")
        String pifThpOfrAgYn,
        @JsonProperty("MKTG_UTLZ_AG_YN")
        String mktgUtlzAgYn,
        @JsonProperty("MSH_PTRM_DV")
        String mshPtrmDv,
        @JsonProperty("CST_AK_ARTC")
        String cstAkArtc
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 고객 통합 리스트 Search Result Dto
    public record CreateKmembersRes(
        @JsonProperty("PRCS_RSLT")
        String prcsRslt,
        @JsonProperty("MSG")
        String msg,
        @JsonProperty("DTL_MSG")
        String dtlMsg
    ) {}
}
