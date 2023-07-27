package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractCstRelDvo {
    private String cntrCstRelId; /* 계약고객관계ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String cntrNo; /* 계약번호 */
    private String dtlCntrNo; /* 상세계약번호 */
    private Integer dtlCntrSn; /* 상세계약일련번호 */
    private String copnDvCd; /* 법인격구분코드 */
    private String cstNo; /* 고객번호 */
    private String cntrCstRelTpCd; /* 계약고객관계유형코드 */
    private String cntrtRelCd; /* 계약자관계코드 */
    private String insiCstTpCd; /* 내부고객유형코드 */
    private String lrnnGryCd; /* 학습학년코드 */
    private String cntrChRcpId; /* 계약변경접수ID */
    private String dtaDlYn; /* 데이터삭제여부 */

    /* STEP1 */
    private String cikVal;
    private String sfkVal;
    private String hddmInf; /* 백점이회원정보 */
    private String fmlRelDvCd;
    private String fmlRelDvNm;
    private String cstKnm;
    private String sexDvCd;
    private String sexDvNm;
    private String bryyMmdd;
    private String bzrno;
    private String cralLocaraTno;
    @DBEncField
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String locaraTno;
    @DBEncField
    @DBDecField
    private String exnoEncr;
    private String idvTno;
    private String adrId;
    private String zip;
    private String adr;
    private String adrDtl;
    private int age;
    private String cntrTpCd;
    private String cntrTpNm;
    private List<GryCdDvo> gryCds;
    private List<String> thmCntrs;

    @Getter
    @Setter
    public static class GryCdDvo {
        String codeId;
        String codeName;
    }
}
