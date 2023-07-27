package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCreateDvo;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractProductDvo;

@Mapper
public interface WctiContractCreateMapper {

    String selectExistCustomerYn(String cntrCstNo);

    String selectCustomerAggrementKey();

    int insertCustomerAgreement(String cstAgId, String agDrmRefkVal, String cstAgChnlDvCd);

    int insertCustomerAgreementDetail(
        String cstAgId, String agAtcDvCd, String pdCd,
        String agStatCd, String agStrtdt
    );

    int insertContractBase(WctiContractCreateDvo contract);

    int insertContractDetail(
        @Param("contract")
        WctiContractCreateDvo contract,
        @Param("product")
        WctiContractProductDvo product
    );

    Optional<WctiContractProductDvo> selectContractProduct(
        String basePdCd,
        String sellInflwChnlDtlCd
    );

    int insertContractPrice(
        @Param("contract")
        WctiContractCreateDvo contract,
        @Param("product")
        WctiContractProductDvo product
    );

    int insertContractCustomerRelation(
        @Param("contract")
        WctiContractCreateDvo contract,
        String cstNo
    );

    int insertContractAddressRelation(WctiContractCreateDvo contract, String adrpcTpCd);

    int insertContractAddressForContract(WctiContractCreateDvo contract);

    int insertContractAddressForInstall(WctiContractCreateDvo contract);

    int insertContractSettlement(WctiContractCreateDvo contract);

    int insertContractSettlementRelation(WctiContractCreateDvo contract);

    int insertContractProductRelation(WctiContractCreateDvo contract);

    int insertContractPartnerRelation(WctiContractCreateDvo contract);

    int insertContractWellsDetail(WctiContractCreateDvo contract);

    int insertTaxInvoiceReceipt(WctiContractCreateDvo contract);

    int insertMachineChange(WctiContractCreateDvo contract);
}
