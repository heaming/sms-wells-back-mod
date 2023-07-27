package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiMachineChangeDvo {
    private String sellTpCd; // 판매유형코드
    private String sellTpNm; // 판매유형코드명
    private String ojCntrNo; // 대상계약번호
    private int ojCntrSn; // 대상계약일련번호
    private String ojSellTpCd; // 대상판매유형코드
    private String ojSellTpNm; // 대상판매유형코드명
    private int mchnChTpCd; // 기기변경유형코드
    private String mchnCpsApyr; // 기기보상적용율
    private String ojCntrMmBaseDvCd; // 대상계약월기준구분코드
    private String fstRgstDtm; // 최초등록일시
    private String fnlMdfcDtm; // 최종수정일시
    private String fstRgstUsrId; // 최초등록사용자ID
    private String fnlMdfcUsrId; // 최종수정사용자ID
    private String fstRgstUsrNm; // 최초등록사용자명
    private String fnlMdfcUsrNm; // 최종수정사용자명
    private String ojIstllKnm; // 대상설치자한글명
    private int ojRecapDutyPtrmN; // 대상유상의무기간수
    private String ojSlDt; // 대상매출일자
    private String ojPdNm; // 대상상품명
    private String ojCralLocaraTno; // 대상휴대지역전화번호
    @DBDecField
    private String ojMexno; // 대상휴대전화국번호
    private String ojCralIdvTno; // 대상휴대개별전화번호
    private String ojLocaraTno; // 대상지역전화번호
    @DBDecField
    private String ojExno; // 대상전화국번호
    private String ojIdvTno; // 대상개별전화번호
    private String ojAdrId; // 대상주소ID
    private String ojAdr; // 대상주소
    private String ojDtlAdr; // 대상상세주소
    private String ojAdrZip; // 대상주소우편번호
    private String ojCanDt; // 대상취소일자
    private String ojWdwalDt; // 대상탈퇴일자
    private String ojReqdDt; // 대상철거일자
    private String ojCpsDt; // 대상보상일자
    private String ojCopnDvCd; // 대상법인격구분코드
    private String dscTpNm; // 할인유형명
    private String rstlRctDt; // 재약정접수일자
    private String rstlCanDt; // 재약정취소일자
    private String rstlCnfmdt; // 재약정확정일자
    private int rstlDscAmt; // 재약정할인금액
}
