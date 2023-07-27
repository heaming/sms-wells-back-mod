package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchRes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiCustomerConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiCustomerMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiCustomerService {

    private final WctiCustomerMapper mapper;
    private final WctiCustomerConverter converter;

    public List<SearchRes> getCustomers(SearchReq req) {
        WctiCustomerDvo dvo = converter.mapSearchReqToWctiCustomerDvo(req);

        // 파라미터 체크 - 파라미터값이 NULL 인 경우, 조회 하지 않음
        if (StringUtils.isAllEmpty(
            dvo.getCstNm(), dvo.getCstNo(), dvo.getCralLocaraTno(), dvo.getMexno(), dvo.getCralIdvTno(),
            dvo.getLocaraTno(), dvo.getExno(), dvo.getIdvTno()
        )) {
            throw new BizException("검색어를 입력해주세요.");
        }

        // 파라미터 체크 - 연락처 검색어가 2개 이상 아닐 경우, 조회 하지 않음
        int emptyCnt = 0;
        if (StringUtils.isEmpty(dvo.getCralLocaraTno())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(dvo.getMexno())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(dvo.getCralIdvTno())) {
            ++emptyCnt;
        }

        if (emptyCnt == 2) {
            throw new BizException("휴대전화번호를 2개 이상 입력해주세요.");
        }

        emptyCnt = 0;
        if (StringUtils.isEmpty(dvo.getLocaraTno())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(dvo.getExno())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(dvo.getIdvTno())) {
            ++emptyCnt;
        }

        if (emptyCnt == 2) {
            throw new BizException("전화번호를 2개 이상 입력해주세요.");
        }

        //고객 검색
        List<WctiCustomerDvo> resList = new ArrayList<>();

        resList.addAll(mapper.selectCustomers(dvo));
        resList.addAll(mapper.selectProspactCustomers(dvo));

        return converter.mapAllWctiCustomerDvoToSearchRes(resList);
    }
}
