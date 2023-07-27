package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtSellTpCd {
    SPAY("1", "일시불"),
    RENTAL("2", "렌탈/리스"),
    MSH("3", "멤버십"),
    CO_IST("4", "회사설치"),
    MTNC("5", "유지관리"),
    RGLR_SPP("6", "정기배송"),
    FILT("9", "필터");

    final String code;
    final String codeName;

    static final String name = "판매유형코드";

    CtSellTpCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtSellTpCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
