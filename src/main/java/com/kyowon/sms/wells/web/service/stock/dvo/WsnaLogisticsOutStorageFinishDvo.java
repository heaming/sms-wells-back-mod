package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 물류센터 출고완료 처리 서비스 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-14
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageFinishDvo {

    // 연계발생ID (LINK_OCRN_ID)
    private String linkOcrnId;

    // 출고확정수량 (OSTR_CNFM_QTY)
    private int ostrCnfmQty;

    // 출고번호 (ITM_OSTR_NO)
    private String itmOstrNo;

    // 출고일련번호 (OSTR_SN)
    private int ostrSn;

    // 출고일자 (OSTR_DT)
    private String ostrDt;

    // 출고요청번호 (OSTR_AK_NO)
    private String ostrAkNo;

    // 출고요청일련번호 (OSTR_AK_SN)
    private int ostrAkSn;

    // 출고요청유형코드 (OSTR_AK_TP_CD)
    private String ostrAkTpCd;

    // 입고대상창고번호 (STR_OJ_WARE_NO)
    private String strOjWareNo;

    // 출고요청창고구분코드 (OSTR_AK_WARE_DV_CD)
    private String ostrAkWareDvCd;

    // 창고관리파트너번호 (WARE_MNGT_PRTNR_NO)
    private String wareMngtPrtnrNo;

    // 창고관리파트너조직유형코드 (WARE_MNGT_PRTNR_OG_TP_CD)
    private String wareMngtPrtnrOgTpCd;

    // 입고대상 상위창고번호 (STR_HGR_WARE_NO)
    private String strHgrWareNo;

    // 입고 상위 창고구분코드 (STR_HGR_DV_CD)
    private String strHgrDvCd;

    // 입고 상위 파트너번호 (STR_HGR_PRTNR_NO)
    private String strHgrPrtnrNo;

    // 입고 상위 파트너 조직유형코드 (STR_HGR_PRTNR_OG_TP_CD)
    private String strHgrPrtnrOgTpCd;

    // 품목종류코드 (ITM_KND_CD)
    private String itmKndCd;

    // 품목상품코드 (ITM_PD_CD)
    private String itmPdCd;

    // 품목등급코드 (ITM_GD_CD)
    private String itmGdCd;

    // 관리단위코드 (MNGT_UNIT_CD)
    private String mngtUnitCd;

    // 박스단위수량 (BOX_UNIT_QTY)
    private Integer boxUnitQty;

    // 출고대상창고번호
    private String ostrOjWareNo;
    // 출고대상창고구분코드
    private String ostrWareDvCd;
    // 출고창고파트너번호
    private String ostrPrtnrNo;
    // 출고창고조직유형코드
    private String ostrPrtnrOgTpCd;
    // 입고희망일자
    private String strHopDt;

}
