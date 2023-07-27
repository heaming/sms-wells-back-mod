package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SearchRes;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractExceptionConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjDtlDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractExceptionMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractExceptionService {

    private final WctaContractExceptionMapper mapper;
    private final WctaContractExceptionConverter converter;

    public PagingResult<SearchRes> getContractExceptionPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectContractExceptionPages(dto, pageInfo);
    }

    public List<SearchRes> getContractExceptionsForExcelDownload(SearchReq dto) {
        return mapper.selectContractExceptionPages(dto);
    }

    @Transactional
    public void saveDtls(SaveReq dto, WctaContractExOjDtlDvo dtlDvo) {
        switch (dto.exProcsCd()) {
            case "W01" -> {
                dtlDvo.setValsByExProcsCd(1, "C1", dto.cstNo());
                mapper.insertContractExceptionDtl(dtlDvo);
            }
            case "W02" -> {
                dtlDvo.setValsByExProcsCd(1, "C2", dto.prtnrNo());
                mapper.insertContractExceptionDtl(dtlDvo);
                dtlDvo.setValsByExProcsCd(2, "C3", dto.ogTpCd());
                mapper.insertContractExceptionDtl(dtlDvo);
                dtlDvo.setValsByExProcsCd(3, "C4", dto.cntrNo());
                mapper.insertContractExceptionDtl(dtlDvo);
            }
            case "W04" -> {
                dtlDvo.setValsByExProcsCd(1, "C5", dto.cstNo());
                mapper.insertContractExceptionDtl(dtlDvo);
            }
        }
    }

    @Transactional
    public int saveContractExceptions(List<SaveReq> dtos) {
        int processCount = 0;
        Iterator<SaveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveReq dto = iterator.next();
            WctaContractExOjBasDvo dvo = converter.mapSaveReqToWctaContractExOjBasDvo(dto);
            WctaContractExOjDtlDvo dtlDvo = converter.mapSaveReqToWctaContractExOjDtlDvo(dto);
            switch (dto.exProcsCd()) {
                case "W01" -> {
                    dvo.setExProcsOjDrmTpCd("5");
                    dvo.setExProcsOjDrmVal(dto.prtnrNo());
                    dvo.setOgTpCd(dto.ogTpCd());
                }
                case "W02" -> {
                    dvo.setExProcsOjDrmTpCd("1");
                    dvo.setExProcsOjDrmVal(dto.cstNo());
                }
                default -> {
                    dvo.setExProcsOjDrmTpCd("2");
                    dvo.setExProcsOjDrmVal(dto.cntrNo());
                }
            }
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int result = mapper.insertContractExceptionBas(dvo);
                    dtlDvo.setExProcsId(dvo.getExProcsId());
                    saveDtls(dto, dtlDvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateContractExceptionBas(dvo);
                    // 업데이트 시 DTL delete and insert
                    mapper.deleteContractExceptionDtl(dto.exProcsId());
                    saveDtls(dto, dtlDvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeContractExceptions(List<String> keys) {
        int processCount = 0;
        int result;
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); processCount += result) {
            String exProcsId = iterator.next();
            result = mapper.deleteContractExceptionBas(exProcsId);
            mapper.deleteContractExceptionDtl(exProcsId);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }
        return processCount;
    }
}
