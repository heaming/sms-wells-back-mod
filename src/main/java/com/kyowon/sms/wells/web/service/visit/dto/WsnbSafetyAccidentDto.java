package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WsnbSafetyAccidentDto {

    @ApiModel(value = "WsnbSafetyAccidentDto-SearchReq")
    public record SearchReq(
        String device, /* 접속화면 */
        String vstDt, /* as센터명 */
        String svcCnrId, /* 제품명 */
        String searchType, /* 검색조건 */
        String cstNm, /* 고객명 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String pdNm, /* 상품명 */
        String svCnrOgId,
        String cpsPrgsCd, /* 보상진행 */
        String imptaRsonCd, /* 귀책여부 */
        String totCpsAmt, /* 총보상액 */
        String rgstDtmFrom,
        String rgstDtmTo,
        String fshDtFrom,
        String fshDtTo,
        String fshDtYn /* 완료여부제외여부 */
    ) {}

    @ApiModel(value = "WsnbSafetyAccidentDto-SearchRes")
    public record SearchRes(
        String acdnRcpId,
        String acdnRcpNm,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String acdnDtm,
        String agrDocRcvYn,
        String pdNm,
        String istAdr,
        String istDtlAdr,
        String istReferAdr,
        String cpsPrgsCd,
        String fstRgstUsrId,
        String fshDt,
        String vstDt,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String slDt,
        String fstRgstDtm,
        String rcpMoCn,
        String acdnCausCn,
        String cstDmdCn,
        String acdnRsCn,
        String fnlMdfcDtm,
        String rptrNm,
        String svCnrOgId,
        String brchNm,
        String imptaRsonCd,
        int totCpsAmt,
        int kwCpsAmt,
        int insrcoCpsAmt

    ) {}

    @ApiModel(value = "WsnbSafetyAccidentDto-FindRes")
    public record FindRes(
        String acdnRcpId,
        String acdnRcpNm,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String pdNm,
        String istAdr,
        String istDtlAdr,
        String istReferAdr,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno,
        String slDt,
        String rcpdt,
        String vstDt,
        String svCnrNm,
        String brchNm,
        String cnrldNm,
        String istLctDtlCn,
        String imptaRsonNm,
        String rcpMoCn,
        String acdnCausCn,
        String cstDmdCn,
        //진행상태 컬럼
        String fshDt,
        String cpsPrgsNm,
        String agrDocFwYn,
        int totCpsAmt,
        int kwCpsAmt,
        int insrcoCpsAmt,
        String psicNm,
        String vstIz,
        String damgIz,
        String estIz,
        String agrIz,
        int totRduAmt
    ) {
        public FindRes {
            if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(exnoEncr)
                && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + exnoEncr + "-" + idvTno;
            } else if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + idvTno;
            }
            mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
        }
    }

    @Builder
    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-EditReq")
    public record EditReq(
        @NotBlank
        String acdnRcpId,
        String fshDt,
        String cpsPrgsCd,
        int insrcoCpsAmt,
        String agrDocFwYn,
        int totCpsAmt,
        int kwCpsAmt,
        String psicNm,
        String vstIz,
        String damgIz,
        String estIz,
        String agrIz,
        int totRduAmt
    ) {}

    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-BiztalkReq")
    //    public record BiztalkReq(
    //        String cnrNm, /* as센터명 */
    //        String svCnrTno, /* as센터연락처 */
    //        @NotBlank
    //        String cstTno /* 고객전화번호 */
    //    ) {}
    //
    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-RemoveReceiptIzReq")
    //    public record RemoveReceiptIzReq(
    //        int rcpSn /* 접수일련번호 */
    //    ) {}
    //    @Builder
    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SaveReceiptIzReq")
    //    public record SaveReceiptIzReq(
    //        @NotBlank
    //        String rowState, /* 상태 */
    //        @NotBlank
    //        int rcpSn, /* 접수일련번호 */
    //        int svCnrSn, /* 서비스센터일련번호 */
    //        String pdNm /* 제품명 */
    //    ) {}
    //
    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReceiptBzReq")
    //    public record SearchReceiptBzReq(
    //        String cnrNm /* as센터명 */
    //    ) {}
    //
    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReceiptBzRes")
    //    public record SearchReceiptBzRes(
    //        int svCnrSn,
    //        String svCnrNm, /* 서비스센터명 */
    //        String svCnrLocaraTno, /* 서비스센터지역전화번호 */
    //        String svCnrExnoEncr, /* 서비스센터전화국번호 */
    //        String svCnrIdvTno, /* 서비스센터개별전화번호 */
    //        String svCnrTno, /* 전화번호 tot */
    //        String svCnrZip, /* 서비스센터우편번호 */
    //        String svCnrAdr, /* 서비스센터주소 */
    //        String svCnrDtlAdr, /* 서비스센터상세주소 */
    //        String svCnrIchrPrtnrNm /* 서비스센터담당파트너명 */
    //    ) {
    //        public SearchReceiptBzRes {
    //            if (StringUtil.isNotBlank(svCnrLocaraTno) && StringUtil.isNotBlank(svCnrExnoEncr)
    //                && StringUtil.isNotBlank(svCnrIdvTno)) {
    //                svCnrTno = svCnrLocaraTno + "-" + svCnrExnoEncr + "-" + svCnrIdvTno;
    //            } else if (StringUtil.isNotBlank(svCnrLocaraTno) && StringUtil.isNotBlank(svCnrIdvTno)) {
    //                svCnrTno = svCnrLocaraTno + "-" + svCnrIdvTno;
    //            }
    //        }
    //    }
    //
    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-RemoveReceiptBzReq")
    //    public record RemoveReceiptBzReq(
    //        int svCnrSn /* 서비스센터일련번호 */
    //    ) {}
    //
    //    @Builder
    //    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SaveReceiptBzReq")
    //    public record SaveReceiptBzReq(
    //        @NotBlank
    //        String rowState, /* 상태 */
    //        int svCnrSn, /* 서비스센터일련번호 */
    //        String svCnrNm, /* 서비스센터명 */
    //        String svCnrLocaraTno, /* 서비스센터지역전화번호 */
    //        String svCnrExnoEncr, /* 서비스센터전화국번호 */
    //        String svCnrIdvTno, /* 서비스센터개별전화번호 */
    //        String svCnrTno, /* 서비스센터전화번호Tot */
    //        String svCnrZip, /* 서비스센터우편번호 */
    //        String svCnrAdr, /* 서비스센터주소 */
    //        String svCnrDtlAdr, /* 서비스센터상세주소 */
    //        String svCnrIchrPrtnrNm /* 서비스센터담당파트너명 */
    //    ) {
    //        public SaveReceiptBzReq {
    //            String[] tno = StringUtils.split(svCnrTno, "-");
    //            if (tno.length == 3) {
    //                svCnrLocaraTno = tno[0];
    //                svCnrExnoEncr = tno[1];
    //                svCnrIdvTno = tno[2];
    //            } else if (tno.length == 2) {
    //                svCnrLocaraTno = tno[0];
    //                svCnrIdvTno = tno[1];
    //            }
    //        }
    //    }
}
