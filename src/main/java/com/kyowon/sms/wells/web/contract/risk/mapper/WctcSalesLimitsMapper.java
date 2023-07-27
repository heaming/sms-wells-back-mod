package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.FindBlacklistRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchEntrpJLmOjReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchEntrpJLmOjRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcSalesLimitsMapper {
    FindBlacklistRes selectBlacklistInfos(String cntrNo, int cntrSn);

    PagingResult<SearchBlacklistRes> selectBlacklistPages(SearchBlacklistReq dto, PageInfo pageInfo);

    List<SearchBlacklistRes> selectBlacklistPages(SearchBlacklistReq dto);

    int insertBlacklist(@Param("item")
    WctcSellLimitOjIzDvo dvo);

    int updateBlacklist(WctcSellLimitOjIzDvo dvo);

    int deleteBlacklist(String sellLmId);

    PagingResult<SearchEntrpJLmOjRes> selectEntrepreneurJoinLmOjss(
        SearchEntrpJLmOjReq dto,
        PageInfo pageInfo
    );

    List<SearchEntrpJLmOjRes> selectEntrepreneurJoinLmOjss(
        SearchEntrpJLmOjReq dto
    );

    int insertEntrepreneurJoinLmOjss(WctcSellLimitOjIzDvo dvo);

    int updateEntrepreneurJoinLmOjss(WctcSellLimitOjIzDvo dvo);

    int deleteEntrepreneurJoinLmOjss(String sellLmId);

    String selectEntrepreneurJoinLmOjssCheck(String sellLmId);
}
