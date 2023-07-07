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
     *  - 230706 조우섭 추가 설치일자 입력 시에 계약상세의 계약상품시작일자 setting 추가
     * @param cntrNo  (필수)계약번호
     *        cntrSn  (필수)계약일련번호
     *        istDt   설치일자
     *        reqdDt  철수일자
     * @return
     */
    public int saveInstallReqdDt(String cntrNo, String cntrSn, String istDt, String reqdDt, String sppDuedt) {
        ValidAssert.hasText(cntrNo);
        ValidAssert.hasText(cntrSn);
        BizAssert.isFalse(StringUtils.isAllEmpty(istDt, reqdDt), "MSG_ALT_NO_APPY_TARGET_DATA");

        int resultCount = mapper.updateInstallReqdDt( cntrNo, cntrSn, istDt, reqdDt) ;

        if(resultCount > 0){
            mapper.updateContractWellsDetailHist(cntrNo, cntrSn);
            mapper.insertContractWellsDetailHist(cntrNo, cntrSn);
        }

        int resultCount2 = 0, resultCount3 = 0;
        /* 예정일자가 있는 경우, 예정일자를 UPDATE */
        if (StringUtils.isNotEmpty(sppDuedt)) {
            resultCount2 = mapper.updateInstallOrderReqDt( cntrNo, cntrSn, sppDuedt) ;
        }

        /* 설치일자가 입력되었거나, 예정일자 계약상세의 계약상품시작일자를 update한다. */
        if ((resultCount > 0 && !istDt.isEmpty()) || resultCount2 > 0 ) {
            resultCount2= mapper.updateContractDetailPdStrtdt(cntrNo, cntrSn);
        }

        if (resultCount2 > 0 || resultCount3 > 0) {
            mapper.updateContractDetailHist(cntrNo, cntrSn);
            mapper.insertContractDetailHist(cntrNo, cntrSn);
        }

        return resultCount;
    }

    public int saveInstallOrderReqDt(String cntrNo, String cntrSn, String sppDuedt) {
        ValidAssert.hasText(cntrNo);
        ValidAssert.hasText(cntrSn);
        BizAssert.isFalse(StringUtils.isEmpty(sppDuedt), "MSG_ALT_NO_APPY_TARGET_DATA");

        int resultCount = mapper.updateInstallOrderReqDt( cntrNo, cntrSn, sppDuedt) ;

        if(resultCount > 0){
            mapper.updateContractDetailHist(cntrNo, cntrSn);
            mapper.insertContractDetailHist(cntrNo, cntrSn);
        }

        return resultCount;
    }

    public int saveDeliveryConfirm(String cntrNo, String cntrSn, String sppDuedt) {
        ValidAssert.hasText(cntrNo);
        ValidAssert.hasText(cntrSn);
        BizAssert.isFalse(StringUtils.isEmpty(sppDuedt), "MSG_ALT_NO_APPY_TARGET_DATA");

        int resultCount = mapper.updateInstallOrderReqDt( cntrNo, cntrSn, sppDuedt) ;

        if(resultCount > 0){
            mapper.updateContractDetailHist(cntrNo, cntrSn);
            mapper.insertContractDetailHist(cntrNo, cntrSn);
        }

        return resultCount;
    }
}

