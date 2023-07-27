package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractChRcchStatChangeDtlHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzTxinvRcpBaseChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaTaxInvoiceInquiryConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaTaxInvoiceInquiryDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaTaxInvoiceInquiryDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaTaxInvoiceInquiryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaTaxInvoiceInquiryService {

    private final WctaTaxInvoiceInquiryMapper mapper;
    private final WctaTaxInvoiceInquiryConverter converter;
    private final WctzHistoryService historyService;
    private final MessageResourceService messageResourceService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public WctaTaxInvoiceInquiryDvo getTaxInvoiceInquiry(String cntrNo, int cntrSn) {
        return mapper.selectTaxInvoiceInquiry(cntrNo, cntrSn);
    }

    @Transactional
    public String saveTaxInvoice(SaveReq dto) {
        WctaTaxInvoiceInquiryDvo dvo = converter.mapSaveReqToWctaTaxInvoiceInquiryDvo(dto);

        int result = 0;

        // 파라미터
        String cntrNo = dvo.getCntrNo();
        int cntrSn = dvo.getCntrSn();
        String bzrno = dvo.getBzrno(); // 사업자번호
        String txinvPblOjYn = dvo.getTxinvPblOjYn(); // 발행여부
        String emadr = dvo.getEmadr(); // 이메일
        String cralIdvTno = dvo.getCralIdvTno(); // 휴대지역전화번호
        String mexno = dvo.getMexno(); // 휴대전화국번호암호화
        String cralLocaraTno = dvo.getCralLocaraTno(); // 휴대개별전화번호
        String now = DateUtil.todayNnow();
        String nowDate = DateUtil.getNowDayString(); // 현재일자
        String nowYm = nowDate.substring(0, 6); // 현재년월
        String dlpnrPsicNm = dvo.getDlpnrPsicNm();
        int nowDay = Integer.parseInt(nowDate.substring(4, 6));// 현재일
        int txinvPblD = Integer.parseInt(dvo.getTxinvPblD()); // 발행일자
        String rtnMsg = ""; // 반환 메세지
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String sellTpCd = dvo.getSellTpCd(); // 판매유형코드

        boolean txinvPblOjInfoCheck = true; // 발행정보 변경 여부
        boolean txinvPblOjCheck = true; // 발행여부 변경 여부

        BizAssert.isFalse(StringUtils.isEmpty(bzrno) || (bzrno.length() < 10), "MSG_ALT_INVALID_BZRNO"); // 잘못된 사업자등록번호 입니다.

        WctaTaxInvoiceInquiryDvo compInvoice = mapper.selectTaxInvoiceInquiryCheck(cntrNo, cntrSn); // 원본 데이터
        BizAssert.isFalse(Objects.isNull(compInvoice), "MSG_ALT_NOT_FOUND_TXINV"); // 등록된 세금계산서를 찾을 수 없습니다.

        // 비교용
        String txinvPblOjYnComp = compInvoice.getTxinvPblOjYn();
        String emadrComp = compInvoice.getEmadr();
        String cralIdvTnoComp = compInvoice.getCralIdvTno();
        String mexnoComp = compInvoice.getMexno();
        String cralLocaraTnoComp = compInvoice.getCralLocaraTno();
        String cntrCnfmDtm = compInvoice.getCntrCnfmDtm();
        String cntrCnfmYm = StringUtils.isEmpty(cntrCnfmDtm) ? "" : cntrCnfmDtm.substring(0, 6);
        String dpTpCd = compInvoice.getDpTpCd();
        String dlpnrPsicNmComp = compInvoice.getDlpnrPsicNm();

        int txinvPblDComp = Integer.parseInt(compInvoice.getTxinvPblD());

        // 파라미터와 일치한 지 확인하는 구간
        if (txinvPblOjYn.equals(txinvPblOjYnComp) /// 발행정보 변경 여부
            && emadr.equals(emadrComp)
            && cralIdvTno.equals(cralIdvTnoComp)
            && mexno.equals(mexnoComp)
            && cralLocaraTno.equals(cralLocaraTnoComp)
            && dlpnrPsicNm.equals(dlpnrPsicNmComp)
            && txinvPblD == txinvPblDComp) {
            txinvPblOjInfoCheck = false;
        }
        if (txinvPblOjYn.equals(txinvPblOjYnComp)) { /// 발행여부 변경 여부
            txinvPblOjCheck = false;
        }

        if (!txinvPblOjCheck && !txinvPblOjInfoCheck) {
            throw new BizException(messageResourceService.getMessage("MSG_ALT_NO_CHG_CNTN")); // 변경된 내용이 없습니다.
        }

        if (txinvPblOjInfoCheck && "Y".equals(txinvPblOjYn)) {
            if (nowYm.equals(cntrCnfmYm)) {
                rtnMsg = messageResourceService.getMessage("MSG_ALT_TXINV_PBL_THM0_LAST_D"); // "0차월 발행은 말일 날짜로 발행되며, 익월 초 수신 가능합니다."
            } else if (nowDay >= txinvPblD || nowDay >= txinvPblDComp) {
                rtnMsg = messageResourceService.getMessage("MSG_ALT_TXINV_CHANGE_RFLT_NEXT_MM"); // 변경사항은 익월부터 반영됩니다.
            }
        }
        if (StringUtils.isNotEmpty(dpTpCd)) {
            BizAssert.isFalse(
                dpTpCd.startsWith("02") && "Y".equals(txinvPblOjYn), "MSG_ALT_CARD_FNT_CST_NOT_PBL",
                new String[] {messageResourceService.getMessage("MSG_TXT_TXINV")}
            ); // 카드이체 고객은 세금계산서 발행이 불가합니다.
        } else {
            log.debug("입금유형코드: {}", dpTpCd);
        }

        if (txinvPblOjCheck) {

            // 계약상세 수정
            result = mapper.updateCntrDetailTxinvPblOj(txinvPblOjYn, cntrNo, cntrSn);
            BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR"); // 저장에 실패하였습니다.
            // 계약상세변경이력 생성
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo
                    .builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histEndDtm(now)
                    .build()
            );

            String txinvPdDvCd = "";
            String txinvPblDvCd = "01";

            // 세금계산서접수기준내역 추가
            switch (sellTpCd) {
                case "1" -> txinvPdDvCd = "21";
                case "2" -> txinvPdDvCd = "23";
                case "3" -> txinvPdDvCd = "25";
                case "6" -> {
                    txinvPdDvCd = "27";
                    txinvPblDvCd = "02";
                }
                case "9" -> txinvPdDvCd = "22";
                default -> {}
            }
            dvo.setTxinvPdDvCd(txinvPdDvCd);
            dvo.setTxinvPblDvCd(txinvPblDvCd);
            dvo.setRcpdt(nowDate);
            dvo.setTxinvPblYn(dvo.getTxinvPblOjYn());
            dvo.setAplcPsicId(session.getEmployeeIDNumber());
            dvo.setDtaDlYn("N");
            dvo.setTxinvPblYn("Y");
            dvo.setMexnoEncr(dvo.getMexno());
            dvo.setCntrChFshDtm(now);

            result = mapper.updateTaxInvoiceInquiry(dvo);
            BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR"); // 저장에 실패하였습니다.
            // 세금계산서접수기준변경이력
            // mapper.insertTaxInvoiceReceiptBaseHist(dvo);
            historyService.createTaxInvoiceReceiptBaseChangeHistory(
                WctzTxinvRcpBaseChangeHistDvo
                    .builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .build()
            );

            String chRqrNm = session.getUserName();
            WctaTaxInvoiceInquiryDvo dateDvo = mapper.selectDateTime();

            String employeeId = dateDvo.getFstRgstUsrId();
            String fstRgstDeptId = dateDvo.getFstRgstDeptId();
            String fnlMdfcPrgId = dateDvo.getFnlMdfcPrgId();
            String fnlMdfcDeptId = dateDvo.getFnlMdfcDeptId();

            String telNo = cralLocaraTno + mexno + cralIdvTno;
            String cntrChPrgsMoCn = String.format(
                "발행구분:%s|담당자명:%s|전화번호:%s|전자메일:%s|발행일자:%s",
                txinvPblOjYn, dlpnrPsicNm, telNo, emadr, txinvPblD
            );

            /* 계약변경접수기본 */
            dvo.setCntrChRcpDtm(now); /* 계약변경접수일시 */
            dvo.setCntrChTpCd("205"); /* 계약변경유형코드 */
            dvo.setChRqrDvCd("1"); /* 변경요청자구분코드 */
            dvo.setChRqrNm(chRqrNm);
            dvo.setCstNo(dvo.getCntrCstNo());
            dvo.setCntrChAkCn(cntrChPrgsMoCn);
            dvo.setCntrChPrgsStatCd("20"); /* 계약변경진행상태코드 */
            dvo.setCntrChPrgsMoCn(cntrChPrgsMoCn); /* 계약변경진행메모내용 */
            dvo.setChRcstDvCd(null); /* 변경접수자구분코드 */
            dvo.setChRcpUsrId(employeeId); /* 변경접수사용자ID */
            dvo.setAprDtm(now); /* 승인일시 */
            dvo.setAprUsrId(employeeId); /* 승인사용자ID */
            dvo.setBizTfId(null); /* 업무이관ID */
            dvo.setDtaDlYn("N"); /* 데이터삭제여부 */
            dvo.setBfchCn(dvo.getBfchCn());

            dvo.setFstRgstDtm(now); // 최초등록일시
            dvo.setFstRgstUsrId(employeeId); // 최초등록유저ID
            dvo.setFstRgstPrgId(employeeId); // 최초등록프로그램ID
            dvo.setFstRgstDeptId(fstRgstDeptId); // 최초등록부서ID
            dvo.setFnlMdfcDtm(now); // 최종수정일시
            dvo.setFnlMdfcUsrId(employeeId); // 최종수정유저ID
            dvo.setFnlMdfcPrgId(fnlMdfcPrgId); // 최종수정프로그램ID
            dvo.setFnlMdfcDeptId(fnlMdfcDeptId); // 최종수정부서ID

            int baseRes = mapper.insertContractChangeReceipt(dvo);
            BizAssert.isFalse(baseRes <= 0, "MSG_ALT_SVE_ERR");

            baseRes = mapper.insertSellPartnerToCntrChRcchHist(dvo);
            BizAssert.isFalse(baseRes <= 0, "MSG_ALT_SVE_ERR");

            //            historyService.createContractChangeRcchStatChangeHistory(
            //                WctzCntrChRcchStatChangeHistDvo.builder()
            //                    .cntrChRcpId(dvo.getCntrChRcpId())
            //                    .cntrChPrgsStatCd(dvo.getCntrChPrgsStatCd())
            //                    .build()
            //            );

            /* 계약변경접수상세 */
            dvo.setCntrUnitTpCd("020"); /* 계약단위유형코드 */
            /* 계약변경상세사유코드 (기간변경:202)  */
            dvo.setCntrNo(null); /* 계약번호 */
            dvo.setDtlCntrNo(cntrNo); /* 계약상세번호 */
            dvo.setDtlCntrSn(cntrSn);
            dvo.setCntrChRsonDvCd("4");
            dvo.setCntrChRsonCd(""); /* TODO: 코드 추가 후 수정예정 */
            dvo.setCntrChAtcDvCd("");
            dvo.setChApyStrtdt(nowDate); /* 변경적용시작일자 */
            dvo.setChApyEnddt("99991231"); /* 변경적용종료일자 */
            dvo.setBfchCn(dvo.getBfchCn());
            dvo.setProcsYn("Y");
            dvo.setProcsDuedt(nowDate); /* 처리예정일자 */
            dvo.setProcsFshDtm(now); /* 처리완료일시 */

            int detailRes = mapper.insertContractChReceiptDetail(dvo);
            BizAssert.isFalse(detailRes <= 0, "MSG_ALT_SVE_ERR");

            historyService.createContractChRcchChangeDtlHistory(
                WctzContractChRcchStatChangeDtlHistDvo
                    .builder()
                    .cntrChRcpId(dvo.getCntrChRcpId())
                    .cntrChSn(dvo.getCntrChSn())
                    .histStrtDtm(dvo.getFnlMdfcDtm())
                    .fnlMdfcDtm(dvo.getFnlMdfcDtm())
                    .fnlMdfcUsrId(dvo.getFnlMdfcUsrId())
                    .fnlMdfcPrgId(dvo.getFnlMdfcPrgId())
                    .fnlMdfcDeptId(dvo.getFnlMdfcDeptId())
                    .fstRgstDtm(dvo.getFstRgstDtm())
                    .fstRgstUsrId(dvo.getFstRgstUsrId())
                    .fstRgstPrgId(dvo.getFstRgstPrgId())
                    .fstRgstDeptId(dvo.getFstRgstDeptId())
                    .build()
            );

        }
        return rtnMsg;
    }
}
