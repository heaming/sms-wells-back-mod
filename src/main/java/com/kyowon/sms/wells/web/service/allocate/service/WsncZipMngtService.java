package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncZipMngtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncZipMngtDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncZipMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsncZipMngtService {

    private final WsncZipMngtMapper wsncZipMngtMapper;

    private final WsncZipMngtConverter wsncZipMngtConverter;

    public PagingResult<WsncZipMngtDto.SearchZipCodeRes> getZipAssignments(
        WsncZipMngtDto.SearchZipCodeReq dto, PageInfo pageInfo
    ) {
        return wsncZipMngtMapper.selectZipAssignments(dto, pageInfo);
    }

    public List<WsncZipMngtDto.SearchZipCodeRes> getZipAssignmentsExcelDownload(WsncZipMngtDto.SearchZipCodeReq dto) {
        return wsncZipMngtMapper.selectZipAssignments(dto);
    }

    @Transactional
    public int saveZipAssignments(List<WsncZipMngtDto.SaveZipCodeReq> dtos) throws Exception {
        int processCnt = 0;
        for (WsncZipMngtDto.SaveZipCodeReq dto : dtos) {
            WsncZipMngtDvo dvo = wsncZipMngtConverter.mapZipCodeResReqToWsncZipMngtDvo(dto);

            //update (USE_YN = 'N' setting)
            if (StringUtils.isNotEmpty(dvo.getChSn())) {
                wsncZipMngtMapper.updateZipAssignment(dvo);
            }

            //insert (CH_SN 채번 후 insert)
            wsncZipMngtMapper.insertZipAssignment(dvo);
            processCnt++;
        }
        return processCnt;
    }

}
