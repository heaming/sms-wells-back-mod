package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtDpDvCd {
    DEPOSIT("1", "입금"),
    REFUND("2", "환불"),
    WHOLE_DEPOSIT("3", "전급입금"),
    WHOLE_REFUND("4", "전금환불");

    final String code;
    final String codeName;

    static final String name = "입금구분코드";

    CtDpDvCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtDpDvCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
