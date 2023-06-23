package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbSafetyAccidentConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.EditReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.FindRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbSafetyAccidentDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSafetyAccidentMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-MP-U-0209M01 안전사고 관리 API
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.19
 */
@Service
@RequiredArgsConstructor
public class WsnbSafetyAccidentService {

    private final WsnbSafetyAccidentMapper mapper;
    private final WsnbSafetyAccidentConverter converter;

    /**
     * 안전사고 관리 조회 - 페이징
     *
     * @param dto
     * @return 조회결과
     */
    public PagingResult<SearchRes> getSafetyAccidents(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnbSafetyAccidentDvo> dvo = new PagingResult<WsnbSafetyAccidentDvo>();

        dvo = mapper.selectSafetyAccidents(dto, pageInfo);
        PagingResult<SearchRes> pagingResult = converter.mapWsnbSafetyAccidentDvoToSearchRes(dvo);
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 안전사고 결과 조회 - 단건
     *
     * @param acdnRcpId
     * @return 조회결과
     */
    public FindRes getSafetyAccident(String acdnRcpId) {
        WsnbSafetyAccidentDvo dvo = mapper.selectSafetyAccident(acdnRcpId);
        return converter.mapWsnbSafetyAccidentDvoToFindRes(dvo);
    }

    /**
     * 안전사고 결과 저장
     *
     * @param dto
     */
    public int editSafetyAccident(EditReq dto) {
        int processCount = 0;
        WsnbSafetyAccidentDvo dvo = converter.mapEditReqToWsnbSafetyAccidentDvo(dto);
        processCount += mapper.updateSafetyAccident(dvo);

        return processCount;
    }
}
