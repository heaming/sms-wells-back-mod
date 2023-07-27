package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractDetailInfoConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.*;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailInfoDvo;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractDetailInfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractDetailInfoService {

    private final WctiContractDetailInfoMapper mapper;
    private final WctiContractDetailInfoConverter converter;

    /**
    * 계약상세 정보 조회
    *
    * @interfaceId   EAI_WSSI1047
    * @programId   W-SS-I-0004
    * @param       dto
    * @return      FindRes
    */
    public FindRes getContractDetail(FindReq dto) {
        WctiContractDetailInfoDvo returnDvo = mapper.selectContractDetail(dto).orElse(null);

        if (ObjectUtils.isNotEmpty(returnDvo)) {
            //계약결제관계 정보를 조회
            FindStlmRes sltmRes = mapper.selectContractDetailStlm(dto);
            if (sltmRes != null) {
                returnDvo.setFntDt(StringUtils.defaultString(sltmRes.fntDt(), ""));
                returnDvo.setDpTpCd(StringUtils.defaultString(sltmRes.dpTpCd(), ""));
                returnDvo.setFnitAprRsCd(StringUtils.defaultString(sltmRes.fnitAprRsCd(), ""));
            }

            //집금파트너정보를 조회
            FindClctamRes clctamRes = mapper.selectContractDetailClctam(dto);
            if (clctamRes != null) {
                returnDvo.setClctamPrtnrNo(clctamRes.clctamPrtnrNo());
                returnDvo.setClctamPrtnrNm(clctamRes.clctamPrtnrNm());
            }

            //멤버십 모코드(렌탈, 일시불)를 조회
            if ("3".equals(returnDvo.getSellTpCd())) {
                FindMembershipRes membershipRes = mapper.selectContractDetailMembership(dto);
                if (membershipRes != null) {
                    returnDvo.setOjCntrNo(membershipRes.ojCntrNo());
                    returnDvo.setOjCntrSn(membershipRes.ojCntrSn());
                    returnDvo.setOjSellTpCd(membershipRes.ojSellTpCd());
                    returnDvo.setOjSellTpNm(membershipRes.ojSellTpNm());
                    returnDvo.setOjIstDt(membershipRes.ojIstDt());
                }
            }

            //정기배송가격구분코드를 조회
            returnDvo.setRglrSppPrcDvCd(StringUtils.defaultString(mapper.selectContractDetailRglrSpp(dto), ""));
        }

        return converter.mapWctiContractDetailInfoDvoToFindRes(returnDvo);
    }
}
