package com.kyowon.sms.wells.web.service.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderRes;
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
        /* TEST CASE1(신규생성) */
        // given
        String nowDayString = DateUtil.getNowDayString();

        // 설치오더
        CreateOrderReq createDto = CreateOrderReq.builder()
            .inChnlDvCd("4")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo("W20234316487")
            .cntrSn("1")
            .mtrStatCd("1")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        // when & then
        List<String> results = callRestApi(List.of(createDto));

        Thread.sleep(1000L);

        /* TEST CASE2(수정) */
        // given
        String asIstOjNo = results.get(0);

        CreateOrderReq editDto = CreateOrderReq.builder()
            .inChnlDvCd("4")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo("W20234316487")
            .cntrSn("1")
            .mtrStatCd("2")
            .asIstOjNo(asIstOjNo)
            .vstRqdt(DateUtil.addDays(nowDayString, 4))
            .smsFwYn("Y")
            .build();

        // when & then
        callRestApi(List.of(editDto));

        /* TEST CASE3(삭제) */
    }

    private List<String> callRestApi(List<CreateOrderReq> dtos) throws Exception {
        MockHttpServletRequestBuilder request = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new EaiWrapper(dtos)));

        String result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andReturn().getResponse().getContentAsString();

        EaiWrapper<List<CreateOrderRes>> response = objectMapper.readValue(result, new TypeReference<>() {});

        return response.getBody().stream()
            .map(x -> x.asIstOjNo())
            .collect(Collectors.toList());
    }
}
