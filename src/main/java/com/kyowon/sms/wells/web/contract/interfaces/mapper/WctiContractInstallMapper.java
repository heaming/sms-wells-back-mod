package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractInstallDvo;

@Mapper
public interface WctiContractInstallMapper {
    int updateContractInstall(WctiContractInstallDvo dvo);

    int updateContractInstallHistory(WctiContractInstallDvo dvo);

    int insertContractInstallHistory(WctiContractInstallDvo dvo);
}
