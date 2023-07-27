package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.google.common.collect.Maps;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep3Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep3Service {
    private final WctaContractRegStep3Mapper mapper;

    private final WctaContractRegStep2Service regStep2Service;
    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep3Dvo step3Dvo = new WctaContractRegStep3Dvo();

        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        List<WctaContractDtlDvo> dtls = regService.selectProductInfos(cntrNo);
        List<WctaContractAdrpcBasDvo> adrpcs = regService.selectContractAdrpcBas(cntrNo);

        // 계약자 기준 기본주소지 조회
        WctaContractAdrpcBasDvo basAdrpc = mapper.selectAdrInfoByCntrCstNo(bas.getCntrCstNo());
        BizAssert.isTrue(ObjectUtils.isNotEmpty(basAdrpc), "계약자 주소지 정보가 없습니다.");
        basAdrpc.setCntrtRelCd("01");
        step3Dvo.setBasAdrpc(basAdrpc);

        if (regService.isNewCntr(bas.getCntrPrgsStatCd(), CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP3)) {
            dtls.forEach((dtl) -> {
                int cntrSn = dtl.getCntrSn();
                String sellTpCd = dtl.getSellTpCd();

                WctaContractWellsDtlDvo wellsDtl = regService.selectContractWellsDtl(cntrNo, cntrSn);
                wellsDtl.setIstPlcTpCd("1");
                wellsDtl.setFmmbN(1);
                wellsDtl.setUseElectTpCd("1");
                wellsDtl.setSrcwtTpCd("1");
                wellsDtl.setWprsItstTpCd("1");

                dtl.setAdrType("1");
                dtl.setAdrpc(basAdrpc);
                dtl.setAdrRel(new WctaContractAdrRelDvo());
                dtl.setWellsDtl(wellsDtl);
                dtl.setDpTpCdMsh("0203");
                dtl.setDpTpCdAftn("0203");
                dtl.setDpTpCdIdrv("0201");
                dtl.setTxinvPblOjYn("N");
                dtl.setRecapMshPtrm(0);
                dtl.setSodbtNftfCntrYn("N");
                dtl.setBlkApy("N");

                if (CtContractConst.SELL_TP_CD_SPAY.equals(sellTpCd)) {
                    // 유상멤버십기간 조회(step2에서 저장했던 정보를 바탕으로 가격 조회 서비스 사용, regService로 이동 검토)
                    WctaContractRegStep2Dvo.PdAmtDvo price = regStep2Service.selectProductPrices(
                        WctaContractDto.SearchPdAmtReq.builder()
                            .pdCd(dtl.getBasePdCd())
                            .sellTpCd(sellTpCd)
                            .sellInflwChnlDtlCd(bas.getSellInflwChnlDtlCd())
                            .frisuBfsvcPtrmN(dtl.getWellsDtl().getFrisuBfsvcPtrmN())
                            .sellDscDvCd(dtl.getSellDscDvCd())
                            .sellDscrCd(dtl.getSellDscrCd())
                            .build()
                    );
                    // TYPE A
                    if (!Objects.isNull(price) && !Objects.isNull(price.getRecapMshPtrm())
                        && price.getRecapMshPtrm() > 0) {
                        dtl.setRecapMshPtrm(price.getRecapMshPtrm());
                    }
                }
            });
        } else {
            selectExistedDtls(cntrNo, bas, dtls, adrpcs);
        }
        step3Dvo.setBas(bas);
        step3Dvo.setDtls(dtls);
        return WctaContractRegDvo.builder()
            .step3(step3Dvo)
            .build();
    }

    public void selectExistedDtls(
        String cntrNo, WctaContractBasDvo bas, List<WctaContractDtlDvo> dtls, List<WctaContractAdrpcBasDvo> adrpcs
    ) {
        dtls.forEach((dtl) -> {
            int cntrSn = dtl.getCntrSn();
            String sellTpCd = dtl.getSellTpCd();
            dtl.setBlkApy("N");

            WctaContractAdrRelDvo adrRel = regService.selectContractAdrRel(cntrNo, cntrSn);
            dtl.setAdrRel(adrRel);
            // 주소관계를 바탕으로 주소지기본 세팅
            dtl.setAdrpc(
                adrpcs.stream().filter((adrpc) -> adrpc.getCntrAdrpcId().equals(adrRel.getCntrAdrpcId()))
                    .findFirst().orElse(new WctaContractAdrpcBasDvo())
            );
            // wells상세
            dtl.setWellsDtl(regService.selectContractWellsDtl(cntrNo, cntrSn));

            // 결제정보
            List<WctaContractStlmRelDvo> stlmRels = regService.selectContractStlmRels(cntrNo, cntrSn);
            dtl.setStlmRels(stlmRels);

            if (CtContractConst.SELL_TP_CD_SPAY.equals(sellTpCd)) {
                // 유상멤버십기간 조회(step2에서 저장했던 정보를 바탕으로 가격 조회 서비스 사용, regService로 이동 검토)
                WctaContractRegStep2Dvo.PdAmtDvo price = regStep2Service.selectProductPrices(
                    WctaContractDto.SearchPdAmtReq.builder()
                        .pdCd(dtl.getBasePdCd())
                        .sellTpCd(sellTpCd)
                        .sellInflwChnlDtlCd(bas.getSellInflwChnlDtlCd())
                        .frisuBfsvcPtrmN(dtl.getWellsDtl().getFrisuBfsvcPtrmN())
                        .sellDscDvCd(dtl.getSellDscDvCd())
                        .sellDscrCd(dtl.getSellDscrCd())
                        .build()
                );
                // TYPE A, B
                if (!Objects.isNull(price) && !Objects.isNull(price.getRecapMshPtrm())
                    && price.getRecapMshPtrm() > 0) {
                    // TYPE A
                    WctaContractStlmRelDvo msh = stlmRels.stream()
                        .filter((stlmRel) -> stlmRel.getRveDvCd().equals("04")).findFirst()
                        .orElse(new WctaContractStlmRelDvo());
                    dtl.setRecapMshPtrm(price.getRecapMshPtrm());
                    dtl.setDpTpCdMsh(msh.getDpTpCd());
                    dtl.setMshAmt(msh.getStlmAmt());
                }
                dtl.setPdAmt(
                    stlmRels.stream()
                        .filter(
                            (stlmRel) -> stlmRel.getRveDvCd().equals("03") && stlmRel.getDpTpCd().equals("0201")
                        ).findFirst()
                        .orElse(new WctaContractStlmRelDvo()).getStlmAmt()
                );
            } else {
                // TYPE C
                dtl.setDpTpCdAftn(
                    stlmRels.stream()
                        .filter((stlmRel) -> stlmRel.getRveDvCd().equals(regService.getRveDvCd(sellTpCd)))
                        .findFirst().orElse(new WctaContractStlmRelDvo()).getDpTpCd()
                );
            }
            dtl.setDpTpCdIdrv(
                stlmRels.stream().filter((stlmRel) -> stlmRel.getRveDvCd().equals("01")).findFirst()
                    .orElse(new WctaContractStlmRelDvo()).getDpTpCd()
            );

            // 총판 비대면 계약여부(고객결제입력방법코드 30)
            if (Objects.isNull(bas.getCstStlmInMthCd())) {
                dtl.setSodbtNftfCntrYn("N");
            } else {
                dtl.setSodbtNftfCntrYn(bas.getCstStlmInMthCd().equals("30") ? "Y" : "N");
            }
        });
    }

    @Transactional
    public String saveContractStep3(WctaContractRegStep3Dvo dvo) {
        String now = DateUtil.todayNnow();
        WctaContractBasDvo bas = dvo.getBas();
        String cntrNo = bas.getCntrNo();
        List<WctaContractDtlDvo> dtls = dvo.getDtls();

        // step3, 4 정보 삭제
        regService.removeStep3Data(cntrNo);
        regService.removeStep4Data(cntrNo);

        // 0. 계약기본
        regService.updateCntrPrgsStatCd(cntrNo, CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP3);
        // 총판 비대면 계약 여부(모든 상세가 동일하므로 idx 0으로 확인), Y라면 bas 업데이트
        if ("Y".equals(dtls.get(0).getSodbtNftfCntrYn())) {
            mapper.updateCstStlmInMthCd(cntrNo, "30");
        }
        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );

        // 조건 일괄 적용 여부
        WctaContractDtlDvo blkApyDtl = dtls.stream().filter((dtl) -> "Y".equals(dtl.getBlkApy())).findFirst()
            .orElse(null);
        List<WctaContractAdrpcBasDvo> adrpcs = Lists.newArrayList();
        if (isBlkApy(blkApyDtl)) {
            // 일괄적용 주소지기본(1건)
            dtls.forEach((dtl) -> {
                dtl.getAdrRel().setAdrpcIdx(0);
            });
            WctaContractAdrpcBasDvo adrpc = blkApyDtl.getAdrpc();
            adrpc.setCntrNo(cntrNo);
            adrpc.setCntrCstNo(bas.getCntrCstNo());
            adrpc.setCopnDvCd(bas.getCopnDvCd());
            adrpc.setOgTpCd(bas.getSellOgTpCd());
            adrpc.setNatCd("KR");
            adrpc.setCnrSppYn("N");
            mapper.insertCntrAdrpcBasStep3(adrpc);
            adrpcs.add(adrpc);
        } else {
            // 일괄적용 아닌 case
            dtls.forEach((dtl) -> {
                WctaContractAdrpcBasDvo a = dtl.getAdrpc();
                int idx = -1;
                for (WctaContractAdrpcBasDvo adrpc : adrpcs) {
                    if (adrpc.getRcgvpKnm().equals(a.getRcgvpKnm())
                        && adrpc.getCralLocaraTno().equals(a.getCralLocaraTno())
                        && adrpc.getMexnoEncr().equals(a.getMexnoEncr())
                        && adrpc.getCralIdvTno().equals(a.getCralIdvTno())
                        && adrpc.getAdrId().equals(a.getAdrId())) {
                        idx++;
                        break;
                    }
                }
                if (idx > -1) {
                    dtl.getAdrRel().setAdrpcIdx(idx);
                } else {
                    dtl.getAdrRel().setAdrpcIdx(adrpcs.size());
                    adrpcs.add(a);
                }
            });

            // 계약주소지기본
            for (WctaContractAdrpcBasDvo adrpc : adrpcs) {
                adrpc.setCntrNo(cntrNo);
                adrpc.setCntrCstNo(bas.getCntrCstNo());
                adrpc.setCopnDvCd(bas.getCopnDvCd());
                adrpc.setOgTpCd(bas.getSellOgTpCd());
                adrpc.setNatCd("KR");
                adrpc.setCnrSppYn("N");
                mapper.insertCntrAdrpcBasStep3(adrpc);
            }
        }

        Map<String, String> stlmBasMap = Maps.newHashMap();
        for (WctaContractDtlDvo dtl : dtls) {
            int cntrSn = dtl.getCntrSn();
            WctaContractDtlDvo bDtl;
            if (isBlkApy(blkApyDtl)) {
                bDtl = blkApyDtl;
                bDtl.setCntrAmt(dtl.getCntrAmt());
                bDtl.setSellAmt(dtl.getSellAmt());
            } else {
                bDtl = dtl;
            }

            // 1-1. 계약상세
            bDtl.setRveCrpCd("D0");
            bDtl.setStlmTpCd("10");
            bDtl.setCrncyDvCd("KRW");
            // 계약금액 일시불일 때 step3저장, 이외의 경우 step2에서 등록비 선택으로 저장
            mapper.updateCntrDtlStep3(bDtl);
            // 1-2. 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(bDtl.getCntrNo())
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );

            // 2-2. 계약주소관계
            mapper.insertCntrAdrRelStep3(
                WctaContractAdrRelDvo.builder()
                    .vlStrtDtm(now)
                    .vlEndDtm(CtContractConst.END_DTM)
                    .adrpcTpCd("3")
                    .cntrUnitTpCd("020")
                    .dtlCntrNo(bDtl.getCntrNo())
                    .dtlCntrSn(cntrSn)
                    .cntrAdrpcId(adrpcs.get(bDtl.getAdrRel().getAdrpcIdx()).getCntrAdrpcId())
                    .build()
            );

            // 총판비대면 계약여부 Y가 아니라면 금액 저장
            if (!"Y".equals(bDtl.getSodbtNftfCntrYn())) {
                Long cntrAmt = bDtl.getCntrAmt();
                if (CtContractConst.SELL_TP_CD_SPAY.equals(bDtl.getSellTpCd())) {
                    // 일시불일 때
                    // 계약금, 01, 0101
                    if (!Objects.isNull(cntrAmt) && 0l < cntrAmt) {
                        createStlmInfo(
                            now, cntrNo, stlmBasMap, cntrSn, cntrAmt, bDtl.getDpTpCdIdrv(), "01", bas.getCntrCstNo()
                        );
                    }
                    Long pdAmt = bDtl.getPdAmt(); // 상품금액, 01, 0201
                    if (!Objects.isNull(pdAmt) && 0l < pdAmt) {
                        createStlmInfo(now, cntrNo, stlmBasMap, cntrSn, pdAmt, "0201", "03", bas.getCntrCstNo());
                    }
                    Long mshAmt = bDtl.getMshAmt(); // 04, 0203 || 0102
                    if (!Objects.isNull(mshAmt) && 0l < mshAmt) {
                        createStlmInfo(
                            now, cntrNo, stlmBasMap, cntrSn, pdAmt, bDtl.getDpTpCdMsh(), "04", bas.getCntrCstNo()
                        );
                    }
                } else {
                    // 그 외(일괄적용 케이스 반영)
                    // 등록비
                    if (!Objects.isNull(cntrAmt) && 0l < cntrAmt) {
                        createStlmInfo(
                            now, cntrNo, stlmBasMap, cntrSn, cntrAmt, bDtl.getDpTpCdIdrv(), "01", bas.getCntrCstNo()
                        );
                    }
                    // 월 렌탈료
                    Long sellAmt = bDtl.getSellAmt();
                    if (!Objects.isNull(sellAmt) && 0l < sellAmt) {
                        createStlmInfo(
                            now, cntrNo, stlmBasMap, cntrSn, sellAmt, bDtl.getDpTpCdAftn(),
                            regService.getRveDvCd(bDtl.getSellTpCd()), bas.getCntrCstNo()
                        );
                    }
                }
            }
            // 4. 계약wells상세
            WctaContractWellsDtlDvo wellsDtl = bDtl.getWellsDtl();
            wellsDtl.setCntrNo(cntrNo);
            wellsDtl.setCntrSn(cntrSn);
            mapper.updateCntrWellsDtlStep3(wellsDtl);
        }
        return cntrNo;
    }

    private static boolean isBlkApy(WctaContractDtlDvo blkApyDtl) {
        return ObjectUtils.isNotEmpty(blkApyDtl) && "Y".equals(blkApyDtl.getBlkApy());
    }

    @Transactional
    void createStlmInfo(
        String now, String cntrNo, Map<String, String> stlmBasMap, int cntrSn, Long cntrAmt, String dpTpCd,
        String rveDvCd, String cstNo
    ) {
        //        if (!stlmBasMap.containsKey(dpTpCd)) {
        WctaContractStlmBasDvo stlmBas = WctaContractStlmBasDvo.builder()
            .cntrNo(cntrNo)
            .cstNo(cstNo)
            .cntrtRelCd("01") // 통합계약에서는 본인 결제정보만 입력가능
            .dpTpCd(dpTpCd)
            .reuseOjYn("N")
            .build();
        mapper.insertCntrStlmBasStep3(stlmBas);
        stlmBasMap.put(dpTpCd, stlmBas.getCntrStlmId());
        //        }
        mapper.insertCntrStlmRelStep3(
            WctaContractStlmRelDvo.builder()
                .vlStrtDtm(now)
                .vlEndDtm(CtContractConst.END_DTM)
                .cntrUnitTpCd("020")
                .cntrStlmId(stlmBas.getCntrStlmId())
                .dtlCntrNo(cntrNo)
                .dtlCntrSn(cntrSn)
                .rveDvCd(rveDvCd)
                .dpTpCd(dpTpCd)
                .stlmAmt(cntrAmt)
                .build()
        );
    }
}
