package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbBillingDocumentForwardingDvo {
    String bildcPblNo;
    String bildcFwTpCd; //유형코드
    String cstFnm; //--고객성명      
    String pdNm; //--상품명
    String pdQtySum; //--총수량    
    String pdSellAmtSum; //--총금액       
    String destInfo; //받는사람
    String callback;//보내는사람
    String bildcFwDrmNo1;
    String bildcFwDrmNo2;
    String fromMail;
}
