package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchPrtnrReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchPrtnrRes;

@Mapper
public interface WsnzOrganizationMapper {
    List<SearchOrganizationRes> selectGeneralDivisions();

    List<SearchOrganizationRes> selectRegionalGroups(String ogId);

    List<SearchOrganizationRes> selectBranchs(String ogId);

    List<SearchPrtnrRes> selectManagers(SearchPrtnrReq dto);
}
