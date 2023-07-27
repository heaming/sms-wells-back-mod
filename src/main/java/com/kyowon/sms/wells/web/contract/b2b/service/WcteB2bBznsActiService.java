package com.kyowon.sms.wells.web.contract.b2b.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.b2b.converter.WcteB2bBznsActiConverter;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SaveDetailReq;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SaveReq;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchReq;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchRes;
import com.kyowon.sms.wells.web.contract.b2b.dvo.WcteB2bBznsActiDvo;
import com.kyowon.sms.wells.web.contract.b2b.mapper.WcteB2bBznsActiMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteB2bBznsActiService {
    private final WcteB2bBznsActiMapper mapper;
    private final WcteB2bBznsActiConverter converter;

    public List<SearchRes> getRentalMutuAlncCheck(SearchReq dto) {
        return converter.mapWcteB2bBznsActiDvoToSearchRes(mapper.selectRentalMutuAlncCheck(dto));
    }

    public WcteB2bBznsActiDto.SearchKeyManRes getKeyMan(WcteB2bBznsActiDto.SearchKeyManReq dto) {
        return converter.mapWcteB2bBznsActiDvoToSearchKeyManRes(mapper.selectKeyMan(dto));
    }

    @Transactional
    public int saveB2bBoMngtSaves(List<SaveReq> dtos) {
        int res = 0;
        for (SaveReq dto : dtos) {
            WcteB2bBznsActiDvo dvo = converter.mapSaveReqToWcteB2bBznsActiDvo(dto);

            switch (dvo.getRowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    mapper.insertSsopLeadCstBas(dvo);
                    mapper.insertSsopLeadCstChHist(dvo.getLeadCstId());
                    mapper.insertSsopLeadCstRlpplDtl(dvo);
                    mapper.insertSsopLeadCstRlpplChHist(dvo.getLeadCstRlpplId());
                    mapper.insertSsopOpptBas(dvo);
                    res += mapper.insertSsopOpptChHist(dvo.getOpptId());
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    mapper.updateSsopLeadCstBas(dvo);
                    mapper.updateSsopLeadCstChHist(dvo.getLeadCstId());
                    mapper.insertSsopLeadCstChHist(dvo.getLeadCstId());
                    mapper.updateSsopLeadCstRlpplDtl(dvo);
                    mapper.updateSsopLeadCstRlpplChHist(dvo.getLeadCstRlpplId());
                    mapper.insertSsopLeadCstRlpplChHist(dvo.getLeadCstRlpplId());
                    mapper.updateSsopOpptBas(dvo);
                    mapper.updateSsopOpptChHist(dvo.getOpptId());
                    res += mapper.insertSsopOpptChHist(dvo.getOpptId());
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return res;
    }

    @Transactional
    public int removeBusinessToBusinessDls(List<SaveReq> dtos) {
        int result = 0;
        for (SaveReq dto : dtos) {
            mapper.deleteSsopLeadCstBas(dto.leadCstId());
            mapper.updateSsopLeadCstChHist(dto.leadCstId());
            mapper.insertSsopLeadCstChHist(dto.leadCstId());
            mapper.deleteSsopLeadCstRlpplDtl(dto.leadCstRlpplId());
            mapper.updateSsopLeadCstRlpplChHist(dto.leadCstRlpplId());
            mapper.insertSsopLeadCstRlpplChHist(dto.leadCstRlpplId());
            result += mapper.deleteSsopOpptBas(dto.opptId());
            mapper.updateSsopOpptChHist(dto.opptId());
            mapper.insertSsopOpptChHist(dto.opptId());
        }
        return result;
    }

    public List<WcteB2bBznsActiDto.SearchDetailRes> getB2bBoMngtDtlIzs(String opptId) {
        return mapper.selectB2bBoMngtDtlIzs(opptId);
    }

    @Transactional
    public int saveB2bBoMngtDtlSaves(List<SaveDetailReq> dtos) {
        int res = 0;
        for (SaveDetailReq dto : dtos) {
            WcteB2bBznsActiDvo dvo = converter.mapSaveDetailReqToWcteB2bBznsActiDvo(dto);

            switch (dvo.getRowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    mapper.insertSsopOpptDtl(dvo);
                    res += mapper.insertSsopOpptDchHist(dvo.getOpptId(), dvo.getOpptSn());
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    mapper.updateSsopOpptDtl(dvo);
                    mapper.updateSsopOpptDchHist(dvo.getOpptId(), dvo.getOpptSn());
                    res += mapper.insertSsopOpptDchHist(dvo.getOpptId(), dvo.getOpptSn());
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return res;
    }

    @Transactional
    public int removeB2bBoMngtDtlIzDls(List<SaveDetailReq> dtos) {
        int result = 0;
        for (SaveDetailReq dto : dtos) {
            result += mapper.deleteSsopOpptDtl(dto.opptId(), dto.opptSn());
            mapper.updateSsopOpptDchHist(dto.opptId(), dto.opptSn());
            mapper.insertSsopOpptDchHist(dto.opptId(), dto.opptSn());
        }
        return result;
    }
}
