package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableAsMonthDto;
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
    public WsncTimeTableSalesDto.FindRes getTmeAssignSales(WsncTimeTableSalesDto.FindReq req) {

        log.debug("----------------------------------- 타임테이블 조회(판매) -----------------------------------------");
        WsncTimeTableSalesDvo result = new WsncTimeTableSalesDvo();

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsicDvo = new WsncTimeTableRpbLocaraPsicDvo();
        List<WsncTimeTableSidingDaysDvo> sidingDaysDvos = null;
        List<WsncTimeTablePsicDataDvo> psicDatas = null;
        List<WsncTimeTableAssignTimeDvo> assignTimes = null;

        WsncTimeTableSalesParamDvo paramDvo = converter.mapSalesParamReqToDvo(req);

        try {

            String chnlDvCd = StringUtil.isEmpty(req.chnlDvCd()) ? "K" : req.chnlDvCd();
            String cntrNo = req.cntrNo(); // W20222324935
            String cntrSn = req.cntrSn(); // 1
            String svDvCd = StringUtil.nvl(req.svDvCd(), ""); // dataGb
            String wrkDt = req.wrkDt(); // P_WRK_DT
            String dataStatCd = req.dataStatCd(); //DATA_STUS
            String svBizDclsfCd = req.svBizDclsfCd(); // wrkTypDtl
            // ---------------------------------------------------------
            String userId = req.userId();
            String returnurl = req.returnUrl();

            String newAdrZip = "";
            String contDt = "";
            String sellDate = StringUtil.isEmpty(req.sellDate()) ? DateUtil.getNowDayString() : req.sellDate();
            String basePdCd = ""; // basePdCd
            String pdctPdCd = ""; // basePdCd
            String sidingYn = "N";
            String spayYn = "N";
            String addGb = "";

            String sowDay = ""; // PAJONG_DAY
            String lcst09 = "";
            String copnDvCd = ""; //법인격구분 1:개인, 2법인
            String sellDscDbCd = "";
            String time = "";

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

            log.debug("--------------------------------------------------");
            log.debug("userId:  {}", userId);
            log.debug("svDvCd(dataGb):  {}", svDvCd);
            log.debug("dataStatCd(dataStus):  {}", dataStatCd);
            log.debug("cntrNo:  {}", req.cntrNo());
            log.debug("cntrSn:  {}", req.cntrSn());
            log.debug("svBizDclsfCd(wrkTypDtl):  {}", svBizDclsfCd);
            log.debug("sellDate:  {}", sellDate);
            log.debug("basePdCd:  {}", basePdCd);
            log.debug("chnlDvCd(gbCd):  {}", chnlDvCd);
            log.debug("--------------------------------------------------");

            WsncTimeTableCntrDvo contractDvo = mapper.selectContract(cntrNo, cntrSn);
            WsncTimeTableProductDvo ProductDvo = mapper
                .selectProduct(contractDvo.getBasePdCd(), contractDvo.getPdctPdCd());

            basePdCd = contractDvo.getBasePdCd();
            pdctPdCd = contractDvo.getPdctPdCd();

            sidingYn = ProductDvo.getSidingYn();
            spayYn = "3".equals(ProductDvo.getRglrSppPrcDvCd()) ? "Y" : "N"; // 일시불여부
            //------------------------------------------------------

            // 모종인지 확인
            if ("Y".equals(sidingYn)) {

                // 일시불여부
                if ("Y".equals(spayYn)) {

                    //------------------------------------------------------
                    // getMojongDays_ilsibul
                    sidingDaysDvos = this.mapper
                        .selectSidingDaysForSpay(lcst09, sellDate, basePdCd, svDvCd, pdctPdCd, cntrNo);
                    //------------------------------------------------------

                    boolean chkAdd40days = false;

                    for (WsncTimeTableSidingDaysDvo sidingDaysDvo : sidingDaysDvos) {
                        if (sidingDaysDvo.getAblDays().equals(DateUtil.formatDate(sellDate, "-"))) {
                            chkAdd40days = true;
                            sowDay = sidingDaysDvo.getSowDay();
                            break;
                        }
                    }

                    if (!chkAdd40days) {
                        sellDate = sidingDaysDvos.get(0).getW3th();
                        sowDay = sidingDaysDvos.get(0).getSowDay();
                    }
                    // 일반모종 타임테이블
                } else {
                    //------------------------------------------------------
                    sidingDaysDvos = mapper.selectSidingDays(basePdCd);
                    //------------------------------------------------------
                }

            }

            addGb = mapper.selectAddGb(basePdCd);

            //홈케어 상품일 경우 , KIWI 상품코드로 변경
            if (addGb.equals("2")) {
                basePdCd = contractDvo.getBasePdCd();
            }

            newAdrZip = contractDvo.getAdrZip();
            contDt = contractDvo.getCntrDt();
            copnDvCd = contractDvo.getCopnDvCd();
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

            /*test*/
            prtnrNo01 = StringUtil.nvl(prtnrNo01, "621303");

            paramDvo.setChnlDvCd(chnlDvCd);
            paramDvo.setSellDate(sellDate);
            paramDvo.setNewAdrZip(newAdrZip);
            paramDvo.setSvDvCd(svDvCd);
            paramDvo.setCntrNo(cntrNo);
            paramDvo.setCntrSn(cntrSn);
            paramDvo.setInGb(paramDvo.getInGb());
            paramDvo.setSvBizDclsfCd(svBizDclsfCd);
            paramDvo.setPdctPdCd(pdctPdCd);
            paramDvo.setPrtnrNo01(prtnrNo01);
            paramDvo.setPrtnrNoBS01(prtnrNoBS01);
            paramDvo.setPrtnrNoOwr01(prtnrNoOwr01);
            paramDvo.setAddGb(addGb);
            paramDvo.setExYn("");
            paramDvo.setContDt(contDt);
            paramDvo.setCopnDvCd(copnDvCd);
            paramDvo.setCopnDvCd(copnDvCd);
            paramDvo.setSellDscDbCd(sellDscDbCd);
            paramDvo.setBasePdCd(basePdCd);

            log.debug("chnlDvCd: {}", chnlDvCd);
            log.debug("sellDate: {}", sellDate);
            log.debug("newAdrZip: {}", newAdrZip);
            log.debug("svDvCd: {}", svDvCd);
            log.debug("cntrNo: {}", cntrNo);
            log.debug("svBizDclsfCd: {}", svBizDclsfCd);
            log.debug("pdctPdCd: {}", pdctPdCd);
            log.debug("prtnrNo01: {}", prtnrNo01);
            log.debug("prtnrNoBS01: {}", prtnrNoBS01);
            log.debug("prtnrNoOwr01: {}", prtnrNoOwr01);

            //-----------------------------------------------------------------------------------------

            // 책임지역 담당자 찾기
            // selectTimeAssign_v2_step1
            rpbLocaraPsicDvo = mapper.selectRpbLocaraPsic(paramDvo);
            paramDvo.setPrtnrNo(rpbLocaraPsicDvo.getHmnrscEmpno());
            paramDvo.setLocalGb(rpbLocaraPsicDvo.getRpbLocaraCd());
            paramDvo.setVstDowValCd(rpbLocaraPsicDvo.getVstDowValCd());

            // 담당자 정보 표시 (왼쪽)
            // selectTimeAssign_v2_step2
            psicDatas = mapper.selectPsicData(rpbLocaraPsicDvo);

            // 시간표시
            // selectTimeAssign_v2_step3
            assignTimes = mapper.selectAssignTime(rpbLocaraPsicDvo);

            List<WsncTimeTableDisableDaysDvo> disableDays = mapper.selectDisableDays(paramDvo);
            String offdays = mapper.selectOffDays(paramDvo);

            //---------------------------------------------------------//

            result.getSmPmNtDvos().clear();
            result.setOffDays(offdays);
            result.setPsicDataDvos(psicDatas);
            result.setAssignTimeDvos(assignTimes);
            result.setSidingDayDvos(sidingDaysDvos);
            result.setNewAdrZip(newAdrZip);
            result.setCurDateTimeString(DateUtil.getNowDayString());
            result.setSelDate(sellDate);
            result.setChnlDvCd(chnlDvCd);
            result.setCntrNo(cntrNo);
            result.setCntrSn(cntrSn);
            result.setInGb(paramDvo.getInGb());//bypass
            result.setWrkDt(wrkDt);
            result.setDataStatCd(dataStatCd);
            result.setSvBizDclsfCd(svBizDclsfCd);
            result.setBasePdCd(basePdCd);
            result.setUserId(userId);
            result.setDisableDayDvos(disableDays);
            result.setSowDay(sowDay);//pajong_day
            result.setLcst09(lcst09);
            result.setReturnurl(returnurl);
            result.setMkCo(paramDvo.getMkCo());//bypass

            for (int i = 0; i < assignTimes.size(); i++) {
                WsncTimeTableSmPmNtDvo smPmNtDvo = new WsncTimeTableSmPmNtDvo();
                time = assignTimes.get(i).getTm();
                smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));
                smPmNtDvo.setCnt(assignTimes.get(i).getWrkCnt());
                smPmNtDvo.setAblYn(assignTimes.get(i).getWrkChk2());
                result.getSmPmNtDvos().add(smPmNtDvo);
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
    protected WsncTimeTableAsMonthDto.FindRes noSessionMngtASMonth(WsncTimeTableAsMonthDto.FindReq req) {

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
