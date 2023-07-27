package com.kyowon.sms.wells.web.contract.b2b.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchDetailRes;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchReq;
import com.kyowon.sms.wells.web.contract.b2b.dvo.WcteB2bBznsActiDvo;

@Mapper
public interface WcteB2bBznsActiMapper {
    List<WcteB2bBznsActiDvo> selectRentalMutuAlncCheck(SearchReq dto);

    WcteB2bBznsActiDvo selectKeyMan(WcteB2bBznsActiDto.SearchKeyManReq dto);

    int insertSsopLeadCstBas(@Param("item")
    WcteB2bBznsActiDvo dvo);

    int updateSsopLeadCstBas(WcteB2bBznsActiDvo dvo);

    int updateSsopLeadCstChHist(String leadCstId);

    int insertSsopLeadCstChHist(String leadCstId);

    int deleteSsopLeadCstBas(String leadCstId);

    int insertSsopLeadCstRlpplDtl(@Param("item")
    WcteB2bBznsActiDvo dvo);

    int updateSsopLeadCstRlpplDtl(WcteB2bBznsActiDvo dvo);

    int updateSsopLeadCstRlpplChHist(String leadCstRlpplId);

    int insertSsopLeadCstRlpplChHist(String leadCstRlpplId);

    int deleteSsopLeadCstRlpplDtl(String leadCstRlpplId);

    int insertSsopOpptBas(@Param("item")
    WcteB2bBznsActiDvo dvo);

    int updateSsopOpptBas(WcteB2bBznsActiDvo dvo);

    int updateSsopOpptChHist(String opptId);

    int insertSsopOpptChHist(String opptId);

    int deleteSsopOpptBas(String opptId);

    List<SearchDetailRes> selectB2bBoMngtDtlIzs(String opptId);

    int insertSsopOpptDtl(@Param("item")
    WcteB2bBznsActiDvo dvo);

    int updateSsopOpptDtl(WcteB2bBznsActiDvo dvo);

    int updateSsopOpptDchHist(String opptId, String opptSn);

    int insertSsopOpptDchHist(String opptId, String opptSn);

    int deleteSsopOpptDtl(String opptId, String opptSn);
}
