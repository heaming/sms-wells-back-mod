package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbContractChangeMgtMapper {
    PagingResult<SearchContractChangeRes> selectContractChanges(SearchContractChangeReq dto, PageInfo pageInfo);

    WctbContractChangeDvo selectCheckOgTpCd();

    List<WctbContractChangeDvo> selectCheckOrderChPrgs(String cntrNo, int cntrSn);

    WctbContractChangeDvo selectCntrOrderInfo(String cntrNo, int cntrSn);

    WctbContractChangeDvo selectCustomerInformation(String cntrNo, int cntrSn);

    WctbContractChangeDvo selectKiwiInstallOrders(String cntrNo, int cntrSn);

    int insertContractChRcpBase(@Param("item")
    WctbContractChangeDvo dvo);

    int insertContractChRcpDetail(@Param("item")
    WctbContractChangeDvo dvo);

    int updateContractAddrBase(WctbContractChangeDvo dvo);

    int updateContractAddrChangeHist(WctbContractChangeDvo dvo);

    int insertContractAddrChangeHist(WctbContractChangeDvo dvo);

    WctbContractChangeMngtDto.FindPartnerRes selectPartnerByCntrNo(String cntrNo, String cntrSn);

    WctbContractChangeDvo selectDateTime();

    int insertSellPartnerToCntrChRcpBas(@Param("dvo")
    WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChRcchHist(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChRcpDtl(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChRcpDchHist(WctbContractChangeDvo inputDvo);

    int updateSellPartnerToCntrBas(WctbContractChangeDvo inputDvo);

    int updateExSellPartnerToCntrChHist(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChHist(WctbContractChangeDvo inputDvo);

    int updateSellPartnerToCntrPrtnrRel(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrPrtnrRel(WctbContractChangeDvo inputDvo);
}
