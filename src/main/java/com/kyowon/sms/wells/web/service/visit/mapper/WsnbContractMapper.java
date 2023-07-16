package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractDvo;

@Mapper
public interface WsnbContractMapper {

    Optional<WsnbContractDvo> selectContract(String cntrNo, String cntrSn);
}
