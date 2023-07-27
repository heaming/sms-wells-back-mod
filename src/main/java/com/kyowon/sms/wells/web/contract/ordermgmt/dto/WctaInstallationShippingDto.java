package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaInstallationShippingDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 설치/배송 조회 SearchIstShipping Request Dto
    @Builder
    @ApiModel("WctaInstallationShippingDto-SearchIstShippingReq")
    public record SearchIstShippingReq(
        @NotBlank
        @ValidDate
        String cntrCnfmDtFr, // 계약시작일자 From
        @NotBlank
        @ValidDate
        String cntrCnfmDtTo, // 계약시작일자 To
        String cstKnm, // 계약자명
        String istPcsvDvCd, // 구분
        String statDv, // 상태
        String cntrDtlNo, // 계약상세번호
        String cntrSn, // 계약일련번호
        String cntrNo, // 계약번호
        String otscPdYn, // 아웃소싱여부
        String wkGrpDv // 작업그룹
    ) {}
    @Builder
    @ApiModel("WctaInstallationShippingDto-SaveAssignProcessingReq")
    public record SaveAssignProcessingReq(
        @NotBlank
        String cntrNo, // 계약시작일자 From
        @NotBlank
        String cntrSn, // 계약시작일자 To
        String prtnrNo, // 사번
        String inputGb, // 입력구분
        String wkGb, // 작업구분
        @ValidDate
        String workDt, // 계약상세번호
        String asIstOjNo, // 작업순번
        String acpgDiv, // 접수구분
        String basePdCd, // 상품코드
        String istPcsvDvCd, // 설치택배구분
        String mnftCoId, // 제조사
        String prdDiv, // 접수구분
        String svBizDclsfCd, // 서비스업무세분류코드
        String svBizHclsfCd, // 입력채널구분코드
        String inChnlDvCd // 서비스세분류코드

    ) {}

    @Builder
    @ApiModel("WctaInstallationShippingDto-EditDueDateReq")
    public record EditDueDateReq(
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn, // 계약일련번호

        @ValidDate
        String sppDuedt // 배송예정

    ) {}
    @Builder
    @ApiModel("WctaInstallationShippingDto-EditDueDateCancelReq")
    public record EditDueDateCancelReq(
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn // 계약일련번호

    ) {}

    // 설치/배송 조회 SearchAssignProcessing Request Dto
    @ApiModel("WctaInstallationShippingDto-SearchAssignProcessingReq")
    public record SearchAssignProcessingReq(
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn, // 계약일련번호
        String prtnrNo, // 사번
        String inputGb, // 입력구분
        String wkGb, // 작업구분

        @NotBlank
        @ValidDate
        String workDt, // 작업일자
        @NotBlank
        String asIstOjNo, // 작업순번
        String acpgDiv, // 구분
        String basePdCd, // 상품코드
        String istPcsvDvCd,
        String mnftCoId, // 제조사(LCJEJO)
        String svBizDclsfCd, // 서비스업무세분류코드
        String prdDiv // 접수구분
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************

    // 설치/배송 조회 Search Result Dto
    @ApiModel("WctaInstallationShippingDto-SearchIstShippingRes")
    public record SearchIstShippingRes(
        String kaetc1, // 상품분류
        String refPdClsfVal, // 상품창조코드
        String sellTpNm, // 판매구분명
        String istPcsvTpCd, //설치택배구분
        String istPcsvTpCdDv, //설치택배구분
        String sellTpCd, // 판매구분코드
        String sellPrtnrNo, // 판매자사번
        String ogCd, // 지점코드
        String dgr3LevlDgPrtnrNm, // 지점장명
        String dgr3CralLocaraTno, // 지점장전화번호-휴대지역전화번호
        String dgr3MexnoEncr, // 지점장전화번호 휴대전화국번호암호화
        String dgr3CralIdvTno, // 지점장전화번호-휴대개별전화번호
        String cntrRcpFshDt, // 계약접수완료일시
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String cstKnm, // 고객명
        String cttYn, // 컨택여부
        String cttRsCd, // 컨택결과코드
        String cttRsNm, // 컨택결과명
        String basePdCd, // 기준상품코드
        String pdNm, // 상품명
        String woPdNm, // 연관전체 상품명
        String sppDuedt, // 배송예정일자
        String istDt, // 설치일자
        String cntrPdStrtdt, // 상품시작일자
        String pcsvBzsCd, // 택배업체
        String pcsvBzsNm, // 택배업체명
        String sppOrdNo, // 설치처정보 - 송장번호
        String booSellYn, // 예약확정유무
        String inChnlDvCd, // 입력채널구분코드

        String profile, // 프로필
        String pdHclsfNm, // 대분류명
        String pdMclsfNm, // 중분류명
        String istPcsvSellTpCd, // 설치택배구분
        String istBzsCd, // 설치업체
        String cntrCanYn, // 취소여부
        String mnftCoId, // 제조사ID
        String dpCprcnfAmt, // 입금대사금액
        String ssSppDuedt, // 삼성전자 - 배송예정일자
        String ssStockStrDt, // 삼성전자-재고입고일자
        String ssOrdId, // 삼성주문번호
        String otscPdYn, // 아웃소싱여부
        String pkgYn, // 패키지여부
        String istAdrZip, // 설치처정보 - 우편번호
        String istRnadr, // 설치처정보 - 기준주소
        String pdctIdno, // 삼성전자 - 제품고유번호
        String lcCanyn, /* 취소여부 */
        String sppQty, /* 배송횟수 */

        String rn, /* rownum */
        String rcpdt, /* 작업일자 */
        String asIstOjNo, /* 작업순번 */
        String cnslMoCn, /* 상담메모(엔지니어 전달메모) */
        String egerAsnDt, /* 배정일자(엔지니어 배정일자) */
        String wkAcpteStatCd, /* 작업수락상태 */
        String wkAcpteDt, /* 작업수락일자 */
        String wkAcpteHh, /* 작업수락시간 */
        String vstExpHh, /* 방문확정예약시간 */
        String vstCnfmdt, /* 방문확정일자 */
        String vstCnfmHh, /* 방문확정시간 */
        String wkPrgsStatCd, /* 작업진행상태 */
        String wkCanMoCn, /* 작업취소메모 */
        String ogNm, /* 센터명 */
        String prtnrKnm, /* 엔지니어명 */
        String egerCrallocaraTno, /* 휴대지역전화번호 */
        String egerMexnoEncr, /* 휴대전화번호 암호화 */
        String egerCralIdvTno, /* 휴대전화개별번호 */
        String retrTrgtYn, /* 반품 대상 여부 */
        String wkGrpDv, /* 작업 그룹 */
        String svBizHclsfCd, /* 서비스대분류코드 */
        boolean hasKiwiOrd, /* 키위 존재여부 */
        String egerNm, /* 엔지니어명 */
        String svBizDclsfCd // 서비스업무세분류코드

    ) {}

    @Builder
    @ApiModel("WctaInstallationShippingDto-SaveAssignProcessingRes")
    public record SaveAssignProcessingRes(

        String asAkId, /* AS요청ID */

        String sysDvCd, /* 시스템 구분코드 */

        String asIstOjNo, /* AS설치대상번호 */

        String mtrStatCd, /* 자료상태코드 */

        String mtrStatCdNm, /* 자료상태코드명 */

        String cntrNo, /* 계약번호 */

        String vstRqdt, /* 방문요청시간 */

        String vstAkHh, /* 방문요청일자 */
        String rcpIchrPrtnrNo /* 접수담당파트너번호 */

    ) {}
}
