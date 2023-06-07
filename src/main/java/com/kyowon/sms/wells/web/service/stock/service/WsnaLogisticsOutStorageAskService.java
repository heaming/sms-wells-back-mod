package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaLogisticsOutStorageAskConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsDeliveryAskDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsOutStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskPcsvDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1005.request.LogisticsOutOfStorageCancelReqIvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1005.response.LogisticsOutOfStorageCancelResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaLogisticsOutStorageAskMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.service.EaiInterfaceService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;

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
@Validated
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
     * @param dtos  (필수) 출고요청품목 리스트
     * @return 출고요청품목 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDto.SaveRes createOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos
    ) {

        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 생성 건수
        int akCnt = 0;
        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 생성 건수
        int akDtlCnt = 0;

        // 출고요청번호 distinct, 출고요청품목으로 데이터를 받기 때문에 출고요청번호(Master) 기준으로 데이터를 필터링 하기 위함.
        List<String> ostrAkNos = dtos.stream().map(WsnaLogisticsOutStorageAskDto.SaveReq::ostrAkNo).distinct()
            .toList();

        for (String ostrAkNo : ostrAkNos) {
            // 출고요청번호로 품목출고요청송신전문 데이터가 존재하는지 체크
            WsnaLogisticsOutStorageAskDvo askDvo = this.mapper.selectItmOstrAkSendEtxtByOstrAkNo(ostrAkNo);

            // 출고요청번호에 해당하는 품목출고 리스트
            List<WsnaLogisticsOutStorageAskDto.SaveReq> itms = dtos.stream()
                .filter(dto -> ostrAkNo.equals(dto.ostrAkNo())).toList();

            if (ObjectUtils.isEmpty(askDvo) && CollectionUtils.isNotEmpty(itms)) {
                // 데이터가 존재하지 않는 경우 품목출고요청송신전문 데이터 생성
                askDvo = this.insertItmOstrAkSendEtxt(itms.get(0));
                akCnt++;
            }
            // 출고요청상세송신전문 데이터 생성
            if (ObjectUtils.isNotEmpty(askDvo)) {
                akDtlCnt += this.insertOstrAkDtlSendEtxts(askDvo, itms);
            }
        }

        return new WsnaLogisticsOutStorageAskDto.SaveRes(akCnt, akDtlCnt);
    }

    /**
     * 품목출고요청송신전문 데이터 생성
     * @param dto   (필수) 출고요청 데이터
     * @return 품목출고요청송신전문 데이터 정보
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDvo insertItmOstrAkSendEtxt(@NotEmpty
    WsnaLogisticsOutStorageAskDto.SaveReq dto) {
        WsnaLogisticsOutStorageAskDvo dvo = this.converter.mapSaveReqToWsnaLogisticsOutStorageAskDvo(dto);

        // 물류출고요청번호 생성
        String lgstOstrAkNo = this.mapper.selectNewLgstOstrAkNo(LGST_OSTR_CD);
        dvo.setLgstOstrAkNo(lgstOstrAkNo);

        // SAP 코드
        dvo.setSapPlntCd(SnServiceConst.SAP_PLNT_CD);
        dvo.setSapCoCd(SnServiceConst.SAP_CO_CD);
        dvo.setSapSaveLctCd(SnServiceConst.SAP_SAVE_LCT_CD);

        // 데이터 생성
        this.mapper.insertItmOstrAkSendEtxt(dvo);

        return dvo;
    }

    /**
     * 출고요청상세송신전문 데이터 생성
     * @param askDvo (필수) 품목출고요청송신전문 데이터
     * @param dtos  (필수) 출고요청상세송신전문 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public int insertOstrAkDtlSendEtxts(@NotEmpty
    WsnaLogisticsOutStorageAskDvo askDvo, @NotEmpty
    List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos
    ) {

        int count = 0;

        List<WsnaLogisticsOutStorageAskDtlDvo> askDtlDvos = this.converter
            .mapAllSaveReqToWsnaLogisticsOutStorageAskDtlDvo(dtos);

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
     * @param dtos  (필수) 출고요청품목 데이터 리스트
     * @return 데이터 수정 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public int editOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos
    ) {

        int count = 0;

        for (WsnaLogisticsOutStorageAskDto.SaveReq dto : dtos) {
            // 출고요청상세송신전문 데이터 조회
            WsnaLogisticsOutStorageAskDto.RemoveReq removeReq = this.converter.mapSaveReqToRemoveReq(dto);
            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper
                .selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(removeReq)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = askDtlDvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우 메시지 처리
            if (YN_Y.equals(trsYn)) {
                // 이미 물류출고 처리되어 변경할 수 없습니다.
                throw new BizException("MSG_ALT_ALRDY_LGST_OSTR_CANT_CHNG");
            }

            WsnaLogisticsOutStorageAskDtlDvo updateDvo = this.converter
                .mapSaveReqToWsnaLogisticsOutStorageAskDtlDvo(dto);
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
     * @param dtos  (필수) 출고요청품목 데이터 리스트
     * @return 데이터 삭제 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDto.SaveRes removeOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.RemoveReq> dtos
    ) {

        // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 삭제 건수
        int akCnt = 0;
        // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 삭제 건수
        int akDtlCnt = 0;

        for (WsnaLogisticsOutStorageAskDto.RemoveReq dto : dtos) {
            // 출고요청상세송신전문 데이터 조회, 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper
                .selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(dto)
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
        List<String> ostrAkNos = dtos.stream().map(WsnaLogisticsOutStorageAskDto.RemoveReq::ostrAkNo).distinct()
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

        return new WsnaLogisticsOutStorageAskDto.SaveRes(akCnt, akDtlCnt);
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
        req.setAkCanDeptId(session.getDepartmentId());

        return this.interfaceService
            .post(EAI_CBD01005, req, LogisticsOutOfStorageCancelResIvo.class);
    }

    /**
     * 물량배정출고요청품목 생성
     * @param dtos  (필수) 물량배정출고요청품목 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDto.CreateQomRes createQomOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.CreateQomReq> dtos
    ) {

        List<WsnaLogisticsOutStorageAskDto.SaveReq> convDtos = this.converter.mapAllCreateQomReqToSaveReq(dtos);

        // 출고요청품목생성 메소드 호출
        WsnaLogisticsOutStorageAskDto.SaveRes akRes = this.createOutOfStorageAsks(convDtos);

        List<WsnaLogisticsDeliveryAskDto.CreateReq> deliveryDtos = this.converter.mapAllCreateQomReqToCreateReq(dtos);

        // 배송요청 인터페이스 호출
        WsnaLogisticsDeliveryAskDto.CreateRes deliveryRes = this.deliveryService
            .createLogisticsDeliveryAsks(deliveryDtos);

        WsnaLogisticsOutStorageAskDto.CreateQomRes res = new WsnaLogisticsOutStorageAskDto.CreateQomRes(
            akRes.akCnt(), akRes.akDtlCnt(), deliveryRes.basCnt(), deliveryRes.pdCnt(), deliveryRes.matCnt()
        );

        return res;
    }

    /**
     * 자가필터/건식상품 출고요청품목 생성
     * @param dtos  (필수) 자가필터/건식상품 출고요청품목 데이터 리스트
     * @return 데이터 생성 건수
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes createSelfFilterOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq> dtos
    ) {

        List<WsnaLogisticsOutStorageAskDto.SaveReq> convDtos = this.converter.mapAllSaveSelfFilterReqToSaveReq(dtos);

        // 출고요청품목생성 메소드 호출
        WsnaLogisticsOutStorageAskDto.SaveRes akRes = this.createOutOfStorageAsks(convDtos);

        int count = 0;

        // 택배 테이블 데이터 생성
        for (WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq dto : dtos) {

            // 출고요청상세송신전문 데이터 조회
            WsnaLogisticsOutStorageAskDto.RemoveReq removeReq = this.converter.mapSaveSelfFilterReqToRemoveReq(dto);

            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper
                .selectOstrAkDtlSendEtxtByOstrAkNoAndOstrAkSn(removeReq)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            WsnaLogisticsOutStorageAskPcsvDvo dvo = this.converter
                .mapSaveSelfFilterReqToWsnaLogisticsOutStorageAskPcsvDvo(dto);
            dvo.setSapPlntCd(askDtlDvo.getSapPlntCd());
            dvo.setOstrAkNo(askDtlDvo.getLgstOstrAkNo());
            dvo.setOstrAkSn(askDtlDvo.getOstrAkSn());

            count += this.mapper.insertOstrAkPcsvSendEtxt(dvo);
        }

        WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes res = new WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes(
            akRes.akCnt(), akRes.akDtlCnt(), count
        );

        return res;
    }

    /**
     * 자가필터/건식상품 출고요청품목 수정
     * @param dtos  (필수) 자가필터/건식상품 출고요청품목 데이터 리스트
     * @return 데이터 수정 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes editSelfFilterOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq> dtos
    ) {

        int count = 0;

        // 택배 테이블 데이터 수정
        for (WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq dto : dtos) {

            WsnaLogisticsOutStorageAskDto.RemoveReq removeReq = this.converter.mapSaveSelfFilterReqToRemoveReq(dto);

            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskPcsvDvo dvo = this.mapper
                .selectOstrAkPcsvSendEtxtByOstrAkNoAndOstrAkSn(removeReq)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = dvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우
            if (YN_Y.equals(trsYn)) {
                // 이미 물류출고 처리되어 변경할 수 없습니다.
                throw new BizException("MSG_ALT_ALRDY_LGST_OSTR_CANT_CHNG");
            }

            // 데이터 수정
            WsnaLogisticsOutStorageAskPcsvDvo updateDvo = this.converter
                .mapSaveSelfFilterReqToWsnaLogisticsOutStorageAskPcsvDvo(dto);

            // pk셋팅
            updateDvo.setSapPlntCd(dvo.getSapPlntCd());
            updateDvo.setOstrAkNo(dvo.getOstrAkNo());
            updateDvo.setOstrAkSn(dvo.getOstrAkSn());

            count += this.mapper.updateOstrAkPcsvSendEtxt(updateDvo);
        }

        // 출고요청품목수정 메소드 호출
        List<WsnaLogisticsOutStorageAskDto.SaveReq> convDtos = this.converter.mapAllSaveSelfFilterReqToSaveReq(dtos);
        int akDtlCnt = this.editOutOfStorageAsks(convDtos);

        WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes res = new WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes(
            0, akDtlCnt, count
        );

        return res;
    }

    /**
     * 자가필터/건식상품 출고요청품목 삭제
     * @param dtos  (필수) 자가필터/건식상품 출고요청품목 데이터 리스트
     * @return 데이터 삭제 건수
     * @throws 물류 처리가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes removeSelfFilterOutOfStorageAsks(
        @Valid
        @NotEmpty
        List<WsnaLogisticsOutStorageAskDto.RemoveReq> dtos
    ) {

        int count = 0;

        // 택배 테이블 데이터 삭제
        for (WsnaLogisticsOutStorageAskDto.RemoveReq dto : dtos) {
            // 데이터가 존재하지 않을 경우, 데이터가 존재하지 않습니다. 메시지 출력
            WsnaLogisticsOutStorageAskPcsvDvo dvo = this.mapper
                .selectOstrAkPcsvSendEtxtByOstrAkNoAndOstrAkSn(dto)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

            // 전송여부 체크
            String trsYn = dvo.getTrsYn();
            // 물류에서 이미 전송이 완료된 경우
            if (YN_Y.equals(trsYn)) {
                // 이미 물류출고 처리되어 삭제할 수 없습니다.
                throw new BizException("MSG_ALT_ALRDY_LGST_OSTR_CANT_DEL");
            }

            // 데이터 삭제처리
            dvo.setDtaDlYn(YN_Y);
            count += this.mapper.updateOstrAkPcsvSendEtxtForRemove(dvo);
        }

        // 출고요청품목삭제 메소드 호출
        WsnaLogisticsOutStorageAskDto.SaveRes akRes = this.removeOutOfStorageAsks(dtos);

        WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes res = new WsnaLogisticsOutStorageAskDto.SaveSelfFilterRes(
            akRes.akCnt(), akRes.akDtlCnt(), count
        );

        return res;
    }

}
