package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
    public SearchRes getTimeTable(SearchReq req) {

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
            case "timeAssign": // 타임테이블 조회(판매) - W-SV-U-0062M01
            case "timeAssignPost": // Wells홈페이지, K멤버스 타임테이블
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
    * @see "timeAssign.do > getTimeAssign_v2"
    * @see "timeAssign.do > getTimeAssign_post_v2"
    * */
    protected WsncTimeTableDvo timeAssign(SearchReq req) {

        log.debug("----------------------------------- 타임테이블 조회(판매) -----------------------------------------");
        //http://10.6.9.53:8083/KIWI-W/timeAssign.do?GB_CD=P&DATA_GB=1&P_GDS_CD=4415&SEL_DATE=20181211&SEL_TIME=&ZIPNO1=052&ZIPNO2=40&returnUrl=https%3A%2F%2Fwww.kwmembers.com
        //http://10.6.9.53:8083/KIWI-W/timeAssign.do?GB_CD=P&DATA_GB=1&P_GDS_CD=4415&SEL_DATE=20181211&SEL_TIME=&ZIPNO1=052&ZIPNO2=40&returnUrl=https%3A%2F%2Fwww.kwmembers.com
        //http://10.6.9.53:8083/KIWI-W/timeAssign.do?GB_CD=P&DATA_GB=1&P_GDS_CD=4415&SEL_DATE=20181211&SEL_TIME=&ZIPNO1=052&ZIPNO2=40&returnUrl=https%3A%2F%2Fwww.kwmembers.com
        log.debug("baseYm: {}", req.baseYm());
        log.debug("prtnrNo:  {}", req.prtnrNo());
        log.debug("prevTag:  {}", req.prevTag());
        log.debug("svBizHclsfCd:  {}", req.svBizHclsfCd());
        log.debug("dataStus:  {}", req.dataStus()); // 1신규,2수정,3삭제
        log.debug("cntrNo:  {}", req.cntrNo());
        log.debug("svBizDclsfCd:  {}", req.svBizDclsfCd());
        log.debug("sellDate:  {}", req.sellDate());
        log.debug("basePdCd:  {}", req.basePdCd());

        WsncTimeTableDvo result = new WsncTimeTableDvo();
        List<WsncTimeTableSidingDaysDvo> ableDays = null;
        List<WsncTimeTableTimAssStep1Dvo> step1 = null;
        List<WsncTimeTableTimAssStep2Dvo> step2 = null;
        List<WsncTimeTableTimAssStep3Dvo> step3 = null;
        try {

            // ---------------------------------------------------------
            // W      : 웰스
            // K      : kss
            // C      : cubic
            // P      : k member
            // I || E : 엔지니어
            // M      : 매니저
            String gbCd = StringUtil.isEmpty(req.gbCd()) ? "K" : req.gbCd();
            // ---------------------------------------------------------
            String cntrNo = req.cntrNo(); // W20222324935

            //String inGb = gbCd.equals("W") ? "4" : gbCd.equals("P") ? "5" : "4";
            String inGb = req.inGb(); //bypass
            String dataGb = req.svBizHclsfCd(); //P_WRK_GB
            String wrkDt = req.wrkDt(); // P_WRK_DT
            String seq = req.seq(); // P_SEQ
            // ---------------------------------------------------------
            // 1: 신규
            // 2: 수정
            // 3: 삭제
            String dataStus = req.dataStus();
            // ---------------------------------------------------------
            // 1110 fix
            String svBizDclsfCd = req.svBizDclsfCd(); // P_WRK_TYP_DTL
            // ---------------------------------------------------------
            String userId = req.userId();
            String returnurl = req.returnUrl();

            String newAdrZip = req.newAdrZip();
            String sellDate = StringUtil.isEmpty(req.sellDate()) ? DateUtil.getNowDayString() : req.sellDate();

            // ---------------------------------------------------------
            // 1:설치
            // 2:B/S
            // 3:A/S
            // 4:홈케어
            // ---------------------------------------------------------
            // 1 or 3 으로만 들어옴
            // sv_dv_cd
            // ---------------------------------------------------------
            String svBizHclsfCd = req.svBizHclsfCd();

            String sidingCd = req.sidingCd(); // P_MOJONG_CD

            String basePdCd = req.basePdCd(); // P_GDS_CD
            String empId = req.prtnrNo(); // P_USER_ID
            String contDt = "";
            String farmYN = "N";
            String lcpkag = "";

            // ---------------------------------------------------------
            // SV_BIZ_HCLSF_CD
            // 1: 설치
            // 2: B/S
            // 3: A/S
            // 4: 홈케어
            // 6: 반품처리
            // 7: 정보변경
            // 9: 삭제
            String addGb = "";
            // ---------------------------------------------------------

            String ojPdCd = "";
            String sowDay = ""; // PAJONG_DAY
            String Lcst09 = "";
            String lcwgub = "";
            String lcetc3 = "";
            String time = "";
            String bf_time = "";
            //String P_MK_CO = request.getParameter("P_MK_CO");

            if (dataGb.equals("4")) {
                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);// 하루 일자를 더한다.
            }

            if (dataGb.equals("1") && sellDate.equals(wrkDt)) {
                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);// 하루 일자를 더한다.
            }

            //            if (gbCd.equals("C") || gbCd.equals("W")) {
            //                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);
            //            }
            //            if (dataStus.equals("1") && sellDate.equals(wrkDt)) {
            //                sellDate = DateUtil.addDays(sellDate, 5);
            //            }

            if (sellDate == null || sellDate.equals("")) {
                sellDate = DateUtil.addDays(sellDate, 5);
            }

            // 2019.05.07(화) Wells 홈페이지 요청: KSS 는 웰스팜 관련 타임테이블 화면 생성전에 LD3000P 생성,
            // Wells 홈페이지는 화면 생성 전에 고객채번만 한다는 차이점이 있어서 따로 모종코드를 받아야 한다.
            if (StringUtil.isNotEmpty(sidingCd) && !sidingCd.equals("999")) {
                basePdCd = sidingCd;
            }

            //
            farmYN = mapper.selectFarmYn(basePdCd);

            String lcst09 = null;
            if (farmYN.equals("Y")) {

                WsncTimeTablePackageDvo packageInfo = mapper.selectPackage(sidingCd, basePdCd);
                lcpkag = packageInfo.getLcpkag(); // = 기준상품코드
                lcst09 = packageInfo.getLcst09();

                lcpkag = mapper.selectOjPdCd(lcpkag);

                // @TODO 하드코딩 이해욱 이사님께 문의 필요
                // WM05106364: 건강샐러드&주스 일시불 패키지 WIDE
                // WM05106365: 건강샐러드&주스 일시불 패키지 SLIM
                // WM05106366: 우리아이 채소식단 일시불 패키지 WIDE
                // WM05106367: 우리아이 채소식단 일시불 패키지 SLIM
                // WM05106368: 건강 밥상 일시불 패키지 WIDE
                // WM05106369: 건강 밥상 일시불 패키지 SLIM
                // WM05106370: 항암건강 일시불 패키지 WIDE
                // WM05106371: 항암건강 일시불 패키지 SLIM
                if (basePdCd.equals("WM05106364") || basePdCd.equals("WM05106365") || basePdCd.equals("WM05106366")
                    || basePdCd.equals("WM05106367") || basePdCd.equals("WM05106368") || basePdCd.equals("WM05106369")
                    || basePdCd.equals("WM05106370") || basePdCd.equals("WM05106371")) {

                    // getMojongDays_ilsibul
                    ableDays = this.mapper
                        .selectSidingDaysSpay(lcst09, sellDate.substring(0, 6), basePdCd, svBizHclsfCd, lcpkag, cntrNo);

                    boolean chk_add_40days = false;

                    for (int i = 0; i < ableDays.size(); i++) {
                        if (ableDays.get(i).getAblDays().equals(DateUtil.formatDate(sellDate, "-"))) {
                            chk_add_40days = true;
                            sowDay = ableDays.get(i).getSowDay();
                            break;
                        }

                    }
                    if (!chk_add_40days) {
                        sellDate = ableDays.get(0).getW3th();
                        sowDay = ableDays.get(0).getSowDay();
                    }
                    // 일반모종 타임테이블
                } else {
                    ableDays = mapper.selectSidingDays(basePdCd);
                }

            }

            WsncTimeTableCustDetailDvo custDetail = mapper.selectCustDetail(cntrNo);

            addGb = mapper.selectAddGb(basePdCd);
            ojPdCd = mapper.selectKiwiItemCode(basePdCd);//고재상품

            //홈케어 상품일 경우 , KIWI 상품코드로 변경
            if (addGb.equals("2") && custDetail != null) {
                basePdCd = custDetail.getSaleCd();
            }

            newAdrZip = custDetail.getZip();
            contDt = custDetail.getCntrDt();
            lcwgub = custDetail.getCopnDvCd();
            lcetc3 = custDetail.getSellDscDvCd();

            // Cubig CC 홈케어 조회용 타임테이블 http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp
            // KSS접수와 동일하게 하기위해 (백현아 K 요청)
            // Cubig CC DATA_GB 변경할수 없음.
            // 상품코드로 접수구분과 DATA_GB 변경
            if (addGb.equals("2") && gbCd.equals("C") && svBizHclsfCd.equals("1")
                && (StringUtil.isEmpty(returnurl))) {
                gbCd = "W";
                svBizHclsfCd = "4";
                returnurl = "http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp";
            }

            log.debug("======================================================================");
            log.debug("newAdrZip: " + newAdrZip);
            log.debug("kiwiItemCd: " + ojPdCd);
            log.debug("svBizDclsfCd: " + svBizDclsfCd);
            log.debug("sellDate: " + sellDate);
            log.debug("prtnrNo: " + empId);
            log.debug("======================================================================");
            empId = StringUtil
                .nvl2(mapper.selectTimeAssign_v2_step0(newAdrZip, ojPdCd, svBizDclsfCd, sellDate), empId);

            //엔지니어 조회
            step1 = mapper.selectTimeAssignStep1(
                gbCd, sellDate, newAdrZip, svBizHclsfCd, cntrNo, inGb, svBizDclsfCd, ojPdCd, empId
            ); // 책임지역 담당자 찾기
                                                                                                                                      // 담당자 정보 표시 (왼쪽)
            step2 = mapper.selectTimeAssignStep2(step1.get(0));
            // 시간표시
            step3 = mapper.selectTimeAssignStep3(step1.get(0));

            List<WsncTimeTableDisableDaysDvo> diabledays = mapper.selectDiableDays(
                addGb, // addGb
                sellDate, // sellDate
                "", // exYn
                newAdrZip, // newAdrZip
                basePdCd, // basePdCd
                "", // gdsCdList
                svBizHclsfCd, // svBizHclsfCd
                DateUtil.getNowDayString(), // contDt
                gbCd, // gbCd
                svBizDclsfCd, // svBizDclsfCd
                empId, // prtnrNo
                step1.get(0).getRpbLocaraCd(), // localGb
                lcwgub, // lcwgub
                lcetc3, // lcetc3
                step1.get(0).getVstDowValCd() // vstDowValCd
            );
            String offdays = mapper.selectOffDays(sellDate, newAdrZip);

            //---------------------------------------------------------//

            result.getSmPmNt().clear();
            result.setOffDays(offdays);
            result.setTimAssStep2(step2); // left_info
            result.setTimAssStep3(step3); // list1
            result.setAbleDays(ableDays);
            result.setZip(newAdrZip);
            result.setCurDateTimeString(DateUtil.getNowDayString());
            result.setSelDate(sellDate);
            result.setGbCd(gbCd);
            result.setCntrNo(cntrNo);
            result.setInGb(inGb);
            result.setWrkDt(svBizHclsfCd);
            result.setDataStus(dataStus);
            result.setWrkTypDtl(svBizDclsfCd);
            result.setGdsCd(basePdCd);
            result.setEmpId(empId);
            result.setDiableDays(diabledays);
            result.setPajongDay(sowDay);
            result.setLcst09(lcst09);

            for (int i = 0; i < step3.size(); i++) {
                WsncTimeTableSmPmNtDvo smPmNtDvo = new WsncTimeTableSmPmNtDvo();
                time = step3.get(i).getTm();
                smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));
                smPmNtDvo.setCnt(step3.get(i).getWrkCnt());
                smPmNtDvo.setAblYn(step3.get(i).getWrkChk2());
                result.getSmPmNt().add(smPmNtDvo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
        String selDate = req.sellDate(); // 20230512
        String wrkTypDtl = req.svBizDclsfCd(); // 3110
        String prtnrNo = req.prtnrNo(); // 1251831

        WsncTimeTableCustDetailDvo custDetail = mapper.selectCustDetail(cntrNo);
        if (custDetail == null) {
            throw new BizException("MSG_ALT_NO_DATA");
        }

        String zip = StringUtil.isNotEmpty(req.newAdrZip()) ? req.newAdrZip() : custDetail.getZip(); // 12249
        String contDt = custDetail.getCntrDt(); // 20220308
        String pdCd = custDetail.getPdCd(); // WM02100828

        String basePdCd = StringUtil.isEmpty(req.basePdCd()) ? custDetail.getSaleCd() : req.basePdCd(); // WP02110409, WP05160110

        String addGb = mapper.selectItemcode(cntrNo);

        String dataGb = gbCd.equals("M") ? "3" : req.svBizHclsfCd();

        String empId = "";
        if (StringUtil.isNotEmpty(dataGb) && dataGb.equals("3")) {
            empId = mapper.selectLocalEmpinfo(zip, wrkTypDtl, selDate, basePdCd);
        } else {
            empId = prtnrNo;
        }

        log.debug("cntrNo:{}", cntrNo);
        log.debug("gbCd:{}", gbCd);
        log.debug("sellDate:{}", selDate);
        log.debug("svBizDclsfCd:{}", wrkTypDtl);
        log.debug("prtnrNo:{}", prtnrNo);

        String framYn = mapper.selectFarmYn(basePdCd);

        if (StringUtil.null2str(framYn).equals("Y")) {
            result.setList(mapper.selectSidingDaysKiwim(basePdCd));
            result.setOrdCnt(mapper.selectMonthSchedule(prtnrNo));
        }

        result.setDiableDays(
            mapper.selectDiableDays(
                addGb,
                selDate,
                "Y",
                zip,
                basePdCd,
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
        result.setSaleCd(basePdCd);

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
