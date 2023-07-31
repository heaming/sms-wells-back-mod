package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationConfirmationDto.FindRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationConfirmationDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallationConfirmationDvo;

/**
 * <pre>
 * W-MP-U-0048P01 전자설치확인서
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.07
 */
@Mapper(componentModel = "spring")
public interface WsnbInstallationConfirmationConverter {
    FindRes mapWsnbInstallationConfirmationDvoToFindRes(WsnbInstallationConfirmationDvo dvo);

    WsnbInstallationConfirmationDvo mapCreateReqToWsnbSafetyAccidentDvo(CreateReq dto);
}
