package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDeletionRjRsonConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDeletionRjRsonDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDeletionRjRsonDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDeletionRjRsonMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaOrderDeletionRjRsonService {
    private final WctaOrderDeletionRjRsonMapper mapper;
    private final WctaOrderDeletionRjRsonConverter converter;

    @Transactional
    public int saveRejectReasonRgst(WctaOrderDeletionRjRsonDto.SaveReq req) {
        WctaOrderDeletionRjRsonDvo dvo = converter.mapSaveReqToWctaOrderDeletionRjRsonDvo(req);
        return mapper.updateRejectReasonRgst(dvo);
    }
}
