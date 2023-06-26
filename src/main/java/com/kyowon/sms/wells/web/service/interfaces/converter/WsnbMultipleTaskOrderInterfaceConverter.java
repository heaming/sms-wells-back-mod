package com.kyowon.sms.wells.web.service.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbMultipleTaskOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbMultipleTaskOrderInterfaceDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;

/**
 * <pre>
 * W-SV-I-0009 타시스템(교원웰스, 고객센터, KMEMBERS)에서 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.26
 */
@Mapper(componentModel = "spring")
public interface WsnbMultipleTaskOrderInterfaceConverter {

    List<WsnbMultipleTaskOrderInterfaceDvo> mapAllCreateOrderReqToDvos(List<CreateOrderReq> dtos);

    SaveReq mapDvoToContractSaveReq(WsnbMultipleTaskOrderDvo multiTaskOrderDvo);

}
