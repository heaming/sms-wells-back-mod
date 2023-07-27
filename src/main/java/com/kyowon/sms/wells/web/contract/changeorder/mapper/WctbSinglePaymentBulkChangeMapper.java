package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSinglePaymentBulkChangeDvo;

@Mapper
public interface WctbSinglePaymentBulkChangeMapper {
    List<SearchRes> selectSinglePaymentBulkChangs(
        String cntrNo, String cntrSn, String rfDt
    );

    WctbSinglePaymentBulkChangeDto.SearchRgstRes selectSinglePaymentBulkChangsRgst(String cntrNo, String cntrSn);

    WctbSinglePaymentBulkChangeDvo selectDateTime();

    int updateCntrWellsDtlCpsDt(WctbSinglePaymentBulkChangeDvo dvo); // 계약WELLS상세 - 보상일자변경

    int updateCntrDtlCanDtCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약상세 - 취소일자변경

    int updateCttBasCttCdCh(WctbSinglePaymentBulkChangeDvo dvo); // 컨택기본 - 컨택정보변경

    int updateCntrDtlCttCdCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약상세 - 컨택정보변경

    int updateCntrDtlRecogAmtInfCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약상세 - 인정금액정보변경

    int updateCntrWellsDtlFreeAsBsInfCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약WELLS상세 - 무상멤버십/AS정보 변경

    int updateCntrWellsDtlEvCdCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약WELLS상세 - 행사코드변경

    int updateCntrWellsDtlIstCan(WctbSinglePaymentBulkChangeDvo dvo); // 계약WELLS상세 - 설치취소

    int updateCntrDtlRcpdtCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약상세 - 접수일 변경

    int updateCntrWellsDtlBsCh(WctbSinglePaymentBulkChangeDvo dvo); // 계약WELLS상세 - BS업체구분값변경

    int insertCntrChRcpBas(@Param("info")
    WctbSinglePaymentBulkChangeDvo dvo); // 계약변경접수기본 생성

    int insertCntrChRcpDtl(@Param("info")
    WctbSinglePaymentBulkChangeDvo dvo); // 계약변경접수상세 생성

    // 계약WELLS상세변경이력
    int updateCntrWellsDchHist(WctbSinglePaymentBulkChangeDvo dvo);

    int insertCntrWellsDchHist(WctbSinglePaymentBulkChangeDvo dvo);

    //계약상세변경이력
    int updateCntrDchHist(WctbSinglePaymentBulkChangeDvo dvo);

    int insertCntrDchHist(WctbSinglePaymentBulkChangeDvo dvo);

    // 컨택변경이력
    int updateCttChHist(WctbSinglePaymentBulkChangeDvo dvo);

    int insertCttChHist(WctbSinglePaymentBulkChangeDvo dvo);

    // 계약변경접수변경이력
    int insertCntrChRcchHist(WctbSinglePaymentBulkChangeDvo dvo);

    // 계약변경접수상세변경이력
    int insertCntrChRcpDchHist(WctbSinglePaymentBulkChangeDvo dvo);

}
