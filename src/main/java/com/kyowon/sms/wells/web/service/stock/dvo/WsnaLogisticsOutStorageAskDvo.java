package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 데이터 생성 (품목출고요청송신전문)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-25
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageAskDvo {

    // SAP플랜트코드 (SAP_PLNT_CD)
    String sapPlntCd;

    // 물류출고요청번호 (LGST_OSTR_AK_NO)
    String lgstOstrAkNo;

    // SAP회사코드 (SAP_CO_CD)
    String sapCoCd;

    // SAP저장위치코드 (SAP_SAVE_LCT_CD)
    String sapSaveLctCd;

    // 출고요청번호 (OSTR_AK_NO)
    String ostrAkNo;

    // 출고창고번호 (OSTR_WARE_NO)
    String ostrWareNo;

    // 출고요청일자 (OSTR_RQDT)
    String ostrRqdt;

    // 출고희망일자 (OSTR_HOP_DT)
    String ostrHopDt;

    // 입출고요청구분코드 (IOST_AK_DV_CD)
    String iostAkDvCd;

    // 담당파트너번호 (ICHR_PRTNR_NO)
    String ichrMngtPrtnrNo;

    // 담당파트너조직유형코드 (ICHR_PRTNR_OG_TP_CD)
    String ichrPrtnrOgTpCd;

    // 전송여부 (TRS_YN)
    String trsYn = "N";

    // 전송일시 (TRS_DTM)
    String trsDtm;

    // 데이터삭제여부 (DTA_DL_YN)
    String dtaDlYn = "N";

}
