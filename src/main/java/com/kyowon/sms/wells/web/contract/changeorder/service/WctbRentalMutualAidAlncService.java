package com.kyowon.sms.wells.web.contract.changeorder.service;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalMutualAidAlncDto.SearchReq;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbRentalMutualAidAlncConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMutualAidAlncDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalMutualAidAlncMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalMutualAidAlncService {

    private final WctbRentalMutualAidAlncMapper mapper;
    private final WctbRentalMutualAidAlncConverter converter;

    public WctbRentalMutualAidAlncDvo getRentalMutuAlncCheck(SearchReq dto) {
        WctbRentalMutualAidAlncDvo dvo = converter.mapSearchReqToWctbRentalMutualAidAlncDvo(dto);

        String alncmpCd = dvo.getAlncmpCd(); // 제휴사코드
        String rentalDscDvCd = dvo.getRentalDscDvCd(); // 렌탈할인구분코드
        String rentalDscTpCd = dvo.getRentalDscTpCd(); // 렌탈할인유형코드
        String mchnChTpCd = dvo.getMchnChTpCd(); // 기기변경유형코드
        String mchnChYn = dvo.getMchnChYn(); // 기기변경여부

        if (!List.of("17", "54", "55").contains(alncmpCd)) {
            throw new BizException("제휴불가 제휴사코드입니다.");
        } else if (("8".equals(rentalDscDvCd) && "02".equals(rentalDscTpCd)) || "2".equals(rentalDscDvCd)) {
            throw new BizException("직월할인 상조제휴 불가합니다.");
        } else if ("Y".equals(mchnChYn) && !List.of("15", "16", "18", "19").contains(mchnChTpCd)) {
            throw new BizException("5 7 차월 미만 기변 상조제휴불가.");
        }

        List<WctbRentalMutualAidAlncDvo> rentalCheck = mapper.selectRentalMutuAlncCheck(dvo);
        if (CollectionUtils.isEmpty(rentalCheck)) {
            throw new BizException("직월할인 상조제휴 불가합니다.");
        }

        dvo.setCheckYn("Y");
        return dvo;
    }
}
