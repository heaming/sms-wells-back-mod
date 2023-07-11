package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctaInstallationReqdDtInMapper {

    int updateInstallReqdDt(String cntrNo, String cntrSn, String istDt, String reqdDt);

    int updateContractDetailPdStrtdt(String cntrNo, String cntrSn);

    int updateContractWellsDetailHist(String cntrNo, String cntrSn);

    int insertContractWellsDetailHist(String cntrNo, String cntrSn);


    int updateInstallOrderReqDt(String cntrNo, String cntrSn, String sppDuedt, String lcCttRsCd);

    int updateContractDetailHist(String cntrNo, String cntrSn);

    int insertContractDetailHist(String cntrNo, String cntrSn);
}
