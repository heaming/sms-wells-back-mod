package com.kyowon.sms.wells.web.contract.common.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.common.dvo.*;

@Mapper
public interface WctzHistoryMapper {

    WctzCntrDetailChangeHistDvo selectCntrDetailForHist(String cntrNo, int cntrSn);

    WctzCntrDetailChangeHistDvo selectCntrDetailChangeHist(String cntrNo, int cntrSn);

    int insertCntrDetailChangeHist(WctzCntrDetailChangeHistDvo dvo);

    int updateCntrDetailChangeHist(WctzCntrDetailChangeHistDvo dvo);

    int deleteCntrDetailChangeHist(WctzCntrDetailChangeHistDvo dvo);

    WctzCntrDtlStatChangeHistDvo selectCntrDetailStatChangeHist(String cntrNo, int cntrSn);

    int insertCntrDetailStatChangeHist(WctzCntrDtlStatChangeHistDvo dvo);

    int updateCntrDetailStatChangeHist(WctzCntrDtlStatChangeHistDvo dvo);

    int deleteCntrDetailStatChangeHist(WctzCntrDtlStatChangeHistDvo dvo);

    WctzTxinvRcpBaseChangeHistDvo selectTaxInvoiceReceiptBase(String cntrNo, int cntrSn);

    int insertTaxInvoiceReceiptBaseHist(WctzTxinvRcpBaseChangeHistDvo dvo);

    WctzCntrChRcchStatChangeHistDvo selectCntrChRcchStatChangeHist(String cntrChRcpId);

    int insertCntrChRcchStatChangeHist(WctzCntrChRcchStatChangeHistDvo dvo);

    int updateCntrChRcchStatChangeHist(WctzCntrChRcchStatChangeHistDvo dvo);

    int deleteCntrChRcchStatChangeHist(WctzCntrChRcchStatChangeHistDvo dvo);

    WctzCntrBasicChangeHistDvo selectCntrBasicForHist(String cntrNo);

    WctzCntrBasicChangeHistDvo selectCntrBasicChangeHistory(String cntrNo);

    int insertCntrBasicChangeHist(WctzCntrBasicChangeHistDvo dvo);

    int updateCntrBasicChangeHist(WctzCntrBasicChangeHistDvo dvo);

    int deleteCntrBasicChangeHist(WctzCntrBasicChangeHistDvo dvo);

    WctzContractWellsDetailHistDvo selectContractWellsDetailHist(String cntrNo, int cntrSn);

    WctzContractWellsDetailHistDvo selectContractWellsDetailForHist(String cntrNo, int cntrSn);

    int updateContractWellsDetailHist(WctzContractWellsDetailHistDvo dvo);

    int insertContractWellsDetailHist(WctzContractWellsDetailHistDvo dvo);

    WctzAcmpalContractIzHistDvo selectAcmpalContractIzForHist(String acmpalCntrId);

    WctzAcmpalContractIzHistDvo selectAcmpalContractIzHist(String acmpalCntrId);

    int updateAcmpalContractIzChangeHist(WctzAcmpalContractIzHistDvo dvo);

    int insertAcmpalContractIzChangeHist(WctzAcmpalContractIzHistDvo dvo);

    // 계약가격산출변경이력
    WctzCntrPrccchHistDvo selectCntrPrccchHistory(String cntrNo, int cntrSn);

    WctzCntrPrccchHistDvo selectCntrPrcCmptForHist(String cntrNo, int cntrSn);

    int updateCntrPrccchHistory(WctzCntrPrccchHistDvo dvo);

    int insertCntrPrccchHistory(WctzCntrPrccchHistDvo dvo);

    // 기기변경이력
    WctzMachineChangeHistoryDvo selectMachineChangeHistory(String cntrNo, int cntrSn);

    WctzMachineChangeHistoryDvo selectMachineChangeForHist(String cntrNo, int cntrSn);

    int updateMachineChangeHistory(WctzMachineChangeHistoryDvo dvo);

    int insertMachineChangeHistory(WctzMachineChangeHistoryDvo dvo);

    // 계약변경접수상세변경이력
    WctzContractChRcchStatChangeDtlHistDvo selectContractChRcchStatChangeDtlHistory(String cntrChRcpId, int cntrChSn);

    WctzContractChRcchStatChangeDtlHistDvo selectContractChRcchStatChangeDtlForHist(String cntrChRcpId, int cntrChSn);

    int updateContractChRcchStatChangeDtlHistory(WctzContractChRcchStatChangeDtlHistDvo dvo);

    int insertContractChRcchStatChangeDtlHistory(WctzContractChRcchStatChangeDtlHistDvo dvo);

    // 계약주소변경이력
    WctzContractAddrChangeHistDvo selectContractAddrChangeHistory(String cntrAdrpcId);

    WctzContractAddrChangeHistDvo selectContractAddrChangeForHist(String cntrAdrpcId);

    int updateContractAddrChangeHistory(WctzContractAddrChangeHistDvo dvo);

    int insertContractAddrChangeHistory(WctzContractAddrChangeHistDvo dvo);

    Optional<WctzPspcCstChHistDvo> selectLastPspcCstChHist(String pspcCstId);

    int expirePspcCstChHistory(String pspcCstId, String histStrtDtm);

    int upsertPspcCstChHist(String pspcCstId, String histStrtDtm);

    Optional<WctzPspcCstCnslChHistDvo> selectLastPspcCstCnslChHist(String pspcCstCnslId);

    int expirePspcCstCnslChHistory(String pspcCstCnslId, String histStrtDtm);

    int upsertPspcCstCnslChHist(String pspcCstCnslId, String histStrtDtm);

    Optional<WctzPspcCstCnslRchHistDvo> selectLastPspcCstCnslRchHist(String pspcCstCnslId, int pspcCstCnslSn);

    int expirePspcCstCnslRchHistory(String pspcCstCnslId, int pspcCstCnslSn, String histStrtDtm);

    int upsertPspcCstCnslRchHist(String pspcCstCnslId, int pspcCstCnslSn, String histStrtDtm);

    int insertContractNotifyFowrdindHist(@Param("item")
    WctzContractNotifyFowrdindHistDvo dvo);

    int updateContractNotifyFowrdindHist(WctzContractNotifyFowrdindHistDvo dvo);
}
