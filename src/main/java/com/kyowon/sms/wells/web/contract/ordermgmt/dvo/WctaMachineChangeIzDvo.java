package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaMachineChangeIzDvo {
    private String baseCntrNo; /* 기준계약번호 */
    private Integer baseCntrSn; /* 기준계약일련번호 */
    private Integer mchnChSn; /* 기기변경일련번호 */
    private String ojCntrNo; /* 대상계약번호 */
    private Integer ojCntrSn; /* 대상계약일련번호 */
    private String mchnChTpCd; /* 기기변경유형코드 */
    private Integer pasgMcn; /* 경과개월수 */
    private String baseCntrRcpdt; /* 기준계약접수일자 */
    private String baseCntrSlDt; /* 기준계약매출일자 */
    private BigDecimal mchnCpsApyr; /* 기기보상적용율 */
    private String mchnClnOjYn; /* 기기회수대상여부 */
    private String ojCntrMmBaseDvCd; /* 대상계약월기준구분코드 */
    private String ojCntrChFshDtm; /* 대상계약변경완료일시 */
    private String dtaDlYn; /* 데이터삭제여부 */

    private Boolean mchnChYn;
    private String pdNm;
}
