package com.kyowon.sms.wells.web.promotion.common.dvo;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class WpmzPromotionFreeGiftDvo {
    @NonNull
    private String fgptCd;              /* 사은품 상품코드    */
    @NonNull
    private String fgptQty;             /* 사은품 수량       */
    private String fgptOstrDt;          /* 사은품 출고일자    */
}
