package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancellationMtrSetDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancellationMtrSetDto.SearchRes;

;

@Mapper
public interface WctbCancellationMtrSetMapper {
    List<SearchRes> selectContractBase(String businessType, String performanceYm);

    List<WctbCancellationMtrSetDto.SearchAcmpalCntrIzRes> selectAcmpalCntrIzs(String cntrNo, String cntrSn);

    int updateContractBas(String cntrCanDtm, String cntrNo);

    int updateAcmpalCntrIz(String acmpalCntrId);

    int updateCntrChRcpBas(String cntrChFshDtm, String cntrChRcpId);

    int updateAcmpalCntrChHist(String acmpalCntrId);

    int insertAcmpalCntrChHist(String acmpalCntrId);
}
