package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractDocumentMailDvo {

    String docDvCd; // 발행구분(증빙서류 구분)

    String recpMail; // 이메일

    String cstKnm; // 고객명

    String startDt; // 시작일

    String endDt; // 종료일

    String pblDt; // 발행일
    String cstGubun; // 구분
    String title; // 제목
    String titleStr; // 분류
    List<Contract> cstList; // 계약리스트

    @Getter
    @Setter
    public static class Contract {
        String cntrDtlNo; // 계약상세번호
        String cntrNo; // 계약번호
        String cntrSn; // 계약일련번호
    }
}
