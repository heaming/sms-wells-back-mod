package com.kyowon.sms.wells.web.service.allocate.service;

import java.text.ParseException;
import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableTimeChoDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSchdChoDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
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
     * 타임테이블 조회(판매)
     *
     * @programId W-SV-U-0062M01
     * @param req : 조회파라메터
     * @return 조회결과
     */
    /**
    * @see "timeAssign.do GET"
    * */
    public WsncTimeTableSalesDto.FindRes getTmeAssignSales(WsncTimeTableSalesDto.FindReq req)
        throws ParseException {

        log.debug("----------------------------------- 타임테이블 조회(판매) -----------------------------------------");
        WsncTimeTableSalesDvo result = new WsncTimeTableSalesDvo();

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsicDvo = new WsncTimeTableRpbLocaraPsicDvo();
        List<WsncTimeTableSidingDaysDvo> sidingDayDvos = null;
        WsncTimeTablePsicDataDvo psicDataDvos = null;
        List<WsncTimeTableAssignTimeDvo> assignTimeDvos = null;

        WsncTimeTableParamDvo paramDvo = converter.mapSalesParamReqToDvo(req);

        String chnlDvCd = StringUtil.isEmpty(req.chnlDvCd()) ? "K" : req.chnlDvCd();
        String cntrNo = req.cntrNo(); // W20222324935
        String cntrSn = req.cntrSn(); // 1
        String svDvCd = StringUtil.nvl(req.svDvCd(), ""); // dataGb
        //wrkDt는 무조건 오늘 날자(홍세기 매니저님 전달)
        String wrkDt = DateUtil.getNowDayString(); // req.wrkDt(); // P_WRK_DT
        String mtrStatCd = req.mtrStatCd(); //DATA_STUS
        String svBizDclsfCd = req.svBizDclsfCd(); // wrkTypDtl
        //String userId = req.userId();
        String returnUrl = req.returnUrl();

        String newAdrZip = "";
        String contDt = "";
        String sellDate = StringUtil.isEmpty(req.sellDate()) ? DateUtil.getNowDayString() : req.sellDate();
        String basePdCd = "";
        String pdctPdCd = "";
        String sidingYn = "N";
        String spayYn = "N";

        String sowDay = ""; // PAJONG_DAY
        String sdingCombin = "";
        String copnDvCd = ""; //법인격구분 1:개인, 2법인
        String sellDscDbCd = "";
        String time = "";

        if (svDvCd.equals("4")) {
            sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);
        }

        if (svDvCd.equals("1") && (sellDate == null || sellDate.equals(wrkDt))) {
            sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 5);
        }

        if (StringUtil.isEmpty(req.sellDate())) {
            sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 5);
        }

        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(cntrNo, cntrSn, sellDate)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));
        basePdCd = contractDvo.getBasePdCd();
        pdctPdCd = contractDvo.getPdctPdCd();
        newAdrZip = contractDvo.getNewAdrZip();
        contDt = contractDvo.getCntrDt();
        copnDvCd = contractDvo.getCopnDvCd();
        sellDscDbCd = contractDvo.getSellDscDbCd();
        sdingCombin = contractDvo.getSdingCombin(); // lcst09
        String cstSvAsnNo = StringUtil.isNotEmpty(req.cstSvAsnNo()) ? req.cstSvAsnNo() : contractDvo.getCstSvAsnNo();

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(basePdCd, pdctPdCd)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));
        sidingYn = productDvo.getSidingYn();
        spayYn = "3".equals(productDvo.getRglrSppPrcDvCd()) ? "Y" : "N"; // 일시불여부
        //------------------------------------------------------

        // 모종인지 확인
        if ("Y".equals(sidingYn)) {

            // 일시불여부
            if ("Y".equals(spayYn)) {

                //------------------------------------------------------
                // getMojongDays_ilsibul
                sidingDayDvos = this.mapper
                    .selectSidingDaysForSpay(sdingCombin, sellDate, basePdCd, svDvCd, pdctPdCd, cntrNo);
                //------------------------------------------------------

                boolean isAdd40Days = false;

                for (WsncTimeTableSidingDaysDvo sidingDaysDvo : sidingDayDvos) {
                    if (sidingDaysDvo.getAblDays().equals(DateUtil.formatDate(sellDate, "-"))) {
                        isAdd40Days = true;
                        sowDay = sidingDaysDvo.getSowDay();
                        break;
                    }
                }

                if (!isAdd40Days) {
                    sellDate = sidingDayDvos.get(0).getW3th();
                    sowDay = sidingDayDvos.get(0).getSowDay();
                }
                // 일반모종 타임테이블
            } else {
                //------------------------------------------------------
                sidingDayDvos = mapper.selectSidingDays(basePdCd);
                //------------------------------------------------------
            }

        }

        boolean isHcr = "Y".equals(productDvo.getHcrYn());

        // Cubig CC 홈케어 조회용 타임테이블 http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp
        // KSS접수와 동일하게 하기위해 (백현아 K 요청)
        // Cubig CC DATA_GB 변경할수 없음.
        // 상품코드로 접수구분과 DATA_GB 변경
        if (isHcr && chnlDvCd.equals("C") && svDvCd.equals("1")
            && (StringUtil.isEmpty(returnUrl))) {
            chnlDvCd = "W";
            svDvCd = "4";
            returnUrl = "http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp";
        }

        String prtnrNo01 = mapper.selectFnSvpdLocaraPrtnr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);
        String prtnrNoBS01 = mapper.selectFnSvpdLocaraPrtnrBs01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate, "");
        String prtnrNoOwr01 = mapper.selectFnSvpdLocaraPrtnrOwr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);

        /*test*/
        //prtnrNo01 = StringUtil.nvl(prtnrNo01, "621303");

        paramDvo.setChnlDvCd(chnlDvCd);
        paramDvo.setSellDate(sellDate);
        paramDvo.setNewAdrZip(newAdrZip);
        paramDvo.setSvDvCd(svDvCd);
        paramDvo.setCntrNo(cntrNo);
        paramDvo.setCntrSn(cntrSn);
        paramDvo.setInflwChnl(paramDvo.getInflwChnl());
        paramDvo.setSvBizDclsfCd(svBizDclsfCd);
        paramDvo.setPdctPdCd(pdctPdCd);
        paramDvo.setPrtnrNo01(prtnrNo01);
        paramDvo.setPrtnrNoBS01(prtnrNoBS01);
        paramDvo.setPrtnrNoOwr01(prtnrNoOwr01);
        paramDvo.setHcrYn(productDvo.getHcrYn());
        paramDvo.setExYn("");
        paramDvo.setContDt(contDt);
        paramDvo.setCopnDvCd(copnDvCd);
        paramDvo.setSellDscDbCd(sellDscDbCd);
        paramDvo.setBasePdCd(basePdCd);
        paramDvo.setCstSvAsnNo(cstSvAsnNo);

        //-----------------------------------------------------------------------------------------

        // 책임지역 담당자 찾기 selectTimeAssign_v2_step1
        rpbLocaraPsicDvo = mapper.selectRpbLocaraPsic(paramDvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));
        // step1_with
        paramDvo.setPrtnrNo(rpbLocaraPsicDvo.getIchrPrtnrNo());
        paramDvo.setLocalGb(rpbLocaraPsicDvo.getRpbLocaraCd());
        paramDvo.setVstDowValCd(rpbLocaraPsicDvo.getVstDowValCd());
        paramDvo.setOgTpCd(rpbLocaraPsicDvo.getOgTpCd());

        // 담당자 정보 표시 selectTimeAssign_v2_step2
        psicDataDvos = mapper.selectPsicData(rpbLocaraPsicDvo); // left_info

        // 시간표시 selectTimeAssign_v2_step3
        rpbLocaraPsicDvo.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsicDvo));
        rpbLocaraPsicDvo.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsicDvo));
        rpbLocaraPsicDvo.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsicDvo));
        assignTimeDvos = mapper.selectAssignTime(rpbLocaraPsicDvo); // list1

        List<WsncTimeTableDisableDaysDvo> disableDayDvos = mapper.selectDisableDays(paramDvo);
        List<String> offDays = mapper.selectOffDays(paramDvo);

        //---------------------------------------------------------//
        result.setBaseYm(req.baseYm());

        List<WsncTimeTableDaysDvo> days = mapper.selectTimeTableDates(req.baseYm());
        result.setDays(days);

        result.getSmTimes().clear();
        result.getAmTimes().clear();
        result.getPmTimes1().clear();
        result.getPmTimes2().clear();
        result.getNtTimes().clear();

        //result.setAssignTimeDvos(assignTimeDvos); // list1 = assignTimeDvos
        result.setPsics(converter.mapSchdPsicsDvoToDto(psicDataDvos)); // left_info = psics
        result.setSidingDays(converter.mapSidingDaysDvoToDto(sidingDayDvos)); // list2 = sidingDays
        result.setOffDays(offDays); // offdays = offDays
        result.setDisableDays(converter.mapDisableDaysDvoToDto(disableDayDvos)); // diabledays = disableDays

        result.setSvDvCd(svDvCd);
        result.setNewAdrZip(newAdrZip);
        result.setCurDateTimeString(DateUtil.getNowDayString());
        result.setSellDate(sellDate);
        result.setChnlDvCd(chnlDvCd);
        result.setCntrNo(cntrNo);
        result.setCntrSn(cntrSn);
        result.setInflwChnl(paramDvo.getInflwChnl());//bypass
        result.setWrkDt(wrkDt);
        result.setDataStatCd(mtrStatCd);
        result.setSvBizDclsfCd(svBizDclsfCd);
        result.setBasePdCd(basePdCd);
        result.setSowDay(sowDay);//pajong_day
        result.setSdingCombin(sdingCombin);
        result.setReturnUrl(returnUrl);
        result.setMkCo(paramDvo.getMkCo());//bypass
        result.setPrtnrNo(paramDvo.getPrtnrNo());
        result.setOgTpCd(paramDvo.getOgTpCd());

        result.setSidingYn(sidingYn); // 모종여부
        result.setSpayYn(spayYn); // 일시불여부

        // 00000001
        result.setSeq(StringUtils.leftPad(StringUtil.nvl(req.seq(), "1"), 8, "0"));
        result.setCstSvAsnNo(StringUtil.nvl(req.cstSvAsnNo(), ""));

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        result.setUserId(session.getEmployeeIDNumber());
        result.setRcpOgTpCd(session.getOgTpCd());

        for (WsncTimeTableAssignTimeDvo assignTime : assignTimeDvos) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = converter.mapAssignTimeDvoToSmPmNtDvo(assignTime);
            time = assignTime.getTm();
            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));

            if (Integer.valueOf(time) >= 10000 && Integer.valueOf(time) < 50000) {
                result.getSmTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else if (Integer.valueOf(time) > 80000 && Integer.valueOf(time) < 140000) {
                result.getAmTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else if (Integer.valueOf(time) >= 140000 && Integer.valueOf(time) < 180000) {
                result.getPmTimes1().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else if (Integer.valueOf(time) >= 180000 && Integer.valueOf(time) < 200000) {
                result.getPmTimes2().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else
                result.getNtTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
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
    * 타임테이블 시간선택(일정변경)
    *
    * @programId W-MP-U-0186P01
    * @see "nosession_mng_as_month.do"
    *
    * */
    public WsncTimeTableSchdChoDto.FindRes getSchdCho(WsncTimeTableSchdChoDto.FindReq req) {

        WsncTimeTableSchdChoDvo result = new WsncTimeTableSchdChoDvo();
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

        String cntrNo = req.cntrNo();
        String cntrSn = req.cntrSn();
        String chnlDvCd = req.chnlDvCd();
        String sellDate = StringUtil.nvl(req.sellDate(), DateUtil.getNowDayString());
        String svDvCd = "M".equals(chnlDvCd) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(req.svDvCd(), "");
        String svBizDclsfCd = req.svBizDclsfCd(); // 3110

        String prtnrNo = req.prtnrNo(); // 1251831
        //
        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(cntrNo, cntrSn, sellDate)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));
        String basePdCd = contractDvo.getBasePdCd();
        String pdctPdCd = contractDvo.getPdctPdCd();
        String newAdrZip = StringUtil.nvl(req.newAdrZip(), contractDvo.getNewAdrZip());
        String rpbLocaraCd = "";

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(basePdCd, pdctPdCd)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));
        String sidingYn = productDvo.getSidingYn();
        //
        String empId = "";
        if ("3".equals(svDvCd)) {
            empId = mapper.selectFnSvpdLocaraPrtnr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);
        } else {
            empId = prtnrNo;
        }

        // 모종인지 확인
        if ("Y".equals(sidingYn)) {
            result.setSidingDay(
                converter.mapSidingDaysDvoToDto(
                    this.mapper
                        .selectSidingDaysForSpay(
                            contractDvo.getSdingCombin(), sellDate, basePdCd, svDvCd, pdctPdCd, cntrNo
                        )
                )
            );
            result.setMonthSchedule(converter.mapMonthScheduleDvoToDto(mapper.selectMonthSchedule(empId)));
        }

        WsncTimeTableParamDvo paramDvo = new WsncTimeTableParamDvo();
        paramDvo.setChnlDvCd(chnlDvCd);
        paramDvo.setNewAdrZip(newAdrZip);
        paramDvo.setBasePdCd(basePdCd);
        paramDvo.setPrtnrNo(empId);
        paramDvo.setSvDvCd(svDvCd);
        paramDvo.setLocalGb(rpbLocaraCd);
        List<WsncTimeTableDisableDaysDvo> disableDayDvos = mapper.selectDisableDays(paramDvo);
        result.setDisableDays(converter.mapDisableDaysDvoToDto(disableDayDvos));

        //        List<WsncTimeTableDaysDvo> days = mapper.selectTimeTableDates(req.baseYm());
        List<WsncTimeTableDaysDvo> days = mapper.selectTimeTableDates(req.baseYm());
        result.setDays(converter.mapDaysDvoToDto(days));

        result.setNewAdrZip(newAdrZip);
        result.setSvBizDclsfCd(svBizDclsfCd);
        result.setChnlDvCd(chnlDvCd);
        result.setSvDvCd(svDvCd);
        result.setCntrNo(cntrNo);
        result.setCntrSn(cntrSn);
        result.setSellDate(sellDate);
        result.setOrdDt(req.ordDt());
        result.setOrdSeq(req.ordSeq());
        result.setEmpId(empId);
        result.setBasePdCd(basePdCd);
        result.setSidingYn(sidingYn);
        return converter.mapSchdChoDvoToRes(result);
    }

    /**
    * @see "nosession_as_timeAssign.do"
    * */
    public WsncTimeTableTimeChoDto.FindRes getTimeCho(WsncTimeTableTimeChoDto.FindReq req) {

        String cntrNo = req.cntrNo();
        String cntrSn = req.cntrSn();
        String chnlDvCd = req.chnlDvCd();
        String sellDate = StringUtil.nvl(req.sellDate(), DateUtil.getNowDayString());
        String svDvCd = "M".equals(chnlDvCd) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(req.svDvCd(), "");
        String svBizDclsfCd = req.svBizDclsfCd(); // 3110
        String pdctPdCd = req.pdctPdCd();
        String basePdCd = req.basePdCd();
        String hcrYn = req.hcrYn(); // add_gb
        String newAdrZip = req.newAdrZip();
        String prtnrNo01 = mapper.selectFnSvpdLocaraPrtnr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);
        String prtnrNoBS01 = mapper.selectFnSvpdLocaraPrtnrBs01(
            newAdrZip, pdctPdCd, svBizDclsfCd, sellDate, mapper.selectVstDvCd(req.cstSvAsnNo())
        );
        String prtnrNoOwr01 = mapper.selectFnSvpdLocaraPrtnrOwr01(newAdrZip, pdctPdCd, svBizDclsfCd, sellDate);
        String cstSvAsnNo = req.cstSvAsnNo();

        WsncTimeTableParamDvo paramDvo = converter.mapTimeChoParamReqToDvo(req);
        paramDvo.setChnlDvCd(chnlDvCd);
        paramDvo.setSellDate(sellDate);
        paramDvo.setNewAdrZip(newAdrZip);
        paramDvo.setSvDvCd(svDvCd);
        paramDvo.setCntrNo(cntrNo);
        paramDvo.setCntrSn(cntrSn);
        paramDvo.setInflwChnl(paramDvo.getInflwChnl());
        paramDvo.setSvBizDclsfCd(svBizDclsfCd);
        paramDvo.setPdctPdCd(pdctPdCd);
        paramDvo.setPrtnrNo01(prtnrNo01);
        paramDvo.setPrtnrNoBS01(prtnrNoBS01);
        paramDvo.setPrtnrNoOwr01(prtnrNoOwr01);
        paramDvo.setHcrYn(hcrYn);
        paramDvo.setBasePdCd(basePdCd);
        paramDvo.setCstSvAsnNo(cstSvAsnNo);

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsicDvo = mapper.selectRpbLocaraPsic(paramDvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));
        paramDvo.setPrtnrNo(rpbLocaraPsicDvo.getIchrPrtnrNo());
        paramDvo.setLocalGb(rpbLocaraPsicDvo.getRpbLocaraCd());
        paramDvo.setVstDowValCd(rpbLocaraPsicDvo.getVstDowValCd());
        paramDvo.setOgTpCd(rpbLocaraPsicDvo.getOgTpCd());

        // 담당자 정보 표시 selectTimeAssign_v2_step2
        WsncTimeTablePsicDataDvo psicDataDvos = mapper.selectPsicData(rpbLocaraPsicDvo); // left_info

        // 시간표시 selectTimeAssign_v2_step3
        rpbLocaraPsicDvo.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsicDvo));
        rpbLocaraPsicDvo.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsicDvo));
        rpbLocaraPsicDvo.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsicDvo));
        List<WsncTimeTableAssignTimeDvo> assignTimeDvos = mapper.selectAssignTime(rpbLocaraPsicDvo); // list1

        WsncTimeTableTimeChoDvo result = new WsncTimeTableTimeChoDvo();

        String time = "";
        for (WsncTimeTableAssignTimeDvo assignTime : assignTimeDvos) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = converter.mapAssignTimeDvoToSmPmNtDvo(assignTime);
            time = assignTime.getTm();

            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));

            if (Integer.valueOf(time) >= 10000 && Integer.valueOf(time) < 50000) {
                result.getSmTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else if (Integer.valueOf(time) > 80000 && Integer.valueOf(time) < 140000) {
                result.getAmTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else if (Integer.valueOf(time) >= 140000 && Integer.valueOf(time) < 180000) {
                result.getPmTimes1().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else if (Integer.valueOf(time) >= 180000 && Integer.valueOf(time) <= 190000) {
                result.getPmTimes2().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else
                result.getNtTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
        }

        result.setAssignTimes(converter.mapAssignTimeDvoToDto(assignTimeDvos));
        result.setPsics(converter.mapTimePsicsDvoToDto(psicDataDvos));
        return converter.mapTimeChoDvoToRes(result);
    }

    /**
    * @see "nosession_bsnext_timeAssign"
    * */
    protected WsncTimeTableSalesDvo noSessionBsTimeAssign() {
        return null;
    }
}
