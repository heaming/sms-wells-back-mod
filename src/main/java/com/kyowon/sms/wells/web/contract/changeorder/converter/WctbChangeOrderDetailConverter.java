package com.kyowon.sms.wells.web.contract.changeorder.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbChangeOrderDetailDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbChangeOrderDetailDvo;

@Mapper(componentModel = "spring")
public interface WctbChangeOrderDetailConverter {
    List<WctbChangeOrderDetailDto.SearchTbSsctCntrAdrChHistRes> mapWctbChangeOrderDetailDvoToSearchTbSsctCntrAdrChHistRes(
        List<WctbChangeOrderDetailDvo> dvos
    );
}
