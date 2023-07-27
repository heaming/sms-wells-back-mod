package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipJoinTcntDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaMembershipJoinTcntMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaMembershipJoinTcntService {
    private final WctaMembershipJoinTcntMapper mapper;

    public List<WctaMembershipJoinTcntDto.SearchRes> getMembershipJoinTcnt(String cntrNo, String cntrSn) {
        return mapper.selectMembershipJoinTcnt(cntrNo, cntrSn);
    }
}
