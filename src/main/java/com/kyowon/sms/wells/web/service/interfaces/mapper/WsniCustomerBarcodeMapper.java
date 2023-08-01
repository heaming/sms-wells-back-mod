package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerBarcodeDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerBarcodeDvo;

/**
 * <pre>
 * W-SV-I-0013 고객코드, 바코드 일치 확인 API 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.26
 */
@Mapper
public interface WsniCustomerBarcodeMapper {

    List<WsniCustomerBarcodeDvo> selectCustomerBarcodes(SearchReq dto);

}
