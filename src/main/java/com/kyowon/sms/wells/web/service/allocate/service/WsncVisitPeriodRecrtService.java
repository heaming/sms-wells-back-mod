package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncVisitPeriodRecrtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitPeriodRecrtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncVisitPeriodRecrtDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncVisitPeriodRecrtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncVisitPeriodRecrtService {

    private final WsncVisitPeriodRecrtMapper mapper;

    private final WsncVisitPeriodRecrtConverter converter;

    private final WsncBsPeriodChartService wsncBsPeriodChartService;

    public int saveVisitPeriodRecrt(Map<String, Object> param) throws Exception {
        WsncVisitPeriodRecrtDvo dvo = new WsncVisitPeriodRecrtDvo();
        dvo.setCntrNo(valueOfEmptyStr(param.get("PARAM1")));
        dvo.setCntrSn(valueOfEmptyStr(param.get("PARAM2")));

        log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] cntrNo ::: " + dvo.getCntrNo());
        log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] cntrSn ::: " + dvo.getCntrSn());

        return saveVisitPeriodRecrt(dvo);
    }

    @Transactional
    public int saveVisitPeriodRecrt(WsncVisitPeriodRecrtDto.SaveReq dto) throws Exception {
        return saveVisitPeriodRecrt(converter.mapPeriodRecrtSaveReqToDvo(dto));
    }

    @Transactional
    public int saveVisitPeriodRecrt(WsncVisitPeriodRecrtDvo req) throws Exception {
        /*
         *  1. 해당 계약건의 방문주기 삭제
         *  2. 해당 계약건의 주기표유형에 따라 분기하여 주기표 생성 (if 처리. 위쪽이 우선순위)
         *   2-1 배송인경우 : W-SV-S-0068 (홈카페 캡슐 B/S주기표를 생성) 수행
         *   2-2 모종인경우 : W-SV-S-0069 (웰스팜 모종 B/S주기표를 생성) 수행
         *   2-3 삼성전자 에어컨인 경우 : W-SV-S-0070 (삼성전자 에어컨의 정기 방문 주기 생성) 수행
         *   2-4 멤버십고객인 경우 : W-SV-S-0071 (정기 B/S주기표를 생성하는 작업)
         *   2-5 그 외 : W-SV-S-0072 (정기 B/S주기표를 생성)
         *  3. 메시지 출력
         *   3-1 정상 처리 된 경우 부모화면으로 : "정상처리 되었습니다." 메시지 전달
         *   3-2 에러 발생 시 : "주기표 생성 오류가 발생하였습니다." 메시지 전달
         */
        try {
            WsncVisitPeriodRecrtDvo dvo = mapper.selectPeriodPdInfo(req);

            if(dvo == null){
                return 1;
            }

            switch (dvo.getSvpdItemGr()) {
                //배송
                case  "12", "13", "14", "15", "16"
                    -> wsncBsPeriodChartService.processBsPeriodChartBs01(converter.mapVisitPeriodDvoToBsPeriodSearchReq(dvo));
                //모종
                case "11"
                    -> wsncBsPeriodChartService.processBsPeriodChartBs05(converter.mapVisitPeriodDvoToBsPeriodSearchReq(dvo));
                //삼성전자 에어컨
                case "삼성전자 에어컨"
                    -> wsncBsPeriodChartService.processBsPeriodChartBs04(converter.mapVisitPeriodDvoToBsPeriodSearchReq(dvo));
                //멤버십, 기타
                default
                    -> wsncBsPeriodChartService.processBsPeriodChartBs03(converter.mapVisitPeriodDvoToBsPeriodSearchReq(dvo), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("MSG_TXT_PERIOD_CHART_ERROR");    //주기표 생성 오류가 발생하였습니다.
        }

        return 1;
    }

    /*
     * String.valueOf() - Null일 경우 Empty String return
     */
    public String valueOfEmptyStr(Object obj){
        if(obj == null){
            return "";
        }
        return String.valueOf(obj);
    }

}
