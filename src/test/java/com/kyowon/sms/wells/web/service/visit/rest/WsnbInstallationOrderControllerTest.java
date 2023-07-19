package com.kyowon.sms.wells.web.service.visit.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class WsnbInstallationOrderControllerTest extends SpringTestSupport {

    private static final String BASE_URL = SnServiceConst.REST_URL_V1 + "/installation-works";

    @Test
    @DisplayName("타임테이블 작업오더 생성")
    void saveInstallationOrder() throws Exception {
        // given
        SaveReq dto = SaveReq.builder()
            .inflwChnl("3")
            .asIstOjNo("312023071900000000")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .urgtYn("N")
            .vstRqdt("20230720")
            .vstAkHh("0910")
            .smsFwYn("N")
            .cnslMoCn("asdasd")
            .ogTpCd("W06")
            .userId("1738565")
            .cntrNo("W20234900311")
            .cntrSn("1")
            .rcpOgTpCd("W01")
            .rcpdt("20230719")
            .mtrStatCd("")
            .build();

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        String result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
    }
}
