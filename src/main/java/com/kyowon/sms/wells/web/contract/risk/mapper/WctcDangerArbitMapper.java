package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;

@Mapper
public interface WctcDangerArbitMapper {
    List<SearchRes> selectDangerArbitManagerial(SearchReq dto);

    String selectDuplicationCheck(
        String dangOjPrtnrNo,
        String dangOcStrtdt
    );

    SearchOrganizationRes selectOrganizationInfInqr(String baseYm, String prtnrNo, String ogTpCd);

    int updateDangerCheckIzDlYn(String dangOjPrtnrNo, String dangOcStrtdt);

    int updateDangerCheckChHist(String dangOjPrtnrNo, String dangOcStrtdt);

    int insertDangerCheckChHistY(String dangOjPrtnrNo, String dangOcStrtdt);

    int insertDangerCheckChHistN(String dangOjPrtnrNo, String dangOcStrtdt);

    int insertDangerCheckIz(@Param("item")
    WctcDangerArbitDvo dvo);

    int updateDangerCheckIz(WctcDangerArbitDvo dvo);
}
