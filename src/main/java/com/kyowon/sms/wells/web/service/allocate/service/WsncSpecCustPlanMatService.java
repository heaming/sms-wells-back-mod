package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncSpecCustPlanMatConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustPlanMatDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncSpecCustPlanMatMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * [WSNC] [W-SV-S-0029] 특정고객 예정자재 인서트
 */
@Service
@RequiredArgsConstructor
public class WsncSpecCustPlanMatService {

    private final WsncSpecCustPlanMatMapper mapper;

    private final WsncSpecCustPlanMatConverter converter;

    @Transactional
    public int processSpecCustPlanMat(WsncSpecCustPlanMatDvo dvo) throws Exception {
        ValidAssert.hasText(dvo.getAsnOjYm());
        List<WsncSpecCustPlanMatDvo> matList = mapper.selectSpecCustPlanMat(dvo);

        for(WsncSpecCustPlanMatDvo matDvo : matList){
            mapper.insertSpecCustPlanMat(matDvo);
        }

        return 1;
    }

    @Transactional
    public int processSpecCustPlanMat(WsncSpecCustPlanMatDto.SaveProcessReq dto) throws Exception {
        WsncSpecCustPlanMatDvo dvo = converter.mapSaveProcessReqToSpecCustPlanMatDvo(dto);
        return processSpecCustPlanMat(dvo);
    }
}
