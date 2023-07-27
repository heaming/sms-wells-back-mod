package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractCreateDvo {
    // 매핑완료
    private String cntrNo;
    private String cntrSn;
    private String cntrCnfmDtm;

    private String cntrCstNo;
    private String cntrCstKnm;

    // 계약자 연락처 & 주소
    private String cntrtAdrId;
    private String cntrtAdrDvCd;
    private String cntrtZip;
    private String cntrtBasAdr;
    private String cntrtDtlAdr;
    private String cntrtLocaraTno;
    private String cntrtExnoEncr;
    private String cntrtIdvTno;
    private String cntrtCralLocaraTno;
    private String cntrtMexnoEncr;
    private String cntrtCralIdvTno;
    private String cntrtEmadr;

    // 설치자 연락처 & 주소
    private String istAdrId;
    private String istllKnm;
    private String istAdrDvCd;
    private String istZip;
    private String istBasAdr;
    private String istDtlAdr;
    private String istLocaraTno;
    private String istExnoEncr;
    private String istIdvTno;
    private String istCralLocaraTno;
    private String istMexnoEncr;
    private String istCralIdvTno;

    // 구매상품
    private String cntrPdRelId;
    private String basePdCd;
    private String pdQty;
    private String fnlAmt;
    private String cntrTam;
    private String sellAmt;
    private String vat;
    private String svPdTpCd;

    // 개인정보동의
    private String cstAgId;
    private String pifThpOfrAgYn;
    private String thpAgYn;
    private String mktgPurpAgYn;
    private String pifCinfThpOfrAgYn;
    private String pifBizFstrAgYn;

    // 결제정보
    private String cntrStlmId;
    private String cntrStlmRelId;
    private String dpDvCd;
    private String stlmAmt;
    private String cdcoCd;
    private String crcdNo;
    private String crcdOwrKnm;
    private String cntrtBryyMmdd;
    private String cardExpdtYm;
    private String istmMcn;
    private String rveDvCd;
    private String bnkCd;
    private String acno;
    private String owrKnm;

    // 판매자정보
    private String cntrPrtnrRelId;
    private String sellOgTpCd;
    private String sellPrtnrNo;

    // 기타
    private String sellInflwChnlDtlCd;
    private String cntrNatCd;
    private String cntrPrgsStatCd;
    private String prrRcpCntrYn;
    private String cntrDtlStatCd;
    private String cntrwTpCd;

    private String cntrTpCd;
    private String cstStlmInMthCd;
    private String crncyDvCd;

    private String cntrCstRelId;
    private String cntrPrcCmptId;
    private String cntrAdrpcId;

    private String coCd;
    private String copnDvCd;
    private String bzrNo;

    private String sellEvCd;
    private String svPrd;
    private String sppDuedt;
    private String frisuBfsvcPtrmN;
    private String istAkArtcMoCn;
    private String sellDscDvCd;
    private String sellDscTpCd;
}
