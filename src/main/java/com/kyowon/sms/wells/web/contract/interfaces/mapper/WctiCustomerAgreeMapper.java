package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerAgreeDvo;

@Mapper
public interface WctiCustomerAgreeMapper {
    List<SearchRes> selectCustomerAgree(SearchReq dto);

    List<String> selectCstAgIdBas(WctiCustomerAgreeDvo dvo);

    List<String> selectCstAgId(WctiCustomerAgreeDvo dvo);

    int insertCubsCstAgIz(@Param("item")
    WctiCustomerAgreeDvo dvo);

    int insertCubsCstAgIzDtl(WctiCustomerAgreeDvo dvo);

    int updateCubsCstAgIz(WctiCustomerAgreeDvo dvo);

    int updateCubsCstAgIzDtl(WctiCustomerAgreeDvo dvo);
}
