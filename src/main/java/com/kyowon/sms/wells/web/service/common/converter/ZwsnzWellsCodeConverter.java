package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.ZwsnzWellsCodeDto.*;
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
public interface ZwsnzWellsCodeConverter {
    List<SearchWorkingEngineersRes> mapAllWorkingEngineersDvoToRes(
        List<ZwsnzWellsCodeWorkingEngineersDvo> dvos
    );

    List<SearchWareHouseRes> mapAllWareHouseDvoToRes(
        List<ZwsnzWellsCodeWareHouseDvo> dvos
    );

    List<SearchMonthStockRes> mapAllDvoToRes(
        List<ZwsnzWellsCodeMonthStockDvo> dvos
    );

    List<SearchServiceCenterOrgsRes> mapAllServiceCenterOrgsDvoToRes(
        List<ZwsnzWellsCodeServiceCenterOrgsDvo> dvos
    );

    List<SearchAllEngineersRes> mapAllEngineersDvoToRes(List<ZwsnzWellsCodeAllEngineersDvo> dvos);
}
