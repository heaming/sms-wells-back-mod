package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaManagementDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper(componentModel = "spring")
public interface WctaManagementConverter {
    List<SearchLspyOrdrDtptListRes> mapWctaLspyOrdrDtptListDvoToSearchLspyOrdrDtptListRes(
        List<WctaLspyOrdrDtptListDvo> dvos
    );

    List<SearchBhOrdrDtptListRes> mapWctaBhOrdrDtptListDvoToSearchBhOrdrDtptListRes(
        List<WctaBhOrdrDtptListDvo> dvos
    );

    List<SearchRentOrdrDtptListRes> mapWctaRentOrdrDtptListDvoToSearchRentOrdrDtptListRes(
        List<WctaRentOrdrDtptListDvo> dvos
    );

    List<SearchMbOrdrDtptListRes> mapWctaMbOrdrDtptListDvoToSearchMbOrdrDtptListRes(
        List<WctaMbOrdrDtptListDvo> dvos
    );

    List<SearchHcsOrdrDtptListRes> mapWctaHcsOrdrDtptListDvoToSearchHcsOrdrDtptListRes(
        List<WctaHcsOrdrDtptListDvo> dvos
    );

    List<SearchPlantsOrdrDtptListRes> mapWctaPlantsOrdrDtptListDvoToSearchPlantsOrdrDtptListRes(
        List<WctaPlantsOrdrDtptListDvo> dvos
    );

    List<SearchRglrDlvrOrdrDtptListRes> mapWctaRglrDlvrOrdrDtptListDvoToSearchRglrDlvrOrdrDtptListRes(
        List<WctaRglrDlvrOrdrDtptListDvo> dvos
    );

    List<SearchMastOrdrDtptRes> mapWctaMastOrdrDtptDvoToSearchMastOrdrDtptRes(
        List<WctaMastOrdrDtptDvo> dvos
    );
}
