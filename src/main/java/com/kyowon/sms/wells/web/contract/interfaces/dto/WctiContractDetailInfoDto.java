package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiContractDetailInfoDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약상세 정보 Find Request Dto
    @Builder
    @ApiModel("WctiContractDetailInfoDto-FindReq")
    public record FindReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn
    ) {}

    @Builder
    @ApiModel("WctiContractDetailInfoDto-FindStlmRes")
    public record FindStlmRes(
        String fntDt, // 이체일자
        String dpTpCd, // 입금유형코드
        String fnitAprRsCd // 금융기관승인결과코드
    ){}

    @Builder
    @ApiModel("WctiContractDetailInfoDto-FindClctamRes")
    public record FindClctamRes(
        String clctamPrtnrNo, // 집금파트너번호
        String clctamPrtnrNm // 집금파트너명
    ){}

    @Builder
    @ApiModel("WctiContractDetailInfoDto-FindMembershipRes")
    public record FindMembershipRes(
        String ojCntrNo, // 대상계약번호
        Integer ojCntrSn, // 대상계약일련번호
        String ojSellTpCd, // 대상판매유형코드
        String ojSellTpNm, // 대상판매유형명
        String ojIstDt // 대상설치일자
    ){}

    // ****************
    // Result Dto
    // *********************************************************
    // 계약상세 정보 Find Result Dto
    @ApiModel("WctiContractDetailInfoDto-FindRes")
    public record FindRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        Integer cntrSn, // 계약일련번호
        @JsonProperty("CNTR_DTL_STAT_CD")
        String cntrDtlStatCd, // 계약상세상태코드
        @JsonProperty("CNTR_DTL_STAT_NM")
        String cntrDtlStatNm, // 계약상세상태코드명
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, // 법인격구분코드
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, // 판매유형코드
        @JsonProperty("SELL_TP_NM")
        String sellTpNm, // 판매유형명
        @JsonProperty("SELL_TP_DTL_CD")
        String sellTpDtlCd, // 판매유형상세코드
        @JsonProperty("SELL_TP_DTL_NM")
        String sellTpDtlNm, // 판매유형상세명
        @JsonProperty("SELL_INFLW_CHNL_DTL_CD")
        String sellInflwChnlDtlCd, // 판매유입채널상세코드
        @JsonProperty("SELL_INFLW_CHNL_DTL_NM")
        String sellInflwChnlDtlNm, // 판매유입채널상세명
        @JsonProperty("CNTR_DTL_STAT_CH_RSON_CD")
        String cntrDtlStatChRsonCd, // 계약상세상태변경사유코드
        @JsonProperty("CNTR_DTL_STAT_CH_RSON_NM")
        String cntrDtlStatChRsonNm, // 계약상세상태변경사유명
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo, // 계약자고객번호
        @JsonProperty("CNTR_CST_NM")
        String cntrCstNm, // 계약자고객명
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd, // 생년월일
        @JsonProperty("SEX_DV_CD")
        String sexDvCd, // 성별구분코드
        @JsonProperty("BASE_PD_CD")
        String basePdCd, // 기준상품코드
        @JsonProperty("BASE_PD_NM")
        String basePdNm, // 기준상품명
        @JsonProperty("SV_PD_CD")
        String svPdCd, // 서비스상품코드
        @JsonProperty("SV_PD_NM")
        String svPdNm, // 서비스상품명
        @JsonProperty("PD_QTY")
        Integer pdQty, // 상품수량
        @JsonProperty("PD_HCLSF_ID")
        String pdHclsfId, // 상품대분류ID
        @JsonProperty("PD_HCLSF_NM")
        String pdHclsfNm, // 상품대분류명
        @JsonProperty("PD_MCLSF_ID")
        String pdMclsfId, // 상품중분류ID
        @JsonProperty("PD_MCLSF_NM")
        String pdMclsfNm, // 상품중분류명
        @JsonProperty("PD_LCLSF_ID")
        String pdLclsfId, // 상품소분류ID
        @JsonProperty("PD_LCLSF_NM")
        String pdLclsfNm, // 상품소분류명
        @JsonProperty("PD_DCLSF_ID")
        String pdDclsfId, // 상품세분류ID
        @JsonProperty("PD_DCLSF_NM")
        String pdDclsfNm, // 상품세분류명
        @JsonProperty("CNTR_RCP_DT")
        String cntrRcpDt, // 계약접수일자
        @JsonProperty("CNTR_CNFM_DT")
        String cntrCnfmDt, // 계약확정일자
        @JsonProperty("CNTR_PD_STRTDT")
        String cntrPdStrtdt, // 상품시작일자
        @JsonProperty("CNTR_PD_ENDDT")
        String cntrPdEnddt, // 상품종료일자
        @JsonProperty("CNTR_CRAL_LOCARA_TNO")
        String cntrCralLocaraTno, // 휴대지역전화번호
        @JsonProperty("CNTR_MEXNO")
        String cntrMexno, // 휴대전화국번호
        @JsonProperty("CNTR_CRAL_IDV_TNO")
        String cntrCralIdvTno, // 휴대개별전화번호
        @JsonProperty("CNTR_LOCARA_TNO")
        String cntrLocaraTno, // 지역전화번호
        @JsonProperty("CNTR_EXNO")
        String cntrExno, // 전화국번호
        @JsonProperty("CNTR_IDV_TNO")
        String cntrIdvTno, // 개별전화번호
        @JsonProperty("CNTR_ADR_ID")
        String cntrAdrId, // 계약주소ID
        @JsonProperty("CNTR_ADR")
        String cntrAdr, // 계약주소
        @JsonProperty("CNTR_DTL_ADR")
        String cntrDtlAdr, // 계약상세주소
        @JsonProperty("CNTR_ADR_ZIP")
        String cntrAdrZip, // 계약우편번호
        @JsonProperty("IST_CST_NM")
        String istCstNm, // 설치고객명
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno, // 휴대지역전화번호
        @JsonProperty("MEXNO")
        String mexno, // 휴대전화국번호
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno, // 휴대개별전화번호
        @JsonProperty("LOCARA_TNO")
        String locaraTno, // 지역전화번호
        @JsonProperty("EXNO")
        String exno, // 전화국번호
        @JsonProperty("IDV_TNO")
        String idvTno, // 개별전화번호
        @JsonProperty("ADR_ID")
        String adrId, // 설치지주소ID
        @JsonProperty("ADR")
        String adr, // 설치지주소
        @JsonProperty("DTL_ADR")
        String dtlAdr, // 설치지상세주소
        @JsonProperty("ADR_ZIP")
        String adrZip, // 설치지우편번호
        @JsonProperty("VST_RQDT")
        String vstRqdt, // 방문요청일자
        @JsonProperty("SPP_MTHD_TP_CD")
        String sppMthdTpCd, // 배송방식유형코드
        @JsonProperty("SPP_MTHD_TP_CD_NM")
        String sppMthdTpCdNm, // 배송방식유형코드명
        @JsonProperty("SPP_ICHR_AOFFCE_CD")
        String sppIchrAoffceCd, // 배송담당사무소코드
        @JsonProperty("SPP_ICHR_AOFFCE_NM")
        String sppIchrAoffceNm, // 배송담당사무소명
        @JsonProperty("BOO_SELL_TP_CD")
        String booSellTpCd, // 예약판매유형코드
        @JsonProperty("CO_CD")
        String coCd, // 소속법인코드
        @JsonProperty("CO_NM")
        String coNm, // 소속법인코드명
        @JsonProperty("CNTR_TAM")
        Integer cntrTam, // 계약총액
        @JsonProperty("SELL_AMT")
        Integer sellAmt, // 판매금액
        @JsonProperty("DSC_AMT")
        Integer dscAmt, // 할인금액
        @JsonProperty("FNL_AMT")
        Integer fnlAmt, // 최종금액
        @JsonProperty("CNTR_AMT")
        Integer cntrAmt, // 계약금액
        @JsonProperty("ISTM_PCAM_AMT")
        Integer istmPcamAmt, // 할부원금금액
        @JsonProperty("MM_ISTM_AMT")
        Integer mmIstmAmt, // 월할부금액
        @JsonProperty("ISTM_INT_AMT")
        Integer istmIntAmt, // 할부이자금액
        @JsonProperty("DP_TOT_AMT")
        Integer dpTotAmt, // 입금금액
        @JsonProperty("BLTF_AMT")
        Integer bltfAmt, // 전금금액
        @JsonProperty("RFND_AMT")
        Integer rfndAmt, // 환불금액
        @JsonProperty("UC_TOT_AMT")
        Integer ucTotAmt, // 미수총금액
        @JsonProperty("FEE_ACKMT_BASE_AMT")
        Integer feeAckmtBaseAmt, // 수수료인정기준금액
        @JsonProperty("DLQ_MCN")
        Integer dlqMcn, // 연체개월수
        @JsonProperty("DLQ_AMT")
        Integer dlqAmt, // 연체금액
        @JsonProperty("CNTR_PTRM")
        Integer cntrPtrm, // 계약기간
        @JsonProperty("SPP_DUEDT")
        String sppDuedt, // 배송예정일자
        @JsonProperty("IST_DT")
        String istDt, // 설치일자
        @JsonProperty("REQD_DT")
        String reqdDt, // 철거일자
        @JsonProperty("CAN_DT")
        String canDt, // 취소일자
        @JsonProperty("SL_DT")
        String slDt, // 매출일자
        @JsonProperty("CPS_DT")
        String cpsDt, // 보상일자
        @JsonProperty("ENSM_CNTR_YN")
        String ensmCntrYn, // 임직원계약여부
        @JsonProperty("MCHN_CH_YN")
        String mchnChYn, // 기기변경여부
        @JsonProperty("MCHN_CH_TP_CD")
        String mchnChTpCd, // 기기변경유형코드
        @JsonProperty("MCHN_CH_TP_CD_NM")
        String mchnChTpCdNm, // 기기변경유형코드명
        @JsonProperty("MCHN_CH_OJ_CNTR_NO")
        String mchnChOjCntrNo, // 기기변경대상계약번호
        @JsonProperty("MCHN_CH_OJ_CNTR_SN")
        Integer mchnChOjCntrSn, // 기기변경대상계약일련번호
        @JsonProperty("SELL_EV_CD")
        String sellEvCd, // 판매행사코드
        @JsonProperty("CST_STLM_IN_MTH_CD")
        String cstStlmInMthCd, // 고객결제입력방법코드
        @JsonProperty("RENTAL_TN")
        Integer rentalTn, // 렌탈회차
        @JsonProperty("STPL_PTRM")
        Integer stplPtrm, // 약정기간
        @JsonProperty("FRISU_BFSVC_PTRM_N")
        Integer frisuBfsvcPtrmN, // 무상BS기간수
        @JsonProperty("FRISU_AS_PTRM_N")
        Integer frisuAsPtrmN, // 무상AS기간수
        @JsonProperty("URGT_OJ_YN")
        String urgtOjYn, // 긴급대상여부
        @JsonProperty("FRISU_YN")
        String frisuYn, // 무상여부
        @JsonProperty("ALNCMP_CD")
        String alncmpCd, // 제휴사코드
        @JsonProperty("ALNCMP_NM")
        String alncmpNm, // 제휴사명
        @JsonProperty("ALNC_DV")
        String alncDv, // 제휴구분
        @JsonProperty("CNTR_CH_RCP_ID")
        String cntrChRcpId, // 계약변경접수ID
        @JsonProperty("CNTR_CH_FSH_DT")
        String cntrChFshDt, // 계약변경완료일자
        @JsonProperty("CNTR_CH_TP_CD")
        String cntrChTpCd, // 계약변경유형코드
        @JsonProperty("CNTR_CH_TP_NM")
        String cntrChTpNm, // 계약변경유형명
        @JsonProperty("CTT_PSIC_ID")
        String cttPsicId, // 컨택담당자ID
        @JsonProperty("CTT_PSIC_NM")
        String cttPsicNm, // 컨택담당자명
        @JsonProperty("CTT_RS_CD")
        String cttRsCd, // 컨택결과코드
        @JsonProperty("CTT_RS_CD_NM")
        String cttRsCdNm, // 컨택결과코드명
        @JsonProperty("CTT_AK_RSON_CD")
        String cttAkRsonCd, // 컨택요청사유코드
        @JsonProperty("CTT_AK_RSON_NM")
        String cttAkRsonNm, // 컨택요청사유명
        @JsonProperty("CTT_MO_CN")
        String cttMoCn, // 컨택메모내용
        @JsonProperty("CTT_FSH_DTM")
        String cttFshDtm, // 컨택완료일시
        @JsonProperty("CTT_EXP_DV_CD")
        String cttExpDvCd, // 컨택예정구분코드
        @JsonProperty("CTT_EXP_DV_CD_NM")
        String cttExpDvCdNm, // 컨택예정구분코드명
        @JsonProperty("FNT_DT")
        String fntDt, // 이체일자
        @JsonProperty("DP_TP_CD")
        String dpTpCd, // 입금유형코드
        @JsonProperty("FNIT_APR_RS_CD")
        String fnitAprRsCd, // 금융기관승인결과코드
        @JsonProperty("SELL_PRTNR_NO")
        String sellPrtnrNo, // 판매파트너번호
        @JsonProperty("SELL_PRTNR_NM")
        String sellPrtnrNm, // 판매파트너명
        @JsonProperty("SELL_OG_CD")
        String sellOgCd, // 판매조직코드
        @JsonProperty("SELL_OG_NM")
        String sellOgNm, // 판매조직명
        @JsonProperty("CLCTAM_PRTNR_NO")
        String clctamPrtnrNo, // 집금파트너번호
        @JsonProperty("CLCTAM_PRTNR_NM")
        String clctamPrtnrNm, // 집금파트너명
        @JsonProperty("PRTNR_CNTR_YN")
        String prtnrCntrYn, // 판매자계약건여부
        @JsonProperty("TXINV_PBL_OJ_YN")
        String txinvPblOjYn, // 세금계산서발행대상여부
        @JsonProperty("SELL_DSC_TP_CD")
        String sellDscTpCd, // 판매할인유형코드
        @JsonProperty("SELL_DSC_TP_NM")
        String sellDscTpNm, // 판매할인유형명
        @JsonProperty("SELL_DSC_DV_CD")
        String sellDscDvCd, // 판매할인구분코드
        @JsonProperty("SELL_DSC_DV_NM")
        String sellDscDvNm, // 판매할인구분명
        @JsonProperty("SELL_DSCR_CD")
        String sellDscrCd, // 판매할인율코드
        @JsonProperty("SELL_DSC_CTR_AMT")
        Integer sellDscCtrAmt, // 판매할인조정금액
        @JsonProperty("ONE_PLUS_ONE_DL_YN")
        String onePlusOneDlYn, // 1+1삭제여부
        @JsonProperty("RENTAL_ADN_SV_CT")
        Integer rentalAdnSvCt, // 렌탈부가서비스건수
        @JsonProperty("RENTAL_ADN_SV_REQD_DT")
        String rentalAdnSvReqdDt, // 렌탈부가서비스철거일자
        @JsonProperty("STPL_YN")
        String stplYn, // 재약정여부
        @JsonProperty("STPL_CAN_DT")
        String stplCanDt, // 재약정취소일자
        @JsonProperty("STPL_ENDDT")
        String stplEnddt, // 재약정종료일자
        @JsonProperty("SDING_COMBI_YN")
        String sdingCombiYn, // 모종결합여부
        @JsonProperty("SV_PRD")
        String svPrd, // 서비스주기
        @JsonProperty("SV_TP_CD")
        String svTpCd, // 용도구분
        @JsonProperty("SCON_CN")
        String sconCn, // 특약내용
        @JsonProperty("SRCWT_TP_CD")
        String srcwtTpCd, // 상수원유형코드
        @JsonProperty("WTQLTY_TST_YN")
        String wtqltyTstYn, // 수질검사여부
        @JsonProperty("IST_PLC_TP_CD")
        String istPlcTpCd, // 설치장소유형코드
        @JsonProperty("WRFR_IST_MTH_CD")
        String wrfrIstMthCd, // 정수기설치방법코드
        @JsonProperty("IST_BZS_CD")
        String istBzsCd, // 설치업체
        @JsonProperty("SV_IST_PCSV_DV_CD")
        String svIstPcsvDvCd, // 설치택배구분
        @JsonProperty("FRISU_RCVRY_TP_CD")
        String frisuRcvryTpCd, // 무상복구유형코드
        @JsonProperty("FILT_ENDDT")
        String filtEnddt, // 최종멤버십종료일자
        @JsonProperty("SV_VST_PRD_CD")
        String svVstPrdCd, // 방문주기
        @JsonProperty("PCSV_PRD_CD")
        String pcsvPrdCd, // 택배주기
        @JsonProperty("ISTM_MCN")
        Integer istmMcn, // 할부개월수
        @JsonProperty("BFSVC_BZS_DV_CD")
        String bfsvcBzsDvCd, // BS업체구분코드
        @JsonProperty("CNTR_SFK_VAL")
        String cntrSfkVal, // 계약자세이프키값
        @JsonProperty("PRTNR_BZS_CD")
        String prtnrBzsCd, // 파트너업체코드
        @JsonProperty("PRTNR_BZS_NM")
        String prtnrBzsNm, // 파트너업체명
        @JsonProperty("PRTNR_BZS_CTPLC")
        String prtnrBzsCtplc, // 파트너업체연락처
        @JsonProperty("OJ_CNTR_NO")
        String ojCntrNo, // 대상계약번호
        @JsonProperty("OJ_CNTR_SN")
        Integer ojCntrSn, // 대상계약일련번호
        @JsonProperty("OJ_SELL_TP_CD")
        String ojSellTpCd, // 대상판매유형코드
        @JsonProperty("OJ_SELL_TP_NM")
        String ojSellTpNm, // 대상판매유형명
        @JsonProperty("OJ_IST_DT")
        String ojIstDt, // 대상설치일자
        @JsonProperty("STLM_TP_CD")
        String stlmTpCd, // 결제유형코드
        @JsonProperty("HCR_MSH_DV_CD")
        String hcrMshDvCd, // 홈케어멤버십구분코드
        @JsonProperty("CLTN_DT")
        String cltnDt, // 해약일자
        @JsonProperty("CLTN_ASK_DT")
        String cltnAskDt, // 해약요청일자
        @JsonProperty("RGLR_SPP_PRC_DV_CD")
        String rglrSppPrcDvCd, // 정기배송가격구분코드
        @JsonProperty("ISLND_YN")
        String islndYn, // 도서지역여부
        @JsonProperty("CO_IST_MNGT_DV_CD")
        String coIstMngtDvCd, // 회사설치관리구분코드
        @JsonProperty("CO_IST_USWY_CD")
        String coIstUswyCd, // 회사설치용도코드
        @JsonProperty("IST_AK_ARTC_MO_CN")
        String istAkArtcMoCn, // 설치요청사항메모내용
        @JsonProperty("FST_RGST_DTM")
        String fstRgstDtm, // 최초등록일시
        @JsonProperty("FST_RGST_USR_ID")
        String fstRgstUsrId, // 최초등록사용자ID
        @JsonProperty("FST_RGST_USR_NM")
        String fstRgstUsrNm, // 최초등록사용자명
        @JsonProperty("FNL_MDFC_DTM")
        String fnlMdfcDtm, // 최종수정일시
        @JsonProperty("FNL_MDFC_USR_ID")
        String fnlMdfcUsrId, // 최종수정사용자ID
        @JsonProperty("FNL_MDFC_USR_NM")
        String fnlMdfcUsrNm // 최종수정사용자명
    ) {}
}
