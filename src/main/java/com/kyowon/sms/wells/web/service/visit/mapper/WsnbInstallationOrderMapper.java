package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;
import java.util.Optional;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractReqDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbOjContractDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaskProgStatDvo;

/**
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.16
 * @see [AS-IS] LC_ASREGN_API_000
 */
@Mapper
public interface WsnbInstallationOrderMapper {

    Optional<WsnbContractReqDvo> selectContractByPk(String cntrNo, String cntrSn);

    int insertAsIstCancel(WsnbWorkOrderDvo dvo);

    WsnbTaskProgStatDvo selectTaskProgStat(String cntrNo, String cntrSn);

    int deleteSvExcnIz(String cntrNo, String cntrSn);

    int deleteIstAsnIz(String cntrNo, String cntrSn);

    int deleteIstOjIz(String cntrNo, String cntrSn);

    List<WsnbOjContractDvo> selectOjContract(WsnbOjContractDvo dvo);

    List<String> selectSeedingCstSvAsnNos(String cntrNo, String cntrSn);

    int deleteSdingSppPlan(String cntrNo, String cntrSn);

    int deleteSdingSppExpIz(String cntrNo, String cntrSn);

}
