package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAutomaticOstrAkNpDlMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaAutomaticOstrAkNpDlService {

    private final WsnaAutomaticOstrAkNpDlMapper mapper;

    public int removeAutoOstrAkNpDls() {
        return mapper.deleteAutoOstrAkNpDls();
    }
}
