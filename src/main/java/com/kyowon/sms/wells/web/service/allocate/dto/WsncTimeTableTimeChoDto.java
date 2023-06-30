package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*
타임테이블 일정선택
매니저 AS 신청하는 타임테이블 캘린더
W-MP-U-0186P01
*/
public class WsncTimeTableTimeChoDto {

    @ApiModel(value = "WsncTimeTableTimeChoDto-FindReq")
    public record FindReq(
        String newAdrZip,
        @NotBlank
        String cntrNo,
        String cntrSn,
        String chnlDvCd,
        String sellDate,
        String svDvCd,
        String svBizDclsfCd,
        String pdctPdCd,
        String basePdCd,
        String hcrYn, // add_gb
        String prtnrNo,
        String ordDt,
        String ordSeq,
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-FindRes")
    public record FindRes(
        WsncTimeTableTimeChoDto.psicData psicDatas, // left_info
        List<WsncTimeTableTimeChoDto.AssignTime> assignTimes, // list1
        List<WsncTimeTableSmPmNtDvo> arrSm,
        List<WsncTimeTableSmPmNtDvo> arrAm,
        List<WsncTimeTableSmPmNtDvo> arrPm1,
        List<WsncTimeTableSmPmNtDvo> arrPm2,
        List<WsncTimeTableSmPmNtDvo> arrNt
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-AssignTime")
    public record AssignTime(
        String vstDt,
        String empId,
        String tm,
        String wrkCnt,
        String wrkTCnt,
        String wrkCCnt,
        String wrkTChk,
        String wrkTRn,
        String wrkNextChk,
        String wrkNextChk2,
        String wrkChk2,
        String wrkChk1Rn,
        String empTWrkCnt,
        String degWrkCnt,
        String tWrkCnt
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-SmPmNt")
    public record SmPmNt(
        String time,
        String wrkCnt,
        String enableYn,
        String wrkTCnt,
        String wrkCCnt,
        String wrkTChk,
        String wrkTRn,
        String wrkNextChk,
        String wrkNextChk2,
        String wrkChk2,
        String wrkChk1Rn,
        String empTWrkCnt,
        String degWrkCnt,
        String totalWrkCnt
    ) {}

    @Builder
    @ApiModel(value = "WsncTimeTableTimeChoDto-psicDatas")
    public record psicData(
        String prtnrNo, //ac021EmpId;
        String sellDate, // ac221CfrmDt;
        String iscgubNm,
        String rolDvNm,
        String rolDvNm2,
        String sjHp1,
        String sjHp2,
        String sjHp3,
        String rpbLocaraCd, // ac146LocalGb;
        String ogNm, // ac125DeptNm;
        String ogId, // ac125DeptCd;
        String prtnrKnm, // ac021EmpNm;
        String prtnrKnm2, // ac021EmpNm1;
        String vstDowVal, // ac146VstCycl
        String degNm,
        String instCnt,
        String bsCnt,
        String asCnt,
        String satWrkYn, // ac146Sat13WrkYn;
        String dfYn, // co160OffdayGb;
        String dowDvCd, // co160Days;
        String fr2pLgldCd, // ac112AdmCd;
        String rstrCndtUseYn, // ac146TtbUse;
        String udsnUseYn, // ac146UaUse;
        String vstPos,
        String rsbDvCd, // ac025EmpOr;
        String amWrkCnt,
        String pmWrkCnt,
        //--------------------------
        String totalWrkCnt,
        //--------------------------
        String empPic,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {}
}
