package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbRentalBulkChangeDvo {
    private String cntrChRcpId; // 계약변경접수ID
    private String dtlCntrNo; // 계약번호
    private String dtlCntrSn; // 계약일련번호
    private String cntrDtlNo; // 계약상세번호
    private String sellTpCd; // 계약변경유형코드
    private String cntrChTpCd; //
    private String cstKnm; // 계약자정보
    private String istDt; // 설치일자
    private String istYm; // 설치년월
    private String bfchDutyPtrmN; // 의무기간 변경전
    private String afchDutyPtrmN; // 의무기간 변경후
    private String ackmtPerfAmt; // 인정실적금액
    private String feeBaseAmt; // 수수료기준가격
    private String feeAckmtCt; // 수수료인정건수
    private String rentalDscAmt; //렌탈할인금액
    private String pdctIdno; //제품고유번호
    private String stpStrtYm; //시작기간(중지시작년월)
    private String stpEndYm; //종료기간 (중지종료년월)
    private String cralLocaraTno; //휴대전화1
    @DBDecField
    private String mexnoEncr; //휴대전화2
    private String cralIdvTno; //휴대전화3
    private String feeFxamYn; // 수수료정액여부
    private String pmotDscMcn; // 할인개월
    private String pmotDscAmt; // 할인금액
    private String sdingAckmtPerfAmt; // 수당건수 인정
    private String sdingFeeBaseAmt; // 수수료기준가격
    private String bfchFeeAckmtCt; // 수수료기준가격(현재,변경전)
    private String bfsvcBzsDvCd; // BS업체구분코드
    private String splyBzsDvCd; // 조달업체구분코드
    private String cttTpCd; // 컨택유형코드
    private String duedt; // 예정일자
    private String cntrChAkCn; // 계약변경요청내용
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록사용자
    private String fstRgstUsrNm;// 최초등록사용자명
}
