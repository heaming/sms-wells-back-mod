package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WwsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.*;
import org.mapstruct.Mapper;

import java.util.List;

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
public interface WwsnzWellsCodeConverter {
    List<SearchWorkingEngineersRes> mapAllWorkingEngineersDvoToRes(List<WwsnzWellsCodeWorkingEngineersDvo> dvos);

    List<SearchWareHouseRes> mapAllWareHouseDvoToRes(List<WwsnzWellsCodeWareHouseDvo> dvos);

    List<SearchMonthStockRes> mapAllDvoToRes(List<WwsnzWellsCodeMonthStockDvo> dvos);

    List<SearchServiceCenterOrgsRes> mapAllServiceCenterOrgsDvoToRes(List<WwsnzWellsCodeServiceCenterOrgsDvo> dvos);

    List<SearchAllEngineersRes> mapAllEngineersDvoToRes(List<WwsnzWellsCodeAllEngineersDvo> dvos);

    List<SearchPartMasterRes> mapAllPartMasterDvoToRes(List<WwsnzWellsCodePartMasterDvo> dvos);

    List<SearchServiceCentersRes> mapAllServiceCentersDvoToRes(List<WwsnzWellsCodeServiceCenterDvo> dvos);

    List<SearchDistrictsRes> mapAllDistrictsDvoToRes(List<WwsnzWellsCodeDistrictsDvo> dvos);

    List<SearchProductBaseRes> mapAllProductBaseDvoToRes(List<WwsnzWellsCodeProductBaseDvo> dvos);

    List<SearchMcbyCstSvOjIzRes> mapAllMonthCstServsDvoToRes(List<WwsnzWellsCodeMonthCstServsDvo> dvos);

    List<SearchLgldCtpvLocarasRes> mapAllLgldCtpvLocarasDvoToRes(List<WwsnzWellsCodeLgldCtpvLocarasDvo> dvos);

}
