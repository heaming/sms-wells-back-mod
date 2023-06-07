package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 데이터 생성 (TB_IFIN_OSTR_AK_PCSV_SEND_ETXT-출고요청택배송신전문)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-05
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageAskPcsvDvo {

    // SAP플랜트코드 (SAP_PLNT_CD)
    private String sapPlntCd;

    // 출고요청번호 (OSTR_AK_NO)
    private String ostrAkNo;

    // 출고요청일련번호 (OSTR_AK_SN)
    private int ostrAkSn;

    // 물류작업방식코드 (LGST_WK_MTHD_CD)
    private String lgstWkMthdCd;

    // 출고번호 (OSTR_NO)
    private String ostrNo;

    // 출고일련번호 (OSTR_SN)
    private Integer ostrSn;

    // 출고일자 (OSTR_DT)
    private String ostrDt;

    // 배송송장번호 (SPP_IVC_NO)
    private String sppIvcNo;

    // 상품바코드번호 (PD_BC_NO)
    private String pdBcNo;

    // 고객번호 (CST_NO)
    private String cstNo;

    // 고객명 (CST_NM)
    private String cstNm;

    // 수취인연계전화번호암호화 (ADRS_LK_TNO_ENCR)
    @DBEncField
    private String adrsLkTnoEncr;

    // 수취인연계휴대전화번호암호화 (ADRS_LK_CRAL_TNO_ENCR)
    @DBEncField
    private String adrsLkCralTnoEncr;

    // 우편번호 (ZIP)
    private String zip;

    // 기본주소 (BAS_ADR)
    private String basAdr;

    // 상세주소 (DTL_ADR)
    private String dtlAdr;

    // 지번주소 (LTN_ADR)
    private String ltnAdr;

    // 상품내용 (PD_CN)
    private String pdCn;

    // 계약번호 (CNTR_NO)
    private String cntrNo;

    // 계약일련번호 (CNTR_SN)
    private Integer cntrSn;

    // 계약바코드번호 (CNTR_BC_NO)
    private String cntrBcNo;

    // 계약자명 (CNTRT_NM)
    private String cntrtNm;

    // 파트너한글명 (PRTNR_KNM)
    private String prtnrKnm;

    // 박스수량 (BOX_QTY)
    private Integer boxQty;

    // 포인트값 (P_VAL)
    private String pVal;

    // 비고내용 (RMK_CN)
    private String rmkCn;

    // 전송여부 (TRS_YN)
    private String trsYn = "N";

    // 전송일시 (TRS_DTM)
    private String trsDtm;

    // 합포장일련번호 (MAPC_SN)
    private Integer mpacSn;

    // 데이터삭제여부 (DTA_DL_YN)
    private String dtaDlYn = "N";
}
