package com.kyowon.sms.wells.web.service.interfaces.converter;

import java.util.List;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbWorkOrderInterfaceDvo;

/**
 * <pre>
 * W-SV-I-0009 타시스템(교원웰스, 고객센터, KMEMBERS)에서 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.26
 */
@Mapper(componentModel = "spring")
public interface WsnbWorkOrderInterfaceConverter {

    List<WsnbWorkOrderInterfaceDvo> mapAllCreateOrderReqToDvos(List<CreateOrderReq> dtos);

    SaveReq mapDvoToContractSaveReq(WsnbWorkOrderDvo multiTaskOrderDvo);

    @Mapping(source = "asRefriDvCd", target = "cstCnrRefriDvCd")
    @Mapping(source = "prchsMatList", target = "partList")
    @Mapping(source = "regUserId", target = "userId")
    WsnbWorkOrderDvo convertInterfaceDvoToDvo(WsnbWorkOrderInterfaceDvo install);

}
