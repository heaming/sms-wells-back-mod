package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 데이터 생성
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-25
 */

public class WsnaLogisticsOutStorageAskDto {
    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-SaveReq")
    public record SaveReq(

        // 출고요청번호 (OSTR_AK_NO)
        @NotBlank
        String ostrAkNo,

        // 출고요청일련번호 (OSTR_AK_SN)
        @Positive
        int ostrAkSn,

        // 출고요청유형코드 (OSTR_AK_TP_CD)
        @NotBlank
        String ostrAkTpCd,

        // 출고요청일자 (OSTR_AK_RGST_DT)
        @NotBlank
        @ValidDate
        String ostrAkRgstDt,

        // 입고희망일자 (STR_HOP_DT)
        @ValidDate
        String strHopDt,

        // 입출고요청구분코드 (IOST_AK_DV_CD)
        @NotBlank
        String iostAkDvCd,

        // 창고관리파트너번호 (WARE_MNGT_PRTNR_NO)
        @NotBlank
        String wareMngtPrtnrNo,

        // 창고관리파트너조직유형코드 (WARE_MNGT_PRTNR_OG_TP_CD)
        @NotBlank
        String wareMngtPrtnrOgTpCd,

        // 합포장일련번호 (MPAC_SN)
        Integer mpacSn,

        // SAP입출고유형코드 (SAP_IOST_TP_CD)
        String sapIostTpCd,

        // 물류배송방식코드 (LGST_SPP_MTHD_CD)
        @NotBlank
        String lgstSppMthdCd,

        // 물류작업방식코드 (LGST_WK_MTHD_CD)
        @NotBlank
        String lgstWkMthdCd,

        // 품목상품코드 (ITM_PD_CD)
        @NotBlank
        String itmPdCd,

        // 출고요청수량 (OSTR_AK_QTY)
        @Positive
        int ostrAkQty,

        // 품목등급코드 (ITM_GD_CD)
        @NotBlank
        String itmGdCd,

        // 출고대상창고번호 (OSTR_OJ_WARE_NO)
        @NotBlank
        String ostrOjWareNo,

        // 고객번호 (CST_NO)
        String cstNo,

        // 고객명 (CST_NM)
        String cstNm,

        // 계약번호 (CNTR_NO)
        String cntrNo,

        // 계약일련번호 (CNTR_SN)
        Integer cntrSn,

        // 서비스센터코드 (SV_CNR_CD)
        String svCnrCd,

        // 서비스센터명 (SV_CNR_NM)
        String svCnrNm,

        // 서비스센터담당파트너명 (SV_CNR_ICHR_PRTNR_NM)
        String svCnrIchrPrtnrNm,

        // 서비스센터전화번호암호화 (SV_CNR_LK_TNO_ENCR)
        String svCnrLkTnoEncr,

        // 서비스센터주소 (SV_CNR_ADR)
        String svCnrAdr,

        // 배차유형코드 (OVIV_TP_CD)
        String ovivTpCd,

        // 비고내용 (RMK_CN)
        String rmkCn,

        // 입고대상창고번호 (STR_OJ_WARE_NO)
        String strOjWareNo,

        // 입고대상 창고의 상세구분코드 (WARE_DTL_DV_CD)
        String wareDtlDvCd,

        // 입고대상창고명 (WARE_NM)
        String wareNm
    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-SaveRes")
    public record SaveRes(
        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 저장 건수
        int akCnt,

        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 저장 건수
        int akDtlCnt
    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-CreateQomReq")
    public record CreateQomReq(

        // 출고요청번호 (OSTR_AK_NO)
        @NotBlank
        String ostrAkNo,

        // 출고요청일련번호 (OSTR_AK_SN)
        @Positive
        int ostrAkSn,

        // 출고요청유형코드 (OSTR_AK_TP_CD)
        @NotBlank
        String ostrAkTpCd,

        // 출고요청일자 (OSTR_AK_RGST_DT)
        @NotBlank
        @ValidDate
        String ostrAkRgstDt,

        // 입고희망일자 (STR_HOP_DT)
        @ValidDate
        String strHopDt,

        // 입출고요청구분코드 (IOST_AK_DV_CD)
        @NotBlank
        String iostAkDvCd,

        // 창고관리파트너번호 (WARE_MNGT_PRTNR_NO)
        @NotBlank
        String wareMngtPrtnrNo,

        // 창고관리파트너조직유형코드 (WARE_MNGT_PRTNR_OG_TP_CD)
        @NotBlank
        String wareMngtPrtnrOgTpCd,

        // 합포장일련번호 (MPAC_SN)
        Integer mpacSn,

        // SAP입출고유형코드 (SAP_IOST_TP_CD)
        String sapIostTpCd,

        // 물류배송방식코드 (LGST_SPP_MTHD_CD)
        @NotBlank
        String lgstSppMthdCd,

        // 물류작업방식코드 (LGST_WK_MTHD_CD)
        @NotBlank
        String lgstWkMthdCd,

        // 품목상품코드 (ITM_PD_CD)
        @NotBlank
        String itmPdCd,

        // 출고요청수량 (OSTR_AK_QTY)
        @Positive
        int ostrAkQty,

        // 품목등급코드 (ITM_GD_CD)
        @NotBlank
        String itmGdCd,

        // 출고대상창고번호 (OSTR_OJ_WARE_NO)
        @NotBlank
        String ostrOjWareNo,

        // 서비스센터코드 (SV_CNR_CD)
        String svCnrCd,

        // 서비스센터명 (SV_CNR_NM)
        String svCnrNm,

        // 서비스센터담당파트너명 (SV_CNR_ICHR_PRTNR_NM)
        String svCnrIchrPrtnrNm,

        // 서비스센터전화번호암호화 (SV_CNR_LK_TNO_ENCR)
        String svCnrLkTnoEncr,

        // 서비스센터주소 (SV_CNR_ADR)
        String svCnrAdr,

        // 배차유형코드 (OVIV_TP_CD)
        String ovivTpCd,

        // 비고내용 (RMK_CN)
        String rmkCn,

        // 입고대상창고번호 (STR_OJ_WARE_NO)
        String strOjWareNo,

        // 입고대상 창고의 상세구분코드 (WARE_DTL_DV_CD)
        String wareDtlDvCd,

        // 입고대상창고명 (WARE_NM)
        String wareNm,

        // 차수 (TCNT)
        @Positive
        int tcnt

    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-CreateQomRes")
    public record CreateQomRes(

        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 생성 건수
        int akCnt,

        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 생성 건수
        int akDtlCnt,

        // TB_IFIN_SPP_BAS_SEND_ETXT - 배송기본송신전문 데이터 생성 건수
        int basCnt,

        // TB_IFIN_SPP_PD_SEND_ETXT - 배송상품송신전문 데이터 생성 건수
        int pdCnt,

        // TB_IFIN_SPP_MAT_SEND_ETXT - 배송자재송신전문 데이터 생성 건수
        int matCnt
    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-SaveSelfFilterReq")
    public record SaveSelfFilterReq(

        // 출고요청번호 (OSTR_AK_NO)
        @NotBlank
        String ostrAkNo,

        // 출고요청일련번호 (OSTR_AK_SN)
        @Positive
        int ostrAkSn,

        // 출고요청유형코드 (OSTR_AK_TP_CD)
        @NotBlank
        String ostrAkTpCd,

        // 출고요청일자 (OSTR_AK_RGST_DT)
        @NotBlank
        @ValidDate
        String ostrAkRgstDt,

        // 입고희망일자 (STR_HOP_DT)
        @ValidDate
        String strHopDt,

        // 입출고요청구분코드 (IOST_AK_DV_CD)
        @NotBlank
        String iostAkDvCd,

        // 창고관리파트너번호 (WARE_MNGT_PRTNR_NO)
        @NotBlank
        String wareMngtPrtnrNo,

        // 창고관리파트너조직유형코드 (WARE_MNGT_PRTNR_OG_TP_CD)
        @NotBlank
        String wareMngtPrtnrOgTpCd,

        // 합포장일련번호 (MPAC_SN)
        Integer mpacSn,

        // SAP입출고유형코드 (SAP_IOST_TP_CD)
        String sapIostTpCd,

        // 물류배송방식코드 (LGST_SPP_MTHD_CD)
        @NotBlank
        String lgstSppMthdCd,

        // 물류작업방식코드 (LGST_WK_MTHD_CD)
        @NotBlank
        String lgstWkMthdCd,

        // 품목상품코드 (ITM_PD_CD)
        @NotBlank
        String itmPdCd,

        // 출고요청수량 (OSTR_AK_QTY)
        @Positive
        int ostrAkQty,

        // 품목등급코드 (ITM_GD_CD)
        @NotBlank
        String itmGdCd,

        // 출고대상창고번호 (OSTR_OJ_WARE_NO)
        @NotBlank
        String ostrOjWareNo,

        // 고객번호 (CST_NO)
        @NotBlank
        String cstNo,

        // 고객명 (CST_NM)
        @NotBlank
        String cstNm,

        // 계약번호 (CNTR_NO)
        @NotBlank
        String cntrNo,

        // 계약일련번호 (CNTR_SN)
        @Positive
        int cntrSn,

        // 계약바코드번호 (CNTR_BC_NO)
        String cntrBcNo,

        // 계약자명 (CNTRT_NM)
        String cntrtNm,

        // 서비스센터코드 (SV_CNR_CD)
        String svCnrCd,

        // 서비스센터명 (SV_CNR_NM)
        String svCnrNm,

        // 서비스센터담당파트너명 (SV_CNR_ICHR_PRTNR_NM)
        String svCnrIchrPrtnrNm,

        // 서비스센터전화번호암호화 (SV_CNR_LK_TNO_ENCR)
        String svCnrLkTnoEncr,

        // 서비스센터주소 (SV_CNR_ADR)
        String svCnrAdr,

        // 배차유형코드 (OVIV_TP_CD)
        String ovivTpCd,

        // 비고내용 (RMK_CN)
        String rmkCn,

        // 입고대상창고번호 (STR_OJ_WARE_NO)
        String strOjWareNo,

        // 입고대상 창고의 상세구분코드 (WARE_DTL_DV_CD)
        String wareDtlDvCd,

        // 입고대상창고명 (WARE_NM)
        String wareNm,

        // 배송송장번호 (SPP_IVC_NO)
        String sppIvcNo,

        // 상품바코드번호 (PD_BC_NO)
        String pdBcNo,

        // 수취인전화번호값 (ADRS_TNO_VAL)
        String adrsTnoVal,

        // 수취인휴대폰번호값 (ADRS_CPHON_NO_VAL)
        String adrsCphonNoVal,

        // 우편번호 (ZIP)
        String zip,

        // 기본주소 (BAS_ADR)
        String basAdr,

        // 상세주소 (DTL_ADR)
        String dtlAdr,

        // 지번주소 (LTN_ADR)
        String ltnAdr,

        // 상품내용 (PD_CN)
        String pdCn,

        // 파트너한글명 (PRTNR_KNM)
        String prtnrKnm,

        // 박스수량 (BOX_QTY)
        Integer boxQty,

        // 포인트값 (P_VAL)
        Integer pVal

    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-SaveSelfFilterRes")
    public record SaveSelfFilterRes(
        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 저장 건수
        int akCnt,

        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 저장 건수
        int akDtlCnt,

        // TB_IFIN_OSTR_AK_PCSV_SEND_ETXT - 출고요청택배송신전문 데이터 저장 건수
        int pcsvCnt
    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-RemoveReq")
    public record RemoveReq(
        // 출고요청번호 (OSTR_AK_NO)
        @NotBlank
        String ostrAkNo,

        // 출고요청일련번호 (OSTR_AK_SN)
        @Positive
        int ostrAkSn

    ) {}

}
