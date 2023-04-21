package com.kyowon.sms.wells.web.service.common.mapper;

import static com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnzOrganizationMapper {
    List<SearchManagerOgRes> selectGeneralDivisions();

    List<SearchManagerOgRes> selectRegionalGroups(String ogId);

    List<SearchManagerOgRes> selectBranchs(String ogId);

    List<SearchManagerRes> selectManagers(SearchPrtnrReq dto);

    List<SearchEngineerOgRes> selectServiceCenters();

    List<SearchEngineerRes> selectEngineers(SearchPrtnrReq dto);
}
