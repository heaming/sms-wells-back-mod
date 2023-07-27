package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalCancelDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalCancelMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalCancelService {

    private final WctbRentalCancelMapper mapper;

    private final MessageResourceService messageResourceService;

    public WctbRentalCancelDvo getRentalCanCcamRfdamt(WctbRentalCancelDvo inputDvo) throws ParseException {
        // 파라미터 체크
        ValidAssert.notNull(inputDvo.getCntrSn());
        ValidAssert.hasText(inputDvo.getCntrNo());
        ValidAssert.hasText(inputDvo.getCanDt());
        ValidAssert.hasText(inputDvo.getCanRqdt());

        WctbRentalCancelDvo returnDvo = new WctbRentalCancelDvo();

        String cntrNo = inputDvo.getCntrNo(); // 계약번호
        int cntrSn = inputDvo.getCntrSn(); // 계약일련번호

        String canTpCd = StringUtils.defaultIfEmpty(inputDvo.getCanTpCd(), "19"); // 취소유형코드 DISP-CFLG

        String cnmsExmptDvCd = inputDvo.getCnmsExmptDvCd(); // 소모품면책구분 DISP-SGUB
        String reqdExmptDvCd = inputDvo.getReqdExmptDvCd(); // 철거면책구분 DISP-RGUB
        String borExmptDvCd = inputDvo.getBorExmptDvCd(); // 위약금면책구분 DISP-CGUB

        String orgCanDt = inputDvo.getCanDt(); // 취소일 WORK-REQT DISP-REQT
        String orgCanRqdt = inputDvo.getCanRqdt(); // 취소요청일자
        String dvCd = StringUtils.isNotEmpty(inputDvo.getDvCd()) && List.of("2", "4").contains(inputDvo.getDvCd())
            ? inputDvo.getDvCd() : "1"; // 입력구분

        int borAmtParam = inputDvo.getBorAmt(); // 위약금

        BizAssert.isFalse(
            orgCanDt.length() > 8, "MSG_ALT_CHK_CONFIRM",
            new String[] {messageResourceService.getMessage("MSG_TXT_CANC_DT")} // 취소일자를 확인하세요.
        );
        // 취소일자 말일
        String lastCanDt = DateUtil.getLastDateOfMonth(orgCanDt);
        int lastCanYm = Integer.parseInt(lastCanDt.substring(0, 6));
        int lastCanDay = Integer.parseInt(lastCanDt.substring(7, 8)); // DISP-RDAY SAVE-MDAY

        // 취소요청일자 말일
        String lastCanRqdt = DateUtil.getLastDateOfMonth(orgCanRqdt);
        int lastCanRqYm = Integer.parseInt(lastCanRqdt.substring(0, 6));
        int lastCanRqDay = Integer.parseInt(lastCanRqdt.substring(0, 6));

        int canCtrAmt = inputDvo.getCanCtrAmt(); // 취소조정(부호) WORK-AC15

        List<WctbRentalCancelDvo> dvos = mapper.selectRentalCanCcamRfdamts(inputDvo);
        WctbRentalCancelDvo dscAmtDvo = new WctbRentalCancelDvo();

        /* 반복문 */
        for (WctbRentalCancelDvo dvo : dvos) {
            int stplPtrm = dvo.getStplPtrm(); // 약정기간
            String pdChYn = dvo.getPdChYn(); // 상변여부
            int canDt = Integer.parseInt(orgCanDt); // 취소일자
            int canRqDt = Integer.parseInt(orgCanRqdt); // 취소요청일자
            int canYm = Integer.parseInt(orgCanDt.substring(0, 6)); // WORK-DAYM REDF-RQYM
            int canRqYm = Integer.parseInt(orgCanRqdt.substring(0, 6));
            int canRqDay = Integer.parseInt(orgCanRqdt.substring(7, 8)); // WORK-REQD
            int canDay = Integer.parseInt(orgCanDt.substring(7, 8));

            int cntrDt = dvo.getCntrDt(); // 계약일

            int btdDlqAddAmt = dvo.getBtdDlqAddAmt(); // 체가산금- 기초연체가산금액 LCAM81 DISP-AM81
            int thmDlqAddDpSumAmt = dvo.getThmDlqAddDpSumAmt(); // 연체가산금- 당월연체가산입금합계금액  SAVE-AM83
            int thmCtrDlqAddAmt = dvo.getThmCtrDlqAddAmt();// 연체가산금 - 당월조정연체가산금액 // DISP-AM85

            int pre1SlDpAggAmt = dvo.getPre1SlDpAggAmt(); // 매출입금누계금액 LCAM39 PRE1-AM39

            int stplEnddt = dvo.getStplEnddt(); // 약정종료일자 DISP-EDDT

            int slRcogDt = dvo.getSlRcogDt(); // 매출일자
            int istmMcn = dvo.getIstmMcn(); // 할부개월수

            int stplTn = dvo.getStplTn(); // 약정회차

            String orgSlRcogDt = Integer.toString(slRcogDt); // 매출일자(String)
            String orgSlRcogYm = orgSlRcogDt.substring(0, 6);
            String orgSlRcogDay = orgSlRcogDt.substring(7, 8);

            String canAdjDv = dvo.getCanAdjDv(); // 취소정산구분

            int slRcogYm = Integer.parseInt(orgSlRcogYm);
            int slRcogDay = Integer.parseInt(orgSlRcogDay); // WORK-SLED

            int pre1ThmUcBlam = dvo.getPre1ThmUcBlam(); // 당월미수잔액 LCMAMT  PRE1-MAMT

            String slYn = dvo.getSlYn(); // 매출정보 존재여부
            String chkDv2 = dvo.getChkDv2(); // 오류-FLG2
            String chkDv4 = dvo.getChkDv4(); // 오류-FLG4
            String akId = inputDvo.getAkId(); // 사번
            String alncmpCd = StringUtils.defaultIfEmpty(dvo.getAlncmpCd(), ""); // 제휴사코드 WORK-ETC8
            String prdGrd = ""; // 상품등급
            String sellTpDtlCd = dvo.getSellTpDtlCd(); // 판매유형상세코드
            String pdCd = dvo.getPdCd(); // 상품코드
            String alncPrtnrDrmVal = dvo.getAlncPrtnrDrmVal(); // 제휴파트너식별값

            String gKey = "33".equals(alncmpCd) ? alncPrtnrDrmVal : "";

            String prmStrtY = dvo.getPrmStrtY(); // 선납시작년 WORK-PSYM DISP-PSYY
            String prmStrtMm = dvo.getPrmStrtMm(); // 선납시작월 WORK-PSMM DISP-PSMM
            int prmStrtYm = Integer.parseInt(prmStrtY + prmStrtMm);

            String prmEndY = dvo.getPrmEndY(); // 선납종료년 WORK-PEYY
            String prmEndMm = dvo.getPrmEndMm(); // 선납종료월 WORK-PEMMM
            int prmEndYm = Integer.parseInt(prmEndY + prmEndMm);

            int slRcogLastDay = Integer.parseInt(DateUtil.getLastDateOfMonth(orgSlRcogDt).substring(7, 8)); // SLET-MDAY

            int asdpAmt = dvo.getAsdpAmt(); // 입금금액
            int btdAtam = dvo.getBtdAtam(); // 기초선수금
            int rfndAmt = dvo.getRfndAmt(); // 환불금액
            int prmBlamBtdAmt = dvo.getPrmBlamBtdAmt(); // 선납잔액기초금액

            int rentalRgstCost = dvo.getRentalRgstCost(); // 렌탈등록비 DISP-TAMT
            int dscAmt = dvo.getDscAmt(); // 할인금액 DISP-RAMT

            int pre1IntDscAggAmt = dvo.getPre1IntDscAggAmt(); // 이자할인누계금액
            int pre1IntCtrAggAmt = dvo.getPre1IntCtrAggAmt(); // 이자조정누계금액

            int eotPcamBlam = dvo.getEotPcamBlam(); // 기말원금잔액
            int eotPcamIntBlam = dvo.getEotPcamIntBlam(); // 기말원금이자잔액

            int canSlDt = canRqDt - slRcogDay; // 취소일자 - 매출일자 DISP-CDAY
            int canRqDtBeforeMonth = Integer.parseInt(DateUtil.addMonths(orgCanRqdt, -1).substring(0, 6));

            int workSday = 0;

            boolean isProdGrd = ("45".equals(alncmpCd) && canSlDt <= 7) || !("45".equals(alncmpCd) && canSlDt <= 14);
            boolean isUseDt = (List.of("13", "14", "45").contains(alncmpCd) && canSlDt <= 7
                || !List.of("13", "14", "45").contains(alncmpCd) && canSlDt <= 14);

            BizAssert.isFalse(stplPtrm <= 0 && "N".equals(pdChYn), "MSG_ALT_NOT_VAILD_LCK_IN_PRD"); // 의무사용기간이 없습니다.

            BizAssert.isFalse("N".equals(slYn) || "Y".equals(chkDv2), "MSG_ALT_REQ_DT_CHECK1"); // 렌탈실적 자료가 없습니다.

            BizAssert.isFalse("Y".equals(chkDv4), "MSG_ALT_RENTAL_EXN_CST"); // 렌탈 만료고객입니다.

            /* 요청일자Check. */
            BizAssert.isFalse(canRqDt < slRcogDt, "MSG_ALT_REQ_DT_CHECK1"); // 요청일자를 확안하십시오.

            int workYday = slRcogDt + (istmMcn / 12);

            BizAssert.isFalse(slRcogDt > workYday, "MSG_ALT_REQ_DT_CHECK2"); // 요청일이 만료일보다 이후입니다.

            BizAssert.isFalse(
                (canRqYm < canRqDtBeforeMonth) && List.of("37658", "38754").contains(akId), "MSG_ALT_CANC_YM_CHECK1" // 취소요청월이 전월 이전입니다.
            );
            /* 취소일자Check. */
            BizAssert.isFalse(canRqDt > canDt, "MSG_ALT_CANC_DT_CHECK1"); // 취소일자를 확인하십시오.

            /* 상품등급Check. */
            if (isProdGrd) {
                prdGrd = "E";
            } else {
                prdGrd = "R";
            }

            dvo.setAssetGdCd(prdGrd); // DISP-IGRD

            BizAssert.isFalse(
                !List.of("E", "R").contains(prdGrd), "MSG_ALT_CHK_CONFIRM",
                new String[] {messageResourceService.getMessage("MSG_TXT_PD_GRD")} // 상품등급을 확인하세요.
            );

            /* 장기할부　사용일수Check */
            if (canSlDt > 14 && List.of("25", "26").contains(sellTpDtlCd) && !"38754".equals(akId)) {
                dvo.setErrCn("I00876");
                dvo.setErrYn("Y");
                return dvo;
            }
            /* 선납／선수잔액－당월Check. */
            dvo.setAsdpRfndAmt((btdAtam + asdpAmt) - rfndAmt); // 입금금액 + 기초선수금 - 환불금액 설정
            int asdpRfndAmt = dvo.getAsdpRfndAmt(); // 입금금액 + 기초선수금 - 환불금액

            /* 재약정　할인금액Check. */
            int stplDscConfAmt = dvo.getStplDscConfAmt(); // 재약정할인 LCRAM3 WORK-RAM3
            int cntrRcpFshDt = dvo.getCntrRcpFshDt(); // 계약시작일
            int rentalPtrm = dvo.getRentalPtrm(); // 렌탈기간 LCMON1

            /* 매출금액Check*/
            if (slRcogDt > 0) {
                workSday = Integer.parseInt(orgCanRqdt.substring(7, 8)); // 취소요청날
            }

            /* 당월　사용일수 */
            if (orgCanRqdt.equals(orgSlRcogYm)) {
                workSday -= slRcogDay; //입력받은 취소요청일자 - 매출일자(DAY)
            }

            workSday = workSday > 30 ? 30 : workSday; // 31일 제외
            /* 설치월(1-3.매출일)　정보(WORK-AMT0, 0DAY) */
            int workAmt71 = dvo.getWorkAmt71(); // 빨간펜포인트
            int rentalTn = dvo.getRentalTn(); // 렌탈회차 DISP-RCNT
            int slThmSlSumAmt = dvo.getSlThmSlSumAmt(); // 매출(설치일)월기준- 매출합계
            int slRentalDc = dvo.getSlRentalDc(); // 매출(설치일)월기준-렌탈일수 WORK-0DAY
            int recapDutyPtrmN = dvo.getRecapDutyPtrmN(); // 유상의무기간수
            int pdChRentalTn = dvo.getPdChRentalTn();// 상변회차

            /* 월 렌탈료 Check */
            int pdBaseAmt = dvo.getPdBaseAmt(); // 월 렌탈료
            int pdBaseAmtDsc = 0;
            int rentalAmt = dvo.getRentalAmt(); // 렌탈료
            int rentalDscAmt = dvo.getRentalDscAmt(); // 렌탈할인1
            int rentalDscAmt2 = dvo.getRentalDscAmt2(); // 렌탈할인2
            int svAmt = dvo.getSvAmt(); // 서비스금액

            int pre1DscAggAmt = dvo.getPre1DscAggAmt(); // 할인누계금액 PRE1-AM18

            if (cntrRcpFshDt >= 20160301) {
                pdBaseAmtDsc = rentalAmt - rentalDscAmt; // CHEK-MAMT
            }

            int workAmt0 = slThmSlSumAmt; // WORK-AMT0
            int workAmt1 = rentalAmt; // WORK-AM1 DISP-AMT1
            int workAmt2 = dvo.getRentalAmt2(); // WORK-AM2 DISP-AMT2
            int workAmt11 = 0; // WORK-AM11
            int workAmt12 = 0; // WORK-AM12
            int workAmt13 = 0; // WORK-AM13
            int workAmt14 = 0; // WORK-AM14
            int workAmt15 = 0; // WORK-AM15
            int workAmt16 = 0; // WORK-AM16
            int workAmt17 = 0; // WORK-AM17
            int workAmt18 = 0; // WORK-AM18
            int workAmt19 = 0; // WORK-AM19
            int workAmt21 = prmBlamBtdAmt; // WORK-AM21
            int workAmt31 = btdAtam; // WORK-AM31
            int workAmt36 = workAmt21 + workAmt31; // WORK-AM36
            int workAmt38 = 0; // WORK-AM38
            int workAmt39 = 0; // WORK-AM39
            int workAmt43 = 0; // WORK-AM43
            int workAmt74 = 0; // WORK-AM74
            int workAmt75 = 0; // WORK-AM75
            int workAmt41 = 0; // WORK-AM41
            int workAmt42 = 0; // WORK-AM42
            int workAmt44 = 0; // WORK-AM44
            int workAmt81 = btdDlqAddAmt; // WORK-AM81
            int workAmt83 = 0; // WORK-AM83
            int workAmt84 = 0; // WORK-AM84
            int workAmt85 = thmCtrDlqAddAmt; // WORK-AM85
            int workIamt = 0; // WORK-IAMT
            int workPamt = 0; // WORK-PAMT
            int workMamt = 0; // WORK-MAMT
            int workCc15 = 0; // WORK-CC15

            int workA441 = 0; // WORK-A441
            int workA442 = 0; // WORK-A442
            int workA443 = 0; // WORK-A443
            int workA444 = 0; // WORK-A444
            int workA445 = 0; // WORK-A445
            int workA446 = 0; // WORK-A446
            int workA447 = 0; // WORK-A447
            int workA448 = 0; // WORK-A448
            int workA449 = 0; // WORK-A449

            int workA541 = 0; // WORK-A545
            int workA542 = 0; // WORK-A545
            int workA543 = 0; // WORK-A545
            int workA544 = 0; // WORK-A545
            int workA545 = 0; // WORK-A545
            int workA546 = 0; // WORK-A546
            int workA547 = 0; // WORK-A547
            int workA548 = 0; // WORK-A548
            int workA549 = 0; // WORK-A549

            int saveAmt83 = thmDlqAddDpSumAmt; // SAVE-AM83

            int workCm11 = 0; // WORK-CM11
            int workCm13 = 0; // WORK-CM13
            int workCm15 = 0; // WORK-CM15
            int workCm16 = 0; // WORK-CM16
            int workCm17 = 0; // WORK-CM17
            int workCm18 = 0; // WORK-CM18
            int workCm19 = 0; // WORK-CM19

            int workAv16 = 0;// WORK-AV16
            int workAv17 = 0;// WORK-AV17
            int workAv43 = 0;// WORK-AV43
            int workCv16 = 0; // WORK-CV16
            int workCv17 = 0; // WORK-CV17

            int saveAm44 = 0; // SAVE-AM44

            int disGub3 = recapDutyPtrmN - pdChRentalTn;
            int dispCont = Integer.parseInt(DateUtil.addMonths(orgSlRcogDt, disGub3)); // DISP-CONT

            int workMjan = 0; //WORK-MJAN

            int workAc15 = canCtrAmt; // WORK-AC15 DISP-AC15

            int spmtSlAmt = dvo.getSpmtSlAmt(); // WORK-AM12
            int nomDscAmt = dvo.getNomDscAmt(); // WORK-AM13
            int spmtDscAmt = dvo.getSpmtDscAmt(); // WORK-AM14
            int slCtrAmt = dvo.getSlCtrAmt(); // WORK-AM15
            int thmSlSumAmt = dvo.getThmSlSumAmt(); // WORK-AM16

            int pre1SlAggAmt = dvo.getPre1SlAggAmt(); // 매출누계금액 PRE1-AM17, WORK-AM11
            int pre1IntAggAmt = dvo.getPre1IntAggAmt();// 이자누계금액 PRE1-CM17, WORK-CM15
            int slAggAmt = dvo.getSlAggAmt(); // LCAM17 PRE1-AM17
            int pre1SlAggVat = dvo.getPre1SlAggVat(); // 매출누계 부가세 PRE1-AV17

            int sellAmt = dvo.getSellAmt(); // 판매금액
            int rentalTam = dvo.getRentalTam(); // 렌탈총액 WORK-AMTT

            int adnSvCsAmt = dvo.getAdnSvCsAmt(); // 서비스:서비스비용
            int adnSvCsDscAmt = dvo.getAdnSvDscAmt(); // 서비스:할인금액

            int l500mon1 = 0; // L500-MON1
            int l500Smt1 = 0; // L500-SMT1

            int l700Tamt = adnSvCsAmt;// L700-TAMT
            int l700Ramt = adnSvCsDscAmt; // L700-RAMT
            int l700Camt = 0; // L700-CAMT

            int tempAmt1 = 0; // TEMP-AMT1
            int tempAmt2 = 0; // TEMP-AMT4
            int tempAmt3 = 0; // TEMP-AMT4
            int tempAmt4 = 0; // TEMP-AMT4
            int tempAmt5 = 0; // TEMP-AMT5

            int workRam1 = rentalDscAmt; // WORK-RAM1
            int workRam2 = rentalDscAmt2; // WORK-RAM2
            int workRam3 = stplDscConfAmt; // WORK-RAM3

            int workMcm2 = 0; // WORK-MCM2
            int workSam2 = 0; // WORK-SAM2

            int chekRam3 = 0; // CHEK-RAM3

            int chekMamt = 0; // CHEK-MAMT
            int chekNamt = 0; // CHEK-NAMT
            int chekTamt = 0; // CHEK-TAMT
            int chekVamt = 0; // CHEK-VAMT

            int chekAmt14 = 0; // CHEK-AM14

            int prmMcn = dvo.getPrmMcn(); // 선납개월수 DISP-PMON
            int slDc = dvo.getSlDc(); // 매출일수 DISP-SDAY

            int pre1EotUcAmt = dvo.getPre1EotUcAmt(); // 기말미수금액
            int prmSlAmt = dvo.getPrmSlAmt(); // 선납매출금액 LCPAM1 TODO: 값이 상이함, 추후 문의
            int prmSlAmt2 = dvo.getPrmSlAmt2(); // 선납매출금액 LCPAM2 TODO: 컬럼 미존재

            String dispCdyn = dvo.getDispCdyn(); // 재약정의무기간내 설정 DISP-CDYN
            /* 2-2-2-3. *---사용　중인　자료는　일수　계산 */
            if (canRqDay == lastCanRqDay || canRqDay >= 30) {
                l700Camt = l700Tamt - l700Ramt;
            } else {
                tempAmt4 = (l700Tamt - l700Ramt) / 30 * canRqDay;
                l700Camt = tempAmt4;
            }

            if ("22".equals(sellTpDtlCd)) {
                /* FIELD-AM114-RTN
                * *1) 취소매출금액　계산
                *---총　사용일수가15일　미만이면　매출금액은　없음
                * */
                if (isProdGrd) {
                    workAmt15 = pre1SlAggAmt;
                    workCm15 = pre1IntAggAmt;
                } else if (canYm == canRqYm // 취소일자와 취소요청일자 월 비교
                    && (slRcogYm == canRqYm)) { // 취소요청년월과 매출년월이 일치하는지 확인
                    workCm11 = rentalTam - sellAmt - (l500Smt1 * l500mon1);
                }
                workAmt16 = workAmt11 + workAmt12 - workAmt13 - workAmt14 - workAmt15;
                workCm16 = workCm11 + workCm15;

                /*
                * *2) 취소조정금액　계산
                *---금융리스　취소조정금액　자동　설정（등록시만）
                *    2021.12이전에만　금융리스　취소조정금액　자동설정
                *
                * */
                if ((cntrRcpFshDt < 20211201) || (List.of("25", "26").contains(sellTpDtlCd))) {
                    if ("1".equals(dvCd) && workAc15 == 0) {
                        /*
                        *    금융리스 14(7)일　경과건
                        *    설치일까지　일할계산금액을　취소조정금액으로　자동설정
                        * */
                        if (canSlDt > 14) {
                            if (slRcogDay >= 30 || slRcogDay == slRcogLastDay) { // REDF-SLED = SLET-MDAY
                                workAc15 = rentalAmt;
                            } else {
                                workAc15 = ((rentalAmt / 30) * (slRcogDay - 1)) * 10;
                            }
                        }
                        /*  금융리스 14(7)일　이내　철회건
                        *   전월청구금액을　취소조정금액으로　자동설정
                        * */
                        if (canSlDt <= 14) {
                            workAc15 = pre1EotUcAmt; // 기말미수금액 (PRE1-AM84)
                        }
                    }
                }
                /*
                *--- 2021.12이후　금융리스　일할계산　적용
                *    장기할부K110-CK13   =  "4"
                *    환경할부K110-CK13   =  "5"
                */

                if ((cntrRcpFshDt >= 20211201) && !List.of("25", "26").contains(sellTpDtlCd)) {
                    if (canSlDt > 14) {
                        workMcm2 = rentalAmt - svAmt; // 진행
                        workSam2 = svAmt;

                        if (workSday < 30 || canDay != lastCanDay) {
                            workMcm2 = (workMcm2 / 30) * workSday;
                            workSam2 = (workSam2 / 30) * workSday;
                        }
                    }
                }
            } else if (List.of("6412", "6413", "6414").contains(pdCd)) {
                /*
                *   원두커피는　취소시　매출　발생시키지　않음  2016.9.2
                *   └배송시는　익월에　취소　등록함
                * */
                workAmt11 = 0; // WORK-AM11
                workAmt12 = 0; // WORK-AM12
                workAmt13 = 0; // WORK-AM13
                workAmt14 = 0; // WORK-AM14
                workAmt16 = 0; // WORK-AM16
            } else if (cntrRcpFshDt < 20160301 && lastCanYm == lastCanRqYm) {
                if (isProdGrd) {
                    workAmt15 = pre1SlAggAmt;
                } else {
                    // FIELD-AM116-RTN 파라미터 세팅
                    dvo.setWorkAmt11(workAmt11);
                    dvo.setWorkSday(workSday);
                    dvo.setLastCanRqDay(lastCanRqDay);
                    dvo.setRentalAmtParam(rentalAmt);
                    dvo.setPre1SlAggAmtParam(pre1SlAggAmt);
                    dvo.setCntrNo(cntrNo);
                    dvo.setCntrSn(cntrSn);
                    dvo.setOrgCanDt(orgCanDt);

                    // FIELD-AM116-RTN 계산로직
                    dscAmtDvo = getRentalBase(dvo);

                    workAmt15 = dscAmtDvo.getWorkAmt15();
                    workAmt12 = dscAmtDvo.getSaveA443();
                    workAmt16 = workAmt11 + workAmt12 - workAmt13 - workAmt14 - workAmt15 - workAc15;
                }
            } else if (cntrRcpFshDt < 20160301) {
                // FIELD-AM111-RTN
                /*
                *---총　사용일수가15일　미만이면　매출금액은　없음
                *    14(7)일　이내　철회건
                * */
                if (isUseDt) {
                    workAmt11 = 0;
                    workAmt15 = pre1IntAggAmt;
                } else if (rentalTn == rentalPtrm) { // //*    변동개월
                    if (prmMcn > 0 && lastCanDay == slDc) { // 변동개월：선납
                        workAmt11 = prmSlAmt;
                        workAmt13 = pdBaseAmtDsc - workAmt11;
                    } else if (workSday >= 30 || workSday == lastCanDay) { // 변동개월：１달　사용
                        // 매출
                        tempAmt1 = (workAmt1 / 30) * slRentalDc;
                        tempAmt2 = (workAmt2 / 30) * slRentalDc;
                        workAmt11 = workAmt1 - (tempAmt1 - tempAmt2);
                        //렌탈료　할인
                        tempAmt1 = (workRam1 / 30) * slRentalDc;
                        tempAmt2 = (workRam2 / 30) * slRentalDc;
                        workAmt13 = workRam1 - (tempAmt1 - tempAmt2);
                    } else if (slRcogDay == canRqDay) {
                        // 매출
                        workAmt11 = (workAmt1 / 30) * workSday;
                        // 렌탈료　할인
                        workAmt13 = (workRam1 / 30) * workSday;
                    } else if (slRcogDay < canRqDay) {
                        // 매출
                        tempAmt1 = (workAmt1 / 30) * slRcogDay;
                        tempAmt2 = (workAmt2 / 30) * (workSday - slRcogDay);
                        workAmt11 = tempAmt1 + tempAmt2;
                        //렌탈료　할인
                        tempAmt1 = (workRam1 / 30) * slRcogDay;
                        tempAmt2 = (workRam2 / 30) * (workSday - slRcogDay);
                        workAmt13 = tempAmt1 + tempAmt2;
                    } else {
                        workAmt11 = (pdBaseAmtDsc / 30) * workSday;
                    }
                } else if (rentalTn > 12) { // 12개월 이후
                    // FIELD-CHK1-RTN 생략
                    if (prmMcn > 0 && lastCanDay == slDc) {
                        if (rentalTn <= rentalPtrm) {
                            workAmt11 = prmSlAmt;
                            workAmt13 = pdBaseAmtDsc - workAmt11;
                        } else {
                            workAmt11 = prmSlAmt2;
                            workAmt13 = pdBaseAmtDsc - workAmt11;
                        }
                    }
                } else {
                    /*
                    *   １２개월　이내
                    *   １２개월　이내에만　할인에　대한　추가매출　발생
                    * */
                    workAmt11 = (pdBaseAmtDsc / 30) * workSday;
                    if (workRam1 == 0) {
                        workAmt12 = pre1DscAggAmt;
                    } else {
                        workAmt12 = pre1DscAggAmt - (workRam1 * (rentalTn - 1));
                    }
                }
                /*
                *---추가할인Check.
                * */
                if (workAmt1 > 0) {
                    if (rentalTn <= rentalPtrm && workRam1 > 0) {
                        chekAmt14 = (workAmt11 * workRam1) / workAmt1;
                    } else if (rentalTn > rentalPtrm && workRam2 > 0) {
                        chekAmt14 = (workAmt11 * workRam2) / workAmt2;
                    }
                }

                /*
                *---재약정중　５７차월이상　기변은　취소월　추가　할인적용
                *   재약정할인　금액은　일할계산(2017.09)_
                * */
                if (workRam3 > 0 && rentalTn >= 56 && "19".equals(canTpCd) && "Y".equals(dispCdyn)) {
                    if (workSday >= 30) {
                        chekRam3 = workRam3;
                    } else {
                        chekRam3 = (workRam3 / 30) * workSday;
                    }
                }
                workAmt16 = workAmt11 + workAmt12 - workAmt13 - workAmt14 - workAmt15 - workAc15;
            } else if (canRqYm > canYm) {
                if (isProdGrd) {
                    workAmt15 = pre1SlAggAmt;
                } else {
                    // FIELD-AM116-RTN 파라미터 세팅
                    dvo.setWorkAmt11(workAmt11);
                    dvo.setWorkSday(workSday);
                    dvo.setLastCanRqDay(lastCanRqDay);
                    dvo.setRentalAmtParam(rentalAmt);
                    dvo.setPre1SlAggAmtParam(pre1SlAggAmt);
                    dvo.setCntrNo(cntrNo);
                    dvo.setCntrSn(cntrSn);
                    dvo.setOrgCanDt(orgCanDt);

                    // FIELD-AM116-RTN 계산로직
                    dscAmtDvo = getRentalBase(dvo);

                    workAmt15 = dscAmtDvo.getWorkAmt15();
                    workAmt12 = dscAmtDvo.getSaveA443();
                    workAmt16 = workAmt11 + workAmt12 - workAmt13 - workAmt14 - workAmt15 - workAc15;
                }
            } else {
                // FIELD-AM112-RTN
                /*
                *---렌탈료　기준금　사용
                *   의무기간　경과건은　정상가격　차액　위약금　제외
                * */
                if (canRqYm < canYm) {
                    chekMamt = pdBaseAmt;
                }
                /*
                *
                * ( K멤버스주문은7일　이내) 2022.06.29~
                * */
                if (isProdGrd) {
                    workAmt11 = 0;
                    workAmt15 = slAggAmt;
                } else {
                    if (workSday < 30 && (workSday != lastCanDay)) {
                        chekMamt = (chekMamt / 30) * workSday;
                    }
                    chekMamt = workAmt11;
                    WctbRentalCancelDvo dscAmtAgrgDvo = mapper.selectDscAmtAgrgPrmDscAmt(cntrNo, cntrSn, orgCanDt);
                    workAmt12 = dscAmtAgrgDvo.getSaveA443();
                }
                workAmt13 = 0;
                chekRam3 = 0;
                /*
                *   재약정중　５７차월이상　기변은　취소월　추가　할인적용
                *   재약정중　종료월　말일자　취소건은　할인적용
                *   재약정할인　금액은　일할계산(2017.09)_
                *   재약정　종료월　말일자　취소건은　할인적용
                * */
                if (workRam3 > 0) {
                    if (rentalTn > 56 && "19".equals(canTpCd) && "Y".equals(dispCdyn) || stplEnddt == canRqDt) {
                        chekRam3 = workRam3;
                        if (workSday < 30 && workSday != lastCanDay) {
                            chekRam3 = (workRam3 / 30) * workSday;
                        }
                    }
                }
                /*
                *---재약정　할인금액　추가　반영
                ADD       CHEK-RAM3             TO  WORK-AM13.

                *   선납중　５７차월이상　기변은　취소월　추가　할인적용
                *   선납중　종료월　말일자　취소건은　할인적용
                *   취소월　선납할인　금액은　일할계산
                *
                * */
                if (prmStrtYm > 0) {
                    if ((rentalTn >= 56 && "19".equals(canTpCd) && prmStrtYm <= canYm && prmEndYm >= canYm)
                        || (canYm == prmEndYm && canRqDay == lastCanDay)) {
                        chekRam3 = chekMamt - prmSlAmt;
                        if (workSday < 30) {
                            chekRam3 = (chekRam3 / 30) * workSday;
                        }
                    }
                }
                chekRam3 = workAmt13;
                workAmt16 = workAmt11 + workAmt12 - workAmt13 - workAmt14 - workAmt15 - workAc15;
            }
            workAmt16 += l700Camt;

            chekTamt = 0 - workAmt16;

            chekNamt = (int)(chekTamt / 1.1 + 0.999);
            chekVamt = chekTamt - chekNamt;

            workAv16 = 0 - chekVamt;

            workAmt17 = pre1SlAggAmt + workAmt16;
            workAv17 = workAv17 < 0 && (pre1SlAggVat + workAv16) < 0 ? 0 : (pre1SlAggVat + workAv16);

            workAmt18 = pre1DscAggAmt + workAmt13;
            workAmt19 = dvo.getPre1IntCtrAggAmt();

            dvo.setNomSlAmt(workAmt11); // DISP-AM11
            dvo.setSpmtSlAmt(workAmt12); // DISP-AM12
            dvo.setNomDscAmt(workAmt13); // DISP-AM13
            dvo.setSpmtDscAmt(workAmt14); // DISP-AM14
            dvo.setSlCtrAmt(workAmt15); // DISP-AM15
            dvo.setSlSumAmt(workAmt16); // DISP-AM16
            dvo.setSlAggAmt(workAmt17); // DISP-AM17

            dvo.setCanCtrAmt(workAc15); // DISP-AC15

            dvo.setSlSumVat(workAv16); // DISP-AV16
            dvo.setSlAggVat(workAv17); // DISP-AV17

            dvo.setDscAggAmt(workAmt18); // DISP-AM18
            dvo.setCtrAggAmt(workAmt19); // DISP-AM19

            /*
            *---매출잔액
            *   금융리스 매출이자 부가세 별도 계산
            * */
            if ("22".equals(sellTpDtlCd)) {
                chekTamt = 0 - workCm16;
                chekNamt = (int)(chekTamt / 1.1 + 0.999);
                chekVamt = chekTamt - chekNamt;

                workCv16 = 0 - chekVamt;

                workCm17 = pre1SlAggAmt + workCm16; // DISP-CM17
                workCv17 = pre1SlAggVat + workCv16; // DISP-CV17

                workCm18 = pre1IntDscAggAmt + workCm13; // DISP-CM18
                workCm19 = pre1IntCtrAggAmt; // DISP-CM19

                workMjan = eotPcamBlam + eotPcamIntBlam;

                dvo.setNomIntAmt(workCm11); // WORK-CM11
                dvo.setIntCtrAmt(workCm15); // WORK-CM15
                dvo.setIntCanCtrAmt(workCc15); // WORK-CC15
                dvo.setIntSumAmt(workCm16); // WORK-CM16
                dvo.setIntSumVat(workCv16); // WORK-CV16
                dvo.setIntAggAmt(workCm17); // WORK-CM17
                dvo.setIntAggVat(workCv17); // WORK-CV17
                dvo.setIntDscAggAmt(workCm18); // WORK-CM18
                dvo.setIntCtrAggAmt(workCm19); // WORK-CM19
                dvo.setThmPaiam(workMcm2); // WORK-MCM2
                dvo.setThmSvAmt(workSam2); // WORK-SAM2
            } else {
                workMjan = rentalTam - workAmt17 - workAmt18 - workAmt19;
            }

            dvo.setSlBlamAmt(workMjan); // DISP-MJAN

            /*
            *---빨간펜포인트　있는　경우에는　포인트에서　매출액　차감
            *    (-)매출도　포함시킴→포인트잔액이(+)됨
            *   설치월　매출액은　제외
            * */
            if (workAmt71 > 0) {
                WctbRentalCancelDvo workPointDvo = mapper.selectWorkPointIamt(cntrNo, cntrSn);
                workIamt = workPointDvo.getWorkIamt();
                workPamt = workPointDvo.getWorkPamt();

                if (slRcogYm == canRqYm) {
                    workAmt16 = workAmt0;
                }
                /*
                *---설치월　렌탈료가　입금액보다　큰　경우
                *   설치월　매출　잔액（포인트　차감　대상이　아님）(TEMP-AMT1)
                * */
                if (workAmt0 > workIamt) {
                    tempAmt1 = workAmt0 - workIamt;
                } else {
                    tempAmt1 = 0;
                }
                /*
                *---현재　미수금과　당월　매출액　계산 (TEMP-AMT2)
                *   →포인트　차감　대상　금액
                *     설치월　매출　잔액은　제외시킴
                *    TEMP-AMT3:포인트　차감하고　남은　잔액
                * */
                tempAmt2 = pre1ThmUcBlam + workAmt16 - tempAmt1;
                if (workAmt71 >= tempAmt2) {
                    workAmt74 = tempAmt2;
                    tempAmt3 = 0;
                } else {
                    workAmt74 = workAmt71;
                    tempAmt3 = tempAmt2 - workAmt71;
                }
                workAmt75 = workAmt71 - workAmt74;
            }
            dvo.setPBtdAmt(workAmt71); // DISP-AM71
            dvo.setPSlAmt(workAmt74); // DISP-AM74
            dvo.setRpRfndAmt(workAmt75); // DISP-AM75

            dvo.setPrmBlamAmt(workAmt21); // DISP-AM21
            dvo.setPrpdBlamAmt(workAmt31); // DISP-AM31

            /*      ---등록환불Check.
            *    14일이내　취소는　등록비　전액환불
            *    14일　경과　취소는　등록비환불　일할계산
            *  　　（단，취소유형 22,77은　등록비　전액환불） )는　１４內　소모품비　없음（택배배송）
            * */
            if (isProdGrd || List.of("22", "77").contains(canTpCd) || "3".equals(borExmptDvCd)) {
                workAmt43 = rentalRgstCost - dscAmt;
            } else if (List.of("1", "4").contains(borExmptDvCd)
                || rentalRgstCost == 0 || rentalTn >= 12 && canRqDt >= dispCont) {
                workAmt43 = 0;
            } else {
                workAmt43 = (rentalRgstCost - dscAmt) * (365 - canSlDt) / 365;
            }
            /*
            *    설치　익월14일내　취소는　등록비　환불
            *    등록비　차액만큼　선수금액에　포함하여　계산
            *
            * */
            if (canRqYm > slRcogYm && "22".equals(sellTpDtlCd)) {
                workAmt31 = workAmt31 + workAmt43;
            }
            chekTamt = workAmt43;
            chekNamt = (int)(chekTamt / 1.1 + 0.9999);
            chekVamt = chekTamt - chekNamt;
            workAv43 = chekVamt;

            dvo.setRgstCostRtrnAmt(chekNamt); // DISP-AM43
            dvo.setRgstCostRtrnVat(chekVamt); // DISP-AV43

            /*
            *---등록비보정Check.
            *   설치당월 14일　경과　취소는　등록비　환불　없음
            *   설치당월　취소는　등록비가　선수금에　포함되어　제외　처리
            *
            * */

            if (canRqYm == slRcogYm && workAmt43 <= 0 && (rentalRgstCost - dscAmt) > 0) {
                workAmt31 = workAmt31 - (rentalRgstCost - dscAmt);
            }
            workAmt36 = workAmt21 + workAmt31;
            dvo.setPrpdBlamAmt(workAmt31); // DISP-AM31

            /*
             *---연체가산금　조정Check.
            *
            * */
            if (thmCtrDlqAddAmt < 0 || (thmCtrDlqAddAmt > 0 && thmCtrDlqAddAmt > btdDlqAddAmt)) {
                throw new BizException("가산금 조정 금액을 확인하십시오!");
            }
            /*
             *---가산금　입금
             *  （가산금　기초금－조정금액）한도
             *   연체가산금　입금액이　없으면，
             *     선수금에서　연체가산금　입금　처리
             * */
            if (workAmt81 > 0 && workAmt36 > 0) {
                if (thmDlqAddDpSumAmt <= 0) {
                    workAmt83 = workAmt81 - workAmt85;
                    workAmt36 = workAmt36 - workAmt83;
                } else {
                    workAmt83 = saveAmt83;
                    workAmt36 = workAmt36 - saveAmt83;
                }
            } else {
                workAmt83 = 0;
            }

            dvo.setAdamtDpAmt(workAmt83); // DISP-AM83
            dvo.setTotPrpdAmt(workAmt36); // DISP-AM36
            /*
             * ---가산금　기말＝기초－입금－공제
             */
            workAmt84 = workAmt81 - workAmt83 - workAmt85;
            dvo.setAdamtEotAmt(workAmt84); // DISP-AM84

            workAmt31 = workAmt31 - workAmt83;

            /*    *---매출입금／입금누계／미수총액Check.
            *
            * */
            if (workAmt16 > 0) {
                if (workAmt21 > workAmt16) {
                    workAmt41 = workAmt21 - workAmt16;
                    workAmt42 = workAmt31;
                    workAmt38 = workAmt16;
                } else if (workAmt31 > 0) {
                    workAmt42 = (workAmt21 + workAmt31) - (workAmt42 - workAmt16) - workAmt83;
                    workAmt41 = 0;
                    if (workAmt41 > 0) {
                        workAmt38 = workAmt16;
                    } else {
                        workAmt38 = workAmt16 + workAmt2;
                        workAmt42 = 0;
                    }
                } else {
                    workAmt41 = 0;
                    workAmt42 = 0;
                    workAmt38 = workAmt21;
                }
            } else {
                workAmt41 = workAmt21;
                workAmt42 = workAmt31;
                workAmt38 = 0;
            }
            workAmt38 = workAmt38 + workAmt74;
            /*
            *---미수금액에　연체가산금　포함
            * */
            workAmt39 = pre1SlDpAggAmt + workAmt38;
            workMamt = workAmt17 - workAmt39 - workAmt84 - tempAmt3;
            /*

            *---금융리스는　미수총액　별도계산
            *   금융리스는　당월　리스료　청구금액　발생　없음（위약금발생
            *   가산조정금액은　반영（취소조정금액은　제외）
            *    14일內취소건은　전월청구액을　취소조정금액으로　생성
            * */
            if ("22".equals(sellTpDtlCd)) {
                workAmt41 = 0;
                workAmt42 = 0;
                workAmt38 = 0;
                workAmt39 = pre1SlDpAggAmt + workAmt38;
                workMamt = pre1ThmUcBlam - workAmt85;
            }
            dvo.setSlDpAmt(workAmt38); // DISP-AM38
            dvo.setSlDpAggAmt(workAmt39); // DISP-AM39
            dvo.setUcAmt(workMamt); // DISP-MAMT
            dvo.setPrmRfndAmt(workAmt41); // DISP-AM41
            dvo.setPrpdRfndAmt(workAmt42); // DISP-AM42

            /*
            *---면책유무　유형　４는　위약금　입력이므로　반드시　입력해야
            * */
            if ("4".equals(borExmptDvCd) && borAmtParam <= 0) {
                throw new BizException("위약금을 입력하십시오!");
            }
            /*
            *---위약금은（－）일　수　없음．필요시　매출조정으로　처리
            * */
            if (borAmtParam < 0) {
                throw new BizException("위약금은（－）불가! 필요시 취소조정 처리!");
            }
            /*
             *---위약금　계산
             위약금 서비스 호출함(IN/OUT 동일)
            *    441잔여렌탈료 442등록비할인 443할인금액
            *    444소모품비   445재약정     446포인트
            *    447철거비　   448여유　     449여유
             TODO: LC32C53 추가 예정
             * */

            /*
            *---재약정　위약금　계산
            *    2016.3.1이후　접수건은LC32C53에서　위약금에　포함됨
            *   재약정기간　경과건은　위약금　계산　제외
            * */

            if (cntrRcpFshDt < 20160301) {
                if (stplTn > 0 && "Y".equals(dispCdyn)) {
                    int stplDscAmt = mapper.selectStplDiscountAmt(cntrNo, cntrSn, stplTn); // LINK-TRMT

                    if (stplDscAmt > 0) {
                        throw new BizException("실적자료가 확정되지 않았습니다!");
                    } else {
                        workAmt44 += stplDscAmt;
                        workA445 = stplDscAmt;
                    }
                }
            }

            /*
            *---재약정　위약금　계산
            *   재약정　약정기간　종료건은　위약금　제외
            *
            *
            * */
            if (workA445 > 0 && !"Y".equals(dispCdyn)) {
                workAmt44 = workAmt44 - workA445;
                workA445 = 0;
                workA545 = 0;
            } else if (workA445 > 0 && stplTn > 0 && rentalTn >= 56 && "19".equals(canTpCd) && "Y".equals(dispCdyn)) { //  * 57차월이상　재렌탈에　의한　재약정　취소건은　위약금　제외
                workAmt44 = workAmt44 - workA445;
                workA445 = 0;
            }
            /*
            *---구몬，더오름　포인트　사용내역　조회．
            //30	구몬제휴
            //33	더오름제휴-렌탈
            //36	웰스팜구몬
            //37	EDU 2자제휴(무상)
            * */
            if (List.of("30", "33", "36", "37").contains(alncmpCd)) {
                String dpTpCd = "";
                switch (alncmpCd) {
                    case "30" -> dpTpCd = "0602";
                    case "33" -> dpTpCd = "0603";
                    case "36" -> dpTpCd = "0702";
                    case "37" -> dpTpCd = "0802";
                }
                int usePAmt = mapper.selecdUsedPointAmt(cntrNo, cntrSn, dpTpCd);
                /*
                 *    사용금액　존재　여부　체크
                * */
                if (usePAmt > 0) {
                    dvo.setPUseAmt(usePAmt); // 사용금액만 사용
                }
            }

            if (cntrRcpFshDt < 20160301) {
                if (List.of("2", "3", "5").contains(borExmptDvCd) || isUseDt) {
                    if (List.of("30", "33", "37", "45").contains(alncmpCd)) {
                        workA446 = workAmt44;
                    } else {
                        workAmt44 = 0;
                    }
                    workA441 = 0;
                    workA442 = 0;
                    workA443 = 0;
                    workA445 = 0;
                } else if (List.of("1", "6").contains(borExmptDvCd)) { // 위약금　적용
                    /*
                    *    위약금50%
                    *    포인트사용액은50%적용　제외
                    * */
                    if ("6".equals(borExmptDvCd)) {
                        workA441 /= 2;
                        workA442 /= 2;
                        workA443 /= 2;
                        workA445 /= 2;
                        workA446 /= 2;
                        workAmt44 /= 2;
                    }
                    dvo.setRentalResBorAmt(workA441); // DISP-A441
                    dvo.setRentalRgstCostBorAmt(workA442); // DISP-A442
                    dvo.setDscCsBorAmt(workA443); // DISP-A443
                    dvo.setRstlBorAmt(workA445); // DISP-A4445
                    dvo.setPBorAmt(workA446); // DISP-A446
                    dvo.setBorAmt(workAmt44); // DISP-AMT44
                }
            }

            /*
            *---위약금면제나，１５일이내，의무기간이내의　경우　위약금없음
            *-  구몬，더오름포인트는　위약금　발생
            *-  포인트몰　사용액은　　위약금　발생
            *gs.p
            *    CHEK-A441 총　잔액의 10%
            *         A442 의무잔액의 10%
            *         A443 의무잔액의 30%
            *         A445 의무잔액의 50%
                  *    ( K멤버스주문은7일　이내) 2022.06.29~
            * */
            if (cntrRcpFshDt >= 20160301) {
                /*    위약금　면제
                 *    ( K멤버스주문은7일　이내) 2022.06.29~
                 */
                if (List.of("2", "3", "5").contains(borExmptDvCd) || isProdGrd) {
                    if (List.of("30", "33", "37", "45").contains(alncmpCd)) {
                        workA446 = workAmt44;
                    } else {
                        workAmt44 = 0;
                    }
                    workA441 = 0;
                    workA442 = 0;
                    workA443 = 0;
                    workA444 = 0;
                    workA445 = 0;
                } else if (List.of("1", "6").contains(borExmptDvCd)) { // 위약금　적용
                    /*
                    *    위약금50%
                    *    포인트사용액은50%적용　제외
                    * */
                    if ("6".equals(borExmptDvCd)) {
                        workA441 /= 2;
                        workA442 /= 2;
                        workA443 /= 2;
                        workA445 /= 2;
                        workA446 /= 2;
                        workAmt44 /= 2;
                    }
                    dvo.setRentalResBorAmt(workA441); // DISP-A441
                    dvo.setRentalRgstCostBorAmt(workA442); // DISP-A442
                    dvo.setDscCsBorAmt(workA443); // DISP-A443
                    dvo.setCsmbCsBorAmt(workA444); // DISP-A444
                    dvo.setRstlBorAmt(workA445); // DISP-A4445
                    dvo.setPBorAmt(workA446); // DISP-A446
                    dvo.setBorAmt(workAmt44); // DISP-AMT44
                }
            }
            if (isProdGrd) {
                workA541 = 0;
                workA542 = 0;
                workA543 = 0;
                workA545 = 0;
                workA546 = 0;
            }
            // 소모품비 - 면제
            if ("2".equals(cnmsExmptDvCd)) {
                workA444 = 0;
            } else if ("6".equals(cnmsExmptDvCd)) { // 소모품비－50%
                // 원단위 절사
                workA444 = (workA444 / 2) / 10 * 10;
            }
            // 위약금　임의등록이　아닐때만　계산금액　적용
            if (!"4".equals(borExmptDvCd)) {
                dvo.setBorAmt(workAmt44); // DISP-AM44
            }
            if (!"4".equals(cnmsExmptDvCd)) {
                dvo.setCsmbCsBorAmt(workA444); // DISP-A444
            }
            // 철거비
            if (StringUtils.isEmpty(reqdExmptDvCd) || "2".equals(reqdExmptDvCd)) {
                workA447 = 0;
            } else if ("6".equals(reqdExmptDvCd)) {
                // 원단위 절사
                workA447 = (workA447 / 2) / 10 * 10;
            }

            if (!"4".equals(reqdExmptDvCd)) {
                dvo.setReqdCsBorAmt(workA447); // DISP-A447
            }
            // 위약금　세부항목　안분（임의　금액　등록시）
            if (cntrRcpFshDt >= 20160301) {
                if ("4".equals(borExmptDvCd)) {
                    // FIELD-CHK2-RTN.
                    int tempRat1 = 0;
                    tempAmt2 = 0;
                    int borAmtSum = dvo.getBorAmt();
                    if (workA441 > 0) {
                        tempRat1 = workA441 / saveAm44 * 1;// TEMP-RAT1

                        tempAmt1 = borAmtSum * tempRat1;
                        workA441 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    if (workA442 > 0) {
                        tempRat1 = workA442 / saveAm44 * 1;
                        tempAmt1 = borAmtSum * tempRat1;
                        workA442 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    if (workA443 > 0) {
                        tempRat1 = workA443 / saveAm44 * 1;
                        tempAmt1 = borAmtSum * tempRat1;
                        workA443 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    if (workA444 > 0) {
                        tempRat1 = workA444 / saveAm44 * 1;
                        tempAmt1 = borAmtSum * tempRat1;
                        workA444 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    if (workA445 > 0) {
                        tempRat1 = workA445 / saveAm44 * 1;
                        tempAmt1 = borAmtSum
                            * tempRat1;
                        workA445 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    if (workA446 > 0) {
                        tempRat1 = workA446 / saveAm44 * 1;
                        tempAmt1 = borAmtSum * tempRat1;
                        workA446 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    if (workA447 > 0) {
                        tempRat1 = workA447 / saveAm44 * 1;
                        tempAmt1 = borAmtSum * tempRat1;
                        workA447 = tempAmt1;
                        tempAmt2 = tempAmt2 + tempAmt1;
                    }

                    /*

                    *---안분하고　남은　자투리가　있으면　세부항목중
                    *   앞부분에　금액이　있는　곳에　추가
                    * */
                    tempAmt3 = borAmtSum - tempAmt2;

                    if (tempAmt3 > 0) {
                        if (workA441 > 0) {
                            workA441 += tempAmt3;
                        } else if (workA442 > 0) {
                            workA442 += tempAmt3;
                        } else if (workA443 > 0) {
                            workA443 += tempAmt3;
                        } else if (workA444 > 0) {
                            workA444 += tempAmt3;
                        } else if (workA445 > 0) {
                            workA445 += tempAmt3;
                        } else if (workA446 > 0) {
                            workA446 += tempAmt3;
                        } else if (workA447 > 0) {
                            workA447 += tempAmt3;
                        }
                    }
                }
                /*
                  *---환불총액Check.
                  *   입금누계에　포인트선수금이　포함되므로
                  *   포인트대체금액은　제외시킴
                  *   선수총액(AM36)에　가산입금(AM83)은　제외되어있음
                  *   가산금공제　추가반영
                  *
                  *
                  * */
                int totPrpdAmt = dvo.getTotPrpdAmt(); // DISP-AM36
                int adamtDpAmt = dvo.getAdamtDpAmt(); // DISP-AM83
                int adamtDdctam = dvo.getAdamtDdctam(); // DISP-AM85
                int borAmt = dvo.getBorAmt(); // DISP-AM44
                int csmbCsBorAmt = dvo.getCsmbCsBorAmt(); // DISP-A444
                int reqdCsBorAmt = dvo.getReqdCsBorAmt(); // DISP-A447
                int lsRntf = dvo.getLsRntf(); // DISP-AM46

                int sumRes = totPrpdAmt + adamtDpAmt + adamtDdctam
                    - borAmt - csmbCsBorAmt - reqdCsBorAmt - lsRntf
                    - pre1ThmUcBlam;

                dvo.setTotRfndAmt(sumRes - workAmt16 + workAmt74);

                /*
                  *---금융리스는　환불총액에　취소조정금액을　추가　반영
                  *   금융리스는　미수총액에　당월　선수대체　반영안되므로　
                  *   　　환불총액에　연체가산금　별도로　반영
                 */
                if ("22".equals(sellTpDtlCd)) {
                    dvo.setTotRfndAmt(sumRes + workAmt74 + workAc15);
                    if (cntrRcpFshDt >= 20211201) {
                        int sumTotRfndAmt = dvo.getTotRfndAmt();
                        dvo.setTotRfndAmt(sumTotRfndAmt - workMcm2 - workSam2);
                    }
                }
            }
            if (workAc15 > 0 && workAmt17 < 0) {
                throw new BizException("취소조정 금액을 확인하십시오!");
            }
            /*
             *---취소조정이　있는데，가산금조정이　안된　경우
             *   가산금조정금액까지　취소조정에　모두　입력한　경우
             *   환불총액≤０（미수＝０）인　경우만　체크
             *
             * */

            int totRfndAmt = dvo.getTotRfndAmt(); // DISP-AM45
            /*
                  *---취소조정이　있는데，가산금조정이　안된　경우
                  *   가산금조정금액까지　취소조정에　모두　입력한　경우
                  *   환불총액≤０（미수＝０）인　경우만　체크
            * */
            boolean isCancelCtr = (totRfndAmt >= 0 && canCtrAmt > 0 && btdDlqAddAmt > 0 && workAmt84 > 0)
                || (totRfndAmt < 0 && canCtrAmt > 0 && btdDlqAddAmt > 0 && workAmt84 > 0
                    && workAmt84 > (totRfndAmt * -1));
            if (isCancelCtr) {
                throw new BizException("가산금은 가산금조정으로 처리해야 합니다!");
            }
            returnDvo = dvo;
        }
        return returnDvo;
    }

    /*
    * FIELD-AM116-RTN 계산로직
    * */
    public WctbRentalCancelDvo getRentalBase(WctbRentalCancelDvo dvo) {
        WctbRentalCancelDvo dscAmtDvo = new WctbRentalCancelDvo();

        int workAmt15 = 0;
        int workSday = dvo.getWorkSday();
        int lastCanRqDay = dvo.getLastCanRqDay();
        int rentalAmt = dvo.getRentalAmtParam();
        int pre1SlAggAmt = dvo.getPre1SlAggAmtParam();

        String cntrNo = dvo.getCntrNo();
        int cntrSn = dvo.getCntrSn();
        String orgCanDt = dvo.getOrgCanDt();

        if (workSday < 30 && !(workSday == lastCanRqDay)) {
            if (rentalAmt > 0) {
                workAmt15 = ((rentalAmt / 30) * workSday) * 10;
            }
            if (rentalAmt - workAmt15 > 0) {
                workAmt15 = rentalAmt - workAmt15;
            }
        }
        if (workAmt15 > pre1SlAggAmt) {
            workAmt15 = pre1SlAggAmt;
        }
        dscAmtDvo = mapper.selectDscAmtAgrgPrmDscAmt(cntrNo, cntrSn, orgCanDt);
        dscAmtDvo.setWorkAmt15(workAmt15);
        return dscAmtDvo;
    }
}
