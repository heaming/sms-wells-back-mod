package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSpayCanProcsDto;

@Mapper
public interface WctbSpayCanProcsMapper {
    WctbSpayCanProcsDto.SearchSsctCntrDtlRes selectCntrDtl(WctbSpayCanProcsDto.SearchReq dto);

    String selectCttOjId(WctbSpayCanProcsDto.SearchReq dto);

    int upateCntrDtl(WctbSpayCanProcsDto.SearchReq dto);

    int updateCntrDchHist(WctbSpayCanProcsDto.SearchReq dto);

    int insertCntrDchHist(WctbSpayCanProcsDto.SearchReq dto);

    int updateCntrDtlStatChHist(WctbSpayCanProcsDto.SearchReq dto);

    int insertCntrDtlStatChHist(WctbSpayCanProcsDto.SearchReq dto);

    int updateSsopCttBas(String cttOjId);

    int updateSsopCttChHist(String cttOjId);

    int insertSsopCttChHist(String cttOjId);
}
