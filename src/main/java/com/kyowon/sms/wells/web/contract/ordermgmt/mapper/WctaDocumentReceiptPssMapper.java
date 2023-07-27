package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SearchDocumentRcpDtlFileInfoRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDocumentRcpDtlInqrsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDocumentReceiptPssDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDocumentReceiptPssRequestDvo;

@Mapper
public interface WctaDocumentReceiptPssMapper {

    List<WctaDocumentReceiptPssDvo> selectDocumentReceipts(WctaDocumentReceiptPssRequestDvo dvo);

    int updateDocumentRcpCnfm(SaveReq dto);

    List<WctaDocumentRcpDtlInqrsDvo> selectDocumentRcpDtlInqrs(String cntrChRcpId);

    List<SearchDocumentRcpDtlFileInfoRes> selectDocumentRcpDtlFileList(String cntrChRcpId);
}
