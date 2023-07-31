package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.service.WctbCancelBaseService;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelMembershipContractReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelMembershipContractRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelRentalContractReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelRentalContractRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractCancelDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractCancelMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractCancelService {

    private final WctiContractCancelMapper mapper;

    private final WctbCancelBaseService cancelBaseService;

    @Transactional
    public CancelRentalContractRes cancelRentalContract(CancelRentalContractReq dto) {
        // 계약정보조회
        WctiContractCancelDvo contract = mapper.selectContract(dto.cntrNo(), dto.cntrSn())
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));

        contract.setCanDt(dto.canDt());
        contract.setRgstUsrId(dto.rgstUsrId());

        // validation
        int overDaysCntrExnDt = DateUtil.getDays(contract.getCanDt(), contract.getCntrExnDt());
        BizAssert.isTrue(overDaysCntrExnDt >= 0, "취소요청일자가 계약만료일자 이후입니다.");

        BizAssert.isTrue("2".equals(contract.getSellTpCd()), "렌털/리스 계약이 아닙니다.");

        // 렌털계약취소
        cancelBaseService.cancelRentalContractForInterface(contract);

        return new CancelRentalContractRes("Y", "", "정상적으로 취소되었습니다.");
    }

    @Transactional
    public CancelMembershipContractRes cancelMembershipContract(CancelMembershipContractReq dto) {
        // 계약정보조회
        WctiContractCancelDvo contract = mapper.selectContract(dto.cntrNo(), dto.cntrSn())
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));

        contract.setCanDt(dto.canDt());
        contract.setSlCtrAmt(dto.slCtrAmt());
        contract.setDscDdctam(dto.dscDdctam());
        contract.setFiltDdctam(dto.filtDdctam());
        contract.setRgstUsrId(dto.rgstUsrId());

        // validation
        BizAssert.isTrue("6".equals(contract.getSellTpCd()), "정기배송 계약이 아닙니다.");

        // 정기배송해약취소
        cancelBaseService.cancelMembershipContractForInterface(contract);

        return new CancelMembershipContractRes("Y", "", "정상적으로 취소되었습니다.");
    }
}
