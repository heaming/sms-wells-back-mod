package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableAssignTimeDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableCntrDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableDisableDaysDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableProductDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTablePsicDataDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableRpbLocaraPsicDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesParamDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSidingDaysDvo;

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
    List<WsncTimeTableSalesDvo> selectTimeTableDates(WsncTimeTableSalesDto.FindReq req);

    //WsncTimeTablePackageDvo selectPackage(String sidingCd, String baePdCd, String cntrNo);
    Optional<WsncTimeTableCntrDvo> selectContract(String cntrNo, String cntrSn);

    WsncTimeTableProductDvo selectProduct(String basePdCd, String pdctPdCd);

    //String selectOjPdCd(String saleCd);

    //List<WsncTimeTableSidingDaysDvo> selectSidingDaysKiwim(String saleCd);

    List<WsncTimeTableSidingDaysDvo> selectSidingDays(String saleCd);//getMojongDays

    //List<WsncTimeTableMonthScheduleDvo> selectMonthSchedule(String prtnrNo);

    List<WsncTimeTableDisableDaysDvo> selectDisableDays(WsncTimeTableSalesParamDvo dvo);

    List<WsncTimeTableSidingDaysDvo> selectSidingDaysForSpay(
        String lcst09,
        String sellDate,
        String basePdCd,
        String dataGb,
        String lcpkag,
        String cntrNo
    );

    String selectFnSvpdLocaraPrtnr01(String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate);

    String selectFnSvpdLocaraPrtnrOwr01(String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate);

    String selectFnSvpdLocaraPrtnrBs01(
        String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate, String vstGb
    );

    WsncTimeTableRpbLocaraPsicDvo selectRpbLocaraPsic(WsncTimeTableSalesParamDvo dvo);

    List<WsncTimeTablePsicDataDvo> selectPsicData(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<WsncTimeTableAssignTimeDvo> selectAssignTime(WsncTimeTableRpbLocaraPsicDvo dvo);

    List<String> selectOffDays(WsncTimeTableSalesParamDvo dvo);
}
