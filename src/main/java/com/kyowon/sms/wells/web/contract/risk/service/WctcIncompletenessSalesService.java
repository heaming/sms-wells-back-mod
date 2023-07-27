package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchReq;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcIncompletenessSalesConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchByCntrNoReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIcptSellChHistDvo;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIncompletenessSellIzDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcIncompletenessSalesMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcIncompletenessSalesService {

    private final WctcIncompletenessSalesMapper mapper;
    private final WctcIncompletenessSalesConverter converter;

    private boolean isValidCntrs(SearchByCntrNoReq dto) {
        return "Y".equals(mapper.isValidCntrs(dto));
    }

    public SearchRes getIncompletenessSales(SearchByCntrNoReq dto) {
        if (isValidCntrs(dto)) {
            List<SearchRes> sales = mapper.selectIncompletenessSales(dto);
            if (CollectionUtils.isNotEmpty(sales)) {
                return sales.get(0);
            }
        }
        throw new BizException("MSG_ALT_INVALID_DEVICE_CHANGE");
    }

    public PagingResult<SearchRes> getIncompletenessSalesPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIncompletenessSalePages(dto, pageInfo);
    }

    public List<SearchRes> getIncompletenessSalesForExcelDownload(SearchReq dto) {
        return mapper.selectIncompletenessSalePages(dto);
    }

    @Transactional
    public int saveIncompletenessSales(List<SaveReq> dtos) {
        int processCount = 0;
        Iterator<SaveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveReq dto = iterator.next();
            WctcIncompletenessSellIzDvo dvo = converter.mapSaveReqToIncompletenessSellIzDvo(dto);
            WctcIcptSellChHistDvo hist = converter.mapIncompletenessSellIzDvoToHistDvo(dvo);
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int result = mapper.insertIncompletenessSales(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    // 저장 성공 시 이력 생성
                    hist.setIcptSellId(dvo.getIcptSellId());
                    mapper.insertIncompletenessSalesHist(hist);
                    yield result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateIncompletenessSales(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    // 수정 성공 시 기존 이력 업데이트, 신규 이력 생성
                    mapper.updateIncompletenessSalesPrevHist(hist);
                    mapper.insertIncompletenessSalesHist(hist);
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeIncompletenessSales(List<String> icptSellIds) {
        int processCount = 0;
        int result;
        WctcIcptSellChHistDvo hist = new WctcIcptSellChHistDvo();
        for (Iterator<String> iterator = icptSellIds.iterator(); iterator.hasNext(); processCount += result) {
            String icptSellId = iterator.next();
            result = mapper.deleteIncompletenessSales(icptSellId);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            // 삭제 성공 시 기존 이력 업데이트
            hist.setIcptSellId(icptSellId);
            mapper.updateIncompletenessSalesPrevHist(hist);
        }
        return processCount;
    }
}
