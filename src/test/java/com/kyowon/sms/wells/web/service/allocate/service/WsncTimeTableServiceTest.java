package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindScheChoReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindTimeAssignReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindTimeChoReq;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@Slf4j
class WsncTimeTableServiceTest extends SpringTestSupport {

    private final WsncTimeTableService service;

    private void log(FindTimeAssignReq req, FindRes res) {
        log.debug("---------------------------------------------------");
        log.debug("request");
        log.debug("req.sellDate = " + req.sellDate());
        log.debug("req.svBizDclsfCd = " + req.svBizDclsfCd());
        log.debug("req.basePdCd = " + req.basePdCd());
        log.debug("---------------------------------------------------");
        log.debug("response ");
        log.debug("    sellDate = " + res.sellDate());
        log.debug("    wrkDt = " + res.wrkDt());
        log.debug("    chnlDvCd = " + res.chnlDvCd());
        log.debug("    inflwChnl = " + res.inflwChnl());
        log.debug("    mtrStatCd = " + res.mtrStatCd());
        log.debug("    newAdrZip = " + res.newAdrZip());
        log.debug("    seq = " + res.seq());
        log.debug("    pdctPdCd = " + res.pdctPdCd());
        log.debug("    basePdCd = " + res.basePdCd());
        log.debug("    basePdCds = " + Arrays.toString(res.basePdCds()));
        log.debug("    svDvCd = " + res.svDvCd());
        log.debug("    userId = " + res.userId());
        log.debug("    mkCo = " + res.mkCo());
        log.debug("    sdingCombin = " + res.sdingCombin());
        log.debug("    sidingYn = " + res.sidingYn());
        log.debug("    spayYn = " + res.spayYn());
        log.debug("    sowDay = " + res.sowDay());
        log.debug("---------------------------------------------------");
        log.debug("    baseYm = " + res.baseYm());
        log.debug("---------------------------------------------------");
        log.debug("    cntrNo = " + res.cntrNo());
        log.debug("    cntrSn = " + res.cntrSn());
        log.debug("---------------------------------------------------");
        log.debug("    returnUrl = " + res.returnUrl());
        log.debug("---------------------------------------------------");
    }

    private void isOkChnl(String chnlDvCd, String inflwChnl) {
        switch (chnlDvCd) {
            case "W": // WELLS
                assertEquals(inflwChnl, "4");
                break;
            case "K": // KSS
                assertEquals(inflwChnl, "3");
                break;
            case "C": // CubicCC
                assertEquals(inflwChnl, "1");
                break;
            case "P": // K-MEMBERS
                assertEquals(inflwChnl, "5");
                break;
            case "I":
            case "E": // 엔지니어
            case "M": // 매니저
            case "B": // BS(엔지니어)
                assertEquals(inflwChnl, "");
                break;
            default:
                break;
        }
    }

    @Test
    void 판매타임테이블() throws ParseException {
        String nowDay = DateUtil.getNowDayString();

        //https://d-wsm.kyowon.co.kr/api/v1/sms/wells/service/time-tables/time-assign?
        // baseYm=202307
        // &chnlDvCd=K
        // &svDvCd=1
        // &sellDate=20230718
        // &svBizDclsfCd=1110
        // &inflwChnl=3
        // &basePdCd=
        // &wrkDt=
        // &mtrStatCd=
        // &returnUrl=
        // &mkCo=
        // &cntrNo=W20235600242
        // &cntrSn=1
        // &seq=

        FindTimeAssignReq req = FindTimeAssignReq.builder()
            .sellDate("20230718")
            .baseYm("202307")
            .chnlDvCd("K")
            .svDvCd("1")
            .svBizDclsfCd("1110")
            .cntrNo("W20235600241")
            .cntrSn("1")
            .build();

        FindRes res = service.getTmeAssign(req);

        log(req, res);

        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    }

    @Test
    void 판매타임테이블_다건() throws ParseException {
        String nowDay = DateUtil.getNowDayString();

        //https://d-wsm.kyowon.co.kr/api/v1/sms/wells/service/time-tables/time-assign?
        // baseYm=202307
        // &chnlDvCd=K
        // &svDvCd=1
        // &sellDate=20230718
        // &svBizDclsfCd=1110,1110
        // &inflwChnl=3
        // &basePdCd=
        // &wrkDt=
        // &mtrStatCd=
        // &returnUrl=
        // &mkCo=
        // &cntrNo=W20235600242
        // &cntrSn=1,2
        // &seq=

        FindTimeAssignReq req = FindTimeAssignReq.builder()
            .sellDate("20230718")
            .baseYm("202307")
            .chnlDvCd("K")
            .svDvCd("1")
            .svBizDclsfCd("1110,1110")
            .cntrNo("W20235600241")
            .cntrSn("1,2")
            .build();

        FindRes res = service.getTmeAssign(req);

        log(req, res);

        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    }

