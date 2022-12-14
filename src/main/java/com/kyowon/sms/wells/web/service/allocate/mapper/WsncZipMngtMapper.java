package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncZipMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsncZipMngtMapper {
    PagingResult<WsncZipMngtDto.SearchZipCodeRes> selectZipAssignments(
        WsncZipMngtDto.SearchZipCodeReq dto, PageInfo pageInfo
    );

    int updateZipAssignment(WsncZipMngtDvo dvo);

    int insertZipAssignment(WsncZipMngtDvo dvo);
}
