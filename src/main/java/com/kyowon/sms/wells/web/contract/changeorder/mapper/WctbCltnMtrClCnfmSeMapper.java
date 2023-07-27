package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WctbCltnMtrClCnfmSeMapper {
    List<String> selectCltnMtrClCnfmSe(String workType, String perfYm);

    List<String> selectBaseCntrNo(String cntrNo);

    List<String> selectOjCntrNo(String cntrNo);

    int updateCltnMtrClCnfmSe(String cntrNo);

    int insertCntrDtlStatChHist(String cntrNo);
}
