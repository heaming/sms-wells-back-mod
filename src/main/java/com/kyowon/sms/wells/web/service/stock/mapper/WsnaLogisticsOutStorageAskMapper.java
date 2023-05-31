package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsOutStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;

@Mapper
public interface WsnaLogisticsOutStorageAskMapper {

    WsnaLogisticsOutStorageAskDvo selectItmOstrAkSendEtxtByOstrAkNo(String ostrAkNo);

    String selectNewLgstOstrAkNo(String lgstOstrCd);

    int insertItmOstrAkSendEtxt(WsnaLogisticsOutStorageAskDvo dvo);

    int insertOstrAkDtlSendEtxt(WsnaLogisticsOutStorageAskDtlDvo dvo);

    Optional<WsnaLogisticsOutStorageAskDtlDvo> selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(
        WsnaLogisticsOutStorageAskDto.RemoveReq dto
    );

    int updateOstrAkDtlSendEtxt(WsnaLogisticsOutStorageAskDtlDvo dvo);

    int updateOstrAkDtlSendEtxtForRemove(WsnaLogisticsOutStorageAskDtlDvo dvo);

    Integer selectOstrAkDtlSendEtxtCount(String ostrAkNo);

    int updateItmOstrAkSendEtxtForRemove(WsnaLogisticsOutStorageAskDvo dvo);
}
