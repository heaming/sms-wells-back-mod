package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.kyowon.sms.common.web.contract.zcommon.utils.CtContractUtils;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaContractStatusDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약현황 Search Request Dto
    @Builder
    @ApiModel("WctaContractStatusDto-SearchReq")
    public record SearchReq(
        String rcpStrtDt,
        String rcpEndDt,
        String cntrPrgsStatCd,
        String pdDvCd,
        String srchDv,
        String srchText,
        String prtnrNm,
        String isBrmgr,
        String isBrmgrCntr
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약현황 Search Result Dto
    @ApiModel("WctaContractStatusDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String sellTpDtlCd,
        String sellTpNm,
        String copnDvNm,
        String sellTpDtlNm,
        String cntrPrgsStatCd,
        String cntrPrgsStatNm,
        String sellPrtnrNo,
        String prtnrKnm,
        String cntrCstNo,
        String cstKnm,
        String bryyMmdd,
        String cntrRcpFshDtm,
        String cntrCnfmDtm,
        String pdCd,
        String pdNm,
        String numprds, // 상세계약 건수
        String pymnamt, // 입금대상금액
        int dfntaprcnt,  // 계약확정승인요청 조회 ( 1 이상이면 확정요청 가능)
        String intlTrgPrdCnt,   // 설치대상 상품 개수
        String intlCmpPrdCnt,    // 설치요청완료 상품 개수
        String pymnSkipYn,
        String cstStlmInMthCd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mobileTelNo,
        String resultDiv,
        String viewRcpFshDtm,
        String viewCntrPrgsStatCd,
        String installYn
    ) {
        public SearchRes{
            mobileTelNo = CtContractUtils.buildTno(cralLocaraTno, mexnoEncr, cralIdvTno);
        }
    }

    // *********************************************************
    // Summary Result Dto
    // *********************************************************
    // 계약현황 Summary Dto
    @Builder
    @ApiModel("WctaContractStatusDto-FindSummaryRes")
    public record FindSummaryRes(
        String cnfm,
        String aprv,
        String wrte,
        String inprgs,
        String delreq
    ) {}

    // *********************************************************
    // Message Request Dto
    // *********************************************************
    // 계약현황 Message Request Dto
    @ApiModel("WctaContractStatusDto-MessageReq")
    public record MessageReq(
        String type, // 메세지 타입 ( SMS / KWork)
        String receiver, // 수신자 (SMS: destInfo, KWork:receiverId
        String url // 기타 정보
    ) {}
}
