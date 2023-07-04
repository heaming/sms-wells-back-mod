package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
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
        String svDvCd,
        String chnlDvCd,
        String sellDate,
        String newAdrZip,
        String cntrNo,
        String cntrSn,
        String inflwChnl,
        String basePdCd,
        String pdctPdCd,
        String prtnrNo,
        String prtnrNo01,
        String prtnrNoBS01,
        String prtnrNoOwr01,
        String empId,
        String hcrYn,
        String mtrStatCd,
        String basePdCdList,
        String exYn,
        String contDt,
        String wrkDt,
        String ogTpCd,
        String rcpOgTpCd,
        String userId,
        String dataStatCd,
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
        String vstGb,
        String cstSvAsnNo,
        String sdingCombin, // LCST09
        String sidingYn,
        String spayYn,
        String sowDay,
        String rpbLocaraCd,
        boolean isHcr,
        List<String> offDays,
        List<WsncTimeTableDto.SidingDays> sidingDays,
        List<WsncTimeTableDto.DisableDays> disableDays,
        WsncTimeTableDto.Psic psic,
        List<WsncTimeTableDto.AssignTime> assignTimes,
        List<WsncTimeTableDto.Days> days,
        List<WsncTimeTableDto.MonthSchedule> monthSchedules,
        List<WsncTimeTableDto.SmPmNt> smTimes,
        List<WsncTimeTableDto.SmPmNt> amTimes,
        List<WsncTimeTableDto.SmPmNt> pmTimes1,
        List<WsncTimeTableDto.SmPmNt> pmTimes2,
        List<WsncTimeTableDto.SmPmNt> ntTimes
    ) {
        @Override
        public String inflwChnl() {
            if (StringUtil.isEmpty(inflwChnl)) {
                String inflwChnlTmp = "";
                switch (chnlDvCd) {
                    case "C":
                        inflwChnlTmp = "1";
                        break;
                    case "W":
                        inflwChnlTmp = "2";
                        break;
                    case "K":
                        inflwChnlTmp = "3";
                        break;
                    case "P":
                        inflwChnlTmp = "2";
                        break;
                }
                return inflwChnlTmp;
            }
            return inflwChnl;
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

    @ApiModel(value = "WsncTimeTableDto-Psics")
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
        @Override
        public String exnoEncr() {
            return DbEncUtil.dec(exnoEncr);
        }

        @Override
        public String mexnoEncr() {
            return DbEncUtil.dec(mexnoEncr);
        }

        @Override
        public String sjHp2() {
            return DbEncUtil.dec(sjHp2);
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
