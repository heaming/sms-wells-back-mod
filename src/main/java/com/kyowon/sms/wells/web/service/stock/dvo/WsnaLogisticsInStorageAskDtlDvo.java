package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0089 물류 반품요청 인터페이스 데이터 생성 (반품요청상세송신전문)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Getter
@Setter
public class WsnaLogisticsInStorageAskDtlDvo {

    // SAP플랜트코드 (SAP_PLNT_CD)
    private String sapPlntCd;

    // 물류입고요청번호 (LGST_STR_AK_NO)
    private String lgstStrAkNo;

    // 입고요청일련번호 (STR_AK_SN)
    private int strAkSn;

    // 관계번호 (REL_NO)
    private String relNo;

    // 관계일련번호 (REL_SN)
    private Integer relSn;

    // SAP입출고유형코드 (SAP_IOST_TP_CD)
    private String sapIostTpCd;

    // 품목상품코드 (ITM_PD_CD)
    private String itmPdCd;

    // 입고요청수량 (STR_AK_QTY)
    private Integer strAkQty;

    // 물류품목등급코드 (LGST_ITM_GD_CD)
    private String lgstItmGdCd;

    // 고객번호 (CST_NO)
    private String cstNo;

    // 계약번호 (CST_NM)
    private String cstNm;

    // 계약일련번호 (CNTR_NO)
    private String cntrNo;

    // 서비스센터코드 (SV_CNR_CD)
    private String svCnrCd;

    // 서비스센터명 (SV_CNR_NM)
    private String svCnrNm;

    // 출고SAP플랜트코드 (OSTR_SAP_PLNT_CD)
    private String ostrSapPlntCd;

    // 출고SAP저장위치코드 (OSTR_SAP_SAVE_LCT_CD)
    private String ostrSapSaveLctCd;

    // 거래처코드 (DLPNR_CD)
    private String dlpnrCd;

    // 물류배송방식코드 (LGST_SPP_MTHD_CD)
    private String lgstSppMthdCd;

    // 배달파트너번호 (MDLV_PRTNR_NO)
    private String mdlvPrtnrNo;

    // 판쇄내용 (PAE_CN)
    private String paeCn;

    // 회수송장번호 (CLN_IVC_NO)
    private String clnIvcNo;

    // 바코드번호 (BC_NO)
    private String bcNo;

    // 거래처바코드번호 (DLPNR_BC_NO)
    private String dlpnrBcNo;

    // 유통기한일자 (CRCL_TMLM_DT)
    private String crclTmlmDt;

    // 제조일자 (MNFT_DT)
    private String mnftDt;

    // 후행처리수량 (BACT_PROCS_QTY)
    private Integer bactProcsQty;

    // 비고내용 (RMK_CN)
    private String rmkCn;

    // 전송여부 (TRS_YN)
    private String trsYn = "N";

    // 전송일시 (TRS_DTM)
    private String trsDtm;

    // 데이터삭제여부 (DTA_DL_YN
    private String dtaDlYn = "N";
}
