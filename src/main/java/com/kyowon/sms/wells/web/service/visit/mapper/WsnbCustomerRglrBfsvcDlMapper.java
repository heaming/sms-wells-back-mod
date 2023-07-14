package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCustomerRglrBfsvcDlDvo;

@Mapper
public interface WsnbCustomerRglrBfsvcDlMapper {
    WsnbCustomerRglrBfsvcDlDvo selectBfsvcAsnIz(WsnbCustomerRglrBfsvcDlDto.SaveReq dto);

    int updateBfsvcAsnIz(WsnbCustomerRglrBfsvcDlDvo dvo);

    int insertBfsvcAsnHist(WsnbCustomerRglrBfsvcDlDvo dvo);

    int deleteRgbsPuItmIz(WsnbCustomerRglrBfsvcDlDvo dvo);

    int deleteCstSvBfsvcOjIz(WsnbCustomerRglrBfsvcDlDvo dvo);
}
