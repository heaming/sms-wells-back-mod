package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractCreateDvo {
    // 계약정보
    private String cntrNo;
    private String cntrSn;
    private String cntrCnfmDt;
    private String cntrCnfmTm;
    private String cntrRcpDt;
    private String cntrRcpTm;

    private String cntrCstNo;
    private String cntrCstKnm;

    private String sellInflwChnlDtlCd;
    private String cntrNatCd;
    private String cntrPrgsStatCd;
    private String prrRcpCntrYn;
    private String cntrDtlStatCd;
    private String cntrwTpCd;
    private String cntrTpCd;

    private String sellDscDvCd;
    private String sellDscTpCd;
    private String sppDuedt;
    private String copnDvCd;
    private String coCd;
    private String bzrNo;
    private String cntrtRelCd;
    private String cttRsCd;
    private String rstlYn;

    private String feeFxamYn;
    private String feeAckmtBaseAmt;
    private String feeAckmtCt;
    private String ackmtPerfRt;
    private String ackmtPerfAmt;
    private String svAmt;
    private String dscAmt;
    private String fnlAmt;
    private String cntrAmt;
    private String cntramDscAmt;
    private String cntrDscAmt;
    private String cntrTam;
    private String sellAmt;
    private String vat;
    private String rentalAmt2;
    private String rentalDscAmt2;
    private String istmPcamAmt;

    // 계약자 연락처 & 주소
    private String cntrAdrRelId;
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
    private String urgtOjYn;

    // 구매상품
    private String cntrPdRelId;
    private String basePdCd;
    private String pdQty;
    private String svPdTpCd;
    private String svPrd;

    // 개인정보동의
    private String cstAgId;
    private String pifClcnUAgYn;
    private String pifThpOfrAgYn;
    private String mktgPurpAgYn;
    private String pifCinfThpOfrAgYn;
    private String pifBizFstrAgYn;

    // 결제정보
    private String cntrStlmId;
    private String cntrStlmRelId;
    private String dpDvCd;
    private String rveDvCd;
    private String cdcoCd;
    private String crcdNo;
    private String cardAmt;
    private String crcdOwrKnm;
    private String cntrtBryyMmdd;
    private String cardExpdtYm;
    private String istmMcn;
    private String bnkCd;
    private String acno;
    private String owrKnm;
    private String stlmAmt;

    // 판매자정보
    private String cntrPrtnrRelId;
    private String sellOgTpCd;
    private String sellPrtnrNo;

    // 제휴업체정보
    private String alncBzsCd;
    private String alncPrtnrNo;
    private String alncPrtnrNm;
    private String alncCntrNo;
    private String alncCntrSn;

    // 기기변경정보
    private String mchnChYn;
    private String mchnCpsApyr;
    private String mchnChCntrMmBaseDvCd;
    private String mchnClnOjYn;
    private String mchnChCntrNo;
    private String mchnChCntrSn;

    // 세금계산서발행정보
    private String txinvPblOjYn;
    private String txinvPdDvCd;
    private String txinvBzrno;
    private String txinvDlpnrPsicNm;
    private String txinvLocaraTno;
    private String txinvExnoEncr;
    private String txinvIdvTno;
    private String txinvEmadr;
    private String txinvCralLocaraTno;
    private String txinvMexnoEncr;
    private String txinvCralIdvTno;
    private String txinvPblD;
    private String txinvRmkCn;

    // 기타
    private String cstStlmInMthCd;
    private String crncyDvCd;
    private String cntrCstRelId;
    private String cntrPrcCmptId;
    private String cntrAdrpcId;
    private String sellEvCd;
    private String frisuBfsvcPtrmN;
    private String frisuAsPtrmN;
    private String istAkArtcMoCn;
    private String sconCn;
    private String fmmbN;
    private String ocoCpsBzsDvCd;

    private String useElectTpCd;
    private String wprsItstTpCd;
    private String wtqltyTstYn;
    private String istMmBilMthdTpCd;
    private String rgstUsrId;
}
