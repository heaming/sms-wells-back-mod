package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.FindRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiPackageContractRelationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiPackageContractRelationService {

    private final WctiPackageContractRelationMapper mapper;

    /**
    * 패키지연관 계약건 조회
    * @programId   EAI_WSSI1067
    * @param       req
    * @return      list
    */
    public List<FindRes> getPackageContractRelations(FindReq req) {
        return mapper.selectPackageContractRelations(req);
    }
}
