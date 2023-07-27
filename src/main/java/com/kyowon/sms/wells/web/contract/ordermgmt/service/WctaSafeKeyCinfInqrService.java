package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.nice.service.NiceSafekeyService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSafeKeyCinfInqrDto.SearchCstInfoRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSafeKeyCinfInqrDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSafeKeyCinfInqrDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaSafeKeyCinfInqrMapper;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaSafeKeyCinfInqrService {
    private final WctaSafeKeyCinfInqrMapper mapper;
    // private final WcteSellLimitObjectService wcteSellLimitObjectService;
    private final WctaBlackContractService wctaBlackContractService;
    private final NiceSafekeyService niceSafekeyService;
    // private final ZctaCinfInqrService zctaCinfInqrService;

    public SearchRes getSafeKeyCinFInqr(SearchReq req) throws Exception {
        String sfkVal = "";
        String fnlIsDtm = "";
        String workDt = "";

        // 처리내용1.
        // 신용정보 조회 여부, Y: 세이프키 + 신용정보 조회, N: 세이프키만 조회
        if (req.isCinfInqr() && StringUtils.isEmpty(req.cntrNo()))
            return SearchRes.builder().isSuccess(false).message("I00001").build();
        if (StringUtils.isEmpty(req.sellTpCd()))
            return SearchRes.builder().isSuccess(false).message("I00361").build();
        if (StringUtils.isEmpty(req.cstNo()))
            return SearchRes.builder().isSuccess(false).message("I00115").build();
        if (StringUtils.isEmpty(req.cntrCnfmDtm()))
            return SearchRes.builder().isSuccess(false).message("I00350").build();
        if (StringUtils.isEmpty(req.copnDvCd()))
            return SearchRes.builder().isSuccess(false).message("I00362").build();
        if (StringUtils.isEmpty(req.cstKnm()))
            return SearchRes.builder().isSuccess(false).message("I00145").build();
        if (req.copnDvCd().equals("1") && StringUtils.isEmpty(req.sexDvCd()))
            return SearchRes.builder().isSuccess(false).message("I00363").build();
        if (req.copnDvCd().equals("1")
            && StringUtils.isAnyEmpty(req.cralLocaraTno(), req.mexnoEncr(), req.cralIdvTno()))
            return SearchRes.builder().isSuccess(false).message("I00147").build();
        if (StringUtils.isEmpty(req.basePdCd()))
            return SearchRes.builder().isSuccess(false).message("I00198").build();
        if (StringUtils.isEmpty(req.prtnrNo()))
            return SearchRes.builder().isSuccess(false).message("I00113").build();
        if (StringUtils.isEmpty(req.bizSpptPrtnrNo()))
            return SearchRes.builder().isSuccess(false).message("I00349").build();

        String cntrCnfmDt = req.cntrCnfmDtm().substring(0, 8);

        // 처리내용2.
        SearchCstInfoRes cstInfo = mapper.selectCstInfo(req.cstNo());
        sfkVal = cstInfo.sfkVal();
        fnlIsDtm = cstInfo.fnlIsDtm();
        // 현재시간보다 최종인증시간(+1개월)이 과거이거나, 휴대전화번호가 다르면 세이프키와 인증완료일시 초기화
        if (Duration.between(LocalDate.now(), LocalDate.parse(fnlIsDtm, DateTimeFormatter.ofPattern("yyyyMMddhhmmss")))
            .isNegative()
            ||
            !(req.cralLocaraTno().equals(cstInfo.cralLocaraTno())
                && req.mexnoEncr().equals(cstInfo.mexnoEncr())
                && req.cralIdvTno().equals(cstInfo.cralIdvTno()))) {
            sfkVal = "";
            fnlIsDtm = "";
        }
        if (!cstInfo.copnDvCd().equals(req.copnDvCd())) {
            return SearchRes.builder().isSuccess(false).message("I00035").build();
        }
        // 개인일 때, 생년월일과 계약확정일자 사이 19년 이상인지 확인
        // 법인일 때, 가입제한여부 검사 서비스 호출 TODO WCTA로 수정하고 서비스 return 확인
        if (req.copnDvCd().equals("1")
            && Period.between(
                LocalDate.parse(req.bryyMmdd(), DateTimeFormatter.ofPattern("yyyyMMdd")),
                LocalDate.parse(cntrCnfmDt, DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).getYears() < 19) {
            return SearchRes.builder().isSuccess(false).message("I00151").build();
        } else if (req.copnDvCd().equals("2")) {
            // && ListUtils.isEmpty(wcteSellLimitObjectService.getCrpJLmOjRgstYnInqr(req.bzrno()))) {
            return SearchRes.builder().isSuccess(false).message("I00146").build();
        }

        // 처리내용3.
        if (req.copnDvCd().equals("1")) {
            workDt = StringUtils.isEmpty(cstInfo.fnlIsDtm()) ? "" : cstInfo.fnlIsDtm().substring(0, 8);
            if (!workDt.equals(cntrCnfmDt) && StringUtils.isEmpty(sfkVal)) {
                // TODO 인터페이스 서비스, 오류코드 확인
                String nsk = niceSafekeyService.getNiceSafekey();
                String errCdFromIf = "";
                if (StringUtils.isNotEmpty(nsk)) {
                    // TODO 고객정보 업데이트 서비스 호출
                } else {
                    return SearchRes.builder().isSuccess(false).message(
                        StringUtil.nvl(
                            Map.ofEntries(
                                Map.entry("P001", "I00846"),
                                Map.entry("P002", "I00847"),
                                Map.entry("P015", "I00848"),
                                Map.entry("P017", "I00849"),
                                Map.entry("S003", "I00850"),
                                Map.entry("S602", "I00851"),
                                Map.entry("S702", "I00852"),
                                Map.entry("S738", "I00853"),
                                Map.entry("Z902", "I00854"),
                                Map.entry("Z998", "I00855"),
                                Map.entry("Z999", "I00856")
                            ).get(errCdFromIf), "I00150"
                        )
                    ).build();
                }
            }
        }

        // 처리내용4.
        if (StringUtils.containsAny(getDebtNonfulfillmentYnS("20", "2", sfkVal), "0094", "0095")) {
            return SearchRes.builder().isSuccess(false).message("I00152").build();
        }

        // 처리내용5. 불필요

        // 처리내용6. 블랙리스트 여부 확인
        if (wctaBlackContractService.isBlacklist(req.cralLocaraTno(), req.mexnoEncr(), req.cralIdvTno())) {
            return SearchRes.builder().isSuccess(false).message("I00870").build();
        }

        // 처리내용7.
        if (!req.isCinfInqr()) {
            // 신용조회여부가 false라면 조회한 인증일시와 세이프키를 반환
            return SearchRes.builder().isSuccess(true).fnlIsDtm(fnlIsDtm).sfkVal(sfkVal).build();
        }
        // TODO 판매상품코드 TO-BE 기준 확인
        if (req.copnDvCd().equals("2") || StringUtils.containsAny(req.basePdCd(), "6412", "6413", "6414")) {
            if (req.pstnDvCd().equals("7")) {
                return SearchRes.builder().isSuccess(false).message("I00341").build();
            } else {
                return SearchRes.builder().isSuccess(true).fnlIsDtm(fnlIsDtm).sfkVal(sfkVal).build();
            }
        } else {
            // TODO 미개발 서비스
            // zctaCinfInqrService.cinfInqr()
            String dfltYn = ""; // 채무불이행여부
            if (StringUtils.containsAny(dfltYn, "1", "3")) {
                // 지점장인 경우 return
                if (req.pstnDvCd().equals("7")) {
                    return SearchRes.builder().isSuccess(false).message("I00341").build();
                } else {
                    return SearchRes.builder().isSuccess(true).fnlIsDtm(fnlIsDtm).sfkVal(sfkVal).build();
                }
            } else if (dfltYn.equals("2")) {
                return SearchRes.builder().isSuccess(false).message("I00342").build();
            } else if (dfltYn.equals("4")) {
                return SearchRes.builder().isSuccess(false).message("I00343").build();
            } else if (dfltYn.equals("5")) {
                return SearchRes.builder().isSuccess(false).message("I00337").build();
            } else if (dfltYn.equals("E")) {
                return SearchRes.builder().isSuccess(false).message("I00338").build();
            } else if (dfltYn.equals("Z")) {
                return SearchRes.builder().isSuccess(false).message("I00339").build();
            } else {
                return SearchRes.builder().isSuccess(false).message("I00342").build();
            }
        }
    }

    public String getDebtNonfulfillmentYnS(String coCd, String dfltMngtDvCd, String sfkVal) {
        return mapper.selectDfltRsonCd(coCd, dfltMngtDvCd, sfkVal);
    }
}
