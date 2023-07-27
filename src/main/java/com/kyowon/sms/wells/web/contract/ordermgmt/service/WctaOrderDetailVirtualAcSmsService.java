package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SaveReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SearchReq;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractNotifyFowrdindHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailVirtualAcSmsConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailVirtualAcSmsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailVirtualAcSmsMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailVirtualAcSmsService {

    private final WctaOrderDetailVirtualAcSmsMapper mapper;
    private final WctaOrderDetailVirtualAcSmsConverter converter;
    private final TemplateService templateService;
    private final SmsMessageService smsMessageService;
    private final WctzHistoryService historyService;

    public WctaOrderDetailVirtualAcSmsDvo getVirtualAccountTemplate(SearchReq dto) throws Exception {
        String templateId = "TMP_CTA_VIRTUAL_ACCOUNT_GUIDE";
        WctaOrderDetailVirtualAcSmsDvo dvo = mapper.selectVirtualAcCustomer(dto);

        String cstNm = StringUtils.defaultString(dvo.getCstNm());
        String cntrNo = StringUtils.defaultString(dvo.getCntrNo());
        int cntrSn = dvo.getCntrSn();
        String vacBnkNm = StringUtils.defaultString(dvo.getVacBnkNm());
        String vacNo = StringUtils.defaultString(dvo.getVacNo());

        String template = templateService.getTemplateContent(
            templateId, Map.of(
                "cstNm", cstNm,
                "cntrNo", cntrNo,
                "cntrSn", cntrSn,
                "vacBnkNm", vacBnkNm,
                "vacNo", vacNo
            )
        );

        dvo.setTemplate(template);
        return dvo;
    }

    public int saveVirtualAccountMessages(SaveReq dto) throws Exception {
        int msgId = 0;
        WctaOrderDetailVirtualAcSmsDvo dvo = converter.mapSaveReqToOrderDetailVirtualAcSmsDvo(dto);

        String now = DateUtil.getNowString();

        String cntrNo = dvo.getCntrNo();
        int cntrSn = dvo.getCntrSn();
        String cstNm = dvo.getCstNm();

        String msgTit = dvo.getMsgTit();
        String template = dvo.getTemplate();

        String cralLocaraTno = dvo.getCralLocaraTno();
        String mexnoEncr = dvo.getMexnoEncr();
        String cralIdvTno = dvo.getCralIdvTno();
        String phone = cralLocaraTno + mexnoEncr + cralIdvTno;

        String destInfo = String.format("%s^%s", cstNm, phone);

        SmsSendReqDvo smsSendReqDvo = SmsSendReqDvo.withContents()
            .subject(msgTit)
            .msgContent(template)
            .destInfo(destInfo)
            .build();

        msgId += smsMessageService.sendMessageAndGetInfo(smsSendReqDvo).get(0).getMsgId();

        if (msgId > 0) {

            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

            String employeeUserId = session.getEmployeeIDNumber();
            String userName = session.getUserName();

            String templateCd = "VIRTUAL_ACCOUNT_SMS";

            String notyFwId = historyService.createContractNotifyFowrdindHistory(
                WctzContractNotifyFowrdindHistDvo.builder()
                    .notyFwTpCd("30") // 알림발송유형코드
                    .notyFwBizDvCd("10") // 알림발송업무구분코드
                    .akUsrId(employeeUserId) // 요청자 ID
                    .rqrNm(userName)// 요청자명
                    .akDtm(now) // 요청일시
                    .fwDtm(now) // 발송일시
                    // .msgTit(title) // 메세지 제목  ( 메세지 내용, 제목은 식별키 값으로 이메일 테이블 join 해서 내용 조회 가능)
                    // .msgCn(sendDvo.getContent()) // 메세지 내용
                    .cntrNo(cntrNo) // 계약번호
                    .cntrSn(cntrSn) // 계약일련번호
                    .fwLkIdkVal(Integer.toString(msgId)) // 발송연계식별키값
                    .fwOjRefkVal1(templateCd) // 발송대상참조키값1
                    .rcvrNm(cstNm) // 수신자명
                    .rcvrLocaraTno(cralLocaraTno) // 수신자지역전화번호
                    .rcvrExnoEncr(mexnoEncr) // 수신자전화국번호암호화
                    .rcvrIdvTno(cralIdvTno) // 수신자개별전화번호
                    .notyFwRsCd("10") // 알림발송결과코드
                    .dtaDlYn("N") // 삭제여부
                    .build(),
                false
            );
        }
        return 1;
    }
}
