package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalContractsDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiRentalContractsDvo;

@Mapper
public interface WctiRentalContractsMapper {
    WctiRentalContractsDvo selectRentalContracts(SearchReq dto);

    SearchReq selectRegDlvr(SearchReq dto);

    WctiRentalContractsDvo selectRegDlvrCntr(String cntrNo, int cntrSn);
}
