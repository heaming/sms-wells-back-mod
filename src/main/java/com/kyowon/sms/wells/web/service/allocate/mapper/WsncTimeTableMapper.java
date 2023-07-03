package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;
import java.util.Optional;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * W-MP-U-0186P01 타임테이블 일정선택
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-05-08
 */
@Mapper
public interface WsncTimeTableMapper {

    /**
     * 타임테이블 일정 조회
     *
     * @param req : 조회파라메터
     * @return 조회결과
     */
    List<WsncTimeTableDto.Days> selectTimeTableDates(String baseYm);

    //WsncTimeTablePackageDvo selectPackage(String sidingCd, String baePdCd, String cntrNo);
    Optional<WsncTimeTableCntrDvo> selectContract(WsncTimeTableDvo dvo);

    Optional<WsncTimeTableProductDvo> selectProduct(WsncTimeTableDvo dvo);

    //String selectOjPdCd(String saleCd);

    //List<WsncTimeTableSidingDaysDvo> selectSidingDaysKiwim(String saleCd);

    List<WsncTimeTableSidingDaysDvo> selectSidingDays(WsncTimeTableDvo dvo);//getMojongDays

    //List<WsncTimeTableMonthScheduleDvo> selectMonthSchedule(String prtnrNo);

    List<WsncTimeTableDisableDaysDvo> selectDisableDays(WsncTimeTableDvo dvo);

    List<WsncTimeTableSidingDaysDvo> selectSidingDaysForSpay(WsncTimeTableDvo dvo);

    List<WsncTimeTableMonthScheduleDvo>selectMonthSchedule(WsncTimeTableDvo dvo);

    String selectFnSvpdLocaraPrtnr01(WsncTimeTableDvo dvo);

    String selectFnSvpdLocaraPrtnrOwr01(WsncTimeTableDvo dvo);

    String selectFnSvpdLocaraPrtnrBs01(WsncTimeTableDvo dvo);

    Optional<WsncTimeTableRpbLocaraPsicDvo> selectRpbLocaraPsic(WsncTimeTableDvo dvo);

    WsncTimeTablePsicDvo selectPsics(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<WsncTimeTableAssignTimeDvo> selectAssignTimes(WsncTimeTableRpbLocaraPsicDvo dvo);

    String selectEmpTWrkCnt(WsncTimeTableRpbLocaraPsicDvo dvo);
    String selectDegWrkCnt(WsncTimeTableRpbLocaraPsicDvo dvo);
    String selectWkHhCd(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<String> selectOffDays(WsncTimeTableDvo dvo);

    String selectVstDvCd(WsncTimeTableDvo dvo);
}
