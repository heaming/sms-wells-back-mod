package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncOutsourcedpdAsReceiptDvo {
    int rcpSn; /* 접수일련번호 */
    String entrpDvCd; /* 사업자구분코드 */
    int svCnrSn; /* 서비스센터일련번호 */
    String pdNm; /* 제품명 */
    String svCnrNm; /* 서비스센터명 */
    String svCnrLocaraTno; /* 서비스센터지역전화번호 */
    @DBEncField
    @DBDecField
    String svCnrExnoEncr; /* 서비스센터전화국번호 */
    String svCnrIdvTno; /* 서비스센터개별전화번호 */
    String svCnrZip; /* 서비스센터우편번호 */
    String svCnrAdr; /* 서비스센터주소 */
    String svCnrDtlAdr; /* 서비스센터상세주소 */
    String svCnrIchrPrtnrNm; /* 서비스센터담당파트너명 */
    String cstTno; /* bizTalk 전송할 고객전화번호 */

}
