package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMembershipBulkChangeDvo;

@Mapper
public interface WctbMembershipBulkChangeMapper {
    List<SearchRes> selectMembershipBulkChanges(String cntrNo, String cntrSn, String rfdt);

    SearchCntrRes selectMembershipBulkChangesContracts(String cntrNo, String cntrSn);

    String selectFirstBznsYn();

    WctbMembershipBulkChangeDvo selectDateTime();

    String selectLstmmLstDays();

    int updateCntrDtlSvcCncl(WctbMembershipBulkChangeDvo dvo);

    int updateCntrWellsDtlHomeCareJDtCh(WctbMembershipBulkChangeDvo dvo);

    int updateCntrDtlHomeCareJDtCh(WctbMembershipBulkChangeDvo dvo);

    int updateCntrBasHomeCareJDtCh(WctbMembershipBulkChangeDvo dvo);

    int updateCntrBasGeMshJDtCh(WctbMembershipBulkChangeDvo dvo);

    int updateCntrDtlGeMshJDtCh(WctbMembershipBulkChangeDvo dvo);

    int updateCntrDtlreceiptCncl(WctbMembershipBulkChangeDvo dvo);

    int updateCntrDtlStdFeeAmt(WctbMembershipBulkChangeDvo dvo);

    int insertCntrChRcpBas(@Param("item")
    WctbMembershipBulkChangeDvo dvo);

    int insertCntrChRcpDtl(@Param("item")
    WctbMembershipBulkChangeDvo dvo);

    int updateCntrDetailStatChangeHist(WctbMembershipBulkChangeDvo dvo);

    int insertCntrDetailStatChangeHist(WctbMembershipBulkChangeDvo dvo);

    int updateCntrDchHist(WctbMembershipBulkChangeDvo dvo);

    int insertCntrDchHist(WctbMembershipBulkChangeDvo dvo);

    int updateContractWellsDetailHist(WctbMembershipBulkChangeDvo dvo);

    int insertContractWellsDetailHist(WctbMembershipBulkChangeDvo dvo);

    int updateCntrChHist(WctbMembershipBulkChangeDvo dvo);

    int insertCntrChHist(WctbMembershipBulkChangeDvo dvo);

    int updateCntrChRcchHist(WctbMembershipBulkChangeDvo dvo);

    int insertCntrChRcchHist(WctbMembershipBulkChangeDvo dvo);

    int updateCntrChRcpDchHist(WctbMembershipBulkChangeDvo dvo);

    int insertCntrChRcpDchHist(WctbMembershipBulkChangeDvo dvo);
}
