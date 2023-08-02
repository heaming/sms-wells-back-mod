package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractProductDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCompanyInstallDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaCompanyInstallMapper {

    PagingResult<WctaCompanyInstallDvo> selectCompanyInstallPages(
        WctaCompanyInstallDvo dvo,
        PageInfo pageInfo
    );

    List<WctaCompanyInstallDvo> selectCompanyInstallPages(
        WctaCompanyInstallDvo dvo
    );

    List<SearchService> selectCompanyServices(String pdCd);

    Optional<WctiContractProductDvo> selectProductInfo(String basePdCd);

    int updateContractDetail(WctaCompanyInstallDvo dvo);

    int updateContractWellsDetail(WctaCompanyInstallDvo dvo);

    List<String> selectCustomer(WctaCompanyInstallDvo dvo);
}
