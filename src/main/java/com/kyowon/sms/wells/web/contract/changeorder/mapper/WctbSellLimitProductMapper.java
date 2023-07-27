package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSellLimitProductQtyDvo;

@Mapper
public interface WctbSellLimitProductMapper {
    WctbSellLimitProductQtyDvo selectQtyCheck(String pdCd);

    int updateSellLimitPdQtyBasVlEndDtm(String sellLmPdBaseId);
    int insertSellLimitPdQtyBas(@Param("item") WctbSellLimitProductQtyDvo dvo, String sellLmPdBaseId);
}
