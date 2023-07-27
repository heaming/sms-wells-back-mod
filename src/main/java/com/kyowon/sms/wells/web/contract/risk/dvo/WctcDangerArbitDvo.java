package com.kyowon.sms.wells.web.contract.risk.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctcDangerArbitDvo {
    private String dangChkId; /* 위험점검ID */
    private String dangOjOgTpCd; /* 위험대상조직유형코드 */
    private String dangOjPrtnrNo; /* 위험대상파트너번호 */
    private String dangOcStrtmm; /* 발생년월 */
    private String dangMngtOgTpCd; /* 위험관리조직유형코드 */
    private String dangMngtPrtnrNo; /* 위험관리파트너번호 */
    private String dangOjOgId; /* 위험대상조직ID */
    private String dangOjPstnDvCd; /* 위험대상직급구분코드 */
    private String dangMngtPstnDvCd; /* 위험관리직급구분코드 */
    private String dangGdCd; /* 위험등급값 */
    private Long dangUncvrCt; /* 위험적발건수 */
    private String dangChkNm; /* 위험점검명 */
    private String dangChkDt; /* 위험점검일자 */
    private String dangOcStrtdt; /* 위험발생시작일자 */
    private String dangOcEnddt; /* 위험발생종료일자 */
    private String dangArbitDt; /* 위험조치일자 */
    private String dangArbitCd; /* 위험조치코드 */
    private Integer dangArbitLvyPc; /* 위험조치부과점수 */
    private String dangArbitOgId; /* 위험조치조직ID */
    private String dangArbitCn; /* 위험조치내용 */
    private String dcplaRsCn; /* 징계결과내용 */
    private Long dcplaRimbAmt; /* 징계변제금액 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
