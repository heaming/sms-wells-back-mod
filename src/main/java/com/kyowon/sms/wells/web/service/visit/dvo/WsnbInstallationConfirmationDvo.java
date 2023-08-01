package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-MP-U-0048P01 전자설치확인서
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.07
 */
@Setter
@Getter
public class WsnbInstallationConfirmationDvo {
    String wkPrtnrNo;
    String prtnrKnmEng;
    String cntrNo;
    String cntrSn;
    String copnDvCdNm;
    String basePdCd;
    String pdNm;
    int rentalAmt;
    String svPrd;
    String prtnrKnmRcpt;
    String cstKnm;
    String bryyMmdd;
    String sexDvCd;
    String bzrno;
    String newAdrZip;
    String addrNm;
    String cralLocaraTno;
    String mexnoEncr;
    String cralIdvTno;
    String locaraTno;
    @DBDecField
    String exnoEncr;
    String idvTno;
    String cstKnmIst;
    String newAdrZipIst;
    String addrNmIst;
    String cralLocaraTnoIst;
    String mexnoEncrIst;
    String cralIdvTnoIst;
    String locaraTnoIst;
    @DBDecField
    String exnoEncrIst;
    String idvTnoIst;
    String wkExcnDt;
    String istPlcTpCdNm;
    String pdCd;
    String pdGrpCd;
    String etcTxt;
    String lowpressYn;
    String notakFwYn;
    String cstSignCn;
    byte[] cstSignByte;
    String decvlIstYn;
    String wtholVlvIstYn;
    String istKitOcoLkYn;
    String rwtkYn;
    String pnchYn;
    String lwrWprsIstYn;
    String inwaYn;
    String unwaIstYn;
    String cstNm;
    String cntrtRelNm;
    String tno;
    String mpno;
    String tnoIst;
    String mpnoIst;
}
