package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsAssignReqDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractReqDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallationObjectSaveDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;

@Mapper
public interface WsnbMultipleTaskOrderMapper {
    WsnbContractReqDvo selectContract(String cntrNo, String cntrSn);

    WsnbAsAssignReqDvo selectAsAssignByPk(String asIstOjNo);

    WsnbMultipleTaskOrderDvo selectAsAssignOganizationByPk(String asIstOjNo);

    int selectAsAssignCountByPk(WsnbMultipleTaskOrderDvo dvo);

    String selectWorkContent(String newSvBizDclsfCd);

    int selectCountChangeTotal(String cntrNo);

    int selectCountRangeChange(String cntrNO);

    int selectCountRangeChangeBs(String cntrNo);

    int selectWorkOutStorageCount(String cntrNo, String cntrSn);

    WsnbMultipleTaskOrderDvo selectAsIstOjIzKey(WsnbMultipleTaskOrderDvo dvo);

    int insertInstallationObjectError(WsnbMultipleTaskOrderDvo dvo);

    String selectMexnoEncr(String userId);

    int insertInstallationObject(WsnbMultipleTaskOrderDvo dvo);

    int updateInstallationObject(WsnbInstallationObjectSaveDvo dvo);

    int updateInstallationObjectDtaStatCd(WsnbMultipleTaskOrderDvo dvo);

    int deleteAsPutItem(WsnbMultipleTaskOrderDvo dvo);

    int insertSeedingTemp(String part);

    int insertSeedingProcsTemp(WsnbMultipleTaskOrderDvo dvo);

    List<WsnbMultipleTaskOrderDvo> selectPutItems(WsnbMultipleTaskOrderDvo dvo);

    int insertAsPutItem(WsnbMultipleTaskOrderDvo dvo);

    WsnbMultipleTaskOrderDvo selectCustomerServiceAssignNo(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssignHist(String asnCstSvAsnNo);

    int deleteAsInstallationAssign(String cstSvAsnNo);

    /**/
    int deleteSeedingShipping(WsnbMultipleTaskOrderDvo dvo);

    WsnbMultipleTaskOrderDvo selectNewAsnCstSvAsnNo(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssign(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssignHistByNewKey(WsnbMultipleTaskOrderDvo dvo);

    Optional<String> selectVstDtChk(String vstRqDt);

    WsnbMultipleTaskOrderDvo selectSdingCount();

    int selectSppPlanSn(WsnbMultipleTaskOrderDvo dvo);

    int insertSeedingPlan(WsnbMultipleTaskOrderDvo dvo);

    int insertSeedingExpByAs(WsnbMultipleTaskOrderDvo dvo);

    int updateAsInstallationAssign(String asnCstSvAsnNo);

    int updateInstallationObjectKey(WsnbMultipleTaskOrderDvo dvo);

    WsnbInstallationObjectSaveDvo selectSaveInstallationObject(WsnbMultipleTaskOrderDvo dvo);

}