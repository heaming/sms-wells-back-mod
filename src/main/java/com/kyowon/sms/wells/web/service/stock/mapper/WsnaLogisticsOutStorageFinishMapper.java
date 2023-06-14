package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageFinishDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageFinishIostDvo;

/**
 * <pre>
 * 물류센터 출고완료 처리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-14
 */

@Mapper
public interface WsnaLogisticsOutStorageFinishMapper {

    List<WsnaLogisticsOutStorageFinishDvo> selectItmOstrFshRcvEtxt();

    String selectNewItmStrNo(String strTpCd);

    String selectNewItmOstrNo(String ostrTpCd);

    String selectNewOstrAkNo(String ostrAkTpCd);

    int selectNewOstrSn(String itmOstrNo);

    int selectNewStrSn(String itmStrNo);

    int selectNewOstrAkSn(String ostrAkNo);

    int insertItmStrIz(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int insertItmOstrIz(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int insertItmOstrAkIz(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int updateItmOstrIzForStr(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int updateItmStrIzForStr(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int updateItmOstrAkIz(WsnaLogisticsOutStorageFinishDvo dvo);

    int updateItmOstrAkIzForHgr(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int updateCstSvItmStocForStr(WsnaLogisticsOutStorageFinishIostDvo dvo);

    int updateItmOstrFshRcvEtxt(String linkOcrnId);

}
