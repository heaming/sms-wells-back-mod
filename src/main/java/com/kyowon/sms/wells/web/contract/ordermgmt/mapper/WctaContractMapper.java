package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractMapper {
    PagingResult<SearchCntrNoRes> selectContractNumberInqrPages(
        SearchCntrNoReq dto,
        PageInfo pageInfo
    );

    List<SearchHomecareContractsRes> selectHomecareContracts(List<SearchHomecareContractsReq> dtos);

    int updateHomecareContractsDuedt(SaveHomecareContractsReq dto);

    int updateHomecareContractsCandt(SaveHomecareContractsReq dto);

    List<SearchRes> selectApprovalAskDivides(String standardDt);

    List<SearchConfirmAprPsicAksRes> selectConfirmAprPsicAks(String cntrNo);

    List<SearchConfirmAprPsicPrchssRes> selectConfirmAprPsicPrchss(String cntrNo);

    List<SearchConfirmApprovalBaseRes> selectConfirmApprovalBases(
        SearchConfirmApprovalBaseReq dto
    );

    PagingResult<SearchConfirmApprovalBaseRes> selectConfirmApprovalBases(
        SearchConfirmApprovalBaseReq dto, PageInfo pageInfo
    );

    int selectCountConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    WctaPdPrcFnlDtlDvo selectRentalFee(SearchRentalFeeReq dto);

    int deleteApprovalAskDivides(WctaCntrAprAkDvCdDvo dvo);

    int insertConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    int updateConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    int deleteConfirmApprovalBases(WctaCntrAprBaseBasDvo dvo);

    List<SearchSpaySlamtInqrRes> selectSpaySlamtInqr(WctaSpaySlamtInqrDvo dvo);

    List<SearchHomeCareMshChecksRes> selectHomeCareMshChecks(String cntrCstNo);

    int insertRentalPackageGrpMngts(WctaRentalPackageGrpMngtsDvo dvo);

    int deleteRentalPackageGrpMngts(WctaRentalPackageGrpMngtsDvo dvo);

    List<SearchRentalPackageGrpMngtsRes> selectRentalPackageGrpMngts(WctaRentalPackageGrpMngtsDvo dvo);

    PagingResult<SearchRnwMshCstRes> selectRenewalMembershipCustomerPages(SearchRnwMshCstReq dto, PageInfo pageInfo);

    PagingResult<SearchMngerPrtnrRes> selectDistrictManagerPartnerPages(SearchMngerPrtnrReq dto, PageInfo pageInfo);

    List<SearchOnepluseoneRes> selectOneplusoneContracts(SearchOnepluseoneReq dto);

    List<SearchConfirmMshRes> selectConfirmMemberships(SearchConfirmMshReq dto);

    List<SearchKMembersCancelAsksRes> selectKMembersCancelAsks(
        SearchKMembersCancelAsksReq dto
    );
}
