package com.kyowon.sms.wells.web.service.allocate.dto;

import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;

import java.util.List;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Slf4j
public class WsncTimeTableDto {

    private static String defineInflwChnl(String chnlDvCd) {
        // in_gb
        return StringUtil.decode(
            chnlDvCd,
            "C", "1", // CubicCC(CustomerCenter)
            "W", "4", // 웰스 홈페이지
            "K", "3", // KSS
            "P", "5", // K-MEMBERS
            "" // I: 엔지니어, E: 엔지니어, M: 매니저, B: BS(엔지니어)
        );
    }

    @Builder
    @ApiModel(value = "WsncTimeTableDto-FindTimeAssignReq")
    public record FindTimeAssignReq(
        @NotBlank
        String chnlDvCd, // gbCd
        @NotBlank
        String svDvCd, // DATA_GB
        @NotBlank
        @ValidDate
        String sellDate, // SEL_DATE
        @NotEmpty
        String svBizDclsfCd, // wrkTypDtl
        String cntrNo,
        String cntrSn,

        String inflwChnl,
        String basePdCd, // GDS_CD
        String pdctPdCd,

        String wrkDt,

        @NotBlank
        String mtrStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo,
        String baseYm,
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String cstSvAsnNo,
        String newAdrZip,
        String contDt,
        String copnDvCd,
        String sellDscDbCd,
        String sdingCombin,
        String vstDvCd
    ) {
        public FindTimeAssignReq {
            wrkDt = StringUtil.isEmpty(wrkDt) ? DateUtil.getNowDayString() : wrkDt;
            inflwChnl = defineInflwChnl(chnlDvCd);
            mtrStatCd = StringUtil.isEmpty(mtrStatCd) ? "1" : mtrStatCd;
            //            log.debug("chnlDvCd: {}", chnlDvCd);
            //            log.debug("svDvCd: {}", svDvCd);
            //            log.debug("sellDate: {}", sellDate);
            //            log.debug("svBizDclsfCd: {}", svBizDclsfCd);
            //            log.debug("cntrNo: {}", cntrNo);
            //            log.debug("cntrSn: {}", cntrSn);
            //            log.debug("inflwChnl: {}", inflwChnl);
            //            log.debug("basePdCd: {}", basePdCd);
            //            log.debug("wrkDt: {}", wrkDt);
            //            log.debug("mtrStatCd: {}", mtrStatCd);
            //            log.debug("userId: {}", userId);
            //            log.debug("mkCo: {}", mkCo);
            //            log.debug("baseYm: {}", baseYm);
            //            log.debug("seq: {}", seq);
            //            log.debug("cstSvAsnNo: {}", cstSvAsnNo);
            //            log.debug("returnUrl: {}", returnUrl);

        }
    }

