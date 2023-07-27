package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCardSalesSlipsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDepositItemizationsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCustomerBaseDvo;

@Mapper
public interface WctaOrderDetailMapper {

    List<SearchPextCstInfoRes> selectRelatedContractsPextCstInfo(SearchReq dto);

    List<SearchNewCstInfoRes> selectRelatedContractsNewCstInfo(SearchReq dto);

    List<WctaOrderDetailCustomerBaseDvo> selectOrderDetailCustomerBase(SearchReq dto);

    List<SearchContractListsRes> selectOrderDetailContractLists(SearchReq dto);

    List<WctaDepositItemizationsDvo> selectDepositItemizations(SearchTradeSpecificationSheetReq dto);

    List<SearchTradeSpecificationSheetRes> selectTradeSpcshs(SearchTradeSpecificationSheetReq dto);

    List<WctaCardSalesSlipsDvo> selectCardSalesSlips(SearchTradeSpecificationSheetReq dto);

    List<SearchContractArticlesRes> selectContractArticles(SearchTradeSpecificationSheetReq dto);

    List<SearchContractsRes> selectContracts(SearchTradeSpecificationSheetReq dto);
}
