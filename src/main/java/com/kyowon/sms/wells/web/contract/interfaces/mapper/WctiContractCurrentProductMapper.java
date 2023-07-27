package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCurrentProductDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCurrentProductDto.FindRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiContractCurrentProductMapper {
    List<FindRes> selectContractCurrentProduct(FindReq dto);
}
