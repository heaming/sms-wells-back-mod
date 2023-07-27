package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SearchDocumentRcpDtlInqrsRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaDocumentReceiptPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDocumentRcpDtlInqrsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDocumentReceiptPssDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDocumentReceiptPssRequestDvo;

@Mapper(componentModel = "spring")
public interface WctaDocumentReceiptPssConverter {
    WctaDocumentReceiptPssRequestDvo mapSearchReqToWwctaDocumentReceiptPssDvo(SearchReq dto);

    List<SearchRes> mapWwctaDocumentReceiptPssDvoToSearchRes(List<WctaDocumentReceiptPssDvo> dvos);

    List<SearchDocumentRcpDtlInqrsRes> mapWctaDocumentRcpDtlInqrsDvoToSearchDocumentRcpDtlInqrsRes(
        List<WctaDocumentRcpDtlInqrsDvo> dvos
    );
}
