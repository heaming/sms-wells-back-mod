package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaRentalProductChngPsDto {

    @ApiModel(
        value = "WctaRentalProductChngPsDto-SearchReq",
        description = "렌탈제품 교체 현황 Search Request Dto"
    )
    public record SearchReq(
        String jobCd, /* 업무구분 */
        String strtdt, /* 설치일자 */
        String enddt, /* 설치일자 */
        String basePdCd, /* 상품코드 */
        String sellOgTpCd, /* 판매구분? */
        String sellPrtnrNo /* 등록담당? */
        ) {
    }

    @ApiModel(
        value = "WctaRentalProductChngPsDto-SearchRes",
        description = "렌탈제품 교체 현황 Search Result Dto"
    )
    public record SearchRes(
        String sellTpDtlCd,
        String sellOgTpCd,
        String cntrNo,
        int cntrSn,
        int chSn,
        String cstKnm,
        String pdctIstDt,
        String reqdSapMatCd,
        String reqdPcdtPdCd,
        String reqdPdCd,
        String reqdPdNm,
        String reqdSapAssetNo,
        String istSapMatCd,
        String istPcdtPdCd,
        String istPdCd,
        String istPdNm,
        String istSapAssetNo,
        String sellPrtnrNo,
        String prtnrNm
    ) {
    }
}
