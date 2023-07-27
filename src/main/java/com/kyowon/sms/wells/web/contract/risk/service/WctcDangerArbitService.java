package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcDangerArbitConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcDangerArbitMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcDangerArbitService {
    private final WctcDangerArbitMapper mapper;
    private final WctcDangerArbitConverter converter;

    public List<SearchRes> getDangerArbitManagerial(SearchReq dto) {
        return mapper.selectDangerArbitManagerial(dto);
    }

    public SearchOrganizationRes getOrganizationInfInqr(String baseYm, String prtnrNo, String ogTpCd) {
        if (StringUtils.isNotEmpty(mapper.selectDuplicationCheck(prtnrNo, baseYm))) {
            throw new BizException("MSG_ALT_DUP_PRTNR_NO_STRT_MM", prtnrNo, baseYm);
        }
        return mapper.selectOrganizationInfInqr(baseYm, prtnrNo, ogTpCd);
    }

    @Transactional
    public int removeDangerArbitManagerial(List<SaveReq> dtos) {
        int result = 0;
        for (SaveReq dto : dtos) {
            mapper.updateDangerCheckChHist(dto.dangOjPrtnrNo(), dto.dangOcStrtmm());
            mapper.insertDangerCheckChHistY(dto.dangOjPrtnrNo(), dto.dangOcStrtmm());
            result += mapper.updateDangerCheckIzDlYn(dto.dangOjPrtnrNo(), dto.dangOcStrtmm());

        }
        return result;
    }

    @Transactional
    public int saveDangerArbitManagerial(List<SaveReq> dtos) {
        int result = 0;
        for (SaveReq dto : dtos) {
            WctcDangerArbitDvo dangerArbitManagerial = converter.mapSaveReqWctcDangerArbitDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (StringUtils
                        .isNotEmpty(mapper.selectDuplicationCheck(dto.dangOjPrtnrNo(), dto.dangOcStrtmm()))) {
                        throw new BizException("MSG_ALT_DUP_PRTNR_NO_STRT_MM", dto.dangOjPrtnrNo(), dto.dangOcStrtmm());
                    }
                    dangerArbitManagerial.setDangMngtOgTpCd(dto.ogTpCd());
                    if (StringUtils.isNotEmpty(dto.dgr1LevlDgPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("2");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.dgr1LevlDgPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                    }
                    if (StringUtils.isNotEmpty(dto.dgr2LevlDgPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("4");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.dgr2LevlDgPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                    }
                    if (StringUtils.isNotEmpty(dto.bznsSpptPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("5");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.bznsSpptPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                    }
                    if (StringUtils.isNotEmpty(dto.dgr3LevlDgPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("7");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.dgr3LevlDgPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                    }
                    dangerArbitManagerial.setDangMngtPstnDvCd(dto.dangOjPstnDvCd());
                    dangerArbitManagerial.setDangMngtPrtnrNo(dto.dangOjPrtnrNo());
                    mapper.insertDangerCheckIz(dangerArbitManagerial);
                    mapper.updateDangerCheckChHist(
                        dangerArbitManagerial.getDangOjPrtnrNo(), dangerArbitManagerial.getDangOcStrtmm()
                    );
                    result += mapper.insertDangerCheckChHistN(
                        dangerArbitManagerial.getDangOjPrtnrNo(), dangerArbitManagerial.getDangOcStrtmm()
                    );
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    mapper.updateDangerCheckIz(dangerArbitManagerial);
                    result += mapper.insertDangerCheckChHistN(
                        dangerArbitManagerial.getDangOjPrtnrNo(), dangerArbitManagerial.getDangOcStrtmm()
                    );
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
            ;
        }
        return result;
    }
}
