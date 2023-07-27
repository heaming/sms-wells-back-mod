package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbMachineChangeCstInfDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbMachineChangeCstInfDto-SearchRes")
    public record SearchRes(
        String ojCntrNo, // 대상계약번호
        String ojCntrSn, // 대상계약일련번호
        String mchnChTpCd, // 기기변경유형코드
        String basePdCd, // 기준상품코드
        String sellTpCd, // 판매유형코드
        String mchnCpsApyr // 기기보상적용율
    ) {}
}
