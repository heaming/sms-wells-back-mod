package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaBulkContractDvo;

@Mapper(componentModel = "spring")
public interface WctaReceiptBulkUploadConverter {
    @Mapping(constant = "10", target = "mgntTpCd") /*가망고객유형코드 - '10' 홈쇼핑*/
    @Mapping(constant = "KR", target = "natCd") /*국가코드 - 'KR'*/
    @Mapping(constant = "N", target = "dtaDlYn")
    @Mapping(source = "alncmpDgPrtnrMapngCd", target = "ichrPrtnrNo") /*담당파트너번호 = 대표파트너번호*/
    @Mapping(source = "alncmpDgPrtnrOgTpCd", target = "ichrOgTpCd") /*담당조직유형코드 = 조직유형코드*/
    WctzPspcCstBasDvo mapCreateProspectCstReqToWctaPspcCstBasDvo(CreateProspectCstReq req);

    @Mapping(constant = "001", target = "pspcCstCnslTpCd") /*가망고객상담유형코드 - '001' 신규문의*/
    @Mapping(constant = "60", target = "sellInflwChnlDvCd") /*판매유입채널구분코드 - '60' 홈쇼핑*/
    @Mapping(constant = "N", target = "dtaDlYn")
    @Mapping(source = "basePdCd", target = "inrtPdCd") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdHclsfId", target = "inrtPdHclsfId") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdMclsfId", target = "inrtPdMclsfId") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdLclsfId", target = "inrtPdLclsfId") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdDclsfId", target = "inrtPdDclsfId") /*담당조직유형코드 = 조직유형코드*/
    WctzPspcCstCnslBasDvo mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(CreateProspectCstReq req);

    WctzCstBasDvo mapValidateBulkRentalReqToWctaCstBasDvo(ValidateBulkRentalReq req);

    @Mapping(source = "basePdCd", target = "pdCd")
    @Mapping(source = "cntrPtrm", target = "pdPrcCndtChrcVal07")
    @Mapping(source = "cntrAmt", target = "pdPrcCndtChrcVal01")
    @Mapping(source = "stplPtrm", target = "stplPrdCd")
    @Mapping(source = "rentalDscDvCd", target = "pdPrcCndtChrcVal02")
    @Mapping(source = "rentalCrpDscrCd", target = "pdPrcCndtChrcVal11")
    @Mapping(source = "rentalDscTpCd", target = "pdPrcCndtChrcVal03")
    @Mapping(constant = "2", target = "sellTpCd") /* 렌탈 */
    WctzPdPrcFnlDtlDvo mapValidateBulkRentalReqToFnlDtlSearchParam(ValidateBulkRentalReq req);

    @Mapping(constant = "KR", target = "cntrNatCd")
    @Mapping(source = "alncmpDgPrtnrMapngCd", target = "sellPrtnrNo")
    @Mapping(source = "alncmpDgPrtnrOgTpCd", target = "sellOgTpCd")
    @Mapping(source = "rentalDscTpCd", target = "sellDscTpCd")
    @Mapping(source = "rentalDscDvCd", target = "sellDscDvCd")
    @Mapping(source = "rentalCrpDscrCd", target = "sellDscrCd")
    @Mapping(source = "alncmpSuscOrdNo", target = "otsdLkDrmVal")
    @Mapping(defaultValue = "0L", target = "sellDscCtrAmt")
    WctaBulkContractDvo mapCreateBulkRentalReqToWctaBulkContractDvo(CreateBulkRentalReq createBulkRentalReq);

    @Mapping(source = "basePdCd", target = "pdCd")
    @Mapping(source = "frisuBfsvcPtrmN", target = "pdPrcFnlPrpVal10")
    @Mapping(source = "spayDscDvCd", target = "pdPrcCndtChrcVal06")
    @Mapping(source = "spayDscrCd", target = "pdPrcCndtChrcVal13")
    @Mapping(source = "hcrDvCd", target = "pdPrcCndtChrcVal05")
    @Mapping(constant = "1", target = "sellTpCd") /* 일시불 */
    WctzPdPrcFnlDtlDvo mapValidateBulkSpayReqToFnlDtlSearchParam(ValidateBulkSpayReq req);

    WctzCstBasDvo mapValidateBulkSpayReqToWctaCstBasDvo(ValidateBulkSpayReq req);

    @Mapping(constant = "KR", target = "cntrNatCd")
    @Mapping(source = "alncmpDgPrtnrMapngCd", target = "sellPrtnrNo")
    @Mapping(source = "alncmpDgPrtnrOgTpCd", target = "sellOgTpCd")
    @Mapping(source = "spayDscDvCd", target = "sellDscDvCd")
    @Mapping(defaultValue = "0L", target = "sellDscCtrAmt")
    /*@Mapping(source = "spayDscrCd", target = "sellDscrCd") 아마도?*/
    WctaBulkContractDvo mapCreateBulkSpayReqToWctaBulkContractDvo(CreateBulkSpayReq createBulkSpayReq);

    @Mapping(constant = "KR", target = "natCd")
    @Mapping(constant = "1", target = "adrDvCd")
    WctzCntrAdprcBasDvo mapCreateBulkIstlcReqToWctzCntrAdprcBasDvo(CreateBulkIstlcReq createBulkIstlcReq);

    @Mapping(source = "origCntrAdrRelId", target = "cntrAdrRelId")
    @Mapping(source = "cntrNo", target = "dtlCntrNo")
    @Mapping(source = "cntrSn", target = "dtlCntrSn")
    WctzCntrAdrRelDvo mapCreateBulkIstlcReqToWctzCntrAdrRelDvo(CreateBulkIstlcReq createBulkIstlcReq);
}
