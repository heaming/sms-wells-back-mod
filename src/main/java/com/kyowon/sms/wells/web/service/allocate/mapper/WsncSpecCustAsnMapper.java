package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;

@Mapper
public interface WsncSpecCustAsnMapper {
    List<WsncSpecCustAsnDvo> selectSpecCustAsn(WsncSpecCustAsnDvo dvo);

    int insertSpecCustAsn(WsncSpecCustAsnDvo dvo);
}
