package com.kyowon.sms.wells.web.service.allocate.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableTimeChoDto;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

import java.io.InputStream;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RequiredArgsConstructor
public class WsncTimeTableServiceTest extends SpringTestSupport {

    private static final String BASE_URL = SnServiceConst.REST_URL_V1 + "/as-work-details";
    private final WsncTimeTableService service;

    @Test
    @DisplayName("타임테이블 시간선택")
    void selectTimeCho() throws Exception {
        TypeReference<WsncTimeTableTimeChoDto.FindReq> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class
            .getResourceAsStream("/service/allocate/WsncTimeTableTimeCho.json");
        WsncTimeTableTimeChoDto.FindReq dto = objectMapper.readValue(inputStream, typeReference);

        // when
        MockHttpServletRequestBuilder request = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        // then
//        String nowDayString = DateUtil.getNowDayString();
//        WsncTimeTableTimeChoDto asWorkDetail = service.getAsWorkDetailByPk(dto.cstSvAsnNo());
//        Assertions.assertThat(asWorkDetail.getWkPrgsStatCd()).isEqualTo("20");
//        Assertions.assertThat(asWorkDetail.getRglvlGdCd()).isNotBlank();
//        Assertions.assertThat(asWorkDetail.getIstDt()).isEqualTo(nowDayString);

    }

}
