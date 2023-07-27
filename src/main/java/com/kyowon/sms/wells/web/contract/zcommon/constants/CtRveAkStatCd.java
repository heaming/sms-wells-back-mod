package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtRveAkStatCd {
    RECEIPT("01", "접수"),
    IN_PROGRESS("02", "처리중"),
    FINISH("03", "처리완료"),
    CANCEL("04", "접수취소");

    final String code;
    final String codeName;

    static final String name = "수납요청상태코드";

    CtRveAkStatCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtRveAkStatCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
