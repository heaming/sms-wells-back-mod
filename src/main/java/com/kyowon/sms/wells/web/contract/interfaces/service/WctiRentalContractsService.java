package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiRentalContractsConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalContractsDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalContractsDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiRentalContractsDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRentalContractsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRentalContractsService {
    private final WctiRentalContractsMapper mapper;
    private final WctiRentalContractsConverter converter;

    /**
    * 렌탈 계약 정보 조회
    * -   렌탈 주문정보조회－모듈
    * @interfaceId   EAI_WSSI1004
    * @programId   W-SS-S-0026
    * @param       dto
    * @return      dto
    */
    public SearchRes getRentalContracts(SearchReq dto) {
        WctiRentalContractsDvo dvo = mapper.selectRentalContracts(dto);
        if (dvo == null) {
            dvo = new WctiRentalContractsDvo();
        }
        SearchReq req = mapper.selectRegDlvr(dto);
        if (req != null) {
            WctiRentalContractsDvo cntrDvo = mapper.selectRegDlvrCntr(req.cntrNo(), req.cntrSn());
            dvo.setRglrSppCombiYn("Y");
            dvo.setRglrSppCntrNo(cntrDvo.getRglrSppCntrNo());
            dvo.setRglrSppCntrSn(cntrDvo.getRglrSppCntrSn());
            dvo.setRglrSppCstKnm(cntrDvo.getRglrSppCstKnm());
            dvo.setRglrSppCopnDvCd(cntrDvo.getRglrSppCopnDvCd());
            dvo.setRglrSppBryyMmdd(cntrDvo.getRglrSppBryyMmdd());
            dvo.setRglrSppBzrno(cntrDvo.getRglrSppBzrno());
            dvo.setRglrSppLocaraTno(cntrDvo.getRglrSppLocaraTno());
            dvo.setRglrSppExnoEncr(cntrDvo.getRglrSppExnoEncr());
            dvo.setRglrSppIdvTno(cntrDvo.getRglrSppIdvTno());
            dvo.setRglrSppCralLocaraTno(cntrDvo.getRglrSppCralLocaraTno());
            dvo.setRglrSppMexnoEncr(cntrDvo.getRglrSppMexnoEncr());
            dvo.setRglrSppCralIdvTno(cntrDvo.getRglrSppCralIdvTno());
            dvo.setRglrSppAdr(cntrDvo.getRglrSppAdr());
            dvo.setRglrSppDtlAdr(cntrDvo.getRglrSppDtlAdr());
            dvo.setRglrSppPrd(cntrDvo.getRglrSppPrd());
            dvo.setRglrSppPdNm(cntrDvo.getRglrSppPdNm());
            dvo.setRglrSppSellAmt(cntrDvo.getRglrSppSellAmt());
            dvo.setRglrSppMmBillAmt(cntrDvo.getRglrSppMmBillAmt());
            dvo.setRglrSppFntDvCd(cntrDvo.getRglrSppFntDvCd());
            dvo.setRglrSppBnkCrdNm(cntrDvo.getRglrSppBnkCrdNm());
            dvo.setRglrSppAchldrNm(cntrDvo.getRglrSppAchldrNm());
            dvo.setRglrSppBnkCrdNo(cntrDvo.getRglrSppBnkCrdNo());
            dvo.setRglrSppFntDt(cntrDvo.getRglrSppFntDt());
            dvo.setRglrSppExpdtYm(cntrDvo.getRglrSppExpdtYm());

        }
        dvo.setScsYn("Y");
        SearchRes res = converter.mapWctiRentalContractsDvoToSearchRes(dvo);
        return res;
    }
}
