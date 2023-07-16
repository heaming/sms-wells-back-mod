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
public class WsnbContractRelationDvo {
    private String cntrRelTpCd;
    private String cntrRelDtlCd;
    private String baseDtlCntrNo;
    private String baseDtlCntrSn;
    private String ojDtlCntrNo;
    private String ojDtlCntrSn;
}
