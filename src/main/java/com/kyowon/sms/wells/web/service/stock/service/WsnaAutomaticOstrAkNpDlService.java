package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAutomaticOstrAkNpDlMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0022 자동출고 요청건 중 미처리건 삭제 처리
 * </pre>
 *
 * @author inho.choi
 * @since 2023.04.25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaAutomaticOstrAkNpDlService {

    private final WsnaAutomaticOstrAkNpDlMapper mapper;

    public int removeAutoOstrAkNpDls() {
        return mapper.deleteAutoOstrAkNpDls();
    }
}
