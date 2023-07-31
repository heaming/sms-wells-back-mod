package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.FindDetailRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCancelBaseDvo;

@Mapper
public interface WctbCancelBaseMapper {

    List<SearchRes> selectCntrBases(SearchReq dto);

    WctbCancelBaseDvo selectCancelBase(SearchReq dto);

    WctbCancelBaseDvo selectRentalCancelBase(SearchReq dto);

    Optional<WctbCancelBaseDvo> selectRentalCancelBase(String cntrNo, String cntrSn, String cancelDt);

    WctbCancelBaseDvo selectMembershipCancelBase(SearchReq dto);

    Optional<WctbCancelBaseDvo> selectMembershipCancelBase(
        String cntrNo, String cntrSn, String cancelDt, String slCtrAmt, String dscDdctam,
        String filtDdctam
    );

    int insertContractResign(WctbCancelBaseDvo dvo);

    int updateContraceDetail(String cntrNo, String cntrSn);

    int updateContraceWellsDetail(String cntrNo, String cntrSn);

    String selectIsUpdateWellsDetail(String cntrNo, String cntrSn);

    int insertContractResignHistory(WctbCancelBaseDvo dvo);

    FindDetailRes selectCancelInfo(SearchReq dto);

    Optional<String> selectPartnerOgTpCd(String prtnrNo);
}
