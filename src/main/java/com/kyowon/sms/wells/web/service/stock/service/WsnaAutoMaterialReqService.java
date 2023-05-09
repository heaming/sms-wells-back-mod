package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAutoMaterialReqMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaAutoMaterialReqService {

    private final WsnaAutoMaterialReqMapper mapper;

    @Transactional
    public int createAutoMaterialReq() {

        int cnt = 0;
        List<SearchRes> list = mapper.selectOstrAkNo();
        cnt += mapper.insertAutoMaterialEngineerReq(list);
        cnt += mapper.insertAutoMaterialBranchReq(list);

        return cnt;
    }
}
