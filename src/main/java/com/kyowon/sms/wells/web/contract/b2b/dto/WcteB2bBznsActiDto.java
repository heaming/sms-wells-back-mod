package com.kyowon.sms.wells.web.contract.b2b.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WcteB2bBznsActiDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WcteB2bBznsActiDto-SaveReq")
    public record SearchReq(
        String srchGbn,
        String srchDateGbn,
        @ValidDate
        String strtdt,
        @ValidDate
        String enddt,
        String bzrno,
        String leadCstNm,
        String prjNm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WcteB2bBznsActiDto-SearchRes")
    public record SearchRes(
        String leadCstId,
        String leadCstRlpplId,
        String prjNm,
        String opptId,
        String fstRgstDt,
        String dgr3LevlOgCd,
        String opptIchrPrtnrNo,
        String prtnrKnm,
        String prjFomCd,
        String bzrno,
        String leadCstNm,
        String leadCstRlpplNm,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String emadrCn,
        String crdrVal,
        String etBiddDt,
        String opptCntrFomCd,
        String totQty,
        String wrfrQty,
        String arcleQty,
        String bdtQty,
        String etcQty,
        String etRcvodQty,
        String etRcvodRat,
        String rcvodDt,
        String lossDt,
        String etCntrStrtdt,
        String maxStplPtrm,
        String biddBzsNm,
        String unuitmCn,
        String fnlMdfcDt,
        String ogTpCd
    ) {}
    @ApiModel("WcteB2bBznsActiDto-SearchKeyManRes")
    public record SearchKeyManReq(
        String bzrno,
        String leadCstNm
    ) {}
    @ApiModel("WcteB2bBznsActiDto-SearchKeyManRes")
    public record SearchKeyManRes(
        String leadCstRlpplNm,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String emadrCn
    ) {}
    @ApiModel("WcteB2bBznsActiDto-SaveReq")
    public record SaveReq(
        String rowState,
        String leadCstId,
        String leadCstRlpplId,
        String prjNm,
        String opptId,
        String fstRgstDt,
        String dgr3LevlOgCd,
        String opptIchrPrtnrNo,
        String prtnrKnm,
        String prjFomCd,
        String bzrno,
        String leadCstNm,
        String leadCstRlpplNm,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String emadrCn,
        String crdrVal,
        String etBiddDt,
        String opptCntrFomCd,
        String totQty,
        String wrfrQty,
        String arcleQty,
        String bdtQty,
        String etcQty,
        String etRcvodQty,
        String etRcvodRat,
        String rcvodDt,
        String lossDt,
        String etCntrStrtdt,
        String maxStplPtrm,
        String biddBzsNm,
        String unuitmCn,
        String fnlMdfcDt,
        String ogTpCd
    ) {
        public SaveReq {
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.enc(exnoEncr) : exnoEncr; // 암호화
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr; // 암호화
        }
    }
    @ApiModel("WcteB2bBznsActiDto-SearchDetailRes")
    public record SearchDetailRes(
        String opptId,
        String opptSn,
        String pdClsfNm,
        String pdNm,
        String basePdCd,
        String pdQty,
        String fnlAmt,
        String unuitmCn

    ) {}
    @ApiModel("WcteB2bBznsActiDto-SaveDetailReq")
    public record SaveDetailReq(
        @NotBlank
        String rowState,
        @NotBlank
        String opptId,
        String opptSn,
        String pdClsfNm,
        String pdNm,
        String basePdCd,
        String pdQty,
        String fnlAmt,
        String unuitmCn
    ) {}
}
