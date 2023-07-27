package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchCntrtInfoReq;

import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzContractNumberService;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.common.service.WctzPartnerService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractRegConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep1Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep1Service {
    private final WctaContractRegStep1Mapper mapper;
    private final WctaContractRegConverter converter;

    private final WctaContractRegService regService;
    private final WctaContractService contractService;
    private final WctzPartnerService prtnrService;
    private final WctzContractNumberService cntrNoService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(WctaContractDto.SearchStep1Req dto) {
        WctaContractRegStep1Dvo step1Dvo = new WctaContractRegStep1Dvo();
        String cstNo = dto.cstNo();
        String cntrNo = dto.cntrNo();
        /**
         * cstNo: 신규계약, 고객번호 조회
         * cstNo cntrNo: 기존계약에서 새로운 계약유형이나 신규 고객으로 변경하는 경우
         * cntrNo: 기존 계약 조회
         */
        boolean updateCntr = StringUtils.isNotEmpty(cstNo) && StringUtils.isNotEmpty(cntrNo);

        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        String cntrTpCd = StringUtils.defaultString(dto.cntrTpCd(), ObjectUtils.isEmpty(bas) ? "" : bas.getCntrTpCd());
        if (StringUtils.isEmpty(cstNo) && ObjectUtils.isNotEmpty(bas)) {
            cstNo = bas.getCntrCstNo();
        }

        // 견적서 존재여부 확인
        step1Dvo.setPextCntrs(mapper.selectPextCntr(cstNo, cntrNo));

        // 계약자 연체 여부 확인
        List<Long> dlqAmt = mapper.selectCntrtDlqAmt(List.of(cstNo));
        BizAssert.empty(
            dlqAmt, "MSG_ALT_DLQ_CST_AMT",
            new String[] {NumberFormat.getCurrencyInstance().format(dlqAmt.stream().reduce(0L, Long::sum))}
        );

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String loginPrtnrno = session.getEmployeeIDNumber();
        if (cntrTpCd.equals(CtContractConst.CNTR_TP_CD_ENSM)) {
            // 임직원계약인 경우 파트너 관련 체크 로직 생략
            step1Dvo.setPrtnr(
                WctaContractPrtnrRelDvo.builder()
                    .prtnrKnm(session.getUserName())
                    .prtnrNo(loginPrtnrno)
                    .prrBizRgrYn("N")
                    .ogTpCd("HR1")
                    .ogId(session.getOgId())
                    .build()
            );
        } else {
            // 3-3. 파트너 정보 조회 입력
            // 3-3-1. 로그인 사용자의 파트너 정보 조회
            WctzPartnerDto.FindPrtnrRes loginPrtnrInfo = prtnrService
                .selectPrtnrInfo(loginPrtnrno, session.getOgTpCd());

            // 3-3-2. 로그인 사용자 = 파트너, 사전업무 등록기간 체크
            BizAssert.notNull(loginPrtnrInfo, "존재하지 않는 파트너입니다.");
            WctaContractPrtnrRelDvo prtnrInfo = converter.mapPrtnrDtoToWctaContractPrtnrRelDvo(loginPrtnrInfo);
            if ("7".equals(prtnrInfo.getPstnDvCd())) {
                // 로그인한 파트너가 지국장인 경우
                step1Dvo.setPrtnr7(prtnrInfo);
                step1Dvo.setPrtnr(
                    converter.mapPrtnrDtoToWctaContractPrtnrRelDvo(
                        prtnrService.selectPrtnrInfo(dto.cntrPrtnrNo(), session.getOgTpCd())
                    )
                );
            } else {
                step1Dvo.setPrtnr(prtnrInfo);
            }

            if ("Y".equals(prtnrInfo.getPrrBizRgstYn())) {
                // TODO 사전업무등록가능자 체크(지국장이 선택한 파트너 기준)
                // 사전업무등록가능자라면, 사전업무등록기간 조회해서 null이면 계약접수 불가, 존재하면 계약자상세정보 dvo에 저장
                List<WctaContractRegStep1Dvo.PrrBizRgstPtrmDvo> prrBizRgstPtrms = mapper.selectPrrBizRgstPtrm();
                BizAssert.notEmpty(prrBizRgstPtrms, "MSG_ALT_IS_NOT_PRR_BIZ_RGST");
                step1Dvo.setPrrBizRgstPtrms(prrBizRgstPtrms);
            }
            // 3-3-3. 계약자의 파트너 정보 조회
            // String cntrtPrtnrNo = regService.selectCstPrtnrNo(cstNo);
        }

        // 6. 계약자 확인 후 정보 조회(기존 데이터 세팅)
        if (StringUtils.isNotEmpty(cntrNo)) {
            step1Dvo.setBas(bas);
            if (updateCntr) {
                step1Dvo.setBas(
                    WctaContractBasDvo.builder()
                        .cntrNo(cntrNo)
                        .build()
                );
                step1Dvo.setCntrt(mapper.selectCntrtInfoByCstNo(cstNo));
            } else {
                // 기존 데이터 조회인 경우(계약기본으로 확인)
                List<WctaContractPrtnrRelDvo> prtnrRels = regService.selectContractPrtnrRel(cntrNo);
                List<WctaContractCstRelDvo> cstRels = regService.selectContractCstRel(cntrNo);

                /*
                step1Dvo.setPrtnr(
                    prtnrRels.size() == 1 ? prtnrRels.get(0)
                        : prtnrRels.stream().filter((p) -> "010".equals(p.getCntrPrtnrTpCd())).findFirst().get()
                ); // 파트너 2줄 적재되는 경우(지국장), 계약파트너유형코드 010인 건이 파트너
                // 파트너는 delete and insert
                */
                WctaContractCstRelDvo c = cstRels.stream().filter((v) -> "10".equals(v.getCntrCstRelTpCd())).findFirst()
                    .get();
                WctaContractCstRelDvo cntrtInfo = mapper.selectCntrtInfoByCstNo(c.getCstNo());
                step1Dvo.setCntrt(converter.mergeContractCstRelDvo(c, cntrtInfo));
            }
        } else {
            step1Dvo.setCntrt(mapper.selectCntrtInfoByCstNo(cstNo));
            step1Dvo.setBas(new WctaContractBasDvo());
        }
        return WctaContractRegDvo.builder()
            .step1(step1Dvo)
            .build();
    }

    // TODO 메시지 코드 처리
    public boolean isExistCntrtInfo(SearchCntrtInfoReq dto) {
        return mapper.isExistCntrtInfo(dto);
    }

    public String selectPrtnrCstNo(String prtnrNo) {
        return regService.selectEnsmCstNo(prtnrNo);
    }

    @Transactional
    public String saveContractStep1(WctaContractRegStep1Dvo dvo) {
        // 파트너정보 조회(존재여부 판단)
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String loginPrtnrNo = session.getEmployeeIDNumber();
        WctaContractPrtnrRelDvo prtnrInfo = converter
            .mapPrtnrDtoToWctaContractPrtnrRelDvo(prtnrService.selectPrtnrInfo(loginPrtnrNo, session.getOgTpCd()));
        WctaContractPrtnrRelDvo prtnr7Info = null;
        if (ObjectUtils.isNotEmpty(dvo.getPrtnr7())) {
            prtnr7Info = converter.mapPrtnrDtoToWctaContractPrtnrRelDvo(
                prtnrService.selectPrtnrInfo(loginPrtnrNo, session.getOgTpCd())
            );
        }
        BizAssert.notNull(prtnrInfo, "파트너를 확인해주세요.");

        // 고객정보 조회(존재여부 판단)
        WctaContractCstRelDvo cntrtInfo = mapper.selectCntrtInfoByCstNo(dvo.getCntrt().getCstNo());
        BizAssert.notNull(cntrtInfo, "고객 정보를 확인해주세요.");

        // 가망고객ID가 입력되었다면, 유효한지 판단하고 변수에 저장
        if (StringUtils.isNotEmpty(dvo.getPspcCstId())) {
            BizAssert.isTrue(mapper.isValidPspcCstId(dvo.getPspcCstId(), cntrtInfo.getCopnDvCd()), "가망고객ID를 확인해주세요.");
        }

        String now = DateUtil.todayNnow();

        // 계약번호 없으면, 신규 채번
        WctaContractBasDvo basDvo = dvo.getBas();
        boolean isNewCntr = StringUtils.isEmpty(basDvo.getCntrNo());
        String cntrNo = "";
        if (CtContractConst.CNTR_TP_CD_MSH.equals(basDvo.getCntrTpCd()) && StringUtils.isNotEmpty(dvo.getMshCntrNo())) {
            // 멤버십의 경우 무조건 신규 채번 후 원계약과 계약관계 저장
            isNewCntr = true;
            cntrNo = cntrNoService.getContractNumber("").cntrNo();
            mapper.insertCntrRelStep1(
                WctaContractRelDvo.builder()
                    .baseDtlCntrNo(cntrNo)
                    .baseDtlCntrSn(1) //
                    .ojDtlCntrNo(dvo.getMshCntrNo())
                    .ojDtlCntrSn(dvo.getMshCntrSn())
                    .vlStrtDtm(now)
                    .vlEndDtm(CtContractConst.END_DTM)
                    .cntrRelDtlCd("212")
                    .cntrRelTpCd("20")
                    .cntrUnitTpCd("020")
                    .build()
            );
        } else {
            cntrNo = isNewCntr ? cntrNoService.getContractNumber("").cntrNo() : basDvo.getCntrNo();
        }

        if (!isNewCntr) {
            // 기존 계약정보 삭제
            // regService.removeStep1Data(cntrNo); // TODO 필요한지 검토(step1에서는 이력테이블들 update로 처리 가능하기 때문)
            regService.removeStep2Data(cntrNo);
            regService.removeStep3Data(cntrNo);
            regService.removeStep4Data(cntrNo);
        }

        /* 테이블 적재 시작 */
        // 1. 계약기본 생성 또는 수정
        basDvo.setCntrTempSaveDtm(now);
        basDvo.setPrrRcpCntrYn(mapper.selectResrOrdrYn());
        basDvo.setPspcCstId(dvo.getPspcCstId());
        String saleInflowChnlDtlCd = contractService.getSaleInflowChnlDtlCd(dvo.getBas().getCntrTpCd());
        BizAssert.isFalse("9999".equals(saleInflowChnlDtlCd), "유효하지 않은 판매유입채널상세코드입니다.");
        basDvo.setSellInflwChnlDtlCd(saleInflowChnlDtlCd);
        basDvo.setCntrNo(cntrNo);
        basDvo.setCntrCstNo(dvo.getCntrt().getCstNo());
        basDvo.setCopnDvCd(dvo.getCntrt().getCopnDvCd());
        basDvo.setSellPrtnrNo(dvo.getPrtnr().getPrtnrNo());
        basDvo.setSellOgTpCd(dvo.getPrtnr().getOgTpCd());
        basDvo.setCntrNatCd("KR");
        basDvo.setCntrPrgsStatCd(CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP1);
        if (isNewCntr) {
            mapper.insertCntrBasStep1(basDvo);
        } else {
            mapper.updateCntrBasStep1(basDvo);
        }

        // 2. 계약변경이력 생성
        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .histStrtDtm(now)
                .build()
        );

        // 3. 계약파트너관계 생성
        /**
         * 1. 계약파트너유형코드
         *   IF 로그인사용자 OG_TP_CD == E03 이면
         *     계약파트너유형코드 = 060(TM판매자)
         *   ELSE IF 로그인사용자의 직급코드 == 7 (지국장)
         *     *2줄이 들어감
         *     계약파트너유형코드 = 040(대리계약판매자) - 1줄
         *     계약파트너유형코드 = 010(판매자) - 1줄
         *   ELSE
         *     계약파트너유형코드 = 010(판매자) - 1줄
         *
         * 2. 접수사무소코드 - 월조직내역_빌딩코드
         */
        // 해당 계약 건 기존 데이터 처리
        if (!isNewCntr) {
            mapper.updateCntrPrtnrRelStep1(
                WctaContractPrtnrRelDvo.builder()
                    .vlEndDtm(now)
                    .cntrNo(cntrNo)
                    .build()
            );
        }
        String cntrPrtnrTpCd = "010";
        String rcpAofficeCd = "01"; // mapper.selectRcpAofficeCd(loginPrtnrNo);
        if ("E03".equals(prtnrInfo.getOgTpCd())) {
            cntrPrtnrTpCd = "060";
        } else if ("7".equals(prtnrInfo.getPstnDvCd())) {
            // TODO 지국장 정보 저장해야함
            mapper.insertCntrPrtnrRelStep1(
                WctaContractPrtnrRelDvo.builder()
                    .vlStrtDtm(now)
                    .vlEndDtm(CtContractConst.END_DTM)
                    .cntrNo(cntrNo)
                    .ogTpCd(prtnr7Info.getOgTpCd())
                    .prtnrNo(prtnr7Info.getPrtnrNo())
                    .cntrPrtnrTpCd("040")
                    .ogId(prtnr7Info.getOgId())
                    .rcpAoffceCd(rcpAofficeCd)
                    .prrBizRgrYn(prtnr7Info.getPrrBizRgstYn())
                    .build()
            );
        }
        // TODO 제휴파트너 정보 추가하기
        mapper.insertCntrPrtnrRelStep1(
            WctaContractPrtnrRelDvo.builder()
                .vlStrtDtm(now)
                .vlEndDtm(CtContractConst.END_DTM)
                .cntrNo(cntrNo)
                .ogTpCd(prtnrInfo.getOgTpCd())
                .prtnrNo(prtnrInfo.getPrtnrNo())
                .cntrPrtnrTpCd(cntrPrtnrTpCd)
                .ogId(prtnrInfo.getOgId())
                .rcpAoffceCd(rcpAofficeCd)
                .prrBizRgrYn(prtnrInfo.getPrrBizRgstYn())
                .build()
        );

        // 4. 계약고객관계 생성
        // 계약유형 판단, 계약유형이 임직원이면 임직원(010), 계약자가 파트너라면 파트너(020), 모두 아니라면 ""
        if (!isNewCntr) {
            mapper.updateCntrCstRelStep1(
                WctaContractCstRelDvo.builder()
                    .vlEndDtm(now)
                    .dtlCntrNo(cntrNo)
                    .build()
            );
        }
        String insiCstTpCd = "";
        if (basDvo.getCntrTpCd().equals("03")) {
            insiCstTpCd = "030";
        } else if (StringUtils.isNotEmpty(regService.selectCstPrtnrNo(dvo.getCntrt().getCstNo()))) {
            insiCstTpCd = "020";
        }
        // 4-1. 계약자
        mapper.insertCntrCstRelStep1(
            WctaContractCstRelDvo.builder()
                .vlStrtDtm(now)
                .vlEndDtm(CtContractConst.END_DTM)
                .cntrUnitTpCd("020") // 상세단위로 수정
                .cntrNo(cntrNo)
                .dtlCntrNo(cntrNo)
                .dtlCntrSn(1)
                .copnDvCd(cntrtInfo.getCopnDvCd())
                .cstNo(cntrtInfo.getCstNo())
                .cntrCstRelTpCd("10")
                .cntrtRelCd("01") // 계약자는 본인(01)
                .insiCstTpCd(insiCstTpCd)
                .build()
        );
        return cntrNo;
    }
}
