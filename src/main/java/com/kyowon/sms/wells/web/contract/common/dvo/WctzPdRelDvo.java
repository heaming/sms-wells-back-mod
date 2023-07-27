package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

/**
 * <pre>
 * TB_PDBS_PD_REL 만을 위한 DVO
 * </pre>
 *
 * @author Administrator
 * @since 2023-07-11
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class WctzPdRelDvo {
    private String pdRelId; /*상품관계ID*/
    private String vlStrtDtm; /*유효시작일시*/
    private String vlEndDtm; /*유효종료일시*/
    private String basePdCd; /*기준상품코드*/
    private String pdRelTpCd; /*상품관계유형코드*/
    private String pdRelDtlTpCd; /*상품관계상세유형코드*/
    private String ojPdCd; /*대상상품코드*/
    private String svPdCd; /*서비스상품코드*/
    private String rltgPdCd; /*실물상품코드*/
    private String stplPrdCd; /*약정주기코드*/
    private String cpbDvCd; /*호환구분코드*/
    private String pdGrpId; /*상품그룹ID*/
    private String pdDtlId; /*상품상세ID*/
    private Float diviRat; /*안분비율*/
    private String blamInptYn; /*잔액산입여부*/
    private String tempSaveYn; /*임시저장여부*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String fstRgstDtm; /*최초등록일시*/
    private String fstRgstUsrId; /*최초등록사용자ID*/
    private String fstRgstPrgId; /*최초등록프로그램ID*/
    private String fstRgstDeptId; /*최초등록부서ID*/
    private String fnlMdfcDtm; /*최종수정일시*/
    private String fnlMdfcUsrId; /*최종수정사용자ID*/
    private String fnlMdfcPrgId; /*최종수정프로그램ID*/
    private String fnlMdfcDeptId; /*최종수정부서ID*/
    private Double basVal; /*기본값*/
    private Double fnlVal; /*최종값*/
    private Long splAmt; /*공급금액*/
    private Long vat; /*부가가치세*/
    private Integer ackmtCt; /*인정건수*/
    private Long ackmtAmt; /*인정금액*/
    private Float ackmtPerfRt; /*인정실적율*/
    private Long sellFee; /*판매수수료*/
    private String feeFxamYn; /*수수료정액여부*/
    private Integer sppTn; /*배송회차*/
    private String booSellYn; /*예약판매여부*/
    private String booSppDt; /*예약배송일자*/
    private Integer itmQty; /*품목수량*/
}
