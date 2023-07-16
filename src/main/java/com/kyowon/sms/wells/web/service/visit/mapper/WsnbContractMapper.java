package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractRelationDvo;

@Mapper
public interface WsnbContractMapper {

    Optional<WsnbContractDvo> selectContract(String cntrNo, String cntrSn);

    List<WsnbContractRelationDvo> selectContractRelations(String cntrNo, String cntrSn, String cntrRelDtlCd);
}
