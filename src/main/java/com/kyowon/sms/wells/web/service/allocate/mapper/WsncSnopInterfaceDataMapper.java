package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSnopInterfaceDataDvo;

/**
 * <pre>
 * W-SV-S-0024 S&OP 인터페이스 자료 생성
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.04.03
 */
@Mapper
public interface WsncSnopInterfaceDataMapper {

    int deleteSapMatPdCdReceiveTxt();

    int insertSapMatPdCdReceiveTxt();

    int deleteSapMatExpectedSendTxt();

    int insertSapMatExpectedSendTxt();

    int deleteSapMatStrQtySendTxt();

    int insertSapMatStrQtySendTxt(WsncSnopInterfaceDataDvo dvo);

    List<WsncSnopInterfaceDataDvo> selectSapMatStrQtySendTxt(String apyYm);

    int deleteSapFilterNeedQtySendTxt();

    int insertSapFilterNeedQtySendTxt();

}
