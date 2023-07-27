package com.kyowon.sms.wells.web.contract.risk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcUserSellLimitDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcUserSellLimitMgtMapper {

    PagingResult<SearchRes> selectSellLimitLists(SearchReq dto);

    String selecBaseCdCheck(SaveReq dto);

    String selecSellBaseSn(String sellBaseId, String sellBaseCd);

    int insertSellBaseBas(@Param("item")
    WctcUserSellLimitDvo userSellLimitMngt);

    int insertSellBaseDtl(WctcUserSellLimitDvo userSellLimitMngt);

    int updateSellBaseBas(WctcUserSellLimitDvo userSellLimitMngt);

    int updateSellBaseDtl(WctcUserSellLimitDvo userSellLimitMngt);

    int removeSellBaseBas(String sellBaseId);

    int removeSellBaseDtl(String sellBaseId);
}
