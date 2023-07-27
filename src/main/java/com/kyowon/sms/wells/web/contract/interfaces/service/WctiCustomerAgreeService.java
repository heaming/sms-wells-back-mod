package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiCustomerAgreeConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SaveReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerAgreeDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiCustomerAgreeMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiCustomerAgreeService {

    private final WctiCustomerAgreeMapper mapper;
    private final WctiCustomerAgreeConverter converter;

    /**
    * 개인정보 동의 현황 조회
    * 고객번호 또는 계약번호 단위의 개인정보 동의 현황 조회한다.
    * @interfaceId   EAI_WCUI1014
    * @programId   W-SS-I-0050
    * @param       dto
    * @return      list
    */
    public List<SearchRes> getCustomerAgree(SearchReq dto) {
        if (StringUtils.isAllEmpty(dto.cntrNo(), dto.cstNo())) {
            throw new BizException("고객번호 또는 계약번호는 필수입니다.");
        }
        return mapper.selectCustomerAgree(dto);
    }

    /**
    * 개인정보 동의 내역 저장
    * 계약번호, 동의항목구분코드에 따른 개인정보 동의 저장/변경한다.
    * @interfaceId   EAI_WCUI1015
    * @programId   W-SS-I-0051
    * @param       dtos
    * @return      String
    */
    @Transactional
    public List<WctiCustomerAgreeDto.SaveRes> saveCustomerAgrees(List<SaveReq> dtos) {
        List<WctiCustomerAgreeDto.SaveRes> result = new ArrayList<>();
        for (SaveReq dto : dtos) {
            WctiCustomerAgreeDvo dvo = converter.mapSaveReqToWctiCustomerAgreeDvo(dto);
            List<String> cstAgIds = mapper.selectCstAgIdBas(dvo);
            if (cstAgIds.size() > 0) {
                for (String cstAgId : cstAgIds) {
                    dvo.setCstAgId(cstAgId);
                    mapper.updateCubsCstAgIz(dvo);
                    mapper.updateCubsCstAgIzDtl(dvo);
                }
                WctiCustomerAgreeDto.SaveRes res = new WctiCustomerAgreeDto.SaveRes("Y");
                result.add(res);
            } else {
                cstAgIds = mapper.selectCstAgId(dvo);
                if (cstAgIds.size() > 0) {
                    for (String cstAgId : cstAgIds) {
                        dvo.setCstAgId(cstAgId);
                        mapper.updateCubsCstAgIz(dvo);
                        mapper.insertCubsCstAgIzDtl(dvo);
                    }
                } else {
                    mapper.insertCubsCstAgIz(dvo);
                    mapper.insertCubsCstAgIzDtl(dvo);

                }
                WctiCustomerAgreeDto.SaveRes res = new WctiCustomerAgreeDto.SaveRes("Y");
                result.add(res);
            }
        }
        return result;
    }
}
