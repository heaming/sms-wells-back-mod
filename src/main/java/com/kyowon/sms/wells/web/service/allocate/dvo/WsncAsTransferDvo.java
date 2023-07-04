package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncAsTransferDvo {

    String cstSvAsnNo; /* 고객서비스배정번호 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String rcgvpKnm; /* 고객명 */
    String sellTpNm; /* 판매유형 */
    String sapMatCd; /* SAP코드 */
    String basePdCd; /* 품목코드 */
    String pdAbbrNm; /* 상품명 */
    String wkDvNm; /* 작업구분 */
    String ctpvNm; /* 시도명 */
    String ctctyNm; /* 시군구명 */
    String amtdNm; /* 행정동명 */
    String cnslMoCn; /* 접수내역 */
    String newAdrZip; /* 우편번호 */
    String adr; /* 주소 */
    String alncDvNm; /* 제휴 */
    String dtmChCausNm; /* 변경원인 */
    String dtmChRsonNm; /* 변경사유 */
    String dtmChRsonDtlCn; /* 변경사유상세 */
    String locaraTno; /* 연락처 */
    @DBEncField
    @DBDecField
    String exnoEncr;
    String idvTno;
    String cralLocaraTno; /* 휴대전화번호 */
    @DBEncField
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String wkPrgsDvNm; /* 진행구분 */
    String vstCnfmDtm; /* 방문확정일시 */
    String asnDtm; /* 배정일시 */
    String rcst; /* 접수자 */
    String bfchBlgNm; /* 소속(이관전담당자) */
    String bfchEmpno; /* 사번(이관전담당자) */
    String bfchFnm; /* 성명(이관전담당자) */
    String afchBlgCdOrigin; /* 소속ID(이관후담당자)origin */
    String afchOgTpCdOrigin;
    String afchEmpnoOrigin; /* 소속명(이관후담당자)origin */
    String afchFnmOrigin;
    String afchBlgCd; /* 소속ID(이관후담당자) */
    String afchOgTpCd;
    String afchEmpno; /* 사번(이관후담당자) */
    String afchFnm;
    String tfDt; /* 이관일자(이관요청정보) */
    String tfRsonNm; /* 이관사유(이관요청정보) */
    String tfBlgNm; /* 소속(이관요청정보) */
    String tfFnm; /* 성명(이관요청정보) */
    String sppOrdNo; /* 배송주문번호 */
    String sppPlanSn; /* 배송계획일련번호 */

}
