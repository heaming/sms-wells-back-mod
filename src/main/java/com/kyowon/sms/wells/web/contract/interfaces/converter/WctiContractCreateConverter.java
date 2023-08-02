package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentReq;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;

@Mapper(componentModel = "spring")
public interface WctiContractCreateConverter {

    @Mapping(source = "rcpChnlDtl", target = "sellInflwChnlDtlCd")
    @Mapping(source = "cstKnm", target = "cntrCstKnm")
    @Mapping(source = "cstEmadr", target = "cntrtEmadr")
    @Mapping(source = "istPlcLocaraTno", target = "istLocaraTno")
    @Mapping(source = "istPlcExno", target = "istExno")
    @Mapping(source = "istPlcIdvTno", target = "istIdvTno")
    @Mapping(source = "istCstCralLocaraTno", target = "istCralLocaraTno")
    @Mapping(source = "istCstMexno", target = "istMexno")
    @Mapping(source = "istCstCralIdvTno", target = "istCralIdvTno")
    @Mapping(source = "pdCd01", target = "basePdCd")
    @Mapping(source = "pdQty01", target = "pdQty")
    @Mapping(source = "amt01", target = "fnlAmt")
    @Mapping(source = "sellTam", target = "cntrTam")
    @Mapping(source = "cntrDt", target = "cntrCnfmDt")
    @Mapping(source = "ag1", target = "pifClcnUAgYn")
    @Mapping(source = "ag2", target = "pifThpOfrAgYn")
    @Mapping(source = "ag3", target = "mktgPurpAgYn")
    @Mapping(source = "ag4", target = "pifCinfThpOfrAgYn")
    @Mapping(source = "ag5", target = "pifBizFstrAgYn")
    @Mapping(source = "dpDvCd1", target = "dpDvCd")
    @Mapping(source = "subscAmt1", target = "stlmAmt")
    @Mapping(source = "cdno1", target = "crcdNo")
    @Mapping(source = "crdcdIstmMcn1", target = "istmMcn")
    @Mapping(source = "cdonrNm1", target = "crcdOwrKnm")
    @Mapping(source = "cino", target = "cntrtBryyMmdd")
    @Mapping(source = "evCd", target = "sellEvCd")
    @Mapping(source = "dcde", target = "sellPrtnrNo")
    @Mapping(source = "achldrNm", target = "owrKnm")
    @Mapping(source = "mngtPrd", target = "svPrd")
    @Mapping(source = "duedt", target = "sppDuedt")
    @Mapping(source = "frisuPrd", target = "frisuBfsvcPtrmN")
    @Mapping(source = "cstAkArtc", target = "istAkArtcMoCn")
    @Mapping(source = "dscDv", target = "sellDscDvCd")
    @Mapping(source = "dscTp", target = "sellDscTpCd")
    @Mapping(source = "uswy", target = "svPdTpCd")
    WctiContractCreateDvo mapCreateSinglePaymentReqToWctiContractCreateDvo(CreateSinglePaymentReq dto);

    /* Default 값 처리 항목
     * sellAmt
     * dscAmt
     * cntrAmt
     * cntramDscAmt
     * cntrTam
     * rentalAmt2
     * rentalDscAmt2
     * svAmt
     */

