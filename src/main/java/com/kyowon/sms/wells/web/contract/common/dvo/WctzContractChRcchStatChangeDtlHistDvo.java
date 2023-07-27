package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WctzContractChRcchStatChangeDtlHistDvo {
    private String cntrChRcpId; /* 계약변경접수ID */
    private String histStrtDtm; /* 이력시작일시 */
    private int cntrChSn; /* 계약변경일련번호 */
    private String histEndDtm; /* 이력종료일시 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String cntrNo; /* 계약번호 */
    private String dtlCntrNo; /* 상세계약번호 */
    private int dtlCntrSn; /* 상세계약일련번호 */
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
    private String cntrChAkCn; /* 계약변경요청내용 */
    private String cntrAdrpcId; /* 계약주소지ID */
    private String cntrStlmId; /* 계약결제ID */
    private String procsYn; /* 처리여부 */
    private String bfchCn; /* 변경전내용 */
    private String procsDuedt; /* 처리예정일자 */
    private String procsFshDtm; /* 처리완료일시 */
    private String dtaDlYn; /* 데이터삭제여부 */

    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록유저ID
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcDtm; // 최종수정일시
    private String fnlMdfcUsrId; // 최종수정유저ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID
}
