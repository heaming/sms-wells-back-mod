package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailCssrConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCssrDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailCssrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailCssrService {

    private final WctaOrderDetailCssrMapper mapper;
    private final WctaOrderDetailCssrConverter converter;

    public List<SearchRcpRes> getCashSalesReceipts(String cntrNo, String cntrSn) {
        return mapper.selectCashSalesReceipts(cntrNo, cntrSn);
    }

    public FindBaseRcpRes getContractBaseInformation(FindBaseRcpReq dto) {
        return mapper.selectContractBaseInformation(dto);
    }

    @Transactional
    public int saveCashSalesReceipt(SaveRcpReq dto) {
        WctaOrderDetailCssrDvo dvo = converter.mapSaveRcpReqToWctaOrderDetailCssrDvo(dto);

        int processCount = 0;
        int checkCount = mapper.selectContractBaseCheck(dvo);

        if (StringUtils.isEmpty(dvo.getCstNo())) {
            dvo.setCstNo(mapper.selectCustomerNumber(dvo.getCntrNo()));
        }

        if (checkCount <= 0) {
            processCount += mapper.insertCashSalesReceipt(dvo);
            processCount += mapper.insertCashSalesReceiptApprovalPresentState(dvo);
        } else {
            processCount += mapper.insertCashSalesReceiptApprovalPresentState(dvo);
            processCount += mapper.updateCashSalesReceiptApprovalPresentState(dvo);
        }

        return processCount;
    }

    @Transactional
    public int saveCashSalesReceiptRpbls(List<SaveRpblsReq> dtos) {
        List<WctaOrderDetailCssrDvo> dvos = converter.mapSaveRpblsReqToWctaOrderDetailCssrDvo(dtos);
        int processCount = 0;

        for (WctaOrderDetailCssrDvo dvo : dvos) {

            if (StringUtils.isEmpty(dvo.getCstNo())) {
                dvo.setCstNo(mapper.selectCustomerNumber(dvo.getCntrNo()));
            }

            processCount += mapper.insertCashSalesReceiptChangeHistory(dvo);
            // processCount += mapper.updateCashSalesReceiptRegistration(dvo);
        }

        return processCount;
    }
}
