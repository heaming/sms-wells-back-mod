package com.kyowon.sms.wells.web.service.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnzHistoryService {

    private final WsnzHistoryMapper mapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param vo 변환할 Source Value Object
     * @return Map 변환된 Target Value Object(Map)
     */
    public Map convertVoToMap(Object vo) {
        Map map = new HashMap<String, String>();
        map = objectMapper.convertValue(vo, Map.class);
        return map;
    }

    /**
     * 고객서비스BS배정이력 History Log
     * (TB_SVPD_CST_SV_BFSVC_ASN_HIST)
     * @param vo
     * cstSvAsnNo
     * asnOjYm
     * cntrNo
     * cntrSn
     * svBizMclsfCd
     * svBizDclsfCd
     * wkSn
     * asnPsicMngtDvCd
     * asnPsicDvCd
     * asnPsicPrtnrOgTpCd
     * asnPsicPrtnrNo
     * cnfmPsicMngtDvCd
     * cnfmPsicAsnDt
     * cnfmPsicAsnHh
     * cnfmPsicDvCd
     * cnfmPsicPrtnrOgTpCd
     * cnfmPsicPrtnrNo
     * vstCnfmdt
     * vstCnfmHh
     * vstOjLocaraCd
     * mngerRglvlDvCd
     * lstmmCrdovrYn
     * lstmmVstCnfmdt
     * lstmmVstCnfmHh
     * vstDuedt
     * vstExpHh
     * vstPrgsStatCd
     * wkExcnDt
     * wkCanRsonCd
     * asnTfDvCd
     * siteAwPdGrpCd
     * siteAwSvTpCd
     * siteAwAtcCd
     * cstCntcDt
     * cntcIchrPrtnrOgTpCd
     * cntcIchrPrtnrNo
     * cstCntcCd
     * cstCntcMoCn
     * spcAsTpCd
     * hpcallFwYn
     * ichrLocaraDvCd
     * arvDt
     * arvHh
     * wkGrpCd
     * locaraGdCd
     * locaraChaosGdCd
     * dtaDlYn
     *
     * @return
     */
    @Transactional
    public int insertCstSvBfsvcAsnHistByMap(Object vo) {
        return mapper.insertCstSvBfsvcAsnHistByMap(this.convertVoToMap(vo));
    }

    /**
     * 고객서비스BS대상이력 History Log
     * (TB_SVPD_CST_SV_BFSVC_OJ_HIST)
     * @param vo
     * cntrNo
     * asnOjYm
     * cntrSn
     * svBizMclsfCd
     * svBizDclsfCd
     * wkSn
     * mngrDvCd
     * rltdSvBizDclsfCd
     * clsfCdSrnPrntCn
     * adrId
     * lstmmCrdovrYn
     * bfVstDuedt
     * pdctPdCd
     * istNmnN
     * vstNmnN
     * egerWkYn
     * vstDuedt
     * asRefriDvCd
     * bfsvcRefriDvCd
     * vstDvCd
     * mtrStatCd
     * spcAsTpCd
     * cstSvAsnNo
     * dtaDlYn
     *
     * @return
     */
    @Transactional
    public int insertCstBfsvcOjHistByMap(Object vo) {
        return mapper.insertCstBfsvcOjHistByMap(this.convertVoToMap(vo));
    }

    /**
     * 고객서비스BS배정이력 History Log By Pk
     * (TB_SVPD_CST_SV_BFSVC_ASN_HIST)
     * @param cstSvAsnNo
     * @return
     */
    @Transactional
    public int insertCstSvBfsvcAsnHistByPk(String cstSvAsnNo) {
        return insertCstSvBfsvcAsnHistByPk(cstSvAsnNo, "N");
    }

    @Transactional
    public int insertCstSvBfsvcAsnHistByPk(String cstSvAsnNo, String dtaDlYn) {
        return mapper.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo, dtaDlYn);
    }

    /**
     * 고객서비스AS설치대상내역(TB_SVPD_CST_SVAS_IST_OJ_IZ) 변경이력 생성 by PK
     * @param asIstOjNo
     */
    @Transactional
    public int createCstSvasIstOjHistByPk(String asIstOjNo) {
        return mapper.insertCstSvasIstOjHistByPk(asIstOjNo);
    }

    /**
     * 고객서비스AS설치배정내역(TB_SVPD_CST_SVAS_IST_ASN_IZ) 변경이력 생성 by PK
     * @param cstSvAsnNo
     */
    @Transactional
    public int createCstSvasIstAsnHistByPk(String cstSvAsnNo) {
        return mapper.insertCstSvasIstAsnHistByPk(cstSvAsnNo);
    }

    /**
     * 계약상세(TB_SSCT_CNTR_DTL) 변경이력 생성 by PK
     * @param cntrNo
     * @param cntrSn
     */
    @Transactional
    public int saveCntrDchHistByPk(String cntrNo, String cntrSn) {
        saveCntrDchHistByPk(cntrNo, cntrSn);
        return createCntrDchHistByPk(cntrNo, cntrSn);
    };

    @Transactional
    public int editCntrDchHistByPk(String cntrNo, String cntrSn) {
        return mapper.updateCntrDchHistByPk(cntrNo, cntrSn);
    };

    @Transactional
    public int createCntrDchHistByPk(String cntrNo, String cntrSn) {
        return mapper.insertCntrDchHistByPk(cntrNo, cntrSn);
    };

    /**
     * 컨택기본(TB_SSOP_CTT_BAS) 변경이력 생성 by PK
     * @param cttOjId
     */
    @Transactional
    public int saveCttChHistByPk(String cttOjId) {
        editCttChHistByPk(cttOjId);
        return createCttChHistByPk(cttOjId);
    };

    @Transactional
    public int editCttChHistByPk(String cttOjId) {
        return mapper.updateCttChHistByPk(cttOjId);
    };

    @Transactional
    public int createCttChHistByPk(String cttOjId) {
        return mapper.insertCttChHistByPk(cttOjId);
    };

    /**
     * 고객서비스정기BS주기내역(TB_SVPD_CST_SV_RGBSPR_IZ) 변경이력 생성 by PK
     * @param cntrNo
     * @param cntrSn
     * @param svBizDclsfCd
     * @param wkSn
     */
    @Transactional
    public int createCstSvRgbsprHistByPk(String cntrNo, String cntrSn, String svBizDclsfCd, String wkSn) {
        return mapper.insertCstSvRgbsprHistByPk(cntrNo, cntrSn, svBizDclsfCd, wkSn);
    };

    /**
     * 고객서비스BS배정내역(TB_SVPD_CST_SV_BFSVC_ASN_IZ) 변경이력 생성 by PK
     * @param cstSvAsnNo
     */
    @Transactional
    public int createCstSvBfsvcAsnHistByPk(String cstSvAsnNo) {
        return mapper.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo, "N");
    }
}
