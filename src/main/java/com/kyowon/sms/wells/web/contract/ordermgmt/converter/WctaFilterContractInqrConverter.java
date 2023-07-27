package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterContractInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaFilterContractInqrDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WctaFilterContractInqrConverter {
    PagingResult<WctaFilterContractInqrDto.SearchRes> mapWctaFilterContractInqrDvoToSearchRes(List<WctaFilterContractInqrDvo> dvos);

}

