package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsAssignReqDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractReqDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;

@Mapper
public interface WsnbMultipleTaskOrderMapper {
    WsnbContractReqDvo selectContract(String cntrNo, String cntrSn);

    WsnbAsAssignReqDvo selectAsAssign(String cstSvAsnNo);

    WsnbMultipleTaskOrderDvo selectWorkRequidationItemization(String asIstOjNo);

    int selectCountAsIstAsnIz(WsnbMultipleTaskOrderDvo dvo);

    String selectWorkContent(String newSvBizDclsfCd);

    int selectCountChangeTotal(String cntrNo);

    int selectCountRangeChange(String cntrNO);

    int selectCountRangeChangeBs(String cntrNo);

    int selectWorkOutStorageCount(String cntrNo, String cntrSn);

    int selectCountAsIstOjIz(String asIstOjNo);

    WsnbMultipleTaskOrderDvo selectAsIstOjIzKey(WsnbMultipleTaskOrderDvo dvo);

    int insertErrorItemization(WsnbMultipleTaskOrderDvo dvo);

    String selectMexnoEncr();

    int insertIstObjectItemization(WsnbMultipleTaskOrderDvo dvo);

    int updateIstObjectItemization(WsnbMultipleTaskOrderDvo dvo);

    int updateIstObjectItemizationByPk(WsnbMultipleTaskOrderDvo dvo);

    int deleteAsPutItemIz(WsnbMultipleTaskOrderDvo dvo);

    int insertSdingIzTemp(String part);

    int insertSdingProcsTemp(WsnbMultipleTaskOrderDvo dvo);

    List<WsnbMultipleTaskOrderDvo> selectPutItems(WsnbMultipleTaskOrderDvo dvo);

    int insertAsPutItemIz(WsnbMultipleTaskOrderDvo dvo);

    WsnbMultipleTaskOrderDvo selectCustomerServiceAssignNo(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssignHist(String asnCstSvAsnNo);

    int deleteAsInstallationAssignIz(String cstSvAsnNo);

    /**/
    int deleteSdingShipping(WsnbMultipleTaskOrderDvo dvo);

    WsnbMultipleTaskOrderDvo selectNewAsnCstSvAsnNo(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssign(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssignHistByNewKey(WsnbMultipleTaskOrderDvo dvo);

    Optional<String> selectVstDtChk(String vstRqDt);

    WsnbMultipleTaskOrderDvo selectSdingCount();

    int selectSppPlanSn(WsnbMultipleTaskOrderDvo dvo);

    String selectSaleNm(String newSaleCd);

    int insertSdingPlan(WsnbMultipleTaskOrderDvo dvo);

    String selectPdSize(String newSaleCd);

    int insertSdingExpByAs(WsnbMultipleTaskOrderDvo dvo);

    int updateAsInstallationAssign(String asnCstSvAsnNo);

    int updateIstObjectKey(WsnbMultipleTaskOrderDvo dvo);

}
