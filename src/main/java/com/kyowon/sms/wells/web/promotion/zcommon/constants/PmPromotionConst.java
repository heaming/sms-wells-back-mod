package com.kyowon.sms.wells.web.promotion.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class PmPromotionConst {

    // 프로모션 - 공통
    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/promotion";

    public static final String[] MANDATORY_INPUT_ATCS = new String[]{"pmotCd", "basePdCd", "basePdPrcDtlCd"};  // 필수 입력항목
    public static final String PRODUCT_CODE_POSTFIX = "PdCd";
    public static final String CLASSIFICATION_CODE_POSTFIX = "PdClsfCd";
    public static final String PRICE_CODE_POSTFIX = "PrcDtlCd";

    /* 시스템항목명 */
    public static final String SYS_CMPP_NM_BASE_PD_CD = "basePdCd";                 // (시스템항목명) 기준상품코드
    public static final String SYS_CMPP_NM_BASE_PD_CLSF_CD = "basePdClsfCd";        // (시스템항목명) 기준상품분류코드
    public static final String SYS_CMPP_NM_SERVICE_PD_CD = "svPdCd";                // (시스템항목명) 서비스코드
    public static final String SYS_CMPP_NM_SERVICE_TYPE_CD = "svTpCd";              // (시스템항목명) 서비스유형코드
    public static final String SYS_CMPP_NM_STPL_PRD_CD = "stplPrdCd";               // (시스템항목명) 약정주기코드
    public static final String SYS_CMPP_NM_RENTAL_DSC_DV_CD = "rentalDscDvCd";      // (시스템항목명) 렌탈할인구분코드
    public static final String SYS_CMPP_NM_RENTAL_DSC_TP_CD = "rentalDscTpCd";      // (시스템항목명) 렌탈할인유형코드
    public static final String SYS_CMPP_NM_SERVICE_VISIT_PERIOD_CD = "svVstPrdCd";  // (시스템항목명) 방문주기
    public static final String SYS_CMPP_NM_PARCEL_SERVICE_PERIOD_CD = "pcsvPrdCd";  // (시스템항목명) 택배주기
    public static final String SYS_CMPP_NM_BEFORE_SERVICE_PERIOD_CD = "svBfsvcPrdCd"; // (시스템항목명) BS주기
    public static final String SYS_CMPP_NM_DISCOUNT_FIX_AMOUNT = "dscFxam";         // (시스템항목명) 할인금액
    public static final String SYS_CMPP_NM_DISCOUNT_MONTHS = "dscMcnt";             // (시스템항목명) 할인개월
    public static final String SYS_CMPP_NM_DISCOUNT_TYPE = "dscTpCd";               // (시스템항목명) 프로모션할인유형코드
    public static final String SYS_CMPP_NM_FEE_UNIT_CODE = "ackmtPerfUnitCd";       // (시스템항목명) 추가실적단위코드
    public static final String SYS_CMPP_NM_FEE_QUANTITY = "ackmtPerfQty";           // (시스템항목명) 추가실적(수)
    public static final String SYS_CMPP_NM_STANDARD_FEE_AMOUNT = "stdFeeAmt";       // (시스템항목명) 기준수수료
    public static final String SYS_CMPP_NM_FEE_FIX_AMOUNT_YN = "feeFxamYn";         // (시스템항목명) 수수료정액여부
    public static final String SYS_CMPP_NM_PREPAY_DUP_PERMIT_YN = "prmDupPrmitYn";  // (시스템항목명) 선납중복허용여부
    public static final String SYS_CMPP_NM_FREE_GIFT_CHOICE_GROUP = "fgptChoGrpCd"; // (시스템항목명) 사은품선택그룹
    public static final String SYS_CMPP_NM_FREE_GIFT_CHOICE = "fgptChoCd";          // (시스템항목명) 사은품선택
    public static final String SYS_CMPP_NM_FREE_GIFT = "fgptCd";                    // (시스템항목명) 사은품상품코드
    public static final String SYS_CMPP_NM_FREE_GIFT_QUANTITY = "fgptQty";          // (시스템항목명) 사은품수량

    /* 프로모션할인유형코드 */
    public static final String DISCOUNT_TYPE_CODE_PRICE = "01";             // 가격할인
    public static final String DISCOUNT_TYPE_CODE_RENTAL = "02";            // 렌탈할인
    public static final String DISCOUNT_TYPE_CODE_MONTHS = "04";            // 할인개월
    public static final String DISCOUNT_TYPE_CODE_FREE_MONTHS = "05";       // 무료개월

    /* 추가실적(수수료)단위코드 */
    public static final String FEE_UNIT_CODE_COUNT = "01";                  // 건수
    public static final String FEE_UNIT_CODE_POINT = "02";                  // 포인트
    public static final String FEE_UNIT_CODE_AMOUNT = "03";                 // 금액

    /* 서비스유형코드 */
    public static final String SERVICE_TYPE_CODE_PARCEL_1 = "7";            // 택배1
    public static final String SERVICE_TYPE_CODE_PARCEL_2 = "8";            // 택배2
}
