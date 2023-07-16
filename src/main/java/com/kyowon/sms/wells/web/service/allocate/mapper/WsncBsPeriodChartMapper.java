package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartReqDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartResDvo;

@Mapper
public interface WsncBsPeriodChartMapper {
    WsncBsPeriodChartResDvo selectPeriodChartBaseInfo(WsncBsPeriodChartDto.SearchReq dto);

    int selectBsPeriodChartBs03_04(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs03_05(WsncBsPeriodChartReqDvo dvo);

    List<WsncBsPeriodChartResDvo> selectBsPeriodChartBs03_06(WsncBsPeriodChartResDvo dto);

    List<WsncBsPeriodChartResDvo> selectBsPeriodChartBs03_07(WsncBsPeriodChartReqDvo dvo);

    List<WsncBsPeriodChartResDvo> selectBsPeriodChartBs04_07(WsncBsPeriodChartReqDvo dvo);

    List<WsncBsPeriodChartResDvo> selectBsPeriodChartBs01_07(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_08(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_09(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs03_10(String chekVstDt);

    String selectBsPeriodChartBs03_11(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_12(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_13(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_14(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_15(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_16(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_17(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_18(WsncBsPeriodChartReqDvo dvo);

    int selectBsPeriodChartBs03_19(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs03_20(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs03_21(WsncBsPeriodChartReqDvo dvo);

    int insertBsPeriodChart(WsncBsPeriodChartReqDvo dvo);

    int insertBsPeriodChartError(WsncBsPeriodChartReqDvo dvo);

    String selectPriorityVstDt(WsncBsPeriodChartDto.SearchReq dto);

    int selectPriorityVstDiff(String priorityVstDt, int chekCyclMths);

    String selectBsPeriodChartBs01_22(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs01_23(WsncBsPeriodChartReqDvo dvo);

    int insertBsPeriodChartBs01(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs05_22(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs05_23(WsncBsPeriodChartReqDvo dvo);

    List<WsncBsPeriodChartResDvo> selectBsPeriodChartBs05_07(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs05_24(WsncBsPeriodChartReqDvo dvo);

    String selectBsPeriodChartBs05_25(WsncBsPeriodChartReqDvo dvo);

    List<WsncBsPeriodChartResDvo> selectSpMkPlantSchedule(WsncBsPeriodChartDto.SearchReq dto);

    int saveSpMkPlantSchedule01(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule02(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule03(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule04(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule05(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule06(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule07(WsncBsPeriodChartResDvo dvo);

    int deleteSpMkPlantSchedule08(WsncBsPeriodChartResDvo dvo);

    int deleteSpMkPlantSchedule09(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule10(WsncBsPeriodChartResDvo dvo);

    int updateSpMkPlantSchedule11(WsncBsPeriodChartResDvo dvo);

    List<WsncBsPeriodChartResDvo> selectSpMkRedginsengSchedule(WsncBsPeriodChartDto.SearchReq dto);

    int saveSpMkRedginsengSchedule01(WsncBsPeriodChartResDvo dvo);

    int deleteSpMkRedginsengSchedule02(WsncBsPeriodChartResDvo dvo);

    int updateSpMkRedginsengSchedule03(WsncBsPeriodChartResDvo dvo);

    int updateSpMkRedginsengSchedule04(WsncBsPeriodChartResDvo dvo);

    int updateSpMkRedginsengSchedule05(WsncBsPeriodChartResDvo dvo);

    int updateSpMkRedginsengSchedule06(WsncBsPeriodChartResDvo dvo);

    int updateSpMkRedginsengSchedule07(WsncBsPeriodChartResDvo dvo);

    int deleteSpMkRedginsengSchedule08(WsncBsPeriodChartResDvo dvo);

    int updateSpMkRedginsengSchedule09(WsncBsPeriodChartResDvo dvo);

}
