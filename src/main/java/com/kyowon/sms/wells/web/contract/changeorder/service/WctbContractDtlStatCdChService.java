package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbContractDtlStatCdChMapper;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbContractDtlStatCdChService {

    private final CodeService codeService;
    private final WctbContractDtlStatCdChMapper mapper;

    /**
     * 계약상세 상태코드 및 종료일자 변경
     * - 계약상세상태변경 이력 및 계약상세변경이력 생성 포함
     * @programId W-SS-S-0083
     * @param   dvo cntrNo 계약번호(필수)
     *          dvo.cntrSn 계약일련번호(필수)
     *          dvo.cntrDtlStatCd 변경할 계약상세상태코드(필수)
     *          dvo.cntrPdEnddt 계약상품종료일자
     * @return  Y/N 성공여부
     */

    public String editContractDtlStatCdCh(WctbContractDtlStatCdChDvo dvo) throws Exception {
        // 0. check validation
        ValidAssert.hasText(dvo.getCntrNo());
        ValidAssert.hasText(dvo.getCntrSn());
        ValidAssert.hasText(dvo.getCntrDtlStatCd());

        //계약상세상태코드가 공통코드 계약상세상태코드에 존재하지 않는 값이면 종료(Exception)
        if(!codeService.isExistCodeDetail("CNTR_DTL_STAT_CD", dvo.getCntrDtlStatCd())) {
            throw new BizException("계약상세상태코드가 존재하지 않는 값입니다.");
        }

        //계약상품종료일자가 null 이 아닌 경우,  날짜형식 체크(Exception)
        if(StringUtil.isNotEmpty(dvo.getCntrPdEnddt()) && !DateUtil.isValid(dvo.getCntrPdEnddt(), "yyyyMMdd")){
            throw new BizException("계약상품종료일자가 날짜 포맷이 아닙니다.");
        }

        // 1. 계약상세상태코드 update
        int result = mapper.updateContractDtlStatCd(dvo);
        if(result <= 0){
            return "N";
        }

        // 2. 계약상세상태변경이력 insert
        mapper.updateCntrDetailStatChangeHist(dvo.getCntrNo(), dvo.getCntrSn());
        mapper.insertCntrDetailStatChangeHist(dvo.getCntrNo(), dvo.getCntrSn());

        // 3. 계약상세변경이력 insert
        mapper.updateCntrDetailChangeHist(dvo.getCntrNo(), dvo.getCntrSn());
        mapper.insertCntrDetailChangeHist(dvo.getCntrNo(), dvo.getCntrSn());

        return "Y";
    }
}
