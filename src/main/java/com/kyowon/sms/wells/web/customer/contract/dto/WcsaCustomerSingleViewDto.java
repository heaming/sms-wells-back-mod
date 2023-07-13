package com.kyowon.sms.wells.web.customer.contract.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import io.swagger.annotations.ApiModel;

public class WcsaCustomerSingleViewDto {

    /**
     * 고객정보 Search Request Dto
     * @param cstNm
     * @param bryyMmdd
     * @param sexDvCd
     */
    @ApiModel("WcsaCustomerSingleViewDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstNm,               /* 고객명 */
        @NotBlank
        String bryyMmdd,            /* 생년월일 */
        @NotBlank
        String sexDvCd              /* 성별 */
    ) {
    }


    /**
     * 고객정보 Search Result Dto
     * @param cstNo
     * @param copnDvCd
     * @param copnDvNm
     * @param cstGdCd
     * @param cstGdNm
     * @param cstNm
     * @param bryyMmdd
     * @param sexDvCd
     * @param sexDvNm
     * @param zip
     * @param basAdr
     * @param dtlAdr
     * @param adr
     * @param cralLocaraTno
     * @param mexnoEncr
     * @param cralIdvTno
     * @param mpno
     * @param hpLocaraTno
     * @param hpEncr
     * @param hpIdvTno
     * @param hpno
     * @param safeKeyDupYn
     */
    @ApiModel("WcsaCustomerSingleViewDto-SearchRes")
    public record SearchRes(
        String cstNo,               /* 고객번호 */
        String copnDvCd,            /* 법인격구분코드 */
        String copnDvNm,            /* 법인격구분명 */
        String cstGdCd,             /* 고객등급코드 */
        String cstGdNm,             /* 고객등급명 */
        String cstNm,               /* 고객한글명 */
        String bryyMmdd,            /* 생년월일 */
        String sexDvCd,             /* 성별구분코드 */
        String sexDvNm,             /* 성별구분명 */
        String zip,                 /* 우편번호 */
        String basAdr,              /* 기본주소 */
        String dtlAdr,              /* 상세주소 */
        String adr,                 /* 주소 */
        String cralLocaraTno,       /* 휴대전화번호 - 지역전화번호 */
        String mexnoEncr,           /* 휴대전화번호 - 전화국번호 */
        String cralIdvTno,          /* 휴대전화번호 - 개별전화번호 */
        String mpno,                /* 휴대전화번호 */
        String hpLocaraTno,         /* 자택전화번호 - 지역전화번호 */
        String hpEncr,              /* 자택전화번호 - 전화국번호 */
        String hpIdvTno,            /* 자택전화번호 - 개별전화번호 */
        String hpno,                /* 자택전화번호 */
        String safeKeyDupYn         /* 세이프키 중복여부 */
    ) {
        public SearchRes {
            mpno = cralLocaraTno + "-" + Objects.toString(mexnoEncr, "") + "-" + cralIdvTno;
            hpno = hpLocaraTno + "-" + Objects.toString(hpEncr, "") + "-" + hpIdvTno;
        }
    }


    /**
     * 납입현황 목록 Result Dto
     * @param thisRentalAmt
     * @param pyAmt
     * @param cancelFeeAmt
     * @param cancelCount
     * @param dlqAmt
     * @param dlqMcn
     * @param dlqPdCount
     * @param fnlPyDt
     */
    @ApiModel("WcsaCustomerSingleViewDto-PaymentRes")
    public record PaymentRes(
        String thisRentalAmt,           /* 당월 렌탈료 */
        String pyAmt,                   /* 납입완료금액 */
        String cancelFeeAmt,            /* 위약금 */
        String cancelCount,             /* 위약대상수 */
        String dlqAmt,                  /* 연체금액 */
        String dlqMcn,                  /* 연체개월수 */
        String dlqPdCount,              /* 연체상품수 */
        String fnlPyDt                  /* 연체상품 최종납입일자 */
        ) {
    }


