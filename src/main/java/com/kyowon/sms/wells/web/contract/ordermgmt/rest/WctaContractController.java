package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.*;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] Contract 공통")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaContractController {

    private final WctaContractService service;
    private final WctaContractRegService regService;
    private final WctaContractRegStep1Service step1Service;
    private final WctaContractRegStep2Service step2Service;
    private final WctaContractRegStep3Service step3Service;
    private final WctaContractRegStep4Service step4Service;

    @ApiOperation(value = "계약번호 페이징 조회", notes = "계약자명, 학습자명, 휴대전화번호, 고객번호를 입력받아 계약번호를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "istCstKnm", value = "설치자명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/numbers/paging")
    public PagingResult<SearchCntrNoRes> getContractNumberInqrPages(
        @Valid
        SearchCntrNoReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getContractNumberInqrPages(dto, pageInfo);
    }

    @ApiOperation(value = "계약서 메일 발송", notes = "계약번호를 받아 수신인에게 메일로 발송한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNos", value = "계약번호", paramType = "query", required = true, allowMultiple = true),
    })
    @PostMapping("/send-emails")
    public List<String> sendContractEmail(
        @RequestBody
        @Valid
        List<SaveSendEmailsReq> dtos
    ) throws Exception {
        return service.sendContractEmail(dtos);
    }

    @ApiOperation(value = "홈케어 계약 조회", notes = "취소일자, 예정일자 수정 대상 홈케어 계약을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNos", value = "계약번호", paramType = "query", required = true, allowMultiple = true),
    })
    @PostMapping("/homecares")
    public List<SearchHomecareContractsRes> getHomecareContracts(
        @RequestBody
        @Valid
        List<SearchHomecareContractsReq> dtos
    ) {
        return service.getHomecareContracts(dtos);
    }

    @ApiOperation(value = "홈케어 계약 저장", notes = "취소일자, 예정일자를 대상 홈케어 계약에 반영한다.")
    @PutMapping("/homecares")
    public SaveResponse saveHomecareContracts(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveHomecareContractsReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveHomecareContracts(dtos)).build();
    }

    @ApiOperation(value = "확정승인기준 리스트 - 승인 요청 구분 조회", notes = "기준일자로 유효시작, 종료일시 에 존재하며, 데이터삭제여부가 Y가 아닌확정승인기준 조회를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-request-standards")
    public List<SearchRes> getApprovalAskDivides(
        @RequestParam
        String standardDt
    ) {
        return service.getApprovalAskDivides(standardDt);
    }

    @ApiOperation(value = "확정승인기준 리스트 - 승인 요청 리스트 삭제", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @DeleteMapping("/approval-request-standards")
    public int removeApprovalAskDivides(
        @RequestBody
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return service.removeApprovalAskDivides(dtos);
    }

    @ApiOperation(value = "확정승인 요청내역 - 확정 승인 요청 내역", notes = "계약승인요청구분코드, 계약확정승인발송이력, ,계약확정승인내역 을 이용하여 확정승인 요청내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @GetMapping("/approval-requests")
    public List<SearchConfirmAprPsicAksRes> getConfirmAprPsicAks(
        @RequestParam
        String cntrNo
    ) {
        return service.getConfirmAprPsicAks(cntrNo);
    }

    @ApiOperation(value = "확정승인 요청내역 - 확정 승인 구매 내역", notes = "계약번호에 따른 교원키를 가지고 해당 교원키로 구매목록을 조회한다. 계약정보, 상품정보, 렌탈, 연체정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @GetMapping("/approval-requests/order-histories")
    public List<SearchConfirmAprPsicPrchssRes> getConfirmAprPsicPrchss(
        @RequestParam
        String cntrNo
    ) {
        return service.getConfirmAprPsicPrchss(cntrNo);
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회", notes = "화면에서 선택한 유효값 체크시 기준일자가 포함되고 체크해제시 기준일자를 제외하고 전체 조회한다. 스크롤 페이징을 이용한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrAprAkDvCd", value = "승인요청구분", paramType = "query"),
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-standards/paging")
    public PagingResult<SearchConfirmApprovalBaseRes> getConfirmApprovalBasePages(
        SearchConfirmApprovalBaseReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getConfirmApprovalBasePages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 확정 승인 기준 관리 엑셀", notes = "화면에서 선택한 유효값 체크시 기준일자가 포함되고 체크해제시 기준일자를 제외하고 전체조회 후 엑셀을 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrAprAkDvCd", value = "승인요청구분", paramType = "query"),
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-standards/excel-download")
    public List<SearchConfirmApprovalBaseRes> getConfirmApprovalBasesExcelDownload(
        SearchConfirmApprovalBaseReq dto
    ) {
        return service.getConfirmApprovalBasesExcelDownload(dto);
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 확정 승인 기준 관리 저장", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @PostMapping("/approval-standards")
    public SaveResponse saveConfirmApprovalBases(
        @RequestBody
        @NotEmpty
        List<SaveConfirmApprovalBaseReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveConfirmApprovalBases(dtos)).build();
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 승인 요청 리스트 삭제", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @DeleteMapping("/approval-standards")
    public SaveResponse removeConfirmApprovalBases(
        @RequestBody
        @NotEmpty
        List<RemoveConfirmApprovalBaseReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeConfirmApprovalBases(dtos)).build();
    }

    @ApiOperation(value = "재약정/멤버십 대상자 조회", notes = "관리중인 고객 중 재약정 가입 대상 고객 목록 조회")
    @ApiImplicitParams(value = {
    })
    @GetMapping("/renewal-membership-customers/paging")
    public PagingResult<SearchRnwMshCstRes> getRenewalMembershipCustomerPages(
        SearchRnwMshCstReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRenewalMembershipCustomerPages(dto, pageInfo);
    }

    @ApiOperation(value = "지국장 소속 파트너 조회", notes = "지국장 소속 파트너 목록을 조회한다.")
    @ApiImplicitParams(value = {
    })
    @GetMapping("/district-manager-partners/paging")
    public PagingResult<SearchMngerPrtnrRes> getDistrictManagerPartnerPages(
        SearchMngerPrtnrReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getDistrictManagerPartnerPages(dto, pageInfo);
    }

    @ApiOperation(value = "1+1 대상 계약 조회", notes = "1+1 대상 계약 목록을 조회한다.")
    @ApiImplicitParams(value = {
    })
    @GetMapping("/oneplusone-contracts")
    public List<SearchOnepluseoneRes> getOneplusoneContracts(
        SearchOnepluseoneReq dto
    ) {
        return service.getOneplusoneContracts(dto);
    }

    @ApiOperation(value = "1+1 연계 가능여부 검사", notes = "선택한 계약이 1+1 연계 가능한지 검사한다.")
    @ApiImplicitParams(value = {
    })
    @GetMapping("/oneplusone-contracts/check")
    public boolean isAvailableOneplusone(
        SearchOnepluseoneReq dto
    ) {
        // TODO W-SS-S-0022 서비스 개발완료되면 호출
        return true;
    }

    @ApiOperation(value = "확정 멤버십 현황 조회", notes = "입력받은 조건에 따라 멤버십 확정 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpStrtDt", value = "접수기간 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "rcpEndDt", value = "접수기간 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdHclsfId", value = "대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "중분류", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "cnfmStrtDt", value = "확정기간 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "cnfmEndDt", value = "확정기간 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "frisuMshCrtYn", value = "자동생성제외", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너코드", paramType = "query"),
    })
    @GetMapping("/memberships")
    public List<SearchConfirmMshRes> getConfirmMemberships(
        SearchConfirmMshReq dto
    ) {
        return service.getConfirmMemberships(dto);
    }

    @ApiOperation(value = "wells K멤버스 취소 요청 목록 조회 - K멤버스 취소 요청 목록", notes = "k멤버스몰 내 구입제품에 대한 취소현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrChRcpStrtDtm", value = "취소시작일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrChRcpFinsDtm", value = "취소종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "statCd", value = "상태", paramType = "query"),
    })
    @GetMapping("/kmembers-cancel-ask")
    public List<SearchKMembersCancelAsksRes> getKMembersCancelAsks(
        SearchKMembersCancelAsksReq dto
    ) {
        return service.getKMembersCancelAsks(dto);
    }

    @ApiOperation(value = "통합계약정보 조회", notes = "저장된 통합계약 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query"),
        @ApiImplicitParam(name = "step", value = "step", paramType = "query", required = true),
    })
    @GetMapping("/cntr-info")
    public WctaContractRegDvo selectContractInfo(
        SearchStep1Req dto
    ) {
        return switch (dto.step()) {
            case 1 -> step1Service.selectStepInfo(dto);
            case 2 -> step2Service.selectStepInfo(dto.cntrNo());
            case 3 -> step3Service.selectStepInfo(dto.cntrNo());
            case 4 -> step4Service.selectStepInfo(dto.cntrNo());
            default -> throw new BizException("MSG_ALT_ERR_CONTACT_ADMIN");
        };
    }

    @ApiOperation(value = "팝업 출력 전 고객 조회", notes = "고객이 없는 경우 신규등록 안내를 위해 팝업 출력 전 조회한다.(getUnfcCustInfo)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrTpCd", value = "계약유형", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "법인격구분", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "이름", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대전화번호", paramType = "query"),
    })
    @GetMapping("/is-exist-cntrt-info")
    public boolean isExistCntrtInfo(
        @Valid
        WctaContractDto.SearchCntrtInfoReq dto
    ) {
        return step1Service.isExistCntrtInfo(dto);
    }

    @ApiOperation(value = "임직원번호로 고객번호 조회", notes = "파트너번호로 고객번호 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
    })
    @GetMapping("/ensm-cst-no")
    public String selectEnsmCstNo(
        @RequestParam
        String prtnrNo
    ) {
        return regService.selectEnsmCstNo(prtnrNo);
    }

    @ApiOperation(value = "Step1 저장", notes = "Step1 정보를 신규 등록하거나 기존 정보를 수정한다.")
    @PostMapping("save-cntr-step1")
    public SaveResponse saveContractStep1(
        @RequestBody
        @Valid
        WctaContractRegStep1Dvo dvo
    ) {
        return SaveResponse.builder().key(step1Service.saveContractStep1(dvo)).build();
    }

    @ApiOperation(value = "상품 조회", notes = "학습자에 적용 가능한 상품을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/reg-products")
    public WctaContractRegStep2Dvo selectProducts(
        @RequestParam
        String cntrNo,
        @RequestParam(required = false)
        String pdFilter
    ) {
        return step2Service.selectProducts(cntrNo, pdFilter);
    }

    @ApiOperation(value = "상품 금액 조회", notes = "선택한 상품의 금액을 조회한다.")
    @GetMapping("/product-prices")
    public WctaContractRegStep2Dvo.PdAmtDvo selectProductPrices(
        SearchPdAmtReq dto
    ) {
        return step2Service.selectProductPrices(dto);
    }

    @ApiOperation(value = "상품 속성 조회", notes = "선택한 상품의 속성목록을 조회한다.")
    @GetMapping("/product-selects")
    public WctaContractDtlDvo selectProductSelects(
        SearchPdSelReq dto
    ) {
        return step2Service.selectProductSelects(dto);
    }

    @ApiOperation(value = "웰스팜/홈카페 조회", notes = "선택한 상품의 웰스팜/홈카페 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
    })
    @GetMapping("/welsf-hcf-pkgs")
    public List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> selectWlsfHcf(
        @RequestParam
        String pdCd
    ) {
        return step2Service.selectWelsfHcfPkgs(pdCd);
    }

    @ApiOperation(value = "Step2 저장", notes = "Step2 정보를 신규 등록하거나 기존 정보를 수정한다.")
    @PostMapping("save-cntr-step2")
    public SaveResponse saveContractStep2(
        @RequestBody
        @Valid
        WctaContractRegStep2Dvo dvo
    ) {
        return SaveResponse.builder().key(step2Service.saveContractStep2(dvo)).build();
    }

    @ApiOperation(value = "Step3 저장", notes = "Step3 정보를 신규 등록하거나 기존 정보를 수정한다.")
    @PostMapping("save-cntr-step3")
    public SaveResponse saveContractStep3(
        @RequestBody
        @Valid
        WctaContractRegStep3Dvo dvo
    ) {
        return SaveResponse.builder().key(step3Service.saveContractStep3(dvo)).build();
    }

    @ApiOperation(value = "Step4 저장", notes = "Step4 정보를 임시저장한다.")
    @PostMapping("save-cntr-step4-temp")
    public SaveResponse saveContractStep4Temp(
        @RequestBody
        @Valid
        WctaContractRegStep4Dvo dvo
    ) {
        return SaveResponse.builder().key(step4Service.saveContractStep4Temp(dvo)).build();
    }

    @ApiOperation(value = "Step4 저장", notes = "Step4 정보를 신규 등록하거나 기존 정보를 수정한다.")
    @PostMapping("save-cntr-step4")
    public SaveResponse saveContractStep4(
        @RequestBody
        @Valid
        WctaContractRegStep4Dvo dvo
    ) {
        return SaveResponse.builder().key(step4Service.saveContractStep4(dvo)).build();
    }

    @ApiOperation(value = "통합계약 요약 조회", notes = "통합계약 요약 정보를 조회한다.")
    @GetMapping("/summaries")
    public WctaContractRegDvo getCntrSmrs(
        @RequestParam
        String cntrNo
    ) {
        return regService.selectCntrSmr(cntrNo);
    }

}
