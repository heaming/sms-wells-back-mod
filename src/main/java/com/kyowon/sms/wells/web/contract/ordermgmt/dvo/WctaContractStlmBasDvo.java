package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractStlmBasDvo {
    private String cntrStlmId; /* 계약결제ID */
    private String cstNo; /* 고객번호 */
    private String cntrNo; /* 계약번호 */
    private String cntrtRelCd; /* 계약자관계코드 */
    private String owrKnm; /* 소유자한글명 */
    private String owrEnm; /* 소유자영문명 */
    private String bryyMmdd; /* 생년월일 */
    private String bzrno; /* 사업자등록번호 */
    private String dpTpCd; /* 입금유형코드 */
    private String bnkCd; /* 은행코드 */
    private String cdcoCd; /* 카드사코드 */
    @DBEncField
    @DBDecField
    private String acnoEncr; /* 계좌번호암호화 */
    @DBEncField
    @DBDecField
    private String crcdnoEncr; /* 신용카드번호암호화 */
    private String cardExpdtYm; /* 카드유효기간년월 */
    private String mlgTpCd; /* 마일리지유형코드 */
    private String mlgDrmVal; /* 마일리지식별값 */
    private String mpyBsdt; /* 납부기준일자 */
    private String hsCtfYn; /* 본인인증여부 */
    private String dcevdnDocId; /* 증빙서류문서ID */
    private String pyerNo; /* 납부자번호 */
    private String vncoDvCd; /* VAN사구분코드 */
    private String fnitAprRsCd; /* 금융기관승인결과코드 */
    private String fnitAprFshDtm; /* 금융기관승인완료일시 */
    private String fnitRsgFshDtm; /* 금융기관해지완료일시 */
    private String acFntImpsCd; /* 계좌이체불능코드 */
    private String cardFntImpsCd; /* 카드이체불능코드 */
    private String acChAkDvCd; /* 계좌변경요청구분코드 */
    private String rveCrpCd; /* 수납법인코드 */
    private String fntEvidDrmVal; /* 이체증빙식별값 */
    private String reuseOjYn; /* 재사용대상여부 */
    private String signFileId; /* 서명파일ID */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
