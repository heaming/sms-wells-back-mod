package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChangeDvo;

@Mapper
public interface WctbCustomerBaseBulkChangeMapper {

    List<WctbCustomerBaseBulkChangeDvo> selectBulkChangeObjects(WctbCustomerBaseBulkChangeDvo dvo);

    List<WctbCustomerBaseBulkChangeDvo> selectPlannerChanges(WctbCustomerBaseBulkChangeDvo dvo);

    String selectContractAddressId(String cntrNo, String cntrSn);

    String selectSellInflwChannelDetailCd(String prtnrNo, String ogTpCd);

    String selectSellOgTpCd(String cntrNo);

    WctbCustomerBaseBulkChangeDvo selectDateTime();

    WctbCustomerBaseBulkChangeDvo selectCrcdBeforeChange(String cntrNo, String cntrSn, String cntrStlmId);

    int insertContractChangeRcpBase(@Param("item")
    WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractChangeRcpBaseHist(WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractChangeRcpDtl(@Param("item")
    WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractChangeRcpDtlHist(WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractDetailHist(WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractBaseHist(WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractStlmRel(WctbCustomerBaseBulkChangeDvo dvo);

    int insertContractStlHist(WctbCustomerBaseBulkChangeDvo dvo);

    int updateContractAdrpcBase(WctbCustomerBaseBulkChangeDvo dvo);

    int updateContractDetail(WctbCustomerBaseBulkChangeDvo dvo);

    int updateContractBase(WctbCustomerBaseBulkChangeDvo dvo);

    int updateContractStlmRel(@Param("item")
    WctbCustomerBaseBulkChangeDvo dvo);

    int updateContractStlmBas(WctbCustomerBaseBulkChangeDvo dvo);

    int updateContractStlHist(WctbCustomerBaseBulkChangeDvo dvo);

}
