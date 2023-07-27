package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCstCntrNoInstallDvo;

@Mapper
public interface WctbCstCntrNoInstallMapper {
    int updateCstCntrDeliver(WctbCstCntrNoInstallDvo dvo);

    int updateContractAddressHistory(WctbCstCntrNoInstallDvo dvo);

    int insertContractAddressHistory(WctbCstCntrNoInstallDvo dvo);
}
