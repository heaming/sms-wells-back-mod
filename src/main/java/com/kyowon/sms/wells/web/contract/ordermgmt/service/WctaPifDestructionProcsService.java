package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPifDestructionProcsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaPifDestructionProcsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaPifDestructionProcsService {
    private final WctaPifDestructionProcsMapper mapper;

    @Transactional
    public String savePifDestructionProcs(String cntrNo) {
        if (StringUtils.isEmpty(cntrNo))
            return "N";

        WctaPifDestructionProcsDvo resDvo = new WctaPifDestructionProcsDvo();

        List<WctaPifDestructionProcsDvo> dvos = mapper.selectCntrAdrpcBas(cntrNo);

        for (WctaPifDestructionProcsDvo dvo : dvos) {
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "RCGVP_KNM", dvo.getRcgvpKnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "RCGVP_ENM", dvo.getRcgvpEnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "ADR_ID", dvo.getAdrId());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "CRAL_LOCARA_TNO", dvo.getCralLocaraTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "MEXNO_ENCR", dvo.getMexnoEncr());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "CRAL_IDV_TNO", dvo.getCralIdvTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "LOCARA_TNO", dvo.getLocaraTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "EXNO_ENCR", dvo.getExnoEncr());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "IDV_TNO", dvo.getIdvTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADRPC_BAS", "EMADR", dvo.getEmadr());
        }

        dvos = mapper.selectCntrAdrChHist(cntrNo);

        for (WctaPifDestructionProcsDvo dvo : dvos) {
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "RCGVP_KNM", dvo.getRcgvpKnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "RCGVP_ENM", dvo.getRcgvpEnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "ADR_ID", dvo.getAdrId());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "CRAL_LOCARA_TNO", dvo.getCralLocaraTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "MEXNO_ENCR", dvo.getMexnoEncr());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "CRAL_IDV_TNO", dvo.getCralIdvTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "LOCARA_TNO", dvo.getLocaraTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "EXNO_ENCR", dvo.getExnoEncr());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "IDV_TNO", dvo.getIdvTno());
            mvsDstcRcvryDta("TB_SSCT_CNTR_ADR_CH_HIST", "EMADR", dvo.getEmadr());
        }

        dvos = mapper.selectCntrStlmBas(cntrNo);

        for (WctaPifDestructionProcsDvo dvo : dvos) {
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_BAS", "OWR_KNM", dvo.getOwrKnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_BAS", "OWR_ENM", dvo.getOwrEnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_BAS", "ACNO_ENCR", dvo.getAcnoEncr());
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_BAS", "CRCDNO_ENCR", dvo.getCrcdnoEncr());
        }

        dvos = mapper.selectCntrStlmChHist(cntrNo);

        for (WctaPifDestructionProcsDvo dvo : dvos) {
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_CH_HIST", "OWR_KNM", dvo.getOwrKnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_CH_HIST", "OWR_ENM", dvo.getOwrEnm());
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_CH_HIST", "ACNO_ENCR", dvo.getAcnoEncr());
            mvsDstcRcvryDta("TB_SSCT_CNTR_STLM_CH_HIST", "CRCDNO_ENCR", dvo.getCrcdnoEncr());
        }

        resDvo.setCntrNo(cntrNo);
        resDvo.setSpace(" ");
        mapper.updateCntrAdrpcBas(resDvo);
        mapper.updateCntrAdrChHists(resDvo);
        mapper.updateCntrStlmBas(resDvo);
        mapper.updateCntrStlmChHists(resDvo);
        return "Y";
    }

    public void mvsDstcRcvryDta(String tblId, String colId, String mvsDstcRcvryOjRefkVal) {
        WctaPifDestructionProcsDvo dvo = new WctaPifDestructionProcsDvo();
        dvo.setTblId(tblId);
        dvo.setColId(colId);
        dvo.setMvsDstcRcvryOjRefkVal(mvsDstcRcvryOjRefkVal);
        int recvryTblCnt = mapper.selectMvsDestructionRcvryTbl(dvo); /* 소산파기복구테이블 조회 */
        int rcvryColCnt = mapper.selectMvsDestructionRcvryCol(dvo); /* 소산파기복구컬럼 조회 */

        if (recvryTblCnt <= 0) {
            mapper.insertMvsDestructionRcvryTbl(dvo);
        }
        if (rcvryColCnt <= 0) {
            mapper.insertMvsDestructionRcvryCol(dvo);
        }
        mapper.insertMvsDestructionRcvryBas(dvo);
        mapper.insertMvsDestructionRcvryTblDtl(dvo);
        mapper.insertMvsDestructionRcvryColDtl(dvo);
    }
}
