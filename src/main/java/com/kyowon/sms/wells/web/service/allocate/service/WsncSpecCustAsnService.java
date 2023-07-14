package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncSpecCustAsnConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncSpecCustAsnMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * [WSNC] [W-SV-S-0027] 특정고객 배정 인서트
 */
@Service
@RequiredArgsConstructor
public class WsncSpecCustAsnService {

    private final WsncSpecCustAsnMapper mapper;

    private final WsncSpecCustAsnConverter converter;

    @Transactional
    public int processSpecCustAsn(WsncSpecCustAsnDvo dvo) throws Exception {
        ValidAssert.hasText(dvo.getAsnOjYm());
        List<WsncSpecCustAsnDvo> asnList = mapper.selectSpecCustAsn(dvo);

        for(WsncSpecCustAsnDvo asnDvo : asnList){
            mapper.insertSpecCustAsn(asnDvo);
        }

        return 1;
    }

    @Transactional
    public int processSpecCustAsn(WsncSpecCustAsnDto.SaveProcessReq dto) throws Exception {
        WsncSpecCustAsnDvo dvo = converter.mapSaveProcessReqToSpecCustAsnDvo(dto);
        return processSpecCustAsn(dvo);
    }
}
