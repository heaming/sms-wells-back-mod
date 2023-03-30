package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncAsTransferDvo {

    String cstSvAsnNo; /* 고객서비스배정번호 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String basePdCd; /* 상품코드 */
    String afchBlgCdOrigin; /* 소속ID(변경전) */
    String afchEmpnoOrigin; /* 파트너번호(변경전) */
    String afchBlgCd; /* 소속ID(변경후) */
    String afchEmpno; /* 파트너번호(변경후) */
    String sppOrdNo; /* 배송주문번호 */
    String sppPlanSn; /* 배송계획일련번호 */
}
