package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsnbPointmallStatusConverter;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbPointmallStatusDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbPointmallStatusDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbPointmallStatusDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbPointmallStatusMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-I-0017 포인트몰 상태정보 조회 트랜잭션
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.21
 */
@Service
@RequiredArgsConstructor
public class WsnbPointmallStatusService {
    private final WsnbPointmallStatusMapper mapper;
    private final WsnbPointmallStatusConverter converter;

    public List<SearchRes> getPointmallStatuses(SearchReq dto) {

        List<SearchRes> result = mapper.selectPointmallStatuses(dto);
        if (result.size() == 0) {
            WsnbPointmallStatusDvo dvo = new WsnbPointmallStatusDvo();
            dvo.setWkAcpteStatCd("N");
            dvo.setWkAcpteDt("00000000");
            dvo.setWkAcpteHh("000000");
            dvo.setVstCnfmdt("00000000");
            dvo.setVstCnfmHh("000000");
            dvo.setWkExcnDt("00000000");
            dvo.setWkExcnHh("000000");
            dvo.setWkPrgsStatCd("00");
            dvo.setRtngdYn("N");

            SearchRes res = converter.mapWsnbPointmallStatusDvoToSearchRes(dvo);
            result.add(res);
        }
        return result;
    }
}