    //
    //    @Test
    //    void timeAssign_test_single_with_cntrNo() throws ParseException {
    //        String nowDay = DateUtil.getNowDayString();
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("C")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1111,3100")
    //            .baseYm("202307")
    //            .cntrNo("W20227121814")
    //            .cntrSn("1")
    //            .build();
    //
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "C");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_test_multi_with_basePdcds() throws ParseException {
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("C")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1111,3100") // 설치+철거
    //            .basePdCd("WP01120279,WP01110622")
    //            .newAdrZip("01000")
    //            .baseYm("202307")
    //            .build();
    //
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "C");
    //
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_test_single_with_basePdcds() throws ParseException {
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("C")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1111") // 설치+철거
    //            .basePdCd("WP01120279")
    //            .newAdrZip("01000")
    //            .baseYm("202307")
    //            .build();
    //
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "C");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_list_single_with_basePdcds() throws ParseException {
    //
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("W")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1110") // 1110, 3100
    //            .basePdCd("WP01120279")
    //            .newAdrZip("01000")
    //            .baseYm("202307")
    //            .build();
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "W");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_list_single_WELLS_with_cntrNo() throws ParseException {
    //
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("W")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1111")
    //            .baseYm("202307")
    //            .cntrNo("W20227121814")
    //            .build();
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "W");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_list_single_조회용_AS() throws ParseException {
    //
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("S")
    //            .svDvCd("3")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1111")
    //            .baseYm("202307")
    //            .cntrNo("W20227121814")
    //            .build();
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "W");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_list_single_kmembers_with_cntrNo() throws ParseException {
    //
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("P")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1111")
    //            .baseYm("202307")
    //            .cntrNo("W20231657419")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .wrkDt("20230717")
    //            .build();
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "P");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_list_single_KSS_with_cntrNo() throws ParseException {
    //
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .baseYm("202307")
    //            .chnlDvCd("K")
    //            .svDvCd("1")
    //            .sellDate("20230718")
    //            .svBizDclsfCd("1110")
    //            .cntrNo("W20235600242")
    //            .cntrSn("1")
    //            .build();
    //
    //        //            &chnlDvCd=K
    //        //&svDvCd=1
    //        //&sellDate=20230718
    //        //&svBizDclsfCd=1110
    //        //&inflwChnl=3
    //        //&basePdCd=
    //        //&wrkDt=
    //        //&mtrStatCd=
    //        //&returnUrl=
    //        //&mkCo=
    //        //&cntrNo=W20235600242
    //        //&cntrSn=1
    //        //&seq=
    //
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "P");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void timeAssign_list_multi_with_basePdcds() throws ParseException {
    //
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindTimeAssignReq req = FindTimeAssignReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("W")
    //            .svDvCd("1")
    //            .mtrStatCd("1")
    //            .seq("1")
    //            .svBizDclsfCd("1110,3100") // 1110, 3100
    //            .basePdCd("WP01120279,WP01110622")
    //            .newAdrZip("01000")
    //            .baseYm("202307")
    //            .build();
    //
    //        FindRes res = service.getTmeAssign(req);
    //
    //        log(req, res);
    //
    //        assertEquals(
    //            DateUtil.isDayOfWeek(nowDay, 7) ? DateUtil.addDays(nowDay, 2) : DateUtil.addDays(nowDay, 1), res.sellDate()
    //        );
    //        assertEquals(res.chnlDvCd(), "W");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    //    @Test
    //    void getScheduleChoice_multi_with_cntrNo() throws ParseException {
    //        String nowDay = DateUtil.getNowDayString();
    //
    //        FindScheChoReq req = FindScheChoReq.builder()
    //            .sellDate(nowDay)
    //            .chnlDvCd("W")
    //            .svDvCd("1")
    //            .mtrStatCd("2")
    //            .seq("1")
    //            .svBizDclsfCd("1111,3100")
    //            .cntrNo("W20227121814")
    //            .cntrSn("1,3")
    //            .baseYm("202307")
    //            .build();
    //
    //        FindRes res = service.getScheduleChoice(req);
    //
    //        assertEquals(nowDay, res.sellDate());
    //        assertEquals(res.chnlDvCd(), "W");
    //        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    //    }
    //
    @Test
    void 일정선택_시간() throws ParseException {
        String nowDay = DateUtil.getNowDayString();

        FindTimeChoReq req = FindTimeChoReq.builder()
            .newAdrZip("13639")
            .cntrNo("W20235600241")
            .cntrSn("1")
            .chnlDvCd("M")
            .sellDate("20230721")
            .svDvCd("3")
            .svBizDclsfCd("3110")
            .baseYm("202307")
            .build();

        FindRes res = service.getTimeChoice(req);

        assertEquals(nowDay, res.sellDate());
        assertEquals(res.chnlDvCd(), "W");
        isOkChnl(req.chnlDvCd(), res.inflwChnl());
    }

    /*
    * reschedule
    *
    * /nosession_mng_as_month.do
    * */
    @Test
    void reschedule() throws ParseException {
        service.getScheduleChoice(
            FindScheChoReq.builder()
                .chnlDvCd("I")
                .svDvCd("3")
                .sellDate(DateUtil.getNowDayString())
                .svBizDclsfCd("1111")
                .cntrNo("W20235600242")
                .cntrSn("1")
                .baseYm("202307")
                .build()
        );
    }

    /*
    * reschedule
    *
    * /nosession_mng_as_month.do
    * */
    @Test
    void reschedule_multi() throws ParseException {
        service.getScheduleChoice(
            FindScheChoReq.builder()
                .chnlDvCd("I")
                .svDvCd("3")
                .sellDate(DateUtil.getNowDayString())
                .svBizDclsfCd("1111,1110")
                .cntrNo("W20235600242")
                .cntrSn("1,2")
                .baseYm("202307")
                .build()
        );
    }

    //    @Test
    //    void getTimeChoice() {}
}
