package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbChangeOrderDetailDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbChangeOrderDetailDvo;

@Mapper
public interface WctbChangeOrderDetailMapper {

    List<SearchTbSsctCntrChHistRes> selectTbSsctCntrChHist(SearchReq dto);

    List<SearchTbSsctCntrDchHistRes> selectTbSsctCntrDchHist(SearchReq dto);

    List<SearchTbSsctCntrCstRelRes> selectTbSsctCntrCstRel(SearchReq dto);

    List<SearchTbSsctCntrPrtnrRelRes> selectTbSsctCntrPrtnrRel(SearchReq dto);

    List<SearchTbSsctCntrAdrRelRes> selectTbSsctCntrAdrRel(SearchReq dto);

    List<WctbChangeOrderDetailDvo> selectTbSsctCntrAdrChHist(SearchReq dto);

    List<SearchTbSsctCntrStlmRelRes> selectTbSsctCntrStlmRel(SearchReq dto);

    List<SearchTbSsctCntrStlmChHistRes> selectTbSsctCntrStlmChHist(SearchReq dto);

    List<SearchTbSsctCntrWellsDchHistRes> selectTbSsctCntrWellsDchHist(SearchReq dto);

    List<SearchTbSsctCntrRelRes> selectTbSsctCntrRel(SearchReq dto);

    List<SearchTbSsctMchnChHistRes> selectTbSsctMchnChHist(SearchReq dto);

    List<SearchTbSsctRentalRstlChHistRes> selectTbSsctRentalRstlChHist(SearchReq dto);
}
