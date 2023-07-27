package com.kyowon.sms.wells.web.contract.changeorder.service;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbAffiliateAlncDto.SaveReq;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbAffiliateAlncConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbAffiliateAlncDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbAffiliateAlncMapper;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzAcmpalContractIzHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractWellsDetailHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbAffiliateAlncService {

    private final WctbAffiliateAlncMapper mapper;
    private final WctbAffiliateAlncConverter converter;
    private final WctzHistoryService historyService;

    @Transactional
    public String saveMutualAidAllianceRsg(SaveReq dto) {
        WctbAffiliateAlncDvo acmpalDvo = converter.saveReqToWctbAffiliateAlncDvo(dto);

        WctbAffiliateAlncDvo checkDvo = mapper.selectAffiliateAlncCheck(dto);

        if (ObjectUtils.isEmpty(checkDvo)) {
            return "2";
        }

        List<WctbAffiliateAlncDvo> alncDvo = mapper.selectAffiliateAlnc(dto);

        if (CollectionUtils.isEmpty(alncDvo)) {
            return "4";
        }

        for (WctbAffiliateAlncDvo dvo : alncDvo) {

            String basePdCd = dvo.getBasePdCd(); /* 기존상품코드 */
            String pmotCd = dvo.getPmotCd(); /* 프로모션코드 */
            String leaseYn = dvo.getLeaseYn(); /* 리스구분 */
            String sellDscDvCd = dvo.getSellDscDvCd(); /* 판매할인구분코드 */
            String grpDv = dvo.getGrpDv(); /* 그룹구분 */
            int dscMcn = dvo.getDscMcn(); /* 할인개월 */
            int istmMcn = dvo.getIstmMcn(); /* 렌탈기간 */
            int cntrDt = Integer.parseInt(dvo.getCntrDt()); /* 계약일 */
            int recapDutyPtrmN = dvo.getRecapDutyPtrmN(); /* 유상의무기간수 */
            int cntrYm = Integer.parseInt(dvo.getCntrDt().substring(0, 6));

            if (!List.of("4055", "4056", "4065", "4066", "4129").contains(basePdCd) /* TODO: 상품코드 추가 시 수정 예정 */
                && !List.of("18", "19").contains(pmotCd)
                && !"Y".equals(leaseYn)) {
                if ((dscMcn >= istmMcn) || (cntrDt > 20190522 && "7".equals(sellDscDvCd))) {
                    dvo.setPrmApyDvCd("2"); // 선납적용구분코드
                } else if ((dscMcn > recapDutyPtrmN) || (cntrYm > 201608 && dscMcn < recapDutyPtrmN)) {
                    dvo.setPrmApyDvCd("1");
                } else if ("P".equals(grpDv) && dscMcn <= recapDutyPtrmN) {
                    dvo.setPrmApyDvCd("5");
                } else if (("03".equals(pmotCd) && cntrDt <= 20190606 && dscMcn <= 6)
                    || ("03".equals(pmotCd) && cntrDt >= 20190607 && dscMcn <= 12)) {
                    dvo.setPrmApyDvCd("3");
                } else {
                    dvo.setPrmApyDvCd("");
                }
                int wellsRes = mapper.updateCntrWellsDtl(dvo);
                BizAssert.isTrue(wellsRes >= 1, "MSG_ALT_SVE_ERR");

                // WELLS 상세 History 생성
                historyService.createContractWellsDetailChangeHistory(
                    WctzContractWellsDetailHistDvo.builder()
                        .cntrNo(dvo.getCntrNo())
                        .cntrSn(dvo.getCntrSn())
                        .build()
                );

                int acmpalRes = mapper.updateAcmpalCntrIz(acmpalDvo);
                BizAssert.isTrue(acmpalRes >= 1, "MSG_ALT_SVE_ERR");

                // 관계사제휴계약 History 생성
                historyService.createAcmpalContractIzChangeHistory(
                    WctzAcmpalContractIzHistDvo.builder()
                        .acmpalCntrId(acmpalDvo.getAcmpalCntrId())
                        .build()
                );
            }
        }

        return "";
    }
}
