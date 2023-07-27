package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindReq;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaMachineChangeCstDvo;

@Mapper
public interface WctaMachineChangeCstMapper {

    String selectMachineChangeCstCntrNoSn(FindReq dto);

    Optional<WctaMachineChangeCstDvo> selectCustomerAddress(String cntrNo, String cntrSn);

}
