package com.kyowon.sms.wells.web.service.visit.service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbSafetyAccidentConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbSafetyAccidentDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSafetyAccidentMapper;
import com.sds.sflex.system.config.core.service.ConfigurationService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-MP-U-0209M01 안전사고 관리 API
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.19
 */
@Service
@RequiredArgsConstructor
public class WsnbSafetyAccidentService {

    private final WsnbSafetyAccidentMapper mapper;
    private final WsnbSafetyAccidentConverter converter;
    private final ConfigurationService configurationService;
    private final KakaoMessageService kakaoMessageService;

    /**
     * 안전사고 관리 조회 - 페이징
     *
     * @param dto
     * @return 조회결과
     */
    public PagingResult<SearchRes> getSafetyAccidents(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnbSafetyAccidentDvo> dvos = new PagingResult<WsnbSafetyAccidentDvo>();

        dvos = mapper.selectSafetyAccidents(dto, pageInfo);
        PagingResult<SearchRes> pagingResult = converter.mapWsnbSafetyAccidentDvoToSearchRes(dvos);
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 안전사고 관리 조회 - 엑셀다운로드 용
     *
     * @param dto
     * @return 조회결과
     */
    public List<SearchRes> getSafetyAccidents(SearchReq dto) {
        List<WsnbSafetyAccidentDvo> dvos = new ArrayList<WsnbSafetyAccidentDvo>();
        dvos = mapper.selectSafetyAccidents(dto);
        return converter.mapDvosToSearchRes(dvos);
    }

    /**
     * 안전사고 결과 조회 - 단건
     *
     * @param acdnRcpId
     * @return 조회결과
     */
    public FindRes getSafetyAccident(String acdnRcpId) {
        WsnbSafetyAccidentDvo dvo = mapper.selectSafetyAccident(acdnRcpId);
        //        String cstSignCn = Base64.getEncoder().encodeToString(dvo.getSignCn());
        //        dvo.setCstSignCn(cstSignCn);
        return converter.mapWsnbSafetyAccidentDvoToFindRes(dvo);
    }

    /**
     * 안전사고 결과 저장
     *
     * @param dto
     */
    public int editSafetyAccident(EditReq dto) {
        int processCount = 0;
        WsnbSafetyAccidentDvo dvo = converter.mapEditReqToWsnbSafetyAccidentDvo(dto);
        processCount += mapper.updateSafetyAccident(dvo);

        return processCount;
    }

    /**
     * 안전사고 결과 알림톡 발송 및 저장
     *
     * @param dto
     */
    public int sendSafetyAccidentBiztalk(BiztalkReq dto) throws Exception {
        int processCount = 0;
        String callbackValue = configurationService.getConfigurationValue("CFG_SNB_WELLS_CST_CNR_TNO");
        WsnbSafetyAccidentDvo dvo = converter.mapBiztalkReqToWsnbSafetyAccidentDvo(dto);

        final SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        format1.setLenient(false);
        Date date = format1.parse(dto.rcpdt());
        final SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("rcpdt", format2.format(date));
        paramMap.put("totCpsAmt", NumberFormat.getInstance().format(dto.totCpsAmt()));
        paramMap.put("url", "URL추후수정예정");

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("TEMP_SFT_ACDN")
            .templateParamMap(paramMap)
            .destInfo(dvo.getCstNm() + "^" + dvo.getMpno())
            .callback(callbackValue)
            .build();
        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);

        dvo.setAgrDocFwYn(processCount > 0 ? "Y" : "N");
        processCount += mapper.updateSafetyAccidentBiztalk(dvo);
        return processCount;
    }

    public int editSafetyAccidentSign(EditSignReq dto) {
        int processCount = 0;
        WsnbSafetyAccidentDvo dvo = converter.mapEditSignReqToWsnbSafetyAccidentDvo(dto);
        processCount += mapper.updateSafetyAccidentSign(dvo);
        return processCount;
    }
}
