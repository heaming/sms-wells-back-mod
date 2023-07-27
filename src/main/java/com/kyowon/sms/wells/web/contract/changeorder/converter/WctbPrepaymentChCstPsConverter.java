package com.kyowon.sms.wells.web.contract.changeorder.converter;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPrepaymentChCstPsDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbPrepaymentChCstPsDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctbPrepaymentChCstPsConverter {

    PagingResult<SearchRes> mapWctbPrepaymentDvoToSearchRes(List<WctbPrepaymentChCstPsDvo> dvos);

    List<SearchRes> mapWctbPrepaymentExcelDvoToSearchRes(List<WctbPrepaymentChCstPsDvo> dvos);
}
