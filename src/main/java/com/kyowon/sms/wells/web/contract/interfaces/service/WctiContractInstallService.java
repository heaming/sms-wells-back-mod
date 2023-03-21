package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractInstallConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto.SaveReq;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractInstallDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractInstallMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctiContractInstallService {
    private final WctiContractInstallMapper mapper;
    private final WctiContractInstallConverter converter;

    /**
     * 계약처, 설치처 정보 변경
     *  - 설치처 정보 변경에 한함
     *  - 입력받은 계약번호, 계약일련번호에 대한 설치처 주소, 전화번호 등을 변경한다.
     * @programId   W-SS-I-0008 / 인터페이스ID:EAI_WSSI1051
     * @param       req
     * @return      Y/N(결과 성공 여부)
     */
    @Transactional
    public String editContractInstall(SaveReq req) {
        String returnFlag = "N";
        WctiContractInstallDvo dvo = converter.mapSaveReqToWctiContractInstallDvo(req);

        //0. check parameter
        // 주소ID(ADR_ID),휴대지역전화번호(CRAL_LOCARA_TNO),휴대전화국번호(MEXNO),휴대개별전화번호(CRAL_IDV_TNO),지역전화번호(LOCARA_TNO),전화국번호(EXNO),개별전화번호(IDV_TNO)가 모두 NULL이면 처리결과 'N'으로 리턴(종료)
        if(StringUtils.isAllEmpty(dvo.getAdrId(), dvo.getCralLocaraTno(), dvo.getMexno(), dvo.getCralIdvTno(), dvo.getLocaraTno(), dvo.getExno(), dvo.getIdvTno())){
            return returnFlag;
        }

        //1. update install info
        int resultCount = mapper.updateContractInstall(dvo);

        //2. save history
        if (resultCount >= 1) {
            returnFlag = "Y";

            mapper.updateContractInstallHistory(dvo);
            mapper.insertContractInstallHistory(dvo);
        }

        return returnFlag;
    }
}
