package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtInfDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailMngtInfMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailMngtInfService {

    private final WctaOrderDetailMngtInfMapper mapper;

    public List<WctaOrderDetailMngtInfDto.SearchRes> getOrderDetailMngtInqr(
        String cntrNo, String cntrSn, String sellTpCd
    ) {
        if (sellTpCd.equals("2")) { // 렌탈
            return mapper.selectOrderDetailRentalMngtInqr(cntrNo, cntrSn);
        } else if (sellTpCd.equals("3")) { // 멤버쉽
            return mapper.selectOrderDetailMembershipMngtInqr(cntrNo, cntrSn);
        } else if (sellTpCd.equals("1")) { // 일시불
            return mapper.selectOrderDetailSpayCntrtMngtInqr(cntrNo, cntrSn);
        } else if (sellTpCd.equals("6")) { // 정기배송
            return mapper.selectOrderDetailRegularShippingsMngtInqr(cntrNo, cntrSn);
        }
        return null;
    }

    public WctaOrderDetailMngtInfDto.SearchPmotFgptRes getPromotions(
        String cntrNo, String cntrSn
    ) {
        List<WctaOrderDetailMngtInfDto.SearchCntrPmotRes> searchCntrPmotResList = null;
        List<WctaOrderDetailMngtInfDto.SearchFgptCntrRes> searchFgptCntrResList = null;

        searchCntrPmotResList = mapper.selectCntrPmotList(cntrNo, cntrSn);
        searchFgptCntrResList = mapper.selectFgptCntrList(cntrNo, cntrSn);

        return new WctaOrderDetailMngtInfDto.SearchPmotFgptRes(
            searchCntrPmotResList, searchFgptCntrResList
        );
    }
}
