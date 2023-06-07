package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.SearchReq;

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
    List<WsncTimeTableDvo> selectTimeTableDates(SearchReq req);

    WsncTimeTableCustDetailDvo selectCustDetail(String cntrNo);

    String selectItemcode(String cntrNo);

    String selectLocalEmpinfo(String zip, String wrkTypDtl, String selDate, String saleCd);

    String selectFarmYn(String dataGb, String saleCd);

    WsncTimeTablePackageDvo selectPackage(String mojongCd, String saleCd);

    String selectPackageKiwi(String saleCd);

    List<WsncTimeTableMojongDaysDvo> selectMojongDaysKiwim(String saleCd);
    List<WsncTimeTableIlsibulDvo> selectMojongDays(String saleCd);

    List<WsncTimeTableMonthScheduleDvo> selectMonthSchedule(String prtnrNo);

    List<WsncTimeTableDisableDaysDvo> selectDiableDays(
        String addGb,
        String selDate,
        String exYn,
        String zip,
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

    List<WsncTimeTableIlsibulDvo> selectMojongDaysIlsibul(
        String lcst09,
        String selDate,
        String gdsCd,
        String dataGb,
        String lcpkagKiwi,
        String cntrNo
    );

    String selectConvertItemcode(String saleCd);
    String selectKiwiItemcode(String saleCd);

    String selectTimeAssign_v2_step0(String zip, String kiwiItemCd, String wrkTypDtl, String selDate);

    List<WsncTimeTableTimAssStep1Dvo> selectTimeAssign_v2_step1(String gbCd, String selDate, String zip, String dataGb, String cntrNo, String inGb, String wrkTypDtl, String kiwiItemCd, String prtnrNo);
    List<WsncTimeTableTimAssStep2Dvo> selectTimeAssign_v2_step2(WsncTimeTableTimAssStep1Dvo dvo);
    List<WsncTimeTableTimAssStep3Dvo> selectTimeAssign_v2_step3(WsncTimeTableTimAssStep1Dvo dvo);

    String selectOffDays(String selDate);
}
