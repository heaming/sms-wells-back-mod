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

        // 출고희망일자 (OSTR_HOP_DT)
        @ValidDate
        String ostrHopDt,

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

        // 비고내용 (RMK_CN)
        String rmkCn
    ) {}

    @Builder
    @ApiModel("WsnaLogisticsOutStorageAskDto-SaveQomReq")
    public record SaveQomReq(

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

        // 비고내용 (RMK_CN)
        String rmkCn,

        // 주문번호 (LLORNO)
        @NotBlank
        String llorno,

        // 순번 (LLORSQ)
        @Positive
        int llorsq,

        // 업무구분 (LLPRTP)
        @NotBlank
        String llprtp,

        // 신청유형 (LLAPTP)
        @NotBlank
        String llaptp,

        // 신청조직구분 (LLAPOC)
        String llapoc,

        // 신청조직명 (LLAPOCNM)
        String llapocnm,

        // 신청소속 (LLAPOR)
        String llapor,

        // 신청소속명 (LLAPORNM)
        String llapornm,

        // 신청자코드 (LLAPPC)
        String llappc,

        // 신청자성명 (LLAPPN)
        String llappn,

        // 주문완료일 (LLORDT)
        @ValidDate
        String llordt,

        // 배송코드 (LLSHCS)
        String llshcs,

        // 배송지코드 (LLRCOR)
        String llrcor,

        // 배송지명 (LLRCORNM)
        String llrcornm,

        // I/F전송번호 (LLIFNO)
        String llifno,

        // 등록일 (LLINDT)
        @ValidDate
        String llindt,

        // KSS수신완료 (LLKSTR)
        String llsktr,

        // 송신자용필드 (LLSDFL)
        String llsdfl,

        // 전송대상여부 (LLKSSU)
        String llkssu,

        // 자재코드 (LLMTCD)
        String llmtcd,

        // 자재명 (LLMTNM)
        String llmtnm,

        // 자재수량 (LLMTQT)
        @Positive
        Integer llmtqt,

        // 출고번호 (LLOTNO)
        String llotno,

        // 출고일자 (LLOTDT)
        @ValidDate
        String llotdt,

        // 박스퍼센트 (LLPERC)
        Integer llperc,

        // 상품코드 (LLPRCD)
        String llprcd,

        // 상품명 (LLPRNM)
        String llprnm,

        // 상품수량 (LLPRQT)
        @Positive
        Integer llprqt,

        // 주문상태코드 (LLORST)
        String llorst,

        // 주문상태일자 (LLORSD)
        @ValidDate
        String llorsd,

        // SAP전송일자 (LLSPDT)
        String llspdt,

        // SAP결과 (LLSPRT)
        String llsprt,

        // 상품구분 (LLPRDV)
        String llprdv

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

        // 출고희망일자 (OSTR_HOP_DT)
        @ValidDate
        String ostrHopDt,

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

        // 계약바코드번호 (CNTR_BC_NO)
        String cntrBcNo,

        // 계약자명 (CNTRT_NM)
        String cntrtNm,

        // 서비스센터코드 (SV_CNR_CD)
        String svCnrCd,

        // 서비스센터명 (SV_CNR_NM)
        String svCnrNm,

        // 비고내용 (RMK_CN)
        String rmkCn,

        // 출고일자 (OSTR_DT)
        @ValidDate
        String ostrDtm,

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
