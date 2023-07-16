package com.kyowon.sms.wells.web.service.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
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
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAssignAsWorkDvo;
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

    //@Commit
    @Test
    @DisplayName("작업오더 - 판매 설치오더 신규")
    @Order(1)
    void createWorkOrdersForInstall() throws Exception {
        // TEST DATA
        String cntrNo = "W20232265840";
        String cntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        CreateOrderReq createDto = getSalesRequest(cntrNo, cntrSn, "1", "", "1");

        // when & then
        callRestApi(List.of(createDto));
    }

    @Test
    @DisplayName("작업오더 - 판매 설치오더 신규/수정/삭제")
    @Order(2)
    void createWorkOrdersForInstallAndModifyAndCancel() throws Exception {
        // TEST DATA
        String cntrNo = "W20233218171";
        String cntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        CreateOrderReq createDto = getSalesRequest(cntrNo, cntrSn, "1", "", "1");

        // when & then
        List<String> results = callRestApi(List.of(createDto));

        String asIstOjNo = results.get(0);

        WsnbAssignAsWorkDvo createAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();

        /* TEST CASE2(수정) */
        // given
        CreateOrderReq editDto = getSalesRequest(cntrNo, cntrSn, "2", asIstOjNo, "1");

        // when
        callRestApi(List.of(editDto));

        // then
        WsnbAssignAsWorkDvo editAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 작업배정번호 신규채번 체크
        Assertions.assertThat(createAsAssign.getCstSvAsnNo()).isNotEqualTo(editAsAssign.getCstSvAsnNo());

        /* TEST CASE3(삭제) */
        // given
        CreateOrderReq removeDto = getSalesRequest(cntrNo, cntrSn, "3", asIstOjNo, "1");

        // when
        callRestApi(List.of(removeDto));

        // then
        WsnbAssignAsWorkDvo removeAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 자료상태 삭제 상태 확인
        Assertions.assertThat(removeAsAssign.getMtrStatCd()).isEqualTo("3");
    }

    @Test
    @DisplayName("작업오더 - 판매 웰스팜 설치오더 신규/삭제")
    @Order(3)
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
        CreateOrderReq createDto = getSalesRequest(cntrNo, cntrSn, "1", "", "1");

        // when & then
        List<String> results = callRestApi(List.of(createDto));
        String asIstOjNo = results.get(0);

        /* TEST CASE2(수정) */
        // given
        CreateOrderReq editDto = getSalesRequest(cntrNo, cntrSn, "2", asIstOjNo, "1");

        // when & then
        callRestApi(List.of(editDto));

        /* TEST CASE3(삭제) */
        // given
        CreateOrderReq removeDto = getSalesRequest(cntrNo, cntrSn, "3", asIstOjNo, "1");

        // when & then
        callRestApi(List.of(removeDto));

        // then
        WsnbAssignAsWorkDvo removeAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 자료상태 삭제 상태 확인
        Assertions.assertThat(removeAsAssign.getMtrStatCd()).isEqualTo("3");
    }

    @Test
    @DisplayName("작업오더 - 판매 웰스팜 설치오더 신규/삭제(당일취소)")
    @Order(4)
    void createWorkOrdersForWellsFarmInstallTodayCancel() throws Exception {
        // TEST DATA
        // 웰스팜 계약번호
        String cntrNo = "W20231854072";
        String cntrSn = "1";

        // 모종 계약번호
        //String sdingCntrNo = "W20230536953";
        //String sdingCntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        CreateOrderReq createDto = getSalesRequest(cntrNo, cntrSn, "1", "", "1");

        // when & then
        List<String> results = callRestApi(List.of(createDto));
        String asIstOjNo = results.get(0);

        /* TEST CASE2(수정) */
        // given
        CreateOrderReq editDto = getSalesRequest(cntrNo, cntrSn, "2", asIstOjNo, "1");

        // when & then
        callRestApi(List.of(editDto));

        /* TEST CASE3(삭제) */
        // given
        CreateOrderReq removeDto = getSalesRequest(cntrNo, cntrSn, "3", asIstOjNo, "9");

        // when & then
        callRestApi(List.of(removeDto));
    }

    @Test
    @DisplayName("작업오더 - 고객센터 AS오더생성(자사회수 + 투입예정자재처리)")
    @Order(5)
    void createWorkOrdersForCustomerCenter() throws Exception {
        // TEST DATA
        String cntrNo = "W20230047394";
        String cntrSn = "1";

        /* TEST CASE1(신규생성) */
        // given
        // 설치오더
        CreateOrderReq createDto = getCustomerCenterRequest(cntrNo, cntrSn, "1", "");

        // when & then
        List<String> results = callRestApi(List.of(createDto));

        String asIstOjNo = results.get(0);

        WsnbAssignAsWorkDvo createAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();

        /* TEST CASE2(수정) */
        // given
        CreateOrderReq editDto = getCustomerCenterRequest(cntrNo, cntrSn, "2", asIstOjNo);

        // when
        callRestApi(List.of(editDto));

        // then
        WsnbAssignAsWorkDvo editAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 작업배정번호 신규채번 체크
        Assertions.assertThat(createAsAssign.getCstSvAsnNo()).isNotEqualTo(editAsAssign.getCstSvAsnNo());

        /* TEST CASE3(삭제) */
        // given
        CreateOrderReq removeDto = getCustomerCenterRequest(cntrNo, cntrSn, "3", asIstOjNo);

        // when
        callRestApi(List.of(removeDto));

        // then
        WsnbAssignAsWorkDvo removeAsAssign = mapper.selectAsAssignByPk(asIstOjNo).orElseThrow();
        // 1) 자료상태 삭제 상태 확인
        Assertions.assertThat(removeAsAssign.getMtrStatCd()).isEqualTo("3");
    }

    private CreateOrderReq getCustomerCenterRequest(String cntrNo, String cntrSn, String mtrStatCd, String asIstOjNo)
        throws ParseException {

        String nowDayString = DateUtil.getNowDayString();
        return CreateOrderReq.builder()
            .inChnlDvCd("1")
            .svBizHclsfCd("1")
            .svBizDclsfCd("1121")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd(mtrStatCd)
            .asIstOjNo(asIstOjNo)
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .cnslTpHclsfCd("EA01")
            .cnslTpMclsfCd("E111")
            .cnslTpLclsfCd("EC10")
            .cnslMoCn("불량고객입니다. 조심하세요.")
            .prchsMatList("A|1|1000,B|1|2000")
            .smsFwYn("Y")
            .build();
    }

    private CreateOrderReq getSalesRequest(
        String cntrNo, String cntrSn, String mtrStatCd, String asIstOjNo, String svBizHclfCd
    )
        throws ParseException {
        String nowDayString = DateUtil.getNowDayString();
        return CreateOrderReq.builder()
            .inChnlDvCd("3")
            .svBizHclsfCd(svBizHclfCd)
            .svBizDclsfCd("1110")
            .cntrNo(cntrNo)
            .cntrSn(cntrSn)
            .mtrStatCd(mtrStatCd)
            .asIstOjNo(asIstOjNo)
            .vstRqdt(DateUtil.addDays(nowDayString, 2))
            .smsFwYn("Y")
            .build();
    }

    private List<String> callRestApi(List<CreateOrderReq> dtos) throws Exception {
        Thread.sleep(1000L);

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
