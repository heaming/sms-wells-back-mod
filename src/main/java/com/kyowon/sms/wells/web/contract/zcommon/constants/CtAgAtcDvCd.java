package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtAgAtcDvCd {
    AG_001("001", "개인정보 수집 및 이용 동의"),
    AG_002("002", "개인정보 제3자 제공 동의"),
    AG_003("003", "마케팅 목적 수집 이용 및 광고성 정보 수신 동의"),
    AG_201("201", "개인정보 수집 및 이용 동의"),
    AG_202("202", "마케팅 목적 처리 동의"),
    AG_101("101", "이용약관"),
    AG_102("102", "개인정보 수집 및 이용 동의"),
    AG_103("103", "마케팅 목적 처리 동의서"),
    AG_107("107", "교원그룹 통합 마케팅 목적 수집 이용 및 광고성 정보 수신 동의");

    final String code;
    final String codeName;

    static final String name = "동의항목구분코드";

    CtAgAtcDvCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtAgAtcDvCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
