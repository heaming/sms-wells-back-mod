package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDeletionRjRsonDvo;

@Mapper
public interface WctaOrderDeletionRjRsonMapper {
    int updateRejectReasonRgst(WctaOrderDeletionRjRsonDvo dvo);
}
