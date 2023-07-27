package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchFrdmPkgReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchFrdmPkgRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPkgBasePdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPkgPdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaSeedingMapper;
import com.sds.sflex.system.config.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineRes;

@Service
@RequiredArgsConstructor
public class WctaSeedingService {
    public static final String SELL_TP_DTL_CD_SEEDING = "62";
    public static final String SELL_TP_DTL_CD_CAPSULE = "63";
    private final WctaSeedingMapper mapper;

    public List<SearchMachineRes> getMachinery(SearchMachineReq dto) {
        return mapper.selectMachinery(dto);
    }

    public SearchFrdmPkgRes getFrdmPkgs(SearchFrdmPkgReq req) {
        WctaPkgBasePdDvo basePd = mapper.selectWctaPkgBasePd(req.basePdCd()).orElseThrow(() ->
            new BizException("해당 제품 코드를 가진 패키지 상품이 없습니다."));

        List<WctaPkgPdDvo> pkgPds;
        if (SELL_TP_DTL_CD_SEEDING.equals(basePd.getSellTpDtlCd()) && basePd.getTotKndQty() > 0) {
            pkgPds = mapper.selectSeedings(req);
        } else if (SELL_TP_DTL_CD_CAPSULE.equals(basePd.getSellTpDtlCd())) {
            pkgPds = mapper.selectCapsules(req);
        } else {
            throw new BizException("해당 상품은 자유 패키지 상품이 아닙니다.");
        }

        return new SearchFrdmPkgRes(basePd, pkgPds);
    }
}
