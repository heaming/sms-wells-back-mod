package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalProductPriceCalculationDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaRentalProductPriceCalculationMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaRentalProductPriceCalculationService {
    private final WctaRentalProductPriceCalculationMapper mapper;

    public List<WctaRentalProductPriceCalculationDvo> getRentalFeeCalculation(
        WctaRentalProductPriceCalculationDvo paramDvo
    ) {
        // 파라미터 유효성 체크
        ValidAssert.hasText(paramDvo.getPdCd()); // 상품코드
        ValidAssert.hasText(paramDvo.getSellTpCd()); // 판매유형코드
        ValidAssert.hasText(paramDvo.getSellChnlCd()); // 판매채널코드

        return mapper.selectRentalFeeCalculation(paramDvo);
    }

    public List<WctaRentalProductPriceCalculationDvo> getRecoveryRentalFee(String cntrNo, String cntrSn) {
        // 파라미터 유효성 체크
        ValidAssert.hasText(cntrNo); // 계약번호
        ValidAssert.hasText(cntrSn); // 계약일련번호

        // 반환값
        // cntrNo; // 계약번호
        // cntrSn; // 계약일련번호
        // basePdCd; // 기준상품코드
        // orgRtPrc; // 원복렌탈료
        // currRtPrc; // 현재렌탈료

        return mapper.selectRecoveryRentalFee(cntrNo, cntrSn);
    }
}
