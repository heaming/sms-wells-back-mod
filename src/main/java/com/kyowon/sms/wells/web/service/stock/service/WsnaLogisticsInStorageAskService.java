package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaLogisticsInStorageAskConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskResDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1006.request.LogisticsInStorageCancelReqIvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1006.response.LogisticsInStorageCancelResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaLogisticsInStorageAskMapper;
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
 * W-SV-S-0089 물류 반품요청 인터페이스 데이터 생성
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-30
 */

@Service
@RequiredArgsConstructor
public class WsnaLogisticsInStorageAskService {

    private final WsnaLogisticsInStorageAskMapper mapper;

    private final WsnaLogisticsInStorageAskConverter converter;

    private final EaiInterfaceService interfaceService;

    private static final String LGST_STR_CD = "ORWE";

    private static final String YN_Y = "Y";

    private static final String RESULT_CODE_S = "0";

    // 입고요청취소 URI
    private static final String EAI_CBD01006 = "/C/BD/EAI_CBDO1006/req";

    /**
     * 입고요청품목 생성
     * @param dvos  (필수) 반품입고품목 리스트
     * @return 반품입고요청 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsInStorageAskResDvo createInStorageAsks(
        List<WsnaLogisticsInStorageAskReqDvo> dvos
    ) {

        // 파라미터 유효성 체크
        ValidAssert.notEmpty(dvos);
        for (WsnaLogisticsInStorageAskReqDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getOstrAkNo());
            ValidAssert.hasText(dvo.getOstrAkTpCd());
            ValidAssert.hasText(dvo.getOstrAkRgstDt());
            ValidAssert.hasText(dvo.getLgstStrTpCd());
            ValidAssert.hasText(dvo.getIostAkDvCd());
            ValidAssert.hasText(dvo.getWareMngtPrtnrNo());
            ValidAssert.hasText(dvo.getWareMngtPrtnrOgTpCd());
            ValidAssert.hasText(dvo.getLgstSppMthdCd());
            ValidAssert.hasText(dvo.getItmPdCd());
            ValidAssert.hasText(dvo.getItmGdCd());
            ValidAssert.hasText(dvo.getOstrOjWareNo());
        }

        // TB_IFIN_PD_RTNGD_AK_SEND_ETXT - 상품반품요청송신전문 데이터 생성 건수
        int akCnt = 0;
        // TB_IFIN_RTNGD_AK_DTL_SEND_ETXT - 반품요청상세송신전문 데이터 생성 건수
        int akDtlCnt = 0;

        // 출고요청번호 distinct, 출고요청품목으로 데이터를 받기 때문에 출고요청번호(Master) 기준으로 데이터를 필터링 하기 위함.
        List<String> ostrAkNos = dvos.stream().map(WsnaLogisticsInStorageAskReqDvo::getOstrAkNo).distinct()
            .toList();

        for (String ostrAkNo : ostrAkNos) {
            // 출고요청번호로 반품출고요청송신전문 데이터가 존재하는지 체크
            WsnaLogisticsInStorageAskDvo askDvo = this.mapper.selectPdRtngdAkSendEtxtByRtngdAkNo(ostrAkNo);

            // 출고요청번호에 해당하는 품목출고 리스트
            List<WsnaLogisticsInStorageAskReqDvo> itms = dvos.stream()
                .filter(dvo -> ostrAkNo.equals(dvo.getOstrAkNo())).toList();

            if (ObjectUtils.isEmpty(askDvo) && CollectionUtils.isNotEmpty(itms)) {
                // 데이터가 존재하지 않는 경우 반품출고요청송신전문 데이터 생성
                askDvo = this.createPdRtngdAkSendEtxt(itms.get(0));
                akCnt++;
            }
            // 반품요청상세송신전문 데이터 생성
            if (ObjectUtils.isNotEmpty(askDvo)) {
                akDtlCnt += this.createRtngdAkDtlSendEtxt(askDvo, itms);
            }
        }

        WsnaLogisticsInStorageAskResDvo res = new WsnaLogisticsInStorageAskResDvo();
        res.setAkCnt(akCnt);
        res.setAkDtlCnt(akDtlCnt);

        return res;
    }

    /**
     * 반품출고요청송신전문 데이터 생성
     * @param dvo (필수) 반품출고 데이터
     * @return 반품출고요청송신전문 데이터 정보
     */
    @Transactional
    public WsnaLogisticsInStorageAskDvo createPdRtngdAkSendEtxt(
        WsnaLogisticsInStorageAskReqDvo dvo
    ) {
        ValidAssert.notNull(dvo);

        WsnaLogisticsInStorageAskDvo askDvo = this.converter
            .mapWsnaLogisticsInStorageAskReqDvoToWsnaLogisticsInStorageAskDvo(dvo);

        // 물류입고요청번호 생성
        String lgstStrAkNo = this.mapper.selectNewLgstStrAkNo(LGST_STR_CD);
        askDvo.setLgstStrAkNo(lgstStrAkNo);

        askDvo.setSapPlntCd(SnServiceConst.SAP_PLNT_CD);
        askDvo.setSapCoCd(SnServiceConst.SAP_CO_CD);
        askDvo.setSapSaveLctCd(SnServiceConst.SAP_SAVE_LCT_CD);

        this.mapper.insertPdRtngdAkSendEtxt(askDvo);

        return askDvo;
    }

