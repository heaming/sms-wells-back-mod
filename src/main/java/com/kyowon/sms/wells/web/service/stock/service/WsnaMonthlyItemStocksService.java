package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMonthlyItemStocksConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyItemStocksDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMonthlyItemStocksMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0086 월별 품목재고내역 관리
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.04.05
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMonthlyItemStocksService {
    private final WsnaMonthlyItemStocksMapper mapper;

    private final WsnaMonthlyItemStocksConverter converter;

    /*월별품목재고내역 등록*/
    public int saveMonthlyStock(WsnaMonthlyItemStocksDto.SaveReq dto) throws ParseException {
        WsnaMonthlyItemStocksDvo dvo = new WsnaMonthlyItemStocksDvo();
        int processCount = 0;

        int strPitmStocAGdQty = 0; //시점재고A등급수량
        int strPitmStocBGdQty = 0; //시점재고B등급수량
        int strPitmStocEGdQty = 0; //시점재고E등급수량
        int strPitmStocRGdQty = 0; //시점재고R등급수량
        String strBaseYm = StringUtils.substring(dto.procsDt(), 0, 6);
        dvo.setWareNo(dto.wareNo());
        dvo.setItmPdCd(dto.itmPdCd());
        dvo.setBaseYm(strBaseYm);

        String month = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");

        int strCmpOnQty = 0;

        /*입력된 상품코드,창고번호,기준년월 정보로 COUNT 조회 처리*/
        int chkValue = mapper.selectCountMcbyStock(dto);

        if (chkValue > 0) {
            WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
            log.info("mcbyDvo ----->", mcbyDvo);

            if (StringUtils.startsWith(dto.workDiv(), "A")) {
                /*===============================================================================
                구매입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --구매입고(110)
                ---------------------------------------------------------------------------------
                --품목등급(구매입고는 정상품(A등급)만 발생)
                --시점재고 = 시점재고 + 구매입고
                --구매입고 = 구매입고 + 구매입고
                ===============================================================================*/
                if (SnServiceConst.PRCHS_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    int strPrchsStrQty = Integer.parseInt(mcbyDvo.getPrchsStrQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setPrchsStrQty(String.valueOf(strPrchsStrQty));

                    processCount += mapper.updatePurchaseAStr(dvo);

                } else if (SnServiceConst.PRCHS_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    int strPrchsStrBGdQty = Integer.parseInt(mcbyDvo.getPrchsStrBGdQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setPrchsStrBGdQty(String.valueOf(strPrchsStrBGdQty));

                    processCount += mapper.updatePurchaseBStr(dvo);

                }
                /*입출유형(117:기타입고*/
                if (SnServiceConst.ETC_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*기타입고A등급수량*/
                    int strEtcStrAGdQty = Integer.parseInt(mcbyDvo.getEtcStrAGdQty() + Integer.parseInt(dto.qty()));

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setEtcStrAGdQty(String.valueOf(strEtcStrAGdQty));

                    processCount += mapper.updateEtcAStr(dvo);

                } else if (SnServiceConst.ETC_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    int strEtcStrEGdQty = Integer.parseInt(mcbyDvo.getEtcStrEGdQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setEtcStrEGdQty(String.valueOf(strEtcStrEGdQty));

                    processCount += mapper.updateEtcEStr(dvo);

                }
                /*===============================================================================
                내부입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부입고란?
                    정상입고(121), 물량배정입고(122), 물량이동입고(123)
                    반품입고내부(161)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 내부입고
                --내부입고 = 내부입고 + 내부입고
                ===============================================================================*/
                if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*정상출고A등급수량*/
                    int strNomOstrAGdQTy = Integer.parseInt(mcbyDvo.getNomOstrAGdQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setNomOstrAGdQty(String.valueOf(strNomOstrAGdQTy));

                    processCount += mapper.updateNomOstrAQty(dvo);

                } else if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    /*정상출고B등급수량*/
                    int strNomOstrBGdQTy = Integer.parseInt(mcbyDvo.getNomOstrBGdQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setNomOstrAGdQty(String.valueOf(strNomOstrBGdQTy));

                    processCount += mapper.updateNomOstrBQty(dvo);

                } else if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    /*정상출고E등급수량*/
                    int strNomOstrEGdQTy = Integer.parseInt(mcbyDvo.getNomOstrEGdQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setNomOstrEGdQty(String.valueOf(strNomOstrEGdQTy));

                    processCount += mapper.updateNomOstrEQty(dvo);

                } else if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    /*정상출고R등급수량*/
                    int strNomOstrRGdQTy = Integer.parseInt(mcbyDvo.getNomOstrRGdQty()) + Integer.parseInt(dto.qty());

                    dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                    dvo.setNomOstrRGdQty(String.valueOf(strNomOstrRGdQTy));

                    processCount += mapper.updateNomOstrRQty(dvo);

                }
                /*입출유형(122:물량배정 A,B,E,R)*/
                if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*물량배정입고A등급수량*/
                    int strQomAsnStrAGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrAGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setQomAsnStrAGdQty(String.valueOf(strQomAsnStrAGdQty));

                    processCount += mapper.updateQomAsnStrAGdQty(dvo);
                } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    /*물량배정입고B등급수량*/
                    int strQomAsnStrBGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrBGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setQomAsnStrBGdQty(String.valueOf(strQomAsnStrBGdQty));

                    processCount += mapper.updateQomAsnStrBGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    /*물량배정입고E등급수량*/
                    int strQomAsnStrEGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrEGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setQomAsnStrEGdQty(String.valueOf(strQomAsnStrEGdQty));

                    processCount += mapper.updateQomAsnStrEGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    /*물량배정입고B등급수량*/
                    int strQomAsnStrRGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrRGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                    dvo.setQomAsnStrRGdQty(String.valueOf(strQomAsnStrRGdQty));

                    processCount += mapper.updateQomAsnStrRGdQty(dvo);

                }
                /*입출유형(123:물량이동)*/
                if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    int strQomMmtStrAGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrAGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setQomMmtStrAGdQty(String.valueOf(strQomMmtStrAGdQty));

                    processCount += mapper.updateQomMmtStrAGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    int strQomMmtStrBGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrBGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setQomMmtStrBGdQty(String.valueOf(strQomMmtStrBGdQty));

                    processCount += mapper.updateQomMmtStrBGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    int strQomMmtStrEGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrEGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setQomMmtStrEGdQty(String.valueOf(strQomMmtStrEGdQty));

                    processCount += mapper.updateQomMmtStrEGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    int strQomMmtStrRGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrRGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                    dvo.setQomMmtStrRGdQty(String.valueOf(strQomMmtStrRGdQty));

                    processCount += mapper.updateQomMmtStrRGdQty(dvo);

                }
                /*입출유형(161:반품입고(내부))*/
                if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고내부A등급수량*/
                    int strRtngdStrInsiAGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiAGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setRtngdStrInsiAGdQty(String.valueOf(strRtngdStrInsiAGdQty));

                    processCount += mapper.updateRtngdStrInsiAgdQty(dvo);

                } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고내부B등급수량*/
                    int strRtngdStrInsiBGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiBGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setRtngdStrInsiBGdQty(String.valueOf(strRtngdStrInsiBGdQty));

                    processCount += mapper.updateRtngdStrInsiBgdQty(dvo);

                } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고내부E등급수량*/
                    int strRtngdStrInsiEGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiEGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setRtngdStrInsiEGdQty(String.valueOf(strRtngdStrInsiEGdQty));

                    processCount += mapper.updateRtngdStrInsiEgdQty(dvo);

                } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고내부R등급수량*/
                    int strRtngdStrInsiRGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiRGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                    dvo.setRtngdStrInsiRGdQty(String.valueOf(strRtngdStrInsiRGdQty));

                    processCount += mapper.updateRtngdStrInsiRgdQty(dvo);

                }

                /*===============================================================================
                외부입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --외부반품입고란?
                    반품입고외부(162)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 외부반품입고
                --외부반품입고 = 외부반품입고 + 외부반품입고
                ===============================================================================*/
                /*입출유형(162:반품입고외부)*/
                if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고외부A등급수량*/
                    int strRtngdStrOtsdAGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdAGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setRtngdStrInsiAGdQty(String.valueOf(strRtngdStrOtsdAGdQty));

                    processCount += mapper.updateRtngStrOtsdAgdQty(dvo);
                } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고외부B등급수량*/
                    int strRtngdStrOtsdBGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdBGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setRtngdStrInsiBGdQty(String.valueOf(strRtngdStrOtsdBGdQty));

                    processCount += mapper.updateRtngStrOtsdBgdQty(dvo);
                } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고외부E등급수량*/
                    int strRtngdStrOtsdEGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdEGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setRtngdStrInsiEGdQty(String.valueOf(strRtngdStrOtsdEGdQty));

                    processCount += mapper.updateRtngStrOtsdEgdQty(dvo);
                } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    /*반품입고외부R등급수량*/
                    int strRtngdStrOtsdRGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdRGdQty())
                        + Integer.parseInt(dto.qty());

                    dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                    dvo.setRtngdStrInsiRGdQty(String.valueOf(strRtngdStrOtsdRGdQty));

                    processCount += mapper.updateRtngStrOtsdRgdQty(dvo);
                }

                /*===============================================================================
                재고조정입고(181)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 재고조정입고
                --재고조정입고 = 재고조정입고 + 재고조정입고
                ===============================================================================*/
                if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*기타입고A등급수량1*/
                    int strEtcStrAGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrAGdQty1()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                    dvo.setEtcStrAGdQty1(String.valueOf(strEtcStrAGdQty1));

                    processCount += mapper.updateEtcStrAGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    /*기타입고B등급수량1*/
                    int strEtcStrBGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrBGdQty1()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                    dvo.setEtcStrBGdQty1(String.valueOf(strEtcStrBGdQty1));

                    processCount += mapper.updateEtcStrBGdQty1(dvo);
                } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    /*기타입고E등급수량1*/
                    int strEtcStrEGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrEGdQty1()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                    dvo.setEtcStrEGdQty1(String.valueOf(strEtcStrEGdQty1));

                    processCount += mapper.updateEtcStrEGdQty1(dvo);
                } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    /*기타입고R등급수량1*/
                    int strEtcStrRGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrRGdQty1()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                    dvo.setEtcStrRGdQty1(String.valueOf(strEtcStrRGdQty1));

                    processCount += mapper.updateEtcStrRGdQty1(dvo);
                }

                /*===============================================================================
                내부출고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부출고란?
                    정상출고(221), 물량배정출고(222), 물량이동출고(223)
                    반품출고내부(261)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 - 내부출고
                --내부출고 = 내부출고 + 내부출고
                --시점재고부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
                //입출유형(221:정상출고)

                if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    /*시점재고*/
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strNomOstrAGdQty = Integer.parseInt(mcbyDvo.getNomOstrAGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setNomOstrAGdQty(String.valueOf(strNomOstrAGdQty));

                    processCount += mapper.updateNomOstrAGdQty(dvo);

                } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strNomOstrBGdQty = Integer.parseInt(mcbyDvo.getNomOstrBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setNomOstrBGdQty(String.valueOf(strNomOstrBGdQty));

                    processCount += mapper.updateNomOstrBGdQty(dvo);

                } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    int strNomOstrEGdQty = Integer.parseInt(mcbyDvo.getNomOstrEGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setNomOstrEGdQty(String.valueOf(strNomOstrEGdQty));

                    processCount += mapper.updateNomOstrEGdQty(dvo);

                } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    int strNomOstrRGdQty = Integer.parseInt(mcbyDvo.getNomOstrRGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setNomOstrRGdQty(String.valueOf(strNomOstrRGdQty));

                    processCount += mapper.updateNomOstrRGdQty(dvo);

                }
                /*입출유형(222:물량배정)*/
                if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {

                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    /*물량배정출고A등급수량*/
                    int strQomAsnOstrAGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrAGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomAsnOstrAGdQty(String.valueOf(strQomAsnOstrAGdQty));

                    processCount += mapper.updateQomAsnOstrAGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {

                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    /*물량배정출고B등급수량*/
                    int strQomAsnOstrBGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomAsnOstrBGdQty(String.valueOf(strQomAsnOstrBGdQty));

                    processCount += mapper.updateQomAsnOstrBGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {

                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    /*물량배정출고E등급수량*/
                    int strQomAsnOstrEGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrEGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomAsnOstrEGdQty(String.valueOf(strQomAsnOstrEGdQty));

                    processCount += mapper.updateQomAsnOstrEGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {

                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    /*물량배정출고R등급수량*/
                    int strQomAsnOstrRGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrRGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomAsnOstrRGdQty(String.valueOf(strQomAsnOstrRGdQty));

                    processCount += mapper.updateQomAsnOstrRGdQty(dvo);

                }
                /*입출유형(223:물량이동)*/
                if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    /*물량이동출고A등급수량*/
                    int strQomMmtOstrAGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrAGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomMmtOstrAGdQty(String.valueOf(strQomMmtOstrAGdQty));

                    processCount += mapper.updateQomMmtOstrAGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    /*물량이동출고B등급수량*/
                    int strQomMmtOstrBGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomMmtOstrBGdQty(String.valueOf(strQomMmtOstrBGdQty));

                    processCount += mapper.updateQomMmtOstrBGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    /*물량이동출고E등급수량*/
                    int strQomMmtOstrEGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrEGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomMmtOstrEGdQty(String.valueOf(strQomMmtOstrEGdQty));

                    processCount += mapper.updateQomMmtOstrEGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    /*물량이동출고R등급수량*/
                    int strQomMmtOstrRGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrRGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setQomMmtOstrRGdQty(String.valueOf(strQomMmtOstrRGdQty));

                    processCount += mapper.updateQomMmtOstrRGdQty(dvo);

                }
                /*입출유형(261:반품출고(내부)*/
                if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고내부A등급수량*/
                    int strRtnGdOstrInsiAGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiAGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrInsiAGdQty(String.valueOf(strRtnGdOstrInsiAGdQty));

                    processCount += mapper.updateRtngdOstrInsiAGdQty(dvo);

                } else if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고내부B등급수량*/
                    int strRtnGdOstrInsiBGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrInsiBGdQty(String.valueOf(strRtnGdOstrInsiBGdQty));

                    processCount += mapper.updateRtngdOstrInsiBGdQty(dvo);
                } else if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고내부E등급수량*/
                    int strRtnGdOstrInsiEGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiEGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrInsiEGdQty(String.valueOf(strRtnGdOstrInsiEGdQty));

                    processCount += mapper.updateRtngdOstrInsiEGdQty(dvo);
                } else if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고내부R등급수량*/
                    int strRtnGdOstrInsiRGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiRGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrInsiRGdQty(String.valueOf(strRtnGdOstrInsiRGdQty));

                    processCount += mapper.updateRtngdOstrInsiRGdQty(dvo);
                }

                /*===============================================================================
                외부출고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --외부출고란?
                    반품출고외부(262), 판매(211), 폐기(212), 작업(213), 기타(217), 리퍼완료(218)
                    기타1(291),기타2(292)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 - 외부출고
                --외부출고 = 외부출고 + 외부출고
                --판매는 정상품(A등급)만 발생
                --시점재고부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
                /*입출유형(262:반품출고외부*/

                if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고외부A등급수량*/
                    int strRtngdOstrOtsdAGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdAGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrOtsdAGdQty(String.valueOf(strRtngdOstrOtsdAGdQty));

                    processCount += mapper.updateRtngdOstrOtsdAGdQty(dvo);

                } else if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고외부B등급수량*/
                    int strRtngdOstrOtsdBGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrOtsdBGdQty(String.valueOf(strRtngdOstrOtsdBGdQty));

                    processCount += mapper.updateRtngdOstrOtsdBGdQty(dvo);

                } else if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고외부B등급수량*/
                    int strRtngdOstrOtsdEGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrOtsdEGdQty(String.valueOf(strRtngdOstrOtsdEGdQty));

                    processCount += mapper.updateRtngdOstrOtsdEGdQty(dvo);

                } else if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    /*반품출고외부R등급수량*/
                    int strRtngdOstrOtsdRGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdRGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRtngdOstrOtsdEGdQty(String.valueOf(strRtngdOstrOtsdRGdQty));

                    processCount += mapper.updateRtngdOstrOtsdRGdQty(dvo);
                }
                /*입출유형(211:판매*/
                if (SnServiceConst.OTSD_OSTR_SELL.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strSellOstrQty = Integer.parseInt(mcbyDvo.getSellOstrQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setSellOstrQty(String.valueOf(strSellOstrQty));

                    processCount += mapper.updateSellOstrQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_SELL.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strSellOstrBGdQty = Integer.parseInt(mcbyDvo.getSellOstrBGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setSellOstrBGdQty(String.valueOf(strSellOstrBGdQty));

                    processCount += mapper.updateSellOstrBGdQty(dvo);
                }

                if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strDsuOstrAGdQty = Integer.parseInt(mcbyDvo.getDsuOstrAGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setDsuOstrAGdQty(String.valueOf(strDsuOstrAGdQty));

                    processCount += mapper.updateDsuOstrAGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strDsuOstrBGdQty = Integer.parseInt(mcbyDvo.getDsuOstrBGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setDsuOstrBGdQty(String.valueOf(strDsuOstrBGdQty));

                    processCount += mapper.updateDsuOstrBGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    int strDsuOstrEGdQty = Integer.parseInt(mcbyDvo.getDsuOstrEGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setDsuOstrEGdQty(String.valueOf(strDsuOstrEGdQty));

                    processCount += mapper.updateDsuOstrEGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    int strDsuOstrRGdQty = Integer.parseInt(mcbyDvo.getDsuOstrRGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setDsuOstrRGdQty(String.valueOf(strDsuOstrRGdQty));

                    processCount += mapper.updateDsuOstrRGdQty(dvo);

                }

                if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    /*작업출고A등급수량*/
                    int strWkOstrAGdQty = Integer.parseInt(mcbyDvo.getWkOstrAGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setWkOstrAGdQty(String.valueOf(strWkOstrAGdQty));

                    processCount += mapper.updateWkOstrAGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    /*작업출고B등급수량*/
                    int strWkOstrBGdQty = Integer.parseInt(mcbyDvo.getWkOstrBGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setWkOstrBGdQty(String.valueOf(strWkOstrBGdQty));

                    processCount += mapper.updateWkOstrBGdQty(dvo);
                } else if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    /*작업출고E등급수량*/
                    int strWkOstrEGdQty = Integer.parseInt(mcbyDvo.getWkOstrEGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setWkOstrEGdQty(String.valueOf(strWkOstrEGdQty));

                    processCount += mapper.updateWkOstrEGdQty(dvo);
                } else if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    /*작업출고R등급수량*/
                    int strWkOstrRGdQty = Integer.parseInt(mcbyDvo.getWkOstrRGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setWkOstrRGdQty(String.valueOf(strWkOstrRGdQty));

                    processCount += mapper.updateWkOstrRGdQty(dvo);
                }
                /*입출유형(217:기타)*/
                if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrAGdQty = Integer.parseInt(mcbyDvo.getEtcOstrAGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrAGdQty(String.valueOf(strEtcOstrAGdQty));

                    processCount += mapper.updateEtcOstrAGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrBGdQty = Integer.parseInt(mcbyDvo.getEtcOstrBGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrBGdQty(String.valueOf(strEtcOstrBGdQty));

                    processCount += mapper.updateEtcOstrBGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrEGdQty = Integer.parseInt(mcbyDvo.getEtcOstrEGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrEGdQty(String.valueOf(strEtcOstrEGdQty));

                    processCount += mapper.updateEtcOstrEGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrRGdQty = Integer.parseInt(mcbyDvo.getEtcOstrRGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrRGdQty(String.valueOf(strEtcOstrRGdQty));

                    processCount += mapper.updateEtcOstrRGdQty(dvo);

                }
                /*입출유형(218:리퍼완료)*/
                if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strRefrOstrAGdQty = Integer.parseInt(mcbyDvo.getRefrOstrAGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRefrOstrAGdQty(String.valueOf(strRefrOstrAGdQty));

                    processCount += mapper.updateRefrOstrAGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strRefrOstrBGdQty = Integer.parseInt(mcbyDvo.getRefrOstrBGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRefrOstrBGdQty(String.valueOf(strRefrOstrBGdQty));

                    processCount += mapper.updateRefrOstrBGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    int strRefrOstrEGdQty = Integer.parseInt(mcbyDvo.getRefrOstrEGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRefrOstrEGdQty(String.valueOf(strRefrOstrEGdQty));

                    processCount += mapper.updateRefrOstrEGdQty(dvo);

                } else if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    int strRefrOstrRGdQty = Integer.parseInt(mcbyDvo.getRefrOstrRGdQty()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setRefrOstrRGdQty(String.valueOf(strRefrOstrRGdQty));

                    processCount += mapper.updateRefrOstrRGdQty(dvo);
                }
                /*===============================================================================
                재고조정출고(281)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 - 재고조정출고
                --재고조정출고 = 재고조정출고 + 재고조정출고
                ===============================================================================*/
                if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrAGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrAGdQty1()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrAGdQty1(String.valueOf(strEtcOstrAGdQty1));

                    processCount += mapper.updateEtcOstrAGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrBGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrBGdQty1()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrBGdQty1(String.valueOf(strEtcOstrBGdQty1));

                    processCount += mapper.updateEtcOstrBGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrEGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrEGdQty1()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrEGdQty1(String.valueOf(strEtcOstrEGdQty1));

                    processCount += mapper.updateEtcOstrEGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    int strEtcOstrRGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrRGdQty1()) + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setEtcOstrRGdQty1(String.valueOf(strEtcOstrRGdQty1));

                    processCount += mapper.updateEtcOstrRGdQty1(dvo);

                }
                /*입출유형(292:기타2)*/
                if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    int strStocAcinspOstrAGdQty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                    dvo.setStocAcinspOstrAGdQty(String.valueOf(strStocAcinspOstrAGdQty));

                    processCount += mapper.updateStocAcinspOstrAGdQty(dvo);

                } else if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    int strStocAcinspOstrBGdQty = Integer.parseInt(mcbyDvo.getStocAcinspBGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                    dvo.setStocAcinspOstrBGdQty(String.valueOf(strStocAcinspOstrBGdQty));

                    processCount += mapper.updateStocAcinspOstrBGdQty(dvo);

                } else if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    int strStocAcinspOstrEGdQty = Integer.parseInt(mcbyDvo.getStocAcinspEGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                    dvo.setStocAcinspOstrEGdQty(String.valueOf(strStocAcinspOstrEGdQty));

                    processCount += mapper.updateStocAcinspOstrEGdQty(dvo);

                } else if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    int strStocAcinspOstrRGdQty = Integer.parseInt(mcbyDvo.getStocAcinspRGdQty())
                        + Integer.parseInt(dto.qty());

                    /*시점재고부수가 MINUS 발생 오류!*/
                    BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                    dvo.setStocAcinspOstrRGdQty(String.valueOf(strStocAcinspOstrRGdQty));

                    processCount += mapper.updateStocAcinspOstrRGdQty(dvo);

                }

                int dtoProcsDt = Integer.parseInt(dto.procsDt().substring(0, 6));
                int dtoProcsYm = Integer.parseInt(dto.procsYm());
                //            Date cmprDateFrom = new Date(dateFormat.parse(dto.procsDt().substring(0, 6)).getTime());
                //            Date cmprDateTo = new Date(dateFormat.parse(dto.procsYm()).getTime());
                //            int result = cmprDateFrom.compareTo(cmprDateTo);

                if (dtoProcsDt < dtoProcsYm) {

                    month = StringUtils.substring(dto.procsDt(), 4, 2);
                    log.info("MM dateCheck ----->", month);

                    WsnaMonthlyItemStocksDvo CmprDvo = new WsnaMonthlyItemStocksDvo();

                    if ("A".equals(dto.itemGd())) {
                        CmprDvo = mapper.selectPitmMmtStocAQty(dto);
                        dvo.setPitmStocAGdQty(CmprDvo.getPitmStocAGdQty());
                        dvo.setMmtStocAGdQty(CmprDvo.getMmtStocAGdQty());
                    } else if ("B".equals(dto.itemGd())) {
                        CmprDvo = mapper.selectPitmMmtStocBQty(dto);
                        dvo.setPitmStocBGdQty(CmprDvo.getPitmStocBGdQty());
                        dvo.setMmtStocBGdQty(CmprDvo.getMmtStocBGdQty());
                    } else if ("E".equals(dto.itemGd())) {
                        CmprDvo = mapper.selectPitmMmtStocEQty(dto);
                        dvo.setPitmStocEGdQty(CmprDvo.getPitmStocEGdQty());
                        dvo.setMmtStocEGdQty(CmprDvo.getMmtStocEGdQty());
                    } else if ("R".equals(dto.itemGd())) {
                        CmprDvo = mapper.selectPitmMmtStocRQty(dto);
                        dvo.setPitmStocRGdQty(CmprDvo.getPitmStocRGdQty());
                        dvo.setMmtStocRGdQty(CmprDvo.getMmtStocRGdQty());
                    }

                    String cmpApldYm = StringUtils.substring(dto.procsDt(), 0, 6);

                    /*수불월의 다음월 산출*/
                    int nextMonth = Integer.parseInt(month) + 1;
                    log.info("II -------> 다음월 산출 데이터", nextMonth);
                    String strCmpApldYm = null;

                    if (nextMonth == 13) {
                        strCmpApldYm = Integer.parseInt(StringUtils.substring(cmpApldYm, 0, 4)) + 1 + "01";
                        dvo.setBaseYm(strCmpApldYm);
                    } else {
                        strCmpApldYm = StringUtils.substring(cmpApldYm, 0, 4) + String.format("%02d", nextMonth);
                        dvo.setBaseYm(strCmpApldYm);
                    }

                    /*TODO : 월별 재고이월 호출(V_CMP_APLD_YM,V_WCOM_STCK_CD,V_WCOM_ITEM_CD,V_WCOM_ITEM_DEG,V_WCOM_STCK_MGR,V_CMP_ON_QTY,V_CMP_BUFF_QTY,V_LOG_ID)
                     *  개발완료 되면 붙일 예정*/
                    saveMonthlyItemStocCrdovrs(converter.mapWsnaMcbyItmStocsDvoToCrdovrRes(dvo));
                }
            }

        } else {

            if (StringUtils.startsWith(dto.workDiv(), "A")) {
                /*===============================================================================
                구매입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --구매입고(110)
                ---------------------------------------------------------------------------------
                --품목등급(구매입고는 정상품(A등급)만 발생)
                --시점재고 = 시점재고 + 구매입고
                --구매입고 = 구매입고 + 구매입고
                ===============================================================================*/
                //SUBSTR(V_WCOM_IO_DT,1,6), V_WCOM_STCK_CD, SUBSTR(V_WCOM_ITEM_CD,1,5), SUBSTR(V_WCOM_ITEM_CD,6,6), V_WCOM_STCK_MGR, V_WCOM_QTY, V_WCOM_QTY, V_REG_DT, V_REG_TM, V_LOG_ID
                if (SnServiceConst.PRCHS_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 5));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setPrchsStrQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());
                    processCount += mapper.insertNewPrchStrQty(dvo);
                } else if (SnServiceConst.PRCHS_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 5));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setPrchsStrBGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());
                    processCount += mapper.insertNewPrchsStrBGdQty(dvo);
                }

                /*===============================================================================
                내부입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부입고란?
                    정상입고(121), 물량배정입고(122), 물량이동입고(123)
                    반품입고내부(161)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 내부입고
                --내부입고 = 내부입고 + 내부입고
                ===============================================================================*/
                if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setNomStrAGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertNomStrAGdQty(dvo);

                } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setNomStrBGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertNomStrBGdQty(dvo);

                } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setNomStrEGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertNomStrEGdQty(dvo);

                } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocRGdQty(dto.qty());
                    dvo.setNomStrRGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertNomStrRGdQty(dvo);
                }

                if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setQomAsnStrAGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomAsnStrAGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setQomAsnStrBGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomAsnStrBGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setQomAsnStrEGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomAsnStrEGdQty(dvo);

                } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocRGdQty(dto.qty());
                    dvo.setQomAsnStrRGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomAsnStrRGdQty(dvo);
                }

                if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setQomMmtStrAGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomMmtStrAGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setQomMmtStrBGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomMmtStrBGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setQomMmtStrEGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomMmtStrEGdQty(dvo);

                } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocRGdQty(dto.qty());
                    dvo.setQomMmtStrRGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertQomMmtStrRGdQty(dvo);

                }
                /*반품입고내부A등급수량*/
                if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setRtngdStrInsiAGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrInsiAGdQty(dvo);

                } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setRtngdStrInsiBGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrInsiBGdQty(dvo);

                } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setRtngdStrInsiEGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrInsiEGdQty(dvo);

                } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocRGdQty(dto.qty());
                    dvo.setRtngdStrInsiRGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrInsiRGdQty(dvo);

                }
                /*기타입고A등급수량*/
                if (SnServiceConst.ETC_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setEtcStrAGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertEtcStrAGdQty(dvo);
                    /*기타입고E등급수량*/
                } else if (SnServiceConst.ETC_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setEtcStrEGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertEtcStrEGdQty(dvo);
                }
                /*===============================================================================
                외부입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --외부반품입고란?
                    반품입고외부(162)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 외부반품입고
                --외부반품입고 = 외부반품입고 + 외부반품입고
                ===============================================================================*/
                /*반품입고외부A등급수량*/
                if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setRtngdStrOtsdAGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrOtsdAGdQty(dvo);

                    /*반품입고외부B등급수량*/
                } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setRtngdStrOtsdBGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrOtsdBGdQty(dvo);

                    /*반품입고외부E등급수량*/
                } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setRtngdStrOtsdEGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrOtsdEGdQty(dvo);
                    /*반품입고외부R등급수량*/
                } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocRGdQty(dto.qty());
                    dvo.setRtngdStrOtsdRGdQty(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertRtngdStrOtsdRGdQty(dvo);

                }

                /*===============================================================================
                재고조정입고(181)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 재고조정입고
                --재고조정입고 = 재고조정입고 + 재고조정입고
                ===============================================================================*/
                /*입출유형(181:재고조정입고)*/
                if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocAGdQty(dto.qty());
                    dvo.setEtcStrAGdQty1(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertEtcStrAGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {

                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocBGdQty(dto.qty());
                    dvo.setEtcStrBGdQty1(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertEtcStrBGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {

                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocEGdQty(dto.qty());
                    dvo.setEtcStrEGdQty1(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertEtcStrEGdQty1(dvo);

                } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {

                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setBaseYm(StringUtils.substring(dto.procsDt(), 0, 6));
                    dvo.setWareMngtPrtnrNo(dto.wareMngtPrtnrNo());
                    dvo.setPitmStocRGdQty(dto.qty());
                    dvo.setEtcStrRGdQty1(dto.qty());
                    dvo.setWareNo(dto.wareNo());

                    processCount += mapper.insertEtcStrRGdQty1(dvo);

                }

                Date cmprDateFrom = new Date(dateFormat.parse(dto.procsDt().substring(0, 6)).getTime());
                Date cmprDateTo = new Date(dateFormat.parse(dto.procsYm()).getTime());
                int result = cmprDateFrom.compareTo(cmprDateTo);

                if (result < 0) {

                    month = StringUtils.substring(dto.procsDt(), 4, 2);
                    log.info("MM dateCheck ----->", month);

                    WsnaMonthlyItemStocksDvo NewCmprDvo = new WsnaMonthlyItemStocksDvo();

                    if ("A".equals(dto.itemGd())) {
                        NewCmprDvo = mapper.selectPitmMmtStocAQty(dto);
                        dvo.setPitmStocAGdQty(NewCmprDvo.getPitmStocAGdQty());
                        dvo.setMmtStocAGdQty(NewCmprDvo.getMmtStocAGdQty());
                    } else if ("B".equals(dto.itemGd())) {
                        NewCmprDvo = mapper.selectPitmMmtStocBQty(dto);
                        dvo.setPitmStocBGdQty(NewCmprDvo.getPitmStocBGdQty());
                        dvo.setMmtStocBGdQty(NewCmprDvo.getMmtStocBGdQty());
                    } else if ("E".equals(dto.itemGd())) {
                        NewCmprDvo = mapper.selectPitmMmtStocEQty(dto);
                        dvo.setPitmStocEGdQty(NewCmprDvo.getPitmStocEGdQty());
                        dvo.setMmtStocEGdQty(NewCmprDvo.getMmtStocEGdQty());
                    } else if ("R".equals(dto.itemGd())) {
                        NewCmprDvo = mapper.selectPitmMmtStocRQty(dto);
                        dvo.setPitmStocRGdQty(NewCmprDvo.getPitmStocRGdQty());
                        dvo.setMmtStocRGdQty(NewCmprDvo.getMmtStocRGdQty());
                    }

                    String cmpApldYm = StringUtils.substring(dto.procsDt(), 0, 6);

                    /*수불월의 다음월 산출*/
                    int nextMonth = Integer.parseInt(month) + 1;
                    log.info("II -------> 다음월 산출 데이터", nextMonth);
                    String strCmpApldYm = null;

                    if (nextMonth == 13) {
                        strCmpApldYm = Integer.parseInt(StringUtils.substring(cmpApldYm, 0, 4)) + 1 + "01";
                        dvo.setBaseYm(strCmpApldYm);
                    } else {
                        strCmpApldYm = StringUtils.substring(dto.procsYm(), 0, 4) + String.format("%02d", nextMonth);
                        dvo.setBaseYm(strCmpApldYm);
                    }

                    /*TODO : 월별 재고이월 호출(V_CMP_APLD_YM,V_WCOM_STCK_CD,V_WCOM_ITEM_CD,V_WCOM_ITEM_DEG,V_WCOM_STCK_MGR,V_CMP_ON_QTY,V_CMP_BUFF_QTY,V_LOG_ID)
                     *  개발완료 되면 붙일 예정*/
                    saveMonthlyItemStocCrdovrs(converter.mapWsnaMcbyItmStocsDvoToCrdovrRes(dvo));
                }
            }

        }
        return processCount;
    }

    /**
     * @param dto :[{procsYm : 처리년월 , procsDt : 처리일자, wareDv : 창고구분 , wareNo : 창고번호 , wareMngtPrtnrNo : 창고파트너번호
     *            iostTp : 입출고유형 , workDiv : 작업구분, itmPdCd : 품목상품코드, mngtUnit : 관리단위
     *            itemGd : 상품등급 , qty : 수량 , mvcYn : ?? , freeYn : ??, chgYn : ??}]
     * 월별 품목재고내역 삭제
     */
    public int removeMonthlyStock(WsnaMonthlyItemStocksDto.SaveReq dto) throws ParseException {
        int processCount = 0;
        WsnaMonthlyItemStocksDvo dvo = new WsnaMonthlyItemStocksDvo();

        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        int strPitmStocAGdQty = 0; //시점재고A등급수량
        int strPitmStocBGdQty = 0; //시점재고B등급수량
        int strPitmStocEGdQty = 0; //시점재고E등급수량
        int strPitmStocRGdQty = 0; //시점재고R등급수량
        String month = null;
        String strBaseYm = StringUtils.substring(dto.procsDt(), 0, 5);
        dvo.setWareNo(dto.wareNo());
        dvo.setItmPdCd(dto.itmPdCd());
        dvo.setBaseYm(strBaseYm);

        int strCmpOnQty = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");

        if (StringUtils.startsWith(dto.workDiv(), "D")) {
            /*===============================================================================
            구매입고 유형에 따른 처리
            ---------------------------------------------------------------------------------
            --구매입고(110)
            ---------------------------------------------------------------------------------
            --품목등급(구매입고는 정상품(A등급)만 발생)
            --시점재고 = 시점재고 + 구매입고
            --구매입고 = 구매입고 + 구매입고
            ===============================================================================*/
            if (SnServiceConst.PRCHS_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                int strPrchsStrQty = Integer.parseInt(mcbyDvo.getPrchsStrQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setPrchsStrQty(String.valueOf(strPrchsStrQty));

                processCount += mapper.updatePurchaseAStr(dvo);

            } else if (SnServiceConst.PRCHS_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                int strPrchsStrBGdQty = Integer.parseInt(mcbyDvo.getPrchsStrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setPrchsStrBGdQty(String.valueOf(strPrchsStrBGdQty));

                processCount += mapper.updatePurchaseBStr(dvo);

            }
            /*===============================================================================
                내부입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부입고란?
                    정상입고(121), 물량배정입고(122), 물량이동입고(123)
                    반품입고내부(161)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 내부입고
                --내부입고 = 내부입고 + 내부입고
                ===============================================================================*/
            if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                /*정상출고A등급수량*/
                int strNomOstrAGdQTy = Integer.parseInt(mcbyDvo.getNomOstrAGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setNomOstrAGdQty(String.valueOf(strNomOstrAGdQTy));

                processCount += mapper.updateNomOstrAQty(dvo);

            } else if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                /*정상출고B등급수량*/
                int strNomOstrBGdQTy = Integer.parseInt(mcbyDvo.getNomOstrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setNomOstrAGdQty(String.valueOf(strNomOstrBGdQTy));

                processCount += mapper.updateNomOstrBQty(dvo);

            } else if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                /*정상출고E등급수량*/
                int strNomOstrEGdQTy = Integer.parseInt(mcbyDvo.getNomOstrEGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                dvo.setNomOstrEGdQty(String.valueOf(strNomOstrEGdQTy));

                processCount += mapper.updateNomOstrEQty(dvo);

            } else if (SnServiceConst.NOM_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                /*정상출고R등급수량*/
                int strNomOstrRGdQTy = Integer.parseInt(mcbyDvo.getNomOstrRGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                dvo.setNomOstrRGdQty(String.valueOf(strNomOstrRGdQTy));

                processCount += mapper.updateNomOstrRQty(dvo);

            }

            /*입출유형(122:물량배정 A,B,E,R)*/
            if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                /*물량배정입고A등급수량*/
                int strQomAsnStrAGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setQomAsnStrAGdQty(String.valueOf(strQomAsnStrAGdQty));

                processCount += mapper.updateQomAsnStrAGdQty(dvo);
            } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                /*물량배정입고B등급수량*/
                int strQomAsnStrBGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setQomAsnStrBGdQty(String.valueOf(strQomAsnStrBGdQty));

                processCount += mapper.updateQomAsnStrBGdQty(dvo);

            } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                /*물량배정입고E등급수량*/
                int strQomAsnStrEGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                dvo.setQomAsnStrEGdQty(String.valueOf(strQomAsnStrEGdQty));

                processCount += mapper.updateQomAsnStrEGdQty(dvo);

            } else if (SnServiceConst.QOM_ASN.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                /*물량배정입고B등급수량*/
                int strQomAsnStrRGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                dvo.setQomAsnStrRGdQty(String.valueOf(strQomAsnStrRGdQty));

                processCount += mapper.updateQomAsnStrRGdQty(dvo);

            }
            /*입출유형(123:물량이동)*/
            if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                int strQomMmtStrAGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setQomMmtStrAGdQty(String.valueOf(strQomMmtStrAGdQty));

                processCount += mapper.updateQomMmtStrAGdQty(dvo);

            } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                int strQomMmtStrBGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setQomMmtStrBGdQty(String.valueOf(strQomMmtStrBGdQty));

                processCount += mapper.updateQomMmtStrBGdQty(dvo);

            } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                int strQomMmtStrEGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                dvo.setQomMmtStrEGdQty(String.valueOf(strQomMmtStrEGdQty));

                processCount += mapper.updateQomMmtStrEGdQty(dvo);

            } else if (SnServiceConst.QOM_MMT.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                int strQomMmtStrRGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                dvo.setQomMmtStrRGdQty(String.valueOf(strQomMmtStrRGdQty));

                processCount += mapper.updateQomMmtStrRGdQty(dvo);

            }
            /*입출유형(161:반품입고(내부))*/
            if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고내부A등급수량*/
                int strRtngdStrInsiAGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setRtngdStrInsiAGdQty(String.valueOf(strRtngdStrInsiAGdQty));

                processCount += mapper.updateRtngdStrInsiAgdQty(dvo);

            } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고내부B등급수량*/
                int strRtngdStrInsiBGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setRtngdStrInsiBGdQty(String.valueOf(strRtngdStrInsiBGdQty));

                processCount += mapper.updateRtngdStrInsiBgdQty(dvo);

            } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고내부E등급수량*/
                int strRtngdStrInsiEGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                dvo.setRtngdStrInsiEGdQty(String.valueOf(strRtngdStrInsiEGdQty));

                processCount += mapper.updateRtngdStrInsiEgdQty(dvo);

            } else if (SnServiceConst.RTNGD_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                /*반품입고내부R등급수량*/
                int strRtngdStrInsiRGdQty = Integer.parseInt(mcbyDvo.getRtngdStrInsiRGdQty())
                    + Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                dvo.setRtngdStrInsiRGdQty(String.valueOf(strRtngdStrInsiRGdQty));

                processCount += mapper.updateRtngdStrInsiRgdQty(dvo);

            }
            /*===============================================================================
            외부입고 유형에 따른 처리
            ---------------------------------------------------------------------------------
            --외부반품입고란?
                반품입고외부(162)
            ---------------------------------------------------------------------------------
            --시점재고 = 시점재고 + 외부반품입고
            --외부반품입고 = 외부반품입고 + 외부반품입고
            ===============================================================================*/
            /*입출유형(162:반품입고외부)*/
            if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고외부A등급수량*/
                int strRtngdStrOtsdAGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setRtngdStrInsiAGdQty(String.valueOf(strRtngdStrOtsdAGdQty));

                processCount += mapper.updateRtngStrOtsdAgdQty(dvo);
            } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고외부B등급수량*/
                int strRtngdStrOtsdBGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setRtngdStrInsiBGdQty(String.valueOf(strRtngdStrOtsdBGdQty));

                processCount += mapper.updateRtngStrOtsdBgdQty(dvo);
            } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고외부E등급수량*/
                int strRtngdStrOtsdEGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                dvo.setRtngdStrInsiEGdQty(String.valueOf(strRtngdStrOtsdEGdQty));

                processCount += mapper.updateRtngStrOtsdEgdQty(dvo);
            } else if (SnServiceConst.RTNGD_OTSD_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                /*반품입고외부R등급수량*/
                int strRtngdStrOtsdRGdQty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                dvo.setRtngdStrInsiRGdQty(String.valueOf(strRtngdStrOtsdRGdQty));

                processCount += mapper.updateRtngStrOtsdRgdQty(dvo);
            }

            /*===============================================================================
            재고조정입고(181)
            ---------------------------------------------------------------------------------
            --시점재고 = 시점재고 + 재고조정입고
            --재고조정입고 = 재고조정입고 + 재고조정입고
            ===============================================================================*/
            if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strPitmStocAGdQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                /*기타입고A등급수량1*/
                int strEtcStrAGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrAGdQty1()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocAGdQty(String.valueOf(strPitmStocAGdQty));
                dvo.setEtcStrAGdQty1(String.valueOf(strEtcStrAGdQty1));

                processCount += mapper.updateEtcStrAGdQty1(dvo);

            } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strPitmStocBGdQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                /*기타입고B등급수량1*/
                int strEtcStrBGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrBGdQty1()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocBGdQty(String.valueOf(strPitmStocBGdQty));
                dvo.setEtcStrBGdQty1(String.valueOf(strEtcStrBGdQty1));

                processCount += mapper.updateEtcStrBGdQty1(dvo);
            } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strPitmStocEGdQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                /*기타입고E등급수량1*/
                int strEtcStrEGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrEGdQty1()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocEGdQty(String.valueOf(strPitmStocEGdQty));
                dvo.setEtcStrEGdQty1(String.valueOf(strEtcStrEGdQty1));

                processCount += mapper.updateEtcStrEGdQty1(dvo);
            } else if (SnServiceConst.STOC_CTR_STR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strPitmStocRGdQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                /*기타입고R등급수량1*/
                int strEtcStrRGdQty1 = Integer.parseInt(mcbyDvo.getEtcStrRGdQty1()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocRGdQty(String.valueOf(strPitmStocRGdQty));
                dvo.setEtcStrRGdQty1(String.valueOf(strEtcStrRGdQty1));

                processCount += mapper.updateEtcStrRGdQty1(dvo);
            }
            /*===============================================================================
                내부출고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부출고란?
                    정상출고(221), 물량배정출고(222), 물량이동출고(223)
                    반품출고내부(261)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 - 내부출고
                --내부출고 = 내부출고 + 내부출고
                --시점재고부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
            //입출유형(221:정상출고)

            if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                /*시점재고*/
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strNomOstrAGdQty = Integer.parseInt(mcbyDvo.getNomOstrAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setNomOstrAGdQty(String.valueOf(strNomOstrAGdQty));

                processCount += mapper.updateNomOstrAGdQty(dvo);

            } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strNomOstrBGdQty = Integer.parseInt(mcbyDvo.getNomOstrBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setNomOstrBGdQty(String.valueOf(strNomOstrBGdQty));

                processCount += mapper.updateNomOstrBGdQty(dvo);

            } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                int strNomOstrEGdQty = Integer.parseInt(mcbyDvo.getNomOstrEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setNomOstrEGdQty(String.valueOf(strNomOstrEGdQty));

                processCount += mapper.updateNomOstrEGdQty(dvo);

            } else if (SnServiceConst.NOM_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                int strNomOstrRGdQty = Integer.parseInt(mcbyDvo.getNomOstrRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setNomOstrRGdQty(String.valueOf(strNomOstrRGdQty));

                processCount += mapper.updateNomOstrRGdQty(dvo);

            }
            /*입출유형(222:물량배정)*/
            if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {

                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                /*물량배정출고A등급수량*/
                int strQomAsnOstrAGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomAsnOstrAGdQty(String.valueOf(strQomAsnOstrAGdQty));

                processCount += mapper.updateQomAsnOstrAGdQty(dvo);

            } else if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {

                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                /*물량배정출고B등급수량*/
                int strQomAsnOstrBGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomAsnOstrBGdQty(String.valueOf(strQomAsnOstrBGdQty));

                processCount += mapper.updateQomAsnOstrBGdQty(dvo);

            } else if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {

                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                /*물량배정출고E등급수량*/
                int strQomAsnOstrEGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomAsnOstrEGdQty(String.valueOf(strQomAsnOstrEGdQty));

                processCount += mapper.updateQomAsnOstrEGdQty(dvo);

            } else if (SnServiceConst.QOM_ASN_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {

                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                /*물량배정출고R등급수량*/
                int strQomAsnOstrRGdQty = Integer.parseInt(mcbyDvo.getQomAsnOstrRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomAsnOstrRGdQty(String.valueOf(strQomAsnOstrRGdQty));

                processCount += mapper.updateQomAsnOstrRGdQty(dvo);

            }
            /*입출유형(223:물량이동)*/
            if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                /*물량이동출고A등급수량*/
                int strQomMmtOstrAGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomMmtOstrAGdQty(String.valueOf(strQomMmtOstrAGdQty));

                processCount += mapper.updateQomMmtOstrAGdQty(dvo);

            } else if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                /*물량이동출고B등급수량*/
                int strQomMmtOstrBGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomMmtOstrBGdQty(String.valueOf(strQomMmtOstrBGdQty));

                processCount += mapper.updateQomMmtOstrBGdQty(dvo);

            } else if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                /*물량이동출고E등급수량*/
                int strQomMmtOstrEGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomMmtOstrEGdQty(String.valueOf(strQomMmtOstrEGdQty));

                processCount += mapper.updateQomMmtOstrEGdQty(dvo);

            } else if (SnServiceConst.QOM_MMT_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                /*물량이동출고R등급수량*/
                int strQomMmtOstrRGdQty = Integer.parseInt(mcbyDvo.getQomMmtOstrRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setQomMmtOstrRGdQty(String.valueOf(strQomMmtOstrRGdQty));

                processCount += mapper.updateQomMmtOstrRGdQty(dvo);

            }
            /*입출유형(261:반품출고(내부)*/
            if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고내부A등급수량*/
                int strRtnGdOstrInsiAGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrInsiAGdQty(String.valueOf(strRtnGdOstrInsiAGdQty));

                processCount += mapper.updateRtngdOstrInsiAGdQty(dvo);

            } else if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고내부B등급수량*/
                int strRtnGdOstrInsiBGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrInsiBGdQty(String.valueOf(strRtnGdOstrInsiBGdQty));

                processCount += mapper.updateRtngdOstrInsiBGdQty(dvo);
            } else if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고내부E등급수량*/
                int strRtnGdOstrInsiEGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrInsiEGdQty(String.valueOf(strRtnGdOstrInsiEGdQty));

                processCount += mapper.updateRtngdOstrInsiEGdQty(dvo);
            } else if (SnServiceConst.RTNGD_OSTR_INSI.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고내부R등급수량*/
                int strRtnGdOstrInsiRGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrInsiRGdQty(String.valueOf(strRtnGdOstrInsiRGdQty));

                processCount += mapper.updateRtngdOstrInsiRGdQty(dvo);
            }
            /*===============================================================================
            외부출고 유형에 따른 처리
            ---------------------------------------------------------------------------------
            --외부출고란?
                반품출고외부(262), 판매(211), 폐기(212), 작업(213), 기타(217), 리퍼완료(218)
                기타1(291),기타2(292)
            ---------------------------------------------------------------------------------
            --시점재고 = 시점재고 - 외부출고
            --외부출고 = 외부출고 + 외부출고
            --판매는 정상품(A등급)만 발생
            --시점재고부수가 0보다 적으면 ERROR 발생
            ===============================================================================*/
            /*입출유형(262:반품출고외부*/

            if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고외부A등급수량*/
                int strRtngdOstrOtsdAGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrOtsdAGdQty(String.valueOf(strRtngdOstrOtsdAGdQty));

                processCount += mapper.updateRtngdOstrOtsdAGdQty(dvo);

            } else if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고외부B등급수량*/
                int strRtngdOstrOtsdBGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrOtsdBGdQty(String.valueOf(strRtngdOstrOtsdBGdQty));

                processCount += mapper.updateRtngdOstrOtsdBGdQty(dvo);

            } else if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고외부B등급수량*/
                int strRtngdOstrOtsdEGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrOtsdEGdQty(String.valueOf(strRtngdOstrOtsdEGdQty));

                processCount += mapper.updateRtngdOstrOtsdEGdQty(dvo);

            } else if (SnServiceConst.RTNGD_OSTR_OTSD.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                /*반품출고외부R등급수량*/
                int strRtngdOstrOtsdRGdQty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setRtngdOstrOtsdEGdQty(String.valueOf(strRtngdOstrOtsdRGdQty));

                processCount += mapper.updateRtngdOstrOtsdRGdQty(dvo);
            }
            /*입출유형(211:판매*/
            if (SnServiceConst.OTSD_OSTR_SELL.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strSellOstrQty = Integer.parseInt(mcbyDvo.getSellOstrQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setSellOstrQty(String.valueOf(strSellOstrQty));

                processCount += mapper.updateSellOstrQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_SELL.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strSellOstrBGdQty = Integer.parseInt(mcbyDvo.getSellOstrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setSellOstrBGdQty(String.valueOf(strSellOstrBGdQty));

                processCount += mapper.updateSellOstrBGdQty(dvo);
            }

            if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strDsuOstrAGdQty = Integer.parseInt(mcbyDvo.getDsuOstrAGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setDsuOstrAGdQty(String.valueOf(strDsuOstrAGdQty));

                processCount += mapper.updateDsuOstrAGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strDsuOstrBGdQty = Integer.parseInt(mcbyDvo.getDsuOstrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setDsuOstrBGdQty(String.valueOf(strDsuOstrBGdQty));

                processCount += mapper.updateDsuOstrBGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                int strDsuOstrEGdQty = Integer.parseInt(mcbyDvo.getDsuOstrEGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setDsuOstrEGdQty(String.valueOf(strDsuOstrEGdQty));

                processCount += mapper.updateDsuOstrEGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_DSU.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                int strDsuOstrRGdQty = Integer.parseInt(mcbyDvo.getDsuOstrRGdQty()) + Integer.parseInt(dto.qty());

                /*시점재고부수가 MINUS 발생 오류!*/
                BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setDsuOstrRGdQty(String.valueOf(strDsuOstrRGdQty));

                processCount += mapper.updateDsuOstrRGdQty(dvo);

            }

            if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                /*작업출고A등급수량*/
                int strWkOstrAGdQty = Integer.parseInt(mcbyDvo.getWkOstrAGdQty()) - Integer.parseInt(dto.qty());

                /*시점재고부수가 MINUS 발생 오류!*/
                BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setWkOstrAGdQty(String.valueOf(strWkOstrAGdQty));

                processCount += mapper.updateWkOstrAGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                /*작업출고B등급수량*/
                int strWkOstrBGdQty = Integer.parseInt(mcbyDvo.getWkOstrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setWkOstrBGdQty(String.valueOf(strWkOstrBGdQty));

                processCount += mapper.updateWkOstrBGdQty(dvo);
            } else if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                /*작업출고E등급수량*/
                int strWkOstrEGdQty = Integer.parseInt(mcbyDvo.getWkOstrEGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setWkOstrEGdQty(String.valueOf(strWkOstrEGdQty));

                processCount += mapper.updateWkOstrEGdQty(dvo);
            } else if (SnServiceConst.OTSD_OSTR_WK.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                /*작업출고R등급수량*/
                int strWkOstrRGdQty = Integer.parseInt(mcbyDvo.getWkOstrRGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setWkOstrRGdQty(String.valueOf(strWkOstrRGdQty));

                processCount += mapper.updateWkOstrRGdQty(dvo);
            }
            /*입출유형(217:기타)*/
            if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrAGdQty = Integer.parseInt(mcbyDvo.getEtcOstrAGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrAGdQty(String.valueOf(strEtcOstrAGdQty));

                processCount += mapper.updateEtcOstrAGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrBGdQty = Integer.parseInt(mcbyDvo.getEtcOstrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrBGdQty(String.valueOf(strEtcOstrBGdQty));

                processCount += mapper.updateEtcOstrBGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrEGdQty = Integer.parseInt(mcbyDvo.getEtcOstrEGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrEGdQty(String.valueOf(strEtcOstrEGdQty));

                processCount += mapper.updateEtcOstrEGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_ETC.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrRGdQty = Integer.parseInt(mcbyDvo.getEtcOstrRGdQty()) - Integer.parseInt(dto.qty());

                /*시점재고부수가 MINUS 발생 오류!*/
                BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrRGdQty(String.valueOf(strEtcOstrRGdQty));

                processCount += mapper.updateEtcOstrRGdQty(dvo);

            }

            /*입출유형(218:리퍼완료)*/
            if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strRefrOstrAGdQty = Integer.parseInt(mcbyDvo.getRefrOstrAGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setRefrOstrAGdQty(String.valueOf(strRefrOstrAGdQty));

                processCount += mapper.updateRefrOstrAGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strRefrOstrBGdQty = Integer.parseInt(mcbyDvo.getRefrOstrBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setRefrOstrBGdQty(String.valueOf(strRefrOstrBGdQty));

                processCount += mapper.updateRefrOstrBGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strRefrOstrEGdQty = Integer.parseInt(mcbyDvo.getRefrOstrEGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setRefrOstrEGdQty(String.valueOf(strRefrOstrEGdQty));

                processCount += mapper.updateRefrOstrEGdQty(dvo);

            } else if (SnServiceConst.OTSD_OSTR_REFR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strRefrOstrRGdQty = Integer.parseInt(mcbyDvo.getRefrOstrRGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setRefrOstrRGdQty(String.valueOf(strRefrOstrRGdQty));

                processCount += mapper.updateRefrOstrRGdQty(dvo);
            }

            /*===============================================================================
            재고조정출고(281)
            ---------------------------------------------------------------------------------
            --시점재고 = 시점재고 - 재고조정출고
            --재고조정출고 = 재고조정출고 + 재고조정출고
            ===============================================================================*/
            if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrAGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrAGdQty1()) - Integer.parseInt(dto.qty());

                /*시점재고부수가 MINUS 발생 오류!*/
                BizAssert.isTrue(strCmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrAGdQty1(String.valueOf(strEtcOstrAGdQty1));

                processCount += mapper.updateEtcOstrAGdQty1(dvo);

            } else if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrBGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrBGdQty1()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrBGdQty1(String.valueOf(strEtcOstrBGdQty1));

                processCount += mapper.updateEtcOstrBGdQty1(dvo);

            } else if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrEGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrEGdQty1()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrEGdQty1(String.valueOf(strEtcOstrEGdQty1));

                processCount += mapper.updateEtcOstrEGdQty1(dvo);

            } else if (SnServiceConst.STOC_CTR_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                int strEtcOstrRGdQty1 = Integer.parseInt(mcbyDvo.getEtcOstrRGdQty1()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setEtcOstrRGdQty1(String.valueOf(strEtcOstrRGdQty1));

                processCount += mapper.updateEtcOstrRGdQty1(dvo);

            }

            /*입출유형(292:기타2)*/
            if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                int strStocAcinspOstrAGdQty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(strCmpOnQty));
                dvo.setStocAcinspOstrAGdQty(String.valueOf(strStocAcinspOstrAGdQty));

                processCount += mapper.updateStocAcinspOstrAGdQty(dvo);

            } else if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                int strStocAcinspOstrBGdQty = Integer.parseInt(mcbyDvo.getStocAcinspBGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(strCmpOnQty));
                dvo.setStocAcinspOstrBGdQty(String.valueOf(strStocAcinspOstrBGdQty));

                processCount += mapper.updateStocAcinspOstrBGdQty(dvo);

            } else if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                int strStocAcinspOstrEGdQty = Integer.parseInt(mcbyDvo.getStocAcinspEGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(strCmpOnQty));
                dvo.setStocAcinspOstrEGdQty(String.valueOf(strStocAcinspOstrEGdQty));

                processCount += mapper.updateStocAcinspOstrEGdQty(dvo);

            } else if (SnServiceConst.STOC_ACINSP_OSTR.equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                strCmpOnQty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                int strStocAcinspOstrRGdQty = Integer.parseInt(mcbyDvo.getStocAcinspRGdQty())
                    - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(strCmpOnQty));
                dvo.setStocAcinspOstrRGdQty(String.valueOf(strStocAcinspOstrRGdQty));

                processCount += mapper.updateStocAcinspOstrRGdQty(dvo);

            }

            int dtoProcsDt = Integer.parseInt(dto.procsDt().substring(0, 6));
            int dtoProcsYm = Integer.parseInt(dto.procsYm());
            //            Date cmprDateFrom = new Date(dateFormat.parse(dto.procsDt().substring(0, 6)).getTime());
            //            Date cmprDateTo = new Date(dateFormat.parse(dto.procsYm()).getTime());
            //            int result = cmprDateFrom.compareTo(cmprDateTo);

            if (dtoProcsDt < dtoProcsYm) {

                month = StringUtils.substring(dto.procsDt(), 4, 2);
                log.info("MM dateCheck ----->", month);

                WsnaMonthlyItemStocksDvo CmprDvo = new WsnaMonthlyItemStocksDvo();

                if ("A".equals(dto.itemGd())) {
                    CmprDvo = mapper.selectPitmMmtStocAQty(dto);
                    dvo.setPitmStocAGdQty(CmprDvo.getPitmStocAGdQty());
                    dvo.setMmtStocAGdQty(CmprDvo.getMmtStocAGdQty());
                } else if ("B".equals(dto.itemGd())) {
                    CmprDvo = mapper.selectPitmMmtStocBQty(dto);
                    dvo.setPitmStocBGdQty(CmprDvo.getPitmStocBGdQty());
                    dvo.setMmtStocBGdQty(CmprDvo.getMmtStocBGdQty());
                } else if ("E".equals(dto.itemGd())) {
                    CmprDvo = mapper.selectPitmMmtStocEQty(dto);
                    dvo.setPitmStocEGdQty(CmprDvo.getPitmStocEGdQty());
                    dvo.setMmtStocEGdQty(CmprDvo.getMmtStocEGdQty());
                } else if ("R".equals(dto.itemGd())) {
                    CmprDvo = mapper.selectPitmMmtStocRQty(dto);
                    dvo.setPitmStocRGdQty(CmprDvo.getPitmStocRGdQty());
                    dvo.setMmtStocRGdQty(CmprDvo.getMmtStocRGdQty());
                }

                String cmpApldYm = StringUtils.substring(dto.procsDt(), 0, 5);

                /*수불월의 다음월 산출*/
                int nextMonth = Integer.parseInt(month) + 1;
                log.info("II -------> 다음월 산출 데이터", nextMonth);
                String strCmpApldYm = null;

                if (nextMonth == 13) {
                    strCmpApldYm = Integer.parseInt(StringUtils.substring(cmpApldYm, 0, 4)) + 1 + "01";
                    dvo.setBaseYm(strCmpApldYm);
                } else {
                    strCmpApldYm = StringUtils.substring(cmpApldYm, 0, 4) + String.format("%02d", nextMonth);
                    dvo.setBaseYm(strCmpApldYm);
                }
                //            SP_ST122TB_IWL(V_CMP_APLD_YM,V_WCOM_STCK_CD,V_WCOM_ITEM_CD,V_WCOM_ITEM_DEG,V_WCOM_STCK_MGR,V_CMP_ON_QTY,V_CMP_BUFF_QTY,V_LOG_ID)
                /*TODO : 월별 재고이월 호출(V_CMP_APLD_YM,V_WCOM_STCK_CD,V_WCOM_ITEM_CD,V_WCOM_ITEM_DEG,V_WCOM_STCK_MGR,V_CMP_ON_QTY,V_CMP_BUFF_QTY,V_LOG_ID)
                 *  개발완료 되면 붙일 예정*/
                saveMonthlyItemStocCrdovrs(converter.mapWsnaMcbyItmStocsDvoToCrdovrRes(dvo));
            }
        }

        return processCount;

    }

    /**
     * @param dto :[{baseYm  : 기준년월, wareNo : 창고번호 ,itmPdCd : 품목상품코드, itemGd : 상품등급
     *             , mmtStocQty : 이동재고수량 , pitmStocQty : 시점재고수량
     * }]
     *  월별 품목재고내역 이월
    v_apld_ym		char,
    v_stck_cd		char,
    v_item_cd		char,
    v_item_deg		char,
    v_stck_mgr      varchar2,

    asis ->v_on_qty		number, tobe -> PITM_STOC_A_GD_QTY
    asis ->v_buff_qty      number, tobe -> MMT_STOC_A_GD_QTY
    V_CMP_APLD_YM,V_WCOM_STCK_CD,V_WCOM_ITEM_CD,V_WCOM_ITEM_DEG,V_WCOM_STCK_MGR,V_CMP_ON_QTY,V_CMP_BUFF_QTY,V_LOG_ID

     ST122_ON_QTY_A, ST122_BUFF_QTY_A
     */

    public int saveMonthlyItemStocCrdovrs(WsnaMonthlyItemStocksDto.CrdovrReq dto) {

        WsnaMonthlyItemStocksDvo dvo = new WsnaMonthlyItemStocksDvo();
        int processCount = 0;
        String strWareMngtPrtnrNo = null;
        int strBaseQty = 0; //기초재고
        int strInQty = 0; //입고정보
        int strOutQty = 0; //출고정보
        int strOnQty = 0; //시점재고

        /*필수로 입력되는 파라미터 세팅*/
        dvo.setBaseYm(dto.baseYm());
        dvo.setWareNo(dto.wareNo());
        dvo.setItmPdCd(dto.itmPdCd());

        /*넘어온 파라미터로 해당하는 창고관리파트너번호를 조회한다.*/
        WsnaMonthlyItemStocksDvo wareDvo = mapper.selectWareMngtPrtnrNo(dto);

        if (StringUtils.isEmpty(wareDvo.getWareMngtPrtnrNo())) {
            strWareMngtPrtnrNo = dto.wareMngtPrtnrNo();
            dvo.setWareMngtPrtnrNo(strWareMngtPrtnrNo);
        }
        /*입력된 상품코드,창고번호,기준년월 정보로 COUNT 조회 처리*/
        int chkValue = mapper.selectCountMcbyItmStoc(dto);

        if (chkValue > 0) {
            WsnaMonthlyItemStocksDvo crdovrsDvo = mapper.selectMcbyItmStocCrdovrs(dto);
            if ("A".equals(dto.itemGd())) {
                //기초재고
                strBaseQty = Integer.parseInt(StringUtil.nvl(dto.pitmStocQty(), "0"));
                // PRCHS_STR_QTY 구매입고수량
                // NOM_STR_A_GD_QTY 정상입고A등급수량
                // QOM_ASN_STR_A_GD_QTY 물량배정입고A등급수량
                // QOM_MMT_STR_A_GD_QTY 물량이동입고A등급수량
                // RTNGD_STR_INSI_A_GD_QTY	반품입고내부A등급수량
                // RTNGD_STR_OTSD_A_GD_QTY	반품입고외부A등급수량
                // ETC_STR_A_GD_QTY1	기타입고A등급수량1
                // STOC_ACINSP_STR_A_GD_QTY	재고실사입고A등급수량
                strInQty = Integer.parseInt(StringUtil.nvl(crdovrsDvo.getPrchsStrQty(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getNomStrAGdQty(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getQomAsnStrAGdQty(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getQomMmtStrAGdQty(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getRtngdStrInsiAGdQty(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getRtngdStrOtsdAGdQty(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getEtcStrAGdQty1(), "0"))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getStocAcinspStrAGdQty(), "0"));

                // NOM_OSTR_A_GD_QTY	정상출고A등급수량
                // QOM_ASN_OSTR_A_GD_QTY	물량배정출고A등급수량
                // QOM_MMT_OSTR_A_GD_QTY	물량이동출고A등급수량
                // RTNGD_OSTR_INSI_A_GD_QTY	반품출고내부A등급수량
                // RTNGD_OSTR_OTSD_A_GD_QTY	반품출고외부A등급수량
                // SELL_OSTR_QTY	판매출고수량
                // DSU_OSTR_A_GD_QTY	폐기출고A등급수량
                // WK_OSTR_A_GD_QTY	작업출고A등급수량
                // ETC_OSTR_A_GD_QTY	기타출고A등급수량
                // ETC_OSTR_A_GD_QTY1	기타출고A등급수량1
                // STOC_ACINSP_OSTR_A_GD_QTY	재고실사출고A등급수량

                strOutQty = Integer.parseInt(StringUtil.nvl(crdovrsDvo.getNomOstrAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getQomAsnOstrAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getQomMmtOstrAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getRtngdOstrInsiAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getRtngdOstrOtsdAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getSellOstrQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getDsuOstrAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getWkOstrAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getEtcOstrAGdQty()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getEtcOstrAGdQty1()))
                    + Integer.parseInt(StringUtil.nvl(crdovrsDvo.getStocAcinspOstrAGdQty()));
                //시점재고 = 기초재고 + 입고정보 - 출고정보
                strOnQty = strBaseQty + strInQty - strOutQty;

                dvo.setBtdStocAGdQty(String.valueOf(strBaseQty));
                dvo.setPitmStocAGdQty(String.valueOf(strOnQty));

                processCount += mapper.updateCrdovrStocAGdQty(dvo);

            } else if ("E".equals(dto.itemGd())) {
                strBaseQty = Integer.parseInt(dto.pitmStocQty());
                // NOM_STR_E_GD_QTY 정상입고E등급수량
                // QOM_ASN_STR_E_GD_QTY 물량배정입고E등급수량
                // QOM_MMT_STR_E_GD_QTY 물량이동입고E등급수량
                // RTNGD_STR_INSI_E_GD_QTY 반품입고내부E등급수량
                // RTNGD_STR_OTSD_E_GD_QTY 반품입고외부E등급수량
                // ETC_STR_E_GD_QTY1 기타입고E등급수량1
                // STOC_ACINSP_STR_E_GD_QTY  재고실사입고E등급수량
                strInQty = Integer.parseInt(crdovrsDvo.getNomStrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomAsnStrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomMmtStrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdStrInsiEGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdStrOtsdEGdQty())
                    + Integer.parseInt(crdovrsDvo.getEtcStrEGdQty1())
                    + Integer.parseInt(crdovrsDvo.getStocAcinspStrEGdQty());

                // NOM_OSTR_E_GD_QTY 정상출고E등급수량
                // QOM_ASN_OSTR_E_GD_QTY 물량배정출고E등급수량
                // QOM_MMT_OSTR_E_GD_QTY 물량이동출고E등급수량
                // RTNGD_OSTR_INSI_E_GD_QTY 반품출고내부E등급수량
                // RTNGD_OSTR_OTSD_E_GD_QTY 반품출고외부E등급수량
                // DSU_OSTR_E_GD_QTY 폐기출고E등급수량
                // WK_OSTR_E_GD_QTY 작업출고E등급수량
                // ETC_OSTR_E_GD_QTY 기타출고E등급수량
                // ETC_OSTR_E_GD_QTY1 기타출고E등급수량1
                // STOC_ACINSP_OSTR_E_GD_QTY 재고실사출고E등급수량
                strOutQty = Integer.parseInt(crdovrsDvo.getNomOstrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomAsnOstrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomMmtOstrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdOstrInsiEGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdOstrOtsdEGdQty())
                    + Integer.parseInt(crdovrsDvo.getDsuOstrEGdQty()) + Integer.parseInt(crdovrsDvo.getWkOstrEGdQty())
                    + Integer.parseInt(crdovrsDvo.getEtcOstrEGdQty()) + Integer.parseInt(crdovrsDvo.getEtcOstrEGdQty1())
                    + Integer.parseInt(crdovrsDvo.getStocAcinspStrEGdQty());
                //시점재고 = 기초재고 + 입고정보 - 출고정보
                strOnQty = strBaseQty + strInQty - strOutQty;

                dvo.setBtdStocEGdQty(String.valueOf(strBaseQty));
                dvo.setPitmStocEGdQty(String.valueOf(strOnQty));

                processCount += mapper.updateCrdovrStocEGdQty(dvo);
            } else if ("R".equals(dto.itemGd())) {
                strBaseQty = Integer.parseInt(dto.pitmStocQty());
                // NOM_STR_R_GD_QTY 정상입고R등급수량
                // QOM_ASN_STR_R_GD_QTY 물량배정입고R등급수량
                // QOM_MMT_STR_R_GD_QTY 물량이동입고R등급수량
                // RTNGD_STR_INSI_R_GD_QTY 반품입고내부R등급수량
                // RTNGD_STR_OTSD_R_GD_QTY 반품입고외부R등급수량
                // ETC_STR_R_GD_QTY1 기타입고R등급수량1
                // STOC_ACINSP_STR_R_GD_QTY  재고실사입고R등급수량
                strInQty = Integer.parseInt(crdovrsDvo.getNomStrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomAsnStrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomMmtStrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdStrInsiRGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdStrOtsdRGdQty())
                    + Integer.parseInt(crdovrsDvo.getEtcStrRGdQty1())
                    + Integer.parseInt(crdovrsDvo.getStocAcinspStrRGdQty());
                // NOM_OSTR_R_GD_QTY 정상출고R등급수량
                // QOM_ASN_OSTR_R_GD_QTY 물량배정출고R등급수량
                // QOM_MMT_OSTR_R_GD_QTY 물량이동출고R등급수량
                // RTNGD_OSTR_INSI_R_GD_QTY 반품출고내부R등급수량
                // RTNGD_OSTR_OTSD_R_GD_QTY 반품출고외부R등급수량
                // DSU_OSTR_R_GD_QTY 폐기출고R등급수량
                // WK_OSTR_R_GD_QTY 작업출고R등급수량
                // ETC_OSTR_R_GD_QTY 기타출고R등급수량
                // ETC_OSTR_R_GD_QTY1 기타출고R등급수량1
                // STOC_ACINSP_OSTR_R_GD_QTY 재고실사출고R등급수량
                strOutQty = Integer.parseInt(crdovrsDvo.getNomOstrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomAsnOstrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getQomMmtOstrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdOstrInsiRGdQty())
                    + Integer.parseInt(crdovrsDvo.getRtngdOstrOtsdRGdQty())
                    + Integer.parseInt(crdovrsDvo.getDsuOstrRGdQty()) + Integer.parseInt(crdovrsDvo.getWkOstrRGdQty())
                    + Integer.parseInt(crdovrsDvo.getEtcOstrRGdQty()) + Integer.parseInt(crdovrsDvo.getEtcOstrRGdQty1())
                    + Integer.parseInt(crdovrsDvo.getStocAcinspStrRGdQty());
                //시점재고 = 기초재고 + 입고정보 - 출고정보
                strOnQty = strBaseQty + strInQty - strOutQty;
                dvo.setBtdStocRGdQty(String.valueOf(strBaseQty));
                dvo.setPitmStocRGdQty(String.valueOf(strOnQty));

                processCount += mapper.updateCrdovrStocRGdQty(dvo);

            }

        } else {
            /*조회된 데이터가 없을경우 신규로 월별품목재고내역에 데이터를 INSERT처리*/
            if ("A".equals(dto.itemGd())) {

                dvo.setBtdStocAGdQty(dto.pitmStocQty());
                dvo.setPitmStocAGdQty(dto.pitmStocQty());
                dvo.setMmtStocAGdQty(dto.mmtStocQty());

                processCount += mapper.insertCrdovrStocAGdQty(dvo);

            } else if ("E".equals(dto.itemGd())) {

                dvo.setBtdStocEGdQty(dto.pitmStocQty());
                dvo.setPitmStocEGdQty(dto.pitmStocQty());
                dvo.setMmtStocEGdQty(dto.mmtStocQty());

                processCount += mapper.insertCrdovrStocEGdQty(dvo);

            } else if ("E".equals(dto.itemGd())) {

                dvo.setBtdStocRGdQty(dto.pitmStocQty());
                dvo.setPitmStocRGdQty(dto.pitmStocQty());
                dvo.setMmtStocRGdQty(dto.mmtStocQty());

                processCount += mapper.insertCrdovrStocRGdQty(dvo);
            }

        }

        return processCount;
    }

}
