package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaTaxInvoiceInquiryDvo {
    private String txinvPblOjYn; /* 세금계산서발행여부 */
    private String bzrno; /* 사업자등록번호 */
    private String dlpnrNm; /* 거래처명 */
    private String dlpnrPsicNm; /* 거래처담당자명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */

    @DBDecField
    private String mexno; /* 휴대전화국번호 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String emadr; /* 이메일 주소 */
    private String emadr1; /* @앞 메일주소 */
    private String emadr2; /* @뒤 메일주소 */
    private String txinvPblD; /* 세금계산서발행일 */
    private String cntrCnfmDtm; /* 계약확정일시 */
    private String dlpnrCd; /* 거래처 코드 */
    private String dpTpCd; /* 입금유형코드 */
    private String sellTpCd;

    /* 세금계산서접수기준내역 */
    private String cntrNo; /* 계약번호 */
    private int cntrSn; /* 계약일련번호 */
    private String txinvPdDvCd; /* 세금계산서상품구분코드 */
    private String txinvPblDvCd; /* 세금계산서발행구분코드 */
    private String aplcPsicId; /* 신청담당자ID */
    private String rcpdt; /* 접수일자 */
    private String cntrCstNo; /* 계약고객번호 */
    private String txinvPblYn; /* 세금계산서발행여부 */
    private Integer txinvBndlSn; /* 세금계산서묶음일련번호 */
    private String locaraTno; /* 지역전화번호 */
    @DBEncField
    private String exnoEncr; /* 전화국번호암호화 */
    private String idvTno; /* 개별전화번호 */
    @DBDecField
    @DBEncField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String rmkCn; /* 비고내용 */
    private String mvsDstcRcvryBaseDtm; /* 소산파기복구기준일시 */
    private String mvsDstcRcvryDvCd; /* 소산파기복구구분코드 */
    private String mvsDstcRcvryDtm; /* 소산파기복구일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
    private String bfchCn; /* 변경 전 내용 */

    /* 계약변경접수기본  */
    private String cntrChRcpId; /* 계약변경접수ID */
    private String cntrChRcpDtm; /* 계약변경접수일시 */
    private String cntrChTpCd; /* 계약변경유형코드 */
    private String chRqrDvCd; /* 변경요청자구분코드 */
    private String chRqrNm; /* 변경요청자명 */
    private String cstNo; /* 고객번호 */
    private String cntrChAkCn; /* 계약변경요청내용 */
    private String cntrChPrgsStatCd; /* 계약변경진행상태코드 */
    private String cntrChPrgsMoCn; /* 계약변경진행메모내용 */
    private String chRcstDvCd; /* 변경접수자구분코드 */
    private String chRcpUsrId; /* 변경접수사용자ID */
    private String aprDtm; /* 승인일시 */
    private String aprUsrId; /* 승인사용자ID */
    private String bizTfId; /* 업무이관ID */

    private String stplPtrmUnitYn; /* 연장구분삭제여부 */

    /* 계약변경접수상세 */
    private int cntrChSn; /* 계약변경일련번호 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String dtlCntrNo; /* 상세계약번호 */
    private Integer dtlCntrSn; /* 상세계약일련번호 */
    private String cntrChRsonDvCd; /* 계약변경사유구분코드 */
    private String cntrChRsonCd; /* 계약변경사유코드 */
    private String cntrChAtcDvCd; /* 계약변경항목구분코드 */
    private String chApyStrtdt; /* 변경적용시작일자 */
    private String chApyEnddt; /* 변경적용종료일자 */
    private String chPdCd; /* 변경상품코드 */
    private Long chQty; /* 변경수량 */
    private Long chAmt; /* 변경금액 */
    private String chPtrmUnitCd; /* 변경기간단위코드 */
    private Long chPtrmN; /* 변경기간수 */
    private String cntrAdrpcId; /* 계약주소지ID */
    private String cntrStlmId; /* 계약결제ID */
    private String procsYn; /* 처리여부 */
    private String procsDuedt; /* 처리예정일자 */
    private String procsFshDtm; /* 처리완료일시 */
    private String cntrChFshDtm;

    /* 최초 등록, 최종 수정*/
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록유저ID
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcDtm; // 최종수정일시
    private String fnlMdfcUsrId; // 최종수정유저ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID

}
