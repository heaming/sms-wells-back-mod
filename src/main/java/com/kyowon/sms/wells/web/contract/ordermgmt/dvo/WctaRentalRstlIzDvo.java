package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaRentalRstlIzDvo {
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private Integer stplTn; /* 약정회차 */
    private String stplTpCd; /* 약정유형코드 */
    private String stplPtrmUnitCd; /* 약정기간단위코드 */
    private Long stplPtrm; /* 약정기간 */
    private String stplStrtdt; /* 약정시작일자 */
    private String stplEnddt; /* 약정종료일자 */
    private Long stplDscAmt; /* 약정할인금액 */
    private String rstlStatCd; /* 재약정상태코드 */
    private String stplRcpDtm; /* 약정접수일시 */
    private String rcpOgTpCd; /* 접수조직유형코드 */
    private String rcpPrtnrNo; /* 접수파트너번호 */
    private BigDecimal feeAckmtCt; /* 수수료인정건수 */
    private Long feeAckmtBaseAmt; /* 수수료인정기준금액 */
    private String feeFxamYn; /* 수수료정액여부 */
    private BigDecimal ackmtPerfRt; /* 인정실적율 */
    private Long ackmtPerfAmt; /* 인정실적금액 */
    private String notyFwId; /* 알림발송ID */
    private String stplCnfmDtm; /* 약정확정일시 */
    private String cnfmUsrId; /* 확정사용자ID */
    private String cntrChFshDtm; /* 계약변경완료일시 */
    private String stplCanDtm; /* 약정취소일시 */
    private String stplCanUsrId; /* 약정취소사용자ID */
    private String stplWdwlDtm; /* 약정철회일시 */
    private String stplWdwlUsrId; /* 약정철회사용자ID */
    private String stplWdwlCn; /* 약정철회내용 */
    private String stplDscStrtdt; /* 약정할인시작일자 */
    private String stplDscEnddt; /* 약정할인종료일자 */
    private Long stplDscAcuAmt; /* 약정할인누적금액 */
    private Long stplDscBorAmt; /* 약정할인위약금액 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
