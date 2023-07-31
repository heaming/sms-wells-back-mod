package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCancelDvo;

@Mapper
public interface WctiContractCancelMapper {

    Optional<WctiContractCancelDvo> selectContract(String cntrNo, String cntrSn);
}
