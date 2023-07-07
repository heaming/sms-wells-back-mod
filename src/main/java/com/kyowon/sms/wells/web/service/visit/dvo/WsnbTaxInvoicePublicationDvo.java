package com.kyowon.sms.wells.web.service.visit.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0299PM01 (법인계약공통) 세금계산서발행요청
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.05.30
 */
@Setter
@Getter
public class WsnbTaxInvoicePublicationDvo {

    String rcgvpKnm;
    String cntrNo;
    String cntrSn;
    String pdAbbrNm;
    BigDecimal stlmAmt;
    String cstSvAsnNo;
    String sellTpNm;
    String copnDvNm;
    String pdUswyNm;
    String pdGdNm;
    String cntrCnfmDtm;
    BigDecimal mmIstmAmt;
    String csBilNo;
    String stlmDvCd;
    String cntrCstNo;
    String copnDvCd;
    String bzrno;
    String emadr;
    String billDvCd;
    List<String> cstSvAsnNos;

}
