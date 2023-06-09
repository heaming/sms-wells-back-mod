package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.*;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Mapper
public interface WsnaTransferMaterialsMapper {

    List<WsnaTransferMaterialsDataDvo> selectTransferMaterials(WsnaTransferMaterialsReqDvo dvo);

    String selectNewItmOstrNo(String ostrTpCd);

    String selectNewItmStrNo(String strTpCd);

    String selectNewOstrAkNo(String ostrAkTpCd);

    Optional<WsnaTransferMaterialsPdDvo> selectPartMasterPdInfo(String itmPdCd);

    int selectNewOstrSn(String itmOstrNo);

    int selectNewStrSn(String itmStrNo);

    int selectNewOstrAkSn(String ostrAkNo);

    int insertItmOstrIz(WsnaTransferMaterialsIostDvo dvo);

    int insertItmStrIz(WsnaTransferMaterialsIostDvo dvo);

    int updateItmOstrIzForStr(WsnaTransferMaterialsIostDvo dvo);

    int updateItmStrIzForStr(WsnaTransferMaterialsIostDvo dvo);

    int updateCstSvItmStocForStr(WsnaTransferMaterialsIostDvo dvo);

    List<WsnaTransferMaterialsStrDvo> selectItmStrIz(WsnaTransferMaterialsHgrDvo dvo);

    int insertItmOstrAkIz(WsnaTransferMaterialsOstrAkDvo dvo);

    int updateItmOstrAkIz(WsnaTransferMaterialsOstrAkDvo dvo);

    int updateItmOstrAkIzForTransfer(WsnaTransferMaterialsReqDvo dvo);

}
