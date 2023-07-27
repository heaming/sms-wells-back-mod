package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdConfirmMshPsInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdConfirmMshPsInqrDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdConfirmMshPsInqrMapper {

    PagingResult<SearchRes> selectConfirmMshPsInqrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectConfirmMshPsInqrPages(
        SearchReq dto
    );
}
