package com.kyowon.sms.wells.web.service.allocate.dvo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableCntrDvo {
    String cntrNo;
    String cntrSn;
    String adrZip;
    String basePdCd;
    String basePdNm;
    String pdctPdCd;
    String pdctPdNm;
    String pdGrpCd;
    String pdGrpNm;
    String cntrDt;
    String copnDvCd; // lcwgub 법인격구분 1:개인, 2법인
    String sellDscDbCd; // lcetc3 판매할인구분코드 1: 일시불할인구분코드, 2: 렌탈할인구분코드, 3: 멤버십할인구분코드
    String sdingCombin; // 모종 분리/결합
}
