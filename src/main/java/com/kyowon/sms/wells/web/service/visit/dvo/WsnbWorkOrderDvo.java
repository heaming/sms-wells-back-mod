package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * 저장 dvo
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Setter
@Getter
public class WsnbWorkOrderDvo {
    String inChnlDvCd; /* 입력채널구분코드 */
    String asIstOjNo; /* AS설치대상번호 */
    String svBizHclsfCd; /* 서비스업무대분류코드 */
    String rcpdt; /* 접수일자 */
    String mtrStatCd; /* 자료상태코드 */
    String svBizDclsfCd; /* 서비스업무세분류코드 */
    String urgtDvCd; /* 긴급여부 */
    String vstRqdt; /* 방문요청일자 */
    String vstAkHh; /* 방문요청시간 */
    String smsFwYn; /* SMS발송여부 */
    int svEtAmt; /* 서비스예상금액 */
    String dpDvCd; /* 입금구분코드 */
    String cnslTpHclsfCd; /* 상담유형대분류코드 */
    String cnslTpMclsfCd; /* 상담유형중분류코드 */
    String cnslTpLclsfCd; /* 상담유형소분류코드 */
    String cnslDtlpTpCd; /* 상담세부유형코드 */
    String cnslMoCn; /* 상담메모내용 */
    String cstCnrRefriDvCd; /* 기타정보 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String rcgvpKnm; /* 고객한글명 */
    String rnadr; /* 고객도로명주소 */
    String rdadr; /* 고객도로명상세주소 */
    String pdCd; /* 상품코드 */
    String saleCd; /* 기간계 상품코드 */
    String svPrd; /* 서비스주기 */
    String cpsYn; /* 기기변경여부 */
    String retYn; /* 회수유무 */
    String cntrNoB; /* 보상계약번호 */
    String cntrSnB; /* 보상계약번호순번 */
    String updtPsicDvCd; /* 업데이트담당자구분코드 */
    String alncBzsCd; /* 제휴업체코드 */
    String payNm; /* 입금자명 */
    String partList;
    String userId;
    String ogTpCd;
    String rcpOgTpCd;
    /* service애서 만들어주는 값 */
    String newSvBizDclsfCd; /* 서비스업무세분류코드(V_WRK_TYP_DTL) */
    String newWkAcpteStatCd; /* V_CFRM_STUS_WRK */
    String newMtrStatCd; /* V_DATA_STUS */
    String newWkAcpteDt; /* V_AC221_CFRM_DT */
    String newWkPrgsStatCd; /* V_AC221_PROC_STUS */
    String newRcgvpKnm; /* V_CUST_NM */
    String newInChnlDvCd; /* V_AC211_IN_GB */
    String newSvBizHclsfCd; /* V_AC211_WRK_GB */
    String newRcpdt; /* V_AC211_WRK_DT */
    String newReq; /* V_AC211_SEQ */
    String puCstSvAsnNo; /* wrk_GB||wrk_DT||lpad(seq,10,''0'') */
    String newAsIstOjNo; /* 입력구분코드(2)+서비스업무대분류코드(2)+접수일자(8)+일련번호(8) */
    String asnSvBizHclsfCd; /* V_AC221_DATA_GB */
    String asnDt; /* V_AC221_ORD_DT */
    String asnReq; /* V_AC221_ORD_SEQ */
    String asnCstSvAsnNo; /* DATA_GB||ORD_DT||lpad(ord_seq,10,''0'') */
    @DBDecField
    String mexnoEncr; /* AC021_HNO_NO2(복호화) */
    String part; /* 자재,수량,금액(partList쪼갠거)  */
    String partCd; /* 자재 */
    String partQty; /* 수량 */
    String partAmt; /* 금액 */
    String itemKndCd;
    String refriYn;
    String ichrCnrCd; /* V_AC110_SVC_CD */
    String ichrPrtnrNo; /* V_AC110_EMP_ID */
    String ichrOgTpCd; /*  */
    String rpbLocaraCd; /* V_AC142_LOCAL_GB */
    int expMat; /* V_FA001_CNT */
    int sdingExpMat; /* V_FA001_P_CNT */
    int expMatSum; /* V_FA001_SUM_AMT */
    int sppPlanSn; /* V_FA101_SEQ */
    String vstDtChk; /* GET_PACKING_DAY(P_VST_DT_REQ) */
    String pdSize; /* GET_ST101_SIZE */

    // 계약정보 Extension
    String istDt;
    String reqdDt;
    String stopYn;
    String cntrCstNo;
    String newPdCd; /* V_GDS_CD */
    String pdGrpCd;
    String basePdCd;
    String basePdNm;

    /* 계약정보 업데이트용 */
    String adrId;
    String cralLocaraTno;
    String mexno;
    String cralIdvTno;
    String locaraTno;
    String exno;
    String idvTno;

}
