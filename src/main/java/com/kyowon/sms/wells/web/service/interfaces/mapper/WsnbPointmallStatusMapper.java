package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbPointmallStatusDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnbPointmallStatusMapper {

    List<WsnbPointmallStatusDto.SearchRes> selectPointmallStatuses(WsnbPointmallStatusDto.SearchReq dto);

}
