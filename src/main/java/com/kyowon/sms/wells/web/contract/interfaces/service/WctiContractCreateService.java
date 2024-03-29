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
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractProductDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractCreateMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
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

    public CreateSinglePaymentRes createContractForSinglePayment(CreateSinglePaymentReq dto) throws Exception {
        WctiContractCreateDvo contract = converter.mapCreateSinglePaymentReqToWctiContractCreateDvo(
            dto
        );

        WctiContractProductDvo product = mapper.selectContractProduct(
            contract.getBasePdCd(), contract.getSellInflwChnlDtlCd()
        ).orElseThrow(() -> new BizException(String.format("등록된 상품코드(=%s)가 아닙니다.", contract.getBasePdCd())));

        // 1. validation
        checkValidation(contract);

        // 2. 계약초기데이터 세팅
        setContractDefaultData(contract);

        // 3. 고객정보동의생성
        createCustomerAggrement(contract);

        // 4. 계약생성
        createContract(contract, product);

        return new CreateSinglePaymentRes("S", "계약생성에 성공했습니다.", "");
    }

    public CreateRentalRes createContractForRental(CreateRentalReq dto) throws Exception {
        WctiContractCreateDvo contract = converter.mapCreateRentalReqToWctiContractCreateDvo(dto);

        WctiContractProductDvo product = mapper.selectContractProduct(
            contract.getBasePdCd(), contract.getSellInflwChnlDtlCd()
        ).orElseThrow(() -> new BizException(String.format("등록된 상품코드(=%s)가 아닙니다.", contract.getBasePdCd())));

        // 1. validation
        checkValidation(contract);

        // 2. 계약초기데이터 세팅
        setContractDefaultData(contract);

        // 3. 고객정보동의생성
        createCustomerAggrement(contract);

        // 4. 계약생성
        createContract(contract, product);

        return new CreateRentalRes("", "S", "계약생성에 성공했습니다.", "");
    }

    private void checkValidation(WctiContractCreateDvo contract) {
        // 1. 고객번호 유효성 검사 체크
        String existYn = mapper.selectExistCustomerYn(contract.getCntrCstNo());
        BizAssert.isTrue(
            "Y".equals(existYn),
            String.format("등록되지 않은 계약고객번호(%s)입니다.", contract.getCntrCstNo())
        );

        // 2. 계약번호 중복 체크
        existYn = mapper.selectExistContractNumberYn(contract.getCntrNo());
        BizAssert.isTrue("N".equals(existYn), String.format("기 등록된 계약번호(%s) 입니다.", contract.getCntrNo()));
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

        if (StringUtils.isEmpty(contract.getSellOgTpCd())
            && StringUtils.isNotEmpty(contract.getSellPrtnrNo())) {
            String ogTpCd = mapper.selectPartnerOgTpCd(contract.getSellPrtnrNo())
                .orElseThrow(() -> new BizException("MSG_ALT_CRSP_PRTNR_NO_INF_NEX"));
            contract.setSellOgTpCd(ogTpCd);
        }
    }

    private void createCustomerAggrement(WctiContractCreateDvo contract) {
        String cstAgId = mapper.selectCustomerAggrementKey();

        // 판매채널 : kmembers(2010), 교원웰스(2050)
        // 동의채널 : kmembers(04), 홈페이지(02)
        String cstAgChnlDvCd = StringUtil.decode(contract.getSellInflwChnlDtlCd(), "2010", "04", "2050", "02");

        mapper.insertCustomerAgreement(cstAgId, contract.getCntrCstNo(), cstAgChnlDvCd);

        String pdCd = contract.getBasePdCd();
        String nowDay = DateUtil.getNowDayString();

        // (계약) 개인정보 수집 및 이용 동의
        String agStatCd = "Y".equals(contract.getPifClcnUAgYn()) ? "01" : "02";
        mapper.insertCustomerAgreementDetail(cstAgId, "111", pdCd, agStatCd, nowDay);

        // (계약) 개인정보 제3자 제공 동의
        agStatCd = "Y".equals(contract.getPifThpOfrAgYn()) ? "01" : "02";
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

    private void createContract(WctiContractCreateDvo contract, WctiContractProductDvo product) throws Exception {
        // 계약기본
        mapper.insertContractBase(contract);
        // 계약기본이력
        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(contract.getCntrNo())
                .build()
        );

        // 계약상세
        mapper.insertContractDetail(contract, product);
        // 계약상세이력
        historyService.createContractDetailChangeHistory(
            WctzCntrDetailChangeHistDvo.builder()
                .cntrNo(contract.getCntrNo())
                .cntrSn(Integer.parseInt(contract.getCntrSn()))
                .build()
        );

        // 계약가격산출내역(TB_SSCT_CNTR_PRC_CMPT_IZ)
        mapper.insertContractPrice(contract, product);
        // 계약가격산출변경이력
        historyService.createCntrPrccchHistory(
            WctzCntrPrccchHistDvo.builder()
                .cntrNo(contract.getCntrNo())
                .cntrSn(Integer.parseInt(contract.getCntrSn()))
                .build()
        );

        // 계약고객관계(계약자)
        mapper.insertContractCustomerRelation(contract, contract.getCntrCstNo());

        // 계약/설치 주소 저장
        if (isValidAddress(contract.getCntrtBasAdr(), contract.getCntrtDtlAdr())) {
            String adrId = getAdrId(contract.getCntrtBasAdr(), contract.getCntrtDtlAdr(), contract.getCntrtAdrDvCd());
            contract.setCntrtAdrId(adrId);

            String cntrtMexnoEncr = DbEncUtil.enc(contract.getCntrtMexno());
            String cntrtExnoEncr = DbEncUtil.enc(contract.getCntrtExno());

            contract.setCntrtMexnoEncr(cntrtMexnoEncr);
            contract.setCntrtExnoEncr(cntrtExnoEncr);
            contract.setAdrpcTpCd("1");
            contract.setCntrAdrpcId(mapper.selectCntrAdrpcId());

            mapper.insertContractAddressForContract(contract);
            mapper.insertContractAddressRelation(contract);
        }

        if (isValidAddress(contract.getIstBasAdr(), contract.getIstDtlAdr())) {
            contract.setIstAdrId(getAdrId(contract.getIstBasAdr(), contract.getIstDtlAdr(), contract.getIstAdrDvCd()));

            String istMexnoEncr = DbEncUtil.enc(contract.getIstMexno());
            String istExnoEncr = DbEncUtil.enc(contract.getIstExno());

            contract.setIstMexnoEncr(istMexnoEncr);
            contract.setIstExnoEncr(istExnoEncr);
            contract.setAdrpcTpCd("3");
            contract.setCntrAdrpcId(mapper.selectCntrAdrpcId());

            mapper.insertContractAddressForInstall(contract);
            mapper.insertContractAddressRelation(contract);
        }

        // 결제정보 저장
        if (StringUtils.isNotEmpty(contract.getStlmAmt())) {
            if (StringUtils.isNotEmpty(contract.getCrcdNo())) {
                String crcdNoEncr = DbEncUtil.enc(contract.getCrcdNo());
                contract.setCrcdNoEncr(crcdNoEncr);
            }

            if (StringUtils.isNotEmpty(contract.getAcno())) {
                String acnoEncr = DbEncUtil.enc(contract.getAcno());
                contract.setAcnoEncr(acnoEncr);
            }

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

        // 세금계산서발행요청
        if ("Y".equals(contract.getTxinvPblOjYn())) {
            contract.setTxinvPdDvCd(StringUtil.decode(product.getSellTpCd(), "1", "21", "2", "23", "3", "25"));
            mapper.insertTaxInvoiceReceipt(contract);
        }

        // 기기변경
        if ("Y".equals(contract.getMchnChYn())) {
            mapper.insertMachineChange(contract);
        }
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

}
