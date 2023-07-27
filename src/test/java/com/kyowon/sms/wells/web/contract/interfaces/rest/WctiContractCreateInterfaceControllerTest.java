package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentReq;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.test.SpringTestSupport;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

class WctiContractCreateInterfaceControllerTest extends SpringTestSupport {
    private final String BASE_URL = CtContractConst.INTERFACE_URL_V1 + "/contracts";

    /*
        -- SAMPLE DATA 추출
        SELECT C1.CNTR_NO
             , C1.CNTR_CST_NO
             , C1.COPN_DV_CD
             , C1.SELL_INFLW_CHNL_DTL_CD
             , C1.SELL_OG_TP_CD
             , C1.SELL_PRTNR_NO
             , C2.CO_CD
             , C2.BASE_PD_CD
             , C2.PD_QTY
             , C2.PD_BASE_AMT
             , C2.CNTR_AMT
             , C6.RCGVP_KNM
             , A1.NEW_ADR_ZIP
             , A1.RNADR
             , A1.RDADR
             , C4.FRISU_BFSVC_PTRM_UNIT_CD
             , C4.FRISU_BFSVC_PTRM_N
          FROM TB_SSCT_CNTR_BAS C1
        INNER JOIN TB_SSCT_CNTR_DTL C2
            ON C1.CNTR_NO = C2.CNTR_NO
        INNER JOIN TB_SSCT_CNTR_ADR_REL C5
            ON C2.CNTR_NO = C5.DTL_CNTR_NO
           AND C2.CNTR_SN = C5.DTL_CNTR_SN
           AND C5.ADRPC_TP_CD = '3'
           AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN C5.VL_STRT_DTM AND C5.VL_END_DTM
        INNER JOIN TB_SSCT_CNTR_ADRPC_BAS C6
            ON C5.CNTR_ADRPC_ID = C6.CNTR_ADRPC_ID
        INNER JOIN TB_GBCO_ADR_BAS A1
            ON C6.ADR_ID = A1.ADR_ID
        INNER JOIN TB_SSCT_CNTR_WELLS_DTL C4
            ON C2.CNTR_NO = C4.CNTR_NO
           AND C2.CNTR_SN = C4.CNTR_SN
         WHERE 1 = 1
           AND C1.SELL_INFLW_CHNL_DTL_CD = '1010'
         --  AND C1.SELL_OG_TP_CD = 'ALC'
           AND EXISTS (SELECT '1'
                        FROM TB_PDBS_PD_PRC_DTL X2
                  INNER JOIN TB_PDBS_PD_PRC_FNL_DTL X3
                          ON X2.PD_PRC_DTL_ID = X3.PD_PRC_DTL_ID
                       WHERE 1 = 1
                         AND C2.BASE_PD_CD = X2.PD_CD)
           AND C1.CNTR_NO = 'W20225388200'
     */

    @Test
    @DisplayName("일시불 wells 계약생성")
    void createContractForSinglePayment() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .cntrNo("W2022538820X")
            .cntrSn("1")
            .rcpChnlDtl("2010")
            .cstKnm("조순옥")
            .cntrtAdrDvCd("1")
            .cntrtCralLocaraTno("010")
            .cntrtMexnoEncr("2222")
            .cntrtCralIdvTno("3333")
            .cntrtZip("16332")
            .cntrtBasAdr("경기 수원시 장안구 천천로22번길 34")
            .cntrtDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .cntrCstNo("031095096")
            .cstKnm("조순옥")
            .istllKnm("조순옥")
            .istAdrDvCd("1")
            .istCstCralLocaraTno("010")
            .istCstMexnoEncr("2222")
            .istCstCralIdvTno("3333")
            .istZip("16332")
            .istBasAdr("경기 수원시 장안구 천천로22번길 34")
            .istDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .pdCd01("WP05120580")
            .pdQty01("1")
            .subscAmt1("1000")
            .dpDvCd1("01")
            .ag1("Y")
            .ag2("Y")
            .ag3("Y")
            .ag4("Y")
            .ag5("Y")
            .dscDv("2")
            .uswy("0")
            .mngtPrd("6")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/single-payment")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }

    @Test
    @DisplayName("렌탈 wells 계약생성")
    void createContractForRental() throws Exception {
        // given
        CreateRentalReq req = CreateRentalReq.builder()
            .cntrNo("W2022538820X")
            .cntrSn("1")
            .rcpChnlDtl("2010")
            .cstNm("조순옥")
            .adrDvCd("1")
            .cphonLocaraTno("010")
            .cphonExnoEncr("2222")
            .cphonIdvTno("3333")
            .zip("16332")
            .basAdr("경기 수원시 장안구 천천로22번길 34")
            .dtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .cstNo("031095096")
            .istCstNm("조순옥")
            .istAdrDvCd("1")
            .istCphonLocaraTno("010")
            .istCphonExnoEncr("2222")
            .istCphonIdvTno("3333")
            .istZip("16332")
            .istBasAdr("경기 수원시 장안구 천천로22번길 34")
            .istDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .pdCd("WP05120580")
            .pdQty("1")
            .cardAmt1("1000")
            .crcdnoEncr1("11122233334444")
            .cardIstmMcn1("1")
            .cdonrNm1("조순옥")
            .dscDvCd("2")
            .dscTpCd("1")
            .vstPrdCd("6")
            .uswyCd("0")
            .prtnrNo("1650501")
            .pifClcnUAgYn("Y")
            .pifThpOfrAgYn("Y")
            .mrktUtlzAgYn("Y")
            .fstrAgYn("Y")
            .pifBizFstrAgYn("Y")
            .cntrtRel("0")
            .txinvPblOjYn("Y")
            .txinvCphonLocaraTno("010")
            .txinvCphonExnoEncr("2222")
            .txinvCphonIdvTno("3333")
            .txinvEmadr("a@kyowon.co.kr")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/rental")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }
}
