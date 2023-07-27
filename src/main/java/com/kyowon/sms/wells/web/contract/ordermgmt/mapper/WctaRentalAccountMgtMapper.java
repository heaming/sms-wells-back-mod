package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto;

@Mapper
public interface WctaRentalAccountMgtMapper {

    List<WctaRentalAccountMgtDto.SearchBpdRentalAccountRes> selectBpdRentalAccount(
        WctaRentalAccountMgtDto.SearchBpdRentalAccountReq dto
    );

    List<WctaRentalAccountMgtDto.SearchByoRentalAccountRes> selectByoRentalAccount(
        WctaRentalAccountMgtDto.SearchByoRentalAccountReq dto
    );
}
