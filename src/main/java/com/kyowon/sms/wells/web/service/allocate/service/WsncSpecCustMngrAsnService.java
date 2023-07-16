package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncSpecCustMngrAsnConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustMngrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncSpecCustMngrAsnMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * [WSNC] [W-SV-S-0031] 특정고객 담당자 지정 BS 오더 생성
 */
@Service
@RequiredArgsConstructor
public class WsncSpecCustMngrAsnService {

    private final WsncSpecCustMngrAsnMapper mapper;

    private final WsncSpecCustMngrAsnConverter converter;

    @Transactional
    public int processSpecCustAsn(WsncSpecCustMngrAsnDvo dvo) throws Exception {
        ValidAssert.hasText(dvo.getAsnOjYm());
        List<WsncSpecCustMngrAsnDvo> mngrAsnList = mapper.selectSpecCustMngrAsn(dvo);
        WsncSpecCustMngrAsnDvo mngrAsnDvo_01;
        WsncSpecCustMngrAsnDvo mngrAsnDvo_02;
        WsncSpecCustMngrAsnDvo mngrAsnDvo_05;

        mngrAsnDvo_01 = mapper.selectSpecCustMngrAsn_01(dvo).orElseThrow();    //vChgGb가 8인 경우 공통코드로 지정한 담당자 고정으로 지정(공통코드)

        for(WsncSpecCustMngrAsnDvo mngrAsnDvo : mngrAsnList){

            //1-2. 관리 구분에 따른 배정담당자 구하는 로직 수행한다.
            //1-2-1. vChgGb가 8인 경우 공통코드로 지정한 담당자 고정으로 지정(공통코드)
            if("8".equals(mngrAsnDvo.getVChgGb())){
                mngrAsnDvo.setPrtnrOgTpCd(mngrAsnDvo_01.getPrtnrOgTpCd());
                mngrAsnDvo.setPrtnrNo(mngrAsnDvo_01.getPrtnrNo());
            }
            //1-2-2. vChgGb가 8이 아닌경우
            else {
                //고정담당자가 있으면 고정담당자를 배정담당자로 지정
                mngrAsnDvo_02 = mapper.selectSpecCustMngrAsn_02(mngrAsnDvo).orElseGet(() -> {
                    //1-2-3. 고정담당자가 없으면 엔지니어와 매니저를 구분하여 배정
                    if ("1".equals(mngrAsnDvo.getVChgGb())) {
                        //1) 매니저인경우 해당 계약에 매핑된 담당 파트너를 배정담당자로 지정한다. (vChgGb가 1인 경우)
                        return mapper.selectSpecCustMngrAsn_03(mngrAsnDvo).orElseGet(WsncSpecCustMngrAsnDvo::new);
                    } else if ("2".equals(mngrAsnDvo.getVChgGb())) {
                        //2) 엔지니어인경우 책임지역관리 로직으로 배정담당자 지정 (vChgGb가 2인 경우)
                        return mapper.selectSpecCustMngrAsn_04(mngrAsnDvo).orElseGet(WsncSpecCustMngrAsnDvo::new);
                    } else {
                        //3) 이 경우는 없어야 하며, 혹시나 하는 마음에 집어넣음.
                        return new WsncSpecCustMngrAsnDvo();
                    }
                });
                mngrAsnDvo.setPrtnrOgTpCd(mngrAsnDvo_02.getPrtnrOgTpCd());
                mngrAsnDvo.setPrtnrNo(mngrAsnDvo_02.getPrtnrNo());
            }

            //배정일자/배정시간 구하는 로직 수행
            mngrAsnDvo_05 = mapper.selectSpecCustMngrAsn_05(mngrAsnDvo).orElseGet(WsncSpecCustMngrAsnDvo::new);

            if(mngrAsnDvo_05 != null && (StringUtils.isNotEmpty(mngrAsnDvo_05.getPsicAsnDt()))){
                mngrAsnDvo.setPsicAsnDt(mngrAsnDvo_05.getPsicAsnDt());
                mngrAsnDvo.setPsicAsnHh(mngrAsnDvo_05.getPsicAsnHh());
                mngrAsnDvo.setCnfmPsicAsnDt(mngrAsnDvo_05.getPsicAsnDt());
                mngrAsnDvo.setCnfmPsicAsnHh(mngrAsnDvo_05.getPsicAsnHh());
            }
            //insert
            mapper.insertSpecCustMngrAsn(mngrAsnDvo);
        }

        return 1;
    }

    @Transactional
    public int processSpecCustAsn(WsncSpecCustMngrAsnDto.SaveProcessReq dto) throws Exception {
        WsncSpecCustMngrAsnDvo dvo = converter.mapSaveProcessReqToSpecCustMngrAsnDvo(dto);
        return processSpecCustAsn(dvo);
    }
}
