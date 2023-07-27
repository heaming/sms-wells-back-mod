package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 삼성전자 미설치 엑셀 업로드 시, TB_SSOP_SPP_BZS_ULD_HIST(배송업체업로드이력) 테이블 데이터 생성을 위한 객체
 * </pre>
 *
 * @author joobro
 * @since 2023-05-23
 */
@Getter
@Setter
@RequiredArgsConstructor
public class WcteSecNistlDvo {
    private String cntrNo;
    private String cntrSn;
    private String sellTpCd;
    private String canRson;

    public WcteSecNistlDvo(WcteSecNistlDvo dvo) {
        this.cntrNo = dvo.cntrNo;
        this.cntrSn = dvo.cntrSn;
        this.sellTpCd = dvo.sellTpCd;
        this.canRson = dvo.canRson;
    }

}
