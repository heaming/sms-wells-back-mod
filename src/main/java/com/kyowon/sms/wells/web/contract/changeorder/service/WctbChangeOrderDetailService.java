package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbChangeOrderDetailConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbChangeOrderDetailDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbChangeOrderDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctbChangeOrderDetailService {
    private final WctbChangeOrderDetailMapper mapper;
    private final WctbChangeOrderDetailConverter converter;

    public SearchRes getContractChangeArtcs(SearchReq dto) {
        // 계약변경이력
        List<SearchTbSsctCntrChHistRes> searchTbSsctCntrChHistResList = mapper.selectTbSsctCntrChHist(dto);
        // 계약상세변경이력
        List<SearchTbSsctCntrDchHistRes> searchTbSsctCntrDchHistResList = mapper.selectTbSsctCntrDchHist(dto);
        // 계약고객관계
        List<SearchTbSsctCntrCstRelRes> searchTbSsctCntrCstRelResList = mapper.selectTbSsctCntrCstRel(dto);
        // 계약파트너관계
        List<SearchTbSsctCntrPrtnrRelRes> searchTbSsctCntrPrtnrRelResList = mapper.selectTbSsctCntrPrtnrRel(dto);
        // 계약주소관계
        List<SearchTbSsctCntrAdrRelRes> searchTbSsctCntrAdrRelResList = mapper.selectTbSsctCntrAdrRel(dto);
        // 계약주소변경이력
        List<SearchTbSsctCntrAdrChHistRes> searchTbSsctCntrAdrChHistResList = converter
            .mapWctbChangeOrderDetailDvoToSearchTbSsctCntrAdrChHistRes(mapper.selectTbSsctCntrAdrChHist(dto));
        // 계약결제관계
        List<SearchTbSsctCntrStlmRelRes> searchTbSsctCntrStlmRelResList = mapper.selectTbSsctCntrStlmRel(dto);
        // 계약결제변경이력
        List<SearchTbSsctCntrStlmChHistRes> searchTbSsctCntrStlmChHistResList = mapper.selectTbSsctCntrStlmChHist(dto);
        // 계약WELLS상세변경이력
        List<SearchTbSsctCntrWellsDchHistRes> searchTbSsctCntrWellsDchHistResList = mapper
            .selectTbSsctCntrWellsDchHist(dto);
        // 계약관계
        List<SearchTbSsctCntrRelRes> searchTbSsctCntrRelResList = mapper.selectTbSsctCntrRel(dto);
        // 기기변경이력
        List<SearchTbSsctMchnChHistRes> searchTbSsctMchnChHistResList = mapper.selectTbSsctMchnChHist(dto);
        // 렌탈재약정변경이력
        List<SearchTbSsctRentalRstlChHistRes> searchTbSsctRentalRstlChHistResList = mapper
            .selectTbSsctRentalRstlChHist(dto);

        return new SearchRes(
            searchTbSsctCntrChHistResList, searchTbSsctCntrDchHistResList, searchTbSsctCntrCstRelResList,
            searchTbSsctCntrPrtnrRelResList, searchTbSsctCntrAdrRelResList, searchTbSsctCntrAdrChHistResList,
            searchTbSsctCntrStlmRelResList, searchTbSsctCntrStlmChHistResList, searchTbSsctCntrWellsDchHistResList,
            searchTbSsctCntrRelResList, searchTbSsctMchnChHistResList, searchTbSsctRentalRstlChHistResList
        );
    }
}
