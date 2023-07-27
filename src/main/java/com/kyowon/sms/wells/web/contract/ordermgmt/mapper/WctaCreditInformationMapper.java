package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCbboDefaultRegistDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCreditInformationDvo;

@Mapper
public interface WctaCreditInformationMapper {

    String selectCinfInqrHistory(WctaCreditInformationDvo dvo);

    int insertCinfInqr(@Param("item") WctaCreditInformationDvo dvo);

    int updateNiceCinfInqr(@Param("item") WctaCreditInformationDvo dvo, @Param("data") Map<String, Object> param);

    int mergeCinfInqr(WctaCreditInformationDvo dvo);

    Optional<WctaCbboDefaultRegistDvo> selectCbboDflt(WctaCreditInformationDvo dvo);

    int updateCbboDflt(WctaCbboDefaultRegistDvo dvo);
}
