package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzTxinvRcpBaseChangeHistDvo {
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private Long chSn; /* 변경일련번호 */
    private String txinvPdDvCd; /* 세금계산서상품구분코드 */
    private String txinvPblDvCd; /* 세금계산서발행구분코드 */
    private String aplcPsicId; /* 신청담당자ID */
    private String rcpdt; /* 접수일자 */
    private String bzrno; /* 사업자등록번호 */
    private String cntrCstNo; /* 계약고객번호 */
    private String txinvPblYn; /* 세금계산서발행여부 */
    private String txinvPblD; /* 세금계산서발행일 */
    private Integer txinvBndlSn; /* 세금계산서묶음일련번호 */
    private String dlpnrPsicNm; /* 거래처담당자명 */
    private String emadr; /* 이메일주소 */
    private String locaraTno; /* 지역전화번호 */
    private String exnoEncr; /* 전화국번호암호화 */
    private String idvTno; /* 개별전화번호 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String rmkCn; /* 비고내용 */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
