package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaContractDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약번호 Search Request Dto
    @Builder
    @ApiModel("WctaContractDto-SearchCntrNoReq")
    public record SearchCntrNoReq(
        String cntrCstKnm,
        String istCstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrCstNo,
        String cntrNo,
        String cntrSn
    ) {
        public SearchCntrNoReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
        }
    }

    // 홈케어 계약 Search Request Dto
    @ApiModel("WctaContractDto-SearchHomecareContractsReq")
    public record SearchHomecareContractsReq(
        @NotBlank
        String cntrNo,
        int cntrSn
    ) {}

    // 홈케어 계약 Save Request Dto
    @ApiModel("WctaContractDto-SaveHomecareContractsReq")
    public record SaveHomecareContractsReq(
        @NotBlank
        String cntrNo,
        int cntrSn,
        @ValidDate
        String candt,
        @ValidDate
        String duedt
    ) {}

    // 메일발송 Save Request Dto
    @ApiModel("WctaContractDto-SaveSendEmailsReq")
    public record SaveSendEmailsReq(
        String cntrNm,
        String cntrNo,
        Integer cntrSn,
        String rstlYn,
        String emadr
    ) {}

    // 고위험 파트너 Remove Request Dto
    @ApiModel("WctaContractDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String cntrAprAkDvCd,
        @NotBlank
        @ValidDate
        String vlStrtDtm
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Request Dto
    @ApiModel("WctaContractDto-SearchConfirmApprovalBaseReq")
    public record SearchConfirmApprovalBaseReq(
        String cntrAprAkDvCd,
        String standardDt,
        boolean aprReqCtgValid

    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 저장 Save Result Dto
    @ApiModel("WctaContractDto-SaveConfirmApprovalBaseReq")
    public record SaveConfirmApprovalBaseReq(
        @NotBlank
        String rowState,
        String checkType,
        String cntrAprBaseId,
        @NotBlank
        String cntrAprSellDvCd,
        @NotBlank
        String cntrAprAkDvCd,
        @NotBlank
        String cntrAprChnlDvVal,
        @NotBlank
        String cntrAprIchrDvCd,
        @NotBlank
        String ichrUsrId,
        @NotBlank
        String psicNm,
        @NotBlank
        String vlStrtDtm,
        @NotBlank
        String vlEndDtm,
        @NotBlank
        String cntrAprAkDvCdNm,

        @NotBlank
        String dtaDlYn,
        String notyFwOjYn
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 삭제 Remove Result Dto
    @ApiModel("WctaContractDto-RemoveConfirmApprovalBaseReq")
    public record RemoveConfirmApprovalBaseReq(
        @NotBlank
        String cntrAprBaseId,
        String cntrAprAkDvCd,
        String cntrAprSellDvCd,
        String cntrAprChnlDvVal,
        String cntrAprIchrDvCd,
        String ichrUsrId,
        String psicNm,
        String vlStrtDtm
    ) {}

    //wells 일시불 판매금 조회 - 일시불 판매금 조회 Search Request Dto
    @ApiModel(value = "WctaContractDto-SearchSpaySlamtInqrReq")
    public record SearchSpaySlamtInqrReq(
        String pdGubn,
        String vlDtm,
        String pdCd,
        String pdClsfId,
        String vstCycl,
        String useGubn,
        String dscGubn,
        String dscType
    ) {}

    //wells 렌탈 패키지 그룹 등록 삭제 - 렌탈 패키지 그룹 등록 삭제 Remove Request Dto
    @ApiModel("WctaContractDto-RemoveRentalPackageGrpMngtsReq")
    public record RemoveRentalPackageGrpMngtsReq(
        String baseDtlCntrNo,
        String baseDtlCntrSn,
        String cntrRelDtlCd,
        String cntrCstGrpId
    ) {}

    // 통합계약Step1 Search Request Dto
    @ApiModel("WctaContractDto-SearchStep1Req")
    public record SearchStep1Req(
        String cntrTpCd,
        String cstNo,
        String cntrNo,
        String cntrPrtnrNo,
        Integer step
    ) {}

    // 지국장 소속 파트너 조회 Search Request Dto
    @ApiModel("WctaContractDto-SearchMngerPrtnrReq")
    public record SearchMngerPrtnrReq(
        String dsmnPrtnrNo,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo
    ) {}

    // 재약정/멤버십 대상자 조회 Search Request Dto
    @ApiModel("WctaContractDto-SearchRnwMshCstReq")
    public record SearchRnwMshCstReq(
        String baseDtlCntrNo,
        String baseDtlCntrSn
    ) {}

    // 1+1 대상계약 조회 Search Request Dto
    @ApiModel("WctaContractDto-SearchOnepluseoneReq")
    public record SearchOnepluseoneReq(
        String baseDtlCntrNo,
        String baseDtlCntrSn
    ) {}

    // 확정 멤버십 조회 Search Request Dto
    @Builder
    @ApiModel("WctaContractDto-SearchConfirmMshReq")
    public record SearchConfirmMshReq(
        @NotBlank
        @ValidDate
        String rcpStrtDt,
        @NotBlank
        @ValidDate
        String rcpEndDt,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        @NotBlank
        @ValidDate
        String cnfmStrtDt,
        @NotBlank
        @ValidDate
        String cnfmEndDt,
        String pdNm,
        String sellOgTpCd,
        @NotBlank
        String frisuMshCrtYn,
        String prtnrNo
    ) {}

    //wells K멤버스 취소 요청 목록 조회 - K멤버스 취소 요청 목록 조회 Search Request Dto
    @ApiModel(value = "WctaContractDto-SearchKMembersCancelAsksReq")
    public record SearchKMembersCancelAsksReq(
        String cntrChRcpStrtDtm,
        String cntrChRcpFinsDtm,
        String cntrNo,
        String cntrSn,
        String statCd
    ) {}

    // 고객 Search Request Dto
    @Builder
    @ApiModel("EctaContractDto-SearchCntrtInfoReq")
    public record SearchCntrtInfoReq(
        String cntrTpCd,
        String cntrCstRelTpCd,
        String cstKnm,
        String bzrno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cntrCstNo,
        String bryyMmdd,
        String sexDvCd,
        String cntrtDv
    ) {
        public SearchCntrtInfoReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
            exnoEncr = DbEncUtil.enc(exnoEncr);
        }
    }

    // 상품 금액 조회 Dto
    @Builder
    @ApiModel("WctaContractDto-SearchPdAmtReq")
    public record SearchPdAmtReq(
        String pdCd,
        String svPdCd,
        String sellTpCd,
        String sellInflwChnlDtlCd,
        Long frisuBfsvcPtrmN,
        String sellDscrCd,
        Long stplPtrm,
        String sellDscDvCd,
        String sellDscTpCd,
        Long cntrAmt,
        Long cntrPtrm,
        Integer rntlMcn
    ) {}

    // 상품 속성목록 조회 Dto
    @Builder
    @ApiModel("EctaContractDto-SearchPdSelReq")
    public record SearchPdSelReq(
        String copnDvCd,
        String sellInflwChnlDtlCd,
        String pdCd,
        String sellTpCd,
        List<String> mshPdCds
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약번호 Search Result Dto
    @ApiModel("WctaContractDto-SearchCntrNoRes")
    public record SearchCntrNoRes(
        String cntrCnfmDtm,
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String cntrCstKnm,
        String istCstKnm,
        String pdCd,
        String pdNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {
        public SearchCntrNoRes {
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    // 홈케어 계약 Search Result Dto
    @ApiModel("WctaContractDto-SearchHomecareContractsRes")
    public record SearchHomecareContractsRes(
        String cntrNo,
        int cntrSn,
        String rcgvpKnm,
        String pdCd,
        String pdNm,
        String cntrCnfmDtm
    ) {}

    // 고위험 파트너 Search Result Dto
    @ApiModel("WctaContractDto-SearchRes")
    public record SearchRes(
        String cntrAprAkDvCd,
        String cntrAprAkDvPk,
        String cntrAprAkDvCdNm,
        String cntrAprAkDvNm,
        String cntrAprAkMsgCn,
        String cntrAprCanMsgCn,
        String cntrAprConfMsgCn,
        String vlStrtDtm,
        String vlStrtDtmPk,
        String vlEndDtm
    ) {}

    // 확정승인 요청내역 - 확정 승인 요청 내역 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmAprPsicAksRes")
    public record SearchConfirmAprPsicAksRes(
        String cntrAprFwId,
        String cntrAprAkDvCd,
        String akUsrId,
        String rqrNm,
        String cntrAprFwDvCd,
        String cntrAprFwDvNm,
        String rcvUsrNm,
        String sendDttm,
        String aprvYn,
        String aprvId,
        String aprvNm,
        String aprvDttm,
        String cancYn,
        String cntrAprAkDvCdNm,
        String cntrNo
    ) {}

    //확정승인 요청내역 - 확정 승인 구매 내역 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmAprPsicPrchssRes")
    public record SearchConfirmAprPsicPrchssRes(
        String cntrDtlNo,
        String cstKnm,
        String cstGdNm,
        String rcgvpKnm,
        String adr,
        String pdNm,
        String istDt,
        String useDiv,
        String apyTn,
        String dlqInfo
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmApprovalBaseRes")
    public record SearchConfirmApprovalBaseRes(

        String cntrAprBaseId,

        String cntrAprSellDvCd,
        String cntrAprAkDvCd,
        String cntrAprChnlDvVal,
        String cntrAprIchrDvCd,
        String ichrUsrId,
        String psicNm,
        String vlStrtDtm,
        String vlEndDtm,
        String notyFwOjYn
    ) {}
    //wells 렌탈료 가져오기 SearchReq
    @ApiModel("WctaContractDto-SearchRentalFeeReq")
    public record SearchRentalFeeReq(
        @NotBlank
        String pdCd,
        @NotBlank
        String svVstPrdCd,
        @NotBlank
        String svTpCd,
        @NotBlank
        String rgstFee,
        @NotBlank
        String chgMcn,
        @NotBlank
        String verSn
    ) {}

    //wells 일시불 판매금 조회 - 일시불 판매금 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchSpaySlamtInqrRes")
    public record SearchSpaySlamtInqrRes(
        String fnlVal,
        String vat,
        String AckmtRt,
        String AckmtAmt,
        String ctrVal,
        String sellFee,
        String svFee
    ) {}

    //wells 홈케어멤버십 가입고객여부 조회 - 홈케어멤버십 가입고객여부 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchHomeCareMshChecksRes")
    public record SearchHomeCareMshChecksRes(
        String hcrMmbrCnt,
        String hcrMmbrYn,
        String hcrSpayCnt
    ) {}

    //wells 렌탈 패키지 그룹 기등록 조회 - 렌탈 패키지 그룹 기등록 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchRentalPackageGrpMngtsRes")
    public record SearchRentalPackageGrpMngtsRes(
        String baseDtlCntrNo,
        String baseDtlCntrSn,
        String startYn
    ) {}

    //wells 렌탈 패키지 그룹 등록 - 렌탈 패키지 그룹 저장 Save Result Dto
    @ApiModel("WctaContractDto-SaveRentalPackageGrpMngtsReq")
    public record SaveRentalPackageGrpMngtsReq(
        String userGubn,
        String inpGubn,
        @NotBlank
        String cntrRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String cntrUnitTpCd,
        String cntrRelTpCd,
        String cntrRelDtlCd,
        String baseCntrNo,
        String ojCntrNo,
        String baseDtlCntrNo,
        String baseDtlCntrSn,
        String ojDtlCntrNo,
        String ojDtlCntrSn,
        String cntrCstGrpId,
        String cntrRelDtlCn,
        String otsdLkDrmVal,
        @NotBlank
        String dtaDlYn
    ) {}

    // 재약정/멤버십 대상자 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchRnwMshCstRes")
    public record SearchRnwMshCstRes(
        String baseDtlCntrNo,
        String baseDtlCntrSn
    ) {}

    // 지국장 소속 파트너 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchMngerPrtnrRes")
    public record SearchMngerPrtnrRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String dgr3LevlOgNm,
        String prtnrKnm,
        String prtnrNo
    ) {}

    // 1+1 대상계약 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchOnepluseoneRes")
    public record SearchOnepluseoneRes(
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sellTpCd,
        String basePdCd,
        String pdNm,
        String copnDvCd,
        String end,
        String retentionStrt,
        String retentionEndt,
        String cntrRcpFshDtm,
        String rnadr,
        String rdadr,
        String newAdrZip,
        String ltnAdr,
        String ltnDtlAdr,
        String oldAdrZip
    ) {}

    // 확정 멤버십 현황 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmMshRes")
    public record SearchConfirmMshRes(
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sellTpNm,
        String sellOgTpNm,
        String pdHclsfNm,
        String pdMclsfNm,
        String basePdCd,
        String pdNm,
        String frisuBfsvcPtrmN,
        String frisuMshCrtYn,
        String nationSptYn,
        String feeYn,
        String prmYn,
        String ogCd,
        String prtnrNo,
        String prtnrKnm,
        String rsbDvNm,
        String cntrRcpFshDt,
        String cntrCnfmYn,
        String cntrCnfmDt,
        String cntrPdStrtdt,
        String cntrPdEnddt,
        String cntrCanDt
    ) {}

    //wells K멤버스 취소 요청 목록 조회 - K멤버스 취소 요청 목록 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchKMembersCancelAsksRes")
    public record SearchKMembersCancelAsksRes(
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String rsgAplcDt,
        String rsgFshDt,
        String mlgResAmt,
        String procsYn,
        String kmbrsProcsBsdt,
        String trsDt,
        String rcpDt
    ) {}
}
