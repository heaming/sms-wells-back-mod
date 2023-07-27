package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 삼성전자 확정일 엑셀 업로드 시, validation 수행을 위한 계약 정보 조회 객체
 * </pre>
 *
 * @author 박주형
 * @since 2023-05-23
 */
@Getter
@Setter
public class WcteConfirmValidationDvo {
    private String cntrNo;
    private int cntrSn;
    private String canDt;
    private String cntrCnfmYn;
    private String istDt;
    private String sppDuedt;
    private String cpsDt;

    private String sellTpCd;
    private String secPdYn;
    private String ostrRgstYn;
}
