package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbAffiliateAlncDto.SaveReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbAffiliateAlncDvo;

@Mapper
public interface WctbAffiliateAlncMapper {
    WctbAffiliateAlncDvo selectAffiliateAlncCheck(SaveReq dto);

    List<WctbAffiliateAlncDvo> selectAffiliateAlnc(SaveReq dto);

    int updateCntrWellsDtl(WctbAffiliateAlncDvo dvo);

    int updateAcmpalCntrIz(WctbAffiliateAlncDvo dvo);
}
