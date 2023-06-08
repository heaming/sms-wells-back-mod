package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0089 물류 반품요청 데이터 생성 Request Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Getter
@Setter
public class WsnaLogisticsInStorageAskReqDvo {

    // 출고요청번호 (OSTR_AK_NO)

    private String ostrAkNo;

    // 출고요청일련번호 (OSTR_AK_SN)
    private int ostrAkSn;

    // 출고요청유형코드 (OSTR_AK_TP_CD)
    private String ostrAkTpCd;

    // 출고요청일자 (OSTR_AK_RGST_DT)
    private String ostrAkRgstDt;

    // 입고희망일자 (STR_HOP_DT)
    private String strHopDt;

    // 물류입고유형코드 (LGST_STR_TP_CD)
    private String lgstStrTpCd;

    // 입출고요청구분코드 (IOST_AK_DV_CD)
    private String iostAkDvCd;

    // 창고관리파트너번호 (WARE_MNGT_PRTNR_NO)
    private String wareMngtPrtnrNo;

    // 창고관리파트너조직유형코드 (WARE_MNGT_PRTNR_OG_TP_CD)
    private String wareMngtPrtnrOgTpCd;

    // SAP입출고유형코드 (SAP_IOST_TP_CD)
    private String sapIostTpCd;

    // 물류배송방식코드 (LGST_SPP_MTHD_CD)
    private String lgstSppMthdCd;

    // 품목상품코드 (ITM_PD_CD)
    private String itmPdCd;

    // 출고요청수량 (OSTR_AK_QTY)
    private int ostrAkQty;

    // 품목등급코드 (ITM_GD_CD)
    private String itmGdCd;

    // 출고대상창고번호 (OSTR_OJ_WARE_NO)
    private String ostrOjWareNo;

    // 서비스센터코드 (SV_CNR_CD)
    private String svCnrCd;

    // 서비스센터명 (SV_CNR_NM)
    private String svCnrNm;

    // 비고내용 (RMK_CN)
    private String rmkCn;
}
