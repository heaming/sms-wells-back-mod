package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbMultipleTaskOrderConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultipleTaskOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbMultipleTaskOrderMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbMultipleTaskOrderService {
    private WsnbMultipleTaskOrderMapper mapper;
    private WsnbMultipleTaskOrderConverter converter;

    /**
     * W-SV-S-0012 다건 작업오더, 정보변경 처리
     * TODO : 테이블 정의 완료 시 정리
     * @param dto : [{  asIstOjNo : AS설치대상번호, histChDtm : 이력변경일시, cntrNo : 계약번호, cntrSn : 계약일련번호,
     *            cntrCst_no : 계약고객번호, inChnlDvCd : 입력채널구분코드, svBizHclsfCd : 서비스업무대분류코드,
     *            svBizDclsfCd : 서비스업무세분류코드, rcpSvBizDclsfCd : 접수서비스업무세분류코드, rcpdt : 접수일자,
     *            rcpHh : 접수시간, urgtYn : 긴급여부, vstRqdt : 방문요청일자, vstAkHh : 방문요청시간,
     *            cnslTpHclsfCd : 상담유형대분류코드, cnslTpMclsfCd : 상담유형중분류코드, cnslTpLclsfCd : 상담유형소분류코드,
     *            asRefriDvCd : AS유무상구분코드, bfsvcRefriDvCd : BS유무상구분코드, smsFwYn : SMS발송여부, dpDvCd : 입금구분코드,
     *            svEtAmt : 서비스예상금액, svCnrOgId : 서비스센터조직ID, mrtStatCd : 자료상태코드, pdCd : 상품코드,
     *            pdGdCd : 상품등급코드, pdUswyCd : 상품용도코드, cstSvAsnNo : 고객서비스배정번호, cnslMoCn : 상담메모내용,
     *            cnslDtlpTpCd : 상담세부유형코드, asAkDvCd1 : AS요청구분코드1, asAkDvCd2 : AS요청구분코드2,
     *            istllKnm : 설치자한글명, adrDvCd : 주소구분코드, istAdr : 설치주소 }]
     */
    public int saveMultipleTaskOrders(SaveReq dto) throws Exception {
        int processCount = 0;
        /* V_객제역할 */
        WsnbMultipleTaskOrderDvo dvo = converter.mapSaveReqToWsnbMultipleTaskOrderDvo(dto);

        processCount += this.saveMultipleTaskOrders(dvo);
        return processCount;
    }

    public int saveMultipleTaskOrders(WsnbMultipleTaskOrderDvo dvo) throws Exception {
        int processCount = 0;
        /* V_객제역할 */

        int instDtCnt = 0; // V_AC201_INST_DT_CNT
        int reqdDtCnt = 0; // V_AC201_REMV_DT_CNT
        int asIstAsnIzCnt = 0; // V_AC221_CNT
        int stopDtcnt = 0; // V_AC201_STOP_DT_CNT
        int useMonths = 0; // V_USE_MONTHS
        int totChangeCnt = 0; // V_TOTAL_CHANGE_CNT
        int rangeChangeCnt = 0; // V_RANGE_CHANGE_CNT
        int rangeChangeBsCnt = 0; // V_RANGE_CHANGE_BS_CNT
        int itemCnt = 0; // V_ITEM_CNT
        int asIstOjIzCnt = 0; // V_AC211_CNT
        // V_RANGE_CNT , V_RANGE_CNT2 , V_AC201_CSMR_B_CNT

        /* KSS 접수 건이면 작업상세코드 재확인 */
        if (List.of("3", "9").contains(dvo.getInChnlDvCd()) && List.of("1", "2").contains(dvo.getMtrStatCd())
            && !StringUtils.startsWith(dvo.getSvBizDclsfCd(), "7")) {
            /* 보상 여부가 Y면 */
            if ("Y".equals(dvo.getCompYn())) {
                dvo.setNewSvBizDclsfCd("1124");
            } else if ("Y".equals(dvo.getMtrStatCd()) || "D".equals(dvo.getCompYn())) {
                /*상태코드가 전기레인지 체크*/
                int rangeCnt = mapper.selectCountItemizationByItemGroup(dvo.getCntrNoB());
                /*설치고객코드가 전기레인지 체크*/
                int rangeCnt2 = mapper.selectCountItemizationByItemGroup(dvo.getCntrNo());
                /*철거 가능한 상대 코드가 존재하는지 체크*/
                int reqdCnt = mapper.selectCountItemizationByCntrNoB(dvo.getCntrNoB());

                if (rangeCnt > 0 && rangeCnt2 == 0) {
                    dvo.setNewSvBizDclsfCd("1126"); //이종간기변
                } else if (reqdCnt == 0 && "D".equals(dvo.getMtrStatCd())) {
                    dvo.setNewSvBizDclsfCd("1122"); //자사미회수
                } else {
                    dvo.setNewSvBizDclsfCd("1121"); //자사회수
                }
            } else {
                dvo.setNewSvBizDclsfCd(dvo.getSvBizDclsfCd());
            }
        } else if (List.of("2", "3").contains(dvo.getMtrStatCd())) {
            //wrkTypDtl, cfrmStusWrk, dataStus, ac221CfrmDt, ac221ProcStus
            WsnbMultipleTaskOrderDvo res = mapper.selectWorkRequidationItemization(dvo.getAsIstOjNo());
            dvo.setNewSvBizDclsfCd(res.getNewSvBizDclsfCd());
            dvo.setNewWkAcpteStatCd(res.getNewWkAcpteStatCd());
            dvo.setNewMtrStatCd(res.getNewMtrStatCd());
            dvo.setNewWkAcpteDt(res.getNewWkAcpteDt());
            dvo.setNewWkPrgsStatCd(res.getNewWkPrgsStatCd());
        } else {
            dvo.setNewSvBizDclsfCd(dvo.getSvBizDclsfCd());
        }

        /* 고객명이 없으면 구한다. */
        if (dvo.getRcgvpKnm().trim().length() == 0) {
            dvo.setNewRcgvpKnm(mapper.selectRcgvpKnm(dvo.getCntrNo(), dvo.getCntrSn()));
        } else {
            dvo.setNewRcgvpKnm(dvo.getRcgvpKnm());
        }
        String[] cstNm = {dvo.getNewRcgvpKnm()};

        /* 정상 접수 건인지 체크 */
        if ("1".equals(dvo.getMtrStatCd())) {

            /* 설치 오더인데 설치 일자가 이미 있는지 체크 */
            if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "11") && !"1113".equals(dvo.getNewSvBizDclsfCd())) {
                instDtCnt = mapper.selectCountInstallationDate(dvo.getCntrNo(), dvo.getCntrSn());
                /* 철거 오더인데 철거 일자가 이미 있는지 체크 */
            } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "34")) {
                reqdDtCnt = mapper.selectCountRequidationDate(dvo.getCntrNo(), dvo.getCntrSn());
            }
            /* 해당 고객에 미완료 된 동일 유형의 오더 건이 존재 하는지 체크 */
            asIstAsnIzCnt = mapper.selectCountAsIstAsnIz(dvo);
            /* 고객중지여부 확인 */
            stopDtcnt = mapper.selectCountStopDate(dvo.getCntrNo(), dvo.getCntrSn());

            if (DateUtil.getNowDayString().equals(dvo.getVstRqdt().trim())
                && List.of("3", "4", "5").contains(dvo.getInChnlDvCd())) {
                BizAssert.isTrue(false, "MSG_ALT_TOD_VST_AK_REJ");
            }
            if (instDtCnt > 0) {
                BizAssert.isFalse(instDtCnt > 0, "MSG_ALT_ARDY_IST", cstNm);
            }
            if (reqdDtCnt > 0) {
                BizAssert.isFalse(reqdDtCnt > 0, "MSG_ALT_ARDY_REQD", cstNm);
            }

            if (asIstAsnIzCnt > 0) {
                String cntrNo = dvo.getCntrNo().substring(1, 4) + "-" + dvo.getCntrNo().substring(5);
                String workContent = mapper.selectWorkContent(dvo.getNewSvBizDclsfCd());
                String[] param = {dvo.getNewRcgvpKnm(), cntrNo, workContent};
                BizAssert.isFalse(asIstAsnIzCnt > 0, "MSG_ALT_ARDY_WK", param);
            }

            if ("Y".equals(dvo.getRetYn()) && dvo.getCntrNoB().length() == 0) {
                BizAssert.isTrue(false, "MSG_ALT_RET_NOT_CNTRNO", cstNm);
            }

            if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "34") && stopDtcnt > 0) {
                BizAssert.isTrue(false, "MSG_ALT_DLQ_SV_STP");
            }
            if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3123")
                || StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3124")) {
                /*21.12.22 이영진, 김예은 매니저님요청, 라이트상품(6M3) 상판교체 서비스 없음*/
                if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3124")
                    && !"6M1".equals(dvo.getSvPrd().trim().replace("M0", "M1"))) {
                    BizAssert.isTrue(false, "MSG_ALT_NOT_ESTOV_TOP_CHNG", cstNm);
                }

                /* 사용 개월 체크 */
                useMonths = mapper.selectUseMonth(dvo.getVstRqdt(), dvo.getCntrNo(), dvo.getCntrSn());
                /*21.10.18 최고급안마의자 토탈체인지 서비스 수행 여부 체크*/
                totChangeCnt = mapper.selectCountChangeTotal(dvo.getCntrNo());

                /*21.10.19 전기레인지 글라스 상판 교체 서비스 수행 여부 체크, 또는 특정자재 사용 여부 체크*/
                /* 49280000000 전기레인지 하이브리드(KW-RKB1)
                   49281000000 전기레인지 RM523ABA 하이브리드 3구
                   49282000000 전기레인지 RM723ABA 인덕션 3구 */
                rangeChangeCnt = mapper.selectCountRangeChange(dvo.getCntrNo());
                /*21.10.19 전기레인지 글라스 상판 교체 BS 서비스 수행 여부 체크*/
                rangeChangeBsCnt = mapper.selectCountRangeChangeBs(dvo.getCntrNo());
                /*21.10.19 안마의자 토탈체인지, 전기레인지 상판교체 자재 사용으로 서비스 체크*/
                itemCnt = mapper.selectCountItem(dvo.getCntrNo());

                /*21.10.18 이영진, 최고급 안마의자 토탈 체인지 서비스, 전기레인지 상판 교체 설치 차월 체크*/
                if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3123")) {
                    BizAssert.isFalse(useMonths <= 12, "MSG_ALT_NOT_YET_12M", cstNm);
                    /*21.10.18 이영진, 최고급 안마의자 토탈 체인지 서비스 체크*/
                    if (totChangeCnt > 0 || itemCnt > 0) {
                        BizAssert.isTrue(false, "MSG_ALT_ARDY_TOT_CHNG", cstNm);
                    }
                }
                if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3124")) {
                    BizAssert.isFalse(useMonths <= 24, "MSG_ALT_NOT_YET_24M", cstNm);
                    /*21.10.19 이영진, 전기레인지 글라스 상판 교체 서비스 체크*/
                    if ((rangeChangeCnt > 0 || rangeChangeBsCnt > 0) || itemCnt > 0) {
                        BizAssert.isTrue(false, "MSG_ALT_ARDY_RANGE_TOP_CHNG", cstNm);
                    }
                }

            }
        }

        if (List.of("2", "3").contains(dvo.getMtrStatCd())) {
            /*해당접수키로 존재하는지 체크*/
            asIstOjIzCnt = mapper.selectCountAsIstOjIz(dvo.getAsIstOjNo());

            if (!"00".equals(dvo.getNewWkPrgsStatCd()) && !"10".equals(dvo.getNewWkPrgsStatCd())) {
                BizAssert.isTrue(false, "MSG_ALT_NOT_MDFC_CAN_STAT");
            }
            if (DateUtil.getNowDayString().equals(dvo.getVstRqdt().trim())
                && List.of("3", "4", "5").contains(dvo.getInChnlDvCd())) {
                BizAssert.isTrue(false, "MSG_ALT_TOD_VST_AK_REJ");
            }
            BizAssert.isFalse(asIstOjIzCnt == 0, "MSG_ALT_RCP_DIFF_WAY", cstNm);
            if (DateUtil.getNowDayString().equals(dvo.getNewWkAcpteDt())
                && !"3460".equals(dvo.getNewSvBizDclsfCd())) {
                BizAssert.isTrue(false, "MSG_ALT_TOD_VST_MDFC_CAN_IMP", cstNm);
            }
            /* 수락 상태이면 수정/취소 안되게 */
            BizAssert.isFalse("Y".equals(dvo.getNewWkAcpteStatCd()), "MSG_ALT_ARDY_ACPTE_STAT", cstNm);
            /* 취소 상태이면 수정/취소 안되게 */
            BizAssert.isFalse("3".equals(dvo.getNewMtrStatCd()), "MSG_ALT_ARDY_CAN_STAT", cstNm);
        }

        /*LC_ALLOCATE_AC211TB 키를 생성한다.
        P_IN_GB 입력구분 1:CC, 2:KIWI, 3:KSS 1이면 CC에서 입력된 P_WRK_DT, P_SEQ 사용 아니면 자체 생성
        SEQ_LC_ALLOCATE_AC211TB 5자리 시퀀스 생성, MAX 되면 CYCLE*/
        WsnbMultipleTaskOrderDvo res2 = mapper.selectAsIstOjIzKey(dvo);
        String newAsIstOjNo = res2.getNewInChnlDvCd() + res2.getNewSvBizHclsfCd() + res2.getNewRcpdt()
            + StringUtils.leftPad(res2.getNewReq(), 8, '0');
        String puCstSvAsnNo = res2.getNewSvBizHclsfCd() + res2.getNewRcpdt()
            + StringUtils.leftPad(res2.getNewReq(), 10, '0');
        dvo.setNewInChnlDvCd(res2.getNewInChnlDvCd());
        dvo.setNewSvBizHclsfCd(res2.getNewSvBizHclsfCd());
        dvo.setNewRcpdt(res2.getNewRcpdt());
        dvo.setNewReq(res2.getNewReq());
        dvo.setNewAsIstOjNo(newAsIstOjNo);
        dvo.setPuCstSvAsnNo(puCstSvAsnNo);

        /* KIWI 코드 및 판매 코드를 세팅한다 */
        WsnbMultipleTaskOrderDvo res3 = mapper.selectPdCd(dvo);
        dvo.setNewPdCd(res3.getNewPdCd());
        dvo.setNewSaleCd(res3.getNewSaleCd());

        String itemGr = mapper.selectItemGr(dvo.getNewPdCd());
        /* 고객서비스수행내역(TB_SVPD_CST_SV_EXCN_IZ) 저장 로직 삭제. TOBE에서는 배치로 처리하기로 함. */
        /* 216TB INSERT 로직 삭제 */
        /* 정보변경프로시저(SP_WRK_TYP_DTL_7100) 호출 로직 삭제 */

        /* 정보 변경이 아니라면 오더 생성 */
        if (!(StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "7"))) {
            /* 받아온 접수키가 존재하는데 P_DATA_STUS 수정(2)이나 취소(3)가 아닌경우 에러 로그 TB_SVPD_CST_SVAS_IST_OJ_ERR_IZ에 저장 */
            if (!"1".equals(dvo.getInChnlDvCd().trim()) && dvo.getAsIstOjNo().trim().length() > 0
                && !List.of("2", "3").contains(dvo.getMtrStatCd())) {
                processCount += mapper.insertErrorItemization(dvo); /* TB_SVPD_CST_SVAS_IST_OJ_ERR_IZ */
            } else if ("1".equals(dvo.getMtrStatCd())) {
                dvo.setMexnoEncr(mapper.selectMexnoEncr()); /* 전화번호 가운데 복호화 */
                processCount += mapper.insertIstObjectItemization(dvo); /* TB_SVPD_CST_SVAS_IST_OJ_IZ */
            } else if ("2".equals(dvo.getMtrStatCd())) {
                processCount += mapper.updateIstObjectItemization(dvo); /* TB_SVPD_CST_SVAS_IST_OJ_IZ */
            } else if ("3".equals(dvo.getMtrStatCd())) {
                processCount += mapper.updateIstObjectItemizationByPk(dvo); /* TB_SVPD_CST_SVAS_IST_OJ_IZ */
            }

            if (!"3".equals(dvo.getMtrStatCd()) && List.of("1310", "3531").contains(dvo.getInChnlDvCd())
                && dvo.getPartList() != null) {
                /*TB_SVPD_CST_SVAS_PU_ITM_IZ 이전 정보를 삭제 */
                processCount += mapper.deleteAsPutItemIz(dvo);
                /*PART_LIST 자재코드,수량,금액 | 자재코드,수량,금액 | ~~~
                * 위 형태의 List를 쪼개서 자재, 수량, 금액으로 저장해서 임시테이블에 insert
                * */
                String[] partList = StringUtils.split(dvo.getPartList(), "|");
                WsnbMultipleTaskOrderDvo partDvo = new WsnbMultipleTaskOrderDvo();
                for (String part : partList) {
                    String[] arr = StringUtils.split(part, ",");
                    partDvo.setPartCd(arr[0]);
                    partDvo.setPartQty(arr[1]);
                    partDvo.setPartAmt(arr[2]);
                    /* 임시테이블에 insert */
                    mapper.insertSdingIzTemp(part); /* TB_SVPD_SDING_PRCHS_IZ_TEMP */
                    mapper.insertSdingProcsTemp(partDvo); /* TB_SVPD_SDING_PRCHS_PROCS_TEMP */
                }

                /* TB_SVPD_CST_SVAS_PU_ITM_IZ에 INSERT할 값 조회 */
                List<WsnbMultipleTaskOrderDvo> putItems = mapper.selectPutItems(dvo);
                for (WsnbMultipleTaskOrderDvo putItem : putItems) {
                    mapper.insertAsPutItemIz(putItem); /* TB_SVPD_CST_SVAS_PU_ITM_IZ */
                }
            }

            /*고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ) 키를 조회 한다.*/
            WsnbMultipleTaskOrderDvo res4 = mapper.selectCustomerServiceAssignNo(dvo);
            String asnCstSvAsnNo = res4.getAsnSvBizHclsfCd() + res4.getAsnDt() + res4.getAsnReq();
            dvo.setAsnSvBizHclsfCd(res4.getAsnSvBizHclsfCd());
            dvo.setAsnDt(res4.getAsnDt());
            dvo.setAsnReq(res4.getAsnReq());
            dvo.setAsnCstSvAsnNo(asnCstSvAsnNo);

            if (List.of("2", "3").contains(dvo.getMtrStatCd())) {
                /* 로그저장(TB_SVPD_CST_SV_AS_IST_ASN_HIST) */
                processCount += mapper.insertAsInstallationAssignHist(dvo.getAsnCstSvAsnNo());
                /* DELETE TB_SVPD_CST_SVAS_IST_ASN_IZ */
                processCount += mapper.deleteAsInstallationAssignIz(dvo.getAsnCstSvAsnNo());
                /*모종 고객이라면 확정되지 않은 해당 방문 스케쥴의 모종 배송 스케쥴을 삭제한다.*/
                if ("11".equals(itemGr)) {
                    /*확정되지 않은 배송오더의 예정 모종 삭제*/
                    mapper.deleteSdingShipping(dvo);
                }
            }

            if (!"3".equals(dvo.getMtrStatCd())) {
                /* 고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ) 키를 생성한다.*/
                WsnbMultipleTaskOrderDvo res5 = mapper.selectCustomerServiceAssignNo(dvo);
                String newAsnCstSvAsnNo = res5.getAsnSvBizHclsfCd() + res5.getAsnDt() + res5.getAsnReq();
                dvo.setAsnSvBizHclsfCd(res5.getAsnSvBizHclsfCd());
                dvo.setAsnDt(res5.getAsnDt());
                dvo.setAsnReq(res5.getAsnReq());
                dvo.setAsnCstSvAsnNo(newAsnCstSvAsnNo);

                /* TODO: 배정정보를 구한다. */

                /* 고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ), HIST insert*/
                processCount += mapper.insertAsInstallationAssign(dvo);
                mapper.insertAsInstallationAssignHistByNewKey(dvo);

                /*모종 고객이라면 생성된 방문 오더 기준 모종 배송 스케쥴을 업데이트 또는 인서트 해주고 방문 오더를 생성한다.*/
                if ("11".equals(itemGr)) {
                    String vstDtChk = mapper.selectVstDtChk(dvo.getVstRqdt());
                    BizAssert.notNull(vstDtChk, "MSG_ALT_CHK_VSTDT");

                    /* ---------진행중------ */
                    if (StringUtils.isEmpty(dvo.getPartList())) {
                        dvo.setExpMat(0);
                        dvo.setSdingExpMat(0);
                        dvo.setExpMatSum(0);
                    } else {
                        /*파라미터로 받은 모종 정보가 있는지 확인한다*/
                        /*파라미터를 임시테이블에 하나씩 넣는다.*/
                        /*2020.01.17 유엔젤주 씨앗패키지 AS접수시 예외처리  */
                        /* TODO : DB2테이블 확인필요 (INSERT INTO LC_FARM_FA001TB 까지 해야함.)*/

                        /*예정 자재 건수 체크, 자재 중 모정 건수 체크, 전체 유상 비용 합*/
                        WsnbMultipleTaskOrderDvo res6 = mapper.selectSdingCount();
                        dvo.setExpMat(res6.getExpMat());
                        dvo.setSdingExpMat(res6.getSdingExpMat());
                        dvo.setExpMatSum(res6.getExpMatSum());
                    }
                    /*--씨앗 패키지 1월 2월 설치분 상품 코드 활력채패키지(123)으로 변경*/
                    WsnbMultipleTaskOrderDvo res7 = mapper.selectPdCd(dvo);
                    dvo.setNewPdCd(res7.getNewPdCd());
                    dvo.setNewSaleCd(res7.getNewSaleCd());

                    /* sppPlanSn 순번구하기 */
                    dvo.setSppPlanSn(mapper.selectSppPlanSn(dvo));
                    /* GET_GOODS_NAME_SALE_CD 값 받아놓기.*/
                    dvo.setSaleNm(mapper.selectSaleNm(dvo.getNewSaleCd()));
                    /*배송 스케쥴 테이블 인서트*/
                    processCount += mapper.insertSdingPlan(dvo);

                    if (dvo.getExpMat() == 0) { /*인터페이스 된 출고 예정 자재 건수가 0 이라면 */
                        dvo.setPdSize(mapper.selectPdSize(dvo.getNewSaleCd()));
                        /* 모종정보 인서트(TODO : DB2 테이블확인필요) */

                    } else { /*구매/AS 고객이라면*/
                        /* 모종정보 인서트 */
                        processCount += mapper.insertSdingExpByAs(dvo);

                        /*배양액만 배송이라면 담당자를 71394 생산관리팀 28714 최진아 사원으로 업데이트*/
                        /*20.08.10 새싹시앗 패키지일 경우 택배배송*/
                        if (dvo.getSdingExpMat() == 0 || (StringUtils.startsWith(dvo.getSaleNm(), "새싹")
                            && !StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "1"))) {
                            processCount += mapper.updateAsInstallationAssign(dvo.getAsnCstSvAsnNo());
                        }
                    }
                }
            }
            /*TB_SVPD_CST_SVAS_IST_OJ_IZ 배정 키 업데이트*/
            processCount += mapper.updateIstObjectKey(dvo);

        }

        return processCount;
    }
}