    @Builder
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
        @NotBlank
        String mtrStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo,
        String baseYm,
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String cstSvAsnNo,
        String prtnrNo,
        String newAdrZip
    ) {
        public FindScheChoReq {
            wrkDt = StringUtil.isEmpty(wrkDt) ? DateUtil.getNowDayString() : wrkDt;
            inflwChnl = defineInflwChnl(chnlDvCd);
            mtrStatCd = StringUtil.isEmpty(mtrStatCd) ? "1" : mtrStatCd;

            log.debug("chnlDvCd: {}", chnlDvCd);
            log.debug("svDvCd: {}", svDvCd);
            log.debug("sellDate: {}", sellDate);
            log.debug("svBizDclsfCd: {}", svBizDclsfCd);
            log.debug("cntrNo: {}", cntrNo);
            log.debug("cntrSn: {}", cntrSn);
            log.debug("inflwChnl: {}", inflwChnl);
            log.debug("basePdCd: {}", basePdCd);
            log.debug("wrkDt: {}", wrkDt);
            log.debug("mtrStatCd: {}", mtrStatCd);
            log.debug("userId: {}", userId);
            log.debug("mkCo: {}", mkCo);
            log.debug("baseYm: {}", baseYm);
            log.debug("seq: {}", seq);
            log.debug("cstSvAsnNo: {}", cstSvAsnNo);
            log.debug("prtnrNo: {}", prtnrNo);
            log.debug("newAdrZip: {}", newAdrZip);
            log.debug("returnUrl: {}", returnUrl);
        }
    }

    @Builder
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
        @NotBlank
        String mtrStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo,
        String baseYm,
        String seq, // P_IN_GB + P_WRK_GB + P_WRK_DT + LEFTPAD(P_SEQ, 8,"0")
        String cstSvAsnNo,
        String prtnrNo,
        String newAdrZip,
        String orgDt,
        String ordSeq
    ) {
        public FindTimeChoReq {

            wrkDt = StringUtil.isEmpty(wrkDt) ? DateUtil.getNowDayString() : wrkDt;
            inflwChnl = defineInflwChnl(chnlDvCd);
            mtrStatCd = StringUtil.isEmpty(mtrStatCd) ? "1" : mtrStatCd;

            log.debug("chnlDvCd: {}", chnlDvCd);
            log.debug("svDvCd: {}", svDvCd);
            log.debug("sellDate: {}", sellDate);
            log.debug("svBizDclsfCd: {}", svBizDclsfCd);
            log.debug("cntrNo: {}", cntrNo);
            log.debug("cntrSn: {}", cntrSn);
            log.debug("inflwChnl: {}", inflwChnl);
            log.debug("basePdCd: {}", basePdCd);
            log.debug("wrkDt: {}", wrkDt);
            log.debug("mtrStatCd: {}", mtrStatCd);
            log.debug("userId: {}", userId);
            log.debug("mkCo: {}", mkCo);
            log.debug("baseYm: {}", baseYm);
            log.debug("seq: {}", seq);
            log.debug("cstSvAsnNo: {}", cstSvAsnNo);
            log.debug("prtnrNo: {}", prtnrNo);
            log.debug("newAdrZip: {}", newAdrZip);
            log.debug("returnUrl: {}", returnUrl);
        }
    }

    @ApiModel(value = "WsncTimeTableDto-FindRes")
    public record FindRes(
        String svDvCd,
        String chnlDvCd,
        String sellDate,
        String newAdrZip,
        String cntrNo,
        String cntrSn,
        String inflwChnl,
        String basePdCd,
        String[] basePdCds,
        String pdctPdCd,
        String prtnrNo,
        String prtnrNo01,
        String prtnrNoBS01,
        String prtnrNoOwr01,
        String empId,
        String hcrYn,
        @NotBlank
        String mtrStatCd,
        String exYn,
        String contDt,
        String wrkDt,
        String ogTpCd,
        String rcpOgTpCd,
        String userId,
        String returnUrl,
        String baseYm,
        String seq,
        String localGb,
        String mkCo,
        String vstDowValCd,
        String basePdNm,
        String pdctPdNm,
        String pdGrpCd,
        String pdGrpNm,
        String cntrDt,
        String copnDvCd,
        String sellDscDbCd,
        String lcst09,
        String vstDvCd,
        String cstSvAsnNo,
        String sdingCombin, // LCST09
        String sidingYn,
        String spayYn,
        String sowDay, // PAJONG_DAY
        String rpbLocaraCd,
        boolean isHcr,
        List<String> offDays,
        List<SidingDays> sidingDays,
        List<DisableDays> disableDays,
        Psic psic,
        List<AssignTime> assignTimes,
        List<Days> days,
        List<MonthSchedule> monthSchedules,
        List<SmPmNt> smTimes,
        List<SmPmNt> amTimes,
        List<SmPmNt> pmTimes1,
        List<SmPmNt> pmTimes2,
        List<SmPmNt> ntTimes
    ) {
        public FindRes {
            wrkDt = StringUtil.isEmpty(wrkDt) ? DateUtil.getNowDayString() : wrkDt;
            inflwChnl = defineInflwChnl(chnlDvCd);
            mtrStatCd = StringUtil.isEmpty(mtrStatCd) ? "1" : mtrStatCd;

            log.debug("chnlDvCd: {}", chnlDvCd);
            log.debug("svDvCd: {}", svDvCd);
            log.debug("sellDate: {}", sellDate);
            log.debug("cntrNo: {}", cntrNo);
            log.debug("cntrSn: {}", cntrSn);
            log.debug("inflwChnl: {}", inflwChnl);
            log.debug("basePdCd: {}", basePdCd);
            log.debug("wrkDt: {}", wrkDt);
            log.debug("mtrStatCd: {}", mtrStatCd);
            log.debug("userId: {}", userId);
            log.debug("mkCo: {}", mkCo);
            log.debug("baseYm: {}", baseYm);
            log.debug("seq: {}", seq);
            log.debug("cstSvAsnNo: {}", cstSvAsnNo);
            log.debug("returnUrl: {}", returnUrl);
        }
    }

    @ApiModel(value = "WsncTimeTableDto-SidingDays")
    public record SidingDays(
        String title,
        String sumCnt,
        String st,
        String ed,
        String w3th,
        String enableDays,
        String sowDay // PAJONG_DAY
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

    @ApiModel(value = "WsncTimeTableDto-Psic")
    public record Psic(
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
    ) {
        public Psic {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
            sjHp2 = DbEncUtil.dec(sjHp2);
        }
    }

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
