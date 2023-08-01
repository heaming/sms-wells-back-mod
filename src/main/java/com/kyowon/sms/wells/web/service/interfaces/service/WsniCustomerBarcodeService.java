package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerBarcodeDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerBarcodeDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerBarcodeDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCustomerBarcodeMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-I-0013 고객코드, 바코드 일치 확인 API 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.26
 */
@Service
@RequiredArgsConstructor
public class WsniCustomerBarcodeService {

    private final WsniCustomerBarcodeMapper mapper;

    public SearchRes getCustomerBarcodes(SearchReq req) {
        WsniCustomerBarcodeDvo dvo = new WsniCustomerBarcodeDvo();
        SearchRes res;
        try {
            List<WsniCustomerBarcodeDvo> customers = mapper.selectCustomerBarcodes(req);
            if (customers.size() > 0) {
                WsniCustomerBarcodeDvo customer = customers.get(0);
                if (StringUtils.equals(customer.getCntrNo(), req.cntrNo())) {
                    res = new SearchRes("00", customer.getCustNm(), "ok");
                } else {
                    res = new SearchRes("01", "", "바코드와 고객코드가 일치하지 않습니다.");
                }
            } else if (req.cntrNo().length() > 0) {
                res = new SearchRes("02", "", "바코드에 해당하는 고객이 없습니다.");
            } else {
                res = new SearchRes("03", "", "바코드를 입력하세요.");
            }
        } catch (Exception e) {
            res = new SearchRes("99", "", "바코드 조회 오류.");
        }
        return res;
    }
}
