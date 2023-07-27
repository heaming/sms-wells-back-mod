package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaChdvcRsgTpInqrDto.SearchRes;

@Mapper
public interface WctaChdvcRsgTpInqrMapper {
    List<SearchRes> selectChdvcRsgTpInqr(String ojCntrNo, String ojCntrSn);
}
