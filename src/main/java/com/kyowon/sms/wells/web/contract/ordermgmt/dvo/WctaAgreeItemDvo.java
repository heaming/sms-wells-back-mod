package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WctaAgreeItemDvo {
    private String cstAgId; /*고객동의ID*/
    private String agDrmDvCd; /*동의식별구분코드*/
    private String agDrmRefkVal; /*동의식별참조키값*/
    private String cntcPrtnrNo; /*접촉파트너번호*/
    private String cnslNo; /*상담번호*/
    private String agProcsDtm; /*동의처리일시*/
    private String agExnPrcsdt; /*동의만료처리일자*/
    private String agExnProcsRsonCd; /*동의만료처리사유코드*/
    private String moCn; /*메모내용*/
}


