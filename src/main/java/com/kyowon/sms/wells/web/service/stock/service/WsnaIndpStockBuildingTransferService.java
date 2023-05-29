package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndpStockBuildingTransferMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaIndpStockBuildingTransferService {
    private final WsnaIndpStockBuildingTransferMapper mapper;
    @Transactional
    public int saveCarriedOverAddressUseYn(){
        return mapper.updateCarriedOverAddressUseYn();
    }
}
