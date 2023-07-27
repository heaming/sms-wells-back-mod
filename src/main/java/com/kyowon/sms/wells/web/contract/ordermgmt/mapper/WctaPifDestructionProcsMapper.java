package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPifDestructionProcsDvo;

@Mapper
public interface WctaPifDestructionProcsMapper {
    List<WctaPifDestructionProcsDvo> selectCntrAdrpcBas(String cntrNo);

    List<WctaPifDestructionProcsDvo> selectCntrAdrChHist(String cntrNo);

    List<WctaPifDestructionProcsDvo> selectCntrStlmBas(String cntrNo);

    List<WctaPifDestructionProcsDvo> selectCntrStlmChHist(String cntrNo);

    int selectMvsDestructionRcvryTbl(WctaPifDestructionProcsDvo dvo);

    int selectMvsDestructionRcvryCol(WctaPifDestructionProcsDvo dvo);

    int insertMvsDestructionRcvryTbl(WctaPifDestructionProcsDvo dvo);

    int insertMvsDestructionRcvryCol(WctaPifDestructionProcsDvo dvo);

    int insertMvsDestructionRcvryBas(@Param("item")
    WctaPifDestructionProcsDvo dvo);

    int insertMvsDestructionRcvryTblDtl(WctaPifDestructionProcsDvo dvo);

    int insertMvsDestructionRcvryColDtl(WctaPifDestructionProcsDvo dvo);

    int updateCntrAdrpcBas(WctaPifDestructionProcsDvo dvo);

    int updateCntrAdrChHists(WctaPifDestructionProcsDvo dvo);

    int updateCntrStlmBas(WctaPifDestructionProcsDvo dvo);

    int updateCntrStlmChHists(WctaPifDestructionProcsDvo dvo);
}
