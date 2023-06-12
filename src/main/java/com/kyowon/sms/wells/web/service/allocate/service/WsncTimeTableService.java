package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableMngtASMonthDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    //public FindRes getTimeTableSales(findReq req) {

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

    //        String prevTag = StringUtil.null2str(req.prevTag());
    //
    //        WsncTimeTableSalesDvo result = null;
    //        switch (prevTag) {
    //            case "schedule":
    //                // nosession_timeAssign.do
    //                result = noSessionTimeAssign(req);
    //                break;
    //            case "reschedule":
    //            case "mng_as_schedule":
    //                // nosession_mng_as_month.do
    //                result = noSessionMngAsMonth(req);
    //                break;
    //            case "retimeselect":
    //            case "mng_as_timeselect":
    //                // nosession_as_timeAssign.do
    //                result = noSessionAsTimeAssign(req);
    //                break;
    //            case "next_bs_schedule":
    //            case "bs_schedule":
    //                // nosession_bsnext_timeAssign.do
    //                result = noSessionBsTimeAssign(req);
    //                break;
    //            case "timeAssignSales": // 타임테이블 조회(판매) - W-SV-U-0062M01
    //                result = timeAssignSales(req);
    //                break;
    //            case "timeAssignWellsKmembers": // Wells홈페이지, K멤버스 타임테이블
    //                result = timeAssignWellsKmembers();
    //                break;
    //        }
    //
    //        return converter.mapDvoToRes(result);
    //}

    /**
    * @see "timeAssign.do GET"
    * */
    public WsncTimeTableSalesDto.findRes getTmeAssignSales(WsncTimeTableSalesDto.findReq req) {

        log.debug("----------------------------------- 타임테이블 조회(판매) -----------------------------------------");
        //http://10.6.9.53:8083/KIWI-W/timeAssign.do?GB_CD=P&DATA_GB=1&P_GDS_CD=4415&SEL_DATE=20181211&SEL_TIME=&ZIPNO1=052&ZIPNO2=40&returnUrl=https%3A%2F%2Fwww.kwmembers.com
        //http://10.6.9.53:8083/KIWI-W/timeAssign.do?GB_CD=P&DATA_GB=1&P_GDS_CD=4415&SEL_DATE=20181211&SEL_TIME=&ZIPNO1=052&ZIPNO2=40&returnUrl=https%3A%2F%2Fwww.kwmembers.com
        //http://10.6.9.53:8083/KIWI-W/timeAssign.do?GB_CD=P&DATA_GB=1&P_GDS_CD=4415&SEL_DATE=20181211&SEL_TIME=&ZIPNO1=052&ZIPNO2=40&returnUrl=https%3A%2F%2Fwww.kwmembers.com

        WsncTimeTableSalesDvo result = new WsncTimeTableSalesDvo();
        List<WsncTimeTableSidingDaysDvo> ableDays = null;
        List<WsncTimeTableTimAssStep1Dvo> step1 = null;
        List<WsncTimeTableTimAssStep2Dvo> step2 = null;
        List<WsncTimeTableTimAssStep3Dvo> step3 = null;

        try {

            String chnlDvCd = StringUtil.isEmpty(req.chnlDvCd()) ? "K" : req.chnlDvCd();
            String cntrNo = req.cntrNo(); // W20222324935
            String cntrSn = req.cntrSn(); // 1
            String inGb = req.inGb(); //bypass
            String svDvCd = StringUtil.nvl(req.svDvCd(), ""); // dataGb
            String wrkDt = req.wrkDt(); // P_WRK_DT
            String seq = req.seq(); // P_SEQ
            String dataStatCd = req.dtaStatCd();
            String svBizDclsfCd = req.svBizDclsfCd(); // wrkTypDtl
            // ---------------------------------------------------------
            String userId = req.userId();
            String returnurl = req.returnUrl();

            String newAdrZip = "";
            String contDt = "";
            String sellDate = StringUtil.isEmpty(req.sellDate()) ? DateUtil.getNowDayString() : req.sellDate();
            String basePdCd = ""; // gdsCd
            String pdctPdCd = ""; // gdsCd
            String farmYN = "N";
            String lcpkag = "";
            String mkCo = req.mkCo();
            String addGb = "";

            String sowDay = ""; // PAJONG_DAY
            String lcst09 = "";
            String lcwgub = "";
            String sellDscDbCd = "";
            String time = "";
            String bf_time = "";
            //String P_MK_CO = request.getParameter("P_MK_CO");

            if (svDvCd.equals("4")) {
                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);
            }

            if (svDvCd.equals("1") && sellDate.equals(wrkDt)) {
                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 5);
            }

            if (sellDate == null || sellDate.equals("")) {
                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 5);
            }

            //            if (gbCd.equals("C") || gbCd.equals("W")) {
            //                sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);
            //            }
            //            if (dataStus.equals("1") && sellDate.equals(wrkDt)) {
            //                sellDate = DateUtil.addDays(sellDate, 5);
            //            }

            //            if (StringUtil.isNotEmpty(sidingCd) && !sidingCd.equals("999")) {
            //                basePdCd = sidingCd;
            //            }

            log.debug("userId:  {}", userId);
            log.debug("--------------------------------------------------");
            log.debug("1:설치");
            log.debug("2:B/S");
            log.debug("3:A/S");
            log.debug("4:홈케어");
            log.debug("svDvCd(dataGb):  {}", svDvCd);
            log.debug("--------------------------------------------------");
            log.debug("1: 신규");
            log.debug("2: 수정");
            log.debug("3: 삭제");
            log.debug("dataStatCd(dataStus):  {}", dataStatCd);
            log.debug("--------------------------------------------------");
            log.debug("cntrNo:  {}", req.cntrNo());
            log.debug("cntrSn:  {}", req.cntrSn());
            log.debug("--------------------------------------------------");
            log.debug("1110 fix");
            log.debug("svBizDclsfCd(wrkTypDtl):  {}", svBizDclsfCd);
            log.debug("--------------------------------------------------");
            log.debug("sellDate:  {}", sellDate);
            log.debug("basePdCd:  {}", basePdCd);
            log.debug("--------------------------------------------------");
            log.debug("W      : 웰스");
            log.debug("K      : KSS");
            log.debug("C      : CubicCC");
            log.debug("P      : K-MEMBERS");
            log.debug("I || E : 엔지니어");
            log.debug("M      : 매니저");
            log.debug("chnlDvCd(gbCd):  {}", chnlDvCd);
            log.debug("--------------------------------------------------");

            WsncTimeTableCntrDvo cntrDvo = mapper.selectCntr(cntrNo, cntrSn);
            WsncTimeTableProductDvo ProductDvo = mapper.selectProduct(cntrDvo.getBasePdCd(), cntrDvo.getPdctPdCd());

            basePdCd = cntrDvo.getBasePdCd();
            pdctPdCd = cntrDvo.getPdctPdCd();

            //farmYN = mapper.selectFarmYn(basePdCd, svDvCd);
            farmYN = ProductDvo.getSdingYn();
            //------------------------------------------------------

            // 모종인지 확인
            if (farmYN.equals("Y")) {

                //------------------------------------------------------
                //WsncTimeTablePackageDvo packageInfo = mapper.selectPackage(sidingCd, basePdCd, cntrNo);
                //------------------------------------------------------
                //lcpkag = packageInfo.getLcpkag(); // = 기준상품코드
                //lcst09 = packageInfo.getLcst09();
                //------------------------------------------------------
                //selectPackage_kiwi
                //lcpkag = mapper.selectOjPdCd(lcpkag);
                //------------------------------------------------------

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

                    //------------------------------------------------------
                    // getMojongDays_ilsibul
                    ableDays = this.mapper
                        .selectSidingDaysSpay(lcst09, sellDate, basePdCd, svDvCd, pdctPdCd, cntrNo);
                    //------------------------------------------------------

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
                    //------------------------------------------------------
                    ableDays = mapper.selectSidingDays(basePdCd);
                    //------------------------------------------------------
                }

            }

            //------------------------------------------------------
            //WsncTimeTableCustDetailDvo custDetail = mapper.selectCustDetail(cntrNo);
            //------------------------------------------------------

            // ---------------------------------------------------------
            // 1: 설치
            // 2: B/S
            // 3: A/S
            // 4: 홈케어
            // 6: 반품처리
            // 7: 정보변경
            // 9: 삭제
            // ---------------------------------------------------------
            addGb = mapper.selectAddGb(basePdCd);
            //------------------------------------------------------
            //pdctPdCd = mapper.selectKiwiItemCode(basePdCd);
            //------------------------------------------------------

            //홈케어 상품일 경우 , KIWI 상품코드로 변경
            if (addGb.equals("2")) {
                basePdCd = cntrDvo.getBasePdCd();
            }

            newAdrZip = cntrDvo.getAdrZip();
            contDt = cntrDvo.getCntrDt();
            //lcwgub = cntrDvo.getCopnDvCd();
            //sellDscDbCd = cntrDvo.getSellDscDvCd();

            // Cubig CC 홈케어 조회용 타임테이블 http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp
            // KSS접수와 동일하게 하기위해 (백현아 K 요청)
            // Cubig CC DATA_GB 변경할수 없음.
            // 상품코드로 접수구분과 DATA_GB 변경
            if (addGb.equals("2") && chnlDvCd.equals("C") && svDvCd.equals("1")
                && (StringUtil.isEmpty(returnurl))) {
                chnlDvCd = "W";
                svDvCd = "4";
                returnurl = "http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp";
            }

            String prtnrNo01 = mapper.selectFnSvpdLocaraPrtnr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);
            String prtnrNoBS01 = mapper.selectFnSvpdLocaraPrtnrBs01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate, "");
            String prtnrNoOwr01 = mapper.selectFnSvpdLocaraPrtnrOwr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);

            // 책임지역 담당자 찾기
            step1 = mapper.selectTimeAssignStep1(
                chnlDvCd,
                sellDate,
                newAdrZip,
                svDvCd,
                cntrNo,
                inGb,
                svBizDclsfCd,
                pdctPdCd,
                prtnrNo01,
                prtnrNoBS01,
                prtnrNoOwr01,
                ""
            );
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
                svDvCd, // dataGb
                contDt, // contDt
                chnlDvCd, // gbCd
                svBizDclsfCd, // wrkTypDtl
                userId, // P_USER_ID
                step1.get(0).getRpbLocaraCd(), // localGb
                lcwgub, // lcwgub
                sellDscDbCd, // lcetc3
                step1.get(0).getVstDowValCd() // vstDowValCd
            );
            String offdays = mapper.selectOffDays(sellDate, newAdrZip);

            //---------------------------------------------------------//

            result.getSmPmNt().clear();
            result.setOffDays(offdays);
            result.setTimAssStep2(step2);
            result.setTimAssStep3(step3);
            result.setAbleDays(ableDays);
            result.setNewAdrZip(newAdrZip);
            result.setCurDateTimeString(DateUtil.getNowDayString());
            result.setSelDate(sellDate);
            result.setChnlDvCd(chnlDvCd);
            result.setCntrNo(cntrNo);
            result.setCntrSn(cntrSn);
            result.setInGb(inGb);//bypass
            result.setWrkDt(wrkDt);
            result.setDataStatCd(dataStatCd);
            result.setSvBizDclsfCd(svBizDclsfCd);
            result.setBasePdCd(basePdCd);
            result.setUserId(userId);
            result.setDiableDays(diabledays);
            result.setSowDay(sowDay);//pajong_day
            result.setLcst09(lcst09);
            result.setReturnurl(returnurl);
            result.setMkCo(mkCo);//bypass

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
        return converter.mapSalesDvoToRes(result);
    }

    /**
    * @see "nosession_timeAssign.do"
    * */
    protected WsncTimeTableSalesDvo noSessionTimeAssign() {
        return null;
    }

    /**
    * @see "timeAssign.do > getTimeAssign_post_v2"
    * */
    protected WsncTimeTableSalesDvo timeAssignWellsKmembers() {
        return null;
    }

    /**
    * @programId W-MP-U-0186P01
    * @see "nosession_mng_as_month.do"
    *
    * */
    protected WsncTimeTableMngtASMonthDto.findRes noSessionMngtASMonth(WsncTimeTableMngtASMonthDto.findReq req) {

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

        //        WsncTimeTableSalesDvo result = new WsncTimeTableSalesDvo();
        //
        //        String cntrNo = req.cntrNo(); // W20222324935
        //        String chnlDvCd = req.chnlDvCd(); // M (C, K, M, P, W)
        //        String selDate = req.sellDate(); // 20230512
        //        String svBizDclsfCd = req.svBizDclsfCd(); // 3110
        //        String prtnrNo = req.prtnrNo(); // 1251831
        //
        //        WsncTimeTableCustDetailDvo custDetail = mapper.selectCustDetail(cntrNo);
        //        if (custDetail == null) {
        //            throw new BizException("MSG_ALT_NO_DATA");
        //        }
        //
        //        String zip = StringUtil.isNotEmpty(req.newAdrZip()) ? req.newAdrZip() : custDetail.getZip(); // 12249
        //        String contDt = custDetail.getCntrDt(); // 20220308
        //        String pdCd = custDetail.getPdCd(); // WM02100828
        //
        //        String basePdCd = StringUtil.isEmpty(req.basePdCd()) ? custDetail.getSaleCd() : req.basePdCd(); // WP02110409, WP05160110
        //
        //        String addGb = mapper.selectItemcode(cntrNo);
        //
        //        String svDvCd = chnlDvCd.equals("M") ? "3" : req.svDvCd();
        //
        //        String empId = "";
        //        if (StringUtil.isNotEmpty(svDvCd) && svDvCd.equals("3")) {
        //            empId = mapper.selectLocalEmpinfo(zip, svBizDclsfCd, selDate, basePdCd);
        //        } else {
        //            empId = prtnrNo;
        //        }
        //
        //        log.debug("cntrNo:{}", cntrNo);
        //        log.debug("gbCd:{}", chnlDvCd);
        //        log.debug("sellDate:{}", selDate);
        //        log.debug("svBizDclsfCd:{}", svBizDclsfCd);
        //        log.debug("prtnrNo:{}", prtnrNo);
        //
        //        String framYn = mapper.selectFarmYn(basePdCd, svDvCd);
        //
        //        if (StringUtil.null2str(framYn).equals("Y")) {
        //            result.setList(mapper.selectSidingDaysKiwim(basePdCd));
        //            result.setOrdCnt(mapper.selectMonthSchedule(prtnrNo));
        //        }
        //
        //        result.setDiableDays(
        //            mapper.selectDiableDays(
        //                addGb,
        //                selDate,
        //                "Y",
        //                zip,
        //                basePdCd,
        //                "",
        //                svDvCd,
        //                contDt,
        //                chnlDvCd,
        //                svBizDclsfCd,
        //                empId,
        //                "",
        //                "",
        //                "",
        //                ""
        //            )
        //        );
        //
        //        result.setZip(zip);
        //        result.setSvBizDclsfCd(svBizDclsfCd);
        //        result.setChnlDvCd(chnlDvCd);
        //        result.setSvDvCd(svDvCd);
        //        result.setCntrNo(cntrNo);
        //        result.setSelDate(selDate);
        //        result.setSaleCd(basePdCd);
        //
        //        return result;
        return null;
    }

    /**
    * @see "nosession_as_timeAssign.do"
    * */
    protected WsncTimeTableSalesDvo noSessionAsTimeAssign() {
        return null;
    }

    /**
    * @see "nosession_bsnext_timeAssign"
    * */
    protected WsncTimeTableSalesDvo noSessionBsTimeAssign() {
        return null;
    }

}
