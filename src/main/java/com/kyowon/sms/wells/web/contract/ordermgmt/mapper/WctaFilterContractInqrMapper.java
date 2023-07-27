package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaFilterContractInqrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterContractInqrDto.SearchReq;

@Mapper
public interface WctaFilterContractInqrMapper {
    List<WctaFilterContractInqrDvo> selectFilterContractInqrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

}
