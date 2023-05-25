package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndpStockBldTfMapper;
import com.sds.sflex.system.config.response.SaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaIndpStockBldTfService {
    private final WsnaIndpStockBldTfMapper mapper;
    public int SaveCarriedOverAddressUseYn(){
        return mapper.updateCarriedOverAddressUseYn();
    }
}
