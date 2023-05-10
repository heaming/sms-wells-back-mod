package com.kyowon.sms.wells.web.promotion.common.service;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionAtcDvo;
import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionDtlFvrDvo;
import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionInfoDvo;
import com.kyowon.sms.common.web.promotion.common.service.ZpmzPromotionApplyService;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionFreeGiftDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionInputDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionOutputDvo;
import com.kyowon.sms.wells.web.promotion.common.mapper.WpmzPromotionCheckMapper;
import com.kyowon.sms.wells.web.promotion.zcommon.constants.PmPromotionConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpmzPromotionCheckService {

    private final WpmzPromotionCheckMapper mapper;
    private final MessageResourceService messageService;
    private final ZpmzPromotionApplyService applyService;

    public List<WpmzPromotionOutputDvo> getAppliedPromotionList(WpmzPromotionInputDvo paramDvo) throws IllegalAccessException, NoSuchFieldException {

        /* 1. 입력 파라미터 Validation Check */
        // 1.1. 필수 항목 체크 - 기준상품코드/복합상품코드/상품가격상세코드
        boolean mandatoryAtcCheck = false;
        for (Field field : paramDvo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (StringUtils.isNotEmpty(Objects.toString(field.get(paramDvo), "")) && Arrays.stream(PmPromotionConst.MANDATORY_INPUT_ATCS).anyMatch(field.getName()::equals)){
                mandatoryAtcCheck = true;
                break;
            }
        }
        ValidAssert.isTrue(mandatoryAtcCheck, messageService.getMessage("MSG_ALT_REQ_INPUT_VAL", PmPromotionConst.MANDATORY_INPUT_ATCS));

        /* 2. 프로모션용 dvo로 변경 */
        // 2.1. 입력 데이터 전체 항목 dvo로 이관
        List<ZpmzPromotionAtcDvo> inputDvos = new ArrayList<>();
        for (Field field : paramDvo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (StringUtils.isNotEmpty(Objects.toString(field.get(paramDvo), ""))){
                inputDvos.add(new ZpmzPromotionAtcDvo(field.getName(), Objects.toString(field.get(paramDvo), "")));
            }
        }

        // 2.2. 상품코드가 존재하는 경우, 상품분류 항목 추가
        List<ZpmzPromotionAtcDvo> addClsfInputDvos = new ArrayList<>();
        for (ZpmzPromotionAtcDvo inputAtc : inputDvos) {
            int pdIndex = inputAtc.getSysCmppNm().toLowerCase().indexOf(PmPromotionConst.PRODUCT_CODE_POSTFIX);
            if (pdIndex > -1) {   // 상품코드인 경우
                String sysCmppNm = inputAtc.getSysCmppNm().substring(0, pdIndex) + PmPromotionConst.CLASSIFICATION_CODE_POSTFIX;
                sysCmppNm = sysCmppNm.substring(0, 1).toUpperCase() + sysCmppNm.substring(1);
                Optional<ZpmzPromotionAtcDvo> clsfDvoOpt = inputDvos.stream().filter(item -> item.getSysCmppNm().toLowerCase().endsWith(PmPromotionConst.CLASSIFICATION_CODE_POSTFIX.toLowerCase())).findAny();
                if (clsfDvoOpt.isEmpty() || !StringUtils.equals(clsfDvoOpt.get().getSysCmppNm(), sysCmppNm)) {
                    addClsfInputDvos.add(new ZpmzPromotionAtcDvo(sysCmppNm, mapper.selectProductClassificationInfo(inputAtc.getVarbBasVal())));
                }
            }
        }
        if (!addClsfInputDvos.isEmpty()) inputDvos.addAll(addClsfInputDvos);

        /* 3. 적용되는 프로모션 목록 조회 */
        List<ZpmzPromotionInfoDvo> appliedPromotions = applyService.getPromotionsByConditions(inputDvos);

        /* 4. 우선순위 고려한 프로모션 추출 */
        // TODO 업무 협의후 로직 추가 예정

        /* 5. 프로모션 혜택 결과 정리 후 리턴 */
        return convertAppliedPromotionsToFinalResults(appliedPromotions);
    }

    private List<WpmzPromotionOutputDvo> convertAppliedPromotionsToFinalResults(List<ZpmzPromotionInfoDvo> appliedPromotions) {

        List<WpmzPromotionOutputDvo> finalResults = new ArrayList<>();
        if (!appliedPromotions.isEmpty()) {
            for (ZpmzPromotionInfoDvo infoDvo : appliedPromotions) {

                WpmzPromotionOutputDvo resultDvo = new WpmzPromotionOutputDvo();

                /* 1. 프로모션코드 */
                resultDvo.setPmotCd(infoDvo.getPmotCd());

                /* 2. 프로모션 혜택정리 */
                if (infoDvo.getPmotDtlFvrs() != null) {
                    for (ZpmzPromotionDtlFvrDvo fvrDvo : infoDvo.getPmotDtlFvrs()) {
                        // 2.0. 혜택 상세항목이 아닌 경우 continue (Leaf Node만 체크)
                        if (StringUtils.isEmpty(Objects.toString(fvrDvo.getSysCmppNm(), ""))) continue;

                        // 2.1. 상품가격상세코드
                        if (fvrDvo.getSysCmppNm().endsWith(PmPromotionConst.PRICE_CODE_POSTFIX)) {
                            resultDvo.setPdPrcDtlCd(fvrDvo.getVarbBasVal());
                        }
                        // 2.2. 가격 관련 항목
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_DISCOUNT_TYPE)) {
                            // 2.2.1. 할인금액
                            if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.DISCOUNT_TYPE_CODE_PRICE)) {
                                resultDvo.setDscFxam(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_DISCOUNT_FIX_AMOUNT));
                            }
                            // 2.2.2. 렌탈금액
                            else if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.DISCOUNT_TYPE_CODE_RENTAL)) {
                                resultDvo.setRentalFxam(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_DISCOUNT_FIX_AMOUNT));
                            }
                            // 2.2.3. 할인개월
                            else if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.DISCOUNT_TYPE_CODE_MONTHS)) {
                                resultDvo.setDscMcnt(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_DISCOUNT_MONTHS));
                            }
                            // 2.2.4. 무료개월
                            else if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.DISCOUNT_TYPE_CODE_FREE_MONTHS)) {
                                resultDvo.setFreeMcnt(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_DISCOUNT_MONTHS));
                            }
                        }
                        // 2.3. 인정실적(수수료) 관련 항목
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_FEE_UNIT_CODE)) {
                            // 2.3.1. 인정건수
                            if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.FEE_UNIT_CODE_COUNT)) {
                                resultDvo.setAckmtCt(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_FEE_QUANTITY));
                            }
                            // 2.3.2. 인정실적
                            else if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.FEE_UNIT_CODE_AMOUNT)) {
                                resultDvo.setAckmtAmt(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_FEE_QUANTITY));
                            }
                            // 2.3.3. 인정실적(P)
                            else if (StringUtils.equals(fvrDvo.getVarbBasVal(), PmPromotionConst.FEE_UNIT_CODE_POINT)) {
                                resultDvo.setAckmtRt(getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_FEE_QUANTITY));
                            }
                        }
                        // 2.4. 기준수수료
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_STANDARD_FEE_AMOUNT)) {
                            resultDvo.setStdFeeAmt(fvrDvo.getVarbBasVal());
                        }
                        // 2.5. 수수료정액여부
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_FEE_FIX_AMOUNT_YN)) {
                            resultDvo.setFeeFxamYn(fvrDvo.getVarbBasVal());
                        }
                        // 2.6. 선납중복허용여부
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_PREPAY_DUP_PERMIT_YN)) {
                            resultDvo.setPrmDupPrmitYn(fvrDvo.getVarbBasVal());
                        }
                        // 2.7. 사은품선택그룹
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_FREE_GIFT_CHOICE_GROUP)) {
                            resultDvo.setFgptChoGrpCd(fvrDvo.getVarbBasVal());
                        }
                        // 2.8. 사은품선택
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_FREE_GIFT_CHOICE)) {
                            resultDvo.setFgptChoCd(fvrDvo.getVarbBasVal());
                        }
                        // 2.9. 사은품정보
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_FREE_GIFT)) {
                            if (resultDvo.getPmotFreeGifts() == null) {
                                resultDvo.setPmotFreeGifts(new ArrayList<>());
                            }
                            resultDvo.getPmotFreeGifts().add(new WpmzPromotionFreeGiftDvo(fvrDvo.getVarbBasVal(), getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_FREE_GIFT_QUANTITY)));
                        }
                    }
                }
                finalResults.add(resultDvo);
            }
        }
        return finalResults;
    }

    private String getFavorValue(List<ZpmzPromotionDtlFvrDvo> allFvrDvos, ZpmzPromotionDtlFvrDvo standardFvrDvo, String valueSysCmppNm) {
        Optional<ZpmzPromotionDtlFvrDvo> valDvoOpt = allFvrDvos.stream()
            .filter(item -> StringUtils.equals(item.getHgrPmotFvrId(), standardFvrDvo.getHgrPmotFvrId())
                         && StringUtils.equals(item.getSysCmppNm(), valueSysCmppNm))
            .findAny();
        String returnValue = "";
        if (valDvoOpt.isPresent() && StringUtils.isNotEmpty(valDvoOpt.get().getVarbBasVal())) {
            returnValue = valDvoOpt.get().getVarbBasVal();
        }
        return returnValue;
    }
}
