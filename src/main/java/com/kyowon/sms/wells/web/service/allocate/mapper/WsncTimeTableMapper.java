package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto.findReq;

import java.util.List;

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
    List<WsncTimeTableSalesDvo> selectTimeTableDates(findReq req);

    //WsncTimeTableCustDetailDvo selectCustDetail(String cntrNo);

    //String selectItemcode(String cntrNo);

    //String selectLocalEmpinfo(String zip, String wrkTypDtl, String selDate, String saleCd);

    //String selectFarmYn(String basePdCd, String svDvCd);

    //WsncTimeTablePackageDvo selectPackage(String sidingCd, String baePdCd, String cntrNo);
    WsncTimeTableCntrDvo selectCntr(String cntrNo, String cntrSn);

    WsncTimeTableProductDvo selectProduct(String basePdCd, String pdctPdCd);

    //String selectOjPdCd(String saleCd);

    //List<WsncTimeTableSidingDaysDvo> selectSidingDaysKiwim(String saleCd);

    List<WsncTimeTableSidingDaysDvo> selectSidingDays(String saleCd);//getMojongDays

    //List<WsncTimeTableMonthScheduleDvo> selectMonthSchedule(String prtnrNo);

    List<WsncTimeTableDisableDaysDvo> selectDiableDays(
        String addGb,
        String selDate,
        String exYn,
        String newAdrZip,
        String gdsCd,
        String gdsCdList,
        String dataGb,
        String contDt,
        String gbCd,
        String wrkTypDtl,
        String prtnrNo,
        String localGb,
        String lcwgub,
        String lcetc3,
        String vstDowValCd
    );

    List<WsncTimeTableSidingDaysDvo> selectSidingDaysSpay(
        String lcst09,
        String sellDate,
        String basePdCd,
        String dataGb,
        String lcpkag,
        String cntrNo
    );

    String selectAddGb(String saleCd);
    //String selectKiwiItemCode(String saleCd);

    String selectFnSvpdLocaraPrtnr01(String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate);

    String selectFnSvpdLocaraPrtnrOwr01(String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate);

    String selectFnSvpdLocaraPrtnrBs01(
        String newAdrZip, String pdctPdCd, String svBizDclsfCd, String sellDate, String vstGb
    );

    List<WsncTimeTableTimAssStep1Dvo> selectTimeAssignStep1(
        String chnlDvCd, // GB_CD
        String sellDate,
        String newAdrZip,
        String svDvCd, // DATA_GB
        String cntrNo,
        String inGb, // IN_GB
        String svBizDclsfCd, // WRK_TYP_DTL
        String pdctPdCd, // KIWI_ITEM_CD
        String prtnrNo01,
        String prtnrNoBS01,
        String prtnrNoOwr01,
        String cstSvAsnNo
    );

    List<WsncTimeTableTimAssStep2Dvo> selectTimeAssignStep2(WsncTimeTableTimAssStep1Dvo dvo);

    List<WsncTimeTableTimAssStep3Dvo> selectTimeAssignStep3(WsncTimeTableTimAssStep1Dvo dvo);

    String selectOffDays(String selDate, String newAdrZip);
}
