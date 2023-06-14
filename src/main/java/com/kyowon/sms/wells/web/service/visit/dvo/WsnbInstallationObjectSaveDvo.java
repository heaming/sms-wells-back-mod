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
public class WsnbInstallationObjectSaveDvo {
    String asIstOjNo;
    String urgtYn; /* 긴급여부 */
    String vstRqdt; /* 방문요청일자 */
    String vstAkHh; /* 방문요청시간 */
    String cnslTpHclsfCd; /* 상담유형대분류코드 */
    String cnslTpMclsfCd; /* 상담유형중분류코드 */
    String cnslTpLclsfCd; /* 상담유형소분류코드 */
    String asRefriDvCd;
    String bfsvcRefriDvCd;
    String smsFwYn; /* SMS발송여부 */
    String dpDvCd; /* 입금구분코드 */
    int svEtAmt; /* 서비스예상금액 */
    String svCnrOgId;
    String svBizDclsfCd;
    String dtaStatCd; /* 자료상태코드 */
    String pdctPdCd;
    String pdGdCd;
    String pdUswyCd;
    String cnslDtlpTpCd; /* 상담세부유형코드 */
    String cnslMoCn; /* 상담메모내용 */
    String rcgvpKnm;

}
