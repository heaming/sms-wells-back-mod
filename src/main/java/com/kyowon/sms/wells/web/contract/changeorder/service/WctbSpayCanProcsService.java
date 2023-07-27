package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSpayCanProcsDto;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbSpayCanProcsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbSpayCanProcsService {
    private final WctbSpayCanProcsMapper mapper;

    @Transactional
    public WctbSpayCanProcsDto.SearchRes saveKMembersCancelProcss(WctbSpayCanProcsDto.SearchReq dto) {
        WctbSpayCanProcsDto.SearchRes res = null;
        if (StringUtils.isAnyEmpty(dto.cntrNo(), dto.cntrSn())) {
            res = new WctbSpayCanProcsDto.SearchRes("N", "", "주문번호를 확인하십시오");
            return res;
        }
        if (!dto.useCls().equals("5")) {
            res = new WctbSpayCanProcsDto.SearchRes("N", "", "사용구분을 확인하십시오");
            return res;
        }
        WctbSpayCanProcsDto.SearchSsctCntrDtlRes dtlRes = mapper.selectCntrDtl(dto);
        if (dtlRes == null) {
            res = new WctbSpayCanProcsDto.SearchRes("N", "", "등록된 해당자료가 없습니다!");
            return res;
        }
        if ("303".equals(dtlRes.cntrDtlStatCd())) {
            res = new WctbSpayCanProcsDto.SearchRes("N", "", "기준일자 이전에 취소가 된 건");
            return res;
        }
        if (StringUtils.isNotEmpty(dtlRes.cntrPdStrtdt())) {
            res = new WctbSpayCanProcsDto.SearchRes("N", "", "삭제불가！처리완료된자료입니다");
            return res;
        }
        if (!"46".equals(dtlRes.alncmpCd())) {
            res = new WctbSpayCanProcsDto.SearchRes("N", "", "삭제불가！포인트몰 주문이 아닙니다!");
            return res;
        }
        //환불처리 -> 환불처리라고는 하지만, 실제로는 계약취소 처리 임.(아직 돈을 받지 않은 상태임)
        mapper.upateCntrDtl(dto);
        mapper.updateCntrDchHist(dto);
        mapper.insertCntrDchHist(dto);
        mapper.updateCntrDtlStatChHist(dto);
        mapper.insertCntrDtlStatChHist(dto);
        String cttOjId = mapper.selectCttOjId(dto);
        mapper.updateSsopCttBas(cttOjId);
        mapper.updateSsopCttChHist(cttOjId);
        mapper.insertSsopCttChHist(cttOjId);
        res = new WctbSpayCanProcsDto.SearchRes("Y", "", "");
        return res;
    }
}
