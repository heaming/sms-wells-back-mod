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
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIndividualVisitPrdDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIndividualVisitPrdMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
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

        //계약번호를 확인해주세요.
        BizAssert.hasText(dto.cntrNo(), "MSG_ALT_CHK_CNTR_NO");
        //계약일련번호를 확인해주세요.
        BizAssert.hasText(dto.cntrSn(), "MSG_ALT_CHK_CNTR_SN");
        BizAssert.hasText(
            dto.asnOjYm(), "MSG_ALT_NCELL_REQUIRED_ITEM",
            new String[] {messageService.getMessage("MSG_TXT_ASN_YM")}
        );

        WsnbIndividualVisitPrdDvo dvo = mapper.selectValidBsForceAssign(dto);

        if("Y".equals(dvo.getValidYn1())){
            throw new BizException("MSG_ALT_ASN_DATA_EXISTS"); //기존 배정 데이터가 있습니다.
        } else if("Y".equals(dvo.getValidYn2()) || "Y".equals(dvo.getValidYn3()) || "Y".equals(dvo.getValidYn4())){
            throw new BizException("MSG_ALT_CANNOT_ASN_VALID_1"); //현재월에 배정건이 있어 강제배정할 수 없습니다.
        } else if("Y".equals(dvo.getValidYn5())){
            throw new BizException("MSG_ALT_CANNOT_ASN_VALID_2"); //미완료 배정건이 없어 강제배정 할 수 없습니다.
        }

        mapper.insertBsForceAssign(dto);

        //해당월, 해당계약으로 BS정기배정 서비스 수행 (W-SV-S-0074를 수행)
        processBsAssign(dto);

        return 1;
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
        WsncVisitPeriodRecrtDto.SaveReq param = new WsncVisitPeriodRecrtDto.SaveReq(dto.cntrNo(), dto.cntrSn(), dto.periodDeleteYmd());
        return wsncVisitPeriodRecrtService.saveVisitPeriodRecrt(param);
    }

}
