package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;

/**
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.06.16
 * @see [AS-IS] LC_ASREGN_API_000
 */
@Mapper(componentModel = "spring")
public interface WsnbInstallationOrderConverter {

    @Mapping(source = "inflwChnl", target = "inChnlDvCd")
    List<WsnbWorkOrderDvo> mapAllSaveReqToWorkOrderDvo(List<SaveReq> dto);

}
