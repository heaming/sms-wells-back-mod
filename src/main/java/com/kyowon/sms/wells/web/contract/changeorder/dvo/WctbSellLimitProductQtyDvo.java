package com.kyowon.sms.wells.web.contract.changeorder.dvo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbSellLimitProductQtyDvo {
    private String sellLmPdBaseId; // 판매제한상품기준ID
    private int sellBaseQty; // 판매기준수량
    private int sellAcuQty; // 판매누적수량
    private String sellLmRsonCn; // 판매제한사유내용

    private int workQty; // 잔여수량
    private String stpYn; // 중지여부
}


