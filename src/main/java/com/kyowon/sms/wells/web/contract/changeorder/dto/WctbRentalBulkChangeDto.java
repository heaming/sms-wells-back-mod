package com.kyowon.sms.wells.web.contract.changeorder.dto;

import com.kyowon.sms.common.web.contract.zcommon.utils.CtContractUtils;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbRentalBulkChangeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 멤버십 일괄변경 조회 Search Request Dto
    @Builder
    @ApiModel("WctbRentalBulkChangeDto-SearchReq")
    public record SearchReq(
        String cntrChTpCd, // 처리구분
        String srchDiv,  // 검색구분
        String srchDt, // 반영일자
        String cntrNo, // 계약번호
        String cntrSn // 계약일련번호
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십 일괄변경 조회 Search Result Dto
    @ApiModel("WctbRentalBulkChangeDto-SearchRes")
    public record SearchRes(
        String cntrChRcpId, // 계약변경접수ID
        String dtlCntrNo, // 계약번호
        String dtlCntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String sellTpCd, // 계약변경유형코드
        String cntrChTpCd, //
        String cstKnm, // 계약자정보
        String istDt, // 설치일자
        String istYm, // 설치년월
        String bfchDutyPtrmN, // 의무기간 변경전
        String afchDutyPtrmN, // 의무기간 변경후
        String ackmtPerfAmt, // 인정실적금액
        String feeBaseAmt, // 수수료기준가격
        String feeAckmtCt, // 수수료인정건수
        String rentalDscAmt, //렌탈할인금액
        String pdctIdno, //제품고유번호
        String stpStrtYm, //시작기간(중지시작년월)
        String stpEndYm, //종료기간 (중지종료년월)
        String cralLocaraTno, //휴대전화1
        String mexnoEncr, //휴대전화2
        String cralIdvTno, //휴대전화3
        String mobileTelNo, // 휴대전화
        String feeFxamYn, // 수수료정액여부
        String pmotDscMcn, // 할인개월
        String pmotDscAmt, // 할인금액
        String sdingAckmtPerfAmt, // 수당건수 인정
        String sdingFeeBaseAmt, // 수수료기준가격
        String bfchFeeAckmtCt, // (모종)수수료 인정건수
        String bfsvcBzsDvCd, // BS업체구분코드
        String splyBzsDvCd, // 조달업체구분코드
        String cttTpCd, // 컨택유형코드
        String duedt, // 예정일자
        String cntrChAkCn, // 계약변경요청내용
        String fstRgstDtm, // 최초등록일시
        String fstRgstUsrId, // 최초등록사용자
        String fstRgstUsrNm // 최초등록사용자명
    ) {
        public SearchRes {
            mobileTelNo = CtContractUtils.buildTno(cralLocaraTno, mexnoEncr, cralIdvTno);
        }
    }
}
