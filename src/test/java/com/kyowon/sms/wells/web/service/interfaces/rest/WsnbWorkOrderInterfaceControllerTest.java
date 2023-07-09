package com.kyowon.sms.wells.web.service.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.test.SpringTestSupport;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WsnbWorkOrderInterfaceControllerTest extends SpringTestSupport {

    private static final String BASE_URL = SnServiceConst.REST_INTERFACE_URL_V1 + "/work-orders";

    @Test
    @DisplayName("작업오더 생성 테스트 - 웰스웹")
    @Order(1)
    void createWorkOrdersForInstall() throws Exception {
        // given
        String nowDayString = DateUtil.getNowDayString();

        List<CreateOrderReq> dtos = new ArrayList<>();

        // 설치오더
        CreateOrderReq dto1 = CreateOrderReq.builder()
            .inChnlDvCd("4")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo("W20234316487")
            .cntrSn("1")
            .mtrStatCd("1")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        dtos.add(dto1);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new EaiWrapper(dtos)));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }
}
