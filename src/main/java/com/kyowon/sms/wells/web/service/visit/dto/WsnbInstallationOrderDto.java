package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.16
 * @see [AS-IS] LC_ASREGN_API_000
 */
public class WsnbInstallationOrderDto {

    @Builder
    @ApiModel(value = "WsnbInstallationOrderDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String inChnlDvCd, /* 입력채널구분코드 */
        @NotBlank
        String svBizHclsfCd, /* 서비스업무대분류코드 */
        @NotBlank
        String mtrStatCd, /* 자료상태코드 */
        String asIstOjNo, /* AS설치대상번호 */
        String rcpdt, /* 접수일자 */
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
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        String cntrSn, /* 계약일련번호 */
        String rcgvpKnm, /* 고객한글명 */
        String rnadr, /* 고객도로명주소 */
        String rdadr, /* 고객도로명상세주소 */
        String pdCd, /* 상품코드 */
        String saleCd, /* 기간계 상품코드 */
        String svPrd, /* 서비스주기 */
        String cpsYn, /* 기기변경여부 */
        String retYn, /* 회수유무 */
        String cntrNoB, /* 보상계약번호 */
        String cntrSnB, /* 보상계약번호순번 */
        String updtPsicDvCd, /* 업데이트담당자구분코드 */
        String alncBzsCd, /* 제휴업체코드 */
        String payNm, /* 입금자명 */
        String partList, /* 자재코드,수량,금액 | 자재코드,수량,금액 */
        String userId, /* 접수자 */
        String rcpOgTpCd, /* 접수자 조직유형코드 */
        String ogTpCd, /* 설치기사 */
        String prtnrNo /* 설치기사 파트너번호 */
    ) {
        public SaveReq {
            cntrSn = StringUtil.nvl2(cntrSn, "1");
        }
    }

}
