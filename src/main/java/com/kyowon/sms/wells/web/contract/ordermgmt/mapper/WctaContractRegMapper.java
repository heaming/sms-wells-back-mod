package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaContractRegMapper {
    String selectPrtnrCstNo(String prtnrNo);

    String selectEnsmCstNo(String ensmNo);

    String selectCstPrtnrNo(String cstNo);

    String selectCstEnsmNo(String cstNo);

    String selectBryyMmdd(String cstNo);

    int updateCntrPrgsStatCd(String cntrNo, String cntrPrgsStatCd);

    String selectHddmInf(String cntrNo);

    WctaContractBasDvo selectContractBas(String cntrNo);

    List<WctaContractDtlDvo> selectContractDtl(String cntrNo);

    List<WctaContractDtlDvo> selectContractDtl(String cntrNo, int cntrSn);

    List<WctaContractPrtnrRelDvo> selectContractPrtnrRel(String cntrNo);

    List<WctaContractCstRelDvo> selectContractCstRel(String cntrNo);

    List<WctaContractCstRelDvo> selectContractCstRel(String cntrNo, int cntrSn);

    List<WctaContractRelDvo> selectContractRel(String cntrNo);

    List<WctaMachineChangeIzDvo> selectMachineChangeIz(String cntrNo);

    List<WctaContractPdRelDvo> selectContractPdRel(String cntrNo, int cntrSn);

    List<WctaContractPmotIzDvo> selectContractPmotIz(String cntrNo, int cntrSn);

    List<WctaFgptRcpIzDvo> selectFgptRcpIz(String cntrNo, int cntrSn);

    List<WctaContractPrcCmptIzDvo> selectContractPrcCmptIz(String cntrNo, int cntrSn);

    WctaContractStlmBasDvo selectContractStlmBas(String cntrNo, int cntrSn);

    List<WctaContractStlmRelDvo> selectContractStlmRels(String cntrNo);

    List<WctaContractStlmRelDvo> selectContractStlmRels(String cntrNo, int cntrSn);

    WctaContractWellsDtlDvo selectContractWellsDtl(String cntrNo, int cntrSn);

    List<WctaContractAdrpcBasDvo> selectContractAdrpcBas(String cntrNo);

    WctaContractAdrRelDvo selectContractAdrRel(String cntrNo, int cntrSn);
}
