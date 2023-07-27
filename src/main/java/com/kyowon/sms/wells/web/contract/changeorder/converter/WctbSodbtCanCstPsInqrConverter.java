package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSodbtCanCstPsInqrDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSodbtCanCstPsInqrDvo;

@Mapper(componentModel = "spring")
public interface WctbSodbtCanCstPsInqrConverter {
    WctbSodbtCanCstPsInqrDto.SearchRes mapWctbSodbtCanCstPsInqrDvoToSearchRes(WctbSodbtCanCstPsInqrDvo dvo);
}
