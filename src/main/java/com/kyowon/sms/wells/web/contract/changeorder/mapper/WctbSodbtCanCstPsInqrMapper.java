package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSodbtCanCstPsInqrDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSodbtCanCstPsInqrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbSodbtCanCstPsInqrMapper {
    PagingResult<WctbSodbtCanCstPsInqrDvo> selectCancelContracts(
        WctbSodbtCanCstPsInqrDto.SearchReq req, PageInfo pageInfo
    );
}
