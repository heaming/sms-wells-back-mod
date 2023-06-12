package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;

@Mapper
public interface WsnbMultipleTaskOrderMapper {

    int selectCountItemizationByItemGroup(String cntrNo);

    int selectCountItemizationByCntrNoB(String cntrNo);

    WsnbMultipleTaskOrderDvo selectWorkRequidationItemization(String asIstOjNo);

    String selectRcgvpKnm(String cntrNo, String cntrSn);

    int selectCountInstallationDate(String cntrNo, String cntrSn);

    int selectCountRequidationDate(String cntrNo, String cntrSn);

    int selectCountAsIstAsnIz(WsnbMultipleTaskOrderDvo dvo);

    int selectCountStopDate(String cntrNo, String cntrSn);

    String selectWorkContent(String newSvBizDclsfCd);

    int selectUseMonth(String vstRqdt, String cntrNo, String cntrSn);

    int selectCountChangeTotal(String cntrNo);

    int selectCountRangeChange(String cntrNO);

    int selectCountRangeChangeBs(String cntrNo);

    int selectCountItem(String cntrNo);

    int selectCountAsIstOjIz(String asIstOjNo);

    WsnbMultipleTaskOrderDvo selectAsIstOjIzKey(WsnbMultipleTaskOrderDvo dvo);

    WsnbMultipleTaskOrderDvo selectPdCd(WsnbMultipleTaskOrderDvo dvo);

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

    String selectItemGr(String newPdCd);

    /**/
    int deleteSidingShipping(WsnbMultipleTaskOrderDvo dvo);

    WsnbMultipleTaskOrderDvo selectNewAsnCstSvAsnNo(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssign(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssignHistByNewKey(WsnbMultipleTaskOrderDvo dvo);

    String selectVstDtChk(String vstRqDt);
    //
    //    int updateAsInstallationAssign(WsnbMultipleTaskOrderDvo dvo);

}
