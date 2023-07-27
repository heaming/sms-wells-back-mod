package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductChangeDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractChangeRcpBasDvo;

@Mapper
public interface WctiShippingProductChangeMapper {

    Optional<WctiContractChangeRcpBasDvo> selectShippingProduct(WctiShippingProductChangeDto.SaveReq dto);

    int insertCntrChRcpBas(@Param("item") WctiContractChangeRcpBasDvo dvo);
    int intertCntrChRcchHist(String cntrChRcpId);

    int insertCntrChRcpDtl(@Param("item") WctiContractChangeRcpBasDvo dvo);
    int intertCntrChRcpDchHist(String cntrChRcpId, String cntrChSn);

    int updateCntrPdRel(String cntrPdRelId);

    int insertCntrPdRel(WctiContractChangeRcpBasDvo dvo);
}
