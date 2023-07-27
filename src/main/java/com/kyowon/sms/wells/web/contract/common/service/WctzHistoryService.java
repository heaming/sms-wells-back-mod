package com.kyowon.sms.wells.web.contract.common.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.contract.zcommon.constants.CtContractConst;
import com.kyowon.sms.wells.web.contract.common.converter.WctzHistoryConverter;
import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzHistoryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzHistoryService {
    private final WctzHistoryMapper mapper;
    private final WctzHistoryConverter converter;

    /**
     * 계약상세변경이력(최신) 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return 계약상세변경이력(최신)
     */
    public WctzCntrDetailChangeHistDvo getContractDetailChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectCntrDetailChangeHist(cntrNo, cntrSn);
    }

    /**
     * 계약상세변경이력 생성
     * @param dvo 이력정보
     */
    public void createContractDetailChangeHistory(WctzCntrDetailChangeHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzCntrDetailChangeHistDvo newHist = converter.convertCntrDetailToChangeHist(
            dvo,
            mapper.selectCntrDetailForHist(dvo.getCntrNo(), dvo.getCntrSn())
        );

        WctzCntrDetailChangeHistDvo befHist = getContractDetailChangeHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrDetailChangeHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrDetailChangeHist(newHist);
    }

    /**
     * 계약상세상태변경이력(최신) 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return 계약상세상태변경이력(최신)
     */
    public WctzCntrDtlStatChangeHistDvo getContractDetailStatChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectCntrDetailStatChangeHist(cntrNo, cntrSn);
    }

    /**
     * 계약상세상태변경이력 생성
     * @param dvo 이력정보 (코드, 사유코드, 메모내용)
     */
    public void createContractDetailStatChangeHistory(WctzCntrDtlStatChangeHistDvo dvo) {
        BizAssert.hasText(dvo.getCntrNo(), "MSG_ALT_CHK_CNTR_NO");
        BizAssert.hasText(dvo.getCntrDtlStatCd(), "MSG_ALT_CHK_CNTR_DTL_STAT_CD");
        if (0 == dvo.getCntrSn()) {
            throw new BizException("MSG_ALT_CHK_CNTR_SN");
        }

        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }
        WctzCntrDtlStatChangeHistDvo befHist = getContractDetailStatChangeHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrDetailStatChangeHist(befHist);
        }
        dvo.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrDetailStatChangeHist(dvo);
    }

    /**
    * 세금계산서접수기준변경이력 생성
    * @param dvo 계약정보 (계약번호, 계약일련번호)
    */
    public void createTaxInvoiceReceiptBaseChangeHistory(WctzTxinvRcpBaseChangeHistDvo dvo) {
        BizAssert.hasText(dvo.getCntrNo(), "MSG_ALT_CHK_CNTR_NO");
        if (0 == dvo.getCntrSn()) {
            throw new BizException("MSG_ALT_CHK_CNTR_SN");
        }

        WctzTxinvRcpBaseChangeHistDvo newHist = converter
            .convertTaxInvoiceReceiptBaseToChangeHist(
                dvo, mapper.selectTaxInvoiceReceiptBase(dvo.getCntrNo(), dvo.getCntrSn())
            );
        mapper.insertTaxInvoiceReceiptBaseHist(newHist);
    }

    /**
     * 계약변경접수변경이력(최신) 조회
     * @param cntrChRcpId 계약변경접수ID
     * @return 계약변경접수변경이력(최신)
     */
    public WctzCntrChRcchStatChangeHistDvo getContractChangeRcchStatChangeHistory(String cntrChRcpId) {
        return mapper.selectCntrChRcchStatChangeHist(cntrChRcpId);
    }

    /**
     * 계약변경접수변경이력 생성
     * @param dvo 이력정보 (계약변경요청내용, 계약변경진행상태코드, 계약변경진행메모내용)
     */
    public void createContractChangeRcchStatChangeHistory(WctzCntrChRcchStatChangeHistDvo dvo) {
        BizAssert.hasText(dvo.getCntrChRcpId(), "MSG_ALT_CHK_CNTR_CH_RCP_ID");
        BizAssert.hasText(dvo.getCntrChPrgsStatCd(), "MSG_ALT_CHK_CNTR_CH_PRGS_STAT_CD");

        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }
        WctzCntrChRcchStatChangeHistDvo befHist = getContractChangeRcchStatChangeHistory(dvo.getCntrChRcpId());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrChRcchStatChangeHist(befHist);
        }
        dvo.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrChRcchStatChangeHist(dvo);
    }

    /**
     * 계약기본변경이력(최신) 조회
     * @param cntrNo 계약번호
     * @return 계약기본변경이력(최신)
     */
    public WctzCntrBasicChangeHistDvo getContractBasicChangeHistory(String cntrNo) {
        return mapper.selectCntrBasicChangeHistory(cntrNo);
    }

    /**
     * 계약기본변경이력 생성
     * @param dvo 이력정보
     */
    public void createContractBasicChangeHistory(WctzCntrBasicChangeHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzCntrBasicChangeHistDvo newHist = converter.convertCntrBasicToChangeHist(
            dvo,
            mapper.selectCntrBasicForHist(dvo.getCntrNo())
        );

        WctzCntrBasicChangeHistDvo befHist = getContractBasicChangeHistory(dvo.getCntrNo());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now); /* 위에서 넣는데 최초일 때는 강제하는 의미인가요? */
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrBasicChangeHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrBasicChangeHist(newHist);
    }

    public void expireContractBasicChangeHistory(String cntrNo) {
        String now = DateUtil.todayNnow();
        WctzCntrBasicChangeHistDvo befHist = getContractBasicChangeHistory(cntrNo);
        befHist.setHistEndDtm(now);
        int result = mapper.updateCntrBasicChangeHist(befHist);
        BizAssert.isTrue(result == 1, "계약 변경 이력 생성 실패");
    }

    /**
    * 관계사제휴계약변경이력(최신) 조회
    * @param acmpalCntrId 관계사제휴계약ID
    * @return 관계사제휴계약변경이력(최신)
    */
    public WctzAcmpalContractIzHistDvo getAcmpalContractIzChangeHistory(String acmpalCntrId) {
        return mapper.selectAcmpalContractIzHist(acmpalCntrId);
    }

    /**
     * 관계사제휴계약변경이력 생성
     * @param dvo 이력정보
     */
    public void createAcmpalContractIzChangeHistory(WctzAcmpalContractIzHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzAcmpalContractIzHistDvo newHist = converter.convertAcmpalCntrIzToChangeHist(
            dvo,
            mapper.selectAcmpalContractIzForHist(dvo.getAcmpalCntrId())
        );

        WctzAcmpalContractIzHistDvo befHist = getAcmpalContractIzChangeHistory(dvo.getAcmpalCntrId());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateAcmpalContractIzChangeHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertAcmpalContractIzChangeHist(newHist);
    }

    /**
    * 계약WELLS상세(최신) 조회
    * @param cntrNo 계약번호
    * @param cntrSn 계약일련번호
    * @return 계약WELLS상세(최신)
    */
    public WctzContractWellsDetailHistDvo getContractWellsDetailChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectContractWellsDetailHist(cntrNo, cntrSn);
    }

    /**
     * 계약WELLS상세이력 생성
     * @param dvo 이력정보
     */
    public void createContractWellsDetailChangeHistory(WctzContractWellsDetailHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzContractWellsDetailHistDvo newHist = converter.convertCntrWellsDetailToChangeHist(
            dvo,
            mapper.selectContractWellsDetailForHist(dvo.getCntrNo(), dvo.getCntrSn())
        );

        WctzContractWellsDetailHistDvo befHist = getContractWellsDetailChangeHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateContractWellsDetailHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertContractWellsDetailHist(newHist);
    }

    /**
     * 계약가격산출변경이력 조회
     *
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return dvo
     */
    public WctzCntrPrccchHistDvo getCntrPrccchHistory(String cntrNo, int cntrSn) {
        return mapper.selectCntrPrccchHistory(cntrNo, cntrSn);
    }

    /**
     * 계약가격산출변경이력 생성
     *
     * @param dvo 이력 객체
     */
    @Transactional
    public void createCntrPrccchHistory(WctzCntrPrccchHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzCntrPrccchHistDvo newHist = converter.convertPrcCmptToHist(
            dvo,
            mapper.selectCntrPrcCmptForHist(dvo.getCntrNo(), dvo.getCntrSn())
        );

        WctzCntrPrccchHistDvo befHist = getCntrPrccchHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrPrccchHistory(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrPrccchHistory(newHist);
    }

    /**
     * 기기변경이력 조회
     *
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return dvo
     */
    public WctzMachineChangeHistoryDvo getMachineChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectMachineChangeHistory(cntrNo, cntrSn);
    }

    /**
     * 기기변경이력 생성
     *
     * @param dvo 이력 객체
     */
    @Transactional
    public void createMachineChangeHistory(WctzMachineChangeHistoryDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzMachineChangeHistoryDvo newHist = converter.convertMachineHistoryToHist(
            dvo,
            mapper.selectMachineChangeForHist(dvo.getBaseCntrNo(), dvo.getBaseCntrSn())
        );

        WctzMachineChangeHistoryDvo befHist = getMachineChangeHistory(dvo.getBaseCntrNo(), dvo.getBaseCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateMachineChangeHistory(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertMachineChangeHistory(newHist);
    }

    /**
    * 계약변경접수상세변경이력 조회
    *
    * @param cntrChRcpId 계약변경접수ID
    * @param cntrChSn 계약변경일련번호
    * @return dvo
    */
    public WctzContractChRcchStatChangeDtlHistDvo getContractChRcchChangeDtlHistory(String cntrChRcpId, int cntrChSn) {
        return mapper.selectContractChRcchStatChangeDtlHistory(cntrChRcpId, cntrChSn);
    }

    /**
    * 계약변경접수상세변경이력
    *
    * @param dvo 이력 객체
    * */
    @Transactional
    public void createContractChRcchChangeDtlHistory(WctzContractChRcchStatChangeDtlHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzContractChRcchStatChangeDtlHistDvo newHist = converter.convertContractChangeReceiptDtlToHist(
            dvo,
            mapper.selectContractChRcchStatChangeDtlForHist(dvo.getCntrChRcpId(), dvo.getCntrChSn())
        );

        WctzContractChRcchStatChangeDtlHistDvo befHist = getContractChRcchChangeDtlHistory(
            dvo.getCntrChRcpId(), dvo.getCntrChSn()
        );
        if (ObjectUtils.isEmpty(befHist)) {
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateContractChRcchStatChangeDtlHistory(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertContractChRcchStatChangeDtlHistory(newHist);
    }

    /**
    * 계약주소변경이력 조회
    *
    * @param cntrAdrpcId 계약주소지 ID
    * @return dvo
    */
    public WctzContractAddrChangeHistDvo getContractAddrChangeHistory(String cntrAdrpcId) {
        return mapper.selectContractAddrChangeForHist(cntrAdrpcId);
    }

    /**
    * 계약주소변경이력
    *
    * @param dvo 이력 객체
    * */
    @Transactional
    public void createContractAddrChangeHistory(WctzContractAddrChangeHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzContractAddrChangeHistDvo newHist = converter.convertContractAddrChangeToHist(
            dvo,
            mapper.selectContractAddrChangeHistory(dvo.getCntrAdrpcId())
        );

        WctzContractAddrChangeHistDvo befHist = getContractAddrChangeHistory(dvo.getCntrAdrpcId());
        if (ObjectUtils.isEmpty(befHist)) {
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateContractAddrChangeHistory(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertContractAddrChangeHistory(newHist);
    }

    /**
     * 가망고객기본변경이력 생성
     */
    @Transactional
    public void createPspcCstChHistory(String pspcCstId) {
        createPspcCstChHistory(pspcCstId, false);
    }

    @Transactional
    public void createPspcCstChHistory(String pspcCstId, boolean forced) {
        String now = DateUtil.todayNnow();

        mapper.selectLastPspcCstCnslChHist(pspcCstId).ifPresent((lastHist) -> {
            if (now.equals(lastHist.getHistStrtDtm())) {
                if (!forced) {
                    throw new BizException("이력 생성 실패");
                }
            } else {
                int result = mapper.expirePspcCstChHistory(pspcCstId, now);
                BizAssert.isTrue(result == 1, "이력 생성 실패");
            }
        });

        int result = mapper.upsertPspcCstChHist(pspcCstId, now);
        BizAssert.isTrue(result == 1, "이력 생성 실패");
    }

    /**
     * 가망고객상담기본변경이력 생성
     */
    @Transactional
    public void createPspcCstCnslChHistory(String pspcCstCnslId) {
        createPspcCstCnslChHistory(pspcCstCnslId, false);
    }

    @Transactional
    public void createPspcCstCnslChHistory(String pspcCstCnslId, boolean forced) {
        String now = DateUtil.todayNnow();

        mapper.selectLastPspcCstCnslChHist(pspcCstCnslId).ifPresent((lastHist) -> {
            if (now.equals(lastHist.getHistStrtDtm())) {
                if (!forced) {
                    throw new BizException("이력 생성 실패");
                }
            } else {
                int result = mapper.expirePspcCstCnslChHistory(pspcCstCnslId, now);
                BizAssert.isTrue(result == 1, "이력 생성 실패");
            }
        });

        /* 기존 데이터가 있고, 시간이 다르면 expired, 아니면 upsert */
        int result = mapper.upsertPspcCstCnslChHist(pspcCstCnslId, now);
        BizAssert.isTrue(result == 1, "이력 생성 실패");
    }

    /**
     * 가망고객상담추천내역변경이력 생성
     */
    @Transactional
    public void createPspcCstCnslRchHistory(String pspcCstCnslId, int pspcCstCnslSn) {
        createPspcCstCnslRchHistory(pspcCstCnslId, pspcCstCnslSn, false);
    }

    @Transactional
    public void createPspcCstCnslRchHistory(String pspcCstCnslId, int pspcCstCnslSn, boolean forced) {
        String now = DateUtil.todayNnow();

        mapper.selectLastPspcCstCnslRchHist(pspcCstCnslId, pspcCstCnslSn).ifPresent((lastHist) -> {
            if (now.equals(lastHist.getHistStrtDtm())) {
                if (!forced) {
                    throw new BizException("이력 생성 실패");
                }
            } else {
                int result = mapper.expirePspcCstCnslRchHistory(pspcCstCnslId, pspcCstCnslSn, now);
                BizAssert.isTrue(result == 1, "이력 생성 실패");
            }
        });

        int result = mapper.upsertPspcCstCnslRchHist(pspcCstCnslId, pspcCstCnslSn, now);
        BizAssert.isTrue(result == 1, "이력 생성 실패");
    }

    /**
     * 계약알림발송이력 생성
     * @param dvo 계약알림발송정보
     * @param isEdit 신규추가/수정 구분
     */

    @Transactional
    public String createContractNotifyFowrdindHistory(WctzContractNotifyFowrdindHistDvo dvo, boolean isEdit) {
        if (!isEdit) {
            int insertRes = mapper.insertContractNotifyFowrdindHist(dvo);
            BizAssert.isTrue(insertRes == 1, "이력 생성 실패");

            return dvo.getNotyFwId();
        } else {
            int updateRes = mapper.updateContractNotifyFowrdindHist(dvo);
            BizAssert.isTrue(updateRes == 1, "이력 생성 실패");

            return "Y";
        }
    }
}
