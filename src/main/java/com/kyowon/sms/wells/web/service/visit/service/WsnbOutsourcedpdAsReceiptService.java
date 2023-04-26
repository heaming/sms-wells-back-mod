package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbOutsourcedpdAsReceiptConverter;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.BiztalkReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbOutsourcedpdAsReceiptDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbOutsourcedpdAsReceiptMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.system.config.core.service.ConfigurationService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbOutsourcedpdAsReceiptService {
    private final WsnbOutsourcedpdAsReceiptMapper mapper;
    private final TemplateService templateService;
    private final WsnbOutsourcedpdAsReceiptConverter converter;
    private final ConfigurationService configurationService;
    private final KakaoMessageService kakaoMessageService;

    /**
     * 외주상품 A/S 접수처 조회 - 페이징
     * @param  dto : {String cnrNm,  String pdNm }
     * @return 조회결과
     */
    public PagingResult<SearchRes> getOutsourcedpdAsReceipts(SearchReq dto, PageInfo pageInfo) {

        return mapper.selectOutsourcedpdAsReceipts(dto, pageInfo);
    }

    /**
     * 외주상품 A/S 접수처 조회 - 엑셀다운로드
     * @param  dto : {String cnrNm,  String pdNm }
     * @return 조회결과
     */
    public List<SearchRes> getOutsourcedpdAsReceipts(SearchReq dto) {

        return mapper.selectOutsourcedpdAsReceipts(dto);
    }

    /**
     * 외주상품 A/S 접수처 조회 알림톡 발송
     * @param  dto : 비즈톡 내용에 들어갈 파라미터
     * @return 조회결과
     */
    public int sendAsReceiptBiztalk(BiztalkReq dto) throws Exception {

        int processCount = 0;
        String callbackValue = configurationService.getConfigurationValue("CFG_SNB_WELLS_CST_CNR_TNO");
        WsnbOutsourcedpdAsReceiptDvo dvo = converter.mapBiztalkReqToWsncOutsourcedpdAsReceiptDvo(dto);

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
