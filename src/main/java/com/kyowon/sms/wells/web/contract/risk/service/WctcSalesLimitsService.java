package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.*;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcSalesLimitsConverter;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcSalesLimitsMapper;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcSalesLimitsService {

    private final WctcSalesLimitsMapper mapper;
    private final WctcSalesLimitsConverter converter;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public FindBlacklistRes getBlacklistInfos(String cntrNo, int cntrSn) {
        return mapper.selectBlacklistInfos(cntrNo, cntrSn);
    }

    public PagingResult<SearchBlacklistRes> getBlacklistPages(SearchBlacklistReq dto, PageInfo pageInfo) {
        return mapper.selectBlacklistPages(dto, pageInfo);
    }

    public List<SearchBlacklistRes> getBlacklistsForExcelDownload(SearchBlacklistReq dto) {
        return mapper.selectBlacklistPages(dto);
    }

    @Transactional
    public int saveBlacklists(List<SaveBlacklistReq> dtos) {
        int processCount = 0;
        Iterator<SaveBlacklistReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveBlacklistReq dto = iterator.next();
            WctcSellLimitOjIzDvo dvo = converter.mapSaveBlacklistReqToWctcSellLimitOjIzDvo(dto);
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> mapper.insertBlacklist(dvo);
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateBlacklist(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeBlacklists(List<String> sellLmIds) {
        int processCount = 0;
        int result;
        for (Iterator<String> iterator = sellLmIds.iterator(); iterator.hasNext(); processCount += result) {
            result = mapper.deleteBlacklist(iterator.next());
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }
        return processCount;
    }

    public PagingResult<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssPages(
        SearchEntrpJLmOjReq dto, PageInfo pageInfo
    ) {
        return mapper.selectEntrepreneurJoinLmOjss(dto, pageInfo);
    }

    public List<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssExcelDownload(
        SearchEntrpJLmOjReq dto
    ) {
        return mapper.selectEntrepreneurJoinLmOjss(dto);
    }

    @Transactional
    public int saveEntrepreneurJoinLmOjss(List<SaveEntrpJLmOjReq> dtos) {
        int processCount = 0;
        Iterator<SaveEntrpJLmOjReq> iterator = dtos.iterator();

        while (iterator.hasNext()) {
            SaveEntrpJLmOjReq dto = iterator.next();
            WctcSellLimitOjIzDvo dvo = converter.mapSaveEntrpJLmOjReqToDvo(dto);
            String sellLmDv = dvo.getSellLmDv();
            String sellLmRlsDtm = dvo.getSellLmRlsDtm(); //해제일자
            String sellLmOcDtm = dvo.getSellLmOcDtm(); //발생일자
            String[] param = {Integer.toString(dvo.getDataRow() + 1)};
            boolean hasSellLmRlsDtm = StringUtils.isEmpty(sellLmRlsDtm); // 해제일자 존재여부
            int parsedSellRlsDtm = !hasSellLmRlsDtm ? Integer.parseInt(sellLmRlsDtm) : 0;
            int parsedSellLmDtm = Integer.parseInt(sellLmOcDtm);

            processCount += switch (dvo.getRowState()) {
                case CommConst.ROW_STATE_UPDATED -> {
                    if ("3".equals(sellLmDv))
                        BizAssert.isTrue(hasSellLmRlsDtm, "MSG_ALT_BAD_RLS_ERR", param);

                    if ("4".equals(sellLmDv))
                        BizAssert.isFalse(
                            (hasSellLmRlsDtm || parsedSellRlsDtm < parsedSellLmDtm), "MSG_ALT_RLS_DT_ERR", param
                        );

                    String sellLmBzrno = mapper.selectEntrepreneurJoinLmOjssCheck(dvo.getSellLmId());
                    BizAssert.isTrue(dvo.getSellLmBzrno().equals(sellLmBzrno), "MSG_ALT_LM_BZRNO_ERR");

                    int result = mapper.updateEntrepreneurJoinLmOjss(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }

                case CommConst.ROW_STATE_CREATED -> {
                    if ("3".equals(sellLmDv))
                        BizAssert.isTrue(hasSellLmRlsDtm, "MSG_ALT_RLS_DT_ERR", param);

                    if ("4".equals(sellLmDv))
                        BizAssert.isFalse(
                            (hasSellLmRlsDtm || parsedSellRlsDtm < parsedSellLmDtm), "MSG_ALT_RLS_DT_ERR", param
                        );

                    BizAssert.isTrue(sellLmOcDtm.length() == 8, "MSG_ALT_BAD_OC_DT_ERR");

                    String sellLmBzrno = dvo.getSellLmBzrno().replaceAll("-", "");

                    BizAssert
                        .isTrue(
                            sellLmBzrno.length() == 10, "MSG_ALT_ROW_IS_WRONG",
                            new String[] {param[0],
                                messageResourceService.getMessage("MSG_TXT_ENTRP_NO") + "[" + dvo.getSellLmBzrno()
                                    + "]"}
                        );

                    dvo.setSellLmBzrno(sellLmBzrno);
                    int result = mapper.insertEntrepreneurJoinLmOjss(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeEntrepreneurJoinLmOjss(List<String> sellLmIds) {
        int processCount = 0;

        for (String sellLmId : sellLmIds) {
            int result = mapper.deleteEntrepreneurJoinLmOjss(sellLmId);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public List<SaveEntrpJLmOjReq> saveEntrepreneurForExcelUpload(MultipartFile file)
        throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("sellLmDv", messageResourceService.getMessage("MSG_TXT_INF_CLS"));
        headerTitle.put("sellLmBzrno", messageResourceService.getMessage("MSG_TXT_ENTRP_NO"));
        headerTitle.put("sellLmRsonCd", messageResourceService.getMessage("MSG_TXT_DFT_CD"));
        headerTitle.put("sellLmOcDtm", messageResourceService.getMessage("MSG_TXT_OCCUR_DATE"));
        headerTitle.put("sellLmRlsDtm", messageResourceService.getMessage("MSG_TXT_CNC_DT"));
        headerTitle.put("sellLmRson", messageResourceService.getMessage("MSG_TXT_OCC_RSN"));

        // 업로드 엑셀 파일 DRM 복호화
        List<WctcSellLimitOjIzDvo> dvos = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WctcSellLimitOjIzDvo.class);
        List<SaveEntrpJLmOjReq> result = new LinkedList<>();

        int row = 1;
        int dataRow = 0;
        for (WctcSellLimitOjIzDvo dvo : dvos) {
            if (StringUtils.isEmpty(dvo.getSellLmRlsDtm())) {
                dvo.setSellLmRlsDtm(null);
            }
            dvo.setDataRow(dataRow);
            dvo.setRowState(CommConst.ROW_STATE_CREATED);
            result.add(converter.mapSaveEntrpJLmOjReqToDvoToSaveEntrpJLmOjReq(dvo));
            dataRow++;
        }
        // 업로드 엑셀 파일 DRM 복호화
        Map<String, String> kvForValidation;
        for (WctcSellLimitOjIzDvo req : dvos) {
            kvForValidation = Map.of(
                "sellLmDv", req.getSellLmDv(),
                "sellLmBzrno", req.getSellLmBzrno(),
                "sellLmRsonCd", req.getSellLmRsonCd(),
                "sellLmOcDtm", req.getSellLmOcDtm()
            );

            // 유효성검사
            for (String key : kvForValidation.keySet()) {
                BizAssert.hasText(
                    kvForValidation.get(key), "MSG_ALT_INVALID_UPLOAD_DATA",
                    new String[] {String.valueOf(row), headerTitle.get(key), kvForValidation.get(key)}
                );
            }
        }
        return result;
    }
}
