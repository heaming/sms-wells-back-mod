package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractNotifyFowrdindHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractDocumentMailConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDocumentMailDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractDocumentMailDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractDocumentMailDvo.Contract;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractDocumentMailMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractDocumentMailService {

    private final WctaContractDocumentMailMapper mapper;
    private final WctaContractDocumentMailConverter converter;
    private final TemplateService templateService;
    private final EmailService emailService;

    private final WctzHistoryService historyService;

    public String getContractCstNm(String cntrNo, int cntrSn) {
        return mapper.selectContractCstNm(cntrNo, cntrSn);
    }

    @Transactional
    public int saveDcevdnMlTrss(SaveReq dto) throws Exception {
        WctaContractDocumentMailDvo dvo = converter.saveReqToWctaContractDocumentMailDvo(dto);
        List<Contract> cntrList = dvo.getCstList();
        for (Contract cntrDvo : cntrList) {
            String cntrNoFull = cntrDvo.getCntrDtlNo();

            String cntrNo = cntrDvo.getCntrNo();
            String cntrSn = cntrDvo.getCntrSn();
            String docDvCd = dvo.getDocDvCd();
            String email = dvo.getRecpMail();
            String cstNm = dvo.getCstKnm();

            String title = "";
            String titleStr = "";
            String templateCd = "EVIDENCE_DOC_GUIDE";
            String templateId = "TMP_CTA_EVIDENCE_DOC_GUIDE";

            switch (docDvCd) {
                case "1" -> {
                    title = "WELLS 입금내역서 안내메일";
                    titleStr = "입금내역서";
                }
                case "2" -> {
                    title = "WELLS 거래명세서 안내메일";
                    titleStr = "거래명세서";
                }
                case "3" -> {
                    title = "WELLS 카드매출전표 안내메일";
                    titleStr = "카드매출전표";
                }
                case "4" -> {
                    title = "WELLS 계약사항 안내메일";
                    titleStr = "계약사항";
                }
                default -> {}
            }
            /* TODO: 증빙서류 테이블 추가 후 작업 예정 */
            // 채번한 발송번호로 PDF 접근키를 생성한다.
            //        String decStr = "채번한 발송번호";
            //        String encStr = Base64.encodeBase64String(decStr.getBytes());

            String workNo = "";

            String pdfUrl = "http://wellsorder.kyowon.co.kr/specView/mail/" + workNo; /* TODO: 주소 변경 될 가능성 있음 */

            String perdStr = dvo.getStartDt() + " ~ " + dvo.getEndDt();

            EmailSendReqDvo sendDvo = EmailSendReqDvo.builder()
                .title(title)
                .content(
                    templateService.getTemplateContent(
                        templateId, Map.of(
                            "titleStr", titleStr,
                            "sndgDiv", docDvCd,
                            "perdStr", perdStr,
                            "pdfUrl", pdfUrl,
                            "cstNm", cstNm
                        )
                    )
                )
                .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(email)))
                .build();

            String emailUid = emailService.sendEmail(sendDvo);
            String now = DateUtil.getNowString();

            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

            String employeeUserId = session.getEmployeeIDNumber();
            String userName = session.getUserName();

            String notyFwId = historyService.createContractNotifyFowrdindHistory(
                WctzContractNotifyFowrdindHistDvo.builder()
                    .notyFwTpCd("10") // 알림발송유형코드
                    .notyFwBizDvCd("10") // 알림발송업무구분코드
                    .akUsrId(employeeUserId) // 요청자 ID
                    .rqrNm(userName)// 요청자명
                    .akDtm(now) // 요청일시
                    .fwDtm(now) // 발송일시
                    // .msgTit(title) // 메세지 제목  ( 메세지 내용, 제목은 식별키 값으로 이메일 테이블 join 해서 내용 조회 가능)
                    // .msgCn(sendDvo.getContent()) // 메세지 내용
                    .cntrNo(cntrNo) // 계약번호
                    .cntrSn(Integer.parseInt(cntrSn)) // 계약일련번호
                    .fwLkIdkVal(emailUid) // 발송연계식별키값
                    .fwOjRefkVal1(templateCd) // 발송대상참조키값1
                    .rcvrNm(cstNm) // 수신자명
                    .rcvrEmadr(email) // 수신자 이메일
                    .notyFwRsCd("10") // 알림발송결과코드
                    .dtaDlYn("N") // 삭제여부
                    .build(),
                false
            );
        }
        return 1;
    }
}
