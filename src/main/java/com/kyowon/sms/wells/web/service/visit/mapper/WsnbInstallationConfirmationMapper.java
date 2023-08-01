package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallationConfirmationDvo;

;

@Mapper
public interface WsnbInstallationConfirmationMapper {

    WsnbInstallationConfirmationDvo selectInstallationConfirmation(String cstSvAsnNo, String wprs);

    int createInstallationConfirmation(WsnbInstallationConfirmationDvo dvo);
}
