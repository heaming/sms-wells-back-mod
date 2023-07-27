package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaBlackContractMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaBlackContractService {

    private final WctaBlackContractMapper mapper;

    /**
     * 고객의 휴대전화번호를 기반으로 wells 환경가전 계약 중 블랙리스트를 검사한다.
     * 1. 입력파라미터 유효성 검사
     *   - 전화번호 두번째, 세번째 자리가 공백인 경우 오류처리
     *
     * 2. 입력파라미터의 계약자 휴대전화번호를 가지고 판매제한대상내역 테이블에서 블랙리스트정보를 가져온다.
     *    - 조회 전 입력파라미터의 전화번호 두번째 자리를 암호화 한 후 비교한다. (암호화 방안은 공통 암호화 로직 참조)
     *    - 고객기본 테이블에서 CRAL_LOCARA_TNO, MEXNO_ENCR, CRAL_IDV_TNO 와 입력파라미터가 일치하는 고객을 찾고,
     *      찾은 고객의 고객번호가 계약기본 테이블의 CNTR_CST_NO 일치하는 계약을 찾아,
     *      판매제한대상내역 테이블의 계약번호와 계약기본 테이블의 계약번호가 일치하는 데이터가 있는경우, 해당 테이블의 SELL_LM_RLS_DTM(판매제한해제일시) 가 없는 경우 true 반환
     *
     *   - 계약주소지기본 테이블의 CRAL_LOCARA_TNO (휴대지역전화번호), MEXNO_ENCR (휴대전화국번호암호화), CRAL_IDV_TNO (휴대개별전화번호) 가 입력파라미터와 일치하는 데이터를 조회하여, pk 인 계약주소지ID를 사용하는 계약주소지관계 테이블의 데이터를 조회한다.
     *     해당 데이터의 계약번호, 상세계약번호가 판매제한대상내역 테이블의 계약번호, 계약일련번호와 일치하는 데이터가 있는경우, 해당 테이블의 SELL_LM_RLS_DTM(판매제한해제일시) 가 없는 경우 true 반환
     *
     * 3. 위에 해당하지 않는 경우 false로 프로그램을 종료한다.
     *
     * @param cralLocaraTno 전화번호1
     * @param mexnoEncr (암호화된) 전화번호2
     * @param cralIdvTno 전화번호3
     * @return 블랙리스트 여부
     */
    public boolean isBlacklist(String cralLocaraTno, String mexnoEncr, String cralIdvTno) {
        BizAssert.notNull(mexnoEncr, "MSG_ALT_TEL_NO_IN");
        BizAssert.notNull(cralIdvTno, "MSG_ALT_TEL_NO_IN");
        return "Y".equals(mapper.isBlacklist(cralLocaraTno, mexnoEncr, cralIdvTno));
    }
}
