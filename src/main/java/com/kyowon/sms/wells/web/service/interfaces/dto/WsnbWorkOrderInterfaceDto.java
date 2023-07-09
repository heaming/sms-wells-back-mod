package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-I-0009 타시스템(교원웰스, 고객센터, KMEMBERS)에서 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.26
 */
public class WsnbWorkOrderInterfaceDto {

    @Builder
    @ApiModel(value = "WsnbMultipleTaskOrderInterfaceDto-CreateOrderReq")
    public record CreateOrderReq(
        @NotBlank
        @JsonProperty("IN_CHNL_DV_CD")
        String inChnlDvCd, // 입력채널구분코드
        @NotBlank
        @JsonProperty("SV_BIZ_HCLSF_CD")
        String svBizHclsfCd, // 서비스업무대분류코드
        @JsonProperty("RCPDT")
        String rcpdt, // 접수일자
        @JsonProperty("AS_IST_OJ_NO")
        String asIstOjNo, // AS설치대상번호
        @NotBlank
        @JsonProperty("MTR_STAT_CD")
        String mtrStatCd, // 자료상태코드
        @NotBlank
        @JsonProperty("SV_BIZ_DCLSF_CD")
        String svBizDclsfCd, // 서비스업무세분류코드
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @NotBlank
        @JsonProperty("VST_RQDT")
        String vstRqdt, // 방문요청일자
        @JsonProperty("VST_AK_HH")
        String vstAkHh, // 방문요청시간

        @JsonProperty("URGT_YN")
        String urgtYn, // 긴급여부
        @NotBlank
        @JsonProperty("SMS_FW_YN")
        String smsFwYn, // SMS발송여부
        @JsonProperty("SV_ET_AMT")
        int svEtAmt, // 서비스예상금액 (필터판매 작업일 경우 엔지니어가 고객으로부터 받아야 할 금액)
        @JsonProperty("DP_DV_CD")
        String dpDvCd, // 입금구분코드 (1: 선불, 2: 후불 (AS-IS 에서 필터판매 오더는 무조건 유상으로 처리하고 AC211_ACC_AMT 금액을 표시 해주므로 이 필드는 사용 안 하고 있음))
        @JsonProperty("CNSL_TP_HCLSF_CD")
        String cnslTpHclsfCd, // 상담유형대분류코드
        @JsonProperty("CNSL_TP_MCLSF_CD")
        String cnslTpMclsfCd, // 상담유형중분류코드
        @JsonProperty("CNSL_TP_LCLSF_CD")
        String cnslTpLclsfCd, // 상담유형소분류코드
        @JsonProperty("CNSL_DTLP_TP_CD")
        String cnslDtlpTpCd, // 상담세부유형코드
        @JsonProperty("CNSL_MO_CN")
        String cnslMoCn, // 상담메모내용
        @JsonProperty("AS_REFRI_DV_CD")
        String asRefriDvCd, // AS유무상구분코드
        @JsonProperty("MTCMCO")
        String mtcmco, // 이동통신사
        @JsonProperty("CPHON_IDV_TNO1")
        String cphonIdvTno1, // 휴대폰개별전화번호1
        @JsonProperty("CPHON_IDV_TNO2")
        String cphonIdvTno2, // 휴대폰개별전화번호2
        @JsonProperty("LOCARA_TNO")
        String locaraTno, // 지역전화번호
        @JsonProperty("IDV_TNO1")
        String idvTno1, // 개별전화번호1
        @JsonProperty("IDV_TNO2")
        String idvTno2, // 개별전화번호2
        @JsonProperty("IST_ZIP1")
        String istZip1, // 이전주소 우편번호1
        @JsonProperty("IST_ZIP2")
        String istZip2, // 이전주소 우편번호2
        @JsonProperty("IST_ADR")
        String istAdr, // 이전주소
        @JsonProperty("IST_DTL_ADR")
        String istDtlAdr, // 이전주소상세
        @JsonProperty("REF_ADR")
        String refAdr, // 참조주소
        @JsonProperty("PRCHS_MAT_LIST")
        String prchsMatList, // 구매자재리스트
        /* TODO: @JsonProperty("P_AC216_ETC_1")
        String adrChangeYn, // 분리+재설치 시 이전 주소에서 서비스 받을지 여부*/
        @JsonProperty("REG_USER_ID")
        String regUserId // 입력사용자ID
    ) {}

    @Builder
    @ApiModel(value = "WsnbMultipleTaskOrderInterfaceDto-CreateOrderRes")
    public record CreateOrderRes(
        @JsonProperty("AS_IST_OJ_NO")
        String asIstOjNo // AS설치대상번호
        // TODO: 별도 요청 키 사용 여부 확인 필요
    ) {}

}
