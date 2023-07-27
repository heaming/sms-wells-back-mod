package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaInstallationShippingDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaInstallationShippingMapper {

    PagingResult<WctaInstallationShippingDvo> selectInstallationShippings(
        WctaInstallationShippingDvo dvo, PageInfo pageinfo
    );

    List<WctaInstallationShippingDvo> selectInstallationShippings(WctaInstallationShippingDvo dvo);

    List<WctaInstallationShippingDvo> selectKiwiInstallOrders(List<WctaInstallationShippingDvo> dvo);

    WctaInstallationShippingDvo selectCheckCancel(WctaInstallationShippingDvo dvo);

    WctaInstallationShippingDvo selectSameSppDueDtNotSetCheck(WctaInstallationShippingDvo dvo);

    int updateSppDuedt(WctaInstallationShippingDvo dvo);

}
