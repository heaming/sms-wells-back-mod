package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAsTransferConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsTransferDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAsTransferMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncAsTransferService {
    private final WsncAsTransferMapper mapper;
    private final WsncAsTransferConverter converter;

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
        return mapper.selectAsTransferPages(dto, pageInfo);
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
        return mapper.selectAsTransferPages(dto);
    }

    /**
     * A/S 담당자 이관 - 저장
     * @param dtos : [{ svCnrOgId: 서비스센터조직ID, ichrPrtnrNo : 담당파트너번호, assignDateFrom : 배정일자From
     *      *                 assignDateTo : 배정일자To, vstCnfmdt : 방문확정일, svBizHclsfCd : 작업구분코드 }]
     * @return
     */
    @Transactional
    public int saveAsTransfers(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WsncAsTransferDvo dvo = converter.mapSaveReqToWsncAsTransferDvo(dto);
            mapper.insertAssignResultTransferIz(dvo);
            mapper.updateInstallationAssignIz(dvo);
            mapper.updateSeedingShippingPlanIz(dvo);
            mapper.updateSeedingShippingPlanIz(dvo);
        }
        return processCount;
    }

    public List<Engineer> getEngineers(FindEngineerReq dto) {
        return mapper.selectEngineers(dto);
    }

    public List<Center> getCenters() {
        return mapper.selectCenters();
    }
}
