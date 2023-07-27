package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSellLimitProductOutDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSellLimitProductQtyDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbSellLimitProductMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbSellLimitProductService {
    private final WctbSellLimitProductMapper mapper;

    /**
     * 일시불 본품(선착순)
     *  - 판매 제한 상품 누적 수량 수정
     *
     * @programId W-SS-S-0073
     * @param  workDiv 1.등록 4.삭제
     * @param  pdCd  상품코드
     * @param  inQty  수량
     * @return inOutDvo
     */
    public WctbSellLimitProductOutDvo getSpayStpdFcfs(String workDiv, String pdCd, int inQty) {
        ValidAssert.notNull(workDiv);
        ValidAssert.notNull(pdCd);
        ValidAssert.notNull(inQty);

        // 정의된 상품코드. 수량체크 없이 리턴
        // TODO : 상품코드 맵핑 필요
        // return new WctbSellLimitProductOutDvo();

        // 립력값 확인  - workDiv
        if (!("1".equals(workDiv) || "4".equals(workDiv))) {
            return WctbSellLimitProductOutDvo.builder()
                .rtnMessag("작업구분을 확인해주세요.")
                .rtnFlag("N")
                .build();
        }

        //접수수량(PARM-IQ)이 0 이면 오류셋팅후 종료
        if (inQty == 0) {
            return WctbSellLimitProductOutDvo.builder()
                .rtnMessag("접수수량을 확인해주세요.")
                .rtnFlag("N")
                .build();
        }

        // 상품코드로 남은수량 조회
        WctbSellLimitProductQtyDvo qtyDvo = mapper.selectQtyCheck(pdCd);

        // 등록 일 때, 수량 체크
        if ("1".equals(workDiv)) {
            if (qtyDvo.getWorkQty() == 0) {
                return WctbSellLimitProductOutDvo.builder()
                    .rtnFlag("N")
                    .rtnMessag("주문가능 수량이 없습니다！")
                    .build();
            }

            if (inQty > 0 && (qtyDvo.getWorkQty() - inQty) < 0) { // .. &&  최종 잔여수량 < 0
                return WctbSellLimitProductOutDvo.builder()
                    .rtnFlag("N")
                    .rtnMessag(pdCd + " : 잔고-" + qtyDvo.getWorkQty() + "개 확인요망")
                    .build();
            }
        } else {
            //삭제 일 때,
            inQty *= -1;
        }

        // 데이터 처리 - update, insert
        this.updateQty(qtyDvo, inQty);

        return WctbSellLimitProductOutDvo.builder()
            .rtnFlag("Y")
            .build();
    }

    private void updateQty(WctbSellLimitProductQtyDvo dvo, int inQty) {
        String sellLmPdBaseId = dvo.getSellLmPdBaseId();
        dvo.setSellAcuQty(dvo.getSellAcuQty() + inQty); // 최종 판매누적

        // update  VL_END_DTM = SYSDATE
        // 기존 데이터 가능상태 -> 중지상태로
        if ("N".equals(dvo.getStpYn())) {
            mapper.updateSellLimitPdQtyBasVlEndDtm(sellLmPdBaseId);
        }

        if ((dvo.getWorkQty() - inQty) <= 0) { // 최종 잔여수량 <= 0 (0인 case 만 있음)
            if ("N".equals(dvo.getStpYn())) {
                // 최종 잔여수량이 없으면 중지상태의 1 ROW INSERT
                dvo.setSellLmRsonCn("중지여부:N && 접수수량(WORK-IQTY) + 판매누적수량(조회)) >= 판매기준수량(조회결과)"); // TODO : 사유등록포멧 필요성 확인
                mapper.insertSellLimitPdQtyBas(dvo, sellLmPdBaseId);
            }
        } else {
            // 최종 잔여수량이 있으면 가능상태의 1 ROW INSERT
            mapper.insertSellLimitPdQtyBas(dvo, sellLmPdBaseId);
        }
    }
}
