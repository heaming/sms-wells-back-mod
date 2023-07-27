package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractRestipulationCntrRegDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalRstlIzDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaReStipulationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaReStipulationService {

    private final WctaReStipulationMapper mapper;
    private final WctzHistoryService historyService;

    public PagingResult<SearchRes> getReStipulationCustomerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReStipulationCustomers(dto, pageInfo);
    }

    public Integer getReStipulationCustomerCounts(SearchReq dto) {
        return mapper.selectReStipulationCustomerCounts(dto);
    }

    public List<BasInfoRes> getReStipulationStandardInfo(String cntrNo, Integer cntrSn) {
        return mapper.selectReStipulationStandardInfo(cntrNo, cntrSn);
    }

    public ContractRes getRestipulationContractInfo(String cntrNo, Integer cntrSn) {
        return mapper.selectRestipulationContractInfo(cntrNo, cntrSn);
    }

    public WctaRentalRstlIzDvo getReltalReStipulation(String cntrNo, Integer cntrSn) {
        return mapper.selectRentalRstlIz(cntrNo, cntrSn);
    }

    @Transactional
    public String saveRestipulationContractReg(WctaContractRestipulationCntrRegDvo dvo) {
        mapper.insertRestipulationCntrReg(dvo);
        mapper.updateCntrDtlRestipulation(dvo);
        historyService.createContractDetailChangeHistory(
            WctzCntrDetailChangeHistDvo.builder()
                .cntrNo(dvo.getCntrNo())
                .cntrSn(dvo.getCntrSn())
                .build()
        );
        return "";
    }

}
