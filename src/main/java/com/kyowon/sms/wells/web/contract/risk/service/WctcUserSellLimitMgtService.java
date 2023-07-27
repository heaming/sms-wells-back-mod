package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcUserSellLimitMgtConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcUserSellLimitDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcUserSellLimitMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcUserSellLimitMgtService {
    private final WctcUserSellLimitMgtMapper mapper;
    private final WctcUserSellLimitMgtConverter converter;

    public List<SearchRes> getSellLimitLists(SearchReq dto) {
        return mapper.selectSellLimitLists(dto);
    }

    @Transactional
    public int saveSellBase(List<SaveReq> dtos) {
        int processCount = 0;
        Iterator<SaveReq> iterator = dtos.iterator();

        while (iterator.hasNext()) {
            SaveReq dto = iterator.next();
            WctcUserSellLimitDvo userSellLimit = converter.mapSaveReqToWctcUserSellLimitDvo(dto);
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    String cdCheck = mapper.selecBaseCdCheck(dto);
                    BizAssert.isTrue(cdCheck != "0", "MSG_ALT_DUPLICATE_EXISTS");
                    int result = mapper.insertSellBaseBas(userSellLimit);

                    if (StringUtils.isNotEmpty(dto.sellBaseChnl())) {
                        userSellLimit.setSellBaseCd("11");
                        userSellLimit.setSellBaseChval(dto.sellBaseChnl());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.deptCd())) {
                        userSellLimit.setSellBaseCd("12");
                        userSellLimit.setSellBaseChval(dto.deptCd());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBaseUsr())) {
                        userSellLimit.setSellBaseCd("13");
                        userSellLimit.setSellBaseChval(dto.sellBaseUsr());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.copnDvCd())) {
                        userSellLimit.setSellBaseCd("31");
                        userSellLimit.setSellBaseChval(dto.copnDvCd());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.zip())) {
                        userSellLimit.setSellBaseCd("32");
                        userSellLimit.setSellBaseChval(dto.zip());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.pdCd())) {
                        userSellLimit.setSellBaseCd("25");
                        userSellLimit.setSellBaseChval(dto.pdCd());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    /*
                    if (StringUtils.isNotEmpty(dto.pdMclsfNm())) {
                        userSellLimit.setSellBaseCd("22");
                        userSellLimit.setSellBaseChval(dto.pdMclsfNm());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.pdLclsfNm())) {
                        userSellLimit.setSellBaseCd("23");
                        userSellLimit.setSellBaseChval(dto.pdLclsfNm());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                     */
                    if (StringUtils.isNotEmpty(dto.sellBasePrd())) {
                        userSellLimit.setSellBaseCd("27");
                        userSellLimit.setSellBaseChval(dto.sellBasePrd());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    if (StringUtils.isNotEmpty(dto.sellBaseSellTp())) {
                        userSellLimit.setSellBaseCd("28");
                        userSellLimit.setSellBaseChval(dto.sellBaseSellTp());
                        mapper.insertSellBaseDtl(userSellLimit);
                    }
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateSellBaseBas(userSellLimit);
                    String sellBaseSn = "";
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "11");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("11");
                        userSellLimit.setSellBaseChval(dto.sellBaseChnl());
                        userSellLimit.setSellBaseSn(mapper.selecSellBaseSn(dto.sellBaseId(), "11"));
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.sellBaseChnl())) {
                            userSellLimit.setSellBaseCd("11");
                            userSellLimit.setSellBaseChval(dto.sellBaseChnl());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "12");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("12");
                        userSellLimit.setSellBaseChval(dto.deptCd());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.deptCd())) {
                            userSellLimit.setSellBaseCd("12");
                            userSellLimit.setSellBaseChval(dto.deptCd());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "13");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("13");
                        userSellLimit.setSellBaseChval(dto.sellBaseUsr());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.sellBaseUsr())) {
                            userSellLimit.setSellBaseCd("13");
                            userSellLimit.setSellBaseChval(dto.sellBaseUsr());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "31");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("31");
                        userSellLimit.setSellBaseChval(dto.copnDvCd());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.copnDvCd())) {
                            userSellLimit.setSellBaseCd("31");
                            userSellLimit.setSellBaseChval(dto.copnDvCd());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "32");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("32");
                        userSellLimit.setSellBaseChval(dto.zip());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.zip())) {
                            userSellLimit.setSellBaseCd("32");
                            userSellLimit.setSellBaseChval(dto.zip());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "25");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("25");
                        userSellLimit.setSellBaseChval(dto.pdCd());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.pdCd())) {
                            userSellLimit.setSellBaseCd("25");
                            userSellLimit.setSellBaseChval(dto.pdCd());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "22");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("22");
                        userSellLimit.setSellBaseChval(dto.pdMclsfNm());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.pdMclsfNm())) {
                            userSellLimit.setSellBaseCd("22");
                            userSellLimit.setSellBaseChval(dto.pdMclsfNm());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "23");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("23");
                        userSellLimit.setSellBaseChval(dto.pdLclsfNm());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.pdLclsfNm())) {
                            userSellLimit.setSellBaseCd("23");
                            userSellLimit.setSellBaseChval(dto.pdLclsfNm());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "27");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("27");
                        userSellLimit.setSellBaseChval(dto.sellBasePrd());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.sellBasePrd())) {
                            userSellLimit.setSellBaseCd("27");
                            userSellLimit.setSellBaseChval(dto.sellBasePrd());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    sellBaseSn = mapper.selecSellBaseSn(dto.sellBaseId(), "28");
                    if (StringUtils.isNotEmpty(sellBaseSn)) {
                        userSellLimit.setSellBaseCd("28");
                        userSellLimit.setSellBaseChval(dto.sellBaseSellTp());
                        userSellLimit.setSellBaseSn(sellBaseSn);
                        mapper.updateSellBaseDtl(userSellLimit);
                    } else {
                        if (StringUtils.isNotEmpty(dto.sellBaseSellTp())) {
                            userSellLimit.setSellBaseCd("28");
                            userSellLimit.setSellBaseChval(dto.sellBaseSellTp());
                            userSellLimit.setSellBaseSn(sellBaseSn);
                            mapper.insertSellBaseDtl(userSellLimit);
                        }
                    }
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }

        return processCount;
    }

    @Transactional
    public int removeSellBase(List<String> sellBaseIds) {
        int processCount = 0;
        int result = 0;
        for (Iterator<String> iterator = sellBaseIds.iterator(); iterator.hasNext(); processCount += result) {
            String sellBaseId = iterator.next();
            mapper.removeSellBaseBas(sellBaseId);
            result = mapper.removeSellBaseDtl(sellBaseId);
        }
        return processCount;
    }

}
