package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncZipMngtDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WsncZipMngtConverter {

    @Mapping(source = "newAdrZip", target = "newAdrZip")
    @Mapping(source = "ctpvNm", target = "ctpvNm")
    @Mapping(source = "ctctyNm", target = "ctctyNm")
    @Mapping(source = "emdNm", target = "emdNm")
    @Mapping(source = "ltnAdr", target = "ltnAdr")
    @Mapping(source = "lgldCd", target = "lgldCd")
    @Mapping(source = "lgldNm", target = "lgldNm")
    @Mapping(source = "locaraTno", target = "locaraTno")
    @Mapping(source = "islndLocaraYn", target = "islndLocaraYn")
    @Mapping(source = "oldAdrZip", target = "oldAdrZip")
    @Mapping(source = "dtaDlYn", target = "dtaDlYn")
    @Mapping(source = "chSn", target = "chSn")
    @Mapping(source = "vstPrdVal", target = "vstPrdVal")
    @Mapping(source = "mngerRglvlDvCd", target = "mngerRglvlDvCd")
    @Mapping(source = "mngrDvCd", target = "mngrDvCd")
    @Mapping(source = "brchOgId", target = "brchOgId")
    @Mapping(source = "bfsvcIchrPrtnrNo", target = "bfsvcIchrPrtnrNo")
    @Mapping(source = "useYn", target = "useYn")
    @Mapping(source = "fstRgstDtm", target = "fstRgstDtm")
    @Mapping(source = "fstRgstUsrId", target = "fstRgstUsrId")
    @Mapping(source = "fstRgstPrgId", target = "fstRgstPrgId")
    @Mapping(source = "fstRgstDeptId", target = "fstRgstDeptId")
    @Mapping(source = "fnlMdfcDtm", target = "fnlMdfcDtm")
    @Mapping(source = "fnlMdfcUsrId", target = "fnlMdfcUsrId")
    @Mapping(source = "fnlMdfcPrgId", target = "fnlMdfcPrgId")
    @Mapping(source = "fnlMdfcDeptId", target = "fnlMdfcDeptId")
    WsncZipMngtDvo mapZipCodeResReqToWsncZipMngtDvo(WsncZipMngtDto.SearchZipCodeResReq dto);
}
