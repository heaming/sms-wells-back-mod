package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCntrwTpCd {
    SPAY_ELHM("1", "일시불(환경가전)"),
    SPAY_BH("2", "일시불(BH)"),
    RENTAL("3", "렌탈"),
    MEMBERSHIP("4", "멤버십"),
    HOME_CARE("5", "홈케어서비스"),
    SEEDING("6", "모종일시불"),
    RGLR_SHP("7", "정기배송"),
    LONG_TERM_INSTALLMENT("8", "장기할부"),
    LEASE("9", "리스");

    final String code;
    final String codeName;

    static final String name = "계약서유형코드";

    CtCntrwTpCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtCntrwTpCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
