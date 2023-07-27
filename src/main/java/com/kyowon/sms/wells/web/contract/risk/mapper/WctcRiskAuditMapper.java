package com.kyowon.sms.wells.web.contract.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchRes;

@Mapper
public interface WctcRiskAuditMapper {

    List<SearchRes> selectIrregularBznsInqr(SearchReq dto);

    int updateDangerCheckIz(String dangChkId);

    int updateDangerCheckChHist(String dangChkId);

    int insertDangerCheckChHist(String dangChkId);
}
