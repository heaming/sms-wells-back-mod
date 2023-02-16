package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBfsvcCrdovrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBfsvcCrdovrAsnDvo;

@Mapper
public interface WsncBfsvcCrdovrAsnMapper {

    List<WsncBfsvcCrdovrAsnDvo> selectBeforeBfsvcCrdovrAsn(WsncBfsvcCrdovrAsnDto.SearchReq dto);

    List<WsncBfsvcCrdovrAsnDvo> selectBfsvcCrdovrAsn(WsncBfsvcCrdovrAsnDto.SearchReq dto);

    WsncBfsvcCrdovrAsnDvo selectNewCstSvAsnNo();

    int insertCstSvBfsvcAsn(WsncBfsvcCrdovrAsnDvo dvo);

    int updateRgbsPuItmIz(WsncBfsvcCrdovrAsnDvo dvo);

    int updateCstSvBfsvcOjIz(WsncBfsvcCrdovrAsnDvo dvo);

    int insertCstSvBfsvcAsnHist(WsncBfsvcCrdovrAsnDvo dvo);

    int insertWkDtmChIz(WsncBfsvcCrdovrAsnDvo dvo);
}
