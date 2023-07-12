package com.kyowon.sms.wells.web.service.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsAssignReqDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWorkOrderMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.test.SpringTestSupport;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WsnbWorkOrderInterfaceControllerTest extends SpringTestSupport {

    private static final String BASE_URL = SnServiceConst.REST_INTERFACE_URL_V1 + "/work-orders";

    private final WsnbWorkOrderMapper mapper;

    /* TEST DATA 준비 */
    // test/resources/service/interfaces/WorkOrderTestScript.sql

    @Commit
    @Test
    @DisplayName("작업오더 생성 테스트 - 판매 설치오더 신규")
    @Order(1)
    void createWorkOrdersForInstall() throws Exception {
        // TEST DATA
        String cntrNo = "W20232265840";
        String cntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        String nowDayString = DateUtil.getNowDayString();

        // 설치오더
        CreateOrderReq createDto = CreateOrderReq.builder()
            .inChnlDvCd("3")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd("1")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        // when & then
        callRestApi(List.of(createDto));
    }

    @Test
    @DisplayName("작업오더 생성 테스트 - 판매 설치오더 신규/수정/삭제")
    @Order(2)
    void createWorkOrdersForInstallAndModifyAndCancel() throws Exception {
        // TEST DATA
        String cntrNo = "W20233218171";
        String cntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        String nowDayString = DateUtil.getNowDayString();

        // 설치오더
        CreateOrderReq createDto = CreateOrderReq.builder()
            .inChnlDvCd("3")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd("1")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        // when & then
        List<String> results = callRestApi(List.of(createDto));

        Thread.sleep(1000L);
        String asIstOjNo = results.get(0);

        WsnbAsAssignReqDvo createAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();

        /* TEST CASE2(수정) */
        // given
        CreateOrderReq editDto = CreateOrderReq.builder()
            .inChnlDvCd("4")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd("2")
            .asIstOjNo(asIstOjNo)
            .vstRqdt(DateUtil.addDays(nowDayString, 3))
            .smsFwYn("Y")
            .build();

        // when
        callRestApi(List.of(editDto));

        // then
        WsnbAsAssignReqDvo editAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 작업배정번호 신규채번 체크
        Assertions.assertThat(createAsAssign.getCstSvAsnNo()).isNotEqualTo(editAsAssign.getCstSvAsnNo());

        Thread.sleep(1000L);

        /* TEST CASE3(삭제) */
        // given
        CreateOrderReq removeDto = CreateOrderReq.builder()
            .inChnlDvCd("4")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd("3")
            .asIstOjNo(asIstOjNo)
            .vstRqdt(DateUtil.addDays(nowDayString, 3))
            .smsFwYn("Y")
            .build();

        // when
        callRestApi(List.of(removeDto));

        // then
        WsnbAsAssignReqDvo removeAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 자료상태 삭제 상태 확인
        Assertions.assertThat(removeAsAssign.getMtrStatCd()).isEqualTo("3");
    }

    @Test
    @DisplayName("작업오더 생성 테스트 - 판매 웰스팜 설치오더 신규/삭제")
    @Order(1)
    void createWorkOrdersForWellsFarmInstall() throws Exception {
        // TEST DATA
        // 웰스팜 계약번호
        String cntrNo = "W20231854072";
        String cntrSn = "1";

        // 모종 계약번호
        //String sdingCntrNo = "W20230536953";
        //String sdingCntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        String nowDayString = DateUtil.getNowDayString();

        // 설치오더
        CreateOrderReq createDto = CreateOrderReq.builder()
            .inChnlDvCd("3")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd("1")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        // when & then
        List<String> results = callRestApi(List.of(createDto));

        Thread.sleep(1000L);
        String asIstOjNo = results.get(0);

        WsnbAsAssignReqDvo createAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();

        /* TEST CASE2(수정) */
        // given
        // 설치오더
        CreateOrderReq editDto = CreateOrderReq.builder()
            .inChnlDvCd("3")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .asIstOjNo(asIstOjNo)
            .mtrStatCd("2")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        // when & then
        callRestApi(List.of(editDto));

        /* TEST CASE3(삭제) */
        // given
        CreateOrderReq removeDto = CreateOrderReq.builder()
            .inChnlDvCd("3")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .asIstOjNo(asIstOjNo)
            .mtrStatCd("3")
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();

        // when & then
        callRestApi(List.of(removeDto));

        // then
        WsnbAsAssignReqDvo removeAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 자료상태 삭제 상태 확인
        Assertions.assertThat(removeAsAssign.getMtrStatCd()).isEqualTo("3");
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
