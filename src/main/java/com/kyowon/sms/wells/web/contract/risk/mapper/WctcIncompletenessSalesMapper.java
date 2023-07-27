package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchByCntrNoReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIcptSellChHistDvo;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIncompletenessSellIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcIncompletenessSalesMapper {

    String isValidCntrs(
        SearchByCntrNoReq dto
    );

    List<SearchRes> selectIncompletenessSales(
        SearchByCntrNoReq dto
    );

    PagingResult<SearchRes> selectIncompletenessSalePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectIncompletenessSalePages(
        SearchReq dto
    );

    int insertIncompletenessSales(@Param("item")
    WctcIncompletenessSellIzDvo dvo);

    int updateIncompletenessSales(WctcIncompletenessSellIzDvo dvo);

    int deleteIncompletenessSales(String icptSellId);

    int insertIncompletenessSalesHist(WctcIcptSellChHistDvo dvo);

    int updateIncompletenessSalesPrevHist(WctcIcptSellChHistDvo dvo);

}
