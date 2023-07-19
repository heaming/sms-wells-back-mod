package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.IN_CHNL_DV_CD_KMEMBERS;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.IN_CHNL_DV_CD_SALES;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.IN_CHNL_DV_CD_WEB;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.MTR_STAT_CD_DEL;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.MTR_STAT_CD_MOD;
import static com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst.MTR_STAT_CD_NEW;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbWorkOrderConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWorkOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAssignAsWorkDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWorkOrderMapper;
import com.sds.sflex.common.common.dvo.CodeDetailDvo;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0012 작업오더, 정보변경 처리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbWorkOrderService {
    private final WsnbWorkOrderMapper mapper;
    private final WsnbWorkOrderConverter converter;

    private final WsnbContractService contractService;

    private final CodeService codeService;

    private final WsnzHistoryMapper historyMapper;

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
    public String saveWorkOrders(SaveReq dto) throws Exception {
        WsnbWorkOrderDvo dvo = converter.mapSaveReqToWsnbWorkOrderDvo(dto);

        return this.saveWorkOrders(dvo);
    }

    public String saveWorkOrders(WsnbWorkOrderDvo dvo) throws Exception {

        if (StringUtils.isNotEmpty(dvo.getAsIstOjNo()) && "00000000".equals(dvo.getAsIstOjNo().substring(10))) {
            dvo.setAsIstOjNo(null);
        }

        /* 1. ORDER 데이터 세팅 */
        setWorkOrderExtendData(dvo);

        /* 2. Validation Check */
        // 1) TODAY Request Reject
        String nowDayString = DateUtil.getNowDayString();
        if (nowDayString.equals(dvo.getVstRqdt())
            && (List.of(IN_CHNL_DV_CD_SALES, IN_CHNL_DV_CD_WEB, IN_CHNL_DV_CD_KMEMBERS)
                .contains(dvo.getInChnlDvCd()))) {
            throw new BizException("MSG_ALT_TOD_VST_AK_REJ");
        }

        // 2) 오더데이터 Validation
        if (MTR_STAT_CD_NEW.equals(dvo.getMtrStatCd())) {
            checkValidationNewOrder(dvo);
        } else {
            checkValidationExistOrder(dvo);
        }

        /* 3. 오더 생성(정보 변경이 아니면) */
        if (!StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "7")) {
            saveWorkOrder(dvo);
        }

        return dvo.getNewAsIstOjNo();
    }

    private void setWorkOrderExtendData(WsnbWorkOrderDvo dvo) {
        /* 계약 관련 정보 */
        WsnbContractDvo contract = contractService.getContract(dvo.getCntrNo(), dvo.getCntrSn());

        // 계약정보 Extension
        dvo.setCntrCstNo(contract.getCntrCstNo());
        dvo.setNewRcgvpKnm(contract.getRcgvpKnm());
        dvo.setNewPdCd(contract.getPdctPdCd());
        dvo.setIstDt(contract.getIstDt());
        dvo.setReqdDt(contract.getReqdDt());
        dvo.setStopYn(contract.getStopYn());
        dvo.setPdGrpCd(contract.getPdctPdGrpCd());
        dvo.setPdSize(contract.getPdctPdSize());
        dvo.setBasePdCd(contract.getBasePdCd());
        dvo.setBasePdNm(contract.getBasePdNm());

        boolean isExistWorkOrder = List.of(MTR_STAT_CD_MOD, MTR_STAT_CD_DEL).contains(dvo.getMtrStatCd());
        WsnbAssignAsWorkDvo assignAsWork = new WsnbAssignAsWorkDvo();
        if (isExistWorkOrder) {
            assignAsWork = mapper.selectAsAssignByPk(dvo.getAsIstOjNo())
                .orElseThrow(() -> new BizException("MSG_ALT_RCP_DIFF_WAY", new String[] {dvo.getRcgvpKnm()}));
        }

        if (List.of(MTR_STAT_CD_NEW).contains(dvo.getMtrStatCd())
            && !StringUtils.startsWith(dvo.getSvBizDclsfCd(), "7")) {

            if ("Y".equals(dvo.getCpsYn())) {
                /* 보상판매 */
                dvo.setNewSvBizDclsfCd("1124");
            } else if (StringUtils.isNotEmpty(dvo.getRetYn())) {
                /* 기기변경 */
                // 이전계약 확인
                WsnbContractDvo beforeContract = contractService.getBeforeContract(dvo.getCntrNo(), dvo.getCntrSn());

                dvo.setCntrNoB(beforeContract.getCntrNo());
                dvo.setCntrSnB(beforeContract.getCntrSn());

                if ("91".equals(beforeContract.getPdctPdGrpCd())
                    && !"91".equals(contract.getPdctPdGrpCd())) {
                    dvo.setNewSvBizDclsfCd("1126"); //이종간기변
                } else if ((StringUtils.isEmpty(beforeContract.getReqdDt())
                    && StringUtils.isEmpty(beforeContract.getCpsDt())
                    && !"303".equals(beforeContract.getCntrDtlStatCd())) && "N".equals(dvo.getRetYn())) {
                    dvo.setNewSvBizDclsfCd("1122"); //자사미회수
                } else {
                    dvo.setNewSvBizDclsfCd("1121"); //자사회수
                }
            } else {
                dvo.setNewSvBizDclsfCd(dvo.getSvBizDclsfCd());
            }

        } else if (isExistWorkOrder) {
            dvo.setNewSvBizDclsfCd(assignAsWork.getSvBizDclsfCd());
            dvo.setNewWkAcpteStatCd(assignAsWork.getWkAcpteStatCd());
            dvo.setNewMtrStatCd(assignAsWork.getMtrStatCd());
            dvo.setNewWkAcpteDt(assignAsWork.getWkAcpteDt());
            dvo.setNewWkPrgsStatCd(assignAsWork.getWkPrgsStatCd());
            dvo.setRcpdt(assignAsWork.getRcpdt());
        } else {
            dvo.setNewSvBizDclsfCd(dvo.getSvBizDclsfCd());
        }

        /*LC_ALLOCATE_AC211TB 키를 생성한다.
        P_IN_GB 입력구분 1:CC, 2:KIWI, 3:KSS 1이면 CC에서 입력된 P_WRK_DT, P_SEQ 사용 아니면 자체 생성
        SEQ_LC_ALLOCATE_AC211TB 5자리 시퀀스 생성, MAX 되면 CYCL
        E*/
        WsnbWorkOrderDvo keyForAsReceipt = mapper.selectAsIstOjIzKey(dvo);
        String newAsIstOjNo = keyForAsReceipt.getNewInChnlDvCd() + keyForAsReceipt.getNewSvBizHclsfCd()
            + keyForAsReceipt.getNewRcpdt()
            + StringUtils.leftPad(keyForAsReceipt.getNewReq(), 8, "0");
        String puCstSvAsnNo = keyForAsReceipt.getNewSvBizHclsfCd() + keyForAsReceipt.getNewRcpdt()
            + StringUtils.leftPad(keyForAsReceipt.getNewReq(), 10, "0");
        dvo.setNewInChnlDvCd(keyForAsReceipt.getNewInChnlDvCd());
        dvo.setNewSvBizHclsfCd(keyForAsReceipt.getNewSvBizHclsfCd());
        dvo.setNewRcpdt(keyForAsReceipt.getNewRcpdt());
        dvo.setNewReq(keyForAsReceipt.getNewReq());
        dvo.setNewAsIstOjNo(newAsIstOjNo);
        dvo.setPuCstSvAsnNo(puCstSvAsnNo);

        // 상담메시지설정
        setCounselorMemo(dvo);
    }

    private void setCounselorMemo(WsnbWorkOrderDvo dvo) {
        StringBuilder newCounselorMemo = new StringBuilder();
        if ("2".equals(dvo.getNewInChnlDvCd())) {
            String partnerContactPoint = "";

            UserSessionDvo userSession = SFLEXContextHolder.getContext()
                .getUserSession();

            String mobileNo = String.format(
                "%s-%s-%s", userSession.getCralLocaraTno(), userSession.getMexnoGbencr(), userSession.getCralIdvTno()
            );
            if ("W02".equals(userSession.getOgTpCd())) {
                partnerContactPoint = String.format("%sWM(%s)", userSession.getUserName(), mobileNo);
            } else if ("W06".equals(userSession.getOgTpCd())) {
                partnerContactPoint = String.format("%sENG(%s)", userSession.getUserName(), mobileNo);
            } else {
                partnerContactPoint = String.format("%s(%s)", userSession.getUserName(), mobileNo);
            }

            newCounselorMemo.append(dvo.getCnslMoCn()).append("\n")
                .append(partnerContactPoint);
        } else if ("3".equals(dvo.getNewInChnlDvCd()) && List.of("1121", "1122").contains(dvo.getNewSvBizDclsfCd())) {
            CodeDetailDvo code = codeService.getCodeDetailByPk(
                "SV_BIZ_DCLSF_CD",
                dvo.getNewSvBizDclsfCd()
            );

            WsnbContractDvo beforeContract = contractService.getContract(dvo.getCntrNoB(), dvo.getCntrSnB());

            newCounselorMemo.append(code.getCodeName()).append(": ").append(dvo.getCntrNoB()).append(" ")
                .append(beforeContract.getBasePdAbbrNm()).append("\n")
                .append(dvo.getCnslMoCn());
        } else {
            newCounselorMemo.append(dvo.getCnslMoCn());
        }

        dvo.setCnslMoCn(newCounselorMemo.toString());
    }

    private void saveWorkOrder(WsnbWorkOrderDvo dvo) throws Exception {
        if (List.of(MTR_STAT_CD_NEW, MTR_STAT_CD_MOD).contains(dvo.getMtrStatCd())) {
            int result = mapper.mergeInstallationObject(dvo); /* TB_SVPD_CST_SVAS_IST_OJ_IZ */
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        } else if (MTR_STAT_CD_DEL.equals(dvo.getMtrStatCd())) {
            int result = mapper.updateInstallationObjectMtrStatCd(dvo); /* TB_SVPD_CST_SVAS_IST_OJ_IZ */
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }

        setAssignKeyAndValue(dvo);

        boolean isWellsFarmSeeding = "11".equals(dvo.getPdGrpCd());
        if (MTR_STAT_CD_MOD.equals(dvo.getMtrStatCd())
            || MTR_STAT_CD_DEL.equals(dvo.getMtrStatCd())) {
            /* 로그저장(TB_SVPD_CST_SV_AS_IST_ASN_HIST) */
            historyMapper.insertCstSvasIstAsnHistByPk(dvo.getAsnCstSvAsnNo());
            /* DELETE TB_SVPD_CST_SVAS_IST_ASN_IZ */
            mapper.deleteAsInstallationAssign(dvo.getAsnCstSvAsnNo());
            /*모종 고객이라면 확정되지 않은 해당 방문 스케쥴의 모종 배송 스케쥴을 삭제한다.*/
            if (isWellsFarmSeeding) {
                /*확정되지 않은 배송오더의 예정 모종 삭제*/
                mapper.deleteSeedingShipping(dvo);
                /* TODO: 확정되지않은 배송오더삭제 */
            }
        }

        if (!MTR_STAT_CD_DEL.equals(dvo.getMtrStatCd())) {
            /* 배정 담당자 정보 세팅 */
            WsnbWorkOrderDvo IchrPrtnr = mapper.selectAsAssignOganizationByPk(dvo);
            dvo.setIchrCnrCd(IchrPrtnr.getIchrCnrCd());
            dvo.setIchrPrtnrNo(IchrPrtnr.getIchrPrtnrNo());
            dvo.setIchrOgTpCd(IchrPrtnr.getIchrOgTpCd());
            dvo.setRpbLocaraCd(IchrPrtnr.getRpbLocaraCd());

            //키 생성.
            setNewAssignKey(dvo);
            /* 고객서비스AS설치배정내역(TB_SVPD_CST_SV_AS_IST_ASN_IZ), HIST insert*/
            mapper.insertAsInstallationAssign(dvo);
            Thread.sleep(1000);
            historyMapper.insertCstSvasIstAsnHistByPk(dvo.getAsnCstSvAsnNo());

            /*--필터판매 또는 자재선택(3531 - 비스포크 패널 교체)일 경우 A/S투입부품정보 테이블에 인서트 한다.*/
            if (List.of("1310", "3531").contains(dvo.getNewSvBizDclsfCd()) && dvo.getPartList() != null) {
                saveAsPuItem(dvo);
            }

            /*모종 고객이라면 생성된 방문 오더 기준 모종 배송 스케쥴을 업데이트 또는 인서트 해주고 방문 오더를 생성한다.*/
            if (isWellsFarmSeeding) {
                createSeedingPlan(dvo);
            }
        }

        /*TB_SVPD_CST_SVAS_IST_OJ_IZ 배정 키 업데이트*/
        mapper.updateInstallationObjectKey(dvo);
    }

    private void setAssignKeyAndValue(WsnbWorkOrderDvo dvo) {
        WsnbWorkOrderDvo keyForAsAssign = mapper.selectCustomerServiceAssignNo(dvo);
        String asnCstSvAsnNo = keyForAsAssign.getAsnSvBizHclsfCd() + keyForAsAssign.getAsnDt()
            + keyForAsAssign.getAsnReq();
        dvo.setAsnSvBizHclsfCd(keyForAsAssign.getAsnSvBizHclsfCd());
        dvo.setAsnDt(keyForAsAssign.getAsnDt());
        dvo.setAsnReq(keyForAsAssign.getAsnReq());
        dvo.setAsnCstSvAsnNo(asnCstSvAsnNo);
    }

    private void setNewAssignKey(WsnbWorkOrderDvo dvo) {
        WsnbWorkOrderDvo newkeyForAsAssign = mapper.selectCustomerServiceNewAssignNo(dvo);
        String newAsnCstSvAsnNo = newkeyForAsAssign.getAsnSvBizHclsfCd() + newkeyForAsAssign.getAsnDt()
            + newkeyForAsAssign.getAsnReq();
        dvo.setAsnSvBizHclsfCd(newkeyForAsAssign.getAsnSvBizHclsfCd());
        dvo.setAsnDt(newkeyForAsAssign.getAsnDt());
        dvo.setAsnReq(newkeyForAsAssign.getAsnReq());
        dvo.setAsnCstSvAsnNo(newAsnCstSvAsnNo);
    }

    private void saveAsPuItem(WsnbWorkOrderDvo dvo) {
        /*TB_SVPD_CST_SVAS_PU_ITM_IZ 이전 정보를 삭제 */
        mapper.deleteAsPutItem(dvo);

        /*PART_LIST 자재코드,수량,금액 | 자재코드,수량,금액 | ~~~
        * 위 형태의 List를 쪼개서 자재, 수량, 금액으로 저장해서 임시테이블에 insert
        * */
        String[] partList = StringUtils.split(dvo.getPartList(), "|");
        WsnbWorkOrderDvo partDvo = new WsnbWorkOrderDvo();
        for (String part : partList) {
            String[] arr = StringUtils.split(part, ",");
            partDvo.setPartCd(arr[0]);
            partDvo.setPartQty(arr[1]);
            partDvo.setPartAmt(arr[2]);
            /* 임시테이블에 insert */
            mapper.insertSeedingTemp(part); /* TB_SVPD_SDING_PRCHS_IZ_TEMP */
            mapper.insertSeedingProcsTemp(partDvo); /* TB_SVPD_SDING_PRCHS_PROCS_TEMP */
        }

        /* TB_SVPD_CST_SVAS_PU_ITM_IZ에 INSERT할 값 조회 */
        List<WsnbWorkOrderDvo> putItems = mapper.selectPutItems(dvo);
        for (WsnbWorkOrderDvo putItem : putItems) {
            mapper.insertAsPutItem(putItem); /* TB_SVPD_CST_SVAS_PU_ITM_IZ */
        }
    }

    private void createSeedingPlan(WsnbWorkOrderDvo dvo) {
        String vstDtChk = mapper.selectVstDtChk(dvo.getVstRqdt())
            .orElseThrow(() -> new BizException("MSG_ALT_CHK_VSTDT"));
        dvo.setVstDtChk(vstDtChk);

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
            WsnbWorkOrderDvo res6 = mapper.selectSdingCount();
            dvo.setExpMat(res6.getExpMat());
            dvo.setSdingExpMat(res6.getSdingExpMat());
            dvo.setExpMatSum(res6.getExpMatSum());
        }

        /* sppPlanSn 순번구하기 */
        dvo.setSppPlanSn(mapper.selectSppPlanSn(dvo));
        /*배송 스케쥴 테이블 인서트*/
        mapper.insertSeedingPlan(dvo);

        if (dvo.getExpMat() == 0) { /*인터페이스 된 출고 예정 자재 건수가 0 이라면 */
            /* 모종정보 인서트(TODO : DB2 테이블확인필요) */

        } else { /*구매/AS 고객이라면*/
            /* 모종정보 인서트 */
            mapper.insertSeedingExpByAs(dvo);

            /*배양액만 배송이라면 담당자를 71394 생산관리팀 28714 최진아 사원으로 업데이트*/
            /*20.08.10 새싹시앗 패키지일 경우 택배배송*/
            if (dvo.getSdingExpMat() == 0 || (StringUtils.startsWith(dvo.getBasePdNm(), "새싹")
                && !StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "1"))) {
                mapper.updateAsInstallationAssign(dvo.getAsnCstSvAsnNo());
            }
        }
    }

    private void checkValidationNewOrder(WsnbWorkOrderDvo dvo) {
        int rangeChangeCnt;
        int rangeChangeBsCnt;
        int asAssignCnt;
        int totChangeCnt;
        int itemCnt;

        String[] cstNm = {dvo.getNewRcgvpKnm()};

        /* 설치 오더인데 설치 일자가 이미 있는지 체크 */
        if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "11") && !"1113".equals(dvo.getNewSvBizDclsfCd())) {
            BizAssert.isNull(dvo.getIstDt(), "MSG_ALT_ARDY_IST", cstNm);
            /* 철거 오더인데 철거 일자가 이미 있는지 체크 */
        } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "34")) {
            BizAssert.isNull(dvo.getReqdDt(), "MSG_ALT_ARDY_REQD", cstNm);
        }

        /* 해당 고객에 미완료 된 동일 유형의 오더 건이 존재 하는지 체크 */
        asAssignCnt = mapper.selectAsAssignCountByPk(dvo);

        if (asAssignCnt > 0) {
            String cntrNo = dvo.getCntrNo().substring(1, 4) + "-" + dvo.getCntrNo().substring(5);
            String workContent = mapper.selectWorkContent(dvo.getNewSvBizDclsfCd());
            String[] param = {dvo.getNewRcgvpKnm(), cntrNo, workContent};
            throw new BizException("MSG_ALT_ARDY_WK", param);
        }

        if ("Y".equals(dvo.getRetYn()) && dvo.getCntrNoB().length() == 0) {
            throw new BizException("MSG_ALT_RET_NOT_CNTRNO", cstNm);
        }

        if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "34") && "Y".equals(dvo.getStopYn())) {
            throw new BizException("MSG_ALT_DLQ_SV_STP");
        }
        if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3123")
            || StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3124")) {
            /*21.12.22 이영진, 김예은 매니저님요청, 라이트상품(6M3) 상판교체 서비스 없음*/
            if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3124")
                && !"6M1".equals(dvo.getSvPrd().trim().replace("M0", "M1"))) {
                throw new BizException("MSG_ALT_NOT_ESTOV_TOP_CHNG", cstNm);
            }

            /* 사용 개월 체크 */
            int useMonths = 0;
            if (StringUtils.isNotEmpty(dvo.getIstDt())) {
                useMonths = (Integer.parseInt(dvo.getVstRqdt()) - Integer.parseInt(dvo.getIstDt())) / 30;
            }
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
            itemCnt = mapper.selectWorkOutStorageCount(dvo.getCntrNo(), dvo.getCntrSn());

            /*21.10.18 이영진, 최고급 안마의자 토탈 체인지 서비스, 전기레인지 상판 교체 설치 차월 체크*/
            if (StringUtils.startsWith(dvo.getNewSvBizDclsfCd(), "3123")) {
                BizAssert.isFalse(useMonths <= 12, "MSG_ALT_NOT_YET_12M", cstNm);
                /*21.10.18 이영진, 최고급 안마의자 토탈 체인지 서비스 체크*/
                BizAssert.isTrue(totChangeCnt > 0 || itemCnt > 0, "MSG_ALT_ARDY_TOT_CHNG", cstNm);

            }
            if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3124")) {
                BizAssert.isFalse(useMonths <= 24, "MSG_ALT_NOT_YET_24M", cstNm);
                /*21.10.19 이영진, 전기레인지 글라스 상판 교체 서비스 체크*/

                BizAssert.isTrue(
                    (rangeChangeCnt > 0 || rangeChangeBsCnt > 0) || itemCnt > 0, "MSG_ALT_ARDY_RANGE_TOP_CHNG",
                    cstNm
                );
            }
        }
    }

    private void checkValidationExistOrder(WsnbWorkOrderDvo dvo) {
        String[] cstNm = {dvo.getNewRcgvpKnm()};

        if (dvo.getNewWkPrgsStatCd() != null) {

            boolean canChangeWkPrgsStatCd = List.of("00", "10").contains(dvo.getNewWkPrgsStatCd());
            BizAssert.isTrue(canChangeWkPrgsStatCd, "MSG_ALT_NOT_MDFC_CAN_STAT");

            String nowDayString = DateUtil.getNowDayString();
            if (nowDayString.equals(dvo.getNewWkAcpteDt())
                && !"3460".equals(dvo.getNewSvBizDclsfCd())) {
                throw new BizException("MSG_ALT_TOD_VST_MDFC_CAN_IMP", cstNm);
            }
            /* 수락 상태이면 수정/취소 안되게 */
            BizAssert.isFalse("Y".equals(dvo.getNewWkAcpteStatCd()), "MSG_ALT_ARDY_ACPTE_STAT", cstNm);
            /* 취소 상태이면 수정/취소 안되게 */
            BizAssert
                .isFalse(MTR_STAT_CD_DEL.equals(dvo.getNewMtrStatCd()), "MSG_ALT_ARDY_CAN_STAT", cstNm);
        }

    }
}
