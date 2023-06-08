package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 데이터 생성 (TB_IFIN_OSTR_AK_DTL_SEND_ETXT-출고요청상세송신전문)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-25
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageAskDtlDvo {

    // SAP플랜트코드 (SAP_PLNT_CD)
    private String sapPlntCd;

    // 물류출고요청번호 (LGST_OSTR_AK_NO)
    private String lgstOstrAkNo;

    // 출고요청일련번호 (OSTR_AK_SN)
    private int ostrAkSn;

    // 합포장일련번호 (MPAC_SN)
    private Integer mpacSn;

    // 관계번호 (REL_NO)
    private String relNo;

    // 관계일련번호 (REL_SN)
    private Integer relSn;

    // SAP입출고유형코드 (SAP_IOST_TP_CD)
    private String sapIostTpCd;

    // 물류배송방식코드 (LGST_SPP_MTHD_CD)
    private String lgstSppMthdCd;

    // 물류작업방식코드 (LGST_WK_MTHD_CD)
    private String lgstWkMthdCd;

    // 배달파트너번호 (MDLV_PRTNR_NO)
    private String mdlvPrtnrNo;

    // 품목상품코드 (ITM_PD_CD)
    private String itmPdCd;

    // 출고요청수량 (OSTR_AK_QTY)
    private Integer ostrAkQty;

    // 물류품목등급코드 (LGST_ITM_GD_CD)
    private String lgstItmGdCd;

    // 고객번호 (CST_NO)
    private String cstNo;

    // 고객명 (CST_NM)
    private String cstNm;

    // 계약번호 (CNTR_NO)
    private String cntrNo;

    // 계약일련번호 (CNTR_SN)
    private Integer cntrSn;

    // 서비스센터코드 (SV_CNR_CD)
    private String svCnrCd;

    // 서비스센터명 (SV_CNR_NM)
    private String svCnrNm;

    // 서비스센터담당파트너명 (SV_CNR_ICHR_PRTNR_NM)
    private String svCnrIchrPrtnrNm;

    // 서비스센터연계전화번호암호화 (SV_CNR_LK_TNO_ENCR)
    @DBEncField
    private String svCnrLkTnoEncr;

    // 서비스센터주소 (SV_CNR_ADR)
    private String svCnrAdr;

    // 배차유형코드 (OVIV_TP_CD)
    private String ovivTpCd;

    // 학습지분류코드 (HSMTRL_CLSF_CD)
    private String hsmtrlClsfCd;

    // 학습지분류명 (HSMTRL_CLSF_NM)
    private String hsmtrlClsfNm;

    // 후행처리수량 (BACT_PROCS_QTY)
    private Integer bactProcsQty;

    // 비고내용 (RMK_CN)
    private String rmkCn;

    // 전송여부 (TRS_YN)
    private String trsYn = "N";

    // 전송일시 (TRS_DTM)
    private String trsDtm;

    // 데이터삭제여부 (DTA_DL_YN)
    private String dtaDlYn = "N";

}
