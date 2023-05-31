package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsInStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDvo;

@Mapper
public interface WsnaLogisticsInStorageAskMapper {

    Optional<WsnaLogisticsInStorageAskDtlDvo> selectRtngdAkDtlSendEtxtByOstrAkNoAndOstrAkSn(
        WsnaLogisticsInStorageAskDto.RemoveReq dto
    );

    WsnaLogisticsInStorageAskDvo selectPdRtngdAkSendEtxtByRtngdAkNo(String rtngdAkNo);

    String selectNewLgstStrAkNo(String lgstStrCd);

    int insertPdRtngdAkSendEtxt(WsnaLogisticsInStorageAskDvo dvo);

    int insertRtngdAkDtlSendEtxt(WsnaLogisticsInStorageAskDtlDvo dvo);

    int updateRtngdAkDtlSendEtxt(WsnaLogisticsInStorageAskDtlDvo dvo);

    int updateRtngdAkDtlSendEtxtForRemove(WsnaLogisticsInStorageAskDtlDvo dvo);

    Integer selectRtngdAkDtlSendEtxtCount(String ostrAkNo);

    int updatePdRtngdAkSendEtxtForRemove(WsnaLogisticsInStorageAskDvo dvo);

}
