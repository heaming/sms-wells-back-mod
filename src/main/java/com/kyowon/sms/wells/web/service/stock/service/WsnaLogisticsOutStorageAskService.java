package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaLogisticsOutStorageAskConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1005.request.LogisticsOutOfStorageCancelReqIvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1005.response.LogisticsOutOfStorageCancelResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaLogisticsOutStorageAskMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.service.EaiInterfaceService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 인터페이스 데이터 생성
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-25
 */

@Service
@RequiredArgsConstructor
public class WsnaLogisticsOutStorageAskService {

    private final WsnaLogisticsOutStorageAskMapper mapper;

    private final WsnaLogisticsOutStorageAskConverter converter;

    private final EaiInterfaceService interfaceService;

    private final WsnaLogisticsDeliveryAskService deliveryService;

    private static final String YN_Y = "Y";

    private static final String RESULT_CODE_S = "0";

    private static final String LGST_OSTR_CD = "ORWE";

    // 출고요청취소 URI
    private static final String EAI_CBD01005 = "/C/BD/EAI_CBDO1005/req";

    /**
     * 출고요청품목 생성
     * @param dvos  (필수) 출고요청품목 리스트
     * @return 출고요청품목 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsOutStorageAskResDvo createOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notEmpty(dvos);
        for (WsnaLogisticsOutStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getOstrAkNo());
            ValidAssert.hasText(dvo.getOstrAkTpCd());
            ValidAssert.hasText(dvo.getOstrAkRgstDt());
            ValidAssert.hasText(dvo.getIostAkDvCd());
            ValidAssert.hasText(dvo.getWareMngtPrtnrNo());
            ValidAssert.hasText(dvo.getWareMngtPrtnrOgTpCd());
            ValidAssert.hasText(dvo.getLgstSppMthdCd());
            ValidAssert.hasText(dvo.getLgstWkMthdCd());
            ValidAssert.hasText(dvo.getItmPdCd());
            ValidAssert.hasText(dvo.getItmGdCd());
            ValidAssert.hasText(dvo.getOstrOjWareNo());
        }

        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 생성 건수
        int akCnt = 0;
        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 생성 건수
        int akDtlCnt = 0;

        // 출고요청번호 distinct, 출고요청품목으로 데이터를 받기 때문에 출고요청번호(Master) 기준으로 데이터를 필터링 하기 위함.
        List<String> ostrAkNos = dvos.stream().map(WsnaLogisticsOutStorageAskReqDvo::getOstrAkNo).distinct()
            .toList();

        for (String ostrAkNo : ostrAkNos) {
            // 출고요청번호로 품목출고요청송신전문 데이터가 존재하는지 체크
            WsnaLogisticsOutStorageAskDvo askDvo = this.mapper.selectItmOstrAkSendEtxtByOstrAkNo(ostrAkNo);

            // 출고요청번호에 해당하는 품목출고 리스트
            List<WsnaLogisticsOutStorageAskReqDvo> itms = dvos.stream()
                .filter(dvo -> ostrAkNo.equals(dvo.getOstrAkNo())).toList();

            if (ObjectUtils.isEmpty(askDvo) && CollectionUtils.isNotEmpty(itms)) {
                // 데이터가 존재하지 않는 경우 품목출고요청송신전문 데이터 생성
                askDvo = this.createItmOstrAkSendEtxt(itms.get(0));
                akCnt++;
            }
            // 출고요청상세송신전문 데이터 생성
            if (ObjectUtils.isNotEmpty(askDvo)) {
                akDtlCnt += this.createOstrAkDtlSendEtxts(askDvo, itms);
            }
        }

        WsnaLogisticsOutStorageAskResDvo res = new WsnaLogisticsOutStorageAskResDvo();
        res.setAkCnt(akCnt);
        res.setAkDtlCnt(akDtlCnt);

        return res;
    }

    /**
     * 품목출고요청송신전문 데이터 생성
     * @param dvo   (필수) 출고요청 데이터
     * @return 품목출고요청송신전문 데이터 정보
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDvo createItmOstrAkSendEtxt(
        WsnaLogisticsOutStorageAskReqDvo dvo
    ) {

        ValidAssert.notNull(dvo);

        WsnaLogisticsOutStorageAskDvo askDvo = this.converter
            .mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskDvo(dvo);

        // 물류출고요청번호 생성
        String lgstOstrAkNo = this.mapper.selectNewLgstOstrAkNo(LGST_OSTR_CD);
        askDvo.setLgstOstrAkNo(lgstOstrAkNo);

        // SAP 코드
        askDvo.setSapPlntCd(SnServiceConst.SAP_PLNT_CD);
        askDvo.setSapCoCd(SnServiceConst.SAP_CO_CD);
        askDvo.setSapSaveLctCd(SnServiceConst.SAP_SAVE_LCT_CD);

        // 데이터 생성
        this.mapper.insertItmOstrAkSendEtxt(askDvo);

        return askDvo;
    }

    /**
     * 출고요청상세송신전문 데이터 생성
     * @param askDvo (필수) 품목출고요청송신전문 데이터
     * @param dvos  (필수) 출고요청상세송신전문 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public int createOstrAkDtlSendEtxts(
        WsnaLogisticsOutStorageAskDvo askDvo,
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notNull(askDvo);
        ValidAssert.notEmpty(dvos);

        int count = 0;

        List<WsnaLogisticsOutStorageAskDtlDvo> askDtlDvos = this.converter
            .mapAllWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskDtlDvo(dvos);

        // 품목출고요청송신전문 PK 셋팅, 데이터 생성
        for (WsnaLogisticsOutStorageAskDtlDvo askDtlDvo : askDtlDvos) {
            askDtlDvo.setSapPlntCd(askDvo.getSapPlntCd());
            askDtlDvo.setLgstOstrAkNo(askDvo.getLgstOstrAkNo());
            askDtlDvo.setRelNo(askDvo.getOstrAkNo());
            askDtlDvo.setRelSn(askDtlDvo.getOstrAkSn());

            count += this.mapper.insertOstrAkDtlSendEtxt(askDtlDvo);
        }

        return count;
    }

    /**
     * 출고요청품목 수정
     * @param dvos  (필수) 출고요청품목 데이터 리스트
     * @return 데이터 수정 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public int editOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notEmpty(dvos);
        int count = 0;

        for (WsnaLogisticsOutStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getOstrAkNo());
            ValidAssert.hasText(dvo.getOstrAkTpCd());
            ValidAssert.hasText(dvo.getOstrAkRgstDt());
            ValidAssert.hasText(dvo.getIostAkDvCd());
            ValidAssert.hasText(dvo.getWareMngtPrtnrNo());
            ValidAssert.hasText(dvo.getWareMngtPrtnrOgTpCd());
            ValidAssert.hasText(dvo.getLgstSppMthdCd());
            ValidAssert.hasText(dvo.getLgstWkMthdCd());
            ValidAssert.hasText(dvo.getItmPdCd());
            ValidAssert.hasText(dvo.getItmGdCd());
            ValidAssert.hasText(dvo.getOstrOjWareNo());

            // 출고요청상세송신전문 데이터 조회
            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper
                .selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = askDtlDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우 메시지 처리, 이미 물류출고 처리되어 변경할 수 없습니다.
            BizAssert.isFalse(YN_Y.equals(trsYn), "MSG_ALT_ALRDY_LGST_OSTR_CANT_CHNG");

            WsnaLogisticsOutStorageAskDtlDvo updateDvo = this.converter
                .mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskDtlDvo(dvo);
            // PK 셋팅
            updateDvo.setSapPlntCd(askDtlDvo.getSapPlntCd());
            updateDvo.setLgstOstrAkNo(askDtlDvo.getLgstOstrAkNo());
            updateDvo.setOstrAkSn(askDtlDvo.getOstrAkSn());

            // 출고요청상세송신전문 데이터 변경
            count += this.mapper.updateOstrAkDtlSendEtxt(updateDvo);
        }

        return count;
    }

    /**
     * 출고요청품목 삭제
     * @param dvos  (필수) 출고요청품목 데이터 리스트
     * @return 데이터 삭제 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsOutStorageAskResDvo removeOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notEmpty(dvos);

        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 삭제 건수
        int akCnt = 0;
        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 삭제 건수
        int akDtlCnt = 0;

        for (WsnaLogisticsOutStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getOstrAkNo());

            // 출고요청상세송신전문 데이터 조회, 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper
                .selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = askDtlDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우 취소 API 호출
            if (YN_Y.equals(trsYn)) {
                // 물류 취소 API 호출
                LogisticsOutOfStorageCancelResIvo resIvo = this.cancelLogisticsOutOfStorage(askDtlDvo);
                // Exception 처리
                if (ObjectUtils.isNotEmpty(resIvo) && !RESULT_CODE_S.equals(resIvo.getResultCode())) {
                    // 이미 물류출고 처리되어 삭제할 수 없습니다.
                    throw new BizException("MSG_ALT_ALRDY_LGST_OSTR_CANT_DEL");
                }
            }

            // 데이터 삭제처리
            askDtlDvo.setDtaDlYn(YN_Y);
            akDtlCnt += this.mapper.updateOstrAkDtlSendEtxtForRemove(askDtlDvo);
        }

        // 출고요청번호 필터링
        List<String> ostrAkNos = dvos.stream().map(WsnaLogisticsOutStorageAskReqDvo::getOstrAkNo).distinct()
            .toList();
        for (String ostrAkNo : ostrAkNos) {
            // 품목출고요청상세송신 데이터가 모두 삭제처리 되었는지 체크
            Integer removeCount = this.mapper.selectOstrAkDtlSendEtxtCount(ostrAkNo);
            if (ObjectUtils.isEmpty(removeCount)) {
                // 품목출고요청상세송신 데이터가 모두 삭제된 경우 품목 출고요청송신 데이터 삭제처리
                WsnaLogisticsOutStorageAskDvo askDvo = this.mapper.selectItmOstrAkSendEtxtByOstrAkNo(ostrAkNo);
                if (ObjectUtils.isNotEmpty(askDvo)) {
                    askDvo.setDtaDlYn(YN_Y);
                    akCnt += this.mapper.updateItmOstrAkSendEtxtForRemove(askDvo);
                }
            }
        }

        WsnaLogisticsOutStorageAskResDvo res = new WsnaLogisticsOutStorageAskResDvo();
        res.setAkCnt(akCnt);
        res.setAkDtlCnt(akDtlCnt);

        return res;
    }

    /**
     * 물류 출고요청 취소 API 호출
     * @param askDtlDvo (필수) 출고요청상세송신전문 데이터
     * @return 물류 출고요청 취소 Response Interface Dvo
     */
    private LogisticsOutOfStorageCancelResIvo cancelLogisticsOutOfStorage(@NotEmpty
    WsnaLogisticsOutStorageAskDtlDvo askDtlDvo
    ) {

        // 사용자 세션
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        LogisticsOutOfStorageCancelReqIvo req = new LogisticsOutOfStorageCancelReqIvo();
        req.setSapPlntCd(askDtlDvo.getSapPlntCd());
        req.setOstrAkNo(askDtlDvo.getLgstOstrAkNo());
        req.setOstrAkSn(askDtlDvo.getOstrAkSn());
        // 세션정보 활용
        req.setAkCanUsrId(session.getUserId());
        req.setAkCanDeptId(session.getOgCd());

        return this.interfaceService
            .post(EAI_CBD01005, req, LogisticsOutOfStorageCancelResIvo.class);
    }

