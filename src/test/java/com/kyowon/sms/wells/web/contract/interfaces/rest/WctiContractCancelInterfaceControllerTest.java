package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelMembershipContractReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelRentalContractReq;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.test.SpringTestSupport;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

class WctiContractCancelInterfaceControllerTest extends SpringTestSupport {

    private final String BASE_URL = CtContractConst.INTERFACE_URL_V1;

    /*
        -- SAMPLE DATA
        SELECT C1.CNTR_NO
             , C1.CNTR_SN
             , C1.SELL_TP_CD
             , C1.CNTR_PTRM
             , C1.CNTR_PD_STRTDT
          FROM TB_SSCT_CNTR_DTL C1
         WHERE 1 = 1
           AND C1.SELL_TP_CD = '6'
           AND C1.CNTR_DTL_STAT_CD != '301'
           -- 렌털
           AND EXISTS (SELECT '1'
                         FROM TB_CBCL_WELLS_SL_MM_CL_IZ X1
                        WHERE C1.CNTR_NO = X1.CNTR_NO
                          AND C1.CNTR_SN = X1.CNTR_SN
                          AND X1.SL_CL_YM = TO_CHAR(ADD_MONTHS(SYSDATE, -1),'YYYYMM'))
            -- 멤버쉽(정기배송)
           AND EXISTS (SELECT '1'
                         FROM TB_CBCL_WELLS_SL_MM_CL_IZ X1
                        WHERE C1.CNTR_NO = X1.CNTR_NO
                          AND C1.CNTR_SN = X1.CNTR_SN
                          AND X1.SL_CL_YM = TO_CHAR(SYSDATE, 'YYYYMM'))
    */
    @Test
    @Commit
    @DisplayName("인터페이스 렌털계약 취소")
    void cancelRentalContract() throws Exception {
        SFLEXContext context = SFLEXContextHolder.getContext();
        UserSessionDvo user = context.getUserSession();

        String nowDayString = DateUtil.getNowDayString();
        CancelRentalContractReq req = CancelRentalContractReq.builder()
            .cntrNo("W20225673636")
            .cntrSn("1")
            .canDt(nowDayString)
            .rgstUsrId(user.getEmployeeIDNumber())
            .build();

        EaiWrapper<CancelRentalContractReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/cancel-rental-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }

    @Test
    @DisplayName("인터페이스 멤버쉽계약 취소")
    void cancelMembershipContract() throws Exception {
        SFLEXContext context = SFLEXContextHolder.getContext();
        UserSessionDvo user = context.getUserSession();

        String nowDayString = DateUtil.getNowDayString();
        CancelMembershipContractReq req = CancelMembershipContractReq.builder()
            .cntrNo("W20232291692")
            .cntrSn("1")
            .canDt(nowDayString)
            .rgstUsrId(user.getEmployeeIDNumber())
            .slCtrAmt("0")
            .dscDdctam("0")
            .filtDdctam("0")
            .build();

        EaiWrapper<CancelMembershipContractReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/cancel-membership-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }
}
