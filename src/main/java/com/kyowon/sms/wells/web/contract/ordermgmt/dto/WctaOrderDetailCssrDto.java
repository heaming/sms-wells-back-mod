package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDetailCssrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @Builder
    @ApiModel("WctaOrderDetailCssrDto-FindBaseRcpReq")
    public record FindBaseRcpReq(
        String cntrSn,
        String cntrNo,
        String cstNo
    ) {}
    @Builder
    @ApiModel("WctaOrderDetailCssrDto-SaveRcpReq")
    public record SaveRcpReq(
        String afchCssrIsDvCd,
        String afchCssrIsNo,
        String afchCssrTrdDvCd,
        String chRsonCn,
        String cntrNo,
        String cntrSn,
        String cssrIsDvCd,
        String cssrIsNo,
        String cssrTrdDvCd,
        String cstNo,
        String kwGrpCoCd

    ) {}

    @Builder
    @ApiModel("WctaOrderDetailCssrDto-SaveRpblsReq")
    public record SaveRpblsReq(
        String afchCssrIsDvCd,
        String afchCssrIsNo,
        String afchCssrTrdDvCd,
        String chRsonCn,
        String cntrNo,
        String cntrSn,
        String bfchCssrIsNo,
        String cssrIsDvCd,
        String cssrIsNo,
        String cssrTrdDvCd,
        String cstNo,
        String kwGrpCoCd,
        String rveDt,
        String dpDvCd,
        String cssrAgrgSn,
        String cssrDtlSn

    ) {}
    @Builder
    @ApiModel("WctaOrderDetailCssrDto-SearchRcpRes")
    public record SearchRcpRes(
        String kwGrpCoCd, /* 그룹회사코드 */
        String rveDt, /*수납일자*/
        String pdNm, /*상품명*/
        String dpDvCd, /*입금구분코드*/
        String cssrIsDvCd, /* 현금영수증발급구분코드 */
        String cssrAgrgSn, /*현금영수증집계일련번호*/
        String cssrDtlSn, /*현금영수증상세일련번호*/
        String cntr, /*계약상세번호*/
        String cntrNo,
        String cntrSn,
        String cstKnm, /*고객명*/
        String bfchCssrTrdDvCd, /*변경전 공제구분*/
        String bfchCssrIsNo, /*변경전 발행번호*/
        String bfchCssrTrdAmt, /*변경전 승인금액*/
        String bfchCssrAprRsCd, /*승인결과*/
        String fstRgstDtm, /*등록일*/
        String fstRgstUsrId, /*등록자*/
        /*변경후*/
        String afchCssrIsDvCd, /* 현금영수증발급구분코드 */
        String afchCssrTrdDvCd, /*공제구분*/
        String afchCssrIsNo, /*발행번호*/
        String afchCssrTrdAmt, /*승인금액*/
        String afchCssrAprno, /*승인번호*/
        String afchCssrAprRsCd, /*승인결과*/
        String chRsonCn, /*변경사유*/
        String fnlMdfcDtm, /*등록일*/
        String fnlMdfcUsrId, /* 등록자 사번*/
        String fnlMdfcUsrNm /* 등록자 이름 */
    ) {}
    // 기본정보 FindRes
    @Builder
    @ApiModel("WctaOrderDetailCssrDto-FindBaseRcpRes")
    public record FindBaseRcpRes(
        String cssrIsDvCd, /* 현금영수증발급구분코드 */
        String cssrIsNo, /* 현금영수증발급번호 */
        String chRsonCn, /* 마지막 변경사유 */
        String cntrSn,
        String cntrNo,
        String kwGrpCoCd,
        String cstNo
    ) {}
}
