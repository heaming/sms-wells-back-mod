package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsInStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDvo;

@Mapper
public interface WsnaLogisticsInStorageAskMapper {

    WsnaLogisticsInStorageAskDtlDvo selectRtngdAkDtlSendEtxtByRelNoAndRelSn(WsnaLogisticsInStorageAskDto.FindReq dto);

    WsnaLogisticsInStorageAskDvo selectPdRtngdAkSendEtxtByRtngdAkNo(String ostrAkNo);

    String selectNewLgstStrAkNo(String lgstStrCd);

    int insertPdRtngdAkSendEtxt(WsnaLogisticsInStorageAskDvo dvo);

    int insertRtngdAkDtlSendEtxt(WsnaLogisticsInStorageAskDtlDvo dvo);

    int updateRtngdAkDtlSendEtxt(WsnaLogisticsInStorageAskDtlDvo dvo);

    int updateRtngdAkDtlSendEtxtForRemove(WsnaLogisticsInStorageAskDtlDvo dvo);

    Integer selectRtngdAkDtlSendEtxtCount(String ostrAkNo);

    int updatePdRtngdAkSendEtxtForRemove(WsnaLogisticsInStorageAskDvo dvo);

}
