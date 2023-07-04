package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAsTransferConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SaveReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsTransferDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAsTransferMapper;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncAsTransferService {
    private final WsncAsTransferMapper mapper;
    private final WsncAsTransferConverter converter;
    private final WsnzHistoryMapper wsnzHistoryMapper;

    /**
     * A/S 담당자 이관
     * @param  dto : { svCnrOgId: 서비스센터조직ID, ichrPrtnrNo : 담당파트너번호, assignDateFrom : 배정일자From
     *                 assignDateTo : 배정일자To, vstCnfmdt : 방문확정일, svBizHclsfCd : 작업구분코드 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getAsTransferPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        PagingResult<SearchRes> pagingResult = converter.mapWsncAsTransferDvoToSearchRes(
            mapper.selectAsTransferPages(dto, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * A/S 담당자 이관 엑셀 다운로드
     * @param  dto : { svCnrOgId: 서비스센터조직ID, ichrPrtnrNo : 담당파트너번호, assignDateFrom : 배정일자From
     *                 assignDateTo : 배정일자To, vstCnfmdt : 방문확정일, svBizHclsfCd : 작업구분코드 }
     * @return 조회결과
     */
    public List<SearchRes> getAsTransferPagesExcelDownload(
        SearchReq dto
    ) {

        return converter.mapWsncAsTransferDvoToSearchRes(mapper.selectAsTransferPages(dto));
    }

    /**
     * A/S 담당자 이관 - 저장
     * @param dtos : [{ svCnrOgId: 서비스센터조직ID, ichrPrtnrNo : 담당파트너번호, assignDateFrom : 배정일자From
     *      *                 assignDateTo : 배정일자To, vstCnfmdt : 방문확정일, svBizHclsfCd : 작업구분코드 }]
     * @return
     */
    @Transactional
    public int saveAsTransfers(List<SaveReq> dtos) throws Exception {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WsncAsTransferDvo dvo = converter.mapSaveReqToWsncAsTransferDvo(dto);
            WsncAsTransferDvo dvo1 = dvo;
            processCount += mapper.insertAssignResultTransferIz(dvo);

            //이관출고로그저장
            wsnzHistoryMapper.insertCstSvasIstAsnHistByPk(dvo.getCstSvAsnNo());
            Thread.sleep(1000);
            processCount += mapper.updateInstallationAssignIz(dvo);
            //이관입고로그저장
            wsnzHistoryMapper.insertCstSvasIstAsnHistByPk(dvo.getCstSvAsnNo());
            //웰스팜 기기- 모종 연계코드가 존재하는지여부
            if (mapper.selectFarmCodeCount(dvo) > 0) {
                // 해당 모종 고객번호로 배정된 설치/AS가 있는지 확인
                if (mapper.selectAssignByPk(dvo) > 0) {
                    // TB_SVPD_SDING_SPP_PLAN_IZ 업데이트위한 조회(List)
                    List<WsncAsTransferDvo> farmDvos = mapper.selectFarmShippings(dvo);
                    for (WsncAsTransferDvo farmDvo : farmDvos) {
                        dvo1.setCstSvAsnNo(farmDvo.getCstSvAsnNo());
                        dvo1.setCntrNo(farmDvo.getCntrNo());
                        dvo1.setCntrSn(farmDvo.getCntrSn());

                        //이관처리
                        mapper.insertAssignResultTransferIz(dvo1);
                    }
                    // TODO: 웰스팜 3개월 매니저, 모종 2개월 택배발송 출시에 따른 수정 픽업.
                    //                    if(){
                    //                        processCount += mapper.updateInstallationAssignIz(dvo1);
                    //                        processCount += mapper.updateSeedingShippingPlanIz(dvo1);
                    //                    }

                }
            }

        }
        return processCount;
    }

}
