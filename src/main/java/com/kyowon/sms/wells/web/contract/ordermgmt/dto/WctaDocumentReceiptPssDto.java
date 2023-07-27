package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WctaDocumentReceiptPssDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Search Request Dto
    @ApiModel(value = "WctaDocumentReceiptPssDto-SearchReq")
    public record SearchReq(
        String cntrChRcpStrtDtm,
        String cntrChRcpFinsDtm,
        String cntrChPrgsStatCd,
        String cntrChTpCd,
        String cntrChRcpId,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {}

    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Save Request Dto
    @ApiModel(value = "WctaDocumentReceiptPssDto-SaveReq")
    public record SaveReq(
        String cntrChRcpId,
        String cntrChPrgsStatCd,
        String cntrChAkCn,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    //wells 서류접수현황(추가) - wells 서류접수현황(추가) 조회 Search Result Dto
    @ApiModel("WctaDocumentReceiptPssDto-SearchRes")
    public record SearchRes(
        String cntrChRcpId,
        String reCntrChRcpId,
        String cntrChRcpD,
        String cntrChRcpTm,
        String cntrChPrgsStatCd,
        String cntrChPrgsStatNm,
        String cntrChPrgsStatCdEnd,
        String cntrChPrgsStatNmEnd,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrChTpCd,
        String cntrChTpNm,
        String fnlMdfcDtm
    ) {}

    //wells 서류접수현황(추가) - wells 서류접수상세 조회 Search Result Dto
    @ApiModel("WctaDocumentReceiptPssDto-SearchDocumentRcpDtlInqrsRes")
    public record SearchDocumentRcpDtlInqrsRes(
        String cntrChRcpId,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String bryyMmdd,
        String cntrChPrgsMoCn,
        String cntrChTpCd,
        String cntrChTpNm,
        String dtlCntrNo,
        String dtlCntrSn,
        String cntrDtlNo,
        String sellTpCd,
        String sellTpNm,
        String cntrChRcpD,
        String cntrChRcpTm,
        String cntrChPrgsStatCd,
        String cntrChPrgsStatNm,
        String fnlMdfcDtm,
        String cntrChAkCn,
        String cntrChRsonCd,
        String cntrChRsonNm
    ) {}

    //wells 서류접수현황(추가) - wells 서류접수상세 문서파일리스트 Search Result Dto
    @ApiModel("WctaDocumentReceiptPssDto-SearchDocumentRcpDtlFileInfoRes")
    public record SearchDocumentRcpDtlFileInfoRes(
        String cntrChDocDvNm,
        String cntrChDocSeq,
        String cntrChTpCd,
        String dcmtRcpSn,
        String fileNm,
        String realFpath,
        String fnlMdfcDtm,
        String fileUid
    ) {}

    @ApiModel("WctaDocumentReceiptPssDto-SearchDocumentRcpDtlRes")
    public record SearchDocumentRcpDtlRes(
        List<SearchDocumentRcpDtlInqrsRes> searchDocumentRcpDtlInqrsResList,
        List<SearchDocumentRcpDtlFileInfoRes> searchDocumentRcpDtlFileInfoResList
    ) {}
}
