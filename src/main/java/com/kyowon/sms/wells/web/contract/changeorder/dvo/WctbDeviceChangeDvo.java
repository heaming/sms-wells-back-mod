package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbDeviceChangeDvo {
    private String ojCntrNo; /* 대상계약번호 */
    private String ojCntrSn; /* 대상계약일련번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String basePdCd; /* 기준상품코드 */
    private String slDt; /* 메츨일자 */
    private String canDt; /* 취소일자 */
    private String reqdDt; /* 철거일자 */
    private String cntrPdEnddt; /* 완료일자 */
    private String mchnChTpCd; /* 기기변경유형코드 */
    private String ackmtPerfRt; /* 최종실적율(인정실적율) */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
}
