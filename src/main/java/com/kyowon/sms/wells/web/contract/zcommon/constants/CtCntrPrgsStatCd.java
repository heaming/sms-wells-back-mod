package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCntrPrgsStatCd {
    TEMP_STEP1("10"), /* STEP1 */
    TEMP_STEP2("12"), /* STEP2 */
    TEMP_STEP3("14"), /* STEP3 */
    TEMP_STEP4("16"), /* STEP4 */
    TEMP_STEP5("18"), /* STEP5 */
    WRTE_FSH("20"), /*작성완료*/
    STLM_STNB("30"), /*결재대기*/
    STLM_ING("40"), /*결재중*/
    STLM_FSH("50"), /*결재완료, 하우에버 입금 대기*/
    CNFM("60"), /*확정*/
    DEL("99"); /*삭제*/

    final String code;

    static final String name = "결제 진행 상태";

    CtCntrPrgsStatCd(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CtCntrPrgsStatCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "를 확인해보세요."));
    }
}