    @Mapping(source = "rcpdt", target = "cntrRcpDt")
    @Mapping(source = "rcptm", target = "cntrRcpTm")
    @Mapping(source = "cstNm", target = "cntrCstKnm")
    @Mapping(source = "cphonLocaraTno", target = "cntrtCralLocaraTno")
    @Mapping(source = "cphonExno", target = "cntrtMexno")
    @Mapping(source = "cphonIdvTno", target = "cntrtCralIdvTno")
    @Mapping(source = "locaraTno", target = "cntrtLocaraTno")
    @Mapping(source = "exno", target = "cntrtExno")
    @Mapping(source = "idvTno", target = "cntrtIdvTno")
    @Mapping(source = "adrDvCd", target = "cntrtAdrDvCd")
    @Mapping(source = "zip", target = "cntrtZip")
    @Mapping(source = "basAdr", target = "cntrtBasAdr")
    @Mapping(source = "dtlAdr", target = "cntrtDtlAdr")
    @Mapping(source = "cstNo", target = "cntrCstNo")
    @Mapping(source = "cstEmadr", target = "cntrtEmadr")
    @Mapping(source = "istCphonLocaraTno", target = "istCralLocaraTno")
    @Mapping(source = "istCphonExno", target = "istMexno")
    @Mapping(source = "istCphonIdvTno", target = "istCralIdvTno")
    @Mapping(source = "istCstNm", target = "istllKnm")
    @Mapping(source = "urgtIst", target = "urgtOjYn")
    @Mapping(source = "pdCd", target = "basePdCd")
    @Mapping(source = "pdQty", target = "pdQty", defaultValue = "1")
    @Mapping(source = "bnkCdcoDv1", target = "cdcoCd")
    @Mapping(source = "bnkCdcoDv1", target = "bnkCd")
    @Mapping(source = "cardAmt1", target = "cardAmt")
    @Mapping(source = "crcdno1", target = "crcdNo")
    @Mapping(source = "cardIstmMcn1", target = "istmMcn")
    @Mapping(source = "cdonrNm1", target = "crcdOwrKnm")
    @Mapping(source = "dscDvCd", target = "sellDscDvCd")
    @Mapping(source = "dscTpCd", target = "sellDscTpCd")
    @Mapping(source = "uswyCd", target = "svPdTpCd")
    @Mapping(source = "vstPrdCd", target = "svPrd")
    @Mapping(source = "prtnrNo", target = "sellPrtnrNo")
    @Mapping(source = "rsdtBzrNo", target = "bzrNo")
    @Mapping(source = "mrktUtlzAgYn", target = "mktgPurpAgYn")
    @Mapping(source = "frisuAsPtrm", target = "frisuAsPtrmN")
    @Mapping(source = "chdvcPfr", target = "mchnCpsApyr")
    @Mapping(source = "mmApyDvCd", target = "mchnChCntrMmBaseDvCd")
    @Mapping(source = "clnDv", target = "mchnClnOjYn")
    @Mapping(source = "bfCntrNo1", target = "mchnChCntrNo")
    @Mapping(source = "bfCntrSn1", target = "mchnChCntrSn")
    @Mapping(source = "cntrtRel", target = "cntrtRelCd", defaultValue = "10")
    @Mapping(source = "cstClsCd", target = "copnDvCd")
    @Mapping(source = "rcpChnlDtl", target = "sellInflwChnlDtlCd")
    @Mapping(source = "cttCd", target = "cttRsCd")
    @Mapping(source = "svChram", target = "svAmt", defaultValue = "0")
    @Mapping(source = "rtlfe1", target = "fnlAmt")
    @Mapping(source = "rentalDscAmt1", target = "dscAmt", defaultValue = "0")
    @Mapping(source = "feeBaseAmt", target = "feeAckmtBaseAmt")
    @Mapping(source = "istRqdt", target = "sppDuedt")
    @Mapping(source = "wprsEyn", target = "wprsItstTpCd")
    @Mapping(source = "istMmExmpYn", target = "istMmBilMthdTpCd")
    @Mapping(source = "txinvPblOjYn", target = "txinvPblOjYn")
    @Mapping(source = "txinvBzrno", target = "txinvBzrno")
    @Mapping(source = "txinvPsicNm", target = "txinvDlpnrPsicNm")
    @Mapping(source = "txinvCphonLocaraTno", target = "txinvCralLocaraTno")
    @Mapping(source = "txinvCphonExno", target = "txinvMexno")
    @Mapping(source = "txinvCphonIdvTno", target = "txinvCralIdvTno")
    @Mapping(source = "rgstCost", target = "cntrAmt", defaultValue = "0")
    @Mapping(source = "rgstCostDsc", target = "cntramDscAmt", defaultValue = "0")
    WctiContractCreateDvo mapCreateRentalReqToWctiContractCreateDvo(CreateRentalReq dto);

}
