package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryPackageDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryPackageDto.FindRes;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRegularDeliveryPackageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRegularDeliveryPackageService {

    private final WctiRegularDeliveryPackageMapper mapper;

    /**
     * 정기배송 상품변경 전 정보 조회 - 변경가능 패키지 목록 조회
     *
     * @programId W-SS-I-0026
     * @ingerfaceId EAI_WSSI1069
     * @param dto
     * @return dto
     */
    public FindRes getRegularDeliveryPackage(FindReq dto) {

        FindRes res = mapper.selectRegularDeliveryPackage(dto);

        if (ObjectUtils.isEmpty(res)) {
            return null;
        } else {
            return FindRes.builder()
            .pkgPdCd(res.pkgPdCd())
            .pkgPdNm(res.pkgPdNm())
            .pkgPdAmt(res.pkgPdAmt())
            .pdctList(mapper.selectChangPsbProducts(dto))
            .build();
        }
    }
}
