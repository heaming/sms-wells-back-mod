package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchFrdmPkgReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPkgBasePdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPkgPdDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineRes;

@Mapper
public interface WctaSeedingMapper {
    List<SearchMachineRes> selectMachinery(SearchMachineReq dto);

    Optional<WctaPkgBasePdDvo> selectWctaPkgBasePd(String basePdCd);

    List<WctaPkgPdDvo> selectCapsules(SearchFrdmPkgReq basePdCd);

    List<WctaPkgPdDvo> selectSeedings(SearchFrdmPkgReq basePdCd);
}
