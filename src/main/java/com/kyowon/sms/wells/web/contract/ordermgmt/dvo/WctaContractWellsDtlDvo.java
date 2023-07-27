package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractWellsDtlDvo {
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String frisuBfsvcPtrmUnitCd; /* 무상BS기간단위코드 */
    private Long frisuBfsvcPtrmN; /* 무상BS기간수 */
    private String frisuAsPtrmUnitCd; /* 무상AS기간단위코드 */
    private Integer frisuAsPtrmN; /* 무상AS기간수 */
    private String istDt; /* 설치일자 */
    private String reqdDt; /* 철거일자 */
    private String cpsDt; /* 보상일자 */
    private String prmApyDvCd; /* 선납적용구분코드 */
    private Integer prmPtrmMcn; /* 선납기간개월수 */
    private String sellEvCd; /* 판매행사코드 */
    private String bfsvcBzsDvCd; /* BS업체구분코드 */
    private String splyBzsDvCd; /* 조달업체구분코드 */
    private String ocoCpsBzsDvCd; /* 타사보상업체구분코드 */
    private String hcrDvCd; /* 홈케어구분코드 */
    private Integer fmmbN; /* 가구원수 */
    private String frisuRcvryTpCd; /* 무상복구유형코드 */
    private String istPlcTpCd; /* 설치장소유형코드 */
    private String wrfrIstMthCd; /* 정수기설치방법코드 */
    private String wtqltyTstYn; /* 수질검사여부 */
    private String srcwtTpCd; /* 상수원유형코드 */
    private String wprsItstTpCd; /* 수압강도유형코드 */
    private String useElectTpCd; /* 사용전력유형코드 */
    private String tbhsEyn; /* 튜빙호수유무 */
    private String stvlvEyn; /* 지수전유무 */
    private String kumonItrdtDvCd; /* 구몬소개구분코드 */
    private String kumonCstIdkVal; /* 구몬고객식별키값 */
    private String kumonRcomrIdkVal; /* 구몬소개자식별키값 */
    private String otsdLkDrmVal; /* 외부연계식별값 */
    private String frisuMshCrtYn; /* 무상멤버십생성여부 */
    private String istMmBilMthdTpCd; /* 설치월청구방식유형코드 */
    private String coIstDvCd; /* 회사설치구분코드 */
    private String coIstMngtDvCd; /* 회사설치관리구분코드 */
    private String coIstUswyCd; /* 회사설치용도코드 */
    private String istAkArtcMoCn; /* 설치요청사항메모내용 */
    private String sconCn; /* 특약내용 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
