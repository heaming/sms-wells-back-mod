package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaBogoCustomerCheckDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaBogoCustomerCheckDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaBogoCustomerCheckResultDvo;

@Mapper
public interface WctaBogoCustomerCheckMapper {

    List<WctaBogoCustomerCheckResultDvo> selectEmployeePurchaseCheck(WctaBogoCustomerCheckDvo dvo);

    List<WctaBogoCustomerCheckResultDvo> selectInstallmentCustomers(WctaBogoCustomerCheckDvo dvo);

    List<WctaBogoCustomerCheckResultDvo> selectRentalCustomers(WctaBogoCustomerCheckDvo dvo);

    List<WctaBogoCustomerCheckResultDvo> selectMembershipCustomers(WctaBogoCustomerCheckDvo dvo);

    int selectCountLinkageAvailable(WctaBogoCustomerCheckDvo dvo);

    List<SearchRes> selectCountBogoCustomerCheck(WctaBogoCustomerCheckDvo dvo);
}
