package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-MP-U-0048P01 전자설치확인서
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.07
 */
public class WsnbInstallationConfirmationDto {

    @ApiModel(value = "WsnbInstallationConfirmationDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String decvlIstYn,
        String wtholVlvIstYn,
        String istKitOcoLkYn,
        String rwtkYn,
        String pnchYn,
        String lwrWprsIstYn,
        String inwaYn,
        String unwaIstYn,
        String cstNm,
        String cntrtRelNm,
        String cstSignCn
    ) {}

    @ApiModel(value = "WsnbInstallationConfirmationDto-FindRes")
    public record FindRes(
        String cntrNo,
        String cntrSn,
        String copnDvCdNm,
        String cstKnm,
        String pdNm,
        String basePdCd,
        int rentalAmt,
        String sexDvCd,
        String bryyMmdd,
        String bzrno,
        String newAdrZip,
        String addrNm,
        String tno,
        String mpno,
        String tnoIst,
        String mpnoIst,
        String svPrd,
        String newAdrZipIst,
        String addrNmIst,
        String cstKnmIst,
        String istPlcTpCdNm,
        String etcTxt,
        String cstSignCn,
        String wkExcnDt,
        String lowpressYn,
        String notakFwYn,
        String decvlIstYn,
        String wtholVlvIstYn,
        String istKitOcoLkYn,
        String rwtkYn,
        String pnchYn,
        String lwrWprsIstYn,
        String inwaYn,
        String unwaIstYn,
        String cstNm,
        String cntrtRelNm
    ) {}

    @ApiModel(value = "WsnbInstallationConfirmationDto-CreateConfirmationReq")
    public record CreateConfirmationReq(
        String decvlIstYn,
        String wtholVlvIstYn,
        String istKitOcoLkYn,
        String rwtkYn,
        String pnchYn,
        String lwrWprsIstYn,
        String inwaYn,
        String unwaIstYn,
        String cstNm,
        String cntrtRelNm,
        String cstSignCn
    ) {}
}
