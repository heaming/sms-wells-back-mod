package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseCloseCheckDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * W-SV-S-0090 해약매니저 재고 확인
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.06.26
 */
@Mapper
public interface WsnaWarehouseCloseCheckMapper {

    List<WsnaWarehouseCloseCheckDvo> selectPrtnrNoInfo(WsnaWarehouseCloseCheckDvo dvo);

    int selectMmtCountCloseCheck(WsnaWarehouseCloseCheckDvo dataDvo);

    int selectPitmCountCloseCheck(WsnaWarehouseCloseCheckDvo dataDvo);

    int selectMngtCountCloseCheck(WsnaWarehouseCloseCheckDvo dataDvo);

    int selectBfsvcCountCloseCheck(WsnaWarehouseCloseCheckDvo dataDvo);

}
