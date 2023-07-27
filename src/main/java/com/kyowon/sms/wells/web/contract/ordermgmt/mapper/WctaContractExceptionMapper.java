package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjDtlDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractExceptionMapper {

    PagingResult<SearchRes> selectContractExceptionPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectContractExceptionPages(
        SearchReq dto
    );

    int insertContractExceptionBas(@Param("item")
    WctaContractExOjBasDvo dvo);

    int updateContractExceptionBas(WctaContractExOjBasDvo dvo);

    int deleteContractExceptionBas(String exProcsId);

    int insertContractExceptionDtl(WctaContractExOjDtlDvo dvo);

    int deleteContractExceptionDtl(String exProcsId);
}
