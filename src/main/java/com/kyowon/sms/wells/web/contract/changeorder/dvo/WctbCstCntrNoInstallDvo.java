package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbCstCntrNoInstallDvo {
    private String inDv; /* (필수)입력구분(1.계약번호+계약일련번호, 2.고객번호) */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cstNo; /* 고객번호 */
    private String adrId; /* (필수)주소ID */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    private String mexno; /* 휴대전화국번호 */
    private String cralIdvTno; /* 휴대개별전화번호 */
}
