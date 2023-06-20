package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @see PR_KIWI_WRK_CREATE_V2M
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
public class WsnbMultipleTaskOrderDto {

    @ApiModel(value = "WsnbMultipleTaskOrderDto-SaveReq")
    public record SaveReq(
        String inChnlDvCd, /* 입력채널구분코드 */
        String asIstOjNo, /* AS설치대상번호 */
        String svBizHclsfCd, /* 서비스업무대분류코드 */
        String rcpdt, /* 접수일자 */
        String dtaStatCd, /* 자료상태코드 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String urgtYn, /* 긴급여부 */
        String vstRqdt, /* 방문요청일자 */
        String vstAkHh, /* 방문요청시간 */
        String smsFwYn, /* SMS발송여부 */
        int svEtAmt, /* 서비스예상금액 */
        String dpDvCd, /* 입금구분코드 */
        String cnslTpHclsfCd, /* 상담유형대분류코드 */
        String cnslTpMclsfCd, /* 상담유형중분류코드 */
        String cnslTpLclsfCd, /* 상담유형소분류코드 */
        String cnslDtlpTpCd, /* 상담세부유형코드 */
        String cnslMoCn, /* 상담메모내용 */
        String cstCnrRefriDvCd, /* 기타정보 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String rcgvpKnm, /* 고객한글명 */
        String rnadr, /* 고객도로명주소 */
        String rdadr, /* 고객도로명상세주소 */
        String pdCd, /* 상품코드 */
        String saleCd, /* 기간계 상품코드 */
        String svPrd, /* 서비스주기 */
        String compYn, /* 기기변경여부 */
        String retYn, /* 회수유무 */
        String cntrNoB, /* 보상계약번호 */
        String cntrSnB, /* 보상계약번호순번 */
        String updtPsicDvCd, /* 업데이트담당자구분코드 */
        String alncBzsCd, /* 제휴업체코드 */
        String payNm, /* 입금자명 */
        String partList, /* 자재코드,수량,금액 | 자재코드,수량,금액 | ~~~ */
        String userId,
        String rcpOgTpCd
    ) {}

}
