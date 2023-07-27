package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 가망고객상담추천내용 Dvo
 *
 * @author joobro
 * @since 2023-07-07
 */
@Getter
@Setter
public class WctzPspcCstCnslRcmdIzDvo {
    private String pspcCstCnslId; /*가망고객상담ID*/
    private Integer pspcCstCnslSn; /*가망고객상담일련번호*/
    private String mbCstNo; /*회원고객번호*/
    private String pdCd; /*상품코드*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String cntrRcpFshDtm; /*계약접수완료일시*/
    private String trsYn; /*전송여부*/
    private String dtaDlYn; /*데이터삭제여부*/

    public WctzPspcCstCnslRcmdIzDvo() {
    }

    public WctzPspcCstCnslRcmdIzDvo(String pspcCstCnslId, Integer pspcCstCnslSn) {
        this.pspcCstCnslId = pspcCstCnslId;
        this.pspcCstCnslSn = pspcCstCnslSn;
    }
}
