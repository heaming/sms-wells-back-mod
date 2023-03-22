package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbPointmallStatusInfConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbPointmallStatusInfDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbPointmallStatusInfMapper;

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
public class WsnbPointmallStatusInfService {
    private final WsnbPointmallStatusInfMapper mapper;
    private final WsnbPointmallStatusInfConverter converter;

    public List<SearchRes> getPointmallStatusInfs(SearchReq dto) {

        List<SearchRes> result = mapper.selectPointmallStatusInfs(dto);
        if (result.size() == 0) {
            WsnbPointmallStatusInfDvo dvo = new WsnbPointmallStatusInfDvo();
            dvo.setWK_ACPTE_STAT_CD("N");
            dvo.setWK_ACPTE_DT("00000000");
            dvo.setWK_ACPTE_HH("000000");
            dvo.setVST_CNFMDT("00000000");
            dvo.setVST_CNFM_HH("000000");
            dvo.setWK_EXCN_DT("00000000");
            dvo.setWK_EXCN_HH("000000");
            dvo.setWK_PRGS_STAT_CD("00");
            dvo.setRTNGD_YN("N");

            SearchRes res = converter.mapWsnbPointmallStatusInfDvoToSearchRes(dvo);
            result.add(res);
        }
        return result;
    }
}
