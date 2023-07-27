package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaSpectxBlkPrntConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpectxBlkPrntDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaSpectxBlkPrntMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaSpectxBlkPrntService {
    private final WctaSpectxBlkPrntMapper mapper;
    private final WctaSpectxBlkPrntConverter converter;
    private final TemplateService templateService;
    private final EmailService emailService;

    public List<SearchRes> getSpectxBlkPrnts(SearchReq dto) {
        return mapper.selectSpectxBlkPrnts(dto);
    }

    public List<SearchRes> getSpectxBlkPrntsExcelDwonload(SearchReq dto) {
        return mapper.selectSpectxBlkPrnts(dto);
    }

    public SearchCntrRes getTradeSpcshCst(String cntrNo, String cntrSn) {
        return mapper.selectTradeSpcshCst(cntrNo, cntrSn);
    }

    @Transactional
    public String saveSpectxBlkPrntsGrpNo() {
        WctaSpectxBlkPrntDvo dvo = new WctaSpectxBlkPrntDvo();
        int res = mapper.insertSsctSpectxIsBas(dvo);
        return dvo.getSpectxGrpNo();
    }

    @Transactional
    public int saveSpectxBlkPrnts(List<SaveReq> dtos) {
        int res = 0;
        String spectxGrpNoCheck = "";
        for (SaveReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveReqToWctaSpectxBlkPrntDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                        spectxGrpNoCheck = dvo.getSpectxGrpNo();
                        mapper.updateSsctSpectxIsBasFirst(dvo);
                        mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                    }
                    int result = mapper.insertSsctSpectxIsDtl(dvo);
                    mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                        spectxGrpNoCheck = dvo.getSpectxGrpNo();
                        mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                        mapper.updateSsctSpectxIsBas(dvo);
                    }
                    mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
                    int result = mapper.updateSsctSpectxIsDtl(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
            }
            ;
        }
        return res;
    }

    @Transactional
    public int removeSpectxBlkPrnts(List<SaveReq> dtos) {
        int res = 0;
        for (SaveReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveReqToWctaSpectxBlkPrntDvo(dto);
            mapper.updateSsctSpectxIsDtlDtaDlY(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            if (mapper.selectSpectxGrpNoCheck(dvo.getSpectxGrpNo()) <= 0) {
                mapper.updateSsctSpectxIsBasDtaDlY(dvo.getSpectxGrpNo());
                mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
            }
        }
        return res;
    }

    public List<WctaSpectxBlkPrntDto.SpectxFwRes> getTradeSpcshFwInqrs(WctaSpectxBlkPrntDto.SpectxFwReq dto) {
        return mapper.selectTradeSpcshFwInqrs(dto);
    }

    @Transactional
    public int saveTradeSpcshFws(List<WctaSpectxBlkPrntDto.SaveTradeSpcshFwReq> dtos) throws Exception {
        int res = 0;
        for (WctaSpectxBlkPrntDto.SaveTradeSpcshFwReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveTradeSpcshFwReqToWctaSpectxBlkPrntDvo(dto);
            String templateId = "TMP_CTA_WELLS_TRD_SPCSH_GUD";

            String sysDate = DateUtil.getNowString();
            String decStr = "";
            decStr = dvo.getSlClYm() + "01/" + dvo.getSlClYm() + "/" + dvo.getDpTpCd() + "// " + dvo.getCstNm() + "/"
                + dvo.getSpectxGrpNo();
            String encStr = Base64.encodeBase64String(decStr.getBytes());
            encStr = encStr.replaceAll("/", "slash");
            String perdStr = sysDate.substring(0, 4) + "년 "
                + sysDate.substring(4, 6) + "월";
            String pdfUrl = "http://wellsorder.kyowon.co.kr/specView/" + encStr;

            EmailSendReqDvo sendDvo = EmailSendReqDvo.builder()
                .title(templateService.getTemplateByTemplateId(templateId).getSendTemplateTitle())
                .content(
                    templateService.getTemplateContent(
                        templateId, Map.of(
                            "perdStr", perdStr,
                            "pdfUrl", pdfUrl
                        )
                    )
                )
                .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dto.emadr())))
                .build();

            mapper.insertSsctSpectxPblHistSend(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            res += mapper.updateSsctSpectxIsDtlChSn(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
        }

        return res;
    }
}
