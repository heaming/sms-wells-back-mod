package com.kyowon.sms.wells.web.contract.salesstatus.service;

import com.kyowon.sms.wells.web.contract.salesstatus.converter.WcteSecProductConverter;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.*;
import com.kyowon.sms.wells.web.contract.salesstatus.mapper.WcteSecProductMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.*;

@Service
@RequiredArgsConstructor
public class WcteSecProductService {

    private final WcteSecProductMapper mapper;
    private final WcteSecProductConverter converter;

    private final MessageResourceService messageResourceService;

    public PagingResult<SearchReservationRes> getReservationPages(SearchReservationReq dto, PageInfo pageInfo) {
        return mapper.selectReservationPages(dto, pageInfo);
    }

    public List<SearchReservationRes> getReservationsForExcelDownload(SearchReservationReq dto) {
        return mapper.selectReservationPages(dto);
    }

    public PagingResult<SearchConfirmRes> getConfirmPages(SearchConfirmReq dto, PageInfo pageInfo) {
        return mapper.selectConfirms(dto, pageInfo);
    }

    /**
     * 삼성전자 확정일 목록 조회
     * @param dto SearchConfirmReq
     * @return 확정일 리스트
     */
    public List<SearchConfirmRes> getConfirms(SearchConfirmReq dto) {
        return mapper.selectConfirms(dto);
    }

    /**
     * 확정일 단건 생성
     * @param dto 확정일 생성 요청 객체
     * @return 업데이트 건수
     */
    @Transactional
    public int createConfirm(CreateConfirmReq dto) {
        WcteConfirmValidationDvo validationObject = mapper.selectConfirmValidation(dto.cntrNo(), dto.cntrSn()).orElseThrow(
            () -> new BizException("해당 계약 번호를 가진 계약은 존재하지 않습니다.")
        );

        if (!StringUtils.isEmpty(validationObject.getCanDt())) {
            throw new BizException("취소된 고객입니다!");
        }
        if ("Y".equals(validationObject.getCntrCnfmYn())) {
            throw new BizException("매출확정된 고객입니다!");
        }
        if (!StringUtils.isEmpty(validationObject.getIstDt())) {
            throw new BizException("이미 설치가 되었습니다!");
        }

        /* TODO: 확인 필요, MSG */
        if (!"Y".equals(validationObject.getSecPdYn())) {
            throw new BizException("삼성제품이 아닙니다!");
        }

        if ("Y".equals(validationObject.getOstrRgstYn())) {
            throw new BizException("제품 출고 요청이 이미 되었습니다!");
        }

        /* 계약 상세에 배송예정일자 업데이트 */
        int processCount = mapper.updateShippingDueDate(dto.cntrNo(), dto.cntrSn(), dto.sppFshDt());
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" ) ;

        /* 계약 상세 변경 이력 생성 */
        processCount = mapper.insertContractDetailChangeHistory(dto.cntrNo(), dto.cntrSn());
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" );

        /* 매출 조정 자료 생성: 제품고유번호출고내역 생성 */
        WctePdOstrIzDvo productOutOfStorageIz = converter.mapCreateConfirmReqToWcteProdu1ctOutOfStorageIz(dto);
        productOutOfStorageIz.setSellTpCd(validationObject.getSellTpCd());
        processCount = mapper.insertProductOutOfStorageIz(productOutOfStorageIz);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" ) ;

        /* 삼성API 테이블 기록: 배송업체송장처리내역 생성 */
        WcteInvoiceProcessIzDvo invoiceProcessIz = converter.mapCreateConfirmReqToWcteInvoiceProcessIz(dto);
        processCount = mapper.insertInvoiceProcessIz(invoiceProcessIz);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" ) ;

        /*배송업체송장처리이력 생성*/
        processCount = mapper.insertInvoiceProcessHistory(mapper.selectInvoiceProcessIzPk(invoiceProcessIz));
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" );

        return processCount;
    }

    @Transactional
    public int createConfirms(List<CreateConfirmReq> list) {
        int processCount = 0;
        for (CreateConfirmReq req : list) {
            processCount += createConfirm(req);
        }
        return processCount;
    }

    /**
     * 삼성전자 미설치 페이지 조회 서비스
     *
     * @param dto 조회조건
     * @param pageInfo 페이지 정보
     * @return 삼성전자  미설치 정보 list
     */
    public PagingResult<SearchNotInstalledRes> getNotInstalledPages(SearchNotInstalledReq dto, PageInfo pageInfo) {
        return mapper.selectNotInstalledIzs(dto, pageInfo);
    }


