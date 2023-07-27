package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCompanyInstallDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaCompanyInstallMapper {

    PagingResult<SearchRes> selectCompanyInstallPages(
        WctaCompanyInstallDvo dvo,
        PageInfo pageInfo
    );

    List<SearchRes> selectCompanyInstallPages(
        WctaCompanyInstallDvo dvo
    );
}
