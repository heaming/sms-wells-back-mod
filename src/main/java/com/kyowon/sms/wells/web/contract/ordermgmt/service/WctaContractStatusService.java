package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractStatusDto.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractStatusConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractStatusMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WctaContractStatusService {

    private final WctaContractStatusMapper mapper;
    private final WctaContractStatusConverter converter;

    public PagingResult<SearchRes> getContractStatusPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = converter.mapAllContractStatusDvoToSearchRes(mapper.selectContractStatusPages(dto, pageInfo))
                ;
        res.setPageInfo(pageInfo);
        return res;
    }

    public FindSummaryRes getContractStatusSummary(SearchReq dto) {
        return mapper.selectContractStatusSummary(dto).orElse(FindSummaryRes.builder()
                        .aprv("0").cnfm("0").delreq("0").inprgs("0").wrte("0")
                        .build());
    }

    public String getContractPrgsStatCd(String cntrNo) {
        return mapper.selectContractPrgsStatCd(cntrNo);
    }

    @Transactional
    public int removeContract(String cntrNo){
        /*
         계약기본 및 관련 테이블 업데이트 / 아래 테이블의 DTA_DL_YN = 'Y' 으로 업데이트 한다.
         - 계약기본 TB_SSCT_CNTR_BAS, 계약상세 TB_SSCT_CNTR_DTL(where CNTR_NO = {계약번호})
         - 계약고객관계 TB_SSCT_CNTR_CST_REL, 계약파트너관계 TB_SSCT_CNTR_PRTNR_REL, 계약주소관계 TB_SSCT_CNTR_ADR_REL, 계약주소지기본 TB_SSCT_CNTR_ADRPC_BAS
         - 계약결제관계 TB_SSCT_CNTR_STLM_REL, 계약결제기본 TB_SSCT_CNTR_STLM_BAS
         - 계약상품관계 TB_SSCT_CNTR_PD_REL, 계약WELLS상세 TB_SSCT_CNTR_WELLS_DTL
         - 계약가격산출내역 TB_SSCT_CNTR_PRC_CMPT_IZ, 계약프로모션내역 TB_SSCT_CNTR_PMOT_IZ, 사은품접수내역 TB_SSCT_FGPT_RCP_IZ
         - 계약관계 TB_SSCT_CNTR_REL, 기기변경내역 TB_SSCT_MCHN_CH_IZ, 관계사제휴내역 TB_SSCT_ACMPAL_CNTR_IZ
         - 렌탈재약정내역 TB_SSCT_RENTAL_RSTL_IZ
        */

        /*
        이력테이블도 동일하게 업데이트한다
        - 계약주소변경이력 TB_SSCT_CNTR_ADR_CH_HIST
        - 계약변경이력 TB_SSCT_CNTR_CH_HIST
        - 계약상세변경이력 TB_SSCT_CNTR_DCH_HIST
        - 계약상세상태변경이력 TB_SSCT_CNTR_DTL_STAT_CH_HIST
        - 계약결제변경이력 TB_SSCT_CNTR_STLM_CH_HIST
        - 계약WELLS상세변경이력 TB_SSCT_CNTR_WELLS_DCH_HIST
        - 관계사제휴계약변경이력 TB_SSCT_ACMPAL_CNTR_CH_HIST
        - 렌탈재약정변경이력 TB_SSCT_RENTAL_RSTL_CH_HIST
        */

        // DTA_DL_YN = 'Y' 으로 업데이트
        mapper.updateDtaDlYnCntrBas(cntrNo);
        mapper.updateDtaDlYnCntrDtl(cntrNo);

        mapper.updateDtaDlYnCntrCstRel(cntrNo);
        mapper.updateDtaDlYnCntrPrtnrRel(cntrNo);
        mapper.updateDtaDlYncntrAdrRel(cntrNo);
        mapper.updateDtaDlYnCntrAdrpcBas(cntrNo);

        mapper.updateDtaDlYnCntrStlmRel(cntrNo);
        mapper.updateDtaDlYnCntrStlmBas(cntrNo);

        mapper.updateDtaDlYnCntrPdRel(cntrNo);
        mapper.updateDtaDlYnCntrWellsDtl(cntrNo);

        mapper.updateDtaDlYnCntrPrcCmptIz(cntrNo);
        mapper.updateDtaDlYnCntrPmotIz(cntrNo);
        mapper.updateDtaDlYnFgptRcpIz(cntrNo);

        mapper.updateDtaDlYnCntrRel(cntrNo);
        mapper.updateDtaDlYnMchnChIz(cntrNo);

        mapper.updateDtaDlYnRentalRstlIz(cntrNo);

        //이력테이블 insert
        mapper.updateCntrAdrChHist(cntrNo);
        mapper.insertCntrAdrChHist(cntrNo);
        mapper.updateCntrChHist(cntrNo);
        mapper.insertCntrChHist(cntrNo);
        mapper.updateCntrDchHist(cntrNo);
        mapper.insertCntrDchHist(cntrNo);
        mapper.updateCntrDtlStatChHist(cntrNo);
        mapper.insertCntrDtlStatChHist(cntrNo);
        mapper.updateCntrStlmChHist(cntrNo);
        mapper.insertCntrStlmChHist(cntrNo);
        mapper.updateCntrWellsDchHist(cntrNo);
        mapper.insertCntrWellsDchHist(cntrNo);
        mapper.updateAcmpalCntrChHist(cntrNo);
        mapper.insertAcmpalCntrChHist(cntrNo);
        mapper.insertRentalRstlChHist(cntrNo);//HIST_STRT_DTM / HIST_END_DTM/ 칼럼 없음.

        return 0;
    }

    /**
     * 해당 계약에 대한 설치오더 대상 계약 일련번호 목록을 조회합니다.
     *
     * @param cntrNo - 계약번호
     * @return 설치오더 대상 계약 일련번호 목록
     */
    public List<Integer> getInstallationOrderTargets(String cntrNo) {
        return mapper.selectInstallationOrderTargets(cntrNo);
    }
}
