package com.kyowon.sms.wells.web.contract.common.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctzItgDpBasDvo { /*통합입금기본*/
    private String itgDpNo; /*통합입금번호*/
    private String rveCd; /*수납코드*/
    private String prtnrNo; /*파트너번호*/
    private String cstNo; /*고객번호*/
    private String ogId; /*조직ID*/
    private String dpTpCd; /*입금유형코드*/
    private String dpDtm; /*입금일시*/
    private String perfDt; /*실적일자*/
    private String dprNm; /*입금자명*/
    private String dpAmt; /*입금금액*/
    private String dpCprcnfAmt; /*입금대사금액*/
    private String canAmt; /*취소금액*/
    private String rfndAmt; /*환불금액*/
    private String dpBlam; /*입금잔액*/
    private String ovrCanAmt; /*초과취소금액*/
    private String itgDpCanYn; /*통합입금취소여부*/
    private String itgDpCanDtm; /*통합입금취소일시*/
    private String itgDpCanRsonCd; /*통합입금취소사유코드*/
    private String incmdcYn; /*소득공제여부*/
    @DBEncField
    @DBDecField
    private String acnoEncr; /*계좌번호암호화*/
    private String pchsCdcoCd; /*매입카드사코드*/
    @DBEncField
    @DBDecField
    private String crcdnoEncr; /*신용카드번호암호화*/
    private String orintDpNo; /*원통합입금번호*/
    private String dpAccCd; /*입금계정코드*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String rveCoCd; /*수납회사코드*/
    private String ogTpCd; /*조직유형코드*/
    private String dpDvCd; /*입금구분코드*/
    private String fnitCd; /*금융기관코드*/
    private String crcdonrNm; /*신용카드주명*/
    private String rveAkNo; /*수납요청번호*/
    private String pgFeeAmt; /*PG수수료금액*/
    private String cardKndCd; /*카드종류코드*/
    private String vncoDvCd; /*VAN사구분코드*/
    private String itgDpChRsonCd; /*통합입금변경사유코드*/
    private String dpMesCd; /*입금수단코드*/
    private String vacIsId; /*가상계좌발급ID*/
    private String crdcdCopnDvCd; /*신용카드법인격구분코드*/
    private String crdcdExpdtYm; /*신용카드유효기간년월*/
    private String crdcdIstmMcn; /*신용카드할부개월수*/
    private String crdcdAprDtm; /*신용카드승인일시*/
    private String crdcdAprno; /*신용카드승인번호*/
    private String crdcdFnitCd; /*신용카드금융기관코드*/
    private String crdcdBryyMmdd; /*신용카드생년월일*/
    private String crdcdBzrno; /*신용카드사업자등록번호*/
}
