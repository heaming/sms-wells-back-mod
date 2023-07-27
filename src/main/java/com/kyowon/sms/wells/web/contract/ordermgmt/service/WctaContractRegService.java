package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.*;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegService {
    private final WctaContractRegMapper mapper;
    private final WctaContractRegStep1Mapper step1Mapper;
    private final WctaContractRegStep2Mapper step2Mapper;
    private final WctaContractRegStep3Mapper step3Mapper;
    private final WctaContractRegStep4Mapper step4Mapper;

    public String selectEnsmCstNo(String ensmNo) {
        return mapper.selectEnsmCstNo(ensmNo);
    }

    public String selectPrtnrCstNo(String prtnrNo) {
        return mapper.selectPrtnrCstNo(prtnrNo);
    }

    public String selectCstPrtnrNo(String cstNo) {
        return mapper.selectCstPrtnrNo(cstNo);
    }

    public boolean isNewCntr(String curCntrPrgsStatCd, String cntrPrgsStatCd) {
        return Integer.valueOf(curCntrPrgsStatCd) < Integer.valueOf(cntrPrgsStatCd);
    }

    public String getBryyMmdd(String cstNo) {
        return mapper.selectBryyMmdd(cstNo);
    }

    public String getCntrwTpCd(String sellTpCd, String sellTpDtlCd, String cntrRelDtlCd, String mchnSellTpCd) {
        if (sellTpDtlCd.equals("13")) {
            return "1";
        }
        if (sellTpCd.equals(CtContractConst.SELL_TP_CD_SPAY)) {
            return "2";
        } else if (sellTpCd.equals(CtContractConst.SELL_TP_CD_RNTL)) {
            return "3";
        } else if (sellTpCd.equals(CtContractConst.SELL_TP_CD_MSH)) {
            if (sellTpDtlCd.equals("33")) {
                return "5";
            } else {
                return "4";
            }
        } else if (sellTpCd.equals(CtContractConst.SELL_TP_CD_RGSP)) {
            if (StringUtils.isEmpty(cntrRelDtlCd) || cntrRelDtlCd.equals("214")) {
                return "7";
            } else if (cntrRelDtlCd.equals("216")) {
                if (mchnSellTpCd.equals(CtContractConst.SELL_TP_CD_SPAY)) {
                    return "1";
                } else if (mchnSellTpCd.equals(CtContractConst.SELL_TP_CD_RNTL)) {
                    return "2";
                }
            }
        }
        return "";
    }

    public String getRveDvCd(String sellTpCd) {
        return switch (sellTpCd) {
            case "3" -> "04";
            case "6" -> "05";
            default -> "03";
        };
    }

    public List<WctaContractDtlDvo> selectProductInfos(String cntrNo) {
        return step2Mapper.selectContractDtlWithPdInfo(cntrNo);
    }

    @Transactional
    public int updateCntrPrgsStatCd(String cntrNo, String cntrPrgsStatCd) {
        return mapper.updateCntrPrgsStatCd(cntrNo, cntrPrgsStatCd);
    }

    public WctaContractCstRelDvo selectCntrtInfoByCstNo(String cstNo) {
        return step1Mapper.selectCntrtInfoByCstNo(cstNo);
    }

    public WctaContractBasDvo selectContractBas(String cntrNo) {
        return mapper.selectContractBas(cntrNo);
    };

    public List<WctaContractDtlDvo> selectContractDtl(String cntrNo) {
        return mapper.selectContractDtl(cntrNo);
    };

    public List<WctaContractCstRelDvo> selectContractCstRel(String cntrNo) {
        return mapper.selectContractCstRel(cntrNo);
    };

    public List<WctaContractPrtnrRelDvo> selectContractPrtnrRel(String cntrNo) {
        return mapper.selectContractPrtnrRel(cntrNo);
    }

    public List<WctaContractPrcCmptIzDvo> selectContractPrcCmptIz(String cntrNo, int cntrSn) {
        return mapper.selectContractPrcCmptIz(cntrNo, cntrSn);
    }

    public List<WctaContractPdRelDvo> selectContractPdRel(String cntrNo, int cntrSn) {
        return mapper.selectContractPdRel(cntrNo, cntrSn);
    }

    public WctaContractWellsDtlDvo selectContractWellsDtl(String cntrNo, int cntrSn) {
        return mapper.selectContractWellsDtl(cntrNo, cntrSn);
    }

    public List<WctaContractRelDvo> selectContractRel(String cntrNo) {
        return mapper.selectContractRel(cntrNo);
    }

    public List<WctaMachineChangeIzDvo> selectMachineChangeIz(String cntrNo) {
        return mapper.selectMachineChangeIz(cntrNo);
    }

    public List<WctaContractAdrpcBasDvo> selectContractAdrpcBas(String cntrNo) {
        return mapper.selectContractAdrpcBas(cntrNo);
    };

    public WctaContractAdrRelDvo selectContractAdrRel(String cntrNo, int cntrSn) {
        return mapper.selectContractAdrRel(cntrNo, cntrSn);
    };

    public WctaContractStlmBasDvo selectContractStlmBas(String cntrNo, int cntrSn) {
        return mapper.selectContractStlmBas(cntrNo, cntrSn);
    }

    public List<WctaContractStlmRelDvo> selectContractStlmRels(String cntrNo, int cntrSn) {
        return mapper.selectContractStlmRels(cntrNo, cntrSn);
    }

    @Transactional
    public void removeStep1Data(String cntrNo) {
        step1Mapper.deleteCntrPrtnrRelStep1(cntrNo);
        step1Mapper.deleteCntrCstRelStep1(cntrNo);
        step1Mapper.deleteCntrRelStep1(cntrNo);
    }

    @Transactional
    public void removeStep2Data(String cntrNo) {
        step2Mapper.deleteCntrRelStep2(cntrNo);
        step2Mapper.deleteMchnChIzStep2(cntrNo);
        step2Mapper.deleteCntrDtlStep2(cntrNo);
        step2Mapper.deleteContractDetailHistory(cntrNo);
        step2Mapper.deleteCntrHsmtrlDtlStep2(cntrNo);
        step2Mapper.deleteCntrHsmtrDchHistory(cntrNo);
        step2Mapper.deleteCntrPdRelStep2(cntrNo);
        step2Mapper.deleteCntrPmotIzStep2(cntrNo);
        step2Mapper.deleteFgptRcpIzStep2(cntrNo);
        step2Mapper.deleteCntrPrcCmptIzStep2(cntrNo);
        step2Mapper.deleteCntrPrccchHistory(cntrNo);
        step2Mapper.deleteCntrWellsDtlStep2(cntrNo);
    }

    @Transactional
    public void removeStep3Data(String cntrNo) {
        step3Mapper.deleteCntrStlmBasStep3(cntrNo);
        step3Mapper.deleteCntrStlmRelsStep3(cntrNo);
        step3Mapper.deleteCntrAdrpcBasStep3(cntrNo);
        step3Mapper.deleteCntrAdrRelsStep3(cntrNo);
    }

    @Transactional
    public void removeStep4Data(String cntrNo) {
        step4Mapper.updateCntrBasStep4(cntrNo, "");
    }

    public WctaContractRegDvo selectCntrSmr(String cntrNo) {
        WctaContractRegDvo dvo = new WctaContractRegDvo();
        dvo.setCntrNo(cntrNo);

        WctaContractRegStep1Dvo step1Dvo = new WctaContractRegStep1Dvo();
        WctaContractRegStep2Dvo step2Dvo = new WctaContractRegStep2Dvo();
        WctaContractRegStep3Dvo step3Dvo = new WctaContractRegStep3Dvo();
        WctaContractRegStep4Dvo step4Dvo = new WctaContractRegStep4Dvo();

        step1Dvo.setBas(selectContractBas(cntrNo));
        List<WctaContractCstRelDvo> cstRels = selectContractCstRel(cntrNo);
        step1Dvo.setCntrt(
            selectCntrtInfoByCstNo(
                cstRels.stream().filter((v) -> "10".equals(v.getCntrCstRelTpCd())).findFirst()
                    .get().getCstNo()
            )
        );
        step1Dvo.setLrnrCstNo(
            cstRels.stream().filter((v) -> "20".equals(v.getCntrCstRelTpCd())).findFirst()
                .orElse(new WctaContractCstRelDvo()).getCstNo()
        );
        List<WctaContractDtlDvo> dtls = selectProductInfos(cntrNo);
        if (CollectionUtils.isNotEmpty(dtls)) {
            step2Dvo.setDtls(dtls);
            dvo.setStep2(step2Dvo);
            // 결제관계 전체 리스트에서 수납코드구분 01일 때 0101이나 0201이 하나라도 존재하는 경우 해당 데이터 선택
            List<WctaContractStlmRelDvo> stlmRels = Lists.newArrayList();
            WctaContractDtlDvo fDtl = dtls.get(0);
            step3Dvo.setStlmTpCd(fDtl.getStlmTpCd());
            dtls.forEach(
                (dtl) -> {
                    // 금액: 계약결제관계 세팅
                    List<WctaContractStlmRelDvo> stlms = selectContractStlmRels(dtl.getCntrNo(), dtl.getCntrSn());
                    stlmRels.addAll(stlms.stream().filter((stlm) -> "01".equals(stlm.getRveDvCd())).toList());
                }
            );
            if (CollectionUtils.isNotEmpty(stlmRels)
                || step1Dvo.getBas().getCntrTpCd().equals(CtContractConst.CNTR_TP_CD_MSH)) {
                // 계약금/일시금 결제방법 카드/가상계좌 중 하나 찾으면 해당 값 세팅(무조건 통일이므로)
                // 멤버십의 경우 계약금/등록금이 없기 때문에 stlmRels가 없어도 생성
                step3Dvo.setCntramDpTpCd(
                    stlmRels.stream().filter((stlm) -> StringUtils.containsAny(stlm.getDpTpCd(), "0201", "0101"))
                        .findFirst().orElse(
                            WctaContractStlmRelDvo.builder()
                                .dpTpCd("")
                                .build()
                        ).getDpTpCd()
                );
            }
        }
        dvo.setStep1(step1Dvo);
        dvo.setStep2(step2Dvo);
        dvo.setStep3(step3Dvo);
        dvo.setStep4(step4Dvo);
        return dvo;
    }
}
