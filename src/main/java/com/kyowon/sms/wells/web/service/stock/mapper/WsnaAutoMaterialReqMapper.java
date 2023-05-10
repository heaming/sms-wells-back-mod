package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAutoMaterialReqDvo;

/**
 * <pre>
 * W-SV-S-0023 엔지니어 자재 자동 신청
 * W-SV-S-0065 서비스운영팀 자재자동신청 관련 데이터 이월
 * </pre>
 *
 * @author inho.choi
 * @since 2023.05.09
 */
@Mapper
public interface WsnaAutoMaterialReqMapper {

    List<SearchRes> selectOstrAkNo();

    int insertAutoMaterialEngineerReq(List<WsnaAutoMaterialReqDvo> list);

    int insertAutoMaterialBranchReq(List<WsnaAutoMaterialReqDvo> list);

    int insertOteamMatAutoAplcIz();

    int updateItmOstrAkIz();
}
