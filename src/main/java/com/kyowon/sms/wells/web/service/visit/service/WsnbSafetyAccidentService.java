package com.kyowon.sms.wells.web.service.visit.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sflex.common.system.service.QueryStringEncService;
import com.kyowon.sflex.common.system.service.UrlShortenerService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbSafetyAccidentConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbSafetyAccidentDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSafetyAccidentMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.core.service.ConfigurationService;
import com.sds.sflex.system.config.core.util.IDGenUtil;
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
    private final AttachFileService attachFileService;
    private final QueryStringEncService queryEncService;

    private final UrlShortenerService urlService;

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

        String cstSignCn = "";
        if (dvo.getSignCn() != null) {
            cstSignCn = Base64Utils.encodeToString(dvo.getSignCn());
            cstSignCn = "data:image/png;base64," + cstSignCn;
            dvo.setCstSignCn(cstSignCn);
        }
        return converter.mapWsnbSafetyAccidentDvoToFindRes(dvo);
    }

    /**
     * 안전사고 결과 등록
     *
     * @param dto
     */
    public int editSafetyAccidentResult(EditReq dto) {
        int processCount = 0;
        WsnbSafetyAccidentDvo dvo = converter.mapEditReqToWsnbSafetyAccidentDvo(dto);
        processCount += mapper.updateSafetyAccidentResult(dvo);

        return processCount;
    }

    /**
     * 안전사고 등록 저장
     *
     * @param dto
     */
    public int saveSafetyAccident(SaveReq dto) throws Exception {
        int processCount = 0;
        WsnbSafetyAccidentDvo dvo = converter.mapSaveReqToWsnbSafetyAccidentDvo(dto);

        //첨부파일저장 후 docId 저장
        if (ObjectUtils.isNotEmpty(dto.acdnPhoApnFile())) {
            String acdnPhoApnFileId = IDGenUtil.getUUID("ATG");
            attachFileService.saveAttachFiles("ATG_SNB_ACDN_ALL", acdnPhoApnFileId, dto.acdnPhoApnFile());
            dvo.setAcdnPhoApnDocId(acdnPhoApnFileId);
        }
        if (ObjectUtils.isNotEmpty(dto.acdnPictrApnFile())) {
            String acdnPictrApnFileId = IDGenUtil.getUUID("ATG");
            attachFileService.saveAttachFiles("ATG_SNB_ACDN_ALL", acdnPictrApnFileId, dto.acdnPictrApnFile());
            dvo.setAcdnPictrApnDocId(acdnPictrApnFileId);
        }
        if (ObjectUtils.isNotEmpty(dto.causAnaApnFile())) {
            String causAnaApnFileId = IDGenUtil.getUUID("ATG");
            attachFileService.saveAttachFiles("ATG_SNB_ACDN_ALL", causAnaApnFileId, dto.causAnaApnFile());
            dvo.setCausAnaApnDocId(causAnaApnFileId);
        }
        /* TB_SVPD_ACDN_DOAN_IZ 테이블 insert or update */
        processCount += mapper.mergeSafetyAccident(dvo);
        if (StringUtils.isEmpty(dto.acdnRcpId())) {
            /* TB_SVPD_CST_SV_WK_RS_IZ 테이블 SFT_ACDN_YN = 'Y' */
            processCount += mapper.updateWorkResult(dvo);
        }

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
        /* TODO: url확인 후 수정. */
        String url = "/anonymous/login?redirectUrl=/#/ns/wmsnb-safety-accident-agreement&acdnRcpId="
            + dto.acdnRcpId();
        url = urlService.getShortedUrl(queryEncService.getEncParamUrl(url));
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cntrNo", dto.cntrNo());
        paramMap.put("cntrSN", dto.cntrSn());
        paramMap.put("cstNm", dto.cstNm());
        paramMap.put("pdNm", dto.pdNm());
        paramMap.put("rcpdt", format2.format(date));
        paramMap.put("url", url);

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("Wells18387")
            .templateParamMap(paramMap)
            .destInfo(dvo.getCstNm() + "^" + dvo.getMpno())
            .callback(callbackValue)
            .build();
        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);

        dvo.setAgrDocFwYn(processCount > 0 ? "Y" : "N");
        processCount += mapper.updateSafetyAccidentBiztalk(dvo);
        return processCount;
    }

    /**
     * 전자합의서 서명 저장
     *
     * @param dto
     */
    public int editSafetyAccidentSign(EditSignReq dto) {
        int processCount = 0;
        WsnbSafetyAccidentDvo dvo = converter.mapEditSignReqToWsnbSafetyAccidentDvo(dto);
        processCount += mapper.updateSafetyAccidentSign(dvo);
        return processCount;
    }

    /**
     * 안전사고 등록화면 조회 - 초기화
     *
     * @param dto
     */
    public FindInitRes getSafetyAccidentInit(FindInitReq dto) {
        WsnbSafetyAccidentDvo dvo = mapper.selectSafetyAccidentInit(dto);
        return converter.mapWsnbSafetyAccidentDvoToFindInitRes(dvo);
    }
}
