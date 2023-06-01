package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsncOutsourcedpdAsReceiptDto {

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReceiptIzReq")
    public record SearchReceiptIzReq(
        String cnrNm, /* as센터명 */
        String pdNm /* 제품명 */
    ) {}

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReceiptIzRes")
    public record SearchReceiptIzRes(
        int rcpSn, /* 접수일련번호 */
        String entrpDvCd, /* 사업자구분코드 */
        int svCnrSn, /* 서비스센터일련번호 */
        String pdNm, /* 제품명 */
        String svCnrNm, /* 서비스센터명 */
        String svCnrLocaraTno, /* 서비스센터지역전화번호 */
        String svCnrExnoEncr, /* 서비스센터전화국번호 */
        String svCnrIdvTno, /* 서비스센터개별전화번호 */
        String svCnrTno, /* 전화번호 tot */
        String svCnrAdr, /* 서비스센터주소 */
        String svCnrDtlAdr, /* 서비스센터상세주소 */
        String svCnrIchrPrtnrNm /* 서비스센터담당파트너명 */
    ) {
        public SearchReceiptIzRes {
            if (StringUtil.isNotBlank(svCnrLocaraTno) && StringUtil.isNotBlank(svCnrExnoEncr)
                && StringUtil.isNotBlank(svCnrIdvTno)) {
                svCnrTno = svCnrLocaraTno + "-" + svCnrExnoEncr + "-" + svCnrIdvTno;
            } else if (StringUtil.isNotBlank(svCnrLocaraTno) && StringUtil.isNotBlank(svCnrIdvTno)) {
                svCnrTno = svCnrLocaraTno + "-" + svCnrIdvTno;
            }
        }
    }

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-BiztalkReq")
    public record BiztalkReq(
        String cnrNm, /* as센터명 */
        String svCnrTno, /* as센터연락처 */
        @NotBlank
        String cstTno /* 고객전화번호 */
    ) {}

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-RemoveReceiptIzReq")
    public record RemoveReceiptIzReq(
        int rcpSn /* 접수일련번호 */
    ) {}
    @Builder
    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SaveReceiptIzReq")
    public record SaveReceiptIzReq(
        @NotBlank
        String rowState, /* 상태 */
        @NotBlank
        int rcpSn, /* 접수일련번호 */
        int svCnrSn, /* 서비스센터일련번호 */
        String pdNm /* 제품명 */
    ) {}

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReceiptBzReq")
    public record SearchReceiptBzReq(
        String cnrNm /* as센터명 */
    ) {}

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SearchReceiptBzRes")
    public record SearchReceiptBzRes(
        int svCnrSn,
        String svCnrNm, /* 서비스센터명 */
        String svCnrLocaraTno, /* 서비스센터지역전화번호 */
        String svCnrExnoEncr, /* 서비스센터전화국번호 */
        String svCnrIdvTno, /* 서비스센터개별전화번호 */
        String svCnrTno, /* 전화번호 tot */
        String svCnrZip, /* 서비스센터우편번호 */
        String svCnrAdr, /* 서비스센터주소 */
        String svCnrDtlAdr, /* 서비스센터상세주소 */
        String svCnrIchrPrtnrNm /* 서비스센터담당파트너명 */
    ) {
        public SearchReceiptBzRes {
            if (StringUtil.isNotBlank(svCnrLocaraTno) && StringUtil.isNotBlank(svCnrExnoEncr)
                && StringUtil.isNotBlank(svCnrIdvTno)) {
                svCnrTno = svCnrLocaraTno + "-" + svCnrExnoEncr + "-" + svCnrIdvTno;
            } else if (StringUtil.isNotBlank(svCnrLocaraTno) && StringUtil.isNotBlank(svCnrIdvTno)) {
                svCnrTno = svCnrLocaraTno + "-" + svCnrIdvTno;
            }
        }
    }

    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-RemoveReceiptBzReq")
    public record RemoveReceiptBzReq(
        int svCnrSn /* 서비스센터일련번호 */
    ) {}

    @Builder
    @ApiModel(value = "WsncOutsourcedpdAsReceiptDto-SaveReceiptBzReq")
    public record SaveReceiptBzReq(
        @NotBlank
        String rowState, /* 상태 */
        int svCnrSn, /* 서비스센터일련번호 */
        String svCnrNm, /* 서비스센터명 */
        String svCnrLocaraTno, /* 서비스센터지역전화번호 */
        String svCnrExnoEncr, /* 서비스센터전화국번호 */
        String svCnrIdvTno, /* 서비스센터개별전화번호 */
        String svCnrTno, /* 서비스센터전화번호Tot */
        String svCnrZip, /* 서비스센터우편번호 */
        String svCnrAdr, /* 서비스센터주소 */
        String svCnrDtlAdr, /* 서비스센터상세주소 */
        String svCnrIchrPrtnrNm /* 서비스센터담당파트너명 */
    ) {
        public SaveReceiptBzReq {
            String[] tno = StringUtils.split(svCnrTno, "-");
            if (tno.length == 3) {
                svCnrLocaraTno = tno[0];
                svCnrExnoEncr = tno[1];
                svCnrIdvTno = tno[2];
            } else if (tno.length == 2) {
                svCnrLocaraTno = tno[0];
                svCnrIdvTno = tno[1];
            }
        }
    }
}
