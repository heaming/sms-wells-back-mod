package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep2Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep2Service {
    private final WctaContractRegStep2Mapper mapper;

    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep2Dvo step2Dvo = new WctaContractRegStep2Dvo();
        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        step2Dvo.setBas(bas);
        List<WctaContractDtlDvo> dtls = regService.selectProductInfos(cntrNo);
        for (WctaContractDtlDvo dtl : dtls) {
            int cntrSn = dtl.getCntrSn();

            dtl.setPdCd(dtl.getBasePdCd());
            // 판매유형코드 등에 따라 select option 조회
            String sellTpCd = dtl.getSellTpCd();
            WctaContractDtlDvo sels = selectProductSelects(
                WctaContractDto.SearchPdSelReq.builder()
                    .copnDvCd(bas.getCopnDvCd())
                    .sellInflwChnlDtlCd(bas.getSellInflwChnlDtlCd())
                    .pdCd(dtl.getBasePdCd())
                    .sellTpCd(sellTpCd)
                    .build()
            );

            // 계약관계 관련 정보 세팅
            List<WctaContractPdRelDvo> pdRels = regService.selectContractPdRel(cntrNo, cntrSn);
            List<WctaContractRelDvo> rels = regService.selectContractRel(cntrNo);
            WctaContractRelDvo rel = rels.stream().filter((r) -> r.getBaseDtlCntrSn() == cntrSn).findFirst()
                .orElse(WctaContractRelDvo.builder().build());
            dtl.setCntrRelDtlCd(rel.getCntrRelDtlCd());
            if (CtContractConst.SELL_TP_CD_RGSP.equals(sellTpCd)) {
                if ("216".equals(dtl.getCntrRelDtlCd())) {
                    // 정기배송 정보 세팅
                    dtl.setPkg(dtl.getPdCd());
                    WctaContractDtlDvo m = dtls.stream().filter((d) -> d.getCntrSn() == rel.getOjDtlCntrSn())
                        .findFirst()
                        .orElseThrow();
                    List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> pkgs = selectWelsfHcfPkgs(m.getPdCd());
                    pkgs.forEach((p) -> p.setCntrRelDtlCd(rel.getCntrRelDtlCd()));
                    dtl.setPkgs(pkgs);
                    dtl.setSdingCapsls(
                        mapper.selectSdingCapslInfos(
                            cntrNo, cntrSn, dtl.getPdCd(), pdRels.stream().map(r -> r.getOjPdCd()).toList()
                        )
                    );
                } else if ("214".equals(dtl.getCntrRelDtlCd())) {
                    // 단독 정기배송 정보 세팅
                    dtl.setSltrRglrSppMchn(
                        WctaContractRegStep2Dvo.PdSltrRglrSppMchn.builder()
                            .rglrSppMchnYn(true)
                            .ojCntrNo(rel.getOjDtlCntrNo())
                            .ojCntrSn(rel.getOjDtlCntrSn())
                            .pdNm(mapper.selectPdNm(rel.getOjDtlCntrNo(), rel.getOjDtlCntrSn()))
                            .build()
                    );
                    dtl.setSdingCapsls(
                        mapper.selectSdingCapslInfos(
                            cntrNo, cntrSn, dtl.getPdCd(), pdRels.stream().map(r -> r.getOjPdCd()).toList()
                        )
                    );
                }
            }
            if ("215".equals(dtl.getCntrRelDtlCd())) {
                // 1+1 정보 세팅
                dtl.setOpo(
                    WctaContractRegStep2Dvo.PdOpo.builder()
                        .opoYn(true)
                        .ojCntrNo(rel.getOjDtlCntrNo())
                        .ojCntrSn(rel.getOjDtlCntrSn())
                        .pdNm(mapper.selectPdNm(rel.getOjDtlCntrNo(), rel.getOjDtlCntrSn()))
                        .build()
                );
                dtl.setRentalDiscountFixed(true);
            }
            // 기기변경 내역 확인, 존재하는 경우 세팅
            List<WctaMachineChangeIzDvo> mchnChs = regService.selectMachineChangeIz(cntrNo);
            Optional<WctaMachineChangeIzDvo> mchnCh = mchnChs.stream().filter((m) -> m.getBaseCntrSn() == cntrSn)
                .findFirst();
            if (mchnCh.isPresent()) {
                WctaMachineChangeIzDvo m = mchnCh.get();
                m.setMchnChYn(true);
                m.setPdNm(mapper.selectPdNm(m.getOjCntrNo(), m.getOjCntrSn()));
                dtl.setMchnCh(m);
            }

            // select option 세팅(converter 사용?)
            dtl.setSvPdCds(sels.getSvPdCds());
            if (CtContractConst.SELL_TP_CD_SPAY.equals(sellTpCd)) {
                dtl.setAlncmpCntrDrmVals(null);
                dtl.setSellDscrCds(sels.getSellDscrCds());
                dtl.setSellDscDvCds(sels.getSellDscDvCds());
                dtl.setFrisuBfsvcPtrmNs(sels.getFrisuBfsvcPtrmNs());
            } else if (CtContractConst.SELL_TP_CD_RNTL.equals(sellTpCd)) {
                dtl.setStplPtrms(sels.getStplPtrms());
                dtl.setCntrPtrms(sels.getCntrPtrms());
                dtl.setRgstCss(sels.getRgstCss());
                dtl.setSellDscTpCds(sels.getSellDscTpCds());
                dtl.setSellDscrCds(sels.getSellDscrCds());
                dtl.setSellDscDvCds(sels.getSellDscDvCds());
            } else if (CtContractConst.SELL_TP_CD_MSH.equals(sellTpCd)) {
                dtl.setStplPtrms(sels.getStplPtrms());
            }

            // 계약상품관계 조회, 상품관계 유형코드가 기준-서비스("03")인 데이터가 있다면(서비스상품) 세팅
            WctaContractPdRelDvo svPdRel = pdRels.stream().filter((pdRel) -> pdRel.getPdRelTpCd().equals("03"))
                .findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(svPdRel)) {
                dtl.setSvPdCd(svPdRel.getOjPdCd());
            }

            // 제휴상품 노출판단, dto가 record 라서...
            boolean existAlncPds = false;
            if (CtContractConst.SELL_TP_CD_RNTL.equals(sellTpCd)) {
                if ("02".equals(bas.getCntrTpCd())) {
                    existAlncPds = mapper.isExistAlncPds(
                        WctaContractDto.SearchPdAmtReq.builder()
                            .pdCd(dtl.getBasePdCd())
                            .sellTpCd(sellTpCd)
                            .svPdCd(dtl.getSvPdCd())
                            .stplPtrm(dtl.getStplPtrm())
                            .sellDscTpCd(dtl.getSellDscTpCd())
                            .sellDscDvCd(dtl.getSellDscDvCd())
                            .sellDscrCd(dtl.getSellDscrCd())
                            .build()
                    );
                } else {
                    existAlncPds = mapper.isExistAlncPds(
                        WctaContractDto.SearchPdAmtReq.builder()
                            .pdCd(dtl.getBasePdCd())
                            .sellTpCd(sellTpCd)
                            .svPdCd(dtl.getSvPdCd())
                            .stplPtrm(dtl.getStplPtrm())
                            .sellDscTpCd(dtl.getSellDscTpCd())
                            .sellDscDvCd(dtl.getSellDscDvCd())
                            .build()
                    );
                }
            } else {
                existAlncPds = mapper.isExistAlncPds(
                    WctaContractDto.SearchPdAmtReq.builder()
                        .pdCd(dtl.getBasePdCd())
                        .sellTpCd(sellTpCd)
                        .svPdCd(dtl.getSvPdCd())
                        .build()
                );
            }
            dtl.setExistAlncPds(existAlncPds);

            // 계약가격산출내역 조회
            List<WctaContractPrcCmptIzDvo> prcCmptIzDvos = regService
                .selectContractPrcCmptIz(cntrNo, cntrSn);
            if (CollectionUtils.isNotEmpty(prcCmptIzDvos)) {
                WctaContractPrcCmptIzDvo prcCmptIz = prcCmptIzDvos.get(0);
                dtl.setPdPrcFnlDtlId(prcCmptIz.getPdPrcFnlDtlId());
                dtl.setVerSn(prcCmptIz.getVerSn());
                dtl.setFxamFxrtDvCd(prcCmptIz.getFxamFxrtDvCd());
                dtl.setCtrVal(prcCmptIz.getCtrVal());
                dtl.setPdPrcId(prcCmptIz.getPdPrcId());
            }

            // 계약WELLS상세 조회
            WctaContractWellsDtlDvo wellsDtlDvo = regService.selectContractWellsDtl(cntrNo, cntrSn);
            dtl.setFrisuBfsvcPtrmN(wellsDtlDvo.getFrisuBfsvcPtrmN());
            dtl.setFrisuAsPtrmN(wellsDtlDvo.getFrisuAsPtrmN());
        }
        step2Dvo.setDtls(dtls);
        return WctaContractRegDvo.builder()
            .step2(step2Dvo)
            .build();
    }

    public WctaContractRegStep2Dvo.PdAmtDvo selectProductPrices(WctaContractDto.SearchPdAmtReq dto) {
        List<WctaContractRegStep2Dvo.PdAmtDvo> pdAmts = mapper.selectProductPrices(dto);
        if (CtContractConst.SELL_TP_CD_MSH.equals(dto.sellTpCd())) {
            // 멤버십의 경우 멤버십 시작일~종료일 구간 확인
            WctaContractRegStep2Dvo.PdAmtDvo vp = null;
            for (WctaContractRegStep2Dvo.PdAmtDvo p : pdAmts) {
                if ((p.getMshStrtMcn() <= dto.rntlMcn() && dto.rntlMcn() <= p.getMshEndMcn())
                    && (ObjectUtils.isEmpty(vp) || vp.getMshEndMcn() > p.getMshEndMcn())) {
                    vp = p;
                }
            }
            if (ObjectUtils.isNotEmpty(vp)) {
                pdAmts = List.of(vp);
            }
        }
        if (pdAmts.size() == 1) {
            WctaContractRegStep2Dvo.PdAmtDvo pdAmt = pdAmts.get(0);
            // 제휴상품 노출여부 판단
            pdAmt.setExistAlncPds(mapper.isExistAlncPds(dto));
            return pdAmt;
        } else {
            // 가격정보 없거나 2건 이상 시 결정할 수 없으므로 빈 객체 return
            return new WctaContractRegStep2Dvo.PdAmtDvo();
        }
    }

    public WctaContractDtlDvo selectProductSelects(WctaContractDto.SearchPdSelReq dto) {
        String pdCd = dto.pdCd();
        String sellTpCd = dto.sellTpCd();
        String sellInflwChnlDtlCd = dto.sellInflwChnlDtlCd();
        List<String> mshPdCds = dto.mshPdCds();
        // 서비스상품은 전 유형 필수
        List<WctaContractRegStep2Dvo.PdDetailDvo> pdCds = mapper.selectProductServiceInfo(pdCd);
        if (CollectionUtils.isNotEmpty(mshPdCds)) {
            List<WctaContractRegStep2Dvo.PdDetailDvo> filteredPdCds = pdCds.stream()
                .filter((p) -> mshPdCds.contains(p.getCodeId())).toList();
            if (CollectionUtils.isNotEmpty(filteredPdCds)) {
                pdCds = filteredPdCds;
            }
        }
        WctaContractDtlDvo dvo = WctaContractDtlDvo.builder()
            .svPdCds(pdCds)
            .build();
        if (CtContractConst.SELL_TP_CD_SPAY.equals(sellTpCd)) {
            // 일시불인 경우 제휴상품목록(렌탈은 화면에서 select 변경될 때마다 실시간 체크)
            dvo.setAlncmpCntrDrmVals(null);
            dvo.setSellDscrCds(mapper.selectProductDsrtsSpay(dto));
            dvo.setSellDscDvCds(mapper.selectProductDsdvsSpay(dto));
            dvo.setFrisuBfsvcPtrmNs(mapper.selectProductFrisuMshPtrms(dto));
        } else if (CtContractConst.SELL_TP_CD_RNTL.equals(sellTpCd)) {
            dvo.setStplPtrms(mapper.selectProductStplPtrms(dto));
            dvo.setCntrPtrms(mapper.selectProductCntrPtrms(dto));
            dvo.setRgstCss(mapper.selectProductRgstCss(dto));
            dvo.setSellDscTpCds(mapper.selectProductDstps(dto));
            dvo.setSellDscrCds(mapper.selectProductDsrtsRntl(dto));
            dvo.setSellDscDvCds(mapper.selectProductDsdvsRntl(dto));
        } else if (CtContractConst.SELL_TP_CD_MSH.equals(sellTpCd)) {
            dvo.setStplPtrms(mapper.selectProductStplPtrms(dto));
        } else if (CtContractConst.SELL_TP_CD_RGSP.equals(sellTpCd)) {
            dvo.setSdingCapsls(mapper.selectSdingCapsls(pdCd));
        }
        return dvo;
    }

    private List<WctaContractRegStep2Dvo.PdClsfDvo> buildPdClsfs(String... kv) {
        List<WctaContractRegStep2Dvo.PdClsfDvo> pdClsfs = Lists.newArrayList();
        for (int i = 0; i < kv.length; i += 2) {
            pdClsfs.add(
                WctaContractRegStep2Dvo.PdClsfDvo.builder()
                    .pdClsfId(kv[i])
                    .pdClsfNm(kv[i + 1])
                    .products(Lists.newArrayList())
                    .build()
            );
        }
        return pdClsfs;
    }

    public WctaContractRegStep2Dvo selectProducts(String cntrNo, String pdFilter) {
        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);

        WctaContractRegStep2Dvo pdDvo = new WctaContractRegStep2Dvo();
        List<WctaContractRegStep2Dvo.PdClsfDvo> pdClsfs = buildPdClsfs(
            "1", "정수기",
            "2", "청정기",
            "3", "비데",
            "4", "삼성가전",
            "5", "정기배송",
            "6", "기타",
            "7", "복합상품"
        );

        String sellInflwChnlDtlCd = bas.getSellInflwChnlDtlCd();
        List<WctaContractRegStep2Dvo.PdDvo> pds;
        if (CtContractConst.CNTR_TP_CD_MSH.equals(bas.getCntrTpCd())) {
            // 멤버십인 경우 원계약에서 계약된 서비스상품코드(S단위) + 원계약의 제품코드(M단위)와 동일한 기준 상품만 조회
            WctaContractRelDvo rel = regService.selectContractRel(cntrNo).stream()
                .filter((r) -> r.getCntrRelDtlCd().equals("212")).findFirst().orElseThrow();
            String ojCntrNo = rel.getOjDtlCntrNo();
            Integer ojCntrSn = rel.getOjDtlCntrSn();
            // 원계약의 판매유형상세코드
            WctaContractDtlDvo dtl = regService.selectContractDtl(ojCntrNo).stream()
                .filter((d) -> d.getCntrSn() == ojCntrSn).findFirst().orElseThrow();
            // 원계약에서 계약된 서비스상품코드, 제품코드와 동일한 기준상품을 조회하기 위해 상품관계 테이블 조회, 대상상품코드 목록 추출
            List<String> mshPdCds = regService.selectContractPdRel(ojCntrNo, ojCntrSn).stream()
                .filter((p) -> StringUtils.equalsAny(p.getPdRelTpCd(), "03", "05"))
                .map((p) -> p.getOjPdCd()).distinct().toList();
            // 멤버십 대상상품이 있는 경우 상품관계 조건 추가, 정기배송 상품 제외
            pds = mapper.selectProducts(sellInflwChnlDtlCd, pdFilter, mshPdCds, dtl.getSellTpDtlCd()).stream()
                .collect(Collectors.toMap(WctaContractRegStep2Dvo.PdDvo::getPdCd, p -> p, (p, q) -> p)).values()
                .stream().toList();
            // 렌탈차월 조회
            int rntlMcn = mapper.selectRntlMcn(ojCntrNo, ojCntrSn);
            // 화면에서 서비스상품코드 제한하기 위해 조회한 멤버십 상품에 멤버십 서비스상품코드, 금액조회를 위한 렌탈차월 세팅
            pds.forEach((p) -> {
                p.setMshPdCds(mshPdCds);
                p.setRntlMcn(rntlMcn);
            });
        } else {
            // 일반적인 경우 정기배송 상품 포함, 상품관계 조건 없음
            pds = mapper.selectProducts(sellInflwChnlDtlCd, pdFilter);
        }
        for (WctaContractRegStep2Dvo.PdDvo pd : pds) {
            pdClsfs.stream().filter((pdClsf) -> pdClsf.getPdClsfId().equals(pd.getPdClsf())).findFirst().get()
                .getProducts().add(pd);
            // 단독정기배송일 때 세부상품리스트 추가
            if (CtContractConst.SELL_TP_CD_RGSP.equals(pd.getSellTpCd())) {
                pd.setSdingCapsls(mapper.selectSdingCapsls(pd.getPdCd()));
            }
        }
        pdClsfs.removeIf((pdClsf) -> CollectionUtils.isEmpty(pdClsf.getProducts()));
        pdDvo.setPdClsf(pdClsfs);
        // pdDvo.setProducts(pds);
        return pdDvo;
    }

    public List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> selectWelsfHcfPkgs(String pdCd) {
        List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> welsfHcfPkgs = mapper.selectWelsfHcfPkgs(pdCd);
        welsfHcfPkgs.forEach((pkg) -> {
            pkg.setSdingCapsls(mapper.selectSdingCapsls(pkg.getPdCd()));
        });
        return welsfHcfPkgs.stream().sorted(Comparator.comparing(WctaContractRegStep2Dvo.PdWelsfHcfPkg::getCodeId))
            .toList();
    }

    @Transactional
    public String saveContractStep2(WctaContractRegStep2Dvo dvo) {
        String now = DateUtil.todayNnow();
        WctaContractBasDvo bas = dvo.getBas();
        String cntrNo = bas.getCntrNo();
        // 0. 계약기본
        regService.updateCntrPrgsStatCd(cntrNo, CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP2);

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );

        // 1. step 저장은 계약기본 제외하고 delete and insert
        regService.removeStep2Data(cntrNo);
        regService.removeStep3Data(cntrNo);
        regService.removeStep4Data(cntrNo);

        for (WctaContractDtlDvo dtl : dvo.getDtls()) {
            int cntrSn = dtl.getCntrSn();
            String sellTpCd = dtl.getSellTpCd();

            dtl.setCntrNo(cntrNo);
            dtl.setBasePdCd(dtl.getPdCd());
            dtl.setSvPrd(0l); // TODO 서비스 주기 조회해서 저장해야 할듯(콤보에 주기 정보 없으므로)
            dtl.setBlgCrpCd("D0");
            dtl.setRveCrpCd("D0");
            dtl.setCoCd("2000");
            dtl.setPdBaseAmt(0l); // TODO 20230616
            dtl.setSellAmt(Math.multiplyExact(dtl.getPdQty(), dtl.getFnlAmt()));
            dtl.setSppDuedt(""); // TODO 배송예정일자
            dtl.setRstlYn(""); // TODO 재약정여부

            String mchnSellTpCd = "";
            if ("216".equals(dtl.getCntrRelDtlCd())) {
                // 정기배송(기기+모종캡슐)인 경우 기기의 계약기간, 약정기간 세팅
                WctaContractDtlDvo m = dvo.getDtls().stream().filter((d) -> d.getCntrSn() == cntrSn - 1).findFirst()
                    .orElseThrow();
                dtl.setCntrPtrm(m.getCntrPtrm());
                dtl.setStplPtrm(m.getStplPtrm());
                mchnSellTpCd = m.getSellTpCd();
            } else if ("214".equals(dtl.getCntrRelDtlCd())) {
                // 단독 정기배송인 경우 상품속성의 정기배송계약기간, 정기배송약정기간 세팅
                dtl.setCntrPtrm(dtl.getRglrSppCntrDvCd());
                dtl.setStplPtrm(dtl.getRglrSppDutyPtrmDvCd());
            }

            dtl.setCntrwTpCd(
                regService.getCntrwTpCd(dtl.getSellTpCd(), dtl.getSellTpDtlCd(), dtl.getCntrRelDtlCd(), mchnSellTpCd)
            );

            if (CtContractConst.SELL_TP_CD_SPAY.equals(sellTpCd)) {
                dtl.setCntrAmt(dtl.getFnlAmt());
                dtl.setCntrTam(dtl.getSellAmt());
            } else {
                dtl.setCntrTam(Math.multiplyExact(dtl.getCntrPtrm(), dtl.getFnlAmt()) + dtl.getCntrAmt());
            }

            // 2-1. 계약상세
            mapper.insertCntrDtlStep2(dtl);
            // 2-2. 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );

            // 3. 계약상품관계 (기준상품에 서비스가 있는경우)
            if (!CtContractConst.SELL_TP_CD_RGSP.equals(dtl.getSellTpCd())) {
                // 3-1. 정기배송이 아닌 경우, 서비스상품 조회 후 저장
                mapper.selectPdSvcsInBasePd(dtl.getBasePdCd()).stream()
                    .forEach(
                        (pdRel) -> {
                            pdRel.setCntrNo(cntrNo);
                            pdRel.setCntrSn(cntrSn);
                            pdRel.setVlStrtDtm(now);
                            pdRel.setVlEndDtm(CtContractConst.END_DTM);
                            pdRel.setPdQty(1l);
                            mapper.insertCntrPdRelStep2(pdRel);
                        }
                    );
                if (StringUtils.isNotEmpty(dtl.getSvPdCd())) {
                    String pdRelId = mapper.selectProductRelId(dtl.getBasePdCd(), dtl.getSvPdCd());
                    mapper.insertCntrPdRelStep2(
                        WctaContractPdRelDvo.builder()
                            .cntrNo(cntrNo)
                            .cntrSn(cntrSn)
                            .pdRelId(pdRelId)
                            .vlStrtDtm(now)
                            .vlEndDtm(CtContractConst.END_DTM)
                            .ojPdCd(dtl.getSvPdCd())
                            .basePdCd(dtl.getBasePdCd())
                            .pdRelTpCd("03")
                            .pdQty(1l)
                            .build()
                    );
                }
            } else {
                // 3-2. 정기배송제품
                if (CollectionUtils.isNotEmpty(dtl.getSdingCapsls())) {
                    dtl.getSdingCapsls().forEach((pdSdingCapsl -> {
                        mapper.insertCntrPdRelStep2(
                            WctaContractPdRelDvo.builder()
                                .cntrNo(cntrNo)
                                .cntrSn(cntrSn)
                                .pdRelId(pdSdingCapsl.getPdRelId())
                                .vlStrtDtm(now)
                                .vlEndDtm(CtContractConst.END_DTM)
                                .ojPdCd(pdSdingCapsl.getPartPdCd())
                                .basePdCd(dtl.getPdCd())
                                .pdRelTpCd(pdSdingCapsl.getPdRelTpCd())
                                .pdQty(pdSdingCapsl.getItmQty())
                                .build()
                        );
                    }));
                }
            }

            // 4. 계약관계 - 1+1, 다건구매할인, 복합상품구매, 법인다건구매(기기변경 제외) - 상세 단위당 계약관계 대상 1개_20230710
            if (StringUtils.containsAny(
                dtl.getCntrRelDtlCd(),
                "216", "214", "215", "22P", "22M", "22W"
            )) {
                String ojCntrNo = cntrNo;
                Integer ojCntrSn = cntrSn;
                if ("216".equals(dtl.getCntrRelDtlCd())) {
                    ojCntrNo = cntrNo;
                    ojCntrSn = cntrSn - 1;
                } else if ("214".equals(dtl.getCntrRelDtlCd())) {
                    WctaContractRegStep2Dvo.PdSltrRglrSppMchn sltrRglrSppMchn = dtl.getSltrRglrSppMchn();
                    ojCntrNo = sltrRglrSppMchn.getOjCntrNo();
                    ojCntrSn = sltrRglrSppMchn.getOjCntrSn();
                } else if ("215".equals(dtl.getCntrRelDtlCd())) {
                    WctaContractRegStep2Dvo.PdOpo opo = dtl.getOpo();
                    ojCntrNo = opo.getOjCntrNo();
                    ojCntrSn = opo.getOjCntrSn();
                }
                mapper.insertCntrRelStep2(
                    WctaContractRelDvo.builder()
                        .baseDtlCntrNo(cntrNo)
                        .baseDtlCntrSn(cntrSn)
                        .ojDtlCntrNo(ojCntrNo)
                        .ojDtlCntrSn(ojCntrSn)
                        .vlStrtDtm(now)
                        .vlEndDtm(CtContractConst.END_DTM)
                        .cntrRelDtlCd(dtl.getCntrRelDtlCd())
                        .cntrRelTpCd("20")
                        .cntrUnitTpCd("020")
                        .build()
                );
            }

            // 5. 기기변경내역
            WctaMachineChangeIzDvo mchnCh = dtl.getMchnCh();
            if (ObjectUtils.isNotEmpty(mchnCh) && mchnCh.getMchnChYn()) {
                mchnCh.setBaseCntrNo(cntrNo);
                mchnCh.setBaseCntrSn(cntrSn);
                mchnCh.setMchnChSn(1);
                mapper.insertMchnChIzStep2(mchnCh);

                historyService.createMachineChangeHistory(
                    WctzMachineChangeHistoryDvo.builder()
                        .baseCntrNo(cntrNo)
                        .baseCntrSn(cntrSn)
                        .histStrtDtm(now)
                        .build()
                );
            }

            /* TODO 프로모션, 사은품 정의되면 처리
            // 6. 계약프로모션내역(프로모션을 선택한 경우)
            mapper.insertCntrPmotIzStep2(
                WctaContractPmotIzDvo.builder()
                    .build()
            );
            // 7. 사은품접수내역(사은품이 있는 경우)
            mapper.insertFgptRcpIzStep2(
                WctaFgptRcpIzDvo.builder()
                    .build()
            );
             */

            // 8. 관계사제휴계약내역

            // 9-1. 계약가격산출내역
            mapper.insertCntrPrcCmptIzStep2(
                WctaContractPrcCmptIzDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .pdCd(dtl.getBasePdCd())
                    .pdPrcFnlDtlId(dtl.getPdPrcFnlDtlId())
                    .verSn(dtl.getVerSn())
                    .fxamFxrtDvCd(dtl.getFxamFxrtDvCd())
                    .ctrVal(dtl.getCtrVal())
                    .fnlVal(dtl.getFnlAmt())
                    .pdPrcId(dtl.getPdPrcId())
                    .build()
            );
            // 9-2. 계약가격산출변경이력
            historyService.createCntrPrccchHistory(
                WctzCntrPrccchHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );

            // 10. 계약WELLS상세
            mapper.insertCntrWellsDtlStep2(
                WctaContractWellsDtlDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .frisuBfsvcPtrmN(dtl.getFrisuBfsvcPtrmN())
                    .frisuAsPtrmN(dtl.getFrisuAsPtrmN())
                    .sellEvCd("") // TODO 판매행사코드
                    .ocoCpsBzsDvCd("") // TODO 타사보상업체코드
                    .build()
            );
            historyService.createContractWellsDetailChangeHistory(
                WctzContractWellsDetailHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );
        }

        return cntrNo;
    }
}
