package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.allocate.converter.WsncOutsourcedpdAsReceiptConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncOutsourcedpdAsReceiptDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncOutsourcedpdAsReceiptMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.ConfigurationService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncOutsourcedpdAsReceiptService {
    private final WsncOutsourcedpdAsReceiptMapper mapper;
    private final TemplateService templateService;
    private final WsncOutsourcedpdAsReceiptConverter converter;
    private final ConfigurationService configurationService;
    private final KakaoMessageService kakaoMessageService;

    /**
     * 외주상품 A/S 접수내역 조회 - 페이징
     * @param  dto : { cnrNm : as접수업체명,  pdNm : 외주상품명 }
     * @return 조회결과
     */
    public PagingResult<SearchReceiptIzRes> getOutsourcedpdAsReceiptIzs(SearchReceiptIzReq dto, PageInfo pageInfo) {

        PagingResult<SearchReceiptIzRes> pagingResult = converter.mapWsncAsTransferDvoToSearchReceiptIzRes(
            mapper.selectOutsourcedpdAsReceiptIzs(dto, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 외주상품 A/S 접수내역 조회 - 엑셀다운로드
     * @param  dto : { cnrNm : as접수업체명,  pdNm : 외주상품명 }
     * @return 조회결과
     */
    public List<SearchReceiptIzRes> getOutsourcedpdAsReceiptIzs(SearchReceiptIzReq dto) {

        return converter.mapWsncAsTransferDvoToSearchReceiptIzRes(mapper.selectOutsourcedpdAsReceiptIzs(dto));
    }

    /**
     * 외주상품 A/S 접수 내역 삭제
     * @param  dtos : 삭제할 접수내역
     */
    public int removeOutsourcedpdAsReceiptIzs(List<RemoveReceiptIzReq> dtos) {
        int processCount = 0;

        for (RemoveReceiptIzReq dto : dtos) {
            processCount += mapper.deleteOutsourcedpdAsReceiptIz(dto);
        }
        return processCount;
    }

    /**
     * 외주상품 A/S 접수 내역 저장
     * @param  dtos : 저장할 접수내역
     */
    public int saveOutsourcedpdAsReceiptIzs(List<SaveReceiptIzReq> dtos) {
        int processCount = 0;

        for (SaveReceiptIzReq dto : dtos) {
            WsncOutsourcedpdAsReceiptDvo dvo = converter.mapSaveReceiptIzReqToWsncOutsourcedpdAsReceiptDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += mapper.insertOutsourcedpdAsReceiptIz(dvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateOutsourcedpdAsReceiptIz(dvo);
                }
            }
        }
        return processCount;
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
        paramMap.put("cnrNm", dto.cnrNm());
        paramMap.put("cnrTno", dto.cnrTno());

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("wells17952")
            .templateParamMap(paramMap)
            .destInfo("" + "^" + dvo.getCstTno())
            .callback(callbackValue)
            .build();
        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);

        return processCount;
    }

    /**
     * 외주상품 A/S 접수처 조회 - 엑셀다운로드
     * @param  dto : { sv_cnr_nm : as접수업체명 }
     * @return 조회결과
     */
    public PagingResult<SearchReceiptBzRes> getOutsourcedpdAsReceiptBzs(SearchReceiptBzReq dto, PageInfo pageInfo) {

        PagingResult<SearchReceiptBzRes> pagingResult = converter.mapWsncAsTransferDvoToSearchReceiptBzRes(
            mapper.selectOutsourcedpdAsReceiptBzs(dto, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 외주상품 A/S 접수처 조회
     * @param  dto : { sv_cnr_nm : as접수업체명 }
     * @return 조회결과
     */
    public List<SearchReceiptBzRes> getOutsourcedpdAsReceiptBzs(SearchReceiptBzReq dto) {

        return converter.mapWsncAsTransferDvoToSearchReceiptBzRes(mapper.selectOutsourcedpdAsReceiptBzs(dto));
    }

    /**
     * 외주상품 A/S 접수처 삭제
     * @param  dtos : [{ sv_cnr_sn :  서비스센터일련번호 }]
     */
    public int removeOutsourcedpdAsReceiptBzs(List<RemoveReceiptBzReq> dtos) {
        int processCount = 0;

        for (RemoveReceiptBzReq dto : dtos) {
            processCount += mapper.deleteOutsourcedpdAsReceiptBz(dto);
        }
        return processCount;
    }

    /**
     * 외주상품 A/S 접수처 저장
     * @param  dtos : 저장할 접수내역
     */
    public int saveOutsourcedpdAsReceiptBzs(List<SaveReceiptBzReq> dtos) {
        int processCount = 0;

        for (SaveReceiptBzReq dto : dtos) {
            WsncOutsourcedpdAsReceiptDvo dvo = converter.mapSaveReceiptBzReqToWsncOutsourcedpdAsReceiptDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += mapper.insertOutsourcedpdAsReceiptBz(dvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateOutsourcedpdAsReceiptBz(dvo);
                }
            }
        }
        return processCount;
    }
}
