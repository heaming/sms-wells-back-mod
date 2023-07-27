package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbChangeOrderDetailDvo {
    private String cntrAdrpcId; /* 계약주소지ID */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String cntrNo; /* 계약번호 */
    private String cntrCstNo; /* 계약고객번호 */
    private String cntrtRelCd; /* 계약자관계코드 */
    private String rcgvpKnm; /* 수령자한글명 */
    private String rcgvpEnm; /* 수령자영문명 */
    private String copnDvCd; /* 법인격구분코드 */
    private String crpSpmtDrmNm; /* 법인추가식별명 */
    private String natCd; /* 국가코드 */
    private String adrDvCd; /* 주소ID */
    private String cralTno; /* 계약자 휴대전화번호 */
    private String cralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cralIdvTno; /* 계약자 휴대개별전화번호 */
    private String emadr; /* 이메일주소 */
    private String cnrSppYn; /* 센터배송여부 */
    private String ogTpCd; /* 조직유형코드 */
    private String bldCd; /* 빌딩코드 */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
    private String fstRgstDtm; /* 최초등록일시 */
    private String fstRgstUsrId; /* 최초등록사용자ID */
    private String fstRgstPrgId; /* 최초등록프로그램ID */
    private String fstRgstDeptId; /* 최초등록부서ID */
    private String fnlMdfcDtm; /* 최종수정일시 */
    private String fnlMdfcUsrId; /* 최종수정사용자ID */
    private String fnlMdfcPrgId; /* 최종수정프로그램ID */
    private String fnlMdfcDeptId; /* 최종수정부서ID */
}
