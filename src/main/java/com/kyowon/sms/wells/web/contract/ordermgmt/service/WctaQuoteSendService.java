package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaQuoteSendConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaQuoteSendDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaQuoteSendDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaQuoteSendMapper;
import com.sds.sflex.common.common.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaQuoteSendService {
    private final WctaQuoteSendMapper mapper;
    private final WctaQuoteSendConverter converter;
    // Dependency Injection
    private final KakaoMessageService kakaoMessageService;
    // Dependency Injection
    private final EmailService emailService;
    private final TemplateService templateService;

    public List<WctaQuoteSendDto.SearchRes> getQuoteSendHists(WctaQuoteSendDto.SearchReq dto) {
        return mapper.selectQuoteSendHists(dto);
    }

    public WctaQuoteSendDto.SearchInfRes getQuoteSendInf(WctaQuoteSendDto.SearchReq dto) {
        return converter.mapWctaQuoteSendDvoToSearchInfRes(mapper.selectQuoteSendInf(dto));
    }

    public int saveQuoteSend(WctaQuoteSendDto.SaveReq dto) throws Exception {
        int processCount = 0;
        WctaQuoteSendDvo dvo = converter.mapSaveReqToWctaQuoteSendDvo(dto);
        if (dvo.getFwTpCd().equals("01")) {
            // 알림톡 메시지 발송
            Map<String, Object> paramMap = new HashMap<>();
            String recpTno = dvo.getRecpTelNo1() + dvo.getRecpTelNo2()
                + dvo.getRecpTelNo3();
            String sendTno = dvo.getSendTelNo1() + dvo.getSendTelNo2()
                + dvo.getSendTelNo3();
            String prtnrTno = dvo.getPrtnrCralLocaraTno() + dvo.getPrtnrMexnoEncr()
                + dvo.getPrtnrCralIdvTno();
            paramMap.put("cnrtNm", dvo.getCntrCstNm()); // 고객명
            paramMap.put("cnrtDiv", dvo.getCopnDvNm()); // 고객구분
            paramMap.put("prdtNm", dvo.getBasePdNm()); // 제품명
            paramMap.put("dcTyp", dvo.getSellDscTpCd()); // 유형/약정
            paramMap.put("cyclUsg", dvo.getSvPrd()); //주기/용도
            paramMap.put("alln", dvo.getAlncmpCdNm()); //제휴
            paramMap.put("rgfePrc", dvo.getCntrAmt()); //등록비
            paramMap.put("nrmlPrc", dvo.getPdBaseAmt()); //정상가
            paramMap.put("dcAplyPrc", dvo.getFnlAmt()); //할인적용가
            paramMap.put("linkUrl", dvo.getLinkUrl()); //URL
            paramMap.put("selrNm", dvo.getPrtnrKnm()); //판매자명
            paramMap.put("sendPhoneNo", prtnrTno); //판매자번호

            KakaoSendReqDvo kakaoDvo = KakaoSendReqDvo.withTemplateCode()
                .templateCode("wells17946")
                .templateParamMap(paramMap)
                .destInfo(dvo.getCntrCstNm() + "^" + recpTno)
                .callback(sendTno)
                .reserved1(dvo.getFwTpCd())
                .reserved2(dvo.getCntrNo())
                .reserved3(dvo.getCntrSn())
                .build();
            processCount += kakaoMessageService.sendMessage(kakaoDvo);
        }
        if (dvo.getFwTpCd().equals("02")) {
            // 공통함수 호출
            String templateId = "TMP_CTA_QUOTE_DOC_GUIDE";
            EmailSendReqDvo emailDvo = EmailSendReqDvo.builder()
                .title(templateService.getTemplateByTemplateId(templateId).getSendTemplateTitle())
                .content(
                    templateService.getTemplateContent(
                        templateId, Map.of(
                            "cnrtNm", dvo.getCntrCstNm(),
                            "linkUrl", dvo.getLinkUrl(),
                            "selrNm", dvo.getCntrCstNm(),
                            "selrPhoneNo", dvo.getPrtnrCralLocaraTno() + "-" + dvo.getPrtnrMexnoEncr() + "-"
                                + dvo.getPrtnrCralIdvTno()
                        )
                    )
                )
                .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dvo.getRecpMail())))
                .build();
            String emailUid = emailService.sendEmail(emailDvo);
            processCount += 1;
        }

        return processCount;
    }
}
