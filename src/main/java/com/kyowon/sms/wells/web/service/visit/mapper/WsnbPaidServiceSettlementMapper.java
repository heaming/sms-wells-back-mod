package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbPaidServiceSettlementDvo;

/**
 * <pre>
 * W-SV-S-0094 유상 서비스 결제정보 생성
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.28
 */
@Mapper
public interface WsnbPaidServiceSettlementMapper {

    int selectSvCsBilBas(WsnbPaidServiceSettlementDvo dvo);

    int updateSvCsBilBas(WsnbPaidServiceSettlementDvo dvo);

    int updateSvCsBilIz(WsnbPaidServiceSettlementDvo dvo);

    int mergeSvCsDpIz(WsnbPaidServiceSettlementDvo dvo);

    int mergeSvCsCrdcdPcsIz(WsnbPaidServiceSettlementDvo dvo);

    int mergeSvCsVacPcsIz(WsnbPaidServiceSettlementDvo dvo);

}
