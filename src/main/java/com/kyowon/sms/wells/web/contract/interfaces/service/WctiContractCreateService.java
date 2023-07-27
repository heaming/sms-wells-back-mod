package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.common.dvo.FormatAddressDvo;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sflex.common.zcommon.constants.CmSujiewonConst;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrPrccchHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractCreateConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateKmembersReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateKmembersRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractProductDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractCreateMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractCreateService {

    private final WctiContractCreateMapper mapper;

    private final WctiContractCreateConverter converter;

    private final WctzHistoryService historyService;

    private final SujiewonService sujiewonService;

    public CreateKmembersRes createContractForKmembers(CreateKmembersReq dto) throws Exception {
        WctiContractCreateDvo contract = converter.mapCreateKmembersReqToWctiContractCreateDvo(
            dto
        );

        // 1. validation
        checkCustomerNumberValidation(contract);

        // 2. 계약초기데이터 세팅
        setContractDefaultData(contract);
        contract.setSellInflwChnlDtlCd("2010"); // KMEMBERS

        // 3. 고객정보동의생성
        createCustomerAggrement(contract, "04");

        // 4. 계약생성
        createContract(contract);

        return new CreateKmembersRes("S", "계약생성에 성공했습니다.", "");
    }

    private void checkCustomerNumberValidation(WctiContractCreateDvo contract) {
        String existYn = mapper.selectExistCustomerYn(contract.getCntrCstNo());
        BizAssert.isTrue(
            "Y".equals(existYn),
            String.format("등록되지 않은 계약고객번호(%s)입니다.", contract.getCntrCstNo())
        );
    }

    private void setContractDefaultData(WctiContractCreateDvo contract) {
        contract.setCntrNatCd("KR");
        contract.setCntrPrgsStatCd("60");
        contract.setPrrRcpCntrYn("N");
        contract.setCntrDtlStatCd("101"); // 계약상태(101:정상)
        contract.setCstStlmInMthCd("10"); // 10	대면결제
        contract.setCrncyDvCd("KRW");

        // TODO: 값 설정 방법 확인
        contract.setCntrwTpCd("3");
        contract.setCntrTpCd("01");

        contract.setRveDvCd("01"); // 계약(청약)금(01)
    }

    private void createCustomerAggrement(WctiContractCreateDvo contract, String cstAgChnlDvCd) {
        String cstAgId = mapper.selectCustomerAggrementKey();

        mapper.insertCustomerAgreement(cstAgId, contract.getCntrCstNo(), cstAgChnlDvCd);

        String pdCd = contract.getBasePdCd();
        String nowDay = DateUtil.getNowDayString();

        // (계약) 개인정보3자제공동의
        String agStatCd = "Y".equals(contract.getPifThpOfrAgYn()) ? "01" : "02";
        mapper.insertCustomerAgreementDetail(cstAgId, "111", pdCd, agStatCd, nowDay);

        // (계약) 개인정보 제3자 제공 동의
        agStatCd = "Y".equals(contract.getThpAgYn()) ? "01" : "02";
        mapper.insertCustomerAgreementDetail(cstAgId, "112", pdCd, agStatCd, nowDay);

        // (계약) 마케팅 목적 수집 이용 및 광고성 정보 수신 동의
        agStatCd = "Y".equals(contract.getMktgPurpAgYn()) ? "01" : "02";
        mapper.insertCustomerAgreementDetail(cstAgId, "113", pdCd, agStatCd, nowDay);

        // (계약) 개인(신용)정보 수집, 제공, 조회동의
        agStatCd = "Y".equals(contract.getPifCinfThpOfrAgYn()) ? "01" : "02";
        mapper.insertCustomerAgreementDetail(cstAgId, "114", pdCd, agStatCd, nowDay);

        // (계약) 개인정보업무위탁동의
        agStatCd = "Y".equals(contract.getPifBizFstrAgYn()) ? "01" : "02";
        mapper.insertCustomerAgreementDetail(cstAgId, "115", pdCd, agStatCd, nowDay);
    }

    private void createContract(WctiContractCreateDvo contract) throws Exception {
        // 계약기본
        mapper.insertContractBase(contract);
        // 계약기본이력
        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(contract.getCntrNo())
                .build()
        );

        // 계약상세
        WctiContractProductDvo contractProduct = mapper.selectContractProduct(
            contract.getBasePdCd(), contract.getSellInflwChnlDtlCd()
        ).orElseThrow(() -> new BizException(String.format("등록된 상품코드(=%s)가 아닙니다.", contract.getBasePdCd())));

        mapper.insertContractDetail(contract, contractProduct);
        // 계약상세이력
        historyService.createContractDetailChangeHistory(
            WctzCntrDetailChangeHistDvo.builder()
                .cntrNo(contract.getCntrNo())
                .cntrSn(Integer.parseInt(contract.getCntrSn()))
                .build()
        );

        // 계약가격산출내역(TB_SSCT_CNTR_PRC_CMPT_IZ)
        mapper.insertContractPrice(contract, contractProduct);
        // 계약가격산출변경이력
        historyService.createCntrPrccchHistory(
            WctzCntrPrccchHistDvo.builder()
                .cntrNo(contract.getCntrNo())
                .cntrSn(Integer.parseInt(contract.getCntrSn()))
                .build()
        );

        // 계약고객관계(계약자)
        mapper.insertContractCustomerRelation(contract, contract.getCntrCstNo(), "10");

        // 계약/설치 주소 저장
        if (isValidAddress(contract.getCntrtBasAdr(), contract.getCntrtDtlAdr())) {
            contract.setCntrtAdrId(getAdrId(contract.getCntrtBasAdr(), contract.getCntrtDtlAdr()));
            mapper.insertContractAddressForContract(contract);
        }

        if (isValidAddress(contract.getIstBasAdr(), contract.getIstDtlAdr())) {
            contract.setIstAdrId(getAdrId(contract.getIstBasAdr(), contract.getIstDtlAdr()));
            mapper.insertContractAddressForInstall(contract);
        }

        // 결제정보 저장
        if (StringUtils.isNotEmpty(contract.getStlmAmt())) {
            mapper.insertContractSettlement(contract);
            mapper.insertContractSettlementRelation(contract);
        }

        // 계약-상품관계 저장
        if (StringUtils.isNotEmpty(contract.getBasePdCd())) {
            mapper.insertContractProductRelation(contract);
        }

        // 계약-파트너관계 저장
        if (StringUtils.isNotEmpty(contract.getSellPrtnrNo())) {
            mapper.insertContractPartnerRelation(contract);
        }

        // 웰스계약상세 저장
        mapper.insertContractWellsDetail(contract);
    }

    private boolean isValidAddress(String basAdr, String dtlAdr) {
        return StringUtils.isNotEmpty(basAdr) && StringUtils.isNotEmpty(dtlAdr);
    }

    private String getAdrId(String baseAdr, String dtlAdr) throws Exception {
        // 1. 수지원넷 주소정제
        FormatAddressDvo formatAddress = sujiewonService.getFormattedAddress(
            baseAdr + " " + dtlAdr, CmSujiewonConst.FORMAT_TYPE_ROAD_ADDRESS
        );

        return formatAddress.getAdrId();
    }
}
