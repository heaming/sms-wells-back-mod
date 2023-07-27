package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sflex.common.common.dto.SujiewonDto;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sms.common.web.contract.zcommon.constants.*;
import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.common.service.WctzContractNumberService;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaReceiptBulkUploadConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaReceiptBulkUploadMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.*;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WctaReceiptBulkUploadService {

    private final WctaReceiptBulkUploadConverter converter;
    private final WctaReceiptBulkUploadMapper mapper;
    private final SujiewonService sujiewonService;
    private final WctzHistoryService historyService;
    private final WctzContractNumberService contractNumberService;

    /**
     * 가망고객기본 단건 조회
     */
    public WctzPspcCstBasDvo getPspcCstBasByPk(String pspcCstId) {
        return mapper.selectPspcCstBasByPk(pspcCstId).orElseThrow(() -> new BizException("MSG_ALT_SVE_ERR"));
    }

    public ValidateProspectRes validateProspect(ValidateProspectReq req) {
        SujiewonDto.FormatRes adr = getFormattedAddresses(req.adr1(), req.adr2());

        WctzPdBasDvo pdBas = getPdBas(req.basePdCd());

        if (StringUtil.isNotBlank(req.svPdCd())) {
            boolean svExist = mapper.isExistServiceProduct(req.svPdCd());
            BizAssert.isTrue(svExist, "서비스상품코드를 확인해 주시길 바랍니다.");
        }

        return ValidateProspectRes.builder()
            .adr(adr)
            .pdBas(pdBas)
            .build();
    }

    @Deprecated
    @Transactional(timeout = 600)
    public int createProspectCsts(List<CreateProspectCstReq> reqs) {
        reqs.forEach(this::createProspectCst);
        return 1;
    }

    public int createBulkProspectCsts(List<CreateProspectCstReq> reqs) {
        List<WctaBulkProspectCustomerDvo> wctaBulkProspectCustomerDvos = reqs.stream()
            .map(req -> WctaBulkProspectCustomerDvo.builder()
                .wctzPspcCstBasDvo(converter.mapCreateProspectCstReqToWctaPspcCstBasDvo(req))
                .wctzPspcCstCnslBasDvo(converter.mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(req))
                .wctzPspcCstCnslRcmdIzDvo(new WctzPspcCstCnslRcmdIzDvo())
                .build())
            .toList();

        /* 쿼리에서 한방에 처리하기 위해서 키가 정수형이라는 가정이 있어야합니다. 아니면 시퀀스라도 있으면 좋을 것 같아요. */
        String firstPspcCstId = mapper.selectPspcCstIdForNewPspcCstBas();
        long iHateThis1 = Long.parseLong(firstPspcCstId);

        String firstPspcCstCnslId = mapper.selectPspcCstCnslIdForNewPspcCstCnslBas();
        long iHateThis2 = Long.parseLong(firstPspcCstCnslId);

        for (WctaBulkProspectCustomerDvo wctaBulkProspectCustomerDvo : wctaBulkProspectCustomerDvos) {
            wctaBulkProspectCustomerDvo.setPspcCstId(String.format("%015d", iHateThis1++));
            wctaBulkProspectCustomerDvo.setPspcCstCnslId(String.format("%015d", iHateThis2++));
            wctaBulkProspectCustomerDvo.setPspcCstCnslSn(1);
        }

        int result = mapper.insertProspectCustomers(wctaBulkProspectCustomerDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");

        return 1;
    }

    public ValidateBulkRentalRes validateBulkRental(ValidateBulkRentalReq req) {

        WctzCstBasDvo basDvo = converter.mapValidateBulkRentalReqToWctaCstBasDvo(req);
        basDvo.setMexnoEncr(DbEncUtil.enc(basDvo.getMexnoEncr())); /* 특정 메소드 전치사만 어노테이션 작동한다...*/
        BizAssert.isTrue(mapper.isExistCstBas(basDvo), "고객 정보를 조회할 수 없습니다. 고객 정보를 다시 확인해주세요.");

        SujiewonDto.FormatRes adr = getFormattedAddresses(req.adr1(), req.adr2());

        WctzPdBasDvo pdBas = getPdBas(req.basePdCd());

        List<WctzPdRelDvo> pdRelDvos = getPdRels(req.basePdCd());

        WctzPdRelDvo pdctPdRel = pdRelDvos.stream()
            .filter(wctzPdRelDvo -> "05".equals(wctzPdRelDvo.getPdRelTpCd()))
            .findFirst()
            .orElseThrow(
                () -> new BizException("헤당 상품에 해당하는 제품이 없습니다.")
            );

        boolean svExist = mapper.isExistServiceProduct(req.svPdCd());
        BizAssert.isTrue(svExist, "서비스상품코드를 확인해 주시길 바랍니다.");

        WctzPdRelDvo svPdRel = pdRelDvos.stream()
            .filter(wctzPdRelDvo ->
                "03".equals(wctzPdRelDvo.getPdRelTpCd()) && req.svPdCd().equals(wctzPdRelDvo.getOjPdCd()))
            .findFirst()
            .orElseThrow(
                () -> new BizException("대상 기준 상품에 해당하는 서비스 상품이 아닙니다. 서비스상품코드를 확인해 주시길 바랍니다.")
            );

        WctzMmPrtnrIzDvo partner = mapper.selectAlncmpDgPrtnr(req.alncmpDgPrtnrMapngCd(), req.alncmpDgPrtnrOgTpCd())
            .orElseThrow(() -> new BizException("파트너조회에 실패했습니다.\n관리자에게 문의해 주세요.."));

        WctzPdPrcFnlDtlDvo fnlDtlSearchParams = converter.mapValidateBulkRentalReqToFnlDtlSearchParam(req);
        fnlDtlSearchParams.setSellChnlCd(partner.getSellInflwChnlDtlCd());

        WctaRentalFinalPriceDvo price = getRentalPrice(fnlDtlSearchParams);

        if (req.sellDscCtrAmt() != null) {
            if (CtCopnDvCd.of(req.copnDvCd()) == CtCopnDvCd.INDIVIDUAL && req.sellDscCtrAmt() > 0) {
                throw new BizException("개인 고객은 법인특별할인을 받을 수 없습니다.");
            }
            Double fnlVal = price.getFnlVal();
            BizAssert.isTrue(fnlVal >= req.sellDscCtrAmt(), "법인할인금액이 월 렌탈료를 초과했습니다.");
        }

        int basePdQty = 1;

        return ValidateBulkRentalRes.builder()
            .adrId(adr.adrCd())
            .pdHclsfId(pdBas.getPdHclsfId())
            .pdMclsfId(pdBas.getPdMclsfId())
            .pdLclsfId(pdBas.getPdLclsfId())
            .pdDclsfId(pdBas.getPdDclsfId())
            .sellTpCd(pdBas.getSellTpCd())
            .sellTpDtlCd(pdBas.getSellTpDtlCd())
            .cntrTam(price.getFnlVal() * req.cntrPtrm())
            .ackmtPerfRt(pdBas.getAckmtPerfRt())
            .ackmtPerfAmt(0L) /* todo 상품 기본에 없음. */
            .feeAckmtCt(pdBas.getAckmtCt())
            .feeAckmtBaseAmt(pdBas.getFeeAmt())
            .svPrd(0) /* todo 상품 기본에 없음. */
            .pdBaseAmt(price.getBasVal())
            .cntramDscAmt(0L) /* todo 모르겠음. */
            .fnlAmt(price.getFnlVal())
            .sellAmt(price.getFnlVal() * basePdQty) /* 우선 최종금액과 같다고 함.*/
            .dscAmt(price.getBasVal() - price.getFnlVal())
            .vat(price.getVat())
            .sellInflwChnlDtlCd(partner.getSellInflwChnlDtlCd())
            .pdctPdRelId(pdctPdRel.getPdRelId())
            .pdctPdCd(pdctPdRel.getOjPdCd())
            .pdctVlStrtDtm(pdctPdRel.getVlStrtDtm())
            .pdctVlEndDtm(pdctPdRel.getVlEndDtm())
            .pdctPdQty(pdctPdRel.getItmQty())
            .svPdRelId(svPdRel.getPdRelId())
            .svVlStrtDtm(svPdRel.getVlStrtDtm())
            .svVlEndDtm(svPdRel.getVlEndDtm())
            .svPdQty(svPdRel.getItmQty())
            .pdPrcFnlDtlId(price.getPdPrcFnlDtlId())
            .verSn(price.getVerSn())
            .fxamFxrtDvCd(price.getFxamFxrtDvCd())
            .ctrVal(price.getCtrVal())
            .fnlVal(price.getFnlVal())
            .pdPrcId(price.getPdPrcId())
            .build();
    }

    @Transactional
    public int createBulkRentals(List<CreateBulkRentalReq> reqs) {
        List<WctaBulkContractDvo> wctaBulkContractDvos = reqs.stream()
            .map(converter::mapCreateBulkRentalReqToWctaBulkContractDvo)
            .toList();

        String firstCntrPrtnrRelId = mapper.selectCntrPrtnrRelIdForNewCntrPrtnrRel();
        long iHateThis1 = Long.parseLong(firstCntrPrtnrRelId);
        String firstCntrCstRelId = mapper.selectCntrCstRelIdForNewCntrCstRel();
        long iHateThis2 = Long.parseLong(firstCntrCstRelId);
        String firstCntrPdRelId = mapper.selectCntrPdRelIdForNewCntrPdRel();
        long iHateThis3 = Long.parseLong(firstCntrPdRelId);
        String firstCntrPrcCmptId = mapper.selectCntrPrcCmptIdForNewCntrPrcCmptIz();
        long iHateThis4 = Long.parseLong(firstCntrPrcCmptId);

        int count = 0;
        for (WctaBulkContractDvo wctaBulkContractDvo : wctaBulkContractDvos) {
            String cntrNo = contractNumberService.getContractNumber("").cntrNo();
            log.debug("! 채번된 cntrNo {}, {}", count++, cntrNo);
            int cntrSn = 1;
            int basePdQty = 1;
            wctaBulkContractDvo.setCntrNo(cntrNo);
            wctaBulkContractDvo.setCntrSn(cntrSn);
            wctaBulkContractDvo.setCntrNatCd("KR");
            switch (CtCopnDvCd.of(wctaBulkContractDvo.getCopnDvCd())) {
                case INDIVIDUAL -> {
                    wctaBulkContractDvo.setCntrTpCd(CtCntrTpCd.INDIVIDUAL.getCode());
                    wctaBulkContractDvo.setTxinvPblOjYn("N");
                }
                case COOPERATION -> {
                    wctaBulkContractDvo.setCntrTpCd(CtCntrTpCd.COOPERATION.getCode());
                    wctaBulkContractDvo.setTxinvPblOjYn("Y");
                }
            }
            wctaBulkContractDvo.setCntrPrgsStatCd(CtCntrPrgsStatCd.TEMP_STEP2.getCode());
            wctaBulkContractDvo.setPdQty(basePdQty);
            wctaBulkContractDvo.setCntrwTpCd(CtCntrwTpCd.RENTAL.getCode());
            wctaBulkContractDvo.setCoCd(CtCoCd.KYOWON_PROPERTY.getCode()); /* 교원프라퍼티 */
            wctaBulkContractDvo.setCntrPrtnrRelId(String.format("%015d", iHateThis1++));
            wctaBulkContractDvo.setCntrPrtnrTpCd(CtCntrPrtnrTpCd.SELLER_PERSON.getCode()); /* 파트너 */
            wctaBulkContractDvo.setCntrCstRelId(String.format("%015d", iHateThis2++));
            wctaBulkContractDvo.setCntrUnitTpCd(CtCntrUnitTpCd.CNTR_BAS.getCode()); /* 계약단위유형코드 - '010' (계약) */
            wctaBulkContractDvo.setCntrCstRelTpCd(CtCntrCstRelTpCd.CONTRACTOR.getCode()); /* 계약고객관계유형코드 = '010' (계약자)*/
            wctaBulkContractDvo.setCntrtRelCd(CtCntrtRelCd.SELF.getCode());/* 계약자관계코드 = '01' (본인) */
            wctaBulkContractDvo.setPdctCntrPdRelId(String.format("%015d", iHateThis3++));
            wctaBulkContractDvo.setSvCntrPdRelId(String.format("%015d", iHateThis3++));
            wctaBulkContractDvo.setCntrPrcCmptId(String.format("%015d", iHateThis4++));
        }

        int result = mapper.insertBulkRentals(wctaBulkContractDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");

        return 1;
    }

    public ValidateBulkSpayRes validateBulkSpay(ValidateBulkSpayReq req) {
        WctzCstBasDvo basDvo = converter.mapValidateBulkSpayReqToWctaCstBasDvo(req);

        WctzCstBasDvo customer = getCstBas(basDvo);

        SujiewonDto.FormatRes adr = getFormattedAddresses(req.adr1(), req.adr2());

        WctzPdBasDvo pdBas = getPdBas(req.basePdCd());

        List<WctzPdRelDvo> pdRelDvos = getPdRels(req.basePdCd());

        WctzPdRelDvo pdctPdRel = pdRelDvos.stream()
            .filter(wctzPdRelDvo -> "05".equals(wctzPdRelDvo.getPdRelTpCd()))
            .findFirst()
            .orElseThrow(
                () -> new BizException("헤당 상품에 해당하는 제품이 없습니다.")
            );

        WctzPdRelDvo svPdRel = null;
        if (StringUtils.hasText(req.svPdCd())) {
            boolean svExist = mapper.isExistServiceProduct(req.svPdCd());
            BizAssert.isTrue(svExist, "서비스상품코드를 확인해 주시길 바랍니다.");

            svPdRel = pdRelDvos.stream()
                .filter(wctzPdRelDvo ->
                    "03".equals(wctzPdRelDvo.getPdRelTpCd()) && req.svPdCd().equals(wctzPdRelDvo.getOjPdCd()))
                .findFirst()
                .orElseThrow(
                    () -> new BizException("대상 기준 상품에 해당하는 서비스 상품이 아닙니다. 서비스상품코드를 확인해 주시길 바랍니다.")
                );
        }



        WctzMmPrtnrIzDvo partner = mapper.selectAlncmpDgPrtnr(req.alncmpDgPrtnrMapngCd(), req.alncmpDgPrtnrOgTpCd())
            .orElseThrow(() -> new BizException("파트너조회에 실패했습니다.\n관리자에게 문의해 주세요.."));

        WctzPdPrcFnlDtlDvo fnlDtlSearchParams = converter.mapValidateBulkSpayReqToFnlDtlSearchParam(req);
        fnlDtlSearchParams.setSellChnlCd(partner.getSellInflwChnlDtlCd());

        WctaSpayFinalPriceDvo price = getSpayPrice(fnlDtlSearchParams);

        if (req.sellDscCtrAmt() != null) {
            if (CtCopnDvCd.of(customer.getCopnDvCd()) == CtCopnDvCd.INDIVIDUAL && req.sellDscCtrAmt() > 0) {
                throw new BizException("개인 고객은 법인특별할인을 받을 수 없습니다.");
            }
            Double fnlVal = price.getFnlVal();
            BizAssert.isTrue(fnlVal >= req.sellDscCtrAmt(), "법인할인금액이 월 렌탈료를 초과했습니다.");
        }

        int basePdQty = 1;

        return ValidateBulkSpayRes.builder()
            .adrId(adr.adrCd())
            .pdHclsfId(pdBas.getPdHclsfId())
            .pdMclsfId(pdBas.getPdMclsfId())
            .pdLclsfId(pdBas.getPdLclsfId())
            .pdDclsfId(pdBas.getPdDclsfId())
            .sellTpCd(pdBas.getSellTpCd())
            .sellTpDtlCd(pdBas.getSellTpDtlCd())
            .cntrTam(price.getFnlVal())
            .ackmtPerfRt(pdBas.getAckmtPerfRt())
            .ackmtPerfAmt(0L) /* todo 상품 기본에 없음. */
            .feeAckmtCt(pdBas.getAckmtCt())
            .feeAckmtBaseAmt(pdBas.getFeeAmt())
            .svPrd(0) /* todo 상품 기본에 없음. */
            .pdBaseAmt(price.getBasVal())
            .cntramDscAmt(0L) /* todo 모르겠음. */
            .fnlAmt(price.getFnlVal())
            .sellAmt(price.getFnlVal() * basePdQty) /* 우선 최종금액과 같다고 함.*/
            .dscAmt(price.getBasVal() - price.getFnlVal())
            .vat(price.getVat())
            .sellInflwChnlDtlCd(partner.getSellInflwChnlDtlCd())
            .pdctPdRelId(pdctPdRel.getPdRelId())
            .pdctPdCd(pdctPdRel.getOjPdCd())
            .pdctVlStrtDtm(pdctPdRel.getVlStrtDtm())
            .pdctVlEndDtm(pdctPdRel.getVlEndDtm())
            .pdctPdQty(pdctPdRel.getItmQty())
            .svPdRelId(ObjectUtils.isEmpty(svPdRel) ? null : svPdRel.getPdRelId())
            .svVlStrtDtm(ObjectUtils.isEmpty(svPdRel) ? null : svPdRel.getVlStrtDtm())
            .svVlEndDtm(ObjectUtils.isEmpty(svPdRel) ? null : svPdRel.getVlEndDtm())
            .svPdQty(ObjectUtils.isEmpty(svPdRel) ? null : svPdRel.getItmQty())
            .pdPrcFnlDtlId(price.getPdPrcFnlDtlId())
            .verSn(price.getVerSn())
            .fxamFxrtDvCd(price.getFxamFxrtDvCd())
            .ctrVal(price.getCtrVal())
            .fnlVal(price.getFnlVal())
            .pdPrcId(price.getPdPrcId())
            .build();
    }

    @Transactional
    public int createBulkSpays(List<CreateBulkSpayReq> reqs) {
        List<WctaBulkContractDvo> wctaBulkContractDvos = reqs.stream()
            .map(converter::mapCreateBulkSpayReqToWctaBulkContractDvo)
            .toList();

        String firstCntrPrtnrRelId = mapper.selectCntrPrtnrRelIdForNewCntrPrtnrRel();
        long iHateThis1 = Long.parseLong(firstCntrPrtnrRelId);
        String firstCntrCstRelId = mapper.selectCntrCstRelIdForNewCntrCstRel();
        long iHateThis2 = Long.parseLong(firstCntrCstRelId);
        String firstCntrPdRelId = mapper.selectCntrPdRelIdForNewCntrPdRel();
        long iHateThis3 = Long.parseLong(firstCntrPdRelId);
        String firstCntrPrcCmptId = mapper.selectCntrPrcCmptIdForNewCntrPrcCmptIz();
        long iHateThis4 = Long.parseLong(firstCntrPrcCmptId);


        int result = 0;
        for (WctaBulkContractDvo wctaBulkContractDvo : wctaBulkContractDvos) {
            String cntrNo = contractNumberService.getContractNumber("").cntrNo();
            int cntrSn = 1;
            int basePdQty = 1;
            wctaBulkContractDvo.setCntrNo(cntrNo);
            wctaBulkContractDvo.setCntrSn(cntrSn);
            wctaBulkContractDvo.setCntrNatCd("KR");
            switch (CtCopnDvCd.of(wctaBulkContractDvo.getCopnDvCd())) {
                case INDIVIDUAL -> {
                    wctaBulkContractDvo.setCntrTpCd(CtCntrTpCd.INDIVIDUAL.getCode());
                    wctaBulkContractDvo.setTxinvPblOjYn("N");
                }
                case COOPERATION -> {
                    wctaBulkContractDvo.setCntrTpCd(CtCntrTpCd.COOPERATION.getCode());
                    wctaBulkContractDvo.setTxinvPblOjYn("Y");
                }
            }
            wctaBulkContractDvo.setCntrPrgsStatCd(CtCntrPrgsStatCd.TEMP_STEP2.getCode());
            wctaBulkContractDvo.setPdQty(basePdQty);
            wctaBulkContractDvo.setCntrwTpCd(CtCntrwTpCd.RENTAL.getCode());
            wctaBulkContractDvo.setCoCd(CtCoCd.KYOWON_PROPERTY.getCode()); /* 교원프라퍼티 */
            wctaBulkContractDvo.setCntrPrtnrRelId(String.format("%015d", iHateThis1++));
            wctaBulkContractDvo.setCntrPrtnrTpCd(CtCntrPrtnrTpCd.SELLER_PERSON.getCode()); /* 파트너 */
            wctaBulkContractDvo.setCntrCstRelId(String.format("%015d", iHateThis2++));
            wctaBulkContractDvo.setCntrUnitTpCd(CtCntrUnitTpCd.CNTR_BAS.getCode()); /* 계약단위유형코드 - '010' (계약) */
            wctaBulkContractDvo.setCntrCstRelTpCd(CtCntrCstRelTpCd.CONTRACTOR.getCode()); /* 계약고객관계유형코드 = '010' (계약자)*/
            wctaBulkContractDvo.setCntrtRelCd(CtCntrtRelCd.SELF.getCode());/* 계약자관계코드 = '01' (본인) */
            wctaBulkContractDvo.setPdctCntrPdRelId(String.format("%015d", iHateThis3++));
            if (StringUtils.hasText(wctaBulkContractDvo.getSvPdRelId())) {
                wctaBulkContractDvo.setSvCntrPdRelId(String.format("%015d", iHateThis3++));
            }
            wctaBulkContractDvo.setCntrPrcCmptId(String.format("%015d", iHateThis4++));

            // result +=  mapper.insertBulkSpay(wctaBulkContractDvo);
        }

        result = mapper.insertBulkSpays(wctaBulkContractDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");

        return 1;
    }

    public ValidateIstlcRes validateInstallLocation(ValidateIstlcReq req) {
        String cntrNo = req.cntrNo();
        int cntrSn = req.cntrSn();

        WctaIstlcValidationDvo validationDvo = mapper.selectIstlcValidation(cntrNo, cntrSn).orElseThrow(() -> new BizException("계약번호, 계약일련번호를 다시 확인해 주세요."));

        if (StringUtils.hasText(validationDvo.getCntrPdStrtdt())) {
            throw new BizException("이미 설치가 완료된 계약입니다.");
        }

        SujiewonDto.FormatRes adr = getFormattedAddresses(req.adr1(), req.adr2());

        return ValidateIstlcRes.builder()
            .adrId(adr.adrCd())
            .cntrCstNo(validationDvo.getCstNo())
            .copnDvCd(validationDvo.getCopnDvCd())
            .origCntrAdrRelId(validationDvo.getCntrAdrRelId())
            .adrpcTpCd(validationDvo.getAdrpcTpCd())
            .cntrUnitTpCd(validationDvo.getCntrUnitTpCd())
            .build();
    }

    @Transactional
    public int createBulkInstallLocations(List<CreateBulkIstlcReq> reqs) {
        List<WctzCntrAdprcBasDvo> wctzCntrAdprcBasDvos = reqs.stream()
            .map(converter::mapCreateBulkIstlcReqToWctzCntrAdprcBasDvo)
            .toList();

        List<WctzCntrAdrRelDvo> wctzCntrAdrRelDvos = reqs.stream()
            .map(converter::mapCreateBulkIstlcReqToWctzCntrAdrRelDvo)
            .toList();

        List<String> origCntrAdrRelIds = reqs.stream()
            .map(CreateBulkIstlcReq::origCntrAdrRelId)
            .toList();

        String firstCntrAdrpcId = mapper.selectNewCntrAdrpcId();
        long cntrAdrpcIdSeq = Long.parseLong(firstCntrAdrpcId);
        String firstCntrAdrRelId = mapper.selectNewCntrAdrRelId();
        long cntrAdrRelIdSeq = Long.parseLong(firstCntrAdrRelId);

        String now = DateUtil.todayNnow();

        for (WctzCntrAdprcBasDvo wctzCntrAdprcBasDvo : wctzCntrAdprcBasDvos) {
            String cntrAdrpcId = String.format("%015d", cntrAdrpcIdSeq++);
            wctzCntrAdprcBasDvo.setCntrAdrpcId(cntrAdrpcId);
        }

        for (int i = 0; i < wctzCntrAdprcBasDvos.size(); i++) {
            String cntrAdrpcId = String.format("%015d", cntrAdrpcIdSeq++);
            WctzCntrAdprcBasDvo wctzCntrAdprcBasDvo = wctzCntrAdprcBasDvos.get(i);
            wctzCntrAdprcBasDvo.setCntrAdrpcId(cntrAdrpcId);

            String cntrAdrRelId = String.format("%015d", cntrAdrRelIdSeq++);
            WctzCntrAdrRelDvo wctzCntrAdrRelDvo = wctzCntrAdrRelDvos.get(i);
            wctzCntrAdrRelDvo.setCntrAdrRelId(cntrAdrRelId);
            wctzCntrAdrRelDvo.setCntrAdrpcId(cntrAdrpcId);
            wctzCntrAdrRelDvo.setAdrpcTpCd("2");
            wctzCntrAdrRelDvo.setCntrUnitTpCd("020");
            wctzCntrAdrRelDvo.setVlStrtDtm(now);
            wctzCntrAdrRelDvo.setVlEndDtm(CtContractConst.END_DTM);
        }

        int result = mapper.insertBulkAdprcBases(wctzCntrAdprcBasDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");
        result = mapper.updateBulkExpireCntrAdrRels(origCntrAdrRelIds, now);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");
        result = mapper.insertBulkCntrAdrRels(wctzCntrAdrRelDvos);
        BizAssert.isTrue(result >= 1, "저장에 실패했습니다.");

        return 1;
    }

    // default
    WctzPdBasDvo getPdBas(String pdCd) {
        return mapper.selectPdBasByPk(pdCd).orElseThrow(() -> new BizException("상품코드를 확인해 주시길 바랍니다."));
    }

    WctzCstBasDvo getCstBas(WctzCstBasDvo dvo) {
        if (CtCopnDvCd.of(dvo.getCopnDvCd()) == CtCopnDvCd.COOPERATION) {
            dvo.setCopnDvCd(null); /* 사업자 번호가 엑셀에 없다. */
        }
        return mapper.selectCstBasWithInfos(dvo)
            .orElseThrow(
                () -> new BizException("고객 정보를 조회할 수 없습니다. 고객 정보를 다시 확인해주세요.")
            );
    }

    WctaRentalFinalPriceDvo getRentalPrice(WctzPdPrcFnlDtlDvo fnlDtlSearchParams) {
        List<WctaRentalFinalPriceDvo> queried = mapper.selectRentalPdPrcFnlDtl(fnlDtlSearchParams);
        BizAssert.isFalse(queried.isEmpty(), "상품가격조회에 실패했습니다.\n입력된 상품 및 서비스 정보를 확인하세요.");
        BizAssert.isFalse(queried.size() > 1, "상품가격이 모호합니다.\n입력된 상품 및 서비스 정보를 확인하세요.");
        return queried.get(0);
    }

    List<WctzPdRelDvo> getPdRels(String basePdCd) {
        return mapper.selectPdRels(basePdCd);
    }

    WctaSpayFinalPriceDvo getSpayPrice(WctzPdPrcFnlDtlDvo fnlDtlSearchParams) {
        List<WctaSpayFinalPriceDvo> queried = mapper.selectSpayPdPrcFnlDtl(fnlDtlSearchParams);
        BizAssert.isFalse(queried.isEmpty(), "상품가격조회에 실패했습니다.\n입력된 상품 및 서비스 정보를 확인하세요.");
        BizAssert.isFalse(queried.size() > 1, "상품가격이 모호합니다.\n입력된 상품 및 서비스 정보를 확인하세요.");
        return queried.get(0);
    }

    /**
     * 상세 주소 기준으로 수지원넷에 요청하고 정제된 주소 정보를 조회 한다. 화면에서 조용히 실행하기 위해 biz exception 으로 감싸 rethrow 한다.
     *
     * @param adr1 주소1
     * @param adr2 주소2
     * @return 주소객체
     */
    SujiewonDto.FormatRes getFormattedAddresses(String adr1, String adr2) {
        try {
            /* 상세 주소 기준으로 수지원넷에 요청하고 정제된 주소 정보를 조회 한다. 화면에서 조용히 실행하기 위해 biz exception 으로 감싸 rethrow 한다. */
            String searchWord = (adr1 + (" " + adr2)).trim();
            SujiewonDto.FormatRes formatRes = sujiewonService.getFormattedAddressesForRestApi(searchWord);

            if (formatRes.adrCd().startsWith("IF_ERR")) {
                throw new BizException("주소를 확인해 주시기 바랍니다.");
            }

            return formatRes;
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    @Deprecated
    @Transactional
    void createProspectCst(CreateProspectCstReq req) {
        WctzPspcCstBasDvo pspcCstBasDvo = converter.mapCreateProspectCstReqToWctaPspcCstBasDvo(req);
        String pspcCstId = createPspcCst(pspcCstBasDvo);
        WctzPspcCstCnslBasDvo pspcCstCnslBasDvo = converter.mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(req);
        pspcCstCnslBasDvo.setPspcCstId(pspcCstId);
        String pspcCstCnslId = createPspcCstCnsl(pspcCstCnslBasDvo);
        WctzPspcCstCnslRcmdIzDvo pspcCstCnslRcmdIzDvo = new WctzPspcCstCnslRcmdIzDvo(pspcCstCnslId, 1); /* 생성이니 일련번호는 1이 아닐까?*/
        pspcCstCnslRcmdIzDvo.setPdCd(req.basePdCd());
        createPspcCstCnslRcmdIz(pspcCstCnslRcmdIzDvo);
    }

    @Deprecated
    @Transactional
    void createPspcCst(WctzPspcCstBasDvo dvo, String pspcCstId) {
        dvo.setPspcCstId(pspcCstId);
        int result = mapper.insertPspcCstBas(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstChHistory(pspcCstId);
    }

    @Deprecated
    @Transactional
    String createPspcCst(WctzPspcCstBasDvo dvo) {
        String pspcCstId = mapper.selectPspcCstIdForNewPspcCstBas();
        createPspcCst(dvo, pspcCstId);
        return pspcCstId;
    }

    @Deprecated
    @Transactional
    void createPspcCstCnsl(WctzPspcCstCnslBasDvo dvo, String pspcCstCnslId) {
        dvo.setPspcCstCnslId(pspcCstCnslId);
        int result = mapper.insertPspcCstCnslBas(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstCnslChHistory(pspcCstCnslId);
    }

    @Deprecated
    @Transactional
    String createPspcCstCnsl(WctzPspcCstCnslBasDvo dvo) {
        String pspcCstCnslId = mapper.selectPspcCstCnslIdForNewPspcCstCnslBas();
        createPspcCstCnsl(dvo, pspcCstCnslId);
        return pspcCstCnslId;
    }

    @Deprecated
    @Transactional
    void createPspcCstCnslRcmdIz(WctzPspcCstCnslRcmdIzDvo dvo) {
        int result = mapper.insertPspcCstCnslRcmdIz(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstCnslRchHistory(dvo.getPspcCstCnslId(), dvo.getPspcCstCnslSn());
    }
}
