package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctaInstallationShippingDvo {

    /* 설치/
    배송 조회 */
    private String cntrCnfmDtFr; // 계약시작일자 From
    private String cntrCnfmDtTo; // 계약시작일자 To
    private String statDv; // 상태
    private String istPcsvDvCd; // 구분
    private String wkGrpDv; // 작업그룹
    private String kaetc1; // 상품분류
    private String refPdClsfVal; // 상품창조코드
    private String sellTpNm; // 판매구분명
    private String istPcsvTpCd; //설치택배구분
    private String istPcsvTpCdDv;
    private String sellTpCd; // 판매구분코드
    private String sellPrtnrNo; // 판매자사번
    private String ogCd; // 지점코드
    private String dgr3LevlDgPrtnrNm; // 지점장명
    private String dgr3CralLocaraTno; // 지점장전화번호-휴대지역전화번호

    private String istPcsvTpCdDiv; // REQ_SET_DIV
    private String profile; // 프로필

    @DBDecField
    private String egerMexnoEncr; /* 휴대전화번호 암호화 */
    private String egerCralIdvTno; /* 휴대전화 개별번호 */
    private String retrTrgtYn; /* 반품 대상 여부 */
    @DBDecField
    private String dgr3MexnoEncr; // 지점장전화번호 휴대전화국번호암호화
    private String dgr3CralIdvTno; // 지점장전화번호-휴대개별전화번호
    private String cntrRcpFshDt; // 계약접수완료일시
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cntrDtlNo; // 계약상세번호
    private String cstKnm; // 고객명
    private String cttYn; // 컨택여부
    private String cttRsCd; // 컨택결과코드
    private String cttRsNm; // 컨택결과명
    private String basePdCd; // 기준상품코드
    private String pdNm; // 상품명
    private String woPdNm; // 연관전체 상품명
    private String sppDuedt; // 배송예정일자
    private String istDt; // 설치일자
    private String cntrPdStrtdt; // 상품시작일자
    private String pcsvBzsCd; // 택배업체
    private String pcsvBzsNm; // 택배업체명
    private String sppOrdNo; // 설치처정보 - 송장번호
    private String booSellYn; // 예약확정유무
    private String pdHclsfNm; // 대분류명
    private String pdMclsfNm; // 중분류명
    private String istPcsvSellTpCd; // 설치택배구분
    private String istBzsCd; // 설치업체
    private String cntrCanYn; // 취소여부
    private String mnftCoId; // 제조사ID
    private String dpCprcnfAmt; // 입금대사금액
    private String ssSppDuedt; // 삼성전자 - 배송예정일자
    private String ssStockStrDt; // 삼성전자-재고입고일자
    private String ssOrdId; // 삼성주문번호
    private String otscPdYn; // 아웃소싱여부
    private String pkgYn; // 패키지여부
    private String istAdrZip; // 설치처정보 - 우편번호
    private String istRnadr; // 설치처정보 - 기준주소
    private String pdctIdno; // 삼성전자 - 제품고유번호
    private String lcCanyn; /* 취소여부 */
    private String sppQty; /* 배송 횟수 */

    /* 키위 조회 */

    private String rn; /* rownum */
    private String rcpdt; /* 작업일자 */
    private String asIstOjNo; /* 작업순번 */
    private String cnslMoCn; /* 상담메모(엔지니어 전달메모) */
    private String eggrAsnDt; /* 배정일자(엔지니어 배정일자) */
    private String wkAcpteStatCd; /* 작업수락상태 */
    private String wkAcpteDt; /* 작업수락일자 */
    private String wkAcpteHh; /* 작업수락시간 */
    private String vstExpHh; /* 방문확정예약시간 */
    private String vstCnfmdt; /* 방문확정일자 */
    private String vstCnfmHh; /* 방문확정시간 */
    private String wkPrgsStatCd; /* 작업진행상태 */
    private String wkCanMoCn; /* 작업취소메모 */
    private String ogNm; /* 센터명 */
    private String prtnrKnm; /* 엔지니어명 */
    private String egerCrallocaraTno; /* 휴대지역전화번호 */
    private boolean hasKiwiOrd; /* 키위 존재여부 */
    private String egerNm; /* 엔지니어명 */

    /* 취소여부 */
    private String prdDiv; /* 접수구분 */
    private String wkGb; /* 작업구분 */
    private String inChnlDvCd; /* 입력채널구분코드 */
    private String atmtRgsnYn; /* 자동이체 등록여부 */
    private String newAddrZip; /* 설치처정보 - 우편번호 : LCWZP1, LCWZP2 */
    private String rcgvpKnm; /* 수령자 한글명 LCWNAM */

    /* 설치/배송 접수 */
    private String inputGb; /* 입력구분 */
    private String workDt; /* 작업일자 */
    private String acpgDiv; /* 접수구분 */
    private String prtnrNo; /* 사번 */
    private String svBizDclsfCd; /* 서비스업무세분류코드 */
    private String svBizHclsfCd; /* 서비스대분류코드 */

}
