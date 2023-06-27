package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseCloseCheckDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseCloseCheckMapper;
import com.sds.sflex.system.config.validation.ValidAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * W-SV-S-0090 해약매니저 재고 확인
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.06.26
 */

@Service
@RequiredArgsConstructor
public class WsnaWarehouseCloseCheckService {

    private final WsnaWarehouseCloseCheckMapper mapper;

    //이동입고 수량존재시
    private static final String MMT_STOCK_RETURN = "01";

    private static final String PITM_STOCK_RETURN = "02";

    private static final String MNGT_CST_ASN_RETURN = "03";

    private static final String BFSVC_ASN_RETURN = "04";

    private static final String NOM_RETURN = "00";

    /**
     * 물량이동 수불 데이터 처리
     *
     * @param dvo (필수) 해약매니저 재고확인
     * @return
     */
    public String getWarehouseCloseCheck(WsnaWarehouseCloseCheckDvo dvo) {
        String returnValue = null;
        //유효성 체크
        ValidAssert.notNull(dvo);

        //1.1 월파트너 내역에서 파라미터로 받은 사번의 내역을 조회
        List<WsnaWarehouseCloseCheckDvo> dataDvos = this.mapper.selectPrtnrNoInfo(dvo);

        if (CollectionUtils.isNotEmpty(dataDvos)) {

            for (WsnaWarehouseCloseCheckDvo dataDvo : dataDvos) {
                //1.2 품목입고내역 및 서비스품목재고내역 , 월별창고내역에서 각각 넘어온 파라미터로 데이터가 존재하는지 확인
                int mmtCount = this.mapper.selectMmtCountCloseCheck(dataDvo);

                if (mmtCount > 0) {
                    returnValue = MMT_STOCK_RETURN;
                }
                //1.3 월별품목재고내역 , 월별창고내역에서 시점재고가 있는지 확인한다.
                int pitmCount = this.mapper.selectPitmCountCloseCheck(dataDvo);

                if (pitmCount > 0) {
                    returnValue = PITM_STOCK_RETURN;
                }

                //1.4 고객서비스수행내역 에서 관리고객계정이 존재하는경우 체크
                int mngtCount = this.mapper.selectMngtCountCloseCheck(dataDvo);

                if (mngtCount > 0) {
                    returnValue = MNGT_CST_ASN_RETURN;
                }
                //1.5 고객서비스BS배정내역(TB_SVPD_CST_SV_BFSVC_ASN_IZ)에서 방문계정이 존재하는 경우
                int bfsvcCount = this.mapper.selectBfsvcCountCloseCheck(dataDvo);

                if (bfsvcCount > 0) {
                    returnValue = BFSVC_ASN_RETURN;
                }

                //정상일경우
                returnValue = NOM_RETURN;

            }

        }

        return returnValue;

    }

}
