package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaFgptRcpIzDvo {
    private String fgptRcpId; /* 사은품접수ID */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String cntrNo; /* 계약번호 */
    private String dtlCntrNo; /* 상세계약번호 */
    private Integer dtlCntrSn; /* 상세계약일련번호 */
    private String pspcCstId; /* 가망고객ID */
    private String fgptRcpdt; /* 사은품접수일자 */
    private String fgptOcTpCd; /* 사은품발생유형코드 */
    private String pmotFvrDtlId; /* 프로모션혜택상세ID */
    private String fgptPdCd; /* 사은품상품코드 */
    private Integer fgptQty; /* 사은품수량 */
    private Long fgptBaseUprc; /* 사은품기준단가 */
    private Long fgptFnlAmt; /* 사은품최종금액 */
    private String fgptFrisuDvCd; /* 사은품무상구분코드 */
    private String sppMthdCd; /* 배송방식코드 */
    private String fgptSppPlanDt; /* 사은품배송계획일자 */
    private String fgptAwdTpCd; /* 사은품시상유형코드 */
    private String sppFshDtm; /* 배송완료일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
