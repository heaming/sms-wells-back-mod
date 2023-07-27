package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaWellsVlCntrEynInqrDvo;

@Mapper
public interface WctaWellsVlCntrEynInqrMapper {
    List<WctaWellsVlCntrEynInqrDvo> selectRentalCsts(WctaWellsVlCntrEynInqrDvo dvo);

    List<WctaWellsVlCntrEynInqrDvo> selectMshCsts(WctaWellsVlCntrEynInqrDvo dvo);

}
