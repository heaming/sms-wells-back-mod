package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBfsvcCrdovrAsnDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;

@Mapper
public interface WsnbIndividualVisitPrdMapper {

    WsnbIndividualVisitPrdDto.SearchRes selectCustomerInfo(WsnbIndividualVisitPrdDto.SearchReq dto);

    List<WsnbIndividualVisitPrdDto.SearchVstRes> selectVisits(WsnbIndividualVisitPrdDto.SearchReq dto);

    List<WsnbIndividualVisitPrdDto.SearchPeriodRes> selectPeriods(WsnbIndividualVisitPrdDto.SearchPeriodReq dto);

    List<WsncBfsvcCrdovrAsnDto.SaveReq> selectAsnNos(WsnbIndividualVisitPrdDto.SearchProcessReq dto);

    List<WsnbCustomerRglrBfsvcDlDto.SaveReq> selectAsnNoTypeBs(WsnbIndividualVisitPrdDto.SearchProcessReq dto);

    int deleteSvRgbsprIz(WsnbIndividualVisitPrdDto.SearchProcessReq dto);
}
