package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractRelationDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractRelationDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractRelationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractRelationService {
    private final WctiContractRelationMapper mapper;

    /**
    * 연관계약 건 목록 조회 - 입력받은 계약번호, 계약일련번호에 대해 계약 관계 내에서 '연계상품', '대체회원' 관계에 있는 계약정보를 조회
    * @programId   EAI_WSSI1048
    * @param       req
    * @return      list
    */
    public List<SearchRes> getRelatedContracts(SearchReq req) {
        return mapper.selectRelatedContracts(req);
    }
}
