package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateOrdWrteListDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaEstimateOrdWrteListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaEstimateOrdWrteListService {
    private final WctaEstimateOrdWrteListMapper mapper;

    public PagingResult<WctaEstimateOrdWrteListDto.SearchRes> getEstimateOrdWrteLists(
        WctaEstimateOrdWrteListDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectEstimateOrdWrteLists(dto, pageInfo);
    }

    @Transactional
    public int removeEstOrdWrteListDl(String cntrNo, String cntrSn) {
        int res = 0;
        mapper.updateCntrBas(cntrNo);
        mapper.updateCntrChHist(cntrNo);
        mapper.insertCntrChHist(cntrNo);
        mapper.updateCntrDtl(cntrNo, cntrSn);
        mapper.updateCntrDtlStatChHist(cntrNo, cntrSn);
        mapper.insertCntrDetailStatChangeHist(cntrNo, cntrSn);
        mapper.updateCntrDchHist(cntrNo, cntrSn);
        res += mapper.insertCntrDchHist(cntrNo, cntrSn);
        String cntrStlmId = mapper.selectCntrStlmId(cntrNo, cntrSn);
        if (StringUtils.isNotEmpty(cntrStlmId)) {
            mapper.updateCntrStlmRel(cntrNo, cntrSn);
            mapper.updateCntrStlmBas(cntrStlmId);
            mapper.updateCntrStlmChHist(cntrStlmId);
            mapper.insertCntrStlmChHist(cntrStlmId);
        }

        return res;
    }
}
