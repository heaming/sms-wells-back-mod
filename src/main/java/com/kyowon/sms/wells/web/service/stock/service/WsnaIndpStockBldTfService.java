package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndpStockBldTfMapper;
import com.sds.sflex.system.config.response.SaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaIndpStockBldTfService {
    private final WsnaIndpStockBldTfMapper mapper;
    @Transactional
    public int SaveCarriedOverAddressUseYn(){
        return mapper.updateCarriedOverAddressUseYn();
    }
}
