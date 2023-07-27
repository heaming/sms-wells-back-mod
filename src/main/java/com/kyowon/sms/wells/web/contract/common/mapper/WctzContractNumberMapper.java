package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzContractNumberDto.SearchRes;

@Mapper
public interface WctzContractNumberMapper {
    SearchRes selectNewContractNumber();

    SearchRes selectNewContractSn(String cntrNo);
}
