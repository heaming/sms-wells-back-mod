package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaTaxInvoiceInquiryDvo;

@Mapper
public interface WctaTaxInvoiceInquiryMapper {

    WctaTaxInvoiceInquiryDvo selectTaxInvoiceInquiry(String cntrNo, int cntrSn);

    WctaTaxInvoiceInquiryDvo selectTaxInvoiceInquiryCheck(String cntrNo, int cntrSn);

    WctaTaxInvoiceInquiryDvo selectDateTime();

    int updateTaxInvoiceInquiry(WctaTaxInvoiceInquiryDvo dvo);

    int updateCntrDetailTxinvPblOj(String txinvPblOjYn, String cntrNo, int cntrSn);

    int insertTaxInvoiceReceiptBaseHist(WctaTaxInvoiceInquiryDvo dvo);

    int insertSellPartnerToCntrChRcchHist(WctaTaxInvoiceInquiryDvo dvo);

    int insertContractChangeReceipt(@Param("item")
    WctaTaxInvoiceInquiryDvo dvo);

    int insertContractChReceiptDetail(@Param("item")
    WctaTaxInvoiceInquiryDvo dvo);
}
