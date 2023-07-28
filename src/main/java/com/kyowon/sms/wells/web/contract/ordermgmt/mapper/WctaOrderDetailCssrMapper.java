package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCssrDvo;

@Mapper
public interface WctaOrderDetailCssrMapper {

    WctaOrderDetailCssrDvo selectContractBaseInformation(FindBaseRcpReq dto);

    int selectContractBaseCheck(WctaOrderDetailCssrDvo dvo);

    List<WctaOrderDetailCssrDvo> selectCashSalesReceipts(String cntrNo, String cntrSn);

    String selectCustomerNumber(String cntrNo);

    int insertCashSalesReceipt(WctaOrderDetailCssrDvo dvo);

    int insertCashSalesReceiptApprovalPresentState(WctaOrderDetailCssrDvo dvo);

    int insertCashSalesReceiptChangeHistory(WctaOrderDetailCssrDvo dvo);

    int updateCashSalesReceiptApprovalPresentState(WctaOrderDetailCssrDvo dvo);

    int updateCashSalesReceiptRegistration(WctaOrderDetailCssrDvo dvo);
}
