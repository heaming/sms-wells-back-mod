package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchCntrtInfoReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaContractRegStep1Mapper {
    boolean isExistCntrtInfo(SearchCntrtInfoReq dto);

    WctaContractCstRelDvo selectCntrtInfoByCstNo(String cstNo);

    List<WctaContractRegStep1Dvo.PextCntrDvo> selectPextCntr(String cstNo, String cntrNo);

    List<Long> selectCntrtDlqAmt(List<String> cstNos);

    List<String> selectCntrtCstNosByLrnrs(List<String> familyCstNos);

    List<WctaContractCstRelDvo.GryCdDvo> selectGryCdList();

    List<WctaContractCstRelDvo.GryCdDvo> selectGryCdList(int age);

    List<WctaContractRegStep1Dvo.ResBznsPsbDtDvo> selectResBznsPsbDt();

    String selectResrOrdrYn();

    List<String> selectCntrtFmlPrtnrNo(String cstNo);

    List<String> selectThmCntrsByLrnr(String lrnrCstNo);

    List<WctaContractRegStep1Dvo.PrrBizRgstPtrmDvo> selectPrrBizRgstPtrm();

    boolean isValidPspcCstId(String pspcCstId, String copnDvCd);

    String selectRcpAofficeCd(String loginPrtnrNo);

    /* Step1 저장 */
    int insertCntrBasStep1(WctaContractBasDvo dvo);

    int updateCntrBasStep1(WctaContractBasDvo dvo);

    int updateCntrPrtnrRelStep1(WctaContractPrtnrRelDvo dvo);

    int insertCntrPrtnrRelStep1(@Param("item")
    WctaContractPrtnrRelDvo dvo);

    int updateCntrCstRelStep1(WctaContractCstRelDvo dvo);

    int insertCntrCstRelStep1(@Param("item")
    WctaContractCstRelDvo dvo);

    int insertCntrRelStep1(@Param("item")
    WctaContractRelDvo dvo);

    int deleteCntrPrtnrRelStep1(String cntrNo);

    int deleteCntrCstRelStep1(String cntrNo);

    int deleteCntrRelStep1(String cntrNo);
}
