package com.kyowon.sms.wells.web.service.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
    public Map convertVoToMap(Object vo){
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
    public int insertCstSvBfsvcAsnHistByMap(Object vo){
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
    public int insertCstBfsvcOjHistByMap(Object vo){
        return mapper.insertCstBfsvcOjHistByMap(this.convertVoToMap(vo));
    }

    /**
     * 고객서비스BS배정이력 History Log By Pk
     * (TB_SVPD_CST_SV_BFSVC_ASN_HIST)
     * @param cstSvAsnNo
     * @return
     */
    @Transactional
    public int insertCstSvBfsvcAsnHistByPk(String cstSvAsnNo){
//        return mapper.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo);
        return insertCstSvBfsvcAsnHistByPk(cstSvAsnNo, "N");
    }
    @Transactional
    public int insertCstSvBfsvcAsnHistByPk(String cstSvAsnNo, String dtaDlYn){
        return mapper.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo, dtaDlYn);
    }

}
