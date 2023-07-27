package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaBogoCustomerCheckDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaBogoCustomerCheckDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaBogoCustomerCheckResultDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctaBogoCustomerCheckMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaBogoCustomerCheckService {

    private final WctaBogoCustomerCheckMapper mapper;

    List<WctaBogoCustomerCheckResultDvo> result;

    @Transactional
    public List<SearchRes> getBogoCustomerCheck(WctaBogoCustomerCheckDvo dvo) {
        //체크1. 직원구매（신채널）는1+1연계　예외　허용（홈카페，살균수기　대상）
        if (20210401 <= Integer.parseInt(dvo.getRcpDt()) && 99991231 <= Integer.parseInt(dvo.getRcpDt())
            && Arrays.asList(new String[] {"4461", "4462", "4463", "4464", "4459"}).contains(dvo.getPdCd())
            && Arrays.asList(new String[] {"1564070", "1525290", "1650501", "1593484", "1703186", "1703187", "1673717"})
                .contains(dvo.getSellPrtnrNo())) {} else if (Arrays
                    .asList(
                        new String[] {"1525290", "1527802", "1533215", "1533218", "1549214", "1550255", "1551337",
                            "1519476", "1554170", "1564070", "1564705", "1568966", "1570408"}
                    ).contains(dvo.getSellPrtnrNo())) {
            throw new BizException("MSG_ALT_CHK_SELLER_CD"); //판매자코드를 확인하세요.
        }

        //체크2. 대상계약정보 비어있는지 체크 OR 대상계약정보와 기준계약정보가 동일여부 체크 후 오류 리턴
        if (StringUtil.isEmpty(dvo.getBaseCntrNo()) || StringUtil.isEmpty(dvo.getOjCntrNo())
            || dvo.getBaseCntrNo().equals(dvo.getOjCntrNo())) {
            throw new BizException("MSG_ALT_CHK_PEXT_CST_CD"); //기존 고객코드를 확인하세요.
        }

        //체크3. 계약정보 확인(회사설치분 조회 후 확인)
        String ojCntrNo = dvo.getOjCntrNo().substring(1, 4);
        if (ojCntrNo.equals("8888")) {
            result = mapper.selectEmployeePurchaseCheck(dvo);
            if (StringUtil.isEmpty(result.get(0).getCntrNo())) {
                throw new BizException("MSG_ALT_CHK_PEXT_CST_CD"); //기존 고객코드를 확인하세요.
            }
        }
        //체크4. 회사설치 외 LC3001L / LC3101L / LC3501L 체크
        else {
            //LC3001L 할부고객　파일
            result = mapper.selectInstallmentCustomers(dvo);
            if (result.size() == 0) {
                //LC3101L 렌탈고객　파일
                result = mapper.selectRentalCustomers(dvo);
                if (result.size() == 0) {
                    //LC3501L 멤버십고객　파일
                    result = mapper.selectMembershipCustomers(dvo);
                    if (result.size() == 0) {
                        throw new BizException("MSG_ALT_CHK_PEXT_CST_CD"); //기존 고객코드를 확인하세요.
                    }
                }
            }
        }

        //체크5. *---제품명Setting ==> 위에 조회시 상품기본테이블과 조인으로 체크5 SKIP
        /*if (StringUtil.isNotEmpty(dvo.getPdCd())) {
            //체크4에서 조회한 상품코드 로 상품명을 조회하여 미존재시 에러 리턴 (단, 상품정보 미존재시 다음 작업)
            List<SearchBogoCustomerCheckRes> result = mapper.selectEmployeePurchaseCheck(dvo);
            if (CollectionUtils.isEmpty(result)) {
                throw new BizException("상품코드를 확인하세요！");
            }
        }*/

        //체크6. *---Ａ０８(4129)예외처리
        String cntrCnfmDtm = result.get(0).getCntrCnfmDtm().substring(1, 6);
        if (dvo.getPdCd().equals("4129") && Integer.parseInt(dvo.getRcpDt()) >= 20161019) {
            //이전코드는 당월 신규접수 또는 재렌탈만 허용
            if (!cntrCnfmDtm.equals(dvo.getRcpDt().substring(1, 6))) {
                throw new BizException("MSG_ALT_CONN_PSBL_THM_NW_RCP"); //당월 신규 접수건만 연계가능합니다.
            }
        }

        //체크7. 이전상품 연계허용 대상지정(4129)
        if (dvo.getPdCd().equals("4129")
            && !Arrays.asList(
                new String[] {"4120", "4700", "4400", "4401", "4200", "4390", "4391", "4025", "4035", "4036", "4045",
                    "4046", "4075", "4055", "4056", "4065", "4066", "4402", "4403", "4392", "4115", "4135", "4145",
                    "4360", "4175", "4185", "4105", "4095", "4097", "4155", "4165"}
            ).contains(result.get(0).getPdCd())) {
            throw new BizException("MSG_ALT_CHK_CONN_PD_CD"); //연계 상품코드를 확인하세요.
        }

        //체크8. 연계가능건수 체크(새싹재배기는 연계가능건수 제한 없음)
        if (dvo.getPdCd().equals("4032") || dvo.getPdCd().equals("4052")) {} else if (Arrays
            .asList(new String[] {"4045", "4129", "4185"}).contains(dvo.getPdCd())) { //공기청정기
            //CALL ONEPCNT-READ-RTN 실행(최대３건까지 건수 체크)
            int checkCount = mapper.selectCountLinkageAvailable(dvo);
            if (checkCount >= 3) {
                throw new BizException("MSG_ALT_CONN_PSBL_MAX_THREE"); //최대３건까지 연계가능합니다.(A08E1)
            }
        }

        //체크9. 직원구매예외처리
        if (dvo.getDscGubn().equals("2") && Integer.parseInt(dvo.getRcpDt()) >= 20180201) {
            //이전코드는 당월 신규 접수만 허용
            if (!cntrCnfmDtm.equals(dvo.getRcpDt().substring(1, 6))
                && !Arrays.asList(new String[] {"4461", "4462", "4463", "4464", "4459"}).contains(dvo.getPdCd())) {
                throw new BizException("MSG_ALT_CONN_PSBL_THM_NW_RCP"); //당월 신규 접수건만 연계가능합니다.
            }

            //이전코드는 정수기 직원구매만 허용(예외로 특정판매자사번은 직원구매로 인정)
            if (!result.get(0).getDscApyTpCd().equals("2")
                && !Arrays.asList(new String[] {"1525290", "1650501", "1593484", "1564070", "1703186", "1703187"})
                    .contains(result.get(0).getCntrCstNo())) {
                throw new BizException("MSG_ALT_CONN_PSBL_EMPL_PRCH"); //직원구매건만 연계가능합니다.
            }

            //이전코드는 정수기 직원구매만 허용
            if (result.get(0).getRefPdClsfVal().contains("01001")) {
                throw new BizException("MSG_ALT_CONN_PSBL_PURIFIER_EMPL_PRCH"); //정수기 직원구매만 연계가능합니다.
            }
        }

        //1+1 교차등록확인
        return mapper.selectCountBogoCustomerCheck(dvo);
    }
}
