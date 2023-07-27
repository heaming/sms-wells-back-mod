package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractRegConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep4Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep4Service {
    private final WctaContractRegStep4Mapper mapper;
    private final WctaContractRegConverter converter;

    private final WctaContractRegStep3Service regStep3Service;
    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep4Dvo step4Dvo = new WctaContractRegStep4Dvo();

        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        step4Dvo.setBas(bas);

        if (StringUtils.isEmpty(bas.getCstStlmInMthCd())) {
            // 신규인 경우
            bas.setCstStlmInMthCd("10");
        }

        List<WctaContractDtlDvo> dtls = regService.selectProductInfos(cntrNo);
        List<WctaContractAdrpcBasDvo> adrpcs = regService.selectContractAdrpcBas(cntrNo);
        regStep3Service.selectExistedDtls(cntrNo, bas, dtls, adrpcs);
        step4Dvo.setDtls(dtls);

        List<WctaContractCstRelDvo> cstRels = regService.selectContractCstRel(cntrNo);
        WctaContractCstRelDvo c = cstRels.stream().filter((v) -> "10".equals(v.getCntrCstRelTpCd())).findFirst().get();
        WctaContractCstRelDvo cntrtInfo = regService.selectCntrtInfoByCstNo(c.getCstNo());
        step4Dvo.setCntrt(converter.mergeContractCstRelDvo(c, cntrtInfo));

        List<WctaContractPrtnrRelDvo> prtnrRels = regService.selectContractPrtnrRel(cntrNo);
        if (prtnrRels.size() > 1) {
            // 파트너관계가 2건 이상인 경우, 계약파트너유형코드 010인 건
            step4Dvo.setPrtnr(prtnrRels.stream().filter((p) -> "010".equals(p.getCntrPrtnrTpCd())).findFirst().get());
        } else {
            step4Dvo.setPrtnr(prtnrRels.get(0));
        }

        // 법인이고, 계약자의 주소ID와 설치지 주소ID가 다른 경우가 존재한다면 첨부파일 노출
        String cntrtAdrId = cntrtInfo.getAdrId();
        List<WctaContractAdrRelDvo> adrRels = Lists.newArrayList();
        dtls.stream().forEach((dtl) -> adrRels.add(dtl.getAdrRel()));
        step4Dvo.setIsUseAttach(
            adrRels.stream()
                .anyMatch(
                    (adrRel) -> !cntrtAdrId.equals(
                        adrpcs.stream().filter((adrpc) -> adrpc.getCntrAdrpcId().equals(adrRel.getCntrAdrpcId()))
                            .findFirst().orElse(new WctaContractAdrpcBasDvo()).getAdrId()
                    )
                ) ? "Y" : "N"
        );

        step4Dvo.setCntrDtls(mapper.selectCntrDtls(cntrNo));
        step4Dvo.setStlmDtls(mapper.selectStlmDtls(cntrNo));

        return WctaContractRegDvo.builder()
            .step4(step4Dvo)
            .build();
    }

    public String saveContractStep4Temp(WctaContractRegStep4Dvo dvo) {
        return saveContractStep4(dvo, CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP4);
    }

    public String saveContractStep4(WctaContractRegStep4Dvo dvo) {
        return saveContractStep4(dvo, CtContractConst.CNTR_PRGS_STAT_CD_WRTE_FSH);
    }

    @Transactional
    public String saveContractStep4(WctaContractRegStep4Dvo dvo, String cntrPrgsStatCd) {
        String cntrNo = dvo.getBas().getCntrNo();
        // 0. 계약기본
        regService.updateCntrPrgsStatCd(cntrNo, cntrPrgsStatCd);
        mapper.updateCntrBasStep4(cntrNo, dvo.getBas().getCstStlmInMthCd());

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );
        return cntrNo;
    }
}
