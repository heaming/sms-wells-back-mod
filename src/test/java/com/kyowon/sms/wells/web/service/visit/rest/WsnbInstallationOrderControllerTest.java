package com.kyowon.sms.wells.web.service.visit.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
            .inChnlDvCd("3")
            .asIstOjNo("")
            .svBizHclsfCd("1")
            .svBizDclsfCd("3420")
            .urgtYn("N")
            .vstRqdt("20230724")
            .vstAkHh("0910")
            .smsFwYn("N")
            .cnslMoCn("asdasd")
            .ogTpCd("W06")
            .userId("1750966")
            .cntrNo("W20217003935")
            .cntrSn("1")
            .rcpOgTpCd("W01")
            .rcpdt("20230720")
            .mtrStatCd("1")
            .build();

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(List.of(dto)));

        String result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
    }
}
