package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 데이터 생성 Request Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageAskReqDvo {

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

    // 입출고요청구분코드 (IOST_AK_DV_CD)
    private String iostAkDvCd;

    // 창고관리파트너번호 (WARE_MNGT_PRTNR_NO)
    private String wareMngtPrtnrNo;

    // 창고관리파트너조직유형코드 (WARE_MNGT_PRTNR_OG_TP_CD)
    private String wareMngtPrtnrOgTpCd;

    // 합포장일련번호 (MPAC_SN)
    private Integer mpacSn;

    // SAP입출고유형코드 (SAP_IOST_TP_CD)
    private String sapIostTpCd;

    // 물류배송방식코드 (LGST_SPP_MTHD_CD)
    private String lgstSppMthdCd;

    // 물류작업방식코드 (LGST_WK_MTHD_CD)
    private String lgstWkMthdCd;

    // 품목상품코드 (ITM_PD_CD)
    private String itmPdCd;

    // 출고요청수량 (OSTR_AK_QTY)
    private int ostrAkQty;

    // 품목등급코드 (ITM_GD_CD)
    private String itmGdCd;

    // 출고대상창고번호 (OSTR_OJ_WARE_NO)
    private String ostrOjWareNo;

    // 고객번호 (CST_NO)
    private String cstNo;

    // 고객명 (CST_NM)
    private String cstNm;

    // 계약번호 (CNTR_NO)
    private String cntrNo;

    // 계약일련번호 (CNTR_SN)
    private Integer cntrSn;

    // 계약바코드번호 (CNTR_BC_NO)
    private String cntrBcNo;

    // 계약자명 (CNTRT_NM)
    private String cntrtNm;

    // 서비스센터코드 (SV_CNR_CD)
    private String svCnrCd;

    // 서비스센터명 (SV_CNR_NM)
    private String svCnrNm;

    // 서비스센터담당파트너명 (SV_CNR_ICHR_PRTNR_NM)
    private String svCnrIchrPrtnrNm;

    // 서비스센터전화번호암호화 (SV_CNR_LK_TNO_ENCR)
    private String svCnrLkTnoEncr;

    // 서비스센터주소 (SV_CNR_ADR)
    private String svCnrAdr;

    // 배차유형코드 (OVIV_TP_CD)
    private String ovivTpCd;

    // 비고내용 (RMK_CN)
    private String rmkCn;

    // 차수 (TCNT)
    private Integer tcnt;

    // 입고대상창고번호 (STR_OJ_WARE_NO)
    private String strOjWareNo;

    // 입고대상 창고의 상세구분코드 (WARE_DTL_DV_CD)
    private String wareDtlDvCd;

    // 입고대상창고명 (WARE_NM)
    private String wareNm;

    // 배송송장번호 (SPP_IVC_NO)
    private String sppIvcNo;

    // 상품바코드번호 (PD_BC_NO)
    private String pdBcNo;

    // 수취인전화번호값 (ADRS_TNO_VAL)
    private String adrsTnoVal;

    // 수취인휴대폰번호값 (ADRS_CPHON_NO_VAL)
    private String adrsCphonNoVal;

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

    // 파트너한글명 (PRTNR_KNM)
    private String prtnrKnm;

    // 박스수량 (BOX_QTY)
    private Integer boxQty;

    // 포인트값 (P_VAL)
    private String pVal;

}
