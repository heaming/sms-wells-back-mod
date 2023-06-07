package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsOutStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskPcsvDvo;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 인터페이스 데이터 생성
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-25
 */

@Mapper
public interface WsnaLogisticsOutStorageAskMapper {

    WsnaLogisticsOutStorageAskDvo selectItmOstrAkSendEtxtByOstrAkNo(String ostrAkNo);

    String selectNewLgstOstrAkNo(String lgstOstrCd);

    int insertItmOstrAkSendEtxt(WsnaLogisticsOutStorageAskDvo dvo);

    int insertOstrAkDtlSendEtxt(WsnaLogisticsOutStorageAskDtlDvo dvo);

    Optional<WsnaLogisticsOutStorageAskDtlDvo> selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(
        WsnaLogisticsOutStorageAskDto.RemoveReq dto
    );

    int updateOstrAkDtlSendEtxt(WsnaLogisticsOutStorageAskDtlDvo dvo);

    int updateOstrAkDtlSendEtxtForRemove(WsnaLogisticsOutStorageAskDtlDvo dvo);

    Integer selectOstrAkDtlSendEtxtCount(String ostrAkNo);

    int updateItmOstrAkSendEtxtForRemove(WsnaLogisticsOutStorageAskDvo dvo);

    int insertOstrAkPcsvSendEtxt(WsnaLogisticsOutStorageAskPcsvDvo dvo);

    Optional<WsnaLogisticsOutStorageAskPcsvDvo> selectOstrAkPcsvSendEtxtByOstrAkNoAndOstrAkSn(
        WsnaLogisticsOutStorageAskDto.RemoveReq dto
    );

    int updateOstrAkPcsvSendEtxt(WsnaLogisticsOutStorageAskPcsvDvo dvo);

    int updateOstrAkPcsvSendEtxtForRemove(WsnaLogisticsOutStorageAskPcsvDvo dvo);
}
