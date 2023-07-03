package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*
타임테이블 일정선택
매니저 AS 신청하는 타임테이블 캘린더
W-MP-U-0186P01
*/
public class WsncTimeTableSchdChoDto {

    @ApiModel(value = "WsncTimeTableSchdChoDto-FindReq")
    public record FindReq(
        @NotBlank
        String cntrNo,
        String cntrSn,
        String chnlDvCd,
        String sellDate,
        String svDvCd,
        String svBizDclsfCd,
        String prtnrNo,
        String ordDt,
        String ordSeq,
        String newAdrZip,
        @NotBlank
        String baseYm
    ) {}

    @ApiModel(value = "WsncTimeTableSchdChoDto-FindRes")
    public record FindRes(
        String newAdrZip,
        String svBizDclsfCd,
        String chnlDvCd,
        String svDvCd,
        String cntrNo,
        String cntrSn,
        String sellDate,
        String ordDt,
        String ordSeq,
        String empId,
        String basePdCd,
        String sidingYn,

        List<WsncTimeTableSchdChoDto.TimeTableDays> days,
        List<WsncTimeTableSchdChoDto.SidingDays> sidingDay,
        List<WsncTimeTableSchdChoDto.MonthSchedule> monthSchedule,
        List<WsncTimeTableSchdChoDto.DisableDays> disableDays
    ) {}

    @ApiModel(value = "WsncTimeTableSchdChoDto-TimeTableDays")
    public record TimeTableDays(
        String baseY, /* 기준연도 */
        String baseMm, /* 기준월 */
        String baseD, /* 기준일 */
        String dowDvCd, /* 요일구분코드 */
        String dfYn, /* 휴무여부 */
        String phldYn, /* 공휴일여부 */
        String rmkCn/* 비고내용 */
    ) {}

    @ApiModel(value = "WsncTimeTableSchdChoDto-SidingDays")
    public record SidingDays(
        String title,
        String sumCnt,
        String st,
        String ed,
        String w3th,
        String ablDays,
        String sowDay
    ) {}

    @ApiModel(value = "WsncTimeTableSchdChoDto-MonthSchedule")
    public record MonthSchedule(
        String title,
        String st,
        String ed
    ) {}

    @ApiModel(value = "WsncTimeTableSchdChoDto-DisableDays")
    public record DisableDays(
        String disableDays,
        String disableFuldays,
        String tcMsg
    ) {}

    @ApiModel(value = "WsncTimeTableSchdChoDto-SmPmNt")
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

    @ApiModel(value = "WsncTimeTableSchdChoDto-PsicData")
    public record PsicData(
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

}
