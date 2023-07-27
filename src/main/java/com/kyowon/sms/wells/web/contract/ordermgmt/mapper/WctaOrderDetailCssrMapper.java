package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SearchRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCssrDvo;

@Mapper
public interface WctaOrderDetailCssrMapper {

    FindBaseRcpRes selectContractBaseInformation(FindBaseRcpReq dto);

    int selectContractBaseCheck(WctaOrderDetailCssrDvo dvo);

    List<SearchRcpRes> selectCashSalesReceipts(String cntrNo, String cntrSn);

    String selectCustomerNumber(String cntrNo);

    int insertCashSalesReceipt(WctaOrderDetailCssrDvo dvo);

    int insertCashSalesReceiptApprovalPresentState(WctaOrderDetailCssrDvo dvo);

    int insertCashSalesReceiptChangeHistory(WctaOrderDetailCssrDvo dvo);

    int updateCashSalesReceiptApprovalPresentState(WctaOrderDetailCssrDvo dvo);

    int updateCashSalesReceiptRegistration(WctaOrderDetailCssrDvo dvo);
}
