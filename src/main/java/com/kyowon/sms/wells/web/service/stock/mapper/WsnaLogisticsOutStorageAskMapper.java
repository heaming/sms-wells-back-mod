package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;

@Mapper
public interface WsnaLogisticsOutStorageAskMapper {

    public WsnaLogisticsOutStorageAskDvo selectItmOstrAkSendEtxtByOstrAkNo(String ostrAkNo);

    public String selectNewLgstOstrAkNo(String lgstOstrCd);

    public int insertItmOstrAkSendEtxt(WsnaLogisticsOutStorageAskDvo dvo);

    public int insertOstrAkDtlSendEtxt(WsnaLogisticsOutStorageAskDtlDvo dvo);

}
