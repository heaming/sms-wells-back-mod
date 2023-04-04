package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSnopInterfaceDataDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncSnopInterfaceDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * W-SV-S-0024 S&OP 인터페이스 자료 생성
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.04.03
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncSnopInterfaceDataService {

    private final WsncSnopInterfaceDataMapper mapper;

    // @see [SP_IF_KIWI_SNOP] SNOP 인터페이스 자료 생성
    public int saveSnopInterfaceDatas() {
        log.info("[WSNC] saveSnopInterfaceDatas start");

        int processCount = 0;

        // 1. TB_IFIN_SAP_MCPD_CRCV_ETXT (SAP자재코드상품코드수신전문) DATA 생성
        processCount += this.mapper.deleteSapMatPdCdReceiveTxt();
        processCount += this.mapper.insertSapMatPdCdReceiveTxt();

        // 2. TB_IFIN_SNOP_MECT_SEND_ETXT (SNOP자재예정건수송신전문) DATA 생성
        processCount += this.mapper.deleteSapMatExpectedSendTxt();
        processCount += this.mapper.insertSapMatExpectedSendTxt();

        // 3. TB_IFIN_SNOP_MSQTY_SEND_ETXT (SNOP자재입고수량송신전문) DATA 생성
        this.saveSapMatStrQtySendTxt();

        // 4. TB_IFIN_SNOP_FNDQT_SEND_ETXT (SNOP필터소요예상수량송신전문) DATA 생성
        processCount += this.mapper.deleteSapFilterNeedQtySendTxt();
        processCount += this.mapper.insertSapFilterNeedQtySendTxt();

        log.info("[WSNC] saveSnopInterfaceDatas end");
        return processCount;
    }

    private void saveSapMatStrQtySendTxt() {
        this.mapper.deleteSapMatStrQtySendTxt();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        String thisMonth = sdf.format(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        String lastMonth = sdf.format(cal.getTime());

        String[] apyYmArray = new String[] {lastMonth, thisMonth};
        List<String> apyYms = Arrays.asList(apyYmArray);

        apyYms.forEach((apyYm) -> {
            List<WsncSnopInterfaceDataDvo> dvos = this.mapper.selectSapMatStrQtySendTxt(apyYm);
            for (WsncSnopInterfaceDataDvo dvo : dvos) {
                this.mapper.insertSapMatStrQtySendTxt(dvo);
            }
        });
    }

}
