package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMachineChangeDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMachineChangeDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiMachineChangeConverter;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiMachineChangeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiMachineChangeService {

    private final WctiMachineChangeMapper mapper;
    private final WctiMachineChangeConverter converter;

     /**
     * 고객센터 내 기기변경 대상 계약번호, 계약일련번호를 입력받아 기기변경 대상 계약 정보 등을 조회를 위한 I/F 대상 프로그램
     *
     * @programId W-SS-I-0014
     * @ingerfaceId EAI_WSSI1057
     * @param dto
     * @return list
     */
    public List<SearchRes> getMachineChanges(SearchReq dto) {
        return converter.mapAllWctiMachineChangeDvoToSearchRes(mapper.selectMachineChanges(dto));
    }
}
