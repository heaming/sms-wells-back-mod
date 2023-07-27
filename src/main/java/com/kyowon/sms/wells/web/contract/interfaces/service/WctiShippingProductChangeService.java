package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductChangeDto.SaveReq;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractChangeRcpBasDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiShippingProductChangeMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiShippingProductChangeService {

    private final WctiShippingProductChangeMapper mapper;
    private final WctzHistoryService historyService;

    @Transactional
    public String editShippingProduct(SaveReq dto) {
        WctiContractChangeRcpBasDvo saveDvo = mapper.selectShippingProduct(dto).orElseThrow(
            () -> new BizException("변경전 제품 상품코드가 존재하지 않습니다.")
        );

        saveDvo.setCntrChTpCd("201");
        saveDvo.setChRqrDvCd("1");
        saveDvo.setCntrChAkCn("커피원두 상품변경("+dto.bfchPdctPdCd()+" -> "+dto.afchPdctPdCd()+")");
        saveDvo.setCntrChPrgsStatCd("");    // TODO: 추후 완료를 넣는 것으로 바뀔 수 있음
        saveDvo.setChRcpUsrId(dto.chRcstId());
        saveDvo.setAprUsrId(dto.chRcstId());

        //계약변경접수기본 생성
        int resultCnt = mapper.insertCntrChRcpBas(saveDvo);
        if(resultCnt <= 0)
            return "N";

        //계약변경접수변경이력 생성
        mapper.intertCntrChRcchHist(saveDvo.getCntrChRcpId());

        saveDvo.setCntrUnitTpCd("020");
        saveDvo.setDtlCntrNo(dto.cntrNo());
        saveDvo.setDtlCntrSn(dto.cntrSn());
        saveDvo.setCntrChRsonCd("");    // TODO: 추후 적용
        saveDvo.setCntrChRsonDvCd("");  // TODO: 추후 적용
        saveDvo.setCntrChAtcDvCd("");   // TODO: 추후 적용
        saveDvo.setChPdCd(dto.afchPdctPdCd());
        saveDvo.setBfchCn(dto.bfchPdctPdCd());
        saveDvo.setProcsYn("Y");

        //계약변경접수상세 생성
        resultCnt = mapper.insertCntrChRcpDtl(saveDvo);

        //계약변경접수상세변경이력 생
        if(resultCnt > 0)
            mapper.intertCntrChRcpDchHist(saveDvo.getCntrChRcpId(), saveDvo.getCntrChSn());

        //계약상품관계 변경(기존 데이터)
        mapper.updateCntrPdRel(saveDvo.getCntrPdRelId());

        //계약상품관계 생성
        mapper.insertCntrPdRel(saveDvo); // TODO: 테스트용 주석


        return "Y";
    }
}
