package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.closing.mileage.dvo.argument.ZdceMileageStatusByContractDvo;
import com.kyowon.sms.common.web.closing.mileage.mapper.ZdceSmartMileageMapper;
import com.kyowon.sms.common.web.closing.mileage.service.ZdceSmartMileageService;
import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbCancelBaseConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCancelBaseDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCancelBaseMapper;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractWellsDetailHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCancelBaseService {

    private final WctbCancelBaseMapper mapper;
    private final WctbCancelBaseConverter converter;
    private final WctzHistoryService historyService;

    private final ZdceSmartMileageService mileageService;
    private final ZdceSmartMileageMapper mileageSMapper;

    public List<SearchRes> getCntrBases(SearchReq dto) {
        return mapper.selectCntrBases(dto);
    }

    public FindSubDetailRes getCancelBases(SearchReq dto) {
        if ("2".equals(dto.sellTpCd())) {
            return getRentalCancelBases(dto);
        } else if ("3".equals(dto.sellTpCd())) {
            return converter.mapCancelBaseDvoToFindSubDetailRes(mapper.selectMembershipCancelBase(dto));
        } else if ("6".equals(dto.sellTpCd())) {
            return converter.mapCancelBaseDvoToFindSubDetailRes(mapper.selectMembershipCancelBase(dto));
        }

        BizAssert.notEmpty(null, "MSG_ALT_NO_INFO_SRCH");
        return null;
    }

    private FindSubDetailRes getRentalCancelBases(SearchReq dto) {
        WctbCancelBaseDvo baseDvo = mapper.selectCancelBase(dto);
        BizAssert.isTrue((DateUtil.getDays(dto.reqDt(), baseDvo.getExnDt()) >= 0), "요청일자가 만료일자 이후입니다.");

        baseDvo.setGrade(
            (("45".equals(baseDvo.getAlncmpCd()) && baseDvo.getUseDays() <= 7)
                || (!"45".equals(baseDvo.getAlncmpCd()) && baseDvo.getUseDays() <= 14)) ? "E" : "R"
        );

        WctbCancelBaseDvo cancelDvo = mapper.selectRentalCancelBase(dto);
        if (!ObjectUtils.isEmpty(cancelDvo))
            cancelDvo = converter.convertCancelBaseDvo(cancelDvo, baseDvo);

        return converter.mapCancelBaseDvoToFindSubDetailRes(cancelDvo);
    }

    @Transactional
    public int saveCancel(List<SaveReq> dtos) {
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String now = DateUtil.todayNnow();
        int rtn = 0;

        for (SaveReq dto : dtos) {
            WctbCancelBaseDvo dvo = converter.mapSaveReqToCancelBaseDvo(dto);

            dvo.setIchrOgTpCd(session.getOgTpCd());
            dvo.setIchrPrtnrNo(session.getEmployeeIDNumber());

            //1. 계약해지처리내역(TB_SSCT_CNTR_RSG_PROCS_IZ) insert
            rtn += mapper.insertContractResign(dvo);

            //2. 계약해지처리내역이력 insert
            mapper.insertContractResignHistory(dvo);

            //5-1) 계약상세 UPDATE
            mapper.updateContraceDetail(dvo.getCntrNo(), dvo.getCntrSn());

            //3. 철거 및 이후 처리를 위한 기본 정보 조회
            String isUpdateWellsDetail = mapper.selectIsUpdateWellsDetail(dvo.getCntrNo(), dvo.getCntrSn());
            if ("Y".equals(isUpdateWellsDetail) || "59".equals(dvo.getCntrStatChRsonCd())) { // 취소유형 : 59.분실
                //5-2) 계약WELLS상세 UPDATE
                mapper.updateContraceWellsDetail(dvo.getCntrNo(), dvo.getCntrSn());

                // 6. 계약상세, 계약wells상세 이력 생성
                historyService.createContractWellsDetailChangeHistory(
                    WctzContractWellsDetailHistDvo.builder()
                        .cntrNo(dvo.getCntrNo())
                        .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                        .histStrtDtm(now)
                        .build()
                );
            }

            // 6. 계약상세, 계약wells상세 이력 생성
            historyService.createContractDetailStatChangeHistory(
                WctzCntrDtlStatChangeHistDvo.builder()
                    .cntrNo(dvo.getCntrNo())
                    .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                    .cntrDtlStatCd("301") // 계약상세상태코드 : 301.고객요청해약
                    .cntrDtlStatChRsonCd(dvo.getCntrStatChRsonCd())
                    .histStrtDtm(now)
                    .build()
            );
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(dvo.getCntrNo())
                    .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                    .histStrtDtm(now)
                    .build()
            );

            //7. 적립금 사용중지 모듈 호출(Z-CL-S-0005)
            ZdceMileageStatusByContractDvo mileageStatusByContractDvo = new ZdceMileageStatusByContractDvo();
            mileageStatusByContractDvo.setCntrNo(dvo.getCntrNo());
            mileageStatusByContractDvo.setCntrSn(Integer.parseInt(dvo.getCntrSn()));
            mileageStatusByContractDvo.setMlgStatCd("STOP"); // STOP.사용중지
            mileageStatusByContractDvo.setMlgStatRsonCd("CNCL_CNTR"); // CNCL_CNTR.청약철회

            try {
                if (!ObjectUtils.isEmpty(
                    mileageSMapper
                        .selectMileageStatusByContract(mileageStatusByContractDvo)
                )) {
                    mileageService.editMileageStatusByContract(mileageStatusByContractDvo);
                }
            } catch (Exception ex) {

            }
            // TODO : 8. 택배제품인 경우, 반품입고등록 (추후 서비스에서 모듈 생성 후 호출하는 것으로)
            // TODO : 9. 매출식적자료 update - 이것도 실시간이 필요없다면 우선 아무것도 하지 않아도 됨
        }

        //throw new NullPointerException();
        return rtn;
    }

    public FindDetailRes getCancelInfo(SearchReq dto) {
        return mapper.selectCancelInfo(dto);
    }

}
