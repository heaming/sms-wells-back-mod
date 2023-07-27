package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiContractCreateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 일시불 계약생성 Request Dto
    @Builder
    @ApiModel("WctiContractCreateDto-CreateSinglePaymentReq")
    public record CreateSinglePaymentReq(
        @NotBlank
        @JsonProperty("RCP_CHNL_DTL")
        String rcpChnlDtl,
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

    @Builder
    @ApiModel("WctiContractCreateDto-CreateRentalReq")
    public record CreateRentalReq(
        @NotBlank
        @JsonProperty("RCP_CHNL_DTL")
        String rcpChnlDtl,
        @JsonProperty("IN_DV")
        String inDv,
        @JsonProperty("IN_DV_CD")
        String inDvCd,
        @JsonProperty("RCPDT")
        String rcpdt,
        @JsonProperty("RCPTM")
        String rcptm,
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @JsonProperty("CST_NM")
        String cstNm,
        @JsonProperty("CST_SEX")
        String cstSex,
        @JsonProperty("CST_BRYMD")
        String cstBrymd,
        @JsonProperty("CPHON_LOCARA_TNO")
        String cphonLocaraTno,
        @JsonProperty("CPHON_EXNO_ENCR")
        String cphonExnoEncr,
        @JsonProperty("CPHON_IDV_TNO")
        String cphonIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO_ENCR")
        String exnoEncr,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("ZIP")
        String zip,
        @JsonProperty("ADR_DV_CD")
        String adrDvCd,
        @JsonProperty("BAS_ADR")
        String basAdr,
        @JsonProperty("DTL_ADR")
        String dtlAdr,
        @JsonProperty("CST_NO")
        String cstNo,
        @JsonProperty("CST_EMADR")
        String cstEmadr,
        @JsonProperty("IST_CST_NM")
        String istCstNm,
        @JsonProperty("IST_CPHON_LOCARA_TNO")
        String istCphonLocaraTno,
        @JsonProperty("IST_CPHON_EXNO_ENCR")
        String istCphonExnoEncr,
        @JsonProperty("IST_CPHON_IDV_TNO")
        String istCphonIdvTno,
        @JsonProperty("IST_LOCARA_TNO")
        String istLocaraTno,
        @JsonProperty("IST_EXNO_ENCR")
        String istExnoEncr,
        @JsonProperty("IST_IDV_TNO")
        String istIdvTno,
        @JsonProperty("IST_ZIP")
        String istZip,
        @JsonProperty("IST_ADR_DV_CD")
        String istAdrDvCd,
        @JsonProperty("IST_BAS_ADR")
        String istBasAdr,
        @JsonProperty("IST_DTL_ADR")
        String istDtlAdr,
        @JsonProperty("IST_PLC")
        String istPlc,
        @JsonProperty("URGT_IST")
        String urgtIst,
        @JsonProperty("PD_CD")
        String pdCd,
        @JsonProperty("PD_QTY")
        String pdQty,
        @JsonProperty("PD_CD01")
        String pdCd01,
        @JsonProperty("PD_QTY01")
        String pdQty01,
        @JsonProperty("PD_CD02")
        String pdCd02,
        @JsonProperty("PD_QTY02")
        String pdQty02,
        @JsonProperty("PD_CD03")
        String pdCd03,
        @JsonProperty("PD_QTY03")
        String pdQty03,
        @JsonProperty("PD_CD04")
        String pdCd04,
        @JsonProperty("PD_QTY04")
        String pdQty04,
        @JsonProperty("PD_CD05")
        String pdCd05,
        @JsonProperty("PD_QTY05")
        String pdQty05,
        @JsonProperty("PD_CD06")
        String pdCd06,
        @JsonProperty("PD_QTY06")
        String pdQty06,
        @JsonProperty("VAT")
        String vat,
        @JsonProperty("PRESL_CARD1")
        String preslCard1,
        @JsonProperty("BNK_CDCO_DV1")
        String bnkCdcoDv1,
        @JsonProperty("CARD_AMT1")
        String cardAmt1,
        @JsonProperty("CRCDNO_ENCR1")
        String crcdnoEncr1,
        @JsonProperty("CARD_ISTM_MCN1")
        String cardIstmMcn1,
        @JsonProperty("CARD_APRNO1")
        String cardAprno1,
        @JsonProperty("CDONR_NM1")
        String cdonrNm1,
        @JsonProperty("CPRCNF_YN1")
        String cprcnfYn1,
        @JsonProperty("DP_CPRCNF_NO1")
        String dpCprcnfNo1,
        @JsonProperty("PRESL_CARD2")
        String preslCard2,
        @JsonProperty("BNK_CDCO_DV2")
        String bnkCdcoDv2,
        @JsonProperty("CARD_AMT2")
        String cardAmt2,
        @JsonProperty("CRCDNO_ENCR2")
        String crcdnoEncr2,
        @JsonProperty("CARD_ISTM_MCN2")
        String cardIstmMcn2,
        @JsonProperty("CARD_APRNO2")
        String cardAprno2,
        @JsonProperty("CDONR_NM2")
        String cdonrNm2,
        @JsonProperty("CPRCNF_YN2")
        String cprcnfYn2,
        @JsonProperty("DP_CPRCNF_NO2")
        String dpCprcnfNo2,
        @JsonProperty("PRESL_CARD3")
        String preslCard3,
        @JsonProperty("BNK_CDCO_DV3")
        String bnkCdcoDv3,
        @JsonProperty("CARD_AMT3")
        String cardAmt3,
        @JsonProperty("CRCDNO_ENCR3")
        String crcdnoEncr3,
        @JsonProperty("CARD_ISTM_MCN3")
        String cardIstmMcn3,
        @JsonProperty("CARD_APRNO3")
        String cardAprno3,
        @JsonProperty("CDONR_NM3")
        String cdonrNm3,
        @JsonProperty("CPRCNF_YN3")
        String cprcnfYn3,
        @JsonProperty("DP_CPRCNF_NO3")
        String dpCprcnfNo3,
        @JsonProperty("CTF_CHG_YN")
        String ctfChgYn,
        @JsonProperty("CTF_NO")
        String ctfNo,
        @JsonProperty("CTF_DV_CD")
        String ctfDvCd,
        @JsonProperty("DSC_DV_CD")
        String dscDvCd,
        @JsonProperty("DSC_TP_CD")
        String dscTpCd,
        @JsonProperty("VST_PRD_CD")
        String vstPrdCd,
        @JsonProperty("USWY_CD")
        String uswyCd,
        @JsonProperty("SFK_RNW_YN")
        String sfkRnwYn,
        @JsonProperty("SFK")
        String sfk,
        @JsonProperty("SELL_DV_CD")
        String sellDvCd,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("PRTNR_OG_CD")
        String prtnrOgCd,
        @JsonProperty("RSDT_BZR_NO")
        String rsdtBzrNo,
        @JsonProperty("PNTML_RV_AMT")
        String pntmlRvAmt,
        @JsonProperty("BLG_DV")
        String blgDv,
        @JsonProperty("JOB_PSIC_EMPNO")
        String jobPsicEmpno,
        @JsonProperty("PIF_CLCN_U_AG_YN")
        String pifClcnUAgYn,
        @JsonProperty("PIF_THP_OFR_AG_YN")
        String pifThpOfrAgYn,
        @JsonProperty("MRKT_UTLZ_AG_YN")
        String mrktUtlzAgYn,
        @JsonProperty("FSTR_AG_YN")
        String fstrAgYn,
        @JsonProperty("PIF_BIZ_FSTR_AG_YN")
        String pifBizFstrAgYn,
        @JsonProperty("ITG_PIF_THP_OFR_AG_YN")
        String itgPifThpOfrAgYn,
        @JsonProperty("ITG_MRKT_UTLZ_AG_YN")
        String itgMrktUtlzAgYn,
        @JsonProperty("LIFE_MRKT_UTLZ_AG_YN")
        String lifeMrktUtlzAgYn,
        @JsonProperty("CPRTNCO_SV_AG_YN1")
        String cprtncoSvAgYn1,
        @JsonProperty("CPRTNCO_THP_OFR_AG_YN1")
        String cprtncoThpOfrAgYn1,
        @JsonProperty("CPRTNCO_SV_AG_YN2")
        String cprtncoSvAgYn2,
        @JsonProperty("CPRTNCO_THP_OFR_AG_YN2")
        String cprtncoThpOfrAgYn2,
        @JsonProperty("FGPT_CHO")
        String fgptCho,
        @JsonProperty("FGPT_DV_CD")
        String fgptDvCd,
        @JsonProperty("ALNC_DV_CD")
        String alncDvCd,
        @JsonProperty("ALNC_FSTR_BZS")
        String alncFstrBzs,
        @JsonProperty("ALNC_BZS_CD")
        String alncBzsCd,
        @JsonProperty("ALNC_PRTNR_NO")
        String alncPrtnrNo,
        @JsonProperty("ALNC_PRTNR_NM")
        String alncPrtnrNm,
        @JsonProperty("ALNC_CNTR_NO")
        String alncCntrNo,
        @JsonProperty("ALNC_CNTR_SN")
        String alncCntrSn,
        @JsonProperty("ALNC_SBJ_NO")
        String alncSbjNo,
        @JsonProperty("COMBI_YN")
        String combiYn,
        @JsonProperty("COMBI_CNTR_NO")
        String combiCntrNo,
        @JsonProperty("COMBI_CNTR_SN")
        String combiCntrSn,
        @JsonProperty("LIFE_CTFC_YN")
        String lifeCtfcYn,
        @JsonProperty("LIFE_CNTR_NO")
        String lifeCntrNo,
        @JsonProperty("LIFE_CNTR_SN")
        String lifeCntrSn,
        @JsonProperty("LIFE_CNTR_NO2")
        String lifeCntrNo2,
        @JsonProperty("LIFE_CNTR_SN2")
        String lifeCntrSn2,
        @JsonProperty("FTF_YN")
        String ftfYn,
        @JsonProperty("EMP_PRCHS_CD")
        String empPrchsCd,
        @JsonProperty("FRISU_AS_PTRM")
        String frisuAsPtrm,
        @JsonProperty("DSC_MCN")
        String dscMcn,
        @JsonProperty("FRE_MCN")
        String freMcn,
        @JsonProperty("MCHN_CH_YN")
        String mchnChYn,
        @JsonProperty("CHDVC_PFR")
        String chdvcPfr,
        @JsonProperty("MM_APY_DV_CD")
        String mmApyDvCd,
        @JsonProperty("CLN_DV")
        String clnDv,
        @JsonProperty("APYR_ITG")
        String apyrItg,
        @JsonProperty("BF_CNTR_NO1")
        String bfCntrNo1,
        @JsonProperty("BF_CNTR_SN1")
        String bfCntrSn1,
        @JsonProperty("BF_CNTR_NO2")
        String bfCntrNo2,
        @JsonProperty("BF_CNTR_SN2")
        String bfCntrSn2,
        @JsonProperty("SELL_EV_CD")
        String sellEvCd,
        @JsonProperty("IST_OPT")
        String istOpt,
        @JsonProperty("CNTRT_REL")
        String cntrtRel,
        @JsonProperty("CST_CLS_CD")
        String cstClsCd,
        @JsonProperty("GRP_DV_CD")
        String grpDvCd,
        @JsonProperty("AFTN_DV_CD")
        String aftnDvCd,
        @JsonProperty("CTT_CD")
        String cttCd,
        @JsonProperty("RSTL_YN")
        String rstlYn,
        @JsonProperty("FEE_FXAM_YN")
        String feeFxamYn,
        @JsonProperty("FEE_BASE_AMT")
        String feeBaseAmt,
        @JsonProperty("FEE_ACKMT_CT")
        String feeAckmtCt,
        @JsonProperty("ACKMT_PERF_RT")
        String ackmtPerfRt,
        @JsonProperty("ACKMT_PERF_AMT")
        String ackmtPerfAmt,
        @JsonProperty("SV_CHRAM")
        String svChram,
        @JsonProperty("DSC_AMT")
        String dscAmt,
        @JsonProperty("RTLFE1")
        String rtlfe1,
        @JsonProperty("RTLFE2")
        String rtlfe2,
        @JsonProperty("RENTAL_DSC_AMT1")
        String rentalDscAmt1,
        @JsonProperty("RENTAL_DSC_AMT2")
        String rentalDscAmt2,
        @JsonProperty("RENTAL_DSC_AMT5")
        String rentalDscAmt5,
        @JsonProperty("ISTM_PCAM_AMT")
        String istmPcamAmt,
        @JsonProperty("SCON_CN")
        String sconCn,
        @JsonProperty("FML_MBR_CT")
        String fmlMbrCt,
        @JsonProperty("OCO_CPS_BZS")
        String ocoCpsBzs,
        @JsonProperty("DUTY_USE")
        String dutyUse,
        @JsonProperty("IST_DV_CD")
        String istDvCd,
        @JsonProperty("IST_RQDT")
        String istRqdt,
        @JsonProperty("BOO_SELL_YN")
        String booSellYn,
        @JsonProperty("PWSUP_EYN")
        String pwsupEyn,
        @JsonProperty("USE_ELECT_TP_CD")
        String useElectTpCd,
        @JsonProperty("WPRS_EYN")
        String wprsEyn,
        @JsonProperty("WTQLTY_DV_CD")
        String wtqltyDvCd,
        @JsonProperty("WTQLTY_TST_YN")
        String wtqltyTstYn,
        @JsonProperty("IST_MM_EXMP_YN")
        String istMmExmpYn,
        @JsonProperty("PRM_DUP_PRMIT")
        String prmDupPrmit,
        @JsonProperty("PRM_YN")
        String prmYn,
        @JsonProperty("PRM_APY_DV_CD")
        String prmApyDvCd,
        @JsonProperty("PRM_MCN")
        String prmMcn,
        @JsonProperty("PRM_DSCR")
        String prmDscr,
        @JsonProperty("PRM_DSC_AMT")
        String prmDscAmt,
        @JsonProperty("CUST_CLS_CD1")
        String custClsCd1,
        @JsonProperty("SELL_TP_CD1")
        String sellTpCd1,
        @JsonProperty("PD_CD1")
        String pdCd1,
        @JsonProperty("TP_CD1")
        String tpCd1,
        @JsonProperty("APYR1")
        String apyr1,
        @JsonProperty("CUST_CLS_CD2")
        String custClsCd2,
        @JsonProperty("SELL_TP_CD2")
        String sellTpCd2,
        @JsonProperty("PD_CD2")
        String pdCd2,
        @JsonProperty("TP_CD2")
        String tpCd2,
        @JsonProperty("APYR2")
        String apyr2,
        @JsonProperty("PMOT_CD")
        String pmotCd,
        @JsonProperty("PMOT_SEQN")
        String pmotSeqn,
        @JsonProperty("PMOT_TP_CD")
        String pmotTpCd,
        @JsonProperty("PMOT_DTL_TP_CD")
        String pmotDtlTpCd,
        @JsonProperty("CUBIC_LKKEY")
        String cubicLkkey,
        @JsonProperty("USER_DV")
        String userDv,
        @JsonProperty("RGST_USR_ID")
        String rgstUsrId,
        @JsonProperty("PKG_SN")
        String pkgSn,
        @JsonProperty("PKG_PD_CD")
        String pkgPdCd,
        @JsonProperty("TXINV_PBL_OJ_YN")
        String txinvPblOjYn,
        @JsonProperty("TXINV_BZRNO")
        String txinvBzrno,
        @JsonProperty("TXINV_PSIC_NM")
        String txinvPsicNm,
        @JsonProperty("TXINV_LOCARA_TNO")
        String txinvLocaraTno,
        @JsonProperty("TXINV_EXNO_ENCR")
        String txinvExnoEncr,
        @JsonProperty("TXINV_IDV_TNO")
        String txinvIdvTno,
        @JsonProperty("TXINV_EMADR")
        String txinvEmadr,
        @JsonProperty("TXINV_CPHON_LOCARA_TNO")
        String txinvCphonLocaraTno,
        @JsonProperty("TXINV_CPHON_EXNO_ENCR")
        String txinvCphonExnoEncr,
        @JsonProperty("TXINV_CPHON_IDV_TNO")
        String txinvCphonIdvTno,
        @JsonProperty("TXINV_PBL_D")
        String txinvPblD,
        @JsonProperty("TXINV_RMK_CN")
        String txinvRmkCn,
        @JsonProperty("KUMON_HDDM_YN")
        String kumonHddmYn,
        @JsonProperty("PST_FW")
        String pstFw,
        @JsonProperty("FRE_EXPN_YN")
        String freExpnYn,
        @JsonProperty("FRE_EXPN_CNFM_YN")
        String freExpnCnfmYn,
        @JsonProperty("FRE_EXPN_CNFM_DTM")
        String freExpnCnfmDtm,
        @JsonProperty("FRE_EXPN_CNTR_NO")
        String freExpnCntrNo,
        @JsonProperty("FRE_EXPN_CNTR_SN")
        String freExpnCntrSn,
        @JsonProperty("RERNT_BOGO")
        String rerntBogo,
        @JsonProperty("FRDN_YN")
        String frdnYn,
        @JsonProperty("LEASE_DV")
        String leaseDv,
        @JsonProperty("LEASE_TAM")
        String leaseTam,
        @JsonProperty("CHG_MCN1")
        String chgMcn1,
        @JsonProperty("CHG_MCN2")
        String chgMcn2,
        @JsonProperty("RGST_COST")
        String rgstCost,
        @JsonProperty("RGST_COST_DSC")
        String rgstCostDsc,
        @JsonProperty("AUTO_CRT_YN")
        String autoCrtYn,
        @JsonProperty("FRE_YY_YN")
        String freYyYn,
        @JsonProperty("VAC_DP_DTM")
        String vacDpDtm,
        @JsonProperty("IST_INDV_DV")
        String istIndvDv,
        @JsonProperty("CNTR_CONF")
        String cntrConf,
        @JsonProperty("PRC_TCNT")
        String prcTcnt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 일시불 Result Dto
    @ApiModel("WctiContractCreateDto-CreateSinglePaymentRes")
    public record CreateSinglePaymentRes(
        @JsonProperty("PRCS_RSLT")
        String prcsRslt,
        @JsonProperty("MSG")
        String msg,
        @JsonProperty("DTL_MSG")
        String dtlMsg
    ) {}

    @ApiModel("WctiContractCreateDto-CreateRentalRes")
    public record CreateRentalRes(
        @JsonProperty("CHK_RS")
        String chkRs,
        @JsonProperty("RS_CD")
        String rsCd,
        @JsonProperty("RS_MSG")
        String rsMsg,
        @JsonProperty("ETC")
        String etc
    ) {}
}
