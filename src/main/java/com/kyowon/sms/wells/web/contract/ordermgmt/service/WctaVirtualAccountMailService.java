package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractNotifyFowrdindHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaVirtualAccountMailDto;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaVirtualAccountMailService {
    private final EmailService emailService;
    private final TemplateService templateService;
    private final WctzHistoryService historyService;

    @Transactional
    public int saveVirtualAccountMail(WctaVirtualAccountMailDto.SaveReq dto) throws Exception {

        // TODO: 명세서 이메일 발송 마스터 테이블에 INSERT
        // TODO: 신규 발송번호 채번하면서, 파라미터로 받은 데이터를 저장한다.

        // 채번한 발송번호로 PDF 접근키를 생성한다.
        String decStr = "채번한 발송번호";
        String encStr = Base64.encodeBase64String(decStr.getBytes());

        // 템플릿코드를 불러와서 이메일을 전송(등록)한다.
        String templateId = "TMP_CTA_VIRTUAL_ACCOUNT_CFDC"; // WELLS 가상계좌확인서 안내메일
        String pdfUrl = "http://wellsorder.kyowon.co.kr/specView/vrtlMail/" + encStr;
        String titleStr = "가상계좌확인서";

        EmailSendReqDvo emailDvo = EmailSendReqDvo.builder()
            .title(templateService.getTemplateByTemplateId(templateId).getSendTemplateTitle())
            .content(
                templateService.getTemplateContent(
                    templateId,
                    Map.of("titleStr", titleStr, "pdfUrl", pdfUrl)
                )
            )
            .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dto.mailAddr())))
            .build();

        String emailUid = emailService.sendEmail(emailDvo);

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String now = DateUtil.getNowString();

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
                .fwLkIdkVal(emailUid) // 발송연계식별키값
                .fwOjRefkVal1("VIRTUAL_ACCOUNT_CFDC") // 발송대상참조키값1
                .rcvrNm(dto.custNm()) // 수신자명
                .rcvrEmadr(dto.mailAddr()) // 수신자 이메일
                .notyFwRsCd("10") // 알림발송결과코드
                .dtaDlYn("N") // 삭제여부
                .build(),
            false
        );

        return 1;
    }
}
