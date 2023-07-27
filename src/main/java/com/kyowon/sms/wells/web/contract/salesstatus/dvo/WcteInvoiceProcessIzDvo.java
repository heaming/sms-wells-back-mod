package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 삼성전자 확정일 엑셀 업로드 시, TB_SSSO_SPP_BZS_IVC_PROCS_IZ(배송업체송장처리내역) 데이터 생성 객체
 * </pre>
 *
 * @author 박주형
 * @since 2023-05-23
 */
@Getter
@Setter
public class WcteInvoiceProcessIzDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String sppBzsOrdId; /* 엑셀:주문번호, 컬럼:배송업체주문ID*/
    private String sppFshDt; /* 엑셀:설치일자, 컬럼:배송완료일시*/
}
