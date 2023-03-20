package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncAsTransferDto {

    @ApiModel(value = "WsncAsTransferDto-SearchReq")
    public record SearchReq(
        String svCnrOgId, /* 서비스센터조직ID */
        String ichrPrtnrNo, /* 담당파트너번호 */
        String assignDateFrom, /* 배정일자From */
        String assignDateTo, /* 배정일자To */
        String vstCnfmdt, /* 방문확정일 */
        String svBizHclsfCd /* 작업구분코드 */
    ) {}

    @ApiModel(value = "WsncAsTransferDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* 계약번호 */
        String rcgvpKnm, /* 고객명 */
        String sellTpNm, /* 판매유형 */
        String sapMatCd, /* SAP코드 */
        String basePdCd, /* 품목코드 */
        String pdAbbrNm, /* 상품명 */
        String wkDvNm, /* 작업구분 */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String amtdNm, /* 행정동명 */
        String cnslMoCn, /* 접수내역 */
        String newAdrZip, /* 우편번호 */
        String adr, /* 주소 */
        String alncDvNm, /* 제휴 */
        String dtmChCausNm, /* 변경원인 */
        String dtmChRsonNm, /* 변경사유 */
        String dtmChRsonDtlCn, /* 변경사유상세 */
        String tno, /* 연락처 */
        String mpno, /* 휴대전화번호 */
        String mtrStatNm, /* 작업상태 */
        String wkPrgsDvNm, /* 진행구분 */
        String vstCnfmDtm, /* 방문확정일시 */
        String asnDtm, /* 배정일시 */
        String rcst, /* 접수자 */
        String bfchBlgNm, /* 소속(이관전담당자) */
        String bfchEmpno, /* 사번(이관전담당자) */
        String bfchFnm, /* 성명(이관전담당자) */
        String afchBlgNm, /* 소속(이관후담당자) */
        String afchEmpno, /* 사번(이관후담당자) */
        String afchFnm, /* 성명(이관후담당자) */
        String tfDt, /* 이관일자(이관요청정보) */
        String tfRsonNm, /* 이관사유(이관요청정보) */
        String tfBlgNm, /* 소속(이관요청정보) */
        String tfFnm /* 성명(이관요청정보) */
    ) {}

    @ApiModel(value = "WsncAsTransferDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String svCnrOgId, /* 서비스센터조직ID */
        @NotBlank
        String ichrPrtnrNo, /* 담당파트너번호 */
        String assignDateFrom, /* 배정일자From */
        String assignDateTo, /* 배정일자To */
        String vstCnfmdt, /* 방문확정일 */
        String svBizHclsfCd /* 작업구분코드 */
    ) {}
}
