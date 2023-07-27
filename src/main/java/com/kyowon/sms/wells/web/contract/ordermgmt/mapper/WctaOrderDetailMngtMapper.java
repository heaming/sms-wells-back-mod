package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaOrderDetailMngtMapper {
    PagingResult<WctaOrderDetailRentalPagesDvo> selectOrderDetailRentalPages(
        WctaOrderDetailRentalPagesRequestDvo dvo,
        PageInfo pageInfo
    );

    List<WctaOrderDetailRentalPagesDvo> selectOrderDetailRentalPages(
        SearchReq dto
    );

    PagingResult<WctaOrderDetailMembershipPagesDvo> selectOrderDetailMshPages(
        WctaOrderDetailMembershipPagesRequestDvo dvo,
        PageInfo pageInfo
    );

    List<WctaOrderDetailMembershipPagesDvo> selectOrderDetailMshPages(
        SearchOrderDetailMshPagesReq dto
    );

    PagingResult<WctaOrderDetailSinglePaymentPagesDvo> selectOrderDetailSpayCntrtPages(
        WctaOrderDetailSinglePaymentPagesRequestDvo dvo, PageInfo pageInfo
    );

    List<WctaOrderDetailSinglePaymentPagesDvo> selectOrderDetailSpayCntrtPages(
        SearchOrderDetailSnglPmntPagesReq dto
    );

    PagingResult<WctaOrderDetailRglrDlvrPagesDvo> selectOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto,
        PageInfo pageInfo
    );

    List<WctaOrderDetailRglrDlvrPagesDvo> selectOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto
    );

    int updateMembershipConfirmsCntrBas(SaveMembershipConfirmsReq dto);

    int updateMembershipConfirmsCntrDtl(SaveMembershipConfirmsReq dto);

    List<SearchCompositionProductsRes> selectCompositionProducts(SearchCompositionProductsReq dto);

    List<SearchFreeGiftInformationRes> selectFreeGiftInformation(SearchCompositionProductsReq dto);
}
