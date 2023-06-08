package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;

/**
 * <pre>
 * W-SV-S-0089 물류 반품요청 인터페이스 데이터 생성
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Mapper
public interface WsnaLogisticsInStorageAskMapper {

    Optional<WsnaLogisticsInStorageAskDtlDvo> selectRtngdAkDtlSendEtxtByOstrAkNoAndOstrAkSn(
        WsnaLogisticsInStorageAskReqDvo dvo
    );

    WsnaLogisticsInStorageAskDvo selectPdRtngdAkSendEtxtByRtngdAkNo(String rtngdAkNo);

    String selectNewLgstStrAkNo(String lgstStrCd);

    int insertPdRtngdAkSendEtxt(WsnaLogisticsInStorageAskDvo dvo);

    int insertRtngdAkDtlSendEtxt(WsnaLogisticsInStorageAskDtlDvo dvo);

    int updateRtngdAkDtlSendEtxt(WsnaLogisticsInStorageAskDtlDvo dvo);

    int updateRtngdAkDtlSendEtxtForRemove(WsnaLogisticsInStorageAskDtlDvo dvo);

    Integer selectRtngdAkDtlSendEtxtCount(String ostrNo);

    int updatePdRtngdAkSendEtxtForRemove(WsnaLogisticsInStorageAskDvo dvo);

}
