package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInstallationShippingDto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaInstallationShippingConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaInstallationShippingDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaInstallationShippingMapper;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbWorkOrderInterfaceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaInstallationShippingService {

    private final WctaInstallationShippingMapper mapper;
    private final WctaInstallationShippingConverter converter;
    private final WctzHistoryService historyService;
    private final WsnbWorkOrderInterfaceService interfaceService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    /* TODO: 인터페이스 생성 후 재작업 예정 */
    private static final String TIMEASSIGN_URL = "/W/SV/EAI_WSVI1003/req";

    public PagingResult<SearchIstShippingRes> getInstallationShippingsPages(
        SearchIstShippingReq dto, PageInfo pageinfo
    ) {

        WctaInstallationShippingDvo dvo = converter.mapSearchReqToWctaIstShippingDvo(dto);
        String istPcsvDvCd = dvo.getIstPcsvDvCd();
        PagingResult<SearchIstShippingRes> resultDto = new PagingResult<>();

        PagingResult<WctaInstallationShippingDvo> shippings = mapper.selectInstallationShippings(dvo, pageinfo);

        if (CollectionUtils.isNotEmpty(shippings) && "1".equals(istPcsvDvCd)) {
            List<WctaInstallationShippingDvo> kiwiInstallOrders = mapper.selectKiwiInstallOrders(shippings.getList());

            for (WctaInstallationShippingDvo shipping : shippings) {
                shipping.setProfile(activeProfile);
                shipping.setHasKiwiOrd(false);

                if (CollectionUtils.isEmpty(kiwiInstallOrders)) {
                    resultDto.add(converter.mapWctaIstShippingDvoToSearchRes(shipping));
                } else {
                    kiwiInstallOrders.stream()
                        .filter(
                            data -> data.getCntrNo().equals(shipping.getCntrNo())
                                && data.getCntrSn().equals(shipping.getCntrSn())
                        )
                        .forEach(item -> {
                            shipping.setRcpdt(item.getRcpdt());
                            shipping.setAsIstOjNo(item.getAsIstOjNo());
                            shipping.setCnslMoCn(item.getCnslMoCn());
                            shipping.setEggrAsnDt(item.getEggrAsnDt());
                            shipping.setWkAcpteStatCd(item.getWkAcpteStatCd());
                            shipping.setWkAcpteDt(item.getWkAcpteDt());
                            shipping.setWkAcpteHh(item.getWkAcpteHh());
                            shipping.setVstExpHh(item.getVstExpHh());
                            shipping.setVstCnfmdt(item.getVstCnfmdt());
                            shipping.setVstCnfmHh(item.getVstCnfmHh());
                            shipping.setWkPrgsStatCd(item.getWkPrgsStatCd());
                            shipping.setWkCanMoCn(item.getWkCanMoCn());
                            shipping.setOgNm(item.getOgNm());
                            shipping.setEgerNm(item.getEgerNm());
                            shipping.setEgerCrallocaraTno(item.getEgerCrallocaraTno());
                            shipping.setEgerCralIdvTno(item.getEgerCralIdvTno());
                            shipping.setEgerMexnoEncr(item.getEgerMexnoEncr());
                            shipping.setRetrTrgtYn(item.getRetrTrgtYn());
                            shipping.setInChnlDvCd(item.getInChnlDvCd());
                            shipping.setSvBizHclsfCd(item.getSvBizHclsfCd());
                            shipping.setSvBizDclsfCd(item.getSvBizDclsfCd());
                            shipping.setHasKiwiOrd(true);
                        });
                    resultDto.add(converter.mapWctaIstShippingDvoToSearchRes(shipping));
                }
            }
        } else {
            resultDto = converter.mapAllWctaIstShippingDvoToSearchRes(shippings);
        }
        resultDto.setPageInfo(shippings.getPageInfo());
        return resultDto;
    }

    @Transactional
    public String saveAssignProcessings(SaveAssignProcessingReq dto) throws Exception {
        WctaInstallationShippingDvo dvo = converter.mapSaveAsnProcsReqToWctaIstShippingDvo(dto);

        String cntrNo = dvo.getCntrNo();
        String cntrSn = dvo.getCntrSn();
        String prtnrNo = dvo.getPrtnrNo();
        String inputGb = dvo.getInputGb();
        String wkGb = dvo.getWkGb();
        String workDt = dvo.getWorkDt();
        String asIstOjNo = dvo.getAsIstOjNo();
        String acpgDiv = dvo.getAcpgDiv();
        String basePdCd = dvo.getBasePdCd();
        String istPcsvDvCd = dvo.getIstPcsvDvCd();
        String mnftCoId = dvo.getMnftCoId();
        String svBizDclsfCd = dvo.getSvBizDclsfCd();
        String prdDiv = dvo.getPrdDiv();
        String inChnlDvCd = dvo.getInChnlDvCd();
        String cnslMoCn = dvo.getCnslMoCn();
        String svBizHclsfCd = dvo.getSvBizHclsfCd();

        dvo.setCntrDtlNo(dvo.getCntrNo() + dvo.getCntrSn());
        String cntrDtlNo = dvo.getCntrDtlNo();

        String checkYn = getProcessingBfChecks(
            new SearchAssignProcessingReq(
                cntrNo,
                cntrSn,
                prtnrNo,
                inputGb,
                wkGb,
                workDt,
                asIstOjNo,
                acpgDiv,
                basePdCd,
                istPcsvDvCd,
                mnftCoId,
                svBizDclsfCd,
                prdDiv
            )
        );
        if ("Y".equals(checkYn)) {
            List<CreateOrderReq> req = new ArrayList<>();
            req.add(
                CreateOrderReq.builder()
                    .asIstOjNo(asIstOjNo)
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .svBizDclsfCd(svBizDclsfCd)
                    .inChnlDvCd(inChnlDvCd)
                    .mtrStatCd(prdDiv)
                    .svBizHclsfCd(svBizHclsfCd)
                    .build()
            );

            List<CreateOrderRes> result = interfaceService
                .createWorkOrders(req);

            if (CollectionUtils.isNotEmpty(result)) {
                return "Y";
            }
        }
        return "N";
    }

    @Transactional
    public String editDueDates(EditDueDateReq dto) {
        WctaInstallationShippingDvo dvo = converter.mapEditDueDateReqoWctaIstShippingDvo(dto);
        WctaInstallationShippingDvo checkDvo = mapper.selectSameSppDueDtNotSetCheck(dvo);
        if (!ObjectUtils.isEmpty(checkDvo)) {
            int res = mapper.updateSppDuedt(dvo);
            BizAssert.isTrue(res > 0, "MSG_ALT_SVE_ERR");

            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(dvo.getCntrNo())
                    .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                    .build()
            );

            return "Y";
        }
        return "N";
    }

    @Transactional
    public String editDueDateCancels(EditDueDateCancelReq dto) {
        WctaInstallationShippingDvo dvo = converter.mapEditDueDateCancelReqToWctaIstShippingDvo(dto);
        dvo.setIstPcsvDvCd("1");
        LocalDate currentDate = LocalDate.now();

        List<WctaInstallationShippingDvo> checkDvos = mapper.selectInstallationShippings(dvo);
        if (CollectionUtils.isEmpty(checkDvos)) {
            throw new BizException("MSG_ALT_ORD_INF_NOT_FOUND"); // 주문정보를 찾을 수 없습니다.
        }

        for (WctaInstallationShippingDvo checkDvo : checkDvos) {
            int year = Integer.parseInt(checkDvo.getSppDuedt().substring(0, 4));
            int month = Integer.parseInt(checkDvo.getSppDuedt().substring(4, 6));
            int day = Integer.parseInt(checkDvo.getSppDuedt().substring(6, 8));

            LocalDate sppDuedt = LocalDate.of(year, month, day);

            if (StringUtils.isEmpty(checkDvo.getSppDuedt())) {
                throw new BizException("MSG_ALT_SPP_EXP_DT_NOT_RGST"); // 배송 예정일자 미등록 상태입니다.
            }
            if ("Y".equals(checkDvo.getPkgYn())) {
                BizAssert.isTrue(
                    currentDate.compareTo(sppDuedt) > 3, "MSG_ALT_CANT_CANC_ASGN_AFTER_DAYS", new String[] {"2"} // D-2일 이후는 배정 취소 불가 합니다.(패키지모종)
                );
            } else {
                BizAssert.isTrue(
                    currentDate.compareTo(sppDuedt) >= 34, "MSG_ALT_CANT_CANC_ASGN_AFTER_DAYS", new String[] {"35"} // D-35일 이후는 배정 취소 불가 합니다.(패키지모종)
                );
            }
        }
        int res = mapper.updateSppDuedt(dvo);
        BizAssert.isTrue(res > 0, "MSG_ALT_SVE_ERR");

        historyService.createContractDetailChangeHistory(
            WctzCntrDetailChangeHistDvo.builder()
                .cntrNo(dvo.getCntrNo())
                .cntrSn(Integer.parseInt(dvo.getCntrSn()))
                .build()
        );

        return "Y";
    }

    public String getProcessingBfChecks(SearchAssignProcessingReq dto) {
        WctaInstallationShippingDvo dvo = converter.mapSearchAsnProcsReqToWctaIstShippingDvo(dto);
        List<WctaInstallationShippingDvo> dvos = new ArrayList<>();
        dvos.add(dvo);

        String prdDiv = dvo.getPrdDiv();
        String wkGb = dvo.getWkGb();

        List<WctaInstallationShippingDvo> kiwiInstallOrders = mapper.selectKiwiInstallOrders(dvos);
        WctaInstallationShippingDvo checkCancel = mapper.selectCheckCancel(dvo);

        if (CollectionUtils.isEmpty(kiwiInstallOrders)) {
            if (List.of("2", "3").contains(prdDiv)) {
                if ("9".equals(wkGb)) {
                    return "";
                } else {
                    throw new BizException("MSG_ALT_TGT_MTR_NOT_FOUND"); // 대상 자료를 찾을수 없었습니다.
                }
            }
        }

        for (WctaInstallationShippingDvo kiwiInstallOrder : kiwiInstallOrders) {
            String wkAcpteStatCd = kiwiInstallOrder.getWkAcpteStatCd(); /* 작업수락상태 */
            String wkPrgsStatCd = kiwiInstallOrder.getWkPrgsStatCd(); /* 작업진행상태 */
            String wkAcpteDt = kiwiInstallOrder.getWkAcpteDt(); /* 작업수락일자 */
            String inChnlDvCd = kiwiInstallOrder.getInChnlDvCd(); /* 입력채널구분코드 */
            String retrTrgtYn = kiwiInstallOrder.getRetrTrgtYn(); /* 반품 대상 여부 */

            String lcCanyn = checkCancel.getLcCanyn(); /* 취소여부 */
            String cntrRcpFshDt = checkCancel.getCntrRcpFshDt(); /* 계약접수완료일시 */
            String atmtRgsnYn = checkCancel.getAtmtRgsnYn(); /* 자동이체 등록여부 */

            int parsedWkPrgsStatCd = Integer.parseInt(wkPrgsStatCd);

            if (List.of("2", "3").contains(prdDiv)) {
                BizAssert.isFalse("20".equals(wkPrgsStatCd), "MSG_ALT_ALRDY_IST_ORD");// 설치완료된 주문입니다.

                if (parsedWkPrgsStatCd < 70) {

                    BizAssert.isFalse("Y".equals(wkAcpteStatCd), "MSG_ALT_ALRDY_EGER_ACPTE");// 엔지니어 수락건입니다. 변경/요청취소 불가능합니다.

                    BizAssert.isFalse("1".equals(inChnlDvCd), "MSG_ALT_NOT_MDFC_IN_KSS");// 인바운드센터에서 접수한 설치예정건으로 KSS에서 수정이 불가합니다.\n인바운드센터(1588-4113)로 의뢰하세요.

                    BizAssert.isFalse("2".equals(inChnlDvCd), "MSG_ALT_ALRDY_RGST_MT_KIWI"); // KIWI에서 등록한 자료입니다. 변경/취소 불가 합니다.

                }
            }
            BizAssert.isFalse(
                "1".equals(prdDiv) && parsedWkPrgsStatCd < 70 && "Y".equals(retrTrgtYn),
                "MSG_ALT_ALRDY_RECEIPT_IST_ASK_INF"
            ); // 접수된 설치요청 정보가 있습니다.

            if (!"3".equals(prdDiv)) {
                BizAssert.isFalse(ObjectUtils.isEmpty(checkCancel) || "Y".equals(lcCanyn), "MSG_ALT_ORD_DEL_OR_CANC"); // 주문이 삭제되었거나 취소 되었습니다.

                BizAssert.isFalse(Integer.parseInt(cntrRcpFshDt) <= 20180404, "MSG_ALT_NOT_MDFC_IN_KSS"); // 인바운드센터에서 접수한 설치예정건으로 KSS에서 수정이 불가합니다.\n인바운드센터(1588-4113)로 의뢰하세요.

                BizAssert.isFalse(
                    "N".equals(atmtRgsnYn), "MSG_ALT_NO_RGST_AFTN_CANT_RGST_IST_ASN"
                ); // 자동이체 등록 되지 않은 주문은 설치배정 등록 불가합니다.

            }
        }
        return "Y";
    }
}
