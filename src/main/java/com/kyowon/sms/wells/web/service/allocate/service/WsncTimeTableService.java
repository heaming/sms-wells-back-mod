package com.kyowon.sms.wells.web.service.allocate.service;

import java.text.ParseException;
import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableAsMonthDto;
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
     * 타임테이블 조회(판매) 팝업
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

        WsncTimeTableSalesParamDvo paramDvo = converter.mapSalesParamReqToDvo(req);

        String chnlDvCd = StringUtil.isEmpty(req.chnlDvCd()) ? "K" : req.chnlDvCd();
        String cntrNo = req.cntrNo(); // W20222324935
        String cntrSn = req.cntrSn(); // 1
        String svDvCd = StringUtil.nvl(req.svDvCd(), ""); // dataGb
        String wrkDt = req.wrkDt(); // P_WRK_DT
        String dataStatCd = req.dataStatCd(); //DATA_STUS
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

        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(cntrNo, cntrSn).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(contractDvo.getBasePdCd(), contractDvo.getPdctPdCd());

        basePdCd = contractDvo.getBasePdCd();
        pdctPdCd = contractDvo.getPdctPdCd();

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
                    .selectSidingDaysForSpay(lcst09, sellDate, basePdCd, svDvCd, pdctPdCd, cntrNo);
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

        newAdrZip = contractDvo.getAdrZip();
        contDt = contractDvo.getCntrDt();
        copnDvCd = contractDvo.getCopnDvCd();
        sellDscDbCd = contractDvo.getSellDscDbCd();

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
        paramDvo.setCopnDvCd(copnDvCd);
        paramDvo.setSellDscDbCd(sellDscDbCd);
        paramDvo.setBasePdCd(basePdCd);

        //-----------------------------------------------------------------------------------------

        // 책임지역 담당자 찾기
        // selectTimeAssign_v2_step1
        rpbLocaraPsicDvo = mapper.selectRpbLocaraPsic(paramDvo); // step1_with
        paramDvo.setPrtnrNo(rpbLocaraPsicDvo.getIchrPrtnrNo());
        paramDvo.setLocalGb(rpbLocaraPsicDvo.getRpbLocaraCd());
        paramDvo.setVstDowValCd(rpbLocaraPsicDvo.getVstDowValCd());
        paramDvo.setOgTpCd(rpbLocaraPsicDvo.getOgTpCd());

        // 담당자 정보 표시 (왼쪽)
        // selectTimeAssign_v2_step2
        psicDataDvos = mapper.selectPsicData(rpbLocaraPsicDvo); // left_info

        // 시간표시
        // selectTimeAssign_v2_step3
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

        result.getArrSm().clear();
        result.getArrAm().clear();
        result.getArrPm1().clear();
        result.getArrPm2().clear();
        result.getArrNt().clear();

        //result.setAssignTimeDvos(assignTimeDvos); // list1 = assignTimeDvos
        result.setPsicDatas(psicDataDvos); // left_info = psicDatas
        result.setSidingDays(sidingDayDvos); // list2 = sidingDays
        result.setOffDays(offDays); // offdays = offDays
        result.setDisableDays(disableDayDvos); // diabledays = disableDays

        result.setSvDvCd(svDvCd);
        result.setNewAdrZip(newAdrZip);
        result.setCurDateTimeString(DateUtil.getNowDayString());
        result.setSellDate(sellDate);
        result.setChnlDvCd(chnlDvCd);
        result.setCntrNo(cntrNo);
        result.setCntrSn(cntrSn);
        result.setInflwChnl(paramDvo.getInflwChnl());//bypass
        result.setWrkDt(wrkDt);
        result.setDataStatCd(dataStatCd);
        result.setSvBizDclsfCd(svBizDclsfCd);
        result.setBasePdCd(basePdCd);
        //result.setUserId(userId);
        result.setSowDay(sowDay);//pajong_day
        result.setLcst09(lcst09);
        result.setReturnUrl(returnUrl);
        result.setMkCo(paramDvo.getMkCo());//bypass
        result.setPrtnrNo(paramDvo.getPrtnrNo());
        result.setOgTpCd(paramDvo.getOgTpCd());
        result.setWrkDt(wrkDt);

        result.setSidingYn(sidingYn); // 모종여부
        result.setSpayYn(spayYn); // 일시불여부


        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        result.setUserId(session.getEmployeeIDNumber());
        result.setRcpOgTpCd(session.getOgTpCd());

        for (WsncTimeTableAssignTimeDvo assignTime : assignTimeDvos) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = new WsncTimeTableSmPmNtDvo();
            time = assignTime.getTm();
            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));
            smPmNtDvo.setCnt(assignTime.getWrkCnt());
            smPmNtDvo.setAblYn(assignTime.getWrkChk2());

            if (Integer.valueOf(time) >= 10000 && Integer.valueOf(time) < 50000) {
                result.getArrSm().add(smPmNtDvo);
            } else if (Integer.valueOf(time) > 80000 && Integer.valueOf(time) < 140000) {
                result.getArrAm().add(smPmNtDvo);
            } else if (Integer.valueOf(time) >= 140000 && Integer.valueOf(time) < 180000) {
                result.getArrPm1().add(smPmNtDvo);
            } else if (Integer.valueOf(time) >= 180000 && Integer.valueOf(time) < 200000) {
                result.getArrPm2().add(smPmNtDvo);
            } else
                result.getArrNt().add(smPmNtDvo);

        }

        log.debug("baesYm: {}", result.getBaseYm());
        log.debug("NewAdrZip: {}", result.getNewAdrZip());
        log.debug("CurDateTimeString: {}", result.getCurDateTimeString());
        log.debug("SellDate: {}", result.getSellDate());
        log.debug("ChnlDvCd: {}", result.getChnlDvCd());
        log.debug("CntrNo: {}", result.getCntrNo());
        log.debug("CntrSn: {}", result.getCntrSn());
        log.debug("inflwChnl: {}", result.getInflwChnl());
        log.debug("WrkDt: {}", result.getWrkDt());
        log.debug("DataStatCd: {}", result.getDataStatCd());
        log.debug("SvBizDclsfCd: {}", result.getSvBizDclsfCd());
        log.debug("BasePdCd: {}", result.getBasePdCd());
        log.debug("UserId: {}", result.getUserId());
        log.debug("SowDay: {}", result.getSowDay());
        log.debug("Lcst09: {}", result.getLcst09());
        log.debug("returnUrl: {}", result.getReturnUrl());
        log.debug("MkCo: {}", result.getMkCo());
        log.debug("prtnrNo: {}", result.getPrtnrNo());

        log.debug(
            "offDays: {}", result.getOffDays() != null ? result.getOffDays().toArray().toString() : "offDays is null"
        );
        log.debug(
            "psicDatas: {}",
            result.getPsicDatas() != null ? result.getPsicDatas().toString() : "psicDatas is null"
        );
        log.debug(
            "sidingDays: {}",
            result.getSidingDays() != null ? result.getSidingDays().toString() : "sidingDays is null"
        );
        log.debug(
            "disableDays: {}",
            result.getDisableDays() != null ? result.getDisableDays().toString() : "disableDays is null"
        );

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
