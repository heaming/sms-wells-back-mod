package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractRegSmrDvo {
    String cntrTpNm; // 계약유형
    String cntrtKnm; // 계약자명
    List<String> products; // 상품
    String stlmTpNm; // 결제유형
    String dpTpNm; // 결제방법
    Long rcAmt; // 등록금(계약금)
    Long mpAmt; // 월납부금
}
