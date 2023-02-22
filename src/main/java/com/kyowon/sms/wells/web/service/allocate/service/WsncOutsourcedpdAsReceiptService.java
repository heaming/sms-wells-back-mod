package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.allocate.converter.WsncOutsourcedpdAsReceiptConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.BiztalkReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncOutsourcedpdAsReceiptDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WmsncOutsourcedpdAsReceiptMapper;
import com.sds.sflex.common.common.service.ConfigurationService;
import com.sds.sflex.common.common.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncOutsourcedpdAsReceiptService {
    private final WmsncOutsourcedpdAsReceiptMapper mapper;
    private final TemplateService templateService;
    private final WsncOutsourcedpdAsReceiptConverter converter;
    private final ConfigurationService configurationService;
    private final KakaoMessageService kakaoMessageService;

    /**
     * 외주상품 A/S 접수처 조회
     * @param  searchParam : as센터명 or 제품명
     * @return 조회결과
     */
    public List<SearchRes> getOutsourcedpdAsReceipts(String searchParam) {

        return mapper.selectOutsourcedpdAsReceipts(searchParam);
    }

    /**
     * 외주상품 A/S 접수처 조회 알림톡 발송
     * @param  dto : 비즈톡 내용에 들어갈 파라미터
     * @return 조회결과
     */
    public int sendAsReceiptBiztalk(BiztalkReq dto) throws Exception {

        int processCount = 0;
        String callbackValue = configurationService.getConfigurationValue("CFG_SNB_WELLS_CST_CNR_TNO");
        WsncOutsourcedpdAsReceiptDvo dvo = converter.mapBiztalkReqToWsncOutsourcedpdAsReceiptDvo(dto);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cstKnm", dto.cstKnm());
        paramMap.put("cnrNm", dto.cnrNm());
        paramMap.put("cnrTno", dto.cnrTno());

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("wells17952")
            .templateParamMap(paramMap)
            .destInfo(dvo.getCstKnm() + "^" + dvo.getCstTno())
            .callback(callbackValue)
            .build();
        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);

        return processCount;
    }
}
