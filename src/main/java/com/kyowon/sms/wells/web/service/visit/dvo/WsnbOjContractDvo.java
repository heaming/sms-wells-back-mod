package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API (모종계약정보)
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.16
 * @see [AS-IS] LC_ASREGN_API_000
 */
@Setter
@Getter
public class WsnbOjContractDvo {

    String cntrNo;
    String cntrSn;
    String relDtlCd;

    public WsnbOjContractDvo(String cntrNo, String cntrSn, String relDtlCd) {
        this.cntrNo = cntrNo;
        this.cntrSn = cntrSn;
        this.relDtlCd = relDtlCd;
    }

}
