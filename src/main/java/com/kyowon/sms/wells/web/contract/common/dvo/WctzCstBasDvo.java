package com.kyowon.sms.wells.web.contract.common.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * TB_CUBS_CST_BAS 를 위한 DVO
 * </pre>
 *
 * @author GOAT
 * @since 2023-07-08
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class WctzCstBasDvo {
    private String cstNo; /*고객번호*/
    private String itgCstNo; /*통합고객번호*/
    private String cmnSfkVal; /*공통세이프키값*/
    private String fnlCstNo; /*최종고객번호*/
    private String cikVal; /*CI키값*/
    private String sfkVal; /*세이프키값*/
    private String copnDvCd; /*법인격구분코드*/
    private String dlpnrCd; /*거래처코드*/
    private String crpSpmtDrmNm; /*법인추가식별명*/
    private String cstInflwPhDvCd; /*고객유입경로구분코드*/
    private String prtnrNo; /*파트너번호*/
    private String rnmnoDvCd; /*실명번호구분코드*/
    @DBDecField
    @DBEncField
    private String rnmnoEncr; /*실명번호암호화*/
    private String bzrno; /*사업자등록번호*/
    private String lnfDvCd; /*내외국인구분코드*/
    private String cstKnm; /*고객한글명*/
    private String cstEnm; /*고객영문명*/
    private String adrId; /*주소ID*/
    private String cralLocaraTno; /*휴대지역전화번호*/
    @DBDecField
    @DBEncField
    private String mexnoEncr; /*휴대전화국번호암호화*/
    private String cralIdvTno; /*휴대개별전화번호*/
    private String emadr; /*이메일주소*/
    private String bryyMmdd; /*생년월일*/
    private String lncldYn; /*음력여부*/
    private String sexDvCd; /*성별구분코드*/
    private String unuitmCn; /*특이사항내용*/
    private String tempSaveYn; /*임시저장여부*/
    private String nusdRsonCd; /*미사용사유코드*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String chLtrqConfYn; /*변경요청서확인여부*/
    private String chLtrqConfDt; /*변경요청서확인일자*/
    private String ogTpCd; /*조직유형코드*/
}