    /**
     * 반품요청상세송신전문 데이터 생성
     * @param askDvo    (필수) 반품출고요청송신전문 데이터
     * @param dvos      (필수) 반품요청상세송신전문 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public int createRtngdAkDtlSendEtxt(
        WsnaLogisticsInStorageAskDvo askDvo,
        List<WsnaLogisticsInStorageAskReqDvo> dvos
    ) {

        ValidAssert.notNull(askDvo);
        ValidAssert.notEmpty(dvos);

        int count = 0;

        List<WsnaLogisticsInStorageAskDtlDvo> askDtlDvos = this.converter
            .mapAllWsnaLogisticsInStorageAskReqDvoToWsnaLogisticsInStorageAskDtlDvo(dvos);

        // 품목출고요청송신전문 PK 셋팅, 데이터 생성
        for (WsnaLogisticsInStorageAskDtlDvo askDtlDvo : askDtlDvos) {
            askDtlDvo.setSapPlntCd(askDvo.getSapPlntCd());
            askDtlDvo.setLgstStrAkNo(askDvo.getLgstStrAkNo());
            askDtlDvo.setRelNo(askDvo.getRtngdAkNo());
            askDtlDvo.setRelSn(askDtlDvo.getStrAkSn());

            count += this.mapper.insertRtngdAkDtlSendEtxt(askDtlDvo);
        }

        return count;

    }

    /**
     * 입고요청품목 수정
     * @param dvos  (필수) 반품요청품목 데이터 리스트
     * @return 데이터 수정 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public int editInStorageAsks(
        List<WsnaLogisticsInStorageAskReqDvo> dvos
    ) {
        // 파라미터 유효성 체크
        ValidAssert.notEmpty(dvos);

        int count = 0;

        for (WsnaLogisticsInStorageAskReqDvo dvo : dvos) {
            // 유효성 체크
            ValidAssert.hasText(dvo.getOstrAkNo());

            // 상품반품요청송신전문 데이터 조회
            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsInStorageAskDtlDvo askDtlDvo = this.mapper
                .selectRtngdAkDtlSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = askDtlDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우 메시지 처리, 이미 물류입고 처리되어 변경할 수 없습니다.
            BizAssert.isTrue(YN_Y.equals(trsYn), "MSG_ALT_ALRDY_LGST_STR_CANT_CHNG");

            WsnaLogisticsInStorageAskDtlDvo updateDvo = this.converter
                .mapWsnaLogisticsInStorageAskReqDvoToWsnaLogisticsInStorageAskDtlDvo(dvo);
            // PK 셋팅
            updateDvo.setSapPlntCd(askDtlDvo.getSapPlntCd());
            updateDvo.setLgstStrAkNo(askDtlDvo.getLgstStrAkNo());
            updateDvo.setStrAkSn(askDtlDvo.getStrAkSn());

            // 반품요청상세송신전문 데이터 변경
            count += this.mapper.updateRtngdAkDtlSendEtxt(updateDvo);
        }

        return count;
    }

    /**
     * 입고요청품목 삭제
     * @param dvos  (필수) 반품요청품목 데이터 리스트
     * @return 데이터 삭제 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsInStorageAskResDvo removeInStorageAsks(
        List<WsnaLogisticsInStorageAskReqDvo> dvos
    ) {

        // 파라미터 유효성 체크
        ValidAssert.notEmpty(dvos);

        // TB_IFIN_PD_RTNGD_AK_SEND_ETXT - 상품반품요청송신전문 데이터 삭제 건수
        int akCnt = 0;
        // TB_IFIN_RTNGD_AK_DTL_SEND_ETXT - 반품요청상세송신전문 데이터 삭제 건수
        int akDtlCnt = 0;

        for (WsnaLogisticsInStorageAskReqDvo dvo : dvos) {
            // 유효성 체크
            ValidAssert.hasText(dvo.getOstrAkNo());

            // 출고요청상세송신전문 데이터 조회, 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsInStorageAskDtlDvo askDtlDvo = this.mapper
                .selectRtngdAkDtlSendEtxtByOstrAkNoAndOstrAkSn(dvo)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = askDtlDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우 취소 API 호출
            if (YN_Y.equals(trsYn)) {
                // 물류 취소 API 호출
                LogisticsInStorageCancelResIvo resIvo = this.cancelLogisticsInStorage(askDtlDvo);
                // Exception 처리
                if (ObjectUtils.isNotEmpty(resIvo) && !RESULT_CODE_S.equals(resIvo.getResultCode())) {
                    // 이미 물류입고 처리되어 삭제할 수 없습니다.
                    throw new BizException("MSG_ALT_ALRDY_LGST_STR_CANT_DEL");
                }
            }

            // 데이터 삭제처리
            askDtlDvo.setDtaDlYn(YN_Y);
            akDtlCnt += this.mapper.updateRtngdAkDtlSendEtxtForRemove(askDtlDvo);
        }

        // 출고요청번호 필터링
        List<String> ostrNos = dvos.stream().map(WsnaLogisticsInStorageAskReqDvo::getOstrAkNo).distinct()
            .toList();
        for (String ostrNo : ostrNos) {
            // 반품요청상세송신 데이터가 모두 삭제처리 되었는지 체크
            Integer removeCount = this.mapper.selectRtngdAkDtlSendEtxtCount(ostrNo);
            if (ObjectUtils.isEmpty(removeCount)) {
                // 반품요청상세송신 데이터가 모두 삭제된 경우 상품 반품요청송신 데이터 삭제처리
                WsnaLogisticsInStorageAskDvo askDvo = this.mapper.selectPdRtngdAkSendEtxtByRtngdAkNo(ostrNo);
                if (ObjectUtils.isNotEmpty(askDvo)) {
                    // 전송여부 조회
                    String trsYn = this.mapper.selectPdRtngdAkSendTrsYn(askDvo);
                    // 물류에서 이미 전송이 완료된 경우 취소 API 호출
                    if (YN_Y.equals(trsYn)) {
                        WsnaLogisticsInStorageAskDtlDvo askDtlDvo = new WsnaLogisticsInStorageAskDtlDvo();
                        askDtlDvo.setSapPlntCd(askDvo.getSapPlntCd());
                        askDtlDvo.setLgstStrAkNo(askDvo.getLgstStrAkNo());

                        // 물류 취소 API 호출
                        LogisticsInStorageCancelResIvo resIvo = this.cancelLogisticsInStorage(askDtlDvo);
                        // Exception 처리
                        if (ObjectUtils.isNotEmpty(resIvo) && !RESULT_CODE_S.equals(resIvo.getResultCode())) {
                            // 이미 물류입고 처리되어 삭제할 수 없습니다.
                            throw new BizException("MSG_ALT_ALRDY_LGST_STR_CANT_DEL");
                        }
                    }

                    askDvo.setDtaDlYn(YN_Y);
                    akCnt += this.mapper.updatePdRtngdAkSendEtxtForRemove(askDvo);
                }
            }
        }

        WsnaLogisticsInStorageAskResDvo res = new WsnaLogisticsInStorageAskResDvo();
        res.setAkCnt(akCnt);
        res.setAkDtlCnt(akDtlCnt);

        return res;

    }

    /**
     * 물류 입고요청 취소 API 호출
     * @param askDtlDvo (필수)
     * @return 물류 입고요청 취소 Response Interface Dvo
     */
    private LogisticsInStorageCancelResIvo cancelLogisticsInStorage(@NotEmpty
    WsnaLogisticsInStorageAskDtlDvo askDtlDvo
    ) {

        // 사용자 세션
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        LogisticsInStorageCancelReqIvo req = new LogisticsInStorageCancelReqIvo();
        req.setSapPlntCd(askDtlDvo.getSapPlntCd());
        req.setStrAkNo(askDtlDvo.getLgstStrAkNo());
        req.setStrAkSn(askDtlDvo.getStrAkSn());
        // 세션정보 활용
        req.setAkCanUsrId(session.getUserId());
        req.setAkCanDeptId(session.getOgCd());

        return this.interfaceService
            .post(EAI_CBD01006, req, LogisticsInStorageCancelResIvo.class);
    }

}
