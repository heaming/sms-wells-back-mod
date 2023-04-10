package com.kyowon.sms.wells.web.service.common.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.*;

/**
 *
 * <pre>
 * W-SV-U-0000M00 코드조회
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-08
 */
@Mapper(componentModel = "spring")
public interface WsnzWellsCodeConverter {
    List<SearchWorkingEngineersRes> mapAllWorkingEngineersDvoToRes(List<WsnzWellsCodeWorkingEngineersDvo> dvos);

    List<SearchWareHouseRes> mapAllWareHouseDvoToRes(List<WsnzWellsCodeWareHouseDvo> dvos);

    List<SearchMonthStockRes> mapAllDvoToRes(List<WsnzWellsCodeMonthStockDvo> dvos);

    List<SearchServiceCenterOrgsRes> mapAllServiceCenterOrgsDvoToRes(List<WsnzWellsCodeServiceCenterOrgsDvo> dvos);

    List<SearchAllEngineersRes> mapAllEngineersDvoToRes(List<WsnzWellsCodeAllEngineersDvo> dvos);

    List<SearchPartMasterRes> mapAllPartMasterDvoToRes(List<WsnzWellsCodePartMasterDvo> dvos);

    List<SearchServiceCentersRes> mapAllServiceCentersDvoToRes(List<WsnzWellsCodeServiceCenterDvo> dvos);

    List<SearchDistrictsRes> mapAllDistrictsDvoToRes(List<WsnzWellsCodeDistrictsDvo> dvos);

    List<SearchProductBaseRes> mapAllProductBaseDvoToRes(List<WsnzWellsCodeProductBaseDvo> dvos);

    List<SearchMcbyCstSvOjIzRes> mapAllMonthCstServsDvoToRes(List<WsnzWellsCodeMonthCstServsDvo> dvos);

    List<SearchLgldCtpvLocarasRes> mapAllLgldCtpvLocarasDvoToRes(List<WsnzWellsCodeLgldCtpvLocarasDvo> dvos);

}
