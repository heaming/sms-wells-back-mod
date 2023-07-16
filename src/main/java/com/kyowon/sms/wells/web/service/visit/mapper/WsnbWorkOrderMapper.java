package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAssignAsWorkDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;

@Mapper
public interface WsnbWorkOrderMapper {

    Optional<WsnbAssignAsWorkDvo> selectAsAssignByPk(String asIstOjNo);

    WsnbWorkOrderDvo selectAsAssignOganizationByPk(WsnbWorkOrderDvo dvo);

    int selectAsAssignCountByPk(WsnbWorkOrderDvo dvo);

    String selectWorkContent(String newSvBizDclsfCd);

    int selectCountChangeTotal(String cntrNo);

    int selectCountRangeChange(String cntrNO);

    int selectCountRangeChangeBs(String cntrNo);

    int selectWorkOutStorageCount(String cntrNo, String cntrSn);

    WsnbWorkOrderDvo selectAsIstOjIzKey(WsnbWorkOrderDvo dvo);

    int mergeInstallationObject(WsnbWorkOrderDvo dvo);

    int updateInstallationObjectMtrStatCd(WsnbWorkOrderDvo dvo);

    int deleteAsPutItem(WsnbWorkOrderDvo dvo);

    int insertSeedingTemp(String part);

    int insertSeedingProcsTemp(WsnbWorkOrderDvo dvo);

    List<WsnbWorkOrderDvo> selectPutItems(WsnbWorkOrderDvo dvo);

    int insertAsPutItem(WsnbWorkOrderDvo dvo);

    WsnbWorkOrderDvo selectCustomerServiceAssignNo(WsnbWorkOrderDvo dvo);

    int deleteAsInstallationAssign(String cstSvAsnNo);

    /**/
    int deleteSeedingShipping(WsnbWorkOrderDvo dvo);

    int insertAsInstallationAssign(WsnbWorkOrderDvo dvo);

    Optional<String> selectVstDtChk(String vstRqDt);

    WsnbWorkOrderDvo selectSdingCount();

    int selectSppPlanSn(WsnbWorkOrderDvo dvo);

    int insertSeedingPlan(WsnbWorkOrderDvo dvo);

    int insertSeedingExpByAs(WsnbWorkOrderDvo dvo);

    int updateAsInstallationAssign(String asnCstSvAsnNo);

    int updateInstallationObjectKey(WsnbWorkOrderDvo dvo);

    WsnbWorkOrderDvo selectCustomerServiceNewAssignNo(WsnbWorkOrderDvo dvo);
}
