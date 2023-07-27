package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPkgBasePdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPkgPdDvo;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WctaSeedingDto {
    @Builder
    @ApiModel(
        value = "WctaSeedingDto-SearchMachineReq",
        description = "모종기기 Search Request Dto"
    )
    public record SearchMachineReq(
        @NotBlank
        String cntrCstNo,
        @NotBlank
        String rglrSppMchnTpCd /* enumerable???????*/
    ) {
    }

    @ApiModel(
        value = "WctaSeedingDto-SearchMachineRes",
        description = "모종기기 Search Result Dto"
    )
    public record SearchMachineRes(
        String cntrNo,
        String cntrSn,
        String pdCd,
        String pdNm,
        String istDt,
        String posQty
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaSeedingDto-SearchFrdmPkgReq",
        description = "모종기기 Search Request Dto"
    )
    public record SearchFrdmPkgReq(
        @NotBlank
        String basePdCd,
        /* 이하, 모종 가격이 필요한 경우 요청되어야 할 정보들*/
        String rglrSppMchnTpCd,
        String rglrSppPrcDvCd
    ) {
    }

    @ApiModel(
        value = "WctaSeedingDto-SearchFrdmPkgRes",
        description = "패키지 상품 Search Result Dto"
    )
    public record SearchFrdmPkgRes(
        WctaPkgBasePdDvo basePd,
        List<WctaPkgPdDvo> pkgPds
    ) {
    }
}
