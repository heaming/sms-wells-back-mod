package com.kyowon.sms.wells.web.promotion.common.dto;

import java.util.List;

import com.kyowon.sms.common.web.promotion.common.dto.ZpmzPromotionApplyDto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *     Wells 공통 프로모션 체크 조회 DTO
 * </pre>
 * @author domin.pyun
 * @since 2023-05-11
 */
public class WpmzPromotionCheckDto {

    /**
     * SearchReq - 조회조건 DTO
     * @param pmotCd
     * @param pmotOjGrpDvCd
     * @param pmotApyChnlCd
     * @param pmotApyOgTpCd
     * @param basePdCd
     * @param basePdPrcDtlCd
     * @param lkPdClsfCd
     * @param lkPdCd
     * @param pkgMndtPdCd
     * @param chdvcPrmitYn
     * @param chdvcBfPdClsfCd
     * @param chdvcBfPdCd
     * @param lkChdvcPrmitYn
     * @param chdvcTpCd
     * @param oppstOrdRcptdt
     * @param oppstSlDt
     * @param alncBzsCd
     * @param evCd
     * @param selrCd
     * @param dupApyPsbYn
     * @param crpDscExcdYn
     * @param spcDscCd
     */
    @ApiModel(value = "WpmzPromotionCheckDto-SearchReq")
    public record SearchReq(
        String pmotCd,              /* 프로모션코드       */
        String pmotOjGrpDvCd,       /* 프로모대상그룹코드   */
        String pmotApyChnlCd,       /* 프로모션적용채널코드 */
        String pmotApyOgTpCd,       /* 프로모션적용조직코드 */
        String basePdCd,            /* 상품코드          */
        String basePdPrcDtlCd,      /* 상품가격상세코드    */
        String lkPdClsfCd,          /* 연계상품분류(모종 상품분류코드)  */
        String lkPdCd,              /* 연계상품코드(모종 상품코드)  */
        String pkgMndtPdCd,         /* 패키지필수 상품코드  */
        String chdvcPrmitYn,        /* 기기변경허용여부 */
        String chdvcBfPdClsfCd,     /* 기기변경이전상품분류 */
        String chdvcBfPdCd,         /* 기기변경이전상품코드 */
        String lkChdvcPrmitYn,      /* 연계코드기변제외 */
        String chdvcTpCd,           /* 기기변경유형 */
        String oppstOrdRcptdt,      /* 기기변경이전상품접수일자 */
        String oppstSlDt,           /* 기기변경이전상품매출일자 */
        String alncBzsCd,           /* 제휴업체코드 */
        String evCd,                /* 행사코드 */
        String selrCd,              /* 판매자코드 */
        String dupApyPsbYn,         /* 중복적용가능여부 */
        String crpDscExcdYn,        /* 법인DC제외여부 */
        String spcDscCd             /* 특별할인코드 */
    ) {}

    /**
     * SearchRes - 조회 결과 DTO
     * @param pmotCd
     * @param pmotCndtFvrRelId
     * @param dscMcnt
     * @param dscFxam
     * @param freeMcnt
     * @param rentalFxam
     * @param ackmtCt
     * @param ackmtAmt
     * @param ackmtRt
     * @param stdFeeAmt
     * @param feeFxamYn
     * @param fgptChoGrpCd
     * @param fgptChoCd
     * @param prmDupPrmitYn
     * @param pdPrcDtlCd
     * @param pmotFreeGifts
     */
    @ApiModel(value = "WpmzPromotionCheckDto-SearchRes")
    public record SearchRes(
        String pmotCd,              /* 프로모션코드      */
        String pmotCndtFvrRelId,    /* 프로모션조건혜택관계ID */
        String dscMcnt,             /* 할인개월         */
        String dscFxam,             /* 할인금액         */
        String freeMcnt,            /* 무료개월         */
        String rentalFxam,          /* 렌탈금액         */
        String ackmtCt,             /* 인정건수 */
        String ackmtAmt,            /* 인정금액 */
        String ackmtRt,             /* 인정금액(P) */
        String stdFeeAmt,           /* 기준수수료 */
        String feeFxamYn,           /* 수수료정액여부 */
        String fgptChoGrpCd,        /* 사은품선택그룹 */
        String fgptChoCd,           /* 사은품선택 */
        String prmDupPrmitYn,       /* 선납중복허용여부 */
        String pdPrcDtlCd,          /* 상품가격상세코드 */
        List<ZpmzPromotionApplyDto.FreeGift> pmotFreeGifts  /* 사은품목록 */
    ) {}
}
