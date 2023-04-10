package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class WsnzWellsCodeDto {
    @ApiModel(value = "WsnzWellsCodeDto-SearchWorkingEngineersReq")
    public record SearchWorkingEngineersReq() {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchWorkingEngineersRes")
    public record SearchWorkingEngineersRes(
        String codeId,
        String codeNm,
        String codeNm1,
        String codeNm2,
        String cphonIdvTno,
        String ogCd,
        String ogNm,
        String empDvVal,
        String empRsbDvVal,
        String empTpVal,
        String entcoDt,
        String rsgnDt,
        String empRolDvVal,
        String empDvValNm,
        String empRolDvValNm,
        String hgrDeptCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchWareHouseReq")
    public record SearchWareHouseReq(
        String startYm,
        String endYm,
        String wareDvCd,
        String wareIchrNo,
        String hgrWareNo
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchWareHouseRes")
    public record SearchWareHouseRes(
        String wareNo,
        String wareNm
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchMonthStockReq")
    public record SearchMonthStockReq(
        String wareMngtPrtnrNo,
        String apyYm
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchMonthStockRes")
    public record SearchMonthStockRes(
        String codeId,
        String codeName,
        String wareMngtPrtnrNo,
        String wareIchrNo,
        String wareDvCd,
        String hgrWareNo,
        String apyYm,
        String parentWarNm,
        String parentWareMngtPrtnrNo,
        String parentWareLocaraCd,
        String parentWareDvCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchServiceCenterOrgsReq")
    public record SearchServiceCenterOrgsReq() {}
    @ApiModel(value = "WsnzWellsCodeDto-SearchServiceCenterOrgsRes")
    public record SearchServiceCenterOrgsRes(
        String bldCd,
        String cloDt,
        String cloYn,
        String cnrLocaraDvCd,
        String cpsnDtrcOgId,
        String dgPrtnrNo,
        String dtrcSbrncOgId,
        String hgrOgId,
        String mngtWidaDvCd,
        String ogAbbrNm,
        String ogCd,
        String ogClsfCd,
        String ogId,
        String ogLevlDvCd,
        String ogNm,
        String ogTpCd,
        String opDt,
        String psicNm,
        String sbrncDvCd,
        String sbrncYn,
        String tempSaveYn,
        String vdtcrPdDvCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchAllEngineersReq")
    public record SearchAllEngineersReq(
        String hgrDeptCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchAllEngineersRes")
    public record SearchAllEngineersRes(
        String codeId,
        String codeNm,
        String codeNm1,
        String codeNm2,
        String cphonIdvTno,
        String ogCd,
        String ogNm,
        String empDvVal,
        String empRsbDvVal,
        String empTpVal,
        String entcoDt,
        String rsgnDt,
        String empRolDvVal,
        String empDvValNm,
        String empRolDvValNm,
        String hgrDeptCd,
        String ogTpCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchPartMasterReq")
    public record SearchPartMasterReq(
        String pdTpCd,
        String sellIndate,
        String itemKnd,
        String itemGr,
        String sellTpCd,
        String pdClsfId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String partCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchPartMasterRes")
    public record SearchPartMasterRes(
        String itemCd,
        String partCd,
        String nmKor,
        String nmAbbr1,
        String nmCust,
        String itemKnd,
        String itemKndNm,
        String itemGr,
        String itemGrNm,
        String pdClsfId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String sellTpCd,
        String useYn,
        String apldFr,
        String apldTo,
        String sapCd,
        String sapGrp,
        String sapLevel,
        String sapClass,
        String pdctClssRsc,
        String stdPr,
        String imgUrl,
        String desc1,
        String valCommGb,
        String commGbNm,
        String valLpGb,
        String valMgtTyp,
        String mgtTypNm,
        String valSftyQty,
        String valSize,
        String valMgtUnt,
        String mgtUntNm,
        String valLt,
        String valDelUnt,
        String delUntNm,
        String valBoxQty,
        String valPltQty,
        String valBarCd,
        String valBaseGb,
        String valBaseColorGb,
        String valRefurYn,
        String valQtylmtYn,
        String valDisposalCost,
        String valTotWei,
        String valPltWei,
        String valMoq,
        String valTurnoverTgt,
        String cd,
        String cdNm,
        String codeId,
        String codeName,
        String pdTpCd,
        String sellStrtdt,
        String sellEnddt
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchServiceCenterReq")
    public record SearchServiceCenterReq(
        String ogId,
        String ogCd,
        String hgrOgId,
        String ogNm
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchDistrictsReq")
    public record SearchDistrictsReq(
        String fr2pLgldCd,
        String ctctyNm,
        String lawcEmdNm,
        String searchType
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchProductBaseReq")
    public record SearchProductBaseReq(
        String newAdrZip,
        String emdSn,
        String ctpvNm,
        String ctctyNm,
        String lawcEmdNm,
        String amtdNm,
        String pdlvNo
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchMcbyCstSvOjIzReq")
    public record SearchMcbyCstSvOjIzReq(
        String mngtYm,
        String pdGdCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchServiceCentersRes")
    public record SearchServiceCentersRes(
        String ogCd,
        String ogNm,
        String mngtWidaDvCd,
        String cnrLocaraDvCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchDistrictsRes")
    public record SearchDistrictsRes(
        String newAdrZip,
        int emdSn,
        String fr2pLgldCd,
        String ctpvNm,
        String ctctyNm,
        String lawcEmdNm,
        String amtdNm,
        String kynorLocaraYn,
        String ildYn,
        String pdlvNo,
        String ctctyCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchProductBaseRes")
    public record SearchProductBaseRes(
        String newAdrZip,
        String emdSn,
        String ctpvNm,
        String ctctyNm,
        String lawcEmdNm,
        String amtdNm,
        String pdlvNo
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchMcbyCstSvOjIzRes")
    public record SearchMcbyCstSvOjIzRes(
        String pdCd, /*상품코드*/
        String pdNm /*상품명*/
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchLgldCtpvLocarasReq")
    public record SearchLgldCtpvLocarasReq() {}
    @ApiModel(value = "WsnzWellsCodeDto-SearchLgldCtpvLocarasRes")
    public record SearchLgldCtpvLocarasRes(
        String amtdNm,
        String apyEnddt,
        String apyStrtdt,
        String ctctyNm,
        String ctpvNm,
        String fr2pLgldCd,
        String lawcEmdNm,
        long locaraSn,
        String rpbLocaraCd
    ) {}

    @ApiModel(value = "WsnzWellsCodeDto-SearchWarehouseCLReq")
    public record SearchWarehouseCLReq(
        String apyYm,
        String wareNo
    ) {}
}
