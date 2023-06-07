package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsDeliveryAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsDeliveryAskBssDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaLogisticsDeliveryAskMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0091 물류배송요청 서비스 (HQ 생성로직)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-31
 */

@Service
@Validated
@RequiredArgsConstructor
public class WsnaLogisticsDeliveryAskService {

    private final WsnaLogisticsDeliveryAskMapper mapper;

    /**
     * 물류배송요청 생성
     * @param dtos  (필수) 물류배송요청 데이터 리스트
     * @return 배송기본송신전문 데이터 생성 건수, 배송상품송신전문 데이터 생성 건수, 배송자제송신전문 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsDeliveryAskDto.CreateRes createLogisticsDeliveryAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsDeliveryAskDto.CreateReq> dtos
    ) {

        // TB_IFIN_KSS_QOM_ASN_SEND_TEMP - KSS물량배정송신전문 데이터 생성
        this.mapper.insertKssQomAsnSendTemp(dtos);

        // 파트너번호별 주문번호 조회
        List<WsnaLogisticsDeliveryAskBssDvo> bssDvos = this.mapper.selectKssQomAsnSendTempLlornos();

        // 주문번호 업데이트
        this.mapper.updateKssQomAsnSendTempLlornos(bssDvos);

        // TB_IFIN_SPP_BAS_SEND_ETXT - 배송기본송신전문 데이터 생성
        int basCnt = this.mapper.insertSppBasSendEtxt();

        // 차수
        int tcnt = dtos.get(0).tcnt();

        // TB_IFIN_KSS_QOM_ASN_ITM_TEMP - KSS물량배정품목임시 데이터 생성
        this.mapper.insertKssQomAsnItmTemp(tcnt);

        // TB_IFIN_SPP_PD_SEND_ETXT - 배송상품송신전문 데이터 생성
        int pdCnt = this.mapper.insertSppPdSendEtxt();

        // TB_IFIN_SPP_MAT_SEND_ETXT - 배송자재송신전문
        int matCnt = this.mapper.insertSppMatSendEtxt();

        return new WsnaLogisticsDeliveryAskDto.CreateRes(basCnt, pdCnt, matCnt);
    }

}
