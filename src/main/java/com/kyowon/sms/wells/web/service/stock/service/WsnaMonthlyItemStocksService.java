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
        int strCmpOnQty = 0;
        String month = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");

        /*입력된 상품코드,창고번호,기준년월 정보로 COUNT 조회 처리*/
        int chkValue = mapper.selectCountMcbyStock(dto);

        if (chkValue > 0) {
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
                switch (dto.iostTp()) {
                    /* 구매입고(110) */
                    case SnServiceConst.PRCHS_STR:
                        processCount += prchsStrCount(dto);
                        break;
                    /* 입출유형(117:기타입고) */
                    case SnServiceConst.ETC_STR:
                        processCount += etcStrCount(dto);
                        break;
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
                switch (dto.iostTp()) {
                    /* 정상입고(121) */
                    case SnServiceConst.NOM_STR:
                        processCount += nomStrCount(dto);
                        break;
                    /* 물량배정입고(122) */
                    case SnServiceConst.QOM_ASN:
                        processCount += qomAsnCount(dto);
                        break;
                    /* 물량이동입고(123) */
                    case SnServiceConst.QOM_MMT:
                        processCount += qomMmtCount(dto);
                        break;
                    /* 반품입고내부(161) */
                    case SnServiceConst.RTNGD_STR:
                        processCount += rtngdStrCount(dto);
                        break;
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
                switch (dto.iostTp()) {
                    /*입출유형(162:반품입고외부)*/
                    case SnServiceConst.RTNGD_OTSD_STR:
                        processCount += rtngdOtsdStrCount(dto);
                        break;
                }

                /*===============================================================================
                재고조정입고(181)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 재고조정입고
                --재고조정입고 = 재고조정입고 + 재고조정입고
                ===============================================================================*/
                switch (dto.iostTp()) {
                    /*재고조정입고(181)*/
                    case SnServiceConst.STOC_CTR_STR:
                        processCount += stocCtrStrCount(dto);
                        break;
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
                switch (dto.iostTp()) {
                    /*입출유형(221:정상출고)*/
                    case SnServiceConst.NOM_OSTR:
                        processCount += nomOstrCount(dto);
                        break;
                    /*물량배정출고(222)*/
                    case SnServiceConst.QOM_ASN_OSTR:
                        processCount += qomAsnOstrCount(dto);
                        break;
                    /*물량이동출고(223)*/
                    case SnServiceConst.QOM_MMT_OSTR:
                        processCount += qomMmtOstrCount(dto);
                        break;
                    /*반품출고내부(261)*/
                    case SnServiceConst.RTNGD_OSTR_INSI:
                        processCount += rtngdOstrInsiCount(dto);
                        break;
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
            Date cmprDateFrom = new Date(dateFormat.parse(dto.procsDt().substring(0, 6)).getTime());
            Date cmprDateTo = new Date(dateFormat.parse(dto.procsYm()).getTime());
            int result = cmprDateFrom.compareTo(cmprDateTo);

            if (result < 0) {

                month = StringUtils.substring(dto.procsDt(), 4, 2);
                log.info("MM dateCheck ----->", month);
            }
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

    /**
     * private 시작
     */
    //구매입고(110)
    private int prchsStrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumPrchsStrQty(dvo, mcbyDvo, qty);
                processCount = mapper.updatePurchaseAStr(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumPrchsStrBGdQty(dvo, mcbyDvo, qty);
                processCount = mapper.updatePurchaseBStr(dvo);
                break;
        }

        return processCount;
    }

    //입출유형(117:기타입고)
    private int etcStrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumEtcStrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcAStr(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumEtcStrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcEStr(dvo);
                break;
        }

        return processCount;
    }

    //내부입고 : 정상입고(121)
    private int nomStrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumNomStrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateNomOstrAQty(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumNomStrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateNomOstrBQty(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumNomStrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateNomOstrEQty(dvo);
                break;
            case "R":
                setSumPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumNomStrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateNomOstrRQty(dvo);
                break;
        }

        return processCount;
    }

    /* 입출유형(122:물량배정 A,B,E,R) */
    private int qomAsnCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnStrAGdQty(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnStrBGdQty(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnStrEGdQty(dvo);
                break;
            case "R":
                setSumPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnStrRGdQty(dvo);
                break;
        }

        return processCount;
    }

    /* 입출유형(123:물량이동) */
    private int qomMmtCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumQomMmtAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomMmtStrAGdQty(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumQomMmtBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomMmtStrBGdQty(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumQomMmtEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomMmtStrEGdQty(dvo);
                break;
            case "R":
                setSumPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumQomMmtRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomMmtStrRGdQty(dvo);
                break;
        }

        return processCount;
    }

    /*입출유형(161:반품입고(내부))*/
    private int rtngdStrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumRtngdStrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngdStrInsiAgdQty(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumRtngdStrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngdStrInsiBgdQty(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumRtngdStrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngdStrInsiEgdQty(dvo);
                break;
            case "R":
                setSumPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumRtngdStrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngdStrInsiRgdQty(dvo);
                break;
        }

        return processCount;
    }

    /*입출유형(162:반품입고외부)*/
    private int rtngdOtsdStrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOtsdStrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngStrOtsdAgdQty(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOtsdStrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngStrOtsdBgdQty(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOtsdStrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngStrOtsdEgdQty(dvo);
                break;
            case "R":
                setSumPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOtsdStrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateRtngStrOtsdRgdQty(dvo);
                break;
        }

        return processCount;
    }

    /*재고조정입고(181)*/
    private int stocCtrStrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setSumPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumStocCtrStrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrAGdQty1(dvo);
                break;
            case "B":
                setSumPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumStocCtrStrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrBGdQty1(dvo);
                break;
            case "E":
                setSumPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumStocCtrStrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrEGdQty1(dvo);
                break;
            case "R":
                setSumPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumStocCtrStrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrRGdQty1(dvo);
                break;
        }

        return processCount;
    }

    /*입출유형(221:정상출고)*/
    private int nomOstrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setMinusPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumNomOstrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrAGdQty1(dvo);
                break;
            case "B":
                setMinusPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumNomOstrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrBGdQty1(dvo);
                break;
            case "E":
                setMinusPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumNomOstrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrEGdQty1(dvo);
                break;
            case "R":
                setMinusPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumNomOstrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrRGdQty1(dvo);
                break;
        }

        return processCount;
    }

    /*입출유형(222:물량배정)*/
    private int qomAsnOstrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setMinusPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnOstrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnOstrAGdQty(dvo);
                break;
            case "B":
                setMinusPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnOstrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnOstrBGdQty(dvo);
                break;
            case "E":
                setMinusPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnOstrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnOstrEGdQty(dvo);
                break;
            case "R":
                setMinusPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumQomAsnOstrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateQomAsnOstrRGdQty(dvo);
                break;
        }

        return processCount;
    }

    /*입출유형(223:물량이동)*/
    private int qomMmtOstrCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setMinusPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumQomMntOstrAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrAGdQty1(dvo);
                break;
            case "B":
                setMinusPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumQomMntOstrBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrBGdQty1(dvo);
                break;
            case "E":
                setMinusPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumQomMntOstrEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrEGdQty1(dvo);
                break;
            case "R":
                setMinusPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumQomMntOstrRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrRGdQty1(dvo);
                break;
        }

        return processCount;
    }

    /*입출유형(261:반품출고(내부)*/
    private int rtngdOstrInsiCount(WsnaMonthlyItemStocksDto.SaveReq dto) {
        int processCount = 0;
        int qty = Integer.parseInt(dto.qty());

        WsnaMonthlyItemStocksDvo dvo = commonProcess(dto);
        WsnaMonthlyItemStocksDvo mcbyDvo = mapper.selectMcbyItmStocs(dto);
        log.info("prchsStrCount : mcbyDvo ----->", mcbyDvo);

        switch (dto.itemGd()) {
            case "A":
                setMinusPitmStocAGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOstrInsiAGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrAGdQty1(dvo);
                break;
            case "B":
                setMinusPitmStocBGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOstrInsiBGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrBGdQty1(dvo);
                break;
            case "E":
                setMinusPitmStocEGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOstrInsiEGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrEGdQty1(dvo);
                break;
            case "R":
                setMinusPitmStocRGdQty(dvo, mcbyDvo, qty);
                setSumRtngdOstrInsiRGdQty(dvo, mcbyDvo, qty);
                processCount += mapper.updateEtcStrRGdQty1(dvo);
                break;
        }

        return processCount;
    }

    protected WsnaMonthlyItemStocksDvo commonProcess(WsnaMonthlyItemStocksDto.SaveReq dto) {
        WsnaMonthlyItemStocksDvo dvo = new WsnaMonthlyItemStocksDvo();
        String strBaseYm = StringUtils.substring(dto.procsDt(), 0, 5);
        dvo.setBaseYm(strBaseYm);
        dvo.setWareNo(dto.wareNo());
        dvo.setItmPdCd(dto.itmPdCd());
        return dvo;
    }

    protected int getQty(WsnaMonthlyItemStocksDvo mcbyDvo, String attr) {
        int qty = 0;
        switch (attr) {
            case "PITM_STOC_A":
                qty = Integer.parseInt(mcbyDvo.getPitmStocAGdQty());
                break;
            case "PITM_STOC_B":
                qty = Integer.parseInt(mcbyDvo.getPitmStocBGdQty());
                break;
            case "PITM_STOC_E":
                qty = Integer.parseInt(mcbyDvo.getPitmStocEGdQty());
                break;
            case "PITM_STOC_R":
                qty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty());
                break;

            case "PRCHS_STR_A":
                qty = Integer.parseInt(mcbyDvo.getPrchsStrQty());
                break;
            case "PRCHS_STR_B":
                qty = Integer.parseInt(mcbyDvo.getPrchsStrBGdQty());
                break;

            case "ETC_STR_A":
                qty = Integer.parseInt(mcbyDvo.getEtcStrAGdQty());
                break;
            case "ETC_STR_E":
                qty = Integer.parseInt(mcbyDvo.getEtcStrEGdQty());
                break;

            case "NOM_STR_A":
                qty = Integer.parseInt(mcbyDvo.getNomOstrAGdQty());
                break;
            case "NOM_STR_B":
                qty = Integer.parseInt(mcbyDvo.getNomOstrBGdQty());
                break;
            case "NOM_STR_E":
                qty = Integer.parseInt(mcbyDvo.getNomOstrEGdQty());
                break;
            case "NOM_STR_R":
                qty = Integer.parseInt(mcbyDvo.getNomOstrRGdQty());
                break;

            case "QOM_ASN_A":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrAGdQty());
                break;
            case "QOM_ASN_B":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrBGdQty());
                break;
            case "QOM_ASN_E":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrEGdQty());
                break;
            case "QOM_ASN_R":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrRGdQty());
                break;

            case "QOM_MMT_A":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrAGdQty());
                break;
            case "QOM_MMT_B":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrBGdQty());
                break;
            case "QOM_MMT_E":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrEGdQty());
                break;
            case "QOM_MMT_R":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrRGdQty());
                break;

            case "RTNGD_STR_A":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrInsiAGdQty());
                break;
            case "RTNGD_STR_B":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrInsiBGdQty());
                break;
            case "RTNGD_STR_E":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrInsiEGdQty());
                break;
            case "RTNGD_STR_R":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrInsiRGdQty());
                break;

            case "RTNGD_OTSD_STR_A":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdAGdQty());
                break;
            case "RTNGD_OTSD_STR_B":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdBGdQty());
                break;
            case "RTNGD_OTSD_STR_E":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdEGdQty());
                break;
            case "RTNGD_OTSD_STR_R":
                qty = Integer.parseInt(mcbyDvo.getRtngdStrOtsdRGdQty());
                break;

            case "STOC_CTR_STR_A":
                qty = Integer.parseInt(mcbyDvo.getEtcStrAGdQty1());
                break;
            case "STOC_CTR_STR_B":
                qty = Integer.parseInt(mcbyDvo.getEtcStrBGdQty1());
                break;
            case "STOC_CTR_STR_E":
                qty = Integer.parseInt(mcbyDvo.getEtcStrEGdQty1());
                break;
            case "STOC_CTR_STR_R":
                qty = Integer.parseInt(mcbyDvo.getPitmStocRGdQty());
                break;

            case "NOM_OSTR_A":
                qty = Integer.parseInt(mcbyDvo.getNomOstrAGdQty());
                break;
            case "NOM_OSTR_B":
                qty = Integer.parseInt(mcbyDvo.getNomOstrBGdQty());
                break;
            case "NOM_OSTR_E":
                qty = Integer.parseInt(mcbyDvo.getNomOstrEGdQty());
                break;
            case "NOM_OSTR_R":
                qty = Integer.parseInt(mcbyDvo.getNomOstrRGdQty());
                break;

            case "QOM_ASN_OSTR_A":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrAGdQty());
                break;
            case "QOM_ASN_OSTR_B":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrBGdQty());
                break;
            case "QOM_ASN_OSTR_E":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrEGdQty());
                break;
            case "QOM_ASN_OSTR_R":
                qty = Integer.parseInt(mcbyDvo.getQomAsnOstrRGdQty());
                break;

            case "QOM_MMT_OSTR_A":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrAGdQty());
                break;
            case "QOM_MMT_OSTR_B":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrBGdQty());
                break;
            case "QOM_MMT_OSTR_E":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrEGdQty());
                break;
            case "QOM_MMT_OSTR_R":
                qty = Integer.parseInt(mcbyDvo.getQomMmtOstrRGdQty());
                break;

            case "RTNGD_OSTR_INSI_A":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiAGdQty());
                break;
            case "RTNGD_OSTR_INSI_B":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiBGdQty());
                break;
            case "RTNGD_OSTR_INSI_E":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiEGdQty());
                break;
            case "RTNGD_OSTR_INSI_R":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrInsiRGdQty());
                break;

            case "RTNGD_OSTR_OTSD_A":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdAGdQty());
                break;
            case "RTNGD_OSTR_OTSD_B":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdBGdQty());
                break;
            case "RTNGD_OSTR_OTSD_E":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdEGdQty());
                break;
            case "RTNGD_OSTR_OTSD_R":
                qty = Integer.parseInt(mcbyDvo.getRtngdOstrOtsdRGdQty());
                break;

            case "OTSD_OSTR_SELL_A":
                break;
            case "OTSD_OSTR_SELL_B":
                break;

            case "OTSD_OSTR_DSU_A":
                break;
            case "OTSD_OSTR_DSU_B":
                break;
            case "OTSD_OSTR_DSU_E":
                break;
            case "OTSD_OSTR_DSU_R":
                break;

            case "OTSD_OSTR_WK_A":
                break;
            case "OTSD_OSTR_WK_B":
                break;
            case "OTSD_OSTR_WK_E":
                break;
            case "OTSD_OSTR_WK_R":
                break;

            case "OTSD_OSTR_ETC_A":
                break;
            case "OTSD_OSTR_ETC_B":
                break;
            case "OTSD_OSTR_ETC_E":
                break;
            case "OTSD_OSTR_ETC_R":
                break;

            case "OTSD_OSTR_REFR_A":
                break;
            case "OTSD_OSTR_REFR_B":
                break;
            case "OTSD_OSTR_REFR_E":
                break;
            case "OTSD_OSTR_REFR_R":
                break;

            case "STOC_CTR_OSTR_A":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty());
                break;
            case "STOC_CTR_OSTR_B":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty());
                break;
            case "STOC_CTR_OSTR_E":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty());
                break;
            case "STOC_CTR_OSTR_R":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty());
                break;

            case "STOC_ACINSP_OSTR_A":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspAGdQty());
                break;
            case "STOC_ACINSP_OSTR_B":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspBGdQty());
                break;
            case "STOC_ACINSP_OSTR_E":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspEGdQty());
                break;
            case "STOC_ACINSP_OSTR_R":
                qty = Integer.parseInt(mcbyDvo.getStocAcinspRGdQty());
                break;

        }
        return qty;
    }

    //PITM_STOC : A
    protected void setMinusPitmStocAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocAGdQty = getQty(mcbyDvo, "PITM_STOC_A");
        int sumQty = strPitmStocAGdQty - qty;

        /*시점재고부수가 MINUS 발생 오류!*/
        BizAssert.isTrue(sumQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

        dvo.setPitmStocAGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : B
    protected void setMinusPitmStocBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocBGdQty = getQty(mcbyDvo, "PITM_STOC_B");
        int sumQty = strPitmStocBGdQty - qty;

        /*시점재고부수가 MINUS 발생 오류!*/
        BizAssert.isTrue(sumQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

        dvo.setPitmStocBGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : E
    protected void setMinusPitmStocEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocEGdQty = getQty(mcbyDvo, "PITM_STOC_B");
        int sumQty = strPitmStocEGdQty - qty;

        /*시점재고부수가 MINUS 발생 오류!*/
        BizAssert.isTrue(sumQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

        dvo.setPitmStocEGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : R
    protected void setMinusPitmStocRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocRGdQty = getQty(mcbyDvo, "PITM_STOC_R");
        int sumQty = strPitmStocRGdQty - qty;

        /*시점재고부수가 MINUS 발생 오류!*/
        BizAssert.isTrue(sumQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

        dvo.setPitmStocRGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : A
    protected void setSumPitmStocAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocAGdQty = getQty(mcbyDvo, "PITM_STOC_A");
        int sumQty = strPitmStocAGdQty + qty;
        dvo.setPitmStocAGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : B
    protected void setSumPitmStocBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocBGdQty = getQty(mcbyDvo, "PITM_STOC_B");
        int sumQty = strPitmStocBGdQty + qty;
        dvo.setPitmStocBGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : E
    protected void setSumPitmStocEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocEGdQty = getQty(mcbyDvo, "PITM_STOC_B");
        int sumQty = strPitmStocEGdQty + qty;
        dvo.setPitmStocEGdQty(String.valueOf(sumQty));
    }

    //PITM_STOC : R
    protected void setSumPitmStocRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPitmStocRGdQty = getQty(mcbyDvo, "PITM_STOC_R");
        int sumQty = strPitmStocRGdQty + qty;
        dvo.setPitmStocRGdQty(String.valueOf(sumQty));
    }

    //PRCHS_STR : A
    protected void setSumPrchsStrQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPrchsStrQty = getQty(mcbyDvo, "PRCHS_STR_A");
        int sumQty = strPrchsStrQty + qty;
        dvo.setPrchsStrQty(String.valueOf(sumQty));
    }

    //PRCHS_STR : B
    protected void setSumPrchsStrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strPrchsStrBGdQty = getQty(mcbyDvo, "PRCHS_STR_B");
        int sumQty = strPrchsStrBGdQty + qty;
        dvo.setPrchsStrBGdQty(String.valueOf(sumQty));
    }

    //ETC_STR : A
    protected void setSumEtcStrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strEtcStrAGdQty = getQty(mcbyDvo, "ETC_STR_A");
        int sumQty = strEtcStrAGdQty + qty;
        dvo.setEtcStrAGdQty(String.valueOf(sumQty));
    }

    //ETC_STR : E
    protected void setSumEtcStrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strEtcStrEGdQty = getQty(mcbyDvo, "ETC_STR_E");
        int sumQty = strEtcStrEGdQty + qty;
        dvo.setEtcStrEGdQty(String.valueOf(sumQty));
    }

    //NOM_STR : A
    protected void setSumNomStrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrAGdQTy = getQty(mcbyDvo, "NOM_STR_A");
        int sumQty = strNomOstrAGdQTy + qty;
        dvo.setNomOstrAGdQty(String.valueOf(sumQty));
    }

    //NOM_STR : B
    protected void setSumNomStrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrBGdQTy = getQty(mcbyDvo, "NOM_STR_B");
        int sumQty = strNomOstrBGdQTy + qty;
        dvo.setNomOstrBGdQty(String.valueOf(sumQty));
    }

    //NOM_STR : E
    protected void setSumNomStrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrEGdQTy = getQty(mcbyDvo, "NOM_STR_E");
        int sumQty = strNomOstrEGdQTy + qty;
        dvo.setNomOstrEGdQty(String.valueOf(sumQty));
    }

    //NOM_STR : R
    protected void setSumNomStrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrRGdQTy = getQty(mcbyDvo, "NOM_STR_R");
        int sumQty = strNomOstrRGdQTy + qty;
        dvo.setNomOstrRGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN : A
    protected void setSumQomAsnAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnStrAGdQty = getQty(mcbyDvo, "QOM_ASN_A");
        int sumQty = strQomAsnStrAGdQty + qty;
        dvo.setQomAsnStrAGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN : B
    protected void setSumQomAsnBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnStrBGdQty = getQty(mcbyDvo, "QOM_ASN_B");
        int sumQty = strQomAsnStrBGdQty + qty;
        dvo.setQomAsnStrBGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN : E
    protected void setSumQomAsnEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnStrEGdQty = getQty(mcbyDvo, "QOM_ASN_E");
        int sumQty = strQomAsnStrEGdQty + qty;
        dvo.setQomAsnStrEGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN : R
    protected void setSumQomAsnRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnStrRGdQty = getQty(mcbyDvo, "QOM_ASN_R");
        int sumQty = strQomAsnStrRGdQty + qty;
        dvo.setQomAsnStrRGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT : A
    protected void setSumQomMmtAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtStrAGdQty = getQty(mcbyDvo, "QOM_MMT_A");
        int sumQty = strQomMmtStrAGdQty + qty;
        dvo.setQomMmtStrAGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT : B
    protected void setSumQomMmtBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtStrBGdQty = getQty(mcbyDvo, "QOM_MMT_B");
        int sumQty = strQomMmtStrBGdQty + qty;
        dvo.setQomMmtStrBGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT : E
    protected void setSumQomMmtEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtStrEGdQty = getQty(mcbyDvo, "QOM_MMT_E");
        int sumQty = strQomMmtStrEGdQty + qty;
        dvo.setQomMmtStrEGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT : R
    protected void setSumQomMmtRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtStrRGdQty = getQty(mcbyDvo, "QOM_MMT_R");
        int sumQty = strQomMmtStrRGdQty + qty;
        dvo.setQomMmtStrRGdQty(String.valueOf(sumQty));
    }

    //RTNGD_STR : A
    protected void setSumRtngdStrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrInsiAGdQty = getQty(mcbyDvo, "RTNGD_STR_A");
        int sumQty = strRtngdStrInsiAGdQty + qty;
        dvo.setRtngdStrInsiAGdQty(String.valueOf(sumQty));
    }

    //RTNGD_STR : B
    protected void setSumRtngdStrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrInsiBGdQty = getQty(mcbyDvo, "RTNGD_STR_B");
        int sumQty = strRtngdStrInsiBGdQty + qty;
        dvo.setRtngdStrInsiBGdQty(String.valueOf(sumQty));
    }

    //RTNGD_STR : E
    protected void setSumRtngdStrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrInsiEGdQty = getQty(mcbyDvo, "RTNGD_STR_E");
        int sumQty = strRtngdStrInsiEGdQty + qty;
        dvo.setRtngdStrInsiEGdQty(String.valueOf(sumQty));
    }

    //RTNGD_STR : R
    protected void setSumRtngdStrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrInsiRGdQty = getQty(mcbyDvo, "RTNGD_STR_R");
        int sumQty = strRtngdStrInsiRGdQty + qty;
        dvo.setRtngdStrInsiRGdQty(String.valueOf(sumQty));
    }

    //RTNGD_OTSD_STR : A
    protected void setSumRtngdOtsdStrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrOtsdAGdQty = getQty(mcbyDvo, "RTNGD_STR_A");
        int sumQty = strRtngdStrOtsdAGdQty + qty;
        dvo.setRtngdStrInsiAGdQty(String.valueOf(sumQty));
    }

    //RTNGD_OTSD_STR : B
    protected void setSumRtngdOtsdStrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrOtsdBGdQty = getQty(mcbyDvo, "RTNGD_STR_B");
        int sumQty = strRtngdStrOtsdBGdQty + qty;
        dvo.setRtngdStrInsiBGdQty(String.valueOf(sumQty));
    }

    //RTNGD_OTSD_STR : E
    protected void setSumRtngdOtsdStrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrOtsdEGdQty = getQty(mcbyDvo, "RTNGD_STR_E");
        int sumQty = strRtngdStrOtsdEGdQty + qty;
        dvo.setRtngdStrInsiEGdQty(String.valueOf(sumQty));
    }

    //RTNGD_OTSD_STR : R
    protected void setSumRtngdOtsdStrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtngdStrOtsdRGdQty = getQty(mcbyDvo, "RTNGD_STR_R");
        int sumQty = strRtngdStrOtsdRGdQty + qty;
        dvo.setRtngdStrInsiRGdQty(String.valueOf(sumQty));
    }

    //STOC_CTR_STR : A
    protected void setSumStocCtrStrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strEtcStrAGdQty1 = getQty(mcbyDvo, "STOC_CTR_STR_A");
        int sumQty = strEtcStrAGdQty1 + qty;
        dvo.setEtcStrAGdQty1(String.valueOf(sumQty));
    }

    //STOC_CTR_STR : B
    protected void setSumStocCtrStrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strEtcStrBGdQty1 = getQty(mcbyDvo, "STOC_CTR_STR_B");
        int sumQty = strEtcStrBGdQty1 + qty;
        dvo.setEtcStrBGdQty1(String.valueOf(sumQty));
    }

    //STOC_CTR_STR : E
    protected void setSumStocCtrStrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strEtcStrEGdQty1 = getQty(mcbyDvo, "STOC_CTR_STR_E");
        int sumQty = strEtcStrEGdQty1 + qty;
        dvo.setEtcStrEGdQty1(String.valueOf(sumQty));
    }

    //STOC_CTR_STR : R
    protected void setSumStocCtrStrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strEtcStrRGdQty1 = getQty(mcbyDvo, "STOC_CTR_STR_R");
        int sumQty = strEtcStrRGdQty1 + qty;
        dvo.setEtcStrRGdQty1(String.valueOf(sumQty));
    }

    //NOM_OSTR : A
    protected void setSumNomOstrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrAGdQty = getQty(mcbyDvo, "NOM_OSTR_A");
        int sumQty = strNomOstrAGdQty + qty;
        dvo.setNomOstrAGdQty(String.valueOf(sumQty));
    }

    //NOM_OSTR : B
    protected void setSumNomOstrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrBGdQty = getQty(mcbyDvo, "NOM_OSTR_B");
        int sumQty = strNomOstrBGdQty + qty;
        dvo.setNomOstrBGdQty(String.valueOf(sumQty));
    }

    //NOM_OSTR : E
    protected void setSumNomOstrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrEGdQty = getQty(mcbyDvo, "NOM_OSTR_E");
        int sumQty = strNomOstrEGdQty + qty;
        dvo.setNomOstrEGdQty(String.valueOf(sumQty));
    }

    //NOM_OSTR : R
    protected void setSumNomOstrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strNomOstrRGdQty = getQty(mcbyDvo, "NOM_OSTR_R");
        int sumQty = strNomOstrRGdQty + qty;
        dvo.setNomOstrRGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : A
    protected void setSumQomAsnOstrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnOstrAGdQty = getQty(mcbyDvo, "QOM_ASN_OSTR_A");
        int sumQty = strQomAsnOstrAGdQty + qty;
        dvo.setQomAsnOstrAGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : B
    protected void setSumQomAsnOstrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnOstrBGdQty = getQty(mcbyDvo, "QOM_ASN_OSTR_B");
        int sumQty = strQomAsnOstrBGdQty + qty;
        dvo.setQomAsnOstrBGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : E
    protected void setSumQomAsnOstrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnOstrEGdQty = getQty(mcbyDvo, "QOM_ASN_OSTR_E");
        int sumQty = strQomAsnOstrEGdQty + qty;
        dvo.setQomAsnOstrEGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : R
    protected void setSumQomAsnOstrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomAsnOstrRGdQty = getQty(mcbyDvo, "QOM_ASN_OSTR_R");
        int sumQty = strQomAsnOstrRGdQty + qty;
        dvo.setQomAsnOstrRGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT_OSTR : A
    protected void setSumQomMntOstrAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtOstrAGdQty = getQty(mcbyDvo, "QOM_MMT_OSTR_A");
        int sumQty = strQomMmtOstrAGdQty + qty;
        dvo.setQomMmtOstrAGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT_OSTR : B
    protected void setSumQomMntOstrBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtOstrBGdQty = getQty(mcbyDvo, "QOM_MMT_OSTR_B");
        int sumQty = strQomMmtOstrBGdQty + qty;
        dvo.setQomMmtOstrBGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT_OSTR : E
    protected void setSumQomMntOstrEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtOstrEGdQty = getQty(mcbyDvo, "QOM_MMT_OSTR_E");
        int sumQty = strQomMmtOstrEGdQty + qty;
        dvo.setQomMmtOstrEGdQty(String.valueOf(sumQty));
    }

    //QOM_MMT_OSTR : R
    protected void setSumQomMntOstrRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strQomMmtOstrRGdQty = getQty(mcbyDvo, "QOM_MMT_OSTR_R");
        int sumQty = strQomMmtOstrRGdQty + qty;
        dvo.setQomMmtOstrRGdQty(String.valueOf(sumQty));
    }

    //RTNGD_OSTR_INSI : A
    protected void setSumRtngdOstrInsiAGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtnGdOstrInsiAGdQty = getQty(mcbyDvo, "RTNGD_OSTR_INSI_A");
        int sumQty = strRtnGdOstrInsiAGdQty + qty;
        dvo.setRtngdOstrInsiAGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : B
    protected void setSumRtngdOstrInsiBGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtnGdOstrInsiBGdQty = getQty(mcbyDvo, "RTNGD_OSTR_INSI_A");
        int sumQty = strRtnGdOstrInsiBGdQty + qty;
        dvo.setRtngdOstrInsiBGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : E
    protected void setSumRtngdOstrInsiEGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtnGdOstrInsiEGdQty = getQty(mcbyDvo, "RTNGD_OSTR_INSI_A");
        int sumQty = strRtnGdOstrInsiEGdQty + qty;
        dvo.setRtngdOstrInsiEGdQty(String.valueOf(sumQty));
    }

    //QOM_ASN_OSTR : R
    protected void setSumRtngdOstrInsiRGdQty(WsnaMonthlyItemStocksDvo dvo, WsnaMonthlyItemStocksDvo mcbyDvo, int qty) {
        int strRtnGdOstrInsiRGdQty = getQty(mcbyDvo, "RTNGD_OSTR_INSI_A");
        int sumQty = strRtnGdOstrInsiRGdQty + qty;
        dvo.setRtngdOstrInsiRGdQty(String.valueOf(sumQty));
    }
}
