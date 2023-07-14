package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRegularBfsvcAsnConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcAsnDvo;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsncRegularBfsvcAsnService {

    private final WsncSpecCustAsnService wsncSpecCustAsnService;    //[W-SV-S-0027] 특정고객 배정 인서트

    private final WsncSpecCustPlanMatService wsncSpecCustPlanMatService;    //[W-SV-S-0029] 특정고객 예정자재 인서트

    private final WsncSpecCustMngrAsnService wsncSpecCustMngrAsnService;    //[W-SV-S-0031] 특정고객 담당자 지정 BS 오더 생성

    private final WsncRegularBfsvcAsnConverter converter;

    @Transactional
    public int processRegularBfsvcAsn(WsncRegularBfsvcAsnDto.SaveProcessReq dto) throws Exception {
        return processRegularBfsvcAsn(converter.mapSaveProcessReqToDvo(dto));
    }

    @Transactional
    public int processRegularBfsvcAsn(WsncRegularBfsvcAsnDvo dvo) throws Exception {

        /*
         * 아래의 step 이 모두 완료되어야 정상 처리한다. (All or nothing)
         * - 아래의 서비스에 배정년월과 담당자사번, 정기BS 배정할 계약 정보를 파라미터로 전달한다.
         * 1. 특정고객 배정 인서트 (W-SV-S-0027) 를 수행한다.
         * 2. 특정고객 예정자재 인서트 (W-SV-S-0029) 서비스를 수행한다.
         * 3. 특정고객 담당자 지정 BS 오더 생성 서비스(W-SV-S-0031)를 수행한다.
         * 4. 성공/실패 여부를 리턴한다. (성공: S, 실패: F)
         */

        //Step 1. [W-SV-S-0027] ::: 특정고객 배정 인서트를 수행한다.
        wsncSpecCustAsnService.processSpecCustAsn(converter.mapDvoToSpecCustAsnDvo(dvo));

        //Step 2. [W-SV-S-0029] ::: 특정고객 예정자재 인서트 서비스를 수행한다.
        wsncSpecCustPlanMatService.processSpecCustPlanMat(converter.mapDvoToSpecCustPlanMatDvo(dvo));

        //Step 3. [W-SV-S-0031] ::: 특정고객 담당자 지정 BS 오더 생성 서비스를 수행한다.
        wsncSpecCustMngrAsnService.processSpecCustAsn(converter.mapDvoToSpecCustMngrAsnDvo(dvo));


        return 1;
    }

    @Transactional
    public int processRegularBfsvcAsn(Map<String, Object> param) throws Exception {
        WsncRegularBfsvcAsnDvo dvo = new WsncRegularBfsvcAsnDvo();
        dvo.setAsnOjYm(StringUtil.nvl(param.get("PARAM1")));
        dvo.setPrtnrNo(StringUtil.nvl(param.get("PARAM2")));
        dvo.setCntrSn(StringUtil.nvl(param.get("PARAM3")));
        dvo.setCntrSn(StringUtil.nvl(param.get("PARAM4")));

        log.info("[WsncRegularBfsvcAsnService.processRegularBfsvcAsn] AsnOjYm ::: " + dvo.getAsnOjYm());
        log.info("[WsncRegularBfsvcAsnService.processRegularBfsvcAsn] PrtnrNo ::: " + dvo.getPrtnrNo());
        log.info("[WsncRegularBfsvcAsnService.processRegularBfsvcAsn] CntrSn ::: " + dvo.getCntrSn());
        log.info("[WsncRegularBfsvcAsnService.processRegularBfsvcAsn] CntrSn ::: " + dvo.getCntrSn());

        return processRegularBfsvcAsn(dvo);
    }
}
