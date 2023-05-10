package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAutoMaterialReqConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAutoMaterialReqDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAutoMaterialReqMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0023 엔지니어 자재 자동 신청
 * W-SV-S-0065 서비스운영팀 자재자동신청 관련 데이터 이월
 * </pre>
 *
 * @author inho.choi
 * @since 2023.05.09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaAutoMaterialReqService {

    private final WsnaAutoMaterialReqMapper mapper;
    private final WsnaAutoMaterialReqConverter converter;

    @Transactional
    public int createAutoMaterialReq() {

        int cnt = 0;
        List<SearchRes> list = mapper.selectOstrAkNo();
        List<WsnaAutoMaterialReqDvo> dvoList = converter.mapAllWsnaAutoMaterialReqDvo(list);
        cnt += mapper.insertAutoMaterialEngineerReq(dvoList);
        cnt += mapper.insertAutoMaterialBranchReq(dvoList);

        return cnt;
    }

    @Transactional
    public int carryOver() {
        int cnt = 0;

        cnt += mapper.insertOteamMatAutoAplcIz();
        cnt += mapper.updateItmOstrAkIz();
        return cnt;
    }
}
