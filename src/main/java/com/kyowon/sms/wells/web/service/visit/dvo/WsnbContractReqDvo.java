package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Setter
@Getter
public class WsnbContractReqDvo {
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String cntrCnfmDtm; /* 계약확정일시 */
    String cntrCanDtm; /* 계약취소일시 */
    String cntrDtlStatCd; /* 계약상세상태코드 */
    String rcgvpKnm; /* 계약자명 */
    String stopYn; /* */
    String istDt; /* 설치일자 */
    String reqdDt; /* 철거일자 */
    String cpsDt; /* 보상일자 */
    String basePdCd; /* saleCd */
    String basePdNm; /* saleNm */
    String pdctPdCd; /* pdCd */
    String pdctPdGrpCd; /**/
    String pdctPdSize; /**/
}
