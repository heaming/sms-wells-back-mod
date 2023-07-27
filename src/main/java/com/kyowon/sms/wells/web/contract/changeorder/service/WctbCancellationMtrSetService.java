package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbCancellationMtrSetConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancellationMtrSetDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancellationMtrSetDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCancellationMtrSetDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCancellationMtrSetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCancellationMtrSetService {
    private final WctbCancellationMtrSetMapper mapper;
    private final WctbCancellationMtrSetConverter converter;

    @Transactional
    public int cancellationMtrClSe(String businessType, String performanceYm) {
        int result = 0;
        String canDtm = "";
        if (StringUtils.isEmpty(performanceYm) && performanceYm.length() != 6) {
            return result;
        }
        if (businessType != "2" && businessType != "3" && businessType != "6" && businessType != "9") {
            return result;
        }
        List<SearchRes> dtos = mapper.selectContractBase(businessType, performanceYm);

        for (SearchRes dto : dtos) {
            WctbCancellationMtrSetDvo dvo = converter.mapSearchResToWctbCancellationMtrSetDvo(dto);
            if (dvo.getCntrChFshDtmNchk().equals("0") && dvo.getCntrCanDtmNchk().equals("1")) {
                canDtm = dvo.getCntrChFshDtm();
                result += mapper.updateContractBas(canDtm, dvo.getCntrNo());
            }
            if (dvo.getCntrChFshDtmNchk().equals("1") && dvo.getCntrCanDtmNchk().equals("0")) {
                canDtm = dvo.getCntrCanDtm();
                result += mapper.updateCntrChRcpBas(canDtm, dvo.getCntrChRcpId());
            }

            List<WctbCancellationMtrSetDto.SearchAcmpalCntrIzRes> acmpalCntrIzdtos = mapper
                .selectAcmpalCntrIzs(dvo.getCntrNo(), dvo.getCntrSn());
            for (WctbCancellationMtrSetDto.SearchAcmpalCntrIzRes acmpalCntrIzdto : acmpalCntrIzdtos) {
                mapper.updateContractBas(canDtm, acmpalCntrIzdto.ojCntrNo());
                mapper.updateAcmpalCntrIz(acmpalCntrIzdto.acmpalCntrId());
                mapper.updateAcmpalCntrChHist(acmpalCntrIzdto.acmpalCntrId());
                mapper.insertAcmpalCntrChHist(acmpalCntrIzdto.acmpalCntrId());
            }
        }
        return result;
    }

}
