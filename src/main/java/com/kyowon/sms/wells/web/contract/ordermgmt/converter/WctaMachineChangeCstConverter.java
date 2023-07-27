package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMachineChStatInOutDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaMachineChangeCstDvo;

@Mapper(componentModel = "spring")
public interface WctaMachineChangeCstConverter {
    WctbMachineChStatInOutDvo mapFindReqToWctbMachineChStatInOutDvo(FindReq req);

    @Mapping(source = "validDvo.workFlag", target = "workFlag")
    @Mapping(source = "validDvo.resultDvCheck", target = "resultDvCheck")
    @Mapping(source = "validDvo.resultMessage", target = "resultMessage")
    @Mapping(source = "validDvo.finalPerfRt", target = "finalPerfRt")
    @Mapping(source = "validDvo.ptyCopnDvCd", target = "ptyCopnDvCd")
    @Mapping(source = "validDvo.pdCd", target = "pdCd")
    @Mapping(source = "validDvo.rentalNmnN", target = "rentalNmnN")
    @Mapping(source = "validDvo.rerntPsbDt", target = "rerntPsbDt")
    @Mapping(source = "validDvo.stplDutyStrtDt", target = "stplDutyStrtDt")
    @Mapping(source = "validDvo.stplDutyEndDt", target = "stplDutyEndDt")
    @Mapping(source = "validDvo.rstlDutyStrtDt", target = "rstlDutyStrtDt")
    @Mapping(source = "validDvo.rstlDutyEndDt", target = "rstlDutyEndDt")
    @Mapping(source = "validDvo.ownrsExnDt", target = "ownrsExnDt")
    @Mapping(source = "validDvo.dlqAmt", target = "dlqAmt")
    @Mapping(source = "validDvo.ucAmt", target = "ucAmt")
    @Mapping(source = "validDvo.recapDutyPtrmN", target = "recapDutyPtrmN")
    @Mapping(source = "baseInfoDvo.adr", target = "adr")
    @Mapping(source = "baseInfoDvo.sellTpCd", target = "sellTpCd")
    @Mapping(source = "baseInfoDvo.cstKnm", target = "cstKnm")
    @Mapping(source = "baseInfoDvo.istDt", target = "istDt")
    @Mapping(source = "baseInfoDvo.reqdDt", target = "reqdDt")
    @Mapping(source = "validDvo.pdNm", target = "pdNm")
    FindRes mapWctaMachineChangeCstDvoToFindRes(WctbMachineChStatInOutDvo validDvo, WctaMachineChangeCstDvo baseInfoDvo);
}
