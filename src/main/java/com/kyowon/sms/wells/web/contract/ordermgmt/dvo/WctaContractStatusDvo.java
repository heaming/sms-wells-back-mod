package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractStatusDvo {
    private String cntrNo;
    private String cntrSn;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String sellTpNm;
    private String copnDvNm;
    private String sellTpDtlNm;
    private String cntrPrgsStatCd;
    private String cntrPrgsStatNm;
    private String sellPrtnrNo;
    private String prtnrKnm;
    private String cntrCstNo;
    private String cstKnm;
    private String bryyMmdd;
    private String cntrRcpFshDtm;
    private String cntrCnfmDtm;
    private String pdCd;
    private String pdNm;
    private String numprds; // 상세계약 건수
    private String pymnamt; // 입금대상금액
    private int dfntaprcnt;  // 계약확정승인요청 조회 ( 1 이상이면 확정요청 가능)
    private String intlTrgPrdCnt;   // 설치대상 상품 개수
    private String intlCmpPrdCnt;    // 설치요청완료 상품 개수
    private String pymnSkipYn;
    private String cstStlmInMthCd;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String mobileTelNo;

    private String resultDiv;
    private String viewRcpFshDtm;
    private String viewCntrPrgsStatCd;
    private String installYn;
}
