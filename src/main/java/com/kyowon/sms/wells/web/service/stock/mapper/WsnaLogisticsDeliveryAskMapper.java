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

    int insertKssQomAsnSendTemp(List<WsnaLogisticsDeliveryAskDto.CreateReq> dtos);

    List<WsnaLogisticsDeliveryAskBssDvo> selectKssQomAsnSendTempLlornos();

    int updateKssQomAsnSendTempLlornos(List<WsnaLogisticsDeliveryAskBssDvo> dvos);

    int insertSppBasSendEtxt();

    int insertKssQomAsnItmTemp(int tcnt);

    int insertSppPdSendEtxt();

    int insertSppMatSendEtxt();

}
