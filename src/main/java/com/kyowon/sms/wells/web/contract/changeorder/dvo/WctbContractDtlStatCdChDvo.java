package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbContractDtlStatCdChDvo {
    private String cntrNo; /* (필수)계약번호 */
    private String cntrSn; /* (필수)계약일련번호 */
    private String cntrDtlStatCd; /* (필수)변경할 계약상세상태코드 */
    private String cntrPdEnddt; /* 계약상품종료일자 */
}
