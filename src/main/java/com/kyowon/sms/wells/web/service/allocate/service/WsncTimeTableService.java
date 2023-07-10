package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindScheChoReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindTimeAssignReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindTimeChoReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;

import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncTimeTableService {

    private final WsncTimeTableMapper mapper;
    private final WsncTimeTableConverter converter;

    public FindRes getTmeAssign(FindTimeAssignReq req)
        throws ParseException {


        WsncTimeTableDvo dvo = converter.mapTimeAssignReqToParamDvo(req);
        dvo.getSmTimes().clear();
        dvo.getAmTimes().clear();
        dvo.getPmTimes1().clear();
        dvo.getPmTimes2().clear();
        dvo.getNtTimes().clear();

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsic;
        List<WsncTimeTableSidingDaysDvo> sidingDays = null;
        WsncTimeTablePsicDvo psic;

        // 00000001
        dvo.setSeq(StringUtils.leftPad(StringUtil.nvl(req.seq(), "1"), 8, "0"));

        String sellDate = dvo.getSellDate();

        if (StringUtil.isEmpty(req.sellDate()))
            switch (req.chnlDvCd()) {
                case "K":
                case "W":
                case "C":
                    sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);
                    break;
                default:
                    sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 5);
                    break;
            }

        // 홈케어
        if ("4".equals(dvo.getSvDvCd()))
            sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 1);

        // 설치 && (sellDate is null or selldate == wrkDt)
        if ("1".equals(dvo.getSvDvCd()) && (StringUtil.isEmpty(dvo.getSellDate())
            || StringUtil.nvl(dvo.getSellDate(), "").equals(StringUtil.nvl(dvo.getWrkDt(), ""))))
            sellDate = DateUtil.addDays(DateUtil.getNowDayString(), 5);

        dvo.setSellDate(sellDate);

        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));

        dvo.setBasePdCd(contractDvo.getBasePdCd());
        dvo.setPdctPdCd(contractDvo.getPdctPdCd());
        dvo.setNewAdrZip(contractDvo.getNewAdrZip());
        dvo.setContDt("C".equals(dvo.getChnlDvCd()) ? DateUtil.getNowDayString() : contractDvo.getCntrDt());
        dvo.setCopnDvCd(contractDvo.getCopnDvCd());
        dvo.setSellDscDbCd(contractDvo.getSellDscDbCd());
        dvo.setSdingCombin(contractDvo.getSdingCombin()); // lcst09;
        dvo.setCstSvAsnNo(
            StringUtil.isNotEmpty(dvo.getCstSvAsnNo()) ? dvo.getCstSvAsnNo() : contractDvo.getCstSvAsnNo()
        );

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));
        dvo.setSidingYn(productDvo.getSidingYn());
        dvo.setSpayYn("3".equals(productDvo.getRglrSppPrcDvCd()) ? "Y" : "N"); // 일시불여부
        //------------------------------------------------------

        // 모종인지 확인
        // 일시불여부
        // 일반모종 타임테이블
        //------------------------------------------------------
        //------------------------------------------------------
        if ("Y".equals(dvo.getSidingYn()))
            if ("Y".equals(dvo.getSpayYn())) {

                //------------------------------------------------------
                // getMojongDays_ilsibul
                sidingDays = this.mapper.selectSidingDaysForSpay(dvo);
                //------------------------------------------------------

                boolean isAdd40Days = false;

                for (WsncTimeTableSidingDaysDvo sidingDay : sidingDays)
                    if (sidingDay.getAblDays().equals(DateUtil.formatDate(sellDate, "-"))) {
                        isAdd40Days = true;
                        dvo.setSowDay(sidingDay.getSowDay());
                        break;
                    }

                if (!isAdd40Days) {
                    sellDate = sidingDays.get(0).getW3th();
                    dvo.setSowDay(sidingDays.get(0).getSowDay());
                }
            } else
                sidingDays = mapper.selectSidingDays(dvo);

        dvo.setSellDate(sellDate);
        dvo.setHcr("Y".equals(productDvo.getHcrYn()));
        dvo.setSvBizHclsfCd(productDvo.getSvBizHclsfCd());//addgb

        String prtnrNo01 = mapper.selectFnSvpdLocaraPrtnr01(dvo);
        String prtnrNoBS01 = mapper.selectFnSvpdLocaraPrtnrBs01(dvo);
        String prtnrNoOwr01 = mapper.selectFnSvpdLocaraPrtnrOwr01(dvo);

        dvo.setPrtnrNo01(prtnrNo01);
        dvo.setPrtnrNoBS01(prtnrNoBS01);
        dvo.setPrtnrNoOwr01(prtnrNoOwr01);
        dvo.setHcrYn(productDvo.getHcrYn());
        dvo.setExYn("C".equals(dvo.getChnlDvCd()) ? "Y" : "");

        // 책임지역 담당자 찾기 selectTimeAssign_v2_step1
        rpbLocaraPsic = mapper.selectRpbLocaraPsic(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));

        // step1_with
        dvo.setPrtnrNo(rpbLocaraPsic.getIchrPrtnrNo());
        dvo.setLocalGb(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());
        dvo.setOgTpCd(rpbLocaraPsic.getOgTpCd());

        // 담당자 정보 표시 selectTimeAssign_v2_step2
        psic = mapper.selectPsic(rpbLocaraPsic); // left_info

        // 시간표시 selectTimeAssign_v2_step3
        rpbLocaraPsic.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsic));
        List<WsncTimeTableAssignTimeDvo> assignTimes = mapper.selectAssignTimes(rpbLocaraPsic); // list1

        dvo.setDays(converter.mapDaysDvoToDto(mapper.selectTimeTableDates(req.baseYm())));
        dvo.setPsic(converter.mapPsicDvoToDto(psic)); // left_info = psics
        dvo.setSidingDays(converter.mapSidingDaysDvoToDto(sidingDays)); // list2 = sidingDays
        dvo.setOffDays(mapper.selectOffDays(dvo)); // offdays = offDays
        dvo.setDisableDays(converter.mapDisableDaysDvoToDto(mapper.selectDisableDays(dvo))); // diabledays = disableDays
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        dvo.setUserId(session.getEmployeeIDNumber());
        dvo.setRcpOgTpCd(session.getOgTpCd());

        String time;
        int iTime;
        for (WsncTimeTableAssignTimeDvo assignTime : assignTimes) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = converter.mapAssignTimeDvoToSmPmNtDvo(assignTime);
            time = assignTime.getTm();
            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));
            smPmNtDvo.setEnableYn(assignTime.getWrkChk2());

            iTime = Integer.parseInt(time);

            if (iTime >= 10000 && iTime < 50000)
                dvo.getSmTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            else if (iTime > 80000 && iTime < 140000)
                dvo.getAmTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            else if (iTime >= 140000 && iTime < 180000)
                dvo.getPmTimes1().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            else if (iTime >= 180000 && iTime < 200000)
                dvo.getPmTimes2().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            else
                dvo.getNtTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
        }

        return converter.mapTimeAssignDvoToRes(dvo);
    }

    /**
    *
    * @param req sellDate: 배정선택일자(달력)
    * @param req baseYm: 달력기준 년월
    * @param req svDvCd: 1:설치, 2: BS, 3: AS, 4: 홈케어
    * @param req chnlDvCd: 유입체널 W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어)
    * @param req cntrNo: 계약번호
    * @param req cntrSn: 계약순번
    * @param req prtnrNo: 파트너번호
    * @param req basePdCdList: 선택불가일자 조회 시 in 조건으로 사용
    * @param req contDt: 계약일자
    * @param req vstDowValCd: 방문요일값 1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일
    * @param req copnDvCd: 법인격구분코드 1:개인, 2:법인
    *
    **/
    public FindRes getScheduleChoice(FindScheChoReq req) {

        WsncTimeTableDvo dvo = converter.mapScheChoReqToDvo(req);
        dvo.getSmTimes().clear();
        dvo.getAmTimes().clear();
        dvo.getPmTimes1().clear();
        dvo.getPmTimes2().clear();
        dvo.getNtTimes().clear();

        dvo.setSellDate(StringUtil.nvl(req.sellDate(), DateUtil.getNowDayString()));
        dvo.setSvDvCd("M".equals(dvo.getChnlDvCd()) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(dvo.getSvDvCd(), ""));
        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));
        dvo.setBasePdCd(contractDvo.getBasePdCd());
        dvo.setPdctPdCd(contractDvo.getPdctPdCd());
        dvo.setNewAdrZip(StringUtil.nvl(req.newAdrZip(), contractDvo.getNewAdrZip()));
        dvo.setRpbLocaraCd("");

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));
        dvo.setSidingYn(productDvo.getSidingYn());

        dvo.setContDt("C".equals(dvo.getChnlDvCd()) ? DateUtil.getNowDayString() : contractDvo.getCntrDt());
        dvo.setPrtnrNo("3".equals(dvo.getSvDvCd()) ? mapper.selectFnSvpdLocaraPrtnr01(dvo) : dvo.getPrtnrNo());

        // 모종인지 확인
        if ("Y".equals(dvo.getSidingYn())) {
            dvo.setSidingDays(converter.mapSidingDaysDvoToDto(this.mapper.selectSidingDaysForSpay(dvo)));
            dvo.setMonthSchedules(converter.mapMonthScheduleDvoToDto(mapper.selectMonthSchedule(dvo)));
        }

        List<WsncTimeTableDisableDaysDvo> disableDayDvos = mapper.selectDisableDays(dvo);
        dvo.setDisableDays(converter.mapDisableDaysDvoToDto(disableDayDvos));
        dvo.setDays(converter.mapDaysDvoToDto(mapper.selectTimeTableDates(req.baseYm())));

        return converter.mapSchdChoDvoToRes(dvo);
    }


    /**
    *
    * @param req sellDate: 배정선택일자(달력)
    * @param req svDvCd: 1:설치, 2: BS, 3: AS, 4: 홈케어
    * @param req chnlDvCd: W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어)
    * @param req newAdrZip: 우편번호
    * @param req pdctPdCd: 제품상품코드
    * @param req svBizDclsfCd:
    * @param req inflwChnl: 채널구분
    *                       AS-IS에서는 파라메터로 받았으나 FindTimeChoReq에서 chnlDvCd 에 따라 정함.
    *                       1: Cubig CC, 2: Wells, 3: KSS, 4: wells 홈페이지, 5:K-members
    * @param req vstGb: 방문구분코드
    *                   10:방문, 11:매니저, 12:엔지니어, 13:홈마스터, 20:택배
    * @param req cstSvAsnNo: 고객서비스배정번호
    *
    * */
    public FindRes getTimeChoice(FindTimeChoReq req) {

        WsncTimeTableDvo dvo = converter.mapTimeChoReqToDvo(req);

        dvo.setSellDate(StringUtil.nvl(dvo.getSellDate(), DateUtil.getNowDayString()));
        dvo.setSvDvCd("M".equals(dvo.getChnlDvCd()) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(dvo.getSvDvCd(), ""));

        dvo.setPrtnrNo01(mapper.selectFnSvpdLocaraPrtnr01(dvo));
        dvo.setPrtnrNoBS01(mapper.selectFnSvpdLocaraPrtnrBs01(dvo));
        dvo.setPrtnrNoOwr01(mapper.selectFnSvpdLocaraPrtnrOwr01(dvo));

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsic = mapper.selectRpbLocaraPsic(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));
        dvo.setPrtnrNo(rpbLocaraPsic.getIchrPrtnrNo());
        dvo.setLocalGb(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());
        dvo.setOgTpCd(rpbLocaraPsic.getOgTpCd());

        // 담당자 정보 표시 selectTimeAssign_v2_step2
        WsncTimeTablePsicDvo psic = mapper.selectPsic(rpbLocaraPsic); // left_info

        // 시간표시 selectTimeAssign_v2_step3
        rpbLocaraPsic.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsic));
        List<WsncTimeTableAssignTimeDvo> assignTimes = mapper.selectAssignTimes(rpbLocaraPsic); // list1

        String time;
        int iTime;
        for (WsncTimeTableAssignTimeDvo assignTime : assignTimes) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = converter.mapAssignTimeDvoToSmPmNtDvo(assignTime);
            time = assignTime.getTm();

            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));
            smPmNtDvo.setEnableYn(assignTime.getWrkChk2());

            iTime = Integer.parseInt(time);

            if (iTime >= 10000 && iTime < 50000)
                dvo.getSmTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            else if (iTime > 80000 && iTime < 140000)
                dvo.getAmTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            else if (iTime >= 140000 && iTime < 180000)
                dvo.getPmTimes1().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            else if (iTime >= 180000 && iTime <= 190000)
                dvo.getPmTimes2().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            else
                dvo.getNtTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
        }

        dvo.setAssignTimes(converter.mapAssignTimeDvoToDto(assignTimes));
        dvo.setPsic(converter.mapPsicDvoToDto(psic));

        return converter.mapTimeChoDvoToRes(dvo);
    }
}
