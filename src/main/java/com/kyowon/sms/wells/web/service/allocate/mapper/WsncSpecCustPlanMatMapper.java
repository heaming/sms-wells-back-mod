package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;

@Mapper
public interface WsncSpecCustPlanMatMapper {
    List<WsncSpecCustPlanMatDvo> selectSpecCustPlanMat(WsncSpecCustPlanMatDvo dvo);

    int insertSpecCustPlanMat(WsncSpecCustPlanMatDvo dvo);
}
