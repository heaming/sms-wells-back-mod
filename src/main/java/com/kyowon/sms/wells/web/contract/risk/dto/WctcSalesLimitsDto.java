package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcSalesLimitsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 접수제한 관리-블랙리스트 Search Request Dto
    @Builder
    @ApiModel("WctcSalesLimitsDto-SearchBlacklistReq")
    public record SearchBlacklistReq(
        String cntrCstNo,
        String cntrNo,
        String cstKnm,
        String adrCl,
        String adr,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String prtnrInfo
    ) {
        public SearchBlacklistReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
        }
    }

    // 접수제한 관리-블랙리스트 Save Request Dto
    @Builder
    @ApiModel("WctcSalesLimitsDto-SaveBlacklistReq")
    public record SaveBlacklistReq(
        @NotBlank
        String rowState,
        String sellLmId,
        String cntrNo,
        int cntrSn,
        String sellLmRsonCn,
        String dtaDlYn,
        String orglDtaDlYn,
        String fnlMdfcDtm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 접수제한 관리-블랙리스트 Find Result Dto
    @ApiModel("WctcSalesLimitsDto-FindBlacklistRes")
    public record FindBlacklistRes(
        String sellTpCd,
        String cntrCstNo,
        String cstKnm,
        String copnDvCd,
        String bryyMmdd,
        String bzrno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String cntrMpno,
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno,
        String cntrTno,
        String cntrZip,
        String cntrAdr,
        String istllKnm,
        String istllCralLocaraTno,
        String istllMexnoEncr,
        String istllCralIdvTno,
        String istllMpno,
        String istllLocaraTno,
        String istllExnoEncr,
        String istllIdvTno,
        String istllTno,
        String istllZip,
        String istllAdr,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String prtnrCralLocaraTno,
        String prtnrMexnoEncr,
        String prtnrCralIdvTno,
        String prtnrMpno
    ) {
        public FindBlacklistRes {
            cntrMexnoEncr = DbEncUtil.dec(cntrMexnoEncr);
            cntrExnoEncr = DbEncUtil.dec(cntrExnoEncr);
            istllMexnoEncr = DbEncUtil.dec(istllMexnoEncr);
            istllExnoEncr = DbEncUtil.dec(istllExnoEncr);
            prtnrMexnoEncr = DbEncUtil.dec(prtnrMexnoEncr);
        }
    }
    // wells 사업자 가입제한 대상 관리 Search Request Dto
    @Builder
    @ApiModel("WctcSalesLimitDto-SearchEntrpJLmOjReq")
    public record SearchEntrpJLmOjReq(
        String dlpnrNm, //상호명

        String sellLmBzrno, //사업자번호
        @ValidDate
        String sellLmOcStm, //시작일자
        @ValidDate
        String sellLmOcDtm //종료일자
    ) {}

    @Builder
    @ApiModel("WctcSalesLimitDto-SaveEntrpJLmOjReq")
    public record SaveEntrpJLmOjReq(
        @NotBlank
        String sellLmBzrno, //사업자번호
        @NotBlank
        String sellLmDv,
        @NotBlank
        String rowState, //Row의 상태
        String sellLmId,

        String sellLmRson,
        String sellLmRsonCd,
        String sellLmRsonCn,
        String sellLmRlsCn,
        String sellLmCntrSn,
        String dataRow,
        String sellLmOcDtm,
        String sellLmRlsDtm,
        String sellRson,
        String sellLmPsic,
        String sellLmRlsPsic,
        String sellLmPsicNm,
        String sellLmRlsPsicNm,
        String dtaDlYn
    ) {}

    // 접수제한 관리-블랙리스트 Search Result Dto
    @ApiModel("WctcSalesLimitsDto-SearchBlacklistRes")
    public record SearchBlacklistRes(
        String sellLmId,
        String dtaDlYn,
        String orglDtaDlYn,
        String sellTpCd,
        String cntrNo,
        int cntrSn,
        String sellLmRsonCn,
        String cntrCstNo,
        String cstKnm,
        String copnDvCd,
        String bryyMmdd,
        String bzrno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String cntrMpno,
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno,
        String cntrTno,
        String cntrZip,
        String cntrAdr,
        String istllKnm,
        String istllCralLocaraTno,
        String istllMexnoEncr,
        String istllCralIdvTno,
        String istllMpno,
        String istllLocaraTno,
        String istllExnoEncr,
        String istllIdvTno,
        String istllTno,
        String istllZip,
        String istllAdr,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String prtnrCralLocaraTno,
        String prtnrMexnoEncr,
        String prtnrCralIdvTno,
        String prtnrMpno,
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm
    ) {
        public SearchBlacklistRes {
            cntrMexnoEncr = DbEncUtil.dec(cntrMexnoEncr);
            cntrExnoEncr = DbEncUtil.dec(cntrExnoEncr);
            istllMexnoEncr = DbEncUtil.dec(istllMexnoEncr);
            istllExnoEncr = DbEncUtil.dec(istllExnoEncr);
            prtnrMexnoEncr = DbEncUtil.dec(prtnrMexnoEncr);
        }
    }

    // wells 사업자 가입제한 대상 관리 Search Result Dto
    @ApiModel("WctcSalesLimitDto-SearchEntrpJLmOjRes")
    public record SearchEntrpJLmOjRes(
        String sellLmId,
        String sellLmDv,
        String sellLmBzrno,
        String dlpnrNm,
        String dlgpsNm,
        String bryyMmdd,
        String sellLmRson,
        String sellLmRsonCd,
        String sellLmOcDtm,
        String sellLmRlsDtm,
        String sellLmPsic,
        String sellLmRlsPsic,
        String sellLmPsicNm,
        String sellLmRlsPsicNm
    ) {}
}
