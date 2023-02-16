package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBfsvcCrdovrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBfsvcCrdovrAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBfsvcCrdovrAsnMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncBfsvcCrdovrAsnService {

    private final WsncBfsvcCrdovrAsnMapper mapper;

    @Transactional
    public int saveBfsvcCrdovrAsn(WsncBfsvcCrdovrAsnDto.SaveReq dto) throws Exception {

        //해당년월의 전월 데이터가 없는 경우
        List<WsncBfsvcCrdovrAsnDvo> beforeBfSvcCrdovrAsns = mapper.selectBeforeBfsvcCrdovrAsn(dto);
        if (CollectionUtils.isEmpty(beforeBfSvcCrdovrAsns)) {
            throw new BizException("MSG_TXT_CAN_NOT_CRDOVR"); //이월불가! 전월 배정된 자료가 없습니다!
        }

        //해당년월의 데이터가 존재하는 경우
        if (mapper.selectBfsvcCrdovrAsn(dto).size() > 0) {
            throw new BizException("MSG_TXT_CAN_NOT_ASN"); //배정불가! 이미 당월 배정된 자료가 있습니다!
        }

        //신규 고객서비스배정번호 채번
        WsncBfsvcCrdovrAsnDvo newCstSvAsnNo = mapper.selectNewCstSvAsnNo();
        newCstSvAsnNo.setCstSvAsnNo(beforeBfSvcCrdovrAsns.get(0).getCstSvAsnNo());

        //insert TB_SVPD_CST_SV_BFSVC_ASN_IZ(고객서비스BS배정내역)
        //TODO : 확정담당자가 퇴사자인 경우, 해당 지역의 업무담당자를 update (현재 로직 미협의)
        mapper.insertCstSvBfsvcAsn(newCstSvAsnNo);

        //insert TB_SVPD_RGBS_PU_ITM_IZ(정기BS투입품목내역)
        mapper.updateRgbsPuItmIz(newCstSvAsnNo);

        //insert TB_SVPD_CST_SV_BFSVC_OJ_IZ(고객서비스BS대상내역)
        mapper.updateCstSvBfsvcOjIz(newCstSvAsnNo);

        //insert TB_SVPD_CST_SV_BFSVC_ASN_HIST(고객서비스BS배정이력)
        mapper.insertCstSvBfsvcAsnHist(newCstSvAsnNo);

        //insert TB_SVPD_WK_DTM_CH_IZ(작업일시변경내역)
        mapper.insertWkDtmChIz(newCstSvAsnNo);

        return 1;
    }

}