    /**
     * 계약현황 목록 Result Dto
     * @param cntrCstNo
     * @param cntrNo
     * @param cntrSn
     * @param cntrRcpFshDt
     * @param istCstKnm
     * @param pdCd
     * @param pdNm
     * @param cntrPrgsStatCd
     * @param cntrPrgsStatNm
     * @param sellTpCd
     */
    @ApiModel("WcsaCustomerSingleViewDto-ContractRes")
    public record ContractRes(
        String cntrCstNo,               /* 계약고객번호 */
        String cntrNo,                  /* 계약번호 */
        String cntrSn,                  /* 계약일련번호 */
        String cntrRcpFshDt,            /* 계약접수완료일자 */
        String istCstKnm,               /* 수령자명 */
        String pdCd,                    /* 상품코드 */
        String pdNm,                    /* 상품명 */
        String cntrPrgsStatCd,          /* 계약진행상태코드 */
        String cntrPrgsStatNm,          /* 계약진행상태명 */
        String sellTpCd                 /* 판매유형코드 */
        ) {
    }


    /**
     * 서비스이력 목록 Result Dto
     * @param wkExcnDt
     * @param serviceGb
     * @param cntrNo
     * @param pdGrpNm
     * @param pdNm
     * @param partPdNm
     * @param cntrSn
     */
    @ApiModel("WcsaCustomerSingleViewDto-ServiceRes")
    public record ServiceRes(
        String wkExcnDt,                /* 작업수행일자 */
        String serviceGb,               /* 서비스구분 */
        String cntrNo,                  /* 계약번호 */
        String pdGrpNm,                 /* 상품그룹명 */
        String pdNm,                    /* 상품명 */
        String partPdNm,                /* 부품상품명 */
        String cntrSn                   /* 계약일련번호 */
        ) {
    }


    /**
     * 보유상품 목록 Result Dto
     * @param pdCd
     * @param pdNm
     * @param sellTpCd
     * @param sellTpNm
     * @param fnlAmt
     * @param rcgvpKnm
     * @param prtnrKnm
     * @param rentalTn
     * @param cntrPtrm
     * @param frisuBfsvcEnddt
     * @param svPrd
     * @param lastBs
     * @param nextBs
     * @param cntrNo
     * @param cntrSn
     * @param cntrCnfmDtm
     * @param cntrCstNo
     * @param expireSoonYn
     */
    @ApiModel("WcsaCustomerSingleViewDto-ProductRes")
    public record ProductRes(
        String pdCd,            /* 상품코드 */
        String pdNm,            /* 상품명 */
        String sellTpCd,        /* 판매유형코드 */
        String sellTpNm,        /* 판매유형명 */
        String fnlAmt,          /* 최종금액 */
        String rcgvpKnm,        /* 설치자 */
        String prtnrKnm,        /* 담당자 */
        String rentalTn,        /* 렌탈시용기간 */
        String cntrPtrm,        /* 전체계약기간 */
        String frisuBfsvcEnddt, /* 무상멤버십종료일 */
        String svPrd,           /* 서비스주기 */
        String lastBs,          /* 최근B/S */
        String nextBs,          /* 다음B/S */
        String cntrNo,          /* 계약번호 */
        String cntrSn,          /* 계약일련번호 */
        String cntrCnfmDtm,     /* 계약확정일시 */
        String cntrCstNo,       /* 계약고객번호 */
        String expireSoonYn     /* 남은계약기간 3개월 이내 인지 여부 */
        ) {
        public ProductRes {
            expireSoonYn = "N";
            if (!StringUtils.equals(rentalTn, "-") && !StringUtils.equals(cntrPtrm, "-")) {
                if (Integer.parseInt(cntrPtrm) - Integer.parseInt(rentalTn) <= 3) {
                    expireSoonYn = "Y";
                }
            }
        }
    }
}
