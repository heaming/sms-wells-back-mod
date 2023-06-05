package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0089 물류 반품요청 인터페이스 데이터 생성 (TB_IFIN_PD_RTNGD_AK_SEND_ETXT-상품반품요청송신전문)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Getter
@Setter
public class WsnaLogisticsInStorageAskDvo {

    // SAP플랜트코드 (SAP_PLNT_CD)
    private String sapPlntCd;

    // 물류입고요청번호 (LGST_STR_AK_NO)
    private String lgstStrAkNo;

    // SAP회사코드 (SAP_CO_CD)
    private String sapCoCd;

    // SAP저장위치코드 (SAP_SAVE_LCT_CD)
    private String sapSaveLctCd;

    // 반품요청번호 (RTNGD_AK_NO)
    private String rtngdAkNo;

    // 물류입고유형코드 (LGST_STR_TP_CD)
    private String lgstStrTpCd;

    // 입고요청등록일자 (STR_AK_RGST_DT)
    private String strAkRgstDt;

    // 입고예정일자 (STR_DUEDT)
    private String strDueDt;

    // 입출고요청구분코드 (IOST_AK_DV_CD)
    private String iostAkDvCd;

    // 담당파트너번호 (ICHR_PRTNR_NO)
    private String ichrPrtnrNo;

    // 전송여부 (TRS_YN)
    private String trsYn = "N";

    // 전송일시 (TRS_DTM)
    private String trsDtm;

    // 데이터삭제여부 (DTA_DL_YN)
    private String dtaDlYn = "N";

}
