package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncBsPeriodChartConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartReqDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartResDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsPeriodChartMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsncBsPeriodChartService {
    private final WsncBsPeriodChartMapper mapper;

    private final WsncBsPeriodChartConverter converter;

    /*
     * 정기 BS 주기표 생성
     * - 멤버십 (W-SV-S-0072) (BS03)
     * - 정기 B/S주기표를 생성하는 작업 (W-SV-S-0071) (BS02)
     */
    @Transactional
    public int processBsPeriodChartBs03(WsncBsPeriodChartDto.SearchReq dto, boolean isMembership) throws Exception {
        WsncBsPeriodChartResDvo baseInfoRes = mapper.selectPeriodChartBaseInfo(dto);

        //AC201_BS_GB1 != '00' 일 경우 아무것도 처리 하지 않는다.
        if (baseInfoRes == null || !"00".equals(baseInfoRes.getBfsvcSppStpRsonCd())) {
            return 0;
        }

        /*******************************************************************************************************
         * 변수 선언부
         *******************************************************************************************************/
        int chekInstMths = 0; //설치차월
        int wrkTypDtlCnt; //1회성 작업 체크용 변수
        int chekCyclMths; //방문기준 차월수 ::: VST_NMN_N
        String chekVstDt; //방문예정일자
        String vVs104CfrmDt; //고객이 요청한 방문일자
        String newWrkTypDtl; //작업유형상세
        String newPartCd; //주기표에 최종적으로 들어갈 자재코드(계절별맞춤형 필터 도입때문에)
        String vVs104CstmFltr; //고객이 마지막으로 신청한 필터가 있는지 체크
        String newVstGb; //방문구분 10 방문, 11 방문 매니저, 12 방문 엔지니어, 13 방문 홈케어, 20 택배
        String vVs107WrkDt; //주기표상 마지막 방문일자
        String priorityVstDt = ""; // 가구화 결과 ::: 방문예정일자(YYYYMMDD)
        int priorityVstDiff = 0; //가구화 결과 ::: 방문예정일자와 가구화 결과 방문예정일자와의 차월 수

        List<WsncBsPeriodChartResDvo> chart06ResList = mapper.selectBsPeriodChartBs03_06(baseInfoRes);
        WsncBsPeriodChartReqDvo processParam = converter.mapBaseInfoResToPeriodChartDvo(baseInfoRes);

        //주기표 생성 데이터가 없을 경우, 오류 로그 처리 후 종료.
        if (CollectionUtils.isEmpty(chart06ResList)) {
            processParam.setCrtErrCn("'NO DATA NOT FOUND!'");
            processParam.setPgrmId("BS03");
            mapper.insertBsPeriodChartError(processParam);
            return 0;
        }

        chekCyclMths = mapper.selectBsPeriodChartBs03_04(processParam); //방문기준 차월수
        vVs107WrkDt = mapper.selectBsPeriodChartBs03_05(processParam); //주기표상 마지막 방문일자

        processParam.setChekCyclMths(chekCyclMths);


        /*******************************************************************************************************
         * 가구화 로직 (방문일자 및 차월 계산)
         *******************************************************************************************************/
        priorityVstDt = mapper.selectPriorityVstDt(dto);
        if (StringUtils.isNotEmpty(priorityVstDt)) {
            //가구화에 따른 방문월 padding 계산
            priorityVstDiff = mapper.selectPriorityVstDiff(priorityVstDt, chekCyclMths);
        }

        //AS-IS ::: WORK_X Loop(chart06Res)
        for (WsncBsPeriodChartResDvo chart06Res : chart06ResList) {
            //SELL_TP_CD = 1 ::: 일시불, 2 렌탈
            if (("1".equals(baseInfoRes.getSellTpCd()) || ("2".equals(baseInfoRes.getSellTpCd()))) && chart06Res.getVstNmnN() == 0) {
                continue;
            }

            //설치차월 계산
            //SELL_TP_CD = 1 ::: 일시불, 2 렌탈
            if(("1".equals(baseInfoRes.getSellTpCd())) || ("2".equals(baseInfoRes.getSellTpCd()))){
                chekInstMths = chart06Res.getVstNmnN();     // 설치차월 수 세팅 - js
//                chekInstMths = chekInstMths + chart06Res.getVstNmnN();
            }
            //SELL_TP_CD = 3 ::: 멤버십 (AS-IS는 2)
            else if ("3".equals(baseInfoRes.getSellTpCd())) {
                //TB_PDBS_RGBS_WK_BASE_DTL.TOT_STPL_MCN 로 대체
                chekInstMths = chart06Res.getVstNmnN();     // 설치차월 수 세팅 - js
//                chekInstMths = baseInfoRes.getChekInstMths();
//                chekInstMths = chekInstMths + chart06Res.getVstNmnN();
            }

            processParam.setDtlSn(chart06Res.getDtlSn());       // js - 다중 for문 제어를 위해 추가

            processParam.setChekCyclMths(chart06Res.getVstNmnN());     // 방문차월 수 세팅 - js
            processParam.setChekInstMths(chekInstMths);         // 설치차월 수 세팅 - js

            List<WsncBsPeriodChartResDvo> chart07ResList = mapper.selectBsPeriodChartBs03_07(processParam);
            //AS-IS ::: C1 Loop(chart07Res)
            for (WsncBsPeriodChartResDvo chart07Res : chart07ResList) {

                //방문예정일자 계산
                //멤버십 가입일자가 없는 경우 (렌탈인경우인지 확인 필요) (멤버십 ::: "3".equals(baseInfoRes.sellTpCd()))
                if (StringUtils.isEmpty(baseInfoRes.getCntrPdStrtdt())
                    || "01".equals(baseInfoRes.getCntrPdStrtdt().substring(6, 8))) {
                    //기존 주기표에 데이터가 없는 경우 - 첫 계약인 경우
                    if (StringUtils.isEmpty(vVs107WrkDt)) {
                        chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths);
                        chekVstDt = mapper.selectBsPeriodChartBs03_10(chekVstDt); //GET_HLDY_NEXTDAY
                    } else {
                        chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                            + vVs107WrkDt.substring(6, 8);
                    }
                } else {
                    //멤버십인 경우, 멤버십 가입일자를 가져와서 그대로 사용 (휴일체크 할 필요 없음) (cherro ::: 멤버십 조건 넣어야 할지 모르겠음.)
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                        + baseInfoRes.getCntrPdStrtdt().substring(6, 8);
                }
                processParam.setChekVstDt(chekVstDt);
                vVs104CfrmDt = mapper.selectBsPeriodChartBs03_11(processParam); //20.02.27 이영진 - 고객이 약속한 방문 예정일자가 있는지 확인
                //고객 요청 방문일자가 있다면 그걸로 세팅, 아님 설치 차월 기준으로 계산한 일자 세팅
                //위의 차기 방문일자가 있는 경우만 일자 변경
                if (StringUtils.isNotEmpty(vVs104CfrmDt)) {
                    chekVstDt = vVs104CfrmDt;
                }

                /*******************************************************************************************************
                 * 가구화 로직 (로직 첫 부분에서 계산한 차월 반영)
                *******************************************************************************************************/
                if (StringUtils.isNotEmpty(priorityVstDt) && priorityVstDiff != 0) {
                    chekVstDt = DateUtil.addMonths(chekVstDt, priorityVstDiff).substring(0, 6)
                        + priorityVstDt.substring(6, 8);
                }

                //월에 마지막일자보다 크다면 해당월 마지막 일자라 세팅
                if (DateUtil.getLastDateOfMonth(chekVstDt).compareTo(chekVstDt) < 0) {
                    chekVstDt = DateUtil.getLastDateOfMonth(chekVstDt);
                }
                processParam.setChekVstDt(chekVstDt);

                newWrkTypDtl = chart07Res.getWrkTypDtl();
                processParam.setNewWrkTypDtl(newWrkTypDtl);

                newVstGb = chart07Res.getVstGb();
                processParam.setNewVstGb(newVstGb);

                //cherro ::: TO-BE Table이 없음.(TB_PDBS_ARCLE_CHOFLT_BASE_DTL -> TB_PDBS_LVLH_CSTMW_FILT_BASE)
                processParam.setVstGb(chart07Res.getVstGb());
                processParam.setPartCd(chart07Res.getPartCd());
                newPartCd = mapper.selectBsPeriodChartBs03_20(processParam);

                /*20.02.27 이영진 - 고객이 마지막으로 신청한 필터가 있는지 체크*/
                /*21.03.04 확인 결과 현재 다음 방문시 필터를 받을때 방문이냐 택배냐 구분해서 맞는 필터를 받음으로 아래 로직이 필요 없음. 상황봐서 제거 필요함*/
                //cherro ::: TO-BE Table이 없음.(TB_PDBS_ARCLE_CHOFLT_BASE_DTL)
                vVs104CstmFltr = mapper.selectBsPeriodChartBs03_21(processParam);
                //고객 요청 필터가 있으면 맞춤형필터보다 고객 요청 필터를 우선
                if (StringUtils.isNotEmpty(vVs104CstmFltr)) {
                    newPartCd = vVs104CstmFltr;
                }
                processParam.setNewPartCd(newPartCd);

                processParam.setChngStg(chart07Res.getChngStg());
                processParam.setItemKnd(chart07Res.getItemKnd());
                processParam.setQty(chart07Res.getQty());

                //주기표 생성
                processParam.setPgrmId("BS03");
                mapper.insertBsPeriodChart(processParam);
            }
        }
        return 1;
    }

    /*
     * 정기 BS 주기표 생성
     * - 삼성전자 에어컨 (W-SV-S-0070)
     */
    @Transactional
    public int processBsPeriodChartBs04(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        WsncBsPeriodChartResDvo baseInfoRes = mapper.selectPeriodChartBaseInfo(dto);

        //AC201_BS_GB1 != '00' 일 경우 아무것도 처리 하지 않는다.
        if (baseInfoRes == null || !"00".equals(baseInfoRes.getBfsvcSppStpRsonCd())) {
            return 0;
        }

        /*******************************************************************************************************
         * 변수 선언부
         *******************************************************************************************************/
        int chekInstMths = 0; //설치차월
        String chekVstDt; //방문예정일자
        String newWrkTypDtl; //작업유형상세
        String newVstGb; //방문구분 10 방문, 11 방문 매니저, 12 방문 엔지니어, 13 방문 홈케어, 20 택배
        String chekVstGb20 = "N"; //강제택배처리건(신안군보건소)
        String vVs107WrkDt; //주기표상 마지막 방문일자

        List<WsncBsPeriodChartResDvo> chart06ResList = mapper.selectBsPeriodChartBs03_06(baseInfoRes);
        WsncBsPeriodChartReqDvo processParam = converter.mapBaseInfoResToPeriodChartDvo(baseInfoRes);

        //주기표 생성 데이터가 없을 경우, 오류 로그 처리 후 종료.
        if (CollectionUtils.isEmpty(chart06ResList)) {
            processParam.setCrtErrCn("'NO DATA NOT FOUND!'");
            processParam.setPgrmId("BS04");
            mapper.insertBsPeriodChartError(processParam);
            return 0;
        }

        vVs107WrkDt = mapper.selectBsPeriodChartBs03_05(processParam); //주기표상 마지막 방문일자
        processParam.setChekVstGb20(chekVstGb20);

        //AS-IS ::: WORK_X Loop(chart06Res)
        for (WsncBsPeriodChartResDvo chart06Res : chart06ResList) {

            //설치차월 계산 - 기준일자 와 설치일자 사이의 차월수
//            chekInstMths = baseInfoRes.getChekInstMths();
//            chekInstMths = chekInstMths + chart06Res.getVstNmnN();
            chekInstMths = chart06Res.getVstNmnN();
            processParam.setChekInstMths(chekInstMths);

            //방문예정일자 계산
            String todayDt = DateUtil.getNowDayString(); //YYYYMMDD
            if ("01".equals(todayDt.substring(6, 8))) {
                if (StringUtils.isEmpty(vVs107WrkDt)) {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths);
                    chekVstDt = mapper.selectBsPeriodChartBs03_10(chekVstDt); //GET_HLDY_NEXTDAY
                } else {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                        + vVs107WrkDt.substring(6, 8);
                }
            } else {
                chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                    + todayDt.substring(6, 8);
            }

            //월에 마지막일자보다 크다면 해당월 마지막 일자라 세팅
            if (DateUtil.getLastDateOfMonth(chekVstDt).compareTo(chekVstDt) < 0) {
                chekVstDt = DateUtil.getLastDateOfMonth(chekVstDt);
            }
            processParam.setChekVstDt(chekVstDt);
            processParam.setChekCyclMths(chart06Res.getVstNmnN());     // 방문차월 수 세팅 - js
            processParam.setDtlSn(chart06Res.getDtlSn());       // js - 다중 for문 제어를 위해 추가

            List<WsncBsPeriodChartResDvo> chart07ResList = mapper.selectBsPeriodChartBs04_07(processParam);

            //AS-IS ::: C1 Loop(chart07Res)
            for (WsncBsPeriodChartResDvo chart07Res : chart07ResList) {

                newWrkTypDtl = chart07Res.getWrkTypDtl();
                newVstGb = chart07Res.getVstGb();

                processParam.setNewWrkTypDtl(newWrkTypDtl);
                processParam.setNewVstGb(newVstGb);
                processParam.setChekCyclMths(chart07Res.getVstMths()); //cherro ::: 이걸로 가구화 로직 수행해야 하나...?
                processParam.setNewPartCd(chart07Res.getPartCd());
                processParam.setChngStg(chart07Res.getChngStg());
                processParam.setItemKnd(chart07Res.getItemKnd());
                processParam.setQty(chart07Res.getQty());

                //맞춤형제품여부가 Y인 경우
                if("Y".equals(chart07Res.getCstmwPdctYn())){
                    chart07Res.setPartCd(mapper.selectBsPeriodChartBs03_20(processParam));
                }
                processParam.setNewPartCd(chart07Res.getPartCd());

                //주기표 생성
                processParam.setPgrmId("BS04");
                mapper.insertBsPeriodChart(processParam);
            }
        }
        return 1;
    }

    /*
     * 정기 BS 주기표 생성
     * - 홈카페 캡슐 B/S주기표를 생성 (W-SV-S-0068)
     */
    @Transactional
    public int processBsPeriodChartBs01(WsncBsPeriodChartDto.SearchReq dto) throws Exception {

        WsncBsPeriodChartResDvo baseInfoRes = mapper.selectPeriodChartBaseInfo(dto);

        //AC201_BS_GB1 != '00' 일 경우 아무것도 처리 하지 않는다.
        if (baseInfoRes == null || !"00".equals(baseInfoRes.getBfsvcSppStpRsonCd())) {
            return 0;
        }

        /*******************************************************************************************************
         * 변수 선언부
         *******************************************************************************************************/
        int chekInstMths = 0; //설치차월
        int chekCyclMths; //방문기준 차월수 ::: VST_NMN_N
        String chekVstDt; //방문예정일자
        String vVs104CfrmDt; //고객이 요청한 방문일자
        String newWrkTypDtl; //작업유형상세
        String newVstGb; //방문구분 10 방문, 11 방문 매니저, 12 방문 엔지니어, 13 방문 홈케어, 20 택배
        String vVs107WrkDt; //주기표상 마지막 방문일자
        int chekVstMths = 0;
        String newGdsCd; //패키지 변경 요청을 적용한 패키지
        String vVs107CnclDt; //패키지 중지 요청 일자

        List<WsncBsPeriodChartResDvo> chart06ResList = mapper.selectBsPeriodChartBs03_06(baseInfoRes);
        WsncBsPeriodChartReqDvo processParam = converter.mapBaseInfoResToPeriodChartDvo(baseInfoRes);

        //주기표 생성 데이터가 없을 경우, 오류 로그 처리 후 종료.
        if (CollectionUtils.isEmpty(chart06ResList)) {
            processParam.setCrtErrCn("'NO DATA NOT FOUND!'");
            processParam.setPgrmId("BS01");
            mapper.insertBsPeriodChartError(processParam);
            return 0;
        }

        chekCyclMths = mapper.selectBsPeriodChartBs03_04(processParam); //방문기준 차월수
        vVs107WrkDt = mapper.selectBsPeriodChartBs03_05(processParam); //주기표상 마지막 방문일자

        //AS-IS ::: WORK_X Loop(chart06Res)
        for (WsncBsPeriodChartResDvo chart06Res : chart06ResList) {

            if("66".equals(baseInfoRes.getPdctPdCd().substring(0, 2))){
                continue;
            }

            chekInstMths = chart06Res.getVstNmnN();

            //방문예정일자 계산
            //멤버십 가입일자가 없는 경우 (렌탈인경우인지 확인 필요) (멤버십 ::: "3".equals(baseInfoRes.sellTpCd()))
            if (StringUtils.isEmpty(baseInfoRes.getCntrPdStrtdt())
                || "01".equals(baseInfoRes.getCntrPdStrtdt().substring(6, 8))) {
                //기존 주기표에 데이터가 없는 경우 - 첫 계약인 경우
                if (StringUtils.isEmpty(vVs107WrkDt) && StringUtils.isEmpty(baseInfoRes.getStvlvChngDt())) {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths);
                    chekVstDt = mapper.selectBsPeriodChartBs03_10(chekVstDt); //GET_HLDY_NEXTDAY
                } else {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                        + vVs107WrkDt.substring(6, 8);
                }
            } else {
                //멤버십인 경우, 멤버십 가입일자를 가져와서 그대로 사용 (휴일체크 할 필요 없음) (cherro ::: 멤버십 조건 넣어야 할지 모르겠음.)
                chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                    + baseInfoRes.getCntrPdStrtdt().substring(6, 8);
            }

            //월에 마지막일자보다 크다면 해당월 마지막 일자라 세팅
            if (DateUtil.getLastDateOfMonth(chekVstDt).compareTo(chekVstDt) < 0) {
                chekVstDt = DateUtil.getLastDateOfMonth(chekVstDt);
            }
            processParam.setChekVstDt(chekVstDt);

            newGdsCd = mapper.selectBsPeriodChartBs01_22(processParam);
            vVs107CnclDt = mapper.selectBsPeriodChartBs01_23(processParam);
            processParam.setNewGdsCd(newGdsCd);
            processParam.setChekCyclMths(chart06Res.getVstNmnN());     // 방문차월 수 세팅 - js
            processParam.setDtlSn(chart06Res.getDtlSn());       // js - 다중 for문 제어를 위해 추가

            List<WsncBsPeriodChartResDvo> chart07ResList = mapper.selectBsPeriodChartBs01_07(processParam);
            //AS-IS ::: C1 Loop(chart07Res)
            for (WsncBsPeriodChartResDvo chart07Res : chart07ResList) {

                newWrkTypDtl = chart07Res.getWrkTypDtl();
                newVstGb = chart07Res.getVstGb();

                //고객 요청 방문일자가 있다면 그걸로 세팅, 아님 설치 차월 기준으로 계산한 일자 세팅
                vVs104CfrmDt = mapper.selectBsPeriodChartBs03_11(processParam);
                if(StringUtils.isNotEmpty(vVs104CfrmDt)){
                    chekVstDt = vVs104CfrmDt;
                }
                processParam.setChekVstDt(chekVstDt);
                processParam.setNewWrkTypDtl(newWrkTypDtl);
                processParam.setNewVstGb(newVstGb);
                processParam.setChekCyclMths(chart07Res.getVstMths());
                processParam.setChekInstMths(chart07Res.getVstMths());
                processParam.setNewPartCd(chart07Res.getPartCd());
                processParam.setChngStg(chart07Res.getChngStg());
                processParam.setItemKnd(chart07Res.getItemKnd());
                processParam.setQty(chart07Res.getQty());
                processParam.setVVs107CnclDt(vVs107CnclDt);

                //주기표 생성
                processParam.setPgrmId("BS01");
                mapper.insertBsPeriodChartBs01(processParam);
            }
        }
        return 1;
    }

    /*
     * 정기 BS 주기표 생성
     * - 웰스팜 모종 BS주기표를 생성 (W-SV-S-0069)
     */
    @Transactional
    public int processBsPeriodChartBs05(WsncBsPeriodChartDto.SearchReq dto) throws Exception {

        WsncBsPeriodChartResDvo baseInfoRes = mapper.selectPeriodChartBaseInfo(dto);

        //AC201_BS_GB1 != '00' 일 경우 아무것도 처리 하지 않는다.
        if (baseInfoRes == null || !"00".equals(baseInfoRes.getBfsvcSppStpRsonCd())) {
            return 0;
        }

        /*******************************************************************************************************
         * 변수 선언부
         *******************************************************************************************************/
        int chekInstMths = 0; //설치차월
        int wrkTypDtlCnt; //1회성 작업 체크용 변수
        int chekCyclMths; //방문기준 차월수 ::: VST_NMN_N
        String chekVstDt; //방문예정일자
        String vVs104CfrmDt; //고객이 요청한 방문일자
        String newWrkTypDtl; //작업유형상세
//        String newPartCd; //주기표에 최종적으로 들어갈 자재코드(계절별맞춤형 필터 도입때문에)
        String newVstGb; //방문구분 10 방문, 11 방문 매니저, 12 방문 엔지니어, 13 방문 홈케어, 20 택배
        String vVs107WrkDt; //주기표상 마지막 방문일자
        String newGdsCd; //패키지 변경 요청을 적용한 패키지
        String vVs107CnclDt; //패키지 중지 요청 일자
        String newPartList; //모종 자유선택 패키지 구성품

        List<WsncBsPeriodChartResDvo> chart06ResList = mapper.selectBsPeriodChartBs03_06(baseInfoRes);
        WsncBsPeriodChartReqDvo processParam = converter.mapBaseInfoResToPeriodChartDvo(baseInfoRes);

        //주기표 생성 데이터가 없을 경우, 오류 로그 처리 후 종료.
        if (CollectionUtils.isEmpty(chart06ResList)) {
            processParam.setCrtErrCn("'NO DATA NOT FOUND!'");
            processParam.setPgrmId("BS05");
            mapper.insertBsPeriodChartError(processParam);
            return 0;
        }

        chekCyclMths = mapper.selectBsPeriodChartBs03_04(processParam); //방문기준 차월수
        vVs107WrkDt = mapper.selectBsPeriodChartBs03_05(processParam); //주기표상 마지막 방문일자


        //AS-IS ::: WORK_X Loop(chart06Res)
        for (WsncBsPeriodChartResDvo chart06Res : chart06ResList) {

            //cherro ::: vstNmnN (방문차월) 값은 0이 없을 것. 혹시나 해서 로직 넣어둠.
            //시작차월 체크(일반:1차월 멤버십:0차월
            if (("1".equals(baseInfoRes.getSellTpCd()) || ("2".equals(baseInfoRes.getSellTpCd()))) && chart06Res.getVstNmnN() == 0) {
                continue;
            }

            chekInstMths = chart06Res.getVstNmnN();

            //방문예정일자 계산
            //멤버십 가입일자가 없는 경우 (렌탈인경우인지 확인 필요) (멤버십 ::: "3".equals(baseInfoRes.sellTpCd()))
            if (StringUtils.isEmpty(baseInfoRes.getCntrPdStrtdt())
                || "01".equals(baseInfoRes.getCntrPdStrtdt().substring(6, 8))) {
                //기존 주기표에 데이터가 없는 경우 - 첫 계약인 경우
                if (StringUtils.isEmpty(vVs107WrkDt)) {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths);
                    chekVstDt = mapper.selectBsPeriodChartBs03_10(chekVstDt); //GET_HLDY_NEXTDAY
                } else {
                    chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                        + vVs107WrkDt.substring(6, 8);
                }
            } else {
                //멤버십인 경우, 멤버십 가입일자를 가져와서 그대로 사용 (휴일체크 할 필요 없음) (cherro ::: 멤버십 조건 넣어야 할지 모르겠음.)
                chekVstDt = DateUtil.addMonths(baseInfoRes.getIstDt(), chekInstMths).substring(0, 6)
                    + baseInfoRes.getCntrPdStrtdt().substring(6, 8);
            }
            processParam.setChekVstDt(chekVstDt);

            //월에 마지막일자보다 크다면 해당월 마지막 일자라 세팅
            if (DateUtil.getLastDateOfMonth(chekVstDt).compareTo(chekVstDt) < 0) {
                chekVstDt = DateUtil.getLastDateOfMonth(chekVstDt);
            }
            processParam.setChekVstDt(chekVstDt);

            newGdsCd = mapper.selectBsPeriodChartBs05_22(processParam);
            newPartList = mapper.selectBsPeriodChartBs05_25(processParam);
            vVs107CnclDt = mapper.selectBsPeriodChartBs05_23(processParam);
            processParam.setNewGdsCd(newGdsCd);
            processParam.setNewPartList(newPartList);
            processParam.setVVs107CnclDt(vVs107CnclDt);
            processParam.setChekCyclMths(chart06Res.getVstNmnN());     // 방문차월 수 세팅 - js
            processParam.setDtlSn(chart06Res.getDtlSn());       // js - 다중 for문 제어를 위해 추가

            List<WsncBsPeriodChartResDvo> chart07ResList = mapper.selectBsPeriodChartBs05_07(processParam);
            //AS-IS ::: C1 Loop(chart07Res)
            for (WsncBsPeriodChartResDvo chart07Res : chart07ResList) {

                newWrkTypDtl = chart07Res.getWrkTypDtl();
                processParam.setNewWrkTypDtl(newWrkTypDtl);

                if("10".equals(chart07Res.getVstGb())){
                    newVstGb = "20";
                } else {
                    newVstGb = chart07Res.getVstGb();
                }

                //비데 1회성 작업 체크 2172 살균전극모듈
//                if ("2172".equals(newWrkTypDtl)) {
//                    wrkTypDtlCnt = mapper.selectBsPeriodChartBs03_12(processParam);
//                    //3M4 일 경우 18, 36 두번 교체 / 3M4 가 아닌 경우 18, 36 중 1번 교체 (P_CYCL_TYP 조건은 삭제되었으므로 로직 다시 생각해야 함) (BS03과 로직은 거의 비슷하지만, 등호가 차이남.)
//                    if (wrkTypDtlCnt > 2 || wrkTypDtlCnt > 1) {
//                        continue;
//                    }
//                }

                /*안마의자 1회성 작업 체크 2163 주요패드교체 */
//                if ("2163".equals(newWrkTypDtl)) {
//                    wrkTypDtlCnt = mapper.selectBsPeriodChartBs03_12(processParam);
//                    if (wrkTypDtlCnt > 1) {
//                        continue;
//                    }
//                }

                processParam.setPartCd(chart07Res.getPartCd());
//                newPartCd = mapper.selectBsPeriodChartBs05_24(processParam);
                processParam.setNewPartCd(chart07Res.getPartCd());

                //맞춤형제품상품여부 Table이 데이터 확정이 되면 아래 로직으로 구현(Bs05_24 쿼리도 좀 수정)
//                if("Y".equals(chart07Res.get맞춤형제품상품여부())){
//                    newPartCd = mapper.selectBsPeriodChartBs05_24(processParam);
//                    processParam.setNewPartCd(newPartCd);
//                }

                vVs104CfrmDt = mapper.selectBsPeriodChartBs03_11(processParam); //21.01.14 이영진 - 고객이 약속한 방문 예정일자가 있는지 확인

                //고객 요청 방문일자가 있다면 그걸로 세팅, 아님 설치 차월 기준으로 계산한 일자 세팅
                //위의 차기 방문일자가 있는 경우만 일자 변경
                if (StringUtils.isNotEmpty(vVs104CfrmDt)) {
                    chekVstDt = vVs104CfrmDt;
                }

                processParam.setChekVstDt(chekVstDt);
                processParam.setNewVstGb(newVstGb);
                processParam.setChekCyclMths(chart07Res.getVstMths());
                processParam.setChekInstMths(chart07Res.getVstMths());
                processParam.setChngStg(chart07Res.getChngStg());
                processParam.setItemKnd(chart07Res.getItemKnd());
                processParam.setQty(chart07Res.getQty());

                //주기표 생성
                processParam.setPgrmId("BS05");
                mapper.insertBsPeriodChartBs01(processParam); //BS01 주기표 생성 쿼리 재사용
            }
        }
        return 1;
    }

    /*
     * W-SV-S-0051
     * 모종 일시불 구매 고객 정보 및 방문 스케쥴 인서트업데이트
     *
     * @TODO
     *   해당 로직은 현재(2023.04.13) 전체 로직에 대한 로직 협의중이며, Query는 모두 AS-IS Query를 TO-BE로 변환하지 않은 상태이다.
     *   로직 협의가 완료되면 Query를 TO-BE로 변환하고 Parameter로 처리할 조건부들을 처리해야 한다.
     */
    @Transactional
    public int processSpMkPlantSchedule(WsncBsPeriodChartDto.SearchReq dto) throws Exception {

        List<WsncBsPeriodChartResDvo> targetList = mapper.selectSpMkPlantSchedule(dto);

        //전체 Transaction Rollback 처리를 위한 throw exception
        if (CollectionUtils.isEmpty(targetList)) {
            log.error("[WsncBsPeriodChartService.processSpMkPlantSchedule] NO DATA NOT FOUND");
            throw new BizException("NO DATA NOT FOUND");
        }

        //AS-IS ::: C1 Loop
        for(WsncBsPeriodChartResDvo target : targetList){

            mapper.saveSpMkPlantSchedule01(target);

            //C1.P_AC201_CSMR_SER <= C1.AC201_CSMR_SER
            if("C1.P_AC201_CSMR_SER".compareTo("C1.AC201_CSMR_SER") <= 0){
                /*2. 모종의 스케쥴을 생성한다.*/
                /* 19.03.06 이영진, 일시불 12개월 구매건 적용,  -1을 하는 이유는 마지막 방문 생성 안되게 하려고 모종은 시작시점에 출고가 되는 형태이기 때문에 */
                mapper.updateSpMkPlantSchedule02(target);

                /*3. 디바이스의 스케쥴을 생성한다. */
                mapper.updateSpMkPlantSchedule03(target);

                //SUBSTR(C1.REQ_VST_DT,1,6) = TO_CHAR(SYSDATE, 'YYYYMM')

                if("C1.REQ_VST_DT".substring(0, 6).equals(DateUtil.getNowDayString().substring(0, 6))){
                    mapper.updateSpMkPlantSchedule04(target);
                    mapper.updateSpMkPlantSchedule05(target);
                    mapper.updateSpMkPlantSchedule06(target);
                    mapper.updateSpMkPlantSchedule07(target);
                }
            } else {
                /*C1.P_AC201_CSMR_SER > C1.AC201_CSMR_SER  즉 kiwi 마스터가 순번이 크다면 모종마스터가 등록됐다고 취소된걸로 인지*/
                /*1. 모종의 스케쥴을 삭제 한다.*/
                mapper.deleteSpMkPlantSchedule08(target);

                /*2. 디바이스의 스케쥴을 삭제한다. */
                mapper.deleteSpMkPlantSchedule09(target);

                /*3.모종의 배정을 삭제한다. */
                mapper.updateSpMkPlantSchedule10(target);

                /*4.디바이스의 배정을 삭제한다. */
                mapper.updateSpMkPlantSchedule11(target);

            }

        }
        return 1;
    }

    /*
     * W-SV-S-0052
     * 홍삼 캡슐 정기구매 고객 스케쥴 인서트업데이트
     *
     * @TODO
     *   해당 로직은 현재(2023.04.13) 전체 로직에 대한 로직 협의중이며, Query는 모두 AS-IS Query를 TO-BE로 변환하지 않은 상태이다.
     *   로직 협의가 완료되면 Query를 TO-BE로 변환하고 Parameter로 처리할 조건부들을 처리해야 한다.
     */
    @Transactional
    public int processSpMkRedginsengSchedule(WsncBsPeriodChartDto.SearchReq dto) throws Exception {

        List<WsncBsPeriodChartResDvo> targetList = mapper.selectSpMkRedginsengSchedule(dto);

        //전체 Transaction Rollback 처리를 위한 throw exception
        if (CollectionUtils.isEmpty(targetList)) {
            log.error("[WsncBsPeriodChartService.processSpMkPlantSchedule] NO DATA NOT FOUND");
            throw new BizException("NO DATA NOT FOUND");
        }

        //AS-IS ::: C1 Loop
        for(WsncBsPeriodChartResDvo target : targetList){
            mapper.saveSpMkRedginsengSchedule01(target);

            //C1.AC201_CANC_DT IS NULL
            /*당월 BS 배정*/
            if(StringUtils.isEmpty("C1.AC201_CANC_DT")){

                /* 주기표 강제 생성 */
                /*무결성 제약 조건 오류 방지*/
                mapper.deleteSpMkRedginsengSchedule02(target);

                /*2. 스케쥴을 생성한다.*/
                mapper.updateSpMkRedginsengSchedule03(target);

                /*3. 배정을 삭제한다. */
                mapper.updateSpMkRedginsengSchedule04(target);

                /*사전방문 BS 생성*/
                mapper.updateSpMkRedginsengSchedule05(target);

                /*BS 사전생성을 위해서 강제 입력한 설치일자 삭제 */
                mapper.updateSpMkRedginsengSchedule06(target);
            } else {
                /*취소일자가 있다면  취소된걸로 인지*/
                /*취소일자 업데이트*/
                mapper.updateSpMkRedginsengSchedule07(target);

                /*1. 스케쥴을 삭제 한다.*/
                mapper.deleteSpMkRedginsengSchedule08(target);

                /*3. 배정을 삭제한다. */
                mapper.updateSpMkRedginsengSchedule09(target);
            }
        }
        return 1;
    }

}
