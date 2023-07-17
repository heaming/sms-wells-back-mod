package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;
import java.util.Optional;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gs.piit122 김동엽
 * @since 2023-05-08
 */
@Mapper
public interface WsncTimeTableMapper {

    List<WsncTimeTableDaysDvo> selectTimeTableDates(String baseYm);

    //WsncTimeTablePackageDvo selectPackage(String sidingCd, String baePdCd, String cntrNo);
    Optional<String> selectAdrId(String cntrNo);

    List<WsncTimeTableCntrDvo> selectContract(WsncTimeTableDvo dvo);

    Optional<WsncTimeTableProductDvo> selectProduct(String svDvCd, String basePdCd);

    List<WsncTimeTableSidingDaysDvo> selectSidingDays(String basePdCd);//getMojongDays

    List<WsncTimeTableDisableDaysDvo> selectDisableDays(WsncTimeTableDvo dvo);

    List<WsncTimeTableSidingDaysDvo> selectSidingDaysForSpay(
        String sdingCombin,
        String sellDate,
        String basePdCd,
        String pdctPdCd,
        String svDvCd,
        String cntrNo
    );

    List<WsncTimeTableMonthScheduleDvo> selectMonthSchedule(WsncTimeTableDvo dvo);

    String selectFnSvpdLocaraPrtnr01(WsncTimeTableDvo dvo);

    String selectFnSvpdLocaraPrtnrOwr01(WsncTimeTableDvo dvo);

    String selectFnSvpdLocaraPrtnrBs01(WsncTimeTableDvo dvo);

    Optional<WsncTimeTableRpbLocaraPsicDvo> selectRpbLocaraPsic(WsncTimeTableDvo dvo);

    WsncTimeTablePsicDvo selectPsic(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<WsncTimeTableAssignTimeDvo> selectAssignTimes(WsncTimeTableRpbLocaraPsicDvo dvo);

    String selectEmpTWrkCnt(WsncTimeTableRpbLocaraPsicDvo dvo);

    String selectDegWrkCnt(WsncTimeTableRpbLocaraPsicDvo dvo);

    String selectWkHhCd(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<String> selectOffDays(WsncTimeTableDvo dvo);

    String selectVstDvCd(WsncTimeTableDvo dvo);
}
