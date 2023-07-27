package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbPaymentCancelNextdayConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPaymentCancelNextdayDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbPaymentCancelNextdayDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbPaymentCancelNextdayMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbPaymentCancelNextdayService {
    private final WctbPaymentCancelNextdayMapper mapper;
    private final WctbPaymentCancelNextdayConverter converter;

    @Transactional
    public WctbPaymentCancelNextdayDto.SearchRes saveKMembersCancelProcss(String useCls, String cntrNo, int cntrSn) {
        WctbPaymentCancelNextdayDvo dvo = new WctbPaymentCancelNextdayDvo();
        if (StringUtils.isEmpty(cntrNo) || cntrSn == 0) {
            dvo.setPrcsRslt("N");
            dvo.setDtlMsg("주문번호를 확인하십시오");
            return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
        }
        if (!useCls.equals("5")) {
            dvo.setPrcsRslt("N");
            dvo.setDtlMsg("사용구분을 확인하십시오");
            return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
        }
        dvo = mapper.selectSsctCntrDtl(cntrNo, cntrSn);
        if (dvo == null) {
            dvo = new WctbPaymentCancelNextdayDvo();
            dvo.setPrcsRslt("N");
            dvo.setDtlMsg("등록된 해당자료가 없습니다!");
            return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
        }
        if (dvo.getCntrDtlStatCd().equals("303")) {
            dvo.setPrcsRslt("N");
            dvo.setDtlMsg("기준일자 이전에 취소가 된 건");
            return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
        }
        if (StringUtils.isNotEmpty(dvo.getCntrPdStrtdt())) {
            dvo.setPrcsRslt("N");
            dvo.setDtlMsg("삭제불가！처리완료된자료입니다");
            return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
        }
        if (dvo.getAlncmpCd().equals("46")) {
            dvo.setPrcsRslt("N");
            dvo.setDtlMsg("삭제불가！포인트몰 주문이 아닙니다!");
            return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
        }
        mapper.updateSsctCntrDtl(cntrNo, cntrSn);
        mapper.updateCntrDtlStatChHist(cntrNo, cntrSn);
        mapper.insertCntrDtlStatChHist(cntrNo, cntrSn);
        mapper.updateCntrDchHist(cntrNo, cntrSn);
        mapper.insertCntrDchHist(cntrNo, cntrSn);
        mapper.updateSsopCttBas(cntrNo, cntrSn);
        mapper.updateSsopCttChHist(cntrNo, cntrSn);
        mapper.insertSsopCttChHist(cntrNo, cntrSn);
        dvo.setPrcsRslt("Y");

        return converter.mapWctbPaymentCancelNextdayDvoToSearchRes(dvo);
    }
}
