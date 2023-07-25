package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnzHistoryMapper {
    int insertCstSvasIstOjHistByPk(String asIstOjNo);

    int insertCstSvasIstAsnHistByPk(String cstSvAsnNo);

    int insertCstSvBfsvcAsnHistByPk(String cstSvAsnNo, String dtaDlYn);

    int updateCntrDchHistByPk(String cntrNo, String cntrSn);

    int insertCntrDchHistByPk(String cntrNo, String cntrSn);

    int updateCttChHistByPk(String cttOjId);

    int insertCttChHistByPk(String cttOjId);

    int insertCstSvBfsvcAsnHistByMap(Map param);

    int insertCstBfsvcOjHistByMap(Map param);

    int insertCstSvRgbsprHistByPk(String cntrNo, String cntrSn, String svBizDclsfCd, String wkSn);
}
