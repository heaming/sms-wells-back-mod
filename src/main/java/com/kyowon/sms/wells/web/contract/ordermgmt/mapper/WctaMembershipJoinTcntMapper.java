package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipJoinTcntDto;

@Mapper
public interface WctaMembershipJoinTcntMapper {
    List<WctaMembershipJoinTcntDto.SearchRes> selectMembershipJoinTcnt(String cntrNo, String cntrSn);
}
