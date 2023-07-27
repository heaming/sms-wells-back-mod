package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.FindRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiPackageContractRelationMapper {
    List<FindRes> selectPackageContractRelations(FindReq dto);
}
