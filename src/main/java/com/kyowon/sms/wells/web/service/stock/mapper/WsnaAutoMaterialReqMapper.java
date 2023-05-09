package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.SearchRes;

@Mapper
public interface WsnaAutoMaterialReqMapper {

    List<SearchRes> selectOstrAkNo();

    int insertAutoMaterialEngineerReq(List<SearchRes> list);

    int insertAutoMaterialBranchReq(List<SearchRes> list);
}
