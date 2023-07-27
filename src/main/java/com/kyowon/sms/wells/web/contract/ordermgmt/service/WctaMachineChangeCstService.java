package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMachineChStatInOutDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbMachineChangeStatusService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaMachineChangeCstConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaMachineChangeCstDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaMachineChangeCstMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaMachineChangeCstService {

    private final WctaMachineChangeCstMapper mapper;
    private final WctaMachineChangeCstConverter converter;
    private final WctbMachineChangeStatusService statusService;

    public FindRes getMachineChangeCst(FindReq dto) throws Exception{
        //세션 조회
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        //기존에 접수된 기기변경 건이 있는지 체크
        String cntrNoSn = mapper.selectMachineChangeCstCntrNoSn(dto);
        if(StringUtils.isNotEmpty(cntrNoSn)){
            throw new BizException("MSG_ALT_INPROGRESS_CNTR_SN", cntrNoSn);
        }

        //기기변경 고객 유효성 체크 조회 (서비스 호출)
        WctbMachineChStatInOutDvo validDvo = converter.mapFindReqToWctbMachineChStatInOutDvo(dto);
        validDvo.setOgTpCd(session.getOgTpCd());
        validDvo = statusService.getMachineChangeStatus(validDvo);

        //기기변경대상건 주소 및 기타 조회
        WctaMachineChangeCstDvo baseInfoDvo = mapper.selectCustomerAddress(validDvo.getCntrNo(), validDvo.getCntrSn()).orElseGet(WctaMachineChangeCstDvo::new);

        return converter.mapWctaMachineChangeCstDvoToFindRes(validDvo, baseInfoDvo);
    }
}
