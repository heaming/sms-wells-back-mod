package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 삼성전자 확정일 엑셀 업로드 시, TB_SSSO_PDCT_IDNO_OSTR_IZ(제품고유번호출고내역) 데이터 생성 객체
 * </pre>
 *
 * @author joobro
 * @since 2023-05-23
 */
@Getter
@Setter
public class WctePdOstrIzDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
//    private int ostrSn; /*출고일련번호; 0 */
    private String sellTpCd; /*판매유형코드*/
//    private String ostrRqdt; /*출고요청일자*/
    private String pdctIdno; /*제품고유번호*/
    private String sppBzsModelId; /*배송업체모델ID*/
//    private String ostrMtrDvCd; /*출고자료구분코드: 1 일반배송*/
//    private String sppProcsBzsCd; /*배송처리업체코드: 3. 삼성물산*/
}
