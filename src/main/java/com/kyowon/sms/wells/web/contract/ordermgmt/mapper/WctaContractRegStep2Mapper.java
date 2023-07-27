package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SearchPdSelReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaContractRegStep2Mapper {

    List<WctaContractRegStep2Dvo.PdClsfDvo> selectPdClsfs();

    List<WctaContractRegStep2Dvo.PdDvo> selectProducts(String sellInflwChnlDtlCd, String pdFilter);

    List<WctaContractRegStep2Dvo.PdDvo> selectProducts(
        String sellInflwChnlDtlCd, String pdFilter, List<String> mshPdCds, String ojCntrSellTpDtlCd
    );

    List<WctaContractDtlDvo> selectContractDtlWithPdInfo(String cntrNo);

    String selectProductRelId(String basePdCd, String svPdCd);

    int insertCntrDtlStep2(WctaContractDtlDvo dvo);

    int insertCntrPdRelStep2(@Param("item")
    WctaContractPdRelDvo dvo);

    int insertMchnChIzStep2(WctaMachineChangeIzDvo dvo);

    int insertCntrPrcCmptIzStep2(@Param("item")
    WctaContractPrcCmptIzDvo dvo);

    int insertCntrRelStep2(@Param("item")
    WctaContractRelDvo dvo);

    int insertCntrWellsDtlStep2(WctaContractWellsDtlDvo dvo);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductDstps(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductDsdvsSpay(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductDsdvsRntl(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductDsrtsSpay(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductDsrtsRntl(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductStplPtrms(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductCntrPtrms(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductFrisuMshPtrms(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductRgstCss(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductAlncPds(SearchPdSelReq dto);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductServiceInfo(String pdCd);

    List<WctaContractRegStep2Dvo.PdAmtDvo> selectProductPrices(WctaContractDto.SearchPdAmtReq dto);

    List<WctaContractPdRelDvo> selectPdSvcsInBasePd(String pdCd);

    List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> selectWelsfHcfPkgs(String pdCd);

    List<WctaContractRegStep2Dvo.PdSdingCapsl> selectSdingCapsls(String pdCd);

    List<WctaContractRegStep2Dvo.PdSdingCapsl> selectSdingCapslInfos(
        String cntrNo, int cntrSn, String pdCd, List<String> parts
    );

    String selectPdNm(String cntrNo, Integer cntrSn);

    int selectRntlMcn(String cntrNo, Integer cntrSn);

    boolean isExistAlncPds(WctaContractDto.SearchPdAmtReq dto);

    int deleteCntrDtlStep2(String cntrNo);

    int deleteContractDetailHistory(String cntrNo);

    int deleteCntrHsmtrlDtlStep2(String cntrNo);

    int deleteCntrHsmtrDchHistory(String cntrNo);

    int deleteCntrPdRelStep2(String cntrNo);

    int deleteMchnChIzStep2(String cntrNo);

    int deleteCntrPmotIzStep2(String cntrNo);

    int deleteFgptRcpIzStep2(String cntrNo);

    int deleteCntrPrcCmptIzStep2(String cntrNo);

    int deleteCntrPrccchHistory(String cntrNo);

    int deleteCntrWellsDtlStep2(String cntrNo);

    int deleteCntrRelStep2(String cntrNo);
}
