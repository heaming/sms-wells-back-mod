package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPrepaymentChCstPsDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbPrepaymentChCstPsDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbPrepaymentChCstPsMapper {

    PagingResult<WctbPrepaymentChCstPsDvo> selectPrepaymentChCstPsPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WctbPrepaymentChCstPsDvo> selectPrepaymentChCstPsPages(
        SearchReq dto
    );
}
