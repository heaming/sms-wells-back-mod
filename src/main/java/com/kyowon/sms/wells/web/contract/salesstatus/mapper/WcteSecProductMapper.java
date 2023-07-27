package com.kyowon.sms.wells.web.contract.salesstatus.mapper;

import java.util.List;
import java.util.Optional;

import com.kyowon.sms.wells.web.contract.salesstatus.dvo.*;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WcteSecProductMapper {

    PagingResult<SearchReservationRes> selectReservationPages(
        SearchReservationReq dto,
        PageInfo pageInfo
    );

    List<SearchReservationRes> selectReservationPages(
        SearchReservationReq dto
    );

    PagingResult<SearchConfirmRes> selectConfirms(
        SearchConfirmReq dto,
        PageInfo pageInfo
    );

    List<SearchConfirmRes> selectConfirms(SearchConfirmReq dto);

    Optional<WcteConfirmValidationDvo> selectConfirmValidation(String cntrNo, int cntrSn);

    int updateShippingDueDate(String cntrNo, int cntrSn, String sppDuedt);

    int insertContractDetailChangeHistory(String cntrNo, int cntrSn);

    int insertProductOutOfStorageIz(WctePdOstrIzDvo productOutOfStorageIz);

    int insertInvoiceProcessIz(WcteInvoiceProcessIzDvo invoiceProcessIz);

    String selectInvoiceProcessIzPk(WcteInvoiceProcessIzDvo invoiceProcessIz);

    int insertInvoiceProcessHistory(String sppBzsProcsId);

    PagingResult<SearchNotInstalledRes> selectNotInstalledIzs(SearchNotInstalledReq dto, PageInfo pageInfo);

    Optional<WcteSecNistlValidationDvo> selectSecNistlValidation(String cntrNo, int cntrSn);

    int insertNotInstalledIz(WcteSecNistlDvo dvo);

    List<WcteSecPdBycfDvo> selectSecPdBycfs();

    PagingResult<SearchShippingRes> selectShippings(SearchShippingReq dto, PageInfo pageInfo);

    List<SearchShippingRes> selectShippings(SearchShippingReq dto);

    PagingResult<SearchFreeAsRes> selectFreeASs(SearchFreeAsReq dto, PageInfo pageInfo);

    List<SearchFreeAsRes> selectFreeASs(SearchFreeAsReq dto);
}
