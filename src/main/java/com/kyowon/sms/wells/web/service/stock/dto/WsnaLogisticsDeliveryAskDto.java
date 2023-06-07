package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-S-0091 물류 배송요청 서비스 (HQ 생성로직)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-31
 */

public class WsnaLogisticsDeliveryAskDto {

    @Builder
    @ApiModel("WsnaLogisticsDeliveryAskDto-CreateReq")
    public record CreateReq(
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
    @ApiModel("WsnaLogisticsDeliveryAskDto-CreateRes")
    public record CreateRes(
        // TB_IFIN_SPP_BAS_SEND_ETXT - 배송기본송신전문 데이터 생성 건수
        int basCnt,

        // TB_IFIN_SPP_PD_SEND_ETXT - 배송상품송신전문 데이터 생성 건수
        int pdCnt,

        // TB_IFIN_SPP_MAT_SEND_ETXT - 배송자재송신전문 데이터 생성 건수
        int matCnt
    ) {}

}
