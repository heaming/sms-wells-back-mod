package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCltnMtrClCnfmSeMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WctbCltnMtrClCnfmSeService {
    private final WctbCltnMtrClCnfmSeMapper mapper;
    @Transactional
    public int saveCltnMtrClCnfmSe(String workType, String perfYm) {
        int res = 0;
        List<String> cntrNos = mapper.selectCltnMtrClCnfmSe(workType, perfYm);
        for(int i = 0; i<cntrNos.size(); i++){
            String cntrNo = cntrNos.get(i);
            mapper.updateCltnMtrClCnfmSe(cntrNo);
            res += mapper.insertCntrDtlStatChHist(cntrNo);
            List<String> baseCntrNos = mapper.selectBaseCntrNo(cntrNo);
            for(int j = 0; j<baseCntrNos.size(); j++){
                String baseCntrNo = baseCntrNos.get(j);
                mapper.updateCltnMtrClCnfmSe(baseCntrNo);
                res += mapper.insertCntrDtlStatChHist(baseCntrNo);
            }
            List<String> ojCntrNos = mapper.selectOjCntrNo(cntrNo);
            for(int k = 0; k<ojCntrNos.size(); k++){
                String ojCntrNo = ojCntrNos.get(k);
                mapper.updateCltnMtrClCnfmSe(ojCntrNo);
                res += mapper.insertCntrDtlStatChHist(ojCntrNo);
            }
        }
        return res;
    }
}
