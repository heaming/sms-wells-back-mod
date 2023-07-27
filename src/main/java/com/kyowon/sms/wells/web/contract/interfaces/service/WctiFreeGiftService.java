package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeGiftDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeGiftDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiFreeGiftMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctiFreeGiftService {
    private final WctiFreeGiftMapper mapper;

    /**
     * 사은품 정보 조회
     * @programId    EAI_WSSI1055
     * @param       req
     * @return      list
     */
    public List<SearchRes> getFreeGift(SearchReq req) {
        return mapper.selectFreeGift(req);
    }
}
