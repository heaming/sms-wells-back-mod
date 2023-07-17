package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindScheChoReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindTimeAssignReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.FindTimeChoReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.SidingDays;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncTimeTableService {

    private final String nowDay = DateUtil.getNowDayString();

    private final WsncTimeTableMapper mapper;
    private final WsncTimeTableConverter converter;

    /**
    * /timeAssign_list.do Wells홈페이지, K멤버스 타임테이블
    * /timeAssign_test.do CubicCC
    * /timeAssign.do GET Wells홈페이지, kmembers
    * /timeAssign.do POST KSS
    * /timeAssign_wells.do
    * /timeAssign_wells_list.do
    *
    *
    * 단건일 경우 basePdCd 필수, svBizDclsfCd 필수
    *
    * 다건일 경우 basePdCd "," 구분으로 합쳐서
    * 다건일 경우 svBizDclsfCd "," 구분으로 합쳐서
    *
    * 계약번호로 조회 시 cntrNo=W20227121814, cntrSn=1,3 와 같이 데이터 유입.
    *
    * 다건 처리 시에 웰스팜 + 정수기 와 같은 케이스는 없다(웰스팜과 정수기는 서로 워크 그룹이 다르다)
    *
    *
    * @param req seq
    * @param req *newAdrZip
    * @param req wrkDt 접수일자
    * @param req pdctPdCd
    * @param req *svDvCd
    * @param req *chnlDvCd 유입체널 W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어)
    * @param req svBizDclsfCd
    * @param req *sellDate
    *
    * @param req cntrNo
     *@param req cntrSn
     *
    * */
    public FindRes getTmeAssign(FindTimeAssignReq req)
        throws ParseException {

        WsncTimeTableDvo dvo = converter.mapTimeAssignReqToParamDvo(req);

        dvo.getSmTimes().clear();
        dvo.getAmTimes().clear();
        dvo.getPmTimes1().clear();
        dvo.getPmTimes2().clear();
        dvo.getNtTimes().clear();

        dvo.getSvBizDclsfCds().clear();

        /*-----------------------------------------------------------------------------------------------*/
        //다건 svBizDclsfCds 처리
        dvo.getSvBizDclsfCds().add(dvo.getSvBizDclsfCd());
        if (StringUtil.nvl(dvo.getSvBizDclsfCd(), "").contains(",")) {
            dvo.setSvBizDclsfCds(Arrays.asList(dvo.getSvBizDclsfCd().split("\\,")));
            dvo.setSvBizDclsfCd(dvo.getSvBizDclsfCds().get(0));
        }

        //다건 basePdCd 처리
        if (StringUtil.nvl(dvo.getBasePdCd(), "").contains(",")) {
            dvo.setBasePdCds(Arrays.asList(dvo.getBasePdCd().split("\\,")));
            dvo.setBasePdCd(dvo.getBasePdCds().get(0));
            if (dvo.getBasePdCds().size() != dvo.getSvBizDclsfCds().size())
                throw new BizException("다건처리 유입 데이터 오류");

            //다건 workTypeDtl 처리
            String workTypeDtl = "";
            for (int i = 0; i < dvo.getSvBizDclsfCds().size(); i++)
                workTypeDtl += (i == 0 ? "" : "|") + dvo.getBasePdCds().get(i) + "," + dvo.getSvBizDclsfCds().get(i);

            dvo.setWorkTypeDtl(workTypeDtl);
        }

        //단건 basePdCd, svBizDclsfCds, workTypeDtl 처리
        if (StringUtil.isNotEmpty(dvo.getBasePdCd()) && StringUtil.isNotEmpty(dvo.getSvBizDclsfCd())) {
            dvo.setBasePdCds(new ArrayList<>());
            dvo.setSvBizDclsfCds(new ArrayList<>());
            dvo.setWorkTypeDtl(dvo.getBasePdCd() + "," + dvo.getSvBizDclsfCd());
        }

        //        log.debug("-- basePdCds = " + Arrays.toString(dvo.getBasePdCds().toArray()));
        //        log.debug("-- svBizDclsfCds = " + Arrays.toString(dvo.getSvBizDclsfCds().toArray()));
        //        log.debug("-- workTypeDtl = " + dvo.getWorkTypeDtl());
        /*-----------------------------------------------------------------------------------------------*/

        // 00000001
        dvo.setSeq(StringUtils.leftPad(StringUtil.nvl(req.seq(), "1"), 8, "0"));

        String sellDate = dvo.getSellDate();

        switch (req.chnlDvCd()) {
            case "W":
            case "P": // wells 홈페이지 혹은 k-members인 경우 +1일
            case "C": // CC경우 +1일
                //토요일이면 +2일
                sellDate = DateUtil.addDays(nowDay, DateUtil.isDayOfWeek(nowDay, 7) ? 2 : 1);
                break;
            default:
                sellDate = DateUtil.addDays(nowDay, 5);
                break;
        }

        // 홈케어
        if ("4".equals(dvo.getSvDvCd()))
            sellDate = DateUtil.addDays(nowDay, 1);

        // 설치 && (sellDate is null or selldate == wrkDt)
        if ("1".equals(dvo.getSvDvCd())
            && (StringUtil.isEmpty(req.sellDate()) || sellDate.equals(StringUtil.nvl(req.wrkDt(), ""))))
            sellDate = DateUtil.addDays(nowDay, 5);

        dvo.setSellDate(sellDate);

        if (StringUtil.isNotEmpty(dvo.getCstSvAsnNo()))
            dvo.setVstDvCd(mapper.selectVstDvCd(dvo)); // 방문구분코드

        /*-----------------------------------------------------------------------------------------------*/
        if (StringUtil.isNotEmpty(dvo.getCntrNo())) {

            if (StringUtil.isEmpty(dvo.getCntrSn()))
                dvo.setCntrSn("1");

            dvo.setCntrSns(Arrays.asList(dvo.getCntrSn().split(",")));
            /**
             * @param cntrNo
             * @param cntrSns
             * @param sellDate
             */
            List<WsncTimeTableCntrDvo> contractDvos = mapper.selectContract(dvo);
            if (contractDvos.size() == 0)
                new BizException("MSG_ALT_NO_PRODUCT_FOUND");

            dvo.getBasePdCds().clear();
            dvo.getPdctPdCds().clear();
            dvo.getSdingCombins().clear();

            for (WsncTimeTableCntrDvo contractDvo : contractDvos) {
                dvo.getBasePdCds().add(contractDvo.getBasePdCd());
                dvo.getPdctPdCds().add(contractDvo.getPdctPdCd());
                dvo.getSdingCombins().add(contractDvo.getSdingCombin());
            }

            String workTypeDtl = "";

            for (int i = 0; i < dvo.getBasePdCds().size(); i++)
                workTypeDtl += (i == 0 ? "" : "|") + dvo.getBasePdCds().get(i) + "," + dvo.getSvBizDclsfCds().get(i);

            dvo.setWorkTypeDtl(workTypeDtl); // WP01120279,1110|WP01110622,3100

            WsncTimeTableCntrDvo contractDvo = contractDvos.get(0);

            dvo.setBasePdCd(contractDvo.getBasePdCd());
            dvo.setNewAdrZip(contractDvo.getNewAdrZip());
            dvo.setContDt(
                StringUtil.decode(
                    dvo.getChnlDvCd(), "C", nowDay, //CC
                    "W", nowDay, // Wells
                    "P", nowDay, // k-members
                    contractDvo.getCntrDt()
                )
            );
            dvo.setCopnDvCd(contractDvo.getCopnDvCd()); // 법인격구분
            dvo.setSellDscDbCd(contractDvo.getSellDscDbCd()); // 판매할인구분코드
            dvo.setSdingCombin(contractDvo.getSdingCombin()); // lcst09 모종패키지 분리여부
            dvo.setCstSvAsnNo(
                StringUtil.isEmpty(req.cstSvAsnNo()) ? contractDvo.getCstSvAsnNo() : req.cstSvAsnNo()
            );
        }
        /*-----------------------------------------------------------------------------------------------*/

        dvo.getPdctPdCds().clear();

        for (int i = 0; i < dvo.getBasePdCds().size(); i++) {

            /**
             * @param basePdCd
             * @param svDvCd
             */
            WsncTimeTableProductDvo productDvo = mapper.selectProduct(dvo.getSvDvCd(), dvo.getBasePdCds().get(i))
                .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));

            String sidingYn = productDvo.getSidingYn();
            String spayYn = "3".equals(productDvo.getRglrSppPrcDvCd()) ? "Y" : "N"; // 일시불여부
            String hcrYn = productDvo.getHcrYn();

            dvo.getPdctPdCds().add(productDvo.getPdctPdCd());

            if(i == 0) dvo.setPdctPdCd(productDvo.getPdctPdCd());

            if ("Y".equals(sidingYn)) {

                List<WsncTimeTableSidingDaysDvo> sidingDays = null;

                //------------------------------------------------------
                // getMojongDays_ilsibul
                //------------------------------------------------------
                if ("Y".equals(spayYn)) {
                    /**
                     * @param sdingCombin
                     * @param sellDate
                     * @param basePdCd
                     * @param pdctPdCd
                     * @param svDvCd
                     * @param cntrNo
                    */
                    sidingDays = this.mapper.selectSidingDaysForSpay(
                        dvo.getSdingCombins().get(i),
                        dvo.getSellDate(),
                        dvo.getBasePdCds().get(i),
                        dvo.getPdctPdCds().get(i),
                        dvo.getSvDvCd(),
                        dvo.getCntrNo()
                    );
                    boolean isAdd40Days = false;
                    for (WsncTimeTableSidingDaysDvo sidingDay : sidingDays)
                        if (sidingDay.getEnableDays().equals(DateUtil.formatDate(sellDate, "-"))) {
                            isAdd40Days = true;
                            dvo.setSowDay(sidingDay.getSowDay());
                            break;
                        }
                    if (!isAdd40Days) {
                        sellDate = sidingDays.get(0).getW3th();
                        dvo.setSellDate(sellDate);
                        dvo.setSowDay(sidingDays.get(0).getSowDay());
                    }
                } else
                    /**
                    * @param basePdCd
                    */
                    sidingDays = mapper.selectSidingDays(dvo.getBasePdCds().get(i));

                List<SidingDays> sidingDaysCollection = dvo.getSidingDays();
                sidingDaysCollection.retainAll(converter.mapSidingDaysDvoToDto(sidingDays)); // 교집합
                dvo.setSidingDays(sidingDaysCollection); // list2 = sidingDays
            }
        }

        /*
         * @param newAdrZip
         * @param pdctPdCd
         * @param workTypeDtl
         * @param sellDate
         * @param vstDvCd
         **/
        log.debug("-- PdctPdCd = " + dvo.getPdctPdCd());
        log.debug("-- PdctPdCds = " + Arrays.toString(dvo.getPdctPdCds().toArray()));
        String prtnrNo01 = mapper.selectFnSvpdLocaraPrtnr01(dvo);
        String prtnrNoBS01 = mapper.selectFnSvpdLocaraPrtnrBs01(dvo);
        String prtnrNoOwr01 = mapper.selectFnSvpdLocaraPrtnrOwr01(dvo);

        dvo.setPrtnrNo01(prtnrNo01);
        dvo.setPrtnrNoBS01(prtnrNoBS01);
        dvo.setPrtnrNoOwr01(prtnrNoOwr01);
        dvo.setExYn("C".equals(dvo.getChnlDvCd()) ? "Y" : "");

        /*
         * 책임지역 담당자 찾기 ### selectTimeAssign_v2_step1 ###
         *
         * @param inflwChnl
         * @param chnlDvCd      유입체널 W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어)
         * @param sellDate
         * @param workTypeDtl
         * @param pdctPdCd
         * @param vstDvCd
         * @param svDvCd
         * @param newAdrZip
         * @param prtnrNo01
         * @param prtnrNoBS01
         * @param prtnrNoOwr01
         * @param cstSvAsnNo
         * */
        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsic = mapper.selectRpbLocaraPsic(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));

        // step1_with
        dvo.setPrtnrNo(rpbLocaraPsic.getIchrPrtnrNo());
        dvo.setLocalGb(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());
        dvo.setOgTpCd(rpbLocaraPsic.getOgTpCd());
        dvo.setRpbLocaraCd(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());

        /*
         * 담당자 정보 표시 ### selectTimeAssign_v2_step2 ###
         *
         *
         * @param session.tenantId
         * @param rpbLocaraPsic > sellDate
         * @param rpbLocaraPsic > wrkCnt          전체 작업 건수 합 (FN_SVPD_WRK_CT_TOT_01)
         * @param rpbLocaraPsic > vstDowVal       방문요일
         * @param rpbLocaraPsic > vstPos          방문가능, 해당일 방문불가
         * @param rpbLocaraPsic > rstrCndtUseYn   제약조건사용여부
         * @param rpbLocaraPsic > udsnUseYn       미지정사용여부
         * @param rpbLocaraPsic > satWrkYn        토요일근무여부
         * @param rpbLocaraPsic > dfYn            휴무여부
         * @param rpbLocaraPsic > dowDvCd         요일구분코드
         * @param rpbLocaraPsic > fr2pLgldCd      앞2자리법정동코드
         * @param rpbLocaraPsic > ichrPrtnrNo     담당파트너번호
         * @param rpbLocaraPsic > rpbLocaraCd     책임지역코드
         * @param rpbLocaraPsic > wkGrpCd         작업그룹코드
         */
        WsncTimeTablePsicDvo psic = mapper.selectPsic(rpbLocaraPsic); // left_info
        dvo.setPsic(converter.mapPsicDvoToDto(psic)); // left_info = psics

        /*
         * 시간표시 ### selectTimeAssign_v2_step3 ###
         *
         * @param rpbLocaraPsic > sellDate
         * @param rpbLocaraPsic > ichrPrtnrNo 담당파트너번호
         * */
        rpbLocaraPsic.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsic));

        /*
         * @param rpbLocaraPsic > rpbLocaraCd 책임지역코드
         * @param rpbLocaraPsic > wkGrpCd     작업그룹코드
         * @param rpbLocaraPsic > sellDate
         * */
        rpbLocaraPsic.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsic));

        /*
         * @param rpbLocaraPsic > rpbLocaraCd 책임지역코드
         * @param rpbLocaraPsic > wkGrpCd     작업그룹코드
         * @param rpbLocaraPsic > sellDate
         * */
        rpbLocaraPsic.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsic));

        /*
         * @param baseYm
         * */
        dvo.setDays(converter.mapDaysDvoToDto(mapper.selectTimeTableDates(req.baseYm())));

        /*
         * offdays = offDays
         *
         * @param newAdrZip
         * */
        dvo.setOffDays(mapper.selectOffDays(dvo));

        /*
         *  diabledays = disableDays
         *
         * @param chnlDvCd                  유입체널(W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어))
         * @param newAdrZip
         * @param prtnrNo
         * @param svDvCd                    서비스구분코드(1:설치, 2: BS, 3: AS, 4: 홈케어)
         * @param rpbLocaraCd               책임지역코드
         * @param svBizDclsfCd              서비스업무세분류코드
         * @param contDt                    계약일자
         * @param vstDowValCd               방문요일구분코드
         * @param copnDvCd                  법인격구분코드(1:개인, 2:법인)
         * @param sellDscDbCd               판매할인구분코드
         * @param basePdCd || basePdCdList
         * */
        dvo.setDisableDays(converter.mapDisableDaysDvoToDto(mapper.selectDisableDays(dvo)));

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        dvo.setUserId(session.getEmployeeIDNumber());
        dvo.setRcpOgTpCd(session.getOgTpCd());

        /*
         *
         * list1
         *
         * @param empTWrkCnt
         * @param degWrkCnt
         * @param wrkCnt
         * @param sellDate
         * @param ichrPrtnrNo
         * @param wkHhCd
         * */
        List<WsncTimeTableAssignTimeDvo> assignTimes = mapper.selectAssignTimes(rpbLocaraPsic);

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

        if (StringUtil.isEmpty(dvo.getCntrNo()) || StringUtil.isEmpty(dvo.getCntrSn()))
            throw new BizException("MSG_ALT_CNTR_NO_NOT_EXIST");

        /*---------------------------------------------------------------------------*/
        dvo.setSellDate(StringUtil.nvl(req.sellDate(), nowDay));
        dvo.setSvDvCd("M".equals(dvo.getChnlDvCd()) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(dvo.getSvDvCd(), ""));
        /*---------------------------------------------------------------------------*/

        //다건 svBizDclsfCds 처리
        if (StringUtil.nvl(dvo.getSvBizDclsfCd(), "").contains(",")) {
            dvo.setSvBizDclsfCds(Arrays.asList(dvo.getSvBizDclsfCd().split("\\,")));
            dvo.setSvBizDclsfCd(dvo.getSvBizDclsfCds().get(0));
        }

        dvo.setCntrSns(Arrays.asList(dvo.getCntrSn().split(",")));

        /*---------------------------------------------------------------------------*/
        /*
         * @param cntrNo
         * @param cntrSn
         * @param sellDate
         */
        List<WsncTimeTableCntrDvo> contractDvos = mapper.selectContract(dvo);
        if (contractDvos.size() == 0)
            new BizException("MSG_ALT_NO_PRODUCT_FOUND");

        dvo.setBasePdCds(new ArrayList<>());
        dvo.setPdctPdCds(new ArrayList<>());
        dvo.setSdingCombins(new ArrayList<>());

        for (WsncTimeTableCntrDvo contractDvo : contractDvos) {
            dvo.getBasePdCds().add(contractDvo.getBasePdCd());
            dvo.getPdctPdCds().add(contractDvo.getPdctPdCd());
            dvo.getSdingCombins().add(contractDvo.getSdingCombin());
        }

        String workTypeDtl = "";
        for (int i = 0; i < dvo.getBasePdCds().size(); i++)
            workTypeDtl += (i == 0 ? "" : "|") + dvo.getBasePdCds().get(i) + "," + dvo.getSvBizDclsfCds().get(i);
        dvo.setWorkTypeDtl(workTypeDtl); // WP01120279,1110|WP01110622,3100

        WsncTimeTableCntrDvo contractDvo = contractDvos.get(0);

        dvo.setNewAdrZip(StringUtil.nvl(req.newAdrZip(), contractDvos.get(0).getNewAdrZip()));
        dvo.setContDt(
            StringUtil.decode(
                dvo.getChnlDvCd(), "C", nowDay, /*CC*/ "W", nowDay, /* Wells*/ "P", nowDay,
                /* k-members*/ contractDvo.getCntrDt()
            )
        );
        dvo.setCopnDvCd(contractDvo.getCopnDvCd()); // 법인격구분
        dvo.setSellDscDbCd(contractDvo.getSellDscDbCd()); // 판매할인구분코드
        dvo.setSdingCombin(contractDvo.getSdingCombin()); // lcst09 모종패키지 분리여부
        dvo.setCstSvAsnNo(
            StringUtil.isEmpty(req.cstSvAsnNo()) ? contractDvo.getCstSvAsnNo() : req.cstSvAsnNo()
        );
        dvo.setRpbLocaraCd("");
        /*---------------------------------------------------------------------------*/

        dvo.getPdctPdCds().clear();
        dvo.setPrtnrNo("3".equals(dvo.getSvDvCd()) ? mapper.selectFnSvpdLocaraPrtnr01(dvo) : dvo.getPrtnrNo());

        for (int i = 0; i < dvo.getBasePdCds().size(); i++) {

            /**
            * @param basePdCd
            * @param svDvCd
            */
            WsncTimeTableProductDvo productDvo = mapper.selectProduct(dvo.getSvDvCd(), dvo.getBasePdCds().get(i))
                .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));

            String sidingYn = productDvo.getSidingYn();
            //String spayYn = "3".equals(productDvo.getRglrSppPrcDvCd()) ? "Y" : "N"; // 일시불여부
            //String hcrYn = productDvo.getHcrYn();
            dvo.getPdctPdCds().add(productDvo.getPdctPdCd());

            // 모종인지 확인
            if ("Y".equals(sidingYn)) {

                /**
                 * @param sdingCombin
                 * @param sellDate
                 * @param basePdCd
                 * @param pdctPdCd
                 * @param svDvCd
                 * @param cntrNo
                 */
                List<SidingDays> sidingDaysCollection = dvo.getSidingDays();
                sidingDaysCollection.retainAll(
                    converter.mapSidingDaysDvoToDto(
                        mapper.selectSidingDaysForSpay(
                            dvo.getSdingCombins().get(i),
                            dvo.getSellDate(),
                            dvo.getBasePdCds().get(i),
                            dvo.getPdctPdCds().get(i),
                            dvo.getSvDvCd(),
                            dvo.getCntrNo()
                        )
                    )
                ); // 교집합
                dvo.setSidingDays(sidingDaysCollection); // list2 = sidingDays

                /**
                 * @param prtnrNo
                 * */
                dvo.setMonthSchedules(converter.mapMonthScheduleDvoToDto(mapper.selectMonthSchedule(dvo)));
            }
        }

        /*
         *  diabledays = disableDays
         *
         * @param chnlDvCd                  유입체널(W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어))
         * @param newAdrZip
         * @param prtnrNo
         * @param svDvCd                    서비스구분코드(1:설치, 2: BS, 3: AS, 4: 홈케어)
         * @param rpbLocaraCd               책임지역코드
         * @param svBizDclsfCd              서비스업무세분류코드
         * @param contDt                    계약일자
         * @param vstDowValCd               방문요일구분코드
         * @param copnDvCd                  법인격구분코드(1:개인, 2:법인)
         * @param sellDscDbCd               판매할인구분코드
         * @param basePdCd || basePdCdList
         * */
        dvo.setDisableDays(converter.mapDisableDaysDvoToDto(mapper.selectDisableDays(dvo)));

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
    * @param req svBizDclsfCd: 서비스업무세분류코드
    * @param req vstDvCd: 방문구분코드
    *                   10:방문, 11:매니저, 12:엔지니어, 13:홈마스터, 20:택배
    * @param req cstSvAsnNo: 고객서비스배정번호
    *
    * */
    public FindRes getTimeChoice(FindTimeChoReq req) {

        WsncTimeTableDvo dvo = converter.mapTimeChoReqToDvo(req);

        //다건 svBizDclsfCds 처리
        if (StringUtil.nvl(dvo.getSvBizDclsfCd(), "").contains(",")) {
            dvo.setSvBizDclsfCds(Arrays.asList(dvo.getSvBizDclsfCd().split("\\,")));
            dvo.setSvBizDclsfCd(dvo.getSvBizDclsfCds().get(0));
        }

        //다건 basePdCd 처리
        if (StringUtil.nvl(dvo.getBasePdCd(), "").contains(",")) {
            dvo.setBasePdCds(Arrays.asList(dvo.getBasePdCd().split("\\,")));
            dvo.setBasePdCd(dvo.getBasePdCds().get(0));
            if (dvo.getBasePdCds().size() != dvo.getSvBizDclsfCds().size())
                throw new BizException("다건처리 유입 데이터 오류");

            //다건 workTypeDtl 처리
            String workTypeDtl = "";
            for (int i = 0; i < dvo.getSvBizDclsfCds().size(); i++)
                workTypeDtl += (i == 0 ? "" : "|") + dvo.getBasePdCds().get(i) + "," + dvo.getSvBizDclsfCds().get(i);

            dvo.setWorkTypeDtl(workTypeDtl);
        }

        //단건 basePdCd, svBizDclsfCds, workTypeDtl 처리
        if (StringUtil.isNotEmpty(dvo.getBasePdCd()) && StringUtil.isNotEmpty(dvo.getSvBizDclsfCd())) {
            dvo.setBasePdCds(Arrays.asList(dvo.getBasePdCd()));
            dvo.setSvBizDclsfCds(Arrays.asList(dvo.getSvBizDclsfCd()));
            dvo.setWorkTypeDtl(dvo.getBasePdCd() + "," + dvo.getSvBizDclsfCd());
        }

        dvo.setSellDate(StringUtil.nvl(dvo.getSellDate(), nowDay));
        dvo.setSvDvCd("M".equals(dvo.getChnlDvCd()) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(dvo.getSvDvCd(), ""));

        if (StringUtil.isNotEmpty(dvo.getCstSvAsnNo()))
            dvo.setVstDvCd(mapper.selectVstDvCd(dvo)); // 방문구분코드

        /*---------------------------------------------------------------------------*/
        /*
         * @param cntrNo
         * @param cntrSn
         * @param sellDate
         */
        List<WsncTimeTableCntrDvo> contractDvos = mapper.selectContract(dvo);
        if (contractDvos.size() == 0)
            new BizException("MSG_ALT_NO_PRODUCT_FOUND");

        dvo.getBasePdCds().clear();
        dvo.getPdctPdCds().clear();
        dvo.getSdingCombins().clear();

        for (WsncTimeTableCntrDvo contractDvo : contractDvos) {
            dvo.getBasePdCds().add(contractDvo.getBasePdCd());
            dvo.getPdctPdCds().add(contractDvo.getPdctPdCd());
            dvo.getSdingCombins().add(contractDvo.getSdingCombin());
        }

        String workTypeDtl = "";
        for (int i = 0; i < dvo.getBasePdCds().size(); i++)
            workTypeDtl += (i == 0 ? "" : "|") + dvo.getBasePdCds().get(i) + "," + dvo.getSvBizDclsfCds().get(i);
        dvo.setWorkTypeDtl(workTypeDtl); // WP01120279,1110|WP01110622,3100

        WsncTimeTableCntrDvo contractDvo = contractDvos.get(0);

        dvo.setNewAdrZip(StringUtil.nvl(req.newAdrZip(), contractDvos.get(0).getNewAdrZip()));
        dvo.setContDt(
            StringUtil.decode(
                dvo.getChnlDvCd(), "C", nowDay, /*CC*/ "W", nowDay, /* Wells*/ "P", nowDay,
                /* k-members*/ contractDvo.getCntrDt()
            )
        );
        dvo.setCopnDvCd(contractDvo.getCopnDvCd()); // 법인격구분
        dvo.setSellDscDbCd(contractDvo.getSellDscDbCd()); // 판매할인구분코드
        dvo.setSdingCombin(contractDvo.getSdingCombin()); // lcst09 모종패키지 분리여부
        dvo.setCstSvAsnNo(
            StringUtil.isEmpty(req.cstSvAsnNo()) ? contractDvo.getCstSvAsnNo() : req.cstSvAsnNo()
        );
        dvo.setRpbLocaraCd("");
        /*---------------------------------------------------------------------------*/

        /*
         * @param newAdrZip
         * @param pdctPdCd
         * @param workTypeDtl
         * @param sellDate
         * @param vstDvCd
         **/
        dvo.setPrtnrNo01(mapper.selectFnSvpdLocaraPrtnr01(dvo));
        dvo.setPrtnrNoBS01(mapper.selectFnSvpdLocaraPrtnrBs01(dvo));
        dvo.setPrtnrNoOwr01(mapper.selectFnSvpdLocaraPrtnrOwr01(dvo));

        /*
         * 책임지역 담당자 찾기 ### selectTimeAssign_v2_step1 ###
         *
         * @param inflwChnl
         * @param chnlDvCd      유입체널 W: 웰스, K: KSS, C: CubicCC, P: K-MEMBERS, I || E: 엔지니어, M: 매니저, S: 조회용(일정), B: BS업무(엔지니어)
         * @param sellDate
         * @param workTypeDtl
         * @param pdctPdCd
         * @param vstDvCd
         * @param svDvCd
         * @param newAdrZip
         * @param prtnrNo01
         * @param prtnrNoBS01
         * @param prtnrNoOwr01
         * @param cstSvAsnNo
         * */
        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsic = mapper.selectRpbLocaraPsic(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));
        dvo.setPrtnrNo(rpbLocaraPsic.getIchrPrtnrNo());
        dvo.setLocalGb(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());
        dvo.setOgTpCd(rpbLocaraPsic.getOgTpCd());

        /*
         * 담당자 정보 표시 ### selectTimeAssign_v2_step2 ###
         *
         *
         * @param session.tenantId
         * @param rpbLocaraPsic > sellDate
         * @param rpbLocaraPsic > wrkCnt          전체 작업 건수 합 (FN_SVPD_WRK_CT_TOT_01)
         * @param rpbLocaraPsic > vstDowVal       방문요일
         * @param rpbLocaraPsic > vstPos          방문가능, 해당일 방문불가
         * @param rpbLocaraPsic > rstrCndtUseYn   제약조건사용여부
         * @param rpbLocaraPsic > udsnUseYn       미지정사용여부
         * @param rpbLocaraPsic > satWrkYn        토요일근무여부
         * @param rpbLocaraPsic > dfYn            휴무여부
         * @param rpbLocaraPsic > dowDvCd         요일구분코드
         * @param rpbLocaraPsic > fr2pLgldCd      앞2자리법정동코드
         * @param rpbLocaraPsic > ichrPrtnrNo     담당파트너번호
         * @param rpbLocaraPsic > rpbLocaraCd     책임지역코드
         * @param rpbLocaraPsic > wkGrpCd         작업그룹코드
         */
        WsncTimeTablePsicDvo psic = mapper.selectPsic(rpbLocaraPsic); // left_info

        /*
         * 시간표시 ### selectTimeAssign_v2_step3 ###
         *
         * @param rpbLocaraPsic > sellDate
         * @param rpbLocaraPsic > ichrPrtnrNo 담당파트너번호
         * */
        rpbLocaraPsic.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsic));

        /*
         * @param rpbLocaraPsic > rpbLocaraCd 책임지역코드
         * @param rpbLocaraPsic > wkGrpCd     작업그룹코드
         * @param rpbLocaraPsic > sellDate
         * */
        rpbLocaraPsic.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsic));

        /*
         * @param rpbLocaraPsic > rpbLocaraCd 책임지역코드
         * @param rpbLocaraPsic > wkGrpCd     작업그룹코드
         * @param rpbLocaraPsic > sellDate
         * */
        rpbLocaraPsic.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsic));

        /*
         *
         * list1
         *
         * @param selectRpbLocaraPsic > empTWrkCnt
         * @param selectRpbLocaraPsic > degWrkCnt
         * @param selectRpbLocaraPsic > wrkCnt
         * @param selectRpbLocaraPsic > sellDate
         * @param selectRpbLocaraPsic > ichrPrtnrNo
         * @param selectRpbLocaraPsic > wkHhCd
         * */
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
