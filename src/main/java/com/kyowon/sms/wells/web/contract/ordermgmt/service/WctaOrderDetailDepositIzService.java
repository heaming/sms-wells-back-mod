package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositIzDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailDepositIzMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailDepositIzService {

    private final WctaOrderDetailDepositIzMapper mapper;

    public SearchRes getOrderDetailDepositIz(
        String cntrNo, String cntrSn, String sellTpCd, String cntrCstNo
    ) {
        List<SearchSpayCntrtDepositIzRes> searchSpayCntrtDepositIzResList = null;
        List<SearchRentalDepositIzRes> searchRentalDepositIzResList = null;
        List<SearchMembershipDepositIzRes> searchMembershipDepositIzResList = null;
        List<SearchRegularShippingsDepositIzRes> searchRegularShippingsDepositIzResList = null;
        List<SearchLendingLimitDepositIzRes> searchLendingLimitDepositIzResList = null;

        if (sellTpCd.equals("1")) { // 일시불(여신한도)
            searchSpayCntrtDepositIzResList = mapper
                .selectOrderDetailSpayCntrtDepositIz(cntrNo, cntrSn);
            searchLendingLimitDepositIzResList = mapper
                .selectOrderLendingLimitDepositIz(cntrCstNo);
        } else if (sellTpCd.equals("2")) { // 렌탈
            searchRentalDepositIzResList = mapper
                .selectOrderDetailRentalDepositIz(cntrNo, cntrSn);
        } else if (sellTpCd.equals("3")) { // 멤버쉽
            searchMembershipDepositIzResList = mapper
                .selectOrderDetailMembershipDepositIz(cntrNo, cntrSn);
        } else if (sellTpCd.equals("6")) { // 정기배송
            searchRegularShippingsDepositIzResList = mapper
                .selectOrderDetailRegularShippingsDepositIz(cntrNo, cntrSn);
        }
        return new SearchRes(
            searchRentalDepositIzResList, searchMembershipDepositIzResList, searchSpayCntrtDepositIzResList,
            searchRegularShippingsDepositIzResList, searchLendingLimitDepositIzResList
        );
    }
}
