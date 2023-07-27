package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailMembershipPagesRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailRentalPagesRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailSinglePaymentPagesRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailMngtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaOrderDetailMngtService {
    private final WctaOrderDetailMngtMapper mapper;
    private final WctaOrderDetailConverter converter;
    private final WctzHistoryService historyService;

    public PagingResult<SearchRes> getOrderDetailRentalPages(SearchReq dto, PageInfo pageInfo) {
        WctaOrderDetailRentalPagesRequestDvo dvo = converter.mapSearchReqToWctaOrderDetailRentalPagesDvo(dto);

        // PagingResult<WctaOrderDetailRentalPagesDvo> pagingResultDvo = mapper.selectOrderDetailRentalPages(dvo, pageInfo);
        PagingResult<SearchRes> pagingResultDto = converter
            .mapWctaOrderDetailRentalPagesDvoToSearchRes(mapper.selectOrderDetailRentalPages(dvo, pageInfo));
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchRes> getOrderDtlRentalExcels(SearchReq dto) {
        return converter.mapWctaOrderDetailRentalPagesExcelDvoToSearchRes(mapper.selectOrderDetailRentalPages(dto));
    }

    public PagingResult<SearchOrderDetailMshPagesRes> getOrderDetailMshPages(
        SearchOrderDetailMshPagesReq dto, PageInfo pageInfo
    ) {
        WctaOrderDetailMembershipPagesRequestDvo dvo = converter
            .mapSearchOrderDetailMshPagesReqToWctaOrderDetailMembershipPagesDvo(dto);

        PagingResult<SearchOrderDetailMshPagesRes> pagingResultDto = converter
            .mapWctaOrderDetailMembershipPagesDvoToSearchOrderDetailMshPagesRes(
                mapper.selectOrderDetailMshPages(dvo, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchOrderDetailMshPagesRes> getOrderDetailMshExcels(SearchOrderDetailMshPagesReq dto) {
        return converter.mapWctaOrderDetailMembershipPagesExcelDvoToSearchOrderDetailMshPagesRes(
            mapper.selectOrderDetailMshPages(dto)
        );
    }

    public PagingResult<SearchOrderDetailSnglPmntPagesRes> getOrderDetailSpayCntrtPages(
        SearchOrderDetailSnglPmntPagesReq dto, PageInfo pageInfo
    ) {
        WctaOrderDetailSinglePaymentPagesRequestDvo dvo = converter
            .mapSearchOrderDetailSnglPmntPagesReqToWctaOrderDetailSinglePaymentPagesDvo(dto);

        PagingResult<SearchOrderDetailSnglPmntPagesRes> pagingResultDto = converter
            .mapWctaOrderDetailSinglePaymentPagesDvoToSearchOrderDetailSnglPmntPagesRes(
                mapper.selectOrderDetailSpayCntrtPages(dvo, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchOrderDetailSnglPmntPagesRes> getOrderDetailSpayCntrtPagesExcelDownload(
        SearchOrderDetailSnglPmntPagesReq dto
    ) {
        return converter.mapWctaOrderDetailSinglePaymentPagesExcelDvoToSearchOrderDetailSnglPmntPagesRes(
            mapper.selectOrderDetailSpayCntrtPages(dto)
        );
    }

    public PagingResult<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto, PageInfo pageInfo
    ) {
        PagingResult<SearchOrderDetailRglrDlvrPagesRes> pagingResultDto = converter
            .mapWctaOrderDetailRglrDlvrPagesDvoToSearchOrderDetailRglrDlvrPagesRes(
                mapper.selectOrderRegularShippingsPages(dto, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsExcels(
        SearchOrderDetailRglrDlvrPagesReq dto
    ) {
        return converter.mapWctaOrderDetailRglrDlvrPagesExcelDvoToSearchOrderDetailRglrDlvrPagesRes(
            mapper.selectOrderRegularShippingsPages(dto)
        );
    }

    @Transactional
    public int saveMembershipConfirms(List<SaveMembershipConfirmsReq> dtos) {
        int processCount = 0;
        Iterator<SaveMembershipConfirmsReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveMembershipConfirmsReq dto = iterator.next();
            String histStrtDtm = DateUtil.getNowString();

            if (StringUtil.isNotEmpty(dto.cntrCnfmYn())) {
                processCount = mapper.updateMembershipConfirmsCntrBas(dto);
                historyService.createContractBasicChangeHistory(
                    WctzCntrBasicChangeHistDvo.builder()
                        .cntrNo(dto.cntrNo())
                        .cntrCnfmDtm(dto.cntrCnfmDtm())
                        .histStrtDtm(histStrtDtm)
                        .build()
                );
                processCount += mapper.updateMembershipConfirmsCntrDtl(dto);
                historyService.createContractDetailChangeHistory(
                    WctzCntrDetailChangeHistDvo.builder()
                        .cntrNo(dto.cntrNo())
                        .cntrSn(dto.cntrSn())
                        .cntrPdStrtdt(dto.cntrPdStrtdt())
                        .histStrtDtm(histStrtDtm)
                        .build()
                );
            }
        }
        return processCount;
    }

    public List<SearchCompositionProductsRes> getCompositionProducts(SearchCompositionProductsReq dto) {
        return mapper.selectCompositionProducts(dto);
    }

    public List<SearchFreeGiftInformationRes> getFreeGiftInformation(SearchCompositionProductsReq dto) {
        return mapper.selectFreeGiftInformation(dto);
    }
}
