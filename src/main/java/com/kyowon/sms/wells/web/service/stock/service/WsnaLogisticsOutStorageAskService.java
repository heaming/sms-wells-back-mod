package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaLogisticsOutStorageAskConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsOutStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaLogisticsOutStorageAskMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 관련 서비스
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

    private static final String YN_Y = "Y";

    private static final String LGST_OSTR_CD = "ORWE";

    /**
     * SAP 관련 코드
     */
    // (주)교원프라퍼티
    private static final String SAP_CO_CD = "2000";
    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";

    /**
     * 출고요청품목 생성
     * @param dtos  (필수) 출고요청품목 리스트
     * @return 출고요청품목 데이터 생성 건수
     */
    @Validated
    @Transactional
    public int createOutOfStorageAsks(@Valid
    List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos) {

        int cnt = 0;

        if (CollectionUtils.isNotEmpty(dtos)) {

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
                }
                // 출고요청상세송신전문 데이터 생성
                if (ObjectUtils.isNotEmpty(askDvo)) {
                    cnt += this.insertOstrAkDtlSendEtxt(askDvo, itms);
                }
            }
        }

        return cnt;
    }

    /**
     * 품목출고요청송신전문 데이터 생성
     * @param dto   (필수) 출고요청 데이터
     * @return 품목출고요청송신전문 데이터 정보
     */
    @Transactional
    private WsnaLogisticsOutStorageAskDvo insertItmOstrAkSendEtxt(WsnaLogisticsOutStorageAskDto.SaveReq dto) {
        WsnaLogisticsOutStorageAskDvo dvo = this.converter.mapSaveReqToWsnaLogisticsOutStorageAskDvo(dto);

        // 물류출고요청번호 생성
        String lgstOstrAkNo = this.mapper.selectNewLgstOstrAkNo(LGST_OSTR_CD);
        dvo.setLgstOstrAkNo(lgstOstrAkNo);

        // SAP 코드
        dvo.setSapPlntCd(SAP_PLNT_CD);
        dvo.setSapCoCd(SAP_CO_CD);
        dvo.setSapSaveLctCd(SAP_SAVE_LCT_CD);

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
    private int insertOstrAkDtlSendEtxt(
        WsnaLogisticsOutStorageAskDvo askDvo, List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos
    ) {

        int cnt = 0;

        if (ObjectUtils.isNotEmpty(askDvo) && CollectionUtils.isNotEmpty(dtos)) {
            List<WsnaLogisticsOutStorageAskDtlDvo> askDtlDvos = this.converter
                .mapAllSaveReqToWsnaLogisticsOutStorageAskDtlDvo(dtos);

            // 품목출고요청송신전문 PK 셋팅, 데이터 생성
            for (WsnaLogisticsOutStorageAskDtlDvo askDtlDvo : askDtlDvos) {
                askDtlDvo.setSapPlntCd(askDvo.getSapPlntCd());
                askDtlDvo.setLgstOstrAkNo(askDvo.getLgstOstrAkNo());
                askDtlDvo.setRelNo(askDvo.getOstrAkNo());
                askDtlDvo.setRelSn(askDtlDvo.getOstrAkSn());

                cnt += this.mapper.insertOstrAkDtlSendEtxt(askDtlDvo);
            }
        }

        return cnt;
    }

    /**
     * 출고요청품목 수정
     * @param dtos  (필수) 출고요청품목 데이터 리스트
     * @return 데이터 수정 건수
     * @throws 물류 출고가 완료된 경우 BizExcpeiton 처리
     */
    @Transactional
    public int editOutOfStorageAsk(List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos) {

        int cnt = 0;

        if (CollectionUtils.isNotEmpty(dtos)) {
            for (WsnaLogisticsOutStorageAskDto.SaveReq dto : dtos) {
                // 출고요청상세송신전문 데이터 조회
                WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper.selectOstrAkDtlSendEtxtByRelNoAndRelSn(dto);
                if (ObjectUtils.isNotEmpty(askDtlDvo)) {
                    // 전송여부 체크
                    String trsYn = askDtlDvo.getTrsYn();
                    // 물류에서 이미 전송이 완료된 경우 메시지 처리
                    if (YN_Y.equals(trsYn)) {
                        // 이미 물류출고 처리되어 변경할 수 없습니다.
                        throw new BizException("MSG_ALT_ALRDY_LGST_PROC_CANT_CHNG");
                    }

                    WsnaLogisticsOutStorageAskDtlDvo updateDvo = this.converter
                        .mapSaveReqToWsnaLogisticsOutStorageAskDtlDvo(dto);
                    // PK 셋팅
                    updateDvo.setSapPlntCd(askDtlDvo.getSapPlntCd());
                    updateDvo.setLgstOstrAkNo(askDtlDvo.getLgstOstrAkNo());
                    updateDvo.setOstrAkSn(askDtlDvo.getOstrAkSn());

                    // 출고요청상세송신전문 데이터 변경
                    cnt += this.mapper.updateOstrAkDtlSendEtxt(updateDvo);
                }
            }
        }

        return cnt;
    }

    /**
     * 출고요청품목 삭제
     * @param dtos  (필수) 출고요청품목 데이터 리스트
     * @return 데이터 삭제 건수
     */
    @Transactional
    public int removeOutOfStorageAsk(List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos) {

        int cnt = 0;

        if (CollectionUtils.isNotEmpty(dtos)) {
            for (WsnaLogisticsOutStorageAskDto.SaveReq dto : dtos) {
                // 출고요청상세송신전문 데이터 조회
                WsnaLogisticsOutStorageAskDtlDvo askDtlDvo = this.mapper.selectOstrAkDtlSendEtxtByRelNoAndRelSn(dto);
                if (ObjectUtils.isNotEmpty(askDtlDvo)) {
                    // 전송여부 체크
                    String trsYn = askDtlDvo.getTrsYn();
                    // 물류에서 이미 전송이 완료된 경우 취소 API 호출
                    if (YN_Y.equals(trsYn)) {
                        // TODO: 물류 취소 API 호출 및 Exception 처리 추가.
                    }

                    // 데이터 삭제처리
                    askDtlDvo.setDtaDlYn(YN_Y);
                    cnt += this.mapper.updateOstrAkDtlSendEtxtForRemove(askDtlDvo);
                }
            }

            // 출고요청번호 필터링
            List<String> ostrAkNos = dtos.stream().map(WsnaLogisticsOutStorageAskDto.SaveReq::ostrAkNo).distinct()
                .toList();
            for (String ostrAkNo : ostrAkNos) {
                // 품목출고요청상세송신 데이터가 모두 삭제처리 되었는지 체크
                Integer removeCount = this.mapper.selectOstrAkDtlSendEtxtCount(ostrAkNo);
                if (ObjectUtils.isEmpty(removeCount)) {
                    // 품목출고요청상세송신 데이터가 모두 삭제된 경우 품목 출고요청송신 데이터 삭제처리
                    WsnaLogisticsOutStorageAskDvo askDvo = this.mapper.selectItmOstrAkSendEtxtByOstrAkNo(ostrAkNo);
                    if (ObjectUtils.isNotEmpty(askDvo)) {
                        askDvo.setDtaDlYn(YN_Y);
                        this.mapper.updateItmOstrAkSendEtxtForRemove(askDvo);
                    }
                }
            }
        }

        return cnt;
    }
}
