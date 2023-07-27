package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class CtContractConst {

    private CtContractConst() {}

    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/contract";
    public static final String INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/interface/sms/wells/contract";

    public static final String STRT_DTM = "00000101000000";
    public static final String END_DTM = "99991231235959";
    public static final String EXCEL_UPLOAD_SUCCESS = "S";
    public static final String EXCEL_UPLOAD_ERROR = "E";

    /* 계약유형코드 */
    public static final String CNTR_TP_CD_INDV = "01";
    public static final String CNTR_TP_CD_CRP = "02";
    public static final String CNTR_TP_CD_ENSM = "03";
    public static final String CNTR_TP_CD_MSH = "07";
    public static final String CNTR_TP_CD_RSTL = "08";
    public static final String CNTR_TP_CD_QUOT = "09";

    /* 계약진행상태코드 */
    public static final String CNTR_PRGS_STAT_CD_TEMP_STEP1 = "10";
    public static final String CNTR_PRGS_STAT_CD_TEMP_STEP2 = "12";
    public static final String CNTR_PRGS_STAT_CD_TEMP_STEP3 = "14";
    public static final String CNTR_PRGS_STAT_CD_TEMP_STEP4 = "16";
    public static final String CNTR_PRGS_STAT_CD_TEMP_STEP5 = "18";
    public static final String CNTR_PRGS_STAT_CD_WRTE_FSH = "20";
    public static final String CNTR_PRGS_STAT_CD_STLM_STNB = "30";
    public static final String CNTR_PRGS_STAT_CD_STLM_ING = "40";
    public static final String CNTR_PRGS_STAT_CD_STLM_FSH = "50";
    public static final String CNTR_PRGS_STAT_CD_CNFM = "60";
    public static final String CNTR_PRGS_STAT_CD_DEL = "99";

    public static final String SELL_TP_CD_SPAY = "1";
    public static final String SELL_TP_CD_RNTL = "2";
    public static final String SELL_TP_CD_MSH = "3";
    public static final String SELL_TP_CD_RGSP = "6";

    public enum PeriodType {
        RCT_DT, /*접수일자*/
        SL_RCOG_DT, /*매출일자*/
        CAN_DT, /*취소일자*/
        IST_DT, /*설치일자*/
        EXN_DT /*만료일자*/
    }

}
