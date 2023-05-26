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

        // 출고요청일자 (OSTR_RQDT)
        @NotBlank
        @ValidDate
        String ortrRqdt,

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
        int mpacSn,

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

        // 고객번호 (CST_NO)
        String cstNo,

        // 고객명 (CST_NM)
        String cstNm,

        // 계약번호 (CNTR_NO)
        String cntrNo,

        // 계약일련번호 (CNTR_SN)
        Integer cntrSn,

        // 출고대상창고번호 (OSTR_OJ_WARE_NO)
        @NotBlank
        String ostrOjWareNo,

        // 서비스센터코드 (SV_CNR_CD)
        String svCnrCd,

        // 서비스센터명 (SV_CNR_NM)
        String svCnrNm,

        // 비고내용 (RMK_CN)
        String rmkCn,

        // 입고대상창고번호 (STR_OJ_WARE_NO)
        String strOjWareNo,

        // 입고대상 창고의 상세구분코드 (STR_OJ_WARE_DTL_DV_CD)
        String strOjWareDtlDvCd,

        // 입고대상창고명 (STR_OJ_WARE_NM)
        String strOjWareNm
    ) {}

}
