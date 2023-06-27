package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnbSafetyAccidentDto {

    @ApiModel(value = "WsnbSafetyAccidentDto-SearchReq")
    public record SearchReq(
        String device, /* 접속화면 */
        String vstDt, /* as센터명 */
        String svcCnrId, /* 제품명 */
        String vstDtFrom,
        String vstDtTo,
        String searchType, /* 검색조건 */
        String cstNm, /* 고객명 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String pdNm, /* 상품명 */
        String svCnrOgId,
        String cpsPrgsCd, /* 보상진행 */
        String imptaRsonCd, /* 귀책여부 */
        String totCpsAmt, /* 총보상액 */
        String rgstDtmFrom,
        String rgstDtmTo,
        String fshDtFrom,
        String fshDtTo,
        String fshDtYn, /* 완료여부제외여부 */
        String acdnRcpNm
    ) {}

    @ApiModel(value = "WsnbSafetyAccidentDto-SearchRes")
    public record SearchRes(
        String acdnRcpId,
        String acdnRcpNm,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String rcpdt,
        String acdnDtm, //사고일시
        String agrDocRcvYn, //합의서수신여부
        String pdNm,
        String istAdr,
        String istDtlAdr,
        String istReferAdr,
        String cpsPrgsCd,
        String cpsPrgsNm,
        String fstRgstUsrId,
        String fshDt,
        String vstDt,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno,
        String slDt,
        String fstRgstDtm,
        String rcpMoCn,
        String acdnCausCn,
        String cstDmdCn,
        String acdnRsCn,
        String fnlMdfcDtm,
        String rptrNm,
        String svCnrNm,
        String brchNm,
        String imptaRsonCd,
        String imptaRsonNm,
        int totCpsAmt,
        int kwCpsAmt,
        int insrcoCpsAmt

    ) {
        public SearchRes {
            if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(exnoEncr)
                && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + exnoEncr + "-" + idvTno;
            } else if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + idvTno;
            }
            mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
        }
    }

    @ApiModel(value = "WsnbSafetyAccidentDto-FindRes")
    public record FindRes(
        String acdnRcpId,
        String acdnRcpNm,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String pdCd,
        String pdNm,
        String istAdr,
        String istDtlAdr,
        String istReferAdr,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno,
        String slDt,
        String rcpdt,
        String vstDt,
        String acdnDtm, //사고일시
        String svCnrNm,
        String brchNm,
        String cnrldNm,
        String istLctDtlCn,
        String imptaRsonNm,
        String rcpMoCn,
        String acdnCausCn,
        String cstDmdCn,
        //진행상태 컬럼
        String fshDt,
        String cpsPrgsNm,
        String agrDocFwYn,
        int totCpsAmt,
        int kwCpsAmt,
        int insrcoCpsAmt,
        String psicNm,
        String vstIz,
        String damgIz,
        String estIz,
        String agrIz,
        int totRduAmt,
        String cpsDvCd,
        String cstSignCn,
        String maasBirthdate,
        String maasFnm,
        String fmlRelDvNm1,
        String rfndAcnoEncr,
        String rfndBnkNm,
        String rfndAcownNm,
        String fmlRelDvNm2,
        String wrteDt
    ) {
        public FindRes {
            if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(exnoEncr)
                && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + exnoEncr + "-" + idvTno;
            } else if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + idvTno;
            }
            mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
        }
    }

    @Builder
    @ApiModel(value = "WsnbSafetyAccidentDto-EditReq")
    public record EditReq(
        @NotBlank
        String acdnRcpId,
        String fshDt,
        String cpsPrgsCd,
        int insrcoCpsAmt,
        String agrDocFwYn,
        int totCpsAmt,
        int kwCpsAmt,
        String psicNm,
        String vstIz,
        String damgIz,
        String estIz,
        String agrIz,
        int totRduAmt
    ) {}

    @Builder
    @ApiModel(value = "WsnbSafetyAccidentDto-BiztalkReq")
    public record BiztalkReq(
        @NotBlank
        String acdnRcpId,
        String fmlRelDvCd1,
        String etcCn1,
        String fmlRelDvCd2,
        String etcCn2,
        String maasFnm,
        String maasBirthdate,
        String maasMpno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String rfndAcnoEncr,
        String rfndBnkCd,
        String rfndAcownNm,
        String agrDocFwYn,
        String rcpdt,
        int totCpsAmt,
        @NotBlank
        String mpno,
        String cstNm
    ) {}

    @Builder
    @ApiModel(value = "WsnbSafetyAccidentDto-EditSignReq")
    public record EditSignReq(
        @NotBlank
        String acdnRcpId,
        String wrteDt
    ) {}
}
