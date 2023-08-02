package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.*;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sflex.common.common.dvo.FormatAddressDvo;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sflex.common.zcommon.constants.CmSujiewonConst;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzContractNumberService;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractProductDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractCreateMapper;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaCompanyInstallConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCompanyInstallDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractAdrRelDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractAdrpcBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaCompanyInstallMapper;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep3Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaCompanyInstallService {

    private final WctaCompanyInstallMapper mapper;
    private final WctaCompanyInstallConverter converter;
    private final ExcelReadService excelReadService;
    private final MessageResourceService messageResourceService;
    private final WctzContractNumberService cntrNoService;

    private final WctiContractCreateMapper contractMapper;
    private final WctzHistoryService historyService;

    private final SujiewonService sujiewonService;
    private final WctaContractRegStep3Service contractStep3Service;
    private final WctaContractRegStep3Mapper contractStep3Mapper;

    public PagingResult<SearchRes> getCompanyInstallPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> res = converter
            .mapAllDvoToSearchRes(mapper.selectCompanyInstallPages(converter.mapSearchReqToDvo(dto), pageInfo));
        res.setPageInfo(pageInfo);

        return res;
    }

    public List<SearchRes> getCompanyInstallsForExcelDownload(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectCompanyInstallPages(converter.mapSearchReqToDvo(dto)));
    }

    /**
     * WctiContractCreateService 의 계약 생성 서비스 활용
     * 추가로 insert 가 필요한 칼럼은 update 함.
     * @param dtos
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveCompanyInstalls(List<SaveReq> dtos) throws Exception {
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String now = DateUtil.todayNnow();

        for (SaveReq dto : dtos) {
            WctaCompanyInstallDvo dvo = converter.mapSaveReqToCompanyInstallDvo(dto);

            dvo.setCntrRcpTm("000000");
            dvo.setCntrNo(cntrNoService.getContractNumber("").cntrNo());
            dvo.setCntrSn("1");

            WctiContractCreateDvo contract = converter.mapCompanyInstallDvoToContractDvo(dvo);
            contract.setSellInflwChnlDtlCd("1030");
            contract.setSellOgTpCd(session.getOgTpCd());
            contract.setSellPrtnrNo(session.getEmployeeIDNumber());
            contract.setCntrPrgsStatCd("60");
            contract.setCntrTpCd("0" + contract.getCopnDvCd());
            contract.setCntrDtlStatCd(StringUtils.isEmpty(dvo.getRtnDt()) ? "101" : "301");

            // 계약기본
            contractMapper.insertContractBase(contract);

            // 계약기본이력
            historyService.createContractBasicChangeHistory(
                WctzCntrBasicChangeHistDvo.builder()
                    .cntrNo(contract.getCntrNo())
                    .build()
            );

            WctiContractProductDvo contractProduct = mapper.selectProductInfo(dvo.getBasePdCd())
                .orElseThrow(() -> new BizException(String.format("등록된 상품코드(=%s)가 아닙니다.", dvo.getBasePdCd())));

            // 계약상세
            contractMapper.insertContractDetail(contract, contractProduct);
            mapper.updateContractDetail(dvo);

            // 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(contract.getCntrNo())
                    .cntrSn(Integer.parseInt(contract.getCntrSn()))
                    .build()
            );

            // 계약고객관계(계약자)
            contractMapper.insertContractCustomerRelation(contract, contract.getCntrCstNo());

            // 계약-파트너관계 저장
            contractMapper.insertContractPartnerRelation(contract);

            // 계약/설치 주소 저장
            WctaContractAdrpcBasDvo adrpc = converter.mapCompanyInstallDvoToAdrpcBasDvo(dvo);
            adrpc.setAdrDvCd("2");
            adrpc.setAdrId(getAdrId(adrpc.getAdr(), adrpc.getAdrDtl(), adrpc.getAdrDvCd()));
            adrpc.setOgTpCd(session.getOgTpCd());
            contractStep3Mapper.insertCntrAdrpcBasStep3(adrpc);

            contractStep3Mapper.insertCntrAdrRelStep3(
                WctaContractAdrRelDvo.builder()
                    .vlStrtDtm(now)
                    .vlEndDtm(CtContractConst.END_DTM)
                    .adrpcTpCd("3")
                    .cntrUnitTpCd("020")
                    .dtlCntrNo(contract.getCntrNo())
                    .dtlCntrSn(Integer.parseInt(contract.getCntrSn()))
                    .cntrAdrpcId(adrpc.getCntrAdrpcId())
                    .build()
            );

            // 결제정보 저장
            contractStep3Service.createStlmInfo(
                DateUtil.todayNnow(), contract.getCntrNo(), new HashMap<>(), Integer.parseInt(contract.getCntrSn()),
                null, "", "",
                contract.getCntrCstNo()
            );

            // 계약-상품관계 저장
            contractMapper.insertContractProductRelation(contract);

            // 웰스계약상세 저장
            contractMapper.insertContractWellsDetail(contract);
            mapper.updateContractWellsDetail(dvo);
        }
        //throw new NullPointerException();
        return 0;
    }

    private boolean isValidAddress(String basAdr, String dtlAdr) {
        return StringUtils.isNotEmpty(basAdr) && StringUtils.isNotEmpty(dtlAdr);
    }

    private String getAdrId(String baseAdr, String dtlAdr, String adrDvCd) throws Exception {
        String addressType = "2".equals(adrDvCd) ? CmSujiewonConst.FORMAT_TYPE_LOT_NUMBER
            : CmSujiewonConst.FORMAT_TYPE_ROAD_ADDRESS;

        // 수지원넷 주소정제
        FormatAddressDvo formatAddress = sujiewonService.getFormattedAddress(
            baseAdr + " " + dtlAdr, addressType
        );

        return formatAddress.getAdrId();
    }

    public List<SaveReq> saveEntrepreneurForExcelUpload(MultipartFile file)
        throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("copnDvCd", "구분(1:개인, 2:사업)"); //A
        headerTitle.put("cstKnm", "계약자이름"); //
        headerTitle.put("bzrno", "계약생년월일/사업자번호"); //
        headerTitle.put("gender", "계약성별)");
        headerTitle.put("mpno", "계약핸드폰번호");
        headerTitle.put("telNoYn", "계약전화번호 등록여부(Y,N)");
        headerTitle.put("telNo", "계약전화번호");
        headerTitle.put("zip", "계약우편번호");
        headerTitle.put("basAdr", "계약주소");
        headerTitle.put("dtlAdr", "계약상세주소");

        headerTitle.put("istCopnDvCd", "구분(1:개인, 2:사업)"); // K
        headerTitle.put("rcgvpKnm", "설치처이름");
        headerTitle.put("istBzrno", "생년월일/사업자번호");
        headerTitle.put("installMpno", "핸드폰번호");
        headerTitle.put("installTelNoYn", "전화번호 등록여부(Y,N)");
        headerTitle.put("installTelNo", "전화번호");
        headerTitle.put("installZip", "우편번호"); //
        headerTitle.put("installBasAdr", "주소"); //
        headerTitle.put("installDtlAdr", "상세주소"); //
        headerTitle.put("cntrRcpDt", "접수일"); //

        headerTitle.put("basePdCd", "설치상품"); // U
        headerTitle.put("svPrd", "주기"); //
        headerTitle.put("svPdTpCd", "용도"); //
        headerTitle.put("pdGdCd", "등급");
        headerTitle.put("coIstDvCd", "설치구분");
        headerTitle.put("expnYn", "체험단여부");
        headerTitle.put("coIstUswyCd", "설치용도");
        headerTitle.put("serialNo", "Serial No");
        headerTitle.put("coIstMngtDvCd", "관리구분");
        headerTitle.put("coCd", "관리회사");

        headerTitle.put("ogCd", "관리부서"); //
        headerTitle.put("istPlcTpCd", "설치장소코드");
        headerTitle.put("istPlcTpNm", "설치장소명");
        headerTitle.put("wmDvCd", "WM구분");
        headerTitle.put("filterExp", "필터비용");
        headerTitle.put("filterRate", "비율");
        headerTitle.put("frisuBfsvcPtrmN", "무상기간");
        headerTitle.put("frisuAsPtrmN", "A/S기간");
        headerTitle.put("rtnDt", "반품일");
        headerTitle.put("istAkArtcMoCn", "참고사항");

        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

        // 업로드 엑셀 파일 DRM 복호화
        List<WctaCompanyInstallDvo> dvos = excelReadService.readExcel(file, meta, WctaCompanyInstallDvo.class);

        int row = 1;
        Map<String, String> kvForValidation;

        // 필수체크
        for (WctaCompanyInstallDvo dvo : dvos) {
            kvForValidation = Map.of(
                "copnDvCd", dvo.getCopnDvCd(),
                "cstKnm", dvo.getCstKnm(),
                "bzrno", dvo.getBzrno(),
                "installZip", dvo.getInstallZip(),
                "installBasAdr", dvo.getInstallBasAdr(),
                "installDtlAdr", dvo.getInstallDtlAdr(),
                "cntrRcpDt", dvo.getCntrRcpDt(),
                "basePdCd", dvo.getBasePdCd(),
                "svPrd", dvo.getSvPrd(),
                "svPdTpCd", dvo.getSvPdTpCd()
            );

            for (String key : kvForValidation.keySet()) {
                BizAssert.hasText(
                    kvForValidation.get(key),
                    "MSG_ALT_INVALID_UPLOAD_DATA",
                    new String[] {String.valueOf(row), headerTitle.get(key), kvForValidation.get(key)}
                );
            }
            row++;
        }

        row = 1;
        String[] tno = null;
        // 유효성검사 및 데이터 재 설정
        for (WctaCompanyInstallDvo dvo : dvos) {
            if ("1".equals(dvo.getCopnDvCd())) {
                dvo.setBryyMmdd(dvo.getBzrno());
                dvo.setBzrno("");
            }

            if (StringUtils.isNotEmpty(dvo.getMpno())) {
                tno = checkTelNoValidate(dvo.getMpno(), headerTitle.get("mpno"), row);
                dvo.setCralLocaraTno(tno[0]);
                dvo.setMexnoEncr(tno[1]);
                dvo.setCralIdvTno(tno[2]);
            }

            if (StringUtils.isNotEmpty(dvo.getInstallMpno())) {
                tno = checkTelNoValidate(dvo.getInstallMpno(), headerTitle.get("installMpno"), row);
                dvo.setInstallCralLocaraTno(tno[0]);
                dvo.setInstallMexnoEncr(tno[1]);
                dvo.setInstallCralIdvTno(tno[2]);
            }

            if ("Y".equals(dvo.getInstallTelNoYn()) && StringUtils.isNotEmpty(dvo.getInstallTelNo())) {
                tno = checkTelNoValidate(dvo.getInstallTelNo(), headerTitle.get("installTelNo"), row);
                dvo.setInstallLocaraTno(tno[0]);
                dvo.setInstallExnoEncr(tno[1]);
                dvo.setInstallIdvTno(tno[2]);
            }

            dvo.setInstallZip(StringUtils.remove(dvo.getInstallZip(), "-"));
            BizAssert.isTrue(
                DateUtil.isValid(dvo.getCntrRcpDt(), "yyyyMMdd"), "MSG_ALT_INVALID_UPLOAD_DATA",
                new String[] {String.valueOf(row), headerTitle.get("cntrRcpDt"), dvo.getCntrRcpDt()}
            );

            checkNumValidate(dvo.getSvPrd(), headerTitle.get("svPrd"), row);
            checkNumValidate(dvo.getSvPdTpCd(), headerTitle.get("svPdTpCd"), row);
            checkNumValidate(dvo.getCoIstDvCd(), headerTitle.get("coIstDvCd"), row);
            checkNumValidate(dvo.getCoIstUswyCd(), headerTitle.get("coIstUswyCd"), row);
            checkNumValidate(dvo.getCoIstMngtDvCd(), headerTitle.get("coIstMngtDvCd"), row);
            checkNumValidate(dvo.getFrisuBfsvcPtrmN(), headerTitle.get("frisuBfsvcPtrmN"), row);
            checkNumValidate(dvo.getFrisuAsPtrmN(), headerTitle.get("frisuAsPtrmN"), row);

            // 고객번호 조회
            dvo.setCntrCstNo(getCustomer(dvo));

            row++;
        }

        int dataRow = 0;
        List<SaveReq> result = new LinkedList<>();
        for (WctaCompanyInstallDvo dvo : dvos) {
            dvo.setDataRow(dataRow);
            dvo.setRowState(CommConst.ROW_STATE_CREATED);
            result.add(converter.mapCompanyInstallDvoToSaveReq(dvo));
            dataRow++;
        }
        return result;
    }

    private String[] checkTelNoValidate(String text, String header, int row) {
        String[] tno = StringUtils.split(text, "-");
        BizAssert.isTrue(
            tno.length == 3,
            "MSG_ALT_INVALID_UPLOAD_DATA", new String[] {String.valueOf(row), header, text}
        );
        return tno;
    }

    private void checkNumValidate(String text, String header, int row) {
        if (StringUtils.isNotEmpty(text)) {
            BizAssert.isTrue(
                StringUtils.isNumeric(text), "MSG_ALT_INVALID_UPLOAD_DATA",
                new String[] {String.valueOf(row), header, text}
            );
        }
    }

    private String getCustomer(WctaCompanyInstallDvo dvo) {
        List<String> csts = mapper.selectCustomer(dvo);
        BizAssert.isTrue(
            csts != null && csts.size() > 0, "MSG_ALT_NOT_EXIST_CST_INFO_IT",
            new String[] {"(" + dvo.getCstKnm() + ")"}
        );
        BizAssert.isTrue(
            csts.size() == 1, "MSG_ALT_ABNORMAL_TO_MUCH_RESULT",
            new String[] {messageResourceService.getMessage("MSG_TXT_CST_INF")}
        );

        return csts.get(0);
    }

    public List<SearchService> getCompanyServices(String pdCd) {
        return mapper.selectCompanyServices(pdCd);
    }

}