    /**
     * 물량배정출고요청품목 생성
     * @param dvos  (필수) 물량배정출고요청품목 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsOutStorageAskResDvo createQomOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        // 출고요청품목생성 메소드 호출
        WsnaLogisticsOutStorageAskResDvo akRes = this.createOutOfStorageAsks(dvos);

        List<WsnaLogisticsDeliveryAskReqDvo> deliveryDvos = this.converter
            .mapAllCWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsDeliveryAskReqDvo(dvos);

        // 배송요청 인터페이스 호출
        WsnaLogisticsDeliveryAskResDvo deliveryRes = this.deliveryService
            .createLogisticsDeliveryAsks(deliveryDvos);

        akRes.setBasCnt(deliveryRes.getBasCnt());
        akRes.setPdCnt(deliveryRes.getPdCnt());
        akRes.setMatCnt(deliveryRes.getMatCnt());

        return akRes;
    }

    /**
     * 자가필터/건식상품 출고요청품목 생성
     * @param dvos  (필수) 자가필터/건식상품 출고요청품목 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsOutStorageAskResDvo createSelfFilterOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notEmpty(dvos);

        // 출고요청품목생성 메소드 호출
        WsnaLogisticsOutStorageAskResDvo akRes = this.createOutOfStorageAsks(dvos);

        int count = 0;

        // 택배 테이블 데이터 생성
        for (WsnaLogisticsOutStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getCstNo());
            ValidAssert.hasText(dvo.getCstNm());
            ValidAssert.hasText(dvo.getCntrNo());
            ValidAssert.notNull(dvo.getCntrSn());

            // 출고요청상세송신전문 데이터 조회
            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper
                .selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            WsnaLogisticsOutStorageAskPcsvDvo pcsvDvo = this.converter
                .mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskPcsvDvo(dvo);

            pcsvDvo.setSapPlntCd(askDtlDvo.getSapPlntCd());
            pcsvDvo.setOstrAkNo(askDtlDvo.getLgstOstrAkNo());
            pcsvDvo.setOstrAkSn(askDtlDvo.getOstrAkSn());

            count += this.mapper.insertOstrAkPcsvSendEtxt(pcsvDvo);
        }

        akRes.setPcsvCnt(count);

        return akRes;
    }

    /**
     * 자가필터/건식상품 출고요청품목 수정
     * @param dvos  (필수) 자가필터/건식상품 출고요청품목 데이터 리스트
     * @return 데이터 수정 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsOutStorageAskResDvo editSelfFilterOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notEmpty(dvos);

        int count = 0;

        // 택배 테이블 데이터 수정
        for (WsnaLogisticsOutStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getOstrAkNo());
            ValidAssert.hasText(dvo.getCstNo());
            ValidAssert.hasText(dvo.getCstNm());
            ValidAssert.hasText(dvo.getCntrNo());
            ValidAssert.notNull(dvo.getCntrSn());

            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskPcsvDvo pcsvDvo = this.mapper
                .selectOstrAkPcsvSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = pcsvDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우, 이미 물류출고 처리되어 변경할 수 없습니다.
            BizAssert.isFalse(YN_Y.equals(trsYn), "MSG_ALT_ALRDY_LGST_OSTR_CANT_CHNG");

            // 데이터 수정
            WsnaLogisticsOutStorageAskPcsvDvo updateDvo = this.converter
                .mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskPcsvDvo(dvo);

            // pk셋팅
            updateDvo.setSapPlntCd(pcsvDvo.getSapPlntCd());
            updateDvo.setOstrAkNo(pcsvDvo.getOstrAkNo());
            updateDvo.setOstrAkSn(pcsvDvo.getOstrAkSn());

            count += this.mapper.updateOstrAkPcsvSendEtxt(updateDvo);
        }

        // 출고요청품목수정 메소드 호출
        int akDtlCnt = this.editOutOfStorageAsks(dvos);

        WsnaLogisticsOutStorageAskResDvo res = new WsnaLogisticsOutStorageAskResDvo();
        res.setAkDtlCnt(akDtlCnt);
        res.setPcsvCnt(count);

        return res;
    }

    /**
     * 자가필터/건식상품 출고요청품목 삭제
     * @param dvos  (필수) 자가필터/건식상품 출고요청품목 데이터 리스트
     * @return 데이터 삭제 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsOutStorageAskResDvo removeSelfFilterOutOfStorageAsks(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    ) {

        ValidAssert.notEmpty(dvos);

        int count = 0;

        // 택배 테이블 데이터 삭제
        for (WsnaLogisticsOutStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getOstrAkNo());

            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskPcsvDvo pcsvDvo = this.mapper
                .selectOstrAkPcsvSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = pcsvDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우, 이미 물류출고 처리되어 삭제할 수 없습니다.
            BizAssert.isFalse(YN_Y.equals(trsYn), "MSG_ALT_ALRDY_LGST_OSTR_CANT_DEL");

            // 데이터 삭제처리
            pcsvDvo.setDtaDlYn(YN_Y);
            count += this.mapper.updateOstrAkPcsvSendEtxtForRemove(pcsvDvo);
        }

        // 출고요청품목삭제 메소드 호출
        WsnaLogisticsOutStorageAskResDvo akRes = this.removeOutOfStorageAsks(dvos);
        akRes.setPcsvCnt(count);

        return akRes;
    }

}
