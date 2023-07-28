package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalBulkChangeDvo;

@Mapper
public interface WctbRentalBulkChangeMapper {
    List<WctbRentalBulkChangeDvo> selectRentalBulkChanges(
        SearchReq dto
    );

    WctbRentalBulkChangeDto.SearchCntrRes selectBulkChangeContractsInfs(String cntrNo, String cntrSn);

    WctbRentalBulkChangeDvo selectDateTime();

    String selectBfCntrDtlStatCd(WctbRentalBulkChangeDvo dvo); // 렌탈취소원복시 이전 상태 조회

    String selectRegyn(WctbRentalBulkChangeDvo dvo); // 매출조정_기간별 매출 조정 등록 여부

    int selectRegMm(WctbRentalBulkChangeDvo dvo); // 매출조정_주문별 특정 유형 기간 합계

    WctbRentalBulkChangeDvo selectLifeCstCdStatus(WctbRentalBulkChangeDvo dvo); // 라이프 고객코드 상태 체크

    List<WctbRentalBulkChangeDvo> selectOtherLifeCstCdMapping(WctbRentalBulkChangeDvo dvo);

    int updateCntrDtl(WctbRentalBulkChangeDvo dvo); // 계약상세 업데이트

    int updateCntrWellsDtl(WctbRentalBulkChangeDvo dvo); // 계약wells상세 업데이트

    int updatePdctIdnoOstrIz(WctbRentalBulkChangeDvo dvo); // 제품고유번호출고내역 업데이트

    int updateCntrDtlClear(WctbRentalBulkChangeDvo dvo); // 맵핑 등록건 CLEAR

    int insertAcmpalCntrIz(WctbRentalBulkChangeDvo dvo); // 관계사제휴계약내역 생성

    int insertCntrChRcpBas(@Param("info")
    WctbRentalBulkChangeDvo dvo); // 계약변경접수기본 생성

    int insertCntrChRcpDtl(@Param("info")
    WctbRentalBulkChangeDvo dvo); // 계약변경접수상세 생성

    // 계약WELLS상세변경이력
    int updateCntrWellsDchHist(WctbRentalBulkChangeDvo dvo);

    int insertCntrWellsDchHist(WctbRentalBulkChangeDvo dvo);

    //계약상세변경이력
    int updateCntrDchHist(WctbRentalBulkChangeDvo dvo);

    int insertCntrDchHist(WctbRentalBulkChangeDvo dvo);

    //계약상세상태변경이력
    int updateCntrDtlStatChangeHist(WctbRentalBulkChangeDvo dvo);

    int insertCntrDtlStatChangeHist(WctbRentalBulkChangeDvo dvo);

    // 계약변경접수변경이력
    int insertCntrChRcchHist(WctbRentalBulkChangeDvo dvo);

    // 계약변경접수상세변경이력
    int insertCntrChRcpDchHist(WctbRentalBulkChangeDvo dvo);
}
