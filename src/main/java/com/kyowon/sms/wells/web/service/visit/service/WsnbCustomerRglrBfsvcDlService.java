package com.kyowon.sms.wells.web.service.visit.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCustomerRglrBfsvcDlDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbCustomerRglrBfsvcDlMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbCustomerRglrBfsvcDlService {

    private final WsnbCustomerRglrBfsvcDlMapper mapper;

    @Transactional
    public int removeRglrBfsvcDl(WsnbCustomerRglrBfsvcDlDto.SaveReq dto) {

        /*
         * 1. TB_SVPD_CST_SV_BFSVC_ASN_IZ 테이블 데이터를 확인한다.
         *  - 삭제할년월에 배정된 배정정보가 있는지 확인한다.
         *   배정정보가 없다면 종료 (return msg: "삭제불가! 당월 배정된 자료가 없습니다!")
         *  - 방문여부를 체크한다. (TB_SVPD_CST_SV_BFSVC_ASN_IZ-WK_EXCN_DT is null 이면 작업 데이터 없음)
         *   방문정보가 없다면 종료 (return msg: "삭제불가! 이미 방문완료한 배정자료입니다!")
         *
         * 2. TB_SVPD_CST_SV_EXCN_IZ(고객서비스수행내역) 테이블에서 PDCT_PD_CD(제품상품코드) 조회하고 해당 제품상품코드가 모종인지 확인하여 로직 분기 처리한다.
         *   2.1 모종인경우 (현재 모종관련 로직 확인 중 - 추후 정의)
         *     - 배송 스케줄, 모종 구성정보 테이블 데이터 삭제
         *   2.2 그 외 제품상품코드인 경우는 고객서비스배정번호로 조회된 배정 데이터를 삭제 처리한다. (데이터삭제여부 컬럼 Y로 변경)
         *     2.2.1 배정데이터를 삭제 처리: TB_SVPD_CST_SV_BFSVC_ASN_IZ-DTA_DL_YN = 'Y'
         *     2.2.2 로그 테이블에 삭제 처리된 데이터 저장: TB_SVPD_CST_SV_BFSVC_ASN_HIST 테이블에 위에서 변경한 데이터 그대로 저장
         *      - BFSVC_EXCN_ASN_IZ_ID 채번규칙 : YYYYMMDD + LPAD(TB_SVPD_CST_SV_BFSVC_ASN_HIST$BFSVC_EXCN_ASN_IZ_ID, 12, '0')  = 20자리
         *
         * 3. TB_SVPD_RGBS_PU_ITM_IZ(정기BS투입품목내역) 테이블에서 삭제할년월, 고객서비스배정번호에 해당하는 품목 내역을 삭제한다.
         *
         * 4. TB_SVPD_CST_SV_BFSVC_OJ_IZ(고객서비스BS대상내역) 테이블에서 삭제할년월, 고객서비스배정번호에 해당하는 BS대상내역을 삭제한다.
         */
        log.info("[WsnbCustomerRglrBfsvcDlService.deleteRglrBfsvcDl] Delete Process Start!");
        WsnbCustomerRglrBfsvcDlDvo dvo = mapper.selectBfsvcAsnIz(dto);

        if (dvo == null || StringUtils.isEmpty(dvo.getCstSvAsnNo())) {
            throw new BizException("삭제불가! 당월 배정된 자료가 없습니다!");
        } else if (StringUtils.isNotEmpty(dvo.getWkExcnDt())) {
            throw new BizException("삭제불가! 이미 방문완료한 배정자료입니다!");
        }

        //TODO 현재 모종관련 로직 확인 중 - 추후 정의
        if (1 != 1) {
            //모종인 경우
        } else {
            //모종 이외의 경우
            mapper.updateBfsvcAsnIz(dvo);
            mapper.insertBfsvcAsnHist(dvo);
        }

        mapper.deleteRgbsPuItmIz(dvo);
        mapper.deleteCstSvBfsvcOjIz(dvo);

        return 1;
    }
}
