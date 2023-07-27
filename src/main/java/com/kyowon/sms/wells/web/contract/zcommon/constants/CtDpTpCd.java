package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.kyowon.sms.common.web.contract.zcommon.constants.CtDpMesCd;
import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtDpTpCd {
    RDS_DDTN("0503", "RDS공제", "05"),
    FEE_DDTN("0502", "수수료공제", "05"),
    W_POINT_CV("0601", "W포인트_전환", "06"),
    M_POINT("0602", "M포인트", "06"),
    ORM_WELS_ALNC_POINT("0603", "더오름웰스제휴포인트", "06"),
    ORM_LIF_ALNC_POINT("0604", "더오름라이프제휴포인트", "06"),
    RECAP_POINT("0605", "유상포인트", "06"),
    FRISU_POINT("0606", "무상포인트", "06"),
    ACTI_MLG("0701", "활동마일리지", "07"),
    SMT_MLG("0702", "스마트마일리지", "07"),
    KMBRS_POINT("0703", "K멤버스포인트", "07"),
    W_MONEY_WELS_ALNC("0801", "W머니웰스제휴", "08"),
    W_MONEY("0802", "K머니", "08"),
    KMBRS_CASH("0803", "K멤버스캐시", "08"),
    IDV_RVE_VAC("0101", "개별수납가상계좌", "01"),
    AC_AFTN("0102", "계좌자동이체", "01"),
    PG_AFTN("0103", "PG계좌이체", "01"),
    CRP_AC("0104", "법인계좌회사통장", "01"),
    IDV_RVE_CRDCD("0201", "개별수납신용카드", "02"),
    YMDR_CARD_VCH("0202", "여민동락카드바우처", "02"),
    CRDCD_AFTN("0203", "카드자동이체", "02"),
    PG_CRDCD("0204", "PG신용카드", "02"),
    BILL("0301", "어음", "03"),
    GIRO("0401", "지로", "04");

    final String code;
    final String codeName;
    final CtDpMesCd dpMesCd;

    static final String name = "입금유형코드";

    CtDpTpCd(String code, String codeName, String dpMesCd) {
        this.code = code;
        this.codeName = codeName;
        this.dpMesCd = CtDpMesCd.of(dpMesCd);
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public CtDpMesCd getCtDpMesCd() {
        return dpMesCd;
    }

    public String getDpMesCd() {
        return dpMesCd.getCode();
    }

    public static CtDpTpCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
