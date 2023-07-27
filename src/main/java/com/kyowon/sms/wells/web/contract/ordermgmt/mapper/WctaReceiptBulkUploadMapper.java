package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WctaReceiptBulkUploadMapper {
    boolean isExistProduct(String pdCd);

    boolean isExistServiceProduct(String pdCd);

    Optional<WctzPdBasDvo> selectPdBasByPk(String pdCd);

    String selectPspcCstIdForNewPspcCstBas();

    Optional<WctzPspcCstBasDvo> selectPspcCstBasByPk(String pspcCstId);

    int insertPspcCstBas(WctzPspcCstBasDvo dvo);

    String selectPspcCstCnslIdForNewPspcCstCnslBas();

    int insertPspcCstCnslBas(WctzPspcCstCnslBasDvo dvo);

    int insertPspcCstCnslRcmdIz(WctzPspcCstCnslRcmdIzDvo dvo);

    boolean isExistCstBas(WctzCstBasDvo basDvo);

    Optional<WctzCstBasDvo> selectCstBasWithInfos(WctzCstBasDvo basDvo);

    int insertProspectCustomers(List<WctaBulkProspectCustomerDvo> dvos);

    List<WctaRentalFinalPriceDvo> selectRentalPdPrcFnlDtl(WctzPdPrcFnlDtlDvo req);

    Optional<WctzMmPrtnrIzDvo> selectAlncmpDgPrtnr(String alncmpDgPrtnrMapngCd, String alncmpDgPrtnrOgTpCd);

    String selectCntrPrtnrRelIdForNewCntrPrtnrRel();

    String selectCntrCstRelIdForNewCntrCstRel();

    String selectCntrPdRelIdForNewCntrPdRel();

    String selectCntrPrcCmptIdForNewCntrPrcCmptIz();

    int insertBulkRentals(List<WctaBulkContractDvo> dvos);

    List<WctzPdRelDvo> selectPdRels(String basePdCd);

    List<WctzPdRelDvo> selectPdRels(String basePdCd, String pdRelTpCd);

    List<WctaSpayFinalPriceDvo> selectSpayPdPrcFnlDtl(WctzPdPrcFnlDtlDvo fnlDtlSearchParams);

    int insertBulkSpays(List<WctaBulkContractDvo> dvos);

    int insertBulkSpay(WctaBulkContractDvo dvo);

    String selectNewCntrNo();

    Optional<WctaIstlcValidationDvo> selectIstlcValidation(String cntrNo, int cntrSn);

    String selectNewCntrAdrpcId();

    String selectNewCntrAdrRelId();

    int insertBulkAdprcBases(List<WctzCntrAdprcBasDvo> dvos);

    int insertBulkCntrAdrRels(List<WctzCntrAdrRelDvo> dvos);

    int updateBulkExpireCntrAdrRels(List<String> origCntrAdrRelIds, String now);
}
