package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMachineChangeCstInfDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMchnChCstCanDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalMchnChCstCanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalMchnChCstCanService {
    private final WctbRentalMchnChCstCanMapper mapper;
    private final WctbMachineChangeCstInfService service;

    public int saveRentalMchnChCstCan(String cntrNo, String cntrSn, String cntrCanDtm) {
        WctbMachineChangeCstInfDto.SearchRes res = service.getMachineChangeCstInf(cntrNo, cntrSn);
        int result = 0;
        if (res != null) {
            if (res.sellTpCd().equals("1")) { // 판매유형코드 = 일시불 일 경우
                mapper.updateCntrBas(cntrNo, cntrCanDtm);
                mapper.updateCntrDtl(cntrNo, cntrSn);
                mapper.updateCntrDchHist(cntrNo, cntrSn);
                result += mapper.insertCntrDchHist(cntrNo, cntrSn);
                WctbRentalMchnChCstCanDvo dvo = mapper.selectCntrDtlMembership(cntrNo);
                if (dvo != null) {
                    mapper.updateCntrDtl(cntrNo, dvo.getCntrSn());
                    mapper.updateCntrDchHist(cntrNo, dvo.getCntrSn());
                    result += mapper.insertCntrDchHist(cntrNo, dvo.getCntrSn());
                }
            }
            if (res.sellTpCd().equals("2")) { // 판매유형코드 = 렌탈 일 경우
                mapper.updateCntrBasRental(cntrNo, cntrSn, cntrCanDtm);
                mapper.updateCntrDtl(cntrNo, cntrSn);
                mapper.updateCntrDchHist(cntrNo, cntrSn);
                result += mapper.insertCntrDchHist(cntrNo, cntrSn);
                WctbRentalMchnChCstCanDvo dvo = mapper.selectCntrDtlMembership(cntrNo);
                if (dvo != null) {
                    mapper.updateCntrDtl(cntrNo, dvo.getCntrSn());
                    mapper.updateCntrDchHist(cntrNo, dvo.getCntrSn());
                    result += mapper.insertCntrDchHist(cntrNo, dvo.getCntrSn());
                }
            }
        }
        return result;
    }
}
