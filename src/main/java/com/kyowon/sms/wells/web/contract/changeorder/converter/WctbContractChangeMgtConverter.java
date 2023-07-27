package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeDvo;

@Mapper(componentModel = "spring")
public interface WctbContractChangeMgtConverter {
    WctbContractChangeDvo findCheckReqToWctbContractChangeDvo(FindBeforeChangeCheckReq dto);

    FindCustomerInformationRes wctbContractChangeDvoToFindCustomerRes(WctbContractChangeDvo dvo);

    WctbContractChangeDvo saveCancelReqToWctbContractChangeDvo(SaveCancelReq dto);

    WctbContractChangeDvo saveChangeReqToWctbContractChangeDvo(SaveChangeReq dto);

    WctbContractChangeDvo mapEditPartnerReqToWctbContractChangeMngtDvo(
        EditPartnerReq dto
    );
}
