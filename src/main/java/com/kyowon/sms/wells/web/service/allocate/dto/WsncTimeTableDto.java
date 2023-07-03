package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*타임테이블 조회(판매)*/
public class WsncTimeTableDto {

    @ApiModel(value = "WsncTimeTableDto-FindTimeAssignReq")
    public record FindTimeAssignReq(
        @NotBlank
        String chnlDvCd, // gbCd
        @NotBlank
        String svDvCd, // DATA_GB
        @NotBlank
        @ValidDate
        String sellDate, // SEL_DATE
        @NotBlank
        String svBizDclsfCd, // wrkTypDtl
        @NotBlank
        String cntrNo,
        String cntrSn,

        String inflwChnl,
        String basePdCd, // GDS_CD
        String wrkDt,
        String mtrStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo,
        String baseYm,
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncTimeTableDto-FindScheChoReq")
    public record FindScheChoReq(
        @NotBlank
        String chnlDvCd, // gbCd
        @NotBlank
        String svDvCd, // DATA_GB
        @NotBlank
        @ValidDate
        String sellDate, // SEL_DATE
        @NotBlank
        String svBizDclsfCd, // wrkTypDtl
        @NotBlank
        String cntrNo,
        String cntrSn,

        String inflwChnl,
        String basePdCd, // GDS_CD
        String wrkDt,
        String mtrStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo,
        String baseYm,
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String cstSvAsnNo,
        String prtnrNo,
        String newAdrZip
    ) {}

    @ApiModel(value = "WsncTimeTableDto-FindTimeChoReq")
    public record FindTimeChoReq(
        @NotBlank
        String chnlDvCd, // gbCd
        @NotBlank
        String svDvCd, // DATA_GB
        @NotBlank
        @ValidDate
        String sellDate, // SEL_DATE
        @NotBlank
        String svBizDclsfCd, // wrkTypDtl
        @NotBlank
        String cntrNo,
        String cntrSn,

        String inflwChnl,
        String basePdCd, // GDS_CD
        String wrkDt,
        String mtrStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo,
        String baseYm,
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String cstSvAsnNo,
        String prtnrNo,
        String newAdrZip
    ) {}

    @ApiModel(value = "WsncTimeTableDto-FindRes")
    public record FindRes(
        String baseYm,
        String dowDvCd,
        String svBizDclsfCd, //wrkTypDtl
        String inflwChnl, // inflwChnl
        String chnlDvCd, //gbCd
        String svDvCd, //dataGb
        String cntrNo,
        String cntrSn,
        String sellDate,
        String empId,
        String curDateTimeString,
        String wrkDt,
        String mtrStatCd, // P_DATA_STUS
        String basePdCd,
        String lcst09,
        String newAdrZip,
        String sowDay,
        String returnUrl,
        String mkCo,

        String userId,
        String rcpOgTpCd,

        String prtnrNo,
        String ogTpCd,

        String sidingYn, // 모종 여부
        String spayYn, // 일시불여부
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String sdingCombin,

        List<String> offDays,
        List<WsncTimeTableSidingDaysDvo> sidingDays, // list2
        List<WsncTimeTableDisableDaysDvo> disableDays, // diabledays
        WsncTimeTablePsicDvo psics, // left_info
        List<WsncTimeTableAssignTimeDvo> assignTimeDvos, // list1

        List<WsncTimeTableDaysDvo> days,
        List<WsncTimeTableSmPmNtDvo> smTimes,
        List<WsncTimeTableSmPmNtDvo> amTimes,
        List<WsncTimeTableSmPmNtDvo> pmTimes1,
        List<WsncTimeTableSmPmNtDvo> pmTimes2,
        List<WsncTimeTableSmPmNtDvo> ntTimes

    ) {
        public FindRes {
            psics.setExnoEncr(DbEncUtil.dec(psics.getExnoEncr()));
            psics.setMexnoEncr(DbEncUtil.dec(psics.getMexnoEncr()));
            psics.setSjHp2(DbEncUtil.dec(psics.getSjHp2()));
        }
    }

    @ApiModel(value = "WsncTimeTableDto-SidingDays")
    public record SidingDays(
        String title,
        String sumCnt,
        String st,
        String ed,
        String w3th,
        String ablDays,
        String sowDay
    ) {}

    @ApiModel(value = "WsncTimeTableDto-DisableDays")
    public record DisableDays(
        String disableDays,
        String disableFuldays,
        String tcMsg
    ) {}

    @ApiModel(value = "WsncTimeTableDto-MonthSchedule")
    public record MonthSchedule(
        String title,
        String st,
        String ed
    ) {}

    @ApiModel(value = "WsncTimeTableDto-PsicData")
    public record Psics(
        String prtnrNo,
        String sellDate,
        String iscgubNm,
        String rolDvNm,
        String rolDvNm2,
        String sjHp1,
        String sjHp2,
        String sjHp3,
        String rpbLocaraCd,
        String ogNm,
        String ogId,
        String prtnrKnm,
        String prtnrKnm2,
        String vstDowVal,
        String degNm,
        String instCnt,
        String bsCnt,
        String asCnt,
        String satWrkYn,
        String dfYn,
        String dowDvCd,
        String fr2pLgldCd,
        String rstrCndtUseYn,
        String udsnUseYn,
        String vstPos,
        String rsbDvCd,
        String amWrkCnt,
        String pmWrkCnt,
        String tWrkCnt,
        String empPic,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {}

    @ApiModel(value = "WsncTimeTableDto-SmPmNt")
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

    @ApiModel(value = "WsncTimeTableDto-Days")
    public record Days(
        String baseY, /* 기준연도 */
        String baseMm, /* 기준월 */
        String baseD, /* 기준일 */
        String dowDvCd, /* 요일구분코드 */
        String dfYn, /* 휴무여부 */
        String phldYn, /* 공휴일여부 */
        String rmkCn/* 비고내용 */
    ) {}

    @ApiModel(value = "WsncTimeTableDto-AssignTime")
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
}