    /**
     * 미설치 엑셀 데이터 검증
     * @param excelData 엑셀 데이터 리스트
     * @return 에러 정보 목록
`     */
    public WcteValidateListDvo<WcteSecNistlDvo> validateNotInstalledIzs(List<WcteSecNistlDvo> excelData) {
        List<ExcelUploadErrorDvo> errList = new LinkedList<>();
        List<WcteSecNistlDvo> modifiedList = new ArrayList<>();
        for (int idx = 0; idx < excelData.size(); idx++) {
            WcteSecNistlDvo row = new WcteSecNistlDvo(excelData.get(idx));
            Optional<WcteSecNistlValidationDvo> optional =  mapper.selectSecNistlValidation(row.getCntrNo(), Integer.parseInt(row.getCntrSn()));

            boolean valid = true;

            if (optional.isEmpty()) {
                ExcelUploadErrorDvo eueDvo = new ExcelUploadErrorDvo();
                eueDvo.setHeaderName(messageResourceService.getMessage("계약번호"));
                eueDvo.setErrorRow(idx + 1);
                eueDvo.setErrorData(messageResourceService.getMessage("해당 계약 번호를 가진 계약이 존재하지 않습니다.(계약번호 : {0}-{1})", row.getCntrNo(), row.getCntrSn()));
                eueDvo.setHeaderName("계약번호");
                eueDvo.setErrorData("해당 계약 번호를 가진 진행중인 계약이 존재하지 않습니다.(계약번호 : {0}-{1})");
                errList.add(eueDvo);

                continue;
            }
            WcteSecNistlValidationDvo validationDvo = optional.get();
            if (!CtContractConst.SELL_TP_CD_SPAY.equals(validationDvo.getSellTpCd()) &&
                !CtContractConst.SELL_TP_CD_RNTL.equals(validationDvo.getSellTpCd())) {
                ExcelUploadErrorDvo eueDvo = new ExcelUploadErrorDvo();
                eueDvo.setHeaderName(messageResourceService.getMessage("계약번호"));
                eueDvo.setErrorRow(idx + 1);
                eueDvo.setErrorData(messageResourceService.getMessage("렌탈, 일시불만 가능합니다.(계약번호 : {0}-{1})", row.getCntrNo(), row.getCntrSn()));
                eueDvo.setHeaderName("계약번호");
                eueDvo.setErrorData("렌탈, 일시불만 가능합니다.(계약번호 : {0}-{1})");
                errList.add(eueDvo);

                valid = false;
            }
            if (!"Y".equals(validationDvo.getSecPdYn())) {
                ExcelUploadErrorDvo eueDvo = new ExcelUploadErrorDvo();
                eueDvo.setHeaderName(messageResourceService.getMessage("계약번호"));
                eueDvo.setErrorRow(idx + 1);
                eueDvo.setErrorData(messageResourceService.getMessage("삼성전자 제품에 대한 계약이 맞는지 확인해주세요..(계약번호 : {0}-{1})", row.getCntrNo(), row.getCntrSn()));
                eueDvo.setHeaderName("계약번호");
                eueDvo.setErrorData("삼성전자 제품에 대한 계약이 맞는지 확인해주세요.");
                errList.add(eueDvo);

                valid = false;
            }
            if ("Y".equals(validationDvo.getCanOrRejYn())) {
                ExcelUploadErrorDvo eueDvo = new ExcelUploadErrorDvo();
                eueDvo.setHeaderName(messageResourceService.getMessage("계약번호"));
                eueDvo.setErrorRow(idx + 1);
                eueDvo.setErrorData(messageResourceService.getMessage("취소되거나 방문을 거절한 계약입니다. (계약번호 : {0}-{1})", row.getCntrNo(), row.getCntrSn()));
                eueDvo.setHeaderName("계약번호");
                eueDvo.setErrorData("취소되거나 방문을 거절한 계약입니다.");

                valid = false;
            }

            if (valid) {
                row.setSellTpCd(validationDvo.getSellTpCd());
                modifiedList.add(row);
            }
        }
        boolean valid = errList.isEmpty();

        return WcteValidateListDvo.<WcteSecNistlDvo>builder()
            .valid(valid)
            .errList(errList)
            .cleansingList(modifiedList)
            .build();
    }

    @Transactional
    int createNotInstalledIz(WcteSecNistlDvo dvo) {
        return mapper.insertNotInstalledIz(dvo);
    }

    /**
     * 삼성전자 미설치 생성
     * @param list 정보 객체
     * @return 업데이트 건수
     */
    @Transactional
    public int createNotInstalledIzs(List<WcteSecNistlDvo> list) {
        int processCount = 0;
        for (WcteSecNistlDvo dvo : list) {
            processCount += createNotInstalledIz(dvo);
        }
        return processCount;
    }

    /**
     * 중분류 된 상품 목록 조회
     *
     * @return List<SearchSecPdBycfRes>
     */
    public List<SearchSecPdBycfRes> getSecPdBycfs() {
        return mapper.selectSecPdBycfs().stream()
                .map(converter::mapWcteSecPdBycfDvoToSearchSecPdBycfRes)
                .collect(Collectors.toList());
    }


    /**
     * 삼성전자 상품관리 배송 페이지 조회
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 페이징 된 배송 목록
     */
    public PagingResult<SearchShippingRes> getShippingPages(SearchShippingReq dto, PageInfo pageInfo) {
        return mapper.selectShippings(dto, pageInfo);
    }

    /**
     * 삼성전자 상품관리 배송 다건조회
     * @param dto 조회조건
     * @return 배송 목록
     */
    public List<SearchShippingRes> getShippings(SearchShippingReq dto) {
        return mapper.selectShippings(dto);
    }

    public PagingResult<SearchFreeAsRes> getFreeASPages(SearchFreeAsReq dto, PageInfo pageInfo) {
        return mapper.selectFreeASs(dto, pageInfo);
    }

    public List<SearchFreeAsRes> getFreeASs(SearchFreeAsReq dto) {
        return mapper.selectFreeASs(dto);
    }
}
