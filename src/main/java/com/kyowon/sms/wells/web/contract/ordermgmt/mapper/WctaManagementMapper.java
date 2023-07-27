package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaManagementDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaManagementMapper {
    List<SearchKssOrdrListRes> selectKssOrdrList(SearchKssOrdrListReq dto);

    List<SearchRePromConcListRes> selectRePromConcList(SearchKssOrdrListReq dto);

    List<SearchEmployeePurchaseListRes> selectEmployeePurchaseList(SearchKssOrdrListReq dto);

    List<WctaLspyOrdrDtptListDvo> selectLspyOrdrDtptList(String cntrNo);

    List<WctaBhOrdrDtptListDvo> selectBhOrdrDtptList(String cntrNo);

    List<WctaRentOrdrDtptListDvo> selectRentOrdrDtptList(String cntrNo, String cntrPrgsStatCd);

    List<WctaMbOrdrDtptListDvo> selectMbOrdrDtptList(String cntrNo);

    List<WctaHcsOrdrDtptListDvo> selectHcsOrdrDtptList(String cntrNo);

    List<WctaPlantsOrdrDtptListDvo> selectPlantsOrdrDtptList(String cntrNo);

    List<WctaRglrDlvrOrdrDtptListDvo> selectRglrDlvrOrdrDtptList(String cntrNo);

    List<WctaRentOrdrDtptListDvo> selectLtmIstmOrdrDtptList(String cntrNo, String cntrPrgsStatCd);

    List<SearchOrdrInfo4ReqDfntRes> selectOrdrInfo4ReqDfntList(String cntrNo, String cntrSn);

    List<SearchDfntAckdReqListRes> selectDfntAckdReqList(WctaDfntAckdReqDvo paramMap);

    List<SearchOrderStatCdInfoRes> selectOrderStatCdInfo(String cntrNo);

    int updateAprvDfntAckdReq(String cntrAprId);

    List<WctaMastOrdrDtptDvo> selectMastOrdrDtpt(String cntrNo, String cntrSn);
}
