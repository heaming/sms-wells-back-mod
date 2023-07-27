package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractStatusDto.FindSummaryRes;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractStatusDto.SearchReq;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractStatusDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractStatusMapper {

    PagingResult<WctaContractStatusDvo> selectContractStatusPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    Optional<FindSummaryRes> selectContractStatusSummary(SearchReq dto);

    String selectContractPrgsStatCd(String cntrNo);

    int updateDtaDlYnCntrBas(String cntrNo);
    int updateDtaDlYnCntrDtl(String cntrNo);
    int updateDtaDlYnCntrCstRel(String cntrNo);
    int updateDtaDlYnCntrPrtnrRel(String cntrNo);
    int updateDtaDlYncntrAdrRel(String cntrNo);
    int updateDtaDlYnCntrAdrpcBas(String cntrNo);
    int updateDtaDlYnCntrStlmRel(String cntrNo);
    int updateDtaDlYnCntrStlmBas(String cntrNo);
    int updateDtaDlYnCntrPdRel(String cntrNo);
    int updateDtaDlYnCntrWellsDtl(String cntrNo);
    int updateDtaDlYnCntrPrcCmptIz(String cntrNo);
    int updateDtaDlYnCntrPmotIz(String cntrNo);
    int updateDtaDlYnFgptRcpIz(String cntrNo);
    int updateDtaDlYnCntrRel(String cntrNo);
    int updateDtaDlYnMchnChIz(String cntrNo);
    int updateDtaDlYnRentalRstlIz(String cntrNo);

    int updateCntrAdrChHist(String cntrNo);
    int insertCntrAdrChHist(String cntrNo);
    int updateCntrChHist(String cntrNo);
    int insertCntrChHist(String cntrNo);
    int updateCntrDchHist(String cntrNo);
    int insertCntrDchHist(String cntrNo);
    int updateCntrDtlStatChHist(String cntrNo);
    int insertCntrDtlStatChHist(String cntrNo);
    int updateCntrStlmChHist(String cntrNo);
    int insertCntrStlmChHist(String cntrNo);
    int updateCntrWellsDchHist(String cntrNo);
    int insertCntrWellsDchHist(String cntrNo);
    int updateAcmpalCntrChHist(String cntrNo);
    int insertAcmpalCntrChHist(String cntrNo);
    int insertRentalRstlChHist(String cntrNo); //HIST_STRT_DTM / HIST_END_DTM/ 칼럼 없음.

    List<Integer> selectInstallationOrderTargets(String cntrNo);
}
