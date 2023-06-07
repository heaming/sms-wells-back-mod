package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsDeliveryAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsDeliveryAskBssDvo;

/**
 * <pre>
 * W-SV-S-0091 물류배송요청 서비스 (HQ 생성로직)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-31
 */

@Mapper
public interface WsnaLogisticsDeliveryAskMapper {

    int insertSppBasSendEtxt(List<WsnaLogisticsDeliveryAskDto.CreateReq> dtos);

    List<WsnaLogisticsDeliveryAskBssDvo> selectSppBasSendEtxtLlornos();

    int updateSppBasSendEtxtLlornos(List<WsnaLogisticsDeliveryAskBssDvo> dvos);

    int insertKssQomAsnSendTemp(List<WsnaLogisticsDeliveryAskDto.CreateReq> dtos);

    int insertKssQomAsnItmTemp(int tcnt);

    int insertSppPdSendEtxt();

    int insertSppMatSendEtxt();

}
