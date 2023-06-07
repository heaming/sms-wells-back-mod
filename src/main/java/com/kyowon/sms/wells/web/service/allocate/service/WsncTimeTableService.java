package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 * W-MP-U-0186P01 타임테이블 일정선택
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-05-08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncTimeTableService {

    private final WsncTimeTableMapper mapper;
    private final WsncTimeTableConverter converter;

    /**
     * 타임테이블 일정 조회
     *
     * @programId W-MP-U-0186P01
     * @param req : 조회파라메터
     * @return 조회결과
     */
    public SearchRes getTimeTable(
        SearchReq req
    ) {

        /*Test*/
        /*SearchReq req = new SearchReq(
            "202305",
            "reschedule",
            "M",
            "3",
            request.prtnrNo(),
            "20230512",
            "WP01130572",
            request.ordDt(),
            request.ordSeq(),
            "3110",
            request.zipno(),
            "WM01100337",
            ""
        );*/
        /*Test*/

        String prevTag = StringUtil.null2str(req.prevTag());

        WsncTimeTableDvo result = null;
        switch (prevTag) {
            case "schedule":
                // nosession_timeAssign.do
                result = noSessionTimeAssign(req);
                break;
            case "reschedule":
            case "mng_as_schedule":
                // nosession_mng_as_month.do
                result = noSessionMngAsMonth(req);
                break;
            case "retimeselect":
            case "mng_as_timeselect":
                // nosession_as_timeAssign.do
                result = noSessionAsTimeAssign(req);
                break;
            case "next_bs_schedule":
            case "bs_schedule":
                // nosession_bsnext_timeAssign.do
                result = noSessionBsTimeAssign(req);
                break;
            case "timeAssign":
                // nosession_bsnext_timeAssign.do
                result = timeAssign(req);
                break;
        }

        return converter.mapDvoToRes(result);
    }

    /**
    * @see "nosession_timeAssign.do"
    * */
    protected WsncTimeTableDvo noSessionTimeAssign(SearchReq req) {
        return null;
    }

    /**
    * @see "timeAssign.do"
    * */
    protected WsncTimeTableDvo timeAssign(SearchReq req) {

        List<WsncTimeTableIlsibulDvo> abledays = null;

        String cntrNo = req.cntrNo(); // W20222324935

        String P_IN_GB = req.inGb();
        String dataGb = req.dataGb();//P_WRK_GB
        String P_WRK_DT = req.wrkDt();
        //        String P_SEQ = request.getParameter("P_SEQ");
        String dataStus = req.dataStus();
        String wrkTypDtl = req.wrkTypDtl();
        String prtnrNo = req.prtnrNo(); // P_USER_ID
        //String returnurl = request.getParameter("returnUrl");
        String gbCd = StringUtil.isEmpty(req.gbCd()) ? "K" : req.gbCd();

        String inGb = "3";
        String zip = "";
        String contDt = "";
        String farmYN = "N";
        String LCPKAG = "";
        String LCPKAG_KIWI = "";
        String addGb = "";
        String kiwiItemCd = "";
        String pajongDay = "";
        String LCST09 = "";
        String P_LCWGUB = "";
        String P_LCETC3 = "";
        String saleCd = req.saleCd(); // P_GDS_CD
        String selDate = req.selDate();

        String time = "";
        String bf_time = "";

        //        String P_MK_CO = request.getParameter("P_MK_CO");
        //        String DATA_GB = request.getParameter("P_WRK_GB");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String curDateTimeString = format.format(cal.getTime());
        if (dataGb.equals("4")) {
            cal.add(Calendar.DATE, 1); // 하루 일자를 더한다.
        }

        if (dataStus.equals("1") && selDate.equals(P_WRK_DT)) {
            cal.add(Calendar.DATE, 5);
            selDate = format.format(cal.getTime());
        }

        if (selDate == null || selDate.equals("")) {
            cal.add(Calendar.DATE, 5);
            selDate = format.format(cal.getTime());
        }

        farmYN = mapper.selectFarmYn(dataGb, saleCd);

        String lcst09 = null;
        if (StringUtil.null2str(farmYN).equals("Y")) {

            WsncTimeTablePackageDvo pakageInfo = mapper.selectPackage("", saleCd);
            lcst09 = pakageInfo.getLcst09();

            String pdCd = mapper.selectPackageKiwi(saleCd);

            // @TODO 하드코딩 이해욱 이사님께 문의 필요
            if (saleCd.equals("WM05106364") || saleCd.equals("WM05106365") || saleCd.equals("WM05106366")
                || saleCd.equals("WM05106367") || saleCd.equals("WM05106368") || saleCd.equals("WM05106369")
                || saleCd.equals("WM05106370") || saleCd.equals("WM05106371")) {

                abledays = this.mapper
                    .selectMojongDaysIlsibul(lcst09, selDate.substring(0, 6), saleCd, dataGb, pdCd, cntrNo);

                boolean chk_add_40days = false;

                for (int i = 0; i < abledays.size(); i++) {
                    if (abledays.get(i).getAblDays().equals(
                        selDate.substring(0, 4) + "-" + Integer.valueOf(selDate.substring(4, 6)) + "-"
                            + Integer.valueOf(selDate.substring(6, 8))
                    )) {
                        chk_add_40days = true;
                        pajongDay = abledays.get(i).getPajongDay();
                        break;
                    }

                }
                if (!chk_add_40days) {
                    selDate = abledays.get(0).getW3th();
                    pajongDay = abledays.get(0).getPajongDay();
                }

            } else {
                abledays = mapper.selectMojongDays(saleCd);
            }

        }

        WsncTimeTableCustDetailDvo custDetail = mapper.selectCustDetail(cntrNo);

        zip = custDetail.getZip();
        contDt = custDetail.getCntrDt();
        P_LCWGUB = custDetail.getCopnDvCd();
        P_LCETC3 = custDetail.getSellDscDvCd();

        addGb = mapper.selectConvertItemcode(saleCd);
        kiwiItemCd = mapper.selectKiwiItemcode(saleCd);

        //홈케어 상품일 경우 , KIWI 상품코드로 변경
        if (addGb.equals("2")) {
            saleCd = custDetail.getSaleCd();

        }

        List<WsncTimeTableTimAssStep1Dvo> step1 = mapper.selectTimeAssign_v2_step1(gbCd, selDate, zip, dataGb, cntrNo, inGb, wrkTypDtl, kiwiItemCd,
            StringUtil.nvl2(mapper.selectTimeAssign_v2_step0(zip, saleCd, wrkTypDtl, selDate), prtnrNo)
        ); // 책임지역 담당자 찾기
        List<WsncTimeTableTimAssStep2Dvo> step2 = mapper.selectTimeAssign_v2_step2(step1.get(0)); // 담당자 정보 표시 (왼쪽)
        List<WsncTimeTableTimAssStep3Dvo> step3 = mapper.selectTimeAssign_v2_step3(step1.get(0)); // 시간표시

        List<WsncTimeTableDisableDaysDvo> diabledays = mapper.selectDiableDays(
            addGb, selDate, "", zip, saleCd, "", dataGb, contDt, gbCd, wrkTypDtl, prtnrNo,
            step1.get(0).getRpbLocaraCd(), P_LCWGUB, P_LCETC3, step1.get(0).getVstDowValCd()
        );
        String offdays = mapper.selectOffDays(selDate);

        //---------------------------------------------------------//

        WsncTimeTableDvo result = new WsncTimeTableDvo();
        result.getSmPmNt().clear();
        result.setOffDays(offdays);
        result.setTimAssStep2(step2); // left_info
        result.setTimAssStep3(step3); // list1
        result.setAbledays(abledays);
        result.setZip(zip);
        result.setCurDateTimeString(curDateTimeString);
        result.setSelDate(selDate);
        result.setGbCd(gbCd);
        result.setCntrNo(cntrNo);
        result.setInGb(inGb);
        result.setWrkDt(dataGb);
        result.setDataStus(dataStus);
        result.setWrkTypDtl(wrkTypDtl);
        result.setGdsCd(req.gdsCd());
        result.setEmpId(prtnrNo);
        result.setDiabledays(diabledays);
        result.setPajongDay(pajongDay);
        result.setLcst09(lcst09);

        for (int i = 0; i < step3.size(); i++) {
            WsncTimeTableSmPmNtDvo smPmNtDvo = new WsncTimeTableSmPmNtDvo();
            time = step3.get(i).getTm();
            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));
            smPmNtDvo.setCnt(step3.get(i).getWrkCnt());
            smPmNtDvo.setAblYn(step3.get(i).getWrkChk2());
            result.getSmPmNt().add(smPmNtDvo);
        }

        return result;
    }

    /**
    * @programId W-MP-U-0186P01
    * @see "nosession_mng_as_month.do"
    *
    * */
    protected WsncTimeTableDvo noSessionMngAsMonth(SearchReq req) {

        // -------------------------------------------------
        // 고객검색-> 신규AS등록 -> 방문일자 선택
        // ## getMonthAsSchedule ##
        // ZIPNO1      = 449
        // ZIPNO2      = 64
        // GB_CD       = M
        // SEL_DATE    = 20230512
        // SALE_CD     = 4770
        // EMP_ID      = 1251831 -> 로그인한 사용자
        // DATA_GB     = 3
        // WRK_TYP_DTL = 3110
        // ORD_DT      = null
        // ORD_SEQ     = null
        // -------------------------------------------------

        WsncTimeTableDvo result = new WsncTimeTableDvo();

        String cntrNo = req.cntrNo(); // W20222324935
        String gbCd = req.gbCd(); // M (C, K, M, P, W)
        String selDate = req.selDate(); // 20230512
        String wrkTypDtl = req.wrkTypDtl(); // 3110
        String prtnrNo = req.prtnrNo(); // 1251831

        WsncTimeTableCustDetailDvo custDetail = mapper.selectCustDetail(cntrNo);
        if (custDetail == null) {
            throw new BizException("MSG_ALT_NO_DATA");
        }

        String zip = StringUtil.isNotEmpty(req.zipno()) ? req.zipno() : custDetail.getZip(); // 12249
        String contDt = custDetail.getCntrDt(); // 20220308
        String pdCd = custDetail.getPdCd(); // WM02100828

        String saleCd = StringUtil.isEmpty(req.saleCd()) ? custDetail.getSaleCd() : req.saleCd(); // WP02110409, WP05160110

        String addGb = mapper.selectItemcode(cntrNo);

        String dataGb = gbCd.equals("M") ? "3" : req.dataGb();

        String empId = "";
        if (StringUtil.isNotEmpty(dataGb) && dataGb.equals("3")) {
            empId = mapper.selectLocalEmpinfo(zip, wrkTypDtl, selDate, saleCd);
        } else {
            empId = prtnrNo;
        }

        log.debug("cntrNo:{}", cntrNo);
        log.debug("gbCd:{}", gbCd);
        log.debug("selDate:{}", selDate);
        log.debug("wrkTypDtl:{}", wrkTypDtl);
        log.debug("prtnrNo:{}", prtnrNo);

        String framYn = mapper.selectFarmYn(dataGb, saleCd);

        if (StringUtil.null2str(framYn).equals("Y")) {
            result.setList1(mapper.selectMojongDaysKiwim(saleCd));
            result.setOrdcnt(mapper.selectMonthSchedule(prtnrNo));
        }

        result.setDiabledays(
            mapper.selectDiableDays(
                addGb,
                selDate,
                "Y",
                zip,
                saleCd,
                "",
                dataGb,
                contDt,
                gbCd,
                wrkTypDtl,
                empId,
                "",
                "",
                "",
                ""
            )
        );

        result.setZip(zip);
        result.setWrkTypDtl(wrkTypDtl);
        result.setGbCd(gbCd);
        result.setDataGb(dataGb);
        result.setCntrNo(cntrNo);
        result.setSelDate(selDate);
        result.setSaleCd(saleCd);

        return result;
    }

    /**
    * @see "nosession_as_timeAssign.do"
    * */
    protected WsncTimeTableDvo noSessionAsTimeAssign(SearchReq req) {
        return null;
    }

    /**
    * @see "nosession_bsnext_timeAssign"
    * */
    protected WsncTimeTableDvo noSessionBsTimeAssign(SearchReq req) {
        return null;
    }

}
