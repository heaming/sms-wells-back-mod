package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKakaotalksDto.SearchRes;

@Mapper
public interface WctaKakaotalksMapper {

    public List<SearchRes> selectKakaotalkFwIzs(String cntrNo, String cntrSn, List<String> tempCodeList);

}
