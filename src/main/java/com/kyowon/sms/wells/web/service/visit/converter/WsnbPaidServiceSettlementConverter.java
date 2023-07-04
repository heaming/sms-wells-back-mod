package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPaidServiceSettlementDto.CostDeposit;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPaidServiceSettlementDto.CreditCard;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPaidServiceSettlementDto.VirtualAccount;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbPaidServiceSettlementDvo;

/**
 * <pre>
 * W-SV-S-0094 유상 서비스 결제정보 생성
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.28
 */
@Mapper(componentModel = "spring")
public interface WsnbPaidServiceSettlementConverter {

    WsnbPaidServiceSettlementDvo mapCostDepositToDvo(CostDeposit dto);

    WsnbPaidServiceSettlementDvo mapCreditCardToDvo(CreditCard dto);

    WsnbPaidServiceSettlementDvo mapVirtualAccountToDvo(VirtualAccount dto);

}
