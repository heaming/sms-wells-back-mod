package com.kyowon.sms.wells.web.promotion.common.service;

import java.lang.reflect.Field;
import java.util.*;

import com.kyowon.sms.common.web.promotion.common.dvo.*;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.promotion.common.mapper.ZpmzPromotionApplyMapper;
import com.kyowon.sms.common.web.promotion.common.service.ZpmzPromotionApplyService;
import com.kyowon.sms.wells.web.promotion.common.converter.WpmzPromotionCheckConverter;
import com.kyowon.sms.wells.web.promotion.common.dto.WpmzPromotionCheckDto.SearchReq;
import com.kyowon.sms.wells.web.promotion.common.dto.WpmzPromotionCheckDto.SearchRes;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionInputDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionOutputDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionPriceDetailDvo;
import com.kyowon.sms.wells.web.promotion.common.mapper.WpmzPromotionCheckMapper;
import com.kyowon.sms.wells.web.promotion.zcommon.constants.PmPromotionConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpmzPromotionCheckService {

    private final WpmzPromotionCheckMapper mapper;
    private final WpmzPromotionCheckConverter converter;
    private final ZpmzPromotionApplyMapper commonMapper;
    private final MessageResourceService messageService;
    private final ZpmzPromotionApplyService applyService;


    /**
     * 조건에 맞는 프로모션 조회 (Swagger 테스트용)
     * @param req
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public List<SearchRes> getAppliedPromotions(SearchReq req) throws IllegalAccessException, NoSuchFieldException {
        return converter.mapAllWpmzPromotionOutputDvoToSearchRes(getAppliedPromotions(converter.mapSearchReqToWpmzPromotionInputDvo(req)));
    }


    /**
     * 조건에 맞는 프로모션 조회
     * @param paramDvo
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public List<WpmzPromotionOutputDvo> getAppliedPromotions(WpmzPromotionInputDvo paramDvo) throws IllegalAccessException, NoSuchFieldException {

        /* 1. 입력 파라미터 Validation Check */
        checkInputParameterValidation(paramDvo);

        /* 2. 프로모션용 dvo로 변경 */
        // 2.1. 입력 데이터 전체 항목 dvo로 이관
        List<ZpmzPromotionAtcDvo> inputDvos = new ArrayList<>();
        for (Field field : paramDvo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (StringUtils.isNotEmpty(Objects.toString(field.get(paramDvo), ""))){
                inputDvos.add(new ZpmzPromotionAtcDvo(field.getName(), Objects.toString(field.get(paramDvo), "")));
            }
        }

        // 2.2. 상품관련 항목 추가
        List<ZpmzPromotionAtcDvo> addClsfInputDvos = getAdditionalProductArticles(inputDvos);
        if (!addClsfInputDvos.isEmpty()) inputDvos.addAll(addClsfInputDvos);

        /* 3. 적용되는 프로모션 목록 조회 */
        List<ZpmzPromotionInfoDvo> appliedPromotions = applyService.getPromotionsByConditions(inputDvos);

        /* 4. 우선순위 고려한 프로모션 추출 */
        List<ZpmzPromotionInfoDvo> priorityRankedPromotions = applyService.getPromotionsByPriorityRank(appliedPromotions);

        /* 5. 프로모션 혜택 결과 정리 후 리턴 */
        return convertAppliedPromotionsToFinalResults(priorityRankedPromotions);
    }


    /**
     * 입력 파라미터 Validation Check
     * @param paramDvo
     * @throws IllegalAccessException
     */
    private void checkInputParameterValidation(WpmzPromotionInputDvo paramDvo) throws IllegalAccessException {
        // 1.1. 필수 항목 체크 - 기준상품코드/복합상품코드/상품가격상세코드
        boolean mandatoryAtcCheck = false;
        for (Field field : paramDvo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            boolean isExistMandatoryInputAtcs = Arrays.stream(PmPromotionConst.MANDATORY_INPUT_ATCS).anyMatch(field.getName()::equals);
            if (isExistMandatoryInputAtcs && StringUtils.isNotEmpty(Objects.toString(field.get(paramDvo), ""))){
                mandatoryAtcCheck = true;
                break;
            }
        }
        ValidAssert.isTrue(mandatoryAtcCheck, messageService.getMessage("MSG_ALT_REQ_INPUT_VALS", Arrays.toString(PmPromotionConst.MANDATORY_INPUT_ATCS)));

        // 1.2. 프로모션 유효성 체크
        if (StringUtils.isNotBlank(paramDvo.getPmotCd())) {
            boolean isVaild = StringUtils.equals("Y", Objects.toString(commonMapper.selectPromotionValidCheckYn(paramDvo.getPmotCd()), ""));
            BizAssert.isTrue(isVaild, messageService.getMessage("MSG_ALT_INVALID_ANYTHING", messageService.getMessage("MSG_TXT_PMOT"), paramDvo.getPmotCd()));
        }

        // 1.3. 기준상품 유효성 체크
        if (StringUtils.isNotBlank(paramDvo.getBasePdCd())) {
            boolean isVaild = StringUtils.equals("Y", Objects.toString(commonMapper.selectProductValidCheckYn(paramDvo.getBasePdCd()), ""));
            BizAssert.isTrue(isVaild, messageService.getMessage("MSG_ALT_INVALID_ANYTHING", messageService.getMessage("MSG_TXT_PRDT"), paramDvo.getBasePdCd()));
        }

        // 1.4. 연계상품(모종) 유효성 체크
        if (StringUtils.isNotBlank(paramDvo.getLkPdCd())) {
            boolean isVaild = StringUtils.equals("Y", Objects.toString(commonMapper.selectProductValidCheckYn(paramDvo.getLkPdCd()), ""));
            BizAssert.isTrue(isVaild, messageService.getMessage("MSG_ALT_INVALID_ANYTHING", messageService.getMessage("MSG_TXT_PRDT"), paramDvo.getLkPdCd()));
        }

        // 1.5. 패키지필수상품 유효성 체크
        if (StringUtils.isNotBlank(paramDvo.getPkgMndtPdCd())) {
            boolean isVaild = StringUtils.equals("Y", Objects.toString(commonMapper.selectProductValidCheckYn(paramDvo.getPkgMndtPdCd()), ""));
            BizAssert.isTrue(isVaild, messageService.getMessage("MSG_ALT_INVALID_ANYTHING", messageService.getMessage("MSG_TXT_PRDT"), paramDvo.getPkgMndtPdCd()));
        }
    }


    /**
     * 상품관련 항목 추가
     * @param inputDvos
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private List<ZpmzPromotionAtcDvo> getAdditionalProductArticles(List<ZpmzPromotionAtcDvo> inputDvos) throws NoSuchFieldException, IllegalAccessException {

        List<ZpmzPromotionAtcDvo> addClsfInputDvos = new ArrayList<>();
        for (ZpmzPromotionAtcDvo inputAtc : inputDvos) {
            /* 1. 상품가격상세코드가 존재하는 경우 */
            int priceIndex = inputAtc.getSysCmppNm().indexOf(PmPromotionConst.PRICE_CODE_POSTFIX);
            if (priceIndex > -1) {
                WpmzPromotionPriceDetailDvo priceDetailDvo = mapper.selectProductPriceDetailInfo(inputAtc.getVarbBasVal());
                if (priceDetailDvo != null) {
                    // 1.1. 상품/가격속성값에서 항목 추출 후 적용 (렌탈할인구분코드, 렌탈할인유형코드, 방문주기, 택배주기)
                    setArticlesInProductProps(priceDetailDvo);

                    // 1.2. 기준상품코드 / 기준상품분류코드
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getPdCd(), ""))) {
                        // 기준상품코드
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_BASE_PD_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_BASE_PD_CD, priceDetailDvo.getPdCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getPdCd());
                        // 기준상품분류코드
                        String pdClfCd = commonMapper.selectProductClassificationInfo(priceDetailDvo.getPdCd());
                        if (StringUtils.isNotBlank(pdClfCd)) {
                            Optional<ZpmzPromotionAtcDvo> clsfDvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_BASE_PD_CLSF_CD)).findAny();
                            if (clsfDvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_BASE_PD_CLSF_CD, pdClfCd));
                            else clsfDvoOpt.get().setVarbBasVal(pdClfCd);
                        }
                    }
                    // 1.3. 서비스코드
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getSvPdCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_SERVICE_PD_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_SERVICE_PD_CD, priceDetailDvo.getSvPdCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getSvPdCd());
                    }
                    // 1.4. 약정주기코드
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getStplPrdCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_STPL_PRD_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_STPL_PRD_CD, priceDetailDvo.getStplPrdCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getStplPrdCd());
                    }
                    // 1.5. 서비스유형코드
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getSvTpCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_SERVICE_TYPE_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_SERVICE_TYPE_CD, priceDetailDvo.getSvTpCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getSvTpCd());
                    }
                    // 1.6. 렌탈할인구분코드
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getRentalDscDvCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_RENTAL_DSC_DV_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_RENTAL_DSC_DV_CD, priceDetailDvo.getRentalDscDvCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getRentalDscDvCd());
                    }
                    // 1.7. 렌탈할인유형코드
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getRentalDscTpCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_RENTAL_DSC_TP_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_RENTAL_DSC_TP_CD, priceDetailDvo.getRentalDscTpCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getRentalDscTpCd());
                    }
                    // 1.8. 방문주기
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getSvVstPrdCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_SERVICE_VISIT_PERIOD_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_SERVICE_VISIT_PERIOD_CD, priceDetailDvo.getSvVstPrdCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getSvVstPrdCd());
                    }
                    // 1.9. 택배주기
                    if (StringUtils.isNotBlank(Objects.toString(priceDetailDvo.getPcsvPrdCd(), ""))) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_PARCEL_SERVICE_PERIOD_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_PARCEL_SERVICE_PERIOD_CD, priceDetailDvo.getPcsvPrdCd()));
                        else dvoOpt.get().setVarbBasVal(priceDetailDvo.getPcsvPrdCd());
                    }
                    // 1.10. BS주기
                    String svBfsvcPrdCd = Objects.toString(priceDetailDvo.getSvVstPrdCd(), "");
                    if (StringUtils.equals(Objects.toString(priceDetailDvo.getSvTpCd(), ""), PmPromotionConst.SERVICE_TYPE_CODE_PARCEL_1)
                        || StringUtils.equals(Objects.toString(priceDetailDvo.getSvTpCd(), ""), PmPromotionConst.SERVICE_TYPE_CODE_PARCEL_2)) {
                        svBfsvcPrdCd = Objects.toString(priceDetailDvo.getPcsvPrdCd(), "");
                    }
                    if (StringUtils.isNotBlank(svBfsvcPrdCd)) {
                        Optional<ZpmzPromotionAtcDvo> dvoOpt = inputDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_BEFORE_SERVICE_PERIOD_CD)).findAny();
                        if (dvoOpt.isEmpty()) addClsfInputDvos.add(new ZpmzPromotionAtcDvo(PmPromotionConst.SYS_CMPP_NM_BEFORE_SERVICE_PERIOD_CD, svBfsvcPrdCd));
                        else dvoOpt.get().setVarbBasVal(svBfsvcPrdCd);
                    }
                }
            }

            /* 2. 상품코드가 존재하는 경우 */
            // 상품분류항목 추가
            int pdIndex = inputAtc.getSysCmppNm().toLowerCase().indexOf(PmPromotionConst.PRODUCT_CODE_POSTFIX.toLowerCase());
            if (pdIndex > -1) {
                String sysCmppNm = inputAtc.getSysCmppNm().substring(0, pdIndex) + PmPromotionConst.CLASSIFICATION_CODE_POSTFIX;
                sysCmppNm = sysCmppNm.substring(0, 1).toUpperCase() + sysCmppNm.substring(1);
                Optional<ZpmzPromotionAtcDvo> clsfDvoOpt = inputDvos.stream().filter(item -> item.getSysCmppNm().toLowerCase().endsWith(PmPromotionConst.CLASSIFICATION_CODE_POSTFIX.toLowerCase())).findAny();
                if (clsfDvoOpt.isEmpty() || !StringUtils.equals(clsfDvoOpt.get().getSysCmppNm(), sysCmppNm)) {
                    addClsfInputDvos.add(new ZpmzPromotionAtcDvo(sysCmppNm, commonMapper.selectProductClassificationInfo(inputAtc.getVarbBasVal())));
                }
            }
        }
        return addClsfInputDvos;
    }


    /**
     * 상품/가격속성값에서 항목 추출 후 적용 (렌탈할인구분코드, 렌탈할인유형코드, 방문주기, 택배주기)
     * @param priceDetailDvo
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void setArticlesInProductProps(WpmzPromotionPriceDetailDvo priceDetailDvo) throws NoSuchFieldException, IllegalAccessException {
        // 상품가격조건특성 메타 조회
        WpmzPromotionPriceDetailDvo metaDvo = mapper.selectProductPriceMetaInfo();

        // 상품가격조건특성값에서 렌탈할인구분코드 추출
        Field rentalDscDvCdField = priceDetailDvo.getClass().getDeclaredField(JdbcUtils.convertUnderscoreNameToPropertyName(metaDvo.getRentalDscDvCd()));
        rentalDscDvCdField.setAccessible(true);
        priceDetailDvo.setRentalDscDvCd(Objects.toString(rentalDscDvCdField.get(priceDetailDvo), ""));

        // 상품가격조건특성값에서 렌탈할인유형코드 추출
        Field rentalDscTpCdField = priceDetailDvo.getClass().getDeclaredField(JdbcUtils.convertUnderscoreNameToPropertyName(metaDvo.getRentalDscTpCd()));
        rentalDscTpCdField.setAccessible(true);
        priceDetailDvo.setRentalDscTpCd(Objects.toString(rentalDscTpCdField.get(priceDetailDvo), ""));

        // 서비스상품속성값에서 방문주기 추출
        Field svVstPrdCdField = priceDetailDvo.getClass().getDeclaredField(JdbcUtils.convertUnderscoreNameToPropertyName(metaDvo.getSvVstPrdCd()));
        svVstPrdCdField.setAccessible(true);
        priceDetailDvo.setSvVstPrdCd(Objects.toString(svVstPrdCdField.get(priceDetailDvo), ""));

        // 서비스상품속성값에서 택배주기 추출
        Field pcsvPrdCdField = priceDetailDvo.getClass().getDeclaredField(JdbcUtils.convertUnderscoreNameToPropertyName(metaDvo.getPcsvPrdCd()));
        pcsvPrdCdField.setAccessible(true);
        priceDetailDvo.setPcsvPrdCd(Objects.toString(pcsvPrdCdField.get(priceDetailDvo), ""));
    }


    /**
     * 프로모션 혜택 결과 정리
     * @param appliedPromotions
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private List<WpmzPromotionOutputDvo> convertAppliedPromotionsToFinalResults(List<ZpmzPromotionInfoDvo> appliedPromotions) throws NoSuchFieldException, IllegalAccessException {

        List<WpmzPromotionOutputDvo> finalResults = new ArrayList<>();
        if (!appliedPromotions.isEmpty()) {
            for (ZpmzPromotionInfoDvo infoDvo : appliedPromotions) {

                WpmzPromotionOutputDvo resultDvo = new WpmzPromotionOutputDvo();

                /* 1. 프로모션코드 / 프로모션조건혜택관계ID / 비고 */
                resultDvo.setPmotCd(infoDvo.getPmotCd());
                resultDvo.setPmotCndtFvrRelId(infoDvo.getPmotCndtFvrRelId());
                resultDvo.setRmkCn(infoDvo.getRmkCn());

                /* 2. 프로모션 혜택정리 */
                if (infoDvo.getPmotDtlFvrs() != null) {

                    // WpmzPromotionOutputDvo 변수명 목록 추출
                    Field[] fields = resultDvo.getClass().getDeclaredFields();
                    List<String> outputNames = Arrays.stream(fields).map(field -> {
                        field.setAccessible(true);
                        return field.getName();
                    }).toList();

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
                        // 2.7. 사은품정보
                        else if (StringUtils.equals(fvrDvo.getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_FREE_GIFT)) {
                            if (resultDvo.getPmotFreeGifts() == null) {
                                resultDvo.setPmotFreeGifts(new ArrayList<>());
                            }
                            resultDvo.getPmotFreeGifts().add(new ZpmzPromotionFreeGiftDvo(fvrDvo.getVarbBasVal(), getFavorValue(infoDvo.getPmotDtlFvrs(), fvrDvo, PmPromotionConst.SYS_CMPP_NM_FREE_GIFT_QUANTITY)));
                        }
                        // 2.8. 기타 (단순변환항목)
                        else {
                            boolean isExistSimpleOutputAtcs = outputNames.contains(fvrDvo.getSysCmppNm());
                            if (isExistSimpleOutputAtcs && StringUtils.isNotEmpty(Objects.toString(fvrDvo.getVarbBasVal(), ""))) {
                                Field field = resultDvo.getClass().getDeclaredField(fvrDvo.getSysCmppNm());
                                field.setAccessible(true);
                                field.set(resultDvo, fvrDvo.getVarbBasVal());
                            }
                        }
                    }
                }

                /* 3. 프로모션 적용그룹/옵션 정보 */
                if (infoDvo.getPmotDtlCndts() != null) {
                    // 3.1. 프로모션 적용그룹 설정
                    Optional<ZpmzPromotionDtlCndtDvo> applyGroupOptional = infoDvo.getPmotDtlCndts().stream().filter((cndtDvo) -> StringUtils.equals(((ZpmzPromotionDtlCndtDvo)cndtDvo).getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_APPLY_GROUP_CODE)).findAny();
                    if (applyGroupOptional.isPresent() && StringUtils.isNotEmpty(applyGroupOptional.get().getVarbBasVal())) {
                        resultDvo.setPmotApyGrpCd(applyGroupOptional.get().getVarbBasVal());
                    }
                    // 3.2. 프로모션 적용옵션 설정
                    Optional<ZpmzPromotionDtlCndtDvo> applyOptOptional = infoDvo.getPmotDtlCndts().stream().filter((cndtDvo) -> StringUtils.equals(((ZpmzPromotionDtlCndtDvo)cndtDvo).getSysCmppNm(), PmPromotionConst.SYS_CMPP_NM_APPLY_OPTION_CODE)).findAny();
                    if (applyOptOptional.isPresent() && StringUtils.isNotEmpty(applyOptOptional.get().getVarbBasVal())) {
                        resultDvo.setPmotApyOptCd(applyOptOptional.get().getVarbBasVal());
                    }
                }
                finalResults.add(resultDvo);
            }
        }
        return finalResults;
    }


    /**
     * 특정 혜택값 추출
     * @param allFvrDvos
     * @param standardFvrDvo
     * @param valueSysCmppNm
     * @return
     */
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
