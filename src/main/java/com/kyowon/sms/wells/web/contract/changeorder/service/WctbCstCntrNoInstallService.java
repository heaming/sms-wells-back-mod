package com.kyowon.sms.wells.web.contract.changeorder.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCstCntrNoInstallDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCstCntrNoInstallMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCstCntrNoInstallService {

    private final WctbCstCntrNoInstallMapper mapper;

    /**
     * (판매공통-wells) 설치처 변경(고객번호/계약번호 기준)
     *  - 입력받은 고객번호, 계약상세번호(계약번호 + 계약일련번호) 기준으로 계약기본의 배송지 정보를 변경한다.
     *  - 주소는 필수 입력이며 휴대전화번호는 optional(입력값이 존재하는 경우만 update)
     * @programId  W-SS-S-0084
     * @param  dvo     WctbCstCntrNoInstallDvo
     * @return Y(변경 성공)/N(변경실패)
     */
    @Transactional
    public String editCstCntrNoInstall(WctbCstCntrNoInstallDvo dvo) {
        //check parameter
        ValidAssert.hasText(dvo.getInDv());
        ValidAssert.hasText(dvo.getAdrId());
        String returnFlag = "N";

        //휴대전화번호의 경우 세가지 인자가 모두 존재하거나 모두 존재하지 않아야 하며, 존재하는 경우에만 update 포함
        if(StringUtils.isAnyEmpty(dvo.getCralLocaraTno(), dvo.getMexno(), dvo.getCralIdvTno())){
            dvo.setCralLocaraTno("");
            dvo.setMexno("");
            dvo.setCralIdvTno("");
        }

        if("1".equals(dvo.getInDv())){
            //입력구분이 1인경우, 계약번호, 계약일련번호 중 둘 중 하나라도 없는 경우 종료(Exception)
            BizAssert.isTrue(!StringUtils.isAnyEmpty(dvo.getCntrNo(), dvo.getCntrSn()) , "MSG_ALT_CNTR_NO_CNTR_SN_IN");
        }else if("2".equals(dvo.getInDv())){
            //입력구분이 2인경우, 고객번호가 존재하지 않으면 종료(Exception)
            BizAssert.isTrue(!StringUtils.isEmpty(dvo.getCstNo()),  "MSG_ALT_CHK_NCSR", new String[] {"MSG_TXT_CST_NO"});
        }else
            return returnFlag;

        //update address info
        int resultCount = mapper.updateCstCntrDeliver(dvo);

        //save history
        if (resultCount >= 1) {
            returnFlag = "Y";

            mapper.updateContractAddressHistory(dvo);
            mapper.insertContractAddressHistory(dvo);
        }

        return returnFlag;
    }
}
