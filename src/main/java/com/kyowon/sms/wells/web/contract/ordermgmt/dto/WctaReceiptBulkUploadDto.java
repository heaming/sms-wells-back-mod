package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.kyowon.sflex.common.common.dto.SujiewonDto;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzPdBasDvo;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WctaReceiptBulkUploadDto {
    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateProspectReq",
        description = "가망고객 검증 요청 객체"
    )
    public record ValidateProspectReq(
        @NotBlank
        String adr1,
        String adr2,
        @NotBlank
        String basePdCd,
        String svPdCd
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateProspectRes",
        description = "가망고객 검증 결과 객체"
    )
    public record ValidateProspectRes(
        SujiewonDto.FormatRes adr,
        WctzPdBasDvo pdBas
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-CreateProspectCstReq",
        description = "가망고객 생성 요청 객체"
    )
    public record CreateProspectCstReq(
        @NotBlank
        String basePdCd,
        @NotBlank
        @ValidDate
        String pspcCstInflwDt,
        @NotBlank
        String pspcCstKnm,
        @NotBlank
        String copnDvCd,
        @NotBlank
        @ValidDate
        String bryyMmdd,
        String bzrno,
        String sexDvCd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        @NotBlank
        String adrId,
        @NotBlank
        String alncmpDgPrtnrMapngCd,
        String alncmpCd,
        String alncmpDgPrtnrOgTpCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String cnslMoCn
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateBulkRentalReq",
        description = "렌탈 업로드 검증 요청 객체"
    )
    public record ValidateBulkRentalReq(
        @NotBlank
        String adr1,
        String adr2,
        @NotBlank
        String basePdCd,
        @NotBlank
        String svPdCd,
        @NotBlank
        String cstNo,
        @NotBlank
        String cstKnm,
        @NotBlank
        String copnDvCd,
        @ValidDate
        String bryyMmdd,
        String bzrno,
        @NotBlank
        String sexDvCd,
        @NotBlank
        String cralLocaraTno,
        @NotBlank
        String mexnoEncr,
        @NotBlank
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        @NotNull
        Integer cntrPtrm,
        @NotNull
        Long cntrAmt,
        @NotNull
        Integer stplPtrm,
        @NotBlank
        String alncmpDgPrtnrMapngCd,
        @NotBlank
        String alncmpDgPrtnrOgTpCd,
        @NotBlank
        String rentalDscDvCd,
        @NotBlank
        String rentalCrpDscrCd,
        @NotBlank
        String rentalDscTpCd,
        Long sellDscCtrAmt
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateBulkRentalRes",
        description = "렌탈 업로드 검증 결과 객체"
    )
    public record ValidateBulkRentalRes(
        String adrId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String sellTpCd,
        String sellTpDtlCd,
        Integer svPrd,
        Double pdBaseAmt,
        Double sellAmt,
        Double dscAmt,
        Long cntramDscAmt,
        Double fnlAmt,
        Long vat,
        Double cntrTam,
        Float ackmtPerfRt,
        Long ackmtPerfAmt,
        Integer feeAckmtCt,
        Long feeAckmtBaseAmt,
        String sellInflwChnlDtlCd,
        String pdctPdRelId,
        String pdctPdCd,
        String pdctVlStrtDtm,
        String pdctVlEndDtm,
        Integer pdctPdQty,
        String svPdRelId,
        String svVlStrtDtm,
        String svVlEndDtm,
        Integer svPdQty,
        String pdPrcFnlDtlId,
        Integer verSn,
        String fxamFxrtDvCd,
        Double ctrVal,
        Double fnlVal,
        String pdPrcId
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-CreateBulkRentalReq",
        description = "렌탈 업로드 계약 생성 요청 객체"
    )
    public record CreateBulkRentalReq(
        @NotBlank
        String basePdCd, /*기준상품코드*/
        @NotBlank
        String copnDvCd, /*법인격구분코드*/
        @NotBlank
        String cstNo, /*고객번호*/
        @NotBlank
        String alncmpDgPrtnrMapngCd, /*대표파트너번호*/
        String alncmpCd, /*제휴사코드*/
        String alncmpDgPrtnrOgTpCd, /*대표파트너조직코드*/
        Long cntrAmt, /*등록비*/
        Integer cntrPtrm, /*계약기간*/
        @NotBlank
        String svPdCd, /*서비스상품코드*/
        Integer stplPtrm, /*약정기간*/
        String rentalDscTpCd, /*렌탈할인유형코드*/
        String rentalDscDvCd, /*렌탈할인구분코드*/
        String rentalCrpDscrCd, /*렌탈할인율코드*/
        Long sellDscCtrAmt, /*법인특별할인금액*/
        String alncmpSuscOrdNo, /*구독주문번호*/
        @NotBlank
        String adrId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        @NotBlank
        String sellTpCd,
        @NotBlank
        String sellTpDtlCd, /*판매유형상세코드*/
        Integer svPrd,
        Double pdBaseAmt,
        Double sellAmt,
        Double dscAmt,
        Double fnlAmt,
        Long cntramDscAmt,
        Long vat,
        Double cntrTam,
        Float ackmtPerfRt,
        Long ackmtPerfAmt,
        Integer feeAckmtCt,
        Long feeAckmtBaseAmt,
        @NotBlank
        String sellInflwChnlDtlCd,
        @NotBlank
        String pdctPdRelId,
        @NotBlank
        String pdctPdCd,
        @NotBlank
        String pdctVlStrtDtm,
        @NotBlank
        String pdctVlEndDtm,
        Integer pdctPdQty,
        @NotBlank
        String svPdRelId,
        @NotBlank
        String svVlStrtDtm,
        @NotBlank
        String svVlEndDtm,
        Integer svPdQty,
        @NotBlank
        String pdPrcFnlDtlId,
        @NotBlank
        Integer verSn,
        String fxamFxrtDvCd,
        Double ctrVal,
        Double fnlVal,
        String pdPrcId
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateBulkSpayReq",
        description = "일시불 업로드 검증 요청 객체"
    )
    public record ValidateBulkSpayReq(
        @NotBlank
        String basePdCd, /* 기준상품코드 */
        @NotBlank
        String hcrDvCd, /* 홈케어구분코드 */
        @NotBlank
        String alncmpDgPrtnrMapngCd, /* 대표파트너코드 */
        @NotBlank
        String alncmpDgPrtnrOgTpCd, /* 대표파트너조직유형코드 */
        @NotBlank
        String spayDscDvCd, /* 일시불할인구분코드*/
        @NotBlank
        String spayDscrCd, /* FIXME: 일시불할인율코드?  */
        Long sellDscCtrAmt, /*법인특별할인금액*/
        Integer frisuBfsvcPtrmN, /* 무상멤버십기간 */
        @NotBlank
        String svPdCd, /* 서비스상품코드 */
        @NotBlank
        String copnDvCd, /* 법인격구분코드 */
        @NotBlank
        String cstNo, /* 고객번호 */
        @NotBlank
        String cstKnm, /* 고객명 */
//        @NotBlank
//        String copnDvCd,
        @ValidDate
        String bryyMmdd, /* 생년월일 */
//        String bzrno,
        @NotBlank
        String sexDvCd, /* 성별 */
        @NotBlank
        String cralLocaraTno,
        @NotBlank
        String mexnoEncr,
        @NotBlank
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        @NotBlank
        String adr1,
        String adr2
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateBulkSpayRes",
        description = "일시불 업로드 검증 결과 객체"
    )
    public record ValidateBulkSpayRes(
        String cntrNo, /* 흠...? */
        String adrId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String sellTpCd,
        String sellTpDtlCd,
        Integer svPrd,
        Double pdBaseAmt,
        Double sellAmt,
        Double dscAmt,
        Long cntramDscAmt,
        Double fnlAmt,
        Long vat,
        Double cntrTam,
        Float ackmtPerfRt,
        Long ackmtPerfAmt,
        Integer feeAckmtCt,
        Long feeAckmtBaseAmt,
        String sellInflwChnlDtlCd,
        String pdctPdRelId,
        String pdctPdCd,
        String pdctVlStrtDtm,
        String pdctVlEndDtm,
        Integer pdctPdQty,
        String svPdRelId,
        String svVlStrtDtm,
        String svVlEndDtm,
        Integer svPdQty,
        String pdPrcFnlDtlId,
        Integer verSn,
        String fxamFxrtDvCd,
        Double ctrVal,
        Double fnlVal,
        String pdPrcId
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-CreateBulkSpayReq",
        description = "일시물 업로드 계약 생성 요청 객체"
    )
    public record CreateBulkSpayReq(
        @NotBlank
        String basePdCd, /*기준상품코드*/
        @NotBlank
        String hcrDvCd, /* 홈케어구분코드 */
        @NotBlank
        String alncmpDgPrtnrMapngCd, /*대표파트너번호*/
        String alncmpCd, /*제휴사코드*/
        String alncmpDgPrtnrOgTpCd, /*대표파트너조직코드*/
        String spayDscDvCd, /*일시불할인구분코드*/
        String spayDscrCd, /*일시불할인율코드*/
        Long sellDscCtrAmt, /*법인특별할인금액*/
        Integer frisuBfsvcPtrmN, /* 무상멤버십기간 */
        @NotBlank
        String svPdCd, /*서비스상품코드*/
        @NotBlank
        String copnDvCd, /* 법인격구분코드*/
        @NotBlank
        String cstNo, /*고객번호*/
        @ValidDate
        String istDt, /*설치일자*/
        String bfsvcBzsDvCd, /*BS업체구분코드*/
        String splyBzsDvCd, /*조달업체구분코드*/
        @NotBlank
        String cntrNo,
        @NotBlank
        String adrId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        @NotBlank
        String sellTpCd,
        @NotBlank
        String sellTpDtlCd, /*판매유형상세코드*/
        Integer svPrd,
        Double pdBaseAmt,
        Double sellAmt,
        Double dscAmt,
        Double fnlAmt,
        Long cntramDscAmt,
        Long vat,
        Double cntrTam,
        Float ackmtPerfRt,
        Long ackmtPerfAmt,
        Integer feeAckmtCt,
        Long feeAckmtBaseAmt,
        @NotBlank
        String sellInflwChnlDtlCd,
        @NotBlank
        String pdctPdRelId,
        @NotBlank
        String pdctPdCd,
        @NotBlank
        String pdctVlStrtDtm,
        @NotBlank
        String pdctVlEndDtm,
        Integer pdctPdQty,
        @NotBlank
        String svPdRelId,
        @NotBlank
        String svVlStrtDtm,
        @NotBlank
        String svVlEndDtm,
        Integer svPdQty,
        @NotBlank
        String pdPrcFnlDtlId,
        @NotBlank
        Integer verSn,
        String fxamFxrtDvCd,
        Double ctrVal,
        Double fnlVal,
        String pdPrcId
    ) {
    }

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateIstlcReq",
        description = "설치처 검증 요청 객체"
    )
    public record ValidateIstlcReq(
        @NotBlank
        String cntrNo,
        int cntrSn,
        @NotBlank
        String adr1,
        String adr2
    ) {}

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-ValidateIstlcRes",
        description = "설치처 검증 결과 객체"
    )
    public record ValidateIstlcRes(
        String adrId,
        String cntrCstNo,
        String copnDvCd,
        String origCntrAdrRelId,
        String adrpcTpCd,
        String cntrUnitTpCd
    ) {}

    @Builder
    @ApiModel(
        value = "WctaReceiptBulkUploadDto-CreateBulkSpayReq",
        description = "일시물 업로드 계약 생성 요청 객체"
    )
    public record CreateBulkIstlcReq(
        @NotBlank
        String cntrNo, /*계약번호*/
        @NotBlank
        int cntrSn, /* 계약일련번호 */
        @NotBlank
        String cstKnm, /*고객명*/
        @NotBlank
        String cntrtRelCd, /*계약자관계코드*/
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        @NotBlank
        String adrId,
        @NotBlank
        String cntrCstNo,
        @NotBlank
        String copnDvCd,
        @NotBlank
        String origCntrAdrRelId,
        @NotBlank
        String adrpcTpCd,
        @NotBlank
        String cntrUnitTpCd
    ) {
    }

}
