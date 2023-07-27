package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaKMembersWellsOrdInqrDvo;

@Mapper
public interface WctaKMembersWellsOrdInqrMapper {
    List<WctaKMembersWellsOrdInqrDvo> selectKMembersWellsOrdInqrs(
        String cntrNo, String cntrSn, String cmnSfkVal
    );
}
