package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalMshSpayUseDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMshSpayUseDvo;

@Mapper
public interface WctbRentalMshSpayUseMapper {
    WctbRentalMshSpayUseDvo selectPdMclsfCnt(WctbRentalMshSpayUseDto.SearchReq dto);

    WctbRentalMshSpayUseDvo selectCstGdCdCheck(WctbRentalMshSpayUseDto.SearchReq dto);

    WctbRentalMshSpayUseDvo selectEmplPmotCheck(WctbRentalMshSpayUseDto.SearchReq dto);

    WctbRentalMshSpayUseDvo selectRcmdPmotCheck(WctbRentalMshSpayUseDto.SearchReq dto);

    WctbRentalMshSpayUseDvo selectReentryPmotCheck(WctbRentalMshSpayUseDto.SearchReq dto);

    WctbRentalMshSpayUseDvo selectNewPmotCheck(WctbRentalMshSpayUseDto.SearchReq dto);
}
