package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractInstallDvo {
    private String cntrNo; //계약번호(필수)
    private String cntrSn; //계약일련번호(필수)
    private String adrId; //변경될 주소ID
    private String cralLocaraTno;//휴대지역전화번호
    @DBEncField
    private String mexno; //휴대전화국번호
    private String cralIdvTno; //휴대개별전화번호
    private String locaraTno; //지역전화번호
    @DBEncField
    private String exno; //전화국번호
    private String idvTno; //개별전화번호
}
