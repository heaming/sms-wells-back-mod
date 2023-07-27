package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailConverter {
    WctaOrderDetailRentalPagesRequestDvo mapSearchReqToWctaOrderDetailRentalPagesDvo(SearchReq dto);

    PagingResult<SearchRes> mapWctaOrderDetailRentalPagesDvoToSearchRes(
        List<WctaOrderDetailRentalPagesDvo> dvos
    );

    List<SearchRes> mapWctaOrderDetailRentalPagesExcelDvoToSearchRes(List<WctaOrderDetailRentalPagesDvo> dvos);

    WctaOrderDetailMembershipPagesRequestDvo mapSearchOrderDetailMshPagesReqToWctaOrderDetailMembershipPagesDvo(
        SearchOrderDetailMshPagesReq dto
    );

    PagingResult<SearchOrderDetailMshPagesRes> mapWctaOrderDetailMembershipPagesDvoToSearchOrderDetailMshPagesRes(
        List<WctaOrderDetailMembershipPagesDvo> dvos
    );

    List<SearchOrderDetailMshPagesRes> mapWctaOrderDetailMembershipPagesExcelDvoToSearchOrderDetailMshPagesRes(
        List<WctaOrderDetailMembershipPagesDvo> dvos
    );

    WctaOrderDetailSinglePaymentPagesRequestDvo mapSearchOrderDetailSnglPmntPagesReqToWctaOrderDetailSinglePaymentPagesDvo(
        SearchOrderDetailSnglPmntPagesReq dto
    );

    PagingResult<SearchOrderDetailSnglPmntPagesRes> mapWctaOrderDetailSinglePaymentPagesDvoToSearchOrderDetailSnglPmntPagesRes(
        List<WctaOrderDetailSinglePaymentPagesDvo> dvos
    );

    List<SearchOrderDetailSnglPmntPagesRes> mapWctaOrderDetailSinglePaymentPagesExcelDvoToSearchOrderDetailSnglPmntPagesRes(
        List<WctaOrderDetailSinglePaymentPagesDvo> dvos
    );

    PagingResult<SearchOrderDetailRglrDlvrPagesRes> mapWctaOrderDetailRglrDlvrPagesDvoToSearchOrderDetailRglrDlvrPagesRes(
        List<WctaOrderDetailRglrDlvrPagesDvo> dvos
    );

    List<SearchOrderDetailRglrDlvrPagesRes> mapWctaOrderDetailRglrDlvrPagesExcelDvoToSearchOrderDetailRglrDlvrPagesRes(
        List<WctaOrderDetailRglrDlvrPagesDvo> dvos
    );
}
