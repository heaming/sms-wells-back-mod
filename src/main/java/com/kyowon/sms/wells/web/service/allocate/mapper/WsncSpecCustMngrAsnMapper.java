package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;

@Mapper
public interface WsncSpecCustMngrAsnMapper {

    List<WsncSpecCustMngrAsnDvo> selectSpecCustMngrAsn(WsncSpecCustMngrAsnDvo dvo);

    Optional<WsncSpecCustMngrAsnDvo> selectSpecCustMngrAsn_01(WsncSpecCustMngrAsnDvo dvo);

    Optional<WsncSpecCustMngrAsnDvo> selectSpecCustMngrAsn_02(WsncSpecCustMngrAsnDvo dvo);

    Optional<WsncSpecCustMngrAsnDvo> selectSpecCustMngrAsn_03(WsncSpecCustMngrAsnDvo dvo);

    Optional<WsncSpecCustMngrAsnDvo> selectSpecCustMngrAsn_04(WsncSpecCustMngrAsnDvo dvo);

    Optional<WsncSpecCustMngrAsnDvo> selectSpecCustMngrAsn_05(WsncSpecCustMngrAsnDvo dvo);

    int insertSpecCustMngrAsn(WsncSpecCustMngrAsnDvo dvo);
}
