package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
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
    @DBDecField
    @DBEncField
    private String mexnoEncr; // 휴대전화번호2
    private String cralIdvTno; // 휴대전화번호3
    private String outputDiv; // 출력구분
    private String sellOgTpCd;// 조직유형코드

    private String rowState;
    private int dataRow;

    // save data
    //private String cntrNo;
    //private String cntrSn;
    private String cntrCfmn;
    private String assignCan;
    private String istReRegn;
    private String cstKnm;
    private String bryyMmdd;
    private String bzrno;
    private String cntrCstNo;
    //private String rcgvpKnm;
    private String cntrCnfmDt;
    private String sellTpCd;
    private String sellTpNm;
    private String coCd;
    private String ogCd;
    private String coIstMngtDvCd;
    private String coIstMngtDvNm;
    //private String basePdCd;
    //private String pdNm;
    private String pkgPdCd;
    private String pkgPdNm;
    private String svPrd;
    private String svPdTpCd;
    private String svTpNm;
    private String pdGdCd;
    //private String coIstDvCd;
    private String coIstDvNm;
    //private String coIstUswyCd;
    private String coIstUswyNm;
    private String istPlcTpCd;
    private String frisuBfsvcPtrmN;
    private String frisuAsPtrmN;

    private String sppDuedt;
    private String istDt;
    private String rtnDt;
    private String istAkArtcMoCn;
    private String cttRsCd;
    private String cttPsicId;
    private String sconCn;
    private String sellTpDtlCd;
    private String sellTpDtlNm;
    private String cntrNo216;
    private String cntrSn216;
    private String reguDelYn;
    private String memExpGbn;
    private String fstRgstDt;
    private String fstRgstTm;
    private String fstRgstUsrId;
    private String fstRgstUsrNm;
    private String fnlMdfcDt;
    private String fnlMdfcTm;
    private String fnlMdfcUsrId;
    private String fnlMdfcUsrNm;
    private String copnDvCd;
    private String mpno;
    //private String cralLocaraTno;
    //private String mexnoEncr;
    //private String cralIdvTn;
    private String zip;
    private String basAdr;
    private String dtlAdr;

    private String installMpno;
    private String installCralLocaraTno;
    @DBDecField
    private String installMexnoEncr;
    private String installCralIdvTno;
    private String installZip;
    private String installBasAdr;
    private String installDtlAdr;

    private String cntrCnfmTm;
    private String cntrRcpDt;
    private String cntrRcpTm;
    private String fnlAmt;

    private String gender;
    private String telNoYn;
    private String telNo;
    private String istCopnDvCd;
    private String istBzrno;
    private String installTelNoYn;
    private String installTelNo;

    private String installLocaraTno;
    @DBDecField
    private String installExnoEncr;
    private String installIdvTno;

    /// 삭제
    // private String wpDvNm;
    //private String filterExp;
    //private String dscRate;
    //private String cpsDt;
}
