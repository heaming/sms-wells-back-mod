package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-I-0013 고객코드, 바코드 일치 확인 API 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.26
 */
@Getter
@Setter
public class WsniCustomerBarcodeDvo {
    String cntrNo;
    String custNm;
}
