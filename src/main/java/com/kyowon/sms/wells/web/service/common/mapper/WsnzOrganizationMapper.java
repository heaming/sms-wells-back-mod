package com.kyowon.sms.wells.web.service.common.mapper;

import static com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WsnzOrganizationMapper {
    List<SearchManagerOgRes> selectGeneralDivisions(
        @Param("authYn")
        String authYn
    );

    List<SearchManagerOgRes> selectRegionalGroups(
        @Param("ogId")
        String ogId,
        @Param("authYn")
        String authYn
    );

    List<SearchManagerOgRes> selectBranchs(
        @Param("ogId")
        String ogId,
        @Param("authYn")
        String authYn
    );

    List<SearchManagerRes> selectManagers(SearchPrtnrReq dto);

    List<SearchEngineerOgRes> selectServiceCenters(
        @Param("authYn")
        String authYn
    );

    List<SearchEngineerRes> selectEngineers(SearchPrtnrReq dto);
}
