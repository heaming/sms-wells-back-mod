package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctaInterestFreeIstmYnDvo {

    private String pdCd; // 상품코드
    private String rcpDt; // 접수일자
    private int pdQty; // 상품수량
    private String crpDscDvCd; // 법인할인구분코드
    private int cshBlam; // 현금잔액
    private int istmMcn;// 할부개월
    private int resMnthIstmAmt; // 잔여월할부금액
    private String intfrCheckYn; // 무이자여부
    private String errYn; // 오류여부
}
