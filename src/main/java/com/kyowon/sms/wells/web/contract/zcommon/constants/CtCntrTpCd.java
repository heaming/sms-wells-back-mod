package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCntrTpCd {
    INDIVIDUAL("01", "개인"),
    COOPERATION("02", "법인"),
    EMPLOYEE("03", "임직원"),
    MEMBERSHIP("07", "멤버십"),
    RE_STIPULATION("08", "재약정"),
    QUOTE("09", "견적서");

    final String code;
    final String codeName;

    static final String name = "계약유형코드";

    CtCntrTpCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtCntrTpCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
