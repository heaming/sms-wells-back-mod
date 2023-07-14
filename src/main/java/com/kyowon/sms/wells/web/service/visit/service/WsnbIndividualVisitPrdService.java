package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBfsvcCrdovrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitPeriodRecrtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBfsvcCrdovrAsnService;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.allocate.service.WsncVisitPeriodRecrtService;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIndividualVisitPrdMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbIndividualVisitPrdService {

    private final WsnbIndividualVisitPrdMapper mapper;

    private final WsncBfsvcCrdovrAsnService wsncBfsvcCrdovrAsnService;

    private final WsnbCustomerRglrBfsvcDlService wsnbCustomerRglrBfsvcDlService;

    private final WsncVisitPeriodRecrtService wsncVisitPeriodRecrtService;

    private final MessageResourceService messageService;

    private final WsncRegularBfsvcAsnService wsncRegularBfsvcAsnService;

    public WsnbIndividualVisitPrdDto.SearchRes getCustomerInfo(WsnbIndividualVisitPrdDto.SearchReq dto) {
        return mapper.selectCustomerInfo(dto);
    }

    public List<WsnbIndividualVisitPrdDto.SearchVstRes> getVisits(WsnbIndividualVisitPrdDto.SearchReq dto) {
        return mapper.selectVisits(dto);
    }

    public List<WsnbIndividualVisitPrdDto.SearchPeriodRes> getPeriods(
        WsnbIndividualVisitPrdDto.SearchPeriodReq dto
    ) {
        return mapper.selectPeriods(dto);
    }

    /*
     * B/S 배정
     */
    @Transactional
    public int processBsAssign(WsnbIndividualVisitPrdDto.SearchProcessReq dto) throws Exception {
        WsncRegularBfsvcAsnDvo dvo = new WsncRegularBfsvcAsnDvo();
        dvo.setCntrNo(dto.cntrNo());
        dvo.setCntrSn(dto.cntrSn());
        dvo.setAsnOjYm(dto.asnOjYm());
        wsncRegularBfsvcAsnService.processRegularBfsvcAsn(dvo);
        return 1;
    }

    /*
     * B/S 배정 이월 Service Call
     * (W-SV-S-0076)
     */
    @Transactional
    public int processCarriedForward(WsnbIndividualVisitPrdDto.SearchProcessReq dto) throws Exception {
        List<WsncBfsvcCrdovrAsnDto.SaveReq> asnNoList = mapper.selectAsnNos(dto);

        for (WsncBfsvcCrdovrAsnDto.SaveReq param : asnNoList) {
            wsncBfsvcCrdovrAsnService.saveBfsvcCrdovrAsn(param);
        }

        return 1;
    }

    /*
     * B/S 배정 삭제 Service Call
     * (W-SV-S-0073)
     */
    @Transactional
    public int processBsDelete(WsnbIndividualVisitPrdDto.SearchProcessReq dto) throws Exception {
        List<WsnbCustomerRglrBfsvcDlDto.SaveReq> asnNoList = mapper.selectAsnNoTypeBs(dto);

        for (WsnbCustomerRglrBfsvcDlDto.SaveReq param : asnNoList) {
            wsnbCustomerRglrBfsvcDlService.removeRglrBfsvcDl(param);
        }

        return 1;
    }

    /*
     * B/S 강제배정
     */
    @Transactional
    public int processBsForceAssign(WsnbIndividualVisitPrdDto.SearchProcessReq dto) throws Exception {
        log.info("[WsnbIndividualVisitPrdService.processBsForceAssign] process start!");
        return 0;
    }

    /*
     * 방문주기 삭제
     */
    @Transactional
    public int processVisitPeriodDelete(WsnbIndividualVisitPrdDto.SearchProcessReq dto) throws Exception {
        //계약번호를 확인해주세요.
        BizAssert.hasText(dto.cntrNo(), "MSG_ALT_CHK_CNTR_NO");
        //계약일련번호를 확인해주세요.
        BizAssert.hasText(dto.cntrSn(), "MSG_ALT_CHK_CNTR_SN");
        BizAssert.hasText(
            dto.periodDeleteYmd(), "MSG_ALT_NCELL_REQUIRED_ITEM",
            new String[] {messageService.getMessage("MSG_TXT_CYCL_DELETE_DT")}
        );

        return mapper.deleteSvRgbsprIz(dto);
    }

    /*
     * 방문주기 재생성 Service Call
     * (W-SV-S-0067)
     */
    @Transactional
    public int processVisitPeriodRegen(WsnbIndividualVisitPrdDto.SearchProcessReq dto) throws Exception {
        //방문주기 삭제 후 생성
        processVisitPeriodDelete(dto);
        WsncVisitPeriodRecrtDto.SaveReq param = new WsncVisitPeriodRecrtDto.SaveReq(dto.cntrNo(), dto.cntrSn());
        return wsncVisitPeriodRecrtService.saveVisitPeriodRecrt(param);
    }

}
