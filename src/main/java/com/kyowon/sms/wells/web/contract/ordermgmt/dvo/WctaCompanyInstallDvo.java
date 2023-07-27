package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaCompanyInstallDvo {
    //request param
    private String cntrCnfmDtFrom; // 접수일FROM
    private String cntrCnfmDtTo; // 접수일TO
    private String insDtFrom; // 설치일FROM
    private String insDtTo; // 설치일TO
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String pdHclsfId; // 상품대분류
    private String pdMclsfId; // 상품중분류
    private String basePdCd; // 싱픔코드
    private String pdNm; // 상품명
    private String rcgvpKnm; // 설치자명
    private String coIstDvCd; // 설치구분
    private String coIstUswyCd; // 설치용도
    private String cralLocaraTno; // 휴대전화번호1
    @DBEncField
    private String mexnoEncr; // 휴대전화번호2
    private String cralIdvTno; // 휴대전화번호3
    private String outputDiv; // 출력구분
    private String sellOgTpCd;// 조직유형코드
}
