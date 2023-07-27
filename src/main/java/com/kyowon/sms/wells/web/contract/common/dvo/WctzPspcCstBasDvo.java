package com.kyowon.sms.wells.web.contract.common.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

/**
 * 가망고객기본 Dvo
 *
 * @author joobro
 * @since 2023-07-07
 */
@Getter
@Setter
public class WctzPspcCstBasDvo {
    private String pspcCstId; /*가망고객ID*/
    private String pspcCstInflwDt; /*가망고객유입일자*/
    private String pspcCstKnm; /*가망고객한글명*/
    private String pspcCstEnm; /*가망고객영문명*/
    private String copnDvCd; /*법인격구분코드*/
    private String crpSpmtDrmNm; /*법인추가식별명*/
    private String cstNo; /*고객번호*/
    private String idkKndCd; /*식별키종류코드*/
    private String idkVal; /*식별키값*/
    private String idkIsDtm; /*식별키발급일시*/
    private String natCd; /*국가코드*/
    private String adrId; /*주소ID*/
    private String sexDvCd; /*성별구분코드*/
    private String bryyMmdd; /*생년월일*/
    private String cralLocaraTno; /*휴대지역전화번호*/
    @DBEncField
    @DBDecField
    private String mexnoEncr; /*휴대전화국번호암호화*/
    private String cralIdvTno; /*휴대개별전화번호*/
    private String locaraTno; /*지역전화번호*/
    @DBEncField
    @DBDecField
    private String exnoEncr; /*전화국번호암호화*/
    private String idvTno; /*개별전화번호*/
    private String emadr; /*이메일주소*/
    private String nwInflwCstYn; /*신규유입고객여부*/
    private String aplcDvcDvCd; /*신청디바이스구분코드*/
    private String pspcCstInflwPhCd; /*가망고객유입경로코드*/
    private String pspcCstFtfYn; /*가망고객대면여부*/
    private String mgntTpCd; /*중요도유형코드*/
    private String pspcCstRcpCn; /*가망고객접수내용*/
    private String evnNm; /*이벤트명*/
    private String rcmdrPrtnrNo; /*추천인파트너번호*/
    private String ogAsnStatCd; /*조직배정상태코드*/
    private String ichrPrtnrNo; /*담당파트너번호*/
    private String prtnrMoCn; /*파트너메모내용*/
    private String otsdLkDrmVal; /*외부연계식별값*/
    private String pspcCstDstcBaseDtm; /*가망고객파기기준일시*/
    private String mvsDstcRcvryDvCd; /*소산파기복구구분코드*/
    private String mvsDstcRcvryDtm; /*소산파기복구일시*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String pspcCstTpCd; /*가망고객유형코드*/
    private String bzrno; /*사업자등록번호*/
    private String ctpvNm; /*시도명*/
    private Integer reasnCt; /*재배정건수*/
    private String prPrtnrNwInflwCstYn; /*홍보파트너신규유입고객여부*/
    private String prPrtnrAsnDtm; /*홍보파트너배정일시*/
    private String otsdChnlSpmtYn; /*외부채널추가여부*/
    private String freDgnsTpCd; /*무료진단유형코드*/
    private String hgrFreDgnsTpCd; /*상위무료진단유형코드*/
    private String ichrOgTpCd; /*담당조직유형코드*/
    private String ptlCstDbDvCd; /*잠재고객DB구분코드*/
    private String pspcCstDivDvCd; /*가망고객사업부구분코드*/
    private String otsdLkOgTpCd; /*외부연계조직유형코드*/
    private String otsdLkPrtnrNo; /*외부연계파트너번호*/
}
