package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaInterestFreeIstmYnConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInterestFreeIstmYnDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaInterestFreeIstmYnDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaInterestFreeIstmYnMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaInterestFreeIstmYnService {

    private final WctaInterestFreeIstmYnMapper mapper;
    private final WctaInterestFreeIstmYnConverter converter;

    public WctaInterestFreeIstmYnDvo getInterestFreeIstmYn(SearchReq dto) {
        WctaInterestFreeIstmYnDvo dvo = converter.mapSearchReqToWctaInterestDvo(dto); // 파라미터 변환

        // 파라미터 세팅
        String sellTpCd = mapper.selectSellTypeCode(dto);
        String crpDscDvCd = dvo.getCrpDscDvCd();

        // 파라미터 검사 및 조건 분기처리
        BizAssert.isFalse(StringUtils.isEmpty(sellTpCd), "MSG_ALT_NEX_PD_CD");
        BizAssert.isFalse(("1".equals(sellTpCd) && "F".equals(crpDscDvCd)), "MSG_ALT_NO_INSTLMNT_CPR_ORD_DEC");

        int resMnthIstAmt = dvo.getCshBlam() / dvo.getIstmMcn(); // 잔여월할부금 계산

        dvo.setResMnthIstmAmt(resMnthIstAmt); // 잔여월할부금
        dvo.setIntfrCheckYn("Y"); // 무이자여부
        dvo.setErrYn("N"); // 오류여부

        return dvo;
    }
}
