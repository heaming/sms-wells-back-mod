package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaBulkContractDvo {
    private String basePdCd;
    private String copnDvCd;
    private String cstNo;
    private String sellPrtnrNo; /* from  alncmpDgPrtnrMapngCd */
    private String alncmpCd;
    private String sellOgTpCd; /* from alncmpDgPrtnrOgTpCd */
    private Long cntrAmt;
    private Integer cntrPtrm;
    private String svPdCd;
    private Integer stplPtrm;
    private String pspcCstInflwDt;
    private String sellDscTpCd; /* from rentalDscTpCd, spayDscTpCd */
    private String sellDscDvCd; /* from rentalDscDvCd */
    private String sellDscrCd; /* from rentalCrpDscrCd */
    private Long sellDscCtrAmt;
    private String otsdLkDrmVal; /* from alncmpSuscOrdNo */
    private String adrId;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String pdDclsfId;
    private String sellTpCd;
    private String sellTpDtlCd;
    private Integer svPrd;
    private Double pdBaseAmt; /* todo change type */
    private Double sellAmt;
    private Double dscAmt;
    private Double fnlAmt;
    private Long vat;
    private Double cntrTam;
    private Float ackmtPerfRt;
    private Long ackmtPerfAmt;
    private Integer feeAckmtCt;
    private Long feeAckmtBaseAmt;
    private String cntrNo;
    private Integer cntrSn;
    private String cntrTpCd;
    private String cntrPrgsStatCd;
    private String cntrNatCd;
    private Integer pdQty;
    private String cntrwTpCd;
    private String txinvPblOjYn;
    private String coCd;
    private Long cntramDscAmt; /* todo */
    private String cntrPrtnrRelId;
    private String cntrPrtnrTpCd;
    private String cntrCstRelId;
    private String cntrUnitTpCd;
    private String cntrCstRelTpCd;
    private String cntrtRelCd;
    private String sellInflwChnlDtlCd;
    private String pdctCntrPdRelId;
    private String pdctPdRelId;
    private String pdctPdCd;
    private String pdctVlStrtDtm;
    private String pdctVlEndDtm;
    private Integer pdctPdQty;
    private String svCntrPdRelId;
    private String svPdRelId;
    private String svVlStrtDtm;
    private String svVlEndDtm;
    private Integer svPdQty;
    private String cntrPrcCmptId;
    private String pdPrcFnlDtlId;
    private Integer verSn;
    private String fxamFxrtDvCd;
    private Double ctrVal;
    private Double fnlVal;
    private String pdPrcId;

    /* 일시불 only */
    private String hcrDvCd; /* 홈케어구분코드 */
    private String frisuBfsvcPtrmN; /* 무상멤버십기간 */
    private String istDt; /*설치일자*/
    private String bfsvcBzsDvCd; /*BS업체구분코드*/
    private String splyBzsDvCd; /*조달업체구분코드*/
}
