package com.kyowon.sms.wells.web.contract.ordermgmt.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaInstallationReqdDtInMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaInstallationReqdDtInService {
    private final WctaInstallationReqdDtInMapper mapper;

    /**
     * 프로그램ID : W-SS-S-0085
     *  - 서비스에서 설치/철거 후 계약WELLS상세의 설치일자에 UPDATE를 한다.
     * @param cntrNo  (필수)계약번호
     *        cntrSn  (필수)계약일련번호
     *        istDt   설치일자
     *        reqdDt  철수일자
     * @return
     */
    public int saveInstallReqdDt(String cntrNo, String cntrSn, String istDt, String reqdDt) {
        ValidAssert.hasText(cntrNo);
        ValidAssert.hasText(cntrSn);
        BizAssert.isFalse(StringUtils.isAllEmpty(istDt, reqdDt), "MSG_ALT_NO_APPY_TARGET_DATA");

        int resultCount = mapper.updateInstallReqdDt( cntrNo, cntrSn, istDt, reqdDt) ;

        if(resultCount > 0){
            mapper.updateContractWellsDetailHist(cntrNo, cntrSn);
            mapper.insertContractWellsDetailHist(cntrNo, cntrSn);
        }

        return resultCount;
    }
}

