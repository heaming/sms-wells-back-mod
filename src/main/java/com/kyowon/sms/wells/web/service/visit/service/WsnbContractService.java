package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.CNTR_REL_DTL_CD_BEFORE_CONTRACT;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractRelationDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbContractMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbContractService {

    private final WsnbContractMapper mapper;

    public WsnbContractDvo getContract(String cntrNo, String cntrSn) {
        return mapper.selectContract(cntrNo, cntrSn).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

    public WsnbContractDvo getBeforeContract(String cntrNo, String cntrSn) {
        WsnbContractRelationDvo originContractRelation = getOriginContractRelation(cntrNo, cntrSn);

        return mapper
            .selectContract(originContractRelation.getBaseDtlCntrNo(), originContractRelation.getBaseDtlCntrSn())
            .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

    private WsnbContractRelationDvo getOriginContractRelation(String cntrNo, String cntrSn) {
        List<WsnbContractRelationDvo> contractRelations = mapper.selectContractRelations(
            cntrNo, cntrSn, CNTR_REL_DTL_CD_BEFORE_CONTRACT
        );

        BizAssert.notEmpty(contractRelations, "MSG_ALT_NO_DATA");
        return contractRelations.get(0);
    }

    public List<WsnbContractRelationDvo> getContractRelation(String cntrNo, String cntrSn, String cntrRelDtlCd) {
        return mapper.selectContractRelations(cntrNo, cntrSn, cntrRelDtlCd);
    }
}
