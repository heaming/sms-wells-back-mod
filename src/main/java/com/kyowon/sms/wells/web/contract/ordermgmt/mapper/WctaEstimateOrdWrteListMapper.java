package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateOrdWrteListDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaEstimateOrdWrteListMapper {
    PagingResult<WctaEstimateOrdWrteListDto.SearchRes> selectEstimateOrdWrteLists(
        WctaEstimateOrdWrteListDto.SearchReq dto,
        PageInfo pageInfo
    );

    String selectCntrStlmId(String cntrNo, String cntrSn);

    int updateCntrBas(String cntrNo);

    int updateCntrChHist(String cntrNo);

    int insertCntrChHist(String cntrNo);

    int updateCntrDtl(String cntrNo, String cntrSn);

    int updateCntrDchHist(String cntrNo, String cntrSn);

    int insertCntrDchHist(String cntrNo, String cntrSn);

    int updateCntrDtlStatChHist(String cntrNo, String cntrSn);

    int insertCntrDetailStatChangeHist(String cntrNo, String cntrSn);

    int updateCntrStlmRel(String cntrNo, String cntrSn);

    int updateCntrStlmBas(String cntrStlmId);

    int updateCntrStlmChHist(String cntrStlmId);

    int insertCntrStlmChHist(String cntrStlmId);

}
