package com.kyowon.sms.wells.web.service.visit.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.docs.dto.AttachFileDto;
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
        String searchDtFrom,
        String searchDtTo,
        String fshDtYn, /* 완료여부제외여부 */
        String acdnRcpNm,
        String searchOption /* 조회구분(1: 등록일자, 2: 완료일자) */
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
        String cnrldNo, /* 등록자번호(센터장번호) */
        String cnrldNm, /* 등록자명(센터장명) */
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
        String cntrDtlNo,
        String cstNm,
        String pdCd,
        String pdNm,
        String adrId,
        String istZip,
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
        String istDt,
        String rcpdt,
        String vstDt,
        String acdnDtm, //사고일시
        String acdnDt,
        String acdnTm,
        String svCnrOgId,
        String svCnrNm,
        String cnrldNo,
        String cnrldNm,
        String istLctDtlCn,
        String imptaRsonCd,
        String imptaRsonNm,
        String rcpMoCn,
        String acdnCausCn,
        String cstDmdCn,
        String acdnRsCn, /* 사고결과 */
        //진행상태 컬럼
        String fshDt,
        String cpsPrgsCd,
        String cpsPrgsNm,
        String agrDocFwYn, //합의서발신여부
        String agrDocRcvYn, //합의서수신여부
        String krnTotCpsAmtMrkNm,
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
        String maasFnm,
        String fmlRelDvNm1,
        String rfndAcnoEncr,
        String rfndBnkNm,
        String rfndAcownNm,
        String fmlRelDvNm2,
        String wrteDt,
        String acdnPhoApnDocId,
        String acdnPictrApnDocId,
        String causAnaApnDocId
    ) {
        public FindRes {
            //전화번호
            if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(exnoEncr)
                && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + exnoEncr + "-" + idvTno;
            } else if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + idvTno;
            }
            mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
            //계약상세번호
            cntrDtlNo = cntrNo + "-" + cntrSn;
            if (StringUtil.isNotBlank(acdnDtm)) {
                acdnDt = acdnDtm.substring(0, 8);
                acdnTm = acdnDtm.substring(8);
            }
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
        String krnTotCpsAmtMrkNm,
        int kwCpsAmt,
        String acdnRsCn
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
        String maasMpno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String rfndAcnoEncr,
        String rfndBnkCd,
        String rfndAcownNm,
        String agrDocFwYn,
        String rcpdt,
        String krnTotCpsAmtMrkNm,
        int totCpsAmt,
        @NotBlank
        String mpno,
        String cstNm,
        String cntrNo,
        String cntrSn,
        String pdNm
    ) {}

    @Builder
    @ApiModel(value = "WsnbSafetyAccidentDto-EditSignReq")
    public record EditSignReq(
        @NotBlank
        String acdnRcpId,
        String wrteDt
    ) {}

    @ApiModel(value = "WsnbSafetyAccidentDto-FindInitReq")
    public record FindInitReq(
        String cntrNo,
        String cntrSn
    ) {}

    @ApiModel(value = "WsnbSafetyAccidentDto-FindInitRes")
    public record FindInitRes(
        String cntrNo,
        String cntrSn,
        String cstNm,
        String pdNm,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno,
        String istDt, /* 설치일자 */
        String istZip,
        String istAdr,
        String istDtlAdr,
        String rcpdt,
        String svCnrOgId,
        String cnrldNm,
        String istLctDtlCn,
        String rcpMoCn
    ) {
        public FindInitRes {
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
    @ApiModel(value = "WsnbSafetyAccidentDto-SaveReq")
    public record SaveReq(

        String acdnRcpId,
        String acdnRcpNm,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String pdNm,
        String tno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String mpno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String istZip,
        String adrId,
        String adrDvCd,
        String istAdr,
        String istDtlAdr,
        String istDt,
        String rcpdt,
        String acdnDtm,
        String istLctDtlCn,
        String svCnrOgId,
        String svCnrNm,
        String cnrldNm,
        String imptaRsonCd,
        String cpsDvCd,
        String rcpMoCn,
        String acdnCausCn,
        String cstDmdCn,
        String acdnRsCn,
        String fshDt,
        String cpsPrgsCd,
        String krnTotCpsAmtMrkNm,
        int insrcoCpsAmt,
        int totCpsAmt,
        int kwCpsAmt,
        List<AttachFileDto.AttachFile> acdnPhoApnFile,
        List<AttachFileDto.AttachFile> acdnPictrApnFile,
        List<AttachFileDto.AttachFile> causAnaApnFile
    ) {
        public SaveReq {
            String[] tnoArr = StringUtils.split(tno, "-");
            if (tnoArr.length == 3) {
                locaraTno = tnoArr[0];
                exnoEncr = tnoArr[1];
                idvTno = tnoArr[2];
            } else if (tnoArr.length == 2) {
                locaraTno = tnoArr[0];
                idvTno = tnoArr[1];
            }
            String[] mpnoArr = StringUtils.split(mpno, "-");
            if (mpnoArr.length == 3) {
                cralLocaraTno = mpnoArr[0];
                mexnoEncr = mpnoArr[1];
                cralIdvTno = mpnoArr[2];
            } else if (mpnoArr.length == 2) {
                cralLocaraTno = mpnoArr[0];
                cralIdvTno = mpnoArr[1];
            }
        }
    }
}
