package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaContractRegStep3Mapper {
    int updateCntrDtlStep3(WctaContractDtlDvo dtl);

    int insertCntrStlmBasStep3(@Param("item")
    WctaContractStlmBasDvo dvo);

    int insertCntrStlmRelStep3(@Param("item")
    WctaContractStlmRelDvo dvo);

    int insertCntrAdrpcBasStep3(@Param("item")
    WctaContractAdrpcBasDvo dvo);

    int insertCntrAdrRelStep3(@Param("item")
    WctaContractAdrRelDvo dvo);

    int updateCntrWellsDtlStep3(WctaContractWellsDtlDvo dvo);

    WctaContractAdrpcBasDvo selectAdrInfoByCntrCstNo(String cntrCstNo);

    int updateCstStlmInMthCd(String cntrNo, String cstStlmInMthCd);

    int deleteCntrStlmBasStep3(String cntrNo);

    int deleteCntrStlmRelsStep3(String cntrNo);

    int deleteCntrAdrpcBasStep3(String cntrNo);

    int deleteCntrAdrRelsStep3(String cntrNo);

    int deleteWellsDtlStep3(String cntrNo);

}
