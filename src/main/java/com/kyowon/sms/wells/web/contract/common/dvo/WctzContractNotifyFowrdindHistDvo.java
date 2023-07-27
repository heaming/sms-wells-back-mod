package com.kyowon.sms.wells.web.contract.common.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzContractNotifyFowrdindHistDvo {
    private String notyFwId; /* 알림발송ID */
    private String notyFwTpCd; /* 알림발송유형코드 */
    private String notyFwBizDvCd; /* 알림발송업무구분코드 */
    private String akUsrId; /* 요청사용자ID */
    private String rqrNm; /* 요청자명 */
    private String akDtm; /* 요청일시 */
    private String fwbooDtm; /* 발송예약일시 */
    private String fwDtm; /* 발송일시 */
    private String msgTit; /* 메시지제목 */
    private String msgCn; /* 메시지내용 */
    private String notyFwRsCd; /* 알림발송결과코드 */
    private String fwLkIdkVal; /* 발송연계식별키값 */
    private String cstNo; /* 고객번호 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String fwOjRefkVal1; /* 발송대상참조키값1 */
    private String fwOjRefkVal2; /* 발송대상참조키값2 */
    private String fwOjRefkVal3; /* 발송대상참조키값3 */
    private String rcvrNm; /* 수신자명 */
    private String rcvrEmadr; /* 수신자이메일주소 */
    private String rcvrLocaraTno; /* 수신자지역전화번호 */

    @DBDecField
    @DBEncField
    private String rcvrExnoEncr; /* 수신자전화국번호암호화 */
    private String rcvrIdvTno; /* 수신자개별전화번호 */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
