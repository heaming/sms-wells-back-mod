package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractAdrpcBasDvo {
    private String cntrAdrpcId; /* 계약주소지ID */
    private String cntrNo; /* 계약번호 */
    private String cntrCstNo; /* 계약고객번호 */
    private String cntrtRelCd; /* 계약자관계코드 */
    private String rcgvpKnm; /* 수령자한글명 */
    private String rcgvpEnm; /* 수령자영문명 */
    private String copnDvCd; /* 법인격구분코드 */
    private String crpSpmtDrmNm; /* 법인추가식별명 */
    private String natCd; /* 국가코드 */
    private String adrDvCd; /* 주소구분코드 */
    private String adrId; /* 주소ID */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String locaraTno; /* 지역전화번호 */
    @DBEncField
    @DBDecField
    private String exnoEncr; /* 전화국번호암호화 */
    private String idvTno; /* 개별전화번호 */
    private String emadr; /* 이메일주소 */
    private String cnrSppYn; /* 센터배송여부 */
    private String ogTpCd; /* 조직유형코드 */
    private String bldCd; /* 빌딩코드 */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */

    /* STEP3 */
    private String adr;
    private String adrDtl;
    private String zip;
}
