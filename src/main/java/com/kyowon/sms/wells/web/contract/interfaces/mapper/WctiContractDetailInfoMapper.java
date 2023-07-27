package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindClctamRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindMembershipRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindStlmRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailInfoDvo;

@Mapper
public interface WctiContractDetailInfoMapper {
    Optional<WctiContractDetailInfoDvo> selectContractDetail(FindReq dto);

    FindStlmRes selectContractDetailStlm(FindReq dto);

    FindClctamRes selectContractDetailClctam(FindReq dto);

    FindMembershipRes selectContractDetailMembership(FindReq dto);

    String selectContractDetailRglrSpp(FindReq dto);
}
