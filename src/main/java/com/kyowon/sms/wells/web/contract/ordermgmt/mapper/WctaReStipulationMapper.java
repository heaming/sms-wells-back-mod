package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractRestipulationCntrRegDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalRstlIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaReStipulationMapper {

    PagingResult<SearchRes> selectReStipulationCustomers(
        SearchReq dto,
        PageInfo pageInfo
    );

    Integer selectReStipulationCustomerCounts(
        SearchReq dto
    );

    List<BasInfoRes> selectReStipulationStandardInfo(String cntrNo, Integer cntrSn);

    ContractRes selectRestipulationContractInfo(String cntrNo, Integer cntrSn);

    WctaRentalRstlIzDvo selectRentalRstlIz(String cntrNo, Integer cntrSn);

    int insertRestipulationCntrReg(WctaContractRestipulationCntrRegDvo dvo);

    int updateCntrDtlRestipulation(WctaContractRestipulationCntrRegDvo dvo);

}
