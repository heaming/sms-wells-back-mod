package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 삼성전자 설치일 엑셀 업로드 시, validation 수행을 위한 계약 정보 조회 객체
 * </pre>
 *
 * @author 박주형
 * @since 2023-05-23
 */
@Getter
@Setter
public class WcteSecNistlValidationDvo {
    private String cntrNo;
    private int cntrSn;
    private String sellTpCd;
    private String secPdYn; /* 삼성전자 상품 여부 */
    private String canOrRejYn; /* 취소 혹은 방문거부 여부 */
}
