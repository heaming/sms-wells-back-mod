package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;
import java.util.Optional;

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
    List<WsncTimeTableDaysDvo> selectTimeTableDates(String baseYm);

    //WsncTimeTablePackageDvo selectPackage(String sidingCd, String baePdCd, String cntrNo);
    Optional<WsncTimeTableCntrDvo> selectContract(String cntrNo, String cntrSn, String sellDate);

    Optional<WsncTimeTableProductDvo> selectProduct(String basePdCd, String pdctPdCd);

    //String selectOjPdCd(String saleCd);

    //List<WsncTimeTableSidingDaysDvo> selectSidingDaysKiwim(String saleCd);

    List<WsncTimeTableSidingDaysDvo> selectSidingDays(String saleCd);//getMojongDays

    //List<WsncTimeTableMonthScheduleDvo> selectMonthSchedule(String prtnrNo);

    List<WsncTimeTableDisableDaysDvo> selectDisableDays(WsncTimeTableParamDvo dvo);

    List<WsncTimeTableSidingDaysDvo> selectSidingDaysForSpay(
        String lcst09,
        String sellDate,
        String basePdCd,
        String dataGb,
        String lcpkag,
        String cntrNo
    );

    List<WsncTimeTableMonthScheduleDvo>selectMonthSchedule(String prtnrNo);

    String selectFnSvpdLocaraPrtnr01(String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate);

    String selectFnSvpdLocaraPrtnrOwr01(String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate);

    String selectFnSvpdLocaraPrtnrBs01(
        String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate, String vstGb
    );

    Optional<WsncTimeTableRpbLocaraPsicDvo> selectRpbLocaraPsic(WsncTimeTableParamDvo dvo);

    WsncTimeTablePsicDataDvo selectPsicData(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<WsncTimeTableAssignTimeDvo> selectAssignTime(WsncTimeTableRpbLocaraPsicDvo dvo);

    String selectEmpTWrkCnt(WsncTimeTableRpbLocaraPsicDvo dvo);
    String selectDegWrkCnt(WsncTimeTableRpbLocaraPsicDvo dvo);
    String selectWkHhCd(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<String> selectOffDays(WsncTimeTableParamDvo dvo);

    String selectVstDvCd(String cstSvAsnNo);
}
